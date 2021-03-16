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

import com.ats.ecomapi.cms.model.TermsAndCondtn;
import com.ats.ecomapi.cms.repo.TermsAndCondtnRepo;
import com.ats.ecomapi.mst_model.Info;

@RestController
public class CMSApiController {

	@Autowired TermsAndCondtnRepo tAndCRepo;
	
//	@RequestMapping(value = { "/getTermsAndCondtns" }, method = RequestMethod.POST)
//	public @ResponseBody List<TermsAndCondtn> getTermsAndCondtns(@RequestParam int companyId) {
//
//		List<TermsAndCondtn> list = new ArrayList<TermsAndCondtn>();
//		try {
//			list = tAndCRepo.findByCompanyIdAndDelStatusOrderByTermsIdDesc(companyId, 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//
//	}
//	
//	@RequestMapping(value = { "/addTermsAndConditions" }, method = RequestMethod.POST)
//	public @ResponseBody TermsAndCondtn addTermsAndConditions(@RequestBody TermsAndCondtn tnc) {
//
//		TermsAndCondtn newTnc = new TermsAndCondtn();
//		
//		try {
//			newTnc = tAndCRepo.save(tnc);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return newTnc;
//	}
//	
//	@RequestMapping(value = { "/getTermsAndCondtnById" }, method = RequestMethod.POST)
//	public @ResponseBody TermsAndCondtn getTermsAndCondtnById(@RequestParam int termId) {
//
//		TermsAndCondtn list = new TermsAndCondtn();
//		try {
//			list = tAndCRepo.findByTermsId(termId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//
//	}
//	
//	
//	@RequestMapping(value = { "/deleteTermsAndCondtnId" }, method = RequestMethod.POST)
//	public @ResponseBody Info deleteUomById(@RequestParam int termsId) {
//
//		Info info = new Info();
//		try {
//			int res = tAndCRepo.deleteTermsAndCondition(termsId);
//			if (res > 0) {
//				info.setError(false);
//				info.setMessage("Terms & Condition Deleted Successfully");
//			} else {
//				info.setError(true);
//				info.setMessage("Failed to Delete Terms & Condition");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return info;
//	}
}
