package com.ats.ecomapi.offer.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.offer_model.GetConfigureOfferList;

 
public interface GetConfigureOfferListRepo extends JpaRepository<GetConfigureOfferList, Integer> {

	@Query(value="SELECT\n" + 
			"    t1.fr_id,\n" + 
			"    t1.fr_name,\n" + 
			"    COALESCE(t2.checked, 0) AS checked\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        *\n" + 
			"    FROM\n" + 
			"        m_franchise f\n" + 
			"    WHERE\n" + 
			"        f.del_status =1 AND f.company_id=:compId\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        f.fr_id,\n" + 
			"        c.offer_config_id,\n" + 
			"        c.offer_id,\n" + 
			"        f.fr_name,\n" + 
			"        1 AS checked\n" + 
			"    FROM\n" + 
			"        mn_offer_config c\n" + 
			"    INNER JOIN m_franchise f ON\n" + 
			"        FIND_IN_SET(f.fr_id, c.fr_id) > 0 AND c.offer_id = :offerId AND f.del_status =1 AND c.del_status=1 \n" + 
			"    GROUP BY\n" + 
			"        f.fr_id\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.fr_id = t2.fr_id  \n" + 
			"ORDER BY `t1`.`fr_id` ASC",nativeQuery=true)
	List<GetConfigureOfferList> getConfigureOferList(@Param("offerId") int offerId, @Param("compId") int compId);
}
