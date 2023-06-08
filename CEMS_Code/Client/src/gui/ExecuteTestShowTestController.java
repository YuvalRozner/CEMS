package gui;

import java.util.ArrayList;

import enteties.QuestionToShow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExecuteTestShowTestController extends AbstractController {

	private ArrayList<QuestionToShow> arrQuestionsToShow=new ArrayList<QuestionToShow>();	
	private ObservableList<QuestionToShow> QuestionsToShowTable;

    @FXML
    private TableColumn<QuestionToShow, String> correctAnswerCol,numCol,questionCol,questionPointCol;


    @FXML
    private Label courseName,date;

    @FXML
    private TableView<QuestionToShow> table= new TableView<QuestionToShow>();

    public ExecuteTestShowTestController() {
    	QuestionToShow Q1= new QuestionToShow("2","10","how much is 1+1?","2");
		QuestionToShow Q2= new QuestionToShow("1","20","how much is 7+8?","3");
		arrQuestionsToShow.add(Q1);
		arrQuestionsToShow.add(Q2);
    	QuestionsToShowTable = FXCollections.observableArrayList(arrQuestionsToShow);
        //for (StudentTest T : arrStudentTest) {
        //    arrdup.add(new StudentTest(T.getStudentId(), T.getGrade(), T.getShow(), T.getNote(), T.getSelect()));
            
        //}
    }
    @FXML
   	protected void initialize() {
    	correctAnswerCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("correctAns"));
    	numCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("numberInTest"));
    	questionCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("question"));
    	questionPointCol.setCellValueFactory(new PropertyValueFactory<QuestionToShow, String>("point"));
    	table.setItems(QuestionsToShowTable);
		table.refresh();
    }

}
