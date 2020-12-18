package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetTnOrderDetails;

public interface GetTnOrderDetailsRepo extends JpaRepository<GetTnOrderDetails, Integer> {
	
	
	@Query(value="SELECT * FROM tn_order_detail WHERE order_id=:orderId AND del_status=1 ",nativeQuery=true)
	List<GetTnOrderDetails> GetGetTnOrderDetailsListByOrderId(@Param("orderId") int orderId);

}
