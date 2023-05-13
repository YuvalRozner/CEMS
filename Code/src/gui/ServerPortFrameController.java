package gui;

import java.net.InetAddress;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.CEMSserver;

public class ServerPortFrameController  {
	
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
	
	public void Done(ActionEvent event) throws Exception {
		String p;
		p = getport();
		if(p.trim().isEmpty()) {
			System.out.println("You must enter a port number");
		}
		else
		{
			sv = new CEMSserver(Integer.valueOf(p), this);
			btnDone.setDisable(true);
			try 
	        {
	          sv.listen(); //Start listening for connections
	          } 
	        catch (Exception ex) {}
		}
	}
	
	public void setClientAddress(InetAddress clientAddress) {
		this.clientIp.setText(clientAddress.getHostAddress());
	}
	
	public void setClientHostName(String clientHostname) {
		this.clientHostName.setText(clientHostname);
	}
	
	public void setClientStatus(String clientStatus) {
		this.clientStatus.setText(clientStatus);
	}

	
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("Exit Academic Tool");
		System.exit(0);			
	}

}