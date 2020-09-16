package com.ats.ecomapi.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.GetSubCatPrefix;

public interface GetSubCatPrefixRepo extends JpaRepository<GetSubCatPrefix, Integer> {
	
	@Query(value = " SELECT UUID() as uuid, m_sub_category.sub_cat_prefix, " + 
			" COALESCE((SELECT count(m_product.product_id) FROM m_product WHERE m_product.prod_sub_cat_id=:subCatId )) "
			+ "as prod_count " + 
			" FROM m_sub_category WHERE m_sub_category.sub_cat_id=:subCatId and m_sub_category.del_status=1 ", nativeQuery = true)
	GetSubCatPrefix getSubCatPrefix(@Param("subCatId") int subCatId);
	
}
