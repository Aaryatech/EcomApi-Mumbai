package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.CompMaster;

public interface CompMasterRepo extends JpaRepository<CompMaster, Integer> {

	CompMaster findByCompanyId(int compId);
	
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_company` SET del_status=0 WHERE company_id=:compId AND maker_user_id=:userId AND updt_dttime=:dateTime ",nativeQuery=true)
	public int deleteCompany(@Param("compId") int compId,@Param("userId") int userId,@Param("dateTime") String dateTime);



	List<CompMaster> findByDelStatusOrderByCompanyIdDesc(int i);
}
