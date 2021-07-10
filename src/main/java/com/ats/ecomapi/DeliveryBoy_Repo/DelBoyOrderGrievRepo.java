package com.ats.ecomapi.DeliveryBoy_Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.deliveryboy_model.DelBoyOrderGriev;
import com.ats.ecomapi.deliveryboy_model.OrHeader;

public interface DelBoyOrderGrievRepo extends JpaRepository<DelBoyOrderGriev , Integer> {
	
	//SACHIn 10-07-2021
		@Query(value="select  tn_order_header.order_id,\n" + 
				"        tn_order_header.order_status,\n" + 
				"        tn_order_header.order_no,\n" + 
				"        tn_order_header.total_amt,\n" + 
				"        tn_order_header.ex_float1 AS taxable_amt,\n" + 
				"        tn_order_header.delivery_charges AS tax_amt,\n" + 
				"        tn_order_header.payment_method, tn_order_header.paid_status,\n" + 
				"        tn_order_header.fr_id,\n" + 
				"        tn_order_header.delivery_date,\n" + 
				"         tn_order_header.ex_var3    AS delivery_time,\n" + 
				"        tn_order_header.insert_date_time,\n" + 
				"        tn_order_header.cust_id,\n" + 
				"         m_franchise.fr_contact_no AS  fr_code,\n" + 
				"        fr_name,\n" + 
				"        fr_address,\n" + 
				"        shops_latitude,\n" + 
				"        shops_logitude,\n" + 
				"        no_of_km_area_cover,\n" + 
				"        tn_order_header.ex_var4 AS cust_name,\n" + 
				"        cust_mobile_no,\n" + 
				"        CONCAT(tn_order_header.address,'~',tn_order_header.landmark) AS address,\n" + 
				"        m_customer_address_detail.latitude,\n" + 
				"        m_customer_address_detail.longitude,"
				+ ""
				+ " tn_grievences.delivery_reason,tn_grievences.pro_img1,tn_grievences.pro_img2,tn_grievences.pro_img3,tn_grievences.d_date,tn_grievences.order_id as griv_id"
				+ "  \n"+
				"from\n" + 
				"        tn_order_header,m_franchise,m_customer,m_customer_address_detail,"
				+ "tn_grievences \n" + 
				"where\n" + 
				"        tn_order_header.order_delivered_by=:order_delivered_by and tn_grievences.d_date=tn_order_header.order_id" + 
				"         "+ "and tn_order_header.del_status=1 \n" + 
				"        and tn_order_header.fr_id=m_franchise.fr_id\n" + 
				"        and tn_order_header.cust_id=m_customer.cust_id" + 
				"        and m_customer.cust_id=m_customer_address_detail.cust_id"+
				"        and  tn_order_header.address_id=m_customer_address_detail.cust_detail_id",nativeQuery=true)
		List<DelBoyOrderGriev> getOrderAndGrievByDelBoyId(@Param("order_delivered_by" )Integer order_delivered_by);

	
}
