package com.ats.ecomapi.fe_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//SAC 23-06-2021
public class HomePageStatusHead {

	@Id
	private int homePageUuid;
	
private int homePageStatusId;
	
	private int companyId;
	
	private int statusId; //to be matched with recieved filter id in frontend
	
	
	private String exVar1; //title~keywords
	
	private String exVar3; //imageAlt~Description

	public int getHomePageUuid() {
		return homePageUuid;
	}

	public void setHomePageUuid(int homePageUuid) {
		this.homePageUuid = homePageUuid;
	}

	public int getHomePageStatusId() {
		return homePageStatusId;
	}

	public void setHomePageStatusId(int homePageStatusId) {
		this.homePageStatusId = homePageStatusId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	@Override
	public String toString() {
		return "HomePageStatusHead [homePageUuid=" + homePageUuid + ", homePageStatusId=" + homePageStatusId
				+ ", companyId=" + companyId + ", statusId=" + statusId + ", exVar1=" + exVar1 + ", exVar3=" + exVar3
				+ "]";
	}
	
}
