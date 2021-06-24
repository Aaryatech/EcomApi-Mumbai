package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.GetFrConfigList;

public interface GetFrConfigListRepo extends JpaRepository<GetFrConfigList, Integer> {
	
	
	
	//All fr 

	@Query(value="\n" + 
			"SELECT\n" + 
			"    m_fr_configration.frachase_config_id,\n" + 
			"    m_fr_configration.config_header_id,\n" + 
			"    m_fr_configration.fr_id,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    mn_city.city_name as fr_city,\n" + 
			"     m_route.route_name AS route,\n" + 
			"    tn_item_config_header.config_name\n" + 
			"FROM\n" + 
			"    tn_item_config_header,\n" + 
			"    m_fr_configration,\n" + 
			"    m_franchise,mn_city,m_route \n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city  AND m_fr_configration.config_header_id IN(:configIds) AND find_in_set(m_franchise.fr_id,m_route.fr_ids) AND tn_item_config_header.company_id=:compId ORDER BY m_fr_configration.config_header_id  DESC",nativeQuery=true)
	public List<GetFrConfigList> getAllFranchiseToFrAllFrOrCon(@Param("configIds") List<String> configIds, @Param("compId") int compId);
	
	
	
	@Query(value="\n" + 
			"SELECT\n" + 
			"    m_fr_configration.frachase_config_id,\n" + 
			"    m_fr_configration.config_header_id,\n" + 
			"    m_fr_configration.fr_id,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    mn_city.city_name as fr_city,\n" + 
			"    m_route.route_name AS route,\n" + 
			"    tn_item_config_header.config_name\n" + 
			"FROM\n" + 
			"    tn_item_config_header,\n" + 
			"    m_fr_configration,\n" + 
			"    m_franchise,mn_city,m_route \n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city  AND m_fr_configration.config_header_id IN(:configIds)  AND find_in_set(m_franchise.fr_id,m_route.fr_ids) AND tn_item_config_header.company_id=:compId ORDER BY m_fr_configration.fr_id  DESC ",nativeQuery=true)
	public List<GetFrConfigList> getAllFranchiseToFrAllFrOrFr(@Param("configIds") List<String> configIds, @Param("compId") int compId);
	
	
//All config
	@Query(value="\n" + 
			"SELECT\n" + 
			"    m_fr_configration.frachase_config_id,\n" + 
			"    m_fr_configration.config_header_id,\n" + 
			"    m_fr_configration.fr_id,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    mn_city.city_name as fr_city,\n" + 
			"     m_route.route_name AS route,\n" + 
			"    tn_item_config_header.config_name\n" + 
			"FROM\n" + 
			"    tn_item_config_header,\n" + 
			"    m_fr_configration,\n" + 
			"    m_franchise,mn_city,m_route \n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city  AND m_fr_configration.fr_id IN(:frIds) AND find_in_set(m_franchise.fr_id,m_route.fr_ids) AND tn_item_config_header.company_id=:compId ORDER BY m_fr_configration.config_header_id  DESC",nativeQuery=true)
	public List<GetFrConfigList> getAllFranchiseToConfigAllConfigOrCon(@Param("frIds") List<String> frIds, @Param("compId") int compId);
	
	
	
	@Query(value="\n" + 
			"SELECT\n" + 
			"    m_fr_configration.frachase_config_id,\n" + 
			"    m_fr_configration.config_header_id,\n" + 
			"    m_fr_configration.fr_id,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    mn_city.city_name as fr_city,\n" + 
			"    m_route.route_name AS route,\n" + 
			"    tn_item_config_header.config_name\n" + 
			"FROM\n" + 
			"    tn_item_config_header,\n" + 
			"    m_fr_configration,\n" + 
			"    m_franchise,mn_city,m_route \n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city  AND m_fr_configration.fr_id IN(:frIds) AND find_in_set(m_franchise.fr_id,m_route.fr_ids) AND tn_item_config_header.company_id=:compId ORDER BY m_fr_configration.fr_id  DESC ",nativeQuery=true)
	public List<GetFrConfigList> getAllFranchiseToConfigAllConfigOrFr(@Param("frIds") List<String> frIds, @Param("compId") int compId);
	
