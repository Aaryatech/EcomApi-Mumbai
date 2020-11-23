package com.ats.ecomapi.mst_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_cate_filter_config")
public class CateFilterConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "cate_filter_config_id")
	private int cateFilterConfigId;
	
	@Column(name = "cate_id")
	private int cateId;
	
	@Column(name = "filter_ids")
	private String filterIds;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;
	
	@Column(name = "comp_id")
	private int compId;
	
	@Column(name = "maker_date_time")
	private String makerDateTime;

	public int getCateFilterConfigId() {
		return cateFilterConfigId;
	}

	public void setCateFilterConfigId(int cateFilterConfigId) {
		this.cateFilterConfigId = cateFilterConfigId;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getFilterIds() {
		return filterIds;
	}

	public void setFilterIds(String filterIds) {
		this.filterIds = filterIds;
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

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public String getMakerDateTime() {
		return makerDateTime;
	}

	public void setMakerDateTime(String makerDateTime) {
		this.makerDateTime = makerDateTime;
	}

	@Override
	public String toString() {
		return "CateFilterConfig [cateFilterConfigId=" + cateFilterConfigId + ", cateId=" + cateId + ", filterIds="
				+ filterIds + ", delStatus=" + delStatus + ", isActive=" + isActive + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", compId=" + compId + ", makerDateTime="
				+ makerDateTime + "]";
	}
	
	
}
