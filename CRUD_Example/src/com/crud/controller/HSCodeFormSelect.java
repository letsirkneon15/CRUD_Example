package com.crud.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.crud.model.DownloadToExcel;
import com.crud.model.HardCodedData;
import com.crud.model.ManageHsCode;
import com.crud.pojo.Hs_Code;

/**
 * Servlet implementation class HSCodeInformation
 */
@WebServlet("/HSCodeFormSelect")
public class HSCodeFormSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Context initContext;
	private Context envContext;
	private DataSource dataSource;
	private Connection conn;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		try{
			initContext = new InitialContext();
			envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/system");
			conn = dataSource.getConnection();
			System.out.println("Connection: " + conn);
		}catch (NamingException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding( "UTF-8" );
		
		/* Variables declaration */
		String newPage = "/MainPage.jsp";
		//newPage = "/CreateNew.jsp";
		String firmCode = "";
		String firmName = "";
		String custCode = "";
		String partNum = "";
		String article = "";
		String pptCode = "";
		String hsCode = "";
		String tariffDescE = "";
		int totalRecs = 0; 
		String action = "";
		int frRowNum = 0;
		int toRowNum = 0;
		String button = "";
		String dsp = "display";

		ArrayList<Hs_Code> hsArr = new ArrayList<>();
		Hs_Code hsRec = new Hs_Code();

		/* Get Firm Code and Osa Code */
		ArrayList<String[]> firmCodeList = new HardCodedData().getFirmCode();		

		/* Retrieve session values from HsCodeSelection pojo */
		HttpSession session=request.getSession();

		if(session != null){		
			firmCode = (String) session.getAttribute("firmCode");
			firmName = (String) session.getAttribute("firmName");
			custCode = (String) session.getAttribute("custCode");
			partNum = (String) session.getAttribute("partNum");
			article = (String) session.getAttribute("article");
			pptCode = (String) session.getAttribute("pptCode");
			hsCode = (String) session.getAttribute("hsCode");
			tariffDescE = (String) session.getAttribute("tariffDescE");
			
			System.out.println("pptCode: " + pptCode);
			System.out.println("firmCode:" + firmCode);
		}

		/* Do this when submit button was clicked */
		action = request.getParameter("action");

		if(action != null){

			System.out.println("Action: " + action);
			if(action.equalsIgnoreCase("formPost")){
				firmCode = request.getParameter("firmCode").split(",")[0];
				firmName = request.getParameter("firmCode").split(",")[1];
				custCode = request.getParameter("custCode");
				partNum = request.getParameter("partNum");
				article = request.getParameter("article");
				pptCode = request.getParameter("pptCode");
				hsCode = request.getParameter("hsCode");
				tariffDescE = request.getParameter("tariffDescE");
				button = request.getParameter("button");
				
				System.out.println("button: " + button);
			
			/* Do this for updating of records */
			}else if(action.equalsIgnoreCase("editRec")){	
				
				int isUpdated = 0;
				button = dsp;
				String edtFirm = request.getParameter("edtFirm");
				String edtCust = request.getParameter("edtCust");
				String edtPartNum = request.getParameter("edtPartNum");
				String edtArticle = request.getParameter("edtArt");
				String edtArtWithoutPlus = request.getParameter("edtArtWithoutPlus");
				String edtPptCode = request.getParameter("edtPptCode");
				String edtH40Code = request.getParameter("edtH40Code");
				String edtHsCode = request.getParameter("edtHsCode");
				int    edtDuty = Integer.parseInt(request.getParameter("edtDuty"));
				String edtTariffEnglish = request.getParameter("edtTariffDescEnglish");
				String edtTariffLocal = request.getParameter("edtTariffDescLocal");
				String edtCusJudge = request.getParameter("edtCusJudge");
				
				System.out.println("From EditRec firmCode: " + edtFirm + " custCode: " + edtCust + " partNum: " + edtPartNum + " article: " + 
						edtArticle + " pptCode: " + edtPptCode + " H40Code: " + edtH40Code + " hsCode: " + edtHsCode + 
						" Tariff Local: " + edtTariffLocal);
				
				hsRec = new Hs_Code(edtFirm, edtCust, edtPartNum, edtArticle, edtArtWithoutPlus, 
						edtPptCode, edtH40Code, edtHsCode, edtDuty, edtTariffEnglish, edtTariffLocal,
						edtCusJudge, " ", 0, 0, " ", 0, 0);
				
				isUpdated = new ManageHsCode().updateHsInfo(conn, hsRec);
				if(isUpdated > 0){
					System.out.println("Record is successfully updated.");
				}else{
					System.out.println("Record is not updated");
				}	
				
			/* Creation of New Records */
			}else if(action.equalsIgnoreCase("createRec")){
				
				boolean isCreated = false;
				button = dsp;
				String crtFirmCode = request.getParameter("crtFirmCode").split(",")[0];
				String crtCust = request.getParameter("crtCust");
				String crtPartNum = request.getParameter("crtPartNum");
				String crtArticle = request.getParameter("crtArt");
				String crtArtWithoutPlus = request.getParameter("crtArtWithoutPlus");
				String crtPptCode = request.getParameter("crtPptCode");
				String crtH40Code = request.getParameter("crtH40Code");
				String crtHsCode = request.getParameter("crtHsCode");
				int    crtDuty = Integer.parseInt(request.getParameter("crtDuty"));
				String crtTariffEnglish = request.getParameter("crtTariffEnglish");
				String crtTariffLocal = request.getParameter("crtTariffLocal");
				String crtCusJudge = request.getParameter("crtCusJudge");
				
				hsRec = new Hs_Code(crtFirmCode, crtCust, crtPartNum, crtArticle, crtArtWithoutPlus, 
							crtPptCode, crtH40Code, crtHsCode, crtDuty, crtTariffEnglish, crtTariffLocal,
							crtCusJudge, "kristel", 0, 0, " ", 0, 0);
				
				isCreated = new ManageHsCode().setHsInfo(conn, hsRec);
				if(isCreated){
					System.out.println("Record is successfully created.");
				}else{
					System.out.println("Record is not created");
				}
				
			/* Deleting Records */
			}else if(action.equalsIgnoreCase("dltRec")){	
				
				boolean isDeleted = false;
				button = dsp;
				String dltFirm = request.getParameter("dltFirm");
				String dltCust = request.getParameter("dltCust");
				String dltPartNum = request.getParameter("dltPartNum");
				String dltArticle = request.getParameter("dltArt");
				String dltPptCode = request.getParameter("dltPptCode");
				String dltH40Code = request.getParameter("dltH40Code");
				String dltHsCode = request.getParameter("dltHsCode");
				
				System.out.println("From DltRec firmCode: " + dltFirm + " custCode: " + dltCust + " partNum: " + dltPartNum + " article: " + 
						dltArticle + " pptCode: " + dltPptCode + " H40Code: " + dltH40Code + " hsCode: " + dltHsCode); 
				
				hsRec = new Hs_Code(dltFirm, dltCust, dltPartNum, dltArticle, "", 
						dltPptCode, dltH40Code, dltHsCode, 0, "", "","", "", 0, 0, "", 0, 0);
				
				isDeleted = new ManageHsCode().deleteHsInfo(conn, hsRec);
				System.out.println("isDeleted: " + isDeleted);
				if(isDeleted){
					System.out.println("Record is successfully deleted.");
				}else{
					System.out.println("Record is not deleted");
				}
				
			/* Download Records in Excel File */
			}else if(action.equalsIgnoreCase("downloadRec")){
				
				String dldFirm = request.getParameter("dldFirm");
				String dldCust = request.getParameter("dldCust");
				String dldPartNum = request.getParameter("dldPartNum");
				String dldArticle = request.getParameter("dldArt");
				String dldPptCode = request.getParameter("dldPptCode");
				String dldHsCode = request.getParameter("dldHsCode");
				String dldTariffE = request.getParameter("dldTariffE");
				
				/* Call Java Class to Create Excel File */
				Boolean isCreated = new DownloadToExcel().createFile(dldFirm, dldPptCode, dldCust, dldHsCode, 
						dldPartNum, dldTariffE, dldArticle);
				
				System.out.println("FirmCode from DownloadRec: " + dldFirm);
			}
		}

		/*This is to store the last value of firmcode & osacode in the item list*/
		if(firmCode != null){		
			int firmIndex = 0;
			for(String[] arr : firmCodeList){
				if(firmCode.equals(arr[0])){
					firmIndex = firmCodeList.indexOf(arr);
					break;
				}
			}
			System.out.println("firmCodeList Index: " + firmIndex);
			firmCodeList.set(firmIndex, firmCodeList.get(0));
			firmCodeList.set(0,new String[]{firmCode, firmName});
		}
		
		totalRecs = new ManageHsCode().getRecsCount(conn, firmCode, custCode, partNum, article, pptCode, hsCode, tariffDescE);
		if(toRowNum == 0){
			toRowNum = totalRecs;
		}
		hsArr = new ManageHsCode().getHsInfoArr(conn, firmCode, custCode, partNum, article, pptCode, hsCode, tariffDescE, frRowNum, toRowNum);
		
		request.setAttribute("firmCodeList", firmCodeList);
		session.setAttribute("hsArr", hsArr);
		session.setAttribute("firmCode", firmCode);
		session.setAttribute("firmName", firmName);
		session.setAttribute("custCode", custCode);
		session.setAttribute("partNum", partNum);
		session.setAttribute("article", article);
		session.setAttribute("pptCode", pptCode);
		session.setAttribute("hsCode", hsCode);
		session.setAttribute("tariffDescE", tariffDescE);
		session.setAttribute("button", button);
		
		System.out.println("button after session: " + button);
			
		/* do redirection */ 
		ServletContext sContext = getServletContext();
		RequestDispatcher rDispatcher = sContext.getRequestDispatcher(newPage);
		rDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding( "UTF-8" ); 
		doGet(request, response);
	}

}
