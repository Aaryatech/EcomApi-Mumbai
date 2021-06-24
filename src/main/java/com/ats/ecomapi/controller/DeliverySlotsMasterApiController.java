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

import com.ats.ecomapi.master.model.DeliverySlots;
import com.ats.ecomapi.master.repo.DeliverySlotsRepository;
import com.ats.ecomapi.mst_model.Info;

@RestController
public class DeliverySlotsMasterApiController {
	
	
	
	@Autowired
	DeliverySlotsRepository  DeliSlotRepo;
	
	
	@RequestMapping(value="/getAllDeliverySlots",method=RequestMethod.POST)
	public @ResponseBody List<DeliverySlots> getAllDeliverySlots(@RequestParam int compId){
		List<DeliverySlots> delSlotList=new ArrayList<>();
		
		try {
			delSlotList=DeliSlotRepo.getAllDeliverySlots( compId);
			System.err.println("Delivery Slot List Size==>"+delSlotList.size());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occured In /getAllDeliverySlots");
			e.printStackTrace();
		}
		return delSlotList;
	}
	
	
	@RequestMapping(value="/addDeliverySlots",method=RequestMethod.POST)
	public @ResponseBody DeliverySlots addDeliverySlots(@RequestBody DeliverySlots delSlot) {
		DeliverySlots deliverySlot=new DeliverySlots();
		
		try {
			deliverySlot=DeliSlotRepo.save(delSlot);
			System.err.println("Saved Del Slot --->"+deliverySlot);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /addDeliverySlots");
			e.printStackTrace();
		}
		return deliverySlot;
	}
	
	
	
	@RequestMapping(value="/getDeliverySlotById",method=RequestMethod.POST)
	public @ResponseBody DeliverySlots getDeliverySlotById(@RequestParam int delSlotId) {
		DeliverySlots deliverySlot=new DeliverySlots();
		
		try {
			deliverySlot=DeliSlotRepo.getDeliverySlotById(delSlotId);
			System.err.println(" Del Slot --->"+deliverySlot);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /getDeliverySlotById");
			e.printStackTrace();
		}
		return deliverySlot;
	}
	
	
	
	@RequestMapping(value="/DelteDeliSlots",method=RequestMethod.POST)
	public @ResponseBody Info DelteDeliSlots(@RequestParam int delSlotId) {
		Info info=new Info();
		int flag=0;
		try {
			flag=DeliSlotRepo.DelteDeliSlots(delSlotId);
			if(flag==0) {
				info.setError(true);
				info.setMsg("Unable To Delete Delivery Slots");
			}else {
				info.setError(false);
				info.setMsg("Delivery Slots Deleted!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMsg("Unable To Delete Delivery Slots Exception Occuered");
			System.err.println("Exception Occuered In /DelteDeliSlots");
			e.printStackTrace();
		}
		return info;
	}
	
	
	

}
