package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.CompanyTestomonials;



//Akhilesh 2020-12-17 
public interface CompanyTestomonialsRepo extends JpaRepository<CompanyTestomonials, Integer> {
	
	@Query(value="SELECT * FROM company_testimonials WHERE is_active=1 AND del_status=1 ",nativeQuery=true)
	List<CompanyTestomonials> getCompanyTestomonialsList();
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE company_testimonials SET del_status=0 WHERE id=:id ",nativeQuery=true)
	int deleteCompanyTestimonial(@Param("id") int id);
	

}
