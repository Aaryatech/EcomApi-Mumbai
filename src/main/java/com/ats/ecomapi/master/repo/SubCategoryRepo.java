package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.SubCategory;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer> {

	@Query(value="SELECT\n" + 
			"    s.sub_cat_id,\n" + 
			"    s.cat_id,\n" + 
			"    s.sub_cat_code,\n" + 
			"    s.sub_cat_name,\n" + 
			"    s.sub_cat_prefix,\n" + 
			"    s.sub_cat_desc,\n" + 
			"    s.image_name,\n" + 
			"    s.company_id,\n" + 
			"    s.is_parent,\n" + 
			"    s.allow_to_copy,\n" + 
			"    s.sort_no,\n" + 
			"    s.is_active,\n" + 
			"    s.del_status,\n" + 
			"    s.ex_int1,\n" + 
			"    s.ex_int2,\n" + 
			"    s.ex_int3,\n" + 
			"    s.ex_var1,\n" + 
			"    s.ex_var2,\n" + 
			"    s.ex_var3,    \n" + 
			"    s.ex_float1,\n" + 
			"    s.ex_float2,\n" + 
			"    s.ex_float3,\n" + 
			"    s.ex_date1,\n" + 
			"    s.ex_date2,\n" + 
			"    c.cat_name AS ex_var4\n" + 
			"FROM\n" + 
			"    m_sub_category s,\n" + 
			"    m_category c\n" + 
			"WHERE\n" + 
			"    s.cat_id=c.cat_id AND\n" + 
			"    s.del_status=1 AND\n" + 
			"    s.is_active=1 AND\n" + 
			"    c.del_status=1 AND\n" + 
			"    c.is_active=1 AND\n" + 
			"    s.company_id=:compId  ORDER BY s.sub_cat_id DESC",nativeQuery=true)
	List<SubCategory> getAllActiveSubcategories(@Param("compId") int compId);
}