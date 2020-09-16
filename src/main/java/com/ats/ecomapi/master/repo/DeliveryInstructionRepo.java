package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.DeliveryInstruction;


public interface DeliveryInstructionRepo extends JpaRepository<DeliveryInstruction, Integer> {

	List<DeliveryInstruction> findByDelStatusAndCompanyIdOrderByInstruIdDesc(int del, int compId);
	
	DeliveryInstruction findByInstructnCaptionIgnoreCaseAndCompanyId(String caption, int compId);
	
	DeliveryInstruction findByInstructnCaptionIgnoreCaseAndCompanyIdAndInstruIdNot(String caption, int compId, int instructId);
	
	DeliveryInstruction findByInstruIdAndDelStatus(int instructId, int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE mn_delivery_instruction SET del_status=0 WHERE instru_id=:instructId",nativeQuery=true)
	public int deleteDelveryInstructnById(@Param("instructId") int instructId);
}
