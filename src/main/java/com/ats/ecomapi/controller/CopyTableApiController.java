package com.ats.ecomapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.master.model.Category;
import com.ats.ecomapi.master.model.CopyTable;
import com.ats.ecomapi.master.model.GetTableFields;
import com.ats.ecomapi.master.model.Uom;
import com.ats.ecomapi.master.repo.CategoryRepo;
import com.ats.ecomapi.master.repo.CopyTableRepo;
import com.ats.ecomapi.master.repo.GetTableFieldsRepo;
import com.ats.ecomapi.master.repo.GetTableNamesRepo;
import com.ats.ecomapi.master.repo.UomRepo;
import com.ats.ecomapi.mst_model.GetTableNames;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@RestController
public class CopyTableApiController {

	@Autowired
	GetTableNamesRepo getTableNamesRepo;

	@Autowired
	CopyTableRepo copyTableRepo;

	@RequestMapping(value = { "/getAllTables" }, method = RequestMethod.GET)
	public @ResponseBody List<GetTableNames> getAllUsers() {

		System.err.println("hii");
		List<GetTableNames> tablList = new ArrayList<GetTableNames>();
		try {
			System.err.println("hii11");

			tablList = getTableNamesRepo.getTableList();
			System.err.println("hii22");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tablList;
	}

	@Autowired
	GetTableFieldsRepo getTableFieldsRepo;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = { "/getTableData" }, method = RequestMethod.POST)
	public @ResponseBody List<GetTableFields> getTableData(String tbl_name) {

		List<GetTableFields> fields = new ArrayList<GetTableFields>();

		try {

			CopyTable copyTabl = copyTableRepo.findByCopyNameAndDelStatus(tbl_name, 1);

			// String q = "SELECT m_category.cat_id AS id,m_category.cat_name AS
			// ext_field1,m_category.cat_prefix AS ext_field2,m_category.cat_desc AS
			// ext_field3,0 AS ext_field4,0 AS ext_field5,0 AS ext_field6,0 AS ext_field7,0
			// AS ext_field8,0 AS ext_field9,0 AS ext_field10 FROM m_category WHERE
			// m_category.del_status = 1 AND m_category.allow_to_copy = 1";

			fields = jdbcTemplate.query(copyTabl.getQuery(), new BeanPropertyRowMapper(GetTableFields.class));
			System.err.println("fields" + fields.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fields;

	}

	@Autowired
	CategoryRepo catRepo;

	@Autowired
	UomRepo uomRepo;

	@RequestMapping(value = { "/getCopyTbl" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getCopyTbl(String tbl_name) {

		List<String> list = new ArrayList<String>();
		CopyTable copyTabl = new CopyTable();

		try {

			copyTabl = copyTableRepo.findByCopyNameAndDelStatus(tbl_name, 1);
			if (copyTabl != null) {
				String[] convertedRankArray = copyTabl.getFieldsToShow().split(",");

				for (String number : convertedRankArray) {
					list.add(number.trim());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = { "/insertRec" }, method = RequestMethod.POST)
	public @ResponseBody Info insertRec(@RequestParam String tbl_name, @RequestParam List<Integer> primaryIds,
			@RequestParam int compId, @RequestParam int userId, @RequestParam String curDateTime) {
		System.err.println("hii");
		Info res = new Info();
		try {

			if (tbl_name.equals("m_category")) {
				System.err.println("hiicat");
				List<Category> catList = new ArrayList<Category>();
				List<Category> savecatList = new ArrayList<Category>();

				catList = catRepo.findByDelStatusAndCatIdIn(1, primaryIds);

				for (int i = 0; i < catList.size(); i++) {
					Category catm = catList.get(i);
					Category cat = new Category();
					cat.setAllowToCopy(0);
					cat.setCatDesc(catm.getCatDesc());
					cat.setCatName(catm.getCatName());
					cat.setCatPrefix(catm.getCatPrefix());
					cat.setIsParent(catm.getIsParent());
					cat.setCompanyId(compId);
					cat.setImageName(catm.getImageName());
					cat.setIsActive(catm.getIsActive());
					cat.setSortNo(0);
					cat.setDelStatus(1);
					cat.setExInt1(0);
					cat.setExInt2(0);
					cat.setExInt3(0);
					cat.setExVar1("NA");
					cat.setExVar2("NA");
					cat.setExVar3("NA");
					savecatList.add(cat);
				}

				List<Category> val = catRepo.saveAll(savecatList);

				if (val != null) {
					res.setError(false);
					res.setMessage("Copied Successfully");
				} else {
					res.setError(true);
					res.setMessage("Failed to Copy");
				}

			} else if (tbl_name.equals("m_uom")) {
				List<Uom> list = new ArrayList<>();
				List<Uom> list1 = new ArrayList<>();
				list = uomRepo.findByDelStatusAndUomIdIn(1, primaryIds);
				for (int i = 0; i < list.size(); i++) {

					Uom um = list.get(i);

					Uom uom = new Uom();
					uom.setCompanyId(compId);
					uom.setDelStatus(1);
					uom.setExInt1(0);
					uom.setExInt2(0);
					uom.setExInt3(0);
					uom.setExVar1("NA");
					uom.setExVar2("NA");
					uom.setExVar3("NA");
					uom.setExVar4("NA");
					uom.setIsParent(um.getIsParent());
					uom.setAllowToCopy(0);
					uom.setIsActive(um.getIsActive());
					uom.setSortNo(um.getSortNo());
					uom.setUomDesc(um.getUomDesc());
					uom.setUomName(um.getUomName());
					uom.setUomShowName(um.getUomShowName());

					list1.add(uom);

				}
				List<Uom> val = uomRepo.saveAll(list1);


				if (val != null) {
					res.setError(false);
					res.setMessage("Copied Successfully");
				} else {
					res.setError(true);
					res.setMessage("Failed to Copy");
				}

			} else {
				res.setError(true);
				res.setMessage("Failed to Copy");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
