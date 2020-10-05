package com.ats.ecomapi.master.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetOrderDisplay {

	@Id
	private String id;

	private int orderId;
	private String orderNo;
	private String orderDate;
	private int frId;
	private int custId;
	private int status;
	private float taxableAmt;
	private float cgstAmt;
	private float sgstAmt;
	private float igstAmt;
	private float discAmt;
	private float itemDiscAmt;
	private float taxAmt;
	private float totalAmt;
	private int orderStatus;
	private int paidStatus;
	private int paymentMethod;
	private String paymentRemark;
	private int cityId;
	private int areaId;
	private int addressId;
	private String address;
	private String whatsappNo;
	private String landmark;
	private String deliveryDate;
	private String deliveryTime;
	private String insertDateTime;
	private int insertUserId;
	private int orderPlatform;
	private int delStatus;
	private int offerId;
	private String remark;
	private int orderDeliveredBy;
	private int exInt1;
	private int exInt2;
	private int exInt3;
	private int exInt4;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	private String exVar4;
	private float exFloat1;
	private float exFloat2;
	private float exFloat3;
	private float exFloat4;
	private String exDate1;
	private String exDate2;
	private String billingName;
	private String billingAddress;
	private String customerGstnNo;
	private int deliveryType;
	private int deliveryInstId;
	private String deliveryInstText;
	private float deliveryKm;
	private float deliveryCharges;
	private int paymentSubMode;
	private int isAgent;

	private int orderDetailId;
	private int itemId;
	private String hsnCode;
	private float qty;
	private float mrp;
	private float rate;
	private float detailTaxableAmt;
	private float cgstPer;
	private float sgstPer;
	private float igstPer;
	private float detailCgstAmt;
	private float detailSgstAmt;
	private float detailIgstAmt;
	private float detailDiscAmt;
	private float detailTaxAmt;
	private float detailTotalAmt;
	private String detailRemark;
	private int detailExInt1;
	private int detailExInt2;
	private int detailExInt3;
	private int detailExInt4;
	private String detailExVar1;
	private String detailExVar2;
	private String detailExVar3;
	private String detailExVar4;
	private float detailExFloat1;
	private float detailExFloat2;
	private float detailExFloat3;
	private float detailExFloat4;

	private String custName;
	private String custMobile;
	private String custWhatsappNo;
	private String frName;
	private String itemName;
	private int catId;
	private String catName;
	private String itemUom;
	private int uomId;
	private String cityName;
	private String areaName;
	private String offerName;
	private String offerDesc;
	private String orderDeliveredByName;

	private int deliveryMonth;
	private int deliveryYear;
	private String deliveryDateDisplay;
	private String deliveryTimeDisplay;
	private String monthName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(float cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public float getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(float sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public float getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(float igstAmt) {
		this.igstAmt = igstAmt;
	}

	public float getDiscAmt() {
		return discAmt;
	}

	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}

	public float getItemDiscAmt() {
		return itemDiscAmt;
	}

	public void setItemDiscAmt(float itemDiscAmt) {
		this.itemDiscAmt = itemDiscAmt;
	}

	public float getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(float taxAmt) {
		this.taxAmt = taxAmt;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(int paidStatus) {
		this.paidStatus = paidStatus;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentRemark() {
		return paymentRemark;
	}

	public void setPaymentRemark(String paymentRemark) {
		this.paymentRemark = paymentRemark;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWhatsappNo() {
		return whatsappNo;
	}

	public void setWhatsappNo(String whatsappNo) {
		this.whatsappNo = whatsappNo;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getInsertDateTime() {
		return insertDateTime;
	}

	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}

	public int getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(int insertUserId) {
		this.insertUserId = insertUserId;
	}

	public int getOrderPlatform() {
		return orderPlatform;
	}

	public void setOrderPlatform(int orderPlatform) {
		this.orderPlatform = orderPlatform;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOrderDeliveredBy() {
		return orderDeliveredBy;
	}

	public void setOrderDeliveredBy(int orderDeliveredBy) {
		this.orderDeliveredBy = orderDeliveredBy;
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

	public int getExInt4() {
		return exInt4;
	}

	public void setExInt4(int exInt4) {
		this.exInt4 = exInt4;
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

	public String getExVar4() {
		return exVar4;
	}

	public void setExVar4(String exVar4) {
		this.exVar4 = exVar4;
	}

	public float getExFloat1() {
		return exFloat1;
	}

	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}

	public float getExFloat2() {
		return exFloat2;
	}

	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
	}

	public float getExFloat3() {
		return exFloat3;
	}

	public void setExFloat3(float exFloat3) {
		this.exFloat3 = exFloat3;
	}

	public float getExFloat4() {
		return exFloat4;
	}

	public void setExFloat4(float exFloat4) {
		this.exFloat4 = exFloat4;
	}

	public String getExDate1() {
		return exDate1;
	}

	public void setExDate1(String exDate1) {
		this.exDate1 = exDate1;
	}

	public String getExDate2() {
		return exDate2;
	}

	public void setExDate2(String exDate2) {
		this.exDate2 = exDate2;
	}

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getCustomerGstnNo() {
		return customerGstnNo;
	}

	public void setCustomerGstnNo(String customerGstnNo) {
		this.customerGstnNo = customerGstnNo;
	}

	public int getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		this.deliveryType = deliveryType;
	}

	public int getDeliveryInstId() {
		return deliveryInstId;
	}

	public void setDeliveryInstId(int deliveryInstId) {
		this.deliveryInstId = deliveryInstId;
	}

	public String getDeliveryInstText() {
		return deliveryInstText;
	}

	public void setDeliveryInstText(String deliveryInstText) {
		this.deliveryInstText = deliveryInstText;
	}

	public float getDeliveryKm() {
		return deliveryKm;
	}

	public void setDeliveryKm(float deliveryKm) {
		this.deliveryKm = deliveryKm;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public float getMrp() {
		return mrp;
	}

	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getDetailTaxableAmt() {
		return detailTaxableAmt;
	}

	public void setDetailTaxableAmt(float detailTaxableAmt) {
		this.detailTaxableAmt = detailTaxableAmt;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public float getDetailCgstAmt() {
		return detailCgstAmt;
	}

	public void setDetailCgstAmt(float detailCgstAmt) {
		this.detailCgstAmt = detailCgstAmt;
	}

	public float getDetailSgstAmt() {
		return detailSgstAmt;
	}

	public void setDetailSgstAmt(float detailSgstAmt) {
		this.detailSgstAmt = detailSgstAmt;
	}

	public float getDetailIgstAmt() {
		return detailIgstAmt;
	}

	public void setDetailIgstAmt(float detailIgstAmt) {
		this.detailIgstAmt = detailIgstAmt;
	}

	public float getDetailDiscAmt() {
		return detailDiscAmt;
	}

	public void setDetailDiscAmt(float detailDiscAmt) {
		this.detailDiscAmt = detailDiscAmt;
	}

	public float getDetailTaxAmt() {
		return detailTaxAmt;
	}

	public void setDetailTaxAmt(float detailTaxAmt) {
		this.detailTaxAmt = detailTaxAmt;
	}

	public float getDetailTotalAmt() {
		return detailTotalAmt;
	}

	public void setDetailTotalAmt(float detailTotalAmt) {
		this.detailTotalAmt = detailTotalAmt;
	}

	public String getDetailRemark() {
		return detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public int getDetailExInt1() {
		return detailExInt1;
	}

	public void setDetailExInt1(int detailExInt1) {
		this.detailExInt1 = detailExInt1;
	}

	public int getDetailExInt2() {
		return detailExInt2;
	}

	public void setDetailExInt2(int detailExInt2) {
		this.detailExInt2 = detailExInt2;
	}

	public int getDetailExInt3() {
		return detailExInt3;
	}

	public void setDetailExInt3(int detailExInt3) {
		this.detailExInt3 = detailExInt3;
	}

	public int getDetailExInt4() {
		return detailExInt4;
	}

	public void setDetailExInt4(int detailExInt4) {
		this.detailExInt4 = detailExInt4;
	}

	public String getDetailExVar1() {
		return detailExVar1;
	}

	public void setDetailExVar1(String detailExVar1) {
		this.detailExVar1 = detailExVar1;
	}

	public String getDetailExVar2() {
		return detailExVar2;
	}

	public void setDetailExVar2(String detailExVar2) {
		this.detailExVar2 = detailExVar2;
	}

	public String getDetailExVar3() {
		return detailExVar3;
	}

	public void setDetailExVar3(String detailExVar3) {
		this.detailExVar3 = detailExVar3;
	}

	public String getDetailExVar4() {
		return detailExVar4;
	}

	public void setDetailExVar4(String detailExVar4) {
		this.detailExVar4 = detailExVar4;
	}

	public float getDetailExFloat1() {
		return detailExFloat1;
	}

	public void setDetailExFloat1(float detailExFloat1) {
		this.detailExFloat1 = detailExFloat1;
	}

	public float getDetailExFloat2() {
		return detailExFloat2;
	}

	public void setDetailExFloat2(float detailExFloat2) {
		this.detailExFloat2 = detailExFloat2;
	}

	public float getDetailExFloat3() {
		return detailExFloat3;
	}

	public void setDetailExFloat3(float detailExFloat3) {
		this.detailExFloat3 = detailExFloat3;
	}

	public float getDetailExFloat4() {
		return detailExFloat4;
	}

	public void setDetailExFloat4(float detailExFloat4) {
		this.detailExFloat4 = detailExFloat4;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getCustWhatsappNo() {
		return custWhatsappNo;
	}

	public void setCustWhatsappNo(String custWhatsappNo) {
		this.custWhatsappNo = custWhatsappNo;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getItemUom() {
		return itemUom;
	}

	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}

	public int getUomId() {
		return uomId;
	}

	public void setUomId(int uomId) {
		this.uomId = uomId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDesc() {
		return offerDesc;
	}

	public void setOfferDesc(String offerDesc) {
		this.offerDesc = offerDesc;
	}

	public String getOrderDeliveredByName() {
		return orderDeliveredByName;
	}

	public void setOrderDeliveredByName(String orderDeliveredByName) {
		this.orderDeliveredByName = orderDeliveredByName;
	}

	public int getDeliveryMonth() {
		return deliveryMonth;
	}

	public void setDeliveryMonth(int deliveryMonth) {
		this.deliveryMonth = deliveryMonth;
	}

	public int getDeliveryYear() {
		return deliveryYear;
	}

	public void setDeliveryYear(int deliveryYear) {
		this.deliveryYear = deliveryYear;
	}

	public String getDeliveryDateDisplay() {
		return deliveryDateDisplay;
	}

	public void setDeliveryDateDisplay(String deliveryDateDisplay) {
		this.deliveryDateDisplay = deliveryDateDisplay;
	}

	public String getDeliveryTimeDisplay() {
		return deliveryTimeDisplay;
	}

	public void setDeliveryTimeDisplay(String deliveryTimeDisplay) {
		this.deliveryTimeDisplay = deliveryTimeDisplay;
	}

	public float getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(float deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public int getPaymentSubMode() {
		return paymentSubMode;
	}

	public void setPaymentSubMode(int paymentSubMode) {
		this.paymentSubMode = paymentSubMode;
	}

	public int getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(int isAgent) {
		this.isAgent = isAgent;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	@Override
	public String toString() {
		return "GetOrderDisplay [id=" + id + ", orderId=" + orderId + ", orderNo=" + orderNo + ", orderDate="
				+ orderDate + ", frId=" + frId + ", custId=" + custId + ", status=" + status + ", taxableAmt="
				+ taxableAmt + ", cgstAmt=" + cgstAmt + ", sgstAmt=" + sgstAmt + ", igstAmt=" + igstAmt + ", discAmt="
				+ discAmt + ", itemDiscAmt=" + itemDiscAmt + ", taxAmt=" + taxAmt + ", totalAmt=" + totalAmt
				+ ", orderStatus=" + orderStatus + ", paidStatus=" + paidStatus + ", paymentMethod=" + paymentMethod
				+ ", paymentRemark=" + paymentRemark + ", cityId=" + cityId + ", areaId=" + areaId + ", addressId="
				+ addressId + ", address=" + address + ", whatsappNo=" + whatsappNo + ", landmark=" + landmark
				+ ", deliveryDate=" + deliveryDate + ", deliveryTime=" + deliveryTime + ", insertDateTime="
				+ insertDateTime + ", insertUserId=" + insertUserId + ", orderPlatform=" + orderPlatform
				+ ", delStatus=" + delStatus + ", offerId=" + offerId + ", remark=" + remark + ", orderDeliveredBy="
				+ orderDeliveredBy + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exInt4="
				+ exInt4 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", exVar4=" + exVar4
				+ ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2 + ", exFloat3=" + exFloat3 + ", exFloat4="
				+ exFloat4 + ", exDate1=" + exDate1 + ", exDate2=" + exDate2 + ", billingName=" + billingName
				+ ", billingAddress=" + billingAddress + ", customerGstnNo=" + customerGstnNo + ", deliveryType="
				+ deliveryType + ", deliveryInstId=" + deliveryInstId + ", deliveryInstText=" + deliveryInstText
				+ ", deliveryKm=" + deliveryKm + ", deliveryCharges=" + deliveryCharges + ", paymentSubMode="
				+ paymentSubMode + ", isAgent=" + isAgent + ", orderDetailId=" + orderDetailId + ", itemId=" + itemId
				+ ", hsnCode=" + hsnCode + ", qty=" + qty + ", mrp=" + mrp + ", rate=" + rate + ", detailTaxableAmt="
				+ detailTaxableAmt + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer=" + igstPer
				+ ", detailCgstAmt=" + detailCgstAmt + ", detailSgstAmt=" + detailSgstAmt + ", detailIgstAmt="
				+ detailIgstAmt + ", detailDiscAmt=" + detailDiscAmt + ", detailTaxAmt=" + detailTaxAmt
				+ ", detailTotalAmt=" + detailTotalAmt + ", detailRemark=" + detailRemark + ", detailExInt1="
				+ detailExInt1 + ", detailExInt2=" + detailExInt2 + ", detailExInt3=" + detailExInt3 + ", detailExInt4="
				+ detailExInt4 + ", detailExVar1=" + detailExVar1 + ", detailExVar2=" + detailExVar2 + ", detailExVar3="
				+ detailExVar3 + ", detailExVar4=" + detailExVar4 + ", detailExFloat1=" + detailExFloat1
				+ ", detailExFloat2=" + detailExFloat2 + ", detailExFloat3=" + detailExFloat3 + ", detailExFloat4="
				+ detailExFloat4 + ", custName=" + custName + ", custMobile=" + custMobile + ", custWhatsappNo="
				+ custWhatsappNo + ", frName=" + frName + ", itemName=" + itemName + ", catId=" + catId + ", catName="
				+ catName + ", itemUom=" + itemUom + ", uomId=" + uomId + ", cityName=" + cityName + ", areaName="
				+ areaName + ", offerName=" + offerName + ", offerDesc=" + offerDesc + ", orderDeliveredByName="
				+ orderDeliveredByName + ", deliveryMonth=" + deliveryMonth + ", deliveryYear=" + deliveryYear
				+ ", deliveryDateDisplay=" + deliveryDateDisplay + ", deliveryTimeDisplay=" + deliveryTimeDisplay
				+ ", monthName=" + monthName + "]";
	}

}
