package com.ats.ecomapi.mst_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tn_order_trail")
public class OrderTrail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "trail_id")
	private int trailId;

	@Column(name = "order_id")
	private int orderId;

	@Column(name = "action_by_user_id")
	private int actionByUserId;

	@Column(name = "action_date_time")
	private String actionDateTime;

	@Column(name = "status")
	private int status;

	@Column(name = "ex_int1")
	private int exInt1;

	@Column(name = "ex_int2")
	private int exInt2;

	@Column(name = "ex_int3")
	private int exInt3;

	@Column(name = "ex_var1")
	private String exVar1;

	@Column(name = "ex_var2")
	private String exVar2;

	@Column(name = "ex_var3")
	private String exVar3;

	public int getTrailId() {
		return trailId;
	}

	public void setTrailId(int trailId) {
		this.trailId = trailId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getActionByUserId() {
		return actionByUserId;
	}

	public void setActionByUserId(int actionByUserId) {
		this.actionByUserId = actionByUserId;
	}

	public String getActionDateTime() {
		return actionDateTime;
	}

	public void setActionDateTime(String actionDateTime) {
		this.actionDateTime = actionDateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
		return "OrderTrail [trailId=" + trailId + ", orderId=" + orderId + ", actionByUserId=" + actionByUserId
				+ ", actionDateTime=" + actionDateTime + ", status=" + status + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ "]";
	}

}
