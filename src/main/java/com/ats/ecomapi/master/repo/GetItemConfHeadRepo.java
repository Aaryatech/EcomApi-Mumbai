package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.GetItemConfHead;


public interface GetItemConfHeadRepo extends JpaRepository<GetItemConfHead,
Integer>{
	
	//Sachin 21-09-2020
	//Desc-to Show Product Conf Header List by Cat Id(s)
	
	@Query(value = " SELECT tn_item_config_header.config_header_id,tn_item_config_header.cat_id,tn_item_config_header.config_name,tn_item_config_header.company_id, " + 
			" tn_item_config_header.is_allow_to_copy,tn_item_config_header.is_active, " + 
			" m_category.cat_name FROM m_category,tn_item_config_header " + 
			" WHERE tn_item_config_header.cat_id=m_category.cat_id AND tn_item_config_header.del_status=1 and m_category.cat_id in(:catIdList) and tn_item_config_header.company_id=:companyId ", nativeQuery = true)
	
	List<GetItemConfHead> getItemConfHeadListByCatId(@Param("catIdList")List<Integer> catIdList
			,@Param("companyId") int companyId);

}
