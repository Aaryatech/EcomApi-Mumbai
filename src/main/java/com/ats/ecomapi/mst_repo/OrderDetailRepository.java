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

	//SACHIN 07-07-2021
	@Transactional
	@Modifying
	@Query("update OrderDetail set ex_int3=:status,ex_var3=:retPer WHERE order_id=:orderId")
	int cancelItemOrder(@Param("status") int status,
			@Param("retPer") String retPer, @Param("orderId") int orderId);

}
