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


import com.ats.ecomapi.DeliveryBoy_Repo.DeliveryBoyProfileUpdate;
import com.ats.ecomapi.DeliveryBoy_Repo.DeliveryBoyRepo1;
import com.ats.ecomapi.DeliveryBoy_Repo.GrievencesRepo;

import com.ats.ecomapi.DeliveryBoy_Repo.OrderDetailRepo1;
import com.ats.ecomapi.DeliveryBoy_Repo.orheaderRepo;
import com.ats.ecomapi.common.CommonUtility;
import com.ats.ecomapi.fe_model.DeliveryBoy;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.OrderDetail;
import com.ats.ecomapi.common.SMSUtility;

import com.ats.ecomapi.deliveryboy_model.DBoyLoginResponse;

import com.ats.ecomapi.deliveryboy_model.Grievances;
import com.ats.ecomapi.deliveryboy_model.HeadObject;
import com.ats.ecomapi.deliveryboy_model.OTP;
import com.ats.ecomapi.deliveryboy_model.OrHeader;
import com.ats.ecomapi.deliveryboy_model.OrderDetail1;
@RestController
public class DeliveryBoyApiController {
	@Autowired
     DeliveryBoyRepo1 dboyRepo;
	
	@Autowired
	OrderDetailRepo1 odRepo;
	
	@Autowired
	orheaderRepo orheadRepo;
	
	@Autowired
	DeliveryBoyProfileUpdate updateRepo;

	@Autowired
	GrievencesRepo grRepo;
	
	
    // 1)Delivery Boy Login API By passing Mobile No as Parameter
	@RequestMapping(value= {"/dBoyLogin"},method=RequestMethod.POST)
	public @ResponseBody DBoyLoginResponse dBoyLogin(@RequestParam String mobile_no){
			Info info=new Info();
			DBoyLoginResponse boy=dboyRepo.toCheckMobileNo(mobile_no);
			 System.out.println("msg"+boy);
		    if(boy!=null)
		     {
			  //info.setResponseObject1(CommonUtility.toJSONString(boy));
	           info.setError(false);
		       info.setMsg("Data found");
		    
            }
		    else {
			  boy =new DBoyLoginResponse();
			  info.setError(true);
		      info.setMsg("No data found");
               		}
		    System.out.println("msg"+boy);
   	      return boy;
		    
	 }
		
	    //2)Get Header and Product Detail API By Order Status		
		@RequestMapping(value= {"/getDeliveryBoy"},method=RequestMethod.GET)
		public @ResponseBody HeadObject DeliveryBoy(@RequestParam Integer order_delivered_by,@RequestParam String order_status)
		{//toArray()
				HeadObject hr1=new HeadObject();
			
			  List<OrHeader> or1=new ArrayList<>();
			  or1=orheadRepo.toMatchOrderIdN(order_delivered_by,order_status);
			  
	          List<OrderDetail1> detailList=new ArrayList<>();
	          detailList =odRepo.getProductDetail(order_delivered_by,order_status);
	          System.out.println("detailList"+detailList);
         for(OrHeader o:or1)
         {
        	 
        	 //or1.getOrHeader();
        	ArrayList<OrderDetail1> detail= new ArrayList<>();
        	 for(OrderDetail1 d:detailList)
        	 {
        		// System.err.println("Oreder Id Of->Head="+o.getOrderId()+"dar-->"+d.getOrderId());
        		// System.err.println("deatils-->"+d);
        		 if(o.getOrderId().equals(d.getOrderId())) {
        			 detail.add(d);	 
        		 }
        	 }
        	 o.setDetailList(detail);
        	
         }
       
            hr1.setOrHeader(or1);
          //  System.err.println(hr1);
			return  hr1;
			
		}
		
