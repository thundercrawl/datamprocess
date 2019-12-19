package com.bg.data.vendor;

public class VendorSession {
	private VendorDB[] dbs;
	public VendorSession(VendorDB[] dbs)
	{
		this.dbs = dbs;
	}
	/*
	 * Bind to db by bussiness requirement, such as DATETIME from 1970-01-01 - 1970-01-30 to 1970.1.database
	 */
	public VendorDB bindDB(String bindCondition)
	{
		if(bindCondition.contains("date"))
		{
			return dbs[0];
		}
		else
			throw new RuntimeException("Can not bind to any database, condition is "+bindCondition);
	}
}
