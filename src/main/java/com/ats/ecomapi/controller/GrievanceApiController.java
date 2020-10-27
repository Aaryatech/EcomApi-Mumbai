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

import com.ats.ecomapi.master.model.ProductHomePageDetail;
import com.ats.ecomapi.mst_model.GetGrievanceHeader;
import com.ats.ecomapi.mst_model.GetGrievanceTrail;
import com.ats.ecomapi.mst_model.TGrievance;
import com.ats.ecomapi.mst_model.TGrievanceTrail;
import com.ats.ecomapi.mst_repo.GetGrievanceRepo;
import com.ats.ecomapi.mst_repo.GetGrievanceTrailRepo;
import com.ats.ecomapi.mst_repo.GrievncTrailRepo;
import com.ats.ecomapi.mst_repo.TGrievncRepo;
@RestController
public class GrievanceApiController {

	@Autowired
	TGrievncRepo grievRepo;

	@Autowired
	GrievncTrailRepo grievTrailRepo;
	
	@Autowired GetGrievanceRepo getGrievRepo;
	
	@Autowired GetGrievanceTrailRepo getGrievTrailRepo;

	// Created By :- Mahendra Singh
	// Created On :- 27-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save TGrievance
	@RequestMapping(value = { "/saveGrievanceDtl" }, method = RequestMethod.POST)
	public @ResponseBody TGrievance saveHomePagePrdctConfigDtl(@RequestBody TGrievance grievance) {

		TGrievance saveGriev = new TGrievance();
		List<TGrievanceTrail> grievTrail = new ArrayList<TGrievanceTrail>();
		try {
			grievTrail = grievTrailRepo.saveAll(grievance.getGrievanceTrais());

			int lastId = grievTrail.get(grievTrail.size() - 1).getTrailId();

			if (lastId > 0) {
				saveGriev = grievRepo.save(grievance);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveGriev;

	}

	// Created By :- Mahendra Singh
	// Created On :- 27-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save TGrievance
	@RequestMapping(value = { "/getGrievanceTrailList" }, method = RequestMethod.GET)
	public @ResponseBody List<TGrievanceTrail> getGrievanceTrailList() {

		List<TGrievanceTrail> grievTrail = new ArrayList<TGrievanceTrail>();
		try {
			grievTrail = grievTrailRepo.findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievTrail;

	}
	
		// Created By :- Mahendra Singh
		// Created On :- 27-10-2020
		// Modified By :- NA
		// Modified On :- NA
		// Description :- Get grievance list by order Id
		@RequestMapping(value = { "/getGrievanceListByOrderId" }, method = RequestMethod.POST)
		public @ResponseBody List<GetGrievanceHeader> getGrievanceListByOrderId(@RequestParam("orderId") int orderId) {

			List<GetGrievanceHeader> grievList = new ArrayList<GetGrievanceHeader>();
			try {
				grievList = getGrievRepo.getGrievanceHeaderByOrderId(orderId);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return grievList;
		}
		
		// Created By :- Mahendra Singh
		// Created On :- 27-10-2020
		// Modified By :- NA
		// Modified On :- NA
		// Description :- Get grievance trail list by order Id
		@RequestMapping(value = { "/getGrievanceTrailListByGrievId" }, method = RequestMethod.POST)
		public @ResponseBody List<GetGrievanceTrail> getGrievanceTrailListByGrievId(@RequestParam("grievanceId") int grievanceId) {

			List<GetGrievanceTrail> grievList = new ArrayList<GetGrievanceTrail>();
			try {
				grievList = getGrievTrailRepo.getGrievTrailListByGrievanceId(grievanceId);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return grievList;
		}

}
