package com.ats.ecomapi.offer.repo;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ecomapi.offer_model.OfferHeader;

 
@Repository
public interface OfferHeaderRepo extends JpaRepository<OfferHeader, Integer> {

	OfferHeader findByOfferId(int i);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE mn_offer_header SET offer_type=:type WHERE offer_id =:offerId",nativeQuery=true)
	public int updateOfferType(@Param("offerId") int offerId,@Param("type") int type);
	
	List<OfferHeader> findByCompIdAndDelStatusAndIsActive(int i,int j,int k);
	
	List<OfferHeader> findByCompIdAndDelStatusOrderByOfferIdDesc(int compId, int del);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE mn_offer_header SET del_status=:status WHERE offer_id =:offerId",nativeQuery=true)
	public int deleteOfferHeader(@Param("offerId") int offerId,@Param("status") int status);
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE mn_offer_header SET offer_images = TRIM(BOTH ',' FROM REPLACE(REPLACE(offer_images,:imageName, ''), ',,', ',')) WHERE offer_id=:offerId",nativeQuery=true)
	public int removeImage(@Param("imageName") String imageName,@Param("offerId") int offerId);
	
	
	@Query(value = "SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    mn_offer_header h\r\n" + 
			"WHERE\r\n" + 
			"    h.offer_id IN(\r\n" + 
			"    SELECT\r\n" + 
			"        offer_id\r\n" + 
			"    FROM\r\n" + 
			"        mn_offer_config\r\n" + 
			"    WHERE\r\n" + 
			"        FIND_IN_SET(:frId, fr_id) AND del_status = 1\r\n" + 
			") AND IF(\r\n" + 
			"    h.frequency_type = 1,\r\n" + 
			"    IF(\r\n" + 
			"        FIND_IN_SET(\r\n" + 
			"            DAYOFWEEK(CURDATE()),\r\n" + 
			"            h.frequency),\r\n" + 
			"            1,\r\n" + 
			"            0\r\n" + 
			"        ),\r\n" + 
			"        0\r\n" + 
			"    ) = 1 OR IF(\r\n" + 
			"        h.frequency_type = 2,\r\n" + 
			"        IF(\r\n" + 
			"            CURDATE() BETWEEN h.from_date AND h.to_date, 1, 0),\r\n" + 
			"            0\r\n" + 
			"        ) = 1 AND CURTIME() BETWEEN h.from_time AND h.to_time AND FIND_IN_SET(:applicableFor, h.applicable_for) AND h.type = :type", nativeQuery = true)
	List<OfferHeader> getOfferHeaderByFr(@Param("frId") int frId, @Param("type") int type, @Param("applicableFor") int applicableFor);

	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE mn_offer_header SET offer_images=:filesList WHERE offer_id =:offerId",nativeQuery=true)
	public int updateImage(@Param("filesList") String filesList,@Param("offerId") int offerId);

}
