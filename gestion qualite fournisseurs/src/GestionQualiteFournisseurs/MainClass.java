package GestionQualiteFournisseurs;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;

public class MainClass extends Application{

	public static void main(String[] args) {
		System.out.println("Software launched");
		
		/*
		// CONNECTION TO THE DATABASE
		
		try{
		      Class.forName("com.mysql.jdbc.Driver").newInstance(); // load the driver
		      
		      String url = "jdbc:mysql://localhost/logiciel_conformite";
		      String user = "root";
		      String passwd = "";
		      
		      Connection conn = (Connection) DriverManager.getConnection(url, user, passwd); // connection
		      
		      System.out.println("connection from database OK");		      
		}
	    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
	    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
	    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
	    catch (SQLException ex)			  {System.err.println(ex.getMessage());}
		*/
		
		launch(args); // call the start function
	}
	
	
	// LAUNCH THE FIRST WINDOW
	
	public void start(Stage primaryStage) {
		// Charger le fichier XML, en capturant une erreur si �a ne marche pas.
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/PageConnexion/PageConnexion.fxml"));
			Scene scene = new Scene(root, 800, 600, Color.BLACK);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
			
			System.out.println("first window SUCCESS");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("first window ERROR");
		}
	}
}
