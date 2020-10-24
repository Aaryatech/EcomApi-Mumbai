package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.CompMaster;
import java.lang.String;

public interface CompMasterRepo extends JpaRepository<CompMaster, Integer> {

	CompMaster findByCompanyId(int compId);	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE\n" + 
			"        `m_company` \n" + 
			"    SET\n" + 
			"        del_status=0,\n" + 
			"        maker_user_id=:userId, \n" + 
			"        updt_dttime=:dateTime \n" + 
			"    WHERE\n" + 
			"        company_id=:compId ",nativeQuery=true)
	public int deleteCompany(@Param("compId") int compId,@Param("userId") int userId,@Param("dateTime") String dateTime);
//UPDATE `m_company` SET del_status=0 WHERE company_id=:compId AND maker_user_id=:userId AND updt_dttime=:dateTime 
	
	List<CompMaster> findByDelStatusOrderByCompanyIdDesc(int i);
	
	CompMaster findByCompanyPrefixIgnoreCaseAndDelStatus(String prefix, int del);
	
	CompMaster findByCompanyPrefixIgnoreCaseAndAndDelStatusAndCompanyIdNot(String prefix, int del, int compId);
	
	@Query(value="select\n" + 
			"         compmaster.company_id,\n" + 
			"         compmaster.comp_address,\n" + 
			"         compmaster.comp_bank_acc_no,\n" + 
			"         compmaster.comp_bank_branch_name,\n" + 
			"         compmaster.comp_bank_ifsc,\n" + 
			"         compmaster.comp_bank_name,\n" + 
			"         compmaster.comp_cin_no,\n" + 
			"         compmaster.comp_city,\n" + 
			"         compmaster.comp_contact_no,\n" + 
			"         compmaster.comp_email_address,\n" + 
			"         compmaster.comp_fda_declar_text,\n" + 
			"         compmaster.comp_fda_no,\n" + 
			"         compmaster.comp_gst_no,\n" + 
			"         compmaster.comp_gst_type,\n" + 
			"         compmaster.comp_opening_date,\n" + 
			"         compmaster.comp_pan_no,\n" + 
			"         compmaster.comp_state,\n" + 
			"         compmaster.comp_state_gst_code,\n" + 
			"         compmaster.comp_website,\n" + 
			"         compmaster.company_logo,\n" + 
			"         compmaster.company_name,\n" + 
			"         compmaster.company_prefix,\n" + 
			"         compmaster.company_type,\n" + 
			"         compmaster.del_status,\n" + 
			"         compmaster.ex_date1,\n" + 
			"         compmaster.ex_date2,\n" + 
			"         compmaster.ex_float1,\n" + 
			"         compmaster.ex_float2,\n" + 
			"         compmaster.ex_float3,\n" + 
			"         compmaster.ex_int1,\n" + 
			"         compmaster.ex_int2,\n" + 
			"         compmaster.ex_int3,\n" + 
			"         compmaster.ex_var1,\n" + 
			"         compmaster.ex_var2,\n" + 
			"         compmaster.ex_var3,\n" + 
			"         city.city_name as ex_var4,\n" + 
			"         compmaster.insert_dttime,\n" + 
			"         compmaster.is_active,\n" + 
			"         compmaster.maker_user_id,\n" + 
			"         compmaster.parent_comp_id,\n" + 
			"         compmaster.payment_gateway_applicable,\n" + 
			"         compmaster.payment_gateway_link,\n" + 
			"         compmaster.updt_dttime\n" + 
			"    from\n" + 
			"        m_company  compmaster,\n" + 
			"        mn_city city\n" + 
			"    where\n" + 
			"         compmaster.del_status=1  AND\n" + 
			"         city.city_id=compmaster.comp_city\n" + 
			"    order by\n" + 
			"         compmaster.company_id desc",nativeQuery=true)
	List<CompMaster> getAllCompany();
}
