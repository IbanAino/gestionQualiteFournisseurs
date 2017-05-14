package PageConnexion;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PageConnexionController {
	
	// ATTRIBUTS
	Connection conn;
	@FXML private TextField name;
	@FXML private PasswordField passWord;
	
	// CONSTRUCTOR
	public PageConnexionController(){
		System.out.println("PageConnexionController constructor called");
		
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
	
	@SuppressWarnings("resource")
	@FXML
	private void buttonConnection(ActionEvent event){
		System.out.println("buttonConnexion clicked");
		
		// variables		
		String databaseName = null;
		String inputName = null;
		String databasePassWord = null;
		String inputPassWord = null;
		
		// READ THE INPUT DATAS
				inputName = name.getText();
		System.out.println("inputName = " + inputName);
		
		
		// ASK THE DATABASE
				try{
			Statement state = conn.createStatement();

		    ResultSet result = state.executeQuery("SELECT nom FROM employe");// L'objet ResultSet contient le r�sultat de la requ�te SQL
			    while (result.next())
			    {
			    	// COMPARE INPUT DATAS WITH THE DATABASE DATAS
			    	
			    	databaseName = result.getString("nom");
			    	System.out.println("databaseName = " + databaseName);			    	
								    	
			    	if(databaseName.equals(inputName)){
			    		System.out.println("name saved into the database");
			    		
			    		// CHECK THE PASSWORD
			    		
			    		// password from the database
			    		result = state.executeQuery("SELECT motDePasse FROM employe WHERE nom = '" + databaseName + "'");
			    		result.next();
			    		databasePassWord = result.getString("motDePasse");
			    		System.out.println("databasePassWord = " + databasePassWord);
			    		
			    		// password from the user
			    		inputPassWord = passWord.getText();
			    		System.out.println("inputPassWord = " + inputPassWord);
			    		
			    		// compare the two passwords
			    		if(databasePassWord.equals(inputPassWord)){
			    			System.out.println("Password correct");
			    			
			    			// LAUNCH THE NEXT WINDOWS
			    			try {
			    				Parent root = FXMLLoader.load(getClass().getResource("/PageLogisticien/logisticien.fxml"));
			    				Scene scene = new Scene(root, 800, 600, Color.BLACK);
			    				Stage primaryStage = new Stage();
			    				primaryStage.setScene(scene);
			    				primaryStage.sizeToScene();
			    				primaryStage.show();
			    				
			    				
			    				
			    			    result.close();
			    			    state.close();
			    			    
			    			    System.out.println("database closed");
			    				System.out.println("second window SUCCESS");
			    				
			    			} catch (IOException e) {
			    				// TODO Auto-generated catch block
			    				e.printStackTrace();
			    				
			    				System.out.println("second window ERROR");
			    			}
			    			
			    		}else{
			    			System.out.println("Wrong password");
			    		}
			    	
			    		break;// exit the while loop
			    	}else{
			    		System.out.println("name not saved");
			    	}
			    	
			    }		
		}
		catch (SQLException ex) {System.err.println(ex.getMessage());}
	}
}




