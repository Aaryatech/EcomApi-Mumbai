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

import com.ats.ecomapi.ac_rights.AccessRightModule;
import com.ats.ecomapi.ac_rights.AccessRightModuleList;
import com.ats.ecomapi.ac_rights.AccessRightService;
import com.ats.ecomapi.ac_rights.AssignRoleDetailList;
import com.ats.ecomapi.ac_rights.AssignRoleDetailListRepository;
import com.ats.ecomapi.ac_rights.CreatedRoleList;
import com.ats.ecomapi.master.model.GetUser;
import com.ats.ecomapi.master.repo.GetUserRepo;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.User;
import com.ats.ecomapi.mst_repo.UserRepo;


@RestController
public class AccessRightApiController {
 
	@Autowired
	AccessRightService accessRightService;
	@Autowired
	AssignRoleDetailListRepository assignRoleDetailListRepository;
	 @Autowired UserRepo userListRepository;

	@RequestMapping(value = { "/deleteRole" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteRole(@RequestParam int roleId) {
		
		int res = 0;
		int isDeleted=0;
		int taskCount = 0;
		if (taskCount < 1) {
		 isDeleted = assignRoleDetailListRepository.deleteRole(roleId);
		}
		Info info = new Info();
		if (isDeleted>0) {
			info.setError(false);
			info.setMessage("Role  Deleted");
		} else {
			info.setError(true);
			info.setMessage("Role Deletion Failed");
		}
		return info;
	}

	@RequestMapping(value = { "/getAllModuleAndSubModule" }, method = RequestMethod.GET)
	public @ResponseBody AccessRightModuleList getAllModuleAndSubModule() {

		AccessRightModuleList accessRightModuleList = new AccessRightModuleList();

		List<AccessRightModule> accessRightModule = accessRightService.getAllModulAndSubModule();

		Info info = new Info();
		if (accessRightModule != null && !accessRightModule.isEmpty()) {
			accessRightModuleList.setAccessRightModuleList(accessRightModule);
			info.setError(false);
			info.setMessage("Success");
		} else {

			info.setError(true);
			info.setMessage("failed");
		}
		accessRightModuleList.setInfo(info);
		return accessRightModuleList;
	}

	@RequestMapping(value = { "/saveAssignRole" }, method = RequestMethod.POST)
	public @ResponseBody Info saveAssignRole(@RequestBody AssignRoleDetailList assignRoleDetailList) {

		Info info = accessRightService.saveAssignRole(assignRoleDetailList);

		return new Info();
	}

	@RequestMapping(value = { "/getAllAccessRole" }, method = RequestMethod.GET)
	public @ResponseBody CreatedRoleList getAllAccessRole() {

		CreatedRoleList createdRoleList = new CreatedRoleList();

		List<AssignRoleDetailList> assignRoleDetailList = accessRightService.getAllAccessRole();

		Info info = new Info();
		if (assignRoleDetailList != null && !assignRoleDetailList.isEmpty()) {
			createdRoleList.setAssignRoleDetailList(assignRoleDetailList);
			info.setError(false);
			info.setMessage("Success");
		} else {

			info.setError(true);
			info.setMessage("failed");
		}
		createdRoleList.setInfo(info);
		return createdRoleList;
	}

	@RequestMapping(value = { "/updateEmpRole" }, method = RequestMethod.POST)
	@ResponseBody
	public Info updateEmpRole(@RequestParam("id") int id, @RequestParam("roleId") int roleId) {
		return accessRightService.updateRoleIdByEmpId(id, roleId);
	}

	@RequestMapping(value = { "/getRoleJson" }, method = RequestMethod.POST)
	@ResponseBody
	public String getRoleJson(@RequestParam("userId") int userId) {

		return accessRightService.getRoleJson(userId);
	}
	
	//11 April..Sachin
	@RequestMapping(value = { "/getRoleJsonByRoleId" }, method = RequestMethod.POST)
	@ResponseBody
	public String getRoleJsonByRoleId(@RequestParam int roleId) {

		return accessRightService.getRoleJsonByRoleId(roleId,1);
		
	}
	@Autowired GetUserRepo getUserRepo;
	
	@RequestMapping(value = {"/getUserListForAssignRole"}, method = RequestMethod.POST)
	public @ResponseBody List<GetUser> getAllEmployees(@RequestParam("compId") int compId){
		
		List<GetUser> empList = new ArrayList<GetUser>();
		try {
			empList = getUserRepo.getUserListForAssignRole(compId);
		}catch (Exception e) {
			System.err.println("Exce in getUserListForAssignRole  " + e.getMessage());
		}
		
		return empList;
		
	}
	
	@RequestMapping(value = { "/updateRoleOfUser" }, method = RequestMethod.POST)
	@ResponseBody
	public Info updateRoleOfUser(@RequestParam("userIdList") List<String> userIdList,@RequestParam("roleId") int roleId,
			@RequestParam("makerUserId") int makerUserId ,
			@RequestParam("makerDttime")String makerDttime) {

		Info info = new Info();
		try {
			
			int update =userListRepository.updateRoleId(roleId, userIdList, makerUserId, makerDttime);
			info.setError(false);
			info.setMessage("update");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			info.setError(true);
			info.setMessage("failed");
		}
		return info;
		
	}
	
	@RequestMapping(value = { "/getRoleByRoleId" }, method = RequestMethod.POST)
	@ResponseBody
	public AssignRoleDetailList getRoleByRoleId( @RequestParam("roleId") int roleId) {

		AssignRoleDetailList role = new AssignRoleDetailList();
		try {
			
			role =assignRoleDetailListRepository.findByRoleIdAndDelStatus(roleId, 1);//(roleId);
			 
		}catch(Exception e) {
			
			e.printStackTrace();
			 
		}
		return role;
		
	}
	
	//assignRoleDetailListRepository;
	
		//10 April
	
	@RequestMapping(value = { "/getRoleIdsByRoleNameList" }, method = RequestMethod.POST)
	@ResponseBody
	public List<AssignRoleDetailList> getRoleIdsByRoleNameList(@RequestParam List<String> roleNameList) {

		List<AssignRoleDetailList> roleNameListRes = new ArrayList<>();
		
		try {
			roleNameListRes =assignRoleDetailListRepository.findByDelStatusAndRoleNameIn(1, roleNameList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return roleNameListRes;
	}
}
