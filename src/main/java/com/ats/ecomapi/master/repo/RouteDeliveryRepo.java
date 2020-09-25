package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.RouteDelivery;

public interface RouteDeliveryRepo extends JpaRepository<RouteDelivery, Integer> {
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_route_delivery SET del_status=0 WHERE rouid_delvery_id=:delvId",nativeQuery=true)
	int deleteRouteDelivery(@Param("delvId")  int delvId);

	List<RouteDelivery> findByDelStatusOrderByRouidDelveryIdDesc(int i);

	RouteDelivery findByRouidDelveryId(int routeId);


}
