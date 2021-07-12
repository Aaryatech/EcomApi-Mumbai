package com.ats.ecomapi.common ;

import java.net.URLEncoder;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ats.ecomapi.mst_model.Info;

public class EmailUtility {
	
	public static void name(String[] args) {
	//	sendEmail("","","handgesachin1@gmail.com", "ORDER ", "My Email","fsd");
		
		sendEmailer( "handgesachin1@gmail.com", "ORDER22 ", "<!doctype html>\n" + 
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
				"                    <img src=https://pecom.monginis.net/ecom/resources/images/emailer_img/logo.png alt=\"main_logo\" style=\"border:none; max-width:100%; padding: 0; float:none;\">\n" + 
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
				"                      	Order No. Monginis-00342 <br>\n" + 
				"						<span style=\"font-size:13px; color:#3a3a3a; font-weight:bold;\">06-07-2021</span>\n" + 
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
				"                                    Discount (Just in case) <br>\n" + 
				"                                    Subtotal <br>\n" + 
				"                                    Delivery Charges <br>\n" + 
				"                                    Total\n" + 
				"                                </td>\n" + 
				"                                <td style=\"float:right; \">\n" + 
				"                                    Rs. -0.0<br>\n" + 
				"                                    Rs. 1800.0<br>\n" + 
				"                                    Rs. 60.0<br>\n" + 
				"                                    Rs. 1860.0\n" + 
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
				"                            	<img src=https://pecom.monginis.net/ecom/resources/images/emailer_img/shipping_pic.png alt=\"\" style=\"border:none; max-width:100%; vertical-align:top; padding:0 5px 0 0; display:inline-block;\">\n" + 
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
				"                            <img src=https://pecom.monginis.net/ecom/resources/images/emailer_img/fb_icon.jpg alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 10px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://twitter.com/MonginisIndia\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src=https://pecom.monginis.net/ecom/resources/images/emailer_img/twt_icon.jpg alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 10px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://www.youtube.com/channel/UCL2K9zi9D94YBbmU1GJicAA\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src=https://pecom.monginis.net/ecom/resources/images/emailer_img/yt_icon.jpg alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 10px;\">\n" + 
				"                        </a>\n" + 
				"                        <a href=\"https://www.instagram.com/monginiscelebrations/\" target=\"_blank\" style=\"text-decoration:none;\"> \n" + 
				"                            <img src=https://pecom.monginis.net/ecom/resources/images/emailer_img/insta_icon.jpg alt=\"\" style=\"border:none; max-width:100%; display:inline-block; margin:0 10px;\">\n" + 
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
				"</html>");


	}
	public static Info sendEmail(String senderEmail,String senderPassword,String recipientEmail,String mailsubject,
		String defUsrName,String defPass) {
		  senderEmail="noreply.order@monginis.net";
		  senderPassword="eCom$orD%0507";
		Info info=new Info();
		
		try {
			
		final String emailSMTPserver = "smtp.gmail.com";
		final String emailSMTPPort = "587";
		final String mailStoreType = "imaps";
		final String username = senderEmail;//"atsinfosoft@gmail.com";
		final String password =senderPassword;//"atsinfosoft@123";

		System.out.println("username" + username);
		System.out.println("password" + password);

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");


		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Store mailStore = session.getStore(mailStoreType);
			mailStore.connect(emailSMTPserver, username, password);

			String address =recipientEmail;// "atsinfosoft@gmail.com";// address of to

			String subject = mailsubject;//" Login Credentials For RUSA Login  ";

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(username));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(defUsrName + defPass);
			
		
			Transport.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response1 : "+info);
			System.err.println("Ex1"+e.getMessage());
		}
			
