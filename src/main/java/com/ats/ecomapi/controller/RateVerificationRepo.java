package com.ats.ecomapi.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface RateVerificationRepo extends JpaRepository<GetMrnQueryItem, Integer> {
	
	/*
	 * @Query(value = "CALL getAllVendorItem(:vendrId);", nativeQuery = true)
	 * List<RateVerificationList> getALLByVendId(@Param("vendrId") int vendrId);
	 */
	@Query(value = "CALL getMrnQueryItem(:fromDate,:itemId);", nativeQuery = true)
	List<GetMrnQueryItem> getMrnQueryItem1(@Param("fromDate") String fromDate,
			@Param("itemId") int itemId);

	@Procedure(value = "getMrnQueryItem")
	List<GetMrnQueryItem> getMrnQueryItem2(String fromDate, int itemId);

	}

/*
 DROP PROCEDURE `getAllVendorItem`;
CREATE DEFINER=`gfpl`@`localhost` PROCEDURE `getAllVendorItem`(IN `vendrId` INT(20)) COMMENT 'SA' NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER SELECT 
			    rv.rm_rate_ver_id,  
			   rv.rm_id, 
			   rv.supp_id, 
			     i.item_desc 
			 FROM 
			     m_rm_rate_verif rv, 
			     m_item i, 
			     m_vendor v  
			 WHERE 
			     rv.supp_id = vendrId AND i.item_id = rv.rm_id AND
			 v.vendor_id = rv.supp_id AND 
			 i.is_used = 1*/	
