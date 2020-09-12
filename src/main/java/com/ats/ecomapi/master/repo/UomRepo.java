// Created By :- Mahendra Singh
// Created On :- 12-09-2020
// Modified By :- NA
// Modified On :- NA
// For :- UOM Module
package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ecomapi.master.model.Uom;

@Repository
public interface UomRepo extends JpaRepository<Uom, Integer> {

	List<Uom> findByDelStatusAndCompanyIdOrderByUomIdDesc(int del, int compId);
	
	List<Uom> findByDelStatusAndCompanyIdAndIsActiveOrderByUomIdDesc(int del, int compId, int isActive);
	
	Uom findByUomId(int uomId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_uom` SET del_status=0 WHERE uom_id=:uomId",nativeQuery=true)
	public int deleteUom(@Param("uomId") int uomId);
}
