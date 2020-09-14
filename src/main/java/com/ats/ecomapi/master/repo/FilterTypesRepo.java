package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.FilterTypes;

public interface FilterTypesRepo extends JpaRepository<FilterTypes, Integer> {

	List<FilterTypes> findByDelStatusAndIsActiveAndCompanyIdOrderByFilterTypeIdDesc(int del, int compId, int isActive);
	
	List<FilterTypes> findByDelStatusAndCompanyIdOrderByFilterTypeIdDesc(int del, int compId);
	
	FilterTypes findByFilterTypeId(int filterTypeId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_filter_type` SET del_status=0 WHERE filter_type_id=:filterTypeId",nativeQuery=true)
	int deleteFilterType(@Param("filterTypeId") int filterTypeId);
}
