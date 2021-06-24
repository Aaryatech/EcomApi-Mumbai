package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.ecomapi.fe_model.DeliveryCharges;

public interface DeliveryChargesRepo extends JpaRepository<DeliveryCharges, Integer> {	
	
	public List<DeliveryCharges> findByDelStatusOrderByChIdDesc(int del);
	
	List<DeliveryCharges> findByExInt1AndDelStatusOrderByChIdDesc(int compId, int del);
	
	DeliveryCharges findByChId(int chargeId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `mn_delivery_charges` SET del_status=1 WHERE ch_id=:chargeId", nativeQuery=true)
	public int deleteDeliveryCharges(@Param("chargeId") int chargeId);
	

}
