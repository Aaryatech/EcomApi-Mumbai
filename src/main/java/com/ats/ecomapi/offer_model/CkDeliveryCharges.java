package com.ats.ecomapi.offer_model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CkDeliveryCharges {
	@Id
	private String id;

	private float amt1;
	private float amt2;
	private float minKm;
	private float maxKm;
	private float minAmt;
	private float freeDelvLimit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getAmt1() {
		return amt1;
	}

	public void setAmt1(float amt1) {
		this.amt1 = amt1;
	}

	public float getAmt2() {
		return amt2;
	}

	public void setAmt2(float amt2) {
		this.amt2 = amt2;
	}

	public float getMinKm() {
		return minKm;
	}

	public void setMinKm(float minKm) {
		this.minKm = minKm;
	}

	public float getMaxKm() {
		return maxKm;
	}

	public void setMaxKm(float maxKm) {
		this.maxKm = maxKm;
	}

	public float getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(float minAmt) {
		this.minAmt = minAmt;
	}

	public float getFreeDelvLimit() {
		return freeDelvLimit;
	}

	public void setFreeDelvLimit(float freeDelvLimit) {
		this.freeDelvLimit = freeDelvLimit;
	}

	@Override
	public String toString() {
		return "CkDeliveryCharges [id=" + id + ", amt1=" + amt1 + ", amt2=" + amt2 + ", minKm=" + minKm + ", maxKm="
				+ maxKm + ", minAmt=" + minAmt + ", freeDelvLimit=" + freeDelvLimit + "]";
	}

}
