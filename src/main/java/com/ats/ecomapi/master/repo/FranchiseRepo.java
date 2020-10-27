package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Franchise;
import java.lang.String;

public interface FranchiseRepo extends JpaRepository<Franchise, Integer> {

	List<Franchise> findByCompanyIdAndDelStatusOrderByFrIdDesc(int compId, int del);
	
	Franchise findByFrId(int frId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_franchise` SET del_status=0 WHERE fr_id=:frId", nativeQuery=true)
	int deleteFranchise(@Param("frId") int frId);
	
	
	Franchise findByFrContactNoAndDelStatus(String mobNo, int del);
	Franchise findByFrContactNoAndDelStatusAndFrIdNot(String mobNo, int del,  int frId);
	
	Franchise findByFrEmailIdAndDelStatus(String emailId, int del); 
	Franchise findByFrEmailIdAndDelStatusAndFrIdNot(String emailId, int del,  int frId);
	
	@Query(value="SELECT COUNT(fr_id) FROM `m_franchise` WHERE `fr_code` LIKE %:coPrefix% AND company_id=:compId",nativeQuery=true)
	public int getCompCountByPrefix(@Param("compId") int compId, @Param("coPrefix") String coPrefix);
	
	
	//Sachin 02-10-2020
	List<Franchise> findByCompanyIdAndDelStatusAndIsActiveOrderByFrIdDesc(int companyId, int delStatus,int isActive);
	
	@Query(value="SELECT * FROM m_franchise f WHERE fr_code = :frCode AND del_status = 1",nativeQuery=true)
	Franchise findByFrCodeAndDelStatus(String frCode);

	
		//Sachin 26-10-2020
		List<Franchise> findByDelStatusAndIsActiveOrderByFrIdDesc(int delStatus,int isActive);
		
		//Sachin 26-10-2020 to get All Franchise of given cityIdList
		List<Franchise> findByDelStatusAndIsActiveAndFrCityInOrderByFrIdDesc(int delStatus,int isActive,List<Integer> cityIdList);

		
		
		@Query(value=" SELECT fr_id, fr_code, fr_name, fr_address, opening_date, fr_image, fr_rating, fr_city, fr_email_id, fr_contact_no, fr_password, fda_number, gst_type, gst_number, pincode, owners_birth_day, fda_license_date_exp, shops_latitude, shops_logitude, m_franchise.is_active, m_franchise.del_status, m_franchise.company_id, pan_no, city, state, co_bank_name, co_bank_branch_name, co_bank_ifsc_code, co_bank_acc_no, payment_getway_link_same_as_parent, payment_getway_link, no_of_km_area_cover, user_id, add_date_time, edit_date_time, pincode_we_served, ex_float1, ex_float2, ex_float3, ex_float4, ex_float5, m_franchise.ex_var1, m_franchise.ex_var2, m_franchise.ex_var3, ex_var4, ex_var5, ex_var6, ex_var7, m_franchise.ex_date1, m_franchise.ex_date2, m_franchise.ex_int1, m_franchise.ex_int2, m_franchise.ex_int3 " + 
				" FROM m_franchise " + 
				" "
				+ " WHERE m_franchise.del_status=1 AND m_franchise.is_active=1 and m_franchise.company_id=:compId  " + 
				" AND m_franchise.fr_id NOT in (SELECT m_franchise.fr_id from m_franchise, m_route WHERE find_IN_SET (m_franchise.fr_id,m_route.fr_ids) and m_route.del_status=1 AND m_route.is_active=1 and m_route.company_id=:compId ) ",nativeQuery=true)

		List<Franchise> getFrListToAddInRoute(@Param("compId")int compId);

		@Query(value=" SELECT fr_id, fr_code, fr_name, fr_address, opening_date, fr_image, fr_rating, fr_city, fr_email_id, fr_contact_no, fr_password, fda_number, gst_type, gst_number, pincode, owners_birth_day, fda_license_date_exp, shops_latitude, shops_logitude, m_franchise.is_active, m_franchise.del_status, m_franchise.company_id, pan_no, city, state, co_bank_name, co_bank_branch_name, co_bank_ifsc_code, co_bank_acc_no, payment_getway_link_same_as_parent, payment_getway_link, no_of_km_area_cover, user_id, add_date_time, edit_date_time, pincode_we_served, ex_float1, ex_float2, ex_float3, ex_float4, ex_float5, m_franchise.ex_var1, m_franchise.ex_var2, m_franchise.ex_var3, ex_var4, ex_var5, ex_var6, ex_var7, m_franchise.ex_date1, m_franchise.ex_date2, m_franchise.ex_int1, m_franchise.ex_int2, m_franchise.ex_int3 " + 
				" FROM m_franchise " + 
				" "
				+ " WHERE m_franchise.del_status=1 AND m_franchise.is_active=1 and m_franchise.company_id=:compId  " + 
				" AND m_franchise.fr_id NOT in (SELECT m_franchise.fr_id from m_franchise, m_route WHERE find_IN_SET (m_franchise.fr_id,m_route.fr_ids) and m_route.del_status=1 AND m_route.is_active=1 and m_route.company_id=:compId and m_franchise.fr_id NOT IN (:frIds) ) ",nativeQuery=true)

		List<Franchise> getFrListToAddInRouteForEdit(@Param("compId") int compId, @Param("frIds") List<Integer> frIds);
				
}
