package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetOrderTrailDisplay;



public interface GetOrderTrailDisplayRepo extends JpaRepository<GetOrderTrailDisplay, Integer> {

	@Query(value="SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t1.*,\r\n" + 
			"        CASE WHEN t1.ex_int1 = 1 THEN COALESCE(t2.user_name, '') ELSE CASE WHEN t1.ex_int1 = 4 THEN COALESCE(t3.user_name, '') ELSE COALESCE(t4.user_name, '')\r\n" + 
			"END\r\n" + 
			"END AS user_name,\r\n" + 
			"DATE_FORMAT(\r\n" + 
			"    t1.action_date_time,\r\n" + 
			"    '%d-%m-%Y %h:%i %p'\r\n" + 
			") AS trail_date\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t.*\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_trail t\r\n" + 
			"    WHERE\r\n" + 
			"        t.order_id IN(\r\n" + 
			"        SELECT\r\n" + 
			"            order_id\r\n" + 
			"        FROM\r\n" + 
			"            tn_order_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND delivery_date BETWEEN :fromDate AND :toDate \r\n" + 
			"    )\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        u.user_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            u.user_name,\r\n" + 
			"            ' (',\r\n" + 
			"            t.user_type_name,\r\n" + 
			"            ') '\r\n" + 
			"        ) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_user u,\r\n" + 
			"        mn_user_type t\r\n" + 
			"    WHERE\r\n" + 
			"        u.del_status = 0 AND u.user_type = t.user_type_id\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t2.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id AS user_id,\r\n" + 
			"        CONCAT(e.fr_emp_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t3.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id AS user_id,\r\n" + 
			"        CONCAT(c.cust_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t4.user_id\r\n" + 
			") tbl\r\n" + 
			"ORDER BY\r\n" + 
			"    order_id,\r\n" + 
			"    trail_id",nativeQuery=true)
	public List<GetOrderTrailDisplay> getOrderTrailDataByDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	
	
	@Query(value="SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t1.*,\r\n" + 
			"        CASE WHEN t1.ex_int1 = 1 THEN COALESCE(t2.user_name, '') ELSE CASE WHEN t1.ex_int1 = 4 THEN COALESCE(t3.user_name, '') ELSE COALESCE(t4.user_name, '')\r\n" + 
			"END\r\n" + 
			"END AS user_name,\r\n" + 
			"DATE_FORMAT(\r\n" + 
			"    t1.action_date_time,\r\n" + 
			"    '%d-%m-%Y %h:%i %p'\r\n" + 
			") AS trail_date\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t.*\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_trail t\r\n" + 
			"    WHERE\r\n" + 
			"        t.order_id IN(\r\n" + 
			"        SELECT\r\n" + 
			"            order_id\r\n" + 
			"        FROM\r\n" + 
			"            tn_order_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND order_status IN(:status) AND delivery_date BETWEEN :fromDate AND :toDate \r\n" + 
			"    )\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        u.user_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            u.user_name,\r\n" + 
			"            ' (',\r\n" + 
			"            t.user_type_name,\r\n" + 
			"            ') '\r\n" + 
			"        ) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_user u,\r\n" + 
			"        mn_user_type t\r\n" + 
			"    WHERE\r\n" + 
			"        u.del_status = 0 AND u.user_type = t.user_type_id\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t2.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id AS user_id,\r\n" + 
			"        CONCAT(e.fr_emp_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t3.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id AS user_id,\r\n" + 
			"        CONCAT(c.cust_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t4.user_id\r\n" + 
			") tbl\r\n" + 
			"ORDER BY\r\n" + 
			"    order_id,\r\n" + 
			"    trail_id",nativeQuery=true)
	public List<GetOrderTrailDisplay> getOrderTrailDataByDateAndStatus(@Param("fromDate") String fromDate, @Param("toDate") String toDate,@Param("status") List<Integer> status);
	
	
	@Query(value="SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t1.*,\r\n" + 
			"        CASE WHEN t1.ex_int1 = 1 THEN COALESCE(t2.user_name, '') ELSE CASE WHEN t1.ex_int1 = 4 THEN COALESCE(t3.user_name, '') ELSE COALESCE(t4.user_name, '')\r\n" + 
			"END\r\n" + 
			"END AS user_name,\r\n" + 
			"DATE_FORMAT(\r\n" + 
			"    t1.action_date_time,\r\n" + 
			"    '%d-%m-%Y %h:%i %p'\r\n" + 
			") AS trail_date\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t.*\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_trail t\r\n" + 
			"    WHERE\r\n" + 
			"        t.order_id IN(\r\n" + 
			"        SELECT\r\n" + 
			"            order_id\r\n" + 
			"        FROM\r\n" + 
			"            tn_order_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND order_status IN(:status) AND fr_id IN(:frIds) AND delivery_date BETWEEN :fromDate AND :toDate \r\n" + 
			"    )\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        u.user_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            u.user_name,\r\n" + 
			"            ' (',\r\n" + 
			"            t.user_type_name,\r\n" + 
			"            ') '\r\n" + 
			"        ) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_user u,\r\n" + 
			"        mn_user_type t\r\n" + 
			"    WHERE\r\n" + 
			"        u.del_status = 0 AND u.user_type = t.user_type_id\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t2.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id AS user_id,\r\n" + 
			"        CONCAT(e.fr_emp_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t3.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id AS user_id,\r\n" + 
			"        CONCAT(c.cust_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t4.user_id\r\n" + 
			") tbl\r\n" + 
			"ORDER BY\r\n" + 
			"    order_id,\r\n" + 
			"    trail_id",nativeQuery=true)
	public List<GetOrderTrailDisplay> getOrderTrailDataByDateAndStatusAndFr(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("status") List<Integer> status,@Param("frIds") List<Integer> frIds);
	
	

