package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import enteties.Question;
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
	public static ArrayList<Question> questionList;
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
	public void handleMessageFromServer(Object msg) {
		awaitResponse = false; //important. magic line.
		System.out.print("Message recieved from server -> ");	
		if(msg instanceof Msg) {
			switch (((Msg)msg).getType()) {
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
					if(((Msg)msg).getDataType().equals("questions")) {
						@SuppressWarnings("unchecked") //ignore warning of casting types.
						ArrayList<ArrayList<String>> dataFromServer = (ArrayList<ArrayList<String>>) ((Msg)msg).getData();
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
					else if(((Msg)msg).getDataType().equals("cems.user")) {
						@SuppressWarnings("unchecked")
						ArrayList<ArrayList<String>> dataFromServer = (ArrayList<ArrayList<String>>) ((Msg)msg).getData();
						user = new User(dataFromServer.get(0).get(0), dataFromServer.get(0).get(1), dataFromServer.get(0).get(2), dataFromServer.get(0).get(3), dataFromServer.get(0).get(4), dataFromServer.get(0).get(5));
					}

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
}
