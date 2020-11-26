package com.ats.ecomapi.mst_model;

import java.util.List;
 
public class OrderSaveData {
	
	private OrderHeader orderHeader;
	private List<OrderDetail> orderDetailList;
	private OrderTrail orderTrail;
	public OrderHeader getOrderHeader() {
		return orderHeader;
	}
	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public OrderTrail getOrderTrail() {
		return orderTrail;
	}
	public void setOrderTrail(OrderTrail orderTrail) {
		this.orderTrail = orderTrail;
	}
	@Override
	public String toString() {
		return "OrderSaveData [orderHeader=" + orderHeader + ", orderDetailList=" + orderDetailList + ", orderTrail="
				+ orderTrail + "]";
	}
	
	
	

}
