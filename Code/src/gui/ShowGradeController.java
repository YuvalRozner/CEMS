package gui;

import java.util.ArrayList;

import client.ChatClient;
import enteties.StudentTest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ShowGradeController extends AbstractController{
	
	private ArrayList<StudentTest> arrGrades;	
	private ObservableList<StudentTest> gradesTable;
    @FXML
    private TableColumn<StudentTest, String> CommentCol,GradeCol,NameCourseCol,NumCourseCol,ShowCol,NumTestCol;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<StudentTest> table;

    @FXML
    void backBtn(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("studentMenu").display();
    }
    public ShowGradeController() {
    	arrGrades = new ArrayList<StudentTest>(Main.arrGrades);
        for (StudentTest Grade : Main.arrGrades) {
        	Grade.setNewShow();
        	Grade.getShow().setOnMouseClicked(event -> {
        		try {
        			showTestOpen(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	});
        }
        gradesTable = FXCollections.observableArrayList(arrGrades);
        //for (StudentTest T : arrStudentTest) {
        //    arrdup.add(new StudentTest(T.getStudentId(), T.getGrade(), T.getShow(), T.getNote(), T.getSelect()));
            
        //}
    }
    
    @FXML
	protected void initialize() {
    	CommentCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("comment"));
    	GradeCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("grade"));
    	ShowCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("show"));

    	
    	
    	NameCourseCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(CellDataFeatures<StudentTest, String> param) {
    	        return new SimpleStringProperty(param.getValue().getCourse().getCourseName());
    	    }
    	});
    	NumCourseCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(CellDataFeatures<StudentTest, String> param) {
    	        return new SimpleStringProperty(param.getValue().getCourse().getCourseNum());
    	    }
    	});
    	NumTestCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(CellDataFeatures<StudentTest, String> param) {
    	        return new SimpleStringProperty(param.getValue().getTest().getId());
    	    }
    	});
		table.setItems(gradesTable);
		table.refresh();
		
	}
    
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception{
    	ChatClient.screens.putIfAbsent("showTest", new ShowTestController());
		ChatClient.getScreen("showTest").start("showTest");
        System.out.println("Button clicked!");
    }
}