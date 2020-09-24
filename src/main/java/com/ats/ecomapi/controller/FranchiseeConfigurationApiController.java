package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.master.model.CopyTable;
import com.ats.ecomapi.master.model.FrConfiguration;
import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.master.model.GetTableFields;
import com.ats.ecomapi.master.repo.FrConfigurationRepo;
import com.ats.ecomapi.master.repo.FranchiseRepo;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.ItemConfHeader;
import com.ats.ecomapi.mst_repo.ItemConfHeaderRepo;

@RestController
public class FranchiseeConfigurationApiController {

	@Autowired
	ItemConfHeaderRepo itemConfHeaderRepo;

	@Autowired
	FranchiseRepo franchiseRepo;

	@Autowired
	FrConfigurationRepo frConfigurationRepo;

	@RequestMapping(value = { "/getConfigurationByCatId" }, method = RequestMethod.POST)
	public @ResponseBody List<ItemConfHeader> getConfigurationByCatId(@RequestParam int catId) {

		List<ItemConfHeader> list = new ArrayList<ItemConfHeader>();

		try {
			list = itemConfHeaderRepo.findByCatIdAndDelStatus(catId, 1);
			System.err.println("list" + list.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/getFranchiseForConfig" }, method = RequestMethod.POST)
	public @ResponseBody List<Franchise> getFranchiseForConfig(@RequestParam int catId, @RequestParam int companyId) {

		List<Franchise> list = new ArrayList<Franchise>();

		try {
			List<ItemConfHeader> cnlist = itemConfHeaderRepo.findAll();

			/* if (cnlist.size() > 0) { */
				list = franchiseRepo.getFranchiseToConfig(companyId, catId);
			/*} else {
				list = franchiseRepo.findByCompanyIdAndDelStatusOrderByFrIdDesc(companyId, 1);
			}*/
			//System.err.println("list" + list.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/insertFrConfig" }, method = RequestMethod.POST)
	public @ResponseBody Info insertFrConfig(@RequestParam int actualRate, @RequestParam int displayRate,
			@RequestParam List<Integer> frIds, @RequestParam int cfgId, @RequestParam int userId,
			@RequestParam String curDateTime) {
		 
		
		Info res = new Info();
		
		
		try {
			List<FrConfiguration> configList=new ArrayList<>();
			for(int i=0;i<frIds.size();i++) {
				
				FrConfiguration fr=new FrConfiguration();
				fr.setActualRate(actualRate);
				fr.setConfigHeaderId(cfgId);
				fr.setDisplayRate(displayRate);
				fr.setFrId(frIds.get(i));
				fr.setUpdatedDateTime(curDateTime);
				fr.setMakerUserId(userId);
				fr.setMakerDatetime(curDateTime);
				fr.setIsActive(1);
				fr.setExInt1(0);
				fr.setExInt2(0);
				fr.setExVar1("NA");
				fr.setExVar2("NA");
				
				configList.add(fr);
			}
			
		List<FrConfiguration> save=	frConfigurationRepo.saveAll(configList);

		if (save != null) {
			res.setError(false);
			res.setMessage("Francise Configured Successfully");
		} else {
			res.setError(true);
			res.setMessage("Failed to Configure");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setError(true);
			res.setMessage("Failed to Configure");
		}
	 

	return res;
}

}