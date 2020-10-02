package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.fe_model.CategoryList;
import com.ats.ecomapi.fe_model.FEBannerList;
import com.ats.ecomapi.fe_model.FEDataTraveller;
import com.ats.ecomapi.fe_model.FEProdDetail;
import com.ats.ecomapi.fe_model.FEProductHeader;
import com.ats.ecomapi.fe_model.FETestimonial;
import com.ats.ecomapi.fe_model.GetFlavorTagStatusList;
import com.ats.ecomapi.fe_repo.CategoryListRepo;
import com.ats.ecomapi.fe_repo.FEBannerListRepo;
import com.ats.ecomapi.fe_repo.FEProdDetailRepo;
import com.ats.ecomapi.fe_repo.FEProductHeaderRepo;
import com.ats.ecomapi.fe_repo.FETestimonialRepo;
import com.ats.ecomapi.fe_repo.GetFlavorTagStatusListRepo;
import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.master.repo.FranchiseRepo;


//Author-Sachin
//Created On-01-10-2020
//Desc- General Controller to exchange data to FrontEnd
@RestController
public class FrontEndDataController {

	@Autowired
	FEProductHeaderRepo feProdHeadRepo;
	@Autowired
	FEProdDetailRepo feProdDetailRepo;
	
	@Autowired FranchiseRepo frRepo;

	
	@Autowired CategoryListRepo feCategoryListRepo;
	@Autowired FEBannerListRepo feBannerRepo;
	@Autowired FETestimonialRepo feTestimonialRepo;
	@Autowired GetFlavorTagStatusListRepo feFlavTagStatusRepo;
	
	@RequestMapping(value = { "/getProdDataForFranchise" }, method = RequestMethod.POST)
	public @ResponseBody Object getProdDataForFranchise(@RequestParam int frId,@RequestParam("companyId") int companyId) {
	  
		 Runtime runtime = Runtime.getRuntime();
		    int MB = 1024*1024;

		    

		FEDataTraveller dataTraveller=new FEDataTraveller();
		//1
		List<FEBannerList> companyBannerList=new ArrayList<FEBannerList>();
		try {
			companyBannerList=feBannerRepo.getBannerListByCompanyId(companyId);
		}catch (Exception e) {
			companyBannerList=new ArrayList<FEBannerList>();
		}
		dataTraveller.setCompanyBannerList(companyBannerList);
		
		//2
		List<CategoryList> companyCatList=new ArrayList<>();
		try {
			companyCatList=feCategoryListRepo.getCompanyCatListByCompId(companyId);
		}catch (Exception e) {
			 companyCatList=new ArrayList<>();	
		}
		dataTraveller.setCompanyCatList(companyCatList);
		
		//3
		List<GetFlavorTagStatusList> flavorTagStatusList=new ArrayList<>();
		try {
			flavorTagStatusList=feFlavTagStatusRepo.getFlavorTagStatusListByCompId(companyId);
		}catch (Exception e) {
			flavorTagStatusList=new ArrayList<>();
		}
		dataTraveller.setFlavorTagStatusList(flavorTagStatusList);
		
		//4
		List<FEBannerList> franchiseBannerList=new ArrayList<>();
		try {
			//franchiseBannerList=feBannerRepo.getBannerListByFrId(frId);
		}catch (Exception e) {
			franchiseBannerList=new ArrayList<>();
		}
		dataTraveller.setFranchiseBannerList(franchiseBannerList);
		
		//5
		List<CategoryList> franchiseCatList=new ArrayList<>();
		try {
			franchiseCatList=feCategoryListRepo.getFrCatListByFrId(frId);
		}catch (Exception e) {
			franchiseCatList=new ArrayList<>();
		}
		dataTraveller.setFranchiseCatList(franchiseCatList);
		
		//6
		List<Franchise> frList = new ArrayList<>();
		try {
			frList=frRepo.findByCompanyIdAndDelStatusAndIsActiveOrderByFrIdDesc(companyId, 1, 1);
		}catch (Exception e) {
			frList = new ArrayList<>();
		}
		dataTraveller.setFrList(frList);
		
		//7
		List<FETestimonial> testimonialList=new ArrayList<FETestimonial>();
		try {
			testimonialList=feTestimonialRepo.getFETestimonialListByCompId(companyId);
		}catch (Exception e) {
			testimonialList=new ArrayList<FETestimonial>();
		}
		dataTraveller.setTestimonialList(testimonialList);
		
	   
	    try {
	    	
	    	frList=frRepo.findByCompanyIdAndDelStatusAndIsActiveOrderByFrIdDesc(companyId, 1, 1);
	    	
	    	if(frList==null) {
	    		frList=new ArrayList<>();
	    	}
	    	
	    }catch (Exception e) {
	    	
	    	frList=new ArrayList<>();
		}
	    
		List<FEProductHeader> prodHeaderList = new ArrayList<FEProductHeader>();
		List<FEProdDetail> prodDetailList = null;

		try {

			prodHeaderList = feProdHeadRepo.getFEProductHeaderByFrId(frId);
			if (prodHeaderList == null) {
				
			}
			if (prodHeaderList.isEmpty()) {

			} else {
				
				//System.err.println(prodHeaderList.toString());
				dataTraveller.setFeProductHeadList(prodHeaderList);

				List<Integer> homePageProdIdsList=feProdHeadRepo.getHomePageConfiguredProdIdsList(companyId);
				
				//System.err.println(homePageProdIdsList.toString());
				
				for(int a=0;a<homePageProdIdsList.size();a++) {
					
					for (int i = 0; i < prodHeaderList.size(); i++) {
						Integer isSame=Integer.compare((int)homePageProdIdsList.get(a),
								prodHeaderList.get(i).getProductId());
						if(isSame.equals(0)) {
							prodHeaderList.get(i).setIsHomePageProd(1);
							break;
						}
					} // End of For Loop prodHeaderList I.
					
					
				} // End of For Loop homePageProdIdsList A.
					
				for (int i = 0; i < prodHeaderList.size(); i++) {

					prodDetailList = new ArrayList<FEProdDetail>();
					try {
						prodDetailList = feProdDetailRepo.getFEProdDetailByConfHeadProdIdFrId(
								prodHeaderList.get(i).getConfigHeaderId(), prodHeaderList.get(i).getProductId(), frId);
					} catch (Exception e) {

					}

					if (prodDetailList != null) {
						prodHeaderList.get(i).setProdDetailList(prodDetailList);
					}
					
				} // End of For Loop prodHeaderList I.

			}//End of else.

		} catch (

		Exception e) {
			System.err.println("in main Exception");
			prodHeaderList = new ArrayList<FEProductHeader>();
		}
	    System.err.println("runtime fm " +runtime.getRuntime().freeMemory()/MB);

		return dataTraveller;
		
	}

}
