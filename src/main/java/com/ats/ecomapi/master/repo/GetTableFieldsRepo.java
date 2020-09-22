package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 import com.ats.ecomapi.master.model.GetTableFields;
import com.ats.ecomapi.mst_model.GetTableNames;

public interface GetTableFieldsRepo extends JpaRepository<GetTableFields, Integer>{

 	
	
	@Query(value=" :querydata ",nativeQuery=true)
	public List<GetTableFields> getDataByQuery(@Param("querydata") String querydata);
	
	 
}
