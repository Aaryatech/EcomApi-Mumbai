package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetOrderHeaderDisplay;

public interface GetOrderHeaderRepo extends JpaRepository<GetOrderHeaderDisplay, Integer> {

	@Query(value=" SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        t1.*,ifnull(gr1.ex_int2,0) as ex_int2,\n" + 
			"        COALESCE(t2.cust_name,\n" + 
			"        '') AS cust_name,\n" + 
			"        COALESCE(t2.cust_mobile_no,\n" + 
			"        '') AS cust_mobile,\n" + 
			"        COALESCE(t2.email_id,\n" + 
			"        '') AS email_id,\n" + 
			"        COALESCE(t3.fr_name,\n" + 
			"        '') AS fr_name, t3.fr_contact_no,t3.city,\n" + 
			"        COALESCE(t5.city_name,\n" + 
			"        '') AS city_name,\n" + 
			"        COALESCE(t6.area_name,\n" + 
			"        '') AS area_name,\n" + 
			"        MONTH(t1.delivery_date) AS delivery_month,\n" + 
			"        YEAR(t1.delivery_date) AS delivery_year,\n" + 
			"        DATE_FORMAT(t1.order_date,\n" + 
			"        '%d-%m-%Y') AS order_date_display,\n" + 
			"        DATE_FORMAT(t1.delivery_date,\n" + 
			"        '%d-%m-%Y') AS delivery_date_display,\n" + 
			"         (t1.ex_var3\n" + 
			"         ) AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name  \n" + 
			"    FROM\n" + 
			"        (      SELECT\n" + 
			"            h.order_id,h.order_no,\n" + 
			"    h.order_date,\n" + 
			"    h.fr_id,\n" + 
			"    h.cust_id,\n" + 
			"    h.status,\n" + 
			"    h.taxable_amt,\n" + 
			"    h.cgst_amt,\n" + 
			"    h.sgst_amt,\n" + 
			"    h.igst_amt,\n" + 
			"    h.disc_amt,\n" + 
			"    h.item_disc_amt,\n" + 
			"    h.tax_amt,\n" + 
			"    h.total_amt,\n" + 
			"    s.status_value AS order_status,\n" + 
			"    h.paid_status,\n" + 
			"    h.payment_method,\n" + 
			"    h.payment_remark,\n" + 
			"    h.city_id,\n" + 
			"    h.area_id,\n" + 
			"    h.address_id,\n" + 
			"    h.address,\n" + 
			"    h.whatsapp_no,\n" + 
			"    h.landmark,\n" + 
			"    h.delivery_date,\n" + 
			"    h.delivery_time,\n" + 
			"    h.production_date,\n" + 
			"    h.production_time,\n" + 
			"    h.insert_date_time,\n" + 
			"    h.insert_user_id,\n" + 
			"    h.order_platform,\n" + 
			"    h.del_status,\n" + 
			"    h.offer_id,\n" + 
			"    h.remark,\n" + 
			"    h.order_delivered_by,\n" + 
			"    h.ex_int1,\n" + 
			"   \n" + 
			"    h.ex_int3,\n" + 
			"    h.ex_int4,\n" + 
			"    h.ex_var1,\n" + 
			"    h.ex_var2,\n" + 
			"    h.ex_var3,\n" + 
			"    h.ex_var4,\n" + 
			"    h.ex_float1,\n" + 
			"    h.ex_float2,\n" + 
			"    h.ex_float3,\n" + 
			"    h.ex_float4,\n" + 
			"    h.ex_date1,\n" + 
			"    h.ex_date2,\n" + 
			"    h.billing_name,\n" + 
			"    h.billing_address,\n" + 
			"    h.customer_gstn_no,\n" + 
			"    h.delivery_type,\n" + 
			"    h.delivery_inst_id,\n" + 
			"    h.delivery_inst_text,\n" + 
			"    h.delivery_km,\n" + 
			"    h.delivery_charges,\n" + 
			"    h.payment_sub_mode,\n" + 
			"    h.is_agent,\n" + 
			"    h.uuid_no,h.order_status as int_order_status                \n" + 
			"        FROM\n" + 
			"            tn_order_header h  ,mn_status s    \n" + 
			"        WHERE\n" + 
			"            h.del_status = 1 \n" + 
			"         	AND h.ex_int1=:compId\n" + 
			"            AND h.delivery_date BETWEEN :fromDate AND :toDate  AND s.status_id=h.order_status \n" + 
			"            AND h.order_status IN(:status)       \n" + 
			"        ORDER BY\n" + 
			"            h.order_id  ) t1 LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                tn_grievences.order_id,\n" + 
			"                tn_grievences.d_date,\n" + 
			"                COUNT('') AS ex_int2                 \n" + 
			"            FROM\n" + 
			"                tn_grievences                                   \n" + 
			"            GROUP BY\n" + 
			"                  tn_grievences.d_date                      \n" + 
			"        ) gr1 \n" + 
			"            ON t1.order_id = gr1.d_date "+
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                cust.cust_id,\n" + 
			"                cust.cust_name,\n" + 
			"                cust.cust_mobile_no,\n" + 
			"                cust.email_id     \n" + 
			"            FROM\n" + 
			"                m_customer cust      \n" + 
			"            WHERE\n" + 
			"                cust.del_status = 1\n" + 
			"            	AND cust.company_id=:compId\n" + 
			"        ) t2  \n" + 
			"            ON      t1.cust_id = t2.cust_id  \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                f.fr_id,\n" + 
			"                CONCAT(f.fr_name,\n" + 
			"                ' - ',\n" + 
			"                f.fr_code) AS fr_name,f.fr_contact_no,   f.city       \n" + 
			"            FROM\n" + 
			"                m_franchise f      \n" + 
			"            WHERE\n" + 
			"                f.del_status = 1\n" + 
			"          	  	AND f.company_id=:compId\n" + 
			"        ) t3  \n" + 
			"            ON      t1.fr_id = t3.fr_id \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                city.city_id,\n" + 
			"                city.city_name      \n" + 
			"            FROM\n" + 
			"                mn_city city      \n" + 
			"            WHERE\n" + 
			"                city.del_status = 1 \n" + 
			"            	AND city.company_id=:compId\n" + 
			"        ) t5  \n" + 
			"            ON      t1.city_id = t5.city_id  \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                a.area_id,\n" + 
			"                a.area_name     \n" + 
			"            FROM\n" + 
			"                mn_area a      \n" + 
			"            WHERE\n" + 
			"                a.del_status = 1\n" + 
			"            	AND a.company_id=:compId\n" + 
			"        ) t6  \n" + 
			"            ON      t1.area_id = t6.area_id",nativeQuery=true)
	List<GetOrderHeaderDisplay> getOrderHeaderByDeliveryDate(@Param("fromDate") String fromDate, 
			@Param("toDate") String toDate, @Param("status") List<Integer> status, @Param("compId") int compId);
	
