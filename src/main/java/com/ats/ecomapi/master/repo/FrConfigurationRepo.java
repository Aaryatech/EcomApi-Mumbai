package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.FrConfiguration;

public interface FrConfigurationRepo extends JpaRepository<FrConfiguration, Integer> {

	
	

	@Transactional
	@Modifying
	@Query(value="Delete FROM  m_fr_configration Where  m_fr_configration.frachase_config_id IN (:configIds)",nativeQuery=true)
	int delFrConfig(@Param("configIds") List<Integer> configIds);

}
