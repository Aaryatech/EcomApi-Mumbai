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

import com.ats.ecomapi.master.model.Area;
import com.ats.ecomapi.master.model.AreaCityList;
import com.ats.ecomapi.master.model.Category;
import com.ats.ecomapi.master.model.City;
import com.ats.ecomapi.master.model.DeliveryInstruction;
import com.ats.ecomapi.master.model.FilterTypes;
import com.ats.ecomapi.master.model.Franchise;
import com.ats.ecomapi.master.model.Language;
import com.ats.ecomapi.master.model.MFilter;
import com.ats.ecomapi.master.model.SubCategory;
import com.ats.ecomapi.master.model.Tax;
import com.ats.ecomapi.master.model.Uom;
import com.ats.ecomapi.master.repo.AreaCityListRepo;
import com.ats.ecomapi.master.repo.AreaRepo;
import com.ats.ecomapi.master.repo.CategoryRepo;
import com.ats.ecomapi.master.repo.CityRepo;
import com.ats.ecomapi.master.repo.DeliveryInstructionRepo;
import com.ats.ecomapi.master.repo.FilterTypesRepo;
import com.ats.ecomapi.master.repo.FranchiseRepo;
import com.ats.ecomapi.master.repo.LanguageRepo;
import com.ats.ecomapi.master.repo.MFilterRepo;
import com.ats.ecomapi.master.repo.SubCategoryRepo;
import com.ats.ecomapi.master.repo.TaxRepo;
import com.ats.ecomapi.master.repo.UomRepo;
import com.ats.ecomapi.master.repo.UserTypeRepo;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.User;
import com.ats.ecomapi.mst_model.UserTypeMaster;
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
	DeliveryInstructionRepo delvInstuctRepo ;

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
				info.setMessage("Uom Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed to Delete Uom");
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
			catList = catRepo.findByDelStatusAndCompanyIdOrderByCatId(1, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catList;
	}

	// Created By :- Mahendra Singh
	// Created On :- 12-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Get All Category By Id
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
	public @ResponseBody Info getSubCatByPrefix(@RequestParam("prefix") String prefix,
			@RequestParam("catId") int catId) {

		Info res = new Info();
		try {
			Category value = new Category();
			if (catId == 0) {
				value = catRepo.findByCatPrefixIgnoreCase(prefix);
			} else {
				value = catRepo.findByCatPrefixIgnoreCaseAndCatIdNot(prefix, catId);
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
			fltrTypeList = filterTypeRepo.findByDelStatusAndCompanyIdOrderByFilterTypeIdDesc(1, compId);
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
			fltrTypeList = filterTypeRepo.findByDelStatusAndIsActiveAndCompanyIdOrderByFilterTypeIdDesc(1, 1, compId);
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
	// Modified By :- NA
	// Modified On :- NA
	// Description :- Save Category
	@RequestMapping(value = { "/saveFilter" }, method = RequestMethod.POST)
	public @ResponseBody MFilter saveFilter(@RequestBody MFilter filter) {

		MFilter saveFilter = new MFilter();
		try {
			saveFilter = filterRepo.save(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFilter;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFranshise;
	}

	@RequestMapping(value = { "/getFrCnt" }, method = RequestMethod.POST)
	public @ResponseBody int updateFrGstAndFdaDtl(@RequestParam int compId, @RequestParam String coPrefix) {

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
			langList = langRepo.findByDelStatusAndCompanyId(1, compId);
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
	public @ResponseBody List<City> getAllCities(@RequestParam  int compId) {

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
	public @ResponseBody List<Area> getAreaByCityIdAndCopmId(@RequestParam List<Integer> cityId,
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

				instruct = delvInstuctRepo.findByInstructnCaptionIgnoreCaseAndCompanyIdAndInstruIdNot(caption, compId, instructId);
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

}
