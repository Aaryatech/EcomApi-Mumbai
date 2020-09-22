package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.mst_model.ConfigHomePageProduct;

public interface ConfigHomePageProductRepo extends JpaRepository<ConfigHomePageProduct, Integer>{

	@Query(value="SELECT\n" + 
			"    t1.*,\n" + 
			"    COALESCE(t2.hp_status_detail_id,0) AS hp_status_detail_id,\n" + 
			"    COALESCE(t2.sort_no, 0) AS sort_no,\n" + 
			"    COALESCE(t2.is_checked,0) AS is_checked\n" + 
			"    \n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        *\n" + 
			"    FROM\n" + 
			"        m_product\n" + 
			"    WHERE\n" + 
			"        del_status = 1 AND is_active = 1 AND company_id = :compId AND prod_status_id=:statusType\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        d.*,\n" + 
			"        1 AS is_checked\n" + 
			"    FROM\n" + 
			"        home_page_status_header h,\n" + 
			"        home_page_status_detail d\n" + 
			"    WHERE\n" + 
			"        h.home_page_status_id = d.home_page_status_id AND h.status_id = :statusType AND h.del_status = 1 AND h.company_id = :compId\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.product_id = t2.product_id",nativeQuery=true)
	List<ConfigHomePageProduct> getConfigHomePagePrdctList(@Param("statusType") int statusType, @Param("compId") int compId);
}