	@Query(value="SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t1.*,\r\n" + 
			"        CASE WHEN t1.ex_int1 = 1 THEN COALESCE(t2.user_name, '') ELSE CASE WHEN t1.ex_int1 = 4 THEN COALESCE(t3.user_name, '') ELSE COALESCE(t4.user_name, '')\r\n" + 
			"END\r\n" + 
			"END AS user_name,\r\n" + 
			"DATE_FORMAT(\r\n" + 
			"    t1.action_date_time,\r\n" + 
			"    '%d-%m-%Y %h:%i %p'\r\n" + 
			") AS trail_date\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t.*\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_trail t\r\n" + 
			"    WHERE\r\n" + 
			"        t.order_id IN(\r\n" + 
			"        SELECT\r\n" + 
			"            order_id\r\n" + 
			"        FROM\r\n" + 
			"            tn_order_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND order_status IN(:status) AND cust_id IN(:custIds) AND delivery_date BETWEEN :fromDate AND :toDate \r\n" + 
			"    )\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        u.user_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            u.user_name,\r\n" + 
			"            ' (',\r\n" + 
			"            t.user_type_name,\r\n" + 
			"            ') '\r\n" + 
			"        ) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_user u,\r\n" + 
			"        mn_user_type t\r\n" + 
			"    WHERE\r\n" + 
			"        u.del_status = 0 AND u.user_type = t.user_type_id\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t2.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id AS user_id,\r\n" + 
			"        CONCAT(e.fr_emp_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t3.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id AS user_id,\r\n" + 
			"        CONCAT(c.cust_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t4.user_id\r\n" + 
			") tbl\r\n" + 
			"ORDER BY\r\n" + 
			"    order_id,\r\n" + 
			"    trail_id",nativeQuery=true)
	public List<GetOrderTrailDisplay> getOrderTrailDataByDateAndStatusAndCust(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("status") List<Integer> status,@Param("custIds") List<Integer> custIds);
	
	
	@Query(value="SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t1.*,\r\n" + 
			"        CASE WHEN t1.ex_int1 = 1 THEN COALESCE(t2.user_name, '') ELSE CASE WHEN t1.ex_int1 = 4 THEN COALESCE(t3.user_name, '') ELSE COALESCE(t4.user_name, '')\r\n" + 
			"END\r\n" + 
			"END AS user_name,\r\n" + 
			"DATE_FORMAT(\r\n" + 
			"    t1.action_date_time,\r\n" + 
			"    '%d-%m-%Y %h:%i %p'\r\n" + 
			") AS trail_date\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t.*\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_trail t\r\n" + 
			"    WHERE\r\n" + 
			"        t.order_id IN(\r\n" + 
			"        SELECT\r\n" + 
			"            order_id\r\n" + 
			"        FROM\r\n" + 
			"            tn_order_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND order_status IN(:status) AND order_platform IN(:platform) AND delivery_date BETWEEN :fromDate AND :toDate \r\n" + 
			"    )\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        u.user_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            u.user_name,\r\n" + 
			"            ' (',\r\n" + 
			"            t.user_type_name,\r\n" + 
			"            ') '\r\n" + 
			"        ) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_user u,\r\n" + 
			"        mn_user_type t\r\n" + 
			"    WHERE\r\n" + 
			"        u.del_status = 0 AND u.user_type = t.user_type_id\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t2.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id AS user_id,\r\n" + 
			"        CONCAT(e.fr_emp_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t3.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id AS user_id,\r\n" + 
			"        CONCAT(c.cust_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t4.user_id\r\n" + 
			") tbl\r\n" + 
			"ORDER BY\r\n" + 
			"    order_id,\r\n" + 
			"    trail_id",nativeQuery=true)
	public List<GetOrderTrailDisplay> getOrderTrailDataByDateAndStatusAndOrderPlatform(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("status") List<Integer> status,@Param("platform") List<Integer> platform);
	
	
	@Query(value="SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t1.*,\r\n" + 
			"        CASE WHEN t1.ex_int1 = 1 THEN COALESCE(t2.user_name, '') ELSE CASE WHEN t1.ex_int1 = 4 THEN COALESCE(t3.user_name, '') ELSE COALESCE(t4.user_name, '')\r\n" + 
			"END\r\n" + 
			"END AS user_name,\r\n" + 
			"DATE_FORMAT(\r\n" + 
			"    t1.action_date_time,\r\n" + 
			"    '%d-%m-%Y %h:%i %p'\r\n" + 
			") AS trail_date\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t.*\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_trail t\r\n" + 
			"    WHERE\r\n" + 
			"        t.order_id IN(\r\n" + 
			"        SELECT\r\n" + 
			"            order_id\r\n" + 
			"        FROM\r\n" + 
			"            tn_order_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND order_status IN(:status) AND payment_method IN(:payment) AND delivery_date BETWEEN :fromDate AND :toDate \r\n" + 
			"    )\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        u.user_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            u.user_name,\r\n" + 
			"            ' (',\r\n" + 
			"            t.user_type_name,\r\n" + 
			"            ') '\r\n" + 
			"        ) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_user u,\r\n" + 
			"        mn_user_type t\r\n" + 
			"    WHERE\r\n" + 
			"        u.del_status = 0 AND u.user_type = t.user_type_id\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t2.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id AS user_id,\r\n" + 
			"        CONCAT(e.fr_emp_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t3.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id AS user_id,\r\n" + 
			"        CONCAT(c.cust_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t4.user_id\r\n" + 
			") tbl\r\n" + 
			"ORDER BY\r\n" + 
			"    order_id,\r\n" + 
			"    trail_id",nativeQuery=true)
	public List<GetOrderTrailDisplay> getOrderTrailDataByDateAndStatusAndPaymentMode(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("status") List<Integer> status,@Param("payment") List<Integer> payment);
	
	
	
