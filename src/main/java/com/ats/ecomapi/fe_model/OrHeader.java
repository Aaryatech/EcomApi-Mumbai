package com.ats.ecomapi.fe_model;

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

//	public Integer getQty() {
//		return qty;
//	}
//
//	public void setQty(Integer qty) {
//		this.qty = qty;
//	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

//	public Integer getItemId() {
//		return itemId;
//	}
//
//	public void setItemId(Integer itemId) {
//		this.itemId = itemId;
//	}

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

	
}
