package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.offer.repo.CkDeliveryChargesRepo;
import com.ats.ecomapi.offer.repo.FrChargesRepo;
import com.ats.ecomapi.offer.repo.OfferDetailRepo;
import com.ats.ecomapi.offer.repo.OfferHeaderRepo;
import com.ats.ecomapi.offer_model.CkDeliveryCharges;
import com.ats.ecomapi.offer_model.FrCharges;
import com.ats.ecomapi.offer_model.OfferDetail;
import com.ats.ecomapi.offer_model.OfferHeader;
import com.ats.ecomapi.offer_model.OrderCheckoutData;

@RestController
public class FrOfferController {

	@Autowired
	CkDeliveryChargesRepo ckDeliveryChargesRepo;

	@Autowired
	FrChargesRepo frChargesRepo;

	@Autowired
	OfferDetailRepo offerDetailRepo;
	
	@Autowired
	OfferHeaderRepo offerHeaderRepo;

	
	@RequestMapping(value = { "/getOrderCheckoutData" }, method = RequestMethod.POST)
	public @ResponseBody OrderCheckoutData getOrderCheckoutData(@RequestParam("km") float km,
			@RequestParam("frId") int frId) {

		System.err.println("KM = " + km + "           FR ID = " + frId + "------------------------------");

		OrderCheckoutData res = new OrderCheckoutData();

		try {
			// Delivery Charges

			try {
				CkDeliveryCharges delCh = ckDeliveryChargesRepo.getDeliveryCharges(km);
				if (delCh == null) {
					delCh = new CkDeliveryCharges();
				}
				res.setDeliveryCharges(delCh);
				System.err.println("delCh ------------------------------------------------------------- " + delCh);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				// Additional Charges By Franchise
				FrCharges addCh = frChargesRepo.findByfrId(frId);
				if (addCh == null) {
					addCh = new FrCharges();
				}
				res.setAdditionalCharges(addCh);
				System.err.println("addCh ------------------------------------------------------------- " + addCh);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				// Offers By Franchisee FOR Executive - bill offers
				List<OfferHeader> offerHeaderList = offerHeaderRepo.getBillOfferHeaderByFr(frId, 2, 1, 1);

				List<OfferDetail> offerDetailList = offerDetailRepo.getOfferDetailByFr(frId);

				System.err.println("offerHeaderList ------------------------------------------------------------- "
						+ offerHeaderList);

				if (offerHeaderList != null) {
					if (offerHeaderList.size() > 0) {

						for (OfferHeader header : offerHeaderList) {

							List<OfferDetail> detailList = new ArrayList<>();
							if (offerDetailList != null) {

								for (OfferDetail detail : offerDetailList) {
									if (header.getOfferId() == detail.getOfferId()) {
										detailList.add(detail);
									}
								}

							}
							header.setOfferDetailList(detailList);
						}
					}
				}
				res.setOfferList(offerHeaderList);
				res.setOfferDetailList(offerDetailList);

				System.err.println("offerDetailList ------------------------------------------------------------- "
						+ offerDetailList);

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.err.println("Res ------------------------------------------------------------- " + res);

		} catch (Exception e) {
			System.err.println("in e");
			e.printStackTrace();
		}
		return res;
	}
	
	@RequestMapping(value = { "/checkIsValidOffer" }, method = RequestMethod.POST)
	public @ResponseBody Info checkIsValidOffer(@RequestParam("offerId") int offerId,
			@RequestParam("coupon") String coupon, @RequestParam("custId") int custId) {

		Info info = new Info();
		try {
			int res = offerHeaderRepo.checkIsValidOffer(offerId, coupon, custId);
			if (res == 1) {
				info.setError(false);
			} else {
				info.setError(true);
			}
		} catch (Exception e) {
			info.setError(true);
		}

		return info;

	}
	
	@RequestMapping(value = { "/getCouponWiseBillOffers" }, method = RequestMethod.POST)
	public @ResponseBody List<OfferHeader> getCouponWiseOffers(@RequestParam("frId") int frId) {

		List<OfferHeader> offerHeaderList = new ArrayList<>();

		try {
			offerHeaderList = offerHeaderRepo.getBillOfferHeaderByFrCouponWise(frId, 2, 1, 1);
		} catch (Exception e) {
		}

		return offerHeaderList;

	}
	
	// GET CUSTOMER WISE OFFERS
		@RequestMapping(value = { "/getCustomerWiseBillOffers" }, method = RequestMethod.POST)
		public @ResponseBody List<OfferHeader> getCustomerWiseBillOffers(@RequestParam("frId") int frId,
				@RequestParam("custId") int custId) {

			List<OfferHeader> offerHeaderList = new ArrayList<>();

			System.err.println("frId - " + frId + "    Cust - " + custId);

			try {
				offerHeaderList = offerHeaderRepo.getBillOfferHeaderByFrCustomerWise(frId, 2, 1, 1, custId);
			} catch (Exception e) {
			}

			return offerHeaderList;

		}
		
		
		@RequestMapping(value = { "/getDeliveryCharges" }, method = RequestMethod.POST)
		public @ResponseBody CkDeliveryCharges getDeliveryCharges(@RequestParam("km") float km) {
			System.err.println(" Inside getDeliveryCharges " +km);
			CkDeliveryCharges res = new CkDeliveryCharges();

			try {
				// Delivery Charges
				res = ckDeliveryChargesRepo.getDeliveryCharges(km);

				if (res == null) {
					res = new CkDeliveryCharges();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		
		@RequestMapping(value = { "/getFrExCharges" }, method = RequestMethod.POST)
		public @ResponseBody FrCharges getFrExCharges(@RequestParam("frId") int frId) {
			System.err.println(" Inside getFrExCharges frId= " +frId);
			FrCharges frExCharge=new FrCharges();

			try {
				// Delivery Charges
				frExCharge = frChargesRepo.getFrExChargesSumForFrId(frId);
				System.err.println("frExCharge " +frExCharge);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return frExCharge;
		}
		
		
	/*
	 * @RequestMapping(value = { "/getAdditionalChargesAndOffersData" }, method =
	 * RequestMethod.POST) public @ResponseBody AdditionalChargesForApp
	 * getAdditionalChargesAndOffersData(@RequestParam("km") float km,
	 * 
	 * @RequestParam("frId") int frId, @RequestParam("custId") int custId,
	 * 
	 * @RequestParam("applicableFor") int applicableFor) {
	 * 
	 * System.err.println("KM = " + km + "           FR ID = " + frId +
	 * "------------------------------");
	 * 
	 * AdditionalChargesForApp res = new AdditionalChargesForApp(); Info info = new
	 * Info();
	 * 
	 * try { // Delivery Charges
	 * 
	 * try { CkDeliveryCharges delCh = ckDeliveryChargesRepo.getDeliveryCharges(km);
	 * if (delCh == null) { delCh = new CkDeliveryCharges(); }
	 * res.setDeliveryCharges(delCh); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * try { // Additional Charges By Franchise FrCharges addCh =
	 * frChargesRepo.findByfrId(frId); if (addCh == null) { addCh = new FrCharges();
	 * } res.setAdditionalCharges(addCh); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * // Offers------
	 * 
	 * List<OfferHeader> offerHeaderList = new ArrayList<>(); List<OfferHeader>
	 * allOfferHeaderList = new ArrayList<>();
	 * 
	 * // List<OfferHeader> couponWiseList = new ArrayList<>();
	 * 
	 * System.err.println("frId - " + frId + "    Cust - " + custId);
	 * 
	 * // try { // couponWiseList =
	 * offerHeaderRepo.getBillOfferHeaderByFrCouponWise(frId, 2, applicableFor, 1);
	 * // } catch (Exception e) { // }
	 * 
	 * try { allOfferHeaderList =
	 * offerHeaderRepo.getBillOfferHeaderByFrCustomerWise(frId, 2, applicableFor, 1,
	 * custId); } catch (Exception e) { }
	 * 
	 * // if (couponWiseList != null) { // offerHeaderList.addAll(couponWiseList);
	 * // }
	 * 
	 * Set<Integer> uniqueHeaderIds = new HashSet<>(); if (allOfferHeaderList !=
	 * null) { for (OfferHeader head : allOfferHeaderList) {
	 * uniqueHeaderIds.add(head.getOfferId()); } }
	 * 
	 * List<Integer> ids = new ArrayList<>(); ids.addAll(uniqueHeaderIds);
	 * 
	 * for (Integer id : ids) { for (OfferHeader head : allOfferHeaderList) { if (id
	 * == head.getOfferId()) {
	 * 
	 * offerHeaderList.add(head); break; } } }
	 * 
	 * // Offer Detail List-----------------
	 * 
	 * try { List<OfferDetail> offerDetailList =
	 * offerDetailRepo.getOfferDetailByFr(frId);
	 * System.err.println("DETAIl LITS ---------------- " + offerDetailList);
	 * 
	 * for (int i = 0; i < offerHeaderList.size(); i++) {
	 * 
	 * List<OfferDetail> tempDetail = new ArrayList<>();
	 * 
	 * for (OfferDetail detail : offerDetailList) {
	 * 
	 * if (offerHeaderList.get(i).getOfferId() == detail.getOfferId()) {
	 * tempDetail.add(detail); }
	 * 
	 * }
	 * 
	 * offerHeaderList.get(i).setOfferDetailList(tempDetail);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { }
	 * 
	 * res.setOfferList(offerHeaderList);
	 * 
	 * info.setError(false); info.setMessage("success");
	 * 
	 * CustWalletTotal wallet = new CustWalletTotal();
	 * 
	 * try { wallet = custWalletTotalRepo.getCustTotalWalletAmt(custId); if (wallet
	 * == null) { wallet = new CustWalletTotal(); }
	 * 
	 * NewSetting applyLimit =
	 * newSettingRepo.findBySettingKeyAndDelStatus("wallet_apply_on_rs", 0);
	 * NewSetting walletPer =
	 * newSettingRepo.findBySettingKeyAndDelStatus("wallet_apply_percent", 0);
	 * NewSetting walletRs =
	 * newSettingRepo.findBySettingKeyAndDelStatus("wallet_apply_rs", 0);
	 * 
	 * if (applyLimit != null) {
	 * wallet.setWalletLimitRs(Float.parseFloat(applyLimit.getSettingValue1())); }
	 * else { wallet.setWalletLimitRs(0); }
	 * 
	 * if (walletPer != null) {
	 * wallet.setWalletPercent(Float.parseFloat(walletPer.getSettingValue1())); }
	 * else { wallet.setWalletPercent(0); }
	 * 
	 * if (walletRs != null) {
	 * wallet.setWalletMinAmt(Float.parseFloat(walletRs.getSettingValue1())); } else
	 * { wallet.setWalletMinAmt(0); }
	 * 
	 * } catch (Exception e) { }
	 * 
	 * res.setCustWalletTotal(wallet);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); info.setError(true);
	 * info.setMessage("failed"); }
	 * 
	 * res.setInfo(info);
	 * 
	 * return res; }
	 */
}
