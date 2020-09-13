package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.mst_model.UserTypeMaster;

public interface UserTypeRepo extends JpaRepository<UserTypeMaster, Integer> {

	List<UserTypeMaster> findByDelStatus(int del);
}
