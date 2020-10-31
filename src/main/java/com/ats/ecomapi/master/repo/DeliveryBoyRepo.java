package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.DeliveryBoy;
import java.lang.String;

public interface DeliveryBoyRepo extends JpaRepository<DeliveryBoy, Integer> {

	DeliveryBoy findByDelBoyId(int delBoyId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE `m_delivery_boy` SET del_status=0 WHERE del_boy_id=:delBoyId", nativeQuery = true)
	int deleteDelveryBoyById(@Param("delBoyId") int delBoyId);

	List<DeliveryBoy> findByCompIdAndDelStatusOrderByDelBoyIdDesc(int compId, int del);

	DeliveryBoy findByMobileNoAndDelStatus(String mobNo, int Del);

	DeliveryBoy findByMobileNoAndDelStatusAndDelBoyIdNot(String mobNo, int Del, int delBoyId);
	
	@Query(value="SELECT\n" + 
			"    t1.del_boy_id,\n" + 
			"    t1.address,\n" + 
			"    t1.comp_id,\n" + 
			"    t1.date_of_birth,\n" + 
			"    t1.del_status,\n" + 
			"    t1.email_id,\n" + 
			"    t1.emp_code,\n" + 
			"    t1.ex_int1,\n" + 
			"    t1.ex_var1,\n" + 
			"    t1.ex_var2,\n" + 
			"    t1.first_name,\n" + 
			"    t1.is_active,\n" + 
			"    t1.joining_date,\n" + 
			"    t1.last_name,\n" + 
			"    t1.mobile_no,\n" + 
			"    t1.vehicle_no,\n" + 
			"    COALESCE(t2.fr_count,0) AS ex_int2\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        del_boy_id,\n" + 
			"        address,\n" + 
			"        comp_id,\n" + 
			"        date_of_birth,\n" + 
			"        del_status,\n" + 
			"        email_id,\n" + 
			"        emp_code,\n" + 
			"        ex_int1,\n" + 
			"        ex_int2,\n" + 
			"        ex_var1,\n" + 
			"        ex_var2,\n" + 
			"        first_name,\n" + 
			"        is_active,\n" + 
			"        joining_date,\n" + 
			"        last_name,\n" + 
			"        mobile_no,\n" + 
			"        vehicle_no\n" + 
			"    FROM\n" + 
			"        m_delivery_boy\n" + 
			"    WHERE\n" + 
			"        comp_id = :compId AND del_status = 1\n" + 
			"    ORDER BY\n" + 
			"        del_boy_id\n" + 
			"    DESC\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        del_boy_id,\n" + 
			"       \n" + 
			"            (\n" + 
			"                CHAR_LENGTH(fr_ids) - CHAR_LENGTH(\n" + 
			"            REPLACE\n" + 
			"                (fr_ids, ',', '')\n" + 
			"            ) + 1\n" + 
			"            ) AS fr_count\n" + 
			"    FROM\n" + 
			"        `t_fr_delivery_boy_assign`\n" + 
			"    WHERE\n" + 
			"        company_id = :compId AND del_status = 1\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.del_boy_id = t2.del_boy_id", nativeQuery=true)
			List<DeliveryBoy> getDelvrBoyAndFrCount(@Param("compId") int compId);
}
