package com.ats.ecomapi.mst_repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.ItemConfDetail;

public interface ItemConfDetailRepo extends JpaRepository<ItemConfDetail, Integer>{
	
	//Sachin 23-09-2020
		//Desc-to update product config detail
			@Transactional
			@Modifying
			@Query(value = " UPDATE tn_item_config_detail SET rate_amt=:rateAmt,mrp_amt=:mrpAmt,sp_rate_amt1=:spRateAmt1,"
					+ "sp_rate_amt2=:spRateAmt2,sp_rate_amt3=:spRateAmt3,sp_rate_amt4=:spRateAmt4,"
					+ " maker_user_id=:makerUserId,updt_dttime=:updtDttime WHERE config_detail_id=:configDetailId ", nativeQuery = true)
			int updateProdConfDetail(@Param("rateAmt") float rateAmt, @Param("mrpAmt") float mrpAmt,
					@Param("spRateAmt1") float spRateAmt1,@Param("spRateAmt2") float spRateAmt2,@Param("spRateAmt3") float spRateAmt3,
					@Param("spRateAmt4") float spRateAmt4, @Param("makerUserId") int makerUserId,
					@Param("updtDttime") String updtDttime,@Param("configDetailId") int configDetailId);
	
}
