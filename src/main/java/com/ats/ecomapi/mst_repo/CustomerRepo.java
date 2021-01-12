package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.Customer;

public interface CustomerRepo  extends JpaRepository<Customer, Integer>{
	
	

	@Transactional
	@Modifying
	@Query(value="UPDATE `m_customer` SET del_status=1 WHERE cust_id=:custId AND   maker_user_id=:userId AND updt_dttime=:dateTime  ",nativeQuery=true)
	public int deleteCustomer(@Param("custId") int custId,@Param("userId") int userId,@Param("dateTime") String dateTime);

	public Customer findByCustId(int custId);

	public List<Customer> findByDelStatusOrderByCustIdDesc(int i);

	public Customer findByCustMobileNoAndDelStatus(String mobNo, int i);

	public Customer findByCustMobileNoAndDelStatusAndCustIdNot(String mobNo, int i, int userId);

	public Customer findByEmailIgnoreCaseIdAndDelStatus(String email, int del);
	
	public Customer findByEmailIdIgnoreCaseAndDelStatusAndCustIdNot(String email, int del, int custId);

	@Query(value="select c.* from m_customer c, tn_order_header h where h.del_status=1 AND h.cust_id=c.cust_id AND h.order_id=:orderId",nativeQuery=true)
	Customer getCustomerByOrderId(@Param("orderId") int orderId);

	@Query(value="select c.* from m_customer c where c.cust_mobile_no=:mobNo and c.del_status=1 and c.is_active=1 order by cust_id desc limit 1 ",nativeQuery=true)
	public Customer findLastByCustIdCustMobileNoAndDelStatusAndIsActive(@Param("mobNo") String mobNo);

}
