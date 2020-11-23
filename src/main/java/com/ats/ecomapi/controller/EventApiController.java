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

import com.ats.ecomapi.master.model.Tax;
import com.ats.ecomapi.mst_model.CateFilterConfig;
import com.ats.ecomapi.mst_model.FestiveEvent;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.ProductMaster;
import com.ats.ecomapi.mst_repo.CateFilterConfigRepo;
import com.ats.ecomapi.mst_repo.FestiveEventRepo;
import com.ats.ecomapi.mst_repo.ProductMasterRepo;

@RestController
public class EventApiController {

	@Autowired
	FestiveEventRepo festivEventRepo;

	@Autowired
	ProductMasterRepo productRepo;
	
	@Autowired 
	CateFilterConfigRepo cateFltrRepo;

	// Created By :- Mahendra Singh
	// Created On :- 23-11-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Configure Product and Festive Events List
	@RequestMapping(value = { "/getFestiveEventAndProductsList" }, method = RequestMethod.POST)
	public @ResponseBody List<FestiveEvent> getFestiveEventAndProductsList(@RequestParam int compId) {
		List<FestiveEvent> res = new ArrayList<>();
		try {
			res = festivEventRepo.findByCompIdAndDelStatusOrderByEventIdDesc(compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// Created By :- Mahendra Singh
	// Created On :- 23-11-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Configure Product and Festive Events
	@RequestMapping(value = { "/saveFestiveEventAndProducts" }, method = RequestMethod.POST)
	public @ResponseBody FestiveEvent saveFestiveEventAndProducts(@RequestBody FestiveEvent festiveEvent) {
		FestiveEvent res = new FestiveEvent();
		try {
			res = festivEventRepo.save(festiveEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// Created By :- Mahendra Singh
	// Created On :- 23-11-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Configure Product and Festive Events By Id
	@RequestMapping(value = { "/getFestiveEventConfigById" }, method = RequestMethod.POST)
	public @ResponseBody FestiveEvent getFestiveEventConfigById(@RequestParam int eventId) {
		FestiveEvent res = new FestiveEvent();
		try {
			res = festivEventRepo.findByEventId(eventId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	// Created By :- Mahendra Singh
	// Created On :- 23-11-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Configure Product and Festive Events By Id
	@RequestMapping(value = { "/deleteFestiveEventById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteFestiveEventById(@RequestParam int eventId) {
		Info info = new Info();
		try {
			int res = festivEventRepo.deleteFestiveEventById(eventId);

			if (res > 0) {
				info.setError(false);
				info.setMessage("Products And Event Configure Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Products And Event Configure Failed To Delete ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;

	}

	// Created By :- Mahendra Singh
	// Created On :- 23-11-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Product List
	@RequestMapping(value = { "/getAllProducts" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getActiveTaxes(@RequestParam int compId) {

		List<ProductMaster> list = new ArrayList<ProductMaster>();
		try {
			list = productRepo.findByDelStatusAndCompanyId(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	/*-------------------------------------------------------------------------------*/
	 
	;

	// Created By :- Mahendra Singh
	// Created On :- 23-11-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Configure Category and Filters List
	@RequestMapping(value = { "/getConfigureCateAndFilters" }, method = RequestMethod.POST)
	public @ResponseBody CateFilterConfig getConfigureCateAndFilters(@RequestParam int compId, @RequestParam int cateId) {
		CateFilterConfig res = new CateFilterConfig();
		try {
			res = cateFltrRepo.findByCompIdAndCateId(compId, cateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// Created By :- Mahendra Singh
	// Created On :- 23-11-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Category Filter Configuration
	@RequestMapping(value = { "/saveCatAndFilter" }, method = RequestMethod.POST)
	public @ResponseBody CateFilterConfig saveCatAndFilter(@RequestBody CateFilterConfig config) {
		CateFilterConfig res = new CateFilterConfig();
		try {
			res = cateFltrRepo.save(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	// Created By :- Mahendra Singh
		// Created On :- 23-11-2020
		// Modified By :- NA
		// Modified On :- NA
		// Description :- Save Category Filter Configuration
		@RequestMapping(value = { "/updateFilterAndCatConfig" }, method = RequestMethod.POST)
		public @ResponseBody Info updateFilterAndCatConfig(@RequestParam int cateId, @RequestParam String filterIds, @RequestParam String upDateTime,
				@RequestParam int compId) {
			Info res = new Info();
			try {
				int val  = cateFltrRepo.updteFilterAndCate(cateId, filterIds, upDateTime, compId);
				
				if(val>0) {
					res.setError(false);
					res.setMsg("Category And Filter Configuration Update Successfully");
				}else {
					res.setError(true);
					res.setMsg("Failed to Update ategory And Filter Configuration");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
	
	
}
