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
	@Query(value="UPDATE\n" + 
			"    `m_customer_address_detail`\n" + 
			"SET\n" + 
			"    del_status = 0,\n" + 
			"    updt_dttime = :dateTime, maker_user_id = :userId\n" + 
			"WHERE\n" + 
			"    cust_detail_id = :custDetId",nativeQuery=true)
	public int deleteCustDet(@Param("custDetId") int custDetId,@Param("userId") int userId,@Param("dateTime") String dateTime);

	public CustomerAddDetail findByCustDetailId(int custDetId);

 
 
	public List<CustomerAddDetail> findByDelStatusAndCustIdOrderByCustDetailIdDesc(int i, int custId);
	
	@Query(value="select\n" + 
			"         customer.cust_detail_id,\n" + 
			"         customer.address,\n" + 
			"         customer.area_id,\n" + 
			"         customer.caption,\n" + 
			"         customer.city_id,\n" + 
			"         customer.cust_id,\n" + 
			"         customer.del_status,\n" + 
			"         customer.ex_int1,\n" + 
			"         customer.ex_int2,\n" + 
			"         customer.ex_int3,\n" + 
			"         customer.ex_var1,\n" + 
			"         customer.ex_var2 ,\n" + 
			"         city.city_name as ex_var3,\n" + 
			"         customer.insert_dttime,\n" + 
			"         customer.is_active,\n" + 
			"         customer.landmark,\n" + 
			"         customer.latitude,\n" + 
			"         customer.longitude,\n" + 
			"         customer.maker_user_id,\n" + 
			"         customer.updt_dttime \n" + 
			"    from\n" + 
			"        m_customer_address_detail customer, \n" + 
			"        mn_city city\n" + 
			"    where\n" + 
			"         customer.del_status=1 \n" + 
			"        AND  customer.cust_id=:custId\n" + 
			"        AND  customer.city_id=city.city_id\n" + 
			"    order by\n" + 
			"         customer.cust_detail_id desc",nativeQuery=true)
	public List<CustomerAddDetail> findByCustAddresDtl(@Param ("custId") int custId);


}
