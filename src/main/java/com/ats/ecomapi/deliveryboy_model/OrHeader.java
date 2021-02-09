package com.ats.ecomapi.deliveryboy_model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class OrHeader {
	@Id
	private Integer orderId;
		
	private Integer orderNo;
	private Integer totalAmt;
	private String orderStatus;
	
	private String deliveryDate;
	private String deliveryTime;
	private String insertDateTime;
	
	
	private Integer paymentMethod;
	
	private Integer frId;
	private String frCode;
	private String frName;
	private String frAddress;
	private Integer shopsLatitude;
	private Integer shopsLogitude;
	private Integer noOfKmAreaCover;
	
	private Integer custId;
	private String custName;
	private Long custMobileNo;
	
	private String address;
	private String latitude;
	private String longitude;
	
	private Integer taxableAmt;
    private Integer taxAmt;
	
	@Transient
	List<OrderDetail1> detailList;
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<OrderDetail1> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetail1> detailList) {
		this.detailList = detailList;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderAmt() {
		return totalAmt;
	}

	public void setOrderAmt(Integer orderAmt) {
		this.totalAmt = orderAmt;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getFrId() {
		return frId;
	}

	public void setFrId(Integer frId) {
		this.frId = frId;
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

	public Integer getShopsLatitude() {
		return shopsLatitude;
	}

	public void setShopsLatitude(Integer shopsLatitude) {
		this.shopsLatitude = shopsLatitude;
	}

	public Integer getShopsLogitude() {
		return shopsLogitude;
	}

	public void setShopsLogitude(Integer shopsLogitude) {
		this.shopsLogitude = shopsLogitude;
	}

	public Integer getNoOfAreaCover() {
		return noOfKmAreaCover;
	}

	public void setNoOfAreaCover(Integer noOfAreaCover) {
		this.noOfKmAreaCover = noOfAreaCover;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Long getCustMobileNo() {
		return custMobileNo;
	}

	public void setCustMobileNo(Long custMobileNo) {
		this.custMobileNo = custMobileNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getInsertDateTime() {
		return insertDateTime;
	}

	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(Integer taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public Integer getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(Integer taxAmt) {
		this.taxAmt = taxAmt;
	}

	
}
