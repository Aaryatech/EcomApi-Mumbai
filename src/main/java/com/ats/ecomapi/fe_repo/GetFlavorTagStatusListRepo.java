package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.GetFlavorTagStatusList;

public interface GetFlavorTagStatusListRepo extends JpaRepository<GetFlavorTagStatusList, Integer> {
	
	@Query(value = " SELECT m_filter.filter_id,m_filter.filter_name,m_filter.admin_name,m_filter.filter_type_id,m_filter.used_for_filter, " + 
			" m_filter.cost_affect,m_filter.used_for_description,m_filter.company_id,m_filter.sort_no " + 
			" , REPLACE(m_filter.filter_name, ' ', '-') as filter_name_disp FROM m_filter  " + 
			" WHERE m_filter.filter_type_id IN (4,7,5,12,1) and m_filter.del_status=1 AND m_filter.is_active=1 AND m_filter.company_id=:companyId " + 
			" ORDER  BY m_filter.sort_no ,m_filter.filter_type_id ", nativeQuery = true)
	List<GetFlavorTagStatusList> getFlavorTagStatusListByCompId(@Param("companyId") int companyId);
	
	
	@Query(value = " SELECT home_page_status_header.status_id FROM home_page_status_header,m_filter WHERE \n" + 
			"	home_page_status_header.company_id=:companyId AND home_page_status_header.is_active=1 AND \n" + 
			"	home_page_status_header.del_status=1 AND home_page_status_header.status_id=m_filter.filter_id and \n" + 
			"	m_filter.filter_type_id=5", nativeQuery = true)
	List<Integer> getHomePageStatusIds(@Param("companyId") int companyId);
	
			
}
