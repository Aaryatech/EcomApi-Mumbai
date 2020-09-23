package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Designation;


public interface DesignationRepo extends JpaRepository<Designation, Integer> {
	
	List<Designation> findByDelStatusOrderByDesignationIdDesc(int del);

	List<Designation> findByDelStatusAndExInt1OrderByDesignationIdDesc(int i, int compId);
 
	Designation findBydesignationIdAndExInt1(int desigId, int compId);
 
	 @Transactional
	 @Modifying
	 @Query(value="UPDATE `mn_designation` SET del_status=0 WHERE designation_id=:desigId",nativeQuery=true)
	 public int deleteDesignation(@Param("desigId") int desigId);
}
