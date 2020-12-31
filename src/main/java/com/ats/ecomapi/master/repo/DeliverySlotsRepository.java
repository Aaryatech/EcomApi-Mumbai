package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.DeliverySlots;

public interface DeliverySlotsRepository extends JpaRepository<DeliverySlots, Integer>{
	
	@Query(value="SELECT * FROM  m_delivery_slots WHERE del_status=1",nativeQuery=true)
	List<DeliverySlots> getAllDeliverySlots();
	
	@Query(value="SELECT * FROM  m_delivery_slots WHERE del_status=1 AND deli_slot_id=:delSlotId",nativeQuery=true)
	DeliverySlots getDeliverySlotById(@Param("delSlotId") int delSlotId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_delivery_slots SET del_status=0 WHERE deli_slot_id=:delSlotId",nativeQuery=true)
	int DelteDeliSlots(@Param("delSlotId") int delSlotId);

}
