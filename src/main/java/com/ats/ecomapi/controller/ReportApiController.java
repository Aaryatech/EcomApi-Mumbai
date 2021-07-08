package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.offer.repo.GetItemSubCatWiseRepo;
import com.ats.ecomapi.offer.repo.ShowPieChartDataRepo;
import com.ats.ecomapi.report.model.GetCustomerWisReport;
import com.ats.ecomapi.report.model.GetDateWiseBillReport;
import com.ats.ecomapi.report.model.GetItemSubCatWise;
import com.ats.ecomapi.report.model.GetSellBillHeader;
import com.ats.ecomapi.report.model.HeadOfficeReport;
import com.ats.ecomapi.report.model.ShowPieChartData;
import com.ats.ecomapi.report.repo.GetCustomerWisReportRepo;
import com.ats.ecomapi.report.repo.GetDateWiseBillReportRepo;
import com.ats.ecomapi.report.repo.GetSellBillHeaderRepo;
import com.ats.ecomapi.report.repo.HeadOfficeReportRepo;

@RestController
public class ReportApiController {

	
	@Autowired GetCustomerWisReportRepo custPrchsRepo;
	
	@RequestMapping(value = { "/getCustPurchaseRepByFrId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetCustomerWisReport> getCustPurchaseRepByFrId(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("frId") List<Integer> frId) {

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
	
	/*------------------------------------------------------------------------------*/
	@Autowired GetDateWiseBillReportRepo dateBillRepo;
	
	@RequestMapping(value = { "/getDateWiseBillsReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetDateWiseBillReport> getDateWiseBillsReport(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("frId") List<Integer> frId) {

		List<GetDateWiseBillReport> repList = new ArrayList<GetDateWiseBillReport>();

		try {
			repList = dateBillRepo.getDateWiseBills(frId, fromDate, toDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repList;
	}
	
	@RequestMapping(value = { "/getOrderDateWiseCustReport" }, method = RequestMethod.POST)
	public @ResponseBody List<GetSellBillHeader> getOrderDateWiseCustReport(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("frId") List<Integer> frId, 
			@RequestParam("compIds") int compIds) {

		List<GetSellBillHeader> repList = new ArrayList<GetSellBillHeader>();

		try {
			repList = custPrchsDtlRepo.getCustOrderDetailReport(compIds, frId, fromDate, toDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repList;
	}
	
	//-------------------------------------------------------------------------------------------------//
	@Autowired HeadOfficeReportRepo headOfficeRepo;
	
	@RequestMapping(value = { "/getHoReport" }, method = RequestMethod.POST)
	public @ResponseBody List<HeadOfficeReport> getheadOfficeReport(@RequestParam("dateType") int dateType,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compIds") List<String> compIds, @RequestParam("paymentMethod") int paymentMethod) {

		List<HeadOfficeReport> repList = new ArrayList<HeadOfficeReport>();

		try {
			if(dateType==1){
				repList = headOfficeRepo.getHeadOfficeReport(fromDate, toDate, orderStatus, compIds, paymentMethod);
			}else {
				repList = headOfficeRepo.getHeadOfficeReportByProdctnDate(fromDate, toDate, orderStatus, compIds, paymentMethod);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(repList);
		return repList;
	}
	
	@Autowired ShowPieChartDataRepo pieChartRepo;	
	
	@RequestMapping(value = { "/getCatQtyChartData" }, method = RequestMethod.POST)
	public @ResponseBody List<ShowPieChartData> getCatQtyChartData(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compIds") List<String> compIds, @RequestParam("paymentMethod") int paymentMethod) {

		List<ShowPieChartData> repList = new ArrayList<ShowPieChartData>();

		try {
			repList = pieChartRepo.getCatQtyChart(fromDate, toDate, orderStatus, compIds, paymentMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(repList);
		return repList;
	}
	
	@RequestMapping(value = { "/getSubCatQtyChartData" }, method = RequestMethod.POST)
	public @ResponseBody List<ShowPieChartData> getSubCatQtyChartData(@RequestParam("catId") int catId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compIds") List<String> compIds, @RequestParam("paymentMethod") int paymentMethod) {

		List<ShowPieChartData> repList = new ArrayList<ShowPieChartData>();

		try {
			repList = pieChartRepo.getSubCatQtyChart(catId, fromDate, toDate, orderStatus, compIds, paymentMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(repList);
		return repList;
	}
	
	@Autowired GetItemSubCatWiseRepo getSubCatItemRepo;
	@RequestMapping(value = { "/getSubCatWiseItemChartData" }, method = RequestMethod.POST)
	public @ResponseBody List<GetItemSubCatWise> getSubCatWiseItemChartData(@RequestParam("subCatId") int subCatId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compIds") List<String> compIds, @RequestParam("paymentMethod") int paymentMethod) {

		List<GetItemSubCatWise> repList = new ArrayList<GetItemSubCatWise>();

		try {
			repList = getSubCatItemRepo.getSubCatItems(subCatId, fromDate, toDate, orderStatus, compIds, paymentMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return repList;
	}
	
	
	@RequestMapping(value = { "/getTotalSaleQtyChartData" }, method = RequestMethod.POST)
	public @ResponseBody List<ShowPieChartData> getTotalSaleQtyChartData(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compIds") List<String> compIds, @RequestParam("paymentMethod") int paymentMethod) {

		List<ShowPieChartData> repList = new ArrayList<ShowPieChartData>();

		try {
			repList = pieChartRepo.getCatTotalSaleChart(fromDate, toDate, orderStatus, compIds, paymentMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(repList);
		return repList;
	}
	
	@RequestMapping(value = { "/getSubCatTotalSaleChartData" }, method = RequestMethod.POST)
	public @ResponseBody List<ShowPieChartData> getSubCatTotalSaleChartData(@RequestParam("catId") int catId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compIds") List<String> compIds, @RequestParam("paymentMethod") int paymentMethod) {

		List<ShowPieChartData> repList = new ArrayList<ShowPieChartData>();

		try {
			repList = pieChartRepo.getTotalSaleByCatIdChart(catId, fromDate, toDate, orderStatus, compIds, paymentMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(repList);
		return repList;
	}
	
	@RequestMapping(value = { "/getItemSalesChartData" }, method = RequestMethod.POST)
	public @ResponseBody List<GetItemSubCatWise> getItemSalesChartData(@RequestParam("subCatId") int subCatId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compIds") List<String> compIds, @RequestParam("paymentMethod") int paymentMethod) {

		List<GetItemSubCatWise> repList = new ArrayList<GetItemSubCatWise>();

		try {
			repList = getSubCatItemRepo.getSubCatTotalSaleItems(subCatId, fromDate, toDate, orderStatus, compIds, paymentMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return repList;
	}
	
	/*---------------------------------------------------------------------------------*/
	@RequestMapping(value = { "/getFrUnitMenufctrReport" }, method = RequestMethod.POST)
	public @ResponseBody List<HeadOfficeReport> getFrUnitMenufctrReport(@RequestParam("dateType") int dateType,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compId") int compId, @RequestParam("paymentMethod") int paymentMethod) {

		List<HeadOfficeReport> repList = new ArrayList<HeadOfficeReport>();

		try {
			if(dateType==1){
				repList = headOfficeRepo.getFrUnitReportByDelvrDate(fromDate, toDate, orderStatus, compId, paymentMethod);
			}else {
				repList = headOfficeRepo.getFrUnitReportByPrdctnDate(fromDate, toDate, orderStatus, compId, paymentMethod);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(repList);
		return repList;
	}
	
	
	
	//SAC -SHUBHAM 08-07-2021
	
	@RequestMapping(value = { "/getFrItemReport" }, method = RequestMethod.POST)
	public @ResponseBody List<HeadOfficeReport> getFrUnitMenufctrReport(@RequestParam("dateType") int dateType,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("orderStatus") List<String> orderStatus, 
			@RequestParam("compId") int compId, @RequestParam("paymentMethod") int paymentMethod,
			@RequestParam("frId") int frId) {

		List<HeadOfficeReport> repList = new ArrayList<HeadOfficeReport>();

		try {
			if(dateType==1){
				repList = headOfficeRepo.getFrUnitReportByDelvrDateAndFrId(fromDate, toDate, orderStatus, compId, paymentMethod,frId);
			}else {
				repList = headOfficeRepo.getFrUnitReportByPrdctnDateAndFrId(fromDate, toDate, orderStatus, compId, paymentMethod,frId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(repList);
		return repList;
	}
	
}
