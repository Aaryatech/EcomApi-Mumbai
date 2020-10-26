package com.ats.ecomapi.master.repo;

import java.util.List;

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
	@Query(value="Delete from  m_product_configuration  WHERE related_product_id=:relatedProductId AND updt_dttime=:dateTime AND maker_user_id=:userId",nativeQuery=true)
 	int deleteConfig(@Param("relatedProductId") int relatedProductId,@Param("userId") int userId,@Param("dateTime") String dateTime);
	
	@Query(value="SELECT  GROUP_CONCAT(m_product_configuration.secondary_item_id ) as con FROM m_product_configuration WHERE m_product_configuration.primary_item_id "
	+" IN (:productIds) and is_active=1 AND del_status=1 ",nativeQuery=true)
	String getProdIdsbyPrimaryProdIdsIn(@Param("productIds") List<Integer> productIds);

}