	//both All
	
	
	
	
	@Query(value="\n" + 
			"SELECT\n" + 
			"    m_fr_configration.frachase_config_id,\n" + 
			"    m_fr_configration.config_header_id,\n" + 
			"    m_fr_configration.fr_id,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    mn_city.city_name as fr_city,\n" + 
			"    m_route.route_name AS route,\n" + 
			"    tn_item_config_header.config_name\n" + 
			"FROM\n" + 
			"    tn_item_config_header,\n" + 
			"    m_fr_configration,\n" + 
			"    m_franchise,mn_city,m_route \n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city AND find_in_set(m_franchise.fr_id,m_route.fr_ids) AND tn_item_config_header.company_id=:compId ORDER BY m_fr_configration.config_header_id  DESC",nativeQuery=true)
	public List<GetFrConfigList> getAllFranchiseToConfigAllOrCon(@Param("compId") int compId);
	
	
	
	@Query(value="\n" + 
			"SELECT\n" + 
			"    m_fr_configration.frachase_config_id,\n" + 
			"    m_fr_configration.config_header_id,\n" + 
			"    m_fr_configration.fr_id,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    mn_city.city_name as fr_city,\n" + 
			"    m_route.route_name AS route,\n" + 
			"    tn_item_config_header.config_name\n" + 
			"FROM\n" + 
			"    tn_item_config_header,\n" + 
			"    m_fr_configration,\n" + 
			"    m_franchise,mn_city,m_route \n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city AND find_in_set(m_franchise.fr_id,m_route.fr_ids)  AND tn_item_config_header.company_id=:compId ORDER BY m_fr_configration.fr_id  DESC ",nativeQuery=true)
	public List<GetFrConfigList> getAllFranchiseToConfigAllOrFr(@Param("compId") int compId);


//both spec

	@Query(value="\n" + 
			"SELECT\n" + 
			"    m_fr_configration.frachase_config_id,\n" + 
			"    m_fr_configration.config_header_id,\n" + 
			"    m_fr_configration.fr_id,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    mn_city.city_name as fr_city,\n" + 
			"     m_route.route_name AS route,\n" + 
			"    tn_item_config_header.config_name\n" + 
			"FROM\n" + 
			"    tn_item_config_header,\n" + 
			"    m_fr_configration,\n" + 
			"    m_franchise,mn_city,m_route \n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city  AND m_fr_configration.fr_id IN(:frIds) AND  m_fr_configration.config_header_id IN(:configIds)  AND find_in_set(m_franchise.fr_id,m_route.fr_ids) AND tn_item_config_header.company_id=:compId ORDER BY m_fr_configration.config_header_id  DESC",nativeQuery=true)
	public List<GetFrConfigList> getAllFranchiseToConfigAllConfigOrCon(@Param("frIds") List<String> frIds,@Param("configIds") List<String> configIds, @Param("compId") int compId);
	
	
	
	@Query(value="\n" + 
			"SELECT\n" + 
			"    m_fr_configration.frachase_config_id,\n" + 
			"    m_fr_configration.config_header_id,\n" + 
			"    m_fr_configration.fr_id,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    mn_city.city_name as fr_city,\n" + 
			"    m_route.route_name AS route,\n" + 
			"    tn_item_config_header.config_name\n" + 
			"FROM\n" + 
			"    tn_item_config_header,\n" + 
			"    m_fr_configration,\n" + 
			"    m_franchise,mn_city,m_route \n" + 
			"WHERE\n" + 
			"    m_franchise.fr_id = m_fr_configration.fr_id AND m_fr_configration.config_header_id = tn_item_config_header.config_header_id AND mn_city.city_id=m_franchise.fr_city  AND m_fr_configration.fr_id IN(:frIds) and  m_fr_configration.config_header_id IN(:configIds)  AND find_in_set(m_franchise.fr_id,m_route.fr_ids) AND tn_item_config_header.company_id=:compId ORDER BY m_fr_configration.fr_id  DESC ",nativeQuery=true)
	public List<GetFrConfigList> getAllFranchiseToConfigAllConfigOrFr(@Param("frIds") List<String> frIds,@Param("configIds") List<String> configIds,@Param("compId") int compId);




}
