package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.FEProdDetail;

public interface FEProdDetailRepo extends JpaRepository<FEProdDetail, Integer> {

	@Query(value = " SELECT UUID() as conf_detail_uuid, tn_item_config_detail.config_detail_id,tn_item_config_detail.product_id,\n"
			+ " tn_item_config_detail.flavor_id,tn_item_config_detail.is_veg,tn_item_config_detail.rate_setting_type,tn_item_config_detail.qty,\n"
			+ " CASE m_fr_configration.display_rate\n" + "WHEN 1 THEN tn_item_config_detail.rate_amt\n"
			+ " WHEN 2 THEN tn_item_config_detail.mrp_amt\n" + "WHEN 3 THEN tn_item_config_detail.sp_rate_amt1\n"
			+ " WHEN 4 THEN tn_item_config_detail.sp_rate_amt2\n" + "WHEN 5 THEN tn_item_config_detail.sp_rate_amt3\n"
			+ " WHEN 6 THEN tn_item_config_detail.sp_rate_amt4\n" + "END AS display_rate,\n" + "\n"
			+ " CASE m_fr_configration.actual_rate WHEN 1 THEN tn_item_config_detail.rate_amt\n"
			+ " WHEN 2 THEN tn_item_config_detail.mrp_amt\n" + "WHEN 3 THEN tn_item_config_detail.sp_rate_amt1\n"
			+ " WHEN 4 THEN tn_item_config_detail.sp_rate_amt2\n" + "WHEN 5 THEN tn_item_config_detail.sp_rate_amt3\n"
			+ " WHEN 6 THEN tn_item_config_detail.sp_rate_amt4\n" + "END AS actual_rate\n" + "\n"
			+ " FROM tn_item_config_detail,m_fr_configration\n"
			+ " WHERE tn_item_config_detail.config_header_id in(:configHeaderId) and tn_item_config_detail.product_id in (:prodIdList)\n"
			+ " and m_fr_configration.config_header_id=tn_item_config_detail.config_header_id and m_fr_configration.fr_id=:frId order by product_id ASC "
			+ " ", nativeQuery = true)
	List<FEProdDetail> getFEProdDetailByConfHeadProdIdFrId(@Param("configHeaderId") int configHeaderId,@Param("prodIdList") int prodIdList, @Param("frId") int frId);

}
