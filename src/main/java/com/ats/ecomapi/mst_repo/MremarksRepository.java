package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.MRemarks;



public interface MremarksRepository extends JpaRepository<MRemarks, Integer> {
	
	@Query(value="SELECT * FROM m_remarks WHERE del_status=0",nativeQuery=true)
	List<MRemarks> getAllRemarks();
	
	@Query(value="SELECT * FROM m_remarks WHERE del_status=0 AND remark_id=:remarkId",nativeQuery=true)
	MRemarks getRemarkById(@Param("remarkId") int remarkId);
	
	
	@Modifying
	@Transactional
	@Query(value="UPDATE m_remarks SET del_status=1 WHERE remark_id=remarkId ",nativeQuery=true)
	int deleteRemark(@Param("remarkId") int remarkId);

}
