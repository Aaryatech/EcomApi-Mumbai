package com.ats.ecomapi.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.master.model.CopyTable;

public interface CopyTableRepo extends JpaRepository<CopyTable, Integer> {

	CopyTable findByCopyNameAndDelStatus(String tbl_name, int i);
	
	
	

}
