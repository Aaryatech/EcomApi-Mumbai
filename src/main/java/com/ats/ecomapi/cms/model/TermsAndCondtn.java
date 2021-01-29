package com.ats.ecomapi.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="terms_and_condtn")
public class TermsAndCondtn {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "terms_id")
	private int termsId;
	
	@Column(name = "terms_txt")
	private String termsTxt;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "company_id")
	private int companyId;	
	
	@Column(name = "section_txt")
	private String sectionTxt;

	public int getTermsId() {
		return termsId;
	}

	public void setTermsId(int termsId) {
		this.termsId = termsId;
	}

	public String getTermsTxt() {
		return termsTxt;
	}

	public void setTermsTxt(String termsTxt) {
		this.termsTxt = termsTxt;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getSectionTxt() {
		return sectionTxt;
	}

	public void setSectionTxt(String sectionTxt) {
		this.sectionTxt = sectionTxt;
	}

	@Override
	public String toString() {
		return "TermsAndCondtn [termsId=" + termsId + ", termsTxt=" + termsTxt + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", delStatus=" + delStatus + ", companyId="
				+ companyId + ", sectionTxt=" + sectionTxt + "]";
	}
	

}
