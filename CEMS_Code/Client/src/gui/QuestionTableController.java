package gui;

import java.util.ArrayList;

import JDBC.DB_controller;
import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import client.ClientUI;
import enteties.Question;
import enteties.StudentTest;
import enteties.Test;
import enteties.TestToExecute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
/**
 * Controller class for the Question Table screen.
 * 
 * @author Mor Shmuel 
 */
public class QuestionTableController extends AbstractController{
	
	private ArrayList<Question> questionList = new ArrayList<>();

	private ObservableList<Question> QTable;
	
    @FXML
    private TableView<Question> table = new TableView<Question>();
    /**
	 * the columns for the table.
	 */
    @FXML
    private TableColumn<Question, String> idCol, questionTextCol, subjectCol, lecturerCol, courseCol;
	
    /**
	 * the columns for the table.
	 */
    @FXML
    private TableColumn<Question, Integer> questionNumberCol;
    
    /**
     * the Question test wanted to be shown. 
     */
    public Question questionToShow;
    
    /**
     * to show the answer.
     */
    @FXML
    private RadioButton answer1RadioButton, answer2RadioButton,answer3RadioButton,answer4RadioButton;
    /**
     * to show the answer.
     */
    @FXML
    private ToggleGroup answersToggleGroup;
    /**
     * to show the answer.
     */
    @FXML
    private Label questionLabel;
    
    /**
     * Default constructor for the CreateTestController class.
     * Initializes the subjectsLst.
     */
	public QuestionTableController() {
		Msg msg = new Msg(MsgType.select);
    	msg.setSelect("question.*");
    	msg.setFrom("cems.question");
    	msg.setWhereCol("hod_subject.subjectNumber", "question.subjectNum");
    	sendMsg(msg);
    	System.out.println(AbstractController.msgReceived.getData());
    	questionList = msgReceived.convertData(Question.class);
    	
		QTable = FXCollections.observableArrayList(questionList);
		/*for (Question question : QTable) {
            question.getShowQ().setOnMouseClicked(event -> {
                try {
                	showQuestionToOpen(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }*/
		
		
	}
	
	/**
     * Event handler for displaying the answers of the selected question.
     *
     * @param event The MouseEvent triggering the event.
     */
    @FXML
    void showAnswers(MouseEvent event) {
    	// Get the selected question from the table.
    	Question selectedQuestion = table.getSelectionModel().getSelectedItem();
    	// Display the question and answers in the UI.
    	questionLabel.setText(selectedQuestion.getQuestion());
    	answer1RadioButton.setText(selectedQuestion.getAnswers()[0]);
    	answer2RadioButton.setText(selectedQuestion.getAnswers()[1]);
    	answer3RadioButton.setText(selectedQuestion.getAnswers()[2]);
    	answer4RadioButton.setText(selectedQuestion.getAnswers()[3]);
    	// Select the correct answer toggle.
    	ObservableList<Toggle> toggles = answersToggleGroup.getToggles();
        Toggle toggle = toggles.get(selectedQuestion.getCorrectAnswer());  // Index 2 represents the third toggle
        answersToggleGroup.selectToggle(toggle); 
    }
	
    
    @FXML
	protected void initialize() {
    	idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
		subjectCol.setCellValueFactory(new PropertyValueFactory<Question, String>("subjectNum"));
		
		//courseCol.setCellValueFactory(new PropertyValueFactory<Question, String>("course"));
		
		questionNumberCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("number"));
		lecturerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("lecturerId"));
		questionTextCol.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        
		table.setItems(QTable);
		table.refresh();
	}
    

    @FXML
    void onEditCommitQuestionTextCol(ActionEvent event) {

    }

    @FXML
    void onMouseEnterdBackBtn(MouseEvent event) {

    }

    @FXML
    void onMouseExitedBackBtn(MouseEvent event) {

    }
 

}