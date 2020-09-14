package com.ats.ecomapi.master.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_franchise")
public class Franchise {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	//Franchise Personal Details//////////////////////////////
	@Column(name = "fr_id")
	private int frId;

	@Column(name = "fr_code")
	private String frCode;
	
	@Column(name = "fr_name")
	private String frName;
	
	@Column(name = "fr_address")
	private String frAddress;
	
	@Column(name = "opening_date")
	private Date openingDate;
	
	@Column(name = "fr_image")
	private String frImage;
	
	@Column(name = "fr_rating")
	private float frRating;
	
	@Column(name = "fr_city")
	private String frCity;
	
	@Column(name = "fr_email_id")
	private String frEmailId;
	
	@Column(name = "fr_contact_no")
	private String frContactNo;
	
	
	//Franchise Other Details	/////////////////////////
	@Column(name = "fr_password")
	private int frPassword;

	@Column(name = "fda_number")
	private String fdaNumber;
	
	@Column(name = "gst_type")
	private String gstType;
	
	@Column(name = "gst_number")
	private String gstNumber;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "owners_birth_day")
	private Date ownersBirthDay;
	
	@Column(name = "fda_license_date_exp")
	private Date fdaLicenseDateExp;
	
	@Column(name = "shops_latitude")
	private float shopsLatitude;
	
	@Column(name = "shops_logitude")
	private float shopsLogitude;
	
	@Column(name = "is_active")
	private int isActive;
	
	
		
	//Franchise Bank Details	///////////////////////////
	@Column(name = "del_status")
	private String del_status;
	
	@Column(name = "company_id")
	private int company_id;

	@Column(name = "pan_no")
	private String pan_no;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "co_bank_name")
	private Date co_bank_name;
	
	@Column(name = "co_bank_branch_name")
	private String co_bank_branch_name;
	
	@Column(name = "co_bank_ifsc_code")
	private float co_bank_ifsc_code;
	
	@Column(name = "co_bank_acc_no")
	private String co_bank_acc_no;
	
	
	
	
}
