package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.SellBillDetailForPos;


public interface SellBillDetailForPosRepository extends JpaRepository<SellBillDetailForPos, Integer>{

	@Query(value="SELECT\n" + 
			"    sd.*,\n" + 
			"    i.product_name as item_name,\n" + 
			"    uom.uom_name as item_uom,\n" + 
			"    i.ex_int2 AS is_decimal,\n" + 
			"    \"NA\" as item_hsncd,\n" + 
			"    c.cat_name\n" + 
			"FROM\n" + 
			"    t_sell_bill_detail sd,\n" + 
			"    m_product i,\n" + 
			"    m_uom uom,\n" + 
			"    m_category c\n" + 
			"WHERE\n" + 
			"    sell_bill_no = :billId AND \n" + 
			"    sd.item_id = i.product_id AND \n" + 
			"    uom.uom_id = i.uom_id AND \n" + 
			"    c.cat_id = sd.cat_id AND \n" + 
			"    sd.del_status = 1",nativeQuery=true)
	List<SellBillDetailForPos> getSellBillDetailForPos(@Param("billId") int billId);

	@Query(value="SELECT\n" + 
			"    sd.*,\n" + 
			"    i.product_name as item_name,\n" + 
			"    uom.uom_name as item_uom,\n" + 
			"    i.ex_int2 AS is_decimal,\n" + 
			"    \"NA\" as item_hsncd,\n" + 
			"    c.cat_name\n" + 
			"FROM\n" + 
			"    t_sell_bill_detail sd,\n" + 
			"    m_product i,\n" + 
			"    m_uom uom,\n" + 
			"    m_category c\n" + 
			"WHERE\n" + 
			"    sell_bill_detail_no = IN (:billDetailNoList) AND \n" + 
			"    sd.item_id = i.product_id AND \n" + 
			"    uom.uom_id = i.uom_id AND \n" + 
			"    c.cat_id = sd.cat_id AND \n" + 
			"    sd.del_status = 1",nativeQuery=true)
	List<SellBillDetailForPos> getSellBillDetailForPosDetail(@Param("billDetailNoList")List<Integer> billDetailNoList);
	
//	select sd.*,i.item_name,sp.item_uom,i.ext_int2 as is_decimal,sp.item_hsncd,c.cat_name from t_sell_bill_detail sd, m_item i,m_item_sup sp,m_category c "
//			+ "where sell_bill_detail_no IN (:billDetailNoList) and sd.item_id=i.id and sp.item_id=i.id and c.cat_id=sd.cat_id and sd.del_status=0

}
