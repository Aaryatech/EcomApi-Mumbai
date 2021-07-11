package com.ats.ecomapi.DeliveryBoy_Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.deliveryboy_model.Grievances;

public interface GrievencesRepo extends JpaRepository<Grievances, Integer>{
	Grievances save(Grievances gre);
	
	@Query(value="SELECT *  FROM tn_grievences WHERE  tn_grievences.d_date=:orderId",nativeQuery=true)
	List<Grievances> getGrevienceByOrderId(@Param("orderId") String orderId );

	@Query(value="SELECT * FROM `tn_grievences`",nativeQuery=true)
	List<Grievances> getAllGreviences();
}
