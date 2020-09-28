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
	@Query(value="UPDATE `m_customer` SET del_status=0 WHERE cust_id=:custId AND   maker_user_id=:userId AND updt_dttime=:dateTime  ",nativeQuery=true)
	public int deleteCustomer(@Param("custId") int custId,@Param("userId") int userId,@Param("dateTime") String dateTime);

	public Customer findByCustId(int custId);

	public List<Customer> findByDelStatusOrderByCustIdDesc(int i);

	public Customer findByCustMobileNoAndDelStatus(String mobNo, int i);

	public Customer findByCustMobileNoAndDelStatusAndCustIdNot(String mobNo, int i, int userId);



}
