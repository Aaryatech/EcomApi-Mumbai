package com.ats.ecomapi.offer_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetConfigureOfferList {
	@Id
	private int frId;
	private String frName;
	private int checked;
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "GetConfigureOfferList [frId=" + frId + ", frName=" + frName + ", checked=" + checked + "]";
	}
	
	
}
