package gui;

import java.util.ArrayList;

import client.ChatClient;
import enteties.TestToExexeute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RunningTestController extends AbstractController{
	
	private ArrayList<TestToExexeute> arrRunningTest;	
	private ObservableList<TestToExexeute> RuningTestTable;
    @FXML
    private Button backbtn;

    @FXML
    private Button changebtn;

    @FXML
    private TableColumn<TestToExexeute, String> corsecol,durationcol,numcol,selectcol,statuscol;

    @FXML
    private Button lockbtn;
    
    @FXML
    private TableView<TestToExexeute> table= new TableView<TestToExexeute>();
    
    public RunningTestController() {
    	arrRunningTest = new ArrayList<TestToExexeute>(Main.arrRuningTest);
    	
        for (TestToExexeute runingTest : Main.arrRuningTest) {
        	runingTest.setNewSelect();
        	runingTest.setNewDurationField();
        }
        RuningTestTable = FXCollections.observableArrayList(arrRunningTest);
       
        //for (StudentTest T : arrStudentTest) {
        //    arrdup.add(new StudentTest(T.getStudentId(), T.getGrade(), T.getShow(), T.getNote(), T.getSelect()));
            
        //}
    }
    
    @FXML
	protected void initialize() {

    	corsecol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("course"));
    	durationcol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("durationField"));
    	numcol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("testNum"));
    	statuscol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("status"));
    	selectcol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("select"));
  
		table.setItems(RuningTestTable);
		table.refresh();
		
	}

    @FXML
    public void backBtn(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("lecturerMenu").display();
	}

    @FXML
    void changeBtn(ActionEvent event) {

    }

    @FXML
    void lockBtn(ActionEvent event) {

    }

}