package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.CategoryList;

public interface CategoryListRepo extends JpaRepository<CategoryList, Integer> {
	
	//Hard code 1 passed as companyId for /generateFrDataJSON Mapping 21-12-2020 
	@Query(value = " SELECT  UUID() as cat_uuid, m_category.cat_id,m_category.cat_name,m_category.cat_prefix,m_category.cat_desc,m_category.company_id, " + 
			" m_category.image_name,m_category.sort_no,ex_var1 as meta_title, ex_var2 as meta_desc, "
			+ " ex_var3 as meta_key,cat_desc as image_alt ,m_category.ex_int1 as static_cat_id" + 
			" FROM m_category " + 
			" WHERE  " + 
			" m_category.del_status=1 and m_category.is_active=1 AND m_category.company_id=:companyId " + 
			" ORDER BY m_category.sort_no DESC ", nativeQuery = true)
	List<CategoryList> getCompanyCatListByCompId(@Param("companyId") int companyId);

	@Query(value = " SELECT UUID() as cat_uuid, m_category.cat_id,m_category.cat_name,m_category.cat_prefix,m_category.cat_desc,m_category.company_id, " + 
			" m_category.image_name,m_category.sort_no, m_category.ex_var1 as meta_title, m_category.ex_var2 as meta_desc, " + 
			"			m_category.ex_var3 as meta_key,m_category.cat_desc as image_alt,"
			+ " m_category.ex_int1 as static_cat_id " + 
			" FROM m_fr_configration,m_category,tn_item_config_header " + 
			" WHERE m_category.cat_id=tn_item_config_header.cat_id AND tn_item_config_header.config_header_id=m_fr_configration.config_header_id AND m_fr_configration.fr_id=:frId " + 
			" and m_category.del_status=1 and m_category.is_active=1 AND tn_item_config_header.del_status=1 and tn_item_config_header.is_active=1 AND m_fr_configration.is_active=1 " + 
			" ORDER BY m_category.sort_no DESC ", nativeQuery = true)
	List<CategoryList> getFrCatListByFrId(@Param("frId") int frId);
	
	
	//Sachin 19-11-2020
	@Query(value = " SELECT  UUID() as cat_uuid, m_category.cat_id,m_category.cat_name,m_category.cat_prefix,m_category.cat_desc,m_category.company_id, " + 
			" m_category.image_name,m_category.sort_no,m_category.ex_var1 as meta_title, m_category.ex_var2 as meta_desc " + 
			" ,m_category.ex_var3 as meta_key,m_category.cat_desc as image_alt,"
			+ " m_category.ex_int1 as static_cat_id FROM m_category " + 
			" WHERE  " + 
			" m_category.del_status=1 and m_category.is_active=1 and m_category.is_parent=1 " + 
			" ORDER BY m_category.sort_no DESC ", nativeQuery = true)
	List<CategoryList> getMasterCompCatList();

}
