package com.ats.ecomapi.DeliveryBoy_Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.fe_model.Grievances;

public interface GrievencesRepo extends JpaRepository<Grievances, Integer>{
	Grievances save(Grievances gre);
}
