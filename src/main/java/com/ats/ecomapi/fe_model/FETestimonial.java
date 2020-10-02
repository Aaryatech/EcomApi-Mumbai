package com.ats.ecomapi.fe_model;

import javax.persistence.Entity;
import javax.persistence.Id;

//Author -Sachin
//Created on -02-10-2020
//Desc - to show Testimonials to Company and  Franchise data to front end pages.
@Entity
public class FETestimonial {

	@Id
	private String uuid;
	
	private int testimonialsId;
	private String captionName;
	private String images;
	private String messages;
	private String name;
	private String frIds;
	private String designation;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getTestimonialsId() {
		return testimonialsId;
	}
	public void setTestimonialsId(int testimonialsId) {
		this.testimonialsId = testimonialsId;
	}
	public String getCaptionName() {
		return captionName;
	}
	public void setCaptionName(String captionName) {
		this.captionName = captionName;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrIds() {
		return frIds;
	}
	public void setFrIds(String frIds) {
		this.frIds = frIds;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "FETestimonial [uuid=" + uuid + ", testimonialsId=" + testimonialsId + ", captionName=" + captionName
				+ ", images=" + images + ", messages=" + messages + ", name=" + name + ", frIds=" + frIds
				+ ", designation=" + designation + "]";
	}
	
	
}
