package com.ats.ecomapi.DeliveryBoy_Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.deliveryboy_model.OrderDetail1;

public interface OrderDetailRepo1 extends JpaRepository<OrderDetail1,Long>{

	
	 @Query(value="select  tn_order_detail.item_id AS product_id,m_product.product_name,m_product.prod_image_primary,tn_order_detail.ex_float3 AS min_qty,tn_order_detail.ex_var4 AS uom,(tn_order_detail.ex_float3*tn_order_detail.mrp)AS total,m_product.product_desc,tn_order_detail.mrp AS basic_mrp,tn_order_detail.order_detail_id ,tn_order_detail.order_id FROM m_product,tn_order_header,tn_order_detail WHERE tn_order_header.order_id=tn_order_detail.order_id and tn_order_detail.item_id=m_product.product_id\n" + 
	 		"and tn_order_header.del_status=1 and tn_order_detail.del_status=1 and tn_order_header.order_delivered_by=:order_delivered_by and tn_order_header.order_status=(:order_status)",nativeQuery=true)
	               List<OrderDetail1> getProductDetail(@Param("order_delivered_by" )Integer orderDeliveredBy,@Param("order_status")String orderStatus);
	
	 
}
