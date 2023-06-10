package gui;

import java.util.ArrayList;
import enteties.Course;
import enteties.Question;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class CreateTestController extends AbstractController{

    @FXML
    private RadioButton answer1RadioButton, answer2RadioButton,answer3RadioButton,answer4RadioButton;

    @FXML
    private ToggleGroup answersToggleGroup;
    @FXML
    private Label questionLabel;
	//@FXML
   // private TableView<Question> table = new TableView<Question>();
    @FXML
    private TableView<Question> table;
    @FXML
    private TableColumn<Question, String>  idCol, lecturerCol, questionTextCol,checkBoxCol,pointsCol;
    @FXML
    private ComboBox<String> subjectComboBox;
    
    @FXML
    private TextArea commentsForLecturerTextArea;
    
    
    @FXML
    private TextArea commentsForStudentTextArea;
    @FXML
    private TextField durationTextField;
    private ArrayList<Question> questionsArrayList;
    private ObservableList<Question> questionsObservableList;
    
    
    private ArrayList<Question> arrdup = new ArrayList<Question>();
	

	
	


    public CreateTestController() {
    	
    	//START fake data //////////////////////////////////////////
    	ArrayList<Question> questionFake; // fake
    	Course course1 = new Course("1","infi", "03");
		Course course2 = new Course("2","logic", "04");
		Course course3 = new Course("3","data sturcture","04");
		ArrayList<Course> CoursesArray1 = new ArrayList<Course>();
		CoursesArray1.add(course1);
		CoursesArray1.add(course2);
		ArrayList<Course> CoursesArray2 = new ArrayList<Course>();
		CoursesArray2.add(course3);
    	
    	String[] answers1 = new String[4];
    	answers1[0] = "nothing";
    	answers1[1] = "1 or 2";
    	answers1[2] = "2";
    	answers1[3] = "i dont know math";
    	Question q1 = new Question("0124",5,"what is 1+1?","03","01", answers1,2,"goodluck",CoursesArray1);

    	String[] answers2 = new String[4];
    	answers2[0] = "i think it 4";
    	answers2[1] = "i know it for";
    	answers2[2] = "it is four";
    	answers2[3] = "for what?";
    	Question q2 = new Question("0125",6,"what is 2+2?","03","01",answers2,2,"you got it",CoursesArray1 );

    	String[] answers3 = new String[4];
    	answers3[0] = "rozni wrote this question, he should answer";
    	answers3[1] = "i dont care ";
    	answers3[2] = "it only for my fun";
    	answers3[3] = "all the answers are correct";
    	Question q3 = new Question("0141",7,"A={2n: n belong to N}. Is multiplying two terms in A is also a term in","03","02",answers3,3,"",CoursesArray2);
    	
    	questionFake = new ArrayList<Question>();
    	questionFake.add(q1);
		questionFake.add(q2);
		questionFake.add(q3);
		//end fake data //////////////////////////////////////////
    	
    	
		questionsArrayList = new ArrayList<Question>(questionFake);
       
        //ArrayList<Question> convertedList = new ArrayList<>();
        for (Question question : questionFake) {
        	question.setNewCheckbox();
        	question.setNewTextField();
        	question.getTextField().setDisable(true);
        	question.getCheckbox().selectedProperty().addListener(new ChangeListener<Boolean>() {
    			@Override
    			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    				question.getTextField().setDisable(!newValue);
    				System.out.println("Checkbox " + (newValue ? "pressed" : "unpressed")); 	}
    		});
        }
      
        
        questionsObservableList = FXCollections.observableArrayList(questionsArrayList);
        
    }

    @FXML
	protected void initialize() {
    	idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
    	questionTextCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Question"));
    	lecturerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
		checkBoxCol.setCellValueFactory(new PropertyValueFactory<Question, String>("checkbox"));
		pointsCol.setCellValueFactory(new PropertyValueFactory<Question, String>("textField"));
		
		table.setItems(questionsObservableList);
		table.refresh();
		subjectComboBox.getItems().addAll("math", "software");
	}
    
    @FXML
    private void createNewQuestion(ActionEvent event) throws Exception{
    	start("createQuestion", "createTest");
    }
    @FXML
    void showAnswers(MouseEvent event) {
    	Question selectedQuestion = table.getSelectionModel().getSelectedItem();
    	questionLabel.setText(selectedQuestion.getQuestion());
    	answer1RadioButton.setText(selectedQuestion.getAnswers()[0]);
    	answer2RadioButton.setText(selectedQuestion.getAnswers()[1]);
    	answer3RadioButton.setText(selectedQuestion.getAnswers()[2]);
    	answer4RadioButton.setText(selectedQuestion.getAnswers()[3]);
    	ObservableList<Toggle> toggles = answersToggleGroup.getToggles();
        Toggle toggle = toggles.get(selectedQuestion.getCorrectAnswer());  // Index 2 represents the third toggle
        answersToggleGroup.selectToggle(toggle);
        
    }
    @FXML
    void save(ActionEvent event) {

    }
}

