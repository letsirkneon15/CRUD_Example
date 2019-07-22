package com.crud.ws.backend;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.crud.ws.common.DbConnection;
import com.crud.ws.pojo.Hs_Code;

public class Manage_Hs_Code {
	
	private static SessionFactory factory;	
	
	/**
	 * This method return HS Code information from Oracle DB - HS_CODE
	 * For Excel File Listing
	 * @param info (firm) - firmCode 
	 * @param info (pptCode) - ppt Code
	 * @param info (custCode) - Customer code
	 * @param info (hsCode) - HS Code
	 * @param info (partNumber) - Part Number
	 * @param info (tariff) - Tariff Description
	 * @param info (article) - article
	 * @return List of HS_Code table
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public List<Hs_Code> getHSCode(String firmCode, String pptCode, String custCode, 
			String hsCode, String partNumber, String tariff, String article){
		
		List<Hs_Code> hsInfo = new ArrayList<Hs_Code>();
		
		if (factory==null) factory = DbConnection.hibernateConnection(null);
		Session session = factory.openSession();

		Transaction tx = null;
		hsInfo = null;

		Query query = null;
		String hql = "";
		
		hql = "FROM Hs_Code WHERE hsFirm = :firmCode AND hsPptC Like :pptCode AND hsCust Like :custCode AND "
				+ "hsCode Like :hsCode AND hsPart Like :partNumber AND hsEProdName Like :tariff AND hsMura Like :article";
		
		System.out.println(hql); 
		
		try {	
			
			query = session.createQuery(hql);
			query.setParameter("firmCode", firmCode);
			query.setParameter("pptCode", pptCode.trim() + '%');
			query.setParameter("custCode", custCode.trim() + '%');
			query.setParameter("hsCode", hsCode.trim() + '%');
			query.setParameter("partNumber", partNumber.trim() + '%');
			query.setParameter("tariff", tariff.trim() + '%');
			query.setParameter("article", article.trim() + '%');
			
			hsInfo = (List<Hs_Code>) query.list();		
		}	
		catch (Exception e) {
			
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return hsInfo;
		
	}	
}
