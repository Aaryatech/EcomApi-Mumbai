package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.mst_model.UserMaster;

public interface UserMasterRepo extends JpaRepository<UserMaster, Integer>{
	
	UserMaster findByUserId(int userId);
	
	List<UserMaster> findByDelStatusAndIsActive(int delStatus, int isActive);
	
	//to check if user mobile exists with given mobile no 
	Integer countByUserMobileNoAndDelStatus(String mobile,int delStatus);
	
	//to check if user mobile exists with given mobile no other than given userId
	Integer countByUserMobileNoAndUserIdNotAndDelStatus(String mobile,int userId,int delStatus);

	
}
