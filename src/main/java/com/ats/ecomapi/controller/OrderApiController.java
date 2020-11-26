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

import com.ats.ecomapi.master.model.GetOrderDetailDisplay;
import com.ats.ecomapi.master.model.GetOrderHeaderDisplay;
import com.ats.ecomapi.master.model.GetOrderTrailDisplay;
import com.ats.ecomapi.master.model.Setting;
import com.ats.ecomapi.master.model.Status;
import com.ats.ecomapi.master.repo.GetOrderDisplayRepo;
import com.ats.ecomapi.master.repo.GetOrderHeaderRepo;
import com.ats.ecomapi.master.repo.GetOrderTrailDisplayRepo;
import com.ats.ecomapi.master.repo.OrderDetailListRepo;
import com.ats.ecomapi.master.repo.OrderTrailRepository;
import com.ats.ecomapi.master.repo.SettingRepo;
import com.ats.ecomapi.master.repo.SettingRepository;
import com.ats.ecomapi.master.repo.StatusRepo;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.OrderDetail;
import com.ats.ecomapi.mst_model.OrderHeader;
import com.ats.ecomapi.mst_model.OrderHeaderRepo;
import com.ats.ecomapi.mst_model.OrderSaveData;
import com.ats.ecomapi.mst_model.OrderTrail;
import com.ats.ecomapi.mst_repo.OrderDetailRepository;

@RestController
public class OrderApiController {

	@Autowired
	StatusRepo statusRepo;

	@Autowired
	GetOrderDisplayRepo getOrderDisplayRepo;

	@Autowired
	GetOrderTrailDisplayRepo getOrderTrailDisplayRepo;

	@Autowired
	GetOrderHeaderRepo orderHeaderRepo;

	@Autowired
	OrderDetailListRepo orderDtlRepo;

	@Autowired
	OrderHeaderRepo orderHeadRepo;

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	SettingRepo settingRepo;
	
	@Autowired
	SettingRepository settingRepository;
	
	@Autowired
	OrderTrailRepository orderTrailRepository;

	@RequestMapping(value = { "/getAllStatus" }, method = RequestMethod.GET)
	public @ResponseBody List<Status> getAllStatus() {

		List<Status> res = new ArrayList<Status>();
		res = statusRepo.findByDelStatusOrderByStatusIdAsc(0);
		return res;
	}

	@RequestMapping(value = { "/getOrderListByDates" }, method = RequestMethod.POST)
	public @ResponseBody List<GetOrderHeaderDisplay> getOrderReportBetweenDateAndStatus(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("status") List<Integer> status, @RequestParam("compId") int compId,
			@RequestParam("dateType") int dateType) {

		List<GetOrderHeaderDisplay> orderList = new ArrayList<>();

		try {

			if (dateType == 1) {
				orderList = orderHeaderRepo.getOrderHeaderByDeliveryDate(fromDate, toDate, status, compId);
			} else {
				orderList = orderHeaderRepo.getOrderHeaderByProdctnDate(fromDate, toDate, status, compId);
			}

			List<GetOrderDetailDisplay> detailList = orderDtlRepo.getOrderDetailsyBillNo(compId);
			List<GetOrderTrailDisplay> trailList = getOrderTrailDisplayRepo.getOrderTrailListByCompId(compId);

			for (int i = 0; i < orderList.size(); i++) {
				List<GetOrderDetailDisplay> detailHeadList = new ArrayList<GetOrderDetailDisplay>();

				for (int j = 0; j < detailList.size(); j++) {
					if (orderList.get(i).getOrderId() == detailList.get(j).getOrderId()) {
						detailHeadList.add(detailList.get(j));
					}
				}

				orderList.get(i).setOrderDetailList(detailHeadList);

				List<GetOrderTrailDisplay> trailHeadList = new ArrayList<GetOrderTrailDisplay>();

				for (int k = 0; k < trailList.size(); k++) {

					if (orderList.get(i).getOrderId() == trailList.get(k).getOrderId()) {
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

	@RequestMapping(value = { "/getOrderListByDatesAndCustId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetOrderHeaderDisplay> getOrderListByDatesAndCustId(
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			@RequestParam("compId") int compId, @RequestParam("custId") int custId) {

		List<GetOrderHeaderDisplay> orderList = new ArrayList<>();

		try {

			orderList = orderHeaderRepo.getOrderHeaderByDeliveryDateCustId(fromDate, toDate, compId, custId);

			List<GetOrderDetailDisplay> detailList = orderDtlRepo.getOrderDetailsyBillNo(compId);
			List<GetOrderTrailDisplay> trailList = getOrderTrailDisplayRepo.getOrderTrailListByCompId(compId);

			for (int i = 0; i < orderList.size(); i++) {
				List<GetOrderDetailDisplay> detailHeadList = new ArrayList<GetOrderDetailDisplay>();

				for (int j = 0; j < detailList.size(); j++) {
					if (orderList.get(i).getOrderId() == detailList.get(j).getOrderId()) {
						detailHeadList.add(detailList.get(j));
					}
				}

				orderList.get(i).setOrderDetailList(detailHeadList);

				List<GetOrderTrailDisplay> trailHeadList = new ArrayList<GetOrderTrailDisplay>();

				for (int k = 0; k < trailList.size(); k++) {

					if (orderList.get(i).getOrderId() == trailList.get(k).getOrderId()) {
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

	@RequestMapping(value = { "/saveOrderHeader" }, method = RequestMethod.POST)
	public @ResponseBody OrderHeader saveOrderHeader(@RequestBody OrderHeader orderHeader) {
		OrderHeader header = new OrderHeader();
		try {
			header = orderHeadRepo.save(orderHeader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return header;
	}

	@RequestMapping(value = { "/saveOrderDetail" }, method = RequestMethod.POST)
	public @ResponseBody List<OrderDetail> saveOrderDetail(@RequestBody List<OrderDetail> detailList) {

		List<OrderDetail> res = new ArrayList<>();

		try {

			res = orderDetailRepository.saveAll(detailList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
//	Mahendra - 25-11-2020 
	//Description - Save Order Header, Order Detail and Order Trail
	@RequestMapping(value = { "/saveCloudOrder" }, method = RequestMethod.POST)
	public @ResponseBody Info saveCloudOrder(@RequestBody OrderSaveData orderSaveData) {
		Info info = new Info();
		try {
			Setting setting = new Setting();
			setting = settingRepo.findBySettingKey("ORDER_NO");

			int no = Integer.parseInt(setting.getSettingValue());
			String orderNo = String.format("%0" + 5 + "d", no);

			orderSaveData.getOrderHeader().setOrderNo(orderNo);

			OrderHeader res = orderHeadRepo.save(orderSaveData.getOrderHeader());
			orderSaveData.getOrderTrail().setOrderId(res.getOrderId());

			no = no + 1;

			int updateOrderNo = settingRepo.udateKeyAndValue("ORDER_NO", no);

			OrderTrail orderRes = orderTrailRepository.save(orderSaveData.getOrderTrail());

			for (int i = 0; i < orderSaveData.getOrderDetailList().size(); i++) {
				orderSaveData.getOrderDetailList().get(i).setOrderId(res.getOrderId());
			}
			List<OrderDetail> orderDetailList = orderDetailRepository.saveAll(orderSaveData.getOrderDetailList());

			info.setError(false);
			info.setMessage(String.valueOf(res.getOrderId()));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

}
