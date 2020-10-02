package com.ats.ecomapi.fe_model;

import javax.persistence.Entity;
import javax.persistence.Id;

//Author -Sachin
//Created on -02-10-2020
//Desc - to show Banner list data for Franchise and 
//Company to front end pages.
@Entity

public class FEBannerList {

	@Id
	private String uuid;
	
	private int bannerId;
	
	private String bannerEventName;
	private String bannerImage;
	
	private int sortNo;
	private String frIds;
	private String tagIds;
	private String captionOnproductPage;
	
	private String updateDateTime;
	private String exVar1;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getBannerId() {
		return bannerId;
	}
	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}
	public String getBannerEventName() {
		return bannerEventName;
	}
	public void setBannerEventName(String bannerEventName) {
		this.bannerEventName = bannerEventName;
	}
	public String getBannerImage() {
		return bannerImage;
	}
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public String getFrIds() {
		return frIds;
	}
	public void setFrIds(String frIds) {
		this.frIds = frIds;
	}
	public String getTagIds() {
		return tagIds;
	}
	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}
	public String getCaptionOnproductPage() {
		return captionOnproductPage;
	}
	public void setCaptionOnproductPage(String captionOnproductPage) {
		this.captionOnproductPage = captionOnproductPage;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	
	@Override
	public String toString() {
		return "FEBannerList [uuid=" + uuid + ", bannerId=" + bannerId + ", bannerEventName=" + bannerEventName
				+ ", bannerImage=" + bannerImage + ", sortNo=" + sortNo + ", frIds=" + frIds + ", tagIds=" + tagIds
				+ ", captionOnproductPage=" + captionOnproductPage + ", updateDateTime=" + updateDateTime + ", exVar1="
				+ exVar1 + "]";
	}
	
}
