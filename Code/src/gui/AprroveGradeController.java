package gui;
import java.util.ArrayList;

import client.ChatClient;
import enteties.StudentTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//// חסרה פונקציונליות של החלפת טבלה כשמחליפים מקצוע
//// חסרה פונקציונליות של select all
public class AprroveGradeController extends AbstractController{
	
	private ArrayList<StudentTest> arrStudentTest;	
	private ObservableList<StudentTest> TestTable;
	//private ArrayList<Question> arrdup = new ArrayList<Question>();

    @FXML
    private TableColumn<StudentTest, String> idstuCol,gradeCol,showCol,NoteCol,checkBoxCol;

    @FXML
    private Button aprrove;

    @FXML
    private Button back;


    @FXML
    private RadioButton selectAll;


    @FXML
    private ComboBox<String> corseComboBox;

    @FXML
    private TableView<StudentTest> table= new TableView<StudentTest>();
    
    public AprroveGradeController() {
        
    	arrStudentTest = new ArrayList<StudentTest>(Main.arrStudentTest);
    	
        for (StudentTest studentTest : Main.arrStudentTest) {
        	studentTest.setNewNote();
        	studentTest.setNewShow();
        	studentTest.setNewSelect();
        	studentTest.getShow().setOnMouseClicked(event -> {
        		try {
        			showTestOpen(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	});
        }
        TestTable = FXCollections.observableArrayList(arrStudentTest);
       
        //for (StudentTest T : arrStudentTest) {
        //    arrdup.add(new StudentTest(T.getStudentId(), T.getGrade(), T.getShow(), T.getNote(), T.getSelect()));
            
        //}
    }
    
    
    @FXML
	protected void initialize() {

    	idstuCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("studentId"));
    	gradeCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("grade"));
    	checkBoxCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("select"));
    	NoteCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("note"));
    	showCol.setCellValueFactory(new PropertyValueFactory<StudentTest, String>("show"));
    	
		table.setItems(TestTable);
		table.refresh();
		corseComboBox.getItems().addAll("infi", "logic");
	}
    
	public void backBtn(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("lecturerMenu").display();
	}
	public void aprroveBtn(ActionEvent event) throws Exception {
		return;
	}
	@FXML
    private void showTestOpen(MouseEvent event) throws Exception{
    	ChatClient.screens.putIfAbsent("showTest", new ShowTestController());
		ChatClient.getScreen("showTest").start("showTest");
        System.out.println("Button clicked!");
    }
    
    
    
    
    
    

}
