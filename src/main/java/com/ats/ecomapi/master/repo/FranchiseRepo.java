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
	
	
	@Query(value="SELECT\n" + 
			"    m_franchise.*\n" + 
			"FROM\n" + 
			"    m_franchise\n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id NOT IN(\n" + 
			"    SELECT DISTINCT\n" + 
			"        m_fr_configration.fr_id\n" + 
			"    FROM\n" + 
			"        m_fr_configration,\n" + 
			"        m_franchise,\n" + 
			"        tn_item_config_header\n" + 
			"    WHERE\n" + 
			"        m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND tn_item_config_header.cat_id =:catId AND m_franchise.fr_id = m_franchise.fr_id\n" + 
			") AND m_franchise.company_id =:compId AND m_franchise.del_status = 1",nativeQuery=true)
	public List<Franchise> getFranchiseToConfig(@Param("compId") int compId, @Param("catId") int catId);
}
