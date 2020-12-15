package com.ats.ecomapi.mst_repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.OrderHeader;

public interface OrderHeaderRepo extends JpaRepository<OrderHeader, Integer> {

	@Transactional
	@Modifying
	@Query("update OrderHeader set order_status=:status WHERE order_id=:orderId")
	int updateStatus(@Param("status") int status, @Param("orderId") int orderId);

	@Query(value = "select uuid_no from tn_order_header where order_id=:orderId", nativeQuery = true)
	String getUuId(@Param("orderId") int orderId);

	OrderHeader findByOrderId(int orderId);

	@Transactional
	@Modifying
	@Query(" UPDATE OrderHeader SET order_delivered_by=:delBoyId WHERE order_id=:orderId")
	int updateDeliveryBoy(@Param("orderId") int orderId, @Param("delBoyId") int delBoyId);

	//Sachin 14-12-2020
	@Transactional
	@Modifying
	@Query("update OrderHeader SET uuid_no=:uniqNo,paid_status=:paidStatus,"
			+ "payment_remark=:payRemark, order_status=:orderStatus WHERE order_id=:orderId")
	int updateOrderFrontEndFailedPay(@Param("uniqNo") String uniqNo,
			@Param("paidStatus") int paidStatus,
			@Param("payRemark") String payRemark,
			@Param("orderStatus") int orderStatus,
			@Param("orderId") int orderId);

	//Sachin 14-12-2020
		@Transactional
		@Modifying
		@Query("update OrderHeader SET uuid_no=:uniqNo,paid_status=:paidStatus,"
				+ "payment_remark=:payRemark WHERE order_id=:orderId")
		int updateOrderFrontEndSuccessPay(@Param("uniqNo") String uniqNo,
				@Param("paidStatus") int paidStatus,
				@Param("payRemark") String payRemark,
				@Param("orderId") int orderId);

}
