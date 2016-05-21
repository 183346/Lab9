package it.polito.tdp.porto.controller;



import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.bean.Creator;
import it.polito.tdp.porto.dao.PortoDAO;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	
	Model model = new Model();
	List<Creator> creators = new LinkedList<Creator>();
	
	

    public void setModel(Model model) {
		PortoDAO dao = new PortoDAO();
		creators=dao.getAllCreators();
		this.comboPrimo.getItems().addAll(creators);
		this.comboSecondo.getItems().addAll(creators);
	   	this.model = model;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboSecondo"
    private ComboBox<Creator> comboSecondo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCoautori"
    private Button btnCoautori; // Value injected by FXMLLoader

    @FXML // fx:id="comboPrimo"
    private ComboBox<Creator> comboPrimo; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnCluster"
    private Button btnCluster; // Value injected by FXMLLoader

    @FXML // fx:id="btnArticoli"
    private Button btnArticoli; // Value injected by FXMLLoader

    @FXML
    void doCoautori(ActionEvent event) {
    	this.txtResult.clear();
    	if(this.comboPrimo.getValue()!=null){
    		String result=model.cercaCoautori(this.comboPrimo.getValue());
    		if(result.compareTo("")==0){this.txtResult.appendText("nessun risultato");return;}
    		this.txtResult.appendText(result);
    	    }else{this.txtResult.appendText("selezionare il primo autore");return;}
    }

    @FXML
    void doCluster(ActionEvent event) {
    	this.txtResult.clear();
      		String result=model.cercaCluster();
    		if(result.compareTo("")==0){this.txtResult.appendText("nessun risultato");return;}
    		this.txtResult.appendText(result);
    	    
    }

   

    @FXML
    void doArticoli(ActionEvent event) {
    	this.txtResult.clear();
    	if(this.comboPrimo.getValue()!=null && this.comboSecondo.getValue()!=null && this.comboPrimo.getValue()!=this.comboSecondo.getValue()){
  		String result=model.cercaArticoli(this.comboPrimo.getValue(),this.comboSecondo.getValue());
		if(result.compareTo("")==0){this.txtResult.appendText("nessun risultato");return;}
		this.txtResult.appendText(result);
    	}else{this.txtResult.appendText("introdurre due autori diversi non coautori");return;}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboSecondo != null : "fx:id=\"comboSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCoautori != null : "fx:id=\"btnCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert comboPrimo != null : "fx:id=\"comboPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCluster != null : "fx:id=\"btnCluster\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnArticoli != null : "fx:id=\"btnArticoli\" was not injected: check your FXML file 'Porto.fxml'.";

    }
}

