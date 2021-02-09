package com.ats.ecomapi.deliveryboy_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetail1 {
	@Id
	private Integer orderDetailId;
	private Integer orderId;
    private Integer productId;
	private String productName;
	private String prodImagePrimary;
	private Integer minQty;
    private String productDesc;
    private Float basicMrp;
    
    
 	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
 
    @Override
	public String toString() {
		return "OrderDetail1 [orderDetailId=" + orderDetailId + ", productId=" + productId + ", productName="
				+ productName + ", prodImagePrimary=" + prodImagePrimary + ", minQty=" + minQty + ", productDesc="
				+ productDesc + "]";
	}
	

    public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProdImagePrimary() {
		return prodImagePrimary;
	}
	public void setProdImagePrimary(String prodImagePrimary) {
		this.prodImagePrimary = prodImagePrimary;
	}
	public Integer getMinQty() {
		return minQty;
	}
	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Float getBasicMrp() {
		return basicMrp;
	}
	public void setBasicMrp(Float basicMrp) {
		this.basicMrp = basicMrp;
	}

}
