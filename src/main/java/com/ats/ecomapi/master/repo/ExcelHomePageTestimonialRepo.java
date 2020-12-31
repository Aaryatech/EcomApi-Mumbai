package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetHomePageTestimonial;
import com.ats.ecomapi.master.model.HomePageTestimonial;

public interface ExcelHomePageTestimonialRepo extends JpaRepository<GetHomePageTestimonial, Integer> {

	@Query(value=" select\n" + 
			"        h.testimonials_id,\n" + 
			"        h.caption_name,\n" + 
			"        h.company_id,\n" + 
			"        h.del_status,\n" + 
			"        h.designation,\n" + 
			"        h.ex_int1,\n" + 
			"        h.ex_int2,\n" + 
			"        h.ex_var1,\n" + 
			"        h.ex_var2,\n" + 
			"        h.fr_ids,\n" + 
			"        h.images,\n" + 
			"        h.insert_date_time,\n" + 
			"        h.insert_user_id,\n" + 
			"        h.is_active,\n" + 
			"        h.messages,\n" + 
			"        h.name,\n" + 
			"        h.sort_no,\n" + 
			"        h.update_date_time,\n" + 
			"        h.update_user_id,\n" + 
			"        d.designation as desi_name,\n" + 
			"        GROUP_CONCAT(f.fr_name) AS franchise\n" + 
			"    from\n" + 
			"        home_page_testimonials h,\n" + 
			"        m_franchise f,\n" + 
			"        mn_designation d\n" + 
			"    where\n" + 
			"        h.company_id=:compId\n" + 
			"        and h.del_status=1\n" + 
			"         AND FIND_IN_SET(\n" + 
			"        f.fr_id,\n" + 
			"        fr_ids\n" + 
			"    ) AND d.designation_id=h.designation\n" + 
			"    GROUP BY\n" + 
			"    	h.testimonials_id\n" + 
			"    order by\n" + 
			"        h.testimonials_id desc",nativeQuery=true)
	List<GetHomePageTestimonial> getHomePgTestimnlExlPdf(@Param("compId") int compId);
}
