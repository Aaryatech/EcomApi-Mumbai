package com.ats.ecomapi.mst_model;

import java.util.Date;

import javax.persistence.Entity;
 
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetCustomerInfo {
	
	

	@Id
 	private int custId;

	private String custName;

	private String custMobileNo;

	private String emailId;

	private String profilePic;

	private String cityName;

	private int companyId;

	private Date dateOfBirth;

	private int custGender;

	private int ageRange;

	private int languageId;
 

	private int isActive;
	private int delStatus;
	
	private String exVar1;
	
	private String companyName;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustMobileNo() {
		return custMobileNo;
	}

	public void setCustMobileNo(String custMobileNo) {
		this.custMobileNo = custMobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getCustGender() {
		return custGender;
	}

	public void setCustGender(int custGender) {
		this.custGender = custGender;
	}

	public int getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(int ageRange) {
		this.ageRange = ageRange;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

 

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "GetCustomerInfo [custId=" + custId + ", custName=" + custName + ", custMobileNo=" + custMobileNo
				+ ", emailId=" + emailId + ", profilePic=" + profilePic + ", cityName=" + cityName + ", companyId="
				+ companyId + ", dateOfBirth=" + dateOfBirth + ", custGender=" + custGender + ", ageRange=" + ageRange
				+ ", languageId=" + languageId + ", isActive=" + isActive + ", delStatus=" + delStatus + ", exVar1="
				+ exVar1 + ", companyName=" + companyName + "]";
	}

	
}
