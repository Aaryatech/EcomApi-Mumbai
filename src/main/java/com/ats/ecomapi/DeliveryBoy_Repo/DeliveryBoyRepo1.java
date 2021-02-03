package com.ats.ecomapi.DeliveryBoy_Repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.DBoyLoginResponse;
import com.ats.ecomapi.fe_model.DeliveryBoy;


public interface DeliveryBoyRepo1 extends JpaRepository<DBoyLoginResponse,Integer>{

	
	@Query(value=" SELECT del_boy_id,first_name,last_name,mobile_no,date_of_birth,joining_date,address,emp_code,comp_id,email_id,vehicle_no FROM m_delivery_boy WHERE mobile_no=:mobile_no ",nativeQuery=true)
	DBoyLoginResponse toCheckMobileNo(@Param("mobile_no") String mobile_no);


	
	
	
}
