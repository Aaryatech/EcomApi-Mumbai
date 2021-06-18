//Mahendra
package com.ats.ecomapi.master.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="home_page_status_header")
public class ProductHomPage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "home_page_status_id")
	private int homePageStatusId;
	
	@Column(name = "company_id")
	private int companyId;
	
	@Column(name = "status_id")
	private int statusId;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "sort_no")
	private int sortNo;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "ex_int1")
	private int exInt1;
	
	@Column(name = "ex_int2")
	private int exInt2;
	
	@Column(name = "ex_var1")
	private String exVar1;
	
	@Column(name = "ex_var2")
	private String exVar2;
	
	@Column(name = "ex_var3")
	private String exVar3;
	
	@Transient
	List<ProductHomePageDetail> prdctHomeList;
	
	public int getHomePageStatusId() {
		return homePageStatusId;
	}

	public void setHomePageStatusId(int homePageStatusId) {
		this.homePageStatusId = homePageStatusId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
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

	public List<ProductHomePageDetail> getPrdctHomeList() {
		return prdctHomeList;
	}

	public void setPrdctHomeList(List<ProductHomePageDetail> prdctHomeList) {
		this.prdctHomeList = prdctHomeList;
	}
	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	@Override
	public String toString() {
		return "ProductHomPage [homePageStatusId=" + homePageStatusId + ", companyId=" + companyId + ", statusId="
				+ statusId + ", productId=" + productId + ", isActive=" + isActive + ", sortNo=" + sortNo
				+ ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", prdctHomeList=" + prdctHomeList + "]";
	}

}
