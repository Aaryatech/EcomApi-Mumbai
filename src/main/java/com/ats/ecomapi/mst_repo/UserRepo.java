package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ecomapi.mst_model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	//@Query(value = " SELECT * FROM m_user WHERE user_id=:userId ", nativeQuery = true)
	User findByUserId( int userId);
	
	
	List<User> findByDelStatusAndIsActive(int delStatus, int isActive);
	
	//to check if user mobile exists with given mobile no 
	Integer countByUserMobileNoAndDelStatus(String mobile,int delStatus);
	
	//to check if user mobile exists with given mobile no other than given userId
	Integer countByUserMobileNoAndUserIdNotAndDelStatus(String mobile,int userId,int delStatus);

	
	@Query(value = " SELECT m_user.user_id FROM m_user where user_email=:userName and del_status=1 and is_active=1 ", nativeQuery = true)
	int getUserId(@Param("userName") String userName);

	@Transactional
	@Modifying
	@Query(value = " UPDATE m_user SET password=:userPass, is_enrolled=1, "
			+ " maker_user_id=:makerUserId, updt_dttime=:makerDttime"
			+ " WHERE user_id=:userId and del_status=1 ", nativeQuery = true)
	Integer updatePass(@Param("userId") int userId, @Param("userPass") String userPass,
			@Param("makerUserId") int makerUserId,@Param("makerDttime") String makerDttime);
	
	@Transactional
	@Modifying
	@Query(value = " UPDATE m_user SET role_id=:roleId, "
			+ " maker_user_id=:makerUserId, updt_dttime=:makerDttime"
			+ " WHERE user_id IN (:userIdList) and del_status=1 ", nativeQuery = true)

	int updateRoleId(@Param("roleId") int roleId, 
			@Param("userIdList") List<String> userIdList,
			@Param("makerUserId") int makerUserId,@Param("makerDttime") String makerDttime);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_user SET del_status=0 WHERE user_id=:userId",nativeQuery=true)
	public int deleteUserById(@Param("userId") int userId);
	
	
	User findByUserMobileNoAndDelStatus(String mobNo, int del);
	User findByUserMobileNoAndDelStatusAndUserIdNot(String mobNo, int del, int userId);
	
	User findByUserEmailAndDelStatus(String email, int del);	
	User findByUserEmailAndDelStatusAndUserIdNot(String email, int del, int userId);

	@Query(value = " SELECT \n" + 
			"               user.user_id, \n" + 
			"               user.user_name, \n" + 
			"               user.user_mobile_no, \n" + 
			"               user.user_address, \n" + 
			"               user.user_email, \n" + 
			"               user.reg_date, \n" + 
			"               user.birth_date, \n" + 
			"               user.profile_pic, \n" + 
			"               user.is_enrolled, \n" + 
			"               user.otp_text, \n" + 
			"               user.password, \n" + 
			"               user.company_Id, \n" + 
			"               user.dept_id, \n" + 
			"               user.user_type_id, \n" + 
			"               user.del_status, \n" + 
			"               user.is_active, \n" + 
			"               user.ex_int1, \n" + 
			"               user.ex_int2, \n" + 
			"               user.ex_int3, \n" + 
			"               user.ex_float1, \n" + 
			"               user.ex_float2, \n" + 
			"               user.ex_float3, \n" + 
			"               user.ex_date1, \n" + 
			"               user.ex_date2, \n" + 
			"               user.ex_var1, \n" + 
			"               user.ex_var2, \n" + 
			"               user.ex_var3, \n" + 
			"               utype.user_type_name AS ex_var4 ,\n" + 
			"               user.updt_dttime, \n" + 
			"               user.insert_dttime, \n" + 
			"               user.role_id, \n" + 
			"               user.maker_user_id \n" + 
			"           FROM \n" + 
			"          		\n" + 
			"                m_user user, \n" + 
			"                m_user_type utype\n" + 
			"                \n" + 
			"           WHERE \n" + 
			"              user.company_Id=:compId AND \n" + 
			"               user.del_status=1 AND\n" + 
			"               utype.user_type_id=user.user_type_id\n" + 
			"               ORDER BY user.user_id DESC", nativeQuery = true)
	List<User> getAllUserList(@Param("compId") int compId);
	
	User findByUserIdAndDelStatusAndCompanyId(int userId, int del, int compId);
	
	
}
