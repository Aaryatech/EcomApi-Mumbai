package com.ats.ecomapi.deliveryboy_model;

import java.util.List;

public class HeadObject {
private List<OrHeader> OrHeader;

private List<DelBoyOrderGriev> delBoyOrderGrievList;





public List<DelBoyOrderGriev> getDelBoyOrderGrievList() {
	return delBoyOrderGrievList;
}

public void setDelBoyOrderGrievList(List<DelBoyOrderGriev> delBoyOrderGrievList) {
	this.delBoyOrderGrievList = delBoyOrderGrievList;
}

public List<OrHeader> getOrHeader() {
	return OrHeader;
}

public void setOrHeader(List<OrHeader> orHeader) {
	OrHeader = orHeader;
}
}
