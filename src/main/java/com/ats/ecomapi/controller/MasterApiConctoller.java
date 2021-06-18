package com.ats.ecomapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.fe_model.FrSetting;
import com.ats.ecomapi.fe_repo.FrSettingRepo;
import com.ats.ecomapi.master.model.Area;
import com.ats.ecomapi.master.model.AreaCityList;
import com.ats.ecomapi.master.model.Category;
import com.ats.ecomapi.master.model.City;
import com.ats.ecomapi.master.model.DeliveryInstruction;
import com.ats.ecomapi.master.model.Designation;
import com.ats.ecomapi.master.model.FilterTypes;
import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.master.model.GetHomePageTestimonial;
import com.ats.ecomapi.master.model.GrievencesInstruction;
import com.ats.ecomapi.master.model.GrievencesTypeInstructn;
import com.ats.ecomapi.master.model.HomePageTestimonial;
import com.ats.ecomapi.master.model.Language;
import com.ats.ecomapi.master.model.MFilter;
import com.ats.ecomapi.master.model.ProductHomPage;
import com.ats.ecomapi.master.model.ProductHomePageDetail;
import com.ats.ecomapi.master.model.SpDayHomePage;
import com.ats.ecomapi.master.model.SpDayHomePageExlPdf;
import com.ats.ecomapi.master.model.SubCategory;
import com.ats.ecomapi.master.model.Tax;
import com.ats.ecomapi.master.model.Uom;
import com.ats.ecomapi.master.repo.AreaCityListRepo;
import com.ats.ecomapi.master.repo.AreaRepo;
import com.ats.ecomapi.master.repo.CategoryRepo;
import com.ats.ecomapi.master.repo.CityRepo;
import com.ats.ecomapi.master.repo.ConfigHomePageProductRepo;
import com.ats.ecomapi.master.repo.DeliveryInstructionRepo;
import com.ats.ecomapi.master.repo.DesignationRepo;
import com.ats.ecomapi.master.repo.ExcelHomePageTestimonialRepo;
import com.ats.ecomapi.master.repo.FilterTypesRepo;
import com.ats.ecomapi.master.repo.FranchiseRepo;
import com.ats.ecomapi.master.repo.GrievencesInstructionRepo;
import com.ats.ecomapi.master.repo.GrievencesTypeInstructnRepo;
import com.ats.ecomapi.master.repo.HomePageTestimonialRepo;
import com.ats.ecomapi.master.repo.LanguageRepo;
import com.ats.ecomapi.master.repo.MFilterRepo;
import com.ats.ecomapi.master.repo.ProductHomPageRepo;
import com.ats.ecomapi.master.repo.ProductHomePageDetailRepo;
import com.ats.ecomapi.master.repo.SpDayHomePageExlPdfRepo;
import com.ats.ecomapi.master.repo.SpDayHomePageRepo;
import com.ats.ecomapi.master.repo.SubCategoryRepo;
import com.ats.ecomapi.master.repo.TaxRepo;
import com.ats.ecomapi.master.repo.UomRepo;
import com.ats.ecomapi.master.repo.UserTypeRepo;
import com.ats.ecomapi.mst_model.ConfigHomePageProduct;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.ProductMaster;
import com.ats.ecomapi.mst_model.User;
import com.ats.ecomapi.mst_model.UserTypeMaster;
import com.ats.ecomapi.mst_repo.ProductMasterRepo;
import com.ats.ecomapi.mst_repo.UserRepo;

@RestController
public class MasterApiConctoller {

	@Autowired
	UomRepo uomRepo;

	@Autowired
	TaxRepo taxRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserTypeRepo userTypeRepo;

	@Autowired
	CategoryRepo catRepo;

	@Autowired
	FilterTypesRepo filterTypeRepo;

	@Autowired
	MFilterRepo filterRepo;

	@Autowired
	SubCategoryRepo subCatRepo;

	@Autowired
	FranchiseRepo frRepo;

	@Autowired
	LanguageRepo langRepo;

	@Autowired
	CityRepo cityRepo;

	@Autowired
	AreaRepo areaRepo;

	@Autowired
	AreaCityListRepo areaCityRepo;

	@Autowired
	DeliveryInstructionRepo delvInstuctRepo;

	@Autowired
	GrievencesTypeInstructnRepo grievTypeInstructRepo;

	@Autowired
	GrievencesInstructionRepo grievanceRepo;

	@Autowired
	ProductMasterRepo productMstrRepo;

	@Autowired
	SpDayHomePageRepo spDayHomePageRepo;

	@Autowired
	ProductHomPageRepo prdctHomeRepo;

	@Autowired
	ProductHomePageDetailRepo prdctHomePageDtlRepo;

	@Autowired
	ConfigHomePageProductRepo configPrdctHomRepo;

	@Autowired
	HomePageTestimonialRepo testimonialRepo;

	@Autowired
	DesignationRepo desigRepo;

	@Autowired
	ProductMasterRepo productMasterRepo;

	@Autowired
	FrSettingRepo frSettingRepo;

