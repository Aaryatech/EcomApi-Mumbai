package com.ats.ecomapi.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_filter_type")
public class FilterTypes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "filter_type_id")
	private int filterTypeId;

	@Column(name = "filter_type_name")
	private String filterTypeName;
	
	@Column(name = "filter_type_desc")
	private String filterTypeDesc;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "is_cost_affect")
	private int isCostAffect;
	
	@Column(name = "is_used_filter")
	private int isUsedFilter;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getFilterTypeId() {
		return filterTypeId;
	}

	public void setFilterTypeId(int filterTypeId) {
		this.filterTypeId = filterTypeId;
	}

	public String getFilterTypeName() {
		return filterTypeName;
	}

	public void setFilterTypeName(String filterTypeName) {
		this.filterTypeName = filterTypeName;
	}

	public String getFilterTypeDesc() {
		return filterTypeDesc;
	}

	public void setFilterTypeDesc(String filterTypeDesc) {
		this.filterTypeDesc = filterTypeDesc;
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

	public int getIsCostAffect() {
		return isCostAffect;
	}

	public void setIsCostAffect(int isCostAffect) {
		this.isCostAffect = isCostAffect;
	}

	public int getIsUsedFilter() {
		return isUsedFilter;
	}

	public void setIsUsedFilter(int isUsedFilter) {
		this.isUsedFilter = isUsedFilter;
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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "FilterTypes [filterTypeId=" + filterTypeId + ", filterTypeName=" + filterTypeName + ", filterTypeDesc="
				+ filterTypeDesc + ", companyId=" + companyId + ", isActive=" + isActive + ", delStatus=" + delStatus
				+ ", isCostAffect=" + isCostAffect + ", isUsedFilter=" + isUsedFilter + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}
