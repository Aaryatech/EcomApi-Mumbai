package com.ats.ecomapi.common;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ats.ecomapi.mst_model.Info;

public class SMSUtility  {

	public static Info sendSMS(String mobileNo, String msg) {

		Info info = new Info();

		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("username", "monginisnew");
			map.add("pass", "monginis@123");
			map.add("senderid", "MONGIN");
			map.add("dest_mobileno", mobileNo);
			map.add("message", msg);
			map.add("response", "Y");
			String sms = restTemplate.postForObject("https://www.smsjust.com/sms/user/urlsms.php", map,
					String.class);
			info.setError(false);
			info.setMsg(sms);
			System.err.println("SMS --------------- "+sms);
		} catch (Exception e) {
			info.setError(true);
			info.setMsg("SMS-Exce");
			//info.setMessage("sendMsg");
			e.printStackTrace();
		}

		return info;
	}
	
	public static void main(String[] args) {
		sendSMS("9404725912","Welcome to sms api services- test msg2" );
	}
	
}
