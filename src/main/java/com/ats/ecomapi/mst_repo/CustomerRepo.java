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
	@Query(value="UPDATE `m_customer` SET del_status=0 WHERE cust_id=:custId",nativeQuery=true)
	public int deleteCustomer(@Param("custId") int custId);

	public Customer findByCustId(int custId);

	public List<Customer> findByDelStatusOrderByCustIdDesc(int i);



}
