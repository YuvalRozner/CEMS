package gui;

import java.io.IOException;
import java.net.InetAddress;

import JDBC.Msg;
import JDBC.MsgType;
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
import server.CEMSserver;

public class ServerController {
	private ObservableList<InetAddress> connectedObserv = FXCollections.observableArrayList();

	private CEMSserver cemsServer;
	@FXML
	private TextField DBNameTxt;

	@FXML
	private TextField DBUsernameTxt;

	@FXML
	private Button btnConnect;

	@FXML
	public Button btnDisconnect;

	@FXML
	private TableView<InetAddress> clientsTable;

	@FXML
	private TableColumn<InetAddress, String> clientHostName, clientIp, clientStatus;

	@FXML
	private TextArea console;

	@FXML
	private PasswordField passwordTxt;

	@FXML
	private TextField portxt;

	@FXML
	private TextField serverIdTxt;

	@FXML
	public void connect(ActionEvent event) {
		String p;
		p = getport();
		if (p.trim().isEmpty())
			addConsole("You must enter a port number.\n");
		else {
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
	public void disconnect(ActionEvent event) {
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
		CEMSserver.serverController.addConsole("\n________________________________________________________  clear  _______________________________________________\n\n");
	}

	@FXML
	public void exit(ActionEvent event) {
		if(!btnDisconnect.isDisable()) disconnect(null);
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
	 * 
	 * @param connected The InetAddress IP to be added
	 */
	public void addConnected(InetAddress connected) {
		connectedObserv.add(connected);
	}

	/**
	 * Removes the given IP from connectedObserv
	 * 
	 * @param connected The InetAddress IP to be removed
	 */
	public void removeConnected(InetAddress connected) {
		connectedObserv.remove(connected);
	}

	public void addConsole(String msg) {
		// console.setText(console.getText() + msg); //old line, show dor and then delete.
		console.appendText(msg); // append without read the prev text & scroll down automatically.
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
