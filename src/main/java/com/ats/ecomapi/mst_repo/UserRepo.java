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
	

	
	
}
