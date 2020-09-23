package com.ats.ecomapi.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "home_page_testimonials")
public class HomePageTestimonial {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "testimonials_id")
	private int testimonialsId;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "caption_name")
	private String captionName;
	
	@Column(name = "fr_ids")
	private String frIds;
	
	@Column(name = "images")
	private String images;
	
	@Column(name = "messages")
	private String messages;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "designation")
	private int designation;
	
	@Column(name = "sort_no")
	private int sortNo;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "insert_date_time")
	private String insertDateTime;
	
	@Column(name = "update_date_time")
	private String updateDateTime;
	
	@Column(name = "insert_user_id")
	private int insertUserId;
	
	@Column(name = "update_user_id")
	private int updateUserId;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getTestimonialsId() {
		return testimonialsId;
	}

	public void setTestimonialsId(int testimonialsId) {
		this.testimonialsId = testimonialsId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCaptionName() {
		return captionName;
	}

	public void setCaptionName(String captionName) {
		this.captionName = captionName;
	}

	public String getFrIds() {
		return frIds;
	}

	public void setFrIds(String frIds) {
		this.frIds = frIds;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDesignation() {
		return designation;
	}

	public void setDesignation(int designation) {
		this.designation = designation;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
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

	@Override
	public String toString() {
		return "HomePageTestimonial [testimonialsId=" + testimonialsId + ", companyId=" + companyId + ", captionName="
				+ captionName + ", frIds=" + frIds + ", images=" + images + ", messages=" + messages + ", name=" + name
				+ ", designation=" + designation + ", sortNo=" + sortNo + ", isActive=" + isActive + ", delStatus="
				+ delStatus + ", insertDateTime=" + insertDateTime + ", updateDateTime=" + updateDateTime
				+ ", insertUserId=" + insertUserId + ", updateUserId=" + updateUserId + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
}
