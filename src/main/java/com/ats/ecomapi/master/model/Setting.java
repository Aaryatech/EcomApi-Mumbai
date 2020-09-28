package com.ats.ecomapi.master.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_setting")
public class Setting {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int settingId ;
 	private String settingKey 	;
 	private String settingValue;
	public int getSettingId() {
		return settingId;
	}
	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}
	public String getSettingKey() {
		return settingKey;
	}
	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
	}
	public String getSettingValue() {
		return settingValue;
	}
	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}
	@Override
	public String toString() {
		return "Setting [settingId=" + settingId + ", settingKey=" + settingKey + ", settingValue=" + settingValue
				+ "]";
	}
 	
 	
 	

}
