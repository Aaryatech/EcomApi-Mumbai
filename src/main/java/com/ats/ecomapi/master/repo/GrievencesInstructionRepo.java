package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GrievencesInstruction;


public interface GrievencesInstructionRepo extends JpaRepository<GrievencesInstruction, Integer> {

	@Query(value="SELECT\n" + 
			"    grv.grievance_id,\n" + 
			"    grv.caption,\n" + 
			"    grv.description,\n" + 
			"    grv.del_status,\n" + 
			"    grv.grievence_type_id,\n" + 
			"    grv.is_active,\n" + 
			"    grv.company_id,\n" + 
			"    grv.allow_to_copy,\n" +
			"    grv.is_parent,\n" +
			"    grv.ex_int1,\n" + 
			"    grv.ex_int2,\n" + 
			"    grv.ex_var1,\n" + 
			"    grv.ex_var2,\n" + 
			"    type.caption as ex_var3\n" + 
			"FROM\n" + 
			"    mn_grievences_instruction grv,\n" + 
			"    mn_grievences_type_instructn type\n" + 
			"WHERE\n" + 
			"    grv.del_status=:del AND\n" + 
			"    type.del_status=:del AND\n" + 
			"    grv.company_id=:compId AND\n" + 
			"    grv.grievence_type_id=type.grev_type_id Order By grv.grievance_id Desc",nativeQuery=true)
	List<GrievencesInstruction> findByDelStatusAndCompanyIdOrderByGrievanceIdDesc(@Param("del") int del,@Param("compId") int compId);
	
	GrievencesInstruction findByCaptionIgnoreCaseAndCompanyId(String caption, int compId);
	
	GrievencesInstruction findByCaptionIgnoreCaseAndCompanyIdAndGrievanceIdNot(String caption, int compid, int grievancId);
	
	GrievencesInstruction findByGrievanceIdAndDelStatusAndCompanyId(int grievanceId, int del, int compId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `mn_grievences_instruction` SET del_status=0 WHERE grievance_id=:grievanceId",nativeQuery=true)
	public int deleteGrievancesInstructn(@Param("grievanceId") int grievanceId);
}
