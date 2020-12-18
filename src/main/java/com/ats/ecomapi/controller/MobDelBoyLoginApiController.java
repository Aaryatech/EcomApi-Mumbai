package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.fe_model.DeliveryBoy;
import com.ats.ecomapi.master.model.GetTnOrderDetails;
import com.ats.ecomapi.master.model.GetTnOrderHeader;
import com.ats.ecomapi.master.model.GrievencesInstruction;
import com.ats.ecomapi.master.repo.DeliveryBoyRepo;
import com.ats.ecomapi.master.repo.GetTnOrderDetailsRepo;
import com.ats.ecomapi.master.repo.GetTnOrderHeaderRepo;
import com.ats.ecomapi.master.repo.GrievencesInstructionRepo;
import com.ats.ecomapi.master.repo.SettingRepository;
import com.ats.ecomapi.mst_model.GetGrievanceHeader;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_repo.GetGrievanceRepo;


//Akhilesh 2020-12-17
@RestController
public class MobDelBoyLoginApiController {
	
	@Autowired
	DeliveryBoyRepo deliveryBoyRepo;
	
	@Autowired
	GetTnOrderHeaderRepo getTnOrderHeaderRepo;
	
	@Autowired
	GetTnOrderDetailsRepo getTnOrderDetailsRepo;
	
	
	@Autowired
	SettingRepository settingRepository;
	
	
	@Autowired 
	GetGrievanceRepo getGrievRepo;
	
	
	@Autowired
	GrievencesInstructionRepo grievanceRepo;
	
	
	
	//Akhilesh 2020-12-17
	@RequestMapping(value="/GetDelBoyByMobAndEmpCode",method=RequestMethod.POST)
	public @ResponseBody DeliveryBoy GetDelBoyByMobAndEmpCode(@RequestParam String mobNo,@RequestParam int empCode){
		DeliveryBoy dBoyResp=new DeliveryBoy();
		
		try {
			dBoyResp=deliveryBoyRepo.GetDelBoyByMobAndEmpCode(mobNo, empCode);
			System.err.println("Delv Boy Details---->"+dBoyResp);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /GetDelBoyByMobAndEmpCode");
			e.printStackTrace();
		}
		
		return dBoyResp;
	}
	
	
	//Akhilesh 2020-12-17
	@RequestMapping(value="/getOrderHeaderByStatusAndDelBoyId",method=RequestMethod.POST)
	public @ResponseBody List<GetTnOrderHeader> getOrderHeaderByStatusAndDelBoyId(@RequestParam  int[] status,@RequestParam int delBoyId){
		List<GetTnOrderHeader> getTnOrederHeadResp=new ArrayList<>();
		List<GetTnOrderDetails> orderDetailList=new ArrayList<>();
		
		try {
			getTnOrederHeadResp=getTnOrderHeaderRepo.getOrderHeaderByStatusAndDelBoyId(status, delBoyId);
			for(GetTnOrderHeader header : getTnOrederHeadResp) {
				orderDetailList=getTnOrderDetailsRepo.GetGetTnOrderDetailsListByOrderId(header.getOrderId());
				header.setTnOrderlist(orderDetailList);
			}
			System.err.println("getTnOrederHeadResp Sizw--->"+getTnOrederHeadResp.size());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occured In /getOrderHeaderByStatusAndDelBoyId");
			e.printStackTrace();
		}
		
		return getTnOrederHeadResp;
		
	}
	
	
	//Akhilesh 2020-12-17
	@RequestMapping(value="/updateTnOrderHeaderStatus",method=RequestMethod.POST)
	public @ResponseBody Info  updateTnOrderHeaderStatus(@RequestParam int orderId,@RequestParam int status) {
		Info info=new Info();
		int flag=0;
		try {
			flag=getTnOrderHeaderRepo.updateTnOrderHeaderStatus(orderId, status);
			if(flag>0) {
				info.setError(false);
				info.setMessage("Order Header Status Updated!!!");
			}else {
				info.setError(true);
				info.setMessage("Unable To Update Order Header Status !!!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMessage("Unable To Update Order Header Status  Exception Occuered!!!");
			System.err.println("Exception Occuered In /updateTnOrderHeaderStatus");
			e.printStackTrace();
		}
		return info;
		
	}
	
	// Created By :- Akhilesh 
			// Created On :- 17-12-2020
			// Modified By :- NA
			// Modified On :- NA
			// Description :- Get grievance list by DeliveryBoy Id
			@RequestMapping(value = { "/getGrievanceHeaderByDelBoyId" }, method = RequestMethod.POST)
			public @ResponseBody List<GetGrievanceHeader> getGrievanceHeaderByDelBoyId(@RequestParam("delBoyId") int delBoyId) {

				List<GetGrievanceHeader> grievList = new ArrayList<GetGrievanceHeader>();
				try {
					grievList = getGrievRepo.getGrievanceHeaderByDelBoyId(delBoyId);

				} catch (Exception e) {
					e.printStackTrace();
				}
				return grievList;
			}

	
			// Created By :- Akhilesh 
						// Created On :- 17-12-2020
						// Modified By :- NA
						// Modified On :- NA
						// Description :- Get grievance list by Company  Id From mn_grievences_instruction
			@RequestMapping(value="/getGrievnceListByCompId",method=RequestMethod.POST)
			public @ResponseBody List<GrievencesInstruction> getGrievnceListByCompId(@RequestParam int compId) {
				List<GrievencesInstruction> GrievanceInstrucList=new ArrayList<>();
				
				
				try {
					GrievanceInstrucList=grievanceRepo.getGrievnceListByCompId(compId);
					System.err.println("GrievanceInstrucList Size--->"+GrievanceInstrucList.size());
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("Exception Occured In /getGrievnceListByCompId");
					e.printStackTrace();
				}
				
				
				
				return GrievanceInstrucList;
				
				
				
				
				
			}
	
	
	
	
	

}
