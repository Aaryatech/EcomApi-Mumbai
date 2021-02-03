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
	
	
	//Akhilesh 2020-12-23 Get All Franchise With Fr Charges By CompanyId
	@Query(value="SELECT\n" + 
			"    franchise0_.fr_id,\n" + 
			"    franchise0_.fr_code,\n" + 
			"    franchise0_.fr_name,\n" + 
			"    franchise0_.fr_address,\n" + 
			"    franchise0_.opening_date,\n" + 
			"    franchise0_.fr_image,\n" + 
			"    franchise0_.fr_rating,\n" + 
			"    franchise0_.fr_city,\n" + 
			"    franchise0_.fr_email_id,\n" + 
			"    franchise0_.fr_contact_no,\n" + 
			"    franchise0_.fr_password,\n" + 
			"    franchise0_.fda_number,\n" + 
			"    franchise0_.gst_type,\n" + 
			"    franchise0_.gst_number,\n" + 
			"    franchise0_.pincode,\n" + 
			"    franchise0_.owners_birth_day,\n" + 
			"    franchise0_.fda_license_date_exp,\n" + 
			"    franchise0_.shops_latitude,\n" + 
			"    franchise0_.shops_logitude,\n" + 
			"    franchise0_.is_active,\n" + 
			"    franchise0_.del_status,\n" + 
			"    franchise0_.company_id,\n" + 
			"    franchise0_.pan_no,\n" + 
			"    franchise0_.city,\n" + 
			"    franchise0_.state,\n" + 
			"    franchise0_.co_bank_name,\n" + 
			"    franchise0_.co_bank_branch_name,\n" + 
			"    franchise0_.co_bank_ifsc_code,\n" + 
			"    franchise0_.co_bank_acc_no,\n" + 
			"    franchise0_.payment_getway_link_same_as_parent,\n" + 
			"    franchise0_.payment_getway_link,\n" + 
			"    franchise0_.no_of_km_area_cover,\n" + 
			"    franchise0_.user_id,\n" + 
			"    franchise0_.add_date_time,\n" + 
			"    franchise0_.edit_date_time,\n" + 
			"    franchise0_.pincode_we_served,\n" + 
			"    franchise0_.ex_float1,\n" + 
			"    franchise0_.ex_float2,\n" + 
			"    franchise0_.ex_float3,\n" + 
			"    franchise0_.ex_float4,\n" + 
			"    franchise0_.ex_float5,\n" + 
			"    franchise0_.ex_var1,\n" + 
			
			"  COALESCE(ch.charge_id, 0) AS ex_var2,\n" + 
			"  COALESCE(ch.surcharge_fee, 0) AS ex_var3,\n" + 
			" COALESCE(ch.packing_chg, 0) AS ex_var4,\n" + 
			"  COALESCE(ch.handling_chg, 0) AS ex_var5,\n" + 
			"    COALESCE(ch.extra_chg, 0) AS ex_var6,\n" + 
			"    COALESCE(ch.round_off_amt, 0) AS ex_var7,\n" + 
			"    franchise0_.ex_date1,\n" + 
			"    franchise0_.ex_date2,\n" + 
			"    franchise0_.ex_int1,\n" + 
			"    franchise0_.ex_int2,\n" + 
			"    franchise0_.ex_int3\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        *\n" + 
			"    FROM\n" + 
			"        m_franchise\n" + 
			"    WHERE\n" + 
			"        m_franchise.company_id =:compId AND m_franchise.del_status = 1\n" + 
			") franchise0_\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        fr_id,\n" + 
			"     \n" + 
			"            mn_fr_charges.charge_id,\n" + 
			"            mn_fr_charges.surcharge_fee,\n" + 
			"     \n" + 
			"            mn_fr_charges.packing_chg,\n" + 
			"     \n" + 
			"            mn_fr_charges.handling_chg,\n" + 
			"\n" + 
			"            mn_fr_charges.extra_chg,\n" + 
			"      \n" + 
			"            mn_fr_charges.round_off_amt\n" + 
			"     \n" + 
			"    FROM\n" + 
			"        mn_fr_charges\n" + 
			"    WHERE\n" + 
			"        1\n" + 
			") ch\n" + 
			"ON\n" + 
			"    franchise0_.fr_id = ch.fr_id\n" + 
			"ORDER BY\n" + 
			"    franchise0_.fr_id\n" + 
			"DESC",nativeQuery=true)
	List<Franchise> getAllFranchiseByCompIdWithCharges(@Param("compId") int compId);
	
	
	
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
		
		@Query(value="SELECT\n" + 
				"    franchise0_.fr_id,\n" + 
				"    franchise0_.fr_code,\n" + 
				"    franchise0_.fr_name,\n" + 
				"    franchise0_.fr_address,\n" + 
				"    franchise0_.opening_date,\n" + 
				"    franchise0_.fr_image,\n" + 
				"    franchise0_.fr_rating,\n" + 
				"    franchise0_.fr_city,\n" + 
				"    franchise0_.fr_email_id,\n" + 
				"    franchise0_.fr_contact_no,\n" + 
				"    franchise0_.fr_password,\n" + 
				"    franchise0_.fda_number,\n" + 
				"    COALESCE(franchise0_.gst_type,0) AS gst_type,\n" + 
				"    franchise0_.gst_number,\n" + 
				"    franchise0_.pincode,\n" + 
				"    franchise0_.owners_birth_day,\n" + 
				"    franchise0_.fda_license_date_exp,\n" + 
				"    franchise0_.shops_latitude,\n" + 
				"    franchise0_.shops_logitude,\n" + 
				"    franchise0_.is_active,\n" + 
				"    franchise0_.del_status,\n" + 
				"    franchise0_.company_id,\n" + 
				"    franchise0_.pan_no,\n" + 
				"    ct.city_name as city,\n" + 
				"    franchise0_.state,\n" + 
				"    franchise0_.co_bank_name,\n" + 
				"    franchise0_.co_bank_branch_name,\n" + 
				"    franchise0_.co_bank_ifsc_code,\n" + 
				"    franchise0_.co_bank_acc_no,\n" + 
				"    franchise0_.payment_getway_link_same_as_parent,\n" + 
				"    franchise0_.payment_getway_link,\n" + 
				"    franchise0_.no_of_km_area_cover,\n" + 
				"    franchise0_.user_id,\n" + 
				"    franchise0_.add_date_time,\n" + 
				"    franchise0_.edit_date_time,\n" + 
				"    franchise0_.pincode_we_served,\n" + 
				"    franchise0_.ex_var1,\n" + 
				"    franchise0_.ex_var2,\n" + 
				"    franchise0_.ex_var3,\n" + 
				"    franchise0_.ex_var4,\n" + 
				"    franchise0_.ex_var5,\n" + 
				"    franchise0_.ex_date1,\n" + 
				"    franchise0_.ex_date2,\n" + 
				"    franchise0_.ex_int1,\n" + 
				"    franchise0_.ex_int2,\n" + 
				"    DATE_FORMAT(ch.from_date, '%d-%m-%Y') AS ex_var6,\n" + 
				"    DATE_FORMAT(ch.to_date, '%d-%m-%Y') AS ex_var7,\n" + 
				"    COALESCE(ch.charge_id, 0) AS ex_int3,\n" + 
				"    COALESCE(ch.surcharge_fee, 0) AS ex_float1,\n" + 
				"    COALESCE(ch.packing_chg, 0) AS ex_float2,\n" + 
				"    COALESCE(ch.handling_chg, 0) AS ex_float3,\n" + 
				"    COALESCE(ch.extra_chg, 0) AS ex_float4,\n" + 
				"    COALESCE(ch.round_off_amt, 0) AS ex_float5\n" + 
				"FROM\n" + 
				"    (\n" + 
				"    SELECT\n" + 
				"        *\n" + 
				"    FROM\n" + 
				"        m_franchise\n" + 
				"    WHERE\n" + 
				"        m_franchise.company_id = :compId AND m_franchise.del_status = 1\n" + 
				") franchise0_\n" + 
				"LEFT JOIN(\n" + 
				"    SELECT\n" + 
				"        fr_id,\n" + 
				"   		mn_fr_charges.from_date,\n" + 
				"   		mn_fr_charges.to_date,\n" + 
				"        mn_fr_charges.charge_id,\n" + 
				"        mn_fr_charges.surcharge_fee,\n" + 
				"        mn_fr_charges.packing_chg,\n" + 
				"        mn_fr_charges.handling_chg,\n" + 
				"        mn_fr_charges.extra_chg,\n" + 
				"        mn_fr_charges.round_off_amt\n" + 
				"    FROM\n" + 
				"        mn_fr_charges\n" + 
				"    WHERE\n" + 
				"        1\n" + 
				") ch\n" + 
				"ON\n" + 
				"    franchise0_.fr_id = ch.fr_id\n" + 
				"LEFT JOIN(\n" + 
				"    SELECT city.city_id,\n" + 
				"        city.city_name,\n" + 
				"        city_code\n" + 
				"    FROM\n" + 
				"        mn_city city\n" + 
				"    WHERE\n" + 
				"        city.company_id = :compId\n" + 
				") ct\n" + 
				"ON\n" + 
				"    ct.city_id = franchise0_.fr_city\n" + 
				"ORDER BY\n" + 
				"    franchise0_.fr_id\n" + 
				"DESC\n" + 
				"    ",nativeQuery=true)
		List<Franchise> getAllFranchiseByCompIdForExlPdf(@Param("compId") int compId);
				
}
