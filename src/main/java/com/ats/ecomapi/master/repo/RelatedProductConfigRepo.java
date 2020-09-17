package com.ats.ecomapi.master.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.RelatedProductConfig;

public interface RelatedProductConfigRepo extends JpaRepository<RelatedProductConfig, Integer> {

	RelatedProductConfig findByPrimaryItemId(int primaryItemId);

	
	
	@Transactional
	@Modifying
	@Query(value="Delete from  m_product_configuration  WHERE related_product_id=:relatedProductId",nativeQuery=true)
 	int deleteConfig(@Param("relatedProductId") int relatedProductId);
	
	
	

}
