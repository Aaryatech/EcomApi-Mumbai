package com.ats.ecomapi.offer.repo;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ecomapi.offer_model.OfferDetail;

 
@Repository
public interface OfferDetailRepo extends JpaRepository<OfferDetail, Integer> {
	
	List<OfferDetail> findAllByOfferIdAndIsActiveAndDelStatus(int id,int j,int k);
	
	@Transactional
	@Modifying
	@Query(value="DELETE from mn_offer_detail WHERE offer_detail_id IN(:offerDetailIds)",nativeQuery=true)
	public int deleteOfferDetails(@Param("offerDetailIds") List<Integer> offerDetailIds);


	@Query(value = "SELECT\r\n" + 
			"    d.*\r\n" + 
			"FROM\r\n" + 
			"    mn_offer_detail d\r\n" + 
			"WHERE\r\n" + 
			"    d.del_status = 1 AND d.offer_id IN(\r\n" + 
			"    SELECT\r\n" + 
			"        offer_id\r\n" + 
			"    FROM\r\n" + 
			"        mn_offer_config\r\n" + 
			"    WHERE\r\n" + 
			"        FIND_IN_SET(:frId, fr_id) AND del_status = 1\r\n" + 
			")", nativeQuery = true)
	List<OfferDetail> getOfferDetailByFr(@Param("frId") int frId);

}
