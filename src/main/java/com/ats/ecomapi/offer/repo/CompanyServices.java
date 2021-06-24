package com.ats.ecomapi.offer.repo;

import java.util.List;


import org.springframework.stereotype.Service;

import com.ats.ecomapi.offer_model.GetConfigureOfferList;
import com.ats.ecomapi.offer_model.GetOfferFrConfiguredList;
import com.ats.ecomapi.offer_model.OfferConfig;
import com.ats.ecomapi.offer_model.OfferHeader;

 
@Service
public interface CompanyServices {
	/*
	 * List<Company> getAllCompany();
	 * 
	 * Company getMnCompanyById(int compId);
	 * 
	 * int deleteMnCompanyById(int compId);
	 * 
	 * Company insertNewCompany(Company company);
	 * 
	 * List<Company> getAllMnCompanyDetaisList();
	 * 
	 * FrConfig insertFrConfiguration(FrConfig frConfig);
	 * 
	 * FrConfig gerFrConfiguration(int configId);
	 * 
	 * int deletConfiguratnById(int configId);
	 * 
	 * List<GetFrConfigList> getAllFrConfigList();
	 * 
	 * List<Company> getAllActiveCompanyList();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * List<Item> getAllProductItems();
	 * 
	 * List<Item> getAllProductItemsById(int itemId);
	 * 
	 * ConfigRelatedProduct getProductById(int itemId);
	 * 
	 * ConfigRelatedProduct insertRelatedProductConfig(ConfigRelatedProduct
	 * product);
	 * 
	 * int udateRelatedProductConfig(String relatedItemIds, int productId, String
	 * updateDatTime, int userId);
	 * 
	 * List<GetProductRelatedList> getAllRelatedProductsByCompId(int compId);
	 * 
	 * int deleteRelProductConfig(int relatedProductId);
	 * 
	 * List<Agent> getAllAgentsByComp(int compId);
	 * 
	 * Agent getAgentById(int agentId, int compId);
	 * 
	 * Agent insertAgent(Agent agent);
	 * 
	 * int deleteAgent(int agentId);
	 * 
	 * Agent getAgentByMobileNo(String mobile);
	 * 
	 */
	
	List<GetConfigureOfferList> getConfigureOfferListById(int offerId, int compId);
	
	
	OfferConfig getConfigureFrOfferDetailById(int offerId);

	OfferConfig insertFrOfferConfig(OfferConfig offer);
	int udateFrOfferConfig(String frIdStr, int offerId, String updtTime, int userId);
	int deleteFrOfferConfig(int frOfferConfigId);
	
	List<GetOfferFrConfiguredList> getOfferFrConfiguredList(int compId);

	List<OfferHeader> getFrOfferConfigList(int compId);
}
