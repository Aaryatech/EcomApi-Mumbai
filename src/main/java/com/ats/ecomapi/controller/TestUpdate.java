package com.ats.ecomapi.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.common.CommonUtility;
import com.ats.ecomapi.master.model.MFilter;
import com.ats.ecomapi.master.repo.GetItemConfHeadRepo;
import com.ats.ecomapi.master.repo.MFilterRepo;
import com.ats.ecomapi.mst_model.GetItemConfHead;
import com.ats.ecomapi.mst_model.ProductMaster;
import com.ats.ecomapi.mst_model.TempConfTraveller;
import com.ats.ecomapi.mst_model.TempProdConfig;
import com.ats.ecomapi.mst_repo.ProductMasterRepo;
import com.ats.ecomapi.mst_repo.TempProdConfigRepo;

@RestController
public class TestUpdate {

	@Autowired
	ProductMasterRepo productMasterRepo;

	@Autowired
	TempProdConfigRepo getProdConfDetail;

	@Autowired
	GetItemConfHeadRepo getItemConfHeadRepo;

	@Autowired
	MFilterRepo filterRepo;

	List<MFilter> flavMastList = new ArrayList<MFilter>();

	public List<MFilter> getFlavList(List<String> flavIds) {
		List<MFilter> flavList = new ArrayList<MFilter>();

		try {
			for (int a = 0; a < flavIds.size(); a++) {
				for (int i = 0; i < flavMastList.size(); i++) {
					Integer isSame = null;
					isSame = Integer.compare(Integer.parseInt(flavIds.get(a)), flavMastList.get(i).getFilterId());
					if (isSame.equals(0)) {
						flavList.add(flavMastList.get(i));
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flavList;
	}

	List<MFilter> VegNonVegList = new ArrayList<MFilter>();

	public List<MFilter> getVegNonVegList(List<String> vnvIds) {
		List<MFilter> vnvList = new ArrayList<MFilter>();

		try {
			for (int a = 0; a < vnvIds.size(); a++) {
				for (int i = 0; i < VegNonVegList.size(); i++) {
					Integer isSame = null;
					isSame = Integer.compare(Integer.parseInt(vnvIds.get(a)), VegNonVegList.get(i).getFilterId());
					if (isSame.equals(0)) {
						vnvList.add(VegNonVegList.get(i));
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return vnvList;
	}

	List<MFilter> shapeIdList = new ArrayList<MFilter>();

	public List<MFilter> getShapeIdList(List<String> shapeIds) {
		List<MFilter> filterShapeList = new ArrayList<MFilter>();
		try {
			for (int a = 0; a < shapeIds.size(); a++) {
				for (int i = 0; i < shapeIdList.size(); i++) {
					Integer isSame = null;
					isSame = Integer.compare(Integer.parseInt(shapeIds.get(a)), shapeIdList.get(i).getFilterId());
					if (isSame.equals(0)) {
						filterShapeList.add(shapeIdList.get(i));
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filterShapeList;
	}

	public  float roundUp(float d) {
		return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	@RequestMapping(value = { "/getProdConfDetailByConfHeader" }, method = RequestMethod.POST)
	public @ResponseBody TempConfTraveller getProdConfDetailByConfHeader(@RequestParam int configHeaderId,
			@RequestParam int companyId) {

		TempConfTraveller traveller = new TempConfTraveller();

		List<TempProdConfig> prodConfDetailList = new ArrayList<>();
		List<TempProdConfig> tempProdConfList = new ArrayList<>();

		GetItemConfHead confHead = getItemConfHeadRepo.getProdConfHeaderByConfHeadId(configHeaderId);
		traveller.setConfHead(confHead);

		flavMastList = new ArrayList<MFilter>();
		try {
			// Get Filter By Comp Id and Filter type ie 4 for Flavor
			flavMastList = filterRepo.getFiltersByFilterId(companyId, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		VegNonVegList = new ArrayList<MFilter>();
		try {
			// Get Filter By Comp Id and Filter type ie 4 for Flavor
			VegNonVegList = filterRepo.getFiltersByFilterId(companyId, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		shapeIdList = new ArrayList<MFilter>();
		try {
			// Get Filter By Comp Id and Filter type ie 4 for Flavor
			shapeIdList = filterRepo.getFiltersByFilterId(companyId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			List<Integer> prodcutIdList = new ArrayList<Integer>();
			prodConfDetailList = getProdConfDetail.getProdConfByConfHeaderId(configHeaderId);
			traveller.setProdConfDetailList(prodConfDetailList);
System.err.println("prodConfDetailList size " +prodConfDetailList.toString());
			for (int i = 0; i < prodConfDetailList.size(); i++) {
				prodcutIdList.add(prodConfDetailList.get(i).getProductId());
			}

			Set set = new HashSet<>();
			set.addAll(prodcutIdList);
			prodcutIdList.clear();

			prodcutIdList.addAll(set);
			List<ProductMaster> prodList = productMasterRepo.findByProductIdIn(prodcutIdList);

			//Sachin new
			
			for (int i = 0; i < prodList.size(); i++) {
				
				float basicMrp=0,flavorPrice=0,shapePrice = 0,vegPrice=0;
				
				ProductMaster pm = prodList.get(i);
				
				List<String> prodFlavIds = Arrays.asList(pm.getFlavourIds().split(",", -1));
				List<String> vegNonVegIds = Arrays.asList(pm.getIsVeg().split(",", -1));
				List<String> shapeIds = Arrays.asList(pm.getShapeId().split(",", -1));
				
				List<MFilter> shapeList = getShapeIdList(shapeIds);
				List<MFilter> flavList = getFlavList(prodFlavIds);
				List<MFilter> vnVegList = getVegNonVegList(vegNonVegIds);
				
				List<String> prodWtList =new ArrayList<String>();
				
				if(pm.getRateSettingType()==2) {
				 prodWtList = Arrays.asList(pm.getAvailInWeights().split(",", -1));
				}else {
					prodWtList.add("1");
				}
				
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
						
						for(int w=0;w<prodWtList.size();w++) {
							basicMrp=pm.getBasicMrp();
							
							if(pm.getRateSettingType()==2) {
								basicMrp=pm.getBasicMrp()*Float.parseFloat(prodWtList.get(w));
							}
							
							if(flavor.getAddOnType()==2) {
								flavorPrice=flavor.getAddOnRs()*Float.parseFloat(prodWtList.get(w));
							}else {
								flavorPrice=flavor.getAddOnRs();
							}
							
							for(int v=0;v<vnVegList.size();v++) {
								
								if(vnVegList.get(v).getAddOnType()==2) {
									vegPrice=vnVegList.get(v).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
								}else {
									vegPrice=vnVegList.get(v).getAddOnRs();
								}
								
								if(!shapeList.isEmpty()) {
									for(int s=0;s<shapeList.size();s++) {
										if(shapeList.get(s).getAddOnType()==2) {
										shapePrice=	shapeList.get(s).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
										}else {
											shapePrice=	shapeList.get(s).getAddOnRs();
										}
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
										config.setVegType(vnVegList.get(v).getFilterId());
										config.setWeight(Float.parseFloat(prodWtList.get(w)));
										
										config.setVegNonVegName(vnVegList.get(v).getFilterName());
										config.setShapeId(shapeList.get(s).getFilterId());
										config.setShapeName(shapeList.get(s).getFilterName());
										
										config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));

										tempProdConfList.add(config);
										
									}//end of shapeList For s
									
								}//if !shapeList.isEmpty()
								else {
									//Shape Empty
									
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
									config.setVegType(vnVegList.get(v).getFilterId());
									config.setWeight(Float.parseFloat(prodWtList.get(w)));
									
									config.setVegNonVegName(vnVegList.get(v).getFilterName());
									config.setShapeId(0);
									config.setShapeName("Shape NA");
									
									config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
									
									tempProdConfList.add(config);
								}//else shapeList.isEmpty()
								
							}//end of vnVegList For v
							
						}//end of prodWtList For w
                                                       
                    }//end of flag==0
              }//end of flavlist loop
				
				//For ShapeList Logic
				
				for(int s=0;s<shapeList.size();s++) {
					int flag = -1;
					MFilter shape = new MFilter();
					for (int j = 0; j < prodConfDetailList.size(); j++) {

						Integer isProdMatch = Integer.compare(pm.getProductId(),
								prodConfDetailList.get(j).getProductId());

						if (isProdMatch.equals(0)) {
							flag = 0;
							shape = shapeList.get(s);

							Integer isShapeMatch = Integer.compare(shape.getFilterId(),
									prodConfDetailList.get(j).getShapeId());

							if (isShapeMatch.equals(0)) {
								flag = 1;
								
								break;
							} // end of if (isShapeMatch.equals(0))

						} // end of if (isProdMatch.equals(0))
					} // end of prodConfDetailList For Loop J
					
					if (flag == 0) {
						// Shape Id not found in Product Detail
												
						for(int w=0;w<prodWtList.size();w++) {
							
							if(pm.getRateSettingType()==2) {
								basicMrp=pm.getBasicMrp()*Float.parseFloat(prodWtList.get(w));
							}else {
								basicMrp=pm.getBasicMrp();
							}
							
							if(shapeList.get(s).getAddOnType()==2) {
								shapePrice=shapeList.get(s).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
							}else {
								shapePrice=shapeList.get(s).getAddOnRs();
							}
							
							for(int v=0;v<vnVegList.size();v++) {
								
								if(vnVegList.get(v).getAddOnType()==2) {
									vegPrice=vnVegList.get(v).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
								}else {
									vegPrice=vnVegList.get(v).getAddOnRs();
								}
								
								if(!flavList.isEmpty()) {
									for(int f=0;f<flavList.size();f++) {
										
										if(flavList.get(f).getAddOnType()==2) {
											flavorPrice=flavList.get(f).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
										}else {
											flavorPrice=flavList.get(f).getAddOnRs();
										}
										
										TempProdConfig config = new TempProdConfig();
										UUID uuid = UUID.randomUUID();
										config.setUuid(uuid.toString());

										config.setCatId(pm.getProdCatId());
										config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
										config.setFlavorId(flavList.get(f).getFilterId());
										config.setFlavorName(flavList.get(f).getFilterName());
										config.setProductId(pm.getProductId());
										config.setProductName(pm.getProductName());
										config.setRateSetingType(pm.getRateSettingType());
										config.setVegType(vnVegList.get(v).getFilterId());
										config.setWeight(Float.parseFloat(prodWtList.get(w)));
										
										config.setVegNonVegName(vnVegList.get(v).getFilterName());
										config.setShapeId(shape.getFilterId());
										config.setShapeName(shape.getFilterName());
										
										config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));

										tempProdConfList.add(config);
										
										
									}//end of flavList For f
									
								}//if !flavList.isEmpty()
								else {
									//Flavor Empty
									
									TempProdConfig config = new TempProdConfig();
									UUID uuid = UUID.randomUUID();
									config.setUuid(uuid.toString());

									config.setCatId(pm.getProdCatId());
									config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
									config.setFlavorId(0);
									config.setFlavorName("Flavor NA");
									config.setProductId(pm.getProductId());
									config.setProductName(pm.getProductName());
									config.setRateSetingType(pm.getRateSettingType());
									config.setVegType(vnVegList.get(v).getFilterId());
									config.setWeight(Float.parseFloat(prodWtList.get(w)));
									
									config.setVegNonVegName(vnVegList.get(v).getFilterName());
									config.setShapeId(shape.getFilterId());
									config.setShapeName(shape.getFilterName());
									
									config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
									
									tempProdConfList.add(config);
								}//else flavList.isEmpty()
								
							}//end of vnVegList For v
							
						}//end of prodWtList For w
                                                       
                    }//end of flag==0
              }//end of shapeList loop 2
				
				
//For vegNonVeg Logic
			
				for(int v=0;v<vnVegList.size();v++) {
					int flag = -1;
					MFilter vegNonVeg = new MFilter();
					for (int j = 0; j < prodConfDetailList.size(); j++) {

						Integer isProdMatch = Integer.compare(pm.getProductId(),
								prodConfDetailList.get(j).getProductId());

						if (isProdMatch.equals(0)) {
							flag = 0;
							vegNonVeg = vnVegList.get(v);

							Integer isVegNonVegMatch = Integer.compare(vegNonVeg.getFilterId(),
									prodConfDetailList.get(j).getVegType());

							if (isVegNonVegMatch.equals(0)) {
								flag = 1;
								
								break;
							} // end of if (isVegNonVegMatch.equals(0))

						} // end of if (isProdMatch.equals(0))
					} // end of prodConfDetailList For Loop J
					
					if (flag == 0) {
						// Veg Non veg Id not found in Product Detail
				
						for(int w=0;w<prodWtList.size();w++) {
							
							if(pm.getRateSettingType()==2) {
								basicMrp=pm.getBasicMrp()*Float.parseFloat(prodWtList.get(w));
							}else {
								basicMrp=pm.getBasicMrp();
							}
							
							if(vnVegList.get(v).getAddOnType()==2) {
								vegPrice=vnVegList.get(v).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
							}else {
								vegPrice=vnVegList.get(v).getAddOnRs();
							}
							
							if(!flavList.isEmpty()) {
								for(int f=0;f<flavList.size();f++) {
									
									if(flavList.get(f).getAddOnType()==2) {
										flavorPrice=flavList.get(f).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
									}else {
										flavorPrice=flavList.get(f).getAddOnRs();
									}
									
									if(!shapeList.isEmpty()) {
										for(int s=0;s<shapeList.size();s++) {
											
											if(shapeList.get(s).getAddOnType()==2) {
												shapePrice=shapeList.get(s).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
											}else {
												shapePrice=shapeList.get(s).getAddOnRs();
											}
											
											TempProdConfig config = new TempProdConfig();
											UUID uuid = UUID.randomUUID();
											config.setUuid(uuid.toString());

											config.setCatId(pm.getProdCatId());
											config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
											config.setFlavorId(flavList.get(f).getFilterId());
											config.setFlavorName(flavList.get(f).getFilterName());
											config.setProductId(pm.getProductId());
											config.setProductName(pm.getProductName());
											config.setRateSetingType(pm.getRateSettingType());
											config.setVegType(vnVegList.get(v).getFilterId());
											config.setWeight(Float.parseFloat(prodWtList.get(w)));
											
											config.setVegNonVegName(vnVegList.get(v).getFilterName());
											config.setShapeId(shapeList.get(s).getFilterId());
											config.setShapeName(shapeList.get(s).getFilterName());
											
											config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
											
											tempProdConfList.add(config);
											
										}//end of for shapeList s
										
									}//end of if  !shapeList.isEmpty()
									else {
										TempProdConfig config = new TempProdConfig();
										UUID uuid = UUID.randomUUID();
										config.setUuid(uuid.toString());

										config.setCatId(pm.getProdCatId());
										config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
										config.setFlavorId(flavList.get(f).getFilterId());
										config.setFlavorName(flavList.get(f).getFilterName());
										config.setProductId(pm.getProductId());
										config.setProductName(pm.getProductName());
										config.setRateSetingType(pm.getRateSettingType());
										config.setVegType(vnVegList.get(v).getFilterId());
										config.setWeight(Float.parseFloat(prodWtList.get(w)));
										
										config.setVegNonVegName(vnVegList.get(v).getFilterName());
										config.setShapeId(0);
										config.setShapeName("Shape NA");
										
										config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
										
										tempProdConfList.add(config);
									}//else shapeList.isEmpty()
									
								}//end of flavList
								
							}//end of if !flavList.isEmpty()
							else {
								if(!shapeList.isEmpty()) {
									for(int s=0;s<shapeList.size();s++) {
										if(shapeList.get(s).getAddOnType()==2) {
											shapePrice=shapeList.get(s).getAddOnRs()*Float.parseFloat(prodWtList.get(w));
										}else {
											shapePrice=shapeList.get(s).getAddOnRs();
										}
										TempProdConfig config = new TempProdConfig();
										UUID uuid = UUID.randomUUID();
										config.setUuid(uuid.toString());

										config.setCatId(pm.getProdCatId());
										config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
										config.setFlavorId(0);
										config.setFlavorName("Flavor NA");
										config.setProductId(pm.getProductId());
										config.setProductName(pm.getProductName());
										config.setRateSetingType(pm.getRateSettingType());
										config.setVegType(vnVegList.get(v).getFilterId());
										config.setWeight(Float.parseFloat(prodWtList.get(w)));
										
										config.setVegNonVegName(vnVegList.get(v).getFilterName());
										config.setShapeId(shapeList.get(s).getFilterId());
										config.setShapeName(shapeList.get(s).getFilterName());
										
										config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
										
										tempProdConfList.add(config);
									}//end of for shapeList s
									
								}//end of if  !shapeList.isEmpty()
								else {
									
									TempProdConfig config = new TempProdConfig();
									UUID uuid = UUID.randomUUID();
									config.setUuid(uuid.toString());

									config.setCatId(pm.getProdCatId());
									config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
									config.setFlavorId(0);
									config.setFlavorName("Flavor NA");
									config.setProductId(pm.getProductId());
									config.setProductName(pm.getProductName());
									config.setRateSetingType(pm.getRateSettingType());
									config.setVegType(vnVegList.get(v).getFilterId());
									config.setWeight(Float.parseFloat(prodWtList.get(w)));
									
									config.setVegNonVegName(vnVegList.get(v).getFilterName());
									config.setShapeId(0);
									config.setShapeName("NA SHAPE");
									
									config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
									
									tempProdConfList.add(config);
								}//else shapeList.isEmpty()
								
							}//else flavList.isEmpty()
							
							
						}//end of for prodWtList For w
						
						
					}//end of flag==0
                     
				}//end of vegNonveg logic Loop
				
				
				//For WeightList Logic
				for(int w=0;w<prodWtList.size();w++) {

					int flag = -1;
					float wt  = Float.parseFloat(prodWtList.get(w));
					for (int j = 0; j < prodConfDetailList.size(); j++) {

						Integer isProdMatch = Integer.compare(pm.getProductId(),
								prodConfDetailList.get(j).getProductId());

						if (isProdMatch.equals(0)) {
							flag = 0;
							

							Integer isWtMatch = Float.compare(wt,
									prodConfDetailList.get(j).getWeight());

							if (isWtMatch.equals(0)) {
								flag = 1;
								
								break;
							} // end of if (isVegNonVegMatch.equals(0))

						} // end of if (isProdMatch.equals(0))
					} // end of prodConfDetailList For Loop J
					
					if (flag == 0) {
						// Weight not found in Product Detail
						basicMrp=pm.getBasicMrp();
						if(pm.getRateSettingType()==2) {
							basicMrp=pm.getBasicMrp()*wt;
						}
						for(int v=0;v<vnVegList.size();v++) {
							
							if(vnVegList.get(v).getAddOnType()==2) {
								vegPrice=vnVegList.get(v).getAddOnRs()*wt;
							}else {
								vegPrice=vnVegList.get(v).getAddOnRs();
							}
							
							if(!flavList.isEmpty()) {
								for(int f=0;f<flavList.size();f++) {
									
									if(flavList.get(f).getAddOnType()==2) {
										flavorPrice=flavList.get(f).getAddOnRs()*wt;
									}else {
										flavorPrice=flavList.get(f).getAddOnRs();
									}
									
									if(!shapeList.isEmpty()) {
										for(int s=0;s<shapeList.size();s++) {
											
											if(shapeList.get(s).getAddOnType()==2) {
												shapePrice=shapeList.get(s).getAddOnRs()*wt;
											}else {
												shapePrice=shapeList.get(s).getAddOnRs();
											}
											
											TempProdConfig config = new TempProdConfig();
											UUID uuid = UUID.randomUUID();
											config.setUuid(uuid.toString());

											config.setCatId(pm.getProdCatId());
											config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
											config.setFlavorId(flavList.get(f).getFilterId());
											config.setFlavorName(flavList.get(f).getFilterName());
											config.setProductId(pm.getProductId());
											config.setProductName(pm.getProductName());
											config.setRateSetingType(pm.getRateSettingType());
											config.setVegType(vnVegList.get(v).getFilterId());
											config.setWeight(w);
											
											config.setVegNonVegName(vnVegList.get(v).getFilterName());
											config.setShapeId(shapeList.get(s).getFilterId());
											config.setShapeName(shapeList.get(s).getFilterName());
											
											config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
											
											tempProdConfList.add(config);
											
										}//end of for shapeList s
										
									}//end of if  !shapeList.isEmpty()
									else {
										TempProdConfig config = new TempProdConfig();
										UUID uuid = UUID.randomUUID();
										config.setUuid(uuid.toString());

										config.setCatId(pm.getProdCatId());
										config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
										config.setFlavorId(flavList.get(f).getFilterId());
										config.setFlavorName(flavList.get(f).getFilterName());
										config.setProductId(pm.getProductId());
										config.setProductName(pm.getProductName());
										config.setRateSetingType(pm.getRateSettingType());
										config.setVegType(vnVegList.get(v).getFilterId());
										config.setWeight(w);
										
										config.setVegNonVegName(vnVegList.get(v).getFilterName());
										config.setShapeId(0);
										config.setShapeName("Shape NA");
										
										config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
										
										tempProdConfList.add(config);
									}//else shapeList.isEmpty()
									
								}//end of flavList
								
							}//end of if !flavList.isEmpty()
							else {
								if(!shapeList.isEmpty()) {
									for(int s=0;s<shapeList.size();s++) {
										
										if(shapeList.get(s).getAddOnType()==2) {
											shapePrice=shapeList.get(s).getAddOnRs()*wt;
										}else {
											shapePrice=shapeList.get(s).getAddOnRs();
										}
										
										
										TempProdConfig config = new TempProdConfig();
										UUID uuid = UUID.randomUUID();
										config.setUuid(uuid.toString());

										config.setCatId(pm.getProdCatId());
										config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
										config.setFlavorId(0);
										config.setFlavorName("Flavor NA");
										config.setProductId(pm.getProductId());
										config.setProductName(pm.getProductName());
										config.setRateSetingType(pm.getRateSettingType());
										config.setVegType(vnVegList.get(v).getFilterId());
										config.setWeight(w);
										
										config.setVegNonVegName(vnVegList.get(v).getFilterName());
										config.setShapeId(shapeList.get(s).getFilterId());
										config.setShapeName(shapeList.get(s).getFilterName());
										
										config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
										
										tempProdConfList.add(config);
									}//end of for shapeList s
									
								}//end of if  !shapeList.isEmpty()
								else {
									
									TempProdConfig config = new TempProdConfig();
									UUID uuid = UUID.randomUUID();
									config.setUuid(uuid.toString());

									config.setCatId(pm.getProdCatId());
									config.setCurTimeStamp(CommonUtility.getCurrentYMDDateTime());
									config.setFlavorId(0);
									config.setFlavorName("Flavor NA");
									config.setProductId(pm.getProductId());
									config.setProductName(pm.getProductName());
									config.setRateSetingType(pm.getRateSettingType());
									config.setVegType(vnVegList.get(v).getFilterId());
									config.setWeight(w);
									
									config.setVegNonVegName(vnVegList.get(v).getFilterName());
									config.setShapeId(0);
									config.setShapeName("NA SHAPE");
									
									config.setMrpAmt(roundUp(basicMrp+vegPrice+shapePrice+flavorPrice));
									
									tempProdConfList.add(config);
								}//else shapeList.isEmpty()
							}//else flavList.isEmpty()
						}//end of for prodWtList For w
					}//end of flag==0
				}//end of Weight List logic Loop
          }//end of prodList loop
			System.err.println("tempProdConfList " +tempProdConfList.toString());
			//Sachin new end
				// End of flavor logic -sachin 23-10-2020
			traveller.setTempProdConfList(tempProdConfList);
		}catch(
 
	Exception e)
	{
			prodConfDetailList = new ArrayList<>();
			e.printStackTrace();
		}

	return traveller;

}

}
