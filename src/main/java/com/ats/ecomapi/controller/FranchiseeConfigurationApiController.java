package com.ats.ecomapi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.common.CommonUtility;
import com.ats.ecomapi.fe_model.DeliveryBoy;
import com.ats.ecomapi.fe_model.FrDelvrBoyConfig;
import com.ats.ecomapi.master.model.CopyTable;
import com.ats.ecomapi.master.model.FrConfiguration;
import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.master.model.GetTableFields;
import com.ats.ecomapi.master.model.HomePageTestimonial;
import com.ats.ecomapi.master.repo.DeliveryBoyRepo;
import com.ats.ecomapi.master.repo.FrConfigurationRepo;
import com.ats.ecomapi.master.repo.FrDelvrBoyConfigRepo;
import com.ats.ecomapi.master.repo.FranchiseRepo;
import com.ats.ecomapi.master.repo.GetFrConfigListRepo;
import com.ats.ecomapi.master.repo.GetFrForConfigRepo;
import com.ats.ecomapi.mst_model.GetFrConfigList;
import com.ats.ecomapi.mst_model.GetFrForConfig;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.ItemConfHeader;
import com.ats.ecomapi.mst_repo.ItemConfHeaderRepo;
import com.ats.ecomapi.offer.repo.FrChargesRepo;
import com.ats.ecomapi.offer_model.FrCharges;

@RestController
public class FranchiseeConfigurationApiController {

	@Autowired
	ItemConfHeaderRepo itemConfHeaderRepo;

	@Autowired
	FranchiseRepo franchiseRepo;

	@Autowired
	FrConfigurationRepo frConfigurationRepo;

	@Autowired
	GetFrForConfigRepo getFrForConfigRepo;

	@Autowired
	GetFrConfigListRepo getFrConfigListRepo;

	@Autowired
	DeliveryBoyRepo delvrBoyRepo;
	
	@Autowired
	FrDelvrBoyConfigRepo frDelvrBoyRepo;
	
	@Autowired
	FrChargesRepo frChargRepo;
	
	@Autowired
	FranchiseRepo frRepo;
	

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 24-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- get all configs

