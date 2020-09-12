// Created By :- Mahendra Singh
// Created On :- 12-09-2020
// Modified By :- NA
// Modified On :- NA
// For :- Tax Module
package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ecomapi.master.model.Tax;
@Repository
public interface TaxRepo extends JpaRepository<Tax, Integer> {
	
	List<Tax> findByDelStatusAndCompanyIdOrderByTaxIdDesc(int del, int compId);
	
	List<Tax> findByDelStatusAndCompanyIdAndIsActiveOrderByTaxIdDesc(int del, int compId, int isActive);
 
	Tax findByTaxId(int taxId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_tax` SET del_status=0 WHERE tax_id=:taxId",nativeQuery=true)
	int deleteTax(@Param("taxId") int taxId);
}
