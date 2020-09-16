package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Franchise;
import java.lang.String;

public interface FranchiseRepo extends JpaRepository<Franchise, Integer> {

	List<Franchise> findByCompanyIdAndDelStatusOrderByFrIdDesc(int compId, int del);
	
	Franchise findByFrId(int frId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_franchise` SET del_status=0 WHERE fr_id=:frId", nativeQuery=true)
	int deleteFranchise(@Param("frId") int frId);
	
	
	Franchise findByFrContactNoAndDelStatus(String mobNo, int del);
	Franchise findByFrContactNoAndDelStatusAndFrIdNot(String mobNo, int del,  int frId);
	
	Franchise findByFrEmailIdAndDelStatus(String emailId, int del); 
	Franchise findByFrEmailIdAndDelStatusAndFrIdNot(String emailId, int del,  int frId);
	
	@Query(value="SELECT COUNT(fr_id) FROM `m_franchise` WHERE `fr_code` LIKE %:coPrefix% AND company_id=:compId",nativeQuery=true)
	public int getCompCountByPrefix(@Param("compId") int compId, @Param("coPrefix") String coPrefix);
}
