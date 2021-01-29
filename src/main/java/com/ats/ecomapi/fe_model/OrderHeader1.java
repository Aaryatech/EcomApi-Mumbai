package com.ats.ecomapi.fe_model;

//import java.awt.Image;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

//import com.ats.ecomapi.common.OrDetail;

@Entity
public class OrderHeader1 {
	@Id
	private Integer orderId;
    private Integer orderDetailId;
	private Integer orderNo;
	private Integer itemId;
	private Integer paymentMethod;
	private Integer orderStatus;
	private Integer frId;
	//private Integer frCode;
	private String billingName;
	private String address;
	private String whatsappNo;
	//private Integer frLatitude;
	//private Integer frLogitude;
	private Integer deliveryKm;
	private Integer custId;
	private Integer qty;
	//private String custName;
	private String landmark;
	private Long totalAmt;
	@Transient
	List<OrderDetail1> detailList;
	//private Integer custLatitude;
	//private Integer custLogitude;
	//private Integer Qty;
	
	public List<OrderDetail1> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<OrderDetail1> detailList) {
		this.detailList = detailList;
	}
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
//	public Integer getOrderAmt() {
//		return orderAmt;
//	}
//	public void setOrderAmt(Integer orderAmt) {
//		this.orderAmt = orderAmt;
//	}
//	public Integer getPaymentMod() {
//		return paymentMod;
//	}
//	public void setPaymentMod(Integer paymentMod) {
//		this.paymentMod = paymentMod;
//	}
//	public Integer getNoOfItem() {
//		return noOfItem;
//	}
//	public void setNoOfItem(Integer noOfItem) {
//		this.noOfItem = noOfItem;
//	}
	public Integer getFrId() {
		return frId;
	}
	public void setFrId(Integer frId) {
		this.frId = frId;
	}
//	public Integer getFrCode() {
//		return frCode;
//	}
//	public void setFrCode(Integer frCode) {
//		this.frCode = frCode;
//	}
//	public String getFrName() {
//		return frName;
//	}
//	public void setFrName(String frName) {
//		this.frName = frName;
//	}
//	public String getFrAddress() {
//		return frAddress;
//	}
//	public void setFrAddress(String frAddress) {
//		this.frAddress = frAddress;
//	}
//	public Integer getFrLatitude() {
//		return frLatitude;
//	}
//	public void setFrLatitude(Integer frLatitude) {
//		this.frLatitude = frLatitude;
//	}
//	public Integer getFrLogitude() {
//		return frLogitude;
//	}
//	public void setFrLogitude(Integer frLogitude) {
//		this.frLogitude = frLogitude;
//	}
//	public Integer getDistance() {
//		return distance;
//	}
//	public void setDistance(Integer distance) {
//		this.distance = distance;
//	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
//	public Integer getCustMobile() {
//		return custMobile;
//	}
//	public void setCustMobile(Integer custMobile) {
//		this.custMobile = custMobile;
//	}
//	public String getCustName() {
//		return custName;
//	}
//	public void setCustName(String custName) {
//		this.custName = custName;
//	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandMark(String landMark) {
		this.landmark = landMark;
	}
//	public String getDetailAddress() {
//		return detailAddress;
//	}
//	public void setDetailAddress(String detailAddress) {
//		this.detailAddress = detailAddress;
//	}
//	public Integer getCustLatitude() {
//		return custLatitude;
//	}
//	public void setCustLatitude(Integer custLatitude) {
//		this.custLatitude = custLatitude;
//	}
//	public Integer getCustLogitude() {
//		return custLogitude;
//	}
//	public void setCustLogitude(Integer custLogitude) {
//		this.custLogitude = custLogitude;
//	}
//	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getBillingName() {
		return billingName;
	}
	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}
	
	public Long getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Long totalAmt) {
		this.totalAmt = totalAmt;
	}
	
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getDeliveryKm() {
		return deliveryKm;
	}
	public void setDeliveryKm(Integer deliveryKm) {
		this.deliveryKm = deliveryKm;
	}
	public String getWhatsappNo() {
		return whatsappNo;
	}
	public void setWhatsappNo(String whatsappNo) {
		this.whatsappNo = whatsappNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
