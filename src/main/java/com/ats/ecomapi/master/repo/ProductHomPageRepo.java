package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.ProductHomPage;

public interface ProductHomPageRepo extends JpaRepository<ProductHomPage, Integer> {

	List<ProductHomPage> findByCompanyIdAndDelStatusAndIsActive(int compId, int del, int isActive);
	
	List<ProductHomPage> findByCompanyIdAndDelStatus(int compId, int del);
	
	@Query(value="SELECT\n" + 
			"    head.home_page_status_id,\n" + 
			"    head.company_id,\n" + 
			"    head.status_id,\n" + 
			"    head.is_active,\n" + 
			"    head.sort_no,\n" + 
			"    head.del_status,\n" + 
			"    head.ex_int1,\n" + 
			"    head.ex_int2,\n" + 
			"    head.ex_var1,\n" + 
			"    head.product_id,\n" + 
			"    f.filter_name AS ex_var2\n" + 
			"FROM\n" + 
			"    home_page_status_header head,\n" + 
			"    m_filter f\n" + 
			"WHERE\n" + 
			"    head.status_id=f.filter_id AND\n" + 
			"    head.del_status=1 AND\n" +  
			"    f.del_status=1 AND\n" + 
			"    f.is_active=1 AND\n" + 
			"    head.home_page_status_id=:homePageStatusId\n" + 
			"ORDER BY head.home_page_status_id DESC", nativeQuery=true)
	ProductHomPage findByHomePageStatusId(@Param("homePageStatusId") int homePageStatusId);
	
	@Query(value="SELECT\n" + 
			"    head.home_page_status_id,\n" + 
			"    head.company_id,\n" + 
			"    head.status_id,\n" + 
			"    head.is_active,\n" + 
			"    head.sort_no,\n" + 
			"    head.del_status,\n" + 
			"    head.ex_int1,\n" + 
			"    head.ex_int2,\n" + 
			"    head.ex_var1,\n" + 
			"    head.product_id,\n" + 
			"    f.filter_name AS ex_var2\n" + 
			"FROM\n" + 
			"    home_page_status_header head,\n" + 
			"    m_filter f\n" + 
			"WHERE\n" + 
			"    head.status_id=f.filter_id AND\n" + 
			"    head.del_status=1 AND\n" + 			 
			"    f.del_status=1 AND\n" + 
			"    f.is_active=1 AND\n" + 
			"    head.company_id=:compId\n" + 
			"ORDER BY head.home_page_status_id DESC", nativeQuery=true)
	List<ProductHomPage> getHomePageProductConfigList(@Param("compId") int compId);

	@Transactional
	@Modifying
	@Query(value="UPDATE `home_page_status_header` SET sort_no=:sortNo, is_active=:isActve WHERE home_page_status_id=:configStatusId", nativeQuery=true)
	int updateSortNo(@Param("configStatusId") int configStatusId, @Param("sortNo") int sortNo, @Param("isActve") int isActve);
	
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM `home_page_status_header` WHERE home_page_status_id=:id", nativeQuery=true)
	int deleteHomePageConfig(@Param("id") int id);
	
	
}
