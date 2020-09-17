package com.ats.ecomapi.master.repo;

import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.offer_model.Images;

 
@Service
public class ImagesServiceImpl implements ImagesService {

	@Autowired
	ImagesRepo imagesRepo;

	@Override
	public Images saveImage(Images images) {

		Images res = imagesRepo.save(images);
		return res;
	}

	@Override
	public Info saveMultipleImages(List<Images> imageList) {

		Info info = new Info();

		List<Images> res = imagesRepo.saveAll(imageList);

		if (res == null) {
			info.setError(true);
			info.setMessage("Unable to save images!");
		} else {
			info.setError(false);
			info.setMessage("Images saved successfully");
		}

		return info;
	}

	@Override
	public List<Images> getImageListByDocIdAndDocType(int id,int type) {

		List<Images> res = imagesRepo.findByDocIdAndDocTypeAndDelStatus(id,type, 0);

		if (res == null) {
			res = new ArrayList<>();
		}

		return res;
	}

	@Override
	public Info deletImageById(int imageId) {
		Info info = new Info();
		int res = imagesRepo.deleteByImageId(imageId);
		if (res > 0) {
			info.setError(false);
			info.setMessage("Image deleted successfully");
		} else {
			info.setError(true);
			info.setMessage("Unable to delete image!");
		}

		return info;
	}

}
