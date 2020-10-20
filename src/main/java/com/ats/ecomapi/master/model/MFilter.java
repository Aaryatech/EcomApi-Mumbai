package com.ats.ecomapi.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_filter")
//Modified By-Sachin
//Modification Date-20-10-2020
//Desc-Added new fields 
public class MFilter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "filter_id")
	private int filterId;

	@Column(name = "filter_name")
	private String filterName;
	
	@Column(name = "filter_desc")
	private String filterDesc;
	
	@Column(name = "filter_type_id")
	private int filterTypeId;

	@Column(name = "used_for_filter")
	private int usedForFilter;
	
	@Column(name = "cost_affect")
	private int costAffect;
	
	@Column(name = "used_for_description")
	private int usedForDescription;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "is_parent")
	private int isParent;

	@Column(name = "allow_to_copy")
	private int allowToCopy;
	
	@Column(name = "sort_no")
	private int sortNo;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_int3")
	private int exInt3;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;
	
	@Column(name = "ex_var3")
	private String exVar3;
	
	
	//Sachin 20-10-2020 new fields total 5
	@Column(name = "add_on_type")
	private int addOnType;
	
	@Column(name = "add_on_rs")
	private float addOnRs;
	
	@Column(name = "is_tag_add")
	private int isTagAdd;
	
	@Column(name = "tag_id")
	private int tagId;
	
	@Column(name = "admin_name")
	private String adminName;
	//Sachin 20-10-2020 new fields total 5

	public int getFilterId() {
		return filterId;
	}

	public void setFilterId(int filterId) {
		this.filterId = filterId;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterDesc() {
		return filterDesc;
	}

	public void setFilterDesc(String filterDesc) {
		this.filterDesc = filterDesc;
	}

	public int getFilterTypeId() {
		return filterTypeId;
	}

	public void setFilterTypeId(int filterTypeId) {
		this.filterTypeId = filterTypeId;
	}

	public int getUsedForFilter() {
		return usedForFilter;
	}

	public void setUsedForFilter(int usedForFilter) {
		this.usedForFilter = usedForFilter;
	}

	public int getCostAffect() {
		return costAffect;
	}

	public void setCostAffect(int costAffect) {
		this.costAffect = costAffect;
	}

	public int getUsedForDescription() {
		return usedForDescription;
	}

	public void setUsedForDescription(int usedForDescription) {
		this.usedForDescription = usedForDescription;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getIsParent() {
		return isParent;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent;
	}

	public int getAllowToCopy() {
		return allowToCopy;
	}

	public void setAllowToCopy(int allowToCopy) {
		this.allowToCopy = allowToCopy;
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

	
	
	
	public int getAddOnType() {
		return addOnType;
	}

	public void setAddOnType(int addOnType) {
		this.addOnType = addOnType;
	}

	public float getAddOnRs() {
		return addOnRs;
	}

	public void setAddOnRs(float addOnRs) {
		this.addOnRs = addOnRs;
	}

	public int getIsTagAdd() {
		return isTagAdd;
	}

	public void setIsTagAdd(int isTagAdd) {
		this.isTagAdd = isTagAdd;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "MFilter [filterId=" + filterId + ", filterName=" + filterName + ", filterDesc=" + filterDesc
				+ ", filterTypeId=" + filterTypeId + ", usedForFilter=" + usedForFilter + ", costAffect=" + costAffect
				+ ", usedForDescription=" + usedForDescription + ", companyId=" + companyId + ", isParent=" + isParent
				+ ", allowToCopy=" + allowToCopy + ", sortNo=" + sortNo + ", isActive=" + isActive + ", delStatus="
				+ delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", addOnType=" + addOnType + ", addOnRs=" + addOnRs
				+ ", isTagAdd=" + isTagAdd + ", tagId=" + tagId + ", adminName=" + adminName + "]";
	}

}
