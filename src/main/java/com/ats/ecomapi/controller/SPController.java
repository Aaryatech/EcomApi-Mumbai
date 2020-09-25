package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.common.CommonUtility;
import com.ats.ecomapi.master.model.MFilter;
import com.ats.ecomapi.master.repo.GetItemConfHeadRepo;
import com.ats.ecomapi.master.repo.GetProdListRepo;
import com.ats.ecomapi.master.repo.GetSubCatPrefixRepo;
import com.ats.ecomapi.master.repo.MFilterRepo;
import com.ats.ecomapi.mst_model.GetItemConfHead;
import com.ats.ecomapi.mst_model.GetProdList;
import com.ats.ecomapi.mst_model.GetSubCatPrefix;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.ItemConfDetail;
import com.ats.ecomapi.mst_model.ItemConfHeader;
import com.ats.ecomapi.mst_model.ProductMaster;
import com.ats.ecomapi.mst_model.TempConfTraveller;
import com.ats.ecomapi.mst_model.TempProdConfig;
import com.ats.ecomapi.mst_model.User;
import com.ats.ecomapi.mst_repo.ItemConfDetailRepo;
import com.ats.ecomapi.mst_repo.ItemConfHeaderRepo;
import com.ats.ecomapi.mst_repo.ProductMasterRepo;
import com.ats.ecomapi.mst_repo.TempProdConfigRepo;
import com.ats.ecomapi.mst_repo.UserRepo;

@RestController
public class SPController {

	@Autowired
	RateVerificationRepo rateVerRepo;

	@RequestMapping(value = { "/getData" }, method = RequestMethod.POST)
	public @ResponseBody List<GetMrnQueryItem> getData(@RequestParam String fromDate, @RequestParam int itemId) {

		return rateVerRepo.getMrnQueryItem2(fromDate, itemId);

	}

	@Autowired
	UserRepo userRepo;

	@RequestMapping(value = { "/checkUserNamePassForLogin" }, method = RequestMethod.POST)
	public @ResponseBody Object checkUserNameForLogin(@RequestParam String userName, @RequestParam String pass) {
		Info info = new Info();
		User loginUser = new User();
		int userId = 0;
		try {
			// Check if user name exists
			// User name should be case in sensitive
			try {
				userId = userRepo.getUserId(userName.trim());
			} catch (Exception e) {
				info.setError(true);
				info.setMsg("Invalid User name ");
			}

			if (userId != 0) {
				try {
					loginUser = userRepo.findByUserId(userId);
				} catch (Exception e) {
					info.setError(true);
					e.printStackTrace();
				}
				if (loginUser != null) {

					if (pass.trim().equals(loginUser.getPassword().trim())) {
						// password matched.
						info.setError(false);
						info.setMsg("Login Successful ");
						info.setResponseObject1(CommonUtility.toJSONString(loginUser));
						return info;
					} else {
						// password not matched.
						info.setError(true);
						info.setMsg("Invalid password ");
						return info;
					}
				}
			} else {
				info.setError(true);
				info.setMsg("Invalid user name ");
				return info;
			}

		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMsg("Exception Occurred  ");
		}

		return info;

	}

	@Autowired
	ProductMasterRepo productMasterRepo;

	// saveProduct
	/*****************************
	 * //Created Date: 16-09-2020 //UpdateDate:16-09-2020 //Description: to submit
	 * Product (Save) //Devloped By(Devloper Name): Sachin //Updated By(Devloper
	 * Name): Sachin
	 ******************************/
	@RequestMapping(value = { "/saveProduct" }, method = RequestMethod.POST)
	public @ResponseBody Object saveProduct(@RequestBody ProductMaster prod) {
		ProductMaster prodMasterRes = new ProductMaster();
		try {
			prodMasterRes = productMasterRepo.save(prod);
			if (prodMasterRes == null) {
				prodMasterRes = new ProductMaster();
			}
		} catch (Exception e) {
			prodMasterRes = new ProductMaster();
			System.err.println("In Exception");
			e.printStackTrace();
		}

		return prodMasterRes;
	}

	@Autowired
	GetSubCatPrefixRepo getSubCatPrefix;

