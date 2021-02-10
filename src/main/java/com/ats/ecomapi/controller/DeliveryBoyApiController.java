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
	public @ResponseBody Object dBoyLogin(@RequestParam String mobile_no){
			Info info=new Info();
		    DBoyLoginResponse boy=dboyRepo.toCheckMobileNo(mobile_no);
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
   	      return boy;
		    
	 }
		
	  
        //2)Get Header and Product Detail API By Order Status		
		@RequestMapping(value= {"/DeliveryBoy"},method=RequestMethod.GET)
		public @ResponseBody HeadObject DeliveryBoy(@RequestParam Integer order_delivered_by,@RequestParam String order_status)
		{//toArray()
				HeadObject hr1=new HeadObject();
			
			  List<OrHeader> or1=new ArrayList<>();
			  or1=orheadRepo.toMatchOrderIdN(order_delivered_by,order_status);
	          List<OrderDetail1> detailList=new ArrayList<>();
	          detailList =odRepo.getProductDetail(order_delivered_by,order_status);
	     
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
			
		}
		
	
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
       public @ResponseBody Info dBoyOTP(@RequestParam String mobile_no,@RequestParam String message)
       {
          // Info info=new Info();
          Info info=SMSUtility.sendSMS(mobile_no,message);
          
	      return info;
       }
       }
