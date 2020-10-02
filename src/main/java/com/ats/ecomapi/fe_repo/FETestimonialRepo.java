package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.FETestimonial;

public interface FETestimonialRepo extends JpaRepository<FETestimonial, Integer>{
	
	@Query(value = " SELECT UUID() as uuid, home_page_testimonials.testimonials_id,home_page_testimonials.caption_name, " + 
			" home_page_testimonials.images,home_page_testimonials.messages,home_page_testimonials.name,home_page_testimonials.fr_ids, " + 
			" mn_designation.designation " + 
			" FROM mn_designation,home_page_testimonials " + 
			" WHERE home_page_testimonials.del_status=1 AND  home_page_testimonials.is_active=1 AND home_page_testimonials.designation=mn_designation.designation_id  " + 
			" and home_page_testimonials.company_id=:companyId ORDER by home_page_testimonials.sort_no DESC " + 
			" ", nativeQuery = true)
	List<FETestimonial> getFETestimonialListByCompId(@Param("companyId") int companyId);
	
}
