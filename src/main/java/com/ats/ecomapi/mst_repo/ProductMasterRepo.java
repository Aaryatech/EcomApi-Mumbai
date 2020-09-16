package com.ats.ecomapi.mst_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.ecomapi.mst_model.ProductMaster;

public interface ProductMasterRepo extends JpaRepository<ProductMaster, Integer>{

	List<ProductMaster> findByProdCatIdAndDelStatus(int catId, int i);

	List<ProductMaster> findByDelStatus(int i);

	List<ProductMaster> findByDelStatusAndProdCatId(int i, int catId);

	List<ProductMaster> findByDelStatusAndCompanyId(int i, int compId);

}
