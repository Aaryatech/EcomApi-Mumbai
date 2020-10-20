package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Category;
import com.ats.ecomapi.master.model.Uom;


public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	public List<Category> findByDelStatusAndCompanyIdOrderByCatIdDesc(int del, int compId);

	Category findByCatId(int catId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_category` SET del_status=0 WHERE cat_id=:catId",nativeQuery=true)
	public int deleteCatId(@Param("catId") int catId);

	public Category findByCatPrefixIgnoreCaseAndCompanyId(String catId, int compId);

	public Category findByCatPrefixIgnoreCaseAndCompanyIdAndCatIdNot(String prefix, int catId, int compId);

	 

 
	public List<Category> findByDelStatusAndCatIdIn(int i, List<Integer> primaryIds);

	
	
 

}
