package com.crud.ws.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.crud.ws.backend.Manage_Hs_Code;
import com.crud.ws.pojo.Hs_Code;
import com.google.gson.Gson;

@Path("/HSCode")
public class HSCode {

	/**
	 * This method return HS Code information from Oracle DB - HS_CODE
	 * For Excel file Listing
	 * @param info (firm) - firmCode 
	 * @param info (pptc) - PPT Code
	 * @param info (cust) - Customer Code
	 * @param info (hsCode) - HS Code
	 * @param info (part) - Part Number
	 * @param info (tariff) - tariff code
	 * @param info (article) - article no.
	 * @return Json String of HS_Code table
	 * @sampleHTTP http://127.0.0.1:8999/HSCode/ForExcel/AA/%20/%20/%20/%20/%20/%20
	 * json format  {"hsFirm": "AA","hsCust": "CS001","hsPart": "PART001","hsMura": "MURA01",
		"hsAmur": "MURA01","hsPptc": "AA","hsH40c": "AAAA","hsCode": "HSCODE1",
		"hsDuty": 0,"hsEProdName": "HSPRODA","hsLProdName": "ตัวกำเนิดแบบผสมคริสตัลสัญญาณกล้องในวงจร",
		"hsCusJudge": " ","hsCrtUsr": "KRISTEL","hsCrtDate": 20190526,"hsCrtTime": 193400,
		"hsUpdUsr": " ","hsUpdDate": 20190528,"hsUpdTime": 164103}
	 */
	@GET
	@Path("/ForExcel/{firm}/{pptc}/{cust}/{hsCode}/{part}/{tariff}/{article}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHSForImports(@PathParam("firm") String firm,
								  @PathParam("pptc") String pptc,
								  @PathParam("cust") String cust,
								  @PathParam("hsCode") String hsCode,
								  @PathParam("part") String part,
								  @PathParam("tariff") String tariff,
								  @PathParam("article") String article) {

		System.out.println("article from webService: " + article);	
		
		//Get HS Code information
		Manage_Hs_Code hsMgr = new Manage_Hs_Code();	
		List<Hs_Code> hs = hsMgr.getHSCode(firm, pptc, cust, hsCode, part, tariff, article);
		
		Gson gson = new Gson();
		String json = gson.toJson(hs);
		System.out.println(json);
		return json;
				
	}
}
