package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.GetProdList;

public interface GetProdListRepo extends JpaRepository<GetProdList, Integer> {

	@Query(value = " SELECT m_product.product_id,m_product.product_code,m_product.product_name,m_category.cat_name,m_sub_category.sub_cat_name, "
			+ " m_product.is_active,m_uom.uom_show_name,m_product.allow_same_day_delivery,m_filter.filter_name as prod_status,m_product.book_before,m_product.is_veg "
			+ " FROM m_product,m_category,m_sub_category,m_uom,m_filter "
			+ " WHERE m_product.prod_cat_id=m_category.cat_id AND m_product.prod_sub_cat_id=m_sub_category.sub_cat_id AND m_product.uom_id=m_uom.uom_id AND"
			+ " m_product.prod_status_id=m_filter.filter_id and m_filter.filter_type_id=5 "
			+ " and m_product.del_status=1 and m_product.company_id=:compId ORDER BY m_product.sort_id DESC  ", nativeQuery = true)
	List<GetProdList> getProdList(@Param("compId") int compId);

}
