package com.ats.ecomapi.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.mst_model.OrderTrail;

public interface OrderTrailRepository extends JpaRepository<OrderTrail, Integer> {
	
	OrderTrail findByOrderIdAndStatus(int i,int j);

}
