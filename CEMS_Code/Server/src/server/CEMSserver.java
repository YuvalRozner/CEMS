package server;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.DB_controller;
import JDBC.DataBaseConnector;
import JDBC.Msg;
import JDBC.MsgType;
import controllers.CemsFileController;
import gui.ServerController;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * The CEMSserver class is responsible for handling communication between the server and the clients.
 */
public class CEMSserver extends AbstractServer {

	Connection conn;
	public static ServerController serverController;

	private CemsFileController cemsFileController = new CemsFileController();

	/**
	 * Constructs an instance of the CEMSserver.
	 *
	 * @param port The port number to connect on.
	 * @param sc   The server controller.
	 */
	public CEMSserver(int port, ServerController sc) {
		super(port);
		CEMSserver.serverController = sc;
	}

	InetAddress clientAddress;
	String clientHostname;

	/**
	 * Constructs a CEMSserver object with the specified port.
	 *
	 * @param port The port number on which the server will listen.
	 */
	public CEMSserver(int port) {
		super(port);
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		// Retrieve the client's IP address and hostname
		InetAddress clientAddress = client.getInetAddress();
		String clientHostname = client.getInetAddress().getHostAddress();
		try {
			//serverController.addConnected(clientAddress);
			serverController.addConnected(client);
		} catch (Throwable t) {
			System.out.println("Error in clientConnected");
			serverController.addConsole("Error in clientConnected.\n");
		}
		;
		// Print the client's IP address and hostname
		serverController
				.addConsole("Client connected from " + clientAddress.getHostAddress() + " (" + clientHostname + ").\n");
		System.out.println("Client connected from " + clientAddress.getHostAddress() + " (" + clientHostname + ")");
	}

	/**
	 * This method handles any messages received from the client.
	 * 
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msgObj, ConnectionToClient client) {
		Msg msg = (Msg) msgObj;
		java.sql.Statement stmt = null;
		String queryStr;
		ResultSet rs;
		serverController.addConsole("Message received: " + msg + " from " + client + ".\n");
		System.out.println("Message received: " + msg + " from " + client);
		try {
			switch (msg.getType()) {
			case select:
				stmt = conn.createStatement();
				queryStr = DB_controller.createSELECTquery(msg.getSelect(), msg.getFrom(), msg.getWhere(),
						msg.getWhereCol());
				serverController.addConsole("query: ->" + queryStr + ".\n");
				System.out.println("query: ->" + queryStr);
				rs = stmt.executeQuery(queryStr);
				MsgType type = (msg.getFrom().get(0).equals("user")) ? MsgType.user : MsgType.data;
				Msg tmpMsg = DB_controller.createDataMsg(type, rs);
				if (tmpMsg.getData() == null || tmpMsg.getData().isEmpty())
					tmpMsg = new Msg(MsgType.empty);
				sendToClient(tmpMsg, client);
				break;
			case update:
				stmt = conn.createStatement();
				queryStr = DB_controller.createUPDATEquery(msg.getTableToUpdate(), msg.getSet(), msg.getWhere());
				serverController.addConsole("query: ->" + queryStr + ".\n");
				System.out.println("query: ->" + queryStr);
				stmt.executeUpdate(queryStr);
				//sendToClient(new Msg(MsgType.succeeded), client);
				sendToClient(new Msg(MsgType.succeeded), client);
				break;
			case disconnect:
				serverController.addConsole("clientDisconnected" + client + ".\n");
				System.out.println("clientDisconnected" + client);
				//serverController.removeConnected(client.getInetAddress());
				serverController.removeConnected(client);
				sendToClient(new Msg(MsgType.bye), client);
				break;
			case manyMessages:
				for (Msg act : msg.getMsgLst())
					handleMessageFromClient(act, client);
				sendToClient(new Msg(MsgType.succeededAll), client);
				break;
			case insert:
				stmt = conn.createStatement();
				queryStr = DB_controller.createINSERTquery(msg.getTableToUpdate(), msg.getColNames(), msg.getValues());					
				serverController.addConsole("query: ->" + queryStr + ".\n");
				System.out.println("query: ->" + queryStr);
				try{stmt.executeUpdate(queryStr);
				}catch(SQLException e) {System.out.println("insert faild"); sendToClient(new Msg(MsgType.insertFail), client); break;}
				sendToClient(new Msg(MsgType.insertSucceeded), client);
				break;
			case lockTest:
				serverController.addConsole("client "+client+ " asked to lock test with test code " + msg.getTestCode()+".\n");
				System.out.println("client "+client+ " asked to lock test with test code " + msg.getTestCode()+".");
				sendToAllClients(msg);
				break;
			case file:
				msg.setPathFile("@../../file/");
				cemsFileController.saveFile(msg);
				sendToClient(new Msg(MsgType.succeeded), client);
				break;
			case fileToSend:
				//
				String LocalfilePath = "@../../file/algebraTest.docx";
				//String LocalfilePath = "C:\\Users\\liorz\\OneDrive\\Documents\\GitHub\\CEMS\\CEMS_Code\\Server\\file\\algebraTest.docx";
				Msg msgToCleint = cemsFileController.createMsgWithFile(LocalfilePath);
				sendToClient(msgToCleint, client);
				break;
			case updatePlusOne:
				stmt = conn.createStatement();
				queryStr = DB_controller.createUPDATEPlusOnequery(msg.getTableToUpdate(), msg.getSet(), msg.getWhere());
				serverController.addConsole("query: ->" + queryStr + ".\n");
				System.out.println("query: ->" + queryStr);
				stmt.executeUpdate(queryStr);
				sendToClient(new Msg(MsgType.succeeded), client);
			default:
				break;
			}
		} catch (SQLException ex) {
			/* handle any errors */}
		return;
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	public void serverStarted() {
		serverController.addConsole("Server listening for connections on port " + getPort() + ".\n");
		System.out.println("Server listening for connections on port " + getPort());
		if (new DataBaseConnector().connectionToDataBase(this)) {
			serverController.addConsole("SQL connection succeed.\n");
			System.out.println("SQL connection succeed");
			serverController.setConnectDisable(true);
			serverController.setDisconnectDisable(false);
		}

		else {
			serverController.addConsole("SQL connection fail");
			System.out.println("SQL connection fail");
			try {
				this.close();
				serverController.addConsole("Server closed.");
				System.out.println("Server closed.");
			} catch (IOException e) {
				serverController.addConsole("Cant close server.");
				System.out.println("Cant close server.");
			}
		}
	}

    /**
     * Retrieves the connection object.
     *
     * @return The connection object.
     */
	public Connection getConn() {
		return conn;
	}

    /**
     * Sets the connection object.
     *
     * @param conn The connection object to set.
     */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

    /**
     * Sends a message to a client.
     *
     * @param msg    The message to send.
     * @param client The client connection object.
     */
	private void sendToClient(Object msg, ConnectionToClient client) {
		try {
			client.sendToClient(msg);
		} catch (Exception ex) {
			serverController.addConsole("error in sending msg to client.");
			System.out.println("error in sending msg to client.");
		}
	}
}
