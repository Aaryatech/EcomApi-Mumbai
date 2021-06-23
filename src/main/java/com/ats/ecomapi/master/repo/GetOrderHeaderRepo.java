package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetOrderHeaderDisplay;

public interface GetOrderHeaderRepo extends JpaRepository<GetOrderHeaderDisplay, Integer> {

	@Query(value=" SELECT\n" + 
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
			"        TIME_FORMAT(t1.delivery_time,\n" + 
			"        '%h:%i %p') AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name  \n" + 
			"    FROM\n" + 
			"        (      SELECT\n" + 
			"            h.*               \n" + 
			"        FROM\n" + 
			"            tn_order_header h    \n" + 
			"        WHERE\n" + 
			"            h.del_status = 1 \n" + 
			"         	AND h.ex_int1=:compId\n" + 
			"            AND h.delivery_date BETWEEN :fromDate AND :toDate \n" + 
			"            AND h.order_status IN(:status)       \n" + 
			"        ORDER BY\n" + 
			"            h.order_id  ) t1  \n" + 
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
			"        TIME_FORMAT(t1.delivery_time,\n" + 
			"        '%h:%i %p') AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name  \n" + 
			"    FROM\n" + 
			"        (      SELECT\n" + 
			"            h.*               \n" + 
			"        FROM\n" + 
			"            tn_order_header h    \n" + 
			"        WHERE\n" + 
			"            h.del_status = 1 \n" + 
			"         	AND h.ex_int1=:compId\n" + 
			"            AND h.production_date BETWEEN :fromDate AND :toDate \n" + 
			"            AND h.order_status IN(:status)       \n" + 
			"        ORDER BY\n" + 
			"            h.order_id  ) t1  \n" + 
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
			"            h.*                        \n" + 
			"        FROM\n" + 
			"            tn_order_header h             \n" + 
			"        WHERE\n" + 
			"            h.del_status = 1            \n" + 
			"            AND h.ex_int1= :compId\n" + 
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
			"        TIME_FORMAT(t1.delivery_time,\n" + 
			"        '%h:%i %p') AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name, 'NA' AS area_name       \n" + 
			"    FROM\n" + 
			"        (      SELECT\n" + 
			"            h.*                        \n" + 
			"        FROM\n" + 
			"            tn_order_header h             \n" + 
			"        WHERE\n" + 
			"         	h.cust_id=:custId AND\n" + 
			"            h.del_status = 1            \n" + 
			"            AND h.ex_int1=:compId           \n" + 
			"            AND h.delivery_date BETWEEN :fromDate AND :toDate              \n" + 
			"            AND h.order_status IN(0,1,2,3,4,5,6,7,8,9)                \n" + 
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
			"        TIME_FORMAT(t1.delivery_time,\n" + 
			"        '%h:%i %p') AS delivery_time_display,\n" + 
			"        MONTHNAME(t1.delivery_date) AS month_name,\n" + 
			"        'NA' AS area_name            \n" + 
			"    FROM\n" + 
			"        (      SELECT\n" + 
			"            h.*                                 \n" + 
			"        FROM\n" + 
			"            tn_order_header h                      \n" + 
			"        WHERE\n" + 
			"            h.cust_id=:custId \n" + 
			"            AND h.del_status = 1                         \n" + 
			"            AND h.ex_int1=:compId                        \n" + 
			"            AND h.order_status IN(\n" + 
			"                0,1,2,3,4,5,6,7,8,9\n" + 
			"            )                         \n" + 
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
