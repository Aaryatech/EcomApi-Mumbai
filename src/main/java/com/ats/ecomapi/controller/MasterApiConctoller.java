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

import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.Uom;
import com.ats.ecomapi.mst_repo.UomRepo;

@RestController
public class MasterApiConctoller {

	@Autowired UomRepo uomRepo;
	
	@RequestMapping(value = { "/getUoms" }, method = RequestMethod.POST)
	public @ResponseBody List<Uom> getUoms(@RequestParam int compId) {

		List<Uom> list = new ArrayList<Uom>();
		try {
			list = uomRepo.findByDelStatusAndCompanyIdOrderByUomIdDesc(0, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	@RequestMapping(value = { "/saveUom" }, method = RequestMethod.POST)
	public @ResponseBody Uom saveUom(@RequestBody Uom uom) {

		Uom addUom = new Uom();
		try {
			addUom = uomRepo.save(uom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addUom;

	}
	
	@RequestMapping(value = { "/getUomById" }, method = RequestMethod.POST)
	public @ResponseBody Uom getUomById(@RequestParam int uomId) {

		Uom uom = new Uom();
		try {
			uom = uomRepo.findByUomId(uomId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uom;

	}
	
	@RequestMapping(value = { "/deleteUomById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteDesignationById(@RequestParam int uomId) {

		Info info = new Info();
		try {
			int res = uomRepo.deleteUom(uomId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Uom Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Uom");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
}
