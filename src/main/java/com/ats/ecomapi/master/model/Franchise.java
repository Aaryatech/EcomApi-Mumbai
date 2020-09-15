package com.ats.ecomapi.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "m_franchise")
public class Franchise {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	//Franchise Personal Details//////////////////////////////
	@Column(name = "fr_id")
	private int frId;

	@Column(name = "fr_code")
	private String frCode;
	
	@Column(name = "fr_name")
	private String frName;
	
	@Column(name = "fr_address")
	private String frAddress;
	
	@Column(name = "opening_date")
	private Date openingDate;
	
	@Column(name = "fr_image")
	private String frImage;
	
	@Column(name = "fr_rating")
	private float frRating;
	
	@Column(name = "fr_city")
	private String frCity;
	
	@Column(name = "fr_email_id")
	private String frEmailId;
	
	@Column(name = "fr_contact_no")
	private String frContactNo;
	
	
	//Franchise Other Details	/////////////////////////
	@Column(name = "fr_password")
	private String frPassword;

	@Column(name = "fda_number")
	private String fdaNumber;
	
	@Column(name = "gst_type")
	private String gstType;
	
	@Column(name = "gst_number")
	private String gstNumber;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "owners_birth_day")
	private Date ownersBirthDay;
	
	@Column(name = "fda_license_date_exp")
	private Date fdaLicenseDateExp;
	
	@Column(name = "shops_latitude")
	private float shopsLatitude;
	
	@Column(name = "shops_logitude")
	private float shopsLogitude;
	
	@Column(name = "is_active")
	private int isActive;
	
	
		
	//Franchise Bank Details	///////////////////////////
	@Column(name = "del_status")
	private String delStatus;
	
	@Column(name = "company_id")
	private int companyId;

	@Column(name = "pan_no")
	private String pan_no;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "co_bank_name")
	private String coBankName;
	
	@Column(name = "co_bank_branch_name")
	private String coBankBranchName;
	
	@Column(name = "co_bank_ifsc_code")
	private String coBankIfscCode;
	
	@Column(name = "co_bank_acc_no")
	private String coBankAccNo;	
	
	@Column(name = "payment_getway_link_same_as_parent")
	private String paymentGetwayLinkSameAsParent;
	
	@Column(name = "payment_getway_link")
	private String paymentGetwayLink;
	
	/******************************************/
	@Column(name = "no_of_km_area_cover")
	private float noOfKmAreaCover;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "add_date_time")
	private String addDateTime;
	
	@Column(name = "edit_date_time")
	private String editDateTime;
	
	@Column(name = "pincode_we_served")
	private String pincodeWeServed;
	
	@Column(name = "ex_float1")
	private float exFloat1;
	
	@Column(name = "ex_float2")
	private float exFloat2;
	
	@Column(name = "ex_float3")
	private float exFloat3;
	
	@Column(name = "ex_float4")
	private float exFloat4;
	
	@Column(name = "ex_float5")
	private float exFloat5;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;
	
	@Column(name = "ex_var3")
	private String exVar3;
	
	@Column(name = "ex_var4")
	private String exVar4;
	
	@Column(name = "ex_var5")
	private String exVar5;
	
	@Column(name = "ex_var6")
	private String exVar6;
	
	@Column(name = "ex_var7")
	private String exVar7;
	
	@Column(name = "ex_date1")
	private Date exDate1;
	
	@Column(name = "ex_date2")
	private Date exDate2;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;	
	
	@Column(name = "ex_int3")
	private int exInt3;

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getFrAddress() {
		return frAddress;
	}

	public void setFrAddress(String frAddress) {
		this.frAddress = frAddress;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getFrImage() {
		return frImage;
	}

	public void setFrImage(String frImage) {
		this.frImage = frImage;
	}

	public float getFrRating() {
		return frRating;
	}

	public void setFrRating(float frRating) {
		this.frRating = frRating;
	}

	public String getFrCity() {
		return frCity;
	}

	public void setFrCity(String frCity) {
		this.frCity = frCity;
	}

	public String getFrEmailId() {
		return frEmailId;
	}

	public void setFrEmailId(String frEmailId) {
		this.frEmailId = frEmailId;
	}

	public String getFrContactNo() {
		return frContactNo;
	}

	public void setFrContactNo(String frContactNo) {
		this.frContactNo = frContactNo;
	}

	public String getFrPassword() {
		return frPassword;
	}

	public void setFrPassword(String frPassword) {
		this.frPassword = frPassword;
	}

	public String getFdaNumber() {
		return fdaNumber;
	}

	public void setFdaNumber(String fdaNumber) {
		this.fdaNumber = fdaNumber;
	}

	public String getGstType() {
		return gstType;
	}

	public void setGstType(String gstType) {
		this.gstType = gstType;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getOwnersBirthDay() {
		return ownersBirthDay;
	}

	public void setOwnersBirthDay(Date ownersBirthDay) {
		this.ownersBirthDay = ownersBirthDay;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getFdaLicenseDateExp() {
		return fdaLicenseDateExp;
	}

	public void setFdaLicenseDateExp(Date fdaLicenseDateExp) {
		this.fdaLicenseDateExp = fdaLicenseDateExp;
	}

	public float getShopsLatitude() {
		return shopsLatitude;
	}

	public void setShopsLatitude(float shopsLatitude) {
		this.shopsLatitude = shopsLatitude;
	}

	public float getShopsLogitude() {
		return shopsLogitude;
	}

	public void setShopsLogitude(float shopsLogitude) {
		this.shopsLogitude = shopsLogitude;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCoBankName() {
		return coBankName;
	}

	public void setCoBankName(String coBankName) {
		this.coBankName = coBankName;
	}

	public String getCoBankBranchName() {
		return coBankBranchName;
	}

	public void setCoBankBranchName(String coBankBranchName) {
		this.coBankBranchName = coBankBranchName;
	}

	public String getCoBankIfscCode() {
		return coBankIfscCode;
	}

	public void setCoBankIfscCode(String coBankIfscCode) {
		this.coBankIfscCode = coBankIfscCode;
	}

	public String getCoBankAccNo() {
		return coBankAccNo;
	}

	public void setCoBankAccNo(String coBankAccNo) {
		this.coBankAccNo = coBankAccNo;
	}

	public String getPaymentGetwayLinkSameAsParent() {
		return paymentGetwayLinkSameAsParent;
	}

	public void setPaymentGetwayLinkSameAsParent(String paymentGetwayLinkSameAsParent) {
		this.paymentGetwayLinkSameAsParent = paymentGetwayLinkSameAsParent;
	}

	public String getPaymentGetwayLink() {
		return paymentGetwayLink;
	}

	public void setPaymentGetwayLink(String paymentGetwayLink) {
		this.paymentGetwayLink = paymentGetwayLink;
	}

	public float getNoOfKmAreaCover() {
		return noOfKmAreaCover;
	}

	public void setNoOfKmAreaCover(float noOfKmAreaCover) {
		this.noOfKmAreaCover = noOfKmAreaCover;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddDateTime() {
		return addDateTime;
	}

	public void setAddDateTime(String addDateTime) {
		this.addDateTime = addDateTime;
	}

	public String getEditDateTime() {
		return editDateTime;
	}

	public void setEditDateTime(String editDateTime) {
		this.editDateTime = editDateTime;
	}

	public String getPincodeWeServed() {
		return pincodeWeServed;
	}

	public void setPincodeWeServed(String pincodeWeServed) {
		this.pincodeWeServed = pincodeWeServed;
	}

	public float getExFloat1() {
		return exFloat1;
	}

	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}

	public float getExFloat2() {
		return exFloat2;
	}

	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
	}

	public float getExFloat3() {
		return exFloat3;
	}

	public void setExFloat3(float exFloat3) {
		this.exFloat3 = exFloat3;
	}

	public float getExFloat4() {
		return exFloat4;
	}

	public void setExFloat4(float exFloat4) {
		this.exFloat4 = exFloat4;
	}

	public float getExFloat5() {
		return exFloat5;
	}

	public void setExFloat5(float exFloat5) {
		this.exFloat5 = exFloat5;
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

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	public String getExVar4() {
		return exVar4;
	}

	public void setExVar4(String exVar4) {
		this.exVar4 = exVar4;
	}

	public String getExVar5() {
		return exVar5;
	}

	public void setExVar5(String exVar5) {
		this.exVar5 = exVar5;
	}

	public String getExVar6() {
		return exVar6;
	}

	public void setExVar6(String exVar6) {
		this.exVar6 = exVar6;
	}

	public String getExVar7() {
		return exVar7;
	}

	public void setExVar7(String exVar7) {
		this.exVar7 = exVar7;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getExDate1() {
		return exDate1;
	}

	public void setExDate1(Date exDate1) {
		this.exDate1 = exDate1;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getExDate2() {
		return exDate2;
	}

	public void setExDate2(Date exDate2) {
		this.exDate2 = exDate2;
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

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}

	@Override
	public String toString() {
		return "Franchise [frId=" + frId + ", frCode=" + frCode + ", frName=" + frName + ", frAddress=" + frAddress
				+ ", openingDate=" + openingDate + ", frImage=" + frImage + ", frRating=" + frRating + ", frCity="
				+ frCity + ", frEmailId=" + frEmailId + ", frContactNo=" + frContactNo + ", frPassword=" + frPassword
				+ ", fdaNumber=" + fdaNumber + ", gstType=" + gstType + ", gstNumber=" + gstNumber + ", pincode="
				+ pincode + ", ownersBirthDay=" + ownersBirthDay + ", fdaLicenseDateExp=" + fdaLicenseDateExp
				+ ", shopsLatitude=" + shopsLatitude + ", shopsLogitude=" + shopsLogitude + ", isActive=" + isActive
				+ ", delStatus=" + delStatus + ", companyId=" + companyId + ", pan_no=" + pan_no + ", city=" + city
				+ ", state=" + state + ", coBankName=" + coBankName + ", coBankBranchName=" + coBankBranchName
				+ ", coBankIfscCode=" + coBankIfscCode + ", coBankAccNo=" + coBankAccNo
				+ ", paymentGetwayLinkSameAsParent=" + paymentGetwayLinkSameAsParent + ", paymentGetwayLink="
				+ paymentGetwayLink + ", noOfKmAreaCover=" + noOfKmAreaCover + ", userId=" + userId + ", addDateTime="
				+ addDateTime + ", editDateTime=" + editDateTime + ", pincodeWeServed=" + pincodeWeServed
				+ ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2 + ", exFloat3=" + exFloat3 + ", exFloat4="
				+ exFloat4 + ", exFloat5=" + exFloat5 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3="
				+ exVar3 + ", exVar4=" + exVar4 + ", exVar5=" + exVar5 + ", exVar6=" + exVar6 + ", exVar7=" + exVar7
				+ ", exDate1=" + exDate1 + ", exDate2=" + exDate2 + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exInt3=" + exInt3 + "]";
	}
	
	
	
}