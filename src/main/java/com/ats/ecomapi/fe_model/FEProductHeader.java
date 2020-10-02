package com.ats.ecomapi.fe_model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

//Author -Sachin
//Created on -01-10-2020
//Desc - to show product master data to front end pages.

@Entity
public class FEProductHeader {

	@Id
	private String prodUuid;

	private int productId;
	private String productCode;
	private String productName;
	private int prodCatId;
	private int prodSubCatId;
	private int taxId;

	private float minQty;

	private int uomId;
	private String shortName;
	private int shapeId;

	private int allowSameDayDelivery;
	private String sameDayTimeAllowedSlot;
	private int prodTypeId;

	private String availInWeights;

	private String flavourIds;

	private int prodStatusId;
	private int bookBefore;

	private String eventsIds;

	private int isCharLimit;
	private int noOfCharsForAlphaCake;

	private int allowCoverPhotoUpload;

	private int allowBasePhotoUpload;

	private int allowSpecialInstruction;

	private int allowMsgOnCake;
	private int noOfCharsOnCake;

	private String productDesc;

	private String ingerdiants;

	private String applicableTags;

	private int companyId; // FK(company_id)

	private String prodImagePrimary; // primary image of product

	private String productImages; // comma sep image names

	private int isVeg; // 0 Veg 1 non Veg 2 Both
	private int prepTime; // Cake Preparation time in minutes
	private int rateSettingType; // 0apply_rate_per_UOM/1apply_rate_per_KG/2apply_rate_as_per _filter

	private int maxWt;

	private String exVar1;
	private String exVar2;

	private float displayRate;
	private float actualRate;

	private int frachaseConfigId; // Primary Key m_fr_configration
	private int isHomePageProd; // 0 No 1 Yes

	private int configHeaderId; // Primary Key tn_item_config_header

	
	
	@Transient
	List<FEProdDetail> prodDetailList;
	
	
	public String getProdUuid() {
		return prodUuid;
	}

	public void setProdUuid(String prodUuid) {
		this.prodUuid = prodUuid;
	}

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

	public int getProdCatId() {
		return prodCatId;
	}

	public void setProdCatId(int prodCatId) {
		this.prodCatId = prodCatId;
	}

	public int getProdSubCatId() {
		return prodSubCatId;
	}

