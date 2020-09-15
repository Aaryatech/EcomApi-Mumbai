package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.MFilter;

public interface MFilterRepo extends JpaRepository<MFilter, Integer> {

	@Query(value=" SELECT\n" + 
			"        f.filter_id,\n" + 
			"        f.filter_name,\n" + 
			"        f.filter_desc,\n" + 
			"        f.filter_type_id,\n" + 
			"        f.used_for_filter,\n" + 
			"        f.cost_affect,\n" + 
			"        f.used_for_description,\n" + 
			"        f.company_id,\n" + 
			"        f.is_parent,\n" + 
			"        f.allow_to_copy,\n" + 
			"        f.sort_no,\n" + 
			"        f.is_active,\n" + 
			"        f.del_status,\n" + 
			"        f.ex_int1,\n" + 
			"        f.ex_int2,\n" + 
			"        f.ex_int3,\n" + 
			"        f.ex_var1,\n" + 
			"        f.ex_var2,\n" + 
			"        t.filter_type_name AS ex_var3 \n" + 
			"    FROM\n" + 
			"        m_filter f,\n" + 
			"        m_filter_type t \n" + 
			"    WHERE\n" + 
			"        f.filter_type_id = t.filter_type_id \n" + 
			"        AND f.del_status = 1 \n" + 
			"        AND t.del_status = 1 \n" + 
			"        AND t.is_active = 1 \n" + 
			"        AND f.company_id = :compId\n" + 
			"        AND f.filter_type_id=:filterTypeId\n" + 
			"    ORDER BY\n" + 
			"        f.filter_id DESC   ", nativeQuery=true)
	List<MFilter> getAllFilters(@Param("compId") int compId, @Param("filterTypeId") int filterTypeId);
	
	MFilter findByFilterId(int filterId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_filter` SET del_status=0 WHERE filter_id=:filterId",nativeQuery=true)
	int deleteFilter(@Param("filterId") int filterId);
}
