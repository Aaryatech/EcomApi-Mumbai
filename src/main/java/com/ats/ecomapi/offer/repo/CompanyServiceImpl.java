package com.ats.ecomapi.offer.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.ecomapi.offer_model.GetConfigureOfferList;
import com.ats.ecomapi.offer_model.GetOfferFrConfiguredList;
import com.ats.ecomapi.offer_model.OfferConfig;
import com.ats.ecomapi.offer_model.OfferHeader;

@Service
public class CompanyServiceImpl implements CompanyServices {
	/*
	 * @Autowired CompanyRepo compRepo;
	 * 
	 * @Autowired FrConfigRepo frConfigRepo;
	 * 
	 * @Autowired GetFrConfigListRepo frConfigListRepo;
	 * 
	 * @Autowired ItemRepository itemRepository;
	 */

	@Autowired
	OfferHeaderRepo offerHeadRepo;

	@Autowired
	GetConfigureOfferListRepo configureFrOfferList;

	@Autowired
	OfferConfigRepo frOfferConfig;

	@Autowired
	GetOfferFrConfiguredListRepo configFrOfferListRepo;

	/*
	 * @Autowired ItemRepository itemRepo;
	 * 
	 * @Autowired ConfigRelatedProductRepo configProductRepo;
	 * 
	 * @Autowired GetProductRelatedListRepo getRelatedProductListRepo;
	 * 
	 * @Autowired AgentRepo agentRepo;
	 */
	/**********************************************************************/

	@Override
	public List<GetConfigureOfferList> getConfigureOfferListById(int offerId, int compId) {
		List<GetConfigureOfferList> list = configureFrOfferList.getConfigureOferList(offerId, compId);
		return list;
	}

	@Override
	public OfferConfig getConfigureFrOfferDetailById(int offerId) {
		OfferConfig frOffer = frOfferConfig.findByOfferIdAndDelStatus(offerId, 1);
		return frOffer;
	}

	@Override
	public OfferConfig insertFrOfferConfig(OfferConfig offer) {
		OfferConfig frOffer = frOfferConfig.save(offer);
		return frOffer;
	}

	@Override
	public int udateFrOfferConfig(String frIdStr, int offerId, String updtTime, int userId) {
		int res = frOfferConfig.updateFrOfferConfig(frIdStr, offerId, updtTime, userId);
		return res;
	}

	@Override
	public List<GetOfferFrConfiguredList> getOfferFrConfiguredList(int compId) {
		List<GetOfferFrConfiguredList> list = configFrOfferListRepo.getConfiguredOfferFrList(compId);
		return list;
	}

	@Override
	public int deleteFrOfferConfig(int frOfferConfigId) {
		int res = frOfferConfig.deleteOfferConfigurationById(frOfferConfigId);
		return res;
	}

	@Override
	public List<OfferHeader> getFrOfferConfigList(int compId) {
		List<OfferHeader> offerList = offerHeadRepo.findByCompIdAndDelStatusOrderByOfferIdDesc(compId, 1);
		return offerList;
	}

