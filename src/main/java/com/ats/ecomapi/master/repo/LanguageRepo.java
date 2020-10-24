package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Language;


public interface LanguageRepo extends JpaRepository<Language, Integer> {

	List<Language> findByDelStatusAndCompanyIdOrderByLangIdDesc(int del, int compId);

	Language findByLangCodeIgnoreCaseAndCompanyId(String langCode, int compId);
	
	Language findByLangCodeIgnoreCaseAndCompanyIdAndLangIdNot(String langCode,int compId, int langId);
	
	Language findByLangIdAndDelStatusAndCompanyId(int langId, int del, int compId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE mn_language SET del_status = 0 WHERE lang_id=:langId",nativeQuery=true)
	public int deleteLanguage(@Param("langId") int langId);
}
