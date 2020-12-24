package com.ats.ecomapi.offer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.offer_model.CkDeliveryCharges;


public interface CkDeliveryChargesRepo extends JpaRepository<CkDeliveryCharges, Integer> {

	@Query(value = "SELECT\r\n" + 
			"	uuid() AS id,\r\n" + 
			"    t1.amt1,\r\n" + 
			"    t1.amt2,\r\n" + 
			"    t1.min_km,\r\n" + 
			"    t1.max_km,\r\n" + 
			"    t2.min_amt,t3.free_delv_limit\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"    SELECT\r\n" + 
			"        d.*,\r\n" + 
			"        1 AS flag\r\n" + 
			"    FROM\r\n" + 
			"        mn_delivery_charges d\r\n" + 
			"    WHERE\r\n" + 
			"        d.del_status = 1 AND :km >= d.min_km AND :km < d.max_km\r\n" + 
			") t1\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT 1 AS flag,\r\n" + 
			"        COALESCE(s.setting_value1, 0) AS min_amt\r\n" + 
			"    FROM\r\n" + 
			"        t_setting_new s\r\n" + 
			"    WHERE\r\n" + 
			"        s.del_status = 1 AND s.setting_key = 'ck_min_order_amt'\r\n" + 
			") t2\r\n" + 
			"ON\r\n" + 
			"    t1.flag = t2.flag\r\n" + 
			"LEFT JOIN(\r\n" + 
			"    SELECT 1 AS flag,\r\n" + 
			"        COALESCE(s.setting_value1, 0) AS free_delv_limit\r\n" + 
			"    FROM\r\n" + 
			"        t_setting_new s\r\n" + 
			"    WHERE\r\n" + 
			"        s.del_status = 1 AND s.setting_key = 'ck_free_delivery_min_order_amt'\r\n" + 
			") t3\r\n" + 
			"ON\r\n" + 
			"    t1.flag = t3.flag", nativeQuery = true)
	public CkDeliveryCharges getDeliveryCharges(@Param("km") float km);

}
