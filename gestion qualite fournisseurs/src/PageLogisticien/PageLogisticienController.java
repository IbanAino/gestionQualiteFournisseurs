package PageLogisticien;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PageLogisticienController {
	
	// ATTRIBUTS
	
	Connection conn;
	
	@FXML private TextField fournisseur;
	@FXML private TextField year;
	@FXML private TextField numberCommand;
	@FXML private TextField lateness;
	@FXML private TextField goodParts;
	@FXML private TextField badParts;
		
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
	
	//@SuppressWarnings("resource")
	@FXML
	private void buttonSendDatas(ActionEvent event) throws SQLException{
		System.out.println("button sendDatas clicked");
		
		// INSERT THE DATAS INTO THE DATABASE
		String fournisseur = null;
		String year = null;
		String numberCommand = null;
		String lateness = null;
		String goodParts = null;
		String badParts = null;
		
		// READ THE INPUT DATAS
		
		fournisseur = this.fournisseur.getText();
		year = this.year.getText();
		numberCommand = this.numberCommand.getText();
		lateness = this.lateness.getText();
		goodParts = this.goodParts.getText();
		badParts = this.badParts.getText();
		
		System.out.println(fournisseur + year + numberCommand + lateness + goodParts + badParts);

		// CHECK IF THERE ARE MISSING DATAS
		if(fournisseur.equals("") || year.equals("") || numberCommand.equals("") || lateness.equals("") || goodParts.equals("") || badParts.equals("")){
			System.out.println("missing datas");
		}else{
			// INSERT DATAS INTO THE DATABASE
			Statement state = conn.createStatement();
			//state.executeUpdate("INSERT INTO qualite (numeroCommande) VALUES ('c03')");
			//state.executeQuery("");
			//ResultSet result = state.executeQuery("SELECT * FROM qualite");
			//state.executeQuery("SELECT * FROM qualite");
			
			state.executeUpdate("INSERT INTO qualite (numeroCommande, fournisseur, annee, piecesConformes, piecesNonConformes, joursRetard)"
					+ "VALUES(" + numberCommand + ',' + fournisseur + ',' + year + ',' + goodParts + ',' + badParts + ',' + lateness + ')');
		
		}
		
	}
}
