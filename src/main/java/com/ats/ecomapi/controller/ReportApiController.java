package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.report.model.GetCustomerWisReport;
import com.ats.ecomapi.report.model.GetSellBillHeader;
import com.ats.ecomapi.report.repo.GetCustomerWisReportRepo;
import com.ats.ecomapi.report.repo.GetSellBillHeaderRepo;

@RestController
public class ReportApiController {

	
	@Autowired GetCustomerWisReportRepo custPrchsRepo;
	
	@RequestMapping(value = { "/getCustPurchaseRepByFrId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetCustomerWisReport> getCustPurchaseRepByFrId(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("frId") int frId) {

		List<GetCustomerWisReport> repList = new ArrayList<GetCustomerWisReport>();

		try {
			repList = custPrchsRepo.getCustWiseReport(frId, fromDate, toDate);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repList;
	}
	
	/*------------------------------------------------------------------------------*/
	@Autowired GetSellBillHeaderRepo custPrchsDtlRepo;
	
	@RequestMapping(value = { "/getCustPurchaseDetailReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetSellBillHeader> getCustPurchaseDetailReport(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("frId") List<Integer> frId, 
			@RequestParam("compId") int compId, @RequestParam("custId") int custId ) {

		List<GetSellBillHeader> repList = new ArrayList<GetSellBillHeader>();

		try {
			repList = custPrchsDtlRepo.getCustPrchsDetailReport(compId, frId, fromDate, toDate,custId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repList;
	}
}
