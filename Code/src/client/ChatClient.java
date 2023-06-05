package client;

import java.io.IOException;
import java.util.HashMap;
import controllers.JDBC.Msg;
import enteties.User;
import gui.AbstractController;
import ocsf.client.AbstractClient;


public class ChatClient extends AbstractClient {
	// Instance variables **********************************************
	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	
	public static HashMap<String, AbstractController> screens = new HashMap<String, AbstractController>();
	public static User user;
	public static boolean awaitResponse = false;

	// Constructors ****************************************************
	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */
	public ChatClient(String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor
		openConnection();
		System.out.println("connected to server");
	}


	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object tmpMsg) {
		Msg msg = ((Msg)tmpMsg);
		awaitResponse = false; //important. magic line.
		System.out.println("Message recieved from server -> " + msg);	
		if(msg instanceof Msg) {
			switch (msg.getType()) {
				case succeeded:
					System.out.println("server executed the query.");
					break;
				case succeededAll:
					System.out.println("server handle all bunch of msgs.");
					break;
				case bye:
					System.out.println("client forced to stop by the server.");
					System.exit(0);
					break;
				case data:
					AbstractController.setDataReceived(msg);
					break;
				case user:
					user = msg.convertData(User.class).get(0);
					break;
				case empty:
					System.out.println("server didn't find any data matching this query.");
					break;
				default:
					break;
			}
		}
	}

	public void handleMessageFromClientUI(Msg msg) {
		try {
			awaitResponse = true;
			sendToServer(msg); //send the msg to server.
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not send message to server: Terminating client." + e);}
	}
	
	public static AbstractController getScreen(String screenName) {
		return screens.get(screenName);
	}
	
	public static void resetUser() {
		user = null;
	}
}
