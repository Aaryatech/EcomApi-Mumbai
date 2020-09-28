package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.CustomerAddDetail;

public interface CustomerAddDetailRepo extends JpaRepository<CustomerAddDetail, Integer> {
	
	

	@Transactional
	@Modifying
	@Query(value="UPDATE `m_customer_address_detail` SET del_status=0 WHERE cust_detail_id=:custDetId AND maker_user_id=:userId AND updt_dttime=:dateTime",nativeQuery=true)
	public int deleteCustDet(@Param("custDetId") int custDetId,@Param("userId") int userId,@Param("dateTime") String dateTime);

	public CustomerAddDetail findByCustDetailId(int custDetId);

 
 
	public List<CustomerAddDetail> findByDelStatusAndCustIdOrderByCustDetailIdDesc(int i, int custId);


}