		/*@RequestMapping(value= {"/DeliveryBoy"},method=RequestMethod.GET)
		public @ResponseBody HeadObject DeliveryBoy(@RequestParam Integer order_delivered_by,@RequestParam String order_status)
		{//toArray()
				HeadObject hr1=new HeadObject();
			
			  List<OrHeader> or1=new ArrayList<>();
			  or1=orheadRepo.toMatchOrderIdN(order_delivered_by,order_status);
	          List<OrderDetail1> detailList=new ArrayList<>();
	          detailList =odRepo.getProductDetail(order_delivered_by,order_status);
	          System.out.println("detailList"+detailList);
         for(OrHeader o:or1)
         {
        	ArrayList<OrderDetail1> detail= new ArrayList<>();
        	 for(OrderDetail1 d:detailList)
        	 {
        		 if(o.getOrderId()==d.getOrderId())
        		 {
        			 detail.add(d);
        		 }
        	 }
        	 o.setDetailList(detail);
        	
         }
       
            hr1.setOrHeader(or1);
			return  hr1;
			
		}*/
		
	
	
	
	
        //3)Profile Update API For Delivery Boy
		@RequestMapping(value= {"/profileUpdate"},method=RequestMethod.PUT)
		public @ResponseBody DeliveryBoy profileUpdate(@RequestBody DeliveryBoy Dboy )
		{
	       //DeliveryBoy boy=new DeliveryBoy();
			DeliveryBoy	boy=updateRepo.save(Dboy);
                System.out.println(Dboy);
			//Integer count=dboyRepo.CheckMobileNo(Dboy.getDelBoyId());
			Integer c=Dboy.getDelBoyId();
			System.out.println(c);
//			if(c!=0) {

//				 boy =new DeliveryBoy();
//				boy=updateRepo.save(Dboy);
//			}
			
			return boy;
		}

        //4)Grievences API
		@RequestMapping(value= {"/grievenceEntry"},method=RequestMethod.POST)
		public @ResponseBody Grievances grievenceEntry(@RequestBody Grievances gre )
		{
         Grievances gr =grRepo.save(gre);
			return gr;
			
		}
		
		
        //5)API For Order Status Update
		@RequestMapping(value= {"/statusUpdate"},method=RequestMethod.POST)
		public @ResponseBody Info statusUpdate(@RequestParam Integer orderId,@RequestParam String orderStatus)
		{
    	Info info=new Info();
		//OrHeader or1;
		 int or1=orheadRepo.toUpdateStatus(orderId,orderStatus);
       if(or1>0)
		{
			info.setMessage("Status Update Successfully");
			info.setError(false);
		}
		else
		{
			info.setMessage("Status Not Update ");
			info.setError(true);
		}
          return info;
			
		}
		
	


       //Get OTP API For Delivery Boy   
       @RequestMapping(value= {"/dBoyOTP"},method=RequestMethod.GET)
       public @ResponseBody OTP dBoyOTP(@RequestParam String mobile_no,@RequestParam String message)
       {
    	   OTP otp =new OTP();
          // OTP otp1 =new OTP();
    	  otp.setOtp(message);
    	   String s="OTP is";
    	    String msg=s+" "+message;  
    	    System.out.println(msg);
          // Info info=new Info();
          SMSUtility.sendSMS(mobile_no,msg);
          
	      return otp;
       }
       



//API For getDelBoyById
@RequestMapping(value= {"/getDelBoyById"},method=RequestMethod.POST)
public @ResponseBody DeliveryBoy getDelBoyById(@RequestParam Integer DelBId)
{
	DeliveryBoy resp=new DeliveryBoy();
	try {
		resp=updateRepo.getDelBoyById(DelBId);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		System.err.println("Exception In /getDelBoyById");
	}
 return resp;
	
}



//API For  Update Token
@RequestMapping(value= {"/delBoyUpdateToken"},method=RequestMethod.POST)
public @ResponseBody Info delBoyUpdateToken(@RequestParam Integer DelBId,@RequestParam String token)
{
Info info=new Info();
//OrHeader or1;
try {
	int or1=updateRepo.delBoyUpdateToken(DelBId, token);
	if(or1>0)
	{
		info.setMessage("Token Update Successfully");
		info.setError(false);
	}
	else
	{
		info.setMessage("Token Not Update ");
		info.setError(true);
	}
} catch (Exception e) {
	// TODO: handle exception
	System.err.println("Exception In /delBoyUpdateToken");
	e.printStackTrace();
}
return info;
	
}



}



