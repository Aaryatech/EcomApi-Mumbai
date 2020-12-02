package com.ats.ecomapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.mst_repo.OrderHeaderRepo;

@RestController
public class OrderHeaderApiController {
	@Autowired OrderHeaderRepo orderHeadRepo;
	
	
}
