package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ecomapi.master.model.SpDayHomePage;

@Repository
public interface SpDayHomePageRepo extends JpaRepository<SpDayHomePage, Integer> {

	List<SpDayHomePage> findByCompanyIdAndDelStatusOrderBySpDayIdDesc(int compId, int del);
	
	SpDayHomePage findBySpDayId(int spDayId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE sp_day_home_page SET del_status=0 WHERE sp_day_id=:spDayId",nativeQuery=true)
	int deleteSpDayId(@Param("spDayId") int spDayId);
}
	
//	SELECT
//	t1.*,
//	t2.fr_name
//	FROM
//	(SELECT
//	    sp.sp_day_id,
//	    sp.spday_caption_home_page,
//	    sp.spday_caption_image_home_page,
//	    sp.is_active,
//	    sp.from_date,
//	    sp.to_date,
//	    sp.from_time,
//	    sp.to_time,
//	    sp.fr_ids,
//	    sp.tag_ids,
//	    sp.caption_on_product_page
//	    
//	FROM
//	    sp_day_home_page sp
//	WHERE
//	    sp.company_id=1 AND    
//	    sp.del_status=1) t1
//	    LEFT JOIN
//	    (SELECT
//		sp.sp_day_id,
//	    GROUP_CONCAT(fr.fr_name) AS fr_name
//	FROM
//	    m_franchise fr
//	    
//	    INNER JOIN sp_day_home_page sp ON FIND_IN_SET(fr.fr_id,sp.fr_ids )
//	WHERE
//	    sp.del_status=1
//	 GROUP BY sp.sp_day_id)t2
//	 ON t1.sp_day_id=t2.sp_day_id
    
    
    