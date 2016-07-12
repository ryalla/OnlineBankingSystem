package com.app.bankDao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class GetConnection {
	
	Properties properties = new Properties();
	Connection connection=null;
//	
//	properties.

	
	public Connection getConnection(){
	try {
	//	properties.load(Boot.class.getClassLoader().getResourceAsStream("config.properties"));
		// properties.load(Resources.getResource("config.properties");
		 InputStream in = getClass().getResourceAsStream("config.properties");
		 properties.load(in);
		 in.close();
		 System.out.println("");
		String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String schema = properties.getProperty("schema");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
 
	//	Class.forName("com.mysql.jdbc.Driver");
	//	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/elanbanking","root","");
		
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
         String url = "jdbc:mysql://" + host + ":" + port + "/"
              + schema;
         
         System.out.println("URL"+url);

         
         connection= DriverManager.getConnection(url, user, password);

		return connection;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return null;
	}

}