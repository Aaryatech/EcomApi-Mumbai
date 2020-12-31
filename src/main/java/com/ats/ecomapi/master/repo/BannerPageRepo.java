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
 
	@Query(value="SELECT t1.*,\n" + 
			"		GROUP_CONCAT(t2.filter_name) as ex_var3\n" + 
			"FROM\n" + 
			"(SELECT \n" + 
			"    banner_home_page.banner_id,\n" + 
			"    banner_home_page.banner_event_name,\n" + 
			"    banner_home_page.banner_image,\n" + 
			"    banner_home_page.caption_onproduct_page,\n" + 
			"    banner_home_page.comp_id,\n" + 
			"    banner_home_page.del_status,\n" + 
			"    banner_home_page.ex_int1,\n" + 
			"    banner_home_page.ex_int2,\n" + 
			"    banner_home_page.ex_int3,\n" + 
			"    banner_home_page.ex_var1,\n" + 
			"    GROUP_CONCAT(f.fr_name) AS ex_var2,\n" + 
			"    banner_home_page.fr_ids,\n" + 
			"    banner_home_page.insert_date_time,\n" + 
			"    banner_home_page.is_active,\n" + 
			"    banner_home_page.sort_no,\n" + 
			"    banner_home_page.tag_ids,\n" + 
			"    banner_home_page.update_date_time,\n" + 
			"    banner_home_page.update_user_id, banner_home_page.insert_user_id\n" + 
			"FROM\n" + 
			"    banner_home_page,\n" + 
			"    m_franchise f\n" + 
			"WHERE\n" + 
			"    comp_id = :compId AND banner_home_page.del_status = 1 AND FIND_IN_SET(\n" + 
			"        f.fr_id,\n" + 
			"        banner_home_page.fr_ids\n" + 
			"    ) \n" + 
			"GROUP BY\n" + 
			"      banner_home_page.banner_id\n" + 
			"ORDER BY\n" + 
			"    banner_id\n" + 
			"DESC)t1\n" + 
			"LEFT JOIN\n" + 
			"(SELECT * FROM m_filter mf  WHERE mf.del_status=1) t2\n" + 
			"ON FIND_IN_SET(t2.filter_id,t1.tag_ids)\n" + 
			"GROUP BY\n" + 
			"      banner_id\n" + 
			"ORDER BY\n" + 
			"    banner_id", nativeQuery = true)
	 List<BannerPage> getBannerDetail(@Param("compId") int compId);
}
