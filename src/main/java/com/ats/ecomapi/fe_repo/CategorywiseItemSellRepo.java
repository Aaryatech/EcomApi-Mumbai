package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.CategorywiseItemSell;

public interface CategorywiseItemSellRepo extends JpaRepository<CategorywiseItemSell, Integer> {

	
	@Query(value = "SELECT\n" + 
			"		    t_sell_bill_detail.item_id,\n" + 
			"		    SUM(t_sell_bill_detail.grand_total)\n" + 
			"		    AS item_total,\n" + 
			"		    m_product.product_name as item_name\n" + 
			"		FROM\n" + 
			"		    t_sell_bill_detail,\n" + 
			"		    t_sell_bill_header,\n" + 
			"		    m_product\n" + 
			"		WHERE\n" + 
			"		    t_sell_bill_header.sell_bill_no = t_sell_bill_detail.sell_bill_no AND\n" + 
			"            t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \n" + 
			"            t_sell_bill_detail.del_status = 1 AND \n" + 
			"            t_sell_bill_header.fr_id =:frId AND \n" + 
			"            t_sell_bill_detail.cat_id =:catId AND  \n" + 
			"            t_sell_bill_detail.item_id=m_product.product_id\n" + 
			"            GROUP BY m_product.product_id ORDER BY item_total DESC LIMIT 5", nativeQuery = true)
	List<CategorywiseItemSell> getCategorywiseItemSellDesc(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("frId") int  frId,@Param("catId") int  catId);
	
	

	@Query(value = "SELECT\n" + 
			"		t_sell_bill_detail.item_id,\n" + 
			"		SUM(t_sell_bill_detail.grand_total)\n" + 
			"		AS item_total,\n" + 
			"		m_product.product_name as item_name\n" + 
			"FROM\n" + 
			"		t_sell_bill_detail,\n" + 
			"		t_sell_bill_header,\n" + 
			"		m_product\n" + 
			"WHERE\n" + 
			"		t_sell_bill_header.sell_bill_no = t_sell_bill_detail.sell_bill_no AND \n" + 
			"        t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \n" + 
			"        t_sell_bill_detail.del_status = 1 AND t_sell_bill_header.fr_id =:frId AND \n" + 
			"        t_sell_bill_detail.cat_id =:catId AND  \n" + 
			"        t_sell_bill_detail.item_id=m_product.product_id\n" + 
			"        GROUP BY m_product.product_id ORDER BY item_total ASC LIMIT 5", nativeQuery = true)
	List<CategorywiseItemSell> getCategorywiseItemSellAsc(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("frId") int  frId,@Param("catId") int  catId);
	
	
	@Query(value = "SELECT\n" + 
			"		t_sell_bill_detail.item_id,\n" + 
			"		SUM(t_sell_bill_detail.grand_total) AS item_total,\n" + 
			"		m_product.product_name as item_name\n" + 
			"FROM\n" + 
			"		t_sell_bill_detail,\n" + 
			"		t_sell_bill_header,\n" + 
			"		m_product\n" + 
			"WHERE\n" + 
			"		t_sell_bill_header.sell_bill_no = t_sell_bill_detail.sell_bill_no AND \n" + 
			"        t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \n" + 
			"        t_sell_bill_detail.del_status = 1 AND \n" + 
			"        t_sell_bill_header.fr_id =:frId AND \n" + 
			"        t_sell_bill_detail.cat_id =:catId AND  \n" + 
			"        t_sell_bill_detail.item_id=m_product.product_id \n" + 
			"        GROUP BY m_product.product_id ORDER BY item_total DESC", nativeQuery = true)
	List<CategorywiseItemSell> getCategorywiseItemSellAll(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("frId") int  frId,@Param("catId") int  catId);
	
	
}
