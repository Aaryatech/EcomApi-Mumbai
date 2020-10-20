package com.ats.ecomapi.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.common.CommonUtility;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Author-Sachin
//Created On-01-10-2020
//Desc- General Controller to exchange data to FrontEnd
@RestController
public class FrontEndDataController {

	@Autowired
	FEProductHeaderRepo feProdHeadRepo;
	@Autowired
	FEProdDetailRepo feProdDetailRepo;

	@Autowired
	FranchiseRepo frRepo;

	@Autowired
	CategoryListRepo feCategoryListRepo;
	@Autowired
	FEBannerListRepo feBannerRepo;
	@Autowired
	FETestimonialRepo feTestimonialRepo;
	@Autowired
	GetFlavorTagStatusListRepo feFlavTagStatusRepo;

	@RequestMapping(value = { "/getProdDataForFranchise" }, method = RequestMethod.POST)
	public @ResponseBody Object getProdDataForFranchise(@RequestParam("frId") int frId,
			@RequestParam("companyId") int companyId) {

		Runtime runtime = Runtime.getRuntime();
		int MB = 1024 * 1024;
		List<FEDataTraveller> dataTravellerList = new ArrayList<>();

		for (int w = 0; w < 300; w++) {
			FEDataTraveller dataTraveller = new FEDataTraveller();
			// 1
			List<FEBannerList> companyBannerList = new ArrayList<FEBannerList>();
			try {
				companyBannerList = feBannerRepo.getBannerListByCompanyId(companyId);
			} catch (Exception e) {
				companyBannerList = new ArrayList<FEBannerList>();
			}
			dataTraveller.setCompanyBannerList(companyBannerList);

			// 2
			List<CategoryList> companyCatList = new ArrayList<>();
			try {
				companyCatList = feCategoryListRepo.getCompanyCatListByCompId(companyId);
			} catch (Exception e) {
				companyCatList = new ArrayList<>();
			}
			dataTraveller.setCompanyCatList(companyCatList);

			// 3
			List<GetFlavorTagStatusList> flavorTagStatusList = new ArrayList<>();
			try {
				flavorTagStatusList = feFlavTagStatusRepo.getFlavorTagStatusListByCompId(companyId);
			} catch (Exception e) {
				flavorTagStatusList = new ArrayList<>();
			}
			dataTraveller.setFlavorTagStatusList(flavorTagStatusList);

			// 4
			List<FEBannerList> franchiseBannerList = new ArrayList<>();
			try {
				// franchiseBannerList=feBannerRepo.getBannerListByFrId(frId);
			} catch (Exception e) {
				franchiseBannerList = new ArrayList<>();
			}
			dataTraveller.setFranchiseBannerList(franchiseBannerList);

			// 5
			List<CategoryList> franchiseCatList = new ArrayList<>();
			try {
				franchiseCatList = feCategoryListRepo.getFrCatListByFrId(frId);
			} catch (Exception e) {
				franchiseCatList = new ArrayList<>();
			}
			dataTraveller.setFranchiseCatList(franchiseCatList);

			// 6
			List<Franchise> frList = new ArrayList<>();
			try {
				frList = frRepo.findByCompanyIdAndDelStatusAndIsActiveOrderByFrIdDesc(companyId, 1, 1);
			} catch (Exception e) {
				frList = new ArrayList<>();
			}
			dataTraveller.setFrList(frList);

			// 7
			List<FETestimonial> testimonialList = new ArrayList<FETestimonial>();
			try {
				testimonialList = feTestimonialRepo.getFETestimonialListByCompId(companyId);
			} catch (Exception e) {
				testimonialList = new ArrayList<FETestimonial>();
			}
			dataTraveller.setTestimonialList(testimonialList);

			try {

				frList = frRepo.findByCompanyIdAndDelStatusAndIsActiveOrderByFrIdDesc(companyId, 1, 1);

				if (frList == null) {
					frList = new ArrayList<>();
				}

			} catch (Exception e) {

				frList = new ArrayList<>();
			}

			List<FEProductHeader> prodHeaderList = new ArrayList<FEProductHeader>();
			List<FEProdDetail> prodDetailList = null;

			try {

				prodHeaderList = feProdHeadRepo.getFEProductHeaderByFrId(frId);
				if (prodHeaderList == null) {

				}
				if (prodHeaderList.isEmpty()) {

				} else {

					// System.err.println(prodHeaderList.toString());
					dataTraveller.setFeProductHeadList(prodHeaderList);

					List<Integer> homePageProdIdsList = feProdHeadRepo.getHomePageConfiguredProdIdsList(companyId);

					// System.err.println(homePageProdIdsList.toString());

					for (int a = 0; a < homePageProdIdsList.size(); a++) {

						for (int i = 0; i < prodHeaderList.size(); i++) {
							Integer isSame = Integer.compare((int) homePageProdIdsList.get(a),
									prodHeaderList.get(i).getProductId());
							if (isSame.equals(0)) {
								prodHeaderList.get(i).setIsHomePageProd(1);
								break;
							}
						} // End of For Loop prodHeaderList I.

					} // End of For Loop homePageProdIdsList A.

					for (int i = 0; i < prodHeaderList.size(); i++) {

						prodDetailList = new ArrayList<FEProdDetail>();
						try {
							prodDetailList = feProdDetailRepo.getFEProdDetailByConfHeadProdIdFrId(
									prodHeaderList.get(i).getConfigHeaderId(), prodHeaderList.get(i).getProductId(),
									frId);
						} catch (Exception e) {

						}

						if (prodDetailList != null) {
							prodHeaderList.get(i).setProdDetailList(prodDetailList);
						}

					} // End of For Loop prodHeaderList I.

				} // End of else.

			} catch (

			Exception e) {
				System.err.println("in main Exception");
				prodHeaderList = new ArrayList<FEProductHeader>();
			}
			System.err.println("runtime fm " + runtime.getRuntime().freeMemory() / MB);
			ObjectMapper Obj = new ObjectMapper();
			String json = "";
			try {
				json = Obj.writeValueAsString(dataTraveller);
			} catch (Exception e) {
				e.printStackTrace();
			}
			publishData(json, frId, w);
			dataTravellerList.add(dataTraveller);
		}
		ObjectMapper obj = new ObjectMapper();
		try {
			publishData(obj.writeValueAsString(dataTravellerList), frId, 1000);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataTravellerList;

	}

	public void publishData(String json, int frId, int i) {

		//final String JSON_SAVE_URL = "/home/ubuntu/Documents/apache-tomcat-8.51.38/webapps/IMG_UP/";
		final String JSON_SAVE_URL = "/opt/apache-tomcat-8.5.37/webapps/PROD_IMG_UP/"; 
		/*
		 * ObjectMapper Obj = new ObjectMapper(); //String json = ""; try { json =
		 * Obj.writeValueAsString(allData); } catch (IOException e1) {
		 * e1.printStackTrace(); }
		 */
		if (json != null) {

			try {
				Writer output = null;
				File file = new File(
						JSON_SAVE_URL + i + "_" + frId + "_" + CommonUtility.getCurrentYMDDateTime() + ".json");
				output = new BufferedWriter(new FileWriter(file));
				output.write(json.toString());
				output.close();

				String fileName = JSON_SAVE_URL + frId + "_" + CommonUtility.getCurrentYMDDateTime() + ".zip";
				String sourceFile = JSON_SAVE_URL + i + "_" + frId + "_" + CommonUtility.getCurrentYMDDateTime()
						+ ".json";
				FileOutputStream fos = new FileOutputStream(fileName);
				ZipOutputStream zipOut = new ZipOutputStream(fos);
				File fileToZip = new File(sourceFile);
				FileInputStream fis = new FileInputStream(fileToZip);
				ZipEntry zipEntry = new ZipEntry(fileToZip.getName());

				zipOut.putNextEntry(zipEntry);
				byte[] bytes = new byte[4096];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zipOut.write(bytes, 0, length);
				}
				zipOut.close();
				fis.close();
				fos.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
