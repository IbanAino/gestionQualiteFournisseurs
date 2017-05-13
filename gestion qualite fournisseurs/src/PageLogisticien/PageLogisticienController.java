package PageLogisticien;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class PageLogisticienController {
	
	// ATTRIBUTS
	
	Connection conn;
	
	// CONSTRUCTOR
	
	public PageLogisticienController(){
		System.out.println("Logisticien page launched.");
		
		// CONNECTION TO THE DATABASE
		
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance(); // load the driver
		      
		    String url = "jdbc:mysql://localhost/logiciel_conformite";
		    String user = "root";
		    String passwd = "";
		      
		    conn = (Connection) DriverManager.getConnection(url, user, passwd); // connection
		      
		    System.out.println("connection from database OK");		      
			}
		catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
		catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
		catch (InstantiationException ex) {System.err.println(ex.getMessage());}
		catch (SQLException ex)	{System.err.println(ex.getMessage());}		
	}
}
