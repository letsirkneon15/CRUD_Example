package com.crud.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DownloadToExcel {
	
	private static XSSFWorkbook workbook = new XSSFWorkbook();
	private static XSSFSheet sheet = workbook.createSheet("HS Code"); 
	
	@SuppressWarnings("unused")
	public boolean createFile(String firmCode, String pptCode, String custCode, String hsCode, 
			String partNum, String tariff, String article) throws IOException{
		
		Boolean isCreated = false;
		String spChar = "%20";
		int ctr = 1;
		
		/* Change blanks to %20 as this web service doesn't allow blanks value */
		if(custCode.trim().equals("")) { custCode = spChar; }
		if(partNum.trim().equals("")) { partNum = spChar; }
		if(article.trim().equals("")) { article = spChar; }
		if(pptCode.trim().equals("")) { pptCode = spChar; }
		if(hsCode.trim().equals("")) { hsCode = spChar; }
		if(tariff.trim().equals("")) {tariff = spChar;}
		
		System.out.println("Cust code: " + custCode);
		
		/* Call the web service and parse it using JsonParser */
		String webServiceURL = "http://127.0.0.1:8999/HSCode/ForExcel/" + firmCode + "/" + pptCode + "/" 
				+ custCode + "/" + hsCode + "/" + partNum + "/" + tariff + "/" + article;
		JsonArray hsData = JsonParserUtil.getWebServiceData(webServiceURL);
		System.out.println(hsData);
		
		for(Object hsRes : hsData){
			JsonObject hs = (JsonObject) hsRes;
			
			/*create excel Column Name*/
			if(ctr == 1){	
				XSSFRow rowhead = sheet.createRow((short)0);
				rowhead.createCell(0).setCellValue("FIRM CODE");
				rowhead.createCell(1).setCellValue("CUSTOMER CODE");
				rowhead.createCell(2).setCellValue("PART NUMBER");
				rowhead.createCell(3).setCellValue("ARTICLE NUMBER");
				rowhead.createCell(4).setCellValue("ALTERNATE ARTICLE NO.");
				rowhead.createCell(5).setCellValue("PPT CODE");
				rowhead.createCell(6).setCellValue("H40 CODE");
				rowhead.createCell(7).setCellValue("HS CODE");	
				rowhead.createCell(8).setCellValue("DUTY");	
				rowhead.createCell(9).setCellValue("TARIFF(E)");	
				rowhead.createCell(10).setCellValue("TARIFF (L)");	
				rowhead.createCell(11).setCellValue("CUSTOM JUDGEMENT");	
				rowhead.createCell(12).setCellValue("CREATED BY");	
				rowhead.createCell(13).setCellValue("CREATED DATE");	
				rowhead.createCell(14).setCellValue("CREATED TIME");	
				rowhead.createCell(15).setCellValue("UPDATE BY");	
				rowhead.createCell(16).setCellValue("UPDATE DATE");	
				rowhead.createCell(17).setCellValue("UDATE TIME");	
			}
			
			/* Tariff Local to use internationalization (Thai Characters) */
			String TariffL = URLDecoder.decode(hs.get("hsLProdName").getAsString(), "UTF-8");
			
			/* Create excel row */
			XSSFRow row = sheet.createRow((short)ctr);
			row.createCell(0).setCellValue(hs.get("hsFirm").getAsString());
			row.createCell(1).setCellValue(hs.get("hsCust").getAsString());
			row.createCell(2).setCellValue(hs.get("hsPart").getAsString());
			row.createCell(3).setCellValue(hs.get("hsMura").getAsString());
			row.createCell(4).setCellValue(hs.get("hsPptc").getAsString());
			row.createCell(5).setCellValue(hs.get("hsAmur").getAsString());
			row.createCell(6).setCellValue(hs.get("hsH40c").getAsString());
			row.createCell(7).setCellValue(hs.get("hsCode").getAsString());
			row.createCell(8).setCellValue(hs.get("hsDuty").getAsString());
			row.createCell(9).setCellValue(hs.get("hsEProdName").getAsString());
			row.createCell(10).setCellValue(TariffL);
			row.createCell(11).setCellValue(hs.get("hsCusJudge").getAsString());
			row.createCell(12).setCellValue(hs.get("hsCrtUsr").getAsString());
			row.createCell(13).setCellValue(hs.get("hsCrtDate").getAsInt());
			row.createCell(14).setCellValue(hs.get("hsCrtTime").getAsInt());
			row.createCell(15).setCellValue(hs.get("hsUpdUsr").getAsString());
			row.createCell(16).setCellValue(hs.get("hsUpdDate").getAsInt());
			row.createCell(17).setCellValue(hs.get("hsUpdTime").getAsInt());
			ctr++;	
		}
		
		/*Create excel file in C directory */
		//String excelFile = "C:/HS_Code_Download.xlsx";
		String excelFile = "C:/Users/Public/HS_Code_Download.xlsx";
		if(ctr > 1){
			FileOutputStream fileOut = new FileOutputStream(excelFile);
			workbook.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");
			isCreated = true;
		}
		
		return isCreated;
	}

}
