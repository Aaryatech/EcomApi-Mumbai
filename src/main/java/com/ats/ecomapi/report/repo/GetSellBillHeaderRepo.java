package com.ats.ecomapi.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.report.model.GetSellBillHeader;

public interface GetSellBillHeaderRepo extends JpaRepository<GetSellBillHeader, Integer> {

	@Query(value="SELECT t1.*,\n" + 
			"	t2.delvr_boy_name,\n" + 
			"   t2.delvr_boy_mob_no, t2.del_boy_id\n" + 
			"FROM\n" + 
			"(SELECT\n" + 
			"    *\n" + 
			"FROM   \n" + 
			"    t_sell_bill_header h    \n" + 
			"WHERE\n" + 
			"    h.del_status=1 AND\n" + 
			"    h.is_dairy_mart_bill=:compId AND\n" + 
			"    h.fr_id IN (:frId) AND h.cust_id=:custId AND\n" + 
			"    h.bill_date BETWEEN :fromDate AND :toDate) t1\n" + 
			"   LEFT JOIN(\n" + 
			"       SELECT 	\n" + 
			"		h.order_id,\n" + 
			"		del_boy.del_boy_id,\n" + 
			"        CONCAT (del_boy.first_name,\n" + 
			"        ' ',\n" + 
			"        del_boy.last_name) as delvr_boy_name,\n" + 
			"        del_boy.mobile_no  as delvr_boy_mob_no   \n" + 
			"    FROM       \n" + 
			"        m_delivery_boy del_boy,\n" + 
			"        tn_order_header h\n" + 
			"    WHERE    \n" + 
			"    del_boy.del_status=1 AND\n" + 
			"    h.order_delivered_by = del_boy.del_boy_id) t2\n" + 
			"    ON t1.ext_int2=t2.order_id",nativeQuery=true)
	public List<GetSellBillHeader> getCustPrchsDetailReport(@Param("compId") int compId, @Param("frId") List<Integer> frId,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("custId") int custId);
	
	@Query(value="SELECT\n" + 
			"        t1.*,\n" + 
			"        t2.delvr_boy_name,\n" + 
			"        t2.delvr_boy_mob_no,\n" + 
			"        t2.del_boy_id \n" + 
			"    FROM\n" + 
			"        (SELECT\n" + 
			"            * \n" + 
			"        FROM\n" + 
			"            t_sell_bill_header h     \n" + 
			"        WHERE\n" + 
			"            h.del_status=1 \n" + 
			"            AND     h.is_dairy_mart_bill=:compId\n" + 
			"            AND     h.fr_id IN (:frId)\n" + 
			"            AND     h.bill_date BETWEEN :fromDate AND :toDate) t1    \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                h.order_id,\n" + 
			"                del_boy.del_boy_id,\n" + 
			"                CONCAT (del_boy.first_name,\n" + 
			"                ' ',\n" + 
			"                del_boy.last_name) as delvr_boy_name,\n" + 
			"                del_boy.mobile_no  as delvr_boy_mob_no        \n" + 
			"            FROM\n" + 
			"                m_delivery_boy del_boy,\n" + 
			"                tn_order_header h     \n" + 
			"            WHERE\n" + 
			"                del_boy.del_status=1 \n" + 
			"                AND     h.order_delivered_by = del_boy.del_boy_id\n" + 
			"        ) t2     \n" + 
			"            ON t1.ext_int2=t2.order_id", nativeQuery=true)
	public List<GetSellBillHeader> getCustOrderDetailReport(@Param("compId") int compId, @Param("frId") List<Integer> frId,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
