package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.master.model.CopyTable;
import com.ats.ecomapi.master.model.GetTableFields;
import com.ats.ecomapi.master.repo.CopyTableRepo;
import com.ats.ecomapi.master.repo.GetTableFieldsRepo;
import com.ats.ecomapi.master.repo.GetTableNamesRepo;
import com.ats.ecomapi.mst_model.GetTableNames;
import com.ats.ecomapi.mst_model.User;
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

}
