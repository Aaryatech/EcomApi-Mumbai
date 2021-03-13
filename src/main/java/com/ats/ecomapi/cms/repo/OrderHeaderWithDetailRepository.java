package com.ats.ecomapi.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.ecomapi.master.model.OrderHeaderWithDetail;

public interface OrderHeaderWithDetailRepository extends JpaRepository<OrderHeaderWithDetail, Integer> {

	
	@Query(value="select UUID() as id,o.*,c.cust_name,f.fr_name from tn_order_header o,m_customer c,m_franchise f where o.order_id=:orderId "
			+ "and c.cust_id=o.cust_id and f.fr_id=o.fr_id", nativeQuery=true)
	OrderHeaderWithDetail getHeader(int orderId);

}