	@Query(value=" SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        t1.*, ifnull(gr1.ex_int2,0) as ex_int2,\n" + 
			"        COALESCE(t2.cust_name,\n" + 
			"        '') AS cust_name,\n" + 
			"        COALESCE(t2.cust_mobile_no,\n" + 
			"        '') AS cust_mobile,\n" + 
			"        COALESCE(t2.email_id,\n" + 
			"        '') AS email_id,\n" + 
			"        COALESCE(t3.fr_name,\n" + 
			"        '') AS fr_name, t3.fr_contact_no,t3.city,\n" + 
			"        COALESCE(t5.city_name,\n" + 
			"        '') AS city_name,\n" + 
			"        COALESCE(t6.area_name,\n" + 
			"        '') AS area_name,\n" + 
			"        MONTH(t1.delivery_date) AS delivery_month,\n" + 
			"        YEAR(t1.delivery_date) AS delivery_year,\n" + 
			"        DATE_FORMAT(t1.order_date,\n" + 
			"        '%d-%m-%Y') AS order_date_display,\n" + 
			"        DATE_FORMAT(t1.delivery_date,\n" + 
			"        '%d-%m-%Y') AS delivery_date_display,\n" + 
			"         (t1.ex_var3 " + 
			"        ) AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name  \n" + 
			"    FROM\n" + 
			"        (      SELECT\n" + 
			"             h.order_no,\n" + 
			"   h.order_id, h.order_date,\n" + 
			"    h.fr_id,\n" + 
			"    h.cust_id,\n" + 
			"    h.status,\n" + 
			"    h.taxable_amt,\n" + 
			"    h.cgst_amt,\n" + 
			"    h.sgst_amt,\n" + 
			"    h.igst_amt,\n" + 
			"    h.disc_amt,\n" + 
			"    h.item_disc_amt,\n" + 
			"    h.tax_amt,\n" + 
			"    h.total_amt,\n" + 
			"    s.status_value AS order_status,\n" + 
			"    h.paid_status,\n" + 
			"    h.payment_method,\n" + 
			"    h.payment_remark,\n" + 
			"    h.city_id,\n" + 
			"    h.area_id,\n" + 
			"    h.address_id,\n" + 
			"    h.address,\n" + 
			"    h.whatsapp_no,\n" + 
			"    h.landmark,\n" + 
			"    h.delivery_date,\n" + 
			"    h.delivery_time,\n" + 
			"    h.production_date,\n" + 
			"    h.production_time,\n" + 
			"    h.insert_date_time,\n" + 
			"    h.insert_user_id,\n" + 
			"    h.order_platform,\n" + 
			"    h.del_status,\n" + 
			"    h.offer_id,\n" + 
			"    h.remark,\n" + 
			"    h.order_delivered_by,\n" + 
			"    h.ex_int1,\n" + 
			"   \n" + 
			"    h.ex_int3,\n" + 
			"    h.ex_int4,\n" + 
			"    h.ex_var1,\n" + 
			"    h.ex_var2,\n" + 
			"    h.ex_var3,\n" + 
			"    h.ex_var4,\n" + 
			"    h.ex_float1,\n" + 
			"    h.ex_float2,\n" + 
			"    h.ex_float3,\n" + 
			"    h.ex_float4,\n" + 
			"    h.ex_date1,\n" + 
			"    h.ex_date2,\n" + 
			"    h.billing_name,\n" + 
			"    h.billing_address,\n" + 
			"    h.customer_gstn_no,\n" + 
			"    h.delivery_type,\n" + 
			"    h.delivery_inst_id,\n" + 
			"    h.delivery_inst_text,\n" + 
			"    h.delivery_km,\n" + 
			"    h.delivery_charges,\n" + 
			"    h.payment_sub_mode,\n" + 
			"    h.is_agent,\n" + 
			"    h.uuid_no, h.order_status as int_order_status            \n" + 
			"        FROM\n" + 
			"            tn_order_header h  ,mn_status s   \n" + 
			"        WHERE\n" + 
			"            h.del_status = 1 \n" + 
			"         	AND h.ex_int1=:compId\n" + 
			"            AND h.production_date BETWEEN :fromDate AND :toDate AND s.status_id=h.order_status\n" + 
			"            AND h.order_status IN(:status)       \n" + 
			"        ORDER BY\n" + 
			"            h.order_id  ) t1  LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                tn_grievences.order_id,\n" + 
			"                tn_grievences.d_date,\n" + 
			"                COUNT('') AS ex_int2                 \n" + 
			"            FROM\n" + 
			"                tn_grievences                                   \n" + 
			"            GROUP BY\n" + 
			"                  tn_grievences.d_date                      \n" + 
			"        ) gr1 \n" + 
			"            ON t1.order_id = gr1.d_date\n" +
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                cust.cust_id,\n" + 
			"                cust.cust_name,\n" + 
			"                cust.cust_mobile_no,\n" + 
			"                cust.email_id     \n" + 
			"            FROM\n" + 
			"                m_customer cust      \n" + 
			"            WHERE\n" + 
			"                cust.del_status = 1\n" + 
			"            	AND cust.company_id=:compId\n" + 
			"        ) t2  \n" + 
			"            ON      t1.cust_id = t2.cust_id  \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                f.fr_id,\n" + 
			"                CONCAT(f.fr_name,\n" + 
			"                ' - ',\n" + 
			"                f.fr_code) AS fr_name ,f.fr_contact_no,   f.city      \n" + 
			"            FROM\n" + 
			"                m_franchise f      \n" + 
			"            WHERE\n" + 
			"                f.del_status = 1\n" + 
			"          	  	AND f.company_id=:compId\n" + 
			"        ) t3  \n" + 
			"            ON      t1.fr_id = t3.fr_id \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                city.city_id,\n" + 
			"                city.city_name      \n" + 
			"            FROM\n" + 
			"                mn_city city      \n" + 
			"            WHERE\n" + 
			"                city.del_status = 1 \n" + 
			"            	AND city.company_id=:compId\n" + 
			"        ) t5  \n" + 
			"            ON      t1.city_id = t5.city_id  \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                a.area_id,\n" + 
			"                a.area_name     \n" + 
			"            FROM\n" + 
			"                mn_area a      \n" + 
			"            WHERE\n" + 
			"                a.del_status = 1\n" + 
			"            	AND a.company_id=:compId\n" + 
			"        ) t6  \n" + 
			"            ON      t1.area_id = t6.area_id",nativeQuery=true)	
	List<GetOrderHeaderDisplay> getOrderHeaderByProdctnDate(@Param("fromDate") String fromDate, 
			@Param("toDate") String toDate, @Param("status") List<Integer> status, @Param("compId") int compId);
	
