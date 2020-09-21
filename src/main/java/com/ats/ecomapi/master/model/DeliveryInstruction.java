package com.ats.ecomapi.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mn_delivery_instruction")
public class DeliveryInstruction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "instru_id")
	private int instruId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "instructn_caption")
	private String instructnCaption;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "allow_to_copy")
	private int allowToCopy;
	
	@Column(name = "is_parent")
	private int isParent;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getInstruId() {
		return instruId;
	}

	public void setInstruId(int instruId) {
		this.instruId = instruId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructnCaption() {
		return instructnCaption;
	}

	public void setInstructnCaption(String instructnCaption) {
		this.instructnCaption = instructnCaption;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public int getAllowToCopy() {
		return allowToCopy;
	}

	public void setAllowToCopy(int allowToCopy) {
		this.allowToCopy = allowToCopy;
	}

	public int getIsParent() {
		return isParent;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent;
	}

	@Override
	public String toString() {
		return "DeliveryInstruction [instruId=" + instruId + ", description=" + description + ", instructnCaption="
				+ instructnCaption + ", delStatus=" + delStatus + ", isActive=" + isActive + ", companyId=" + companyId
				+ ", allowToCopy=" + allowToCopy + ", isParent=" + isParent + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
		
	
}
