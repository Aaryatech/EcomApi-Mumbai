package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.mst_model.GetFrForConfig;

public interface GetFrForConfigRepo extends JpaRepository<GetFrForConfig, Integer> {
	
	
	
	@Query(value="SELECT\n" + 
			"        m_franchise.fr_id,\n" + 
			"        m_franchise.fr_code,\n" + 
			"        m_franchise.fr_name,\n" + 
			"        mn_city.city_name as fr_city,\n" + 
			"        m_route.route_name as route     \n" + 
			"    FROM\n" + 
			"        m_franchise ,\n" + 
			"        mn_city ,m_route    \n" + 
			"    WHERE\n" + 
			"        m_franchise.fr_id NOT IN(\n" + 
			"            SELECT\n" + 
			"                DISTINCT         m_fr_configration.fr_id                  \n" + 
			"            FROM\n" + 
			"                m_fr_configration,\n" + 
			"                m_franchise,\n" + 
			"                tn_item_config_header                  \n" + 
			"            WHERE\n" + 
			"                m_fr_configration.config_header_id = tn_item_config_header.config_header_id                  \n" + 
			"                AND tn_item_config_header.cat_id =1                 \n" + 
			"                AND m_franchise.fr_id = m_franchise.fr_id          \n" + 
			"        )          \n" + 
			"        AND m_franchise.company_id =:compId         \n" + 
			"        AND m_franchise.del_status =:catId \n" + 
			"        AND m_franchise.fr_city=mn_city.city_id AND  find_in_set(m_franchise.fr_id,m_route.fr_ids)",nativeQuery=true)
	public List<GetFrForConfig> getFranchiseToConfig(@Param("compId") int compId, @Param("catId") int catId);

}
