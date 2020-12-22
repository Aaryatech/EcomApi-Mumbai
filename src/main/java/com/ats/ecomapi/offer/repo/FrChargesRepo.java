package com.ats.ecomapi.offer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.offer_model.FrCharges;


public interface FrChargesRepo extends JpaRepository<FrCharges, Integer> {

	FrCharges findByfrId(int frId);
}
