package com.ats.ecomapi.mst_repo;

import java.util.List;

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

	
	@Query(value = " SELECT order_detail_id, order_id, item_id, hsn_code,"
			+ " qty, mrp, rate, taxable_amt, cgst_per, sgst_per, igst_per, cgst_amt, sgst_amt, igst_amt, disc_amt, tax_amt, total_amt, del_status, remark,"
			+ " ex_int1, ex_int2, ex_int3, ex_int4, ex_var1, ex_var2, ex_var3, ex_var4, "
			+ " ex_float1, ex_float2, ex_float3, ex_float4 FROM tn_order_detail"
			+ " WHERE order_detail_id IN (:orderDetailList) " + 
			"", nativeQuery = true)
	List<OrderDetail> getOrderDetailByOrderDetailIdList(@Param("orderDetailList") List<String> orderDetailList);
	
	
	
	@Transactional
	@Modifying
	@Query("update OrderDetail set ex_int3=:status WHERE order_detail_id IN (:orderDetailIds)")
	int partialProductReject(@Param("status") int status,
			@Param("orderDetailIds") List<String> orderDetailIds);

}
