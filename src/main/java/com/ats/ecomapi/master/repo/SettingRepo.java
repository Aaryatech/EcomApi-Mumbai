package com.ats.ecomapi.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.master.model.Setting;

public interface SettingRepo extends JpaRepository<Setting, Integer> {

	Setting findBySettingKey(String dbName);

}