	public void setProdSubCatId(int prodSubCatId) {
		this.prodSubCatId = prodSubCatId;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public float getMinQty() {
		return minQty;
	}

	public void setMinQty(float minQty) {
		this.minQty = minQty;
	}

	public int getUomId() {
		return uomId;
	}

	public void setUomId(int uomId) {
		this.uomId = uomId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getShapeId() {
		return shapeId;
	}

	public void setShapeId(int shapeId) {
		this.shapeId = shapeId;
	}

	public int getAllowSameDayDelivery() {
		return allowSameDayDelivery;
	}

	public void setAllowSameDayDelivery(int allowSameDayDelivery) {
		this.allowSameDayDelivery = allowSameDayDelivery;
	}

	public String getSameDayTimeAllowedSlot() {
		return sameDayTimeAllowedSlot;
	}

	public void setSameDayTimeAllowedSlot(String sameDayTimeAllowedSlot) {
		this.sameDayTimeAllowedSlot = sameDayTimeAllowedSlot;
	}

	public int getProdTypeId() {
		return prodTypeId;
	}

	public void setProdTypeId(int prodTypeId) {
		this.prodTypeId = prodTypeId;
	}

	public String getAvailInWeights() {
		return availInWeights;
	}

	public void setAvailInWeights(String availInWeights) {
		this.availInWeights = availInWeights;
	}

	public String getFlavourIds() {
		return flavourIds;
	}

	public void setFlavourIds(String flavourIds) {
		this.flavourIds = flavourIds;
	}

	public int getProdStatusId() {
		return prodStatusId;
	}

	public void setProdStatusId(int prodStatusId) {
		this.prodStatusId = prodStatusId;
	}

	public int getBookBefore() {
		return bookBefore;
	}

	public void setBookBefore(int bookBefore) {
		this.bookBefore = bookBefore;
	}

	public String getEventsIds() {
		return eventsIds;
	}

	public void setEventsIds(String eventsIds) {
		this.eventsIds = eventsIds;
	}

	public int getIsCharLimit() {
		return isCharLimit;
	}

	public void setIsCharLimit(int isCharLimit) {
		this.isCharLimit = isCharLimit;
	}

	public int getNoOfCharsForAlphaCake() {
		return noOfCharsForAlphaCake;
	}

	public void setNoOfCharsForAlphaCake(int noOfCharsForAlphaCake) {
		this.noOfCharsForAlphaCake = noOfCharsForAlphaCake;
	}

	public int getAllowCoverPhotoUpload() {
		return allowCoverPhotoUpload;
	}

	public void setAllowCoverPhotoUpload(int allowCoverPhotoUpload) {
		this.allowCoverPhotoUpload = allowCoverPhotoUpload;
	}

	public int getAllowBasePhotoUpload() {
		return allowBasePhotoUpload;
	}

	public void setAllowBasePhotoUpload(int allowBasePhotoUpload) {
		this.allowBasePhotoUpload = allowBasePhotoUpload;
	}

	public int getAllowSpecialInstruction() {
		return allowSpecialInstruction;
	}

	public void setAllowSpecialInstruction(int allowSpecialInstruction) {
		this.allowSpecialInstruction = allowSpecialInstruction;
	}

	public int getAllowMsgOnCake() {
		return allowMsgOnCake;
	}

	public void setAllowMsgOnCake(int allowMsgOnCake) {
		this.allowMsgOnCake = allowMsgOnCake;
	}

	public int getNoOfCharsOnCake() {
		return noOfCharsOnCake;
	}

	public void setNoOfCharsOnCake(int noOfCharsOnCake) {
		this.noOfCharsOnCake = noOfCharsOnCake;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getIngerdiants() {
		return ingerdiants;
	}

	public void setIngerdiants(String ingerdiants) {
		this.ingerdiants = ingerdiants;
	}

	public String getApplicableTags() {
		return applicableTags;
	}

	public void setApplicableTags(String applicableTags) {
		this.applicableTags = applicableTags;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getProdImagePrimary() {
		return prodImagePrimary;
	}

	public void setProdImagePrimary(String prodImagePrimary) {
		this.prodImagePrimary = prodImagePrimary;
	}

	public String getProductImages() {
		return productImages;
	}

	public void setProductImages(String productImages) {
		this.productImages = productImages;
	}

	public int getIsVeg() {
		return isVeg;
	}

	public void setIsVeg(int isVeg) {
		this.isVeg = isVeg;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public int getRateSettingType() {
		return rateSettingType;
	}

	public void setRateSettingType(int rateSettingType) {
		this.rateSettingType = rateSettingType;
	}

	public int getMaxWt() {
		return maxWt;
	}

	public void setMaxWt(int maxWt) {
		this.maxWt = maxWt;
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

	public float getDisplayRate() {
		return displayRate;
	}

	public void setDisplayRate(float displayRate) {
		this.displayRate = displayRate;
	}

	public float getActualRate() {
		return actualRate;
	}

	public void setActualRate(float actualRate) {
		this.actualRate = actualRate;
	}

	

	public int getFrachaseConfigId() {
		return frachaseConfigId;
	}

	public void setFrachaseConfigId(int frachaseConfigId) {
		this.frachaseConfigId = frachaseConfigId;
	}

	public int getIsHomePageProd() {
		return isHomePageProd;
	}

	public void setIsHomePageProd(int isHomePageProd) {
		this.isHomePageProd = isHomePageProd;
	}

	public int getConfigHeaderId() {
		return configHeaderId;
	}

	public void setConfigHeaderId(int configHeaderId) {
		this.configHeaderId = configHeaderId;
	}


	public List<FEProdDetail> getProdDetailList() {
		return prodDetailList;
	}

	public void setProdDetailList(List<FEProdDetail> prodDetailList) {
		this.prodDetailList = prodDetailList;
	}
	
	@Override
	public String toString() {
		return "FEProductHeader [prodUuid=" + prodUuid + ", productId=" + productId + ", productCode=" + productCode
				+ ", productName=" + productName + ", prodCatId=" + prodCatId + ", prodSubCatId=" + prodSubCatId
				+ ", taxId=" + taxId + ", minQty=" + minQty + ", uomId=" + uomId + ", shortName=" + shortName
				+ ", shapeId=" + shapeId + ", allowSameDayDelivery=" + allowSameDayDelivery
				+ ", sameDayTimeAllowedSlot=" + sameDayTimeAllowedSlot + ", prodTypeId=" + prodTypeId
				+ ", availInWeights=" + availInWeights + ", flavourIds=" + flavourIds + ", prodStatusId=" + prodStatusId
				+ ", bookBefore=" + bookBefore + ", eventsIds=" + eventsIds + ", isCharLimit=" + isCharLimit
				+ ", noOfCharsForAlphaCake=" + noOfCharsForAlphaCake + ", allowCoverPhotoUpload="
				+ allowCoverPhotoUpload + ", allowBasePhotoUpload=" + allowBasePhotoUpload
				+ ", allowSpecialInstruction=" + allowSpecialInstruction + ", allowMsgOnCake=" + allowMsgOnCake
				+ ", noOfCharsOnCake=" + noOfCharsOnCake + ", productDesc=" + productDesc + ", ingerdiants="
				+ ingerdiants + ", applicableTags=" + applicableTags + ", companyId=" + companyId
				+ ", prodImagePrimary=" + prodImagePrimary + ", productImages=" + productImages + ", isVeg=" + isVeg
				+ ", prepTime=" + prepTime + ", rateSettingType=" + rateSettingType + ", maxWt=" + maxWt + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", displayRate=" + displayRate + ", actualRate=" + actualRate
				+ ", frachaseConfigId=" + frachaseConfigId + ", isHomePageProd=" + isHomePageProd + ", configHeaderId="
				+ configHeaderId + ", prodDetailList=" + prodDetailList + "]";
	}

	
	
}
