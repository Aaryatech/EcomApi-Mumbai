package com.ats.ecomapi.report.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class HeadOfficeReport {

	@Id
	private String id;
	private int orderId;
	private Date deliveryDate;
	private Date orderDate;
	private String companyName;
	private String orderNo;
	private int paymentMethod;
	private int orderStatus;
	private String timeSlot;
	private float qty;
	private float totalAmt;
	private float discAmt;
	private int productId;
	private String productCode;
	private String productName;
	private String catName;
	private String catPrefix;
	private String subCatCode;
	private String subCatName;
	private String subCatPrefix;
	private String custName;
	private String frCode;
	private String frName;
	private String frAddress;
	private String pincode;
	private String couponCode;
	private String payRefNo;
	
	private float deliveryCharges; 
	private String hsnCode; 
	private float mrp; 
	private float sgstAmt; 
	private float cgstAmt; 
	private float igstAmt; 
	
	
	//NEW FIELDS ADDED SACHIN 10-07-2021
	private String delAddress;
	private String billingAddress;
	private String custGst;
	private String uuidNo;
	private int	detailStatus;
	private String retPerc;
	private String shortName;
	
	
	
	
	
	public String getDelAddress() {
		return delAddress;
	}
	public void setDelAddress(String delAddress) {
		this.delAddress = delAddress;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getCustGst() {
		return custGst;
	}
	public void setCustGst(String custGst) {
		this.custGst = custGst;
	}
	public String getUuidNo() {
		return uuidNo;
	}
	public void setUuidNo(String uuidNo) {
		this.uuidNo = uuidNo;
	}
	public int getDetailStatus() {
		return detailStatus;
	}
	public void setDetailStatus(int detailStatus) {
		this.detailStatus = detailStatus;
	}
	public String getRetPerc() {
		return retPerc;
	}
	public void setRetPerc(String retPerc) {
		this.retPerc = retPerc;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}
	public float getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatPrefix() {
		return catPrefix;
	}
	public void setCatPrefix(String catPrefix) {
		this.catPrefix = catPrefix;
	}
	public String getSubCatCode() {
		return subCatCode;
	}
	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getSubCatPrefix() {
		return subCatPrefix;
	}
	public void setSubCatPrefix(String subCatPrefix) {
		this.subCatPrefix = subCatPrefix;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getFrCode() {
		return frCode;
	}
	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getFrAddress() {
		return frAddress;
	}
	public void setFrAddress(String frAddress) {
		this.frAddress = frAddress;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getPayRefNo() {
		return payRefNo;
	}
	public void setPayRefNo(String payRefNo) {
		this.payRefNo = payRefNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public float getDeliveryCharges() {
		return deliveryCharges;
	}
	public void setDeliveryCharges(float deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public float getMrp() {
		return mrp;
	}
	public void setMrp(float mrp) {
		this.mrp = mrp;
	}
	public float getSgstAmt() {
		return sgstAmt;
	}
	public void setSgstAmt(float sgstAmt) {
		this.sgstAmt = sgstAmt;
	}
	public float getCgstAmt() {
		return cgstAmt;
	}
	public void setCgstAmt(float cgstAmt) {
		this.cgstAmt = cgstAmt;
	}
	public float getIgstAmt() {
		return igstAmt;
	}
	public void setIgstAmt(float igstAmt) {
		this.igstAmt = igstAmt;
	}
	@Override
	public String toString() {
		return "HeadOfficeReport [id=" + id + ", orderId=" + orderId + ", deliveryDate=" + deliveryDate + ", orderDate="
				+ orderDate + ", companyName=" + companyName + ", orderNo=" + orderNo + ", paymentMethod="
				+ paymentMethod + ", orderStatus=" + orderStatus + ", timeSlot=" + timeSlot + ", qty=" + qty
				+ ", totalAmt=" + totalAmt + ", discAmt=" + discAmt + ", productId=" + productId + ", productCode="
				+ productCode + ", productName=" + productName + ", catName=" + catName + ", catPrefix=" + catPrefix
				+ ", subCatCode=" + subCatCode + ", subCatName=" + subCatName + ", subCatPrefix=" + subCatPrefix
				+ ", custName=" + custName + ", frCode=" + frCode + ", frName=" + frName + ", frAddress=" + frAddress
				+ ", pincode=" + pincode + ", couponCode=" + couponCode + ", payRefNo=" + payRefNo
				+ ", deliveryCharges=" + deliveryCharges + ", hsnCode=" + hsnCode + ", mrp=" + mrp + ", sgstAmt="
				+ sgstAmt + ", cgstAmt=" + cgstAmt + ", igstAmt=" + igstAmt + "]";
	}
			
}
