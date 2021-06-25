package com.ats.ecomapi.fe_model;

import javax.persistence.Entity;
import javax.persistence.Id;

//Author -Sachin
//Created on -02-10-2020
//Desc - to show Category list data for Franchise and 
//Company to front end pages.
@Entity
public class CategoryList {

	@Id
	private String catUuid;
	
	private int catId;
	
	private String catName;
	
	private String catPrefix;
	
	private String catDesc;
	
	private int companyId;
	
	private String imageName;
	
	private int sortNo;
	
	//SACHIN 21-06-2021
	private String metaTitle;
	private String metaDesc;
	private String metaKey;
	private String imageAlt;
	
	//SACHIN 25-06-2021
	private int staticCatId;
	
	public int getStaticCatId() {
		return staticCatId;
	}
	public void setStaticCatId(int staticCatId) {
		this.staticCatId = staticCatId;
	}
	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaDesc() {
		return metaDesc;
	}
	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}
	public String getMetaKey() {
		return metaKey;
	}
	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}
	public String getImageAlt() {
		return imageAlt;
	}
	public void setImageAlt(String imageAlt) {
		this.imageAlt = imageAlt;
	}
	public String getCatUuid() {
		return catUuid;
	}
	public void setCatUuid(String catUuid) {
		this.catUuid = catUuid;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatPrefix() {
		return catPrefix;
	}
	public void setCatPrefix(String catPrefix) {
		this.catPrefix = catPrefix;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	
	@Override
	public String toString() {
		return "CategoryList [catUuid=" + catUuid + ", catId=" + catId + ", catName=" + catName + ", catPrefix="
				+ catPrefix + ", catDesc=" + catDesc + ", companyId=" + companyId + ", imageName=" + imageName
				+ ", sortNo=" + sortNo + ", metaTitle=" + metaTitle + ", metaDesc=" + metaDesc + ", metaKey=" + metaKey
				+ ", imageAlt=" + imageAlt + "]";
	}
	
	
	
	
	
}
