package com.ats.ecomapi.DeliveryBoy_Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.deliveryboy_model.DBoyLoginResponse;
import com.ats.ecomapi.fe_model.DeliveryBoy;

public interface DeliveryBoyProfileUpdate extends JpaRepository<DeliveryBoy, Integer>{
//	@Query(value="update m_delivery_boy\n" + 
//			"set last_name=\"DDD\" \n" + 
//			"where del_boy_id=1",nativeQuery=true)
//	DeliveryBoy	boyUpdate(@Param("Dboy")DeliveryBoy dboy);
	DeliveryBoy save(DeliveryBoy Dboy);
}
 