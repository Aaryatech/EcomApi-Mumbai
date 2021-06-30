package com.ats.ecomapi.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.ecomapi.master.model.GetOrderDetailDisplay;

public interface OrderDetailListRepo extends JpaRepository<GetOrderDetailDisplay, Integer> {

	@Query(value="  SELECT\n" + 
			"        UUID() AS id,\n" + 
			"        t1.*,\n" + 
			"        COALESCE(t2.product_name,\n" + 
			"        '') AS item_name,\n" +
			"        COALESCE(t2.item_pic,\n" + 
			"        '') AS item_pic,\n" +
			"        COALESCE(t2.cat_id,\n" + 
			"        0) AS cat_id,\n" + 
			"        COALESCE(t2.cat_name,\n" + 
			"        '') AS cat_name,\n" + 
			"        COALESCE(t2.uom_name,\n" + 
			"        '') AS item_uom,\n" + 
			"        COALESCE(t2.uom_id,\n" + 
			"        0) AS uom_id     \n" + 
			"    FROM\n" + 
			"        (      SELECT\n" + 
			"            d.order_detail_id,\n" + 
			"            d.order_id,\n" + 
			"            d.item_id,\n" + 
			"            d.hsn_code,\n" + 
			"            d.ex_float3 AS qty,\n" + 
			"            d.mrp,\n" + 
			"            d.rate,\n" + 
			"            d.taxable_amt,\n" + 
			"            d.cgst_per,\n" + 
			"            d.sgst_per,\n" + 
			"            d.igst_per,\n" + 
			"            d.cgst_amt,\n" + 
			"            d.sgst_amt,\n" + 
			"            d.igst_amt,\n" + 
			"            d.disc_amt,\n" + 
			"            d.tax_amt,\n" + 
			"            d.total_amt,\n" + 
			"            d.del_status,\n" + 
			"            d.remark,\n" + 
			"            d.ex_int1,\n" + 
			"            d.ex_int2,\n" + 
			"            d.ex_int3,\n" + 
			"            d.ex_int4,\n" + 
			"            d.ex_var1,\n" + 
			"            d.ex_var2,\n" + 
			"            d.ex_var3,\n" + 
			"            d.ex_var4,\n" + 
			"            d.ex_float1,\n" + 
			"            d.ex_float2,\n" + 
			"            d.ex_float3,\n" + 
			"            d.ex_float4         \n" + 
			"        FROM\n" + 
			"            tn_order_header h,\n" + 
			"            tn_order_detail d               \n" + 
			"        WHERE\n" + 
			"            h.del_status = 1              \n" + 
			"            AND d.del_status = 1          \n" + 
			"            AND d.order_id=h.order_id \n" + 
			"            AND h.ex_int1=:compId) t1      \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                i.product_id,\n" + 
			"                i.product_name,\n" + 
			"                SUBSTRING_INDEX(i.prod_image_primary, ',', 1) AS item_pic,\n" + 
			"                i.prod_cat_id AS cat_id,\n" + 
			"                u.uom_name,\n" + 
			"                u.uom_id,\n" + 
			"                c.cat_name                   \n" + 
			"            FROM\n" + 
			"                m_product i,\n" + 
			"                m_uom u,\n" + 
			"                m_category c                   \n" + 
			"            WHERE\n" + 
			"                i.del_status = 1               \n" + 
			"                AND i.uom_id = u.uom_id                  \n" + 
			"                AND i.prod_cat_id = c.cat_id               \n" + 
			"                and i.company_id=:compId        \n" + 
			"        ) t2              \n" + 
			"            ON      t1.item_id = t2.product_id",nativeQuery=true)
	List<GetOrderDetailDisplay> getOrderDetailsyBillNo(@Param("compId") int compId);
	
	
	
//	SELECT\n" + 
//	"        UUID() AS id,\n" + 
//	"        t1.*,\n" + 
//	"        COALESCE(t2.product_name,\n" + 
//	"        '') AS item_name,\n" +
//	"        COALESCE(t2.item_pic,\n" + 
//	"        '') AS item_pic,\n" +
//	"        COALESCE(t2.cat_id,\n" + 
//	"        0) AS cat_id,\n" + 
//	"        COALESCE(t2.cat_name,\n" + 
//	"        '') AS cat_name,\n" + 
//	"        COALESCE(t2.uom_name,\n" + 
//	"        '') AS item_uom,\n" + 
//	"        COALESCE(t2.uom_id,\n" + 
//	"        0) AS uom_id     \n" + 
//	"    FROM\n" + 
//	"        (      SELECT\n" + 
//	"            d.order_detail_id,\n" + 
//	"            d.order_id,\n" + 
//	"            d.item_id,\n" + 
//	"            d.hsn_code,\n" + 
//	"            d.qty,\n" + 
//	"            d.mrp,\n" + 
//	"            d.rate,\n" + 
//	"            d.taxable_amt,\n" + 
//	"            d.cgst_per,\n" + 
//	"            d.sgst_per,\n" + 
//	"            d.igst_per,\n" + 
//	"            d.cgst_amt,\n" + 
//	"            d.sgst_amt,\n" + 
//	"            d.igst_amt,\n" + 
//	"            d.disc_amt,\n" + 
//	"            d.tax_amt,\n" + 
//	"            d.total_amt,\n" + 
//	"            d.del_status,\n" + 
//	"            d.remark,\n" + 
//	"            d.ex_int1,\n" + 
//	"            d.ex_int2,\n" + 
//	"            d.ex_int3,\n" + 
//	"            d.ex_int4,\n" + 
//	"            d.ex_var1,\n" + 
//	"            d.ex_var2,\n" + 
//	"            d.ex_var3,\n" + 
//	"            d.ex_var4,\n" + 
//	"            d.ex_float1,\n" + 
//	"            d.ex_float2,\n" + 
//	"            d.ex_float3,\n" + 
//	"            d.ex_float4         \n" + 
//	"        FROM\n" + 
//	"            tn_order_header h,\n" + 
//	"            tn_order_detail d               \n" + 
//	"        WHERE\n" + 
//	"            h.del_status = 1              \n" + 
//	"            AND d.del_status = 1          \n" + 
//	"            AND d.order_id=h.order_id \n" + 
//	"            AND h.ex_int1=:compId) t1      \n" + 
//	"    LEFT JOIN\n" + 
//	"        (\n" + 
//	"            SELECT\n" + 
//	"                i.product_id,\n" + 
//	"                i.product_name,\n" + 
//	"                SUBSTRING_INDEX(i.prod_image_primary, ',', 1) AS item_pic,\n" + 
//	"                i.prod_cat_id AS cat_id,\n" + 
//	"                u.uom_name,\n" + 
//	"                u.uom_id,\n" + 
//	"                c.cat_name                   \n" + 
//	"            FROM\n" + 
//	"                m_product i,\n" + 
//	"                m_uom u,\n" + 
//	"                m_category c                   \n" + 
//	"            WHERE\n" + 
//	"                i.del_status = 1               \n" + 
//	"                AND i.uom_id = u.uom_id                  \n" + 
//	"                AND i.prod_cat_id = c.cat_id               \n" + 
//	"                and i.company_id=:compId        \n" + 
//	"        ) t2              \n" + 
//	"            ON      t1.item_id = t2.product_id
	
}
