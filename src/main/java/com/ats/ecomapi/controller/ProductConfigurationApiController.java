package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ats.ecomapi.master.model.GetRequreProduct;
import com.ats.ecomapi.master.model.RelatedProductConfig;
import com.ats.ecomapi.master.repo.CategoryRepo;
import com.ats.ecomapi.master.repo.GetRequreProductRepo;
import com.ats.ecomapi.master.repo.RelatedProductConfigRepo;
import com.ats.ecomapi.mst_model.GetRelatedProductConfig;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.ProductMaster;
import com.ats.ecomapi.mst_repo.GetRelatedProductConfigRepo;
import com.ats.ecomapi.mst_repo.ProductMasterRepo;

@RestController
public class ProductConfigurationApiController {

	@Autowired
	RelatedProductConfigRepo relatedProductConfigRepo;
	@Autowired
	CategoryRepo catRepo;

	@Autowired
	ProductMasterRepo productMasterRepo;
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 16-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- saveRelatedProductConfig

	@RequestMapping(value = { "/saveRelatedProductConfig" }, method = RequestMethod.POST)
	public @ResponseBody RelatedProductConfig saveRelatedProductConfig(@RequestBody RelatedProductConfig config) {

		RelatedProductConfig save = new RelatedProductConfig();
		try {
			save = relatedProductConfigRepo.save(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;

	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :-16-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getAllProductAndCategory
	@RequestMapping(value = { "/getAllProductAndCategory" }, method = RequestMethod.POST)
	public @ResponseBody List<CategoryProduct> getAllProductAndCategory(@RequestParam("compId") int compId) {

		List<CategoryProduct> list = new ArrayList<CategoryProduct>();
		List<Category> catList = new ArrayList<Category>();
		try {
			catList = catRepo.findByDelStatusAndCompanyIdOrderByCatId(1, compId);
			for (int i = 0; i < catList.size(); i++) {

				CategoryProduct catP = new CategoryProduct();
				catP.setCat(catList.get(i));
				
				List<ProductMaster> proList = productMasterRepo.findByProdCatIdAndDelStatus(catList.get(i).getCatId(),
						1);

				catP.setProductList(proList);

				list.add(catP);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :-16-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getAllProductByCompId

	@RequestMapping(value = { "/getAllProductByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getAllProduct(@RequestParam("compId") int compId) {
		List<ProductMaster> proList = new ArrayList<ProductMaster>();

		try {
			proList = productMasterRepo.findByDelStatusAndCompanyId(1, compId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proList;
	}
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 16-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getAllProductByCatId

	@RequestMapping(value = { "/getAllProductByCatId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getAllProductByCatId(@RequestParam("catId") int catId) {
		List<ProductMaster> proList = new ArrayList<ProductMaster>();

		try {
			proList = productMasterRepo.findByDelStatusAndProdCatId(1, catId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proList;
	}
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 16-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getRelProConfigByCompId

	@Autowired
	GetRelatedProductConfigRepo getRelatedProductConfigRepo;

	@RequestMapping(value = { "/getRelProConfigByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetRelatedProductConfig> getRelProConfigByCompId(@RequestParam("compId") int compId) {
		List<GetRelatedProductConfig> proList = new ArrayList<GetRelatedProductConfig>();

		try {
			proList = getRelatedProductConfigRepo.getCRelatedProductConfig(compId);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proList;
	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :-16-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- deleteProdConfig
	@RequestMapping(value = { "/deleteProdConfig" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteUserById(@RequestParam int relatedProductId,@RequestParam int userId,@RequestParam String  dateTime) {

		Info info = new Info();
		try {
			int res = relatedProductConfigRepo.deleteConfig(relatedProductId,userId,dateTime);
			if (res > 0) {
				info.setError(false);
				info.setMessage(" Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete  ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :-16-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getRelProConfigByPrimaryItemId
	@Autowired
	GetRequreProductRepo getRequreProductRepo;

	@RequestMapping(value = { "/getRelProConfigByPrimaryItemId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetRequreProduct> getRelProConfigByPrimaryItemId(
			@RequestParam("primaryItemId") int primaryItemId) {
		List<GetRequreProduct> proList = new ArrayList<GetRequreProduct>();
		RelatedProductConfig relProConfig = new RelatedProductConfig();
		try {
			relProConfig = relatedProductConfigRepo.findByPrimaryItemId(primaryItemId);

			String ids = relProConfig.getSecondaryItemId();

			String[] elements = ids.split(",");

			// step two : convert String array to list of String
			List<String> list = Arrays.asList(elements);

 
			proList = getRequreProductRepo.getAllProductCat(list);
			
			proList.get(0).setConfigId(relProConfig.getRelatedProductId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proList;
	}

}
