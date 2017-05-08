package PageConnexion;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PageConnexionController {
	
	// ATTRIBUTS
	Connection conn;
	@FXML private TextField name;
	
	// CONSTRUCTOR
	public PageConnexionController(){
		System.out.println("PageConnexionController constructor called");
		
		// CONNECTION TO THE DATABASE
		
		try{
		      Class.forName("com.mysql.jdbc.Driver").newInstance(); // load the driver
		      
		      String url = "jdbc:mysql://localhost/logiciel_conformite";
		      String user = "root";
		      String passwd = "";
		      
		      /*Connection*/ conn = (Connection) DriverManager.getConnection(url, user, passwd); // connection
		      
		      System.out.println("connection from database OK");		      
		}
	    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
	    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
	    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
	    catch (SQLException ex)	{System.err.println(ex.getMessage());}
	}
	
	@FXML
	private void buttonConnection(ActionEvent event){
		System.out.println("buttonConnexion clicked");
		
		// variables
		
		String databaseName = null;
		String inputName = null;
		
		// READ THE INPUT DATAS
		
		inputName = name.getText();
		System.out.println("inputName = " + inputName);
		
		
		// ASK THE DATABASE
		
		try{
			Statement state = conn.createStatement();

		    ResultSet result = state.executeQuery("SELECT * FROM employe");// L'objet ResultSet contient le r�sultat de la requ�te SQL
			    while (result.next())
			    {
			    	databaseName = result.getString("nom");
			    	System.out.println("databaseName = " + databaseName);
			    	
			    	if(databaseName.equals(inputName)){
			    		System.out.println("name saved into the database");
			    	}else{
			    		System.out.println("name not saved");
			    	}
			    	
			    }		
		}
		catch (SQLException ex) {System.err.println(ex.getMessage());}
		
		// COMPARE INPUT DATAS WITH THE DATABASE DATAS


	}

}
