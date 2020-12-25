package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.fe_model.DeliveryCharges;
import com.ats.ecomapi.fe_repo.DeliveryChargesRepo;
import com.ats.ecomapi.mst_model.Info;

@RestController
public class DeliveryChargesApiController {
	
	@Autowired
	DeliveryChargesRepo deliveryChargeRepo;
	
	@RequestMapping(value = { "/getAllDeliveryCharges" }, method = RequestMethod.GET)
	public @ResponseBody List<DeliveryCharges> getAllDeliveryCharges() {

		List<DeliveryCharges> chagesList = new ArrayList<DeliveryCharges>();
		try {
			chagesList = deliveryChargeRepo.findByDelStatusOrderByChIdDesc(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chagesList;
	}

	@RequestMapping(value = { "/addNewDeliveryCharge" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryCharges addNewDeliveryCharge(@RequestBody DeliveryCharges delCharge) {

		DeliveryCharges saveDelCharge = new DeliveryCharges();
		try {
			saveDelCharge = deliveryChargeRepo.save(delCharge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveDelCharge;
	}

	@RequestMapping(value = { "/deleteDeliverChargeById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteDeliverChargeById(@RequestParam int chargeId) {

		Info info = new Info();
		try {
			int res = deliveryChargeRepo.deleteDeliveryCharges(chargeId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Deliver Charge Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed To Delete Deliver Charge");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/getDeliveryChargeById" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryCharges getDeliveryChargeById(@RequestParam int chargeId) {

		DeliveryCharges charge = new DeliveryCharges();
		try {
			charge = deliveryChargeRepo.findByChId(chargeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charge;
	}
	
	
	
	

}
