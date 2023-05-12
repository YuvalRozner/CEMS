package client;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatIF;
import enteties.Question;
import ocsf.client.AbstractClient;


public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;
	public static ArrayList<Question> questionList = new ArrayList<Question>();
	public static boolean awaitResponse = false;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		// openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {

		System.out.println("--> handleMessageFromServer");

		ArrayList<ArrayList<String>> dataFromServer = (ArrayList<ArrayList<String>>) msg;

		awaitResponse = false;

		Question tmpQ;
		for (int i = 0; i < dataFromServer.size(); i++) {
			if (dataFromServer.get(i) != null) {
				tmpQ = new Question((String) dataFromServer.get(i).get(0), (String) dataFromServer.get(i).get(1),
						(String) dataFromServer.get(i).get(2), (String) dataFromServer.get(i).get(3),
						(String) dataFromServer.get(i).get(4), (String) dataFromServer.get(i).get(5));
				questionList.add(tmpQ);
			}
		}

	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */

	public void handleMessageFromClientUI(String message) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			clientUI.display("Could not send message to server: Terminating client." + e);
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
//End of ChatClient class
