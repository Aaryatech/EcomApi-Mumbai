package com.ats.ecomapi.offer_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mn_offer_detail")
public class OfferDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "offer_detail_id")
	private int offerDetailId;

	@Column(name = "offer_id")
	private int offerId;

	@Column(name = "offer_sub_type")
	private int offerSubType;

	@Column(name = "primary_item_id")
	private int primaryItemId;

	@Column(name = "primary_qty")
	private float primaryQty;

	@Column(name = "disc_per")
	private float discPer;

	@Column(name = "offer_limit")
	private float offerLimit;

	@Column(name = "coupon_code")
	private String couponCode;

	@Column(name = "secondary_item_id")
	private int secondaryItemId;

	@Column(name = "secondary_qty")
	private float secondaryQty;

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

	public int getOfferDetailId() {
		return offerDetailId;
	}

	public void setOfferDetailId(int offerDetailId) {
		this.offerDetailId = offerDetailId;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getOfferSubType() {
		return offerSubType;
	}

	public void setOfferSubType(int offerSubType) {
		this.offerSubType = offerSubType;
	}

	public int getPrimaryItemId() {
		return primaryItemId;
	}

	public void setPrimaryItemId(int primaryItemId) {
		this.primaryItemId = primaryItemId;
	}

	public float getPrimaryQty() {
		return primaryQty;
	}

	public void setPrimaryQty(float primaryQty) {
		this.primaryQty = primaryQty;
	}

	public float getDiscPer() {
		return discPer;
	}

	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}

	public float getOfferLimit() {
		return offerLimit;
	}

	public void setOfferLimit(float offerLimit) {
		this.offerLimit = offerLimit;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public int getSecondaryItemId() {
		return secondaryItemId;
	}

	public void setSecondaryItemId(int secondaryItemId) {
		this.secondaryItemId = secondaryItemId;
	}

	public float getSecondaryQty() {
		return secondaryQty;
	}

	public void setSecondaryQty(float secondaryQty) {
		this.secondaryQty = secondaryQty;
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

	@Override
	public String toString() {
		return "OfferDetail [offerDetailId=" + offerDetailId + ", offerId=" + offerId + ", offerSubType=" + offerSubType
				+ ", primaryItemId=" + primaryItemId + ", primaryQty=" + primaryQty + ", discPer=" + discPer
				+ ", offerLimit=" + offerLimit + ", couponCode=" + couponCode + ", secondaryItemId=" + secondaryItemId
				+ ", secondaryQty=" + secondaryQty + ", isActive=" + isActive + ", delStatus=" + delStatus + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exInt4=" + exInt4 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", exVar4=" + exVar4 + ", exFloat1=" + exFloat1
				+ ", exFloat2=" + exFloat2 + ", exFloat3=" + exFloat3 + ", exFloat4=" + exFloat4 + "]";
	}

}
