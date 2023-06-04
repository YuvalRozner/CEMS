package server;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllers.JDBC.DB_controller;
import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

//EchoServer
public class CEMSserver extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	// final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 * 
	 */

	Connection conn;
	public static ServerController serverController;
	
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
        	
        } catch(Throwable t) {System.out.println("Error in clientConnected");};  
        
        // Print the client's IP address and hostname
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
		Msg msg = (Msg)msgObj;
		java.sql.Statement stmt = null;
		String queryStr;
		ResultSet rs;
		System.out.println("Message received: " + msg + " from " + client);
		try {
			switch (msg.getType()) {
				case select:
					stmt = conn.createStatement();
					queryStr = DB_controller.createSELECTquery(msg.getSelect(), msg.getFrom(), msg.getWhere());
					System.out.println("query:\n"+ queryStr);
					rs = stmt.executeQuery(queryStr);
					boolean isUser = (msg.getFrom().get(0).equals("cems.user")) ? true : false;
					Msg tmpMsg = createDataMsg(isUser, rs);
					sendToClient(tmpMsg, client);
					break;
					
				case update:
					stmt = conn.createStatement();
					queryStr = DB_controller.createUPDATEquery(msg.getTableToUpdate(), msg.getSet(), msg.getWhere());
					System.out.println("query:   "+ queryStr);
					stmt.executeUpdate(queryStr);
					sendToClient(new Msg(MsgType.succeeded), client);
					break;
					
				case disconnect:
					System.out.println("clientDisconnected" + client);
					serverController.removeConnected(client.getInetAddress());
					sendToClient(new Msg(MsgType.bye), client);
					break;
					
				case manyMessages:
					for(Msg act : msg.getMsgLst()) 
						handleMessageFromClient(act, client);
					sendToClient(new Msg(MsgType.succeededAll), client);
					break;
					
				case insert:
					break;
				default:
					break;
			}
		} catch (SQLException ex) {/* handle any errors */}
		return;
	}

	/**
	 * @param msg
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Msg createDataMsg(boolean isUser,ResultSet rs) throws SQLException {
		ArrayList<ArrayList<Object>> dataToClient = new ArrayList<>();
		int colunmCount = rs.getMetaData().getColumnCount();
		while (rs.next()) {
			ArrayList<Object> rowTemp = new ArrayList<>(colunmCount);
			for (int i = 1; i < colunmCount + 1; i++)
				rowTemp.add(rs.getObject(i));
			dataToClient.add(rowTemp);
		}
		Msg tmpMsg = new Msg(MsgType.data);
		tmpMsg.setData(dataToClient);
		tmpMsg.setIsUser(isUser);
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
			System.out.println("SQL connection fail");
			try {
				this.close();
				System.out.println("Server closed.");
			} catch (IOException e) {
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
	      try{
	    	  client.sendToClient(msg);
	      } catch (Exception ex) {System.out.println("error in sending msg to client.");}
	    
	}
}
