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
	
	@Query(value="SELECT\n" + 
			"    tn_order_header.order_id,\n" + 
			"    tn_order_header.order_no,\n" + 
			"    tn_order_header.order_date,\n" + 
			"    tn_order_header.fr_id,\n" + 
			"    tn_order_header.cust_id,\n" + 
			"    tn_order_header.status,\n" + 
			"    tn_order_header.taxable_amt,\n" + 
			"    tn_order_header.cgst_amt,\n" + 
			"    tn_order_header.sgst_amt,\n" + 
			"    tn_order_header.igst_amt,\n" + 
			"    tn_order_header.disc_amt,\n" + 
			"    tn_order_header.item_disc_amt,\n" + 
			"    tn_order_header.tax_amt,\n" + 
			"    tn_order_header.total_amt,\n" + 
			"    tn_order_header.order_status,\n" + 
			"    tn_order_header.paid_status,\n" + 
			"    tn_order_header.payment_method,\n" + 
			"    tn_order_header.payment_remark,\n" + 
			"    tn_order_header.city_id,\n" + 
			"    tn_order_header.area_id,\n" + 
			"    tn_order_header.address_id,\n" + 
			"    tn_order_header.address,\n" + 
			"    tn_order_header.whatsapp_no,\n" + 
			"    tn_order_header.landmark,\n" + 
			"    tn_order_header.delivery_date,\n" + 
			"    tn_order_header.delivery_time,\n" + 
			"    tn_order_header.production_date,\n" + 
			"    tn_order_header.production_time,\n" + 
			"    tn_order_header.insert_date_time,\n" + 
			"    tn_order_header.insert_user_id,\n" + 
			"    tn_order_header.order_platform,\n" + 
			"    tn_order_header.del_status,\n" + 
			"    tn_order_header.offer_id,\n" + 
			"    tn_order_header.remark,\n" + 
			"    tn_order_header.order_delivered_by,\n" + 
			"    tn_order_header.ex_int1,\n" + 
			"    tn_order_header.ex_int2,\n" + 
			"    tn_order_header.ex_int3,\n" + 
			"    tn_order_header.ex_int4,\n" + 
			"     m_franchise.fr_name AS ex_var1,\n" + 
			"    tn_order_header.ex_var2,\n" + 
			"    tn_order_header.ex_var3,\n" + 
			"    tn_order_header.ex_var4,\n" + 
			"    tn_order_header.ex_float1,\n" + 
			"    tn_order_header.ex_float2,\n" + 
			"    tn_order_header.ex_float3,\n" + 
			"    tn_order_header.ex_float4,\n" + 
			"    tn_order_header.ex_date1,\n" + 
			"    tn_order_header.ex_date2,\n" + 
			"    tn_order_header.billing_name,\n" + 
			"    tn_order_header.billing_address,\n" + 
			"    tn_order_header.customer_gstn_no,\n" + 
			"    tn_order_header.delivery_type,\n" + 
			"    tn_order_header.delivery_inst_id,\n" + 
			"    tn_order_header.delivery_inst_text,\n" + 
			"    tn_order_header.delivery_km,\n" + 
			"    tn_order_header.delivery_charges,\n" + 
			"    tn_order_header.payment_sub_mode,\n" + 
			"    tn_order_header.is_agent,\n" + 
			"    tn_order_header.uuid_no\n" + 
			"FROM\n" + 
			"    tn_order_header,\n" + 
			"    m_franchise\n" + 
			"WHERE\n" + 
			"    tn_order_header.fr_id=m_franchise.fr_id\n" + 
			"    AND tn_order_header.order_id=:orderId",nativeQuery=true)
	OrderHeader getHeaderByOrderId(@Param("orderId") int orderId);
	

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
				+ "payment_remark=:payRemark, ex_float4=:paidAmt WHERE order_id=:orderId")
		int updateOrderFrontEndSuccessPay(@Param("uniqNo") String uniqNo,
				@Param("paidStatus") int paidStatus,
				@Param("payRemark") String payRemark,  @Param("paidAmt") String paidAmt,
				@Param("orderId") int orderId);
		
		
		@Transactional
		@Modifying
		@Query("update OrderHeader set del_status=:status WHERE order_id=:orderId")
		int deleteOrder(@Param("status") int status, @Param("orderId") int orderId);


		
		@Transactional
		@Modifying
		@Query(" UPDATE OrderHeader SET order_status=:ordStatus,delivery_inst_text=:curDtTime WHERE order_id=:orderId and insert_date_time=:insertDtTime")
		int updateOrderByCust(@Param("orderId") int orderId,@Param("ordStatus") int ordStatus,
				@Param("curDtTime") String curDtTime, @Param("insertDtTime") String insertDtTime);

		
}
