package com.ats.ecomapi.master.repo;

import java.util.List;


import org.springframework.stereotype.Service;

import com.ats.ecomapi.mst_model.Info;
import com.ats.ecomapi.offer_model.Images;
 

@Service
public interface ImagesService {

	Images saveImage(Images images);
	
	Info saveMultipleImages(List<Images> imageList);
	
	List<Images> getImageListByDocIdAndDocType(int id,int type);
	
	Info deletImageById(int imageId);
	
	
	
	
}