	/*----------------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get UOM List
	@RequestMapping(value = { "/getUoms" }, method = RequestMethod.POST)
	public @ResponseBody List<Uom> getUoms(@RequestParam int compId) {

		List<Uom> list = new ArrayList<Uom>();
		try {
			list = uomRepo.findByDelStatusAndCompanyIdOrderByUomIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save UOM
	@RequestMapping(value = { "/saveUom" }, method = RequestMethod.POST)
	public @ResponseBody Uom saveUom(@RequestBody Uom uom) {

		Uom addUom = new Uom();
		try {
			addUom = uomRepo.save(uom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addUom;

	}

	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Single UOM
	@RequestMapping(value = { "/getUomById" }, method = RequestMethod.POST)
	public @ResponseBody Uom getUomById(@RequestParam int uomId) {

		Uom uom = new Uom();
		try {
			uom = uomRepo.findByUomId(uomId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uom;

	}

	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete UOM
	@RequestMapping(value = { "/deleteUomById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteUomById(@RequestParam int uomId) {

		Info info = new Info();
		try {
			int res = uomRepo.deleteUom(uomId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("UOM Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete UOM");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Tax List
	@RequestMapping(value = { "/getTaxes" }, method = RequestMethod.POST)
	public @ResponseBody List<Tax> getTaxes(@RequestParam int compId) {

		List<Tax> list = new ArrayList<Tax>();
		try {
			list = taxRepo.findByDelStatusAndCompanyIdOrderByTaxIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Active Tax List
	@RequestMapping(value = { "/getActiveTaxes" }, method = RequestMethod.POST)
	public @ResponseBody List<Tax> getActiveTaxes(@RequestParam int compId) {

		List<Tax> list = new ArrayList<Tax>();
		try {
			list = taxRepo.findByDelStatusAndCompanyIdAndIsActiveOrderByTaxIdDesc(1, compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Tax
	@RequestMapping(value = { "/saveTax" }, method = RequestMethod.POST)
	public @ResponseBody Tax saveTax(@RequestBody Tax tax) {

		Tax addTax = new Tax();
		try {
			addTax = taxRepo.save(tax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addTax;

	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Sigle Tax
	@RequestMapping(value = { "/getTaxById" }, method = RequestMethod.POST)
	public @ResponseBody Tax getTaxById(@RequestParam int taxId) {

		Tax tax = new Tax();
		try {
			tax = taxRepo.findByTaxId(taxId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tax;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Tax
	@RequestMapping(value = { "/deleteTaxById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteTaxById(@RequestParam int taxId) {

		Info info = new Info();
		try {
			int res = taxRepo.deleteTax(taxId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Tax Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Tax");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/*---------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Users List
	@RequestMapping(value = { "/getAllUsers" }, method = RequestMethod.POST)
	public @ResponseBody List<User> getAllUsers(@RequestParam int compId) {

		List<User> userList = new ArrayList<User>();
		try {
			userList = userRepo.getAllUserList(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Users By Id
	@RequestMapping(value = { "/getUserById" }, method = RequestMethod.POST)
	public @ResponseBody User getUserById(@RequestParam int userId, @RequestParam int compId) {

		User user = new User();
		try {
			user = userRepo.findByUserIdAndDelStatusAndCompanyId(userId, 1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Users By Mobile No.
	@RequestMapping(value = { "/getUserByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody User getUserByMobNo(@RequestParam String mobNo, @RequestParam int userId) {

		User user = new User();
		try {
			if (userId == 0) {

				user = userRepo.findByUserMobileNoAndDelStatus(mobNo, 1);
			} else {

				user = userRepo.findByUserMobileNoAndDelStatusAndUserIdNot(mobNo, 1, userId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Users By Email Id
	@RequestMapping(value = { "/getUserByEmail" }, method = RequestMethod.POST)
	public @ResponseBody User getUserByEmail(@RequestParam String email, @RequestParam int userId) {

		User user = new User();
		try {
			if (userId == 0) {

				user = userRepo.findByUserEmailAndDelStatus(email, 1);
			} else {

				user = userRepo.findByUserEmailAndDelStatusAndUserIdNot(email, 1, userId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete User
	@RequestMapping(value = { "/deleteUserById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteUserById(@RequestParam int userId) {

		Info info = new Info();
		try {
			int res = userRepo.deleteUserById(userId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("User Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete User");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Insert User in database
	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public @ResponseBody User addUser(@RequestBody User user) {

		User User = new User();
		try {
			User = userRepo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return User;
	}

	// Created By :- Mahendra Singh
	// Created On :- 19-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Update User Password
	@RequestMapping(value = { "/updateUserPassword" }, method = RequestMethod.POST)
	public @ResponseBody Info updateUserPassword(@RequestParam int userId, @RequestParam String newPassword) {

		Info info = new Info();
		int res = 0;
		try {
			res = userRepo.updateUserPass(userId, newPassword);

			if (res > 0) {
				info.setError(false);
				info.setMessage("Password changed successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to change password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// Created By :- Mahendra Singh
	// Created On :- 20-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get No. of users by role id.
	@RequestMapping(value = { "/getUserCntByRoleId" }, method = RequestMethod.POST)
	public @ResponseBody int getUserByEmail(@RequestParam int roleId) {

		int userCnt = 0;
		try {
			userCnt = userRepo.getUserCntByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userCnt;
	}

	/*-------------------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All User Types
	@RequestMapping(value = { "/getAllUserTypes" }, method = RequestMethod.GET)
	public @ResponseBody List<UserTypeMaster> getAllUserTypes() {

		List<UserTypeMaster> list = new ArrayList<UserTypeMaster>();
		try {
			list = userTypeRepo.findByDelStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*--------------------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Categories
	@RequestMapping(value = { "/getAllCategories" }, method = RequestMethod.POST)
	public @ResponseBody List<Category> getAllCategories(@RequestParam int compId) {

		List<Category> catList = new ArrayList<Category>();
		try {
			catList = catRepo.findByDelStatusAndCompanyIdOrderByCatIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Category By Id
	@RequestMapping(value = { "/getCatById" }, method = RequestMethod.POST)
	public @ResponseBody Category getCatById(@RequestParam int catId) {

		Category cat = new Category();
		try {
			cat = catRepo.findByCatId(catId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cat;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Category By Id
	@RequestMapping(value = { "/deleteCategoryById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteCategoryById(@RequestParam int catId) {

		Info info = new Info();
		try {
			int res = catRepo.deleteCatId(catId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Category Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Category");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Category
	@RequestMapping(value = { "/saveCategory" }, method = RequestMethod.POST)
	public @ResponseBody Category saveCategory(@RequestBody Category cat) {

		Category saveCat = new Category();
		try {
			saveCat = catRepo.save(cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveCat;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Prefix for unique validation
	@RequestMapping(value = { "/getCatByPrefix" }, method = RequestMethod.POST)
	public @ResponseBody Info getSubCatByPrefix(@RequestParam("prefix") String prefix, @RequestParam("catId") int catId,
			@RequestParam("compId") int compId) {

		Info res = new Info();
		try {
			Category value = new Category();
			if (catId == 0) {
				value = catRepo.findByCatPrefixIgnoreCaseAndCompanyId(prefix, compId);
			} else {
				value = catRepo.findByCatPrefixIgnoreCaseAndCompanyIdAndCatIdNot(prefix, catId, compId);
			}
			if (value != null) {
				res.setError(false);
				res.setMessage("Prefix Found");
			} else {
				res.setError(true);
				res.setMessage("Prefix Not  Found");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;

	}

	// Created By :- Mahendra Singh
	// Created On :- 21-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Category Prefix
	@RequestMapping(value = { "/getCatePrefixByCateId" }, method = RequestMethod.POST)
	public @ResponseBody String getCatPrefixByCatId(@RequestParam int cateId) {

		String cateCode = new String();
		try {
			cateCode = catRepo.getCatePrefix(cateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cateCode;
	}

	/*------------------------------------------------------------------------------------------------------*/

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Filter Types
	@RequestMapping(value = { "/getAllFilterTypes" }, method = RequestMethod.POST)
	public @ResponseBody List<FilterTypes> getAllFilterTypes(@RequestParam int compId) {

		List<FilterTypes> fltrTypeList = new ArrayList<FilterTypes>();
		try {
			fltrTypeList = filterTypeRepo.findByDelStatusOrderByFilterTypeIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fltrTypeList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Active Filter Types
	@RequestMapping(value = { "/getActiveFilterTypes" }, method = RequestMethod.POST)
	public @ResponseBody List<FilterTypes> getActiveFilterTypes(@RequestParam int compId) {

		List<FilterTypes> fltrTypeList = new ArrayList<FilterTypes>();
		try {
			fltrTypeList = filterTypeRepo.findByDelStatusAndIsActiveOrderByFilterTypeIdDesc(1, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fltrTypeList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Filter Type By Id
	@RequestMapping(value = { "/getFilterTypeById" }, method = RequestMethod.POST)
	public @ResponseBody FilterTypes getFilterTypeById(@RequestParam int filterTypeId) {

		FilterTypes filterType = new FilterTypes();
		try {
			filterType = filterTypeRepo.findByFilterTypeId(filterTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterType;
	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Filter Type
	@RequestMapping(value = { "/saveFilterType" }, method = RequestMethod.POST)
	public @ResponseBody FilterTypes saveFilterType(@RequestBody FilterTypes filterType) {

		FilterTypes saveFilterType = new FilterTypes();
		try {
			saveFilterType = filterTypeRepo.save(filterType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFilterType;
	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Filter Type By Id
	@RequestMapping(value = { "/deleteFilterTypeById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteFilterTypeById(@RequestParam int filterTypeId) {

		Info info = new Info();
		try {
			int res = filterTypeRepo.deleteFilterType(filterTypeId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Filter Type Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Filter Type");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/*------------------------------------------------------------------------------------------------------*/

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Filter
	@RequestMapping(value = { "/getAllFilter" }, method = RequestMethod.POST)
	public @ResponseBody List<MFilter> getAllFilter(@RequestParam int compId) {

		List<MFilter> filterList = new ArrayList<MFilter>();
		try {
			filterList = filterRepo.getAllFilters(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Filter
	@RequestMapping(value = { "/getFiltersListByTypeId" }, method = RequestMethod.POST)
	public @ResponseBody List<MFilter> getAllFilter(@RequestParam int compId, @RequestParam int filterTypeId) {

		List<MFilter> filterList = new ArrayList<MFilter>();
		try {
			filterList = filterRepo.getFiltersByFilterId(compId, filterTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Filter By Id
	@RequestMapping(value = { "/getFilterById" }, method = RequestMethod.POST)
	public @ResponseBody MFilter getFilterById(@RequestParam int filterId) {

		MFilter filter = new MFilter();
		try {
			filter = filterRepo.findByFilterId(filterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filter;
	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- Sachin
	// Modified On :- 20-10-2020
	// Description :- Save Category
	@RequestMapping(value = { "/saveFilter" }, method = RequestMethod.POST)
	public @ResponseBody MFilter saveFilter(@RequestBody MFilter filter) {
		// System.err.println("Initial filter " +filter.toString());
		MFilter saveFilter = new MFilter();
		int tagId = 0;
		try {
			if (filter.getFilterId() == 0) {
				// ie new record
				// System.err.println("In else" +filter.toString());
				if (filter.getIsTagAdd() == 1) {
					// System.err.println("In Add tag ");
					tagId = saveTag(filter);
				} else {
					// System.err.println("In No Add tag ");
				}

			} else {
				// System.err.println("In else getFilterId() !=0" +filter.toString());
			}
			try {
				filter.setTagId(tagId);
				saveFilter = filterRepo.save(filter);
				// System.err.println("Main Save " +saveFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFilter;
	}

	// Created By :- Sachin
	// Created On :- 20-10-2020
	private int saveTag(MFilter filter) {
		MFilter tagFilter = new MFilter();

		tagFilter.setAddOnRs(0);
		tagFilter.setAddOnType(0);
		tagFilter.setCostAffect(0);
		tagFilter.setTagId(0);
		tagFilter.setFilterTypeId(7);

		tagFilter.setAllowToCopy(filter.getAllowToCopy());
		tagFilter.setCompanyId(filter.getCompanyId());
		tagFilter.setDelStatus(filter.getDelStatus());
		tagFilter.setExInt1(filter.getExInt1());
		tagFilter.setExInt2(filter.getExInt2());
		tagFilter.setExInt3(filter.getExInt3());
		tagFilter.setExVar1(filter.getExVar1());
		tagFilter.setExVar2(filter.getExVar2());
		tagFilter.setExVar3(filter.getExVar3());
		tagFilter.setFilterDesc(filter.getFilterDesc());
		tagFilter.setFilterId(0);
		tagFilter.setFilterName(filter.getFilterName());
		tagFilter.setFilterTypeId(7);
		tagFilter.setIsActive(filter.getIsActive());
		tagFilter.setIsParent(filter.getIsParent());
		tagFilter.setIsTagAdd(0);
		tagFilter.setSortNo(filter.getSortNo());

		tagFilter.setUsedForDescription(filter.getUsedForDescription());
		tagFilter.setUsedForFilter(filter.getUsedForFilter());

		tagFilter.setAdminName(filter.getFilterName());
		tagFilter = filterRepo.save(tagFilter);
		return tagFilter.getFilterId();

	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Filter By Id
	@RequestMapping(value = { "/deleteFilterById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteFilterById(@RequestParam int filterId) {

		Info info = new Info();
		try {
			int res = filterRepo.deleteFilter(filterId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Filter Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Filter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Filter For Home Page and Product Configuration
	@RequestMapping(value = { "/getAllFilterForConfig" }, method = RequestMethod.POST)
	public @ResponseBody List<MFilter> getAllFilterForConfig(@RequestParam int filterTypeId, @RequestParam int compId) {

		List<MFilter> filterList = new ArrayList<MFilter>();
		try {
			filterList = filterRepo.findByFilterTypeIdAndDelStatusAndCompanyIdAndIsActiveOrderByFilterIdDesc(
					filterTypeId, 1, compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterList;
	}

	/*------------------------------------------------------------------------------------------------------*/

	// Created By :- Mahendra Singh
	// Created On :- 14-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Active Sub Categories
	@RequestMapping(value = { "/getAllActiveSubCategories" }, method = RequestMethod.POST)
	public @ResponseBody List<SubCategory> getAllActiveSubCategories(@RequestParam int compId) {

		List<SubCategory> subCatList = new ArrayList<SubCategory>();
		try {
			subCatList = subCatRepo.getAllActiveSubcategories(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subCatList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 20-10-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Sub Category Count
	@RequestMapping(value = { "/getCatIdCount" }, method = RequestMethod.POST)
	public @ResponseBody int getCatIdCount(@RequestParam int catId) {

		int catIdCnt = 0;
		try {
			catIdCnt = subCatRepo.getCatIdCntByCatId(catId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catIdCnt;
	}

	/*------------------------------------------------------------------------------------------------------*/

	// Created By :- Mahendra Singh
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Franchises
	@RequestMapping(value = { "/getAllFranchises" }, method = RequestMethod.POST)
	public @ResponseBody List<Franchise> getAllFranchises(@RequestParam int compId) {

		List<Franchise> frList = new ArrayList<Franchise>();
		try {
			frList = frRepo.findByCompanyIdAndDelStatusOrderByFrIdDesc(compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frList;
	}

	// Created By :-Akhilesh
	// Created On :- 23-12-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Franchises By Company Id With Charges
	@RequestMapping(value = "/getAllFranchiseByCompIdWithCharges", method = RequestMethod.POST)
	public @ResponseBody List<Franchise> getAllFranchiseByCompIdWithCharges(@RequestParam int compId) {

		List<Franchise> frList = new ArrayList<Franchise>();
		try {
			frList = frRepo.getAllFranchiseByCompIdWithCharges(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frList;
	}

	// Sachin 27-10-2020
	@RequestMapping(value = { "/getFrListToAddInRoute" }, method = RequestMethod.POST)
	public @ResponseBody List<Franchise> getFrListToAddInRoute(@RequestParam int compId,
			@RequestParam List<Integer> frIds) {

		List<Franchise> frList = new ArrayList<Franchise>();
		try {
			if (frIds.get(0) < 1)
				frList = frRepo.getFrListToAddInRoute(compId);
			else
				frList = frRepo.getFrListToAddInRouteForEdit(compId, frIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Franchise By Id
	@RequestMapping(value = { "/getFranchiseById" }, method = RequestMethod.POST)
	public @ResponseBody Franchise getFranchiseById(@RequestParam int frId) {

		Franchise franchise = new Franchise();
		try {
			franchise = frRepo.findByFrId(frId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return franchise;
	}

	// Created By :- Mahendra Singh
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Franchise
	@RequestMapping(value = { "/saveFranchise" }, method = RequestMethod.POST)
	public @ResponseBody Franchise saveFranchise(@RequestBody Franchise franchise) {

		Franchise saveFranshise = new Franchise();
		try {
			saveFranshise = frRepo.save(franchise);
			if (saveFranshise.getFrId() > 0) {
				FrSetting frSetting = new FrSetting();

				frSetting = frSettingRepo.findByFrId(saveFranshise.getFrId());

				if (frSetting == null) {
					FrSetting frSettingSave = new FrSetting();
					frSettingSave.setFrCode(saveFranshise.getFrCode());
					frSettingSave.setFrId(saveFranshise.getFrId());
					frSettingSave.setGrnGvnNo(1);
					frSettingSave.setSellBillNo(1);
					frSettingSave.setSpNo(1);

					System.out.println("***************" + frSettingSave.toString());
					FrSetting frSettingSaveResponse = frSettingRepo.save(frSettingSave);
					System.out.println(frSettingSaveResponse.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFranshise;
	}

	@RequestMapping(value = { "/getFrCnt" }, method = RequestMethod.POST)
	public @ResponseBody int getFrCnt(@RequestParam int compId, @RequestParam String coPrefix) {

		Info info = new Info();
		int cnt = 0;
		try {
			cnt = frRepo.getCompCountByPrefix(compId, coPrefix);
			if (cnt > 0) {
				info.setError(false);
				info.setMessage("Ok");
			} else {
				info.setError(true);
				info.setMessage("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// Created By :- Mahendra Singh
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Franchise By Id
	@RequestMapping(value = { "/deleteFranchiseById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteFranchiseById(@RequestParam int frId) {

		Info info = new Info();
		try {
			int res = frRepo.deleteFranchise(frId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Franchise Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Franchise");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// Created By :- Mahendra Singh
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Franchise By Mobile No.
	@RequestMapping(value = { "/getFranchiseByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody Franchise getFranchiseByMobNo(@RequestParam String mobNo, @RequestParam int frId) {

		Franchise franchise = new Franchise();
		try {
			if (frId == 0) {

				franchise = frRepo.findByFrContactNoAndDelStatus(mobNo, 1);
			} else {

				franchise = frRepo.findByFrContactNoAndDelStatusAndFrIdNot(mobNo, 1, frId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return franchise;
	}

	// Created By :- Mahendra Singh
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Franchise By Email Id
	@RequestMapping(value = { "/getFranchiseByEmail" }, method = RequestMethod.POST)
	public @ResponseBody Franchise getFranchiseByEmail(@RequestParam String email, @RequestParam int frId) {

		Franchise franchise = new Franchise();
		try {
			if (frId == 0) {

				franchise = frRepo.findByFrEmailIdAndDelStatus(email, 1);
			} else {

				franchise = frRepo.findByFrEmailIdAndDelStatusAndFrIdNot(email, 1, frId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return franchise;
	}

	// Created By :- Mahendra Singh
	// Created On :- 30-12-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Franchises For Excel And Pdf
	@RequestMapping(value = { "/getAllFranchisesExlPdf" }, method = RequestMethod.POST)
	public @ResponseBody List<Franchise> getAllFranchisesExlPdf(@RequestParam int compId) {

		List<Franchise> frList = new ArrayList<Franchise>();
		try {
			frList = frRepo.getAllFranchiseByCompIdForExlPdf(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frList;
	}

	/*------------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 15-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Show Language
	@RequestMapping(value = { "/getAllLanguages" }, method = RequestMethod.POST)
	public @ResponseBody List<Language> getAllLanguages(@RequestParam int compId) {

		List<Language> langList = new ArrayList<Language>();
		try {
			langList = langRepo.findByDelStatusAndCompanyIdOrderByLangIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return langList;

	}

	@RequestMapping(value = { "/getLanguageById" }, method = RequestMethod.POST)
	public @ResponseBody Language getLanguageById(@RequestParam int langId, @RequestParam int compId) {

		Language lang = new Language();
		try {
			lang = langRepo.findByLangIdAndDelStatusAndCompanyId(langId, 1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lang;

	}

	@RequestMapping(value = { "/getLanguageByCode" }, method = RequestMethod.POST)
	public @ResponseBody Language getLanguageByCode(@RequestParam String code, @RequestParam int langId,
			@RequestParam int compId) {

		Language lang = new Language();
		try {
			if (langId == 0) {
				lang = langRepo.findByLangCodeIgnoreCaseAndCompanyId(code, compId);
			} else {

				lang = langRepo.findByLangCodeIgnoreCaseAndCompanyIdAndLangIdNot(code, compId, langId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lang;

	}

	@RequestMapping(value = { "/deleteLanguageById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLanguageById(@RequestParam int langId) {

		Info info = new Info();
		try {
			int res = langRepo.deleteLanguage(langId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Language Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Language");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addLanguage" }, method = RequestMethod.POST)
	public @ResponseBody Language addLanguage(@RequestBody Language lang) {

		Language newLang = new Language();
		try {
			newLang = langRepo.save(lang);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newLang;
	}

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value = { "/getAllCities" }, method = RequestMethod.POST)
	public @ResponseBody List<City> getAllCities(@RequestParam int compId) {

		List<City> cityList = new ArrayList<City>();
		try {
			cityList = cityRepo.findByDelStatusAndCompanyIdOrderByCityIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;

	}

	@RequestMapping(value = { "/getAllActiveCities" }, method = RequestMethod.GET)
	public @ResponseBody List<City> getAllActiveCities() {

		List<City> cityList = new ArrayList<City>();
		try {
			cityList = cityRepo.findByDelStatusAndIsActiveOrderByCityIdDesc(1, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;

	}

	@RequestMapping(value = { "/getAllCitiesByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<City> getAllCitiesByCompId(@RequestParam int compId) {

		List<City> cityList = new ArrayList<City>();
		try {
			cityList = cityRepo.findByDelStatusAndIsActiveAndCompanyIdOrderByCityIdDesc(1, 1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;

	}

	@RequestMapping(value = { "/getAllCitiesOnly" }, method = RequestMethod.POST)
	public @ResponseBody List<City> getAllCitiesOnly(@RequestParam int compId) {

		List<City> cityList = new ArrayList<City>();
		try {
			cityList = cityRepo.findByDelStatusAndCompanyIdAndExInt1OrderByCityIdDesc(1, compId, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;

	}

	@RequestMapping(value = { "/getCityById" }, method = RequestMethod.POST)
	public @ResponseBody City getCityById(@RequestParam int cityId) {

		City city = new City();
		try {
			city = cityRepo.findByCityId(cityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;

	}

	@RequestMapping(value = { "/getCityByCode" }, method = RequestMethod.POST)
	public @ResponseBody City getCityByCode(@RequestParam String code, @RequestParam int cityId) {

		City city = new City();
		try {
			if (cityId == 0) {

				city = cityRepo.findByCityCodeIgnoreCase(code);
			} else {

				city = cityRepo.findByCityCodeIgnoreCaseAndCityIdNot(code, cityId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	@RequestMapping(value = { "/deleteCityById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteCityById(@RequestParam int cityId) {

		Info info = new Info();
		try {
			int res = cityRepo.deleteCity(cityId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("City Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete City");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// Akhilesh 2020-12-28 Delete Multiple Cities
	@RequestMapping(value = { "/deleteMultipleCity" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteMultipleCity(@RequestBody List<Integer> cityIds) {

		Info info = new Info();
		try {
			int res = cityRepo.deleteMultipleCity(cityIds);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Cites Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Cities");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addCity" }, method = RequestMethod.POST)
	public @ResponseBody City addCity(@RequestBody City city) {

		City newCity = new City();
		try {
			newCity = cityRepo.save(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCity;
	}

	/*-----------------------------------------------------------------------------------------------*/
	@RequestMapping(value = { "/getAllAreas" }, method = RequestMethod.POST)
	public @ResponseBody List<Area> getAllArea(@RequestParam int compId) {

		List<Area> areaList = new ArrayList<Area>();
		try {
			areaList = areaRepo.findByDelStatusAndCompanyIdAndIsActiveOrderByAreaIdDesc(1, compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaList;
	}

	@RequestMapping(value = { "/getAllAreaCityList" }, method = RequestMethod.POST)
	public @ResponseBody List<AreaCityList> getAllAreaCityList(@RequestParam int compId) {

		List<AreaCityList> areaList = new ArrayList<AreaCityList>();
		try {
			areaList = areaCityRepo.getAllAreaByCompId(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaList;
	}

	@RequestMapping(value = { "/getAreaById" }, method = RequestMethod.POST)
	public @ResponseBody Area getAreaById(@RequestParam int areaId, @RequestParam int compId) {

		Area area = new Area();
		try {
			area = areaRepo.findByAreaIdAndDelStatusAndCompanyId(areaId, 1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return area;
	}

	@RequestMapping(value = { "/getAreaByCode" }, method = RequestMethod.POST)
	public @ResponseBody Area getAreaByCode(@RequestParam String code, @RequestParam int areaId,
			@RequestParam int compId) {

		Area area = new Area();
		try {
			if (areaId == 0) {

				area = areaRepo.findByAreaCodeIgnoreCaseAndCompanyId(code, compId);
			} else {

				area = areaRepo.findByAreaCodeIgnoreCaseAndCompanyIdAndAreaIdNot(code, compId, areaId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return area;
	}

	@RequestMapping(value = { "/deleteAreaById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteAreaById(@RequestParam int areaId) {

		Info info = new Info();
		try {
			int res = areaRepo.deleteArea(areaId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Area Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Area");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addArea" }, method = RequestMethod.POST)
	public @ResponseBody Area addArea(@RequestBody Area area) {

		Area newArea = new Area();
		try {
			newArea = areaRepo.save(area);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newArea;
	}

	@RequestMapping(value = { "/getAreaByCityId" }, method = RequestMethod.POST)
	public @ResponseBody int getAreaByCityId(@RequestParam int cityId) {

		int res = 0;
		try {
			res = areaRepo.getMaxCountAreaCode(cityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@RequestMapping(value = { "/getAreaByCityAndCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<Area> getAreaByCityIdAndCopmId(@RequestParam int cityId, @RequestParam int compId) {

		List<Area> list = new ArrayList<Area>();

		try {
			list = areaRepo.findByCityIdAndCompanyIdAndDelStatus(cityId, compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = { "/getAreaByCityIdsAndCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<Area> getAreaByCitysIdAndCopmId(@RequestParam List<Integer> cityId,
			@RequestParam int compId) {

		List<Area> list = null;

		try {
			list = areaRepo.getAreaByCityIdsAndCompId(cityId, compId);
			if (list == null) {
				list = new ArrayList<Area>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*-------------------------------------------------------------------------------------------------*/
	@RequestMapping(value = { "/getAllDeliveryInstructions" }, method = RequestMethod.POST)
	public @ResponseBody List<DeliveryInstruction> getAllDeliveryInstructions(@RequestParam int compId) {

		List<DeliveryInstruction> instructnList = new ArrayList<DeliveryInstruction>();
		try {
			instructnList = delvInstuctRepo.findByDelStatusAndCompanyIdOrderByInstruIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instructnList;
	}

	@RequestMapping(value = { "/getDeliveryInstructionById" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryInstruction getDeliveryInstructionById(@RequestParam int instructId) {

		DeliveryInstruction del = new DeliveryInstruction();
		try {
			del = delvInstuctRepo.findByInstruIdAndDelStatus(instructId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}

	@RequestMapping(value = { "/getDeliveryInstructionByCaptn" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryInstruction getDeliveryInstructionByCaptn(@RequestParam String caption,
			@RequestParam int compId, @RequestParam int instructId) {

		DeliveryInstruction instruct = new DeliveryInstruction();
		try {
			if (instructId == 0) {

				instruct = delvInstuctRepo.findByInstructnCaptionIgnoreCaseAndCompanyId(caption, compId);
			} else {

				instruct = delvInstuctRepo.findByInstructnCaptionIgnoreCaseAndCompanyIdAndInstruIdNot(caption, compId,
						instructId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return instruct;
	}

	@RequestMapping(value = { "/deleteDeliveryInstructnById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteDeliveryInstructnById(@RequestParam int instructId) {

		Info info = new Info();
		try {
			int res = delvInstuctRepo.deleteDelveryInstructnById(instructId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Delivery Instruction Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Delivery Instruction");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addDeliveryInstrunctn" }, method = RequestMethod.POST)
	public @ResponseBody DeliveryInstruction addDeliveryInstrunctn(@RequestBody DeliveryInstruction instructn) {

		DeliveryInstruction newinstructn = new DeliveryInstruction();
		try {
			newinstructn = delvInstuctRepo.save(instructn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newinstructn;
	}

	/*----------------------------------------------------------------------------------*/
	@RequestMapping(value = { "/getAllGrievTypeInstruct" }, method = RequestMethod.POST)
	public @ResponseBody List<GrievencesTypeInstructn> getAllGrievTypeInstruct(@RequestParam int compId) {

		List<GrievencesTypeInstructn> grievTypeList = new ArrayList<GrievencesTypeInstructn>();
		try {
			grievTypeList = grievTypeInstructRepo.findByDelStatusAndCompanyIdOrderByGrevTypeIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievTypeList;
	}

	@RequestMapping(value = { "/getAllGrievType" }, method = RequestMethod.GET)
	public @ResponseBody List<GrievencesTypeInstructn> getAllGrievType() {

		List<GrievencesTypeInstructn> grievTypeList = new ArrayList<GrievencesTypeInstructn>();
		try {
			grievTypeList = grievTypeInstructRepo.findByDelStatusOrderByGrevTypeIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievTypeList;
	}

	@RequestMapping(value = { "/getGrievTypeInstructById" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesTypeInstructn getGrievTypeInstructById(@RequestParam int grievTypeId,
			@RequestParam int compId) {

		GrievencesTypeInstructn griev = new GrievencesTypeInstructn();
		try {
			griev = grievTypeInstructRepo.findByDelStatusAndGrevTypeIdAndCompanyId(1, grievTypeId, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return griev;
	}

	@RequestMapping(value = { "/getGrievTypeInstructByCaptn" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesTypeInstructn getGrievTypeInstructByCaptn(@RequestParam String caption,
			@RequestParam int compId, @RequestParam int grievTypeId) {

		GrievencesTypeInstructn griev = new GrievencesTypeInstructn();
		try {
			if (grievTypeId == 0) {

				griev = grievTypeInstructRepo.findByCaptionIgnoreCaseAndCompanyId(caption, compId);
			} else {

				griev = grievTypeInstructRepo.findByCaptionIgnoreCaseAndCompanyIdAndGrevTypeIdNot(caption, compId,
						grievTypeId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return griev;
	}

	@RequestMapping(value = { "/deleteGrievTypeInstructById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteGrievTypeInstructById(@RequestParam int grievTypeId) {

		Info info = new Info();
		try {
			int res = grievTypeInstructRepo.deleteGrievancTypeInst(grievTypeId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Grievance Type Instruction Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Grievance Type Instruction");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addGrievTypeInstruct" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesTypeInstructn addGrievTypeInstruct(@RequestBody GrievencesTypeInstructn griev) {

		GrievencesTypeInstructn newGriev = new GrievencesTypeInstructn();
		try {
			newGriev = grievTypeInstructRepo.save(griev);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newGriev;
	}

	/*----------------------------------------------------------------------------------*/
	@RequestMapping(value = { "/getAllGrievancesInstructns" }, method = RequestMethod.POST)
	public @ResponseBody List<GrievencesInstruction> getAllGrievanceInstructn(@RequestParam int compId) {

		List<GrievencesInstruction> grievList = new ArrayList<GrievencesInstruction>();
		try {
			grievList = grievanceRepo.findByDelStatusAndCompanyIdOrderByGrievanceIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievList;
	}

	@RequestMapping(value = { "/getGrievanceInstructnById" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesInstruction getGrievanceInstructnById(@RequestParam int grievanceId,
			@RequestParam int compId) {

		GrievencesInstruction grievance = new GrievencesInstruction();
		try {
			grievance = grievanceRepo.findByGrievanceIdAndDelStatusAndCompanyId(grievanceId, 1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievance;
	}

	@RequestMapping(value = { "/getGrievancenstructnByCaptn" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesInstruction getGrievancenstructnByCaptn(@RequestParam String caption,
			@RequestParam int compId, @RequestParam int grievanceId) {

		GrievencesInstruction grievance = new GrievencesInstruction();
		try {
			if (grievanceId == 0) {

				grievance = grievanceRepo.findByCaptionIgnoreCaseAndCompanyId(caption, compId);
			} else {

				grievance = grievanceRepo.findByCaptionIgnoreCaseAndCompanyIdAndGrievanceIdNot(caption, compId,
						grievanceId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return grievance;
	}

	@RequestMapping(value = { "/deleteGrievanceInstructnById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteGrievanceInstructnById(@RequestParam int grievanceId) {

		Info info = new Info();
		try {
			int res = grievanceRepo.deleteGrievancesInstructn(grievanceId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Grievance Instruction Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Grievance Instruction");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/addGrievance" }, method = RequestMethod.POST)
	public @ResponseBody GrievencesInstruction addGrievanceInstructn(@RequestBody GrievencesInstruction grievance) {

		GrievencesInstruction newGrievance = new GrievencesInstruction();
		try {
			newGrievance = grievanceRepo.save(grievance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newGrievance;
	}

	/*********************************************************************************************/
	@RequestMapping(value = { "/getFilterIds" }, method = RequestMethod.POST)
	public @ResponseBody String getFilterIds(@RequestParam int filterTypeId) {

		String getIds = new String();
		try {
			if (filterTypeId == 2) {
				getIds = productMstrRepo.getTimeSlotFilterIds();
			} else if (filterTypeId == 4) {
				getIds = productMstrRepo.getFlavourFilterIds();
			} else if (filterTypeId == 6) {
				getIds = productMstrRepo.getEventFilterIds();
			} else if (filterTypeId == 7) {
				getIds = productMstrRepo.getTagFilterIds();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getIds;
	}

	@RequestMapping(value = { "/getProductsNotConfigure" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getProductsNotConfigure(@RequestParam int filterTypeId,
			@RequestParam int filterId, @RequestParam int optionVal, @RequestParam int compId) {

		List<ProductMaster> list = new ArrayList<ProductMaster>();
		try {
			if (optionVal == 1) {
				if (filterTypeId == 2) {
					list = productMstrRepo.getProductsNoTimeSlots(filterId, compId);
				} else if (filterTypeId == 4) {
					list = productMstrRepo.getProductsNoFlavours(filterId, compId);
				} else if (filterTypeId == 6) {
					list = productMstrRepo.getProductsNoEvents(filterId, compId);
				} else if (filterTypeId == 7) {
					list = productMstrRepo.getProductsNoTags(filterId, compId);
				}
			} else {
				if (filterTypeId == 2) {
					list = productMstrRepo.getProductsTimeSlots(filterId, compId);
				} else if (filterTypeId == 4) {
					list = productMstrRepo.getProductsFlavours(filterId, compId);
				} else if (filterTypeId == 6) {
					list = productMstrRepo.getProductsEvents(filterId, compId);
				} else if (filterTypeId == 7) {
					list = productMstrRepo.getProductsTags(filterId, compId);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = { "/configProductWithFilter" }, method = RequestMethod.POST)
	public @ResponseBody Info configProductWithFilter(@RequestParam int filterTypeId, @RequestParam String filterId,
			@RequestParam List<Integer> prdctIdsStr, @RequestParam int optnValue) {

		Info info = new Info();
		int res = 0;
		try {

			if (optnValue == 1) {
				if (filterTypeId == 2) {
					res = productMstrRepo.updtConfigProductsTimeSlots(filterId, prdctIdsStr);
				} else if (filterTypeId == 4) {
					res = productMstrRepo.updtConfigProductsFlavours(filterId, prdctIdsStr);
				} else if (filterTypeId == 6) {
					res = productMstrRepo.updtConfigProductsEvents(filterId, prdctIdsStr);
				} else if (filterTypeId == 7) {
					res = productMstrRepo.updtConfigProductsTags(filterId, prdctIdsStr);
				}

				if (res > 0) {
					info.setError(false);
					info.setMessage("Product Configure Successfully");
				} else {
					info.setError(true);
					info.setMessage("Failed to Configure Product");
				}
			} else {
				System.err.println("In Remove");
				if (filterTypeId == 2) {
					res = productMstrRepo.unconfigUpdtProductTimeSlots(filterId, prdctIdsStr);
				} else if (filterTypeId == 4) {
					res = productMstrRepo.unconfigUpdtProductFlavour(filterId, prdctIdsStr);
				} else if (filterTypeId == 6) {
					res = productMstrRepo.unconfigUpdtProductEvents(filterId, prdctIdsStr);
				} else if (filterTypeId == 7) {
					res = productMstrRepo.unconfigUpdtProductTags(filterId, prdctIdsStr);
				}

				if (res > 0) {
					info.setError(false);
					info.setMessage("Product Unconfigure Successfully");
				} else {
					info.setError(true);
					info.setMessage("Failed to Unconfigure Product");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/***********************************************************************************/
	@RequestMapping(value = { "/getProductsByType" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> getProductsByTaxId(@RequestParam int typeConfigId,
			@RequestParam int filterId, @RequestParam int compId, @RequestParam int optionVal) {
		List<ProductMaster> list = new ArrayList<ProductMaster>();

		if (optionVal == 1) {
			if (typeConfigId == 1) {
				list = productMstrRepo.getProductsByNoTaxId(filterId, compId);

			} else if (typeConfigId == 2) {
				list = productMstrRepo.getProductsByReturnPer(compId);

			} else if (typeConfigId == 3) {
				list = productMstrRepo.getProductsByNoCakeShape(filterId, compId);

			} else if (typeConfigId == 4) {
				list = productMstrRepo.getActiveProducts(compId);

			} else if (typeConfigId == 5) {
				list = productMstrRepo.getInActiveProducts(compId);
			}
		} else {
			if (typeConfigId == 1) {
				list = productMstrRepo.getProductsByTaxId(filterId, compId);

			} else if (typeConfigId == 2) {
				list = productMstrRepo.getProductsByReturnPer(compId);

			} else if (typeConfigId == 3) {
				list = productMstrRepo.getProductsByCakeShape(filterId, compId);

			} else if (typeConfigId == 4) {
				list = productMstrRepo.getActiveProducts(compId);

			} else if (typeConfigId == 5) {
				list = productMstrRepo.getInActiveProducts(compId);
			}
		}

		return list;

	}

	@RequestMapping(value = { "/configProductOtherFilter" }, method = RequestMethod.POST)
	public @ResponseBody Info configProductOtherFilter(@RequestParam int typeConfigId, @RequestParam int filterId,
			@RequestParam List<Integer> prdctIdsStr, @RequestParam int optnValue) {
		Info info = new Info();
		int res = 0;
		try {

			if (optnValue == 1) {
				if (typeConfigId == 1) {
					res = productMstrRepo.updateConfigProductsTax(filterId, prdctIdsStr);

				} else if (typeConfigId == 3) {

					res = productMstrRepo.updateConfigProductsCakeShap(filterId, prdctIdsStr);
				}

				if (res > 0) {
					info.setError(false);
					info.setMessage("Product Configure Successfully");
				} else {
					info.setError(true);
					info.setMessage("Failed to Configure Product");
				}
			} else {
				if (typeConfigId == 1) {
					res = productMstrRepo.updateConfigProductsRemoveTax(prdctIdsStr);

				} else if (typeConfigId == 3) {

					res = productMstrRepo.updateConfigProductsRemoveCakeShap(prdctIdsStr);
				}
			}

		} catch (Exception e) {
			System.err.println("dfdf" + e.getMessage());
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/configProductReturnPer" }, method = RequestMethod.POST)
	public @ResponseBody Info configProductReturnPer(@RequestParam float returnVal,
			@RequestParam List<Integer> prdctIdsStr) {
		Info info = new Info();
		int res = 0;
		try {

			res = productMstrRepo.updateConfigProductsReturnPer(returnVal, prdctIdsStr);

			if (res > 0) {
				info.setError(false);
				info.setMessage("Product Configure Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Configure Product");
			}

		} catch (Exception e) {
			System.err.println("dfdf" + e.getMessage());
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/configProductStatus" }, method = RequestMethod.POST)
	public @ResponseBody Info configProductStatus(@RequestParam float typeConfigId,
			@RequestParam List<Integer> prdctIdsStr) {
		Info info = new Info();
		int res = 0;
		try {
			if (typeConfigId == 4) {
				res = productMstrRepo.updtProductsStatusToInActive(prdctIdsStr);

			} else {
				res = productMstrRepo.updtProductsStatusToActive(prdctIdsStr);
			}
			if (res > 0) {
				info.setError(false);
				info.setMessage("Product Configure Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Configure Product");
			}

		} catch (Exception e) {
			System.err.println("dfdf" + e.getMessage());
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/getProductByStatusId" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductMaster> configProductStatus(@RequestParam int statusId, @RequestParam int compId) {
		List<ProductMaster> list = new ArrayList<ProductMaster>();
		try {
			list = productMstrRepo.findByProdStatusIdAndDelStatusAndIsActiveAndCompanyId(statusId, 1, 1, compId);
		} catch (Exception e) {
			System.err.println("dfdf" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	/*----------------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Special Day Home Page List
	@RequestMapping(value = { "/getSpDayHomePages" }, method = RequestMethod.POST)
	public @ResponseBody List<SpDayHomePage> getSpDayHomePages(@RequestParam int compId) {

		List<SpDayHomePage> list = new ArrayList<SpDayHomePage>();
		try {
			list = spDayHomePageRepo.findByCompanyIdAndDelStatusOrderBySpDayIdDesc(compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 31-12-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Special Day Home Page List Excel Pdf
	@Autowired
	SpDayHomePageExlPdfRepo spExlRepo;

	@RequestMapping(value = { "/getAllSpDayHomePagesExl" }, method = RequestMethod.POST)
	public @ResponseBody List<SpDayHomePageExlPdf> getAllSpDayHomePagesExl(@RequestParam int compId) {

		List<SpDayHomePageExlPdf> list = new ArrayList<SpDayHomePageExlPdf>();
		try {
			list = spExlRepo.getAllSpDayPageForExlPdf(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Special Day Home Page By Id
	@RequestMapping(value = { "/getSpDayHomePageById" }, method = RequestMethod.POST)
	public @ResponseBody SpDayHomePage getSpDayHomePageById(@RequestParam int spDayId) {

		SpDayHomePage spDay = new SpDayHomePage();
		try {
			spDay = spDayHomePageRepo.findBySpDayId(spDayId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return spDay;

	}

	// Created By :- Mahendra Singh
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Special Day Home Page
	@RequestMapping(value = { "/saveSpDayHomePage" }, method = RequestMethod.POST)
	public @ResponseBody SpDayHomePage saveSpDayHomePage(@RequestBody SpDayHomePage spDay) {

		SpDayHomePage spDaySave = new SpDayHomePage();
		try {
			spDaySave = spDayHomePageRepo.save(spDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return spDaySave;

	}

	// Created By :- Mahendra Singh
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Special Day Home Page
	@RequestMapping(value = { "/deleteSpDayHomePage" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteSpDayHomePage(@RequestParam int spDayId) {

		Info info = new Info();
		try {
			int res = spDayHomePageRepo.deleteSpDayId(spDayId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Special Day Home Page Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Special Day Home Page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/*--------------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Configuration Product Home Page
	@RequestMapping(value = { "/savePrdctHomePageConfige" }, method = RequestMethod.POST)
	public @ResponseBody ProductHomPage savePrdctHomePageConfige(@RequestBody ProductHomPage prdctHomeHead) {

		ProductHomPage spDaySave = new ProductHomPage();

		List<ProductHomePageDetail> productHomeList = prdctHomeHead.getPrdctHomeList();
		try {
			spDaySave = prdctHomeRepo.save(prdctHomeHead);

			for (int i = 0; i < productHomeList.size(); i++) {
				productHomeList.get(i).setHomePageStatusId(spDaySave.getHomePageStatusId());
			}

			List<ProductHomePageDetail> saveDtl = prdctHomePageDtlRepo.saveAll(productHomeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return spDaySave;

	}

	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Configuration Product Home Page
	@RequestMapping(value = { "/getPrdctHomePageById" }, method = RequestMethod.POST)
	public @ResponseBody ProductHomPage getPrdctHomePageById(@RequestParam int homePageStatusId) {
		ProductHomPage res = new ProductHomPage();
		try {

			res = prdctHomeRepo.findByHomePageStatusId(homePageStatusId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Update Configuration Product Home Page SortNo
	@RequestMapping(value = { "/updatePrdctHomePageSortNo" }, method = RequestMethod.POST)
	public @ResponseBody Info updatePrdctHomePageSortNo(@RequestParam int configStatusId, @RequestParam int sortNo,
			@RequestParam int isActve, @RequestParam String titleKey, @RequestParam String altImg) {

		Info info = new Info();
		try {
			int res = prdctHomeRepo.updateSortNo(configStatusId, sortNo, isActve, titleKey, altImg);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Sort No Update Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Update Sort No");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Configuration Product Home Page Details
	@RequestMapping(value = { "/saveHomePagePrdctConfigDtl" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductHomePageDetail> saveHomePagePrdctConfigDtl(
			@RequestBody List<ProductHomePageDetail> details) {

		List<ProductHomePageDetail> saveDtl = new ArrayList<ProductHomePageDetail>();
		try {

			saveDtl = prdctHomePageDtlRepo.saveAll(details);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveDtl;

	}

	// Created By :- Mahendra Singh
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Home Page Staus List
	@RequestMapping(value = { "/getHomePageSatusList" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductHomPage> getHomePageSatusList(@RequestParam int compId) {

		List<ProductHomPage> list = new ArrayList<ProductHomPage>();
		try {
			list = prdctHomeRepo.findByCompanyIdAndDelStatus(compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Show Configure Product Home Page List
	@RequestMapping(value = { "/getHomePageConfigProductList" }, method = RequestMethod.POST)
	public @ResponseBody List<ProductHomPage> getHomePageConfigProductList(@RequestParam int compId) {

		List<ProductHomPage> list = new ArrayList<ProductHomPage>();
		try {
			list = prdctHomeRepo.getHomePageProductConfigList(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/getProductStatusConfigList" }, method = RequestMethod.POST)
	public @ResponseBody List<ConfigHomePageProduct> getProductStatusConfigList(@RequestParam int statusType,
			@RequestParam int compId) {
		List<ConfigHomePageProduct> list = new ArrayList<ConfigHomePageProduct>();
		try {
			list = configPrdctHomRepo.getConfigHomePagePrdctList(statusType, compId);
		} catch (Exception e) {
			System.err.println("dfdf" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Configure Product Home Page Detail By Header Id
	@RequestMapping(value = { "/deleteHomePageStatusDtl" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteHomepageStatusDtl(@RequestParam int configStatusId) {

		Info info = new Info();
		try {
			int res = prdctHomePageDtlRepo.deleteHomepageStatusDtl(configStatusId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Detail Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Detail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Data Configure Product Home Page Header Detail
	@RequestMapping(value = { "/deleteHomePagePrdct" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteHomePagePrdct(@RequestParam int id) {

		Info info = new Info();
		try {
			int res = prdctHomeRepo.deleteHomePageConfig(id);
			if (res > 0) {
				int del = prdctHomePageDtlRepo.deleteHomepageStatusDtl(id);

				info.setError(false);
				info.setMessage("Configuration Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Configuration");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/*-----------------------------------------------------------------------------------------*/
	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Home Page Testimonials List
	@RequestMapping(value = { "/getTestimonials" }, method = RequestMethod.POST)
	public @ResponseBody List<HomePageTestimonial> getTestimonials(@RequestParam int compId) {

		List<HomePageTestimonial> list = new ArrayList<HomePageTestimonial>();
		try {
			list = testimonialRepo.findByCompanyIdAndDelStatusOrderByTestimonialsIdDesc(compId, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// Created By :- Mahendra Singh
	// Created On :- 31-12-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Home Page Testimonials List For Excel Pdf
	@Autowired ExcelHomePageTestimonialRepo exlHmPgTestmnlRepo;
	@RequestMapping(value = { "/getHomePgTestmnlExlPdf" }, method = RequestMethod.POST)
		public @ResponseBody List<GetHomePageTestimonial> getHomePgTestmnlExlPdf(@RequestParam int compId) {

			List<GetHomePageTestimonial> list = new ArrayList<GetHomePageTestimonial>();
			try {
				list = exlHmPgTestmnlRepo.getHomePgTestimnlExlPdf(compId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;

		}

	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Home Page Testimonials List
	@RequestMapping(value = { "/saveTestimonial" }, method = RequestMethod.POST)
	public @ResponseBody HomePageTestimonial saveTestimonials(@RequestBody HomePageTestimonial testimonial) {

		HomePageTestimonial newTestimonial = new HomePageTestimonial();
		try {
			newTestimonial = testimonialRepo.save(testimonial);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newTestimonial;

	}

	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get Home Page Testimonial By Id
	@RequestMapping(value = { "/getTestimonialsById" }, method = RequestMethod.POST)
	public @ResponseBody HomePageTestimonial getTestimonialsById(@RequestParam int testimonialId) {

		HomePageTestimonial testimonial = new HomePageTestimonial();
		try {
			testimonial = testimonialRepo.findByTestimonialsId(testimonialId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testimonial;

	}

	// Created By :- Mahendra Singh
	// Created On :- 22-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Delete Home Page Testimonial By Id
	@RequestMapping(value = { "/deleteTestimonialsById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteTestimonialsById(@RequestParam int testimonialId) {

		Info info = new Info();
		try {
			int res = testimonialRepo.deleteTestimonial(testimonialId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Testimonial Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Testimonial");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	/*--------------------------------------------------------------------------------------------*/

	@RequestMapping(value = { "/insertDesignation" }, method = RequestMethod.POST)
	public @ResponseBody Designation insertDesignation(@RequestBody Designation desig) {

		Designation res = new Designation();
		try {
			res = desigRepo.save(desig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@RequestMapping(value = { "/getDesignations" }, method = RequestMethod.GET)
	public @ResponseBody List<Designation> getDesignations() {

		List<Designation> list = new ArrayList<Designation>();
		try {
			list = desigRepo.findByDelStatusOrderByDesignationIdDesc(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/getDesignationsByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<Designation> getDesignationsByCompId(@RequestParam int compId) {

		List<Designation> list = new ArrayList<Designation>();
		try {
			list = desigRepo.findByDelStatusAndExInt1OrderByDesignationIdDesc(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/getDesignationById" }, method = RequestMethod.POST)
	public @ResponseBody Designation getDesignationById(@RequestParam int desigId, @RequestParam int compId) {

		Designation designation = new Designation();
		try {
			designation = desigRepo.findBydesignationIdAndExInt1(desigId, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return designation;

	}

	@RequestMapping(value = { "/deleteDesignationById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteDesignationById(@RequestParam int desigId) {

		Info info = new Info();
		try {
			int res = desigRepo.deleteDesignation(desigId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Designation Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Designation");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/getProdIdCntByCatId" }, method = RequestMethod.POST)
	public @ResponseBody int getProdIdCntByCatId(@RequestParam int catId) {

		int pordCnt = 0;
		try {
			pordCnt = productMasterRepo.getProdCntByCatId(catId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pordCnt;
	}

	@RequestMapping(value = { "/getProdIdCntByTax" }, method = RequestMethod.POST)
	public @ResponseBody int getProdIdCntByTax(@RequestParam int taxId) {

		int pordCnt = 0;
		try {
			pordCnt = productMasterRepo.getProdCntByTaxId(taxId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pordCnt;
	}

	@RequestMapping(value = { "/getProdIdCntByUomId" }, method = RequestMethod.POST)
	public @ResponseBody int getProdIdCntByUomId(@RequestParam int uomId) {

		int pordCnt = 0;
		try {
			pordCnt = productMasterRepo.getProdCntByUomId(uomId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pordCnt;
	}
}
