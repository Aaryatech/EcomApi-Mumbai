package com.ats.ecomapi.DeliveryBoy_Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.deliveryboy_model.OrHeader;

public interface orheaderRepo extends JpaRepository<OrHeader,Long>{

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
			"        m_customer_address_detail.longitude  \n"+
			"from\n" + 
			"        tn_order_header,m_franchise,m_customer,m_customer_address_detail\n" + 
			"where\n" + 
			"        tn_order_header.order_delivered_by=:order_delivered_by\n" + 
			"        and tn_order_header.order_status IN (:order_status) "+ "and tn_order_header.del_status=1 \n" + 
			"        and tn_order_header.fr_id=m_franchise.fr_id\n" + 
			"        and tn_order_header.cust_id=m_customer.cust_id" + 
			"        and m_customer.cust_id=m_customer_address_detail.cust_id"+
			"        and  tn_order_header.address_id=m_customer_address_detail.cust_detail_id",nativeQuery=true)
	List<OrHeader> toMatchOrderIdN(@Param("order_delivered_by" )Integer order_delivered_by,@Param("order_status")String order_status);

	@Transactional
	@Modifying
	@Query(value="update tn_order_header set order_status=:order_status where order_id=:order_id",nativeQuery=true)
	Integer toUpdateStatus(@Param("order_id" )Integer order_id,@Param("order_status")String order_status);
	
	//Sac 2021-07-08
	@Transactional
	@Modifying
	@Query(value="update tn_order_header set order_status=:order_status,paid_status=1 where order_id=:order_id",nativeQuery=true)
	Integer toUpdateStatusWithPay(@Param("order_id" )Integer order_id,@Param("order_status")String order_status);
	
	
	/*select
        tn_order_header.order_id,
        tn_order_header.order_status,
        tn_order_header.order_no,
        tn_order_header.total_amt,
        tn_order_header.taxable_amt,
        tn_order_header.tax_amt,
        tn_order_header.payment_method,
        tn_order_header.fr_id,
        tn_order_header.delivery_date,
        tn_order_header.delivery_time,
        tn_order_header.insert_date_time,
        tn_order_header.cust_id,fr_code,fr_name,fr_address,shops_latitude,shops_logitude,no_of_km_area_cover,
        cust_name,cust_mobile_no,  
m_customer_address_detail.address,m_customer_address_detail.latitude,m_customer_address_detail.longitude
from
       tn_order_header,m_franchise,m_customer,m_customer_address_detail
where
        tn_order_header.order_delivered_by=4
        and tn_order_header.order_status IN (4) and tn_order_header.del_status=1  
        and tn_order_header.fr_id=m_franchise.fr_id
        and tn_order_header.cust_id=m_customer.cust_id
        and m_customer.cust_id=m_customer_address_detail.cust_id


AND tn_order_header.address_id=m_customer_address_detail.cust_detail_id*/
	
	
	
	
	
	
	
	
}
