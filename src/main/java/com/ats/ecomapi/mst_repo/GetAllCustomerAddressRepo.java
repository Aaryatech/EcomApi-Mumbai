package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.GetAllCustomerAddress;

public interface GetAllCustomerAddressRepo extends JpaRepository<GetAllCustomerAddress, Integer> {

	@Query(value="SELECT\n" + 
			"    t1.*,\n" + 
			"    t2.city_name,\n" + 
			"    t3.area_name,\n" + 
			"    t4.cust_name,\n" + 
			"    t4.cust_mobile_no\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        *\n" + 
			"    FROM\n" + 
			"        `m_customer_address_detail`\n" + 
			"    WHERE\n" + 
			"        cust_id = :custId AND del_status = 1\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        mn_city.city_id,\n" + 
			"        mn_city.city_name\n" + 
			"    FROM\n" + 
			"        mn_city\n" + 
			"    WHERE\n" + 
			"        mn_city.del_status = 1\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.city_id = t2.city_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        mn_area.area_id,\n" + 
			"        mn_area.area_name\n" + 
			"    FROM\n" + 
			"        mn_area\n" + 
			"    WHERE\n" + 
			"        mn_area.del_status = 1\n" + 
			") t3\n" + 
			"ON\n" + 
			"    t1.area_id = t3.area_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_customer.cust_id,\n" + 
			"        m_customer.cust_name,\n" + 
			"    	m_customer.cust_mobile_no\n" + 
			"    FROM\n" + 
			"        m_customer\n" + 
			"    WHERE\n" + 
			"        m_customer.company_id = :compId AND m_customer.del_status = 1\n" + 
			") t4\n" + 
			"ON\n" + 
			"    t1.cust_id = t4.cust_id", nativeQuery=true)
	public List<GetAllCustomerAddress> getAllCustAddress(@Param("compId") int compId, @Param("custId") int custId);
}
