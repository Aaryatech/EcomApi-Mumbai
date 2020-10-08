package com.ats.ecomapi.master.model;

import com.ats.ecomapi.mst_model.FrEmpMaster;
import com.ats.ecomapi.mst_model.LoginInfo;

public class FrEmpLoginResp {
	 	private LoginInfo loginInfo;
	    private FrEmpMaster frEmp;
	    private Franchise franchisee;
	    
		public LoginInfo getLoginInfo() {
			return loginInfo;
		}
		public void setLoginInfo(LoginInfo loginInfo) {
			this.loginInfo = loginInfo;
		}
		
		public FrEmpMaster getFrEmp() {
			return frEmp;
		}
		public void setFrEmp(FrEmpMaster frEmp) {
			this.frEmp = frEmp;
		}
		public Franchise getFranchisee() {
			return franchisee;
		}
		public void setFranchisee(Franchise franchisee) {
			this.franchisee = franchisee;
		}
		@Override
		public String toString() {
			return "FrEmpLoginResp [loginInfo=" + loginInfo + ", frEmp=" + frEmp + ", franchisee=" + franchisee + "]";
		}
		
}
