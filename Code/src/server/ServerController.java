package server;

import java.io.IOException;
import java.net.InetAddress;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServerController {
	private ObservableList<InetAddress> connectedObserv = FXCollections.observableArrayList();
	private CEMSserver sv;
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
	private Button minimizeBtn;

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
			System.out.println("You must enter a port number");
		} else {
			sv = new CEMSserver(Integer.valueOf(p), this);
			CEMSserver.DBPassword = passwordTxt.getText();
			try {
				sv.listen(); // Start listening for connections
				btnConnect.setDisable(true);
				btnDisconnect.setDisable(false);
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
			sv.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sv.setConn(null);
		btnDisconnect.setDisable(true);
		btnConnect.setDisable(false);
		addConsole("Server is close.\n");
	
		
	}

	@FXML
	void exit(ActionEvent event) {
		System.out.println("Exit CEMS Server app.");
		System.exit(0);
	}

	@FXML
	void minimize(ActionEvent event) {

		// primaryStage.setIconified(true)
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

	
}
