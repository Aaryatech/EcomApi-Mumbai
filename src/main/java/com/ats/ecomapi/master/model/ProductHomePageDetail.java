//Mahendra
package com.ats.ecomapi.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="home_page_status_detail")
public class ProductHomePageDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "hp_status_detail_id")
	private int hpStatusDetailId;
	
	@Column(name = "home_page_status_id")
	private int homePageStatusId;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "sort_no")
	private int sortNo;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;

	public int getHpStatusDetailId() {
		return hpStatusDetailId;
	}

	public void setHpStatusDetailId(int hpStatusDetailId) {
		this.hpStatusDetailId = hpStatusDetailId;
	}

	public int getHomePageStatusId() {
		return homePageStatusId;
	}

	public void setHomePageStatusId(int homePageStatusId) {
		this.homePageStatusId = homePageStatusId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	@Override
	public String toString() {
		return "ProductHomePageDetail [hpStatusDetailId=" + hpStatusDetailId + ", homePageStatusId=" + homePageStatusId
				+ ", productId=" + productId + ", sortNo=" + sortNo + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	
}
