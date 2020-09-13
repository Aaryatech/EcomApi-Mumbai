package com.ats.ecomapi.master.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	public List<Category> findByDelStatusAndCompanyIdOrderByCatId(int del, int compId);

	Category findByCatId(int catId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_category` SET del_status=0 WHERE cat_id=:catId",nativeQuery=true)
	public int deleteCatId(@Param("catId") int catId);

	public Category findByCatPrefixIgnoreCase(String catId);

	public Category findByCatPrefixIgnoreCaseAndCatIdNot(String prefix, int catId);

}
