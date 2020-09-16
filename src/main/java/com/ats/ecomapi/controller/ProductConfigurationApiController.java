package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.master.model.Category;
import com.ats.ecomapi.master.model.CategoryProduct;
import com.ats.ecomapi.master.model.RelatedProductConfig;
import com.ats.ecomapi.master.repo.CategoryRepo;
import com.ats.ecomapi.master.repo.RelatedProductConfigRepo;
import com.ats.ecomapi.mst_model.ProductMaster;
import com.ats.ecomapi.mst_repo.ProductMasterRepo;

@RestController
public class ProductConfigurationApiController {

	@Autowired
	RelatedProductConfigRepo relatedProductConfigRepo;
	@Autowired
	CategoryRepo catRepo;

	@Autowired
	ProductMasterRepo productMasterRepo;

	@RequestMapping(value = { "/saveRelatedProductConfig" }, method = RequestMethod.POST)
	public @ResponseBody RelatedProductConfig saveRelatedProductConfig(@RequestBody RelatedProductConfig config) {
		System.err.println("RelatedProductConfig***" + config.toString());

		RelatedProductConfig save = new RelatedProductConfig();
		try {
			save = relatedProductConfigRepo.save(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;

	}

	@RequestMapping(value = { "/getAllProductAndCategory" }, method = RequestMethod.POST)
	public @ResponseBody List<CategoryProduct> getAllProductAndCategory(@RequestParam("compId") int compId) {

		System.err.println("compId" + compId);
		List<CategoryProduct> list = new ArrayList<CategoryProduct>();
		List<Category> catList = new ArrayList<Category>();
		try {
			catList = catRepo.findByDelStatusAndCompanyIdOrderByCatId(1, compId);
			System.err.println("catlist" + catList.toString());
			for (int i = 0; i < catList.size(); i++) {

				CategoryProduct catP = new CategoryProduct();
				catP.setCat(catList.get(i));
				List<ProductMaster> proList = productMasterRepo.findByProdCatIdAndDelStatus(catList.get(i).getCatId(),
						1);

				System.err.println("prolist" + proList.toString());
				catP.setProductList(proList);
				
				System.err.println("catp"+catP.toString());
				list.add(catP);

			}

			System.err.println("list" + list.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/getAllProductByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getAllProduct(@RequestParam("compId") int compId) {
		List<ProductMaster> proList = new ArrayList<ProductMaster>();

		try {
			proList = productMasterRepo.findByDelStatusAndCompanyId(1,compId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proList;
	}
	
	
	@RequestMapping(value = { "/getAllProductByCatId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getAllProductByCatId(@RequestParam("catId") int catId) {
		List<ProductMaster> proList = new ArrayList<ProductMaster>();

		try {
			proList = productMasterRepo.findByDelStatusAndProdCatId(1,catId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proList;
	}

}
