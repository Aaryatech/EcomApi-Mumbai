package com.ats.ecomapi.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.report.model.HeadOfficeReport;

public interface HeadOfficeReportRepo extends JpaRepository<HeadOfficeReport, Integer> {

	
	
        		@Query(value="SELECT\n" + 
        				"    UUID() AS id,\n" + 
        				"    t1.order_id,\n" + 
        				"    t1.delivery_date,\n" + 
        				"    t1.order_date,\n" + 
        				"    t1.order_no,\n" + 
        				"    t7.company_name,\n" + 
        				"    t1.payment_method,\n" + 
        				"    t1.order_status,\n" + 
        				"    t1.time_slot,\n" + 
        				"    t1.order_date,\n" + 
        				"    t1.delivery_date,\n" + 
        				"    0 AS delivery_charges,\n" + 
        				"    t1.qty,\n" + 
        				"    t1.total_amt,\n" + 
        				"    '-'AS hsn_code,\n" + 
        				"    0 AS mrp,\n" + 
        				"    0 AS sgst_amt,\n" + 
        				"    0 AS cgst_amt,\n" + 
        				"    0 AS igst_amt,\n" + 
        				"    COALESCE(t1.disc_amt,\n" + 
        				"    0) AS disc_amt,\n" + 
        				"    t3.product_id,\n" + 
        				"    t3.product_code,\n" + 
        				"    t3.product_name,\n" + 
        				"    t4.cat_name,\n" + 
        				"    t4.cat_prefix,\n" + 
        				"    t4.sub_cat_code,\n" + 
        				"    t4.sub_cat_name,\n" + 
        				"    t4.sub_cat_prefix,\n" + 
        				"    t5.cust_name,\n" + 
        				"    t6.fr_code,\n" + 
        				"    t6.fr_name,\n" + 
        				"    t6.fr_address,\n" + 
        				"    t6.pincode,\n" + 
        				//"    '-' AS coupon_code,\n" + 
        				"    COALESCE(t1.pay_ref_no,\n" + 
        				"    '-') AS pay_ref_no,"
        				+ "COALESCE(t1.ex_var2,'-') AS coupon_code"
        				+ ",t1.del_address,\n" + 
        				"t1.billing_address,\n" + 
        				"COALESCE(t1.cust_gst,'-') AS cust_gst,   \n" + 
        				"t1.uuid_no,\n" + 
        				"t1.detail_status,\n" + 
        				"COALESCE(t1.ret_perc,'-') AS ret_perc,\n" + 
        				"\n" + 
        				"t3.short_name\n" + 
        				"  \n" + 
        				"FROM\n" + 
        				"    (     SELECT h.ex_var2,\n" + 
        				"      \n" + 
        				"        h.order_no,\n" + 
        				"        h.delivery_date,\n" + 
        				"        h.order_date,\n" + 
        				"        h.ex_int1,\n" + 
        				"        h.payment_method,\n" + 
        				"        s.status_value AS order_status ,\n" + 
        				"      dtl.total_amt ,   \n" + 
        				"        h.cust_id,\n" + 
        				"        h.fr_id,\n" + 
        				"        h.ex_var3 AS time_slot,\n" + 
        				"        h.payment_remark AS pay_ref_no ,\n" + 
        				"dtl.order_detail_id,\n" + 
        				"            dtl.ex_float3 AS qty,\n" + 
        				"            dtl.order_id,\n" + 
        				"            dtl.disc_amt,\n" + 
        				"            dtl.item_id\n" + 
        				"           ,h.address as del_address,\n" + 
        				"h.billing_address,\n" + 
        				"h.ex_var1 as cust_gst,\n" + 
        				"h.uuid_no,\n" + 
        				"dtl.ex_int3 as detail_status,\n" + 
        				"dtl.ex_var3 as ret_perc\n" + 
        				" \n" + 
        				"\n" + 
        				"    FROM\n" + 
        				"        tn_order_header h     ,  tn_order_detail dtl ,mn_status s     \n" + 
        				"    WHERE\n" + 
        				"        h.del_status = 1 and dtl.del_status=1 and dtl.order_id=h.order_id AND s.status_id=h.order_status \n" + 
        				"        AND h.ex_int1 IN(\n" + 
        				"          :compIds " + 
        				"        ) \n" + 
        				"        AND h.delivery_date BETWEEN :fromDate and :toDate " + 
        				"        AND h.order_status IN (\n" + 
        				"          :orderStatus " + 
        				"        ) \n" + 
        				"        AND h.payment_method =:paymentMethod     \n" + 
        				"    ORDER BY\n" + 
        				"        h.order_id     DESC ) t1 \n" + 
        				"\n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            p.product_id,\n" + 
        				"            p.product_code,\n" + 
        				"            p.product_name,\n" + 
        				"            p.prod_cat_id,\n" + 
        				"            p.prod_sub_cat_id ,p.short_name    \n" + 
        				"        FROM\n" + 
        				"            m_product p     \n" + 
        				"        WHERE\n" + 
        				"            p.del_status = 1 \n" + 
        				"    ) t3 \n" + 
        				"        ON     t3.product_id = t1.item_id \n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            cat.cat_id,\n" + 
        				"            cat.cat_name,\n" + 
        				"            cat.cat_prefix,\n" + 
        				"            sub_cat.sub_cat_id,\n" + 
        				"            sub_cat.sub_cat_code,\n" + 
        				"            sub_cat.sub_cat_name,\n" + 
        				"            sub_cat.sub_cat_prefix     \n" + 
        				"        FROM\n" + 
        				"            m_category cat,\n" + 
        				"            m_sub_category sub_cat     \n" + 
        				"        WHERE\n" + 
        				"            cat.cat_id = sub_cat.cat_id \n" + 
        				"    ) t4 \n" + 
        				"        ON     t3.prod_cat_id = t4.cat_id \n" + 
        				"        AND t3.prod_sub_cat_id = t4.sub_cat_id \n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            cust.cust_id,\n" + 
        				"            cust.cust_name     \n" + 
        				"        FROM\n" + 
        				"            m_customer cust     \n" + 
        				"        WHERE\n" + 
        				"            cust.del_status = 1 \n" + 
        				"    ) t5 \n" + 
        				"        ON     t1.cust_id = t5.cust_id \n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            fr.fr_id,\n" + 
        				"            fr.fr_code,\n" + 
        				"            fr.fr_name,\n" + 
        				"            fr.fr_address,\n" + 
        				"            fr.pincode     \n" + 
        				"        FROM\n" + 
        				"            m_franchise fr     \n" + 
        				"        WHERE\n" + 
        				"            fr.del_status = 1 \n" + 
        				"    ) t6 \n" + 
        				"        ON     t1.fr_id = t6.fr_id \n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            comp.company_id,\n" + 
        				"            comp.company_name \n" + 
        				"        FROM\n" + 
        				"            m_company comp \n" + 
        				"        WHERE\n" + 
        				"            comp.del_status = 1 \n" + 
        				"    ) t7 \n" + 
        				"        ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
        		List<HeadOfficeReport> getHeadOfficeReport(@Param("fromDate") String fromDate, @Param("toDate") String toDate, 
        				@Param("orderStatus") List<String> orderStatus, @Param("compIds") List<String> compIds, @Param("paymentMethod") int paymentMethod);
        		
        
	
        		
        		@Query(value="SELECT\n" + 
        				"    UUID() AS id,\n" + 
        				"    t1.order_id,\n" + 
        				"    t1.delivery_date,\n" + 
        				"    t1.order_date,\n" + 
        				"    t1.order_no,\n" + 
        				"    t7.company_name,\n" + 
        				"    t1.payment_method,\n" + 
        				"    t1.order_status,\n" + 
        				"    t1.time_slot,\n" + 
        				"    t1.order_date,\n" + 
        				"    t1.delivery_date,\n" + 
        				"    0 AS delivery_charges,\n" + 
        				"    t1.qty,\n" + 
        				"    t1.total_amt,\n" + 
        				"    '-'AS hsn_code,\n" + 
        				"    0 AS mrp,\n" + 
        				"    0 AS sgst_amt,\n" + 
        				"    0 AS cgst_amt,\n" + 
        				"    0 AS igst_amt,\n" + 
        				"    COALESCE(t1.disc_amt,\n" + 
        				"    0) AS disc_amt,\n" + 
        				"    t3.product_id,\n" + 
        				"    t3.product_code,\n" + 
        				"    t3.product_name,\n" + 
        				"    t4.cat_name,\n" + 
        				"    t4.cat_prefix,\n" + 
        				"    t4.sub_cat_code,\n" + 
        				"    t4.sub_cat_name,\n" + 
        				"    t4.sub_cat_prefix,\n" + 
        				"    t5.cust_name,\n" + 
        				"    t6.fr_code,\n" + 
        				"    t6.fr_name,\n" + 
        				"    t6.fr_address,\n" + 
        				"    t6.pincode,\n" + 
        				//"    '-' AS coupon_code,\n" + 
        				"    COALESCE(t1.pay_ref_no,\n" + 
        				"    '-') AS pay_ref_no,"
        				+ "COALESCE(t1.ex_var2,'-') AS coupon_code  \n" + 
        				" ,t1.del_address,\n" + 
        				"t1.billing_address,\n" + 
        				"COALESCE(t1.cust_gst,'-') AS cust_gst,   \n" + 
        				"t1.uuid_no,\n" + 
        				"t1.detail_status,\n" + 
        				"COALESCE(t1.ret_perc,'-') AS ret_perc,\n" + 
        				"\n" + 
        				"t3.short_name\n" + 
        				""
        				+ "FROM\n" + 
        				"    (     SELECT h.ex_var2,\n" + 
        				"      \n" + 
        				"        h.order_no,\n" + 
        				"        h.delivery_date,\n" + 
        				"        h.order_date,\n" + 
        				"        h.ex_int1,\n" + 
        				"        h.payment_method,\n" + 
        				"        s.status_value AS order_status,\n" + 
        				"      dtl.total_amt ,   \n" + 
        				"        h.cust_id,\n" + 
        				"        h.fr_id,\n" + 
        				"        h.ex_var3 AS time_slot,\n" + 
        				"        h.payment_remark AS pay_ref_no ,\n" + 
        				"dtl.order_detail_id,\n" + 
        				"            dtl.ex_float3 AS qty,\n" + 
        				"            dtl.order_id,\n" + 
        				"            dtl.disc_amt,\n" + 
        				"            dtl.item_id\n" + 
        				"           ,h.address as del_address,\n" + 
        				"h.billing_address,\n" + 
        				"h.ex_var1 as cust_gst,\n" + 
        				"h.uuid_no,\n" + 
        				"dtl.ex_int3 as detail_status,\n" + 
        				"dtl.ex_var3 as ret_perc\n" + 
        				" \n" + 
        				"\n" + 
        				"    FROM\n" + 
        				"        tn_order_header h     ,  tn_order_detail dtl  ,mn_status s    \n" + 
        				"    WHERE\n" + 
        				"        h.del_status = 1 and dtl.del_status=1 and dtl.order_id=h.order_id\n" + 
        				"        AND h.ex_int1 IN(\n" + 
        				"          :compIds " + 
        				"        ) \n" + 
        				"        AND h.production_date BETWEEN :fromDate and :toDate AND s.status_id=h.order_status" + 
        				"        AND h.order_status IN (\n" + 
        				"          :orderStatus " + 
        				"        ) \n" + 
        				"        AND h.payment_method =:paymentMethod     \n" + 
        				"    ORDER BY\n" + 
        				"        h.order_id     DESC ) t1 \n" + 
        				"\n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            p.product_id,\n" + 
        				"            p.product_code,\n" + 
        				"            p.product_name,\n" + 
        				"            p.prod_cat_id,\n" + 
        				"            p.prod_sub_cat_id ,p.short_name     \n" + 
        				"        FROM\n" + 
        				"            m_product p     \n" + 
        				"        WHERE\n" + 
        				"            p.del_status = 1 \n" + 
        				"    ) t3 \n" + 
        				"        ON     t3.product_id = t1.item_id \n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            cat.cat_id,\n" + 
        				"            cat.cat_name,\n" + 
        				"            cat.cat_prefix,\n" + 
        				"            sub_cat.sub_cat_id,\n" + 
        				"            sub_cat.sub_cat_code,\n" + 
        				"            sub_cat.sub_cat_name,\n" + 
        				"            sub_cat.sub_cat_prefix     \n" + 
        				"        FROM\n" + 
        				"            m_category cat,\n" + 
        				"            m_sub_category sub_cat     \n" + 
        				"        WHERE\n" + 
        				"            cat.cat_id = sub_cat.cat_id \n" + 
        				"    ) t4 \n" + 
        				"        ON     t3.prod_cat_id = t4.cat_id \n" + 
        				"        AND t3.prod_sub_cat_id = t4.sub_cat_id \n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            cust.cust_id,\n" + 
        				"            cust.cust_name     \n" + 
        				"        FROM\n" + 
        				"            m_customer cust     \n" + 
        				"        WHERE\n" + 
        				"            cust.del_status = 1 \n" + 
        				"    ) t5 \n" + 
        				"        ON     t1.cust_id = t5.cust_id \n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            fr.fr_id,\n" + 
        				"            fr.fr_code,\n" + 
        				"            fr.fr_name,\n" + 
        				"            fr.fr_address,\n" + 
        				"            fr.pincode     \n" + 
        				"        FROM\n" + 
        				"            m_franchise fr     \n" + 
        				"        WHERE\n" + 
        				"            fr.del_status = 1 \n" + 
        				"    ) t6 \n" + 
        				"        ON     t1.fr_id = t6.fr_id \n" + 
        				"LEFT JOIN\n" + 
        				"    (\n" + 
        				"        SELECT\n" + 
        				"            comp.company_id,\n" + 
        				"            comp.company_name \n" + 
        				"        FROM\n" + 
        				"            m_company comp \n" + 
        				"        WHERE\n" + 
        				"            comp.del_status = 1 \n" + 
        				"    ) t7 \n" + 
        				"        ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
      
	List<HeadOfficeReport> getHeadOfficeReportByProdctnDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate, 
			@Param("orderStatus") List<String> orderStatus, @Param("compIds") List<String> compIds, @Param("paymentMethod") int paymentMethod);
	
	
	

        		
        		
        		@Query(value="SELECT\n" + 
        				"        UUID() AS id,\n" + 
        				"        t1.order_id,\n" + 
        				"        t1.delivery_date,\n" + 
        				"        t1.order_date,\n" + 
        				"        t1.order_no,\n" + 
        				"        t7.company_name,\n" + 
        				"        t1.payment_method,\n" + 
        				"        t1.order_status,\n" + 
        				"        t1.time_slot,\n" + 
        				"        t1.order_date,\n" + 
        				"        t1.delivery_date,\n" + 
        				"        t1.delivery_charges,\n" + 
        				"        t1.qty,\n" + 
        				"        t1.total_amt,\n" + 
        				"        t1.hsn_code,\n" + 
        				"        t1.mrp,\n" + 
        				"        t1.sgst_amt,\n" + 
        				"        t1.cgst_amt,\n" + 
        				"        t1.igst_amt,\n" + 
        				"        COALESCE(t1.disc_amt,\n" + 
        				"        0) AS disc_amt,\n" + 
        				"        t3.product_id,\n" + 
        				"        t3.product_code,\n" + 
        				"        t3.product_name,\n" + 
        				"        t4.cat_name,\n" + 
        				"        t4.cat_prefix,\n" + 
        				"        t4.sub_cat_code,\n" + 
        				"        t4.sub_cat_name,\n" + 
        				"        t4.sub_cat_prefix,\n" + 
        				"        t5.cust_name,\n" + 
        				"        t6.fr_code,\n" + 
        				"        t6.fr_name,\n" + 
        				"        t6.fr_address,\n" + 
        				"        t6.pincode,\n" + 
        			//	"        '-' AS coupon_code,\n" + 
        				"        COALESCE(t1.pay_ref_no,\n" + 
        				"        '-') AS pay_ref_no  ,"
        				+ "COALESCE(t1.ex_var2,'-') AS coupon_code "
        				+ ",t1.del_address,\n" + 
        				"t1.billing_address,\n" + 
        				"COALESCE(t1.cust_gst,'-') AS cust_gst,   \n" + 
        				"t1.uuid_no,\n" + 
        				"t1.detail_status,\n" + 
        				"COALESCE(t1.ret_perc,'-') AS ret_perc,\n" + 
        				"\n" + 
        				"t3.short_name\n" + 
        				"    \n" + 
        				"    FROM\n" + 
        				"        (     SELECT\n" + 
        				"            h.order_id,\n" + 
        				"            h.order_no,\n" + 
        				"            h.delivery_date,\n" + 
        				"            h.order_date,\n" + 
        				"            h.ex_int1,\n" + 
        				"            h.payment_method,\n" + 
        				"            s.status_value AS order_status,\n" + 
        				"            h.ex_var2,\n" + 
        				"            h.cust_id,\n" + 
        				"            h.fr_id,\n" + 
        				"            h.ex_var3 AS time_slot,\n" + 
        				"            h.payment_remark AS pay_ref_no,\n" + 
        				"            h.delivery_charges,\n" + 
        				"dtl.order_detail_id,\n" + 
        				"                dtl.ex_float3 AS qty,\n" + 
        				//"                //dtl.order_id,\n" + 
        				"                dtl.disc_amt,\n" + 
        				"                dtl.item_id,\n" + 
        				"                dtl.total_amt,\n" + 
        				"                dtl.hsn_code,\n" + 
        				"                dtl.mrp,\n" + 
        				"                dtl.sgst_amt,\n" + 
        				"                dtl.cgst_amt,\n" + 
        				"                dtl.igst_amt"
        				+ ",h.address as del_address,\n" + 
        				"h.billing_address,\n" + 
        				"h.ex_var1 as cust_gst,\n" + 
        				"h.uuid_no,\n" + 
        				"dtl.ex_int3 as detail_status,\n" + 
        				"dtl.ex_var3 as ret_perc\n" + 
        				"                      \n" + 
        				"        FROM\n" + 
        				"            tn_order_header h,  tn_order_detail dtl  ,mn_status s              \n" + 
        				"        WHERE\n" + 
        				"            h.del_status = 1    and dtl.del_status = 1       and dtl.order_id=h.order_id          \n" + 
        				"            AND h.ex_int1 =:compId              \n" + 
        				"            AND h.delivery_date BETWEEN :fromDate AND :toDate  AND s.status_id=h.order_status         \n" + 
        				"            AND h.order_status IN (\n" + 
        				"                 :orderStatus " + 
        				"            )              \n" + 
        				"            AND h.payment_method =:paymentMethod             \n" + 
        				"        ORDER BY\n" + 
        				"            h.order_id     DESC ) t1      \n" + 
        				"    \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                p.product_id,\n" + 
        				"                p.product_code,\n" + 
        				"                p.product_name,\n" + 
        				"                p.prod_cat_id,\n" + 
        				"                p.prod_sub_cat_id  ,p.short_name                \n" + 
        				"            FROM\n" + 
        				"                m_product p                  \n" + 
        				"            WHERE\n" + 
        				"                p.del_status = 1          \n" + 
        				"        ) t3              \n" + 
        				"            ON     t3.product_id = t1.item_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                cat.cat_id,\n" + 
        				"                cat.cat_name,\n" + 
        				"                cat.cat_prefix,\n" + 
        				"                sub_cat.sub_cat_id,\n" + 
        				"                sub_cat.sub_cat_code,\n" + 
        				"                sub_cat.sub_cat_name,\n" + 
        				"                sub_cat.sub_cat_prefix                  \n" + 
        				"            FROM\n" + 
        				"                m_category cat,\n" + 
        				"                m_sub_category sub_cat                  \n" + 
        				"            WHERE\n" + 
        				"                cat.cat_id = sub_cat.cat_id          \n" + 
        				"        ) t4              \n" + 
        				"            ON     t3.prod_cat_id = t4.cat_id              \n" + 
        				"            AND t3.prod_sub_cat_id = t4.sub_cat_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                cust.cust_id,\n" + 
        				"                cust.cust_name                  \n" + 
        				"            FROM\n" + 
        				"                m_customer cust                  \n" + 
        				"            WHERE\n" + 
        				"                cust.del_status = 1          \n" + 
        				"        ) t5              \n" + 
        				"            ON     t1.cust_id = t5.cust_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                fr.fr_id,\n" + 
        				"                fr.fr_code,\n" + 
        				"                fr.fr_name,\n" + 
        				"                fr.fr_address,\n" + 
        				"                fr.pincode                  \n" + 
        				"            FROM\n" + 
        				"                m_franchise fr                  \n" + 
        				"            WHERE\n" + 
        				"                fr.del_status = 1          \n" + 
        				"        ) t6              \n" + 
        				"            ON     t1.fr_id = t6.fr_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                comp.company_id,\n" + 
        				"                comp.company_name              \n" + 
        				"            FROM\n" + 
        				"                m_company comp              \n" + 
        				"            WHERE\n" + 
        				"                comp.del_status = 1          \n" + 
        				"        ) t7              \n" + 
        				"            ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
        		List<HeadOfficeReport> getFrUnitReportByDelvrDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate, 
        				@Param("orderStatus") List<String> orderStatus, @Param("compId") int compId, @Param("paymentMethod") int paymentMethod);

	
        		@Query(value="SELECT\n" + 
        				"        UUID() AS id,\n" + 
        				"        t1.order_id,\n" + 
        				"        t1.delivery_date,\n" + 
        				"        t1.order_date,\n" + 
        				"        t1.order_no,\n" + 
        				"        t7.company_name,\n" + 
        				"        t1.payment_method,\n" + 
        				"        t1.order_status,\n" + 
        				"        t1.time_slot,\n" + 
        				"        t1.order_date,\n" + 
        				"        t1.delivery_date,\n" + 
        				"        t1.delivery_charges,\n" + 
        				"        t1.qty,\n" + 
        				"        t1.total_amt,\n" + 
        				"        t1.hsn_code,\n" + 
        				"        t1.mrp,\n" + 
        				"        t1.sgst_amt,\n" + 
        				"        t1.cgst_amt,\n" + 
        				"        t1.igst_amt,\n" + 
        				"        COALESCE(t1.disc_amt,\n" + 
        				"        0) AS disc_amt,\n" + 
        				"        t3.product_id,\n" + 
        				"        t3.product_code,\n" + 
        				"        t3.product_name,\n" + 
        				"        t4.cat_name,\n" + 
        				"        t4.cat_prefix,\n" + 
        				"        t4.sub_cat_code,\n" + 
        				"        t4.sub_cat_name,\n" + 
        				"        t4.sub_cat_prefix,\n" + 
        				"        t5.cust_name,\n" + 
        				"        t6.fr_code,\n" + 
        				"        t6.fr_name,\n" + 
        				"        t6.fr_address,\n" + 
        				"        t6.pincode,\n" + 
        				//"        '-' AS coupon_code,\n" + 
        				"        COALESCE(t1.pay_ref_no,\n" + 
        				"        '-') AS pay_ref_no,COALESCE(t1.ex_var2,'-') AS coupon_code       "
        				+ ",t1.del_address,\n" + 
        				"t1.billing_address,\n" + 
        				"COALESCE(t1.cust_gst,'-') AS cust_gst,   \n" + 
        				"t1.uuid_no,\n" + 
        				"t1.detail_status,\n" + 
        				"COALESCE(t1.ret_perc,'-') AS ret_perc,\n" + 
        				"\n" + 
        				"t3.short_name\n" + 
        				""
        				+ "\n" + 
        				"    FROM\n" + 
        				"        (     SELECT h.ex_var2, \n" + 
        				"            h.order_id,\n" + 
        				"            h.order_no,\n" + 
        				"            h.delivery_date,\n" + 
        				"            h.order_date,\n" + 
        				"            h.ex_int1,\n" + 
        				"            h.payment_method,\n" + 
        				"            s.status_value AS order_status,,\n" + 
        				//"          //  h.total_amt,\n" + 
        				"            h.cust_id,\n" + 
        				"            h.fr_id,\n" + 
        				"            h.ex_var3 AS time_slot,\n" + 
        				"            h.payment_remark AS pay_ref_no,\n" + 
        				"            h.delivery_charges,\n" + 
        				"dtl.order_detail_id,\n" + 
        				"                dtl.ex_float3 AS qty,\n" + 
        				//"                //dtl.order_id,\n" + 
        				"                dtl.disc_amt,\n" + 
        				"                dtl.item_id,\n" + 
        				"                dtl.total_amt,\n" + 
        				"                dtl.hsn_code,\n" + 
        				"                dtl.mrp,\n" + 
        				"                dtl.sgst_amt,\n" + 
        				"                dtl.cgst_amt,\n" + 
        				"                dtl.igst_amt "
        				+ ",h.address as del_address,\n" + 
        				"h.billing_address,\n" + 
        				"h.ex_var1 as cust_gst,\n" + 
        				"h.uuid_no,\n" + 
        				"dtl.ex_int3 as detail_status,\n" + 
        				"dtl.ex_var3 as ret_perc\n" + 
        				"                     \n" + 
        				"        FROM\n" + 
        				"            tn_order_header h,  tn_order_detail dtl    ,mn_status s             \n" + 
        				"        WHERE\n" + 
        				"            h.del_status = 1    and dtl.del_status = 1       and dtl.order_id=h.order_id          \n" + 
        				"            AND h.ex_int1 =:compId              \n" + 
        				"            AND h.production_date BETWEEN :fromDate AND :toDate  AND s.status_id=h.order_status           \n" + 
        				"            AND h.order_status IN (\n" + 
        				"                 :orderStatus " + 
        				"            )              \n" + 
        				"            AND h.payment_method =:paymentMethod             \n" + 
        				"        ORDER BY\n" + 
        				"            h.order_id     DESC ) t1      \n" + 
        				"    \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                p.product_id,\n" + 
        				"                p.product_code,\n" + 
        				"                p.product_name,\n" + 
        				"                p.prod_cat_id,\n" + 
        				"                p.prod_sub_cat_id   ,p.short_name               \n" + 
        				"            FROM\n" + 
        				"                m_product p                  \n" + 
        				"            WHERE\n" + 
        				"                p.del_status = 1          \n" + 
        				"        ) t3              \n" + 
        				"            ON     t3.product_id = t1.item_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                cat.cat_id,\n" + 
        				"                cat.cat_name,\n" + 
        				"                cat.cat_prefix,\n" + 
        				"                sub_cat.sub_cat_id,\n" + 
        				"                sub_cat.sub_cat_code,\n" + 
        				"                sub_cat.sub_cat_name,\n" + 
        				"                sub_cat.sub_cat_prefix                  \n" + 
        				"            FROM\n" + 
        				"                m_category cat,\n" + 
        				"                m_sub_category sub_cat                  \n" + 
        				"            WHERE\n" + 
        				"                cat.cat_id = sub_cat.cat_id          \n" + 
        				"        ) t4              \n" + 
        				"            ON     t3.prod_cat_id = t4.cat_id              \n" + 
        				"            AND t3.prod_sub_cat_id = t4.sub_cat_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                cust.cust_id,\n" + 
        				"                cust.cust_name                  \n" + 
        				"            FROM\n" + 
        				"                m_customer cust                  \n" + 
        				"            WHERE\n" + 
        				"                cust.del_status = 1          \n" + 
        				"        ) t5              \n" + 
        				"            ON     t1.cust_id = t5.cust_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                fr.fr_id,\n" + 
        				"                fr.fr_code,\n" + 
        				"                fr.fr_name,\n" + 
        				"                fr.fr_address,\n" + 
        				"                fr.pincode                  \n" + 
        				"            FROM\n" + 
        				"                m_franchise fr                  \n" + 
        				"            WHERE\n" + 
        				"                fr.del_status = 1          \n" + 
        				"        ) t6              \n" + 
        				"            ON     t1.fr_id = t6.fr_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                comp.company_id,\n" + 
        				"                comp.company_name              \n" + 
        				"            FROM\n" + 
        				"                m_company comp              \n" + 
        				"            WHERE\n" + 
        				"                comp.del_status = 1          \n" + 
        				"        ) t7              \n" + 
        				"            ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
        		
        		List<HeadOfficeReport> getFrUnitReportByPrdctnDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate, 
        				@Param("orderStatus") List<String> orderStatus, @Param("compId") int compId, @Param("paymentMethod") int paymentMethod);

        		
        		
        		//For Fr Shubham
        		
        		
        		@Query(value="SELECT\n" + 
        				"        UUID() AS id,\n" + 
        				"        t1.order_id,\n" + 
        				"        t1.delivery_date,\n" + 
        				"        t1.order_date,\n" + 
        				"        t1.order_no,\n" + 
        				"        t7.company_name,\n" + 
        				"        t1.payment_method,\n" + 
        				"        t1.order_status,\n" + 
        				"        t1.time_slot,\n" + 
        				"        t1.order_date,\n" + 
        				"        t1.delivery_date,\n" + 
        				"        t1.delivery_charges,\n" + 
        				"        t1.qty,\n" + 
        				"        t1.total_amt,\n" + 
        				"        t1.hsn_code,\n" + 
        				"        t1.mrp,\n" + 
        				"        t1.sgst_amt,\n" + 
        				"        t1.cgst_amt,\n" + 
        				"        t1.igst_amt,\n" + 
        				"        COALESCE(t1.disc_amt,\n" + 
        				"        0) AS disc_amt,\n" + 
        				"        t3.product_id,\n" + 
        				"        t3.product_code,\n" + 
        				"        t3.product_name,\n" + 
        				"        t4.cat_name,\n" + 
        				"        t4.cat_prefix,\n" + 
        				"        t4.sub_cat_code,\n" + 
        				"        t4.sub_cat_name,\n" + 
        				"        t4.sub_cat_prefix,\n" + 
        				"        t5.cust_name,\n" + 
        				"        t6.fr_code,\n" + 
        				"        t6.fr_name,\n" + 
        				"        t6.fr_address,\n" + 
        				"        t6.pincode,\n" + 
        				//"        '-' AS coupon_code,\n" + 
        				"        COALESCE(t1.pay_ref_no,\n" + 
        				"        '-') AS pay_ref_no ,COALESCE(t1.ex_var2,'-') AS coupon_code "
        				+ ",t1.del_address,\n" + 
        				"t1.billing_address,\n" + 
        				"COALESCE(t1.cust_gst,'-') AS cust_gst,   \n" + 
        				"t1.uuid_no,\n" + 
        				"t1.detail_status,\n" + 
        				"COALESCE(t1.ret_perc,'-') AS ret_perc,\n" + 
        				"\n" + 
        				"t3.short_name\n" + 
        				"    \n" + 
        				"    FROM\n" + 
        				"        (     SELECT h.ex_var2, \n" + 
        				"            h.order_id,\n" + 
        				"            h.order_no,\n" + 
        				"            h.delivery_date,\n" + 
        				"            h.order_date,\n" + 
        				"            h.ex_int1,\n" + 
        				"            h.payment_method,\n" + 
        				"            s.status_value AS order_status,\n" + 
        				//"          //  h.total_amt,\n" + 
        				"            h.cust_id,\n" + 
        				"            h.fr_id,\n" + 
        				"            h.ex_var3 AS time_slot,\n" + 
        				"            h.payment_remark AS pay_ref_no,\n" + 
        				"            h.delivery_charges,\n" + 
        				"dtl.order_detail_id,\n" + 
        				"                dtl.ex_float3 AS qty,\n" + 
        				//"                //dtl.order_id,\n" + 
        				"                dtl.disc_amt,\n" + 
        				"                dtl.item_id,\n" + 
        				"                dtl.total_amt,\n" + 
        				"                dtl.hsn_code,\n" + 
        				"                dtl.mrp,\n" + 
        				"                dtl.sgst_amt,\n" + 
        				"                dtl.cgst_amt,\n" + 
        				"                dtl.igst_amt "
        				+ ",h.address as del_address,\n" + 
        				"h.billing_address,\n" + 
        				"h.ex_var1 as cust_gst,\n" + 
        				"h.uuid_no,\n" + 
        				"dtl.ex_int3 as detail_status,\n" + 
        				"dtl.ex_var3 as ret_perc\n" + 
        				"                     \n" + 
        				"        FROM\n" + 
        				"            tn_order_header h,  tn_order_detail dtl   ,mn_status s              \n" + 
        				"        WHERE\n" + 
        				"            h.del_status = 1 and h.fr_id=:frId    and dtl.del_status = 1       and dtl.order_id=h.order_id          \n" + 
        				"            AND h.ex_int1 =:compId              \n" + 
        				"            AND h.delivery_date BETWEEN :fromDate AND :toDate    AND s.status_id=h.order_status            \n" + 
        				"            AND h.order_status IN (\n" + 
        				"                 :orderStatus " + 
        				"            )              \n" + 
        				"            AND h.payment_method =:paymentMethod             \n" + 
        				"        ORDER BY\n" + 
        				"            h.order_id     DESC ) t1      \n" + 
        				"    \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                p.product_id,\n" + 
        				"                p.product_code,\n" + 
        				"                p.product_name,\n" + 
        				"                p.prod_cat_id,\n" + 
        				"                p.prod_sub_cat_id  ,p.short_name                \n" + 
        				"            FROM\n" + 
        				"                m_product p                  \n" + 
        				"            WHERE\n" + 
        				"                p.del_status = 1          \n" + 
        				"        ) t3              \n" + 
        				"            ON     t3.product_id = t1.item_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                cat.cat_id,\n" + 
        				"                cat.cat_name,\n" + 
        				"                cat.cat_prefix,\n" + 
        				"                sub_cat.sub_cat_id,\n" + 
        				"                sub_cat.sub_cat_code,\n" + 
        				"                sub_cat.sub_cat_name,\n" + 
        				"                sub_cat.sub_cat_prefix                  \n" + 
        				"            FROM\n" + 
        				"                m_category cat,\n" + 
        				"                m_sub_category sub_cat                  \n" + 
        				"            WHERE\n" + 
        				"                cat.cat_id = sub_cat.cat_id          \n" + 
        				"        ) t4              \n" + 
        				"            ON     t3.prod_cat_id = t4.cat_id              \n" + 
        				"            AND t3.prod_sub_cat_id = t4.sub_cat_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                cust.cust_id,\n" + 
        				"                cust.cust_name                  \n" + 
        				"            FROM\n" + 
        				"                m_customer cust                  \n" + 
        				"            WHERE\n" + 
        				"                cust.del_status = 1          \n" + 
        				"        ) t5              \n" + 
        				"            ON     t1.cust_id = t5.cust_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                fr.fr_id,\n" + 
        				"                fr.fr_code,\n" + 
        				"                fr.fr_name,\n" + 
        				"                fr.fr_address,\n" + 
        				"                fr.pincode                  \n" + 
        				"            FROM\n" + 
        				"                m_franchise fr                  \n" + 
        				"            WHERE\n" + 
        				"                fr.del_status = 1          \n" + 
        				"        ) t6              \n" + 
        				"            ON     t1.fr_id = t6.fr_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                comp.company_id,\n" + 
        				"                comp.company_name              \n" + 
        				"            FROM\n" + 
        				"                m_company comp              \n" + 
        				"            WHERE\n" + 
        				"                comp.del_status = 1          \n" + 
        				"        ) t7              \n" + 
        				"            ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
        		List<HeadOfficeReport> getFrUnitReportByDelvrDateAndFrId(@Param("fromDate") String fromDate, @Param("toDate") String toDate, 
        				@Param("orderStatus") List<String> orderStatus, @Param("compId") int compId, @Param("paymentMethod") int paymentMethod,
        				@Param("frId") int frId);

	@Query(value="SELECT\n" + 
        				"        UUID() AS id,\n" + 
        				"        t1.order_id,\n" + 
        				"        t1.delivery_date,\n" + 
        				"        t1.order_date,\n" + 
        				"        t1.order_no,\n" + 
        				"        t7.company_name,\n" + 
        				"        t1.payment_method,\n" + 
        				"        t1.order_status,\n" + 
        				"        t1.time_slot,\n" + 
        				"        t1.order_date,\n" + 
        				"        t1.delivery_date,\n" + 
        				"        t1.delivery_charges,\n" + 
        				"        t1.qty,\n" + 
        				"        t1.total_amt,\n" + 
        				"        t1.hsn_code,\n" + 
        				"        t1.mrp,\n" + 
        				"        t1.sgst_amt,\n" + 
        				"        t1.cgst_amt,\n" + 
        				"        t1.igst_amt,\n" + 
        				"        COALESCE(t1.disc_amt,\n" + 
        				"        0) AS disc_amt,\n" + 
        				"        t3.product_id,\n" + 
        				"        t3.product_code,\n" + 
        				"        t3.product_name,\n" + 
        				"        t4.cat_name,\n" + 
        				"        t4.cat_prefix,\n" + 
        				"        t4.sub_cat_code,\n" + 
        				"        t4.sub_cat_name,\n" + 
        				"        t4.sub_cat_prefix,\n" + 
        				"        t5.cust_name,\n" + 
        				"        t6.fr_code,\n" + 
        				"        t6.fr_name,\n" + 
        				"        t6.fr_address,\n" + 
        				"        t6.pincode,\n" + 
        			//	"        '-' AS coupon_code,\n" + 
        				"        COALESCE(t1.pay_ref_no,\n" + 
        				"        '-') AS pay_ref_no ,COALESCE(t1.ex_var2,'-') AS coupon_code     "
        				+ ",t1.del_address,\n" + 
        				"t1.billing_address,\n" + 
        				"COALESCE(t1.cust_gst,'-') AS cust_gst,   \n" + 
        				"t1.uuid_no,\n" + 
        				"t1.detail_status,\n" + 
        				"COALESCE(t1.ret_perc,'-') AS ret_perc,\n" + 
        				"\n" + 
        				"t3.short_name\n" + 
        				""
        				+ "\n" + 
        				"    FROM\n" + 
        				"        (     SELECT h.ex_var2, " + 
        				"            h.order_id,\n" + 
        				"            h.order_no,\n" + 
        				"            h.delivery_date,\n" + 
        				"            h.order_date,\n" + 
        				"            h.ex_int1,\n" + 
        				"            h.payment_method,\n" + 
        				"            s.status_value AS order_status,\n" + 
        				//"          //  h.total_amt,\n" + 
        				"            h.cust_id,\n" + 
        				"            h.fr_id,\n" + 
        				"            h.ex_var3 AS time_slot,\n" + 
        				"            h.payment_remark AS pay_ref_no,\n" + 
        				"            h.delivery_charges,\n" + 
        				"dtl.order_detail_id,\n" + 
        				"                dtl.ex_float3 AS qty,\n" + 
        				//"                //dtl.order_id,\n" + 
        				"                dtl.disc_amt,\n" + 
        				"                dtl.item_id,\n" + 
        				"                dtl.total_amt,\n" + 
        				"                dtl.hsn_code,\n" + 
        				"                dtl.mrp,\n" + 
        				"                dtl.sgst_amt,\n" + 
        				"                dtl.cgst_amt,\n" + 
        				"                dtl.igst_amt "
        				+ ",h.address as del_address,\n" + 
        				"h.billing_address,\n" + 
        				"h.ex_var1 as cust_gst,\n" + 
        				"h.uuid_no,\n" + 
        				"dtl.ex_int3 as detail_status,\n" + 
        				"dtl.ex_var3 as ret_perc\n" + 
        				"                     \n" + 
        				"        FROM\n" + 
        				"            tn_order_header h,  tn_order_detail dtl  ,mn_status s            \n" + 
        				"        WHERE\n" + 
        				"            h.del_status = 1 and h.fr_id=:frId   and dtl.del_status = 1       and dtl.order_id=h.order_id          \n" + 
        				"            AND h.ex_int1 =:compId              \n" + 
        				"            AND h.production_date BETWEEN :fromDate AND :toDate AND s.status_id=h.order_status           \n" + 
        				"            AND h.order_status IN (\n" + 
        				"                 :orderStatus " + 
        				"            )              \n" + 
        				"            AND h.payment_method =:paymentMethod             \n" + 
        				"        ORDER BY\n" + 
        				"            h.order_id     DESC ) t1      \n" + 
        				"    \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                p.product_id,\n" + 
        				"                p.product_code,\n" + 
        				"                p.product_name,\n" + 
        				"                p.prod_cat_id,\n" + 
        				"                p.prod_sub_cat_id ,p.short_name                 \n" + 
        				"            FROM\n" + 
        				"                m_product p                  \n" + 
        				"            WHERE\n" + 
        				"                p.del_status = 1          \n" + 
        				"        ) t3              \n" + 
        				"            ON     t3.product_id = t1.item_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                cat.cat_id,\n" + 
        				"                cat.cat_name,\n" + 
        				"                cat.cat_prefix,\n" + 
        				"                sub_cat.sub_cat_id,\n" + 
        				"                sub_cat.sub_cat_code,\n" + 
        				"                sub_cat.sub_cat_name,\n" + 
        				"                sub_cat.sub_cat_prefix                  \n" + 
        				"            FROM\n" + 
        				"                m_category cat,\n" + 
        				"                m_sub_category sub_cat                  \n" + 
        				"            WHERE\n" + 
        				"                cat.cat_id = sub_cat.cat_id          \n" + 
        				"        ) t4              \n" + 
        				"            ON     t3.prod_cat_id = t4.cat_id              \n" + 
        				"            AND t3.prod_sub_cat_id = t4.sub_cat_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                cust.cust_id,\n" + 
        				"                cust.cust_name                  \n" + 
        				"            FROM\n" + 
        				"                m_customer cust                  \n" + 
        				"            WHERE\n" + 
        				"                cust.del_status = 1          \n" + 
        				"        ) t5              \n" + 
        				"            ON     t1.cust_id = t5.cust_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                fr.fr_id,\n" + 
        				"                fr.fr_code,\n" + 
        				"                fr.fr_name,\n" + 
        				"                fr.fr_address,\n" + 
        				"                fr.pincode                  \n" + 
        				"            FROM\n" + 
        				"                m_franchise fr                  \n" + 
        				"            WHERE\n" + 
        				"                fr.del_status = 1          \n" + 
        				"        ) t6              \n" + 
        				"            ON     t1.fr_id = t6.fr_id      \n" + 
        				"    LEFT JOIN\n" + 
        				"        (\n" + 
        				"            SELECT\n" + 
        				"                comp.company_id,\n" + 
        				"                comp.company_name              \n" + 
        				"            FROM\n" + 
        				"                m_company comp              \n" + 
        				"            WHERE\n" + 
        				"                comp.del_status = 1          \n" + 
        				"        ) t7              \n" + 
        				"            ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
        		
        		List<HeadOfficeReport> getFrUnitReportByPrdctnDateAndFrId(@Param("fromDate") String fromDate, @Param("toDate") String toDate, 
        				@Param("orderStatus") List<String> orderStatus, @Param("compId") int compId, @Param("paymentMethod") int paymentMethod,
        				@Param("frId") int frId);



	
	
	/*
	 * @Query(value="SELECT\n" +
	 * "    UUID() AS id, t1.order_id, t1.delivery_date, t1.order_date, t1.order_no, t7.company_name, t1.payment_method, t1.order_status, t1.time_slot, t1.order_date, t1.delivery_date,0 AS delivery_charges,\n"
	 * + "    t2.qty,\n" + "    t2.total_amt,\n" + "	 '-'AS hsn_code,\n" +
	 * "    0 AS mrp,\n" + "    0 AS sgst_amt,\n" + "    0 AS cgst_amt,\n" +
	 * "    0 AS igst_amt,\n" + "    COALESCE(t2.disc_amt, 0) AS disc_amt,\n" +
	 * "    t3.product_id,\n" + "    t3.product_code,\n" + "    t3.product_name,\n"
	 * + "    t4.cat_name,\n" + "    t4.cat_prefix,\n" + "    t4.sub_cat_code,\n" +
	 * "    t4.sub_cat_name,\n" + "    t4.sub_cat_prefix,\n" + "    t5.cust_name,\n"
	 * + "    t6.fr_code,\n" + "    t6.fr_name,\n" + "    t6.fr_address,\n" +
	 * "    t6.pincode,\n" + "    '-' AS coupon_code,\n" +
	 * "    COALESCE(t1.pay_ref_no, '-') AS pay_ref_no\n" + "FROM\n" + "    (\n" +
	 * "    SELECT\n" + "        h.order_id, h.order_no,\n" +
	 * "        h.delivery_date,\n" + "        h.order_date,\n" +
	 * "        h.ex_int1,\n" + "        h.payment_method,\n" +
	 * "        h.order_status,\n" + "        h.total_amt,\n" +
	 * "        h.cust_id,\n" + "        h.fr_id,\n" +
	 * "        h.ex_var3 AS time_slot,\n" +
	 * "        h.payment_remark AS pay_ref_no\n" + "    FROM\n" +
	 * "        tn_order_header h\n" + "    WHERE\n" +
	 * "        h.del_status = 1 AND h.ex_int1 IN(:compIds) AND h.delivery_date BETWEEN :fromDate AND :toDate AND h.order_status IN (:orderStatus) AND h.payment_method = :paymentMethod\n"
	 * + "    ORDER BY\n" + "        h.order_id\n" + "    DESC\n" + ") t1\n" +
	 * "LEFT JOIN(\n" + "    SELECT\n" + "        dtl.order_detail_id,\n" +
	 * "        dtl.ex_float3 AS qty,\n" + "        dtl.order_id,\n" +
	 * "    	dtl.disc_amt,\n" + "        dtl.item_id,\n" +
	 * "        dtl.total_amt\n" + "    FROM\n" + "        tn_order_detail dtl\n" +
	 * "    WHERE\n" + "        dtl.del_status = 1\n" + ") t2\n" + "ON\n" +
	 * "    t1.order_id = t2.order_id\n" + "LEFT JOIN(\n" + "    SELECT\n" +
	 * "        p.product_id,\n" + "        p.product_code,\n" +
	 * "        p.product_name,\n" + "        p.prod_cat_id,\n" +
	 * "        p.prod_sub_cat_id\n" + "    FROM\n" + "        m_product p\n" +
	 * "    WHERE\n" + "        p.del_status = 1\n" + ") t3\n" + "ON\n" +
	 * "    t3.product_id = t2.item_id\n" + "LEFT JOIN(\n" + "    SELECT\n" +
	 * "        cat.cat_id,\n" + "        cat.cat_name,\n" +
	 * "        cat.cat_prefix,\n" + "        sub_cat.sub_cat_id,\n" +
	 * "        sub_cat.sub_cat_code,\n" + "        sub_cat.sub_cat_name,\n" +
	 * "        sub_cat.sub_cat_prefix\n" + "    FROM\n" +
	 * "        m_category cat,\n" + "        m_sub_category sub_cat\n" +
	 * "    WHERE\n" + "        cat.cat_id = sub_cat.cat_id\n" + ") t4\n" + "ON\n" +
	 * "    t3.prod_cat_id = t4.cat_id AND t3.prod_sub_cat_id = t4.sub_cat_id\n" +
	 * "LEFT JOIN(\n" + "    SELECT\n" + "        cust.cust_id,\n" +
	 * "        cust.cust_name\n" + "    FROM\n" + "        m_customer cust\n" +
	 * "    WHERE\n" + "        cust.del_status = 1\n" + ") t5\n" + "ON\n" +
	 * "    t1.cust_id = t5.cust_id\n" + "LEFT JOIN(\n" + "    SELECT\n" +
	 * "        fr.fr_id,\n" + "        fr.fr_code,\n" + "        fr.fr_name,\n" +
	 * "        fr.fr_address,\n" + "        fr.pincode\n" + "    FROM\n" +
	 * "        m_franchise fr\n" + "    WHERE\n" + "        fr.del_status = 1\n" +
	 * ") t6\n" + "ON\n" + "    t1.fr_id = t6.fr_id\n" + "LEFT JOIN(\n" +
	 * "    SELECT\n" + "    comp.company_id,\n" + "    comp.company_name\n" +
	 * "FROM\n" + "    m_company comp\n" + "WHERE\n" + "    comp.del_status = 1\n" +
	 * ") t7\n" + "ON\n" + "    t1.ex_int1 = t7.company_id",nativeQuery=true)
	 * List<HeadOfficeReport> getHeadOfficeReport_OLD(@Param("fromDate") String
	 * fromDate, @Param("toDate") String toDate,
	 * 
	 * @Param("orderStatus") List<String> orderStatus, @Param("compIds")
	 * List<String> compIds, @Param("paymentMethod") int paymentMethod);
	 */
	/*
	 * @Query(value="SELECT\n" +
	 * "                UUID() AS id, t1.order_id, t1.delivery_date, t1.order_date, t1.order_no, t7.company_name, t1.payment_method, t1.order_status, t1.time_slot, t1.order_date, t1.delivery_date, 0 AS delivery_charges,\n"
	 * + "                COALESCE(t2.qty, 0) AS qty,\n" +
	 * "                t2.total_amt,\n" + "		 		 '-' AS hsn_code,\n" +
	 * "        		 0 AS mrp,\n" + "        		 0 AS sgst_amt,\n" +
	 * "       		 0 AS cgst_amt,\n" + "        		 0 AS igst_amt,\n" +
	 * "                COALESCE(t2.disc_amt, 0) AS disc_amt,\n" +
	 * "                COALESCE(t3.product_id, 0) AS product_id,\n" +
	 * "                COALESCE(t3.product_code, '-') AS product_code,\n" +
	 * "                COALESCE(t3.product_name, '-') AS product_name,\n" +
	 * "                t4.cat_name,\n" + "                t4.cat_prefix,\n" +
	 * "                t4.sub_cat_code,\n" + "                t4.sub_cat_name,\n" +
	 * "                t4.sub_cat_prefix,\n" + "                t5.cust_name,\n" +
	 * "                t6.fr_code,\n" + "                t6.fr_name,\n" +
	 * "                t6.fr_address,\n" + "                t6.pincode,\n" +
	 * "                '-' AS coupon_code,\n" +
	 * "                COALESCE(t1.pay_ref_no, '-') AS pay_ref_no\n" +
	 * "            FROM\n" + "                (\n" + "                SELECT\n" +
	 * "                    h.order_id, h.order_no,\n" +
	 * "                    h.production_date AS delivery_date,\n" +
	 * "                    h.order_date,\n" + "                    h.ex_int1,\n" +
	 * "                    h.payment_method,\n" +
	 * "                    h.order_status,\n" +
	 * "                    h.total_amt,\n" + "                    h.cust_id,\n" +
	 * "                    h.fr_id,\n" +
	 * "                    h.ex_var3 AS time_slot,\n" +
	 * "                    h.payment_remark AS pay_ref_no\n" +
	 * "                FROM\n" + "                    tn_order_header h\n" +
	 * "                WHERE\n" +
	 * "                    h.del_status = 1 AND h.ex_int1 IN(:compIds) AND h.production_date BETWEEN :fromDate AND :toDate AND h.order_status IN (:orderStatus) AND h.payment_method = :paymentMethod\n"
	 * + "                ORDER BY\n" + "                    h.order_id\n" +
	 * "                DESC\n" + "            ) t1\n" + "            LEFT JOIN(\n"
	 * + "                SELECT\n" + "                    dtl.order_detail_id,\n" +
	 * "                    dtl.ex_float3 AS qty,\n" +
	 * "                    dtl.order_id,\n" + "                   dtl.disc_amt,\n"
	 * + "                    dtl.item_id,\n" +
	 * "                    dtl.total_amt\n" + "                FROM\n" +
	 * "                    tn_order_detail dtl\n" + "                WHERE\n" +
	 * "                    dtl.del_status = 1\n" + "            ) t2\n" +
	 * "            ON\n" + "                t1.order_id = t2.order_id\n" +
	 * "            LEFT JOIN(\n" + "                SELECT\n" +
	 * "                    p.product_id,\n" +
	 * "                    p.product_code,\n" +
	 * "                    p.product_name,\n" +
	 * "                    p.prod_cat_id,\n" +
	 * "                    p.prod_sub_cat_id\n" + "                FROM\n" +
	 * "                    m_product p\n" + "                WHERE\n" +
	 * "                    p.del_status = 1\n" + "            ) t3\n" +
	 * "            ON\n" + "                t3.product_id = t2.item_id\n" +
	 * "            LEFT JOIN(\n" + "                SELECT\n" +
	 * "                    cat.cat_id,\n" + "                    cat.cat_name,\n" +
	 * "                    cat.cat_prefix,\n" +
	 * "                    sub_cat.sub_cat_id,\n" +
	 * "                    sub_cat.sub_cat_code,\n" +
	 * "                    sub_cat.sub_cat_name,\n" +
	 * "                    sub_cat.sub_cat_prefix\n" + "                FROM\n" +
	 * "                    m_category cat,\n" +
	 * "                    m_sub_category sub_cat\n" + "                WHERE\n" +
	 * "                    cat.cat_id = sub_cat.cat_id\n" + "            ) t4\n" +
	 * "            ON\n" +
	 * "                t3.prod_cat_id = t4.cat_id AND t3.prod_sub_cat_id = t4.sub_cat_id\n"
	 * + "            LEFT JOIN(\n" + "                SELECT\n" +
	 * "                    cust.cust_id,\n" +
	 * "                    cust.cust_name\n" + "                FROM\n" +
	 * "                    m_customer cust\n" + "                WHERE\n" +
	 * "                    cust.del_status = 1\n" + "            ) t5\n" +
	 * "            ON\n" + "                t1.cust_id = t5.cust_id\n" +
	 * "            LEFT JOIN(\n" + "                SELECT\n" +
	 * "                    fr.fr_id,\n" + "                    fr.fr_code,\n" +
	 * "                    fr.fr_name,\n" + "                    fr.fr_address,\n"
	 * + "                    fr.pincode\n" + "                FROM\n" +
	 * "                    m_franchise fr\n" + "                WHERE\n" +
	 * "                    fr.del_status = 1\n" + "            ) t6\n" +
	 * "            ON\n" + "                t1.fr_id = t6.fr_id\n" +
	 * "            LEFT JOIN(\n" + "                SELECT\n" +
	 * "                comp.company_id,\n" + "                comp.company_name\n"
	 * + "            FROM\n" + "                m_company comp\n" +
	 * "            WHERE\n" + "                comp.del_status = 1\n" +
	 * "            ) t7\n" + "            ON\n" +
	 * "                t1.ex_int1 = t7.company_id",nativeQuery=true)
	 */
	
	/*
	 * @Query(value="SELECT\n" + "        UUID() AS id,\n" +
	 * "        t1.order_id,\n" + "        t1.delivery_date,\n" +
	 * "        t1.order_date,\n" + "        t1.order_no,\n" +
	 * "        t7.company_name,\n" + "        t1.payment_method,\n" +
	 * "        t1.order_status,\n" + "        t1.time_slot,\n" +
	 * "        t1.order_date,\n" +
	 * "        t1.delivery_date, t1.delivery_charges,\n" + "        t2.qty,\n" +
	 * "        t2.total_amt,\n" + " 		 t2.hsn_code,\n" + "        t2.mrp,\n" +
	 * "        t2.sgst_amt,\n" + "        t2.cgst_amt,\n" +
	 * "        t2.igst_amt,\n" + "        COALESCE(t2.disc_amt,\n" +
	 * "        0) AS disc_amt,\n" + "        t3.product_id,\n" +
	 * "        t3.product_code,\n" + "        t3.product_name,\n" +
	 * "        t4.cat_name,\n" + "        t4.cat_prefix,\n" +
	 * "        t4.sub_cat_code,\n" + "        t4.sub_cat_name,\n" +
	 * "        t4.sub_cat_prefix,\n" + "        t5.cust_name,\n" +
	 * "        t6.fr_code,\n" + "        t6.fr_name,\n" +
	 * "        t6.fr_address,\n" + "        t6.pincode,\n" +
	 * "        '-' AS coupon_code,\n" + "        COALESCE(t1.pay_ref_no,\n" +
	 * "        '-') AS pay_ref_no \n" + "    FROM\n" + "        (     SELECT\n" +
	 * "            h.order_id,\n" + "            h.order_no,\n" +
	 * "            h.delivery_date,\n" + "            h.order_date,\n" +
	 * "            h.ex_int1,\n" + "            h.payment_method,\n" +
	 * "            h.order_status,\n" + "            h.total_amt,\n" +
	 * "            h.cust_id,\n" + "            h.fr_id,\n" +
	 * "            h.ex_var3 AS time_slot,\n" +
	 * "            h.payment_remark AS pay_ref_no,\n" +
	 * "         	h.delivery_charges\n" + "        FROM\n" +
	 * "            tn_order_header h     \n" + "        WHERE\n" +
	 * "            h.del_status = 1 \n" + "            AND h.ex_int1 =:compId  \n"
	 * + "            AND h.delivery_date BETWEEN :fromDate AND :toDate \n" +
	 * "            AND h.order_status IN (:orderStatus) \n" +
	 * "            AND h.payment_method = :paymentMethod     \n" +
	 * "        ORDER BY\n" + "            h.order_id     DESC ) t1 \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                dtl.order_detail_id,\n" +
	 * "                dtl.ex_float3 AS qty,\n" + "                dtl.order_id,\n"
	 * + "                dtl.disc_amt,\n" + "                dtl.item_id,\n" +
	 * "                dtl.total_amt,     \n" + "            	dtl.hsn_code,\n" +
	 * "            	dtl.mrp,\n" + "            	dtl.sgst_amt,\n" +
	 * "            	dtl.cgst_amt,\n" + "            	dtl.igst_amt\n" +
	 * "            FROM\n" + "                tn_order_detail dtl     \n" +
	 * "            WHERE\n" + "                dtl.del_status = 1 \n" +
	 * "        ) t2 \n" + "            ON     t1.order_id = t2.order_id \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                p.product_id,\n" + "                p.product_code,\n" +
	 * "                p.product_name,\n" + "                p.prod_cat_id,\n" +
	 * "                p.prod_sub_cat_id     \n" + "            FROM\n" +
	 * "                m_product p     \n" + "            WHERE\n" +
	 * "                p.del_status = 1 \n" + "        ) t3 \n" +
	 * "            ON     t3.product_id = t2.item_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                cat.cat_id,\n" +
	 * "                cat.cat_name,\n" + "                cat.cat_prefix,\n" +
	 * "                sub_cat.sub_cat_id,\n" +
	 * "                sub_cat.sub_cat_code,\n" +
	 * "                sub_cat.sub_cat_name,\n" +
	 * "                sub_cat.sub_cat_prefix     \n" + "            FROM\n" +
	 * "                m_category cat,\n" +
	 * "                m_sub_category sub_cat     \n" + "            WHERE\n" +
	 * "                cat.cat_id = sub_cat.cat_id \n" + "        ) t4 \n" +
	 * "            ON     t3.prod_cat_id = t4.cat_id \n" +
	 * "            AND t3.prod_sub_cat_id = t4.sub_cat_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                cust.cust_id,\n" +
	 * "                cust.cust_name     \n" + "            FROM\n" +
	 * "                m_customer cust     \n" + "            WHERE\n" +
	 * "                cust.del_status = 1 \n" + "        ) t5 \n" +
	 * "            ON     t1.cust_id = t5.cust_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                fr.fr_id,\n" +
	 * "                fr.fr_code,\n" + "                fr.fr_name,\n" +
	 * "                fr.fr_address,\n" + "                fr.pincode     \n" +
	 * "            FROM\n" + "                m_franchise fr     \n" +
	 * "            WHERE\n" + "                fr.del_status = 1 \n" +
	 * "        ) t6 \n" + "            ON     t1.fr_id = t6.fr_id \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                comp.company_id,\n" + "                comp.company_name \n"
	 * + "            FROM\n" + "                m_company comp \n" +
	 * "            WHERE\n" + "                comp.del_status = 1 \n" +
	 * "        ) t7 \n" +
	 * "            ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
	 * List<HeadOfficeReport> getFrUnitReportByDelvrDate(@Param("fromDate") String
	 * fromDate, @Param("toDate") String toDate,
	 * 
	 * @Param("orderStatus") List<String> orderStatus, @Param("compId") int
	 * compId, @Param("paymentMethod") int paymentMethod);
	 * 
	 * @Query(value="SELECT\n" + "        UUID() AS id,\n" +
	 * "        t1.order_id,\n" + "        t1.delivery_date,\n" +
	 * "        t1.order_date,\n" + "        t1.order_no,\n" +
	 * "        t7.company_name,\n" + "        t1.payment_method,\n" +
	 * "        t1.order_status,\n" + "        t1.time_slot,\n" +
	 * "        t1.order_date,\n" +
	 * "        t1.delivery_date,t1.delivery_charges,\n" + "        t2.qty,\n" +
	 * "        t2.total_amt,\n" + "		 t2.hsn_code,\n" + "        t2.mrp,\n" +
	 * "        t2.sgst_amt,\n" + "        t2.cgst_amt,\n" +
	 * "        t2.igst_amt,\n" + "        COALESCE(t2.disc_amt,\n" +
	 * "        0) AS disc_amt,\n" + "        t3.product_id,\n" +
	 * "        t3.product_code,\n" + "        t3.product_name,\n" +
	 * "        t4.cat_name,\n" + "        t4.cat_prefix,\n" +
	 * "        t4.sub_cat_code,\n" + "        t4.sub_cat_name,\n" +
	 * "        t4.sub_cat_prefix,\n" + "        t5.cust_name,\n" +
	 * "        t6.fr_code,\n" + "        t6.fr_name,\n" +
	 * "        t6.fr_address,\n" + "        t6.pincode,\n" +
	 * "        '-' AS coupon_code,\n" + "        COALESCE(t1.pay_ref_no,\n" +
	 * "        '-') AS pay_ref_no \n" + "    FROM\n" + "        (     SELECT\n" +
	 * "            h.order_id,\n" + "            h.order_no,\n" +
	 * "            h.delivery_date,\n" + "            h.order_date,\n" +
	 * "            h.ex_int1,\n" + "            h.payment_method,\n" +
	 * "            h.order_status,\n" + "            h.total_amt,\n" +
	 * "            h.cust_id,\n" + "            h.fr_id,\n" +
	 * "            h.ex_var3 AS time_slot,\n" +
	 * "            h.payment_remark AS pay_ref_no,\n" +
	 * "         	 h.delivery_charges\n" + "        FROM\n" +
	 * "            tn_order_header h     \n" + "        WHERE\n" +
	 * "            h.del_status = 1 \n" + "            AND h.ex_int1 =:compId  \n"
	 * + "            AND h.production_date BETWEEN :fromDate AND :toDate \n" +
	 * "            AND h.order_status IN (:orderStatus) \n" +
	 * "            AND h.payment_method = :paymentMethod     \n" +
	 * "        ORDER BY\n" + "            h.order_id     DESC ) t1 \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                dtl.order_detail_id,\n" +
	 * "                dtl.ex_float3 AS qty,\n" + "                dtl.order_id,\n"
	 * + "                dtl.disc_amt,\n" + "                dtl.item_id,\n" +
	 * "                dtl.total_amt,     \n" + "            	dtl.hsn_code,\n" +
	 * "            	dtl.mrp,\n" + "            	dtl.sgst_amt,\n" +
	 * "            	dtl.cgst_amt,\n" + "            	dtl.igst_amt\n" +
	 * "            FROM\n" + "                tn_order_detail dtl     \n" +
	 * "            WHERE\n" + "                dtl.del_status = 1 \n" +
	 * "        ) t2 \n" + "            ON     t1.order_id = t2.order_id \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                p.product_id,\n" + "                p.product_code,\n" +
	 * "                p.product_name,\n" + "                p.prod_cat_id,\n" +
	 * "                p.prod_sub_cat_id     \n" + "            FROM\n" +
	 * "                m_product p     \n" + "            WHERE\n" +
	 * "                p.del_status = 1 \n" + "        ) t3 \n" +
	 * "            ON     t3.product_id = t2.item_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                cat.cat_id,\n" +
	 * "                cat.cat_name,\n" + "                cat.cat_prefix,\n" +
	 * "                sub_cat.sub_cat_id,\n" +
	 * "                sub_cat.sub_cat_code,\n" +
	 * "                sub_cat.sub_cat_name,\n" +
	 * "                sub_cat.sub_cat_prefix     \n" + "            FROM\n" +
	 * "                m_category cat,\n" +
	 * "                m_sub_category sub_cat     \n" + "            WHERE\n" +
	 * "                cat.cat_id = sub_cat.cat_id \n" + "        ) t4 \n" +
	 * "            ON     t3.prod_cat_id = t4.cat_id \n" +
	 * "            AND t3.prod_sub_cat_id = t4.sub_cat_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                cust.cust_id,\n" +
	 * "                cust.cust_name     \n" + "            FROM\n" +
	 * "                m_customer cust     \n" + "            WHERE\n" +
	 * "                cust.del_status = 1 \n" + "        ) t5 \n" +
	 * "            ON     t1.cust_id = t5.cust_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                fr.fr_id,\n" +
	 * "                fr.fr_code,\n" + "                fr.fr_name,\n" +
	 * "                fr.fr_address,\n" + "                fr.pincode     \n" +
	 * "            FROM\n" + "                m_franchise fr     \n" +
	 * "            WHERE\n" + "                fr.del_status = 1 \n" +
	 * "        ) t6 \n" + "            ON     t1.fr_id = t6.fr_id \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                comp.company_id,\n" + "                comp.company_name \n"
	 * + "            FROM\n" + "                m_company comp \n" +
	 * "            WHERE\n" + "                comp.del_status = 1 \n" +
	 * "        ) t7 \n" +
	 * "            ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
	 * List<HeadOfficeReport> getFrUnitReportByPrdctnDate(@Param("fromDate") String
	 * fromDate, @Param("toDate") String toDate,
	 * 
	 * @Param("orderStatus") List<String> orderStatus, @Param("compId") int
	 * compId, @Param("paymentMethod") int paymentMethod);
	 */
	
	/*
	 * @Query(value="SELECT\n" + "        UUID() AS id,\n" +
	 * "        t1.order_id,\n" + "        t1.delivery_date,\n" +
	 * "        t1.order_date,\n" + "        t1.order_no,\n" +
	 * "        t7.company_name,\n" + "        t1.payment_method,\n" +
	 * "        t1.order_status,\n" + "        t1.time_slot,\n" +
	 * "        t1.order_date,\n" +
	 * "        t1.delivery_date,t1.delivery_charges,\n" + "        t2.qty,\n" +
	 * "        t2.total_amt,\n" + "		 t2.hsn_code,\n" + "        t2.mrp,\n" +
	 * "        t2.sgst_amt,\n" + "        t2.cgst_amt,\n" +
	 * "        t2.igst_amt,\n" + "        COALESCE(t2.disc_amt,\n" +
	 * "        0) AS disc_amt,\n" + "        t3.product_id,\n" +
	 * "        t3.product_code,\n" + "        t3.product_name,\n" +
	 * "        t4.cat_name,\n" + "        t4.cat_prefix,\n" +
	 * "        t4.sub_cat_code,\n" + "        t4.sub_cat_name,\n" +
	 * "        t4.sub_cat_prefix,\n" + "        t5.cust_name,\n" +
	 * "        t6.fr_code,\n" + "        t6.fr_name,\n" +
	 * "        t6.fr_address,\n" + "        t6.pincode,\n" +
	 * "        '-' AS coupon_code,\n" + "        COALESCE(t1.pay_ref_no,\n" +
	 * "        '-') AS pay_ref_no \n" + "    FROM\n" + "        (     SELECT\n" +
	 * "            h.order_id,\n" + "            h.order_no,\n" +
	 * "            h.delivery_date,\n" + "            h.order_date,\n" +
	 * "            h.ex_int1,\n" + "            h.payment_method,\n" +
	 * "            h.order_status,\n" + "            h.total_amt,\n" +
	 * "            h.cust_id,\n" + "            h.fr_id,\n" +
	 * "            h.ex_var3 AS time_slot,\n" +
	 * "            h.payment_remark AS pay_ref_no,\n" +
	 * "         	 h.delivery_charges\n" + "        FROM\n" +
	 * "            tn_order_header h     \n" + "        WHERE\n" +
	 * "            h.del_status = 1 \n" + "            AND h.ex_int1 =:compId  \n"
	 * + "            AND h.production_date BETWEEN :fromDate AND :toDate \n" +
	 * "            AND h.order_status IN (:orderStatus) \n" +
	 * "            AND h.payment_method = :paymentMethod     \n" +
	 * "        ORDER BY\n" + "            h.order_id     DESC ) t1 \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                dtl.order_detail_id,\n" +
	 * "                dtl.ex_float3 AS qty,\n" + "                dtl.order_id,\n"
	 * + "                dtl.disc_amt,\n" + "                dtl.item_id,\n" +
	 * "                dtl.total_amt,     \n" + "            	dtl.hsn_code,\n" +
	 * "            	dtl.mrp,\n" + "            	dtl.sgst_amt,\n" +
	 * "            	dtl.cgst_amt,\n" + "            	dtl.igst_amt\n" +
	 * "            FROM\n" + "                tn_order_detail dtl     \n" +
	 * "            WHERE\n" + "                dtl.del_status = 1 \n" +
	 * "        ) t2 \n" + "            ON     t1.order_id = t2.order_id \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                p.product_id,\n" + "                p.product_code,\n" +
	 * "                p.product_name,\n" + "                p.prod_cat_id,\n" +
	 * "                p.prod_sub_cat_id     \n" + "            FROM\n" +
	 * "                m_product p     \n" + "            WHERE\n" +
	 * "                p.del_status = 1 \n" + "        ) t3 \n" +
	 * "            ON     t3.product_id = t2.item_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                cat.cat_id,\n" +
	 * "                cat.cat_name,\n" + "                cat.cat_prefix,\n" +
	 * "                sub_cat.sub_cat_id,\n" +
	 * "                sub_cat.sub_cat_code,\n" +
	 * "                sub_cat.sub_cat_name,\n" +
	 * "                sub_cat.sub_cat_prefix     \n" + "            FROM\n" +
	 * "                m_category cat,\n" +
	 * "                m_sub_category sub_cat     \n" + "            WHERE\n" +
	 * "                cat.cat_id = sub_cat.cat_id \n" + "        ) t4 \n" +
	 * "            ON     t3.prod_cat_id = t4.cat_id \n" +
	 * "            AND t3.prod_sub_cat_id = t4.sub_cat_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                cust.cust_id,\n" +
	 * "                cust.cust_name     \n" + "            FROM\n" +
	 * "                m_customer cust     \n" + "            WHERE\n" +
	 * "                cust.del_status = 1 \n" + "        ) t5 \n" +
	 * "            ON     t1.cust_id = t5.cust_id \n" + "    LEFT JOIN\n" +
	 * "        (\n" + "            SELECT\n" + "                fr.fr_id,\n" +
	 * "                fr.fr_code,\n" + "                fr.fr_name,\n" +
	 * "                fr.fr_address,\n" + "                fr.pincode     \n" +
	 * "            FROM\n" + "                m_franchise fr     \n" +
	 * "            WHERE\n" + "                fr.del_status = 1 \n" +
	 * "        ) t6 \n" + "            ON     t1.fr_id = t6.fr_id \n" +
	 * "    LEFT JOIN\n" + "        (\n" + "            SELECT\n" +
	 * "                comp.company_id,\n" + "                comp.company_name \n"
	 * + "            FROM\n" + "                m_company comp \n" +
	 * "            WHERE\n" + "                comp.del_status = 1 \n" +
	 * "        ) t7 \n" +
	 * "            ON     t1.ex_int1 = t7.company_id",nativeQuery=true)
	 */
}
