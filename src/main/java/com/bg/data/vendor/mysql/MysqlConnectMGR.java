package com.bg.data.vendor.mysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.bg.data.vendor.VendorConnection;
import com.bg.data.vendor.VendorDB;

public class MysqlConnectMGR {
private static MysqlConnectMGR mgr;
private HashMap<VendorDB ,VendorConnection> db2conns = new HashMap<VendorDB ,VendorConnection> ();
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
	VendorDB v2 = new VendorDB("jdbc:mysql://localhost:3306/employees?useSSL=false","root","passw0rd");
	db2conns.put(v1, new VendorConnection(5,v1));
	db2conns.put( v2,new VendorConnection(5,v2));
	}

public void stop()
{
	for(VendorConnection cnn:db2conns.values())
	{
		cnn.stop();
	}
	}

}


