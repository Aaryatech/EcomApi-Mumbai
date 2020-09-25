package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.ItemConfHeader;

public interface ItemConfHeaderRepo extends JpaRepository<ItemConfHeader, Integer>{
	
	//Sachin 23-09-2020
	//Desc-to update product confi header
	@Transactional
	@Modifying
	@Query(value = " UPDATE tn_item_config_header SET config_name=:configName,"
			+ " maker_user_id=:makerUserId,updt_dttime=:updtDttime WHERE config_header_id=:configHeaderId ", nativeQuery = true)
	int updateProdConfHeader(@Param("configName") String configName, @Param("makerUserId") int makerUserId,
			@Param("updtDttime") String updtDttime,@Param("configHeaderId") int configHeaderId);

	List<ItemConfHeader> findByCatIdAndDelStatus(int catId, int i);

	List<ItemConfHeader> findByCompanyIdAndDelStatus(int compId, int i);

}
