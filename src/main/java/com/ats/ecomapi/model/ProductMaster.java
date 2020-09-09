package com.ats.ecomapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_product")
public class ProductMaster {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	private String productCode;
	private String productName;
	
	private int prodCatId;
	private int prodSubCatId;
	
	private int taxId;
	private int sortId;
	private int delStatus;
	
	private int minQty;
	
	private int shelfLife;
	
	private int isReturnAllow;
	private float retPer;
	
	private int isActive;
	
	private int uomId;
	
	private String shortName;
	private int shapeId;
	
	private int allowSameDayDelivery;
	
	private String sameDayTimeAllowedSlot;
	private int prodTypeId;
	
	private String availInWeights; 
	
	private String flavourIds;
	
	private int prodStatusId;
	
	private int bookBefore;
	
	private String eventsIds;
	
	private int isCharLimit;
	private int noOfCharsForAlphaCake;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
