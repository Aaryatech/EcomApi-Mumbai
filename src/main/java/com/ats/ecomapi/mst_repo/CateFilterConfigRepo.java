package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.CateFilterConfig;

public interface CateFilterConfigRepo extends JpaRepository<CateFilterConfig, Integer> {

	CateFilterConfig findByCompIdAndCateId(int compId, int cateId);

	@Transactional
	@Modifying
	@Query(value="UPDATE `m_cate_filter_config` SET `filter_ids` = :filterIds, comp_id=:compId, maker_date_time=:upDateTime  WHERE cate_id=:cateId", nativeQuery=true)
	int updteFilterAndCate(@Param("cateId") int cateId, @Param("filterIds") String filterIds, @Param("upDateTime") String upDateTime, 
			@Param("compId") int compId);
	
	//Sachin 26-11-2020 for FrontEnd
	
	@Query(value=" SELECT cate_filter_config_id, cate_id, filter_ids, m_cate_filter_config.del_status, m_cate_filter_config.is_active, m_cate_filter_config.ex_int1, "
			+ " m_cate_filter_config.ex_int2, m_cate_filter_config.ex_var1,  " + 
			" m_category.cat_name as ex_var2, comp_id, maker_date_time FROM m_cate_filter_config,m_category WHERE "
			+ " m_category.cat_id=m_cate_filter_config.cate_id " + 
			" and m_cate_filter_config.del_status=1 and m_cate_filter_config.is_active=1 and m_cate_filter_config.comp_id=:compId ",nativeQuery=true)
	List<CateFilterConfig> getCompanyCateFilterConf(@Param("compId") int compId);

}
