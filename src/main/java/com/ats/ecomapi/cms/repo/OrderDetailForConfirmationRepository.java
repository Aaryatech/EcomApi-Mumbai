package com.ats.ecomapi.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.ecomapi.master.model.OrderDetailForConfirmation; 

public interface OrderDetailForConfirmationRepository extends JpaRepository<OrderDetailForConfirmation, Integer> {

	@Query(value="select a.*,b.filter_name from (select od.*,p.product_name as item_name,p.prod_image_primary as item_pic,p.rate_setting_type from "
			+ "tn_order_detail od, tn_order_header o,m_product p where od.order_id=:orderId and o.order_id=od.order_id and p.product_id=od.item_id "
			+ "and od.del_status=1 ) a  left join( select fi.filter_name,fi.filter_id from m_filter fi )b on b.filter_id=a.ex_int4", nativeQuery=true)
	List<OrderDetailForConfirmation> getDetail(int orderId);

}
