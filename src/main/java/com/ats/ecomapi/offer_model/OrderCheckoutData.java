package com.ats.ecomapi.offer_model;

import java.util.List;

public class OrderCheckoutData {

	private CkDeliveryCharges deliveryCharges;
	private FrCharges additionalCharges;
	private List<OfferHeader> offerList;
	private List<OfferDetail> offerDetailList;

	public CkDeliveryCharges getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(CkDeliveryCharges deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public FrCharges getAdditionalCharges() {
		return additionalCharges;
	}

	public void setAdditionalCharges(FrCharges additionalCharges) {
		this.additionalCharges = additionalCharges;
	}

	public List<OfferHeader> getOfferList() {
		return offerList;
	}

	public void setOfferList(List<OfferHeader> offerList) {
		this.offerList = offerList;
	}

	public List<OfferDetail> getOfferDetailList() {
		return offerDetailList;
	}

	public void setOfferDetailList(List<OfferDetail> offerDetailList) {
		this.offerDetailList = offerDetailList;
	}

	@Override
	public String toString() {
		return "OrderCheckoutData [deliveryCharges=" + deliveryCharges + ", additionalCharges=" + additionalCharges
				+ ", offerList=" + offerList + ", offerDetailList=" + offerDetailList + "]";
	}

}
