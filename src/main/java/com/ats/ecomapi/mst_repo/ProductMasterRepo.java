package com.ats.ecomapi.mst_repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.ProductMaster;

public interface ProductMasterRepo extends JpaRepository<ProductMaster, Integer> {

	List<ProductMaster> findByProdCatIdAndDelStatus(int catId, int i);

	List<ProductMaster> findByProdCatIdAndDelStatusAndCompanyId(int catId, int i, int companyId);

	List<ProductMaster> findByDelStatus(int i);

	List<ProductMaster> findByDelStatusAndProdCatId(int i, int catId);

	List<ProductMaster> findByDelStatusAndCompanyId(int i, int compId);

	@Query(value = "SELECT GROUP_CONCAT(same_day_time_allowed_slot) AS filterIds FROM `m_product`", nativeQuery = true)
	public String getTimeSlotFilterIds();

	@Query(value = "SELECT GROUP_CONCAT(flavour_ids) AS filterIds FROM `m_product`", nativeQuery = true)
	public String getFlavourFilterIds();

	@Query(value = "SELECT GROUP_CONCAT(events_ids) AS filterIds FROM `m_product`", nativeQuery = true)
	public String getEventFilterIds();

	@Query(value = "SELECT GROUP_CONCAT(applicable_tags) AS filterIds FROM `m_product`", nativeQuery = true)
	public String getTagFilterIds();

	/*------------------------------------------------------------------------*/

