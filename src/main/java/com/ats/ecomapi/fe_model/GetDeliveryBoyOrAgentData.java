package com.ats.ecomapi.fe_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetDeliveryBoyOrAgentData {

	@Id
	private String uId;
	private int delBoyId;
	private String name;
	private String mobileNo;
	private int frId;
	private String cityIds;
	private int isAgent;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getCityIds() {
		return cityIds;
	}

	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	public int getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(int isAgent) {
		this.isAgent = isAgent;
	}

	public int getDelBoyId() {
		return delBoyId;
	}

	public void setDelBoyId(int delBoyId) {
		this.delBoyId = delBoyId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
}
