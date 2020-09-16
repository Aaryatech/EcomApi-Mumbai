package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Area;

import java.lang.String;

public interface AreaRepo extends JpaRepository<Area, Integer> {

	List<Area> findByDelStatusAndCompanyIdAndIsActiveOrderByAreaIdDesc(int del, int compId, int status);
	
	Area findByAreaCodeIgnoreCaseAndCompanyId(String code, int compId);
	
	Area findByAreaCodeIgnoreCaseAndCompanyIdAndAreaIdNot(String code,int compId, int areaId);
	
	Area findByAreaIdAndDelStatusAndCompanyId(int areaId, int del, int compId);
	
	List<Area> findByCityIdAndCompanyIdAndDelStatus(int cityId, int compId, int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE mn_area SET del_status=0 WHERE area_id=:areaId",nativeQuery=true)
	public int deleteArea(@Param("areaId") int areaId);
	
	
	@Query(value="SELECT COUNT(area_code) FROM `mn_area` WHERE city_id=:cityId",nativeQuery=true)
	public int getMaxCountAreaCode(@Param("cityId") int cityId);

	@Query(value="SELECT * FROM mn_area WHERE city_id=:cityId and del_status=0",nativeQuery=true)
	List<Area> getarealistbycityid(@Param("cityId") int cityId);
	
	@Query(value="SELECT * FROM mn_area WHERE city_id IN(:cityId) and del_status=0 and company_id=:compId",nativeQuery=true)
	List<Area> getAreaByCityIdsAndCompId(@Param("cityId") List<Integer> cityId,@Param("compId") int compId);
}
