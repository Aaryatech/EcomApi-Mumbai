package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetUser;


public interface GetUserRepo extends CrudRepository<GetUser, Integer>{
	
	@Query(value = " select b.*,IFNULL(c.role_name,'NA') AS role_name FROM (SELECT m_user.user_id,m_user.user_name, "
			+ "m_user.user_mobile_no,m_user.user_email, " + 
			"m_user.role_id from m_user WHERE m_user.del_status=1 and m_user.company_id=:compId ) b "+ 
			"LEFT JOIN ( SELECT m_assign_role.role_id,m_assign_role.role_name FROM m_assign_role ) c ON b.role_id=c.role_id " + 
			"ORDER BY b.role_id,b.user_name ASC ", nativeQuery = true)
	List<GetUser> getUserListForAssignRole(@Param("compId") int compId);

}
