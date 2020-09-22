package com.ats.ecomapi.mst_model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GetTableNames {
	
	@Id
	private String id;
	
	
	private String tableName;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	@Override
	public String toString() {
		return "GetTableNames [id=" + id + ", tableName=" + tableName + "]";
	}
	
	
	
	
	

}
