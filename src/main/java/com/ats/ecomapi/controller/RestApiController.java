package com.ats.ecomapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.JsonUtil;
import com.ats.ecomapi.master.model.FrEmpLoginResp;
import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.master.model.GetOrderDetailDisplay;
import com.ats.ecomapi.master.model.GetOrderHeaderDisplay;
import com.ats.ecomapi.master.model.GetOrderTrailDisplay;
import com.ats.ecomapi.master.repo.FrEmpMasterRepo;
import com.ats.ecomapi.master.repo.FranchiseRepo;
import com.ats.ecomapi.master.repo.GetOrderHeaderRepo;
import com.ats.ecomapi.master.repo.GetOrderTrailDisplayRepo;
import com.ats.ecomapi.master.repo.OrderDetailListRepo;
import com.ats.ecomapi.mst_model.FrEmpMaster;
import com.ats.ecomapi.mst_model.FrLoginResponse;
import com.ats.ecomapi.mst_model.LoginInfo;
import com.ats.ecomapi.report.model.GetCustomerWisReport;
import com.ats.ecomapi.report.repo.GetCustomerWisReportRepo;

@RestController
public class RestApiController {

	public static String incrementDate(String date, int day) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));

		} catch (ParseException e) {
			System.out.println("Exception while incrementing date " + e.getMessage());
			e.printStackTrace();
		}
		c.add(Calendar.DATE, day); // number of days to add
		date = sdf.format(c.getTime());

		return date;

	}

	@Autowired
	FranchiseRepo franchiseRepo;
	
	@Autowired
	FrEmpMasterRepo frEmpRepo;	
	
	@Autowired
	GetOrderHeaderRepo orderHeaderRepo;
	
	@Autowired GetOrderTrailDisplayRepo getOrderTrailDisplayRepo;
	
	@Autowired OrderDetailListRepo orderDtlRepo;

	// Login FrontEnd Franchisee
	@RequestMapping(value = { "/loginFr" }, method = RequestMethod.POST)
	@ResponseBody
	public String loginFr(@RequestParam("frCode") String frCode, @RequestParam("frPasswordKey") String frPasswordKey) {

		String dbFrCode = null;
		String dbPassword = null;
		String jsonFr = "{}";
		Franchise dbFranchisee = new Franchise();

		FrLoginResponse frLoginResponse = new FrLoginResponse();
		LoginInfo loginInfo = new LoginInfo();
		try {
			dbFranchisee = franchiseRepo.findByFrCodeAndDelStatus(frCode);
			System.out.println(" details " + dbFranchisee.toString());

			dbFrCode = dbFranchisee.getFrCode();
			dbPassword = dbFranchisee.getFrPassword();
		} catch (Exception e) {

			System.out.println("Exception while finding prev fr " + e.getMessage());
			frLoginResponse = new FrLoginResponse();
			frLoginResponse.setFranchisee(dbFranchisee);
			loginInfo.setError(true);
			loginInfo.setAccessRight(0);

			loginInfo.setMessage("Franchisee Not Registerd");
			frLoginResponse.setLoginInfo(loginInfo);
			jsonFr = JsonUtil.javaToJson(frLoginResponse);
		}
		try {
			if (dbFranchisee.getFrCode() == null || dbFranchisee.getFrPassword() == null
					|| dbFranchisee.getFrCode().equalsIgnoreCase("")
					|| dbFranchisee.getFrPassword().equalsIgnoreCase("")) {

				System.out.println("Exception fr details null ");

				frLoginResponse.setFranchisee(dbFranchisee);
				loginInfo.setError(true);
				loginInfo.setAccessRight(0);
				loginInfo.setMessage("Franchisee Not Registered");
				frLoginResponse.setLoginInfo(loginInfo);
				jsonFr = JsonUtil.javaToJson(frLoginResponse);

			} else if (dbFrCode.equalsIgnoreCase(frCode) && dbPassword.equals(frPasswordKey)) {

				frLoginResponse.setFranchisee(dbFranchisee);
				loginInfo.setError(false);
				loginInfo.setAccessRight(1);
				loginInfo.setMessage("Franchisee displayed Successfully");
				frLoginResponse.setLoginInfo(loginInfo);
				jsonFr = JsonUtil.javaToJson(frLoginResponse);

			}
//					else if (dbFrCode.equalsIgnoreCase(frCode) && dbPassword != frPassword) {
//						
//						FranchiseSup franchiseSup=franchiseSupRepository.findFranchiseSupByFrId(dbFranchisee.getFrId());
//
//						System.out.println("FranchiseSup"+franchiseSup.toString());
//	                    if(franchiseSup!=null) {
//	                    	
//	                    	if(franchiseSup.getPass1().equals(frPassword))
//	                    	{
//	                    		frLoginResponse.setFranchisee(dbFranchisee);
//	        					loginInfo.setError(false);
//	        					loginInfo.setAccessRight(1);
//	        					loginInfo.setMessage("Franchisee displayed Successfully");
//	        					frLoginResponse.setLoginInfo(loginInfo);
//	        					jsonFr = JsonUtil.javaToJson(frLoginResponse);
//	                    	}
//	                    	else
//	                    		if(franchiseSup.getPass2().equals(frPassword))
//	                    		{
//	                    			frLoginResponse.setFranchisee(dbFranchisee);
//	            					loginInfo.setError(false);
//	            					loginInfo.setAccessRight(2);
//	            					loginInfo.setMessage("Franchisee displayed Successfully");
//	            					frLoginResponse.setLoginInfo(loginInfo);
//	            					jsonFr = JsonUtil.javaToJson(frLoginResponse);
//	                    		}
//	                    		else
//	                        		if(franchiseSup.getPass3().equals(frPassword))
//	                        		{
//	                        			frLoginResponse.setFranchisee(dbFranchisee);
//	                					loginInfo.setError(false);
//	                					loginInfo.setAccessRight(3);
//	                					loginInfo.setMessage("Franchisee displayed Successfully");
//	                					frLoginResponse.setLoginInfo(loginInfo);
//	                					jsonFr = JsonUtil.javaToJson(frLoginResponse);
//	                        		}
//	                        		else
//	                        		{
//
//	            						frLoginResponse = new FrLoginResponse();
//	            					 loginInfo.setError(true);
//	             					loginInfo.setAccessRight(0);
//
//	            					 loginInfo.setMessage("Invalid login details");
//	            					 frLoginResponse.setLoginInfo(loginInfo);
//	            					 jsonFr = JsonUtil.javaToJson(frLoginResponse);
//	                        		}
//						
//	                    }
//	                    
//					}
		} catch (Exception e) {
			System.out.println("Exception while converting prev fr " + e.getMessage());
			frLoginResponse = new FrLoginResponse();
			frLoginResponse.setFranchisee(dbFranchisee);
			loginInfo.setError(true);
			loginInfo.setAccessRight(0);

			loginInfo.setMessage("Franchisee Not Registered");
			frLoginResponse.setLoginInfo(loginInfo);
			jsonFr = JsonUtil.javaToJson(frLoginResponse);
		}
		return jsonFr;

	}
	
	
	
	@RequestMapping(value = { "/getAllFrEmpByFrid" }, method = RequestMethod.POST)
	public List<FrEmpMaster> getAllFrEmpByFrid(@RequestParam int frId) {
		List<FrEmpMaster> list = new ArrayList<FrEmpMaster>();
		try {
			System.err.println("Service Called---------"+frId);
			list = frEmpRepo.findByFrIdAndDelStatus(frId, 1);
			
			//System.err.println("List-----------" + list);
		} catch (Exception e) {
			System.err.println("Exception in getAllFrEmpByFrid : " + e.getMessage());
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/frEmpById" }, method = RequestMethod.POST)
	@ResponseBody
	public String frEmpById(@RequestParam("empId") int empId, @RequestParam("frId") int frId) {
		String frEmpMob = null;
		String frEmpPass = null;
		String jsonFr = "{}";
		
		Franchise franchise = new Franchise();
		FrEmpMaster frEmp = new FrEmpMaster();
		LoginInfo loginInfo=new LoginInfo();
		
		FrEmpLoginResp empLogResp = new FrEmpLoginResp();
		try { 
			frEmp=frEmpRepo.findByFrIdAndFrEmpIdAndDelStatus(frId,empId, 1);
			franchise = franchiseRepo.findByFrId(frId);
			System.out.println("Franchisee Employee Details : "+frEmp);
			loginInfo.setError(false);
			loginInfo.setAccessRight(1);
			frEmpMob = frEmp.getFrEmpContact();
			frEmpPass = frEmp.getPassword();
			empLogResp.setFrEmp(frEmp);
			
			empLogResp.setFranchisee(franchise);
			empLogResp.setLoginInfo(loginInfo);
			jsonFr = JsonUtil.javaToJson(empLogResp);
		}catch (Exception e) {
			e.printStackTrace();
			
			System.out.println("Exception while finding prev fr "+e.getMessage());
			
			empLogResp.setFrEmp(frEmp);
			empLogResp.setFranchisee(franchise);
			loginInfo.setError(true);
			loginInfo.setAccessRight(0);

			loginInfo.setMessage("Franchisee Employee Not Registerd");
			empLogResp.setLoginInfo(loginInfo);
			jsonFr = JsonUtil.javaToJson(empLogResp);
		}
		try {
			
			if (frEmp.getFrEmpContact() == null || frEmp.getPassword() == null||frEmp.getFrEmpContact().equalsIgnoreCase("")||frEmp.getPassword().equalsIgnoreCase("")) {

				System.out.println("Exception fr details null ");
				
				empLogResp.setFrEmp(frEmp);
				loginInfo.setError(true);
				loginInfo.setAccessRight(0);
				loginInfo.setMessage("Franchisee Employee Not Registered");
				empLogResp.setLoginInfo(loginInfo);
				jsonFr = JsonUtil.javaToJson(empLogResp);
				
			}
			
		}catch (Exception e) {
			System.out.println("Exception while converting prev fr "+e.getMessage());
			
			empLogResp.setFrEmp(frEmp);
			empLogResp.setFranchisee(franchise);
			loginInfo.setError(true);
			loginInfo.setAccessRight(0);

			loginInfo.setMessage("Franchisee Employee Not Registered");
			empLogResp.setLoginInfo(loginInfo);
			jsonFr = JsonUtil.javaToJson(empLogResp);
		}
		
		return jsonFr;
	}
	
	@RequestMapping(value = { "/getOrderHeaderListByFrId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetOrderHeaderDisplay> getOrderReportBetweenDateAndStatus(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("status") List<Integer> status, @RequestParam("compId") int compId, @RequestParam("frId") int frId) {

		List<GetOrderHeaderDisplay> orderList = new ArrayList<>();

		try {
			orderList = orderHeaderRepo.getOrderHeaderByDeliveryDateFrId(fromDate, toDate, status, compId, frId);
			
			List<GetOrderDetailDisplay> detailList = orderDtlRepo.getOrderDetailsyBillNo(compId);
			List<GetOrderTrailDisplay> trailList = getOrderTrailDisplayRepo.getOrderTrailListByCompId(compId);
			
			for (int i = 0; i < orderList.size(); i++) {
				List<GetOrderDetailDisplay> detailHeadList = new ArrayList<GetOrderDetailDisplay>();
				
				for (int j = 0; j < detailList.size(); j++) {
					if(orderList.get(i).getOrderId()==detailList.get(j).getOrderId()) {
						detailHeadList.add(detailList.get(j));
					}
				}
				
				orderList.get(i).setOrderDetailList(detailHeadList);
				
				List<GetOrderTrailDisplay> trailHeadList = new ArrayList<GetOrderTrailDisplay>();
				
				for (int k = 0; k < trailList.size(); k++) {
					
					if(orderList.get(i).getOrderId()==trailList.get(k).getOrderId()) {
						trailHeadList.add(trailList.get(k));
					}					
				}
				
				orderList.get(i).setOrderTrailList(trailHeadList);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderList;

	}
	
	
	
}
