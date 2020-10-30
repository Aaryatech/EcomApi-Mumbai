package com.ats.ecomapi.master.repo;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.FrDelvrBoyConfig;

public interface FrDelvrBoyConfigRepo extends JpaRepository<FrDelvrBoyConfig, Integer> {

	@Query(value="SELECT fr_ids FROM `t_fr_delivery_boy_assign` WHERE company_id=:compId AND del_boy_id=:delBoyId",nativeQuery=true)
	String getFrDelBoyIds(@Param("compId") int compId, @Param("delBoyId") int delBoyId);
	
	FrDelvrBoyConfig findBydelBoyIdAndCompanyId (int delBoyId, int compId);

	@Modifying
	@Transactional
	@Query(value="UPDATE `t_fr_delivery_boy_assign` SET `fr_ids` = :frIdsStr WHERE `t_fr_delivery_boy_assign`.`del_boy_id` = :delBoyId",nativeQuery=true)
	int updateFrConfig(@Param("delBoyId") int delBoyId, @Param("frIdsStr") String frIdsStr);

}
