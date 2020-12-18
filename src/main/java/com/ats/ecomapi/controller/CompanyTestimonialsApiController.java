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

import com.ats.ecomapi.master.model.CompanyTestomonials;
import com.ats.ecomapi.master.repo.CompanyTestomonialsRepo;
import com.ats.ecomapi.mst_model.Info;


//Akhilesh 2020-12-17
@RestController
public class CompanyTestimonialsApiController {
	
	@Autowired
	CompanyTestomonialsRepo companyTestomonialsRepo;
	
	//Akhilesh 2020-12-17
	@RequestMapping(value="/getCompanyTestomonialsList",method=RequestMethod.GET)
	public List<CompanyTestomonials> getCompanyTestomonialsList(){
		
		List<CompanyTestomonials> getCompTestList=new ArrayList<>();
		
		
		try {
			getCompTestList=companyTestomonialsRepo.getCompanyTestomonialsList();
			System.err.println("getCompTestList Size--->"+getCompTestList.size());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occurerd In /getCompanyTestomonialsList");
			e.printStackTrace();
		}
		
			return getCompTestList;
		
	}
	
	
	//Akhilesh 2020-12-17
	@RequestMapping(value="/saveCompanyTestimonial",method=RequestMethod.POST)
	public @ResponseBody CompanyTestomonials saveCompanyTestimonial(@RequestBody CompanyTestomonials cmp ) {
		CompanyTestomonials cmpTest=new CompanyTestomonials();
		
		try {
			cmpTest=companyTestomonialsRepo.save(cmp);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /saveCompanyTestimonial");
			e.printStackTrace();
		}
		return cmpTest;
	}
	
	
	//Akhilesh 2020-12-17
	@RequestMapping(value="/deleteCompanyTestimonial",method=RequestMethod.POST)
	public @ResponseBody Info deleteCompanyTestimonial(@RequestParam int id) {
		Info info=new Info();
		int flag=0;
		
		try {
			flag=companyTestomonialsRepo.deleteCompanyTestimonial(id);
			if(flag>0) {
				info.setError(false);
				info.setMessage("Company Testimonial Successfully Deleted!!!");
			}else {
				info.setError(true);
				info.setMessage(" Unable To Delete Company Testimonial!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMessage(" Unable To Delete Company Testimonial Exception Occured!!!");
			System.err.println("Exception Occured In /deleteCompanyTestimonial");
			e.printStackTrace();
		}
		
		
		return info;
	}
	
	@RequestMapping(value="/getCompanyTestomonialsyId",method=RequestMethod.POST)
	public CompanyTestomonials getCompanyTestomonialsyId(@RequestParam int testimonialId){
		
		CompanyTestomonials getCompTest=new CompanyTestomonials();		
		
		try {
			getCompTest=companyTestomonialsRepo.findById(testimonialId);
		} catch (Exception e) {
		
			System.err.println("Exception Occurerd In /getCompanyTestomonialsyId");
			e.printStackTrace();
		}
		
			return getCompTest;
		
	}
	
	
	
	
	

}
