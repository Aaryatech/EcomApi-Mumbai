package com.ats.ecomapi.master.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SpDayHomePageExlPdf {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private int spDayId;
	private String spdayName;
	private String spdayCaptionHomePage;
	private String spdayCaptionImageHomePage;
	private int isActive;
	private int delStatus;
	private int companyId;
	private int sortNo;
	private Date fromDate;
	private Date toDate;
	private String fromTime;
	private String toTime;
	private String frIds;
	private String tagIds;
	private String captionOnProductPage;
	private String insertDateTime;
	private String updateDateTime;
	private int insertUserId;
	private int updateUserId;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	private String franchises;
	private String filterName;
	
	public int getSpDayId() {
		return spDayId;
	}

	public void setSpDayId(int spDayId) {
		this.spDayId = spDayId;
	}

	public String getSpdayName() {
		return spdayName;
	}

	public void setSpdayName(String spdayName) {
		this.spdayName = spdayName;
	}

	public String getSpdayCaptionHomePage() {
		return spdayCaptionHomePage;
	}

	public void setSpdayCaptionHomePage(String spdayCaptionHomePage) {
		this.spdayCaptionHomePage = spdayCaptionHomePage;
	}

	public String getSpdayCaptionImageHomePage() {
		return spdayCaptionImageHomePage;
	}

	public void setSpdayCaptionImageHomePage(String spdayCaptionImageHomePage) {
		this.spdayCaptionImageHomePage = spdayCaptionImageHomePage;
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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getFrIds() {
		return frIds;
	}

	public void setFrIds(String frIds) {
		this.frIds = frIds;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getCaptionOnProductPage() {
		return captionOnProductPage;
	}

	public void setCaptionOnProductPage(String captionOnProductPage) {
		this.captionOnProductPage = captionOnProductPage;
	}

	public String getInsertDateTime() {
		return insertDateTime;
	}

	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}

	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public int getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(int insertUserId) {
		this.insertUserId = insertUserId;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
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

	public String getFranchises() {
		return franchises;
	}

	public void setFranchises(String franchises) {
		this.franchises = franchises;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	@Override
	public String toString() {
		return "SpDayHomePageExlPdf [spDayId=" + spDayId + ", spdayName=" + spdayName + ", spdayCaptionHomePage="
				+ spdayCaptionHomePage + ", spdayCaptionImageHomePage=" + spdayCaptionImageHomePage + ", isActive="
				+ isActive + ", delStatus=" + delStatus + ", companyId=" + companyId + ", sortNo=" + sortNo
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", fromTime=" + fromTime + ", toTime=" + toTime
				+ ", frIds=" + frIds + ", tagIds=" + tagIds + ", captionOnProductPage=" + captionOnProductPage
				+ ", insertDateTime=" + insertDateTime + ", updateDateTime=" + updateDateTime + ", insertUserId="
				+ insertUserId + ", updateUserId=" + updateUserId + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", franchises=" + franchises + ", filterName="
				+ filterName + "]";
	}

}
