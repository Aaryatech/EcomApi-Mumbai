package com.ats.ecomapi.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.cms.repo.OrderDetailForConfirmationRepository;
import com.ats.ecomapi.cms.repo.OrderHeaderWithDetailRepository;
import com.ats.ecomapi.fe_model.GetDeliveryBoyOrAgentData;
import com.ats.ecomapi.fe_model.SellBillDataForPrint;
import com.ats.ecomapi.fe_model.SellBillDetail;
import com.ats.ecomapi.fe_model.SellBillDetailForPos;
import com.ats.ecomapi.fe_model.SellBillHeader;
import com.ats.ecomapi.fe_model.TaxLabListForPos;
import com.ats.ecomapi.fe_model.TransactionDetail;
import com.ats.ecomapi.fe_repo.SellBillDataForPrintRepo;
import com.ats.ecomapi.fe_repo.SellBillDetailForPosRepository;
import com.ats.ecomapi.fe_repo.SellBillDetailRepository;
import com.ats.ecomapi.fe_repo.SellBillHeaderRepository;
import com.ats.ecomapi.fe_repo.TaxLabListForPosPosRepository;
import com.ats.ecomapi.fe_repo.TransactionDetailRepository;
import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.master.model.GetOrderDetailDisplay;
import com.ats.ecomapi.master.model.GetOrderHeaderDisplay;
import com.ats.ecomapi.master.model.GetOrderTrailDisplay;
import com.ats.ecomapi.master.model.OrderDetailForConfirmation;
import com.ats.ecomapi.master.model.OrderHeaderWithDetail;
import com.ats.ecomapi.master.model.Setting;
import com.ats.ecomapi.master.model.Status;
import com.ats.ecomapi.master.repo.FranchiseRepo;
import com.ats.ecomapi.master.repo.GetOrderDisplayRepo;
import com.ats.ecomapi.master.repo.GetOrderHeaderRepo;
import com.ats.ecomapi.master.repo.GetOrderTrailDisplayRepo;
import com.ats.ecomapi.master.repo.OrderDetailListRepo;
import com.ats.ecomapi.master.repo.OrderTrailRepository;
import com.ats.ecomapi.master.repo.SettingRepo;
import com.ats.ecomapi.master.repo.SettingRepository;
import com.ats.ecomapi.master.repo.StatusRepo;
import com.ats.ecomapi.mst_model.Customer;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.OrderDetail;
import com.ats.ecomapi.mst_model.OrderHeader;
import com.ats.ecomapi.mst_model.OrderSaveData;
import com.ats.ecomapi.mst_model.OrderTrail;
import com.ats.ecomapi.mst_repo.CustomerRepo;
import com.ats.ecomapi.mst_repo.GetDeliveryBoyOrAgentDataRepo;
import com.ats.ecomapi.mst_repo.OrderDetailRepository;
import com.ats.ecomapi.mst_repo.OrderHeaderRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.el.parser.ParseException;

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

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	GetDeliveryBoyOrAgentDataRepo getDeliveryBoyOrAgentDataRepo;

	@Autowired
	FranchiseRepo frRepo;

	@Autowired
	SellBillHeaderRepository sellBillHeaderRepository;

	@Autowired
	SellBillDetailRepository sellBillDetailRepository;

	@Autowired
	TransactionDetailRepository transactionDetailRepository;

	@Autowired
	OrderHeaderRepo orderHeaderRepository;

	@Autowired
	SellBillDataForPrintRepo sellBillDataForPrintRepo;

	@Autowired
	SellBillDetailForPosRepository sellBillDetailForPosRepository;

	@Autowired
	TaxLabListForPosPosRepository taxLabListForPosPosRepository;

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
	// Description - Save Order Header, Order Detail and Order Trail
	@Transactional
	@RequestMapping(value = { "/saveCloudOrder" }, method = RequestMethod.POST)
	public @ResponseBody Info saveCloudOrder(@RequestBody OrderSaveData orderSaveData) {

		Info info = new Info();

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

		return info;

	}

	@RequestMapping(value = { "/getOrderHistoryListByCustId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetOrderHeaderDisplay> getOrderHistoryListByCustId(@RequestParam("compId") int compId,
			@RequestParam("custId") int custId) {

		List<GetOrderHeaderDisplay> orderList = new ArrayList<>();

		try {

			orderList = orderHeaderRepo.getOrderHIstoryList(compId, custId);

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

	// ACCEPT AND PROCESS ORDER-------------------
	@RequestMapping(value = { "/acceptAndProcessOrderOPS" }, method = RequestMethod.POST)
	public @ResponseBody Info acceptAndProcessOrderOPS(@RequestParam("orderId") int orderId,
			@RequestParam("status") int status, @RequestParam("userId") int userId,
			@RequestParam("remark") String remark, @RequestParam("type") int type) {

		Info info = new Info();

		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt = new Date();

			System.err.println("Status = " + status + " ORder id = " + orderId);
			int update = orderHeadRepo.updateStatus(status, orderId);
			System.err.println("update res = " + update);
			if (update > 0) {

				OrderTrail orderTrail1 = new OrderTrail();
				orderTrail1.setOrderId(orderId);
				orderTrail1.setActionByUserId(userId);
				orderTrail1.setActionDateTime(sf.format(dt));
				orderTrail1.setStatus(2);
				orderTrail1.setExVar1(remark);
				orderTrail1.setExInt1(type);
				OrderTrail orderRes1 = orderTrailRepository.save(orderTrail1);

				OrderTrail orderTrail2 = new OrderTrail();
				orderTrail2.setOrderId(orderId);
				orderTrail2.setActionByUserId(userId);
				orderTrail2.setActionDateTime(sf.format(dt));
				orderTrail2.setStatus(status);
				orderTrail2.setExVar1(remark);
				orderTrail2.setExInt1(type);
				OrderTrail orderRes2 = orderTrailRepository.save(orderTrail2);

				info.setError(false);
				info.setMessage("updated");

				try {

					// Setting val = settingRepo.findBySettingKey("msg_process_order");

					// Customer cust = customerRepo.getCustomerByOrderId(orderId);

					// SMSUtility.sendSMS(cust.getPhoneNumber(), val.getSettingValue1(),"MDVDRY");

				} catch (Exception e) {
				}

			} else {
				info.setError(true);
			}

		} catch (Exception e) {
			info.setError(true);
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/changeStatusByOrderId" }, method = RequestMethod.POST)
	public @ResponseBody Info changeStatusByOrderId(@RequestParam("status") int status,
			@RequestParam("userId") int userId, @RequestParam("orderId") int orderId,
			@RequestParam("remark") String remark, @RequestParam("type") int type) {

		Info info = new Info();

		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt = new Date();

			int update = orderHeadRepo.updateStatus(status, orderId);

			String UUID = orderHeadRepo.getUuId(orderId);

			OrderTrail orderTrail = new OrderTrail();
			orderTrail.setOrderId(orderId);
			orderTrail.setActionByUserId(userId);
			orderTrail.setActionDateTime(sf.format(dt));
			orderTrail.setStatus(status);
			orderTrail.setExVar1(remark);
			orderTrail.setExInt1(type);
			OrderTrail orderRes = orderTrailRepository.save(orderTrail);

			info.setError(false);
			info.setMessage(UUID);

			System.err.println("UPDATE RES ======> " + update);
			if (update > 0) {

				try {

					if (status == 7 && type == 4) {

						SimpleDateFormat dttime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
						Date ct = new Date();

//							OrderGrievance orderGrievance = new OrderGrievance();
//							orderGrievance.setOrderId(orderId);
//							orderGrievance.setInsertById(userId);
//							orderGrievance.setInsertDateTime(dttime.format(ct));
//							orderGrievance.setRemark(remark);
//							orderGrievance.setPlatform(1);
//							orderGrievance.setGrievenceSubtypeId(0);
//							orderGrievance.setCurrentStatus(0);
//							orderGrievance.setDate(yy.format(ct));
//							orderGrievance.setGrievencceNo("1");

//
//							OrderGrievanceTrail orderGrievanceTrail = new OrderGrievanceTrail();
//							orderGrievanceTrail.setActionByUserId(userId);
//							orderGrievanceTrail.setActionDateTime(dttime.format(ct));
//							orderGrievanceTrail.setStatus(0);
//							orderGrievanceTrail.setRemark(remark);
//							orderGrievance.setOrderGrievanceTrail(orderGrievanceTrail);
//
//							saveFeedBackOfOrder(orderGrievance);

					}

				} catch (Exception e) {
				}

				try {
					OrderHeader order = orderHeadRepo.findByOrderId(orderId);
					Customer cust = customerRepo.getCustomerByOrderId(orderId);

					String msg = "";

					Setting val = new Setting();

					System.err.println("STATUS ---------------------======> " + status);

					if (status == 2) {
						val = settingRepo.findBySettingKey("msg_accept_order");

						msg = val.getSettingValue();
						msg = msg.replace("$$$", order.getOrderNo());

					} else if (status == 3) {
						val = settingRepo.findBySettingKey("msg_process_order");
					} else if (status == 4) {

						try {
							val = settingRepo.findBySettingKey("msg_delivery_order");

							msg = val.getSettingValue();
							msg = msg.replace("CUSTNAME", cust.getCustName());
							msg = msg.replace("###", order.getOrderNo());

							String agentNm = "", agentMob = "";

//									if(order.getOrderDeliveredBy()!=0) {
//										agentNm = getDeliveryBoyOrAgentDataRepo.getDeliveryBoyNameById(order.getOrderDeliveredBy());
//										agentMob=agentRepo.getDeliveryBoyMobById(order.getOrderDeliveredBy());
//										msg = msg.replace("AGNAME", agentNm.toUpperCase());
//										msg = msg.replace("AGMOB", agentMob);
//									}

							System.err.println("DELIVERY SMS - " + msg);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else if (status == 5) {
						val = settingRepo.findBySettingKey("msg_delivered_order");

						msg = val.getSettingValue();
						msg = msg.replace("###", order.getOrderNo());

					} else if (status == 8) {
						val = settingRepo.findBySettingKey("msg_cancelled_order");

						OrderTrail trail = orderTrailRepository.findByOrderIdAndStatus(order.getOrderId(), 8);

						msg = val.getSettingValue();
						msg = msg.replace("###", trail.getExVar1());

						System.err.println("CANCEL - " + msg);

					}

					// SMSUtility.sendSMS(cust.getPhoneNumber(), msg, "MDVDRY");

					if (status == 4) {

						try {

							Setting val1 = new Setting();
							val1 = settingRepo.findBySettingKey("msg_delivery_order_agent");
							String sms = val1.getSettingValue();

							sms = sms.replace("###", cust.getCustName());
							sms = sms.replace("@@@", cust.getCustMobileNo());

							String pmode = "";
							if (order.getPaymentMethod() == 1) {
								pmode = "COD";
							} else if (order.getPaymentMethod() == 2) {
								pmode = "Online Payment";
							}

							sms = sms.replace("PMODE", pmode);
							sms = sms.replace("OAMT", "" + order.getTotalAmt());

							Franchise fr = frRepo.findByFrId(order.getFrId());
							sms = sms.replace("FRCODE", "" + fr.getFrCode());

//								String agentMob = "";
//								if (order.getIsAgent() == 1) {
//
//									Agent agent = agentRepo.findByAgentIdAndCompanyIdAndDelStatus(order.getOrderDeliveredBy(),
//											1, 0);
//									agentMob = agent.getMobileNo();
//
//								} else {
//									agentMob = agentRepo.getDeliveryBoyMobById(order.getOrderDeliveredBy());
//								}

							System.err.println("AGENT DELIVERY SMS - " + sms);

							// SMSUtility.sendSMS(agentMob, sms, "MDVDRY");

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			info.setError(true);
			e.printStackTrace();
		}
		return info;
	}

	// GET DELIVERY BOY AND AGENT LIST
	@RequestMapping(value = { "/getDelBoyAndAgentListByFrAndCity" }, method = RequestMethod.POST)
	public @ResponseBody List<GetDeliveryBoyOrAgentData> getDelBoyAndAgentListByFrAndCity(
			@RequestParam("frId") int frId) {

		List<GetDeliveryBoyOrAgentData> res = new ArrayList<>();
		try {
			res = getDeliveryBoyOrAgentDataRepo.getDeliveryBoyListByFr(frId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@RequestMapping(value = { "/insertSellBillData" }, method = RequestMethod.POST)
	public @ResponseBody SellBillHeader sellBillData(@RequestBody SellBillHeader sellBillHeader) {
		SellBillHeader sellBillHeaderRes = new SellBillHeader();
		try {

			sellBillHeaderRes = sellBillHeaderRepository.save(sellBillHeader);
			if (sellBillHeaderRes != null) {
				int res = sellBillHeaderRepository.updateRoundOff(sellBillHeaderRes.getSellBillNo());
			}

			int sellBillNo = sellBillHeaderRes.getSellBillNo();
			System.err.println("Details-----" + sellBillHeader.getSellBillDetailsList());
			List<SellBillDetail> sellBillDetailList = sellBillHeader.getSellBillDetailsList();
			List<SellBillDetail> sellBillDetailRes = new ArrayList<SellBillDetail>();
			if (sellBillDetailList != null) {

				for (int j = 0; j < sellBillDetailList.size(); j++) {

					SellBillDetail sellBillDetail = sellBillDetailList.get(j);

					sellBillDetail.setSellBillNo(sellBillNo);

					sellBillDetail = sellBillDetailRepository.save(sellBillDetail);
					sellBillDetailRes.add(sellBillDetail);
				}
			}
			sellBillHeaderRes.setSellBillDetailsList(sellBillDetailRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sellBillHeaderRes;

	}

	@RequestMapping(value = { "/saveTransactionDetail" }, method = RequestMethod.POST)
	public @ResponseBody List<TransactionDetail> saveTransactionDetail(
			@RequestBody List<TransactionDetail> transactionDetail) {

		List<TransactionDetail> transactionDetailRes = transactionDetailRepository.saveAll(transactionDetail);

		return transactionDetailRes;
	}

	// -----------UPDATE DELIVERY BOY----------------
	@RequestMapping(value = { "/updateDeliveryBoy" }, method = RequestMethod.POST)
	public @ResponseBody Info updateDeliveryBoy(@RequestParam("orderId") int orderId,
			@RequestParam("delBoyId") int delBoyId) {

		Info info = new Info();
		try {

			int res = orderHeaderRepository.updateDeliveryBoy(orderId, delBoyId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Success");
			} else {
				info.setError(true);
				info.setMessage("Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

	@RequestMapping(value = { "/getSellBillForPrintByOrderId" }, method = RequestMethod.POST)
	public @ResponseBody SellBillDataForPrint getSellBillForPrintByOrderId(@RequestParam("orderId") int orderId)
			throws ParseException {
		SellBillDataForPrint res = null;
		System.err.println("sellBillNo-------- is ---------" + orderId);
		try {
			res = sellBillDataForPrintRepo.getBillHeaderByOrderId(orderId);
			if (res == null) {
				res = new SellBillDataForPrint();
			}

			List<SellBillDetailForPos> list = sellBillDetailForPosRepository
					.getSellBillDetailForPos(res.getSellBillNo());
			System.err.println("DETAIL = " + list);
			res.setList(list);
			// System.out.println(flag);
			List<TaxLabListForPos> taxLabListForPosList = taxLabListForPosPosRepository
					.taxLabListForPosList(res.getSellBillNo());
			res.setTaxlabList(taxLabListForPosList);

			System.err.println("data is" + res.toString());

		} catch (Exception e) {
			System.out.println("Exc in getSellBillForPrintByOrderId" + e.getMessage());
			// e.printStackTrace();
		}

		return res;

	}

	@RequestMapping(value = { "/getTransactionByBillId" }, method = RequestMethod.POST)
	public @ResponseBody TransactionDetail getTransactionByBillId(@RequestParam("sellBillNo") int sellBillNo) {

		TransactionDetail transactionDetailRes = transactionDetailRepository.getTransactionByBillId(sellBillNo);

		return transactionDetailRes;
	}

	// Sachin 14-12-2020
	// Update order on digital Payment Success/Fail
	@RequestMapping(value = { "/updateOrderFrontEnd" }, method = RequestMethod.POST)
	public @ResponseBody Info updateOrderFrontEnd(@RequestParam("orderId") int orderId,
			@RequestParam("uniqNo") String uniqNo, @RequestParam("paidStatus") int paidStatus,
			@RequestParam("payRemark") String payRemark, @RequestParam("orderStatus") int orderStatus) {

		Info info = new Info();
		int update = 0;
		try {
			if (orderStatus > 0) {
				update = orderHeadRepo.updateOrderFrontEndFailedPay(uniqNo, paidStatus, payRemark, orderStatus,
						orderId);
			} else {
				update = orderHeadRepo.updateOrderFrontEndSuccessPay(uniqNo, paidStatus, payRemark, orderId);
			}
			if (update > 0) {
				info.setError(false);
				info.setMsg("epay");
			}

		} catch (Exception e) {
			info = new Info();
		}
		return info;
	}

	@RequestMapping(value = { "/getJsonPath" }, method = RequestMethod.GET)
	public @ResponseBody String getJsonPath() {
		String val = new String();
		Setting setting = new Setting();
		setting = settingRepo.findBySettingKey("JSON_SAVE_PATH");
		return val;
	}

	@Autowired
	OrderHeaderWithDetailRepository orderHeaderWithDetailRepository;

	@Autowired
	OrderDetailForConfirmationRepository orderDetailForConfirmationRepository;

	@RequestMapping(value = { "/getOrderHeaderAndDetailForConfirmationPage" }, method = RequestMethod.POST)
	public @ResponseBody OrderHeaderWithDetail getOrderHeaderAndDetailForConfirmationPage(
			@RequestParam("orderId") int orderId) {

		OrderHeaderWithDetail obj = new OrderHeaderWithDetail();

		try {
			obj = orderHeaderWithDetailRepository.getHeader(orderId);
			List<OrderDetailForConfirmation> detailList = orderDetailForConfirmationRepository.getDetail(orderId);
			obj.setDetailList(detailList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

}