	/*****************************
	 * //Created Date: 16-09-2020 //UpdateDate:16-09-2020 //Description: to submit
	 * Product (Save) //Devloped By(Devloper Name): Sachin //Updated By(Devloper
	 * Name): Sachin
	 ******************************/
	@RequestMapping(value = { "/getSubCatPrefix" }, method = RequestMethod.POST)
	public @ResponseBody GetSubCatPrefix getSubCatPrefix(@RequestParam int subCatId) {
		GetSubCatPrefix subCatPrefix = new GetSubCatPrefix();

		try {
			subCatPrefix = getSubCatPrefix.getSubCatPrefix(subCatId);
		} catch (Exception e) {
			subCatPrefix = new GetSubCatPrefix();
		}
		return subCatPrefix;
	}

	@Autowired
	GetProdListRepo getProdListRepo;

	/*****************************
	 * //Created Date: 17-09-2020 //UpdateDate:17-09-2020 //Description: to show
	 * list of products //Devloped By(Devloper Name): Sachin //Updated By(Devloper
	 * Name): Sachin
	 ******************************/
	@RequestMapping(value = { "/getProdList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetProdList> getProdList(@RequestParam int compId) {

		List<GetProdList> prodList = new ArrayList<GetProdList>();

		try {
			prodList = getProdListRepo.getProdList(compId);
			if (prodList == null || prodList.isEmpty()) {
				prodList = new ArrayList<GetProdList>();
			} else {
			}

		} catch (Exception e) {
			prodList = new ArrayList<GetProdList>();
		}

		return prodList;
	}

	// Sachin 17-09-2020
	@Autowired
	MFilterRepo filterRepo;

	List<MFilter> filterList = new ArrayList<MFilter>();

	@RequestMapping(value = { "/getProdConf" }, method = RequestMethod.POST)
	public @ResponseBody String getProdConf(@RequestParam int compId, @RequestParam int catId) {

		filterList = new ArrayList<MFilter>();
		try {
			// Get Filter By Comp Id and Filter type ie 4 for Flavor
			filterList = filterRepo.getFiltersByFilterId(compId, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<ProductMaster> prodList = productMasterRepo.findByProdCatIdAndDelStatusAndCompanyId(catId, 1, compId);

			for (int i = 0; i < prodList.size(); i++) {
				List<Integer> vegNonVegList = new ArrayList<>();

				if (prodList.get(i).getIsVeg() == 2) {

					vegNonVegList.add(0);
					vegNonVegList.add(1);

				} else {

				}

				List<TempProdConfig> tempProdConfList = new ArrayList<>();
				System.err.println("prod Id " + prodList.get(i).getProductId());

				List<String> prodFlavIdList = Arrays.asList(prodList.get(i).getFlavourIds().split(",", -1));

				System.err.println("prodFlavIdList " + prodFlavIdList.toString());

				List<MFilter> flavList = getFlavList(prodFlavIdList);

				System.err.println("flavList " + flavList.toString());

				if (!flavList.isEmpty()) {

					if (flavList.size() > 0) {

						if (prodList.get(i).getRateSettingType() == 2) {

							// By weight ids;

							List<String> wtList = Arrays.asList(prodList.get(i).getAvailInWeights().split(",", -1));

							System.err.println("Weight List " + wtList.toString());

							for (int p = 0; p < flavList.size(); p++) {

								for (int x = 0; x < wtList.size(); x++) {

									if (vegNonVegList.size() == 2) {
										// Create Bean and Assign Values
										for (int y = 0; y < vegNonVegList.size(); y++) {
											TempProdConfig config = new TempProdConfig();
											UUID uuid = UUID.randomUUID();
											config.setUuid(uuid.toString());

											config.setCatId(prodList.get(i).getProdCatId());
											config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
											config.setFlavorId(flavList.get(p).getFilterId());
											config.setFlavorName(flavList.get(p).getFilterName());
											config.setProductId(prodList.get(i).getProductId());
											config.setProductName(prodList.get(i).getProductName());
											config.setRateSetingType(prodList.get(i).getRateSettingType());

											config.setVegType(vegNonVegList.get(y));

											config.setWeight(Float.parseFloat(wtList.get(x)));
											tempProdConfList.add(config);
										} // end of vegNonVegList Y for

									} // end of if vegNonVegList.size()==2
									else {
										TempProdConfig config = new TempProdConfig();

										UUID uuid = UUID.randomUUID();
										config.setUuid(uuid.toString());

										config.setCatId(prodList.get(i).getProdCatId());
										config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
										config.setFlavorId(flavList.get(p).getFilterId());
										config.setFlavorName(flavList.get(p).getFilterName());
										config.setProductId(prodList.get(i).getProductId());
										config.setProductName(prodList.get(i).getProductName());
										config.setRateSetingType(prodList.get(i).getRateSettingType());

										config.setVegType(prodList.get(i).getIsVeg());

										config.setWeight(Float.parseFloat(wtList.get(x)));
										tempProdConfList.add(config);

									} // else part of if vegNonVegList.size()==2
								} // end of wtList x for Loop

							} // end of flavList P for Loop

						} // End of If getRateSettingType==2

						else {
							// by UOM or Kg ie constant 1 Qty

							for (int p = 0; p < flavList.size(); p++) {
								// Create Bean and Assign Values

								if (vegNonVegList.size() == 2) {
									// Create Bean and Assign Values
									for (int y = 0; y < vegNonVegList.size(); y++) {
										TempProdConfig config = new TempProdConfig();

										UUID uuid = UUID.randomUUID();
										config.setUuid(uuid.toString());

										config.setCatId(prodList.get(i).getProdCatId());
										config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
										config.setFlavorId(flavList.get(p).getFilterId());
										config.setFlavorName(flavList.get(p).getFilterName());
										config.setProductId(prodList.get(i).getProductId());
										config.setProductName(prodList.get(i).getProductName());
										config.setRateSetingType(prodList.get(i).getRateSettingType());

										config.setVegType(vegNonVegList.get(y));

										config.setWeight(1);
										tempProdConfList.add(config);
									} // end of vegNonVegList Y for
								} // end of vegNonVegList.size()==2
								else {

									TempProdConfig config = new TempProdConfig();

									UUID uuid = UUID.randomUUID();
									config.setUuid(uuid.toString());

									config.setCatId(prodList.get(i).getProdCatId());
									config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
									config.setFlavorId(flavList.get(p).getFilterId());
									config.setFlavorName(flavList.get(p).getFilterName());
									config.setProductId(prodList.get(i).getProductId());
									config.setProductName(prodList.get(i).getProductName());
									config.setRateSetingType(prodList.get(i).getRateSettingType());

									config.setVegType(prodList.get(i).getIsVeg());

									config.setWeight(1);
									tempProdConfList.add(config);
								}
							} // end of flavList P for Loop

						} // End of else

					} // end of If flavList size >0

				} // end of if !flavList.isEmpty
				prodList.get(i).setTempProdConfList(tempProdConfList);
			}
			System.err.println("prodList 0 " + prodList.get(0).getTempProdConfList().toString());
			System.err.println("prodList 1 " + prodList.get(1).getTempProdConfList().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sachin";

	}

	public List<MFilter> getFlavList(List<String> flavIds) {
		List<MFilter> flavList = new ArrayList<MFilter>();

		try {
			for (int a = 0; a < flavIds.size(); a++) {
				for (int i = 0; i < filterList.size(); i++) {
					Integer isSame = null;
					isSame = Integer.compare(Integer.parseInt(flavIds.get(a)), filterList.get(i).getFilterId());
					if (isSame.equals(0)) {
						flavList.add(filterList.get(i));
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flavList;
	}

	// get All Prod By Cat Id CompId
	// Sachin 18-09-2020

	@RequestMapping(value = { "/getProdListByCatIdCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getProdListByCatIdCompId(@RequestParam("catId") int catId,
			@RequestParam("compId") int compId) {
		List<ProductMaster> proList = new ArrayList<ProductMaster>();

		try {
			
			proList = productMasterRepo.findByProdCatIdAndDelStatusAndCompanyId(catId, 1, compId);
			
			if (proList == null) {
				proList = new ArrayList<ProductMaster>();
			}
			
		} catch (Exception e) {
			proList = new ArrayList<ProductMaster>();
			e.printStackTrace();
		}
		return proList;
	}

	// get All Prod By Cat Id CompId
	// Sachin 24-09-2020
	@RequestMapping(value = { "/getProdListForAddingNewItemInExConf" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getProdListForAddingNewItemInExConf(@RequestParam("catId") int catId,
			@RequestParam("compId") int compId, @RequestParam("configId") int configId) {
		List<ProductMaster> prodList = new ArrayList<ProductMaster>();

		try {
			prodList = productMasterRepo.getProdListForAddingNewItemInExConf(catId, compId, configId);
			
			if(prodList==null) {
				prodList = new ArrayList<ProductMaster>();
			}
		} catch (Exception e) {
			prodList = new ArrayList<ProductMaster>();
			e.printStackTrace();
		}
		return prodList;
	}

	// saveProdConfHD
	// Sachin 21-09-2020
	//Desc- To save Product Conf Header and details
	@Autowired
	ItemConfHeaderRepo itemConfHeaderRepo;
	@Autowired
	ItemConfDetailRepo itemConfDetailRepo;

	@RequestMapping(value = { "/saveProdConfHD" }, method = RequestMethod.POST)
	public @ResponseBody Object saveProdConfHD(@RequestBody ItemConfHeader confHeaderInput) {
		ItemConfHeader confHeader = new ItemConfHeader();
		try {
			confHeader = itemConfHeaderRepo.save(confHeaderInput);
			if (confHeader == null) {
				confHeader = new ItemConfHeader();
			} else {

				List<ItemConfDetail> confDetList = confHeaderInput.getItemConfDetList();

				for (int i = 0; i < confDetList.size(); i++) {

					confDetList.get(i).setConfigHeaderId(confHeader.getConfigHeaderId());
				}

				List<ItemConfDetail> detailSaveRes = itemConfDetailRepo.saveAll(confDetList);
			}

		} catch (Exception e) {
			confHeader = new ItemConfHeader();
			System.err.println("In Exception");
			e.printStackTrace();
		}

		return confHeader;
	}

	// Sachin 24-09-2020
	// Desc -Save new Items In Existing Product conf Header

	@RequestMapping(value = { "/saveNewItemToProdConf" }, method = RequestMethod.POST)
	public @ResponseBody Object saveNewItemToProdConf(@RequestBody List<ItemConfDetail> confDetList) {
		
		List<ItemConfDetail> detailSaveRes = new ArrayList<ItemConfDetail>();
		try {
			detailSaveRes = itemConfDetailRepo.saveAll(confDetList);
		
			if(detailSaveRes==null) {
				detailSaveRes = new ArrayList<ItemConfDetail>();
			}
			
		} catch (Exception e) {
			detailSaveRes = new ArrayList<ItemConfDetail>();
			e.printStackTrace();
		}

		return detailSaveRes;
	}

	// Sachin 21-09-2020
	// Desc-Web Service to get Prod Conf Header by company and Cat Id(s)
	// Updated On 21-09-2020
	// Updated By Sachin
	@Autowired
	GetItemConfHeadRepo getItemConfHeadRepo;

	@RequestMapping(value = { "/getProdConfList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetItemConfHead> getProdConfList(@RequestParam int companyId,
			@RequestParam List<Integer> catIdList) {

		List<GetItemConfHead> confHeaderList = new ArrayList<>();

		try {

			confHeaderList = getItemConfHeadRepo.getItemConfHeadListByCatId(catIdList, companyId);

			if(confHeaderList==null) {
				confHeaderList =new ArrayList<>();
			}
			
		} catch (Exception e) {
			confHeaderList = new ArrayList<>();
			e.printStackTrace();

		}

		return confHeaderList;
	}

	// Get Prod Conf Detail for Edit

	// Sachin 21-09-2020
	// Desc-Web Service to get Prod Conf detail By confHeaderId for edit
	// Updated On 21-09-2020
	// Updated By Sachin
	@Autowired
	TempProdConfigRepo getProdConfDetail;

	@RequestMapping(value = { "/getProdConfDetailByConfHeader" }, method = RequestMethod.POST)
	public @ResponseBody TempConfTraveller getProdConfDetailByConfHeader(@RequestParam int configHeaderId,
			@RequestParam int companyId) {

		TempConfTraveller traveller = new TempConfTraveller();

		List<TempProdConfig> prodConfDetailList = new ArrayList<>();
		List<TempProdConfig> tempProdConfList = new ArrayList<>();

		GetItemConfHead confHead = getItemConfHeadRepo.getProdConfHeaderByConfHeadId(configHeaderId);
		traveller.setConfHead(confHead);

		filterList = new ArrayList<MFilter>();
		try {
			// Get Filter By Comp Id and Filter type ie 4 for Flavor
			filterList = filterRepo.getFiltersByFilterId(companyId, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			List<Integer> prodcutIdList = new ArrayList<Integer>();
			prodConfDetailList = getProdConfDetail.getProdConfByConfHeaderId(configHeaderId);
			traveller.setProdConfDetailList(prodConfDetailList);

			for (int i = 0; i < prodConfDetailList.size(); i++) {
				prodcutIdList.add(prodConfDetailList.get(i).getProductId());
			}

			Set set = new HashSet<>();
			set.addAll(prodcutIdList);
			prodcutIdList.clear();

			prodcutIdList.addAll(set);
			List<ProductMaster> prodList = productMasterRepo.findByProductIdIn(prodcutIdList);

			for (int i = 0; i < prodList.size(); i++) {

				ProductMaster pm = prodList.get(i);

				List<Integer> vegNonVegList = new ArrayList<>();

				if (pm.getIsVeg() == 2) {
					vegNonVegList.add(0);
					vegNonVegList.add(1);
				} // end of if (pm.getIsVeg() == 2)
				else {
					vegNonVegList.add(pm.getIsVeg());
				}

				List<String> prodFlavIdList = Arrays.asList(pm.getFlavourIds().split(",", -1));
				List<MFilter> flavList = getFlavList(prodFlavIdList);

				List<String> prodWtList = Arrays.asList(pm.getAvailInWeights().split(",", -1));

				for (int k = 0; k < flavList.size(); k++) {
					int flag = -1;
					MFilter flavor = new MFilter();
					for (int j = 0; j < prodConfDetailList.size(); j++) {

						Integer isProdMatch = Integer.compare(pm.getProductId(),
								prodConfDetailList.get(j).getProductId());

						if (isProdMatch.equals(0)) {
							flag = 0;
							flavor = flavList.get(k);

							Integer isFlavMatch = Integer.compare(flavor.getFilterId(),
									prodConfDetailList.get(j).getFlavorId());

							if (isFlavMatch.equals(0)) {
								flag = 1;
								break;
							} // end of if (isFlavMatch.equals(0))

						} // end of if (isProdMatch.equals(0))
					} // end of prodConfDetailList For Loop J

					if (flag == 0) {
						// Flavor Id not found in Product Detail
						if (pm.getRateSettingType() == 2) {
							// ie by weight Ids

							List<String> wtList = Arrays.asList(pm.getAvailInWeights().split(",", -1));

							for (int x = 0; x < wtList.size(); x++) {

								if (vegNonVegList.size() == 2) {

									for (int y = 0; y < vegNonVegList.size(); y++) {
										TempProdConfig config = new TempProdConfig();
										UUID uuid = UUID.randomUUID();
										config.setUuid(uuid.toString());

										config.setCatId(pm.getProdCatId());
										config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
										config.setFlavorId(flavor.getFilterId());
										config.setFlavorName(flavor.getFilterName());
										config.setProductId(pm.getProductId());
										config.setProductName(pm.getProductName());
										config.setRateSetingType(pm.getRateSettingType());

										config.setVegType(vegNonVegList.get(y));

										config.setWeight(Float.parseFloat(wtList.get(x)));
										tempProdConfList.add(config);
									} // end of for vegNonVegList Y
								} // end of if vegNonVegList.size() == 2
								else {

									TempProdConfig config = new TempProdConfig();

									UUID uuid = UUID.randomUUID();
									config.setUuid(uuid.toString());

									config.setCatId(pm.getProdCatId());
									config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
									config.setFlavorId(flavor.getFilterId());
									config.setFlavorName(flavor.getFilterName());
									config.setProductId(pm.getProductId());
									config.setProductName(pm.getProductName());
									config.setRateSetingType(pm.getRateSettingType());

									config.setVegType(pm.getIsVeg());

									config.setWeight(Float.parseFloat(wtList.get(x)));
									tempProdConfList.add(config);

								} // end of else if vegNonVegList.size() == 2

							} // end of wtList for X

						} // end of if(pm.getRateSettingType()==2)
						else {
							// ie as per UOM/Kg
							if (vegNonVegList.size() == 2) {

								for (int y = 0; y < vegNonVegList.size(); y++) {

									TempProdConfig config = new TempProdConfig();

									UUID uuid = UUID.randomUUID();
									config.setUuid(uuid.toString());

									config.setCatId(pm.getProdCatId());
									config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
									config.setFlavorId(flavor.getFilterId());
									config.setFlavorName(flavor.getFilterName());
									config.setProductId(pm.getProductId());
									config.setProductName(pm.getProductName());
									config.setRateSetingType(pm.getRateSettingType());

									config.setVegType(vegNonVegList.get(y));

									config.setWeight(1);
									tempProdConfList.add(config);
								} // end of for vegNonVegList Y
							} // end of if vegNonVegList.size() == 2
							else {

								TempProdConfig config = new TempProdConfig();

								UUID uuid = UUID.randomUUID();
								config.setUuid(uuid.toString());

								config.setCatId(pm.getProdCatId());
								config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
								config.setFlavorId(flavor.getFilterId());
								config.setFlavorName(flavor.getFilterName());
								config.setProductId(pm.getProductId());
								config.setProductName(pm.getProductName());
								config.setRateSetingType(pm.getRateSettingType());

								config.setVegType(pm.getIsVeg());

								config.setWeight(1);
								tempProdConfList.add(config);

							} // end of else if vegNonVegList.size() == 2

						} // end of else if(pm.getRateSettingType()==2)

					} // end of if flag==0

				} // End of flavList For Loop K

				// End of flavor logic

				// Start Logic with prodWtList

				for (int k = 0; k < prodWtList.size(); k++) {

					float wt = Float.parseFloat(prodWtList.get(k));

					int flag = -1;
					for (int j = 0; j < prodConfDetailList.size(); j++) {
						flag = 0;
						Integer isProdMatch = Integer.compare(pm.getProductId(),
								prodConfDetailList.get(j).getProductId());

						if (isProdMatch.equals(0)) {
							Integer isWtMatch = Float.compare(wt, prodConfDetailList.get(j).getWeight());
							System.err.println("Weight Result " + isWtMatch + "wt = " + wt + "prod WT "
									+ prodConfDetailList.get(j).getWeight());
							if (isWtMatch.equals(0)) {
								// Same weight
								flag = 1;
								break;
							}

						} // end of if (isProdMatch.equals(0))
					} // end of prodConfDetailList For J

					if (flag == 0) {
						// Insert Logic New Weight Found
						System.err.println("New Weight found " + wt);

						for (int a = 0; a < flavList.size(); a++) {
							MFilter flavor = new MFilter();
							flavor = flavList.get(a);
							if (vegNonVegList.size() == 2) {

								for (int y = 0; y < vegNonVegList.size(); y++) {

									TempProdConfig config = new TempProdConfig();

									UUID uuid = UUID.randomUUID();
									config.setUuid(uuid.toString());

									config.setCatId(pm.getProdCatId());
									config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
									config.setFlavorId(flavor.getFilterId());
									config.setFlavorName(flavor.getFilterName());
									config.setProductId(pm.getProductId());
									config.setProductName(pm.getProductName());
									config.setRateSetingType(pm.getRateSettingType());

									config.setVegType(vegNonVegList.get(y));

									config.setWeight(wt);
									tempProdConfList.add(config);

								} // end of vegNonVegList For Y

							} // end of if(vegNonVegList.size()==2)
							else {
								TempProdConfig config = new TempProdConfig();

								UUID uuid = UUID.randomUUID();
								config.setUuid(uuid.toString());

								config.setCatId(pm.getProdCatId());
								config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
								config.setFlavorId(flavor.getFilterId());
								config.setFlavorName(flavor.getFilterName());
								config.setProductId(pm.getProductId());
								config.setProductName(pm.getProductName());
								config.setRateSetingType(pm.getRateSettingType());

								config.setVegType(pm.getIsVeg());

								config.setWeight(wt);
								tempProdConfList.add(config);

							} // end of Else if(vegNonVegList.size()==2)
						} // end of flavList For A
					} // end of If Flag==0
				} // end of prodWtList For K

				// End of Logic for Weight Changes.

				// Start Logic for VegType change

				for (int p = 0; p < vegNonVegList.size(); p++) {

					int flag = -1;
					for (int j = 0; j < prodConfDetailList.size(); j++) {
						flag = 0;
						Integer isProdMatch = Integer.compare(pm.getProductId(),
								prodConfDetailList.get(j).getProductId());

						if (isProdMatch.equals(0)) {

							Integer isVegTypeMach = Integer.compare((int) vegNonVegList.get(p),
									prodConfDetailList.get(j).getVegType());

							if (isVegTypeMach.equals(0)) {
								flag = 1;
								break;
							}

						} // end of if (isProdMatch.equals(0))
					} // end of prodConfDetailList For J

					if (flag == 0) {
						System.err.println("new isVeg Non Veg Found");
						for (int a = 0; a < flavList.size(); a++) {
							MFilter flavor = new MFilter();
							flavor = flavList.get(a);

							for (int k = 0; k < prodWtList.size(); k++) {

								TempProdConfig config = new TempProdConfig();

								UUID uuid = UUID.randomUUID();
								config.setUuid(uuid.toString());

								config.setCatId(pm.getProdCatId());
								config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
								config.setFlavorId(flavor.getFilterId());
								config.setFlavorName(flavor.getFilterName());
								config.setProductId(pm.getProductId());
								config.setProductName(pm.getProductName());
								config.setRateSetingType(pm.getRateSettingType());

								config.setVegType(vegNonVegList.get(a));

								config.setWeight(Float.parseFloat(prodWtList.get(k)));
								tempProdConfList.add(config);

							} // end of prodWtList For K

						} // end of flavList For A

					} // End of if flag==0

				} // end of For vegNonVegList For P

			} // end of prodList For Loop I
			traveller.setTempProdConfList(tempProdConfList);
		} catch (Exception e) {
			prodConfDetailList = new ArrayList<>();
			e.printStackTrace();
		}

		return traveller;

	}
//Sachin 23-09-2020
	//Desc- to save Prod conf Header Detail Edit Call ie some items added and some existing edited
	@RequestMapping(value = { "/saveUpdateProdConfHD" }, method = RequestMethod.POST)
	public @ResponseBody Object saveUpdateProdConfHD(@RequestBody TempConfTraveller traveller) {
		ItemConfHeader confHeader = new ItemConfHeader();
		try {
			GetItemConfHead confHead = traveller.getConfHead();
			int headerUpdateRes = itemConfHeaderRepo.updateProdConfHeader(confHead.getConfigName(), confHead.getCatId(),
					confHead.getCatName(), confHead.getConfigHeaderId());

			if (headerUpdateRes > 0) {
				List<ItemConfDetail> confDetList = traveller.getConfDetailList();
				List<ItemConfDetail> detailSaveRes = itemConfDetailRepo.saveAll(confDetList);

				List<TempProdConfig> updateDetList = traveller.getProdConfDetailList();

				for (int j = 0; j < updateDetList.size(); j++) {
					TempProdConfig detail = updateDetList.get(j);

					int detailRes = itemConfDetailRepo.updateProdConfDetail(detail.getRateAmt(), detail.getMrpAmt(),
							detail.getSpRateAmt1(), detail.getSpRateAmt2(), detail.getSpRateAmt3(),
							detail.getSpRateAmt4(), detail.getMakerUserId(), detail.getUpdtDttime(),
							detail.getConfigDetailId());
				}
			} else {

			}

		} catch (Exception e) {
			confHeader = new ItemConfHeader();
			e.printStackTrace();
		}

		return confHeader;
	}

}