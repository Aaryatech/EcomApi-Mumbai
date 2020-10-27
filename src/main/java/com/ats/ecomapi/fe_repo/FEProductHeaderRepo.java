package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.FEProductHeader;

public interface FEProductHeaderRepo extends JpaRepository<FEProductHeader, Integer> {
	
	/*@Query(value = " SELECT " + 
			"    UUID() AS prod_uuid, m_product.product_id, m_product.product_code, m_product.product_name, m_product.prod_cat_id, m_product.prod_sub_cat_id, m_product.tax_id, m_product.min_qty, m_product.uom_id, m_product.short_name, m_product.shape_id, m_product.allow_same_day_delivery, m_product.same_day_time_allowed_slot, m_product.prod_type_id, m_product.avail_in_weights, m_product.flavour_ids, m_product.prod_status_id, m_product.book_before, "
			+ "m_product.events_ids, m_product.is_char_limit, m_product.no_of_chars_for_alpha_cake, m_product.allow_cover_photo_upload, m_product.allow_base_photo_upload, m_product.allow_special_instruction, m_product.allow_msg_on_cake, m_product.no_of_chars_on_cake, m_product.product_desc, m_product.ingerdiants, m_product.applicable_tags, m_product.company_id, m_product.prod_image_primary, m_product.product_images, m_product.is_veg, m_product.prep_time, m_product.rate_setting_type, m_product.max_wt, m_product.ex_var1, m_product.ex_var2, m_fr_configration.display_rate, m_fr_configration.actual_rate, m_fr_configration.frachase_config_id, 0 AS is_home_page_prod, tn_item_config_header.config_header_id " + 
			" FROM " + 
			"    m_product, tn_item_config_detail, tn_item_config_header, m_fr_configration " + 
			" WHERE " + 
			"    m_product.product_id = tn_item_config_detail.product_id AND tn_item_config_detail.del_status = 1 AND tn_item_config_detail.is_active = 1 AND tn_item_config_detail.config_header_id = tn_item_config_header.config_header_id AND tn_item_config_header.del_status = 1 AND tn_item_config_header.is_active = 1 AND tn_item_config_header.config_header_id = m_fr_configration.config_header_id AND m_fr_configration.fr_id =:frId" + 
			" GROUP BY " + 
			"    m_product.product_id ORDER BY m_product.product_id ASC " + 
			" ", nativeQuery = true)
	List<FEProductHeader> getFEProductHeaderByFrId(@Param("frId") int frId);
	*/
	@Query(value = " SELECT home_page_status_detail.product_id FROM home_page_status_detail,home_page_status_header,m_filter " + 
			" WHERE m_filter.filter_type_id=5 and m_filter.filter_id=home_page_status_header.status_id AND  " + 
			" home_page_status_header.home_page_status_id=home_page_status_detail.home_page_status_id and  " + 
			" m_filter.company_id=home_page_status_header.company_id and home_page_status_header.del_status=1 and "
			+ " home_page_status_header.is_active=1  and home_page_status_header.company_id=:companyId " + 
			" ", nativeQuery = true)
	List<Integer> getHomePageConfiguredProdIdsList(@Param("companyId") int companyId);

	
	@Query(value = " SELECT b.*,  " + 
			" " + 
			"	CASE WHEN b.allow_same_day_delivery=1 THEN COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.same_day_time_allowed_slot) and m_filter.filter_type_id=2),0) else 'NA' end  AS same_day_time_slot_names,\n" + 
			" " + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.events_ids) and m_filter.filter_type_id=6),'NA')  AS event_names,\n" + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.flavour_ids) and m_filter.filter_type_id=4),'NA')  AS flavor_names,\n" + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.applicable_tags) and m_filter.filter_type_id=7),'NA')  AS appli_tag_names,\n" + 
			" " + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.shape_id) and m_filter.filter_type_id=1),'NA')  AS shape_names,\n" + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.prod_type_id) and m_filter.filter_type_id=3),'NA')  AS prod_type_name,\n" + 
			" " + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.prod_status_id) and m_filter.filter_type_id=5),'NA')  AS prod_status_name,\n" + 
			" " + 
			" " + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.topping_cream) and m_filter.filter_type_id=11),'NA')  AS topping_cream_names,\n" + 
			" " + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.layering_cream) and m_filter.filter_type_id=10),'NA')  AS layering_cream_names,\n" + 
			"	         \n" + 
			"	         COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.type_of_cream) and m_filter.filter_type_id=9),'NA')  AS cream_type_name,\n" + 
			" " + 
			"	COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.type_of_bread) and m_filter.filter_type_id=8),'NA')  AS bread_type_name,\n" +
			
			" COALESCE((SELECT GROUP_CONCAT(m_filter.admin_name) FROM m_filter WHERE FIND_IN_SET(m_filter.filter_id,b.is_veg) and m_filter.filter_type_id=12),'NA')  AS veg_nonveg_name"
			+"	     " + 
			"	          " + 
			"	FROM " + 
			"	  " + 
			"	( SELECT " + 
			"	        UUID() AS prod_uuid, " + 
			"	        m_product.product_id, " + 
			"	        m_product.product_code,\n" + 
			"	        m_product.product_name,\n" + 
			"	        m_product.prod_cat_id,\n" + 
			"	        m_product.prod_sub_cat_id," + 
			"	        m_product.tax_id, " + 
			"	        m_product.min_qty, " + 
			"	        m_product.uom_id, " + 
			"	        m_product.short_name, " + 
			"	        m_product.shape_id, " + 
			"	        m_product.allow_same_day_delivery, " + 
			"	        m_product.same_day_time_allowed_slot, " + 
			"	        m_product.prod_type_id, " + 
			"	        m_product.avail_in_weights, " + 
			"	        m_product.flavour_ids, " + 
			"	        m_product.prod_status_id, " + 
			"	        m_product.book_before, " + 
			"	        m_product.events_ids, " + 
			"	        m_product.is_char_limit, " + 
			"	        m_product.no_of_chars_for_alpha_cake, " + 
			"	        m_product.allow_cover_photo_upload, " + 
			"	        m_product.allow_base_photo_upload, " + 
			"	        m_product.allow_special_instruction, " + 
			"	        m_product.allow_msg_on_cake, " + 
			"	        m_product.no_of_chars_on_cake, " + 
			"	        m_product.product_desc, " + 
			"	        m_product.ingerdiants, " + 
			"	        m_product.applicable_tags, " + 
			"	        m_product.company_id,\n" + 
			"	        m_product.prod_image_primary, " + 
			"	        m_product.product_images, " + 
			"	        m_product.is_veg, " + 
			"	        m_product.prep_time, " + 
			"	        m_product.rate_setting_type, " + 
			"	        m_product.max_wt, " + 
			"	        m_product.ex_var1, " + 
			"	        m_product.ex_var2, " + 
			"	        m_fr_configration.display_rate, " + 
			"	        m_fr_configration.actual_rate, " + 
			"	        m_fr_configration.frachase_config_id, " + 
			"	        0 AS is_home_page_prod, " + 
			"	        tn_item_config_header.config_header_id  , " + 
			//"	 0 as new,\n" + 
			"	 m_tax.tax_name,m_tax.hsn_code,m_tax.cgst_per,m_tax.sgst_per,m_tax.igst_per,m_tax.cess_per,m_tax.total_tax_per,\n" + 
			"	 m_sub_category.sub_cat_name," + 
			"	 m_product.sort_id,m_product.shelf_life,m_product.is_return_allow,m_product.ret_per, " + 
			"	 m_product.is_slot_used, " + 
			"	 m_product.topping_cream,m_product.layering_cream," + 
			"	 m_product.type_of_bread,m_product.type_of_cream, " + 
			"	 m_uom.uom_show_name " + 
			"	    FROM " + 
			"	        m_product, " + 
			"	        tn_item_config_detail, " + 
			"	        tn_item_config_header, " + 
			"	        m_fr_configration ,  " + 
			"	 m_tax,m_sub_category,m_uom " + 
			"	    WHERE m_uom.uom_id=m_product.uom_id AND " + 
			"	 m_tax.tax_id=m_product.tax_id AND m_sub_category.sub_cat_id=m_product.prod_sub_cat_id AND \n" + 
			"	        m_product.product_id = tn_item_config_detail.product_id \n" + 
			"	        AND tn_item_config_detail.del_status = 1  " + 
			"	        AND tn_item_config_detail.is_active = 1  " + 
			"	        AND tn_item_config_detail.config_header_id = tn_item_config_header.config_header_id \n" + 
			"	        AND tn_item_config_header.del_status = 1  " + 
			"	        AND tn_item_config_header.is_active = 1  " + 
			"	        AND tn_item_config_header.config_header_id = m_fr_configration.config_header_id \n" + 
			"	        AND m_fr_configration.fr_id =:frId " + 
			"	    GROUP BY " + 
			"	        m_product.product_id  " + 
			"	    ORDER BY " + 
			"	        m_product.product_id ASC ) b " + 
			" ", nativeQuery = true)
	List<FEProductHeader> getFEProductHeaderByFrId(@Param("frId") int frId);
	
	
	
	
	@Query(value = " SELECT  DISTINCT primary_item_id FROM m_product_configuration WHERE "
			+ " FIND_IN_SET "
			+ " (:prodId,secondary_item_id) and del_status=1 AND is_active=1 " +  
			" ", nativeQuery = true)

	List<Integer> getRelatedProdIds(@Param("prodId") int prodId);
	
			
	
	
}
