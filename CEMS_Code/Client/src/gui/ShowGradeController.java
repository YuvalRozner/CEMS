package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.StudentTestController;
import enteties.StudentTest;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ShowGradeController extends AbstractController{
	
	private ArrayList<StudentTest> allTest;	
	private ObservableList<StudentTest> gradesTable;
	
    @FXML
    private TableColumn<StudentTest, String> CommentCol,DateCol,GradeCol,NameCourseCol,ShowCol;
    @FXML
    private TableColumn<StudentTest, Integer> codeTestCol;

    @FXML
    private TableView<StudentTest> table;
    
    StudentTestController studentTestController =new StudentTestController();
    
    public ShowGradeController() {
    	//Msg msg =studentTestController.getMsgForStudentTestsByID(ChatClient.user);
    	//sendMsg(msg);
    	allTest = msgReceived.convertData(StudentTest.class);
    	System.out.println("befor convert"+msgReceived.getData());
    	System.out.println("after convert"+allTest);
        for (StudentTest Grade : allTest) {
        	Grade.setNewShow();
        	Grade.getShow().setOnMouseClicked(event -> {
        		try {
        			showTestOpen(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	});
        }
        gradesTable = FXCollections.observableArrayList(allTest);

    }
    
    @FXML
	protected void initialize() {
    	CommentCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("allNotes"));
    	GradeCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("grade"));
    	ShowCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("show"));
    	System.out.println(codeTestCol);
    	codeTestCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, Integer>, ObservableValue<Integer>>() {
    	    @Override
    	    public ObservableValue<Integer> call(CellDataFeatures<StudentTest, Integer> param) {
    	        Integer value = param.getValue().getTestToExecute().getTestCode(); // Assuming getDate() returns an Integer
    	        return new SimpleIntegerProperty(value).asObject();
    	    }
    	});
    	NameCourseCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(CellDataFeatures<StudentTest, String> param) {
    	        return new SimpleStringProperty(param.getValue().getTestToExecute().getDate());
    	    }
    	});
    	DateCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(CellDataFeatures<StudentTest, String> param) {
    	        return new SimpleStringProperty(param.getValue().getTestToExecute().getDate());
    	    }
    	});
		table.setItems(gradesTable);
		table.refresh();
	}
    
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception{
    	start("showTest", "showGrade");
        System.out.println("Button clicked!");
    }
}