			info.setError(false);
			info.setMessage("success_email");
			//System.err.println("Mail Response2 : "+info);
			
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response3 : "+info);
			System.err.println("Ex2"+e.getMessage());
		}
		
		return info;
		
	}
	
	//OTP EMAIL
	public static Info sendEmailOTP(String senderEmail,String senderPassword,String recipientEmail,String mailsubject,
			String defUsrName,String defPass) {
			
			Info info=new Info();
			
			try {
				
			final String emailSMTPserver = "smtp.gmail.com";
			final String emailSMTPPort = "587";
			final String mailStoreType = "imaps";
			final String username = senderEmail;//"atsinfosoft@gmail.com";
			final String password =senderPassword;//"atsinfosoft@123";

			System.out.println("username" + username);
			System.out.println("password" + password);

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");


			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				Store mailStore = session.getStore(mailStoreType);
				mailStore.connect(emailSMTPserver, username, password);

				String address =recipientEmail;// "atsinfosoft@gmail.com";// address of to

				String subject = mailsubject;//" Login Credentials For RUSA Login  ";

				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(username));
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
				mimeMessage.setSubject(subject);
				mimeMessage.setText("OTP Verification for  RUSA Software \n OTP is: " + defPass);
				
			
				Transport.send(mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMessage("email_exce");
			}
				
				info.setError(false);
				info.setMessage("success_email");
			}catch (Exception e) {
				
				info.setError(true);
				info.setMessage("email_exce");
			}
			
			return info;
			
		}
	
	public static Info sendMsg(String userName,String pass, String phoneNo) {
			
			Info info=new Info();
			
			try {
				   
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				/*map.add("authkey", "74499AcqeCdljW5ae561dd");
				map.add("mobiles", phoneNo);
				map.add("message", "RUSA CREDENTIAL Your User Name :" + userName +" Your Password :" + pass +" Plz Dont Share To Any One ");
				map.add("sender", "ESYRTO");
				map.add("route", "4");
				map.add("country", "91");*/
				
				//
				
			/*
			 * map.add("senderID", "RUSAMH"); map.add("user",
			 * "spdrusamah@gmail.com:Cyber@mva"); map.add("receipientno", phoneNo.trim());
			 * map.add("dcs", "0"); map.add(
			 * "msgtxt","User Registration Successful for RUSA Software \n Username: " +
			 * userName + "\n Password: " + pass); map.add("state", "4");
			 * 
			 * 
			 * //String response =
			 * restTemplate.postForObject("http://control.bestsms.co.in/api/sendhttp.php",
			 * map, String.class);
			 * 
			 * String response = restTemplate.postForObject(
			 * "http://api.mVaayoo.com/mvaayooapi/MessageCompose", map, String.class);
			 */
				String msg="User Registration Successful for RUSA Software \n Username: " + userName + "\n Password: " + pass;
				
				map.add("username", "rusamah-wb");
				map.add("password", "Rus@@123456");
				map.add("senderid", "MHRUSA");
				map.add("mobileno", phoneNo.trim());
				map.add("content", msg); 
				map.add("smsservicetype", "singlemsg"); 
				String sms = restTemplate.postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest",
				map, String.class);
				 
				info.setError(false);
				info.setMessage(sms);
			  
			}catch (Exception e) {
				
				info.setError(true);
				info.setMessage("sendMsg");
			}
			
			return info;
		}

	
	public static Info sendOtp(String OTP, String phoneNo) {
		
		Info info=new Info();
		
		try {
			   
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			String msg = "We welcome You to Madhvi!\n"+
						 "Your OTP to change your password is ("+OTP+").";
						 
			String message = msg;
			String mob = phoneNo.trim();
		
		String sms = restTemplate.getForObject("https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo="+mob+"&SenderID=MADHVI&Message="+message+"&ServiceName=TEMPLATE_BASED", String.class);
		
			info.setError(false);
			info.setMessage(sms);
		  
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("sendMsg");
		}
		
		return info;
	}
	
	public static Info send(String phoneNo, String msg) {
		
		Info info=new Info();
		
		try {
			   
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
//			String msg = "We welcome You to Madhvi!\n"+
//						 "Your OTP to change your password is ("+OTP+").";
						 
			String message = msg;
			String mob = phoneNo.trim();
		
//NA-		String sms = restTemplate.getForObject("https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo="+mob+"&SenderID=MADHVI&Message="+message+"&ServiceName=TEMPLATE_BASED", String.class);
		
			info.setError(false);
			//info.setMessage(sms);
			System.err.println("SMS Resp : "+info);
		  
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("sendMsg");
			System.err.println("SMS Resp : "+info);
			System.err.println("SMS Ex : "+e.getMessage());			
		}
		
		return info;
	}

	public static void sendinBulk(String msg, List<String> mobList) {
		
		Info info=new Info();
		
		try {
			   
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
//			String msg = "We welcome You to Madhvi!\n"+
//						 "Your OTP to change your password is ("+OTP+").";
			String sms="";	 
			String message = msg;
			List<String> mob = mobList;
			for (int i = 0; i < mob.size(); i++) {
				System.out.println("Fr Mobile No.----"+mob.get(i));
				try {
			//NA-		sms = restTemplate.getForObject("https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo="+mob.get(i)+"&SenderID=MADHVI&Message="+message+"&ServiceName=TEMPLATE_BASED", String.class);
				}catch (Exception e) {
					System.err.println("Ex in Bulk SMS : "+e.getMessage());
				}
			}
		
			 
			info.setError(false);
			//info.setMessage(sms);
			System.err.println("SMS Resp : "+info);
		  
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("sendMsg");
			System.err.println("SMS Resp : "+info);
			System.err.println("SMS Ex : "+e.getMessage());			
		}
		
	}

	public static Info sendOrderEmail(String senderEmail, String senderPassword, String frEmail, String mailsubject,
			String mailText) {
		
		Info info=new Info();
		
		try {
			
		final String emailSMTPserver = "smtp.gmail.com";
		final String emailSMTPPort = "587";
		final String mailStoreType = "imaps";
		final String username = senderEmail;//"atsinfosoft@gmail.com";
		final String password =senderPassword;//"atsinfosoft@123";

		System.out.println("username" + username);
		System.out.println("password" + password);

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");


		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Store mailStore = session.getStore(mailStoreType);
			mailStore.connect(emailSMTPserver, username, password);

			String address =frEmail;

			String subject = mailsubject;

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(username));
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(mailText, "text/html");
			
		
			Transport.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response1 : "+info);
			System.err.println("Ex1"+e.getMessage());
			e.printStackTrace();
		}
			
			info.setError(false);
			info.setMessage("success_email");			
			//System.err.println("Mail Response2 : "+info);
			
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response3 : "+info);
			System.err.println("Ex2"+e.getMessage());
			e.printStackTrace();
		}
		
		return info;
		
	}

	public static Info send(String frMob, Formatter f) {
		
		Info info=new Info();
		
		try {
			   
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
//			String msg = "We welcome You to Madhvi!\n"+
//						 "Your OTP to change your password is ("+OTP+").";
						 
			
			String mob = frMob.trim();
		
	//NA-		String sms = restTemplate.getForObject("https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo="+mob+"&SenderID=MADHVI&Message="+f+"&ServiceName=TEMPLATE_BASED", String.class);
		
			info.setError(false);
		//	info.setMessage(sms);
			System.err.println("SMS Resp : "+info);
		  
		}catch (Exception e) {
			
			info.setError(true);
			info.setMessage("sendMsg");
			System.err.println("SMS Resp : "+info);
			System.err.println("SMS Ex : "+e.getMessage());			
		}
		
		return info;
	}
	
	public static Info sendEmailer( String recipientEmail, String mailsubject,
			String mailMsg) {
		
		String senderEmail="noreply.order@monginis.net";
		String senderPassword="eCom$orD%0507";
			 
		Info info = new Info();

		try {
			

			final String emailSMTPserver = "smtp.gmail.com";
			final String emailSMTPPort = "587";
			final String mailStoreType = "imaps";

			System.out.println("senderEmail " + senderEmail);
			System.out.println("password " + senderPassword);

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});

			try {
				Store mailStore = session.getStore(mailStoreType);
				mailStore.connect(emailSMTPserver, senderEmail, senderPassword);
				
				String address = recipientEmail;// "atsinfosoft@gmail.com";// address of to

				String subject = mailsubject;// " Login Credentials For RUSA Login ";

				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(senderEmail));
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
				mimeMessage.setSubject(subject);
				// mimeMessage.setText(mailMsg);
				MimeMultipart multipart = new MimeMultipart("related");
				BodyPart messageBodyPart = new MimeBodyPart();				
				messageBodyPart.setContent(mailMsg, "text/html; charset=UTF-8");
				
				
				multipart.addBodyPart(messageBodyPart);				
				mimeMessage.setContent(multipart);
				
//				messageBodyPart = new MimeBodyPart();
//				DataSource fds = new FileDataSource(imgPath);
//				messageBodyPart.setDataHandler(new DataHandler(fds));
//				messageBodyPart.setHeader("Content-ID", "<image>");
//				
//				mimeMessage.setContent(multipart);

				Transport.send(mimeMessage);
				
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMsg("email_exce");
				// System.err.println("Mail Response1 : "+info);
				System.err.println("Ex1" + e.getMessage());
			}

			info.setError(false);
			info.setMsg("success_email");
			// System.err.println("Mail Response2 : "+info);

		} catch (Exception e) {

			info.setError(true);
			info.setMsg("email_exce");
			// System.err.println("Mail Response3 : "+info);
			System.err.println("Ex2" + e.getMessage());
		}

		return info;

	}

}