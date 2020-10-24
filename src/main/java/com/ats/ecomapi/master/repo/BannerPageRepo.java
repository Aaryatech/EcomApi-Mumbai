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
	@Query(value="UPDATE\n" + 
			"        `banner_home_page` \n" + 
			"    SET\n" + 
			"        del_status=0,\n" + 
			"        update_date_time=:dateTime, \n" + 
			"        update_user_id=:userId\n" + 
			"    WHERE\n" + 
			"        banner_id=:bannerId \n" + 
			"       ",nativeQuery=true)
	int deleteBanner(@Param("bannerId") int bannerId,@Param("userId") int userId,@Param("dateTime") String dateTime);
//UPDATE `banner_home_page` SET del_status=0 WHERE banner_id=:bannerId AND update_date_time=:dateTime AND update_user_id=:userId
	
	
	List<BannerPage> findByCompIdAndDelStatusOrderByBannerIdDesc(int compId, int del);
 
}
