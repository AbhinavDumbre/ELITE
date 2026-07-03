package ipl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class IPLJDBCFactory {
	public static Connection getConnection() throws SQLException{
	 	Properties prop=new Properties();
	 	try {
	 	prop.load(new FileReader("src/mysql.info"));
	 	String url=prop.getProperty("url");
	 	Class.forName(prop.getProperty("driver"));
	 	
//	 	String url = "jdbc:mysql://localhost:3306/elite";// write in format and at end add db
//		String user="root";
//		String  pass="root";
//		DriverManager.registerDriver(new Driver());
		Connection conn = DriverManager.getConnection(url, prop.getProperty("user"),prop.getProperty("pass"));//first is user name
		return conn;
 }
 catch(ClassNotFoundException | IOException e){
	 throw new SQLException(e.getMessage());
	 
 }
 
}
}