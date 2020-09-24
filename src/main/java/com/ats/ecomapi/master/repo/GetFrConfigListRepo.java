package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.mst_model.GetFrConfigList;

public interface GetFrConfigListRepo extends JpaRepository<GetFrConfigList, Integer> {
	
	
	
	
	/*
	 * @Query(value="\n" + "SELECT\n" +
	 * "    m_fr_configration.frachase_config_id,\n" +
	 * "    m_fr_configration.config_header_id,\n" +
	 * "    m_fr_configration.fr_id,\n" + "    m_franchise.fr_code,\n" +
	 * "    m_franchise.fr_name,\n" + "    mn_city.city_name as fr_city,\n" +
	 * "    0 AS route,\n" + "    tn_item_config_header.config_name\n" + "FROM\n" +
	 * "    tn_item_config_header,\n" + "    m_fr_configration,\n" +
	 * "    m_franchise,mn_city\n" + "WHERE\n" +
	 * "    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city  AND m_fr_configration.frachase_config_id IN(:configIds)  ORDER BY m_fr_configration."
	 * +orderBy+"",nativeQuery=true) public List<GetFrConfigList>
	 * getAllFranchiseToConfig(@Param("configIds") List<String> configIds
	 * , @Param("orderBy") String orderBy);
	 */
	

}
