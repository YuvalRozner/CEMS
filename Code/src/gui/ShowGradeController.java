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
        }
        gradesTable = FXCollections.observableArrayList(arrGrades);
       
        //for (StudentTest T : arrStudentTest) {
        //    arrdup.add(new StudentTest(T.getStudentId(), T.getGrade(), T.getShow(), T.getNote(), T.getSelect()));
            
        //}
    }
    
    @FXML
	protected void initialize() {
    	System.out.println("1");
    	CommentCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("comment"));
    	System.out.println("2");
    	GradeCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("grade"));
    	System.out.println("3");
    	ShowCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("show"));
    	System.out.println("4");

    	
    	
    	NameCourseCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(CellDataFeatures<StudentTest, String> param) {
    	        return new SimpleStringProperty(param.getValue().getCourse().getCourseName());
    	    }
    	});
    	System.out.println("5");
    	NumCourseCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(CellDataFeatures<StudentTest, String> param) {
    	        return new SimpleStringProperty(param.getValue().getCourse().getCourseNum());
    	    }
    	});
    	System.out.println("6");
    	NumTestCol.setCellValueFactory(new Callback<CellDataFeatures<StudentTest, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(CellDataFeatures<StudentTest, String> param) {
    	        return new SimpleStringProperty(param.getValue().getTest().getId());
    	    }
    	});
    	System.out.println("7");
		table.setItems(gradesTable);
		System.out.println("8");
		table.refresh();
		System.out.println("9");
		
	}

}