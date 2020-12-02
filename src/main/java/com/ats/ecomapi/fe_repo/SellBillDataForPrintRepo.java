package com.ats.ecomapi.fe_repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.SellBillDataForPrint;

public interface SellBillDataForPrintRepo extends JpaRepository<SellBillDataForPrint, Integer> {

	@Query(value="SELECT\r\n" + 
			"    *,\r\n" + 
			"    oh.is_agent,\r\n" + 
			"    CONCAT(\r\n" + 
			"        del_boy.first_name,\r\n" + 
			"        ' ',\r\n" + 
			"        del_boy.last_name\r\n" + 
			"    ) AS del_boy_name,\r\n" + 
			"    del_boy.mobile_no AS del_boy_mobile,\r\n" + 
			"    c.cust_name,\r\n" + 
			"    c.ex_var2 as gst_no,\r\n" + 
			"    e.fr_emp_name AS emp_name,\r\n" + 
			"    oh.address AS cust_address\r\n" + 
			"FROM\r\n" + 
			"    t_sell_bill_header bh,\r\n" + 
			"    tn_order_header oh,\r\n" + 
			"    m_delivery_boy del_boy,\r\n" + 
			"    m_customer c,\r\n" + 
			"    m_fr_emp e\r\n" + 
			"WHERE\r\n" + 
			"    bh.ext_int2 = :orderId AND bh.ext_int2 = oh.order_id AND bh.cust_id = c.cust_id AND oh.cust_id = c.cust_id AND bh.ext_int1 = e.fr_emp_id AND del_boy.del_boy_id=oh.order_delivered_by ",nativeQuery=true)
	SellBillDataForPrint getBillHeaderByOrderId(@Param("orderId") int orderId);
	
}
