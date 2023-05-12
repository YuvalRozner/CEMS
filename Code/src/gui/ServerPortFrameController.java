package gui;

import server.CEMSserverUI;

import java.net.InetAddress;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerPortFrameController  {
	private StudentFormController sfc;	
	
	String temp="";
	
    @FXML
    private TextField clientHostName;

    @FXML
    private TextField clientIp;

    @FXML
    private TextField clientStatus;
    
	@FXML
	private Button btnExit = null;
	@FXML
	private Button btnDone = null;
	@FXML
	private Label lbllist;
	
	@FXML
	private TextField portxt;
	ObservableList<String> list;
	
	private String getport() {
		return portxt.getText();			
	}
	
	public void Done(ActionEvent event) throws Exception {
		String p;
		
		p=getport();
		if(p.trim().isEmpty()) {
			System.out.println("You must enter a port number");
					
		}
		else
		{
			//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			//CEMSserverUI.runServer(p);
			new CEMSserverUI().runServer(p);
		}
	}
	
	public void setClientAddress(InetAddress clientAddress) {
		System.out.println(clientAddress.getHostAddress());
		clientIp.setEditable(true);
		clientIp.setText(clientAddress.getHostAddress());
		
	}
	
	public void setClientHostName(String clientHostname) {
		System.out.println(clientHostname);
		clientHostName.setText(clientHostname);
	}

	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerPort.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/ServerPort.css").toExternalForm());
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}
	
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("exit Academic Tool");
		System.exit(0);			
	}

}