package com.ats.ecomapi.master.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ecomapi.master.model.ProductHomePageDetail;
@Repository
public interface ProductHomePageDetailRepo extends JpaRepository<ProductHomePageDetail, Integer> {

	@Transactional
	@Modifying
	@Query(value="DELETE FROM `home_page_status_detail` WHERE home_page_status_id=:configStatusId",nativeQuery=true)
	int deleteHomepageStatusDtl(@Param("configStatusId") int connfigStatusId);
	
}
