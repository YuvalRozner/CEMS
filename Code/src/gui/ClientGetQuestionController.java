package gui;

import client.ChatClient;
import client.ClientController;
import client.ClientUI;
import controllers.JDBC.DB_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public  class ClientGetQuestionController {
	
    @FXML
    private Button btnConnect;
    
    @FXML
    private TextField inputIp;

    @FXML
    private TextField inputPort;

    @FXML
    private Label lblIP;

    @FXML
    private Label lblPort;
    
	@FXML
	private Button btnExit = null;
	
	@FXML
	private Button btnShow = null;
	
    @FXML
    void connect(ActionEvent event) {
    	try {
    		ClientUI.chat = new ClientController(inputIp.getText(), Integer.valueOf(inputPort.getText()));
    		btnShow.setDisable(false);
    	} catch(Throwable t) {System.out.println("input ip and port - error connecting.");}    	
    }
    
	public void show(ActionEvent event) throws Exception {
		
		try {
			ClientUI.chat.accept(DB_controller.getAllQuestion());
		}catch(Throwable t) {System.out.println("accept dont work");};
		
		TableViewSample tableViewSample = new TableViewSample(ChatClient.questionList);
		//FXMLLoader loader = new FXMLLoader();
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		tableViewSample.start(primaryStage);
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ClientGetQuestion.fxml"));	
		Scene scene = new Scene(root);
		primaryStage.setTitle("Client Get Question");
		primaryStage.setScene(scene);
		primaryStage.show();	 	   
	}
	
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("exit ClientGetQuestion");
		try {
			ClientUI.chat.accept("disconnected");
			System.exit(0);}
		catch(Throwable t) {
			System.out.println("error getExitBtn");
		}
		System.exit(0); //exit 
	}
	
	public  void display(String message) {
		System.out.println("message");	
	}
}
