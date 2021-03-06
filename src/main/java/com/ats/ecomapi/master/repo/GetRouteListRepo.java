package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetRouteList;
import com.ats.ecomapi.master.model.SubCategory;

public interface GetRouteListRepo  extends JpaRepository<GetRouteList, Integer>{
	
	
	@Query(value="SELECT\n" + 
			"    m_route.route_id,\n" + 
			"    m_route.route_name,\n" + 
			"    m_route.route_km,\n" + 
			"    m_route.sort_no,\n" + 
			"    m_route.route_code,\n" + 
			"    m_route_type.route_type_name,\n" + 
			"    m_route_delivery.delivery_name,\n" + 
			"   COALESCE((SELECT GROUP_CONCAT(m_franchise.fr_name) FROM m_franchise WHERE find_in_set(m_franchise.fr_id,\n" + 
			"                                                                                             m_route.fr_ids)),0) as fr_name, '0' as ex_var1\n" + 
			"FROM\n" + 
			"    " + 
			"    m_route,\n" + 
			"    m_route_type,\n" + 
			"    m_route_delivery\n" + 
			"WHERE\n" + 
			"   m_route_type.route_type_id=m_route.type_of_route AND m_route.is_delivery_no=m_route_delivery.rouid_delvery_id AND m_route.del_status=1 AND m_route.company_id=:compId ORDER BY m_route.route_id DESC",nativeQuery=true)
	List<GetRouteList> getAllRoutes(@Param("compId") int compId);


}
