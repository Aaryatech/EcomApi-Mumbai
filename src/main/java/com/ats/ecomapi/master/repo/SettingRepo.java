package com.ats.ecomapi.master.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Setting;

public interface SettingRepo extends JpaRepository<Setting, Integer> {

	Setting findBySettingKey(String dbName);

	@Transactional
	@Modifying
	@Query(" UPDATE Setting SET setting_value=:value WHERE setting_key=:key")
	int udateKeyAndValue(@Param("key") String key, @Param("value") int value);

}
