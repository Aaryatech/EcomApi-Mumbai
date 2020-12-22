package com.ats.ecomapi.fe_repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.fe_model.FEBannerList;

public interface FEBannerListRepo extends JpaRepository<FEBannerList, Integer> {
	
	@Query(value = " SELECT UUID() as uuid, banner_home_page.banner_id,banner_home_page.banner_event_name,banner_home_page.banner_image,banner_home_page.sort_no,banner_home_page.fr_ids,banner_home_page.tag_ids,banner_home_page.caption_onproduct_page,banner_home_page.update_date_time,banner_home_page.ex_var1 " + 
			" FROM banner_home_page WHERE del_status=1 and is_active=1 and banner_home_page.comp_id=:companyId ORDER by sort_no DESC " + 
			"", nativeQuery = true)
	
	List<FEBannerList> getBannerListByCompanyId(@Param("companyId") int companyId);

	/*
	 * @Query(value =
	 * " SELECT UUID() as uuid, banner_home_page.banner_id,banner_home_page.banner_event_name,banner_home_page.banner_image,banner_home_page.sort_no,banner_home_page.fr_ids,banner_home_page.tag_ids,banner_home_page.caption_onproduct_page,banner_home_page.update_date_time,banner_home_page.ex_var1 "
	 * +
	 * " FROM banner_home_page WHERE find_in_set(:frId,banner_home_page.fr_ids) ORDER by sort_no DESC "
	 * + " ", nativeQuery = true) List<FEBannerList>
	 * getBannerListByFrId(@Param("frId") int frId);
	 */
	
}
