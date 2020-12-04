package com.ats.ecomapi.report.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetDateWiseBillReport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private Date billDate;
	private float totalAmt;
	private int totalBills;
	private int cod;
	private int card;
	private int epay;
	private String monthName;
	private String orderYear;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public int getTotalBills() {
		return totalBills;
	}

	public void setTotalBills(int totalBills) {
		this.totalBills = totalBills;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}

	public int getEpay() {
		return epay;
	}

	public void setEpay(int epay) {
		this.epay = epay;
	}
	

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getOrderYear() {
		return orderYear;
	}

	public void setOrderYear(String orderYear) {
		this.orderYear = orderYear;
	}

	@Override
	public String toString() {
		return "GetDateWiseBillReport [id=" + id + ", billDate=" + billDate + ", totalAmt=" + totalAmt + ", totalBills="
				+ totalBills + ", cod=" + cod + ", card=" + card + ", epay=" + epay + ", monthName=" + monthName
				+ ", orderYear=" + orderYear + "]";
	}

	
}
