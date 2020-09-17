package com.ats.ecomapi.offer_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mn_images")
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "images_id")
	private int imagesId;

	@Column(name = "doc_type")
	private int docType;

	@Column(name = "doc_id")
	private int docId;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "seq_no")
	private float seqNo;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "del_status")
	private int delStatus;

	@Column(name = "ex_int1")
	private int exInt1;

	@Column(name = "ex_int2")
	private int exInt2;

	@Column(name = "ex_int3")
	private int exInt3;

	@Column(name = "ex_var1")
	private String exVar1;

	@Column(name = "ex_var2")
	private String exVar2;

	@Column(name = "ex_var3")
	private String exVar3;

	@Column(name = "ex_var4")
	private String exVar4;

	@Column(name = "ex_Float1")
	private String exFloat1;

	@Column(name = "ex_Float2")
	private String exFloat2;

	@Column(name = "ex_Float3")
	private String exFloat3;

	public int getImagesId() {
		return imagesId;
	}

	public void setImagesId(int imagesId) {
		this.imagesId = imagesId;
	}

	public int getDocType() {
		return docType;
	}

	public void setDocType(int docType) {
		this.docType = docType;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public float getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(float seqNo) {
		this.seqNo = seqNo;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	public String getExVar4() {
		return exVar4;
	}

	public void setExVar4(String exVar4) {
		this.exVar4 = exVar4;
	}

	public String getExFloat1() {
		return exFloat1;
	}

	public void setExFloat1(String exFloat1) {
		this.exFloat1 = exFloat1;
	}

	public String getExFloat2() {
		return exFloat2;
	}

	public void setExFloat2(String exFloat2) {
		this.exFloat2 = exFloat2;
	}

	public String getExFloat3() {
		return exFloat3;
	}

	public void setExFloat3(String exFloat3) {
		this.exFloat3 = exFloat3;
	}

	@Override
	public String toString() {
		return "Images [imagesId=" + imagesId + ", docType=" + docType + ", docId=" + docId + ", imageName=" + imageName
				+ ", seqNo=" + seqNo + ", isActive=" + isActive + ", delStatus=" + delStatus + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + ", exVar4=" + exVar4 + ", exFloat1=" + exFloat1 + ", exFloat2=" + exFloat2
				+ ", exFloat3=" + exFloat3 + "]";
	}

}
