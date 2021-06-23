package com.ats.ecomapi.fe_model;

import javax.persistence.Entity;
import javax.persistence.Id;

//Author -Sachin
//Created on -02-10-2020
//Desc - to show Product's Flavor, Tag, Status List data to front end pages.
//Added Desc - with above it also get Veg nonveg data ie filter type 12.
@Entity
public class GetFlavorTagStatusList {

	@Id
	private int filterId;
	private String filterName;
	private int filterTypeId;
	private int usedForFilter;
	private int costAffect;
	private int usedForDescription;
	
	private int companyId;
	private int sortNo;
	
	private String adminName; //Sachin 27-10-2020
	
	private String filterNameDisp;//SACHIN 23-06-2021
	
	public String getFilterNameDisp() {
		return filterNameDisp;
	}
	public void setFilterNameDisp(String filterNameDisp) {
		this.filterNameDisp = filterNameDisp;
	}
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
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	
	
	

	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	@Override
	public String toString() {
		return "GetFlavorTagStatusList [filterId=" + filterId + ", filterName=" + filterName + ", filterTypeId="
				+ filterTypeId + ", usedForFilter=" + usedForFilter + ", costAffect=" + costAffect
				+ ", usedForDescription=" + usedForDescription + ", companyId=" + companyId + ", sortNo=" + sortNo
				+ ", adminName=" + adminName + "]";
	}
	
	
	
	
}
