package com.ats.ecomapi.mst_repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.mst_model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
