package com.ats.ecomapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SPController {
	
	@Autowired RateVerificationRepo rateVerRepo;
	
	@RequestMapping(value = { "/getData" }, method = RequestMethod.POST)
	public @ResponseBody List<GetMrnQueryItem> getData(
			@RequestParam String fromDate,@RequestParam int itemId) {
		
		return rateVerRepo.getMrnQueryItem2(fromDate, itemId);

	}
	
}
