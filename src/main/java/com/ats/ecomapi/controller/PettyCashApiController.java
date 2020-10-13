package com.ats.ecomapi.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ecomapi.master.model.Setting;
import com.ats.ecomapi.master.repo.FrEmpMasterRepo;
import com.ats.ecomapi.master.repo.SettingRepository;
import com.ats.ecomapi.mst_model.FrEmpMaster;
import com.ats.ecomapi.mst_model.Info;

@RestController
public class PettyCashApiController {

	/***************************************
	 * Franchisee Employee
	 *****************************/

	@Autowired
	FrEmpMasterRepo frEmpRepo;

	/*
	 * @RequestMapping(value = { "/getAllFrEmpByFrid" }, method =
	 * RequestMethod.POST) public List<FrEmpMaster> getAllFrEmpByFrid(@RequestParam
	 * int frId) { List<FrEmpMaster> list = new ArrayList<FrEmpMaster>(); try { list
	 * = frEmpRepo.findByFrIdAndDelStatus(frId, 0);
	 * 
	 * // System.err.println("List-----------" + list); } catch (Exception e) { //
	 * System.err.println("Exception in getAllFrEmpByFrid : " + e.getMessage());
	 * e.printStackTrace(); } return list;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/getAllFrEmp" }, method = RequestMethod.POST)
	public List<FrEmpMaster> getAllFrEmp(@RequestParam int frId) {
		List<FrEmpMaster> list = new ArrayList<FrEmpMaster>();
		try {

			list = frEmpRepo.findByFrId(frId);
			// System.err.println("List-----------" + list);
		} catch (Exception e) {
			// System.err.println("Exception in getAllFrEmpByFrid : " + e.getMessage());
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/getAllFrEmpsByDesination" }, method = RequestMethod.POST)
	public List<FrEmpMaster> getAllFrEmpsByDesination(@RequestParam int frId, @RequestParam int desig) {
		List<FrEmpMaster> list = new ArrayList<FrEmpMaster>();
		try {

			list = frEmpRepo.findByFrIdAndDesignationAndDelStatus(frId, desig, 0);
			// System.err.println("List-----------" + list);
		} catch (Exception e) {
			// System.err.println("Exception in getAllFrEmpsByDesination : " +
			// e.getMessage());
			e.printStackTrace();
		}
		return list;

	}

	@RequestMapping(value = { "/getFrEmpByEmpId" }, method = RequestMethod.POST)
	public FrEmpMaster getFrEmpByEmpId(@RequestParam int empId) {
		FrEmpMaster emp = new FrEmpMaster();
		try {
			emp = frEmpRepo.findByFrEmpId(empId);
		} catch (Exception e) {
			// System.err.println("Exception in getFrEmpByEmpId : " + e.getMessage());
			e.printStackTrace();
		}
		return emp;
	}

	@Autowired
	SettingRepository settingRepository;

	@RequestMapping(value = { "/saveFrEmpDetails" }, method = RequestMethod.POST)
	public FrEmpMaster saveFrEmpDetails(@RequestBody FrEmpMaster emp) {
		FrEmpMaster frEmp = new FrEmpMaster();
		int id = emp.getFrEmpId();
		try {
			frEmp = frEmpRepo.save(emp);
			if (id == 0) {
				if (frEmp != null) {
					Setting setting = settingRepository.findBySettingId(3);
					int val = Integer.parseInt(setting.getSettingValue() + 1);

					int value = settingRepository.udatekeyvalueForFrEmpCode(val);
				}
			}

		} catch (Exception e) {
			// System.err.println("Exception in saveFrEmpDetails : " + e.getMessage());
			e.printStackTrace();
		}
		return frEmp;
	}
	
	@RequestMapping(value = "/getSettingDataById", method = RequestMethod.POST)
	public @ResponseBody Setting getSettingDataById(@RequestParam("settingId") int settingId) {

		Setting Updatevalue = settingRepository.findBySettingId(settingId);

		return Updatevalue;

	}

	@RequestMapping(value = "/updateValueForFrEmpCode", method = RequestMethod.GET)
	public @ResponseBody int updateValueForFrEmpCode() {

		Setting setting = settingRepository.findBySettingId(3);
		int val = Integer.parseInt(setting.getSettingValue() + 1);

		int value = settingRepository.udatekeyvalueForFrEmpCode(val);

		return value;

	}

	@RequestMapping(value = { "/delFrEmp" }, method = RequestMethod.POST)
	public Info delFrEmp(@RequestParam int empId) {
		Info info = new Info();
		try {
			int res = frEmpRepo.deleteEmpByfrEmpId(empId);
			if (res != 0) {
				info.setError(false);
				info.setMessage("Sucess");
			} else {
				info.setError(true);
				info.setMessage("Fail");
			}
		} catch (Exception e) {
			// System.err.println("Exception in saveFrEmpDetails : " + e.getMessage());
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/checkUniqueContactNo" }, method = RequestMethod.POST)
	public Info checkUniqueContactNo(int frId, String mobNo) {
		Info info = new Info();
		try {
			FrEmpMaster emp = new FrEmpMaster();
			// emp = frEmpRepo.findByFrIdAndFrEmpContactAndDelStatus(frId, mobNo, 0);
			emp = frEmpRepo.findByFrIdAndFrEmpContactAndDelStatus(frId, mobNo, 0);
			// System.out.println("Emp-------" + emp);
			if (emp != null) {
				// System.out.println("Contact No. Found");
				info.setError(false);
				info.setMessage("" + emp.getFrEmpId());
			} else {
				// System.out.println("Contact No. Not Found");
				info.setError(true);
				info.setMessage("0");
			}
		} catch (Exception e) {
			// System.err.println("Exception in checkUniqueContactNo : " + e.getMessage());
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = { "/updateFrEmpPassword" }, method = RequestMethod.POST)
	public Info updateFrEmpPassword(int empId, String pass) {
		Info info = new Info();
		try {
			int res = frEmpRepo.updateEmpPass(empId, pass);

			if (res != 0) {
				info.setError(false);
				info.setMessage("Success");
			} else {
				info.setError(true);
				info.setMessage("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("Failed");
		}
		return info;
	}

}
