package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.QuestionController;
import enteties.Question;
import enteties.StudentTest;
import enteties.Test;
import enteties.TestToExecute;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

//gui.OnlineTestWithAnswersController
public class ShowStudentTestController extends AbstractController {
	
	@FXML
    private VBox dataVbox;
	@FXML
    private Label info1,info2,info3,info4,info5,info6;
	
	
    
	private ArrayList<ToggleGroup> toggleGroups = new ArrayList<ToggleGroup>();

    ArrayList<Label> pointsLabels =new ArrayList<>();
 
    private static QuestionController questionController = new QuestionController();
    
    
    StudentTest studentTest;// from approveGradeController
    TestToExecute testToExecute;// from ExecuteTestController
    Test test;
    String testName; //need to bring test name ....
    
    String testId;
    
    ArrayList<Question> questions = new ArrayList<Question>();
    
    public ShowStudentTestController() {
    	Msg msg = initializeDataAndGetMsg();
    	sendMsg(msg);
    	questions = msgReceived.convertData(Question.class); //ArrayList
    }
    
    private Msg initializeDataAndGetMsg(){
    	Msg msg = null;
    	if (ChatClient.lastCurrentScreen instanceof ApproveGradeController) {
    		studentTest =  ((ApproveGradeController)ChatClient.screens.get("approveGrade")).getStudentTestToShow(); //get the studenttest to show from the last screen
    		test = studentTest.getTest();
    		//testName = "(code-"+studentTest.getTestCode()+")  " + studentTest.getTest().getCourse().getName() +"  " + studentTest.getTestToExecute().getDate();
    		testName = "dor";
    		msg = questionController.getQuestionAndPointsByTestCodeAndStudentId(studentTest.getTestCode(), studentTest.getStudentId());
    
    	}
    	else if(ChatClient.lastCurrentScreen instanceof ExecuteTestController) {
    		testToExecute =  ((ExecuteTestController)ChatClient.screens.get("executeTest")).getTestToShow(); //get the studenttest to show from the last screen
		
    		test = testToExecute.getTest();
			testName = "(code-"+testToExecute.getTestCode()+")  " + testToExecute.getTest().getCourse().getName() +"  " + testToExecute.getDate();
			msg = questionController.getQuestionAndPointsByTestId(testToExecute.getTestId());
    	}
    	return msg;
    }
    @FXML
    protected void initialize() {
    	
    	//set the info of the test
    	if (studentTest!= null) {
    		info1.setText("Test:" + testName);
    		info2.setText("Student Name:" + studentTest.getStudentName());
    		info3.setText("Grade:" + String.valueOf(studentTest.getGrade()));
    		//System.out.println(studentTest.getTest());
    		//if (studentTest.getTest().getInstructionsForStudent() != null)
    		//	info5.setText(studentTest.getTest().getInstructionsForStudent());
    		//if (studentTest.getTest().getInstructionsForLecturer() != null)
    		//	info6.setText(studentTest.getTest().getInstructionsForLecturer());
    	}
    	else {
    		info1.setText("Test:" + testName);
    		info2.setText("Duration:" +  testToExecute.getTest().getDuration());
    		info3.setText("Instuction for lecturer:" + testToExecute.getTest().getInstructionsForLecturer());
    		info4.setText("Instuction for student:" + testToExecute.getTest().getInstructionsForStudent());
    		info5.setText(testToExecute.getTest().getInstructionsForStudent());
    		info6.setText(testToExecute.getTest().getInstructionsForLecturer());
    	}
    	
    	int questionCounter = 1;
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        int row = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i); //get a question and 4 answers
            
            Label questionLabel = new Label(questionCounter + ". " + question.getQuestion()); //set the question label
            questionCounter++;
            questionLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-weight: bold; -fx-font-size: 14px;");
            //Label pointsLabel = new Label("Points: " + "0"); //set the points label
            Label pointsLabel = new Label("Points: " + question.getPoints()); //set the points label
            pointsLabels.add(pointsLabel); //save the pointslabel for later changes
            pointsLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px;");
            gridPane.add(questionLabel, 0, i + row);
            gridPane.add(pointsLabel,1, i + row); 
            
            //setting the 4 radiobutton in a toggle group and set each answer
            ToggleGroup answerGroup = new ToggleGroup();
            for (int j = 0; j < 4; j++) {
            	row++;
                RadioButton answerRadioButton = new RadioButton(question.getAnswers()[j]);
                answerRadioButton.setMouseTransparent(true);  ////////////////////////////////////////////////////
                answerRadioButton.setToggleGroup(answerGroup);
                gridPane.add(answerRadioButton, 0, i + row); // Adjust the row and column indices as needed
            }
            toggleGroups.add(answerGroup);
        }
        dataVbox.getChildren().add(gridPane);
        if (studentTest!= null) {
        	showCorrectAnswersAndStudentAnswer();
        	}
       // else {showCorrectAnswers();};
        
    }
 
/*
    public void showCorrectAnswers() {
	    for (int i = 0; i < toggleGroups.size(); i++) {
	        ToggleGroup toggleGroup = toggleGroups.get(i);
	      //  int correctAnswerIndex = correctAnswerIndices.get(i);
	        RadioButton correctRadioButton = (RadioButton) toggleGroup.getToggles().get(correctAnswerIndex);
	        correctRadioButton.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
	        correctRadioButton.setText(correctRadioButton.getText() + " - Correct answer"); 
	    }
	}
	*/
	public void showCorrectAnswersAndStudentAnswer() {
		//System.out.println("Real : the answers for the test  " + questions.get(0).getCorrectAnswer() + ", " + questions.get(1).getCorrectAnswer() + ", " +questions.get(2).getCorrectAnswer() + ", " +questions.get(3).getCorrectAnswer() + ", " +questions.get(4).getCorrectAnswer() + ", " );
		//System.out.println("Student : the answers for the test  " + studentTest.getAnswers());

	    for (int i = 0; i < toggleGroups.size(); i++) {
	    	Question question = questions.get(i); //get a question and 4 answers        
	    	String[] answers = studentTest.getAnswers().split("");
	        ToggleGroup questionToggleGroup = toggleGroups.get(i);
	        int correctAnswerIndex = question.getCorrectAnswer() - 1;
	        RadioButton correctRadioButton = (RadioButton) questionToggleGroup.getToggles().get(correctAnswerIndex);
	        correctRadioButton.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
	        correctRadioButton.setText(correctRadioButton.getText() + " - Correct answer");
	        // Get the student's selected answer index
	        int selectedAnswerIndex = Integer.parseInt(answers[i]) - 1;
	        RadioButton selectedRadioButton = (RadioButton) questionToggleGroup.getToggles().get(selectedAnswerIndex);
	        selectedRadioButton.setSelected(true);
	        System.out.println("the selected is: " + selectedAnswerIndex  + " , and the corrected is: " +correctAnswerIndex +" -1 on evrything" );
            if (selectedAnswerIndex != correctAnswerIndex) {
            	pointsLabels.get(i).setText("Points: 0");
            	pointsLabels.get(i).setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            	selectedRadioButton.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            	selectedRadioButton.setText(selectedRadioButton.getText() + " - Wrong answer");
            }  
	    }
	}
}