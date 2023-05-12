package server;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public InetAddress getClientAddress() {
		return clientAddress;
	}

	public String getClientHostname() {
		return clientHostname;
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
        
        // Print the client's IP address and hostname
        System.out.println("Client connected from " + clientAddress.getHostAddress() + " (" + clientHostname + ")");
    }

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		Statement stmt = null;
		ResultSet data;
		boolean flag = false; // if the update occurs or select return non empty string -> true;
		System.out.println("Message received: " + msg + " from " + client);//////////////////////////
		String[] msgToStringArr = ((String) msg).split("\\s+");
		String firstWord = msgToStringArr[0];
		ArrayList<ArrayList<String>> dataToClient = new ArrayList<ArrayList<String>>();
		try {
			stmt = conn.createStatement();
			if (firstWord.equals("UPDATE") || firstWord.equals("SET")) {
				stmt.executeUpdate((String) msg);
				flag = true;
			} else if (firstWord.equals("SELECT")) {
				data = stmt.executeQuery((String) msg);

				data.toString();
				int colunmCount = data.getMetaData().getColumnCount();
				while (data.next()) {
					ArrayList<String> rowTemp = new ArrayList<String>(colunmCount);
					for (int i = 1; i < colunmCount + 1; i++)
						rowTemp.add(data.getString(i));
					dataToClient.add(rowTemp);
					System.out.println("rowTemp is: " + rowTemp.toString()); ////////////////// checks for us
				}
				this.sendToAllClients(dataToClient);
			}
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			this.sendToAllClients("Error");
		}
	}
	
	/**
	 * This method overrides the one in the superclass. Called when the server starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
		if (new DataBaseConnector().connectionToDataBase(this))
			System.out.println("SQL connection succeed");
		else
			System.out.println("SQL connection fail");

	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	
}

//End of EchoServer class
