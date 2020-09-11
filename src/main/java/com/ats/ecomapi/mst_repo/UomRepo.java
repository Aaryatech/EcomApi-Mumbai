package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.Uom;


public interface UomRepo extends JpaRepository<Uom, Integer> {

	List<Uom> findByDelStatusAndCompanyIdOrderByUomIdDesc(int del, int compId);
	
	Uom findByUomId(int uomId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_uom` SET del_status=1 WHERE uom_id=:uomId",nativeQuery=true)
	public int deleteUom(@Param("uomId") int uomId);
}
