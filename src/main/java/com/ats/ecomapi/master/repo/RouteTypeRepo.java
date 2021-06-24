package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.RouteType;

public interface RouteTypeRepo extends JpaRepository<RouteType, Integer> {

	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_route_type SET del_status=0 WHERE route_type_id=:routeTypeId",nativeQuery=true)
	int deleteRouteType(@Param("routeTypeId") int routeTypeId);

	RouteType findByRouteTypeId(int routeId);

	List<RouteType> findByDelStatusOrderByRouteTypeIdDesc(int i);
	
	List<RouteType> findBycompanyIdAndDelStatusOrderByRouteTypeIdDesc(int compId, int i);
}
