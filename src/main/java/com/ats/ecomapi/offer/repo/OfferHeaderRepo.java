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

	
	@Query(value = "SELECT\r\n" + 
			"    *\r\n" + 
			"FROM\r\n" + 
			"    mn_offer_header h\r\n" + 
			"WHERE\r\n" + 
			"    FIND_IN_SET(:applicableFor, h.applicable_for) AND h.type = :type AND h.offer_type = :offerType AND h.offer_id IN(\r\n" + 
			"    SELECT\r\n" + 
			"        offer_id\r\n" + 
			"    FROM\r\n" + 
			"        mn_offer_config\r\n" + 
			"    WHERE\r\n" + 
			"        FIND_IN_SET(:frId, fr_id) AND del_status = 1\r\n" + 
			") AND IF( h.frequency_type = 1, IF( FIND_IN_SET( DAYOFWEEK(CURDATE()), h.frequency ) , 1 , 0 ), IF(h.frequency_type = 2, IF( CURDATE() BETWEEN h.from_date AND h.to_date, 1, 0), 0 ) ) = 1 AND CURTIME() BETWEEN h.from_time AND h.to_time\r\n" + 
			" = 1", nativeQuery = true)
	List<OfferHeader> getBillOfferHeaderByFr(@Param("frId") int frId, @Param("type") int type, @Param("applicableFor") int applicableFor,@Param("offerType") int offerType);

	@Query(value = "SELECT COALESCE((CASE WHEN o.ex_int1=0 THEN  "
			+ "CASE WHEN (SELECT COUNT(*) FROM tn_order_header h WHERE h.offer_id = :offerId AND h.ex_var2 LIKE :coupon AND h.order_status NOT IN(6,7)) >= d.ex_int1 THEN 0 ELSE 1 END "
			+ "ELSE CASE WHEN (SELECT COUNT(*) FROM tn_order_header h WHERE h.offer_id = :offerId AND h.ex_var2 LIKE :coupon AND h.order_status NOT IN(6,7) AND h.cust_id= :custId) >= d.ex_int1 THEN 0 ELSE 1 END "
			+ "END),0) AS is_valid FROM mn_offer_header o,mn_offer_detail d WHERE o.offer_id= :offerId AND o.offer_id=d.offer_id", nativeQuery = true)
	public Integer checkIsValidOffer(@Param("offerId") int offerId, @Param("coupon") String coupon, @Param("custId") int custId);

	//COUPON WISE OFFERS
		@Query(value = "SELECT\r\n" + 
				"    *\r\n" + 
				"FROM\r\n" + 
				"    mn_offer_header h\r\n" + 
				"WHERE\r\n" + 
				"    FIND_IN_SET(:applicableFor, h.applicable_for) AND h.type = :type AND h.ex_int1=0 AND h.del_status=1 AND h.offer_type = :offerType AND h.offer_id IN(\r\n" + 
				"    SELECT\r\n" + 
				"        offer_id\r\n" + 
				"    FROM\r\n" + 
				"        mn_offer_config\r\n" + 
				"    WHERE\r\n" + 
				"        FIND_IN_SET(:frId, fr_id) AND del_status = 1\r\n" + 
				") AND IF( h.frequency_type = 1, IF( FIND_IN_SET( DAYOFWEEK(CURDATE()), h.frequency ) , 1 , 0 ), IF(h.frequency_type = 2, IF( CURDATE() BETWEEN h.from_date AND h.to_date, 1, 0), 0 ) ) = 1 AND CURTIME() BETWEEN h.from_time AND h.to_time\r\n" + 
				" = 1", nativeQuery = true)
		List<OfferHeader> getBillOfferHeaderByFrCouponWise(@Param("frId") int frId, @Param("type") int type, @Param("applicableFor") int applicableFor,@Param("offerType") int offerType);

		//CUSTOMER WISE OFFERS
		@Query(value = "SELECT\r\n" + 
				"    *\r\n" + 
				"FROM\r\n" + 
				"    mn_offer_header h,mn_offer_detail d\r\n" + 
				"WHERE\r\n" + 
				"    FIND_IN_SET(\r\n" + 
				"        :applicableFor,\r\n" + 
				"        h.applicable_for\r\n" + 
				"    ) AND h.type = :type AND h.ex_int1=1 AND h.offer_type = :offerType AND h.offer_id IN(\r\n" + 
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
				"        IF(\r\n" + 
				"            h.frequency_type = 2,\r\n" + 
				"            IF(\r\n" + 
				"                CURDATE() BETWEEN h.from_date AND h.to_date, 1, 0),\r\n" + 
				"                0\r\n" + 
				"            )\r\n" + 
				"        ) = 1 AND CURTIME() BETWEEN h.from_time AND h.to_time = 1\r\n" + 
				"AND h.offer_id=d.offer_id AND d.ex_int1>(SELECT COUNT(*) FROM tn_order_header oh WHERE oh.cust_id=:custId AND oh.order_status NOT IN(6,7) AND oh.offer_id=h.offer_id)", nativeQuery = true)
		List<OfferHeader> getBillOfferHeaderByFrCustomerWise(@Param("frId") int frId, @Param("type") int type, @Param("applicableFor") int applicableFor,@Param("offerType") int offerType,@Param("custId") int custId);

		
}
