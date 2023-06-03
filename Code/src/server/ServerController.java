package server;

import java.io.IOException;
import java.net.InetAddress;

import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import gui.AbstractController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServerController{
	private ObservableList<InetAddress> connectedObserv = FXCollections.observableArrayList();
	
	private CEMSserver cemsServer;
	@FXML
	private TextField DBNameTxt;

	@FXML
	private TextField DBUsernameTxt;

	@FXML
	private Button btnConnect;

	@FXML
	private Button btnDisconnect;

	@FXML
	private Button btnExit;
	
	@FXML
	private TableView<InetAddress> clientsTable;
	
	@FXML
	private TableColumn<InetAddress, String> clientHostName,clientIp,clientStatus;

	@FXML
	private TextArea console;

	@FXML
	private PasswordField passwordTxt;

	@FXML
	private TextField portxt;

	@FXML
	private TextField serverIdTxt;
	

	@FXML
	void connect(ActionEvent event) {
		String p;
		p = getport();
		if (p.trim().isEmpty()) {
			addConsole("You must enter a port number");
		} else {
			cemsServer = new CEMSserver(Integer.valueOf(p), this);
			try {
				cemsServer.listen(); // Start listening for connections
			} catch (Exception ex) {
			}
		}
	}
	private String getport() {
		return portxt.getText();
	}
	
	@FXML
	void disconnect(ActionEvent event) {
		try {
			cemsServer.sendToAllClients(new Msg(MsgType.bye));
			cemsServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cemsServer.setConn(null);
		connectedObserv.clear(); // clear the connections list.
		btnDisconnect.setDisable(true);
		btnConnect.setDisable(false);
		addConsole("Server is close.\n");
		addConsole("Server has stopped listening for connections.\n"); 
	}

	@FXML
	void exit(ActionEvent event) {
		System.out.println("Exit CEMS Server app.");
		System.exit(0);
	}

	/**
	* Initializes the connected client table (when server app is up)
	*/
	@FXML
	protected void initialize() {
		clientHostName.setCellValueFactory(new PropertyValueFactory<InetAddress, String>("HostName"));
		clientIp.setCellValueFactory(new PropertyValueFactory<InetAddress, String>("HostAddress"));
		clientStatus.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("Connected"));
		clientsTable.setItems(connectedObserv);
	}
	
	/**
	* Adds the given IP to connectedObserv
	* @param connected The InetAddress IP to be added
	*/
	protected void addConnected(InetAddress connected) {
		connectedObserv.add(connected);
	}
	/**
	* Removes the given IP from connectedObserv
	* @param connected The InetAddress IP to be removed
	*/
	protected void removeConnected(InetAddress connected) {
		connectedObserv.remove(connected);
	}
	

	public void addConsole(String msg) {
		console.setText(console.getText() + msg);
	}
	
	public void setConnectDisable(boolean flag) {
		btnConnect.setDisable(flag);
	}
	
	public void setDisconnectDisable(boolean flag) {
		btnDisconnect.setDisable(flag);
	}
	
	public String getDBNameTxt() {
		return DBNameTxt.getText();
	}
	
	public String getDBUsernameTxt() {
		return DBUsernameTxt.getText();
	}
	
	public String getServerIdTxt() {
		return serverIdTxt.getText();
	}


	public String getPasswordTxt() {
		return passwordTxt.getText();
	}
}
