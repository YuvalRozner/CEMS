package gui;

import java.util.ArrayList;

import enteties.TestToExexeute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ExecuteTestController extends AbstractController{
	
	private ArrayList<TestToExexeute> arrExecuteTestLocal;

	private ObservableList<TestToExexeute> ETTable;
	
    @FXML
    private TableView<TestToExexeute> table = new TableView<TestToExexeute>();

    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<TestToExexeute,String> codecol,coursnamecol,numTestcol,selectcol,showcol,typecol,datecol;

    @FXML
    private Button executebtn;
    public ExecuteTestController() {
    	arrExecuteTestLocal = new ArrayList<TestToExexeute>(Main.arrExecuteTest);
       
        //ArrayList<Question> convertedList = new ArrayList<>();
        for (TestToExexeute Test : Main.arrExecuteTest) {
        	Test.setNewShow();
        	Test.setNewSelect();
        	Test.setNewType();
        	Test.setNewCodeField();
        	Test.getShow().setOnMouseClicked(event -> {
        		try {
        			showTestOpen(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	});
        }
        ETTable = FXCollections.observableArrayList(arrExecuteTestLocal);
        //for (Question q : arrQuestion) {
        //    arrdup.add(new Question(q.getID(), q.getSubjectNum(), q.getCourseName(), q.getQuestion(), q.getNumber(),q.getLecturereCreated()));
        //}
    }
    	
    
    @FXML
	protected void initialize() {
    	codecol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("codeField"));
    	coursnamecol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("course"));
    	numTestcol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("testNum"));
    	selectcol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("select"));
    	showcol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("show"));
    	typecol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("type"));
    	datecol.setCellValueFactory(new PropertyValueFactory<TestToExexeute, String>("date"));
    	
		table.setItems(ETTable);
		table.refresh();

	}

    @FXML
    void executeTestBtn(ActionEvent event) {
    }
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception{
		start("executeTestShowTest", "executeTest");
        System.out.println("Button clicked!");
    }

}