package com.ats.ecomapi.mst_repo;

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

}
