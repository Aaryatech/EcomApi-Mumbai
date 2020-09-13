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

import com.ats.ecomapi.master.model.Category;
import com.ats.ecomapi.master.model.Tax;
import com.ats.ecomapi.master.model.Uom;
import com.ats.ecomapi.master.repo.CategoryRepo;
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

	// Created By :- Mahendra Singh
	// Created On :- 11-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- Get UOM List
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
	// Descriprion :- Save UOM
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
	// Descriprion :- Get Single UOM
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
	// Descriprion :- Delete UOM
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
	// Descriprion :- Get All Tax List
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
	// Descriprion :- Get Active Tax List
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
	// Descriprion :- Save Tax
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
	// Descriprion :- Get Sigle Tax
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
	// Descriprion :- Delete Tax
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
	
	@RequestMapping(value = { "/getCatById" }, method = RequestMethod.POST)
	public @ResponseBody Category getCatById( @RequestParam int catId) {

		Category cat = new Category();
		try {
			cat = catRepo.findByCatId(catId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cat;
	}
	
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
	
	@RequestMapping(value = { "/getCatByPrefix" }, method = RequestMethod.POST)
	public @ResponseBody Info getSubCatByPrefix(@RequestParam("prefix") String prefix,
			@RequestParam("catId") int catId) {

		Info res = new Info();
		try {
			Category value = new Category();
			if(catId==0) {
			  value = catRepo.findByCatPrefixIgnoreCase(prefix);
			}else {
				value = catRepo.findByCatPrefixIgnoreCaseAndCatIdNot(prefix, catId);
			}
			if(value!=null) {
				res.setError(false);
				res.setMessage("Prefix Found");
			}else {
				res.setError(true);
				res.setMessage("Prefix Not  Found");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return res;

	}
}
