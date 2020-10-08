package com.ats.ecomapi.mst_model;

import com.ats.ecomapi.master.model.Franchise;

public class FrLoginResponse {
	LoginInfo loginInfo;
	Franchise franchisee;
    
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	public Franchise getFranchisee() {
		return franchisee;
	}
	public void setFranchisee(Franchise franchisee) {
		this.franchisee = franchisee;
	}
    
	

}
