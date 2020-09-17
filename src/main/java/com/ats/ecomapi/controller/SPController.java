package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
import com.ats.ecomapi.master.repo.GetProdListRepo;
import com.ats.ecomapi.master.repo.GetSubCatPrefixRepo;
import com.ats.ecomapi.master.repo.MFilterRepo;
import com.ats.ecomapi.mst_model.GetProdList;
import com.ats.ecomapi.mst_model.GetSubCatPrefix;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.ProductMaster;
import com.ats.ecomapi.mst_model.TempProdConfig;
import com.ats.ecomapi.mst_model.User;
import com.ats.ecomapi.mst_repo.ProductMasterRepo;
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
			System.err.println("Username ----------- " + userName);
			System.err.println("Password ----------- " + pass);
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
				System.err.println("Its prodMaster Res " + prodMasterRes);
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
				System.err.println(" In Null");
				prodList = new ArrayList<GetProdList>();
			} else {
				System.err.println("Not null");
			}

		} catch (Exception e) {
			System.err.println("In Exce");
			prodList = new ArrayList<GetProdList>();
		}

		return prodList;
	}

	// Sachin 17-09-2020
	@Autowired
	MFilterRepo filterRepo;

	List<MFilter> filterList = new ArrayList<MFilter>();

	@RequestMapping(value = { "/getProdConf" }, method = RequestMethod.POST)
	public @ResponseBody String getProdConf(@RequestParam int compId) {

		filterList = new ArrayList<MFilter>();
		try {
			filterList = filterRepo.getFiltersByFilterId(compId, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<ProductMaster> prodList = productMasterRepo.findByProdCatIdAndDelStatus(1, 1);

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
									}// end of vegNonVegList Y for 
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
}