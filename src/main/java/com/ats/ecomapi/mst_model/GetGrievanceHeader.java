package com.ats.ecomapi.mst_model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

//Mahendra Singh 2020-10-27
//Desc- To Show Grievance List at Admin end.

@Entity
public class GetGrievanceHeader {

	@Id
	private int grievanceId;

	private int orderId;

	private int grievanceTypeId;

	private int grievanceSubtypeId;

	private String remark;

	private int currentStatus;

	private Date insertDateTime;

	private String insertUserName;

	private Date date;

	private String exVar1;

	private String exVar2;

	private String grievanceNo;

	private String orderNo;
	private Date orderDate;
	private int frId;
	private int custId;

	private String custName;
	private String custMobileNo;

	private String type;
	private String subType;

	private String frName;
	private String frCode;

	private int grivTrailCount;

	private int exInt1;

	public int getGrivTrailCount() {
		return grivTrailCount;
	}

	public void setGrivTrailCount(int grivTrailCount) {
		this.grivTrailCount = grivTrailCount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy HH:mm:ss")
	public Date getInsertDateTime() {
		return insertDateTime;
	}

	public void setInsertDateTime(Date insertDateTime) {
		this.insertDateTime = insertDateTime;
	}

	public String getInsertUserName() {
		return insertUserName;
	}

	public void setInsertUserName(String insertUserName) {
		this.insertUserName = insertUserName;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getDate() {
		return date;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public void setDate(Date date) {
		this.date = date;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getOrderDate() {
		return orderDate;
	}

	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}

	public int getGrievanceId() {
		return grievanceId;
	}

	public void setGrievanceId(int grievanceId) {
		this.grievanceId = grievanceId;
	}

	public int getGrievanceTypeId() {
		return grievanceTypeId;
	}

	public void setGrievanceTypeId(int grievanceTypeId) {
		this.grievanceTypeId = grievanceTypeId;
	}

	public int getGrievanceSubtypeId() {
		return grievanceSubtypeId;
	}

	public void setGrievanceSubtypeId(int grievanceSubtypeId) {
		this.grievanceSubtypeId = grievanceSubtypeId;
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

	public String getGrievanceNo() {
		return grievanceNo;
	}

	public void setGrievanceNo(String grievanceNo) {
		this.grievanceNo = grievanceNo;
	}

	public String getCustMobileNo() {
		return custMobileNo;
	}

	public void setCustMobileNo(String custMobileNo) {
		this.custMobileNo = custMobileNo;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	@Override
	public String toString() {
		return "GetGrievanceHeader [grievanceId=" + grievanceId + ", orderId=" + orderId + ", grievanceTypeId="
				+ grievanceTypeId + ", grievanceSubtypeId=" + grievanceSubtypeId + ", remark=" + remark
				+ ", currentStatus=" + currentStatus + ", insertDateTime=" + insertDateTime + ", insertUserName="
				+ insertUserName + ", date=" + date + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ","
				+ " grievanceNo=" + grievanceNo + ", orderNo=" + orderNo + ", orderDate=" + orderDate
				+ ", frId=" + frId + ", custId=" + custId + ", custName=" + custName + ", custMobileNo=" + custMobileNo
				+ ", type=" + type + ", subType=" + subType + ", frName=" + frName + ", frCode=" + frCode
				+ ", grivTrailCount=" + grivTrailCount + ", exInt1=" + exInt1 + "]";
	}

}
