package com.ats.ecomapi.cms.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.cms.model.TermsAndCondtn;

public interface TermsAndCondtnRepo extends JpaRepository<TermsAndCondtn, Integer> {

	List<TermsAndCondtn> findByCompanyIdAndDelStatusOrderByTermsIdDesc(int companyId, int del);
	
	TermsAndCondtn findByTermsId(int termId);

	@Transactional
	@Modifying
	@Query(value="UPDATE `terms_and_condtn` SET del_status=0 WHERE terms_id=:termsId", nativeQuery=true)
	int deleteTermsAndCondition(@Param("termsId") int termsId);
}
