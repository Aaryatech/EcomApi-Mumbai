package com.ats.ecomapi.fe_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_fr_delivery_boy_assign")
public class FrDelvrBoyConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "del_boy_assign_id")
	private int delBoyAssignId;
	
	@Column(name = "del_boy_id")
	private int delBoyId;
	
	@Column(name = "fr_ids")
	private String frIds;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "add_dttime")
	private String addDttime;
	
	@Column(name = "edit_dttime")
	private String editDttime;
	
	@Column(name = "maker_usr_id")
	private int makerUsrId;
	
	@Column(name = "is_available")
	private int isAvailable;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getDelBoyAssignId() {
		return delBoyAssignId;
	}

	public void setDelBoyAssignId(int delBoyAssignId) {
		this.delBoyAssignId = delBoyAssignId;
	}

	public int getDelBoyId() {
		return delBoyId;
	}

	public void setDelBoyId(int delBoyId) {
		this.delBoyId = delBoyId;
	}

	public String getFrIds() {
		return frIds;
	}

	public void setFrIds(String frIds) {
		this.frIds = frIds;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public String getAddDttime() {
		return addDttime;
	}

	public void setAddDttime(String addDttime) {
		this.addDttime = addDttime;
	}

	public String getEditDttime() {
		return editDttime;
	}

	public void setEditDttime(String editDttime) {
		this.editDttime = editDttime;
	}

	public int getMakerUsrId() {
		return makerUsrId;
	}

	public void setMakerUsrId(int makerUsrId) {
		this.makerUsrId = makerUsrId;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
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
		return "FrDelvrBoyConfig [delBoyAssignId=" + delBoyAssignId + ", delBoyId=" + delBoyId + ", frIds=" + frIds
				+ ", companyId=" + companyId + ", delStatus=" + delStatus + ", isActive=" + isActive + ", addDttime="
				+ addDttime + ", editDttime=" + editDttime + ", makerUsrId=" + makerUsrId + ", isAvailable="
				+ isAvailable + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2="
				+ exVar2 + "]";
	}
	
}
