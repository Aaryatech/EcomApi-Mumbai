package com.ats.ecomapi.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mn_grievences_instruction")
public class GrievencesInstruction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "grievance_id")
	private int grievanceId;
	
	@Column(name = "caption")
	private String caption;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "grievence_type_id")
	private int grievenceTypeId;
	
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
	
	@Column(name = "ex_var3")
	private String exVar3;

	public int getGrievanceId() {
		return grievanceId;
	}

	public void setGrievanceId(int grievanceId) {
		this.grievanceId = grievanceId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getGrievenceTypeId() {
		return grievenceTypeId;
	}

	public void setGrievenceTypeId(int grievenceTypeId) {
		this.grievenceTypeId = grievenceTypeId;
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

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
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
		return "GrievencesInstruction [grievanceId=" + grievanceId + ", caption=" + caption + ", description="
				+ description + ", delStatus=" + delStatus + ", grievenceTypeId=" + grievenceTypeId + ", isActive="
				+ isActive + ", companyId=" + companyId + ", allowToCopy=" + allowToCopy + ", isParent=" + isParent
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + "]";
	}

	
}
