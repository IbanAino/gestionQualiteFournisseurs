package Statisticien;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PageStatisticienController {
	
	// ATTRIBUTS
	Connection conn;
	@FXML private TextField year;
	
	//CONSTRUCTOR
	public PageStatisticienController(){
		System.out.println("Statistician page launched.");
		
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
	
	@FXML
	private void buttonComputeStats(ActionEvent event){
		System.out.println("Button compute stats clicked.");
	}
}
