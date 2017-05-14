package Statisticien;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	private void buttonComputeStats(ActionEvent event) throws SQLException{
		System.out.println("Button start compute clicked.");
		
		// READ THE YEAR TO MAKE THE STATISTICS
		String year = this.year.getText();
		
		// FIND ALL THE DATAS ABOUT THE YEAR INTO THE DATABASE
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("SELECT fournisseur FROM qualite");
				
		ArrayList<String> fournisseursList = new ArrayList<String>();
		
		while (result.next()){
			
			String fournisseur = result.getString("fournisseur");
			
			boolean saveTheFournisseur = true;
			
			for(int i = 0; i < fournisseursList.size(); i++){
				if(fournisseursList.get(i).equals(fournisseur)){
					saveTheFournisseur = false;
				}
			}
			if(saveTheFournisseur == true){
				fournisseursList.add(fournisseur);
			}
			//System.out.println(fournisseur);	
		}
		 // printn the fournisseurs
		for(int i = 0; i < fournisseursList.size(); i++){
			System.out.println(fournisseursList.get(i));
		}
	}
}
