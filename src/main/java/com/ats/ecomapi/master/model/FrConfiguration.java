package com.ats.ecomapi.master.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_fr_configration")
public class FrConfiguration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int frachaseConfigId;

	private int configHeaderId;

	private int frId;
	private int displayRate;

	private int actualRate;
	private String makerDatetime;
	private String updatedDateTime;;

	private int makerUserId;

	private int isActive;

	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	public int getFrachaseConfigId() {
		return frachaseConfigId;
	}
	public void setFrachaseConfigId(int frachaseConfigId) {
		this.frachaseConfigId = frachaseConfigId;
	}
	public int getConfigHeaderId() {
		return configHeaderId;
	}
	public void setConfigHeaderId(int configHeaderId) {
		this.configHeaderId = configHeaderId;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public int getDisplayRate() {
		return displayRate;
	}
	public void setDisplayRate(int displayRate) {
		this.displayRate = displayRate;
	}
	public int getActualRate() {
		return actualRate;
	}
	public void setActualRate(int actualRate) {
		this.actualRate = actualRate;
	}
	public String getMakerDatetime() {
		return makerDatetime;
	}
	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}
	public String getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
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
	@Override
	public String toString() {
		return "FrConfiguration [frachaseConfigId=" + frachaseConfigId + ", configHeaderId=" + configHeaderId
				+ ", frId=" + frId + ", displayRate=" + displayRate + ", actualRate=" + actualRate + ", makerDatetime="
				+ makerDatetime + ", updatedDateTime=" + updatedDateTime + ", makerUserId=" + makerUserId
				+ ", isActive=" + isActive + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + "]";
	}
	
	
	

}