	/*
	 * 
	 * 
	 * @Override public Company getMnCompanyById(int compId) { Company comp =
	 * compRepo.findByCompanyIdAndDelStatus(compId, 0); return comp; }
	 * 
	 * @Override public int deleteMnCompanyById(int compId) { int comp =
	 * compRepo.deleteCompanyById(compId); return comp; }
	 * 
	 * @Override public Company insertNewCompany(Company company) { Company addComp
	 * = compRepo.save(company); return addComp; }
	 * 
	 * @Override public List<Company> getAllCompany() { List<Company> comp =
	 * compRepo.findByDelStatus(0); return comp; }
	 * 
	 * @Override public List<Company> getAllMnCompanyDetaisList() { List<Company>
	 * comp = compRepo.getAllCompaniesDetails(); return comp; }
	 * 
	 * @Override public FrConfig insertFrConfiguration(FrConfig frConfig) { FrConfig
	 * config = frConfigRepo.save(frConfig); return config; }
	 * 
	 * @Override public FrConfig gerFrConfiguration(int configId) { FrConfig config
	 * = frConfigRepo.findBydelStatusAndFrConfigId(0, configId); return config; }
	 * 
	 * @Override public int deletConfiguratnById(int configId) { int config =
	 * frConfigRepo.deleteConfigurationById(configId); return config; }
	 * 
	 * @Override public List<GetFrConfigList> getAllFrConfigList() {
	 * List<GetFrConfigList> list = frConfigListRepo.getAllFrConfigList(); return
	 * list; }
	 * 
	 * @Override public List<Company> getAllActiveCompanyList() { List<Company> list
	 * = compRepo.findByDelStatusAndIsUsed(0, 0); return list; }
	 * 
	 * @Override public List<OfferHeader> getFrOfferConfigList(int compId) {
	 * List<OfferHeader> offerList =
	 * offerHeadRepo.findByCompIdAndDelStatusOrderByOfferIdDesc(compId, 0); return
	 * offerList; }
	 * 
	 * @Override public int deleteFrOfferConfig(int frOfferConfigId) { int res =
	 * frOfferConfig.deleteOfferConfigurationById(frOfferConfigId); return res; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Override public List<Item> getAllProductItems() { List<Item> itemList =
	 * itemRepo.getAllItems(); return itemList; }
	 * 
	 * @Override public List<Item> getAllProductItemsById(int itemId) { List<Item>
	 * itemList = itemRepo.getAllItemsById(itemId); return itemList; }
	 * 
	 * @Override public ConfigRelatedProduct getProductById(int itemId) {
	 * ConfigRelatedProduct item =
	 * configProductRepo.findByProductIdAndDelStatus(itemId, 0); return item; }
	 * 
	 * @Override public ConfigRelatedProduct
	 * insertRelatedProductConfig(ConfigRelatedProduct product) {
	 * ConfigRelatedProduct res = configProductRepo.save(product); return res; }
	 * 
	 * @Override public int udateRelatedProductConfig(String relatedItemIds, int
	 * productId, String updateDatTime, int userId) { int res =
	 * configProductRepo.updateConfigRelateProduct(productId, relatedItemIds,
	 * userId, updateDatTime); return res; }
	 * 
	 * @Override public int deleteRelProductConfig(int relatedProductId) { int res =
	 * configProductRepo.deleteRelateProductById(relatedProductId); return res; }
	 * 
	 * @Override public List<GetProductRelatedList>
	 * getAllRelatedProductsByCompId(int compId) { List<GetProductRelatedList> list
	 * = getRelatedProductListRepo.getAllConfigureRelatedProducts(compId); return
	 * list; }
	 * 
	 *//******************************************************************************//*
																						 * @Override public List<Agent>
																						 * getAllAgentsByComp(int
																						 * compId) { List<Agent> list =
																						 * agentRepo.
																						 * findByCompanyIdAndDelStatusOrderByAgentIdDesc
																						 * (compId, 0); return list; }
																						 * 
																						 * @Override public Agent
																						 * getAgentById(int agentId, int
																						 * compId) { Agent agent =
																						 * agentRepo.
																						 * findByAgentIdAndCompanyIdAndDelStatus
																						 * (agentId, compId, 0); return
																						 * agent; }
																						 * 
																						 * @Override public Agent
																						 * insertAgent(Agent agent) {
																						 * Agent savAgnt =
																						 * agentRepo.save(agent); return
																						 * savAgnt; }
																						 * 
																						 * @Override public int
																						 * deleteAgent(int agentId) {
																						 * int res =
																						 * agentRepo.deleteAgent(agentId
																						 * ); return res; }
																						 * 
																						 * @Override public Agent
																						 * getAgentByMobileNo(String
																						 * mobile) { Agent agent =
																						 * agentRepo.
																						 * findByMobileNoAndDelStatus(
																						 * mobile, 0); return agent; }
																						 */

}
