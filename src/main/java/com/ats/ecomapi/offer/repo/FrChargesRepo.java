package com.ats.ecomapi.offer.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.offer_model.FrCharges;
import com.ats.ecomapi.offer_model.OfferDetail;


public interface FrChargesRepo extends JpaRepository<FrCharges, Integer> {

	FrCharges findByfrId(int frId);
	
	//Akhilesh 2020-12-23
	@Query(value="SELECT * FROM mn_fr_charges WHERE ex_int1=:compId   ",nativeQuery=true)
	List<FrCharges> getAllFrChargesByCompId(@Param("compId") int compId);
	
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE mn_fr_charges SET from_date=:fromDt,to_date=:toDt,surcharge_fee=:surCharge ,packing_chg=:packing,handling_chg=:handLing,extra_chg=:exTra ,round_off_amt=:roundOff WHERE fr_id=:frId ",nativeQuery=true)
	int updateFrCharges(@Param("fromDt") String fromDt,@Param("toDt") String toDt,
						@Param("surCharge") int surCharge ,@Param("packing") int packing ,
						@Param("handLing") int handLing , @Param("exTra") int exTra,
						@Param("roundOff") int roundOff,@Param("frId") int frId );
	
	
	//Get frCharges Sachin 25-12-2020
	@Query(value = " SELECT COALESCE((SUM(surcharge_fee+packing_chg+handling_chg+extra_chg+round_off_amt) ),0 ) as ex_charge FROM mn_fr_charges WHERE fr_id=:frId " + 
			" AND CURRENT_DATE BETWEEN from_date AND to_date ORDER BY charge_id DESC LIMIT 1 ", nativeQuery = true)
	Float   getFrExChargesSumForFrId1(@Param("frId") int frId);

	//Get frCharges Sachin 30-12-2020
		@Query(value = " SELECT *  FROM mn_fr_charges WHERE fr_id=:frId " + 
				" AND CURRENT_DATE BETWEEN from_date AND to_date ORDER BY charge_id DESC LIMIT 1 ", nativeQuery = true)
		FrCharges   getFrExChargesSumForFrId(@Param("frId") int frId);

	
	
	
	
}
