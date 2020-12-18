package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetTnOrderHeader;

public interface GetTnOrderHeaderRepo extends JpaRepository<GetTnOrderHeader, Integer> {
	
	
	
	@Query(value="SELECT * FROM tn_order_header WHERE  tn_order_header.status IN (:status) AND order_delivered_by=:delBoyId AND del_status=1 ",nativeQuery=true)
	List<GetTnOrderHeader> getOrderHeaderByStatusAndDelBoyId(@Param("status") int[] status,@Param("delBoyId") int delBoyId);

	@Transactional
	@Modifying
	@Query(value="UPDATE tn_order_header SET status=:status WHERE order_id=:orderId AND del_status=1 ",nativeQuery=true)
	int updateTnOrderHeaderStatus(@Param("orderId") int orderId,@Param("status") int  status );
	
	
	
}
