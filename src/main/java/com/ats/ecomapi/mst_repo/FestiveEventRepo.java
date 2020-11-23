package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.FestiveEvent;

public interface FestiveEventRepo extends JpaRepository<FestiveEvent, Integer> {

	List<FestiveEvent> findByCompIdAndDelStatusOrderByEventIdDesc(int compId, int del);

	FestiveEvent findByEventId(int eventId);

	@Transactional
	@Modifying
	@Query(value = "UPDATE `m_festive_events` SET del_status=0 WHERE event_id=:eventId",nativeQuery=true)
	int deleteFestiveEventById(@Param("eventId") int eventId);
		
}
