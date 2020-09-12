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
import com.ats.ecomapi.master.model.Uom;
import com.ats.ecomapi.master.repo.TaxRepo;
import com.ats.ecomapi.master.repo.UomRepo;
import com.ats.ecomapi.mst_model.Info;

@RestController
public class MasterApiConctoller {

	@Autowired
	UomRepo uomRepo;

	@Autowired
	TaxRepo taxRepo;

	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get UOM List
	@RequestMapping(value = { "/getUoms" }, method = RequestMethod.POST)
	public @ResponseBody List<Uom> getUoms(@RequestParam int compId) {

		List<Uom> list = new ArrayList<Uom>();
		try {
			list = uomRepo.findByDelStatusAndCompanyIdOrderByUomIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Save UOM
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

	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Single UOM
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

	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete UOM
	@RequestMapping(value = { "/deleteUomById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteUomById(@RequestParam int uomId) {

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

	/*--------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Tax List
	@RequestMapping(value = { "/getTaxes" }, method = RequestMethod.POST)
	public @ResponseBody List<Tax> getTaxes(@RequestParam int compId) {

		List<Tax> list = new ArrayList<Tax>();
		try {
			list = taxRepo.findByDelStatusAndCompanyIdOrderByTaxIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Active Tax List
	@RequestMapping(value = { "/getActiveTaxes" }, method = RequestMethod.POST)
	public @ResponseBody List<Tax> getActiveTaxes(@RequestParam int compId) {

		List<Tax> list = new ArrayList<Tax>();
		try {
			list = taxRepo.findByDelStatusAndCompanyIdAndIsActiveOrderByTaxIdDesc(1, compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Save Tax
	@RequestMapping(value = { "/saveTax" }, method = RequestMethod.POST)
	public @ResponseBody Tax saveTax(@RequestBody Tax tax) {

		Tax addTax = new Tax();
		try {
			addTax = taxRepo.save(tax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addTax;

	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Sigle Tax
	@RequestMapping(value = { "/getTaxById" }, method = RequestMethod.POST)
	public @ResponseBody Tax getTaxById(@RequestParam int taxId) {

		Tax tax = new Tax();
		try {
			tax = taxRepo.findByTaxId(taxId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tax;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Tax
	@RequestMapping(value = { "/deleteTaxById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteTaxById(@RequestParam int taxId) {

		Info info = new Info();
		try {
			int res = taxRepo.deleteTax(taxId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Tax Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Tax");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
}
