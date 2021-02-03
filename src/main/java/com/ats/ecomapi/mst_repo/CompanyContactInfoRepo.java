package com.ats.ecomapi.mst_repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.CompanyContactInfo;

public interface CompanyContactInfoRepo extends JpaRepository<CompanyContactInfo, Integer> {

	@Query(value="SELECT\n" + 
			"    company_id,\n" + 
			"    company_name,\n" + 
			"    comp_address,\n" + 
			"    comp_city,\n" + 
			"    comp_contact_no,\n" + 
			"    comp_email_address,\n" + 
			"    comp_website,\n" + 
			"    comp_gst_no\n" + 
			"FROM\n" + 
			"    `m_company`\n" + 
			"WHERE\n" + 
			"    company_id = :compId",nativeQuery=true)
	CompanyContactInfo getCompanyInfoByCompanyId(@Param("compId") int compId);
	
}
