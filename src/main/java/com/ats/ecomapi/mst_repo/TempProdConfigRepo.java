package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.TempProdConfig;

public interface TempProdConfigRepo extends JpaRepository<TempProdConfig, Integer> {

	@Query(value = ""
			+ " SELECT UUId() as uuid ,tn_item_config_detail.config_detail_id,tn_item_config_detail.config_header_id,tn_item_config_detail.product_id,tn_item_config_detail.flavor_id,"
			+ "tn_item_config_detail.is_veg as veg_type, "
			+ " tn_item_config_detail.rate_setting_type as rate_seting_type,tn_item_config_detail.qty as weight, "
			+ " tn_item_config_detail.rate_amt  , tn_item_config_detail.mrp_amt , "
			+ " tn_item_config_detail.sp_rate_amt1, tn_item_config_detail.sp_rate_amt2, "
			+ " tn_item_config_detail.sp_rate_amt3, tn_item_config_detail.sp_rate_amt4 , "
			+ " m_filter.filter_name as flavor_name, tn_item_config_detail.updt_dttime as cur_time_stamp, "
			+ " m_product.product_name,m_product.prod_cat_id as cat_id, "
			+ " tn_item_config_detail.maker_user_id,tn_item_config_detail.updt_dttime "
			+ " FROM tn_item_config_detail,m_filter,m_product "
			+ " WHERE tn_item_config_detail.product_id=m_product.product_id "
			+ " AND m_filter.filter_id=tn_item_config_detail.flavor_id "
			+ "AND tn_item_config_detail.config_header_id=:configHeaderId ", nativeQuery = true)

	List<TempProdConfig> getProdConfByConfHeaderId(@Param("configHeaderId") int configHeaderId);

}
