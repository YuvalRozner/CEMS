package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import enteties.Question;
import gui.AbstractController;
import ocsf.client.AbstractClient;


public class ChatClient extends AbstractClient {
	// Instance variables **********************************************
	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	
	public static HashMap<String, AbstractController> screens = new HashMap<String, AbstractController>();
	
	ChatIF clientUI;
	public static ArrayList<Question> questionList;
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
		openConnection();
		//screens.put("ClientGetQuestionController", null);
		//screens.put("TableViewSample", null);
		System.out.println("connected to server");
	}


	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {
		System.out.print("Message recieved from server -> ");
		if (msg instanceof String ) { // update
			System.out.println((String)msg);
			awaitResponse = false;	
			if (((String)msg).equals("disconected")) {
				System.out.println("client forced to stop by the server.");
				System.exit(0);
			}
		}
		else { //create a List of question out of the List of List got from the server:
			System.out.println("list of questions");
			@SuppressWarnings("unchecked") //ignore warning of casting types.
			ArrayList<ArrayList<String>> dataFromServer = (ArrayList<ArrayList<String>>) msg;
			awaitResponse = false;
			questionList = new ArrayList<Question>(); //resets the List of question.
			Question tmpQ;
			for (int i = 0; i < dataFromServer.size(); i++) 
				if (dataFromServer.get(i) != null) {
					tmpQ = new Question(dataFromServer.get(i).get(0), dataFromServer.get(i).get(1),
							dataFromServer.get(i).get(2), dataFromServer.get(i).get(3),
							dataFromServer.get(i).get(4), dataFromServer.get(i).get(5));
					questionList.add(tmpQ); // add the question to the list.
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
			awaitResponse = true;
			sendToServer(message); //send the msg to server.
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		} catch (IOException e) {
			e.printStackTrace();
			clientUI.display("Could not send message to server: Terminating client." + e);
			ClientUI.chat.accept(new Msg(MsgType.disconnect));	}
	}
	
	// new method.
	public void handleMessageFromClientUI(Object msg) {
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
			clientUI.display("Could not send message to server: Terminating client." + e);
			ClientUI.chat.accept(new Msg(MsgType.disconnect));	}
	}
	
	public static AbstractController getScreen(String screenName) {
		return screens.get(screenName);
	}
}
