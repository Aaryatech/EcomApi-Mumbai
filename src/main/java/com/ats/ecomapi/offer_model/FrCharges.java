package com.ats.ecomapi.offer_model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "mn_fr_charges")
public class FrCharges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "charge_id")
	private int chargeId;
	
	@Column(name = "fr_id")
	private int frId;
	
	@Column(name = "from_date ")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "surcharge_fee")
	private float surchargeFee;
	
	@Column(name = "packing_chg")
	private float packingChg;
	
	@Column(name = "handling_chg")
	private float handlingChg;
	
	@Column(name = "extra_chg")
	private float extraChg;
	
	@Column(name = "round_off_amt")
	private float roundOffAmt;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_int3")
	private int exInt3;
	
	@Column(name = "ex_float1")
	private float exFloat1;
	
	@Column(name = "ex_float2")
	private float exFloat2;
	
	@Column(name = "ex_float3")
	private float exFloat3;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;
	
	@Column(name = "ex_var3")
	private String exVar3;

	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}	
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public float getSurchargeFee() {
		return surchargeFee;
	}

	public void setSurchargeFee(float surchargeFee) {
		this.surchargeFee = surchargeFee;
	}

	public float getPackingChg() {
		return packingChg;
	}

	public void setPackingChg(float packingChg) {
		this.packingChg = packingChg;
	}

	public float getHandlingChg() {
		return handlingChg;
	}

	public void setHandlingChg(float handlingChg) {
		this.handlingChg = handlingChg;
	}

	public float getExtraChg() {
		return extraChg;
	}

	public void setExtraChg(float extraChg) {
		this.extraChg = extraChg;
	}

	public float getRoundOffAmt() {
		return roundOffAmt;
	}

	public void setRoundOffAmt(float roundOffAmt) {
		this.roundOffAmt = roundOffAmt;
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

	@Override
	public String toString() {
		return "FrCharges [chargeId=" + chargeId + ", frId=" + frId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", surchargeFee=" + surchargeFee + ", packingChg=" + packingChg + ", handlingChg=" + handlingChg
				+ ", extraChg=" + extraChg + ", roundOffAmt=" + roundOffAmt + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exInt3=" + exInt3 + ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2 + ", exFloat3="
				+ exFloat3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}
	
	
	
}
