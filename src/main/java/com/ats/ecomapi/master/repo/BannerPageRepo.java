package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.BannerPage;

public interface BannerPageRepo extends JpaRepository<BannerPage, Integer>{

	BannerPage findByBannerId(int bannerId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `banner_home_page` SET del_status=0 WHERE banner_id=:bannerId",nativeQuery=true)
	int deleteBanner(@Param("bannerId") int bannerId);

	List<BannerPage> findByCompIdAndDelStatus(int compId, int i);
 
}
