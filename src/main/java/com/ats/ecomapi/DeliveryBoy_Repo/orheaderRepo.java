package com.ats.ecomapi.DeliveryBoy_Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.OrHeader;

public interface orheaderRepo extends JpaRepository<OrHeader,Long>{

	@Query(value="select \n" + 
			"        tn_order_header.order_id,\n" + 
			"        tn_order_header.order_status,\n" +
			"        tn_order_header.order_no,\n" + 
			"        tn_order_header.total_amt,\n" + 
			"        tn_order_header.payment_method,\n" + 
			"        tn_order_header.fr_id,\n" + 
			"        tn_order_header.cust_id,fr_code,fr_name,fr_address,shops_latitude,shops_logitude,no_of_km_area_cover,\n" + 
			"        cust_name,cust_mobile_no \n" + 
			"from\n" + 
			"        tn_order_header,m_franchise,m_customer\n" + 
			"where\n" + 
			"        tn_order_header.order_delivered_by=:order_delivered_by\n" + 
			"        and tn_order_header.order_status IN (:order_status) "+ "and tn_order_header.del_status=1 \n" + 
			"        and tn_order_header.fr_id=m_franchise.fr_id\n" + 
			"        and tn_order_header.cust_id=m_customer.cust_id",nativeQuery=true)
	List<OrHeader> toMatchOrderIdN(@Param("order_delivered_by" )Integer order_delivered_by,@Param("order_status")String order_status);
	
	
	@Transactional
	@Modifying
	@Query(value="update tn_order_header set order_status=:order_status where order_id=:order_id",nativeQuery=true)
	Integer toUpdateStatus(@Param("order_id" )Integer order_id,@Param("order_status")String order_status);
	
}
