package com.bg.data.vendor;

public class VendorDB {
	private String db_url;
	private String db_user;
	private String db_password;
	public VendorDB(String db_url, String db_user, String db_password)
	{
		this.db_url = db_url;
		this.db_user = db_user;
		this.db_password = db_password;
	}
	
	public String getDBURL()
	{
		return db_url;
	}
	public String getDBUser()
	{
		return db_user;
	}
	
	public String getDBPassword()
	{
		return db_password;
	}
}
