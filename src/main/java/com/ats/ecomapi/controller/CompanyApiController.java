package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.master.model.BannerPage;
import com.ats.ecomapi.master.model.GetRouteList;
import com.ats.ecomapi.master.model.Route;
import com.ats.ecomapi.master.model.RouteDelivery;
import com.ats.ecomapi.master.model.RouteType;
import com.ats.ecomapi.master.model.SubCategory;
import com.ats.ecomapi.master.repo.BannerPageRepo;
import com.ats.ecomapi.master.repo.GetCustomerInfoRepo;
import com.ats.ecomapi.master.repo.GetRouteListRepo;
import com.ats.ecomapi.master.repo.RouteDeliveryRepo;
import com.ats.ecomapi.master.repo.RouteRepo;
import com.ats.ecomapi.master.repo.RouteTypeRepo;
import com.ats.ecomapi.master.repo.SubCategoryRepo;
import com.ats.ecomapi.mst_model.CompMaster;
import com.ats.ecomapi.mst_model.Customer;
import com.ats.ecomapi.mst_model.CustomerAddDetail;
import com.ats.ecomapi.mst_model.GetCustomerInfo;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.User;
import com.ats.ecomapi.mst_repo.CompMasterRepo;
import com.ats.ecomapi.mst_repo.CustomerAddDetailRepo;
import com.ats.ecomapi.mst_repo.CustomerRepo;
import com.ats.ecomapi.mst_repo.ProductMasterRepo;

@RestController
public class CompanyApiController {

	@Autowired
	CompMasterRepo compMasterRepo;

	@Autowired
	ProductMasterRepo productMasterRepo;
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Save Company

