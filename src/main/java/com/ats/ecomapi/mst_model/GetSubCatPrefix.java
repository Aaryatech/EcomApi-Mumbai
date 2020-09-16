package com.ats.ecomapi.mst_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class GetSubCatPrefix {

	@Id
	private String uuid;
	private String subCatPrefix;
	private int prodCount;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSubCatPrefix() {
		return subCatPrefix;
	}
	public void setSubCatPrefix(String subCatPrefix) {
		this.subCatPrefix = subCatPrefix;
	}
	public int getProdCount() {
		return prodCount;
	}
	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
	}
	
	@Override
	public String toString() {
		return "GetSubCatPrefix [uuid=" + uuid + ", subCatPrefix=" + subCatPrefix + ", prodCount=" + prodCount + "]";
	}
	
}
