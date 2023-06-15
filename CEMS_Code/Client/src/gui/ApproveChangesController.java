package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.RequestController;
import enteties.Question;
import enteties.Request;
import enteties.StudentTest;
import enteties.TestToExecute;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import notifications.NotificationAlertsController;

public class ApproveChangesController extends AbstractController{
		
	private ObservableList<Request> changesTable;
    @FXML
    private TableColumn<Request, String> CourseCol,ExplanationCol,IDTestCol,LecturerCol,checkBoxCol,previousCol,newDurationCol;

    @FXML
    private TableView<Request> table  = new TableView<Request>();
    
    @FXML
    private Text text;
    
    /**
     * ToggleGroup for the requests in the table.
     */
	private ToggleGroup requestToggleGroup;
    
    private Request chooseRequest; //not sure if i need it
    
    /**
	 * object to use the notifications class.
	 */
    private static NotificationAlertsController notification = new NotificationAlertsController();

    
    /**
	 * the list of testToExecute for the comboBox according to the user logged in.
	 */
    private ArrayList<Request> request;
    
    /**
	 * object to use the RequestController class method.
	 */
    private static RequestController requestController = new RequestController();
    
    public ApproveChangesController() {
    	requestToggleGroup = new ToggleGroup();
    	Msg msg = requestController.selectRequest(ChatClient.user.getId());
    	sendMsg(msg);
    	request = msgReceived.convertData(Request.class);
    	
		for (Request req : request) {
			 req.setNewRadioButton();
	         requestToggleGroup.getToggles().add((RadioButton)req.getRadioButton()); 
		    	
			 req.getRadioButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
		    		if (newValue == true) {
		    			chooseRequest = req;
		    			//add set disable to confirm button
		    		}
		    		else {
		    			//add set disable to confirm button
		    		} 
		    	});
		}
    	
		changesTable = FXCollections.observableArrayList(request);
		System.out.println("changesTable = " + changesTable);
	    table.setItems(changesTable);
	    table.refresh();
        System.out.println("changesTable = " + changesTable);
    }
    
    @FXML
	protected void initialize() {
    	CourseCol.setCellValueFactory(new PropertyValueFactory<Request, String>("courseName"));
    	ExplanationCol.setCellValueFactory(new PropertyValueFactory<Request, String>("explanation"));
    	IDTestCol.setCellValueFactory(new PropertyValueFactory<Request, String>("testId"));
    	LecturerCol.setCellValueFactory(new PropertyValueFactory<Request, String>("lecturerName"));
    	checkBoxCol.setCellValueFactory(new PropertyValueFactory<Request, String>("radioButton"));
    	previousCol.setCellValueFactory(new PropertyValueFactory<Request, String>("originalDuration"));
    	newDurationCol.setCellValueFactory(new PropertyValueFactory<Request, String>("duration"));

		table.setItems(changesTable);
		table.refresh();
	}
    
    /**
     * Event handler for displaying the answers of the selected question.
     *
     * @param event The MouseEvent triggering the event.
     */
    @FXML
    void showAnswers(MouseEvent event) {
        //Get the selected request from the table.
    	Request selectedReq = table.getSelectionModel().getSelectedItem();
        //Display the explanation in the UI.
    	text.setText(selectedReq.getExplanation());
    	//System.out.println("chooseRequest = " + chooseRequest);
    }

    @FXML
    void aprroveBtn(ActionEvent event) {
    	Request selectedReq = table.getSelectionModel().getSelectedItem();
        if (selectedReq != null) {
            // A request has been selected, perform the necessary actions
            System.out.println("Selected request: " + selectedReq);
            // Other logic for handling the selected request
        } else {
            // No request has been selected, display an error message or perform other actions
            System.out.println("No request selected.");
        }
    }
}
