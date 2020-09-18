package com.ats.ecomapi.offer.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.ecomapi.offer_model.GetOfferFrConfiguredList;

 
public interface GetOfferFrConfiguredListRepo extends JpaRepository<GetOfferFrConfiguredList, Integer> {

	@Query(value="SELECT\n" + 
			"               \n" + 
			"                c.offer_config_id,\n" + 
			"                c.offer_id,\n" + 
			"                GROUP_CONCAT(f.fr_name) fr_name,\n" + 
			"                (SELECT offer_name FROM mn_offer_header WHERE offer_id=c.offer_id) AS offer_name, c.ex_var1,\n" + 
			"                c.ex_var2\n" + 
			"            FROM\n" + 
			"                mn_offer_config c                \n" + 
			"            INNER JOIN\n" + 
			"                m_franchise f \n" + 
			"                    ON         FIND_IN_SET(f.fr_id,\n" + 
			"                c.fr_id) > 0 \n" + 
			"                AND f.del_status = 1 AND c.del_status=1\n" + 
			"                GROUP BY c.offer_id",nativeQuery=true)
	List<GetOfferFrConfiguredList> getConfiguredOfferFrList();
}
