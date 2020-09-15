package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Franchise;

public interface FranchiseRepo extends JpaRepository<Franchise, Integer> {

	List<Franchise> findByCompanyIdAndDelStatusOrderByFrIdDesc(int conpId, int del);
	
	Franchise findByFrId(int frId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_franchise` SET del_status=0 WHERE fr_id=:frId", nativeQuery=true)
	int deleteFanchise(@Param("frId") int frId);
	
}
