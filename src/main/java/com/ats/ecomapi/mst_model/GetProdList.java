package com.ats.ecomapi.mst_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
/*****************************
 * //Created Date: 17-09-2020 //UpdateDate:17-09-2020 //Description: to show list of products //Devloped By(Devloper Name): Sachin //Updated By(Devloper
 * Name): Sachin
 ******************************/
public class GetProdList {

	@Id
	private int productId;
	
	private String productCode; //Based On sub Category prefix
	private String productName;
	private String catName;
	private String subCatName;
	private int isActive;
	private String uomShowName;
	private int allowSameDayDelivery;

	private String prodStatus;
	private int bookBefore; //No of days 
	private String isVeg;
	
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
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getUomShowName() {
		return uomShowName;
	}
	public void setUomShowName(String uomShowName) {
		this.uomShowName = uomShowName;
	}
	public int getAllowSameDayDelivery() {
		return allowSameDayDelivery;
	}
	public void setAllowSameDayDelivery(int allowSameDayDelivery) {
		this.allowSameDayDelivery = allowSameDayDelivery;
	}
	public String getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
	public int getBookBefore() {
		return bookBefore;
	}
	public void setBookBefore(int bookBefore) {
		this.bookBefore = bookBefore;
	}
	public String getIsVeg() {
		return isVeg;
	}
	public void setIsVeg(String isVeg) {
		this.isVeg = isVeg;
	}
	
	@Override
	public String toString() {
		return "GetProdList [productId=" + productId + ", productCode=" + productCode + ", productName=" + productName
				+ ", catName=" + catName + ", subCatName=" + subCatName + ", isActive=" + isActive + ", uomShowName="
				+ uomShowName + ", allowSameDayDelivery=" + allowSameDayDelivery + ", prodStatus=" + prodStatus
				+ ", bookBefore=" + bookBefore + ", isVeg=" + isVeg + "]";
	}
	
}
