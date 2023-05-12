package gui;

import server.CEMSserver;
import server.CEMSserverUI;
import server.DataBaseConnector;

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
	private CEMSserver sv;
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
	
	/*
	public static void runServer(String p)
	{
		 int port = 0; //Port to listen on

	        try
	        {
	        	port = Integer.parseInt(p); //Set port to 5555
	          
	        }
	        catch(Throwable t)
	        {
	        	System.out.println("ERROR - Could not connect!");
	        }
	    	
	        //CEMSserver sv = new CEMSserver(port);
	        sv = new CEMSserver(port);
	        	
	        try 
	        {
	          sv.listen(); //Start listening for connections
	        } 
	        catch (Exception ex) 
	        {
	          System.out.println("ERROR - Could not listen for clients!");
	        }
	}
	*/
	
	
	public void Done(ActionEvent event) throws Exception {
		String p;
		
		p=getport();
		if(p.trim().isEmpty()) {
			System.out.println("You must enter a port number");
					
		}
		else
		{
			System.out.println("i am in done");
			//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			//Stage primaryStage = new Stage();
			//FXMLLoader loader = new FXMLLoader();
			//CEMSserverUI.runServer(p);
			//new CEMSserverUI().runServer(p);
			sv = new CEMSserver(5555, this);
			
			try 
	        {
				//new DataBaseConnector().connectionToDataBase(sv);
	          sv.listen(); //Start listening for connections
	          } 
	        catch (Exception ex) {}
		}
	}
	
	public void setClientAddress(InetAddress clientAddress) {
		System.out.println(clientAddress.getHostAddress());
		clientIp.setEditable(true);
		this.clientIp.setText(clientAddress.getHostAddress());
		
	}
	
	public void setClientHostName(String clientHostname) {
		System.out.println(clientHostname);
		this.clientHostName.setText(clientHostname);
	}
	
	
	public void setClientStatus(String clientStatus) {
		this.clientStatus.setText(clientStatus);
	}

	/*
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerPort.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/ServerPort.css").toExternalForm());
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		
		primaryStage.show();	
	}
	*/
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("exit Academic Tool");
		System.exit(0);			
	}

}