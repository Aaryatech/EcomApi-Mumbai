package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.DeliveryBoy_Repo.GrievencesRepo;
import com.ats.ecomapi.deliveryboy_model.Grievances;
import com.ats.ecomapi.fe_model.CategorywiseSell;
import com.ats.ecomapi.fe_repo.CategorywiseItemSellRepo;
import com.ats.ecomapi.master.model.GetOrderDetailDisplay;
import com.ats.ecomapi.master.model.GetOrderHeaderDisplay;
import com.ats.ecomapi.master.model.GetOrderTrailDisplay;
import com.ats.ecomapi.master.repo.GetDashStatusCntRepo;
import com.ats.ecomapi.master.repo.GetOrderHeaderRepo;
import com.ats.ecomapi.master.repo.GetOrderTrailDisplayRepo;
import com.ats.ecomapi.master.repo.OrderDetailListRepo;
import com.ats.ecomapi.mst_model.CategorywiseItemSell;
import com.ats.ecomapi.mst_repo.CategorywiseSellRepo;
import com.ats.ecomapi.report.model.GetDashPieStatusCnt;

@RestController
public class DashApiController {
	@Autowired
	GetDashStatusCntRepo dashRepo;

	@Autowired
	GetOrderHeaderRepo orderHeaderRepo;
	
	@Autowired GetOrderTrailDisplayRepo getOrderTrailDisplayRepo;
	
	@Autowired OrderDetailListRepo orderDtlRepo;
	
	@Autowired
	CategorywiseSellRepo categorywiseSellRepo;
	
	@Autowired
	CategorywiseItemSellRepo categorywiseItemSellRepo;
	
	@Autowired 
	GrievencesRepo grievRepo;
	

	@RequestMapping(value = { "/getAllStatusCount" }, method = RequestMethod.POST)
	@ResponseBody
	public List<GetDashPieStatusCnt> getAllStatusCount(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("compId") int compId) {
		List<GetDashPieStatusCnt> res = new ArrayList<GetDashPieStatusCnt>();
		try {

			res = dashRepo.getDashStatusCnt(fromDate, toDate, compId);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return res;
	}
	
	
	@RequestMapping(value = { "/getAllStatusCountByFrId" }, method = RequestMethod.POST)
	@ResponseBody
	public List<GetDashPieStatusCnt> getAllStatusCountByFrId(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("compId") int compId, @RequestParam("frId") int frId) {
		List<GetDashPieStatusCnt> res = new ArrayList<GetDashPieStatusCnt>();
		try {

			res = dashRepo.getDashStatusCntByFrId(fromDate, toDate, compId, frId);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = { "/getOrderHeaderListByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetOrderHeaderDisplay> getOrderReportBetweenDateAndStatus(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("status") List<Integer> status, @RequestParam("compId") int compId) {

		List<GetOrderHeaderDisplay> orderList = new ArrayList<>();

		try {
			orderList = orderHeaderRepo.getOrderHeaderByDeliveryDate(fromDate, toDate, status, compId);
			
			List<GetOrderDetailDisplay> detailList = orderDtlRepo.getOrderDetailsyBillNo(compId);
			List<GetOrderTrailDisplay> trailList = getOrderTrailDisplayRepo.getOrderTrailListByCompId(compId);
			List<Grievances> grList= grievRepo.getAllGreviences();			
			
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
				
				List<Grievances> grievList = new ArrayList<Grievances>();
				for (int j = 0; j < grList.size(); j++) {
					if(orderList.get(i).getOrderId() == Integer.parseInt(grList.get(j).getdDate())) {						
						grievList.add(grList.get(j));
					}
				}
				orderList.get(i).setGrievances(grievList);				
			}			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderList;

	}
	
	@RequestMapping(value = { "/getAllGrievanceList" }, method = RequestMethod.GET)
	public @ResponseBody List<Grievances> getCatwiseSell() {
		List<Grievances> grievList = new ArrayList<Grievances>();
		try {
			grievList= grievRepo.getAllGreviences();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return grievList;		
	}
	
	
	@RequestMapping(value = { "/getCatwiseSell" }, method = RequestMethod.POST)
	public @ResponseBody List<CategorywiseSell> getCatwiseSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") int frId) {

		List<CategorywiseSell> crnReport = new ArrayList<CategorywiseSell>();

		try {

			crnReport = categorywiseSellRepo.getCategorywiseSell(fromDate, toDate, frId);

		} catch (Exception e) {

			System.err.println("Exception in DashBoardReporApi /getCredNoteReport" + e.getMessage());

			e.printStackTrace();
		}
		return crnReport;
	}
	
	@RequestMapping(value = { "/getCatwiseItemSell" }, method = RequestMethod.POST)
	public @ResponseBody List<CategorywiseItemSell> getCatwiseItemSell(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") int frId, @RequestParam("catId") int catId,
			@RequestParam("flag") int flag) {

		List<CategorywiseItemSell> crnReport = new ArrayList<CategorywiseItemSell>();

		try {
			if (flag == 1) {
				crnReport = categorywiseItemSellRepo.getCategorywiseItemSellDesc(fromDate, toDate, frId, catId);
			} else if (flag == 2) {
				crnReport = categorywiseItemSellRepo.getCategorywiseItemSellAsc(fromDate, toDate, frId, catId);
			} else {
				crnReport = categorywiseItemSellRepo.getCategorywiseItemSellAll(fromDate, toDate, frId, catId);
			}

		} catch (Exception e) {

			System.err.println("Exception in DashBoardReporApi /getCredNoteReport" + e.getMessage());

			e.printStackTrace();
		}

		return crnReport;
	}

}
