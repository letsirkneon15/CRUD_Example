package com.crud.pojo;

import java.io.Serializable;

public class Hs_Code implements Serializable {
	private static final long serialVersionUID = 1L;

	private String hsFirm;
	private String hsCust;
	private String hsPart;
	private String hsMura;
	private String hsAmur;
	private String hsPptc;
	private String hsH40c;
	private String hsCode;
	private int hsDuty;
	private String hsEProdName;
	private String hsLProdName;
	private String hsCusJudge;
	private String hsCrtUsr;
	private int hsCrtDate;
	private int hsCrtTime;
	private String hsUpdUsr;
	private int hsUpdDate;
	private int hsUpdTime;
	
	public Hs_Code() {
		super();
		
	}
	
	public Hs_Code(String hsFirm, String hsMura, String hsPptc) {
		super();
		this.hsFirm = hsFirm;
		this.hsMura = hsMura;
		this.hsPptc = hsPptc;
	}
	

	public Hs_Code(String hsFirm, String hsCust, String hsPart, String hsMura, String hsPptc) {
		super();
		this.hsFirm = hsFirm;
		this.hsCust = hsCust;
		this.hsPart = hsPart;
		this.hsMura = hsMura;
		this.hsPptc = hsPptc;
	}

	
	public Hs_Code(String hsFirm, String hsCust, String hsPart, String hsMura, String hsAmur, String hsPptc,
			String hsH40c, String hsCode) {
		super();
		this.hsFirm = hsFirm;
		this.hsCust = hsCust;
		this.hsPart = hsPart;
		this.hsMura = hsMura;
		this.hsAmur = hsAmur;
		this.hsPptc = hsPptc;
		this.hsH40c = hsH40c;
		this.hsCode = hsCode;
	}
	
	public Hs_Code(String hsFirm, String hsCust, String hsPart, String hsMura, String hsAmur, String hsPptc,
			String hsH40c, String hsCode, int hsDuty, String hsEProdName, String hsLProdName, String hsCusJudge,
			String hsCrtUsr, int hsCrtDate, int hsCrtTime, String hsUpdUsr, int hsUpdDate, int hsUpdTime) {
		super();
		this.hsFirm = hsFirm;
		this.hsCust = hsCust;
		this.hsPart = hsPart;
		this.hsMura = hsMura;
		this.hsAmur = hsAmur;
		this.hsPptc = hsPptc;
		this.hsH40c = hsH40c;
		this.hsCode = hsCode;
		this.hsDuty = hsDuty;
		this.hsEProdName = hsEProdName;
		this.hsLProdName = hsLProdName;
		this.hsCusJudge = hsCusJudge;
		this.hsCrtUsr = hsCrtUsr;
		this.hsCrtDate = hsCrtDate;
		this.hsCrtTime = hsCrtTime;
		this.hsUpdUsr = hsUpdUsr;
		this.hsUpdDate = hsUpdDate;
		this.hsUpdTime = hsUpdTime;
	}
	public String getHsFirm() {
		return hsFirm;
	}
	public void setHsFirm(String hsFirm) {
		this.hsFirm = hsFirm;
	}
	public String getHsCust() {
		return hsCust;
	}
	public void setHsCust(String hsCust) {
		this.hsCust = hsCust;
	}
	public String getHsPart() {
		return hsPart;
	}
	public void setHsPart(String hsPart) {
		this.hsPart = hsPart;
	}
	public String getHsMura() {
		return hsMura;
	}
	public void setHsMura(String hsMura) {
		this.hsMura = hsMura;
	}
	public String getHsAmur() {
		return hsAmur;
	}
	public void setHsAmur(String hsAmur) {
		this.hsAmur = hsAmur;
	}
	public String getHsPptc() {
		return hsPptc;
	}
	public void setHsPptc(String hsPptc) {
		this.hsPptc = hsPptc;
	}
	public String getHsH40c() {
		return hsH40c;
	}
	public void setHsH40c(String hsH40c) {
		this.hsH40c = hsH40c;
	}
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	public int getHsDuty() {
		return hsDuty;
	}
	public void setHsDuty(int hsDuty) {
		this.hsDuty = hsDuty;
	}
	public String getHsEProdName() {
		return hsEProdName;
	}
	public void setHsEProdName(String hsEProdName) {
		this.hsEProdName = hsEProdName;
	}

	public String getHsLProdName() {
		return hsLProdName;
	}

	public void setHsLProdName(String hsLProdName) {
		this.hsLProdName = hsLProdName;
	}
	public String getHsCusJudge() {
		return hsCusJudge;
	}
	public void setHsCusJudge(String hsCusJudge) {
		this.hsCusJudge = hsCusJudge;
	}
	public String getHsCrtUsr() {
		return hsCrtUsr;
	}
	public void setHsCrtUsr(String hsCrtUsr) {
		this.hsCrtUsr = hsCrtUsr;
	}
	public int getHsCrtDate() {
		return hsCrtDate;
	}
	public void setHsCrtDate(int hsCrtDate) {
		this.hsCrtDate = hsCrtDate;
	}
	public int getHsCrtTime() {
		return hsCrtTime;
	}
	public void setHsCrtTime(int hsCrtTime) {
		this.hsCrtTime = hsCrtTime;
	}
	public String getHsUpdUsr() {
		return hsUpdUsr;
	}
	public void setHsUpdUsr(String hsUpdUsr) {
		this.hsUpdUsr = hsUpdUsr;
	}
	public int getHsUpdDate() {
		return hsUpdDate;
	}
	public void setHsUpdDate(int hsUpdDate) {
		this.hsUpdDate = hsUpdDate;
	}
	public int getHsUpdTime() {
		return hsUpdTime;
	}
	public void setHsUpdTime(int hsUpdTime) {
		this.hsUpdTime = hsUpdTime;
	}
	
}
