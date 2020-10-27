package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ecomapi.mst_model.GetGrievanceHeader;

@Repository
public interface GetGrievanceRepo extends JpaRepository<GetGrievanceHeader, Integer> {

	@Query(value="SELECT\n" + 
			"    t_grievance.grievance_id,\n" + 
			"    t_grievance.order_id,\n" + 
			"    t_grievance.grievance_type_id,\n" + 
			"    t_grievance.grievance_subtype_id,\n" + 
			"    t_grievance.remark,\n" + 
			"    t_grievance.current_status,\n" + 
			"    t_grievance.date,\n" + 
			"    t_grievance.ex_var1,\n" + 
			"    t_grievance.ex_var2,\n" + 
			"    t_grievance.grievance_no,\n" + 
			"    t_grievance.insert_date_time,\n" + 
			"    tn_order_header.order_no,\n" + 
			"    tn_order_header.order_date,\n" + 
			"    tn_order_header.fr_id,\n" + 
			"    tn_order_header.cust_id,\n" + 
			"    m_customer.cust_name,\n" + 
			"    m_customer.cust_mobile_no,\n" + 
			"    mn_grievences_type_instructn.caption AS TYPE,\n" + 
			"    mn_grievences_instruction.caption AS sub_type,\n" + 
			"    m_franchise.fr_name,\n" + 
			"    m_franchise.fr_code,\n" + 
			"    COALESCE((user.user_name),\n" + 
			"    0) AS insert_user_name,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            COUNT(*)\n" + 
			"        FROM\n" + 
			"                  t_grievance_trail\n" + 
			"        WHERE\n" + 
			"                   t_grievance_trail.grievance_id = t_grievance.grievance_id\n" + 
			"    ),\n" + 
			"    0\n" + 
			"    ) AS griv_trail_count,\n" + 
			"    t_grievance.ex_int1\n" + 
			"FROM\n" + 
			"    tn_order_header,\n" + 
			"    m_customer,\n" + 
			"    mn_grievences_type_instructn,\n" + 
			"    mn_grievences_instruction,\n" + 
			"    m_franchise,\n" + 
			"    t_grievance\n" + 
			"LEFT JOIN m_user user ON\n" + 
			"    t_grievance.insert_by_id = user.user_id\n" + 
			"WHERE\n" + 
			"    t_grievance.order_id = tn_order_header.order_id AND \n" + 
			"    t_grievance.grievance_type_id = mn_grievences_type_instructn.grev_type_id AND\n" + 
			"    t_grievance.grievance_subtype_id = mn_grievences_instruction.grievance_id AND\n" + 
			"    m_customer.cust_id = tn_order_header.cust_id AND\n" + 
			"    m_franchise.fr_id = tn_order_header.fr_id AND\n" + 
			"    tn_order_header.order_id=:orderId",nativeQuery=true)
	
	List<GetGrievanceHeader> getGrievanceHeaderByOrderId(@Param("orderId") int orderId);
}
