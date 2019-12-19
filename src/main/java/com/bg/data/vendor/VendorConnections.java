package com.bg.data.vendor;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
public class VendorConnections {
	private Integer current_init_connection_number = 5;
	private ArrayList<Connection> conns = new ArrayList<Connection>();
	private VendorDB db;
	public VendorConnections(Integer init_conection,VendorDB db)
	{
		this.db = db;
		if(current_init_connection_number < init_conection )
		{
			current_init_connection_number = init_conection;
		}
		initConnections();
	}
public ArrayList<Connection> getConns()
{
	return conns;
	}
public void initConnections()
{
	for(Integer i=0;i<current_init_connection_number;i++)
	{
	if(conns.size()<current_init_connection_number)
	{
		Connection cnn = getConnection(db);
		if(cnn == null)
			throw new RuntimeException("Failed to create connection, url "+db.getDBURL());
		conns.add(getConnection(db));
	}
	else
		throw new RuntimeException("Max connections exceed, current:"+current_init_connection_number);
	}
}
private Connection getConnection(VendorDB db)
{
	 try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    return DriverManager.getConnection(db.getDBURL(), db.getDBUser(), db.getDBPassword());
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return null;
     
	}

public void stop()
{
	for(Connection c:conns)
	{
		try {
			c.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	
	@SuppressWarnings("finally")
	public ResultSet ExecuteQuery(String sql)
	{
		ResultSet rt = null;
		try {
			rt = conns.get(0).prepareStatement(sql).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			return rt;
		}
		
		
	}
}
