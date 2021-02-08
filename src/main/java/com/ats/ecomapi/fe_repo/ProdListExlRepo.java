package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.FEProductHeader;
import com.ats.ecomapi.fe_model.ProdListExl;

public interface ProdListExlRepo extends JpaRepository<ProdListExl, Integer> {
	@Query(value="  SELECT\n" + 
			"        b.*,\n" + 
			"        '' AS all_filter_names,\n" + 
			"        CASE \n" + 
			"            WHEN b.allow_same_day_delivery = 1 THEN COALESCE(         (         SELECT\n" + 
			"                GROUP_CONCAT(m_filter.filter_name)         \n" + 
			"            FROM\n" + 
			"                m_filter         \n" + 
			"            WHERE\n" + 
			"                FIND_IN_SET(                 m_filter.filter_id,                 b.same_day_time_allowed_slot             ) \n" + 
			"                AND m_filter.filter_type_id = 2     ),\n" + 
			"            0     ) \n" + 
			"            ELSE 'NA' \n" + 
			"        END AS same_day_time_slot_names,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.events_ids         ) \n" + 
			"            AND m_filter.filter_type_id = 6 ),\n" + 
			"        'NA' ) AS event_names,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.flavour_ids         ) \n" + 
			"            AND m_filter.filter_type_id = 4 ),\n" + 
			"        'NA' ) AS flavor_names,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.applicable_tags         ) \n" + 
			"            AND m_filter.filter_type_id = 7 ),\n" + 
			"        'NA' ) AS appli_tag_names,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(m_filter.filter_id, b.shape_id) \n" + 
			"            AND m_filter.filter_type_id = 1 ),\n" + 
			"        'NA' ) AS shape_names,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.prod_type_id         ) \n" + 
			"            AND m_filter.filter_type_id = 3 ),\n" + 
			"        'NA' ) AS prod_type_name,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.prod_status_id         ) \n" + 
			"            AND m_filter.filter_type_id = 5 ),\n" + 
			"        'NA' ) AS prod_status_name,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.topping_cream         ) \n" + 
			"            AND m_filter.filter_type_id = 11 ),\n" + 
			"        'NA' ) AS topping_cream_names,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.layering_cream         ) \n" + 
			"            AND m_filter.filter_type_id = 10 ),\n" + 
			"        'NA' ) AS layering_cream_names,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.type_of_cream         ) \n" + 
			"            AND m_filter.filter_type_id = 9 ),\n" + 
			"        'NA' ) AS cream_type_name,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(             m_filter.filter_id,             b.type_of_bread         ) \n" + 
			"            AND m_filter.filter_type_id = 8 ),\n" + 
			"        'NA' ) AS bread_type_name,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            GROUP_CONCAT(m_filter.filter_name)     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            FIND_IN_SET(m_filter.filter_id, b.is_veg) \n" + 
			"            AND m_filter.filter_type_id = 12 ),\n" + 
			"        'NA' ) AS veg_nonveg_name,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            m_filter.filter_name     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            m_filter.filter_id = b.ex_int2 \n" + 
			"            AND m_filter.filter_type_id = 12 ),\n" + 
			"        'NA' ) AS default_veg_nonveg_name,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            m_filter.filter_name     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            m_filter.filter_id = b.ex_int3 \n" + 
			"            AND m_filter.filter_type_id = 4 ),\n" + 
			"        'NA' ) AS default_flavor_name, \n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            m_filter.filter_name     \n" + 
			"        FROM\n" + 
			"            m_filter     \n" + 
			"        WHERE\n" + 
			"            m_filter.filter_id = b.default_shape_id\n" + 
			"            AND m_filter.filter_type_id = 1 ),\n" + 
			"        'NA' ) AS default_shape_name\n" + 
			"    FROM\n" + 
			"        (     SELECT\n" + 
			"            UUID() AS prod_uuid,\n" + 
			"            m_product.product_id,\n" + 
			"            m_product.product_code,\n" + 
			"            m_product.product_name,\n" + 
			"            m_product.prod_cat_id,\n" + 
			"            m_product.prod_sub_cat_id,\n" + 
			"            m_product.tax_id,\n" + 
			"            m_product.min_qty,\n" + 
			"            m_product.uom_id,\n" + 
			"            m_product.short_name,\n" + 
			"            m_product.shape_id,\n" + 
			"            m_product.allow_same_day_delivery,\n" + 
			"            m_product.same_day_time_allowed_slot,\n" + 
			"            m_product.prod_type_id,\n" + 
			"            m_product.avail_in_weights,\n" + 
			"            m_product.flavour_ids,\n" + 
			"            m_product.prod_status_id,\n" + 
			"            m_product.book_before,\n" + 
			"            m_product.events_ids,\n" + 
			"            m_product.is_char_limit,\n" + 
			"            m_product.no_of_chars_for_alpha_cake,\n" + 
			"            m_product.allow_cover_photo_upload,\n" + 
			"            m_product.allow_base_photo_upload,\n" + 
			"            m_product.allow_special_instruction,\n" + 
			"            m_product.allow_msg_on_cake,\n" + 
			"            m_product.no_of_chars_on_cake,\n" + 
			"            m_product.product_desc,\n" + 
			"            m_product.ingerdiants,\n" + 
			"            m_product.applicable_tags,\n" + 
			"            m_product.company_id,\n" + 
			"            m_product.prod_image_primary,\n" + 
			"            m_product.product_images,\n" + 
			"            m_product.is_veg,\n" + 
			"            m_product.prep_time,\n" + 
			"            m_product.rate_setting_type,\n" + 
			"            m_product.max_wt,\n" + 
			"            m_product.ex_var1,\n" + 
			"            m_product.ex_var2,\n" + 
			"            m_product.ex_int2,\n" + 
			"            m_product.ex_int3,\n" + 
			"            m_product.ex_int3 AS default_flavor_id,\n" + 
			"            m_product.ex_int2 AS default_vegnonveg_id,\n" + 
			"            m_product.ex_int1 AS default_shape_id,\n" + 
			"            m_product.is_active AS is_home_page_prod,\n" + 
			"            m_tax.tax_name,\n" + 
			"            m_tax.hsn_code,\n" + 
			"            m_tax.cgst_per,\n" + 
			"            m_tax.sgst_per,\n" + 
			"            m_tax.igst_per,\n" + 
			"            m_tax.cess_per,\n" + 
			"            m_tax.total_tax_per,\n" + 
			"            m_sub_category.sub_cat_name,\n" + 
			"            m_product.sort_id,\n" + 
			"            m_product.shelf_life,\n" + 
			"            m_product.is_return_allow,\n" + 
			"            m_product.ret_per,\n" + 
			"            m_product.is_slot_used,\n" + 
			"            m_product.topping_cream,\n" + 
			"            m_product.layering_cream,\n" + 
			"            m_product.type_of_bread,\n" + 
			"            m_product.type_of_cream,\n" + 
			"            m_uom.uom_show_name,\n" + 
			"         	 m_category.cat_name,\n" + 
			"            0 AS default_price,\n" + 
			"            0 as display_rate,\n" + 
			"            m_product.basic_mrp as actual_rate,\n" + 
			"            0 as config_header_id,\n" + 
			"            0 as frachase_config_id       \n" + 
			"        FROM\n" + 
			"            m_product,\n" + 
			"            m_tax,\n" + 
			"            m_sub_category,\n" + 
			"         	m_category,\n" + 
			"            m_uom     \n" + 
			"        WHERE\n" + 
			"            m_product.is_active = 1 \n" + 
			"            AND m_product.del_status = 1 \n" + 
			"            AND m_uom.uom_id = m_product.uom_id \n" + 
			"            AND m_tax.tax_id = m_product.tax_id \n" + 
			"            AND m_sub_category.sub_cat_id = m_product.prod_sub_cat_id \n" + 
			"            AND m_product.company_id=:compId    \n" + 
			"         	AND m_category.cat_id=m_product.prod_cat_id\n" + 
			"        GROUP BY\n" + 
			"            m_product.product_id     \n" + 
			"        ORDER BY\n" + 
			"            m_product.product_id ASC) b",nativeQuery=true)
	List<ProdListExl> getProductListForPdfExcel(@Param("compId") int compId);
}
