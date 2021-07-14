package com.ats.ecomapi.controller;

public class OrderReturnPer {

	private String title;
	private int minTime;
	private int maxTime;
	private  float retPer;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMinTime() {
		return minTime;
	}
	public void setMinTime(int minTime) {
		this.minTime = minTime;
	}
	public int getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}
	public float getRetPer() {
		return retPer;
	}
	public void setRetPer(float retPer) {
		this.retPer = retPer;
	}
	
	@Override
	public String toString() {
		return "OrderReturnPer [minTime=" + minTime + ", maxTime=" + maxTime + ", retPer=" + retPer + "]";
	}
	
}
