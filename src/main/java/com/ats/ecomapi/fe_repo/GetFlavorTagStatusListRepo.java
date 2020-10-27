package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.GetFlavorTagStatusList;

public interface GetFlavorTagStatusListRepo extends JpaRepository<GetFlavorTagStatusList, Integer> {
	
	@Query(value = " SELECT m_filter.filter_id,m_filter.filter_name,m_filter.admin_name,m_filter.filter_type_id,m_filter.used_for_filter, " + 
			" m_filter.cost_affect,m_filter.used_for_description,m_filter.company_id,m_filter.sort_no " + 
			" FROM m_filter  " + 
			" WHERE m_filter.filter_type_id IN (4,7,5,12) and m_filter.del_status=1 AND m_filter.is_active=1 AND m_filter.company_id=:companyId " + 
			" ORDER  BY m_filter.sort_no ,m_filter.filter_type_id ", nativeQuery = true)
	List<GetFlavorTagStatusList> getFlavorTagStatusListByCompId(@Param("companyId") int companyId);
	
}
