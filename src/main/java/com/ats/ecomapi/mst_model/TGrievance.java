package com.ats.ecomapi.mst_model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

//Mahendra Singh 27-10-2020
@Entity
@Table(name="t_grievance")
public class TGrievance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "grievance_id")
	private int grievanceId;
	
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "grievance_type_id")
	private int grievanceTypeId;
	
	@Column(name = "grievance_subtype_id")
	private int grievanceSubtypeId;
	
	@Column(name = "grievance_no")
	private String grievanceNo;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "current_status")
	private int currentStatus;
	
	@Column(name = "insert_date_time")
	private String insertDateTime;
	
	@Column(name = "insert_by_id")
	private int insertById;
	
	@Column(name = "grievance_type_name")
	private String grievanceTypeName;
	
	@Column(name = "grievence_subtype_name")
	private String grievenceSubtypeName;
	
	@Column(name = "date")
	private Date date;

	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getGrievanceId() {
		return grievanceId;
	}

	public void setGrievanceId(int grievanceId) {
		this.grievanceId = grievanceId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public String getGrievanceNo() {
		return grievanceNo;
	}

	public void setGrievanceNo(String grievanceNo) {
		this.grievanceNo = grievanceNo;
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

	public String getInsertDateTime() {
		return insertDateTime;
	}

	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}

	public int getInsertById() {
		return insertById;
	}

	public void setInsertById(int insertById) {
		this.insertById = insertById;
	}

	public String getGrievanceTypeName() {
		return grievanceTypeName;
	}

	public void setGrievanceTypeName(String grievanceTypeName) {
		this.grievanceTypeName = grievanceTypeName;
	}

	public String getGrievenceSubtypeName() {
		return grievenceSubtypeName;
	}

	public void setGrievenceSubtypeName(String grievenceSubtypeName) {
		this.grievenceSubtypeName = grievenceSubtypeName;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	
	@Transient
	List<TGrievanceTrail> grievanceTrais;

	public List<TGrievanceTrail> getGrievanceTrais() {
		return grievanceTrais;
	}

	public void setGrievanceTrais(List<TGrievanceTrail> grievanceTrais) {
		this.grievanceTrais = grievanceTrais;
	}

	@Override
	public String toString() {
		return "TGrievance [grievanceId=" + grievanceId + ", orderId=" + orderId + ", grievanceTypeId="
				+ grievanceTypeId + ", grievanceSubtypeId=" + grievanceSubtypeId + ", grievanceNo=" + grievanceNo
				+ ", remark=" + remark + ", currentStatus=" + currentStatus + ", insertDateTime=" + insertDateTime
				+ ", insertById=" + insertById + ", grievanceTypeName=" + grievanceTypeName + ", grievenceSubtypeName="
				+ grievenceSubtypeName + ", date=" + date + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", grievanceTrais=" + grievanceTrais + "]";
	}

	
}
