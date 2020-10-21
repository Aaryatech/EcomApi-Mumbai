package com.ats.ecomapi.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.common.CommonUtility;
import com.ats.ecomapi.common.EmailUtility;
import com.ats.ecomapi.fe_model.OTPVerification;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.User;
import com.ats.ecomapi.mst_repo.UserRepo;

@RestController
public class ForgotPassAPIController {

	@Autowired
	UserRepo userRepo;

	public static String senderEmail = "madhvierp@gmail.com";
	public static String senderPassword = "madhvi@#2020";
	static String mailsubject = "";
	String otp1 = null;

	@RequestMapping(value = { "/getMnUserDetailByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody User getMnUserDetailByMobNo(@RequestParam String mobEmail) {
		OTPVerification.setConNumber(null);
		OTPVerification.setEmailId(null);
		OTPVerification.setOtp(null);
		OTPVerification.setPass(null);
		Info info = new Info();

		User user = new User();
		try {

			user = userRepo.findByUserMobileNoAndDelStatus(mobEmail, 1);
			if (user == null) {
				user = userRepo.findByUserEmailAndDelStatus(mobEmail, 1);
			}
			if (user != null) {
				OTPVerification.setUserId(user.getUserId());

				String emailId = user.getUserEmail();
				String conNumber = user.getUserMobileNo();
				System.err.println("User Found----------" + conNumber);
				char[] otp = CommonUtility.OTP(6);
				otp1 = String.valueOf(otp);
				System.err.println("User otp is : " + otp1);

				//Info inf = EmailUtility.sendOtp(otp1, conNumber);	//Remove Later

				mailsubject = " OTP  Verification ";
				String text = "\n OTP for change your Password: ";
				//Info emailRes = EmailUtility.sendEmail(senderEmail, senderPassword, emailId, mailsubject, text, otp1);
				//Remove Later
				
				OTPVerification.setConNumber(conNumber);
				OTPVerification.setEmailId(emailId);
				OTPVerification.setOtp(otp1);
				OTPVerification.setPass(user.getPassword());
			} else {
				System.err.println("In Else ");

				info.setError(true);
				info.setMessage("not Matched");
				System.err.println(" not Matched ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = { "/verifyOTP" }, method = RequestMethod.POST)
	public @ResponseBody User verifyOTP(@RequestParam String otp) {
		Info info = new Info();

		Object object = new Object();
		HashMap<Integer, User> hashMap = new HashMap<>();

		User user = new User();

		try {
			// System.err.println("OTP
			// Found------------------"+otp+"------"+OTPVerification.getOtp()+"
			// "+OTPVerification.getUserId());
			if (otp.equals(OTPVerification.getOtp()) == true) {
				info.setError(false);
				info.setMessage("success");

				String mobile = OTPVerification.getConNumber();
				String email = OTPVerification.getEmailId();
				String pass = CommonUtility.getAlphaNumericString(7);
				// System.out.println("pass");
				// int res = staffrepo.chagePass(pass, OTPVerification.getUserId());

				user = userRepo.findByUserId(OTPVerification.getUserId());
				System.out.println("user---------" + user);
				hashMap.put(1, user);

			} else {
				info.setError(true);
				info.setMessage("failed");
			}

		} catch (Exception e) {

			System.err.println("Exce in verifyOTP " + e.getMessage());
			e.printStackTrace();
			info.setError(true);
			info.setMessage("excep");
		}

		return user;

	}

	@RequestMapping(value = { "/updateToNewPassword" }, method = RequestMethod.POST)
	public @ResponseBody Info updateToNewPassword(@RequestParam int userId, @RequestParam String newPass) {

		Info res = new Info();

		int a = userRepo.updateUserPass(userId, newPass);
		if (a > 0) {

			mailsubject = " New Credentials ";
			String text = "\n Your new username and password are : \n";

			User usr = new User();
			usr = userRepo.findByUserId(userId);
			if (usr != null) {
				String emailId = usr.getUserEmail();
				String password = "\n Username : " + usr.getUserName() + " \n Password : " + usr.getPassword();

				Info emailRes = EmailUtility.sendEmail(senderEmail, senderPassword, emailId, mailsubject, text,
						password);
			}
			res.setError(false);
			res.setMessage("success");
		} else {
			res.setError(true);
			res.setMessage("fail");
		}

		return res;
	}
}
