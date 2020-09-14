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

import com.ats.ecomapi.mst_model.CompMaster;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_repo.CompMasterRepo;

@RestController
public class CompanyApiController {
	
	

	@Autowired
	CompMasterRepo compMasterRepo;
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Save Company
	
	
	@RequestMapping(value = { "/saveCompany" }, method = RequestMethod.POST)
	public @ResponseBody CompMaster saveCompany(@RequestBody CompMaster comp) {
		System.err.println("CompMaster***"+comp.toString());

		CompMaster addComp = new CompMaster();
		try {
			addComp = compMasterRepo.save(comp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addComp;

	}
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Specific Company
	
	
 
	@RequestMapping(value = { "/getCompById" }, method = RequestMethod.POST)
	public @ResponseBody CompMaster getCompById(@RequestParam int compId) {

		CompMaster comp = new CompMaster();
		try {
			comp = compMasterRepo.findByCompanyId(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comp;

	}

	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Comapny List
	
	
	
	@RequestMapping(value = { "/getAllCompany" }, method = RequestMethod.GET)
	public @ResponseBody List<CompMaster> getAllCompany() {

		List<CompMaster> list = new ArrayList<CompMaster>();
		try {
			list = compMasterRepo.findByDelStatusOrderByCompanyIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Company
	
  
	@RequestMapping(value = { "/deleteCompById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteCompById(@RequestParam int compId) {

		Info info = new Info();
		try {
			int res = compMasterRepo.deleteCompany(compId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Company Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Company");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

}
