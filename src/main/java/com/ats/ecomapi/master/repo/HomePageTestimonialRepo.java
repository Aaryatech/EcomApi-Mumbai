package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.HomePageTestimonial;

public interface HomePageTestimonialRepo extends JpaRepository<HomePageTestimonial, Integer> {

	List<HomePageTestimonial> findByCompanyIdAndDelStatusOrderByTestimonialsIdDesc(int compId, int del);
	
	HomePageTestimonial findByTestimonialsId(int testimonialId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `home_page_testimonials` SET del_status=0 WHERE testimonials_id=:testimonialId", nativeQuery=true)
	int deleteTestimonial(@Param("testimonialId") int testimonialId);
}
