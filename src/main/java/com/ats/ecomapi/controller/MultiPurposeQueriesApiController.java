package com.ats.ecomapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.mst_model.Info;




@RestController
public class MultiPurposeQueriesApiController {
	
	
	
	
	@Autowired
	JdbcTemplate jdbcTemp;
	
	String queryString=""; 
	
	
	@RequestMapping(value="/multiDelete",method=RequestMethod.POST)
	public @ResponseBody Info multiDelete(@RequestParam String tableName,
										@RequestParam String columnName,
										@RequestParam int value,
										@RequestParam String id,
										@RequestParam String comparisionId) {
	Info info=new Info();
		queryString="UPDATE"+" "+tableName+" "+"SET"+" "+columnName+"="+value+" "+"WHERE"+" "+id+" "+"IN"+" "+"("+comparisionId+")";
		System.err.println(queryString);
		int[]  a = jdbcTemp.batchUpdate(queryString);
		System.err.println(a.length);
		if(a.length>0) {
			info.setError(false);
			info.setMsg("Deleted");
		}else {
			info.setError(true);
			info.setMsg("Unable To Delete");
		}
		return info;
	}
	
	
 
}
