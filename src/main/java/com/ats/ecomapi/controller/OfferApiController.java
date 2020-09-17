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
import com.ats.ecomapi.offer.repo.OfferDetailRepo;
import com.ats.ecomapi.offer.repo.OfferHeaderRepo;
import com.ats.ecomapi.offer_model.Images;
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

		List<OfferHeader> res = offerHeaderRepo.findByCompIdAndDelStatusAndIsActive(compId, 0, 0);

		if (res == null) {
			res = new ArrayList<>();
		}

		return res;
	}

	// Author-Anmol Shirke Created On-22-07-2020
	@RequestMapping(value = { "/getOfferDetailListByOfferId" }, method = RequestMethod.POST)
	public @ResponseBody List<OfferDetail> getOfferDetailListByOfferId(@RequestParam("offerId") int offerId) {

		List<OfferDetail> res = offerDetailRepo.findAllByOfferIdAndIsActiveAndDelStatus(offerId, 0, 0);

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
	public @ResponseBody List<Images> getImagesByDocId(@RequestParam int docId, int docType) {
		List<Images> res = imagesService.getImageListByDocIdAndDocType(docId, docType);
		return res;
	}

	// Author-Anmol Shirke Created On-16-07-2020
	// Desc- Returns Info object - delete image by imageId - physical delete.
	@RequestMapping(value = { "/deleteByImageId" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteByImageId(@RequestParam int imageId) {
		Info res = imagesService.deletImageById(imageId);
		return res;
	}


}
