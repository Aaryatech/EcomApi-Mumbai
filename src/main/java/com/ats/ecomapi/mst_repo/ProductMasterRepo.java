package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.ProductMaster;

public interface ProductMasterRepo extends JpaRepository<ProductMaster, Integer>{

	List<ProductMaster> findByProdCatIdAndDelStatus(int catId, int i);

	List<ProductMaster> findByDelStatus(int i);

	List<ProductMaster> findByDelStatusAndProdCatId(int i, int catId);

	List<ProductMaster> findByDelStatusAndCompanyId(int i, int compId);
	
	@Query(value="SELECT GROUP_CONCAT(same_day_time_allowed_slot) AS filterIds FROM `m_product`", nativeQuery=true)
	public String getTimeSlotFilterIds();
	
	
	@Query(value="SELECT GROUP_CONCAT(flavour_ids) AS filterIds FROM `m_product`", nativeQuery=true)
	public String getFlavourFilterIds();
	
	@Query(value="SELECT GROUP_CONCAT(events_ids) AS filterIds FROM `m_product`", nativeQuery=true)
	public String getEventFilterIds();
	
	@Query(value="SELECT GROUP_CONCAT(applicable_tags) AS filterIds FROM `m_product`", nativeQuery=true)
	public String getTagFilterIds();

	/*------------------------------------------------------------------------*/
	
	@Query(value="SELECT * FROM `m_product` WHERE NOT FIND_IN_SET (:filterId, applicable_tags)", nativeQuery=true)
	List<ProductMaster> getProductsNoTags(@Param("filterId") int filterId);

	@Query(value="SELECT * FROM `m_product` WHERE NOT FIND_IN_SET (:filterId, events_ids)", nativeQuery=true)
	List<ProductMaster> getProductsNoEvents(@Param("filterId") int filterId);

	@Query(value="SELECT * FROM `m_product` WHERE NOT FIND_IN_SET (:filterId, flavour_ids)", nativeQuery=true)
	List<ProductMaster> getProductsNoFlavours(@Param("filterId") int filterId);

	@Query(value="SELECT * FROM `m_product` WHERE NOT FIND_IN_SET (:filterId, same_day_time_allowed_slot)", nativeQuery=true)
	List<ProductMaster> getProductsNoTimeSlots(@Param("filterId") int filterId);

	/*------------------------------------------------------------------------*/
	
	@Query(value="SELECT * FROM `m_product` WHERE FIND_IN_SET (:filterId, applicable_tags)", nativeQuery=true)
	List<ProductMaster> getProductsTags(@Param("filterId") int filterId);

	@Query(value="SELECT * FROM `m_product` WHERE FIND_IN_SET (:filterId, events_ids)", nativeQuery=true)
	List<ProductMaster> getProductsEvents(@Param("filterId") int filterId);

	@Query(value="SELECT * FROM `m_product` WHERE FIND_IN_SET (:filterId, flavour_ids)", nativeQuery=true)
	List<ProductMaster> getProductsFlavours(@Param("filterId") int filterId);

	@Query(value="SELECT * FROM `m_product` WHERE FIND_IN_SET (:filterId, same_day_time_allowed_slot)", nativeQuery=true)
	List<ProductMaster> getProductsTimeSlots(@Param("filterId") int filterId);
	
	/*-------------------------------------------------------------------------*/
	@Transactional
	@Modifying
	@Query(value="UPDATE\n" + 
			"    `m_product` SET same_day_time_allowed_slot=\n" + 
			" CASE WHEN \n" + 
			"    same_day_time_allowed_slot != '' THEN   CONCAT(same_day_time_allowed_slot, ',', :filterId) ELSE  :filterId\n" + 
			"END\n" + 
			"WHERE\n" + 
			"    product_id IN(:prdctIdsStr)",nativeQuery=true)
	int getConfigProductsTimeSlots(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value="UPDATE\n" + 
			"    `m_product` SET flavour_ids=\n" + 
			" CASE WHEN \n" + 
			"    flavour_ids != '' THEN   CONCAT(flavour_ids, ',', :filterId) ELSE  :filterId\n" + 
			"END\n" + 
			"WHERE\n" + 
			"    product_id IN(:prdctIdsStr)",nativeQuery=true)
	int getConfigProductsFlavours(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value="UPDATE\n" + 
			"    `m_product` SET events_ids=\n" + 
			" CASE WHEN \n" + 
			"    events_ids != '' THEN   CONCAT(events_ids, ',', :filterId) ELSE  :filterId\n" + 
			"END\n" + 
			"WHERE\n" + 
			"    product_id IN(:prdctIdsStr)",nativeQuery=true)
	int getConfigProductsEvents(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value="UPDATE\n" + 
			"    `m_product` SET applicable_tags=\n" + 
			" CASE WHEN \n" + 
			"    applicable_tags != '' THEN   CONCAT(applicable_tags, ',', :filterId) ELSE  :filterId\n" + 
			"END\n" + 
			"WHERE\n" + 
			"    product_id IN(:prdctIdsStr)",nativeQuery=true)
	int getConfigProductsTags(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);
	
	/*----------------------------------------------------------------------------------------------------*/
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_product SET same_day_time_allowed_slot = TRIM(BOTH ',' FROM REPLACE (REPLACE(same_day_time_allowed_slot, :filterId, ''),',,',',')) WHERE product_id IN(:prdctIdsStr)",nativeQuery=true)
	int unconfigProductTimeSlots(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_product SET flavour_ids = TRIM(BOTH ',' FROM REPLACE (REPLACE(flavour_ids, :filterId, ''),',,',',')) WHERE product_id IN(:prdctIdsStr)",nativeQuery=true)
	int unconfigProductFlavour(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_product SET events_ids = TRIM(BOTH ',' FROM REPLACE (REPLACE(events_ids, :filterId, ''),',,',',')) WHERE product_id IN(:prdctIdsStr)",nativeQuery=true)
	int unconfigProductEvents(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_product SET applicable_tags = TRIM(BOTH ',' FROM REPLACE (REPLACE(applicable_tags, :filterId, ''),',,',',')) WHERE product_id IN(:prdctIdsStr)",nativeQuery=true)
	int unconfigProductTags(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);
	
}
