package com.crud.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.crud.pojo.Hs_Code;

public class ManageHsCode {

	private PreparedStatement pstatement;
	private ResultSet resultSet;

	/* Get today's date */
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat format2 = new SimpleDateFormat("HHmmss");
	private String todaysDate = format1.format(cal.getTime());
	private String todaysTime = format2.format(cal.getTime());

	public ArrayList<Hs_Code> getHsInfoArr(Connection conn, String firmCode, String custCode, 
			String partNum,	String murataType, String pptCode, String hsCode, String tariffDescE, int frRowNum, int toRowNum){

		ArrayList<Hs_Code> hsArr = new ArrayList<>();

		String qry = "SELECT outer.* FROM (SELECT rownum rn, inner.* from("
				+ "SELECT a.* from HS_CODE a WHERE HSFIRM = ? AND HSCUST LIKE ?" +
				" AND HSPART LIKE ? AND HSMURA LIKE ? AND HSPPTC LIKE ? AND HSCODE LIKE ? " +
				" AND HSEPRODNAME LIKE ? ORDER BY HSFIRM, HSCUST, HSPART,"
				+ "HSMURA, HSAMUR, HSPPTC, HSH40C, HSCODE, HSEPRODNAME) inner) outer WHERE outer.rn >= ? and outer.rn <= ?";

		System.out.println("Qry: " + qry + " frRowNum:" + frRowNum + " toRowNum: " + toRowNum);	

		try{
			pstatement = conn.prepareStatement(qry);
			pstatement.setString(1, firmCode.trim());
			pstatement.setString(2, custCode.trim() + '%');
			pstatement.setString(3, partNum.trim() + '%');
			pstatement.setString(4, murataType.trim() + '%');
			pstatement.setString(5, pptCode.trim() + '%');
			pstatement.setString(6, hsCode.trim() + '%');
			pstatement.setString(7, tariffDescE.trim() + '%');
			pstatement.setInt(8, frRowNum);
			pstatement.setInt(9, toRowNum);

			resultSet = pstatement.executeQuery();
			while(resultSet.next()){
				hsArr.add(new Hs_Code(
						resultSet.getString("HSFIRM"),
						resultSet.getString("HSCUST"),
						resultSet.getString("HSPART"),
						resultSet.getString("HSMURA"),
						resultSet.getString("HSAMUR"),
						resultSet.getString("HSPPTC"),
						resultSet.getString("HSH40C"),
						resultSet.getString("HSCODE"), 
						resultSet.getInt("HSDUTY"),
						resultSet.getString("HSEPRODNAME"),
						resultSet.getString("HSLPRODNAME"),
						resultSet.getString("HSCUSJUDGE"),
						resultSet.getString("HSCRTUSR"),
						resultSet.getInt("HSCRTDATE"),
						resultSet.getInt("HSCRTTIME"),
						resultSet.getString("HSUPDUSR"),
						resultSet.getInt("HSUPDDATE"),
						resultSet.getInt("HSUPDTIME")));		  
			}  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				resultSet.close();
				pstatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return hsArr;
	}

	public Hs_Code getHsInfo(Connection conn, String firmCode, String custCode, 
			String partNum,	String murataType, String pptCode, String h40Code, String hsCode){

		Hs_Code hs = new Hs_Code();

		String qry = "Select * from HS_CODE WHERE HSFIRM= ? AND HSCUST= ?" +
				" AND HSPART= ? AND HSMURA= ? AND HSPPTC= ? AND HSH40C= ? AND HSCODE= ?";		

		try{
			pstatement = conn.prepareStatement(qry);
			pstatement.setString(1, firmCode);
			pstatement.setString(2, custCode);
			pstatement.setString(3, partNum);
			pstatement.setString(4, murataType);
			pstatement.setString(5, pptCode);
			pstatement.setString(6, h40Code);
			pstatement.setString(7, hsCode);

			System.out.println("firmCode: " + firmCode + " custCode: " + custCode + " partNum: " + partNum + " murataType: " + 
					murataType + " pptCode: " + pptCode + " H40Code: " + h40Code + " hsCode: " + hsCode);

			resultSet = pstatement.executeQuery();
			while(resultSet.next()){
				hs = (new Hs_Code(
						resultSet.getString("HSFIRM"),
						resultSet.getString("HSCUST"),
						resultSet.getString("HSPART"),
						resultSet.getString("HSMURA"),
						resultSet.getString("HSAMUR"),
						resultSet.getString("HSPPTC"),
						resultSet.getString("HSH40C"),
						resultSet.getString("HSCODE"), 
						resultSet.getInt("HSDUTY"),
						resultSet.getString("HSEPRODNAME"),
						resultSet.getString("HSLPRODNAME"),
						resultSet.getString("HSCUSJUDGE"),
						resultSet.getString("HSCRTUSR"),
						resultSet.getInt("HSCRTDATE"),
						resultSet.getInt("HSCRTTIME"),
						resultSet.getString("HSUPDUSR"),
						resultSet.getInt("HSUPDDATE"),
						resultSet.getInt("HSUPDTIME")));		  
			}  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				resultSet.close();
				pstatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		System.out.println("hsCode: " + hs.getHsCode());
		return hs;
	}

	public boolean setHsInfo(Connection conn, Hs_Code hs){

		boolean isCreated = false;

		try{

			String qry = "INSERT INTO HS_CODE"
					+ "(HSFIRM, HSCUST, HSPART, HSMURA, HSAMUR, HSPPTC, HSH40C, HSCODE,"
					+ "HSDUTY, HSEPRODNAME, HSLPRODNAME, HSCUSJUDGE, HSCRTUSR, HSCRTDATE, HSCRTTIME, "
					+ "HSUPDUSR, HSUPDDATE, HSUPDTIME)"
					+ " VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstatement = conn.prepareStatement(qry);

			pstatement.setString(1, hs.getHsFirm());
			pstatement.setString(2, hs.getHsCust());
			pstatement.setString(3, hs.getHsPart());
			pstatement.setString(4, hs.getHsMura());
			pstatement.setString(5, hs.getHsAmur());
			pstatement.setString(6, hs.getHsPptc());
			pstatement.setString(7, hs.getHsH40c());
			pstatement.setString(8, hs.getHsCode());
			pstatement.setInt(9, hs.getHsDuty());
			pstatement.setString(10, hs.getHsEProdName());
			pstatement.setNString(11, hs.getHsLProdName());
			pstatement.setString(12, hs.getHsCusJudge());
			pstatement.setString(13, hs.getHsCrtUsr());
			pstatement.setInt(14, Integer.parseInt(todaysDate));
			pstatement.setInt(15, Integer.parseInt(todaysTime));
			pstatement.setString(16, hs.getHsUpdUsr());
			pstatement.setInt(17, hs.getHsUpdDate());
			pstatement.setInt(18, hs.getHsUpdTime());

			isCreated = pstatement.execute();
			pstatement.close();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pstatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return isCreated;
	}

	public int updateHsInfo(Connection conn, Hs_Code hs){

		int isUpdated = 0;	

		try{

			String qry = "UPDATE HS_CODE SET HSCODE=?, HSDUTY=?, HSEPRODNAME=?, HSLPRODNAME=?, HSCUSJUDGE=?,"
					+ " HSUPDUSR=?, HSUPDDATE=?, HSUPDTIME=? "
					+ " WHERE HSFIRM=? AND HSCUST=? AND HSPART=? AND HSMURA=? AND HSAMUR=? AND "
					+ " HSPPTC=? AND HSH40C=?";				

			pstatement = conn.prepareStatement(qry);

			pstatement.setString(1, hs.getHsCode());
			pstatement.setInt(2, hs.getHsDuty());
			pstatement.setString(3, hs.getHsEProdName());
			pstatement.setNString(4, hs.getHsLProdName());
			pstatement.setString(5, hs.getHsCusJudge());
			pstatement.setString(6, hs.getHsUpdUsr());
			pstatement.setInt(7, Integer.parseInt(todaysDate));
			pstatement.setInt(8, Integer.parseInt(todaysTime));
			pstatement.setString(9, hs.getHsFirm());
			pstatement.setString(10, hs.getHsCust());
			pstatement.setString(11, hs.getHsPart());
			pstatement.setString(12, hs.getHsMura());
			pstatement.setString(13, hs.getHsAmur());
			pstatement.setString(14, hs.getHsPptc());
			pstatement.setString(15, hs.getHsH40c());

			isUpdated = pstatement.executeUpdate();
			pstatement.close();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pstatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return isUpdated;
	}

	public boolean deleteHsInfo(Connection conn, Hs_Code hs){

		boolean isDeleted = false;	
		int deleteCount = 0;

		try{

			String qry = "DELETE FROM HS_CODE "
					+ " WHERE HSFIRM=? AND HSCUST=? AND HSPART=? AND HSMURA=? AND "
					+ " HSPPTC=? AND HSH40C=? AND HSCODE=?";				

			pstatement = conn.prepareStatement(qry);

			pstatement.setString(1, hs.getHsFirm());
			pstatement.setString(2, hs.getHsCust());
			pstatement.setString(3, hs.getHsPart());
			pstatement.setString(4, hs.getHsMura());
			pstatement.setString(5, hs.getHsPptc());
			pstatement.setString(6, hs.getHsH40c());
			pstatement.setString(7, hs.getHsCode());

			deleteCount = pstatement.executeUpdate();
			pstatement.close();

			if (deleteCount > 0){
				isDeleted = true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pstatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return isDeleted;
	}

	public int getRecsCount(Connection conn, String firmCode, String custCode, 
			String partNum,	String murataType, String pptCode, String hsCode, String tariffDescE){

		int totalRecs = 0;

		String qry = "Select count(*) as rowCount from HS_CODE WHERE HSFIRM = ? AND HSCUST LIKE ?" +
				" AND HSPART LIKE ? AND HSMURA LIKE ? AND HSPPTC LIKE ? AND HSCODE LIKE ? AND HSEPRODNAME LIKE ?";

		try{
			pstatement = conn.prepareStatement(qry);
			pstatement.setString(1, firmCode);
			pstatement.setString(2, custCode + '%');
			pstatement.setString(3, partNum + '%');
			pstatement.setString(4, murataType + '%');
			pstatement.setString(5, pptCode + '%');
			pstatement.setString(6, hsCode + '%');
			pstatement.setString(7, tariffDescE + '%');

			resultSet = pstatement.executeQuery();

			while (resultSet.next()){
				totalRecs = resultSet.getInt("rowCount");
				System.out.println("Total No. of Records: " + totalRecs);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				resultSet.close();
				pstatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return totalRecs;

	}
}
