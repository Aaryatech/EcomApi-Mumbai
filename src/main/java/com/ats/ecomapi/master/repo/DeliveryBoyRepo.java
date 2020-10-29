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
}
