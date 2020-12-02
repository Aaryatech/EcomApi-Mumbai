package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.GetDeliveryBoyOrAgentData;


public interface GetDeliveryBoyOrAgentDataRepo  extends JpaRepository<GetDeliveryBoyOrAgentData, Integer> {

	@Query(value="SELECT\r\n" + 
			"    UUID() as u_id,\r\n" + 
			"    del_boy.del_boy_id,\r\n" + 
			"    CONCAT (del_boy.first_name,' ', del_boy.last_name) as name,\r\n" + 
			"    del_boy.mobile_no,\r\n" + 
			"    0 AS fr_id,\r\n" + 
			"    'NA' AS city_ids,\r\n" + 
			"    0 AS is_agent\r\n" + 
			"FROM\r\n" + 
			"    t_fr_delivery_boy_assign assign,\r\n" + 
			"   	m_delivery_boy del_boy\r\n" + 
			"WHERE\r\n" + 
			"   	FIND_IN_SET(:frId, assign.fr_ids) AND\r\n" + 
			"    assign.del_status=1 AND\r\n" + 
			"    assign.del_boy_id=del_boy.del_boy_id",nativeQuery=true)
	List<GetDeliveryBoyOrAgentData> getDeliveryBoyListByFr(@Param("frId") int frId);

}
