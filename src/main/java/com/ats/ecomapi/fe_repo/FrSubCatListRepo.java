package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.FrSubCatList;

public interface FrSubCatListRepo extends JpaRepository<FrSubCatList, Integer> {
	
	@Query(value = " SELECT UUID() as sub_cat_uuid, m_sub_category.sub_cat_id,m_category.cat_id,m_category.cat_name,m_sub_category.sub_cat_code,m_sub_category.sub_cat_name, " + 
			" m_sub_category.sub_cat_prefix,m_sub_category.image_name,m_sub_category.company_id " + 
			" FROM m_fr_configration,m_category,tn_item_config_header,m_sub_category    " + 
			" WHERE m_category.cat_id=tn_item_config_header.cat_id AND m_sub_category.cat_id=m_category.cat_id and tn_item_config_header.config_header_id=m_fr_configration.config_header_id "
			+ " AND m_fr_configration.fr_id=:frId   " + 
			" AND m_category.del_status=1 and m_category.is_active=1 and m_sub_category.del_status=1 and m_sub_category.is_active=1 AND tn_item_config_header.del_status=1 and tn_item_config_header.is_active=1 AND m_fr_configration.is_active=1 " + 
			" ORDER BY m_category.sort_no DESC ", nativeQuery = true)
	List<FrSubCatList> getFrSubCatList(@Param("frId") int frId);
	
	

}
