package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Route;

public interface RouteRepo extends JpaRepository<Route, Integer>{

	Route findByRouteId(int routeId);

	List<Route> findByDelStatusOrderByRouteIdDesc(int i);

	

	@Transactional
	@Modifying
	@Query(value="UPDATE m_route SET del_status=0 WHERE route_id=:routeId",nativeQuery=true)
	int deleteRoute(@Param("routeId") int routeId);

	Route findByRouteCode(@Param("code") String code);
	
	Route findByRouteCodeAndRouteIdNot(@Param("code") String code ,@Param("routeId") int routeId);
}
