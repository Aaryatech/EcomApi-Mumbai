package com.ats.ecomapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.common.CommonUtility;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.User;
import com.ats.ecomapi.mst_repo.UserRepo;



@RestController
public class SPController {
	
	@Autowired RateVerificationRepo rateVerRepo;
	
	@RequestMapping(value = { "/getData" }, method = RequestMethod.POST)
	public @ResponseBody List<GetMrnQueryItem> getData(
			@RequestParam String fromDate,@RequestParam int itemId) {
		
		return rateVerRepo.getMrnQueryItem2(fromDate, itemId);

	}
	
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping(value = { "/checkUserNamePassForLogin" }, method = RequestMethod.POST)
	public @ResponseBody Object checkUserNameForLogin(@RequestParam String userName, @RequestParam String pass) {
		Info info = new Info();
		User loginUser = new User();
		int userId = 0;
		try {
			System.err.println("Username ----------- "+userName);
			System.err.println("Password ----------- "+pass);
			// Check if user name exists
			// User name should be case in sensitive
			try {
			userId = userRepo.getUserId(userName.trim());
			}catch (Exception e) {
				info.setError(true);
				info.setMsg("Invalid User name ");
			}
			
			if (userId!=0) {
				try {
					loginUser = userRepo.findByUserId(userId);
				} catch (Exception e) {
					info.setError(true);
					e.printStackTrace();
				}
				if(loginUser!=null) {

					if(pass.trim().equals(loginUser.getPassword().trim())) {
						//password matched.
						info.setError(false);
						info.setMsg("Login Successful ");
						info.setResponseObject1(CommonUtility.toJSONString(loginUser));
						return info;
					}else {
						//password not matched.
						info.setError(true);
						info.setMsg("Invalid password ");
						return info;
					}
				}
			} else {
				info.setError(true);
				info.setMsg("Invalid user name ");
				return info;
			}

		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMsg("Exception Occurred  ");
		}
		
		return info;

	}
	
}
