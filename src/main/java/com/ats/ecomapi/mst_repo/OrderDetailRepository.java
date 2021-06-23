package com.ats.ecomapi.mst_repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	
	@Transactional
	@Modifying
	@Query("update OrderDetail set del_status=:status WHERE order_id=:orderId")
	int deleteOrder(@Param("status") int status, @Param("orderId") int orderId);

}
