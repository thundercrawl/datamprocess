package com.bg.data.vendor;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
public class VendorConnection {
	private Integer current_init_connection_number = 5;
	private ArrayList<Connection> conns = new ArrayList<Connection>();
	private VendorDB db;
	public VendorConnection(Integer init_conection,VendorDB db)
	{
		this.db = db;
		if(current_init_connection_number < init_conection )
		{
			current_init_connection_number = init_conection;
		}
		
		for(Integer i=0;i<current_init_connection_number;i++)
		{
			initConnections();
		}
			
	}

private void initConnections()
{
	if(conns.size()<current_init_connection_number)
	{
		Connection cnn = getConnection(db);
		if(cnn == null)
			throw new RuntimeException("Failed to create connection");
		conns.add(getConnection(db));
	}
	else
		throw new RuntimeException("Max connections exceed, current:"+current_init_connection_number);
	}
private Connection getConnection(VendorDB db)
{
	 try {
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
	    return DriverManager.getConnection(db.getDBURL(), db.getDBUser(), db.getDBPassword());
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
     
	}
	
	public void ExecuteQuery()
	{
		
	}
}
