package com.ats.ecomapi.fe_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FrSubCatList {

	@Id
	private String subCatUuid;
	private int subCatId;
	private int catId;
	
	private String catName;
	
	private String subCatCode;
	
	private String subCatName;
	
	private String subCatPrefix;
	
	private String imageName;
	
	private int companyId;

	public String getSubCatUuid() {
		return subCatUuid;
	}

	public void setSubCatUuid(String subCatUuid) {
		this.subCatUuid = subCatUuid;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
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

	public String getSubCatCode() {
		return subCatCode;
	}

	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public String getSubCatPrefix() {
		return subCatPrefix;
	}

	public void setSubCatPrefix(String subCatPrefix) {
		this.subCatPrefix = subCatPrefix;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "FrSubCatList [subCatUuid=" + subCatUuid + ", subCatId=" + subCatId + ", catId=" + catId + ", catName="
				+ catName + ", subCatCode=" + subCatCode + ", subCatName=" + subCatName + ", subCatPrefix="
				+ subCatPrefix + ", imageName=" + imageName + ", companyId=" + companyId + "]";
	}
	
}
