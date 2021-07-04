package com.ats.ecomapi.offer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.report.model.ShowPieChartData;

public interface ShowPieChartDataRepo extends JpaRepository<ShowPieChartData, Integer> {

	@Query(value="SELECT\n" + 
			"	   UUID() AS id,\n" + 
			"       c.cat_id,\n" + 
			"       c.cat_name,\n" + 
			"       c.cat_prefix,\n" + 
			"       SUM(dtl.ex_float3) AS qty,\n" + 
			"       0 AS sub_cat_id,\n" + 
			"       'NA' AS sub_cat_name,\n" + 
			"       'NA'  AS sub_cat_code\n" + 
			"    FROM\n" + 
			"        tn_order_header h,\n" + 
			"        tn_order_detail dtl,\n" + 
			"        m_product p,\n" + 
			"        m_category c\n" + 
			"    WHERE\n" + 
			"    	dtl.order_id=h.order_id AND\n" + 
			"        dtl.item_id=p.product_id AND\n" + 
			"        p.prod_cat_id=c.cat_id AND\n" + 
			"        h.del_status = 1 AND\n" + 
			"        h.ex_int1 IN(:compIds) AND \n" + 
			"        h.delivery_date BETWEEN :fromDate AND :toDate AND\n" + 
			"        h.order_status IN(:orderStatus) AND \n" + 
			"        h.payment_method =:paymentMethod\n" + 
			"    GROUP BY c.cat_id",nativeQuery=true)
	List<ShowPieChartData> getCatQtyChart(@Param("fromDate") String fromDate, @Param("toDate") String toDate, 
			@Param("orderStatus") List<String> orderStatus, @Param("compIds") List<String> compIds, @Param("paymentMethod") int paymentMethod);
	
	@Query(value="SELECT\n" + 
			"	   UUID() AS id,\n" + 
			"       c.cat_id,\n" + 
			" 		'NA' AS cat_name,\n" + 
			"       'NA' AS cat_prefix,\n"+
			"       sub_cat.sub_cat_id,\n" + 
			"       sub_cat.sub_cat_name,\n" + 
			"       sub_cat.sub_cat_code,\n" + 
			"       SUM(dtl.ex_float3) AS qty\n" + 
			"    FROM\n" + 
			"        tn_order_header h,\n" + 
			"        tn_order_detail dtl,\n" + 
			"        m_product p,\n" + 
			"        m_category c,\n" + 
			"        m_sub_category sub_cat\n" + 
			"    WHERE\n" + 
			"    	 c.cat_id=:catId AND dtl.order_id=h.order_id AND\n" + 
			"        dtl.item_id=p.product_id AND\n" + 
			"        p.prod_sub_cat_id=sub_cat.sub_cat_id AND\n" + 
			"        c.cat_id=sub_cat.cat_id AND\n" + 
			"        h.del_status = 1 AND\n" + 
			"        h.ex_int1 IN(:compIds) AND \n" + 
			"        h.delivery_date BETWEEN :fromDate AND :toDate AND\n" + 
			"        h.order_status IN(:orderStatus) AND \n" + 
			"        h.payment_method =:paymentMethod\n" + 
			"    GROUP BY sub_cat.sub_cat_id",nativeQuery=true)
	List<ShowPieChartData> getSubCatQtyChart(@Param("catId") int catId, @Param("fromDate") String fromDate, @Param("toDate") String toDate, 
			@Param("orderStatus") List<String> orderStatus, @Param("compIds") List<String> compIds, @Param("paymentMethod") int paymentMethod);
	
	
	
	/*---------------------------------------------------------------------------------------------*/
	@Query(value="SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        c.cat_id,\n" + 
			"        c.cat_name,\n" + 
			"        c.cat_prefix,\n" + 
			"        SUM(dtl.total_amt) AS qty,\n" + 
			"        0 AS sub_cat_id,\n" + 
			"        'NA' AS sub_cat_name,\n" + 
			"        'NA'  AS sub_cat_code     \n" + 
			"    FROM\n" + 
			"        tn_order_header h,\n" + 
			"        tn_order_detail dtl,\n" + 
			"        m_product p,\n" + 
			"        m_category c     \n" + 
			"    WHERE\n" + 
			"        dtl.order_id=h.order_id \n" + 
			"        AND   dtl.item_id=p.product_id \n" + 
			"        AND   p.prod_cat_id=c.cat_id \n" + 
			"        AND   h.del_status = 1 \n" + 
			"        AND   h.ex_int1 IN(:compIds) \n" + 
			"        AND   h.delivery_date BETWEEN :fromDate AND :toDate \n" + 
			"        AND   h.order_status IN(:orderStatus) \n" + 
			"        AND   h.payment_method =:paymentMethod     \n" + 
			"    GROUP BY\n" + 
			"        c.cat_id",nativeQuery=true)
	List<ShowPieChartData> getCatTotalSaleChart(@Param("fromDate") String fromDate, @Param("toDate") String toDate, 
			@Param("orderStatus") List<String> orderStatus, @Param("compIds") List<String> compIds, @Param("paymentMethod") int paymentMethod);
	
	@Query(value="SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        c.cat_id,\n" + 
			"        'NA' AS cat_name,\n" + 
			"        'NA' AS cat_prefix,\n" + 
			"        sub_cat.sub_cat_id,\n" + 
			"        sub_cat.sub_cat_name,\n" + 
			"        sub_cat.sub_cat_code,\n" + 
			"        SUM(dtl.total_amt) AS qty     \n" + 
			"    FROM\n" + 
			"        tn_order_header h,\n" + 
			"        tn_order_detail dtl,\n" + 
			"        m_product p,\n" + 
			"        m_category c,\n" + 
			"        m_sub_category sub_cat     \n" + 
			"    WHERE\n" + 
			"        c.cat_id=:catId \n" + 
			"        AND dtl.order_id=h.order_id \n" + 
			"        AND         dtl.item_id=p.product_id \n" + 
			"        AND         p.prod_sub_cat_id=sub_cat.sub_cat_id \n" + 
			"        AND         c.cat_id=sub_cat.cat_id \n" + 
			"        AND         h.del_status = 1 \n" + 
			"        AND         h.ex_int1 IN(:compIds) \n" + 
			"        AND         h.delivery_date BETWEEN :fromDate AND :toDate \n" + 
			"        AND         h.order_status IN(:orderStatus) \n" + 
			"        AND         h.payment_method =:paymentMethod    \n" + 
			"    GROUP BY\n" + 
			"        sub_cat.sub_cat_id",nativeQuery=true)
	List<ShowPieChartData> getTotalSaleByCatIdChart(@Param("catId") int catId, @Param("fromDate") String fromDate, @Param("toDate") String toDate, 
			@Param("orderStatus") List<String> orderStatus, @Param("compIds") List<String> compIds, @Param("paymentMethod") int paymentMethod);
}
