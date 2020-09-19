package com.ats.ecomapi.offer_model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mn_offer_header")
public class OfferHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "offer_id")
	private int offerId;

	@Column(name = "offer_name")
	private String offerName;

	@Column(name = "offer_desc")
	private String offerDesc;

	@Column(name = "type")
	private int type;

	@Column(name = "applicable_for")
	private String applicableFor;

	@Column(name = "offer_type")
	private int offerType;

	@Column(name = "frequency_type")
	private int frequencyType;

	@Column(name = "frequency")
	private String frequency;

	@Column(name = "from_date")
	private String fromDate;

	@Column(name = "to_date")
	private String toDate;

	@Column(name = "from_time")
	private String fromTime;

	@Column(name = "to_time")
	private String toTime;
	
	
	@Column(name = "offer_images")
	private String offerImages;

	@Column(name = "maker_user_id")
	private int makerUserId;

	@Column(name = "maker_datetime")
	private String makerDatetime;

	@Column(name = "last_updated_datetime")
	private String lastUpdatedDatetime;

	@Column(name = "comp_id")
	private int compId;

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

	@Column(name = "ex_int4")
	private int exInt4;

	@Column(name = "ex_var1")
	private String exVar1;

	@Column(name = "ex_var2")
	private String exVar2;

	@Column(name = "ex_var3")
	private String exVar3;

	@Column(name = "ex_var4")
	private String exVar4;

	@Column(name = "ex_float1")
	private float exFloat1;

	@Column(name = "ex_float2")
	private float exFloat2;

	@Column(name = "ex_float3")
	private float exFloat3;

	@Column(name = "ex_float4")
	private float exFloat4;

	@Transient
	List<Images> imageList;
	
	

	public String getOfferImages() {
		return offerImages;
	}

	public void setOfferImages(String offerImages) {
		this.offerImages = offerImages;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDesc() {
		return offerDesc;
	}

	public void setOfferDesc(String offerDesc) {
		this.offerDesc = offerDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getApplicableFor() {
		return applicableFor;
	}

	public void setApplicableFor(String applicableFor) {
		this.applicableFor = applicableFor;
	}

	public int getOfferType() {
		return offerType;
	}

	public void setOfferType(int offerType) {
		this.offerType = offerType;
	}

	public int getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(int frequencyType) {
		this.frequencyType = frequencyType;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getMakerDatetime() {
		return makerDatetime;
	}

	public void setMakerDatetime(String makerDatetime) {
		this.makerDatetime = makerDatetime;
	}

	public String getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}

	public void setLastUpdatedDatetime(String lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
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

	public int getExInt4() {
		return exInt4;
	}

	public void setExInt4(int exInt4) {
		this.exInt4 = exInt4;
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

	public String getExVar4() {
		return exVar4;
	}

	public void setExVar4(String exVar4) {
		this.exVar4 = exVar4;
	}

	public float getExFloat1() {
		return exFloat1;
	}

	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}

	public float getExFloat2() {
		return exFloat2;
	}

	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
	}

	public float getExFloat3() {
		return exFloat3;
	}

	public void setExFloat3(float exFloat3) {
		this.exFloat3 = exFloat3;
	}

	public float getExFloat4() {
		return exFloat4;
	}

	public void setExFloat4(float exFloat4) {
		this.exFloat4 = exFloat4;
	}

	public List<Images> getImageList() {
		return imageList;
	}

	public void setImageList(List<Images> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String toString() {
		return "OfferHeader [offerId=" + offerId + ", offerName=" + offerName + ", offerDesc=" + offerDesc + ", type="
				+ type + ", applicableFor=" + applicableFor + ", offerType=" + offerType + ", frequencyType="
				+ frequencyType + ", frequency=" + frequency + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", fromTime=" + fromTime + ", toTime=" + toTime + ", offerImages=" + offerImages + ", makerUserId="
				+ makerUserId + ", makerDatetime=" + makerDatetime + ", lastUpdatedDatetime=" + lastUpdatedDatetime
				+ ", compId=" + compId + ", isActive=" + isActive + ", delStatus=" + delStatus + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exInt4=" + exInt4 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", exVar4=" + exVar4 + ", exFloat1=" + exFloat1
				+ ", exFloat2=" + exFloat2 + ", exFloat3=" + exFloat3 + ", exFloat4=" + exFloat4 + ", imageList="
				+ imageList + "]";
	}

	 

}
