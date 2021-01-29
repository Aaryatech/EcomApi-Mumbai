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
import com.ats.ecomapi.DeliveryBoy_Repo.OrderHeaderRepo1;
import com.ats.ecomapi.DeliveryBoy_Repo.orheaderRepo;
import com.ats.ecomapi.common.CommonUtility;
//import com.ats.ecomapi.common.OrHeader;
import com.ats.ecomapi.fe_model.DBoyLoginResponse;
import com.ats.ecomapi.fe_model.OrHeader;
import com.ats.ecomapi.fe_model.OrderDetail1;
import com.ats.ecomapi.fe_model.OrderHeader1;
import com.ats.ecomapi.fe_model.DeliveryBoy;
import com.ats.ecomapi.fe_model.Grievances;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.mst_model.OrderDetail;
@RestController
public class DeliveryBoyApiController {
	@Autowired
     DeliveryBoyRepo1 dboyRepo;
	
	@Autowired
	 OrderHeaderRepo1 ohRepo;
	
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
		// count=dboyRepo.CheckMobileNo(mobile_no);
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
		public @ResponseBody List<OrHeader> DeliveryBoy(@RequestParam Integer order_delivered_by,@RequestParam String order_status)
		{
			List<OrHeader> or1=new ArrayList<>();
			 or1=orheadRepo.toMatchOrderIdN(order_delivered_by,order_status);
	        // List <OrHeader> or=or1.getOrderId();
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
			return or1;
			
		}
		
		
        //3)Profile Update API For Delivery Boy
		@RequestMapping(value= {"/profileUpdate"},method=RequestMethod.PUT)
		public @ResponseBody DeliveryBoy profileUpdate(@RequestBody DeliveryBoy Dboy )
		{
	//		DeliveryBoy boy=new DeliveryBoy();
			DeliveryBoy	boy=updateRepo.save(Dboy);

			//Integer count=dboyRepo.CheckMobileNo(Dboy.getDelBoyId());
			Integer c=Dboy.getDelBoyId();
			System.out.println(c);
//			if(c!=0) {
//
//				 boy =new DeliveryBoy();
//				boy=updateRepo.save(Dboy);
//			}
//			
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
		
	
}

//Get Header and Product Detail API   
//@RequestMapping(value= {"/dBoyOrderListDetail"},method=RequestMethod.GET)
//public @ResponseBody OrderHeader1 dBoyOrderListDetail(@RequestParam Integer order_id)
//{
//		//OrHeader head=new  OrHeader();
//			//List<OrDetail> detailList=new ArrayList<>();
//			//detailList
//			//head.setDetailList(detailList);
//	
//	OrderHeader1 head=ohRepo.toMatchOrderIdNo(order_id);
//	//head.getParameter("detail_order_id");
//	//Integer order_detail_id = head.getOrderDetailId();
//	
//	List<OrderDetail1> detailList=new ArrayList<>();
//	detailList =odRepo.getProductDetail(order_id);
//	head.setDetailList(detailList);
//	
//	//OrderDetail pro=odRepo.getProductDetail(product_id);
//	return head;
//}
