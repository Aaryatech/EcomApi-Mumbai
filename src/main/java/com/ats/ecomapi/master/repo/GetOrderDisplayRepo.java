package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetOrderDisplay;


public interface GetOrderDisplayRepo extends JpaRepository<GetOrderDisplay, Integer> {

	//---------BETWEEN DATE-----------------
	@Query(value="SELECT\r\n" + 
			"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
			"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
			"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
			"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
			"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
			"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
			"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
			"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
			"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
			"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
			"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
			"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
			"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
			"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
			"    END AS order_delivered_by_name,\r\n" + 
			"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
			"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
			"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
			"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display, MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        h.*,\r\n" + 
			"        d.order_detail_id,\r\n" + 
			"        d.item_id,\r\n" + 
			"        d.hsn_code,\r\n" + 
			"        d.qty,\r\n" + 
			"        d.mrp,\r\n" + 
			"        d.rate,\r\n" + 
			"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
			"        d.cgst_per,\r\n" + 
			"        d.sgst_per,\r\n" + 
			"        d.igst_per,\r\n" + 
			"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
			"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
			"        d.igst_amt AS detail_igst_amt,\r\n" + 
			"        d.disc_amt AS detail_disc_amt,\r\n" + 
			"        d.tax_amt AS detail_tax_amt,\r\n" + 
			"        d.total_amt AS detail_total_amt,\r\n" + 
			"        d.remark AS detail_remark,\r\n" + 
			"        d.ex_int1 AS detail_ex_int1,\r\n" + 
			"        d.ex_int2 AS detail_ex_int2,\r\n" + 
			"        d.ex_int3 AS detail_ex_int3,\r\n" + 
			"        d.ex_int4 AS detail_ex_int4,\r\n" + 
			"        d.ex_var1 AS detail_ex_var1,\r\n" + 
			"        d.ex_var2 AS detail_ex_var2,\r\n" + 
			"        d.ex_var3 AS detail_ex_var3,\r\n" + 
			"        d.ex_var4 AS detail_ex_var4,\r\n" + 
			"        d.ex_float1 AS detail_ex_float1,\r\n" + 
			"        d.ex_float2 AS detail_ex_float2,\r\n" + 
			"        d.ex_float3 AS detail_ex_float3,\r\n" + 
			"        d.ex_float4 AS detail_ex_float4\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_header h,\r\n" + 
			"        tn_order_detail d\r\n" + 
			"    WHERE\r\n" + 
			"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND h.delivery_date BETWEEN :fromDate AND :toDate \r\n" + 
			"    ORDER BY\r\n" + 
			"        h.order_id\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id,\r\n" + 
			"        c.cust_name,\r\n" + 
			"        c.phone_number,\r\n" + 
			"        c.whatsapp_no\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.cust_id = t2.cust_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        f.fr_id,\r\n" + 
			"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
			"    FROM\r\n" + 
			"        m_franchisee f\r\n" + 
			"    WHERE\r\n" + 
			"        f.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.fr_id = t3.fr_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        i.id,\r\n" + 
			"        i.item_name,\r\n" + 
			"        i.item_grp1 AS cat_id,\r\n" + 
			"        s.item_uom,\r\n" + 
			"        s.uom_id,\r\n" + 
			"        c.cat_name\r\n" + 
			"    FROM\r\n" + 
			"        m_item i,\r\n" + 
			"        m_item_sup s,\r\n" + 
			"        m_category c\r\n" + 
			"    WHERE\r\n" + 
			"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.item_id = t4.id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.city_id,\r\n" + 
			"        c.city_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_city c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t5\r\n" + 
			"ON\r\n" + 
			"    t1.city_id = t5.city_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        a.area_id,\r\n" + 
			"        a.area_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_area a\r\n" + 
			"    WHERE\r\n" + 
			"        a.del_status = 0\r\n" + 
			") t6\r\n" + 
			"ON\r\n" + 
			"    t1.area_id = t6.area_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        h.offer_id,\r\n" + 
			"        h.offer_name,\r\n" + 
			"        h.offer_desc\r\n" + 
			"    FROM\r\n" + 
			"        mn_offer_header h\r\n" + 
			"    WHERE\r\n" + 
			"        h.del_status = 0\r\n" + 
			") t7\r\n" + 
			"ON\r\n" + 
			"    t1.offer_id = t7.offer_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            e.fr_emp_name,\r\n" + 
			"            ' - ',\r\n" + 
			"            e.fr_emp_contact\r\n" + 
			"        ) AS del_boy_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t8\r\n" + 
			"ON\r\n" + 
			"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        a.agent_id,\r\n" + 
			"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_agent a\r\n" + 
			"    WHERE\r\n" + 
			"        a.del_status = 0\r\n" + 
			") t9\r\n" + 
			"ON\r\n" + 
			"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
	List<GetOrderDisplay> getAllOrdersByDeliveryDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	
	//---------BETWEEN DATE AND STATUS-----------------
	@Query(value="SELECT\r\n" + 
			"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
			"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
			"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
			"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
			"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
			"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
			"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
			"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
			"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
			"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
			"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
			"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
			"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
			"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
			"    END AS order_delivered_by_name,\r\n" + 
			"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
			"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
			"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
			"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display, MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        h.*,\r\n" + 
			"        d.order_detail_id,\r\n" + 
			"        d.item_id,\r\n" + 
			"        d.hsn_code,\r\n" + 
			"        d.qty,\r\n" + 
			"        d.mrp,\r\n" + 
			"        d.rate,\r\n" + 
			"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
			"        d.cgst_per,\r\n" + 
			"        d.sgst_per,\r\n" + 
			"        d.igst_per,\r\n" + 
			"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
			"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
			"        d.igst_amt AS detail_igst_amt,\r\n" + 
			"        d.disc_amt AS detail_disc_amt,\r\n" + 
			"        d.tax_amt AS detail_tax_amt,\r\n" + 
			"        d.total_amt AS detail_total_amt,\r\n" + 
			"        d.remark AS detail_remark,\r\n" + 
			"        d.ex_int1 AS detail_ex_int1,\r\n" + 
			"        d.ex_int2 AS detail_ex_int2,\r\n" + 
			"        d.ex_int3 AS detail_ex_int3,\r\n" + 
			"        d.ex_int4 AS detail_ex_int4,\r\n" + 
			"        d.ex_var1 AS detail_ex_var1,\r\n" + 
			"        d.ex_var2 AS detail_ex_var2,\r\n" + 
			"        d.ex_var3 AS detail_ex_var3,\r\n" + 
			"        d.ex_var4 AS detail_ex_var4,\r\n" + 
			"        d.ex_float1 AS detail_ex_float1,\r\n" + 
			"        d.ex_float2 AS detail_ex_float2,\r\n" + 
			"        d.ex_float3 AS detail_ex_float3,\r\n" + 
			"        d.ex_float4 AS detail_ex_float4\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_header h,\r\n" + 
			"        tn_order_detail d\r\n" + 
			"    WHERE\r\n" + 
			"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND h.delivery_date BETWEEN :fromDate AND :toDate AND h.order_status IN(:status) \r\n" + 
			"    ORDER BY\r\n" + 
			"        h.order_id\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id,\r\n" + 
			"        c.cust_name,\r\n" + 
			"        c.phone_number,\r\n" + 
			"        c.whatsapp_no\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.cust_id = t2.cust_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        f.fr_id,\r\n" + 
			"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
			"    FROM\r\n" + 
			"        m_franchise f\r\n" + 
			"    WHERE\r\n" + 
			"        f.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.fr_id = t3.fr_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        i.id,\r\n" + 
			"        i.item_name,\r\n" + 
			"        i.item_grp1 AS cat_id,\r\n" + 
			"        s.item_uom,\r\n" + 
			"        s.uom_id,\r\n" + 
			"        c.cat_name\r\n" + 
			"    FROM\r\n" + 
			"        m_item i,\r\n" + 
			"        m_item_sup s,\r\n" + 
			"        m_category c\r\n" + 
			"    WHERE\r\n" + 
			"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.item_id = t4.id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.city_id,\r\n" + 
			"        c.city_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_city c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t5\r\n" + 
			"ON\r\n" + 
			"    t1.city_id = t5.city_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        a.area_id,\r\n" + 
			"        a.area_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_area a\r\n" + 
			"    WHERE\r\n" + 
			"        a.del_status = 0\r\n" + 
			") t6\r\n" + 
			"ON\r\n" + 
			"    t1.area_id = t6.area_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        h.offer_id,\r\n" + 
			"        h.offer_name,\r\n" + 
			"        h.offer_desc\r\n" + 
			"    FROM\r\n" + 
			"        mn_offer_header h\r\n" + 
			"    WHERE\r\n" + 
			"        h.del_status = 0\r\n" + 
			") t7\r\n" + 
			"ON\r\n" + 
			"    t1.offer_id = t7.offer_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            e.fr_emp_name,\r\n" + 
			"            ' - ',\r\n" + 
			"            e.fr_emp_contact\r\n" + 
			"        ) AS del_boy_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t8\r\n" + 
			"ON\r\n" + 
			"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        a.agent_id,\r\n" + 
			"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_agent a\r\n" + 
			"    WHERE\r\n" + 
			"        a.del_status = 0\r\n" + 
			") t9\r\n" + 
			"ON\r\n" + 
			"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
	List<GetOrderDisplay> getAllOrdersByDeliveryDateAndStatus(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("status") List<Integer> status);

	
		//---------BETWEEN DATE AND STATUS AND FRANCHISE-----------------
		@Query(value="SELECT\r\n" + 
				"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
				"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
				"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
				"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
				"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
				"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
				"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
				"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
				"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
				"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
				"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
				"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
				"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
				"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
				"    END AS order_delivered_by_name,\r\n" + 
				"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
				"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
				"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
				"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
				"    MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
				"FROM\r\n" + 
				"    (\r\n" + 
				"    SELECT\r\n" + 
				"        h.*,\r\n" + 
				"        d.order_detail_id,\r\n" + 
				"        d.item_id,\r\n" + 
				"        d.hsn_code,\r\n" + 
				"        d.qty,\r\n" + 
				"        d.mrp,\r\n" + 
				"        d.rate,\r\n" + 
				"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
				"        d.cgst_per,\r\n" + 
				"        d.sgst_per,\r\n" + 
				"        d.igst_per,\r\n" + 
				"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
				"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
				"        d.igst_amt AS detail_igst_amt,\r\n" + 
				"        d.disc_amt AS detail_disc_amt,\r\n" + 
				"        d.tax_amt AS detail_tax_amt,\r\n" + 
				"        d.total_amt AS detail_total_amt,\r\n" + 
				"        d.remark AS detail_remark,\r\n" + 
				"        d.ex_int1 AS detail_ex_int1,\r\n" + 
				"        d.ex_int2 AS detail_ex_int2,\r\n" + 
				"        d.ex_int3 AS detail_ex_int3,\r\n" + 
				"        d.ex_int4 AS detail_ex_int4,\r\n" + 
				"        d.ex_var1 AS detail_ex_var1,\r\n" + 
				"        d.ex_var2 AS detail_ex_var2,\r\n" + 
				"        d.ex_var3 AS detail_ex_var3,\r\n" + 
				"        d.ex_var4 AS detail_ex_var4,\r\n" + 
				"        d.ex_float1 AS detail_ex_float1,\r\n" + 
				"        d.ex_float2 AS detail_ex_float2,\r\n" + 
				"        d.ex_float3 AS detail_ex_float3,\r\n" + 
				"        d.ex_float4 AS detail_ex_float4\r\n" + 
				"    FROM\r\n" + 
				"        tn_order_header h,\r\n" + 
				"        tn_order_detail d\r\n" + 
				"    WHERE\r\n" + 
				"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND h.delivery_date BETWEEN :fromDate AND :toDate AND h.order_status IN(:status) AND h.fr_id IN(:frIds)\r\n" + 
				"    ORDER BY\r\n" + 
				"        h.order_id\r\n" + 
				") t1\r\n" + 
				"LEFT JOIN(\r\n" + 
				"    SELECT\r\n" + 
				"        c.cust_id,\r\n" + 
				"        c.cust_name,\r\n" + 
				"        c.phone_number,\r\n" + 
				"        c.whatsapp_no\r\n" + 
				"    FROM\r\n" + 
				"        m_customer c\r\n" + 
				"    WHERE\r\n" + 
				"        c.del_status = 0\r\n" + 
				") t2\r\n" + 
				"ON\r\n" + 
				"    t1.cust_id = t2.cust_id\r\n" + 
				"LEFT JOIN(\r\n" + 
				"    SELECT\r\n" + 
				"        f.fr_id,\r\n" + 
				"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
				"    FROM\r\n" + 
				"        m_franchise f\r\n" + 
				"    WHERE\r\n" + 
				"        f.del_status = 0\r\n" + 
				") t3\r\n" + 
				"ON\r\n" + 
				"    t1.fr_id = t3.fr_id\r\n" + 
				"LEFT JOIN(\r\n" + 
				"    SELECT\r\n" + 
				"        i.id,\r\n" + 
				"        i.item_name,\r\n" + 
				"        i.item_grp1 AS cat_id,\r\n" + 
				"        s.item_uom,\r\n" + 
				"        s.uom_id,\r\n" + 
				"        c.cat_name\r\n" + 
				"    FROM\r\n" + 
				"        m_item i,\r\n" + 
				"        m_item_sup s,\r\n" + 
				"        m_category c\r\n" + 
				"    WHERE\r\n" + 
				"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
				") t4\r\n" + 
				"ON\r\n" + 
				"    t1.item_id = t4.id\r\n" + 
				"LEFT JOIN(\r\n" + 
				"    SELECT\r\n" + 
				"        c.city_id,\r\n" + 
				"        c.city_name\r\n" + 
				"    FROM\r\n" + 
				"        mn_city c\r\n" + 
				"    WHERE\r\n" + 
				"        c.del_status = 0\r\n" + 
				") t5\r\n" + 
				"ON\r\n" + 
				"    t1.city_id = t5.city_id\r\n" + 
				"LEFT JOIN(\r\n" + 
				"    SELECT\r\n" + 
				"        a.area_id,\r\n" + 
				"        a.area_name\r\n" + 
				"    FROM\r\n" + 
				"        mn_area a\r\n" + 
				"    WHERE\r\n" + 
				"        a.del_status = 0\r\n" + 
				") t6\r\n" + 
				"ON\r\n" + 
				"    t1.area_id = t6.area_id\r\n" + 
				"LEFT JOIN(\r\n" + 
				"    SELECT\r\n" + 
				"        h.offer_id,\r\n" + 
				"        h.offer_name,\r\n" + 
				"        h.offer_desc\r\n" + 
				"    FROM\r\n" + 
				"        mn_offer_header h\r\n" + 
				"    WHERE\r\n" + 
				"        h.del_status = 0\r\n" + 
				") t7\r\n" + 
				"ON\r\n" + 
				"    t1.offer_id = t7.offer_id\r\n" + 
				"LEFT JOIN(\r\n" + 
				"    SELECT\r\n" + 
				"        e.fr_emp_id,\r\n" + 
				"        CONCAT(\r\n" + 
				"            e.fr_emp_name,\r\n" + 
				"            ' - ',\r\n" + 
				"            e.fr_emp_contact\r\n" + 
				"        ) AS del_boy_name\r\n" + 
				"    FROM\r\n" + 
				"        m_fr_emp e\r\n" + 
				"    WHERE\r\n" + 
				"        e.del_status = 0\r\n" + 
				") t8\r\n" + 
				"ON\r\n" + 
				"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
				"LEFT JOIN(\r\n" + 
				"    SELECT\r\n" + 
				"        a.agent_id,\r\n" + 
				"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
				"    FROM\r\n" + 
				"        mn_agent a\r\n" + 
				"    WHERE\r\n" + 
				"        a.del_status = 0\r\n" + 
				") t9\r\n" + 
				"ON\r\n" + 
				"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
		List<GetOrderDisplay> getAllOrdersByDeliveryDateAndStatusAndFr(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("status") List<Integer> status, @Param("frIds") List<Integer> frIds);

		//---------FOR MONTH AND STATUS AND FRANCHISE-----------------
				@Query(value="SELECT\r\n" + 
						"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
						"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
						"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
						"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
						"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
						"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
						"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
						"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
						"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
						"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
						"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
						"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
						"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
						"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
						"    END AS order_delivered_by_name,\r\n" + 
						"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
						"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
						"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
						"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
						"    MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
						"FROM\r\n" + 
						"    (\r\n" + 
						"    SELECT\r\n" + 
						"        h.*,\r\n" + 
						"        d.order_detail_id,\r\n" + 
						"        d.item_id,\r\n" + 
						"        d.hsn_code,\r\n" + 
						"        d.qty,\r\n" + 
						"        d.mrp,\r\n" + 
						"        d.rate,\r\n" + 
						"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
						"        d.cgst_per,\r\n" + 
						"        d.sgst_per,\r\n" + 
						"        d.igst_per,\r\n" + 
						"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
						"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
						"        d.igst_amt AS detail_igst_amt,\r\n" + 
						"        d.disc_amt AS detail_disc_amt,\r\n" + 
						"        d.tax_amt AS detail_tax_amt,\r\n" + 
						"        d.total_amt AS detail_total_amt,\r\n" + 
						"        d.remark AS detail_remark,\r\n" + 
						"        d.ex_int1 AS detail_ex_int1,\r\n" + 
						"        d.ex_int2 AS detail_ex_int2,\r\n" + 
						"        d.ex_int3 AS detail_ex_int3,\r\n" + 
						"        d.ex_int4 AS detail_ex_int4,\r\n" + 
						"        d.ex_var1 AS detail_ex_var1,\r\n" + 
						"        d.ex_var2 AS detail_ex_var2,\r\n" + 
						"        d.ex_var3 AS detail_ex_var3,\r\n" + 
						"        d.ex_var4 AS detail_ex_var4,\r\n" + 
						"        d.ex_float1 AS detail_ex_float1,\r\n" + 
						"        d.ex_float2 AS detail_ex_float2,\r\n" + 
						"        d.ex_float3 AS detail_ex_float3,\r\n" + 
						"        d.ex_float4 AS detail_ex_float4\r\n" + 
						"    FROM\r\n" + 
						"        tn_order_header h,\r\n" + 
						"        tn_order_detail d\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND MONTH(h.delivery_date) =:month AND YEAR(h.delivery_date)=:year  AND h.order_status IN(:status) AND h.fr_id IN(:frIds)\r\n" + 
						"    ORDER BY\r\n" + 
						"        h.order_id\r\n" + 
						") t1\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.cust_id,\r\n" + 
						"        c.cust_name,\r\n" + 
						"        c.phone_number,\r\n" + 
						"        c.whatsapp_no\r\n" + 
						"    FROM\r\n" + 
						"        m_customer c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t2\r\n" + 
						"ON\r\n" + 
						"    t1.cust_id = t2.cust_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        f.fr_id,\r\n" + 
						"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
						"    FROM\r\n" + 
						"        m_franchise f\r\n" + 
						"    WHERE\r\n" + 
						"        f.del_status = 0\r\n" + 
						") t3\r\n" + 
						"ON\r\n" + 
						"    t1.fr_id = t3.fr_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        i.id,\r\n" + 
						"        i.item_name,\r\n" + 
						"        i.item_grp1 AS cat_id,\r\n" + 
						"        s.item_uom,\r\n" + 
						"        s.uom_id,\r\n" + 
						"        c.cat_name\r\n" + 
						"    FROM\r\n" + 
						"        m_item i,\r\n" + 
						"        m_item_sup s,\r\n" + 
						"        m_category c\r\n" + 
						"    WHERE\r\n" + 
						"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
						") t4\r\n" + 
						"ON\r\n" + 
						"    t1.item_id = t4.id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.city_id,\r\n" + 
						"        c.city_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_city c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t5\r\n" + 
						"ON\r\n" + 
						"    t1.city_id = t5.city_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.area_id,\r\n" + 
						"        a.area_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_area a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t6\r\n" + 
						"ON\r\n" + 
						"    t1.area_id = t6.area_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.offer_id,\r\n" + 
						"        h.offer_name,\r\n" + 
						"        h.offer_desc\r\n" + 
						"    FROM\r\n" + 
						"        mn_offer_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0\r\n" + 
						") t7\r\n" + 
						"ON\r\n" + 
						"    t1.offer_id = t7.offer_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        e.fr_emp_id,\r\n" + 
						"        CONCAT(\r\n" + 
						"            e.fr_emp_name,\r\n" + 
						"            ' - ',\r\n" + 
						"            e.fr_emp_contact\r\n" + 
						"        ) AS del_boy_name\r\n" + 
						"    FROM\r\n" + 
						"        m_fr_emp e\r\n" + 
						"    WHERE\r\n" + 
						"        e.del_status = 0\r\n" + 
						") t8\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.agent_id,\r\n" + 
						"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_agent a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t9\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
				List<GetOrderDisplay> getAllOrdersByDeliveryMonthAndStatusAndFr(@Param("month") int month, @Param("year") int year,@Param("status") List<Integer> status, @Param("frIds") List<Integer> frIds);


				//---------BETWEEN DATE AND STATUS AND CUSTOMER-----------------
				@Query(value="SELECT\r\n" + 
						"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
						"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
						"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
						"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
						"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
						"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
						"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
						"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
						"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
						"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
						"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
						"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
						"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
						"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
						"    END AS order_delivered_by_name,\r\n" + 
						"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
						"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
						"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
						"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
						"    MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
						"FROM\r\n" + 
						"    (\r\n" + 
						"    SELECT\r\n" + 
						"        h.*,\r\n" + 
						"        d.order_detail_id,\r\n" + 
						"        d.item_id,\r\n" + 
						"        d.hsn_code,\r\n" + 
						"        d.qty,\r\n" + 
						"        d.mrp,\r\n" + 
						"        d.rate,\r\n" + 
						"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
						"        d.cgst_per,\r\n" + 
						"        d.sgst_per,\r\n" + 
						"        d.igst_per,\r\n" + 
						"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
						"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
						"        d.igst_amt AS detail_igst_amt,\r\n" + 
						"        d.disc_amt AS detail_disc_amt,\r\n" + 
						"        d.tax_amt AS detail_tax_amt,\r\n" + 
						"        d.total_amt AS detail_total_amt,\r\n" + 
						"        d.remark AS detail_remark,\r\n" + 
						"        d.ex_int1 AS detail_ex_int1,\r\n" + 
						"        d.ex_int2 AS detail_ex_int2,\r\n" + 
						"        d.ex_int3 AS detail_ex_int3,\r\n" + 
						"        d.ex_int4 AS detail_ex_int4,\r\n" + 
						"        d.ex_var1 AS detail_ex_var1,\r\n" + 
						"        d.ex_var2 AS detail_ex_var2,\r\n" + 
						"        d.ex_var3 AS detail_ex_var3,\r\n" + 
						"        d.ex_var4 AS detail_ex_var4,\r\n" + 
						"        d.ex_float1 AS detail_ex_float1,\r\n" + 
						"        d.ex_float2 AS detail_ex_float2,\r\n" + 
						"        d.ex_float3 AS detail_ex_float3,\r\n" + 
						"        d.ex_float4 AS detail_ex_float4\r\n" + 
						"    FROM\r\n" + 
						"        tn_order_header h,\r\n" + 
						"        tn_order_detail d\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND h.delivery_date BETWEEN :fromDate AND :toDate AND h.order_status IN(:status) AND h.cust_id IN(:custIds)\r\n" + 
						"    ORDER BY\r\n" + 
						"        h.order_id\r\n" + 
						") t1\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.cust_id,\r\n" + 
						"        c.cust_name,\r\n" + 
						"        c.phone_number,\r\n" + 
						"        c.whatsapp_no\r\n" + 
						"    FROM\r\n" + 
						"        m_customer c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t2\r\n" + 
						"ON\r\n" + 
						"    t1.cust_id = t2.cust_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        f.fr_id,\r\n" + 
						"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
						"    FROM\r\n" + 
						"        m_franchise f\r\n" + 
						"    WHERE\r\n" + 
						"        f.del_status = 0\r\n" + 
						") t3\r\n" + 
						"ON\r\n" + 
						"    t1.fr_id = t3.fr_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        i.id,\r\n" + 
						"        i.item_name,\r\n" + 
						"        i.item_grp1 AS cat_id,\r\n" + 
						"        s.item_uom,\r\n" + 
						"        s.uom_id,\r\n" + 
						"        c.cat_name\r\n" + 
						"    FROM\r\n" + 
						"        m_item i,\r\n" + 
						"        m_item_sup s,\r\n" + 
						"        m_category c\r\n" + 
						"    WHERE\r\n" + 
						"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
						") t4\r\n" + 
						"ON\r\n" + 
						"    t1.item_id = t4.id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.city_id,\r\n" + 
						"        c.city_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_city c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t5\r\n" + 
						"ON\r\n" + 
						"    t1.city_id = t5.city_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.area_id,\r\n" + 
						"        a.area_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_area a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t6\r\n" + 
						"ON\r\n" + 
						"    t1.area_id = t6.area_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.offer_id,\r\n" + 
						"        h.offer_name,\r\n" + 
						"        h.offer_desc\r\n" + 
						"    FROM\r\n" + 
						"        mn_offer_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0\r\n" + 
						") t7\r\n" + 
						"ON\r\n" + 
						"    t1.offer_id = t7.offer_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        e.fr_emp_id,\r\n" + 
						"        CONCAT(\r\n" + 
						"            e.fr_emp_name,\r\n" + 
						"            ' - ',\r\n" + 
						"            e.fr_emp_contact\r\n" + 
						"        ) AS del_boy_name\r\n" + 
						"    FROM\r\n" + 
						"        m_fr_emp e\r\n" + 
						"    WHERE\r\n" + 
						"        e.del_status = 0\r\n" + 
						") t8\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.agent_id,\r\n" + 
						"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_agent a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t9\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
				List<GetOrderDisplay> getAllOrdersByDeliveryDateAndStatusAndCust(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("status") List<Integer> status, @Param("custIds") List<Integer> custIds);
				

				//---------FOR MONTH AND STATUS AND CUSTOMER-----------------
				@Query(value="SELECT\r\n" + 
						"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
						"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
						"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
						"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
						"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
						"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
						"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
						"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
						"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
						"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
						"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
						"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
						"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
						"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
						"    END AS order_delivered_by_name,\r\n" + 
						"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
						"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
						"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
						"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
						"    MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
						"FROM\r\n" + 
						"    (\r\n" + 
						"    SELECT\r\n" + 
						"        h.*,\r\n" + 
						"        d.order_detail_id,\r\n" + 
						"        d.item_id,\r\n" + 
						"        d.hsn_code,\r\n" + 
						"        d.qty,\r\n" + 
						"        d.mrp,\r\n" + 
						"        d.rate,\r\n" + 
						"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
						"        d.cgst_per,\r\n" + 
						"        d.sgst_per,\r\n" + 
						"        d.igst_per,\r\n" + 
						"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
						"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
						"        d.igst_amt AS detail_igst_amt,\r\n" + 
						"        d.disc_amt AS detail_disc_amt,\r\n" + 
						"        d.tax_amt AS detail_tax_amt,\r\n" + 
						"        d.total_amt AS detail_total_amt,\r\n" + 
						"        d.remark AS detail_remark,\r\n" + 
						"        d.ex_int1 AS detail_ex_int1,\r\n" + 
						"        d.ex_int2 AS detail_ex_int2,\r\n" + 
						"        d.ex_int3 AS detail_ex_int3,\r\n" + 
						"        d.ex_int4 AS detail_ex_int4,\r\n" + 
						"        d.ex_var1 AS detail_ex_var1,\r\n" + 
						"        d.ex_var2 AS detail_ex_var2,\r\n" + 
						"        d.ex_var3 AS detail_ex_var3,\r\n" + 
						"        d.ex_var4 AS detail_ex_var4,\r\n" + 
						"        d.ex_float1 AS detail_ex_float1,\r\n" + 
						"        d.ex_float2 AS detail_ex_float2,\r\n" + 
						"        d.ex_float3 AS detail_ex_float3,\r\n" + 
						"        d.ex_float4 AS detail_ex_float4\r\n" + 
						"    FROM\r\n" + 
						"        tn_order_header h,\r\n" + 
						"        tn_order_detail d\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND MONTH(h.delivery_date) =:month AND YEAR(h.delivery_date)=:year  AND h.order_status IN(:status) AND h.cust_id IN(:custIds)\r\n" + 
						"    ORDER BY\r\n" + 
						"        h.order_id\r\n" + 
						") t1\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.cust_id,\r\n" + 
						"        c.cust_name,\r\n" + 
						"        c.phone_number,\r\n" + 
						"        c.whatsapp_no\r\n" + 
						"    FROM\r\n" + 
						"        m_customer c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t2\r\n" + 
						"ON\r\n" + 
						"    t1.cust_id = t2.cust_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        f.fr_id,\r\n" + 
						"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
						"    FROM\r\n" + 
						"        m_franchise f\r\n" + 
						"    WHERE\r\n" + 
						"        f.del_status = 0\r\n" + 
						") t3\r\n" + 
						"ON\r\n" + 
						"    t1.fr_id = t3.fr_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        i.id,\r\n" + 
						"        i.item_name,\r\n" + 
						"        i.item_grp1 AS cat_id,\r\n" + 
						"        s.item_uom,\r\n" + 
						"        s.uom_id,\r\n" + 
						"        c.cat_name\r\n" + 
						"    FROM\r\n" + 
						"        m_item i,\r\n" + 
						"        m_item_sup s,\r\n" + 
						"        m_category c\r\n" + 
						"    WHERE\r\n" + 
						"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
						") t4\r\n" + 
						"ON\r\n" + 
						"    t1.item_id = t4.id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.city_id,\r\n" + 
						"        c.city_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_city c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t5\r\n" + 
						"ON\r\n" + 
						"    t1.city_id = t5.city_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.area_id,\r\n" + 
						"        a.area_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_area a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t6\r\n" + 
						"ON\r\n" + 
						"    t1.area_id = t6.area_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.offer_id,\r\n" + 
						"        h.offer_name,\r\n" + 
						"        h.offer_desc\r\n" + 
						"    FROM\r\n" + 
						"        mn_offer_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0\r\n" + 
						") t7\r\n" + 
						"ON\r\n" + 
						"    t1.offer_id = t7.offer_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        e.fr_emp_id,\r\n" + 
						"        CONCAT(\r\n" + 
						"            e.fr_emp_name,\r\n" + 
						"            ' - ',\r\n" + 
						"            e.fr_emp_contact\r\n" + 
						"        ) AS del_boy_name\r\n" + 
						"    FROM\r\n" + 
						"        m_fr_emp e\r\n" + 
						"    WHERE\r\n" + 
						"        e.del_status = 0\r\n" + 
						") t8\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.agent_id,\r\n" + 
						"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_agent a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t9\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
				List<GetOrderDisplay> getAllOrdersByDeliveryMonthAndStatusAndCust(@Param("month") int month, @Param("year") int year,@Param("status") List<Integer> status, @Param("custIds") List<Integer> custIds);
				

				
				//---------BETWEEN DATE AND STATUS AND ORDER PLATFORM-----------------
				@Query(value="SELECT\r\n" + 
						"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
						"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
						"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
						"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
						"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
						"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
						"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
						"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
						"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
						"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
						"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
						"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
						"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
						"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
						"    END AS order_delivered_by_name,\r\n" + 
						"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
						"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
						"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
						"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
						"    MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
						"FROM\r\n" + 
						"    (\r\n" + 
						"    SELECT\r\n" + 
						"        h.*,\r\n" + 
						"        d.order_detail_id,\r\n" + 
						"        d.item_id,\r\n" + 
						"        d.hsn_code,\r\n" + 
						"        d.qty,\r\n" + 
						"        d.mrp,\r\n" + 
						"        d.rate,\r\n" + 
						"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
						"        d.cgst_per,\r\n" + 
						"        d.sgst_per,\r\n" + 
						"        d.igst_per,\r\n" + 
						"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
						"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
						"        d.igst_amt AS detail_igst_amt,\r\n" + 
						"        d.disc_amt AS detail_disc_amt,\r\n" + 
						"        d.tax_amt AS detail_tax_amt,\r\n" + 
						"        d.total_amt AS detail_total_amt,\r\n" + 
						"        d.remark AS detail_remark,\r\n" + 
						"        d.ex_int1 AS detail_ex_int1,\r\n" + 
						"        d.ex_int2 AS detail_ex_int2,\r\n" + 
						"        d.ex_int3 AS detail_ex_int3,\r\n" + 
						"        d.ex_int4 AS detail_ex_int4,\r\n" + 
						"        d.ex_var1 AS detail_ex_var1,\r\n" + 
						"        d.ex_var2 AS detail_ex_var2,\r\n" + 
						"        d.ex_var3 AS detail_ex_var3,\r\n" + 
						"        d.ex_var4 AS detail_ex_var4,\r\n" + 
						"        d.ex_float1 AS detail_ex_float1,\r\n" + 
						"        d.ex_float2 AS detail_ex_float2,\r\n" + 
						"        d.ex_float3 AS detail_ex_float3,\r\n" + 
						"        d.ex_float4 AS detail_ex_float4\r\n" + 
						"    FROM\r\n" + 
						"        tn_order_header h,\r\n" + 
						"        tn_order_detail d\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND h.delivery_date BETWEEN :fromDate AND :toDate AND h.order_status IN(:status) AND h.order_platform IN(:platform)\r\n" + 
						"    ORDER BY\r\n" + 
						"        h.order_id\r\n" + 
						") t1\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.cust_id,\r\n" + 
						"        c.cust_name,\r\n" + 
						"        c.phone_number,\r\n" + 
						"        c.whatsapp_no\r\n" + 
						"    FROM\r\n" + 
						"        m_customer c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t2\r\n" + 
						"ON\r\n" + 
						"    t1.cust_id = t2.cust_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        f.fr_id,\r\n" + 
						"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
						"    FROM\r\n" + 
						"        m_franchise f\r\n" + 
						"    WHERE\r\n" + 
						"        f.del_status = 0\r\n" + 
						") t3\r\n" + 
						"ON\r\n" + 
						"    t1.fr_id = t3.fr_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        i.id,\r\n" + 
						"        i.item_name,\r\n" + 
						"        i.item_grp1 AS cat_id,\r\n" + 
						"        s.item_uom,\r\n" + 
						"        s.uom_id,\r\n" + 
						"        c.cat_name\r\n" + 
						"    FROM\r\n" + 
						"        m_item i,\r\n" + 
						"        m_item_sup s,\r\n" + 
						"        m_category c\r\n" + 
						"    WHERE\r\n" + 
						"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
						") t4\r\n" + 
						"ON\r\n" + 
						"    t1.item_id = t4.id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.city_id,\r\n" + 
						"        c.city_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_city c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t5\r\n" + 
						"ON\r\n" + 
						"    t1.city_id = t5.city_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.area_id,\r\n" + 
						"        a.area_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_area a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t6\r\n" + 
						"ON\r\n" + 
						"    t1.area_id = t6.area_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.offer_id,\r\n" + 
						"        h.offer_name,\r\n" + 
						"        h.offer_desc\r\n" + 
						"    FROM\r\n" + 
						"        mn_offer_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0\r\n" + 
						") t7\r\n" + 
						"ON\r\n" + 
						"    t1.offer_id = t7.offer_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        e.fr_emp_id,\r\n" + 
						"        CONCAT(\r\n" + 
						"            e.fr_emp_name,\r\n" + 
						"            ' - ',\r\n" + 
						"            e.fr_emp_contact\r\n" + 
						"        ) AS del_boy_name\r\n" + 
						"    FROM\r\n" + 
						"        m_fr_emp e\r\n" + 
						"    WHERE\r\n" + 
						"        e.del_status = 0\r\n" + 
						") t8\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.agent_id,\r\n" + 
						"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_agent a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t9\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
				List<GetOrderDisplay> getAllOrdersByDeliveryDateAndStatusAndOrderPlatform(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("status") List<Integer> status, @Param("platform") List<Integer> platform);

				
				//---------FOR MONTH AND STATUS AND ORDER PLATFORM-----------------
				@Query(value="SELECT\r\n" + 
						"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
						"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
						"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
						"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
						"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
						"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
						"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
						"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
						"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
						"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
						"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
						"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
						"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
						"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
						"    END AS order_delivered_by_name,\r\n" + 
						"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
						"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
						"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
						"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
						"    MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
						"FROM\r\n" + 
						"    (\r\n" + 
						"    SELECT\r\n" + 
						"        h.*,\r\n" + 
						"        d.order_detail_id,\r\n" + 
						"        d.item_id,\r\n" + 
						"        d.hsn_code,\r\n" + 
						"        d.qty,\r\n" + 
						"        d.mrp,\r\n" + 
						"        d.rate,\r\n" + 
						"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
						"        d.cgst_per,\r\n" + 
						"        d.sgst_per,\r\n" + 
						"        d.igst_per,\r\n" + 
						"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
						"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
						"        d.igst_amt AS detail_igst_amt,\r\n" + 
						"        d.disc_amt AS detail_disc_amt,\r\n" + 
						"        d.tax_amt AS detail_tax_amt,\r\n" + 
						"        d.total_amt AS detail_total_amt,\r\n" + 
						"        d.remark AS detail_remark,\r\n" + 
						"        d.ex_int1 AS detail_ex_int1,\r\n" + 
						"        d.ex_int2 AS detail_ex_int2,\r\n" + 
						"        d.ex_int3 AS detail_ex_int3,\r\n" + 
						"        d.ex_int4 AS detail_ex_int4,\r\n" + 
						"        d.ex_var1 AS detail_ex_var1,\r\n" + 
						"        d.ex_var2 AS detail_ex_var2,\r\n" + 
						"        d.ex_var3 AS detail_ex_var3,\r\n" + 
						"        d.ex_var4 AS detail_ex_var4,\r\n" + 
						"        d.ex_float1 AS detail_ex_float1,\r\n" + 
						"        d.ex_float2 AS detail_ex_float2,\r\n" + 
						"        d.ex_float3 AS detail_ex_float3,\r\n" + 
						"        d.ex_float4 AS detail_ex_float4\r\n" + 
						"    FROM\r\n" + 
						"        tn_order_header h,\r\n" + 
						"        tn_order_detail d\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND MONTH(h.delivery_date) =:month AND YEAR(h.delivery_date)=:year  AND h.order_status IN(:status) AND h.order_platform IN(:platform)\r\n" + 
						"    ORDER BY\r\n" + 
						"        h.order_id\r\n" + 
						") t1\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.cust_id,\r\n" + 
						"        c.cust_name,\r\n" + 
						"        c.phone_number,\r\n" + 
						"        c.whatsapp_no\r\n" + 
						"    FROM\r\n" + 
						"        m_customer c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t2\r\n" + 
						"ON\r\n" + 
						"    t1.cust_id = t2.cust_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        f.fr_id,\r\n" + 
						"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
						"    FROM\r\n" + 
						"        m_franchise f\r\n" + 
						"    WHERE\r\n" + 
						"        f.del_status = 0\r\n" + 
						") t3\r\n" + 
						"ON\r\n" + 
						"    t1.fr_id = t3.fr_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        i.id,\r\n" + 
						"        i.item_name,\r\n" + 
						"        i.item_grp1 AS cat_id,\r\n" + 
						"        s.item_uom,\r\n" + 
						"        s.uom_id,\r\n" + 
						"        c.cat_name\r\n" + 
						"    FROM\r\n" + 
						"        m_item i,\r\n" + 
						"        m_item_sup s,\r\n" + 
						"        m_category c\r\n" + 
						"    WHERE\r\n" + 
						"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
						") t4\r\n" + 
						"ON\r\n" + 
						"    t1.item_id = t4.id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.city_id,\r\n" + 
						"        c.city_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_city c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t5\r\n" + 
						"ON\r\n" + 
						"    t1.city_id = t5.city_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.area_id,\r\n" + 
						"        a.area_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_area a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t6\r\n" + 
						"ON\r\n" + 
						"    t1.area_id = t6.area_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.offer_id,\r\n" + 
						"        h.offer_name,\r\n" + 
						"        h.offer_desc\r\n" + 
						"    FROM\r\n" + 
						"        mn_offer_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0\r\n" + 
						") t7\r\n" + 
						"ON\r\n" + 
						"    t1.offer_id = t7.offer_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        e.fr_emp_id,\r\n" + 
						"        CONCAT(\r\n" + 
						"            e.fr_emp_name,\r\n" + 
						"            ' - ',\r\n" + 
						"            e.fr_emp_contact\r\n" + 
						"        ) AS del_boy_name\r\n" + 
						"    FROM\r\n" + 
						"        m_fr_emp e\r\n" + 
						"    WHERE\r\n" + 
						"        e.del_status = 0\r\n" + 
						") t8\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.agent_id,\r\n" + 
						"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_agent a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t9\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
				List<GetOrderDisplay> getAllOrdersByDeliveryMonthAndStatusAndOrderPlatform(@Param("month") int month, @Param("year") int year,@Param("status") List<Integer> status, @Param("platform") List<Integer> platform);
				
				
				
				//---------BETWEEN DATE AND STATUS AND PAYMENT MODE-----------------
				@Query(value="SELECT\r\n" + 
						"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
						"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
						"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
						"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
						"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
						"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
						"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
						"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
						"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
						"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
						"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
						"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
						"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
						"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
						"    END AS order_delivered_by_name,\r\n" + 
						"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
						"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
						"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
						"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
						"    MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
						"FROM\r\n" + 
						"    (\r\n" + 
						"    SELECT\r\n" + 
						"        h.*,\r\n" + 
						"        d.order_detail_id,\r\n" + 
						"        d.item_id,\r\n" + 
						"        d.hsn_code,\r\n" + 
						"        d.qty,\r\n" + 
						"        d.mrp,\r\n" + 
						"        d.rate,\r\n" + 
						"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
						"        d.cgst_per,\r\n" + 
						"        d.sgst_per,\r\n" + 
						"        d.igst_per,\r\n" + 
						"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
						"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
						"        d.igst_amt AS detail_igst_amt,\r\n" + 
						"        d.disc_amt AS detail_disc_amt,\r\n" + 
						"        d.tax_amt AS detail_tax_amt,\r\n" + 
						"        d.total_amt AS detail_total_amt,\r\n" + 
						"        d.remark AS detail_remark,\r\n" + 
						"        d.ex_int1 AS detail_ex_int1,\r\n" + 
						"        d.ex_int2 AS detail_ex_int2,\r\n" + 
						"        d.ex_int3 AS detail_ex_int3,\r\n" + 
						"        d.ex_int4 AS detail_ex_int4,\r\n" + 
						"        d.ex_var1 AS detail_ex_var1,\r\n" + 
						"        d.ex_var2 AS detail_ex_var2,\r\n" + 
						"        d.ex_var3 AS detail_ex_var3,\r\n" + 
						"        d.ex_var4 AS detail_ex_var4,\r\n" + 
						"        d.ex_float1 AS detail_ex_float1,\r\n" + 
						"        d.ex_float2 AS detail_ex_float2,\r\n" + 
						"        d.ex_float3 AS detail_ex_float3,\r\n" + 
						"        d.ex_float4 AS detail_ex_float4\r\n" + 
						"    FROM\r\n" + 
						"        tn_order_header h,\r\n" + 
						"        tn_order_detail d\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND h.delivery_date BETWEEN :fromDate AND :toDate AND h.order_status IN(:status) AND h.payment_method IN(:payment)\r\n" + 
						"    ORDER BY\r\n" + 
						"        h.order_id\r\n" + 
						") t1\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.cust_id,\r\n" + 
						"        c.cust_name,\r\n" + 
						"        c.phone_number,\r\n" + 
						"        c.whatsapp_no\r\n" + 
						"    FROM\r\n" + 
						"        m_customer c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t2\r\n" + 
						"ON\r\n" + 
						"    t1.cust_id = t2.cust_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        f.fr_id,\r\n" + 
						"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
						"    FROM\r\n" + 
						"        m_franchise f\r\n" + 
						"    WHERE\r\n" + 
						"        f.del_status = 0\r\n" + 
						") t3\r\n" + 
						"ON\r\n" + 
						"    t1.fr_id = t3.fr_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        i.id,\r\n" + 
						"        i.item_name,\r\n" + 
						"        i.item_grp1 AS cat_id,\r\n" + 
						"        s.item_uom,\r\n" + 
						"        s.uom_id,\r\n" + 
						"        c.cat_name\r\n" + 
						"    FROM\r\n" + 
						"        m_item i,\r\n" + 
						"        m_item_sup s,\r\n" + 
						"        m_category c\r\n" + 
						"    WHERE\r\n" + 
						"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
						") t4\r\n" + 
						"ON\r\n" + 
						"    t1.item_id = t4.id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.city_id,\r\n" + 
						"        c.city_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_city c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t5\r\n" + 
						"ON\r\n" + 
						"    t1.city_id = t5.city_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.area_id,\r\n" + 
						"        a.area_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_area a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t6\r\n" + 
						"ON\r\n" + 
						"    t1.area_id = t6.area_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.offer_id,\r\n" + 
						"        h.offer_name,\r\n" + 
						"        h.offer_desc\r\n" + 
						"    FROM\r\n" + 
						"        mn_offer_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0\r\n" + 
						") t7\r\n" + 
						"ON\r\n" + 
						"    t1.offer_id = t7.offer_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        e.fr_emp_id,\r\n" + 
						"        CONCAT(\r\n" + 
						"            e.fr_emp_name,\r\n" + 
						"            ' - ',\r\n" + 
						"            e.fr_emp_contact\r\n" + 
						"        ) AS del_boy_name\r\n" + 
						"    FROM\r\n" + 
						"        m_fr_emp e\r\n" + 
						"    WHERE\r\n" + 
						"        e.del_status = 0\r\n" + 
						") t8\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.agent_id,\r\n" + 
						"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_agent a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t9\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
				List<GetOrderDisplay> getAllOrdersByDeliveryDateAndStatusAndPayment(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("status") List<Integer> status, @Param("payment") List<Integer> payment);
		
				
				
				//---------FOR MONTH AND STATUS AND PAYMENT MODE-----------------
				@Query(value="SELECT\r\n" + 
						"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
						"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
						"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
						"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
						"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
						"    COALESCE(t4.cat_id, 0) AS cat_id,\r\n" + 
						"    COALESCE(t4.cat_name, '') AS cat_name,\r\n" + 
						"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
						"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
						"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
						"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
						"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
						"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
						"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
						"    END AS order_delivered_by_name,\r\n" + 
						"    MONTH(t1.delivery_date) AS delivery_month,\r\n" + 
						"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
						"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
						"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
						"    MONTHNAME(t1.delivery_date) AS month_name\r\n" + 
						"FROM\r\n" + 
						"    (\r\n" + 
						"    SELECT\r\n" + 
						"        h.*,\r\n" + 
						"        d.order_detail_id,\r\n" + 
						"        d.item_id,\r\n" + 
						"        d.hsn_code,\r\n" + 
						"        d.qty,\r\n" + 
						"        d.mrp,\r\n" + 
						"        d.rate,\r\n" + 
						"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
						"        d.cgst_per,\r\n" + 
						"        d.sgst_per,\r\n" + 
						"        d.igst_per,\r\n" + 
						"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
						"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
						"        d.igst_amt AS detail_igst_amt,\r\n" + 
						"        d.disc_amt AS detail_disc_amt,\r\n" + 
						"        d.tax_amt AS detail_tax_amt,\r\n" + 
						"        d.total_amt AS detail_total_amt,\r\n" + 
						"        d.remark AS detail_remark,\r\n" + 
						"        d.ex_int1 AS detail_ex_int1,\r\n" + 
						"        d.ex_int2 AS detail_ex_int2,\r\n" + 
						"        d.ex_int3 AS detail_ex_int3,\r\n" + 
						"        d.ex_int4 AS detail_ex_int4,\r\n" + 
						"        d.ex_var1 AS detail_ex_var1,\r\n" + 
						"        d.ex_var2 AS detail_ex_var2,\r\n" + 
						"        d.ex_var3 AS detail_ex_var3,\r\n" + 
						"        d.ex_var4 AS detail_ex_var4,\r\n" + 
						"        d.ex_float1 AS detail_ex_float1,\r\n" + 
						"        d.ex_float2 AS detail_ex_float2,\r\n" + 
						"        d.ex_float3 AS detail_ex_float3,\r\n" + 
						"        d.ex_float4 AS detail_ex_float4\r\n" + 
						"    FROM\r\n" + 
						"        tn_order_header h,\r\n" + 
						"        tn_order_detail d\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND MONTH(h.delivery_date) =:month AND YEAR(h.delivery_date)=:year  AND h.order_status IN(:status) AND h.payment_method IN(:payment)\r\n" + 
						"    ORDER BY\r\n" + 
						"        h.order_id\r\n" + 
						") t1\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.cust_id,\r\n" + 
						"        c.cust_name,\r\n" + 
						"        c.phone_number,\r\n" + 
						"        c.whatsapp_no\r\n" + 
						"    FROM\r\n" + 
						"        m_customer c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t2\r\n" + 
						"ON\r\n" + 
						"    t1.cust_id = t2.cust_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        f.fr_id,\r\n" + 
						"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
						"    FROM\r\n" + 
						"        m_franchise f\r\n" + 
						"    WHERE\r\n" + 
						"        f.del_status = 0\r\n" + 
						") t3\r\n" + 
						"ON\r\n" + 
						"    t1.fr_id = t3.fr_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        i.id,\r\n" + 
						"        i.item_name,\r\n" + 
						"        i.item_grp1 AS cat_id,\r\n" + 
						"        s.item_uom,\r\n" + 
						"        s.uom_id,\r\n" + 
						"        c.cat_name\r\n" + 
						"    FROM\r\n" + 
						"        m_item i,\r\n" + 
						"        m_item_sup s,\r\n" + 
						"        m_category c\r\n" + 
						"    WHERE\r\n" + 
						"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
						") t4\r\n" + 
						"ON\r\n" + 
						"    t1.item_id = t4.id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.city_id,\r\n" + 
						"        c.city_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_city c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t5\r\n" + 
						"ON\r\n" + 
						"    t1.city_id = t5.city_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.area_id,\r\n" + 
						"        a.area_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_area a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t6\r\n" + 
						"ON\r\n" + 
						"    t1.area_id = t6.area_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.offer_id,\r\n" + 
						"        h.offer_name,\r\n" + 
						"        h.offer_desc\r\n" + 
						"    FROM\r\n" + 
						"        mn_offer_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0\r\n" + 
						") t7\r\n" + 
						"ON\r\n" + 
						"    t1.offer_id = t7.offer_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        e.fr_emp_id,\r\n" + 
						"        CONCAT(\r\n" + 
						"            e.fr_emp_name,\r\n" + 
						"            ' - ',\r\n" + 
						"            e.fr_emp_contact\r\n" + 
						"        ) AS del_boy_name\r\n" + 
						"    FROM\r\n" + 
						"        m_fr_emp e\r\n" + 
						"    WHERE\r\n" + 
						"        e.del_status = 0\r\n" + 
						") t8\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.agent_id,\r\n" + 
						"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_agent a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t9\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t9.agent_id",nativeQuery=true)
				List<GetOrderDisplay> getAllOrdersByDeliveryMonthAndStatusAndPayment(@Param("month") int month, @Param("year") int year,@Param("status") List<Integer> status, @Param("payment") List<Integer> payment);
			
				
				
			//GET BY ORDER ID
				@Query(value="SELECT\r\n" + 
						"    UUID() AS id, t1.*, COALESCE(t2.cust_name, '') AS cust_name,\r\n" + 
						"    COALESCE(t2.phone_number, '') AS cust_mobile,\r\n" + 
						"    COALESCE(t2.whatsapp_no, '') AS cust_whatsapp_no,\r\n" + 
						"    COALESCE(t3.fr_name, '') AS fr_name,\r\n" + 
						"    COALESCE(t4.item_name, '') AS item_name,\r\n" + 
						"    COALESCE(t10.sell_bill_no, 0) AS cat_id,\r\n" + 
						"    COALESCE(t10.invoice_no, '') AS cat_name,\r\n" + 
						"    COALESCE(t4.item_uom, '') AS item_uom,\r\n" + 
						"    COALESCE(t4.uom_id, 0) AS uom_id,\r\n" + 
						"    COALESCE(t5.city_name, '') AS city_name,\r\n" + 
						"    COALESCE(t6.area_name, '') AS area_name,\r\n" + 
						"    COALESCE(t7.offer_name, '') AS offer_name,\r\n" + 
						"    COALESCE(t7.offer_desc, '') AS offer_desc,\r\n" + 
						"    CASE WHEN t1.is_agent = 0 THEN COALESCE(t8.del_boy_name, '') ELSE COALESCE(t9.agent_name, '')\r\n" + 
						"    END AS order_delivered_by_name,\r\n" + 
						"    COALESCE(t10.sell_bill_no, 0) AS delivery_month,\r\n" + 
						"    YEAR(t1.delivery_date) AS delivery_year,\r\n" + 
						"    DATE_FORMAT(t1.delivery_date, '%d-%m-%Y') AS delivery_date_display,\r\n" + 
						"    TIME_FORMAT(t1.delivery_time, '%h:%i %p') AS delivery_time_display,\r\n" + 
						"    COALESCE(t10.invoice_no, '') AS month_name\r\n" + 
						"FROM\r\n" + 
						"    (\r\n" + 
						"    SELECT\r\n" + 
						"        h.*,\r\n" + 
						"        d.order_detail_id,\r\n" + 
						"        d.item_id,\r\n" + 
						"        d.hsn_code,\r\n" + 
						"        d.qty,\r\n" + 
						"        d.mrp,\r\n" + 
						"        d.rate,\r\n" + 
						"        d.taxable_amt AS detail_taxable_amt,\r\n" + 
						"        d.cgst_per,\r\n" + 
						"        d.sgst_per,\r\n" + 
						"        d.igst_per,\r\n" + 
						"        d.cgst_amt AS detail_cgst_amt,\r\n" + 
						"        d.sgst_amt AS detail_sgst_amt,\r\n" + 
						"        d.igst_amt AS detail_igst_amt,\r\n" + 
						"        d.disc_amt AS detail_disc_amt,\r\n" + 
						"        d.tax_amt AS detail_tax_amt,\r\n" + 
						"        d.total_amt AS detail_total_amt,\r\n" + 
						"        d.remark AS detail_remark,\r\n" + 
						"        d.ex_int1 AS detail_ex_int1,\r\n" + 
						"        d.ex_int2 AS detail_ex_int2,\r\n" + 
						"        d.ex_int3 AS detail_ex_int3,\r\n" + 
						"        d.ex_int4 AS detail_ex_int4,\r\n" + 
						"        d.ex_var1 AS detail_ex_var1,\r\n" + 
						"        d.ex_var2 AS detail_ex_var2,\r\n" + 
						"        d.ex_var3 AS detail_ex_var3,\r\n" + 
						"        d.ex_var4 AS detail_ex_var4,\r\n" + 
						"        d.ex_float1 AS detail_ex_float1,\r\n" + 
						"        d.ex_float2 AS detail_ex_float2,\r\n" + 
						"        d.ex_float3 AS detail_ex_float3,\r\n" + 
						"        d.ex_float4 AS detail_ex_float4\r\n" + 
						"    FROM\r\n" + 
						"        tn_order_header h,\r\n" + 
						"        tn_order_detail d\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND d.del_status = 0 AND h.order_id = d.order_id AND h.order_id = :orderId\r\n" + 
						"    ORDER BY\r\n" + 
						"        h.order_id\r\n" + 
						") t1\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.cust_id,\r\n" + 
						"        c.cust_name,\r\n" + 
						"        c.phone_number,\r\n" + 
						"        c.whatsapp_no\r\n" + 
						"    FROM\r\n" + 
						"        m_customer c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t2\r\n" + 
						"ON\r\n" + 
						"    t1.cust_id = t2.cust_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        f.fr_id,\r\n" + 
						"        CONCAT(f.fr_name, ' - ', f.fr_code) AS fr_name\r\n" + 
						"    FROM\r\n" + 
						"        m_franchise f\r\n" + 
						"    WHERE\r\n" + 
						"        f.del_status = 0\r\n" + 
						") t3\r\n" + 
						"ON\r\n" + 
						"    t1.fr_id = t3.fr_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        i.id,\r\n" + 
						"        i.item_name,\r\n" + 
						"        i.item_grp1 AS cat_id,\r\n" + 
						"        s.item_uom,\r\n" + 
						"        s.uom_id,\r\n" + 
						"        c.cat_name\r\n" + 
						"    FROM\r\n" + 
						"        m_item i,\r\n" + 
						"        m_item_sup s,\r\n" + 
						"        m_category c\r\n" + 
						"    WHERE\r\n" + 
						"        i.del_status = 0 AND i.id = s.item_id AND i.item_grp1 = c.cat_id\r\n" + 
						") t4\r\n" + 
						"ON\r\n" + 
						"    t1.item_id = t4.id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        c.city_id,\r\n" + 
						"        c.city_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_city c\r\n" + 
						"    WHERE\r\n" + 
						"        c.del_status = 0\r\n" + 
						") t5\r\n" + 
						"ON\r\n" + 
						"    t1.city_id = t5.city_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.area_id,\r\n" + 
						"        a.area_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_area a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t6\r\n" + 
						"ON\r\n" + 
						"    t1.area_id = t6.area_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.offer_id,\r\n" + 
						"        h.offer_name,\r\n" + 
						"        h.offer_desc\r\n" + 
						"    FROM\r\n" + 
						"        mn_offer_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0\r\n" + 
						") t7\r\n" + 
						"ON\r\n" + 
						"    t1.offer_id = t7.offer_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        e.fr_emp_id,\r\n" + 
						"        CONCAT(\r\n" + 
						"            e.fr_emp_name,\r\n" + 
						"            ' - ',\r\n" + 
						"            e.fr_emp_contact\r\n" + 
						"        ) AS del_boy_name\r\n" + 
						"    FROM\r\n" + 
						"        m_fr_emp e\r\n" + 
						"    WHERE\r\n" + 
						"        e.del_status = 0\r\n" + 
						") t8\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t8.fr_emp_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        a.agent_id,\r\n" + 
						"        CONCAT(a.agent_name, ' - ', a.mobile_no) AS agent_name\r\n" + 
						"    FROM\r\n" + 
						"        mn_agent a\r\n" + 
						"    WHERE\r\n" + 
						"        a.del_status = 0\r\n" + 
						") t9\r\n" + 
						"ON\r\n" + 
						"    t1.order_delivered_by = t9.agent_id\r\n" + 
						"LEFT JOIN(\r\n" + 
						"    SELECT\r\n" + 
						"        h.sell_bill_no,\r\n" + 
						"        h.invoice_no,\r\n" + 
						"        h.ext_int2\r\n" + 
						"    FROM\r\n" + 
						"        t_sell_bill_header h\r\n" + 
						"    WHERE\r\n" + 
						"        h.del_status = 0 AND h.ext_int2 > 0\r\n" + 
						") t10\r\n" + 
						"ON\r\n" + 
						"    t1.order_id = t10.ext_int2",nativeQuery=true)
				List<GetOrderDisplay> getOrderById(@Param("orderId") int orderId);

				
				
}
