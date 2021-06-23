package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.HomePageStatusHead;
//SAC 23-06-2021
public interface HomePageStatusHeadRepo extends JpaRepository<HomePageStatusHead, Integer> {
	
	@Query(value=" SELECT UUID as home_page_uuid " + 
			"    head.home_page_status_id, " + 
			"    head.company_id, " + 
			"    head.status_id, " + 
			"    head.ex_var1, head.ex_var3, " + 
			" FROM " + 
			"    home_page_status_header head " + 
			" WHERE " + 
			"    head.del_status=1 AND " + 			 
			"    head.company_id=:compId " + 
			" ", nativeQuery=true)
	List<HomePageStatusHead> getHomePageProductConfigList(@Param("compId") int compId);

}
