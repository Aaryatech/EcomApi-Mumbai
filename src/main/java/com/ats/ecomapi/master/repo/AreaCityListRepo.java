package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.AreaCityList;


public interface AreaCityListRepo extends JpaRepository<AreaCityList, Integer> {

	@Query(value="SELECT\n" + 
			"    area.area_id,\n" + 
			"    area.area_code,\n" + 
			"    area.area_name,\n" + 
			"    area.pincode,\n" + 
			"    area.is_active,\n" + 
			"    area.ex_int1,\n" + 
			"    area.ex_var1,\n" + 
			"    city.city_id,\n" + 
			"    city.city_name\n" + 
			"FROM\n" + 
			"    mn_area area,\n" + 
			"    mn_city city\n" + 
			"WHERE\n" + 
			"    area.del_status=1 AND\n" + 
			"    city.del_status=1 AND\n" + 
			"    city.is_active=1 AND\n" + 
			"    city.city_id=area.city_id AND\n" + 
			"    area.company_id=:compId ORDER BY area.area_id DESC",nativeQuery=true)
	public List<AreaCityList> getAllAreaByCompId(@Param("compId") int compId);
}
