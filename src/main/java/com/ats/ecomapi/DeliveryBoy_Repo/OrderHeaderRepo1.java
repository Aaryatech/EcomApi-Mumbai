package com.ats.ecomapi.DeliveryBoy_Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.OrderHeader1;

public interface OrderHeaderRepo1 extends JpaRepository<OrderHeader1,Long>{
	
@Query(value="select h.order_id,d.order_id,order_no, d.order_detail_id,fr_id,cust_id,item_id,order_status,address,whatsapp_no,landmark,billing_name,delivery_km,payment_method,qty,h.total_amt,product_id,product_name\n" + 
		"from tn_order_detail d,tn_order_header h,m_product \n" + 
		"where h.order_id=d.order_id and d.order_detail_id=m_product.product_id and h.order_id=:order_id",nativeQuery=true)
OrderHeader1 toMatchOrderIdNo(@Param("order_id" )Integer order_id);
//

}
