package com.ats.ecomapi.offer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.report.model.GetItemSubCatWise;

public interface GetItemSubCatWiseRepo extends JpaRepository<GetItemSubCatWise, Integer> {

	@Query(value="SELECT\n" + 
			"	   UUID() AS id,      \n" + 
			"       sub_cat.sub_cat_id,\n" + 
			"       sub_cat.sub_cat_name,\n" + 
			"       p.product_id,\n" + 
			"       p.product_code,\n" + 
			"       p.product_name,\n" + 
			"       SUM(dtl.ex_float3) AS qty,\n" + 
			"       0 as total_sale\n" + 
			"    FROM\n" + 
			"        tn_order_header h,\n" + 
			"        tn_order_detail dtl,\n" + 
			"        m_product p,\n" + 
			"        m_sub_category sub_cat\n" + 
			"    WHERE\n" + 
			"    	sub_cat.sub_cat_id=:subCatId AND\n" + 
			"    	 dtl.order_id=h.order_id AND\n" + 
			"        dtl.item_id=p.product_id AND\n" + 
			"        p.prod_sub_cat_id=sub_cat.sub_cat_id AND\n" + 
			"        h.del_status = 1 AND\n" + 
			"        h.ex_int1 IN(:compIds) AND \n" + 
			"        h.delivery_date BETWEEN :fromDate AND :toDate AND\n" + 
			"        h.order_status IN(:orderStatus) AND \n" + 
			"        h.payment_method = :paymentMethod\n" + 
			"    GROUP BY p.product_id\n" + 
			"    ORDER BY qty DESC\n" + 
			"    LIMIT 0,10;",nativeQuery=true)
	List<GetItemSubCatWise> getSubCatItems(@Param("subCatId") int subCatId, @Param("fromDate") String fromDate, @Param("toDate") String toDate, 
			@Param("orderStatus") List<String> orderStatus, @Param("compIds") List<String> compIds, @Param("paymentMethod") int paymentMethod);
	
	
	@Query(value="SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        sub_cat.sub_cat_id,\n" + 
			"        sub_cat.sub_cat_name,\n" + 
			"        p.product_id,\n" + 
			"        p.product_code,\n" + 
			"        p.product_name,\n" + 
			"        0 AS qty,\n" + 
			"        SUM(dtl.total_amt) total_sale     \n" + 
			"    FROM\n" + 
			"        tn_order_header h,\n" + 
			"        tn_order_detail dtl,\n" + 
			"        m_product p,\n" + 
			"        m_sub_category sub_cat     \n" + 
			"    WHERE\n" + 
			"        sub_cat.sub_cat_id=:subCatId \n" + 
			"        AND       dtl.order_id=h.order_id \n" + 
			"        AND         dtl.item_id=p.product_id \n" + 
			"        AND         p.prod_sub_cat_id=sub_cat.sub_cat_id \n" + 
			"        AND         h.del_status = 1 \n" + 
			"        AND         h.ex_int1 IN (:compIds) \n" + 
			"        AND          h.delivery_date BETWEEN :fromDate AND :toDate\n" + 
			"        AND         h.order_status IN(:orderStatus) \n" + 
			"        AND          h.payment_method = :paymentMethod       \n" + 
			"    GROUP BY\n" + 
			"        p.product_id     \n" + 
			"    ORDER BY\n" + 
			"        qty DESC     LIMIT 0,\n" + 
			"        10;",nativeQuery=true)
	List<GetItemSubCatWise> getSubCatTotalSaleItems(@Param("subCatId") int subCatId, @Param("fromDate") String fromDate, @Param("toDate") String toDate, 
			@Param("orderStatus") List<String> orderStatus, @Param("compIds") List<String> compIds, @Param("paymentMethod") int paymentMethod);
}
