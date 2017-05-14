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
import javafx.scene.text.Text;

public class PageStatisticienController {
	
	// ATTRIBUTS
	Connection conn;
	@FXML private TextField year;
	@FXML private Text computedDatas;
	
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
		
		// variables
		ArrayList<String> fournisseursList = new ArrayList<String>();
		ArrayList<Integer> latenessList = new ArrayList<Integer>();
		ArrayList<Float> ratioNCList = new ArrayList<Float>();
		
		// READ THE YEAR TO MAKE THE STATISTICS
		String year = this.year.getText();
		
		// FIND ALL THE DATAS ABOUT THE YEAR INTO THE DATABASE
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("SELECT fournisseur FROM qualite");
		
		// MAKE A LIST OF FOURNISSEURS
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
		 // println the fournisseurs
		for(int i = 0; i < fournisseursList.size(); i++){
			System.out.println(fournisseursList.get(i));
		}
		
		// list of fournisseurs done
		
		// COMPUTE LATENESS
		for(int i = 0; i < fournisseursList.size(); i++){
			int latenessCounter = 0;
			result = state.executeQuery("SELECT joursRetard FROM qualite WHERE fournisseur = '" + fournisseursList.get(i) + "'");
			int rowCounter = 0;
			while (result.next()){
				latenessCounter += result.getInt("joursRetard");
				rowCounter++;
			}
			latenessList.add(latenessCounter/rowCounter);
			System.out.println(fournisseursList.get(i) + " cumul of lateness : " + latenessCounter);
			System.out.println(fournisseursList.get(i) + " moyenne of lateness : " + latenessList.get(i));
		}
		
		// COMPUTE NON CONFORMS PARTS RATIO
		for(int i = 0; i < fournisseursList.size(); i++){
			//variables
			float goodParts = 0;
			float badParts = 0;
			//check the number of good parts
			result = state.executeQuery("SELECT piecesConformes FROM qualite WHERE fournisseur = '" + fournisseursList.get(i) + "'");
			while (result.next()){
				goodParts += result.getInt("piecesConformes");
			}
			//check the number of bad parts
			result = state.executeQuery("SELECT piecesNonConformes FROM qualite WHERE fournisseur = '" + fournisseursList.get(i) + "'");
			while (result.next()){
				badParts += result.getInt("piecesNonConformes");
			}
			//compute the ratio
			ratioNCList.add((badParts / (goodParts + badParts)) * 100);
			System.out.println(fournisseursList.get(i) + " NC ratio : " + ratioNCList.get(i));
		}
		
		// PRINT THE DATAS INTO THE INTERFACE
		String datasToPrint = "";
		
		for(int i = 0; i < fournisseursList.size(); i++){
			datasToPrint += fournisseursList.get(i) + " : moyenne de jours de retard : " + latenessList.get(i) + " taux de pi�ces non conformes : " + ratioNCList.get(i) + "\n";
		}
		
		computedDatas.setText(datasToPrint);
	}
}
