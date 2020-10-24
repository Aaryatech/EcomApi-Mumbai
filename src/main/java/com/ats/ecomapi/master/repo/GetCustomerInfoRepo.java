package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.ecomapi.master.model.GetUser;
import com.ats.ecomapi.mst_model.GetCustomerInfo;

public interface GetCustomerInfoRepo extends JpaRepository<GetCustomerInfo, Integer> {
	
	@Query(value = "SELECT\n" + 
			"        m_customer.cust_id,\n" + 
			"        m_customer.cust_name,\n" + 
			"        m_customer.cust_mobile_no,\n" + 
			"        m_customer.email_id,\n" + 
			"        m_customer.profile_pic,\n" + 
			"        mn_city.city_name,\n" + 
			"        m_customer.company_id,\n" + 
			"        m_customer.date_of_birth,\n" + 
			"        m_customer.cust_gender,\n" + 
			"        m_customer.age_range,\n" + 
			"        m_customer.language_id,\n" + 
			"        m_customer.del_status,\n" + 
			"        m_customer.is_active,\n" + 
			"        m_customer.ex_var1,\n" + 
			"        m_company.company_name \n" + 
			"    FROM\n" + 
			"        m_customer,\n" + 
			"        m_company,\n" + 
			"        mn_city\n" + 
			"    WHERE\n" + 
			"        m_customer.del_status = 1 \n" + 
			"        AND m_company.del_status = 1 \n" + 
			"        AND m_company.company_id = m_customer.company_id\n" + 
			"        AND mn_city.city_id=m_customer.city_id\n" + 
			"    ORDER BY\n" + 
			"        m_customer.cust_id DESC", nativeQuery = true)
	List<GetCustomerInfo> getCustList();

	
//	SELECT\n" + 
//	"    m_customer.cust_id,\n" + 
//	"    m_customer.cust_name,\n" + 
//	"    m_customer.cust_mobile_no,\n" + 
//	"    m_customer.email_id,\n" + 
//	"    m_customer.profile_pic,\n" + 
//	"    m_customer.city_id,\n" + 
//	"    m_customer.company_id,\n" + 
//	"    m_customer.date_of_birth,\n" + 
//	"    m_customer.cust_gender,\n" + 
//	"    m_customer.age_range,\n" + 
//	"    m_customer.language_id,\n" + 
//	"    m_customer.del_status,\n" + 
//	"    m_customer.is_active, m_customer.ex_var1,\n" + 
//	"    m_company.company_name\n" + 
//	"FROM\n" + 
//	"    m_customer,\n" + 
//	"    m_company\n" + 
//	"WHERE\n" + 
//	"    m_customer.del_status = 1 AND m_company.del_status = 1 AND m_company.company_id = m_customer.company_id ORDER BY  m_customer.cust_id DESC

}
