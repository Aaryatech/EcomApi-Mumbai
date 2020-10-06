package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.master.model.GetOrderDetailDisplay;
import com.ats.ecomapi.master.model.GetOrderHeaderDisplay;
import com.ats.ecomapi.master.model.GetOrderTrailDisplay;
import com.ats.ecomapi.master.repo.GetDashStatusCntRepo;
import com.ats.ecomapi.master.repo.GetOrderHeaderRepo;
import com.ats.ecomapi.report.model.GetDashPieStatusCnt;

@RestController
public class DashApiController {
	@Autowired
	GetDashStatusCntRepo dashRepo;

	@Autowired
	GetOrderHeaderRepo orderHeaderRepo;

	@RequestMapping(value = { "/getAllStatusCount" }, method = RequestMethod.POST)
	@ResponseBody
	public List<GetDashPieStatusCnt> getGrievanceHeader(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("compId") int compId) {
		List<GetDashPieStatusCnt> res = new ArrayList<GetDashPieStatusCnt>();
		try {

			res = dashRepo.getDashStatusCnt(fromDate, toDate, compId);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = { "/getOrderHeaderListBy" }, method = RequestMethod.POST)
	public @ResponseBody List<GetOrderHeaderDisplay> getOrderReportBetweenDateAndStatus(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("status") List<Integer> status, @RequestParam("compId") int compId) {

		List<GetOrderHeaderDisplay> orderList = new ArrayList<>();

		try {
			orderList = orderHeaderRepo.getOrderHeaderByDeliveryDate(fromDate, toDate, status, compId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderList;

	}
}
