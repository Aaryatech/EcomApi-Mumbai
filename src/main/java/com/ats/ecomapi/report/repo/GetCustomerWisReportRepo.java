package com.ats.ecomapi.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.report.model.GetCustomerWisReport;

public interface GetCustomerWisReportRepo extends JpaRepository<GetCustomerWisReport, Integer> {

	@Query(value="SELECT\n" + 
			"	UUID() as id,\n" + 
			"   SUM(h.grand_total) AS grand_total,\n" + 
			"   c.cust_name, c.cust_id,\n" + 
			"   c.cust_mobile_no,\n" + 
			"   c.date_of_birth\n" + 
			"FROM\n" + 
			"     t_sell_bill_header h,\n" + 
			"    m_customer c\n" + 
			"WHERE\n" + 
			"    h.del_status=1 AND\n" + 
			"    h.cust_id=c.cust_id AND\n" + 
			"    h.fr_id=:frId AND\n" + 
			"    h.bill_date BETWEEN :fromDate AND :toDate\n" + 
			"GROUP BY h.cust_id \n" + 
			"ORDER BY h.cust_id",nativeQuery=true)
	public List<GetCustomerWisReport> getCustWiseReport(@Param("frId") int frId, 
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
