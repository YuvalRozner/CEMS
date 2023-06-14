package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.RequestController;
import enteties.Request;
import enteties.TestToExecute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApproveChangesController extends AbstractController{
		
	private ObservableList<Request> changesTable;
    @FXML
    private TableColumn<Request, String> CourseCol,ExplanationCol,IDTestCol,LecturerCol,checkBoxCol,previousCol,newDurationCol;

    @FXML
    private TableView<Request> table;
    
    /**
	 * the list of testToExecute for the comboBox according to the user logged in.
	 */
    private ArrayList<Request> request;
    
    /**
	 * object to use the RequestController class method.
	 */
    private static RequestController requestController = new RequestController();
    
    public ApproveChangesController() {
    	Msg msg = requestController.selectRequest(ChatClient.user.getId());
    	sendMsg(msg);
    	request = msgReceived.convertData(Request.class);
        changesTable = FXCollections.observableArrayList(request);
    }
    @FXML
	protected void initialize() {

    	CourseCol.setCellValueFactory(new PropertyValueFactory<Request, String>("courseName"));
    	ExplanationCol.setCellValueFactory(new PropertyValueFactory<Request, String>("explanation"));
    	IDTestCol.setCellValueFactory(new PropertyValueFactory<Request, String>("idTest"));
    	LecturerCol.setCellValueFactory(new PropertyValueFactory<Request, String>("lecturerName"));
    	checkBoxCol.setCellValueFactory(new PropertyValueFactory<Request, String>("select"));
    	previousCol.setCellValueFactory(new PropertyValueFactory<Request, String>("previousTime"));
    	newDurationCol.setCellValueFactory(new PropertyValueFactory<Request, String>("newTime"));
		table.setItems(changesTable);
		table.refresh();
	}

    @FXML
    void aprroveBtn(ActionEvent event) {

    }
}