	@RequestMapping(value = { "/getConfigurationByCatId" }, method = RequestMethod.POST)
	public @ResponseBody List<ItemConfHeader> getConfigurationByCatId(@RequestParam int catId) {

		List<ItemConfHeader> list = new ArrayList<ItemConfHeader>();

		try {
			list = itemConfHeaderRepo.findByCatIdAndDelStatus(catId, 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 24-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- get all configs by company

	@RequestMapping(value = { "/getConfigurationByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<ItemConfHeader> getConfigurationByCompId(@RequestParam int compId) {

		List<ItemConfHeader> list = new ArrayList<ItemConfHeader>();

		try {
			list = itemConfHeaderRepo.findByCompanyIdAndDelStatus(compId, 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 24-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- get all fr configs

	@RequestMapping(value = { "/getFranchiseForConfig" }, method = RequestMethod.POST)
	public @ResponseBody List<GetFrForConfig> getFranchiseForConfig(@RequestParam int catId,
			@RequestParam int companyId) {

		List<GetFrForConfig> list = new ArrayList<GetFrForConfig>();

		try {
			/*
			 * List<ItemConfHeader> cnlist = itemConfHeaderRepo.findAll();
			 */
			/* if (cnlist.size() > 0) { */
			list = getFrForConfigRepo.getFranchiseToConfig(companyId, catId);
			/*
			 * } else { list =
			 * franchiseRepo.findByCompanyIdAndDelStatusOrderByFrIdDesc(companyId, 1); }
			 */
			// System.err.println("list" + list.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 24-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- insert all configs

	@RequestMapping(value = { "/insertFrConfig" }, method = RequestMethod.POST)
	public @ResponseBody Info insertFrConfig(@RequestParam int actualRate, @RequestParam int displayRate,
			@RequestParam List<Integer> frIds, @RequestParam int cfgId, @RequestParam int userId,
			@RequestParam String curDateTime) {

		Info res = new Info();

		try {
			List<FrConfiguration> configList = new ArrayList<>();
			for (int i = 0; i < frIds.size(); i++) {

				FrConfiguration fr = new FrConfiguration();
				fr.setActualRate(actualRate);
				fr.setConfigHeaderId(cfgId);
				fr.setDisplayRate(displayRate);
				fr.setFrId(frIds.get(i));
				fr.setUpdatedDateTime(curDateTime);
				fr.setMakerUserId(userId);
				fr.setMakerDatetime(curDateTime);
				fr.setIsActive(1);
				fr.setExInt1(0);
				fr.setExInt2(0);
				fr.setExVar1("NA");
				fr.setExVar2("NA");

				configList.add(fr);
			}

			List<FrConfiguration> save = frConfigurationRepo.saveAll(configList);

			if (save != null) {
				res.setError(false);
				res.setMessage("Francise Configured Successfully");
			} else {
				res.setError(true);
				res.setMessage("Failed to Configure");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setError(true);
			res.setMessage("Failed to Configure");
		}

		return res;
	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- get all configs list by filter

	@RequestMapping(value = { "/getFranchiseConfigList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetFrConfigList> getFranchiseConfigList(@RequestParam List<String> frIds,
			@RequestParam List<String> configIds, @RequestParam int orderBy, @RequestParam int compId) {
		// 1-config 2-fr
		List<GetFrConfigList> list = new ArrayList<GetFrConfigList>();

		try {
			if (frIds.contains("0") && !configIds.contains("0") && orderBy == 1) {

				list = getFrConfigListRepo.getAllFranchiseToFrAllFrOrCon(configIds, compId);

			} else if (configIds.contains("0") && !frIds.contains("0") && orderBy == 1) {

				list = getFrConfigListRepo.getAllFranchiseToConfigAllConfigOrCon(frIds, compId);

			} else if (!configIds.contains("0") && !frIds.contains("0") && orderBy == 1) {
				list = getFrConfigListRepo.getAllFranchiseToConfigAllConfigOrCon(frIds, configIds, compId);
			} else if (configIds.contains("0") && frIds.contains("0") && orderBy == 1) {

				list = getFrConfigListRepo.getAllFranchiseToConfigAllOrCon(compId);
			} else if (frIds.contains("0") && !configIds.contains("0") && orderBy == 2) {
				list = getFrConfigListRepo.getAllFranchiseToFrAllFrOrFr(configIds, compId);
			} else if (configIds.contains("0") && !frIds.contains("0") && orderBy == 2) {

				list = getFrConfigListRepo.getAllFranchiseToConfigAllConfigOrFr(frIds, compId);
			} else if (!configIds.contains("0") && !frIds.contains("0") && orderBy == 2) {

				list = getFrConfigListRepo.getAllFranchiseToConfigAllConfigOrFr(frIds, configIds, compId);

			} else {
				// configIds.contains("0") && frIds.contains("0") && orderBy==

				list = getFrConfigListRepo.getAllFranchiseToConfigAllOrFr(compId);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- delete configs
	@RequestMapping(value = { "/deleteFrConfig" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteFrConfig(@RequestParam List<Integer> configIds) {

		Info res = new Info();
		int n = 0;

		try {
			n = frConfigurationRepo.delFrConfig(configIds);

			if (n > 0) {
				res.setError(false);
				res.setMessage("Franchise Configuration Deleted Successfully");
			} else {
				res.setError(true);
				res.setMessage("Failed to Delete Franchise  Configuration");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 29-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Delivery Boy
	@RequestMapping(value = { "/saveDeliveryBoy" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryBoy saveDeliveryBoy(@RequestBody DeliveryBoy delBoy) {
System.err.println(delBoy);
		DeliveryBoy newDelBoy = new DeliveryBoy();
		try {  
			newDelBoy = delvrBoyRepo.save(delBoy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDelBoy;

	}

	// Created By :- Mahendra Singh
	// Created On :- 29-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Single Delivery Boy By Id
	@RequestMapping(value = { "/getDeliveryBoyById" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryBoy getDeliveryBoyById(@RequestParam int delBoyId) {

		DeliveryBoy delBoy = new DeliveryBoy();
		try {
			delBoy = delvrBoyRepo.findByDelBoyId(delBoyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return delBoy;
	}

	// Created By :- Mahendra Singh
	// Created On :- 29-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Delivery Boy
	@RequestMapping(value = { "/deleteDeliveryBoyById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteDeliveryBoyById(@RequestParam int delBoyId) {

		Info res = new Info();
		try {
			int delBoy = delvrBoyRepo.deleteDelveryBoyById(delBoyId);
			if (delBoy > 0) {
				res.setError(false);
				res.setMessage("Delivery Boy Deleted Successfully");
			} else {
				res.setError(true);
				res.setMessage("Failed to Deleted Delivery Boy");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// Created By :- Mahendra Singh
	// Created On :- 29-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get all delivery boy and configure franchise count by company Id.
	@RequestMapping(value = { "/getDeliveryBoysList" }, method = RequestMethod.POST)
	public @ResponseBody List<DeliveryBoy> getDeliveryBoysList(@RequestParam int compId) {

		List<DeliveryBoy> list = new ArrayList<DeliveryBoy>();
		try {
			list = delvrBoyRepo.getDelvrBoyAndFrCount(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// Created By :- Mahendra Singh
	// Created On :- 29-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Check unique delivery boy mobile no.
	@RequestMapping(value = { "/getDeliveryBoyByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryBoy getDeliveryBoyByMobNo(@RequestParam int delBoyId, @RequestParam String mobNo) {

		DeliveryBoy res = new DeliveryBoy();
		try {
			if(delBoyId>0) {
				res = delvrBoyRepo.findByMobileNoAndDelStatusAndDelBoyIdNot(mobNo, 1, delBoyId);
			}else {
				res = delvrBoyRepo.findByMobileNoAndDelStatus(mobNo, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- shubham vitore
	// Created On :- 9-02-2021
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Validate licenseNo
	@RequestMapping(value = { "/getDeliveryBoyBydeliveryBoyLicenseNo" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryBoy getDeliveryBoyBydeliveryBoyLicenseNo(@RequestParam int delBoyId, @RequestParam String deliveryBoyLicenseNo) {

		DeliveryBoy res = new DeliveryBoy();
		try {
			if(delBoyId>0) {
				res = delvrBoyRepo.findBydeliveryBoyLicenseNoAndDelStatusAndDelBoyIdNot(deliveryBoyLicenseNo, 1, delBoyId);
			}else {
			
				res = delvrBoyRepo.findBydeliveryBoyLicenseNoAndDelStatus(deliveryBoyLicenseNo, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	/*
	@RequestMapping(value = { "/getDelvrBoyInfoByvehicleNo" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryBoy getDelvrBoyInfoByvehicleNo(@RequestParam int delBoyId, @RequestParam String vehicleNo) {

		DeliveryBoy res = new DeliveryBoy();
		try {
			if(delBoyId>0) {
				res = delvrBoyRepo.findByvehicleNoAndDelStatusAndDelBoyIdNot(vehicleNo, 1, delBoyId);
			}else {
				res = delvrBoyRepo.findByvehicleNoAndDelStatus(vehicleNo, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	*/
	
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 29-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Delivery Boy
	@RequestMapping(value = { "/configFrDeliveryBoy" }, method = RequestMethod.POST)
	public @ResponseBody FrDelvrBoyConfig configFrDeliveryBoy(@RequestBody FrDelvrBoyConfig config) {

		FrDelvrBoyConfig newConfig = new FrDelvrBoyConfig();
		try {
			newConfig = frDelvrBoyRepo.save(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newConfig;

	}
	
		// Created By :- Mahendra Singh
		// Created On :- 29-10-2020
		// Modified By :- NA
		// Modified On :- NA
		// Description :- Get Franchise Configured Delivery Boy Ids
		@RequestMapping(value = { "/getFrDelBoyIds" }, method = RequestMethod.POST)
		public @ResponseBody String getFrDelBoyIds(@RequestParam int compId, @RequestParam int delBoyId) {

			String delBoyIds = new String();
			try {
				delBoyIds = frDelvrBoyRepo.getFrDelBoyIds(compId, delBoyId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return delBoyIds;

		}
		
			// Created By :- Mahendra Singh
				// Created On :- 29-10-2020
				// Modified By :- NA
				// Modified On :- NA
				// Description :- Get Franchise Configured Delivery 
				@RequestMapping(value = { "/getFrDelvryConfig" }, method = RequestMethod.POST)
				public @ResponseBody FrDelvrBoyConfig getFrDelvroyConfig(@RequestParam int compId, @RequestParam int delBoyId) {

					FrDelvrBoyConfig res = new FrDelvrBoyConfig();
					try {
						res = frDelvrBoyRepo.findBydelBoyIdAndCompanyId(delBoyId, compId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return res;

				}
				
				// Created By :- Mahendra Singh
				// Created On :- 29-10-2020
				// Modified By :- NA
				// Modified On :- NA
				// Description :- Get Franchise Configured Delivery 
				@RequestMapping(value = { "/editConfigFranchises" }, method = RequestMethod.POST)
				public @ResponseBody Info editConfigFranchises(@RequestParam String frIdsStr, @RequestParam int delBoyId) {

					Info res = new Info();
					try {
						int val = frDelvrBoyRepo.updateFrConfig(delBoyId, frIdsStr);
						if(val>0) {
							res.setError(false);
							res.setMsg("Franchise And Delivery Boy Configure Successfully");
						}else {
							res.setError(true);
							res.setMsg("Failed to Configure Franchise And Delivery Boy");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return res;

				}
	/*------------------------------------------------------------------------------------*/
				// Created By :- Mahendra Singh
				// Created On :- 22-10-2020
				// Modified By :- NA
				// Modified On :- NA
				// Description :- Save Franchise Charges
				@RequestMapping(value = { "/saveFrCharges" }, method = RequestMethod.POST)
				public @ResponseBody FrCharges saveFrCharges(@RequestBody FrCharges charges){
					
				 FrCharges saveCharges = new FrCharges();
					try {
						saveCharges = frChargRepo.save(charges);
					}catch (Exception e) {
						e.printStackTrace();
					}
					return saveCharges;
				}
				
				// Created By :- Mahendra Singh
				// Created On :- 22-10-2020
				// Modified By :- NA
				// Modified On :- NA
				// Description :- Save Franchise Charges
				@RequestMapping(value = { "/saveMultiFrCharges" }, method = RequestMethod.POST)
				public @ResponseBody FrCharges saveMultiFrCharges(@RequestBody FrCharges charges){
					
				 FrCharges saveCharges = new FrCharges();
				 List<Franchise> frList = new ArrayList<Franchise>();
					try {
						List<String> selFrIdList = Arrays.asList(charges.getExVar1());
						System.err.println("selFrIdList------"+selFrIdList);
						//frList = frRepo.findByCompanyIdAndDelStatusOrderByFrIdDesc(charges.getExInt1(), 1);
					
						System.err.println("selFrId------"+selFrIdList.size());
					
						List<Integer> frids = Stream.of(charges.getExVar1().split(",")).map(Integer::parseInt)
								.collect(Collectors.toList());
						
						for (int i = 0; i < frids.size(); i++) {
							System.err.println("selFrId------"+frids.get(i));
						}
						System.err.println("Data---------------"+charges);
						//saveCharges = frChargRepo.save(charges);
					}catch (Exception e) {
						e.printStackTrace();
					}
					return saveCharges;
				}
				
				
				
				// Created By :- Akhilesh
				// Created On :- 22-10-2020
				// Modified By :- NA
				// Modified On :- NA
				// Description :- Save Multiple Franchise Charges
				@RequestMapping(value="/saveMultipleFrCharges",method=RequestMethod.POST)
				public @ResponseBody List<FrCharges> saveMultiFrCharges(@RequestBody List<FrCharges> frClist){
					/*List<FrCharges> frChargesList=new ArrayList<>();*/
					List<FrCharges> frResp=new ArrayList<>();
					FrCharges frCharge=new FrCharges();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					int flag=0;
					try {
						/*frChargesList=	frChargRepo.getAllFrChargesByCompId(frClist.get(0).getExInt1());*/
						for(FrCharges charges :frClist) {
							
								String fromDt=dateFormat.format(charges.getFromDate());
								String toDt=dateFormat.format(charges.getToDate());
								int surcharge=(int)charges.getSurchargeFee();
								int pack=(int)charges.getPackingChg();
								int hand=(int)charges.getHandlingChg();
								int extra=(int)charges.getExtraChg();
								int round=(int)charges.getRoundOffAmt();
								if(charges.getChargeId()>0) {
									//System.err.println("In If");
								flag=frChargRepo.updateFrCharges(fromDt, toDt, surcharge,pack, hand, extra, round,charges.getFrId());
								if(flag==0) {
									System.err.println("Unable To Update Frcharges!! Of "+charges.getFrId());
								}else {
									System.err.println("Frcharges Updated !! Of "+charges.getFrId());
								}
								
								}else {
									frCharge =frChargRepo.save(charges);
									frResp.add(frCharge);
									//System.err.println("In Else");
								}
								
						
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.err.println("Exception Occur In /saveMultipleFrCharges");
						e.printStackTrace();
					}
					
					
					return frResp;
				}
				
				
				
			 
				// Created By :- Mahendra Singh
				// Created On :- 22-10-2020
				// Modified By :- NA
				// Modified On :- NA
				// Description :- Ger Franchise Charges By Id
			 @RequestMapping(value = { "/getFrChargesByFrId" }, method = RequestMethod.POST)
				public @ResponseBody FrCharges getFrChargesByFrId(@RequestParam int frId){
					
				 FrCharges charges = new FrCharges();
					try {
						charges = frChargRepo.findByfrId(frId);
					}catch (Exception e) {
						e.printStackTrace();
					}
					return charges;
				}
}
