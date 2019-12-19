package com.bg.data.vendor.mysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.bg.data.vendor.VendorConnections;
import com.bg.data.vendor.VendorDB;

public class MysqlConnectMGR {
private static MysqlConnectMGR mgr;
private HashMap<VendorDB ,VendorConnections> db2conns = new HashMap<VendorDB ,VendorConnections> ();
public static MysqlConnectMGR getInstance()
{
	if(mgr == null)
	{
		mgr= new MysqlConnectMGR();
	}
	return mgr;
}
private MysqlConnectMGR()
{
	//part can be set from config files, such as spring's application.properties
	VendorDB v1 = new VendorDB("jdbc:mysql://localhost:3306/employees?useSSL=false","root","passw0rd");
	VendorDB v2 = new VendorDB("jdbc:mysql://172.16.106.113:3306/employees?useSSL=false","dbuser","hcc123hcc");
	db2conns.put(v1, new VendorConnections(5,v1));
	db2conns.put( v2,new VendorConnections(5,v2));
	}

public void stop()
{
	for(VendorConnections cnn:db2conns.values())
	{
		cnn.stop();
	}
}


public void querySelect(String sql)
{
	for(VendorDB db:db2conns.keySet())
	{
		ResultSet rt = db2conns.get(db).ExecuteQuery(sql);
		try {
			if(rt!=null)
				rt.last();
			System.out.println("Get query result from db "+db.getDBURL()+" data size:"+rt.getRow());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}

}


