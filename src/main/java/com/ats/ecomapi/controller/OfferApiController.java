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

 import com.ats.ecomapi.master.repo.ImagesService;
import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.offer.repo.CompanyServices;
import com.ats.ecomapi.offer.repo.OfferDetailRepo;
import com.ats.ecomapi.offer.repo.OfferHeaderRepo;
import com.ats.ecomapi.offer_model.GetConfigureOfferList;
import com.ats.ecomapi.offer_model.GetOfferFrConfiguredList;
import com.ats.ecomapi.offer_model.Images;
import com.ats.ecomapi.offer_model.OfferConfig;
import com.ats.ecomapi.offer_model.OfferDetail;
import com.ats.ecomapi.offer_model.OfferHeader;

 
 

@RestController
public class OfferApiController {
	@Autowired
	OfferHeaderRepo offerHeaderRepo;

	@Autowired
	OfferDetailRepo offerDetailRepo;

	@Autowired
	ImagesService imagesService;
	
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- saveOfferHeader
	
	@RequestMapping(value = { "/saveOfferHeader" }, method = RequestMethod.POST)
	public @ResponseBody OfferHeader saveOfferHeader(@RequestBody OfferHeader offerHeader) {
		

		OfferHeader res = offerHeaderRepo.save(offerHeader);

		if (res == null) {
			res = new OfferHeader();
		}

		return res;
	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- saveOfferDetailList
 	@RequestMapping(value = { "/saveOfferDetailList" }, method = RequestMethod.POST)
	public @ResponseBody Info saveOfferDetailList(@RequestBody List<OfferDetail> offerDetailList) {

		Info info = new Info();

		List<OfferDetail> res = offerDetailRepo.saveAll(offerDetailList);
		if (res != null) {
			info.setError(false);
			info.setMessage("Success");
		} else {
			info.setError(true);
			info.setMessage("Failed");
		}

		return info;
	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getOfferHeaderById
	@RequestMapping(value = { "/getOfferHeaderById" }, method = RequestMethod.POST)
	public @ResponseBody OfferHeader getOfferHeaderById(@RequestParam("offerId") int offerId) {

		OfferHeader res = offerHeaderRepo.findByOfferId(offerId);

		if (res == null) {
			res = new OfferHeader();
		}

		return res;
	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- deleteOfferHeaderById
	@RequestMapping(value = { "/deleteOfferHeaderById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteOfferHeaderById(@RequestParam("offerId") int offerId,
			@RequestParam("status") int status) {

		Info info = new Info();

		int res = offerHeaderRepo.deleteOfferHeader(offerId, status);
		if (res > 0) {
			info.setError(false);
			info.setMessage("Success");
		} else {
			info.setError(true);
			info.setMessage("Failed");
		}

		return info;
	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getAllOfferHeaderListByCompId
	@RequestMapping(value = { "/getAllOfferHeaderListByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<OfferHeader> getAllOfferHeaderListByCompId(@RequestParam("compId") int compId) {

		List<OfferHeader> res = offerHeaderRepo.findByCompIdAndDelStatusAndIsActive(compId, 1, 1);

		if (res == null) {
			res = new ArrayList<>();
		}

		return res;
	}

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getOfferDetailListByOfferId
	@RequestMapping(value = { "/getOfferDetailListByOfferId" }, method = RequestMethod.POST)
	public @ResponseBody List<OfferDetail> getOfferDetailListByOfferId(@RequestParam("offerId") int offerId) {

		List<OfferDetail> res = offerDetailRepo.findAllByOfferIdAndIsActiveAndDelStatus(offerId, 1, 1);

		if (res == null) {
			res = new ArrayList<>();
		}

		return res;
	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- updateOfferType
	@RequestMapping(value = { "/updateOfferType" }, method = RequestMethod.POST)
	public @ResponseBody Info updateOfferType(@RequestParam("offerId") int offerId, @RequestParam("type") int type) {

		Info info = new Info();

		int res = offerHeaderRepo.updateOfferType(offerId, type);
		if (res > 0) {
			info.setError(false);
			info.setMessage("Success");
		} else {
			info.setError(true);
			info.setMessage("Failed");
		}

		return info;
	}
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- removeOfferDetailIds

	@RequestMapping(value = { "/removeOfferDetailIds" }, method = RequestMethod.POST)
	public @ResponseBody Info removeOfferDetailIds(@RequestParam("offerDetailIds") List<Integer> offerDetailIds) {

		Info info = new Info();

		int res = offerDetailRepo.deleteOfferDetails(offerDetailIds);
		if (res > 0) {
			info.setError(false);
			info.setMessage("Success");
		} else {
			info.setError(true);
			info.setMessage("Failed");
		}

		return info;
	}
	
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getAllOfferHeads
	
	@RequestMapping(value = { "/getAllOfferHeads" }, method = RequestMethod.POST)
	public @ResponseBody List<OfferHeader> getAllOfferHeads(@RequestParam int compId) {

		List<OfferHeader> frOfferList = new ArrayList<OfferHeader>();
		try {
			frOfferList = companyService.getFrOfferConfigList(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frOfferList;
	}
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- updateOfferImg

	@RequestMapping(value = { "/updateOfferImg" }, method = RequestMethod.POST)
	public @ResponseBody Info updateOfferImg(@RequestParam("filesList") String filesList,@RequestParam("offerId") int offerId) {

		Info info = new Info();

		int res = offerHeaderRepo.updateImage(filesList,offerId);
		if (res > 0) {
			info.setError(false);
			info.setMessage("Success");
		} else {
			info.setError(true);
			info.setMessage("Failed");
		}

		return info;
	}
	
	
	
	
	@Autowired
	CompanyServices companyService;

	
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getConfigureOfferList
	/************************** Fr Offer Config ********************************/
	@RequestMapping(value = { "/getConfigureOfferList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetConfigureOfferList> getConfigureOfferList(@RequestParam int offerId, @RequestParam int compId) {

		List<GetConfigureOfferList> frOfferList = new ArrayList<GetConfigureOfferList>();
		try {
			frOfferList = companyService.getConfigureOfferListById(offerId, compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frOfferList;
	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getOfferInfoByOfferId
	@RequestMapping(value = { "/getOfferInfoByOfferId" }, method = RequestMethod.POST)
	public @ResponseBody OfferConfig getOfferInfoByOfferId(@RequestParam int offerId) {

		OfferConfig offer = new OfferConfig();
		try {
			offer = companyService.getConfigureFrOfferDetailById(offerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offer;
	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- addFrOfferConfiguration
	@RequestMapping(value = { "/addFrOfferConfiguration" }, method = RequestMethod.POST)
	public @ResponseBody OfferConfig addFrOfferConfiguration(@RequestBody OfferConfig offer) {

		OfferConfig saveOffer = new OfferConfig();
		try {
			saveOffer = companyService.insertFrOfferConfig(offer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveOffer;
	}

	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- updateFrOfferConfig
	@RequestMapping(value = { "/updateFrOfferConfig" }, method = RequestMethod.POST)
	public @ResponseBody Info updateFrOfferConfig(@RequestParam String frIdStr, @RequestParam int offerId,
			@RequestParam String updtTime, @RequestParam int userId) {

		Info info = new Info();
		try {
			int res = companyService.udateFrOfferConfig(frIdStr, offerId, updtTime, userId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Update Franchisee Offer Configuration Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed Update Franchisee Offer Configuration");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- deleteFrOfferConfigById

	@RequestMapping(value = { "/deleteFrOfferConfigById" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteFrOfferConfigById(@RequestParam int frOfferConfigId) {

		Info info = new Info();
		try {
			int res = companyService.deleteFrOfferConfig(frOfferConfigId);
			if (res > 0) {
				info.setError(false);
				info.setMessage("Delete Franchisee Offer Configuration Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed To Delete Franchisee Offer Configuration");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 18-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getAllOfferFrConfiguredList
	@RequestMapping(value = { "/getAllOfferFrConfiguredList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetOfferFrConfiguredList> getAllOfferFrConfiguredList(@RequestParam int compId) {

		List<GetOfferFrConfiguredList> offerList = new ArrayList<GetOfferFrConfiguredList>();
		try {
			offerList = companyService.getOfferFrConfiguredList(compId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offerList;
	}

	/********************************/
	
	
	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- saveImage
	
	@RequestMapping(value = { "/saveImage" }, method = RequestMethod.POST)
	public @ResponseBody Images saveImage(@RequestBody Images image) {
		
		
		Images res = new Images();
		try {
			res = imagesService.saveImage(image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}


	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- saveMultipleImage
	@RequestMapping(value = { "/saveMultipleImage" }, method = RequestMethod.POST)
	public @ResponseBody Info saveMultipleImage(@RequestBody List<Images> imageList) {
		Info info = imagesService.saveMultipleImages(imageList);
		return info;
	}


	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- getImagesByDocIdAndDocType
	@RequestMapping(value = { "/getImagesByDocIdAndDocType" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getImagesByDocId(@RequestParam int selectId) {
		/*
		 * List<Images> res = imagesService.getImageListByDocIdAndDocType(docId,
		 * docType); return res;
		 */
		
		List<String> convertedRankList = new ArrayList<String>();
	 
		try {
			OfferHeader res = offerHeaderRepo.findByOfferId(selectId);

			if(res!=null) {
				String[] convertedRankArray = res.getOfferImages().split(",");
			
				for (String number : convertedRankArray) {
				    convertedRankList.add(number.trim());
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return  convertedRankList;
		
	}
	
	

	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- deleteByImageOfOffer
	@RequestMapping(value = { "/deleteByImageOfOffer" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteByImageOfOffer(@RequestParam int offerId,@RequestParam String imageName) {
		
		
		Info info = new Info();
		
		int n=offerHeaderRepo.removeImage(imageName, offerId);
		if (n > 0) {
			info.setError(false);
			info.setMessage("Delete Image Successfully");
		} else {
			info.setError(true);
			info.setMessage("Failed To Delete Image ");
		}
		
		
		return info;
	
	
	}
	
	


	/*--------------------------------------------------------------------------------*/
	// Created By :- Harsha Patil
	// Created On :- 21-09-2020
	// Modified By :- NA
	// Modified On :- NA
	// Descriprion :- deleteByImageId
	@RequestMapping(value = { "/deleteByImageId" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteByImageId(@RequestParam int imageId) {
		Info res = imagesService.deletImageById(imageId);
		return res;
	}


}
