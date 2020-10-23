package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.TempProdConfig;

public interface TempProdConfigRepo extends JpaRepository<TempProdConfig, Integer> {

	/*@Query(value = ""
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
*/
	
	    @Query(value = ""
			+ " SELECT\n" + 
			"    UUId() as uuid ,\n" + 
			"    tn_item_config_detail.config_detail_id,\n" + 
			"    tn_item_config_detail.config_header_id,\n" + 
			"    tn_item_config_detail.product_id,\n" + 
			"    tn_item_config_detail.flavor_id,\n" + 
			"    tn_item_config_detail.is_veg as veg_type,\n" + 
			"    tn_item_config_detail.rate_setting_type as rate_seting_type,\n" + 
			"    tn_item_config_detail.qty as weight,\n" + 
			"    tn_item_config_detail.rate_amt  ,\n" + 
			"    tn_item_config_detail.mrp_amt ,\n" + 
			"    tn_item_config_detail.sp_rate_amt1,\n" + 
			"    tn_item_config_detail.sp_rate_amt2,\n" + 
			"    tn_item_config_detail.sp_rate_amt3,\n" + 
			"    tn_item_config_detail.sp_rate_amt4 ,\n" + 
			"    tn_item_config_detail.updt_dttime as cur_time_stamp,\n" + 
			"    m_product.product_name,\n" + 
			"    m_product.prod_cat_id as cat_id,\n" + 
			"    tn_item_config_detail.maker_user_id,\n" + 
			"    tn_item_config_detail.updt_dttime,\n" + 
			"    COALESCE(( SELECT m_filter.filter_name FROM m_filter WHERE m_filter.filter_id=tn_item_config_detail.flavor_id),'Flav-NA') as flavor_name,\n" + 
			"     COALESCE(( SELECT m_filter.filter_name FROM m_filter WHERE m_filter.filter_id=tn_item_config_detail.is_veg),'VNV-NA') as veg_non_veg_name,\n" + 
			"     COALESCE(( SELECT m_filter.filter_name FROM m_filter WHERE m_filter.filter_id=tn_item_config_detail.ex_int1),'Shape-NA') as shape_name,\n" + 
			"  tn_item_config_detail.ex_int1 as shape_id   \n" + 
			"     \n" + 
			" FROM\n" + 
			"    tn_item_config_detail,\n" + 
			"    m_product  \n" + 
			" WHERE\n" + 
			"    tn_item_config_detail.product_id=m_product.product_id  \n" + 
			"    AND tn_item_config_detail.config_header_id=:configHeaderId and tn_item_config_detail.del_status=1 \n" + 
			" ", nativeQuery = true)

	List<TempProdConfig> getProdConfByConfHeaderId(@Param("configHeaderId") int configHeaderId);

}