	@RequestMapping(value = { "/saveCompany" }, method = RequestMethod.POST)
	public @ResponseBody CompMaster saveCompany(@RequestBody CompMaster comp) {
		//System.err.println("CompMaster***" + comp.toString());

		CompMaster save = new CompMaster();
		try {
			save = compMasterRepo.save(comp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Specific Company

	@RequestMapping(value = { "/getCompanyByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody CompMaster getCompanyByCompanyId(@RequestParam int compId) {

		CompMaster comp = new CompMaster();
		try {
			comp = compMasterRepo.findByCompanyId(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comp;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Comapny List

	@RequestMapping(value = { "/getAllCompany" }, method = RequestMethod.GET)
	public @ResponseBody List<CompMaster> getAllCompany() {

		List<CompMaster> list = new ArrayList<CompMaster>();
		try {
			list = compMasterRepo.findByDelStatusOrderByCompanyIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Company

	@RequestMapping(value = { "/deleteCompById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteCompById(@RequestParam int compId,@RequestParam int userId,@RequestParam String dateTime) {

		Info info = new Info();
		try {
			int res = compMasterRepo.deleteCompany(compId,userId,dateTime);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Company Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Company");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@Autowired
	CustomerRepo customerRepo;

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Save Customer

	@RequestMapping(value = { "/saveCustomer" }, method = RequestMethod.POST)
	public @ResponseBody Customer saveCustomer(@RequestBody Customer cust) {

		Customer addCust = new Customer();
		try {
			addCust = customerRepo.save(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addCust;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Specific Customer

	@RequestMapping(value = { "/getCustById" }, method = RequestMethod.POST)
	public @ResponseBody Customer getCustById(@RequestParam int custId) {

		Customer cust = new Customer();
		try {
			cust = customerRepo.findByCustId(custId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cust;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Customer List

	@RequestMapping(value = { "/getAllCustomer" }, method = RequestMethod.GET)
	public @ResponseBody List<Customer> getAllCustomer() {

		List<Customer> list = new ArrayList<Customer>();
		try {
			list = customerRepo.findByDelStatusOrderByCustIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Customer

	@RequestMapping(value = { "/deleteCustById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteCustById(@RequestParam int custId,@RequestParam int userId,@RequestParam String dateTime) {

		Info info = new Info();
		try {
			int res = customerRepo.deleteCustomer(custId,userId,dateTime);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Customer Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Customer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@Autowired
	CustomerAddDetailRepo customerAddDetailRepo;

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Save Customer Address Detail

	@RequestMapping(value = { "/saveCustomerDet" }, method = RequestMethod.POST)
	public @ResponseBody CustomerAddDetail saveCustomerDet(@RequestBody CustomerAddDetail custDet) {

		CustomerAddDetail addCustDet = new CustomerAddDetail();
		try {
			addCustDet = customerAddDetailRepo.save(custDet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addCustDet;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Specific Customer Address Detail

	@RequestMapping(value = { "/getCustDetById" }, method = RequestMethod.POST)
	public @ResponseBody CustomerAddDetail getCustDetById(@RequestParam int custDetId) {

		CustomerAddDetail cust = new CustomerAddDetail();
		try {
			cust = customerAddDetailRepo.findByCustDetailId(custDetId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cust;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Customer Address Detail List

	@RequestMapping(value = { "/getAllCustomerDetailByCustId" }, method = RequestMethod.POST)
	public @ResponseBody List<CustomerAddDetail> getAllCustomerDetail(@RequestParam int custId) {

		List<CustomerAddDetail> list = new ArrayList<CustomerAddDetail>();
		try {
			list = customerAddDetailRepo.findByDelStatusAndCustIdOrderByCustDetailIdDesc(1, custId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Customer Address Detail

	@RequestMapping(value = { "/deleteCustDetById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteCustDetById(@RequestParam int custDetId,@RequestParam int userId,@RequestParam String dateTime) {

		Info info = new Info();
		try {
			int res = customerAddDetailRepo.deleteCustDet(custDetId,userId,dateTime);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Customer Address Detail Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Customer Address Detail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@Autowired
	GetCustomerInfoRepo getCustomerInfoRepo;

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Customer Address Detail List

	@RequestMapping(value = { "/getAllCustomerDetailInfo" }, method = RequestMethod.GET)
	public @ResponseBody List<GetCustomerInfo> getAllCustomerDetailInfo() {

		List<GetCustomerInfo> list = new ArrayList<GetCustomerInfo>();
		try {
			list = getCustomerInfoRepo.getCustList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Check unique Cust mobile

	@RequestMapping(value = { "/getCustByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody Customer getUserByMobNo(@RequestParam String mobNo, @RequestParam int userId) {

		Customer user = new Customer();
		try {
			if (userId == 0) {

				user = customerRepo.findByCustMobileNoAndDelStatus(mobNo, 1);
			} else {

				user = customerRepo.findByCustMobileNoAndDelStatusAndCustIdNot(mobNo, 1, userId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// *********************subCat*******************

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Save SubCategory

	@Autowired
	SubCategoryRepo subCatRepo;

	@RequestMapping(value = { "/saveSubCat" }, method = RequestMethod.POST)
	public @ResponseBody SubCategory saveCustomer(@RequestBody SubCategory subCat) {

		SubCategory addSubCat = new SubCategory();
		try {
			addSubCat = subCatRepo.save(subCat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addSubCat;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get SubCategory Customer
	@RequestMapping(value = { "/getSubCatById" }, method = RequestMethod.POST)
	public @ResponseBody SubCategory getSubCatById(@RequestParam int subCatId) {

		SubCategory subCat = new SubCategory();
		try {
			subCat = subCatRepo.findBySubCatId(subCatId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subCat;

	}
	
	// Created By :- Mahendra Singh
	// Created On :- 21-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get SubCategory Count Id
	@RequestMapping(value = { "/getSubCateIdCnt" }, method = RequestMethod.POST)
	public @ResponseBody int getSubCateIdCnt(@RequestParam int compId, @RequestParam int cateId) {

		int subCateCnt = 0;
		try {
			subCateCnt = subCatRepo.getSubCateCnt(compId, cateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subCateCnt;

	}

	// Created By :- Mahendra Singh
	// Created On :- 21-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Check Unique Sub Category Prefix
	@RequestMapping(value = { "/unqSubCatePrefix" }, method = RequestMethod.POST)
	public @ResponseBody Info unqSubCatePrefix(@RequestParam String prefix, @RequestParam int compId, @RequestParam int subCateId) {

		Info info = new Info();
		try {
			SubCategory res = new SubCategory();
			if(subCateId>0) {
				res = subCatRepo.findBySubCatPrefixIgnoreCaseAndCompanyIdAndSubCatIdNot(prefix, compId, subCateId);
			}else {
				res = subCatRepo.findBySubCatPrefixIgnoreCaseAndCompanyId(prefix, compId);
			}
			
			if (res!=null) {
				info.setError(true);
				info.setMessage("Prefix Found");
			} else {
				info.setError(false);
				info.setMessage("Prefix Not Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;

	}
	
	@RequestMapping(value = { "/getProdIdCntBySubCatId" }, method = RequestMethod.POST)
	public @ResponseBody int getProdIdCntBySubCatId(@RequestParam int subCatId) {

		int pordCnt = 0;
		try {
			pordCnt = productMasterRepo.getProdCntBySubCatId(subCatId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pordCnt;
	}
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get AllSubCategoryList

	@RequestMapping(value = { "/getAllSubCat" }, method = RequestMethod.GET)
	public @ResponseBody List<SubCategory> getAllSubCat() {

		List<SubCategory> list = new ArrayList<SubCategory>();
		try {
			list = subCatRepo.getAllActiveSubcategories1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete SubCategory

	@RequestMapping(value = { "/deleteSubCatById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteSubCatById(@RequestParam int subCatId) {

		Info info = new Info();
		try {
			int res = subCatRepo.deleteSubCat(subCatId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("SubCategory Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete SubCategory");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@Autowired
	BannerPageRepo bannerPageRepo;

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NAfgfdfg
	// Modified On :- NA
	// Descriprion :- saveBanner
	@RequestMapping(value = { "/saveBanner" }, method = RequestMethod.POST)
	public @ResponseBody BannerPage saveBanner(@RequestBody BannerPage subCat) {

		BannerPage addBanner = new BannerPage();
		try {
			addBanner = bannerPageRepo.save(subCat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addBanner;

	}
//hhhh
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NAfgfdfg
	// Modified On :- NA
	// Descriprion :- Get Banner

	@RequestMapping(value = { "/getBannerById" }, method = RequestMethod.POST)
	public @ResponseBody BannerPage getBannerById(@RequestParam int bannerId) {

		BannerPage banner = new BannerPage();
		try {
			banner = bannerPageRepo.findByBannerId(bannerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return banner;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getAllBannerByCompId
	@RequestMapping(value = { "/getAllBannerByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<BannerPage> getAllBannerByCompId(@RequestParam int compId) {

		List<BannerPage> list = new ArrayList<BannerPage>();
		try {
			list = bannerPageRepo.findByCompIdAndDelStatus(compId,1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Banner

	@RequestMapping(value = { "/deleteBannerById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteBannerById(@RequestParam int bannerId,@RequestParam int userId,@RequestParam String dateTime) {

		Info info = new Info();
		try {
			int res = bannerPageRepo.deleteBanner(bannerId,userId,dateTime);
			if (res > 0) {
				info.setError(false);
				info.setMessage("BannerPage Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete BannerPage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	/* route master */
	
	@Autowired
	RouteRepo routeRepo;
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Add Route


	@RequestMapping(value = { "/saveRoute" }, method = RequestMethod.POST)
	public @ResponseBody Route saveRoute(@RequestBody Route route) {
 
		Route addComp = new Route();
		try {
			addComp = routeRepo.save(route);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addComp;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Single Route

	@RequestMapping(value = { "/getRouteById" }, method = RequestMethod.POST)
	public @ResponseBody Route getRouteById(@RequestParam int routeId) {

		Route comp = new Route();
		try {
			comp = routeRepo.findByRouteId(routeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comp;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Route


	@RequestMapping(value = { "/getAllRoute" }, method = RequestMethod.GET)
	public @ResponseBody List<Route> getAllRoute() {

		List<Route> list = new ArrayList<Route>();
		try {
			list = routeRepo.findByDelStatusOrderByRouteIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Route


	@RequestMapping(value = { "/deleteRouteById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteRouteById(@RequestParam int routeId) {

		Info info = new Info();
		try {
			int res = routeRepo.deleteRoute(routeId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Route Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Route");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	@Autowired
	GetRouteListRepo getRouteListRepo;
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Route Company Wise

	
	@RequestMapping(value = { "/getAllRouteByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetRouteList> getAllRouteByCompId(@RequestParam int compId) {

		List<GetRouteList> list = new ArrayList<GetRouteList>();
		try {
			list = getRouteListRepo.getAllRoutes(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	@Autowired
	RouteTypeRepo routeTypeRepo;
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Save Route 
	
	@RequestMapping(value = { "/saveRouteType" }, method = RequestMethod.POST)
	public @ResponseBody RouteType saveRoute(@RequestBody RouteType route) {
 
		RouteType addComp = new RouteType();
		try {
			addComp = routeTypeRepo.save(route);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addComp;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Specific Route

	@RequestMapping(value = { "/getRouteTypeById" }, method = RequestMethod.POST)
	public @ResponseBody RouteType getRouteTypeById(@RequestParam int routeId) {

		RouteType comp = new RouteType();
		try {
			comp = routeTypeRepo.findByRouteTypeId(routeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comp;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Route

	@RequestMapping(value = { "/getAllRouteType" }, method = RequestMethod.GET)
	public @ResponseBody List<RouteType> getAllRouteType() {

		List<RouteType> list = new ArrayList<RouteType>();
		try {
			list = routeTypeRepo.findByDelStatusOrderByRouteTypeIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Rote

	@RequestMapping(value = { "/deleteRouteTypeById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteRouteTypeById(@RequestParam int routeId) {

		Info info = new Info();
		try {
			int res = routeTypeRepo.deleteRouteType(routeId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Route Type Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Route Type");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	 
	
	@Autowired
	RouteDeliveryRepo routeDeliveryRepo;
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :-Save Route Delivery

	
	@RequestMapping(value = { "/saveRouteDelivery" }, method = RequestMethod.POST)
	public @ResponseBody RouteDelivery saveRouteDelivery(@RequestBody RouteDelivery route) {
 
		RouteDelivery addComp = new RouteDelivery();
		try {
			addComp = routeDeliveryRepo.save(route);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addComp;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get Specific Route Delivery

	@RequestMapping(value = { "/getRouteDeliveryById" }, method = RequestMethod.POST)
	public @ResponseBody RouteDelivery getRouteDeliveryById(@RequestParam int routeId) {

		RouteDelivery comp = new RouteDelivery();
		try {
			comp = routeDeliveryRepo.findByRouidDelveryId(routeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comp;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get All Comapny Route Delivery

	@RequestMapping(value = { "/getAllRouteDelivery" }, method = RequestMethod.GET)
	public @ResponseBody List<RouteDelivery> getAllRouteDelivery() {

		List<RouteDelivery> list = new ArrayList<RouteDelivery>();
		try {
			list = routeDeliveryRepo.findByDelStatusOrderByRouidDelveryIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 25-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Delete Route Delivery

	@RequestMapping(value = { "/deleteRouteDeliveryById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteRouteDeliveryById(@RequestParam int delvId) {

		Info info = new Info();
		try {
			int res = routeDeliveryRepo.deleteRouteDelivery(delvId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Route Delivery Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Route Delivery");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	 



}
