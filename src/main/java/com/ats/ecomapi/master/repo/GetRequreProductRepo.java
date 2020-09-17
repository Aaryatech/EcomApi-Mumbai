package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.ecomapi.master.model.GetRequreProduct;

public interface GetRequreProductRepo extends JpaRepository<GetRequreProduct,String>{
	
	
	@Query(value="SELECT\n" + 
			"    UUID() AS id, m_product.product_id, m_product.prod_cat_id as cat_id, 0 AS config_id\n" + 
			"FROM\n" + 
			"    m_product\n" + 
			"WHERE\n" + 
			"    m_product.del_status = 1 AND m_product.product_id IN(:fixedLenghtList)",nativeQuery=true)
	public List<GetRequreProduct> getAllProductCat(@Param("fixedLenghtList") List<String> fixedLenghtList);

}
