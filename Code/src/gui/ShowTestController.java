package gui;

import java.util.ArrayList;

import enteties.QuestionToShow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowTestController extends AbstractController{
	
	private ArrayList<QuestionToShow> arrQuestionsToShow=new ArrayList<QuestionToShow>();	
	private ObservableList<QuestionToShow> QuestionsToShowTable;

    @FXML
    private TableColumn<QuestionToShow, String> MarkedAnswerCol,StudentPointCol,correctAnswerCol,numCol,questionCol,questionPointCol;

    @FXML
    private Button exitBtn;


    @FXML
    private Label courseName,date,grade,studentId;

    @FXML
    private TableView<QuestionToShow> table= new TableView<QuestionToShow>();;

    @FXML
    void ExitBtn(ActionEvent event) {
    	
    }
    public ShowTestController() {
    	QuestionToShow Q1= new QuestionToShow("2","10","1","10","how much is 1+1?","2");
		QuestionToShow Q2= new QuestionToShow("1","20","2","0","how much is 7+8?","3");
		arrQuestionsToShow.add(Q1);
		arrQuestionsToShow.add(Q2);
    	System.out.println("1");
    	System.out.println("2");
    	QuestionsToShowTable = FXCollections.observableArrayList(arrQuestionsToShow);
    	System.out.println("3");
        //for (StudentTest T : arrStudentTest) {
        //    arrdup.add(new StudentTest(T.getStudentId(), T.getGrade(), T.getShow(), T.getNote(), T.getSelect()));
            
        //}
    }
    @FXML
   	protected void initialize() {
    	System.out.println("4");
    	StudentPointCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("pointOfStudent"));
    	System.out.println("5");
    	MarkedAnswerCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("answerOfStudent"));
    	System.out.println("6");
    	correctAnswerCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("correctAns"));
    	System.out.println("7");
    	numCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("numberInTest"));
    	System.out.println("8");
    	questionCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("question"));
    	System.out.println("9");
    	questionPointCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("point"));
    	System.out.println("10");
    	table.setItems(QuestionsToShowTable);
    	System.out.println("11");
		table.refresh();
		System.out.println("12");
    }
}  
