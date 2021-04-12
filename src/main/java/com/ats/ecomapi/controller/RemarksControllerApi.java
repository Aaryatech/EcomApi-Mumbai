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

import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.MRemarks;
import com.ats.ecomapi.mst_repo.MremarksRepository;



@RestController
public class RemarksControllerApi {

	
	@Autowired
	MremarksRepository remarkRepo;
	
	@RequestMapping(value="/getAllRemarks",method=RequestMethod.GET)
	public @ResponseBody List<MRemarks> getAllRemarks(){
		List<MRemarks> remakResp=new ArrayList<>();
		try {
			remakResp=remarkRepo.getAllRemarks();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception In /getAllRemarks");
			e.printStackTrace();
		}
		
		return remakResp;
		
		
	}
	
	
	@RequestMapping(value="/getRemarkById",method=RequestMethod.POST)
	public @ResponseBody MRemarks getRemarkById(@RequestParam int remarkId) {
		MRemarks remark=new MRemarks();
		try {
			remark=remarkRepo.getRemarkById(remarkId);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception In /getRemarkById");
		}
		return remark;
	}
	
	
	
	
	
	
	@RequestMapping(value="/deleteRemarkById",method=RequestMethod.POST)
	public @ResponseBody Info deleteRemarkById(@RequestParam int remarkId) {
		Info info=new Info();
		int flag=0;
		try {
			flag=remarkRepo.deleteRemark(remarkId);
			if(flag>0) {
				info.setError(false);
				info.setMessage("Remark Deleted");
			}else {
				info.setError(true);
				info.setMessage("Unable To Delete Remark ");
			}
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMessage("Unable To Delete Remark Exception Occuered ");
			System.err.println("Exception In /deleteRemarkById");
		}
		return info;
	}
	
	
	@RequestMapping(value="/addNewRemark",method=RequestMethod.POST)
	public @ResponseBody MRemarks addNewRemark(@RequestBody MRemarks remarkReq) {
		MRemarks remark=new MRemarks();
		try {
			remark=remarkRepo.save(remarkReq);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception In /addNewRemark");
			e.printStackTrace();
		}
		
		return remark;
	}
	
	
	
	
	
}
