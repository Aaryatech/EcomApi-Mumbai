package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.ecomapi.mst_model.GetRelatedProductConfig;

public interface GetRelatedProductConfigRepo extends JpaRepository<GetRelatedProductConfig, Integer> {
	
	
	
	@Query(value = " SELECT\n" + 
			"    m_product_configuration.related_product_id,\n" + 
			"    m_product_configuration.primary_item_id,\n" + 
			"    m_product_configuration.secondary_item_id,\n" + 
			"     m_product_configuration.del_status,\n" + 
			"    m_product_configuration.is_active,\n" + 
			"    m_product_configuration.ex_var1,m_product_configuration.ex_var2,\n" + 
			"    m_product.product_name AS primary_item,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        GROUP_CONCAT(DISTINCT c.product_name)\n" + 
			"    FROM\n" + 
			"        m_product_configuration i,\n" + 
			"        m_product c\n" + 
			"    WHERE\n" + 
			"        FIND_IN_SET(\n" + 
			"            c.product_id,\n" + 
			"            i.secondary_item_id\n" + 
			"        ) AND m_product_configuration.related_product_id = i.related_product_id\n" + 
			") AS prod_list\n" + 
			"FROM\n" + 
			"    m_product_configuration,\n" + 
			"    m_product\n" + 
			"WHERE\n" + 
			"    m_product.product_id = m_product_configuration.primary_item_id AND m_product.del_status = 1 AND m_product.company_id =:compId ORDER By m_product.product_id DESC ", nativeQuery = true)
	List<GetRelatedProductConfig> getCRelatedProductConfig(@Param("compId") int compId);


}
