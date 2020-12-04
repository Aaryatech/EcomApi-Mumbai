package com.ats.ecomapi.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.report.model.GetDateWiseBillReport;

public interface GetDateWiseBillReportRepo extends JpaRepository<GetDateWiseBillReport, Integer>{

	@Query(value="Select\n" + 
			"	 UUID() AS id,\n" + 
			"    t1.*,\n" + 
			"	 COALESCE(t2.cod,0) AS cod,\n" + 
			"    COALESCE(t3.card,0) AS card,\n" + 
			"    COALESCE(t4.epay,0) AS epay\n" + 
			"FROM\n" + 
			"(SELECT\n" + 
			"    h.bill_date,\n" + 
			"    SUM(h.grand_total) as total_amt,\n" + 
			"    COUNT(h.sell_bill_no) as total_bills,\n" + 
			" 	h.fr_id, \n" +
			"   MONTHNAME(h.bill_date) as month_name,\n" + 
			"   YEAR(h.bill_date) as order_year\n" + 
			"FROM\n" + 
			"    t_sell_bill_header h\n" + 
			"WHERE\n" + 
			"    h.del_status=1 AND\n" + 
			"    h.bill_date BETWEEN :fromDate AND :toDate AND\n" + 
			"    h.fr_id IN (:frId)\n" + 
			" GROUP BY h.fr_id, h.bill_date  ORDER BY h.bill_date DESC) t1\n" + 
			" LEFT JOIN\n" + 
			" (SELECT\n" + 
			"    	COUNT(sell_bill_no) as cod,\n" + 
			"  		bill_date, fr_id\n" + 
			"	FROM\n" + 
			"   		 t_sell_bill_header\n" + 
			"	WHERE\n" + 
			"   		 payment_mode = 1 AND bill_date BETWEEN :fromDate AND :toDate AND del_status = 1 AND fr_id IN (:frId) GROUP BY fr_id, bill_date  ORDER BY bill_date DESC) t2\n" + 
			"         ON t1.bill_date=t2.bill_date AND t1.fr_id=t2.fr_id\n" + 
			"     LEFT JOIN\n" + 
			" (SELECT\n" + 
			"    	COUNT(sell_bill_no) as card,\n" + 
			"  		bill_date, fr_id\n" + 
			"	FROM\n" + 
			"   		 t_sell_bill_header\n" + 
			"	WHERE\n" + 
			"   		 payment_mode = 2 AND bill_date BETWEEN :fromDate AND :toDate AND del_status = 1 AND fr_id IN (:frId) GROUP BY fr_id, bill_date  ORDER BY bill_date DESC) t3\n" + 
			"         ON t1.bill_date=t3.bill_date AND t1.fr_id=t3.fr_id \n" + 
			"     LEFT JOIN\n" + 
			" (SELECT\n" + 
			"    	COUNT(sell_bill_no) as epay,\n" + 
			"  		bill_date, fr_id\n" + 
			"	FROM\n" + 
			"   		 t_sell_bill_header\n" + 
			"	WHERE\n" + 
			"   		 payment_mode = 3 AND bill_date BETWEEN :fromDate AND :toDate AND del_status = 1 AND fr_id IN (:frId) GROUP BY fr_id, bill_date  ORDER BY bill_date DESC) t4\n" + 
			"         ON t1.bill_date=t4.bill_date AND t1.fr_id=t4.fr_id",nativeQuery=true)
	public List<GetDateWiseBillReport> getDateWiseBills(@Param("frId") List<Integer> frId, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate);
}
