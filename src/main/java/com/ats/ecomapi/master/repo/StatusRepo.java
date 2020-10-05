package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.master.model.Status;


public interface StatusRepo extends JpaRepository<Status, Integer> {

	List<Status> findByDelStatusOrderByStatusIdAsc(int del);
}
