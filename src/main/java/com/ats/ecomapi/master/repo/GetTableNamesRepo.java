package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.GetTableNames;

public interface GetTableNamesRepo extends JpaRepository<GetTableNames,String> {

	
	@Query(value="SELECT UUID() as id,Table_name as table_name from information_schema.tables where table_schema =:dbName",nativeQuery=true)
 	List<GetTableNames> getTableList(@Param("dbName") String dbName);

}
