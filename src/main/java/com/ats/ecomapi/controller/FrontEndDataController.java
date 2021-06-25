package com.ats.ecomapi.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.ats.ecomapi.fe_model.FrSubCatList;
import com.ats.ecomapi.fe_model.GetFlavorTagStatusList;
import com.ats.ecomapi.fe_model.HomePageStatusHead;
import com.ats.ecomapi.fe_model.ProdListExl;
import com.ats.ecomapi.fe_model.SiteVisitor;
import com.ats.ecomapi.fe_repo.CategoryListRepo;
import com.ats.ecomapi.fe_repo.FEBannerListRepo;
import com.ats.ecomapi.fe_repo.FEProdDetailRepo;
import com.ats.ecomapi.fe_repo.FEProductHeaderRepo;
import com.ats.ecomapi.fe_repo.FETestimonialRepo;
import com.ats.ecomapi.fe_repo.FrSubCatListRepo;
import com.ats.ecomapi.fe_repo.GetFlavorTagStatusListRepo;
import com.ats.ecomapi.fe_repo.HomePageStatusHeadRepo;
import com.ats.ecomapi.fe_repo.ProdListExlRepo;
import com.ats.ecomapi.fe_repo.SiteVisitorRepo;
import com.ats.ecomapi.master.model.City;
import com.ats.ecomapi.master.model.CompanyTestomonials;
import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.master.model.Setting;
import com.ats.ecomapi.master.repo.CityRepo;
import com.ats.ecomapi.master.repo.CompanyTestomonialsRepo;
import com.ats.ecomapi.master.repo.FranchiseRepo;
import com.ats.ecomapi.master.repo.RelatedProductConfigRepo;
import com.ats.ecomapi.master.repo.SettingRepo;
import com.ats.ecomapi.mst_model.CateFilterConfig;
import com.ats.ecomapi.mst_model.Customer;
import com.ats.ecomapi.mst_model.CustomerAddDetail;
import com.ats.ecomapi.mst_model.FestiveEvent;
import com.ats.ecomapi.mst_model.GetRelatedProductConfig;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_repo.CateFilterConfigRepo;
import com.ats.ecomapi.mst_repo.CustomerAddDetailRepo;
import com.ats.ecomapi.mst_repo.CustomerRepo;
import com.ats.ecomapi.mst_repo.FestiveEventRepo;
import com.ats.ecomapi.mst_repo.GetRelatedProductConfigRepo;
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

	// Sachin 26-11-2020
	@Autowired
	CateFilterConfigRepo catFilterConfRepo;

	@Autowired
	GetRelatedProductConfigRepo getRelatedProductConfigRepo;

	@Autowired
	CompanyTestomonialsRepo companyTestomonialsRepo;
	//Sachin 22-12-2020
	@Autowired FrSubCatListRepo frSubcatListRepo;
	
	//Sachin 06-01-2021
	@Autowired CustomerRepo custRepo;
	@Autowired CustomerAddDetailRepo custAddDetailRepo;
	//Sachin 06-01-2021
	@RequestMapping(value = { "/getCustomerByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody Object getCustomerByMobNo(@RequestParam("mobNo") String mobNo) {
		
		Customer cust=new  Customer();
		try {
			cust=custRepo.findLastByCustIdCustMobileNoAndDelStatusAndIsActive(mobNo.trim());
			if(cust==null) {
				System.err.println("Its null ");
			}else {
				System.err.println("Its cust "+cust);
				CustomerAddDetail addDetail=custAddDetailRepo.getAddDetailByCustId(cust.getCustId());
				return addDetail;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cust;
		
	}
	@Autowired SiteVisitorRepo siteVistRepo;
	@RequestMapping(value = { "/saveSiteVisitor" }, method = RequestMethod.POST)
	public @ResponseBody Object saveSiteVisitor(@RequestBody SiteVisitor inputVisitor) {
		SiteVisitor visitor=siteVistRepo.save(inputVisitor);
		return visitor;
	}
	@Autowired HomePageStatusHeadRepo feHomePageStatusHeadRepo; //SAC 23-06-2021
	@RequestMapping(value = { "/generateFrDataJSON" }, method = RequestMethod.POST)
	public @ResponseBody Object getProdDataForFranchise(@RequestParam("frIdList") List<Integer> frIdList,
			@RequestParam("companyId") int companyId) {

		Runtime runtime = Runtime.getRuntime();
		int MB = 1024 * 1024;
		List<FEDataTraveller> dataTravellerList = new ArrayList<>();

		// for (int w = 0; w < 300; w++) {
		for (Integer frId : frIdList) {
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
			try {
				List<Integer> homePageStatusIdList=new ArrayList<Integer>();
				homePageStatusIdList=feFlavTagStatusRepo.getHomePageStatusIds(companyId);
				if(homePageStatusIdList!=null) {
					for(int k=0;k<homePageStatusIdList.size();k++) {
						for(int i=0;i<flavorTagStatusList.size();i++) {
							if(flavorTagStatusList.get(i).getFilterTypeId()==5) {
								if(flavorTagStatusList.get(i).getFilterId()==homePageStatusIdList.get(k)) {
								flavorTagStatusList.get(i).setCostAffect(1);
								break;
							}
						}else {
							continue;
						}
					}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
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

				} else if (prodHeaderList.isEmpty()) {

				} else {
					// System.err.println(prodHeaderList.toString());
					// dataTraveller.setFeProductHeadList(prodHeaderList);

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
System.err.println("prodHeaderList "+prodHeaderList.toString());
					for (int i = 0; i < prodHeaderList.size(); i++) {
						
						String	pName = prodHeaderList.get(i).getProductName().replace(' ', '-');    
						prodHeaderList.get(i).setProdNameDisp(pName);
						float defaultPrice = 0;
						prodDetailList = new ArrayList<FEProdDetail>();
						try {
							prodDetailList = feProdDetailRepo.getFEProdDetailByConfHeadProdIdFrId(
									prodHeaderList.get(i).getConfigHeaderId(), prodHeaderList.get(i).getProductId(),
									frId);
							if (prodHeaderList.get(i).getProductId() == 86)
								System.err.println("prodDetailList 86 " + prodDetailList.toString());
						} catch (Exception e) {

						}

						if (prodDetailList != null) {
							prodHeaderList.get(i).setProdDetailList(prodDetailList);
							Integer isVegNonVMatch = 0;

							for (int d = 0; d < prodDetailList.size(); d++) {

								isVegNonVMatch = Integer.compare(prodHeaderList.get(i).getDefaultFlavorId(),
										prodDetailList.get(d).getFlavorId());
									if(prodDetailList.get(0).getQty()>1 && isVegNonVMatch.equals(0)) {
										
										defaultPrice = prodDetailList.get(d).getActualRate();
										if (prodHeaderList.get(i).getProductId() == 86)
											System.err.println("defaultPrice 86 prodId If " + defaultPrice);
									}
									else if (isVegNonVMatch.equals(0) && prodDetailList.get(d).getQty() <= 1) {
									defaultPrice = prodDetailList.get(d).getActualRate();
									if (prodHeaderList.get(i).getProductId() == 86)
										System.err.println("defaultPrice Else" + defaultPrice);
									
									break;
								}
									if (prodHeaderList.get(i).getProductId() == 86)
										System.err.println("defaultPrice " + defaultPrice);
							} // End of For D prodDetailList Loop
						}
						prodHeaderList.get(i).setDefaultPrice(defaultPrice);

						// Sachin 28-11-2020
						FEProductHeader prodH = prodHeaderList.get(i);
						prodHeaderList.get(i)
								.setAllFilterNames(prodH.getSameDayTimeSlotNames() + "," + prodH.getEventNames() + ","
										+ prodH.getFlavorNames() + "," + prodH.getAppliTagNames() + ","
										+ prodH.getShapeNames() + "," + prodH.getProdTypeName() + ","
										+ prodH.getProdStatusName() + "," + prodH.getToppingCreamNames() + ","
										+ prodH.getLayeringCreamNames() + "," + prodH.getCreamTypeName() + ","
										+ prodH.getBreadTypeName() + "," + prodH.getVegNonvegName());
						// Close Sachin 28-11-2020

					} // End of For Loop prodHeaderList I.

				} // End of else.

			} catch (

			Exception e) {
				System.err.println("in main Exception");

				e.printStackTrace();

				prodHeaderList = new ArrayList<FEProductHeader>();
			}
			dataTraveller.setFeProductHeadList(prodHeaderList);

			
			
			
			// System.err.println("runtime fm " + runtime.getRuntime().freeMemory() / MB);
			List<FestiveEvent> festEventList = new ArrayList<>();
			try {
				festEventList = festEventRepo.findByCompIdAndDelStatusAndIsActiveOrderByEventIdDesc(companyId, 1, 1);
			} catch (Exception e) {
				festEventList = new ArrayList<>();
			}
			dataTraveller.setFestEventList(festEventList);

			// Sachin 26-11-2020
			List<CateFilterConfig> catFilterConfig = new ArrayList<>();
			try {
				catFilterConfig = catFilterConfRepo.getCompanyCateFilterConf(companyId);
			} catch (Exception e) {
				catFilterConfig = new ArrayList<>();
			}
			dataTraveller.setCatFilterConfig(catFilterConfig);
			// end
			
			// 5.5 Sachin 22-12-2020
			List<FrSubCatList> frSubCatList = new ArrayList<>();
			try {
				frSubCatList = frSubcatListRepo.getFrSubCatList(frId);
			} catch (Exception e) {
				frSubCatList = new ArrayList<>();
			}
			dataTraveller.setFrSubCatList(frSubCatList);

			//SAC 23-06-2021
			List<HomePageStatusHead> homePageStatusList=new ArrayList<HomePageStatusHead>();
			try {
				homePageStatusList=feHomePageStatusHeadRepo.getHomePageProductConfigList(companyId);
			}catch (Exception e) {
				homePageStatusList=new ArrayList<HomePageStatusHead>();
			}
			dataTraveller.setHomePageStatusList(homePageStatusList);
			//SAC 23-06-2021 END
			ObjectMapper Obj = new ObjectMapper();
			String json = "";
			try {
				json = Obj.writeValueAsString(dataTraveller);
			} catch (Exception e) {
				e.printStackTrace();
			}
			publishData(json, frId, 1);
			
			
			
			dataTravellerList.add(dataTraveller);
		
		} //End of FrId For Loop
		
	
					
		ObjectMapper obj = new ObjectMapper();
		try {
			List<City> cityList = cityRepo.findByDelStatusAndIsActiveOrderByCityIdDesc(1, 1);
			publishData(obj.writeValueAsString(cityList), 0, 3);

			try {
				List<Franchise> frList = frRepo.findByDelStatusAndIsActiveOrderByFrIdDesc(1, 1);
				publishData(obj.writeValueAsString(frList), 0, 2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<CategoryList> masterCompanyCatList = new ArrayList<>();
			try {
				masterCompanyCatList = feCategoryListRepo.getMasterCompCatList();
				System.err.println("CAT " +masterCompanyCatList.toString());
			
			} catch (Exception e) {
				masterCompanyCatList = new ArrayList<>();
			}
			try {
				publishData(obj.writeValueAsString(masterCompanyCatList), 0, 4);
			} catch (Exception e) {
				e.printStackTrace();
			}

			List<FETestimonial> masterTestimonialList = new ArrayList<FETestimonial>();
			try {
				masterTestimonialList = feTestimonialRepo.getFETestimonialListByCompId(1);// for default company Id 1
																							// for master company.
			} catch (Exception e) {
				masterTestimonialList = new ArrayList<FETestimonial>();
			}

			try {
				//publishData(obj.writeValueAsString(masterTestimonialList), 0, 5);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		
			

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return dataTravellerList.get(0).getFeProductHeadList();
		//return prodHeaderList;


	}

	@Autowired
	CityRepo cityRepo;
	@Autowired
	FestiveEventRepo festEventRepo;

//Sachin 26-10-2020
	@RequestMapping(value = { "/getFrListByCityIds" }, method = RequestMethod.POST)
	public @ResponseBody List<Franchise> getFrListByCityIds(@RequestParam("cityIdList") List<Integer> cityIdList) {

		List<Franchise> frList = new ArrayList<Franchise>();
		try {
			frList = frRepo.findByDelStatusAndIsActiveAndFrCityInOrderByFrIdDesc(1, 1, cityIdList);
		} catch (Exception e) {
			frList = new ArrayList<Franchise>();
		}

		return frList;

	}

	// Sachin 26-10-2020
	@Autowired
	RelatedProductConfigRepo relatedProdConfRepo;

	@RequestMapping(value = { "/getRelateProductByProductIds" }, method = RequestMethod.POST)
	public @ResponseBody List<Integer> getRelateProductByProductIds(@RequestParam("compId") int compId,
			  @RequestParam("itemIds") List<Integer> itemIds) {
		List<Integer> relatedProdIdList = new ArrayList<Integer>();

		try {

			List<GetRelatedProductConfig> proList = new ArrayList<GetRelatedProductConfig>();

			try {
				proList = getRelatedProductConfigRepo.getCRelatedProductConfig(compId);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Set<Integer> idSet = new HashSet<Integer>();

			if (proList != null) {

				if (itemIds != null) {

					for (int i : itemIds) {

						for (GetRelatedProductConfig item : proList) {
							if (item.getSecondaryItemId().contains(String.valueOf(i))) {
								idSet.add(item.getPrimaryItemId());
							}
						}

					}

				}

			}

			relatedProdIdList.addAll(idSet);
			// relatedProdIdList = feProdHeadRepo.getRelatedProdIds(prodId);
		} catch (Exception e) {
			relatedProdIdList = new ArrayList<Integer>();
		}
		return relatedProdIdList;
	}
	
	@RequestMapping(value = { "/generateCompTestimonialJson" }, method = RequestMethod.GET)	
	public @ResponseBody Info generateCompTestimonialJson(@RequestParam int compId) {
		Info info = new Info();
		List<CompanyTestomonials> masterCompTestimonialList = new ArrayList<CompanyTestomonials>();
		try {
			masterCompTestimonialList = companyTestomonialsRepo.getCompanyTestomonialsList(compId);
			if(masterCompTestimonialList!=null) {
				info.setError(false);
				info.setMessage("Company Testimonial Json Generated");
			}else {
				info.setError(true);
				info.setMessage("Unable To Company Generated Testimonial Json");
			}
		} catch (Exception e) {
			masterCompTestimonialList = new ArrayList<CompanyTestomonials>();
		}			
		try {
			ObjectMapper obj = new ObjectMapper();
			publishData(obj.writeValueAsString(masterCompTestimonialList), 0, 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return info;
	}
	@Autowired SettingRepo settingRepo;

	public void publishData(String json, int frId, int fileType) {
		Setting setting=settingRepo.findBySettingKey("JSON_SAVE_PATH");
		
		//Setting setting=settingRepo.findBySettingKey("	");
		 String JSON_SAVE_URL = setting.getSettingValue();//"/home/ubuntu/Documents/apache-tomcat-8.51.38/webapps/IMG_UP/";
		 JSON_SAVE_URL="/home/ubuntu/Documents/apache-tomcat-8.51.38/";
//		 final String JSON_SAVE_URL = 
//		 "/opt/apache-tomcat-8.5.39/webapps/IMG_UP/";
System.err.println("current path from setting is " + JSON_SAVE_URL);
		if (json != null) {

			try {
				Writer output = null;
				File file = null;
				if (fileType == 1) {
					file = new File(JSON_SAVE_URL + frId + "_" + ".json");
				} else if (fileType == 2) {
					// Save All Fr JSON
					file = new File(JSON_SAVE_URL + "AllFrData" + "_" + ".json");
				} else if (fileType == 3) {
					// Save All City JSON
					file = new File(JSON_SAVE_URL + "AllCityData" + "_" + ".json");
				} else if (fileType == 4) {
					// Save All Category JSON
					file = new File(JSON_SAVE_URL + "MasterCategoryData" + "_" + ".json");
				} else if (fileType == 5) {
					// Save All Testimonial JSON
					file = new File(JSON_SAVE_URL + "MasterTestimonialData" + "_" + ".json");
				} else if (fileType == 6) {
					// Save All Company Testimonial JSON
					//file = new File(JSON_SAVE_URL + "MasterCompanyTestimonialData" + "_" + ".json");
					file = new File(JSON_SAVE_URL + "MasterTestimonialData" + "_" + ".json");
				}

				output = new BufferedWriter(new FileWriter(file));
				output.write(json.toString());
				output.close();

				/*
				 * String fileName = JSON_SAVE_URL + frId + "_" + ".zip"; String sourceFile =
				 * JSON_SAVE_URL +"_" + frId + "_" + ".json"; FileOutputStream fos = new
				 * FileOutputStream(fileName); ZipOutputStream zipOut = new
				 * ZipOutputStream(fos); File fileToZip = new File(sourceFile); FileInputStream
				 * fis = new FileInputStream(fileToZip); ZipEntry zipEntry = new
				 * ZipEntry(fileToZip.getName());
				 * 
				 * zipOut.putNextEntry(zipEntry); byte[] bytes = new byte[4096]; int length;
				 * while ((length = fis.read(bytes)) >= 0) { zipOut.write(bytes, 0, length); }
				 * zipOut.close(); fis.close(); fos.close();
				 */

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	
	@Autowired ProdListExlRepo prdExlRepo; 
	@RequestMapping(value = { "/getProductListExcl" }, method = RequestMethod.POST)
	public @ResponseBody List<ProdListExl> getProductListExcl(@RequestParam int compId) {
		List<ProdListExl> list = new ArrayList<ProdListExl>();
		try {
			list = prdExlRepo.getProductListForPdfExcel(compId);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		
		return list;
	}
}
