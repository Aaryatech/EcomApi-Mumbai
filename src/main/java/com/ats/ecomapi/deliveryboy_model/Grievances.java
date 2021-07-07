package com.ats.ecomapi.deliveryboy_model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tn_grievences")
public class Grievances {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer orderId;
	private String deliveryReason;
	private String proImg1;
	private String proImg2;
	private String proImg3;
	private String dDate;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getDeliveryReason() {
		return deliveryReason;
	}
	public void setDeliveryReason(String deliveryReason) {
		this.deliveryReason = deliveryReason;
	}
	public String getProImg1() {
		return proImg1;
	}
	public void setProImg1(String proImg1) {
		this.proImg1 = proImg1;
	}
	public String getProImg2() {
		return proImg2;
	}
	public void setProImg2(String proImg2) {
		this.proImg2 = proImg2;
	}
	public String getProImg3() {
		return proImg3;
	}
	public void setProImg3(String proImg3) {
		this.proImg3 = proImg3;
	}
	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	@Override
	public String toString() {
		return "Grievances [orderId=" + orderId + ", deliveryReason=" + deliveryReason + ", proImg1=" + proImg1
				+ ", proImg2=" + proImg2 + ", proImg3=" + proImg3 + ", dDate=" + dDate + "]";
	}
	

}