	//Sachin =17-08-2020
	//Desc to get order trail by orderId
	@Query(value="SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t1.*,\r\n" + 
			"        CASE WHEN t1.ex_int1 = 1 THEN COALESCE(t2.user_name, '') ELSE CASE WHEN t1.ex_int1 = 4 THEN COALESCE(t3.user_name, '') ELSE COALESCE(t4.user_name, '')\r\n" + 
			"END\r\n" + 
			"END AS user_name,\r\n" + 
			"DATE_FORMAT(\r\n" + 
			"    t1.action_date_time,\r\n" + 
			"    '%d-%m-%Y %h:%i %p'\r\n" + 
			") AS trail_date\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        t.*\r\n" + 
			"    FROM\r\n" + 
			"        tn_order_trail t\r\n" + 
			"    WHERE\r\n" + 
			"        t.order_id IN(\r\n" + 
			"        SELECT\r\n" + 
			"            order_id\r\n" + 
			"        FROM\r\n" + 
			"            tn_order_header\r\n" + 
			"        WHERE\r\n" + 
			"            del_status = 0 AND tn_order_header.order_id = :orderId\r\n" + 
			"    )\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        u.user_id,\r\n" + 
			"        CONCAT(\r\n" + 
			"            u.user_name,\r\n" + 
			"            ' (',\r\n" + 
			"            t.user_type_name,\r\n" + 
			"            ') '\r\n" + 
			"        ) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        mn_user u,\r\n" + 
			"        mn_user_type t\r\n" + 
			"    WHERE\r\n" + 
			"        u.del_status = 0 AND u.user_type = t.user_type_id\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t2.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        e.fr_emp_id AS user_id,\r\n" + 
			"        CONCAT(e.fr_emp_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_fr_emp e\r\n" + 
			"    WHERE\r\n" + 
			"        e.del_status = 0\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t3.user_id\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT\r\n" + 
			"        c.cust_id AS user_id,\r\n" + 
			"        CONCAT(c.cust_name) AS user_name\r\n" + 
			"    FROM\r\n" + 
			"        m_customer c\r\n" + 
			"    WHERE\r\n" + 
			"        c.del_status = 0\r\n" + 
			") t4\r\n" + 
			"ON\r\n" + 
			"    t1.action_by_user_id = t4.user_id\r\n" + 
			") tbl\r\n" + 
			"ORDER BY\r\n" + 
			"    order_id,\r\n" + 
			"    trail_id",nativeQuery=true)
	public List<GetOrderTrailDisplay> getOrderTrailDataOrderId(@Param("orderId") int  orderId);
	
	
	@Query(value="SELECT\r\n" + 
			"					trl.trail_id,\r\n" + 
			"					trl.order_id,                	\r\n" + 
			"					trl.action_by_user_id,\r\n" + 
			"					trl.action_date_time,\r\n" + 
			"					trl.status,\r\n" + 
			"				    trl.ex_int1,\r\n" + 
			"				    trl.ex_int2,\r\n" + 
			"				    trl.ex_int3,\r\n" + 
			"				    trl.ex_var1,\r\n" + 
			"				    trl.ex_var2,\r\n" + 
			"				    trl.ex_var3,\r\n" + 
			"				    usr.user_name,\r\n" + 
			"				    DATE_FORMAT(action_date_time,'%d-%m-%Y %h:%i %p') AS trail_date\r\n" + 
			"				FROM\r\n" + 
			"					tn_order_trail trl,\r\n" + 
			"					m_user usr\r\n" + 
			"				WHERE \r\n" + 
			"					usr.del_status=1 \r\n" + 
			"					AND trl.action_by_user_id=usr.user_id\r\n" + 
			"					AND usr.company_id=:compId",nativeQuery=true)
	public List<GetOrderTrailDisplay> getOrderTrailListByCompId(@Param("compId") int compId);
	
}