	@Query(value="SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        t1.*,\n" + 
			"        COALESCE(t2.cust_name,\n" + 
			"        '') AS cust_name,\n" + 
			"        COALESCE(t2.cust_mobile_no,\n" + 
			"        '') AS cust_mobile,\n" + 
			"        COALESCE(t2.email_id,\n" + 
			"        '') AS email_id,\n" + 
			"        COALESCE(t3.fr_name,\n" + 
			"        '') AS fr_name, t3.fr_contact_no,t3.city,\n" + 
			"        COALESCE(t5.city_name,\n" + 
			"        '') AS city_name,\n" + 
			"        COALESCE(t6.area_name,\n" + 
			"        '') AS area_name,\n" + 
			"        MONTH(t1.delivery_date) AS delivery_month,\n" + 
			"        YEAR(t1.delivery_date) AS delivery_year,\n" + 
			"        DATE_FORMAT(t1.order_date,\n" + 
			"        '%d-%m-%Y') AS order_date_display,\n" + 
			"        DATE_FORMAT(t1.delivery_date,\n" + 
			"        '%d-%m-%Y') AS delivery_date_display,\n" + 
			"        t1.ex_var3 AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name       \n" + 
			"    FROM\n" + 
			"        (      SELECT\n" + 
			"            h.order_id,h.order_no,\n" + 
			"    h.order_date,\n" + 
			"    h.fr_id,\n" + 
			"    h.cust_id,\n" + 
			"    h.status,\n" + 
			"    h.taxable_amt,\n" + 
			"    h.cgst_amt,\n" + 
			"    h.sgst_amt,\n" + 
			"    h.igst_amt,\n" + 
			"    h.disc_amt,\n" + 
			"    h.item_disc_amt,\n" + 
			"    h.tax_amt,\n" + 
			"    h.total_amt,\n" + 
			"    s.status_value AS order_status,\n" + 
			"    h.paid_status,\n" + 
			"    h.payment_method,\n" + 
			"    h.payment_remark,\n" + 
			"    h.city_id,\n" + 
			"    h.area_id,\n" + 
			"    h.address_id,\n" + 
			"    h.address,\n" + 
			"    h.whatsapp_no,\n" + 
			"    h.landmark,\n" + 
			"    h.delivery_date,\n" + 
			"    h.delivery_time,\n" + 
			"    h.production_date,\n" + 
			"    h.production_time,\n" + 
			"    h.insert_date_time,\n" + 
			"    h.insert_user_id,\n" + 
			"    h.order_platform,\n" + 
			"    h.del_status,\n" + 
			"    h.offer_id,\n" + 
			"    h.remark,\n" + 
			"    h.order_delivered_by,\n" + 
			"    h.ex_int1,\n" + 
			"   \n" + 
			"    h.ex_int3,\n" + 
			"    h.ex_int4,\n" + 
			"    h.ex_var1,\n" + 
			"    h.ex_var2,\n" + 
			"    h.ex_var3,\n" + 
			"    h.ex_var4,\n" + 
			"    h.ex_float1,\n" + 
			"    h.ex_float2,\n" + 
			"    h.ex_float3,\n" + 
			"    h.ex_float4,\n" + 
			"    h.ex_date1,\n" + 
			"    h.ex_date2,\n" + 
			"    h.billing_name,\n" + 
			"    h.billing_address,\n" + 
			"    h.customer_gstn_no,\n" + 
			"    h.delivery_type,\n" + 
			"    h.delivery_inst_id,\n" + 
			"    h.delivery_inst_text,\n" + 
			"    h.delivery_km,\n" + 
			"    h.delivery_charges,\n" + 
			"    h.payment_sub_mode,\n" + 
			"    h.is_agent,\n" + 
			"    h.uuid_no,h.ex_int2 , h.order_status as int_order_status               \n" + 
//            "    h.*                \n" + 
			"        FROM\n" + 
			"            tn_order_header h ,mn_status s            \n" + 
			"        WHERE\n" + 
			"            h.del_status = 1         \n" + 
			"            AND h.ex_int1= :compId AND s.status_id=h.order_status\n" + 
			"            AND h.fr_id=:frId\n" + 
			"            AND h.delivery_date BETWEEN :fromDate AND :toDate             \n" + 
			"            AND h.order_status IN(:status)) t1       \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                cust.cust_id,\n" + 
			"                cust.cust_name,\n" + 
			"                cust.cust_mobile_no,\n" + 
			"                cust.email_id                  \n" + 
			"            FROM\n" + 
			"                m_customer cust                   \n" + 
			"            WHERE\n" + 
			"                cust.del_status = 1              \n" + 
			"                AND cust.company_id=:compId       \n" + 
			"        ) t2               \n" + 
			"            ON      t1.cust_id = t2.cust_id       \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                f.fr_id,\n" + 
			"                CONCAT(f.fr_name,\n" + 
			"                ' - ',\n" + 
			"                f.fr_code) AS fr_name   ,f.fr_contact_no,   f.city                 \n" + 
			"            FROM\n" + 
			"                m_franchise f                   \n" + 
			"            WHERE\n" + 
			"            	f.fr_id=:frId\n" + 
			"                AND f.del_status = 1               \n" + 
			"                AND f.company_id=:compId        \n" + 
			"        ) t3               \n" + 
			"            ON      t1.fr_id = t3.fr_id      \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                city.city_id,\n" + 
			"                city.city_name                   \n" + 
			"            FROM\n" + 
			"                mn_city city                   \n" + 
			"            WHERE\n" + 
			"                city.del_status = 1               \n" + 
			"                AND city.company_id=:compId        \n" + 
			"        ) t5               \n" + 
			"            ON      t1.city_id = t5.city_id       \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                a.area_id,\n" + 
			"                a.area_name                  \n" + 
			"            FROM\n" + 
			"                mn_area a                   \n" + 
			"            WHERE\n" + 
			"                a.del_status = 1              \n" + 
			"                AND a.company_id=:compId         \n" + 
			"        ) t6               \n" + 
			"            ON      t1.area_id = t6.area_id  ORDER BY t1.delivery_date DESC", nativeQuery=true)
	List<GetOrderHeaderDisplay> getOrderHeaderByDeliveryDateFrId(@Param("fromDate") String fromDate, 
			@Param("toDate") String toDate, @Param("status") List<Integer> status, @Param("compId") int compId, @Param("frId") int frId);
	
	
	@Query(value="SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        t1.*,\n" + 
			"        COALESCE(t2.cust_name,\n" + 
			"        '') AS cust_name,\n" + 
			"        COALESCE(t2.cust_mobile_no,\n" + 
			"        '') AS cust_mobile,\n" + 
			"        COALESCE(t2.email_id,\n" + 
			"        '') AS email_id,\n" + 
			"        COALESCE(t3.fr_name,\n" + 
			"        '') AS fr_name, t3.fr_contact_no,t3.city,\n" + 
			"        COALESCE(t5.city_name,\n" + 
			"        '') AS city_name,\n" + 
			"        MONTH(t1.delivery_date) AS delivery_month,\n" + 
			"        YEAR(t1.delivery_date) AS delivery_year,\n" + 
			"        DATE_FORMAT(t1.order_date,\n" + 
			"        '%d-%m-%Y') AS order_date_display,\n" + 
			"        DATE_FORMAT(t1.delivery_date,\n" + 
			"        '%d-%m-%Y') AS delivery_date_display,\n" + 
			"         (t1.ex_var3) AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name, 'NA' AS area_name       \n" + 
			"    FROM\n" + 
			"        (      SELECT " + 
			//"            h.*                        \n" +
		 
			"            h.order_id,h.order_no,\n" + 
			"    h.order_date,\n" + 
			"    h.fr_id,\n" + 
			"    h.cust_id,\n" + 
			"    h.status,\n" + 
			"    h.taxable_amt,\n" + 
			"    h.cgst_amt,\n" + 
			"    h.sgst_amt,\n" + 
			"    h.igst_amt,\n" + 
			"    h.disc_amt,\n" + 
			"    h.item_disc_amt,\n" + 
			"    h.tax_amt,\n" + 
			"    h.total_amt,\n" + 
			"    s.status_value AS order_status,\n" + 
			"    h.paid_status,\n" + 
			"    h.payment_method,\n" + 
			"    h.payment_remark,\n" + 
			"    h.city_id,\n" + 
			"    h.area_id,\n" + 
			"    h.address_id,\n" + 
			"    h.address,\n" + 
			"    h.whatsapp_no,\n" + 
			"    h.landmark,\n" + 
			"    h.delivery_date,\n" + 
			"    h.delivery_time,\n" + 
			"    h.production_date,\n" + 
			"    h.production_time,\n" + 
			"    h.insert_date_time,\n" + 
			"    h.insert_user_id,\n" + 
			"    h.order_platform,\n" + 
			"    h.del_status,\n" + 
			"    h.offer_id,\n" + 
			"    h.remark,\n" + 
			"    h.order_delivered_by,\n" + 
			"    h.ex_int1,\n" + 
			"   \n" + 
			"    h.ex_int3,\n" + 
			"    h.ex_int4,\n" + 
			"    h.ex_var1,\n" + 
			"    h.ex_var2,\n" + 
			"    h.ex_var3,\n" + 
			"    h.ex_var4,\n" + 
			"    h.ex_float1,\n" + 
			"    h.ex_float2,\n" + 
			"    h.ex_float3,\n" + 
			"    h.ex_float4,\n" + 
			"    h.ex_date1,\n" + 
			"    h.ex_date2,\n" + 
			"    h.billing_name,\n" + 
			"    h.billing_address,\n" + 
			"    h.customer_gstn_no,\n" + 
			"    h.delivery_type,\n" + 
			"    h.delivery_inst_id,\n" + 
			"    h.delivery_inst_text,\n" + 
			"    h.delivery_km,\n" + 
			"    h.delivery_charges,\n" + 
			"    h.payment_sub_mode,\n" + 
			"    h.is_agent,\n" + 
			"    h.uuid_no,h.ex_int2 , h.order_status as int_order_status "+
			"        FROM\n" + 
			"            tn_order_header h, mn_status s             \n" + 
			"        WHERE\n" + 
			"         	h.cust_id=:custId AND s.status_id=h.order_status and " + 
			"            h.del_status = 1            \n" + 
			"            AND h.ex_int1=:compId           \n" + 
			"            AND h.delivery_date BETWEEN :fromDate AND :toDate              \n" + 
			"                    \n" + 
			"        ORDER BY\n" + 
			"            h.order_id  ) t1       \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                cust.cust_id,\n" + 
			"                cust.cust_name,\n" + 
			"                cust.cust_mobile_no,\n" + 
			"                cust.email_id                  \n" + 
			"            FROM\n" + 
			"                m_customer cust                   \n" + 
			"            WHERE\n" + 
			"                cust.del_status = 1              \n" + 
			"                AND cust.company_id=:compId        \n" + 
			"        ) t2               \n" + 
			"            ON      t1.cust_id = t2.cust_id       \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                f.fr_id,\n" + 
			"                CONCAT(f.fr_name,\n" + 
			"                ' - ',\n" + 
			"                f.fr_code) AS fr_name  ,f.fr_contact_no,   f.city                  \n" + 
			"            FROM\n" + 
			"                m_franchise f                   \n" + 
			"            WHERE\n" + 
			"                f.del_status = 1               \n" + 
			"                AND f.company_id=:compId        \n" + 
			"        ) t3               \n" + 
			"            ON      t1.fr_id = t3.fr_id      \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                city.city_id,\n" + 
			"                city.city_name                   \n" + 
			"            FROM\n" + 
			"                mn_city city                   \n" + 
			"            WHERE\n" + 
			"                city.del_status = 1               \n" + 
			"                AND city.company_id=:compId        \n" + 
			"        ) t5               \n" + 
			"            ON      t1.city_id = t5.city_id       \n" + 
			"",nativeQuery=true)
	List<GetOrderHeaderDisplay> getOrderHeaderByDeliveryDateCustId(@Param("fromDate") String fromDate, 
			@Param("toDate") String toDate, @Param("compId") int compId, @Param("custId") int custId);

	
	//USED FOr ECOMOps Order History order status taken from mn_status
	@Query(value="SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        t1.*,\n" + 
			"        COALESCE(t2.cust_name,\n" + 
			"        '') AS cust_name,\n" + 
			"        COALESCE(t2.cust_mobile_no,\n" + 
			"        '') AS cust_mobile,\n" + 
			"        COALESCE(t2.email_id,\n" + 
			"        '') AS email_id,\n" + 
			"        COALESCE(t3.fr_name,\n" + 
			"        '') AS fr_name, t3.fr_contact_no,t3.city,\n" + 
			"        COALESCE(t5.city_name,\n" + 
			"        '') AS city_name,\n" + 
			"        MONTH(t1.delivery_date) AS delivery_month,\n" + 
			"        YEAR(t1.delivery_date) AS delivery_year,\n" + 
			"        DATE_FORMAT(t1.order_date,\n" + 
			"        '%d-%m-%Y') AS order_date_display,\n" + 
			"        DATE_FORMAT(t1.delivery_date,\n" + 
			"        '%d-%m-%Y') AS delivery_date_display,\n" + 
			"         (t1.ex_var3 " + 
			"         ) AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name,\n" + 
			"        'NA' AS area_name            \n" + 
			"    FROM\n" + 
			"        (      SELECT h.order_id, h.order_no, h.order_date, h.fr_id, h.cust_id, h.status, h.taxable_amt, h.cgst_amt, h.sgst_amt, h.igst_amt, h.disc_amt, h.item_disc_amt, h.tax_amt, h.total_amt,  h.paid_status, h.payment_method, h.payment_remark, h.city_id, h.area_id, h.address_id, h.address, h.whatsapp_no, h.landmark, h.delivery_date, h.delivery_time, h.production_date, h.production_time, h.insert_date_time, h.insert_user_id, h.order_platform, h.del_status, h.offer_id, h.remark, h.order_delivered_by, h.ex_int1, h.ex_int2, h.ex_int3, h.ex_int4, h.ex_var1, h.ex_var2, h.ex_var3, h.ex_var4, h.ex_float1, h.ex_float2, h.ex_float3, h.ex_float4, h.ex_date1, h.ex_date2, h.billing_name, h.billing_address, h.customer_gstn_no, h.delivery_type, h.delivery_inst_id, h.delivery_inst_text, h.delivery_km, h.delivery_charges, h.payment_sub_mode, h.is_agent, h.uuid_no,"
			+ "			+ mn_status.status_value as order_status , h.order_status as int_order_status "+                               
			"        FROM\n" + 
			"            tn_order_header h,mn_status                      \n" + 
			"        WHERE\n" + 
			"            h.cust_id=:custId \n" + 
			"            AND h.del_status = 1                         \n" + 
			"            AND h.ex_int1=:compId                        \n" + 
			"            AND h.order_status=mn_status.status_id                       \n" + 
			"        ) t1            \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                cust.cust_id,\n" + 
			"                cust.cust_name,\n" + 
			"                cust.cust_mobile_no,\n" + 
			"                cust.email_id                               \n" + 
			"            FROM\n" + 
			"                m_customer cust                                \n" + 
			"            WHERE\n" + 
			"                cust.del_status = 1                               \n" + 
			"                AND cust.company_id=:compId                \n" + 
			"        ) t2                            \n" + 
			"            ON      t1.cust_id = t2.cust_id            \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                f.fr_id,\n" + 
			"                CONCAT(f.fr_name,\n" + 
			"                ' - ',\n" + 
			"                f.fr_code) AS fr_name ,f.fr_contact_no,   f.city                                \n" + 
			"            FROM\n" + 
			"                m_franchise f                                \n" + 
			"            WHERE\n" + 
			"                f.del_status = 1                                \n" + 
			"                AND f.company_id=:compId                 \n" + 
			"        ) t3                            \n" + 
			"            ON      t1.fr_id = t3.fr_id           \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                city.city_id,\n" + 
			"                city.city_name                                \n" + 
			"            FROM\n" + 
			"                mn_city city                                \n" + 
			"            WHERE\n" + 
			"                city.del_status = 1                                \n" + 
			"                AND city.company_id=:compId                \n" + 
			"        ) t5                            \n" + 
			"            ON      t1.city_id = t5.city_id\n" + 
			"            \n" + 
			"            ORDER BY t1.order_id DESC", nativeQuery=true)
	List<GetOrderHeaderDisplay> getOrderHIstoryList(@Param("compId") int compId, @Param("custId") int custId);
	
	
}
