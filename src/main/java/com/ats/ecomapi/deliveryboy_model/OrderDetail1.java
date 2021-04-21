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
	private Float minQty;
    private String productDesc;
    private Float basicMrp;
    
    private String uom;
    
    private float total;
    
    
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public String getProdImagePrimary() {
		return prodImagePrimary;
	}

	public String getProductDesc() {
		return productDesc;
	}
	public Float getBasicMrp() {
		return basicMrp;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProdImagePrimary(String prodImagePrimary) {
		this.prodImagePrimary = prodImagePrimary;
	}
	
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public void setBasicMrp(Float basicMrp) {
		this.basicMrp = basicMrp;
	}
	
	
	
	public Float getMinQty() {
		return minQty;
	}
	public void setMinQty(Float minQty) {
		this.minQty = minQty;
	}
	public String getUom() {
		return uom;
	}
	public float getTotal() {
		return total;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "OrderDetail1 [orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", productId=" + productId
				+ ", productName=" + productName + ", prodImagePrimary=" + prodImagePrimary + ", minQty=" + minQty
				+ ", productDesc=" + productDesc + ", basicMrp=" + basicMrp + ", uom=" + uom + ", total=" + total + "]";
	}
	
	
	
	
	

	
    
   
}
