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
	
	@RequestMapping(value = { "/saveOfferHeader" }, method = RequestMethod.POST)
	public @ResponseBody OfferHeader saveOfferHeader(@RequestBody OfferHeader offerHeader) {
		
		System.err.println("*******"+offerHeader.toString());

		OfferHeader res = offerHeaderRepo.save(offerHeader);

		if (res == null) {
			res = new OfferHeader();
		}

		return res;
	}

	// Author-Anmol Shirke Created On-22-07-2020
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

	// Author-Anmol Shirke Created On-22-07-2020
	// Desc- Returns OfferHeader object - save OfferHeader.
	@RequestMapping(value = { "/getOfferHeaderById" }, method = RequestMethod.POST)
	public @ResponseBody OfferHeader getOfferHeaderById(@RequestParam("offerId") int offerId) {

		OfferHeader res = offerHeaderRepo.findByOfferId(offerId);

		if (res == null) {
			res = new OfferHeader();
		}

		return res;
	}

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

	// Author-Anmol Shirke Created On-24-07-2020
	@RequestMapping(value = { "/getAllOfferHeaderListByCompId" }, method = RequestMethod.POST)
	public @ResponseBody List<OfferHeader> getAllOfferHeaderListByCompId(@RequestParam("compId") int compId) {

		List<OfferHeader> res = offerHeaderRepo.findByCompIdAndDelStatusAndIsActive(compId, 1, 1);

		if (res == null) {
			res = new ArrayList<>();
		}

		return res;
	}

	// Author-Anmol Shirke Created On-22-07-2020
	@RequestMapping(value = { "/getOfferDetailListByOfferId" }, method = RequestMethod.POST)
	public @ResponseBody List<OfferDetail> getOfferDetailListByOfferId(@RequestParam("offerId") int offerId) {

		List<OfferDetail> res = offerDetailRepo.findAllByOfferIdAndIsActiveAndDelStatus(offerId, 1, 1);

		if (res == null) {
			res = new ArrayList<>();
		}

		return res;
	}

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
	
	
	@RequestMapping(value = { "/getAllOfferHeads" }, method = RequestMethod.POST)
	public @ResponseBody List<OfferHeader> getAllFranchise(@RequestParam int compId) {

		List<OfferHeader> frOfferList = new ArrayList<OfferHeader>();
		try {
			frOfferList = companyService.getFrOfferConfigList(compId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frOfferList;
	}

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

	
	
	/************************** Fr Offer Config ********************************/
	@RequestMapping(value = { "/getConfigureOfferList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetConfigureOfferList> getConfigureOfferList(@RequestParam int offerId) {

		List<GetConfigureOfferList> frOfferList = new ArrayList<GetConfigureOfferList>();
		try {
			frOfferList = companyService.getConfigureOfferListById(offerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return frOfferList;
	}

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

	@RequestMapping(value = { "/getAllOfferFrConfiguredList" }, method = RequestMethod.GET)
	public @ResponseBody List<GetOfferFrConfiguredList> getAllOfferFrConfiguredList() {

		List<GetOfferFrConfiguredList> offerList = new ArrayList<GetOfferFrConfiguredList>();
		try {
			offerList = companyService.getOfferFrConfiguredList();
			
			System.err.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offerList;
	}

	/********************************/
	
	@RequestMapping(value = { "/saveImage" }, method = RequestMethod.POST)
	public @ResponseBody Images saveImage(@RequestBody Images image) {
		Images res = imagesService.saveImage(image);
		return res;
	}

	// Author-Anmol Shirke Created On-16-07-2020
	// Desc- Returns Info object - save multiple image.
	@RequestMapping(value = { "/saveMultipleImage" }, method = RequestMethod.POST)
	public @ResponseBody Info saveMultipleImage(@RequestBody List<Images> imageList) {
		Info info = imagesService.saveMultipleImages(imageList);
		return info;
	}

	// Author-Anmol Shirke Created On-16-07-2020
	// Desc- Returns Images list - get all images by docId and delete status=0.
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
			
			
			System.err.println("convertedRankList"+convertedRankList.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return  convertedRankList;
		
	}
	
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
	
	

	// Author-Anmol Shirke Created On-16-07-2020
	// Desc- Returns Info object - delete image by imageId - physical delete.
	@RequestMapping(value = { "/deleteByImageId" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteByImageId(@RequestParam int imageId) {
		Info res = imagesService.deletImageById(imageId);
		return res;
	}


}
