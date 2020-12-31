package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.SpDayHomePageExlPdf;

public interface SpDayHomePageExlPdfRepo extends JpaRepository<SpDayHomePageExlPdf, Integer> {

	@Query(value=" SELECT t1.*,\n" + 
			"		GROUP_CONCAT(t2.filter_name) as filter_name\n" + 
			"FROM\n" + 
			"(select\n" + 
			"        sp.sp_day_id,\n" + 
			"        sp.caption_on_product_page,\n" + 
			"        sp.company_id,\n" + 
			"        sp.del_status,\n" + 
			"        sp.ex_int1,\n" + 
			"        sp.ex_int2,\n" + 
			"        sp.ex_var1,\n" + 
			"        sp.ex_var2,\n" + 
			"        sp.fr_ids,\n" + 
			"        sp.from_date,\n" + 
			"        sp.from_time,\n" + 
			"        sp.insert_date_time,\n" + 
			"        sp.insert_user_id,\n" + 
			"        sp.is_active,\n" + 
			"        sp.sort_no,\n" + 
			"        sp.spday_caption_home_page,\n" + 
			"        sp.spday_caption_image_home_page,\n" + 
			"        sp.spday_name,\n" + 
			"        sp.tag_ids,\n" + 
			"        sp.to_date,\n" + 
			"        sp.to_time,\n" + 
			"        sp.update_date_time,\n" + 
			"        sp.update_user_id,\n" + 
			"        GROUP_CONCAT(f.fr_name) AS franchises\n" + 
			"    from\n" + 
			"        sp_day_home_page sp,\n" + 
			"        m_franchise f\n" + 
			"    where\n" + 
			"        sp.company_id=:compId\n" + 
			"        and sp.del_status=1\n" + 
			"         AND FIND_IN_SET(\n" + 
			"        f.fr_id,\n" + 
			"        sp.fr_ids\n" + 
			"    )\n" + 
			"    GROUP BY sp.sp_day_id\n" + 
			"    order by\n" + 
			"        sp.sp_day_id desc)t1\n" + 
			"LEFT JOIN\n" + 
			"(SELECT * FROM m_filter mf  WHERE mf.del_status=1) t2\n" + 
			"ON FIND_IN_SET(t2.filter_id,t1.tag_ids)\n" + 
			"GROUP BY\n" + 
			"      sp_day_id\n" + 
			"ORDER BY\n" + 
			"    sp_day_id\n" + 
			"",nativeQuery=true)
	List<SpDayHomePageExlPdf> getAllSpDayPageForExlPdf(@Param("compId") int compId);
	
}
