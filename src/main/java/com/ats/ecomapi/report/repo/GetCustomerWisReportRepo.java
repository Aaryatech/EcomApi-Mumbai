package com.ats.ecomapi.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.report.model.GetCustomerWisReport;

public interface GetCustomerWisReportRepo extends JpaRepository<GetCustomerWisReport, Integer> {

	@Query(value="SELECT\n" + 
			"        UUID() as id,\n" + 
			"        SUM(h.grand_total) AS grand_total,\n" + 
			"        c.cust_name,\n" + 
			"        c.cust_id,\n" + 
			"        c.cust_mobile_no,\n" + 
			"        c.date_of_birth, \n" + 
			"       	concat(fr.fr_code,' ', fr.fr_name) AS fr_name\n" + 
			"    FROM\n" + 
			"        t_sell_bill_header h,\n" + 
			"        m_customer c,\n" + 
			"        m_franchise fr\n" + 
			"    WHERE\n" + 
			"        h.del_status=1 \n" + 
			"        AND     h.cust_id=c.cust_id \n" + 
			"        AND     h.fr_id IN (:frId) \n" + 
			"        AND     h.bill_date BETWEEN :fromDate AND :toDate\n" + 
			"        AND h.fr_id=fr.fr_id\n" + 
			"    GROUP BY\n" + 
			"        h.cust_id  \n" + 
			"    ORDER BY\n" + 
			"        h.cust_id",nativeQuery=true)
	public List<GetCustomerWisReport> getCustWiseReport(@Param("frId") List<Integer> frId, 
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
