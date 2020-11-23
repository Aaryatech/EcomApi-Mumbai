package com.ats.ecomapi.mst_model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_festive_events")
public class FestiveEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "event_id")
	private int eventId;
	
	@Column(name = "event_name")
	private String eventName;

	@Column(name = "description	")
	private String description;	

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "from_time")
	private String fromTime;	

	@Column(name = "to_time")
	private String toTime;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "product_ids")
	private String productIds;

	@Column(name = "comp_id")
	private int compId;

	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "make_time")
	private String makeTime;

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

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId){
		this.eventId = eventId;
	}

	public String getEventName(){
		return eventName;
	}

	public void setEventName(String eventName){
		this.eventName = eventName;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getFromDate(){
		return fromDate;
	}

	public void setFromDate(Date fromDate){
		this.fromDate = fromDate;
	}
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getToDate(){
		return toDate;
	}

	public void setToDate(Date toDate){
		this.toDate = toDate;
	}

	public String getFromTime(){
		return fromTime;
	}

	public void setFromTime(String fromTime){
		this.fromTime = fromTime;
	}

	public String getToTime(){
		return toTime;
	}

	public void setToTime(String toTime){
		this.toTime = toTime;
	}

	public int getIsActive(){
		return isActive;
	}

	public void setIsActive(int isActive){
		this.isActive = isActive;
	}

	public String getProductIds(){
		return productIds;
	}

	public void setProductIds(String productIds){
		this.productIds = productIds;
	}

	public int getCompId(){
		return compId;
	}

	public void setCompId(int compId){
		this.compId = compId;
	}

	public int getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(int delStatus){
		this.delStatus = delStatus;
	}

	public String getMakeTime(){
		return makeTime;
	}

	public void setMakeTime(String makeTime){
		this.makeTime = makeTime;
	}

	public int getExInt1(){
		return exInt1;
	}

	public void setExInt1(int exInt1){
		this.exInt1 = exInt1;
	}

	public int getExInt2(){
		return exInt2;
	}

	public void setExInt2(int exInt2){
		this.exInt2 = exInt2;
	}

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3){
		this.exInt3 = exInt3;
	}

	public String getExVar1(){
		return exVar1;
	}

	public void setExVar1(String exVar1){
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2){
		this.exVar2 = exVar2;
	}

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3){
		this.exVar3 = exVar3;
	}

	@Override
	public String toString() {
		return "FestiveEvent [eventId=" + eventId + ", eventName=" + eventName + ", description=" + description
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", fromTime=" + fromTime + ", toTime=" + toTime
				+ ", isActive=" + isActive + ", productIds=" + productIds + ", compId=" + compId + ", delStatus="
				+ delStatus + ", makeTime=" + makeTime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3="
				+ exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}
	
}
