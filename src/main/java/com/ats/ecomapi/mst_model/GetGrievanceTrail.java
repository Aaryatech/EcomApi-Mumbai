package com.ats.ecomapi.mst_model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

//Mahendra Singh 2020-10-27
//Desc- To Show Grievance Trail  List at Admin end.

@Entity
public class GetGrievanceTrail {

	@Id
	private int trailId;

	private int grievanceId;
	private String remark;
	private int status;

	private int actionByUserId;
	private Date actionDateTime;
 

	private String exVar1;
	private String exVar2;
	
	private String identifiedRootCause;
	private int grievanceResType;
	private String resolutionDetail;
	private float repayAmt;
	private String repayDetails;
	private String actionByUserName;
	
	private String grivActionText;
	private int grivActionValue;
	public int getTrailId() {
		return trailId;
	}
	public void setTrailId(int trailId) {
		this.trailId = trailId;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getActionByUserId() {
		return actionByUserId;
	}
	public void setActionByUserId(int actionByUserId) {
		this.actionByUserId = actionByUserId;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy HH:mm:ss")
	public Date getActionDateTime() {
		return actionDateTime;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy HH:mm:ss")
	public void setActionDateTime(Date actionDateTime) {
		this.actionDateTime = actionDateTime;
	}

	public String getIdentifiedRootCause() {
		return identifiedRootCause;
	}
	public void setIdentifiedRootCause(String identifiedRootCause) {
		this.identifiedRootCause = identifiedRootCause;
	}

	public String getResolutionDetail() {
		return resolutionDetail;
	}
	public void setResolutionDetail(String resolutionDetail) {
		this.resolutionDetail = resolutionDetail;
	}
	public float getRepayAmt() {
		return repayAmt;
	}
	public void setRepayAmt(float repayAmt) {
		this.repayAmt = repayAmt;
	}
	public String getRepayDetails() {
		return repayDetails;
	}
	public void setRepayDetails(String repayDetails) {
		this.repayDetails = repayDetails;
	}
	public String getActionByUserName() {
		return actionByUserName;
	}
	public void setActionByUserName(String actionByUserName) {
		this.actionByUserName = actionByUserName;
	}
	public String getGrivActionText() {
		return grivActionText;
	}
	public void setGrivActionText(String grivActionText) {
		this.grivActionText = grivActionText;
	}
	public int getGrivActionValue() {
		return grivActionValue;
	}
	public void setGrivActionValue(int grivActionValue) {
		this.grivActionValue = grivActionValue;
	}
	public int getGrievanceId() {
		return grievanceId;
	}
	public void setGrievanceId(int grievanceId) {
		this.grievanceId = grievanceId;
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
	public int getGrievanceResType() {
		return grievanceResType;
	}
	public void setGrievanceResType(int grievanceResType) {
		this.grievanceResType = grievanceResType;
	}
	@Override
	public String toString() {
		return "GetGrievanceTrail [trailId=" + trailId + ", grievanceId=" + grievanceId + ", remark=" + remark
				+ ", status=" + status + ", actionByUserId=" + actionByUserId + ", actionDateTime=" + actionDateTime
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", identifiedRootCause=" + identifiedRootCause
				+ ", grievanceResType=" + grievanceResType + ", resolutionDetail=" + resolutionDetail + ", repayAmt="
				+ repayAmt + ", repayDetails=" + repayDetails + ", actionByUserName=" + actionByUserName
				+ ", grivActionText=" + grivActionText + ", grivActionValue=" + grivActionValue + "]";
	}
	
	
}
