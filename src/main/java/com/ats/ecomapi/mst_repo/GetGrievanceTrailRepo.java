package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.GetGrievanceTrail;

public interface GetGrievanceTrailRepo extends JpaRepository<GetGrievanceTrail, Integer> {

	@Query(value="SELECT\n" + 
			"    t_grievance_trail.trail_id,\n" + 
			"    t_grievance_trail.grievance_id,\n" + 
			"    t_grievance_trail.remark,\n" + 
			"    t_grievance_trail.status,\n" + 
			"    t_grievance_trail.action_by_user_id,\n" + 
			"    t_grievance_trail.action_date_time,\n" + 
			"    t_grievance_trail.ex_var1,\n" + 
			"    t_grievance_trail.ex_var2,\n" + 
			"    t_grievance_trail.identified_root_cause,\n" + 
			"    t_grievance_trail.grievance_res_type,\n" + 
			"    t_grievance_trail.resolution_detail,\n" + 
			"    t_grievance_trail.repay_amt,\n" + 
			"    t_grievance_trail.repay_details,\n" + 
			"    t_grievance_trail.griv_action_value,\n" + 
			"    t_grievance_trail.griv_action_text,\n" + 
			"    COALESCE((user_data.user_name),\n" + 
			"    '0') AS action_by_user_name\n" + 
			"FROM\n" + 
			"    t_grievance_trail\n" + 
			"LEFT JOIN m_user user_data ON\n" + 
			"    t_grievance_trail.action_by_user_id = user_data.user_id\n" + 
			"WHERE\n" + 
			"    t_grievance_trail.grievance_id = :grievanceId\n" + 
			"ORDER BY\n" + 
			"    t_grievance_trail.action_date_time\n" + 
			"DESC\n" + 
			"    ",nativeQuery=true)
	List<GetGrievanceTrail> getGrievTrailListByGrievanceId(@Param("grievanceId") int grievanceId);
}
