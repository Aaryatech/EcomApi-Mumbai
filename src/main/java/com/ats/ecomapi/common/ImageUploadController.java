package com.ats.ecomapi.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.ecomapi.cms.repo.OrderDetailForConfirmationRepository;
import com.ats.ecomapi.cms.repo.OrderHeaderWithDetailRepository;
import com.ats.ecomapi.master.model.OrderDetailForConfirmation;
import com.ats.ecomapi.master.model.OrderHeaderWithDetail;
import com.ats.ecomapi.mst_model.Info;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@RestController
public class ImageUploadController {
	
	
	@Autowired
	OrderHeaderWithDetailRepository orderHeaderWithDetailRepository;

	@Autowired
	OrderDetailForConfirmationRepository orderDetailForConfirmationRepository;

	public static float roundHalfUp(float d, int scale) {
		// return BigDecimal.valueOf(d).setScale(2,
		// BigDecimal.ROUND_HALF_UP).floatValue();
		return BigDecimal.valueOf(d).setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	@RequestMapping(value = { "/sendEmail" }, method = RequestMethod.POST)
	public @ResponseBody Info sendEmail(@RequestParam("recipientEmail") String recipientEmail,
			@RequestParam("mailsubject") String mailsubject,
	 		@RequestParam("emailerTitle") String title,
	 		@RequestParam("emailerText") String emailerText,
	 		@RequestParam("orderId") int orderId,
	 		@RequestParam("custEmail") String custEmail ) {
				
		
		OrderHeaderWithDetail orderSaveData = new OrderHeaderWithDetail();

		try {
			orderSaveData = orderHeaderWithDetailRepository.getHeader(orderId);
			List<OrderDetailForConfirmation> detailList = orderDetailForConfirmationRepository.getDetail(orderId);
			orderSaveData.setDetailList(detailList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String logoImgURL="https://pecom.monginis.net/ecom/resources/images/emailer_img/logo.png";
		String shipping_pic_URL="https://pecom.monginis.net/ecom/resources/images/emailer_img/shipping_pic.png";
		
		String fb_icon_URL="https://pecom.monginis.net/ecom/resources/images/emailer_img/fb_icon.jpg";
		String twt_icon_URL="https://pecom.monginis.net/ecom/resources/images/emailer_img/twt_icon.jpg";
		String yt_icon_URL="https://pecom.monginis.net/ecom/resources/images/emailer_img/yt_icon.jpg";
		String insta_icon_URL="https://pecom.monginis.net/ecom/resources/images/emailer_img/insta_icon.jpg";
		String prodImgViewURL="https://pecom.monginis.net/uploads/ecomAdmin/PROD_IMG_UP/";
		
		String billAdd=orderSaveData.getBillingAddress().replaceAll("~", "<br>\n");
		String delAdd=orderSaveData.getAddress().replaceAll("~", "<br>\n");

String productDiv=new  String();
for(int x=0;x<orderSaveData.getDetailList().size();x++) {
String qty=null;
float floatQty=0;
if(orderSaveData.getDetailList().get(x).getExInt1()==0) {
	qty="Qty : "+orderSaveData.getDetailList().get(x).getQty();
	floatQty=orderSaveData.getDetailList().get(x).getQty();
}else {
	qty="Weight : "+orderSaveData.getDetailList().get(x).getExFloat3();
	floatQty=orderSaveData.getDetailList().get(x).getExFloat3();
}
float tot=floatQty*orderSaveData.getDetailList().get(x).getMrp();
productDiv=productDiv+"                    <tr>\n" + 
		"                    	<td style=\"padding:15px; border-bottom:1px solid #d9d9d9;\">\n" + 
		"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
		"                              <tr>\n" + 
		"                                <td><img src="+prodImgViewURL+orderSaveData.getDetailList().get(x).getItemPic().trim()+" alt=\"\" width=\"64\" style=\"border:none; max-width:100%; float:left;\"></td>\n" + 
		"                                <td style=\"font-size:13px; line-height:20px; padding:0 0 0 15px;\"><strong style=\"color:#24247e;\">"+orderSaveData.getDetailList().get(x).getItemName()+"</strong> <br>  "+qty+" </td>\n" + 
		"                                <td style=\"text-align:right; font-size:13px; font-weight:bold; color:#ed258f; padding:0 0 0 15px;\">Rate : "+(roundHalfUp(tot,2))+"</td>\n" + 
		"                              </tr>\n" + 
		"                            </table>\n" + 
		"                        </td>\n" + 
		"                    </tr>\n" ;
}

		String msg1="﻿<!doctype html>\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
				"<head><style type=\"text/css\">\n" + 
				".btm tbody, .btm tr {\n" + 
				"	width: 100% !important;\n" + 
				"	float: left !important;\n" + 
				"}\n" + 
				".ReadMsgBody {\n" + 
				"	width: 100%;\n" + 
				"	background-color: #ffffff;\n" + 
				"}\n" + 
				".ExternalClass {\n" + 
				"	width: 100%;\n" + 
				"	background-color: #ffffff;\n" + 
				"}\n" + 
				"body {\n" + 
				"	width: 100%;\n" + 
				"	background-color: #ffffff;\n" + 
				"	margin: 0;\n" + 
				"	padding: 0;\n" + 
				"	-webkit-font-smoothing: antialiased;\n" + 
				"	font-family: Arial, sans-serif;\n" + 
				"}\n" + 
				"table {\n" + 
				"	border-collapse: collapse;\n" + 
				"}\n" + 
				"body[yahoo] #tborder {\n" + 
				"	/*border: #f2f2f2 thin solid*/\n" + 
				"}\n" + 
				".valid_till {\n" + 
				"	position: absolute;\n" + 
				"	font-size: 11px;\n" + 
				"	color: #fff;\n" + 
				"	font-weight: bold;\n" + 
				"	font-family: Arial, sans-serif;\n" + 
				"	right: -49px;\n" + 
				"	top: 170px;\n" + 
				"	-ms-transform: rotate(90deg);\n" + 
				"	-webkit-transform: rotate(90deg);\n" + 
				"	transform: rotate(90deg);\n" + 
				"}\n" + 
				"@media handheld, only screen and (max-width: 640px) {\n" + 
				"body[yahoo] .bottommargin {\n" + 
				"	margin-bottom: 10px;\n" + 
				"}\n" + 
				"body[yahoo] .deviceWidth {\n" + 
				"	width: 440px!important;\n" + 
				"	padding: 0;\n" + 
				"}\n" + 
				"body[yahoo] .center {\n" + 
				"	text-align: center!important;\n" + 
				"}\n" + 
				"}\n" + 
				"@media handheld, only screen and (max-width: 479px) {\n" + 
				"body[yahoo] .bottommargin {\n" + 
				"	margin-bottom: 10px;\n" + 
				"}\n" + 
				"body[yahoo] .deviceWidth {\n" + 
				"	width: 280px!important;\n" + 
				"	padding: 0;\n" + 
				"}\n" + 
				"body[yahoo] .center {\n" + 
				"	text-align: center!important;\n" + 
				"}\n" + 
				".bbutton {\n" + 
				"	background-color: #111940 !important;\n" + 
				"	padding-top: 10px;\n" + 
				"	padding-bottom: 10px;\n" + 
				"	padding-right: 30px;\n" + 
				"	text-align: center !important;\n" + 
				"}\n" + 
				"}\n" + 
				"@media handheld, only screen and (min-width:641px) {\n" + 
				"body[yahoo] #tborder {\n" + 
				"	/*border: #f2f2f2 thin solid*/\n" + 
				"}\n" + 
				"body[yahoo] .thumbres {\n" + 
				"	padding-left: 20%;\n" + 
				"	padding-right: 20%;\n" + 
				"}\n" + 
				"body[yahoo] .f-left {\n" + 
				"	display: inline-table !important;\n" + 
				"}\n" + 
				"body[yahoo] .f-right {\n" + 
				"	float: right;\n" + 
				"	display: inline-table !important;\n" + 
				"}\n" + 
				"body[yahoo] .f-center {\n" + 
				"	display: inline-table !important;\n" + 
				"}\n" + 
				"}\n" + 
				"</style>\n" + 
				"\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
				"<title>:: Monginis | Emailer ::</title>\n" + 
				"</head>\n" + 
				"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" yahoo=\"fix\" style=\"font-family:Arial, sans-serif; background:#e3ebef;\">\n" + 
				"<!-- Wrapper -->\n" + 
				"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
				"  <tr>\n" + 
				"    <td width=\"100%\" valign=\"top\" bgcolor=\"#f8208d\">\n" + 
				"    	<!-- Start Header-->\n" + 
				"      <table width=\"700\" id=\"tborder\" class=\"deviceWidth\" bgcolor=\"#FFF\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\"  style=\"position:relative;\">\n" + 
				"        <tr>\n" + 
				"          <td cellspacing=\"0\" cellpadding=\"0\"  style=\" padding:0;\"><table width=\"100%\" id=\"\" class=\"\" cellspacing=\"0\" cellpadding=\"0\"  align=\"center\" background=\"#000\" border=\"0\" style=\"padding:0;\">\n" + 
				"          	  \n" + 
				"              <tr>\n" + 
				"                <td align=\"center\">&nbsp;</td>\n" + 
				"              </tr>\n" + 
				"              <tr>\n" + 
				"                <td align=\"center\">\n" + 
				"                    <img src="+logoImgURL+" alt=\"main_logo\" style=\"border:none; max-width:100%; padding: 0; float:none;\">\n" + 
				"                  </td>\n" + 
				"              </tr>\n" + 
				"              <tr>\n" + 
				"                <td cellspacing=\"0\" cellpadding=\"0\" style=\"position:relative; padding:30px 15px 10px 15px;\" border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" + 
				"                    <tr>\n" + 
				"                      <td style=\"text-align:center; font-size:16px; letter-spacing:0.5px; text-transform:uppercase; padding:0 0 20px 0; color:#24247e;\">\n" + 
				"                      	<strong>"+title+"</strong></td>\n" + 
				"                    </tr>\n" + 
				"                    <tr>\n" + 
				"                      <td style=\"text-align:center; padding:0 0 25px 0; font-size:13px; line-height:28px; color:#3a3a3a;\">\n" + 
				"                      	Hi "+orderSaveData.getBillingName()+", <br>\n" + 
//				"                        We've got your order! Your world is about to look a whole lot better.<br>\n" + 
//				"						We'll drop you another email when your order ships.\n" + 
				""+emailerText+"                      </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                      <td style=\"text-align:center; padding:0 0 15px 0; font-size:15px; text-transform:uppercase; letter-spacing:0.5px; font-weight:bold; line-height:24px; color:#24247e;\">\n" + 
				"                      	Order No. Monginis-"+orderSaveData.getOrderNo()+" <br>\n" + 
				"						<span style=\"font-size:13px; color:#3a3a3a; font-weight:bold;\">"+orderSaveData.getDeliveryDate()+"</span>\n" + 
				"                      </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                  </table></td>\n" + 
				"              </tr>\n" + 
				"              \n" + 
				"              <tr>\n" + 
				"                <td cellspacing=\"0\" cellpadding=\"0\"  style=\"background: #FFF; position:relative; padding:0 15px;\" border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                    <tr>                          \n" + 
				"                      <td style=\"background:#FFF; padding:15px 0; color:#24247e; text-align:left; font-size:13px; font-weight:bold; text-transform:uppercase; border-bottom:1px solid #d9d9d9; letter-spacing:0.5px;\"><strong>Item Order</strong></td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				
				""+productDiv+ "\n" + 
				"                    <!--price box-->\n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:20px 0; font-size:13px; font-weight:bold; line-height:28px; color:#3a3a3a;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                              <tr>\n" + 
				"                                <td style=\"text-align:left; width:75%; max-width:100%; line-height:24px;\">\n" + 
				"                                    Sub Total <br>\n" + 
				"                                    Discount  <br>\n" + 
				"                                    Delivery Charges <br>\n" + 
				"                                    Total\n" + 
				"                                </td>\n" + 
				"                                <td style=\"line-height:24px; float:right; text-align:left; \">\n" + 
				"                                    Rs. "+((orderSaveData.getTotalAmt()+orderSaveData.getDiscAmt())-orderSaveData.getDeliveryCharges())+"<br>\n" +
				"                                    Rs. -"+orderSaveData.getDiscAmt()+"<br>\n" + 
				"                                    Rs. "+orderSaveData.getDeliveryCharges()+"<br>\n" + 
				"                                    Rs. "+orderSaveData.getTotalAmt()+"\n" + 
				"                                </td>\n" + 
				"                              </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <!--billing info box-->\n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:20px 0; font-size:13px; color:#3a3a3a; border-top:1px solid #d9d9d9; border-bottom:1px solid #d9d9d9;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                              <tr>\n" + 
				"                                <td style=\"float:left; width:50%; max-width:100%; text-align:left; line-height:24px;\">\n" + 
				"                                	<span style=\"display:block; padding:0 0 5px 0; font-weight:bold; text-transform:uppercase; color:#24247e;\">Billing Info</span>\n" + 
				"                                    "+orderSaveData.getBillingName()+" <br>\n" +billAdd.trim()+ 
				/*
				 * "                                    1st Floor, Raj Apartment, <br>\n" +
				 * "                                    Rachna Vidyalay Road, <br>\n" +
				 * "                                    Sharanpur Rd, Nashik. <br>\n" +
				 * "                                    India. <br>\n" +
				 */
			
				"                                  <br>\n <a href=\"mailto:"+custEmail+"\">"+custEmail+"</a>\n" + 
				"                                </td>\n" + 
				"                                <td style=\"float:right; max-width:100%; width:50%; line-height:24px; \">\n" + 
				"                                	<span style=\"display:block; padding:0 0 5px 0; font-weight:bold; text-transform:uppercase; color:#24247e;\">Shipping Address</span>\n" + 
				"                                    "+orderSaveData.getExVar4()+" <br>\n" +delAdd.trim()+ 
				/*
				 * "                                    1st Floor, Raj Apartment, <br>\n" +
				 * "                                    Rachna Vidyalay Road, <br>\n" +
				 * "                                    Sharanpur Rd, Nashik. <br>\n" +
				 * "                                    India.\n" +
				 */
				"                               <br>\n </td>\n" + 
				"                              </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"border-bottom:1px solid #d9d9d9; padding:25px 0 40px 0; font-size:13px; color:#3a3a3a; text-align:center;\">\n" + 
				"                        	<span style=\"display:block; padding:0 0 25px 0; font-size:13px; text-transform:uppercase; letter-spacing:0.3px; font-weight:bold; color:#24247e;\">Change your mind?</span>\n" + 
				"                            <span style=\"color:#ed258f; font-size:12px; text-transform:uppercase; font-weight:bold; padding:0; line-height:12px;\">Cancelling an order</span>\n" + 
				"                            <p style=\"color:#3a3a3a; padding:0 0 10px 0; display:block;\">we're not able to make changes to your order, but you do have the option to cancel it.</p>\n" + 
				"                            \n" + 
				"                            <p style=\"line-height:20px; padding:0 0 5px 0;\">\n" + 
				"                            	<img src="+shipping_pic_URL+" alt=\"\" style=\"border:none; max-width:100%; vertical-align:top; padding:0 5px 0 0; display:inline-block;\">\n" + 
				"                                <span style=\"display:inline-block; text-align:left;\">\n" + 
				"                                <strong>Standard Delivery</strong> <br>\n" + 
				"								Cancel within one hour</span>\n" + 
				"                            </p>\n" + 
				"                            \n" + 
				"                            <p style=\"font-size:13px; color:#3a3a3a; padding:0 0 25px 0;\">Go to your order from the button below and follow the instructions.</p>\n" + 
				"                            <a href=\"https://pecom.monginis.net/ecom/orderhistory/\" style=\"text-decoration:none; padding:10px 40px; border:1px solid #d9d9d9; color:#3a3a3a; text-transform:uppercase; font-weight:bold; font-size:12px;\">View Order</a>\n" + 
				"                            \n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:20px 0; color:#3a3a3a; font-size:13px; border-bottom:1px solid #d9d9d9;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                              <tr>\n" + 
				"                                <td colspan=\"3\" style=\"text-align:center; padding:0 0 40px 0; line-height:20px; color:#3a3a3a;\">\n" + 
				"                                    If you need help with anything please don't hesitate to drop us an <br>email:care@monginis.com\n" + 
				"                                </td>\n" + 
				"                                </tr>\n" + 
				"                              <tr>\n" + 
				"                                <td colspan=\"3\" >&nbsp;</td>\n" + 
				"                              </tr>\n" + 
				"                              <tr>\n" + 
				"                                <td align=\"center\" style=\"font-size:12px; text-transform:uppercase; font-weight:bold; letter-spacing:0.3px; width:33.333%; max-width:100%;\">\n" + 
				"                                  <a href=\"https://pecom.monginis.net/ecom\" style=\"text-decoration:none; color:#24247e;\">Home</a>\n" + 
				"                                </td>\n" + 
				"                                <td align=\"center\" style=\"font-size:12px; text-transform:uppercase; font-weight:bold; letter-spacing:0.3px; width:33.333%; max-width:100%;\">\n" + 
				"                                  <a href=\"https://pecom.monginis.net/ecom\" style=\"text-decoration:none; color:#24247e;\">About Us</a>\n" + 
				"                                </td>\n" + 
				"                                <td align=\"center\" style=\"font-size:12px; text-transform:uppercase; font-weight:bold; letter-spacing:0.3px; width:33.333%; max-width:100%;\">\n" + 
				"                                  <a href=\"https://pecom.monginis.net/ecom\" style=\"text-decoration:none; color:#24247e;\">Contact Us</a>\n" + 
				"                                </td>\n" + 
				"                              </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"border-bottom:1px solid #d9d9d9;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                  <tr>\n" + 
				"                    <td style=\"width:50%; max-width:100%; text-align:center; padding:20px;\">\n" + 
				"                        <span style=\"font-size:13px; padding:0 0 20px 0; display:block; color:#3a3a3a;\">Lets Hang Out?</span>\n" + 
				"                        <a href=\"https://www.facebook.com/monginis/\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src="+fb_icon_URL+" alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 5px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://twitter.com/MonginisIndia\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src="+twt_icon_URL+" alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 5px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://www.youtube.com/channel/UCL2K9zi9D94YBbmU1GJicAA\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src="+yt_icon_URL+" alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 5px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://www.instagram.com/monginiscelebrations/\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src="+insta_icon_URL+" alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 5px;\">\n" + 
				"                        </a>\n" + 
				"                    </td>\n" + 
				"                    <td style=\"width:50%; max-width:100%; text-align:center; padding:20px; border-left:1px solid #d9d9d9; color:#3a3a3a; font-size:13px;\">\n" + 
				"                    	<span style=\"text-transform:uppercase; font-size:13px; padding:0 0 0px 0; display:block; font-weight:bold;\">Office</span>\n" + 
				"                         <p style=\"font-size:13px; line-height:20px;\">1st Floor, Raj Apartment, <br>\n" + 
				"                            Rachna Vidyalay Road,<br>\n" + 
				"                            Sharanpur Rd, Nashik.<br></p>\n" + 
				"\n" + 
				"						 Copyright &copy; 2021\n" + 
				"                    </td>\n" + 
				"                  </tr>\n" + 
				"</table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:20px 10px; text-align:center;\">\n" + 
				"                        	<a href=\"https://www.monginis.net/\" target=\"_blank\" style=\"color:#24247e; text-decoration:none; font-size:13px;\">monginis.net</a>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                  </table></td>\n" + 
				"              </tr>\n" + 
				"            </table>\n" + 
				"            <!--bottom start---> \n" + 
				"            <!--bottom end---></td>\n" + 
				"        </tr>\n" + 
				"      </table>\n" + 
				"      <!--end--></td>\n" + 
				"  </tr>\n" + 
				"</table>\n" + 
				"</body>\n" + 
				"</html>";

		String msg2="﻿<!doctype html>\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
				"<head>\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
				"<title>:: Monginis | Emailer ::</title>\n" + 
				"</head>\n" + 
				"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" yahoo=\"fix\" style=\"font-family:Arial, sans-serif; background:#e3ebef;\">\n" + 
				"<!-- Wrapper -->\n" + 
				"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" + 
				"  <tr>\n" + 
				"    <td width=\"100%\" valign=\"top\" bgcolor=\"#f8208d\">\n" + 
				"    	<!-- Start Header-->\n" + 
				"      <table width=\"700\" id=\"tborder\" class=\"deviceWidth\" bgcolor=\"#FFF\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\"  style=\"position:relative;\">\n" + 
				"        <tr>\n" + 
				"          <td cellspacing=\"0\" cellpadding=\"0\"  style=\" padding:0;\"><table width=\"100%\" id=\"\" class=\"\" cellspacing=\"0\" cellpadding=\"0\"  align=\"center\" background=\"#000\" border=\"0\" style=\"padding:0;\">\n" + 
				"          	  \n" + 
				"              <tr>\n" + 
				"                <td align=\"center\">&nbsp;</td>\n" + 
				"              </tr>\n" + 
				"              <tr>\n" + 
				"                <td align=\"center\">\n" + 
				"                    <img src=\"images/logo.png\" alt=\"main_logo\" style=\"border:none; max-width:100%; padding: 0; float:none;\">\n" + 
				"                  </td>\n" + 
				"              </tr>\n" + 
				"              <tr>\n" + 
				"                <td cellspacing=\"0\" cellpadding=\"0\" style=\"position:relative; padding:30px 40px 10px 40px;\" border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" + 
				"                    <tr>\n" + 
				"                      <td style=\"text-align:center; font-size:16px; letter-spacing:0.5px; text-transform:uppercase; padding:0 0 20px 0; color:#24247e;\">\n" + 
				"                      	<strong>Order Confirmation</strong></td>\n" + 
				"                    </tr>\n" + 
				"                    <tr>\n" + 
				"                      <td style=\"text-align:center; padding:0 0 25px 0; font-size:13px; line-height:28px; color:#3a3a3a;\">\n" + 
				"                      	Hi James Pattrick, <br>\n" + 
				"                        We've got your order! Your world is about to look a whole lot better.<br>\n" + 
				"						We'll drop you another email when your order ships.\n" + 
				"                      </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                      <td style=\"text-align:center; padding:0 0 15px 0; font-size:15px; text-transform:uppercase; letter-spacing:0.5px; font-weight:bold; line-height:24px; color:#24247e;\">\n" + 
				"                      	Order No. Monginis-1234 <br>\n" + 
				"						<span style=\"font-size:13px; color:#3a3a3a; font-weight:bold;\">01/07/2021</span>\n" + 
				"                      </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                  </table></td>\n" + 
				"              </tr>\n" + 
				"              \n" + 
				"              <tr>\n" + 
				"                <td cellspacing=\"0\" cellpadding=\"0\"  style=\"background: #FFF; position:relative; padding:0 50px;\" border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                    <tr>                          \n" + 
				"                      <td style=\"background:#FFF; padding:15px 0; color:#24247e; text-align:left; font-size:13px; font-weight:bold; text-transform:uppercase; border-bottom:1px solid #d9d9d9; letter-spacing:0.5px;\"><strong>Item Order</strong></td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <!--1-->\n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:15px; border-bottom:1px solid #d9d9d9;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                              <tr>\n" + 
				"                                <td><img src=\"images/cake_pic.jpg\" alt=\"\" width=\"64\" style=\"border:none; max-width:100%;  float:left;\"></td>\n" + 
				"                                <td style=\"font-size:13px; line-height:20px;\"><strong style=\"color:#24247e;\">Premium Black Fores Cake</strong> <br> QTY : 1 </td>\n" + 
				"                                <td style=\"text-align:right; font-size:13px; font-weight:bold; color:#ed258f;\">Rate : 250.00</td>\n" + 
				"                              </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <!--2-->\n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:15px; border-bottom:1px solid #d9d9d9;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                              <tr>\n" + 
				"                                <td><img src=\"images/cake_pic.jpg\" alt=\"\" width=\"64\" style=\"border:none; max-width:100%; float:left;\"></td>\n" + 
				"                                <td style=\"font-size:13px; line-height:20px;\"><strong style=\"color:#24247e;\">Premium Black Fores Cake</strong> <br> QTY : 1 </td>\n" + 
				"                                <td style=\"text-align:right; font-size:13px; font-weight:bold; color:#ed258f;\">Rate : 250.00</td>\n" + 
				"                              </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <!--price box-->\n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:20px 0; font-size:13px; font-weight:bold; line-height:28px; color:#3a3a3a;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                              <tr>\n" + 
				"                                <td style=\"float:left;\">\n" + 
				"                                    Discount (Justincase) <br>\n" + 
				"                                    Subtotal <br>\n" + 
				"                                    Royal Mail Tracked &amp; Signed (4-9 Days) <br>\n" + 
				"                                    Total\n" + 
				"                                </td>\n" + 
				"                                <td style=\"float:right; \">\n" + 
				"                                    Rs. -18.00<br>\n" + 
				"                                    Rs. 232.00<br>\n" + 
				"                                    Rs. 0.00<br>\n" + 
				"                                    Rs. 232.00\n" + 
				"                                </td>\n" + 
				"                              </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <!--billing info box-->\n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:20px 0; font-size:13px; color:#3a3a3a; border-top:1px solid #d9d9d9; border-bottom:1px solid #d9d9d9;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                              <tr>\n" + 
				"                                <td style=\"float:left; max-width:100%; text-align:left; line-height:24px;\">\n" + 
				"                                	<span style=\"display:block; padding:0 0 5px 0; font-weight:bold; text-transform:uppercase; color:#24247e;\">Billing Info</span>\n" + 
				"                                    James Pattrick <br>\n" + 
				"                                    1st Floor, Raj Apartment, <br>\n" + 
				"                                    Rachna Vidyalay Road, <br>\n" + 
				"                                    Sharanpur Rd, Nashik. <br>\n" + 
				"                                    India. <br>\n" + 
				"                                    <a href=\"mailto:hello@jamespattrick.com\">hello@jamespattrick.com</a>\n" + 
				"                                </td>\n" + 
				"                                <td style=\"float:right; max-width:100%; line-height:24px; \">\n" + 
				"                                	<span style=\"display:block; padding:0 0 5px 0; font-weight:bold; text-transform:uppercase; color:#24247e;\">Shipping Address</span>\n" + 
				"                                    James Pattrick <br>\n" + 
				"                                    1st Floor, Raj Apartment, <br>\n" + 
				"                                    Rachna Vidyalay Road, <br>\n" + 
				"                                    Sharanpur Rd, Nashik. <br>\n" + 
				"                                    India.\n" + 
				"                                </td>\n" + 
				"                              </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"border-bottom:1px solid #d9d9d9; padding:25px 0 40px 0; font-size:13px; color:#3a3a3a; text-align:center;\">\n" + 
				"                        	<span style=\"display:block; padding:0 0 25px 0; font-size:13px; text-transform:uppercase; letter-spacing:0.3px; font-weight:bold; color:#24247e;\">Change your mind?</span>\n" + 
				"                            <span style=\"color:#ed258f; font-size:12px; text-transform:uppercase; font-weight:bold; padding:0; line-height:12px;\">Cancelling an order</span>\n" + 
				"                            <p style=\"color:#3a3a3a; padding:0 0 10px 0; display:block;\">we're not able to make changes to your order, but you do have the option to cancel it.</p>\n" + 
				"                            \n" + 
				"                            <p style=\"line-height:20px; padding:0 0 5px 0;\">\n" + 
				"                            	<img src=\"images/shipping_pic.png\" alt=\"\" style=\"border:none; max-width:100%; vertical-align:top; padding:0 5px 0 0; display:inline-block;\">\n" + 
				"                                <span style=\"display:inline-block; text-align:left;\">\n" + 
				"                                <strong>Standard Delivery</strong> <br>\n" + 
				"								Cancel within one hour</span>\n" + 
				"                            </p>\n" + 
				"                            \n" + 
				"                            <p style=\"font-size:13px; color:#3a3a3a; padding:0 0 25px 0;\">Go to your order from the button below and follow the instructions.</p>\n" + 
				"                            <a href=\"#\" style=\"text-decoration:none; padding:10px 40px; border:1px solid #d9d9d9; color:#3a3a3a; text-transform:uppercase; font-weight:bold; font-size:12px;\">View Order</a>\n" + 
				"                            \n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:20px 0; color:#3a3a3a; font-size:13px; border-bottom:1px solid #d9d9d9;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                              <tr>\n" + 
				"                                <td colspan=\"3\" style=\"text-align:center; padding:0 0 40px 0; line-height:20px; color:#3a3a3a;\">\n" + 
				"                                    If you need help with anything please don't hesitate to drop us an <br>email:care@monginis.com\n" + 
				"                                </td>\n" + 
				"                                </tr>\n" + 
				"                              <tr>\n" + 
				"                                <td colspan=\"3\" >&nbsp;</td>\n" + 
				"                              </tr>\n" + 
				"                              <tr>\n" + 
				"                                <td align=\"center\" style=\"font-size:12px; text-transform:uppercase; font-weight:bold; letter-spacing:0.3px; width:33.333%; max-width:100%;\">\n" + 
				"                                  <a href=\"#\" style=\"text-decoration:none; color:#24247e;\">Home</a>\n" + 
				"                                </td>\n" + 
				"                                <td align=\"center\" style=\"font-size:12px; text-transform:uppercase; font-weight:bold; letter-spacing:0.3px; width:33.333%; max-width:100%;\">\n" + 
				"                                  <a href=\"#\" style=\"text-decoration:none; color:#24247e;\">About Us</a>\n" + 
				"                                </td>\n" + 
				"                                <td align=\"center\" style=\"font-size:12px; text-transform:uppercase; font-weight:bold; letter-spacing:0.3px; width:33.333%; max-width:100%;\">\n" + 
				"                                  <a href=\"#\" style=\"text-decoration:none; color:#24247e;\">Contact Us</a>\n" + 
				"                                </td>\n" + 
				"                              </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"border-bottom:1px solid #d9d9d9;\">\n" + 
				"                        	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
				"                  <tr>\n" + 
				"                    <td style=\"width:50%; max-width:100%; text-align:center; padding:20px;\">\n" + 
				"                        <span style=\"font-size:13px; padding:0 0 20px 0; display:block; color:#3a3a3a;\">Lets Hang Out?</span>\n" + 
				"                        <a href=\"https://www.facebook.com/monginis/\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src=\"images/fb_icon.jpg\" alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 10px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://twitter.com/MonginisIndia\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src=\"images/twt_icon.jpg\" alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 10px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://www.youtube.com/channel/UCL2K9zi9D94YBbmU1GJicAA\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src=\"images/yt_icon.jpg\" alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 10px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://www.instagram.com/monginiscelebrations/\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src=\"images/insta_icon.jpg\" alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 10px;\">\n" + 
				"                        </a>\n" + 
				"                    </td>\n" + 
				"                    <td style=\"width:50%; max-width:100%; text-align:center; padding:20px; border-left:1px solid #d9d9d9; color:#3a3a3a; font-size:13px;\">\n" + 
				"                    	<span style=\"text-transform:uppercase; font-size:13px; padding:0 0 0px 0; display:block; font-weight:bold;\">Office</span>\n" + 
				"                         <p style=\"font-size:13px; line-height:20px;\">1st Floor, Raj Apartment, <br>\n" + 
				"                            Rachna Vidyalay Road,<br>\n" + 
				"                            Sharanpur Rd, Nashik.<br></p>\n" + 
				"\n" + 
				"						 Copyright &copy; 2021\n" + 
				"                    </td>\n" + 
				"                  </tr>\n" + 
				"</table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    \n" + 
				"                    <tr>\n" + 
				"                    	<td style=\"padding:20px 10px; text-align:center;\">\n" + 
				"                        	<a href=\"https://www.monginis.net/\" target=\"_blank\" style=\"color:#24247e; text-decoration:none; font-size:13px;\">monginis.net</a>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                  </table></td>\n" + 
				"              </tr>\n" + 
				"            </table>\n" + 
				"            <!--bottom start---> \n" + 
				"            <!--bottom end---></td>\n" + 
				"        </tr>\n" + 
				"      </table>\n" + 
				"      <!--end--></td>\n" + 
				"  </tr>\n" + 
				"</table>\n" + 
				"</body>\n" + 
				"</html>";
		
		Info info = new Info();
		try {
		EmailUtility.sendEmailer(recipientEmail, mailsubject, msg1);
	
		info.setError(false);
		}catch (Exception e) {
			info.setError(true);
			return info;
		}
		
		return info;
	}
	
	
	@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
	public @ResponseBody Info getFarmerContract(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam("imageName") String imageName) {

		Info info = new Info();
		System.out.println("File Name " + uploadfile.getOriginalFilename());
		String a=	imageName.replaceAll("^\"|\"$", "");//Akhilesh ," " In Image Name Problm 2021-03-11
		System.out.println("imageName Name1 " +a);
		
		
		
		try {
			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);//Akhilesh ," " In Image Name Problm 2021-03-11
			saveUploadedFiles(Arrays.asList(uploadfile), a);

			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);

			info.setError(false);
			info.setMessage("File uploaded successfully");

		} catch (IOException e) {
			System.err.println("Exceptn In getFarmerContract ");
			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}

	@RequestMapping(value = { "/photoUpload1" }, method = RequestMethod.POST)
	public @ResponseBody Info getFarmerContract1(@RequestParam("file") List<MultipartFile> uploadfile,
			@RequestParam("imageName") List<String> imageName) {

		Info info = new Info();
		/*
		 * System.out.println("File Name " + uploadfile.getOriginalFilename()); String
		 * a= imageName.replaceAll("^\"|\"$", "");//Akhilesh ," " In Image Name Problm
		 * 2021-03-11 System.out.println("imageName Name1 " +a);
		 */
		
		
		try {
			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);//Akhilesh ," " In Image Name Problm 2021-03-11
			//saveUploadedFiles(Arrays.asList(uploadfile), a);

			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);
			for(int i=0;i<uploadfile.size();i++) {
				String imageName2=imageName.get(i).replace('"', ' ');
				saveUploadedFiles(Arrays.asList(uploadfile.get(i)),imageName2);
				info.setError(false);
				info.setMessage("File uploaded successfully");

			}

			/*
			 * info.setError(false); info.setMessage("File uploaded successfully");
			 */

		} catch (IOException e) {
			System.err.println("Exceptn In getFarmerContract ");
			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}

	
	// save file
	private void saveUploadedFiles(List<MultipartFile> files, String imageName) throws IOException {
		
		try {
			for (MultipartFile file : files) {
				Path path = null;
				if (file.isEmpty()) {
					continue;
				}
			
				path = Paths.get(ApiConstants.UPLOAD_URL+imageName.trim());
				 System.out.println(path.toAbsolutePath());
			
				byte[] bytes = file.getBytes();

				Files.write(path, bytes);

			

		}
			
		}catch (Exception e) {
			System.err.println("Exceptn In saveUploadedFiles ");
			e.printStackTrace();
		}

	}
	

}