	@Query(value = "SELECT * FROM `m_product` WHERE NOT FIND_IN_SET (:filterId, applicable_tags) AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsNoTags(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE NOT FIND_IN_SET (:filterId, events_ids) AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsNoEvents(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE NOT FIND_IN_SET (:filterId, flavour_ids) AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsNoFlavours(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE NOT FIND_IN_SET (:filterId, same_day_time_allowed_slot) AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsNoTimeSlots(@Param("filterId") int filterId, @Param("compId") int compId);

	/*------------------------------------------------------------------------*/

	@Query(value = "SELECT * FROM `m_product` WHERE FIND_IN_SET (:filterId, applicable_tags) AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsTags(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE FIND_IN_SET (:filterId, events_ids) AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsEvents(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE FIND_IN_SET (:filterId, flavour_ids) AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsFlavours(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE FIND_IN_SET (:filterId, same_day_time_allowed_slot) AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsTimeSlots(@Param("filterId") int filterId, @Param("compId") int compId);

	/*-------------------------------------------------------------------------*/
	@Transactional
	@Modifying
	@Query(value = "UPDATE\n" + "    `m_product` SET same_day_time_allowed_slot=\n" + " CASE WHEN \n"
			+ "    same_day_time_allowed_slot != '' THEN   CONCAT(same_day_time_allowed_slot, ',', :filterId) ELSE  :filterId\n"
			+ "END\n" + "WHERE\n" + "    product_id IN(:prdctIdsStr)", nativeQuery = true)
	int updtConfigProductsTimeSlots(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = "UPDATE\n" + "    `m_product` SET flavour_ids=\n" + " CASE WHEN \n"
			+ "    flavour_ids != '' THEN   CONCAT(flavour_ids, ',', :filterId) ELSE  :filterId\n" + "END\n" + "WHERE\n"
			+ "    product_id IN(:prdctIdsStr)", nativeQuery = true)
	int updtConfigProductsFlavours(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = "UPDATE\n" + "    `m_product` SET events_ids=\n" + " CASE WHEN \n"
			+ "    events_ids != '' THEN   CONCAT(events_ids, ',', :filterId) ELSE  :filterId\n" + "END\n" + "WHERE\n"
			+ "    product_id IN(:prdctIdsStr)", nativeQuery = true)
	int updtConfigProductsEvents(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = "UPDATE\n" + "    `m_product` SET applicable_tags=\n" + " CASE WHEN \n"
			+ "    applicable_tags != '' THEN   CONCAT(applicable_tags, ',', :filterId) ELSE  :filterId\n" + "END\n"
			+ "WHERE\n" + "    product_id IN(:prdctIdsStr)", nativeQuery = true)
	int updtConfigProductsTags(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	/*----------------------------------------------------------------------------------------------------*/

	@Transactional
	@Modifying
	@Query(value = "UPDATE m_product SET same_day_time_allowed_slot = TRIM(BOTH ',' FROM REPLACE (REPLACE(same_day_time_allowed_slot, :filterId, ''),',,',',')) WHERE product_id IN(:prdctIdsStr)", nativeQuery = true)
	int unconfigUpdtProductTimeSlots(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = "UPDATE m_product SET flavour_ids = TRIM(BOTH ',' FROM REPLACE (REPLACE(flavour_ids, :filterId, ''),',,',',')) WHERE product_id IN(:prdctIdsStr)", nativeQuery = true)
	int unconfigUpdtProductFlavour(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = "UPDATE m_product SET events_ids = TRIM(BOTH ',' FROM REPLACE (REPLACE(events_ids, :filterId, ''),',,',',')) WHERE product_id IN(:prdctIdsStr)", nativeQuery = true)
	int unconfigUpdtProductEvents(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = "UPDATE m_product SET applicable_tags = TRIM(BOTH ',' FROM REPLACE (REPLACE(applicable_tags, :filterId, ''),',,',',')) WHERE product_id IN(:prdctIdsStr)", nativeQuery = true)
	int unconfigUpdtProductTags(@Param("filterId") String filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	/*------------------------------------------------------------------------------------------------------------*/

	@Query(value = "SELECT * FROM `m_product` WHERE tax_id=:filterId AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsByTaxId(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE shape_id=:filterId AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsByCakeShape(@Param("filterId") int filterId, @Param("compId") int compId);

	/*------------------------------------------------------------------------------------------------------------*/

	@Query(value = "SELECT * FROM `m_product` WHERE tax_id!=:filterId AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsByNoTaxId(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE shape_id!=:filterId AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsByNoCakeShape(@Param("filterId") int filterId, @Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE is_return_allow = 1 AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getProductsByReturnPer(@Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE is_active=1 AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getActiveProducts(@Param("compId") int compId);

	@Query(value = "SELECT * FROM `m_product` WHERE is_active!=1 AND company_id=:compId", nativeQuery = true)
	List<ProductMaster> getInActiveProducts(@Param("compId") int compId);
	/*------------------------------------------------------------------------------------------------------------*/

	// Replace Product Other Filters(Tax, Cake Shape, Return % etc.)
	@Transactional
	@Modifying
	@Query(value = " UPDATE m_product SET tax_id=:filterId WHERE product_id IN (:prdctIdsStr) ", nativeQuery = true)
	int updateConfigProductsTax(@Param("filterId") int filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = " UPDATE m_product SET shape_id=:filterId WHERE product_id IN (:prdctIdsStr)", nativeQuery = true)
	int updateConfigProductsCakeShap(@Param("filterId") int filterId, @Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = " UPDATE m_product SET ret_per=:returnVal WHERE product_id IN (:prdctIdsStr)", nativeQuery = true)
	int updateConfigProductsReturnPer(@Param("returnVal") float returnVal,
			@Param("prdctIdsStr") List<Integer> prdctIdsStr);

	/*------------------------------------------------------------------------------------------------------------*/

	@Transactional
	@Modifying
	@Query(value = "UPDATE `m_product` SET tax_id=0 WHERE product_id IN (:prdctIdsStr)", nativeQuery = true)
	int updateConfigProductsRemoveTax(@Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = " UPDATE m_product SET shape_id=0 WHERE product_id IN (:prdctIdsStr)", nativeQuery = true)
	int updateConfigProductsRemoveCakeShap(List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = " UPDATE m_product SET is_active=0 WHERE product_id IN (:prdctIdsStr)", nativeQuery = true)
	int updtProductsStatusToInActive(@Param("prdctIdsStr") List<Integer> prdctIdsStr);

	@Transactional
	@Modifying
	@Query(value = " UPDATE m_product SET is_active=1 WHERE product_id IN (:prdctIdsStr)", nativeQuery = true)
	int updtProductsStatusToActive(@Param("prdctIdsStr") List<Integer> prdctIdsStr);

	// Sachin 21-09-2020
	List<ProductMaster> findByProductIdIn(List<Integer> prodIdList);

	// Mahendra 21-09-2020
	List<ProductMaster> findByProdStatusIdAndDelStatusAndIsActiveAndCompanyId(int statusId, int del, int isActive,
			int compId);

	@Query(value = "" + " SELECT " + "    prod.product_id,\n" + "    prod.product_code,\n" + "    prod.product_name,\n"
			+ "    prod.prod_cat_id,\n" + "    prod.prod_sub_cat_id,\n" + "    prod.tax_id,\n" + "    prod.sort_id,\n"
			+ "    prod.del_status,\n" + "    prod.min_qty,\n" + "    prod.shelf_life,\n"
			+ "    prod.is_return_allow,\n" + "    prod.ret_per,\n" + "    prod.is_active,\n" + "    prod.uom_id,\n"
			+ "    prod.short_name,\n" + "    prod.shape_id,\n" + "    prod.allow_same_day_delivery,\n"
			+ "    prod.same_day_time_allowed_slot,\n" + "    prod.prod_type_id,\n" + "    prod.avail_in_weights,\n"
			+ "    prod.flavour_ids,\n" + "    prod.prod_status_id,\n" + "    prod.book_before,\n"
			+ "    prod.events_ids,\n" + "    prod.is_char_limit,\n" + "    prod.no_of_chars_for_alpha_cake,\n"
			+ "    prod.allow_cover_photo_upload,\n" + "    prod.allow_base_photo_upload,\n"
			+ "    prod.allow_special_instruction,\n" + "    prod.allow_msg_on_cake,\n"
			+ "    prod.no_of_chars_on_cake,\n" + "    prod.is_used,\n" + "    prod.is_slot_used,\n"
			+ "    prod.type_of_bread,\n" + "    prod.type_of_cream,\n" + "    prod.layering_cream,\n"
			+ "    prod.topping_cream,\n" + "    prod.product_desc,\n" + "    prod.ingerdiants,\n"
			+ "    prod.applicable_tags,\n" + "    prod.company_id,\n" + "    prod.prod_image_primary,\n"
			+ "    prod.product_images,\n" + "    prod.maker_user_id,\n" + "    prod.updt_dttime,\n"
			+ "    prod.insert_dttime, " + "    prod.copy_item_id, " + "    prod.is_veg, " + "    prod.prep_time, "
			+ "    prod.rate_setting_type, " + "    prod.ex_int1, " + "    prod.ex_int2, " + "    prod.ex_int3, "
			+ "    prod.ex_var1, " + "    prod.ex_var2, " + "    prod.ex_var3, " + "    prod.ex_var4, "
			+ "    prod.ex_float1, " + "    prod.ex_float2, " + "    prod.ex_float3, " + "    prod.ex_date1, "
			+ "    prod.ex_date2, " + "    prod.max_wt " + " FROM " + "    m_product prod " + "      " + " WHERE "
			+ "    prod.del_status = 1 AND prod.product_id NOT IN "
			+ " (SELECT tn_item_config_detail.product_id FROM  tn_item_config_detail "
			+ " WHERE tn_item_config_detail.config_header_id=:configId) AND "
			+ " prod.prod_cat_id=:catId and prod.company_id=:compId ", nativeQuery = true)

	List<ProductMaster> getProdListForAddingNewItemInExConf(@Param("catId") int catId, @Param("compId") int compId, @Param("configId") int configId);

	ProductMaster findByDelStatusAndProductId(int delStatus, int productId);

	//Sachin 28-09-2020 update Product images 
	@Transactional
	@Modifying
	@Query(value="UPDATE m_product SET product_images=:filesList WHERE product_id =:productId",nativeQuery=true)
	public int updateProductImages(@Param("filesList") String filesList,@Param("productId") int productId);

	//Sachin 28-09-2020  removes a Product image from list of images
	@Transactional
	@Modifying
	@Query(value="UPDATE m_product SET product_images = TRIM(BOTH ',' FROM REPLACE(REPLACE(product_images,:imageName, ''), ',,', ',')) WHERE product_id=:productId",nativeQuery=true)
	public int removeImageFromProduct(@Param("imageName") String imageName,@Param("productId") int productId);
	
	@Query(value="SELECT COUNT(product_id) FROM `m_product` WHERE del_status=1 AND prod_cat_id=:catId",nativeQuery=true)
	int getProdCntByCatId(@Param("catId") int catId);
	
	
	@Query(value="SELECT COUNT(product_id) FROM `m_product` WHERE prod_sub_cat_id=:subCatId",nativeQuery=true)
	int getProdCntBySubCatId(@Param("subCatId") int subCatId);
	
	@Query(value="SELECT COUNT(product_id) FROM `m_product` WHERE tax_id=:taxId",nativeQuery=true)
	int getProdCntByTaxId(@Param("taxId") int taxId);
	
}
