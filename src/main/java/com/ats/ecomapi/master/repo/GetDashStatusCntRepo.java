package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.report.model.GetDashPieStatusCnt;

public interface GetDashStatusCntRepo extends JpaRepository<GetDashPieStatusCnt, Integer> {

	@Query(value=" SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        COUNT(head.order_status) order_status_cnt,\n" + 
			"        head.order_status,\n" + 
			"        CASE                                                       \n" + 
			"            WHEN head.order_status=1 THEN 'Pending'                                 \n" + 
			"            WHEN head.order_status=2 THEN 'Accept'                                  \n" + 
			"            WHEN head.order_status=3 THEN 'Processing'                                   \n" + 
			"            WHEN head.order_status=4 THEN 'Bill Generated'                                    \n" + 
			"            WHEN head.order_status=5 THEN 'Delivered'                                    \n" + 
			"            WHEN head.order_status=7 THEN 'Return Order'                                      \n" + 
			"            WHEN head.order_status=8 THEN 'Cancelled Order'                                 \n" + 
			"            ELSE  ''                                 \n" + 
			"        END AS status_name,\n" + 
			"        'NA' AS fr_name,\n" + 
			"        head.ex_int1,\n" + 
			"        head.ex_int2,\n" + 
			"        head.ex_int3,\n" + 
			"        head.ex_float1,\n" + 
			"        head.ex_float2,\n" + 
			"        head.ex_float3,\n" + 
			"        head.ex_var1,\n" + 
			"        head.ex_var2,\n" + 
			"        head.ex_var3                 \n" + 
			"    FROM\n" + 
			"        tn_order_header head        \n" + 
			"    WHERE\n" + 
			"        head.del_status=1                     \n" + 
			"        AND    head.ex_int1=:compId                   \n" + 
			"        AND    head.delivery_date BETWEEN :fromDate AND :toDate                       \n" + 
			"                \n" + 
			"    GROUP BY head.order_status\n" + 
			"       ",nativeQuery=true)
	List<GetDashPieStatusCnt> getDashStatusCnt(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate, @Param("compId") int compId);
}

	


