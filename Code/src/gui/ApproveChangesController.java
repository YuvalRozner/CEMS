package gui;

import java.util.ArrayList;

import client.ChatClient;
import enteties.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApproveChangesController extends AbstractController{
	private ArrayList<Request> arrchanges;	
	private ObservableList<Request> changesTable;
    @FXML
    private TableColumn<Request, String> CourseCol,ExplanationCol,IDTestCol,LecturerCol,checkBoxCol,previousCol,newCol;

    @FXML
    private TableView<Request> table;
    
    public ApproveChangesController() {
        
    	arrchanges = new ArrayList<Request>();
    	arrchanges.add(new Request("infi","ki cach ba li","1234","ilena","3","3.5"));
    	arrchanges.add(new Request("logic","ki cach ba li 22","5678","dan","3","2.5"));
    	
        for (Request R : arrchanges) {
        	R.setSelect();
        }
        changesTable = FXCollections.observableArrayList(arrchanges);
    }
    @FXML
	protected void initialize() {

    	CourseCol.setCellValueFactory(new PropertyValueFactory<Request, String>("courseName"));
    	ExplanationCol.setCellValueFactory(new PropertyValueFactory<Request, String>("explanation"));
    	IDTestCol.setCellValueFactory(new PropertyValueFactory<Request, String>("idTest"));
    	LecturerCol.setCellValueFactory(new PropertyValueFactory<Request, String>("lecturer"));
    	checkBoxCol.setCellValueFactory(new PropertyValueFactory<Request, String>("select"));
    	previousCol.setCellValueFactory(new PropertyValueFactory<Request, String>("previousTime"));
    	newCol.setCellValueFactory(new PropertyValueFactory<Request, String>("newTime"));
		table.setItems(changesTable);
		table.refresh();
	}

    @FXML
    void aprroveBtn(ActionEvent event) {

    }

    @FXML
    void backBtn(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("HodMenu").display();
    }

}
