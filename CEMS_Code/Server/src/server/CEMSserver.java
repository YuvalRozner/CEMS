package server;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.DB_controller;
import JDBC.DataBaseConnector;
import JDBC.Msg;
import JDBC.MsgType;
import gui.ServerController;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class CEMSserver extends AbstractServer {

	Connection conn;
	public static ServerController serverController;

	/**
	 * Constructs an instance of the echo server.
	 * 
	 * @param port The port number to connect on.
	 */
	public CEMSserver(int port, ServerController sc) {
		super(port);
		CEMSserver.serverController = sc;
	}

	InetAddress clientAddress;
	String clientHostname;

	public CEMSserver(int port) {
		super(port);
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		// Retrieve the client's IP address and hostname
		InetAddress clientAddress = client.getInetAddress();
		String clientHostname = client.getInetAddress().getHostAddress();
		try {
			serverController.addConnected(clientAddress);
		} catch (Throwable t) {
			System.out.println("Error in clientConnected");
			serverController.addConsole("Error in clientConnected.\n");
		}
		;
		// Print the client's IP address and hostname
		serverController.addConsole("Client connected from " + clientAddress.getHostAddress() + " (" + clientHostname + ").\n");
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
				queryStr = DB_controller.createSELECTquery(msg.getSelect(), msg.getFrom(), msg.getWhere(), msg.getWhereCol());
				serverController.addConsole("query: ->" + queryStr + ".\n");
				System.out.println("query: ->" + queryStr);
				rs = stmt.executeQuery(queryStr);
				MsgType type = (msg.getFrom().get(0).equals("cems.user")) ? MsgType.user : MsgType.data;
				Msg tmpMsg = createDataMsg(type, rs);
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
				sendToClient(new Msg(MsgType.succeeded), client);
				break;
			case disconnect:
				serverController.addConsole("clientDisconnected" + client + ".\n");
				System.out.println("clientDisconnected" + client);
				serverController.removeConnected(client.getInetAddress());
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
				stmt.executeQuery(queryStr);
				sendToClient(new Msg(MsgType.succeeded), client);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			/* handle any errors */}
		return;
	}

	/**
	 * @param msg
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Msg createDataMsg(MsgType type, ResultSet rs) throws SQLException {
		ArrayList<ArrayList<Object>> dataToClient = new ArrayList<>();
		int colunmCount = rs.getMetaData().getColumnCount();
		while (rs.next()) {
			ArrayList<Object> rowTemp = new ArrayList<>(colunmCount);
			for (int i = 1; i < colunmCount + 1; i++)
				rowTemp.add(rs.getObject(i));
			dataToClient.add(rowTemp);
		}
		Msg tmpMsg = new Msg(type);
		tmpMsg.setData(dataToClient);
		return tmpMsg;
	}

	/**
	 * This method overrides the one in the superclass. Called when the server starts listening for connections.
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

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private void sendToClient(Object msg, ConnectionToClient client) {
		try {
			client.sendToClient(msg);
		} catch (Exception ex) {
			serverController.addConsole("error in sending msg to client.");
			System.out.println("error in sending msg to client.");
		}
	}
}
