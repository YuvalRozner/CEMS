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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

//gui.OnlineTestWithAnswersController
public class ShowStudentTestController extends AbstractController {
	
	@FXML
    private VBox dataVbox;
	
	@FXML
    private Label info1,info2,info3,info4,info5,info6,info7;
	
	@FXML
    private TextFlow instructionsForStudentTextFlow,instructionsForLecturerTextFlow;
    
	private ArrayList<ToggleGroup> toggleGroups = new ArrayList<ToggleGroup>();

    ArrayList<Label> pointsLabels =new ArrayList<>();
 
    private static QuestionController questionController = new QuestionController();
    
    private String screenState; // {studentShowTest, lecturerHodShowStudentTest,lecturerHodShowTest,studentDoTest}
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
    		screenState = "lecturerHodShowStudentTest";
    		studentTest =  ((ApproveGradeController)ChatClient.lastCurrentScreen).getStudentTestToShow(); //get the studenttest to show from the last screen
    		testToExecute = ((ApproveGradeController)ChatClient.lastCurrentScreen).getTestToExecuteToShow() ;
    		testName = "(code-"+testToExecute.getTestCode()+")  " + testToExecute.getTest().getCourse().getName() +"  " + testToExecute.getDate();
    		msg = questionController.getQuestionAndPointsByTestCodeAndStudentId(studentTest.getTestCode(), studentTest.getStudentId());
    
    	}
    	else if(ChatClient.lastCurrentScreen instanceof ExecuteTestController) {
    		screenState = "lecturerHodShowTest";
    		testToExecute =  ((ExecuteTestController)ChatClient.lastCurrentScreen).getTestToExecuteToShow(); //get the studenttest to show from the last scree
			testName = "(code-"+testToExecute.getTestCode()+")  " + testToExecute.getTest().getCourse().getName() +"  " + testToExecute.getDate();
			msg = questionController.getQuestionAndPointsByTestId(testToExecute.getTestId());
    	}
    	else if (ChatClient.lastCurrentScreen instanceof ShowGradeController) {
    		screenState = "studentShowTest";
    		studentTest =  ((ShowGradeController)ChatClient.lastCurrentScreen).getStudentTestToShow(); //get the studenttest to show from the last scree
    		testToExecute = studentTest.getTestToExecute();
			testName = "(code-"+testToExecute.getTestCode()+")  " + testToExecute.getTest().getCourse().getName() +"  " + testToExecute.getDate();
			msg = questionController.getQuestionAndPointsByTestId(testToExecute.getTestId());
    	}
    	
    	else if (ChatClient.lastCurrentScreen instanceof ShowTestDataController) {
    		screenState = "lecturerHodShowTest";
    		testToExecute =  ((ShowTestDataController)ChatClient.lastCurrentScreen).getTestToExecuteToShow(); //get the studenttest to show from the last scree
			testName = "(code-"+testToExecute.getTestCode()+")  " + testToExecute.getTest().getCourse().getName() +"  " + testToExecute.getDate();
			msg = questionController.getQuestionAndPointsByTestId(testToExecute.getTestId());
    	}
    	return msg;
    }
    @FXML
    protected void initialize() {
      	//set the info of the test
    	Text textForTextFlow = null;
    	info2.setText("Course: "+ testToExecute.getTest().getCourse().getName());
    	info3.setText("Duration:" +  testToExecute.getTest().getDuration() + " minutes");
    	switch (screenState) {
    	case "lecturerHodShowStudentTest":
    		info1.setText("Test Code: "+testToExecute.getTestCode());
    		info4.setText("Date: "+ testToExecute.getDate());
    		info5.setText("Student Name:" + studentTest.getStudentName());
    		info6.setText("Grade:" + String.valueOf(studentTest.getGrade()));
    		if (testToExecute.getTest().getInstructionsForStudent() != null)
    			textForTextFlow = new Text("Instructions For Student: " + testToExecute.getTest().getInstructionsForStudent());
	    		textForTextFlow.wrappingWidthProperty().bind(instructionsForStudentTextFlow.widthProperty());
	    		textForTextFlow.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
	    		instructionsForStudentTextFlow.getChildren().addAll(textForTextFlow);
			if (testToExecute.getTest().getInstructionsForLecturer() != null)
				textForTextFlow = new Text("Instructions For Lecturer: " + testToExecute.getTest().getInstructionsForLecturer());
				textForTextFlow.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
				textForTextFlow.wrappingWidthProperty().bind(instructionsForLecturerTextFlow.widthProperty());
				instructionsForLecturerTextFlow.getChildren().addAll(textForTextFlow);	
    		break;
    	case "lecturerHodShowTest":
    		info1.setText("Test Id: "+testToExecute.getTest().getId());
    		if (testToExecute.getTest().getInstructionsForStudent() != null)
    			textForTextFlow = new Text("Instructions For Student: " + testToExecute.getTest().getInstructionsForStudent());
	    		textForTextFlow.wrappingWidthProperty().bind(instructionsForStudentTextFlow.widthProperty());
	    		textForTextFlow.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
	    		instructionsForStudentTextFlow.getChildren().addAll(textForTextFlow);
			if (testToExecute.getTest().getInstructionsForLecturer() != null)
				textForTextFlow = new Text("Instructions For Lecturer: " + testToExecute.getTest().getInstructionsForLecturer());
				textForTextFlow.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
				textForTextFlow.wrappingWidthProperty().bind(instructionsForLecturerTextFlow.widthProperty());
				instructionsForLecturerTextFlow.getChildren().addAll(textForTextFlow);	
    		break;
    	case "studentShowTest":
    		info1.setText("Test Code: "+testToExecute.getTestCode());
    		info4.setText("Date: "+ testToExecute.getDate());
    		info5.setText("Student Name:" + studentTest.getStudentName());
    		info6.setText("Grade:" + String.valueOf(studentTest.getGrade()));
    		if (testToExecute.getTest().getInstructionsForStudent() != null)
    			textForTextFlow = new Text("Instructions For Student: " + testToExecute.getTest().getInstructionsForStudent());
	    		textForTextFlow.wrappingWidthProperty().bind(instructionsForStudentTextFlow.widthProperty());
	    		textForTextFlow.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
	    		instructionsForStudentTextFlow.getChildren().addAll(textForTextFlow);
    		break;
    	case "studentDoTest":
    		info4.setText("Student Name:" + studentTest.getStudentName());
    		break;
    	}
    	int questionCounter = 1;
        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        int row = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i); //get a question and 4 answers
            
            Label questionLabel = new Label(questionCounter + ". " + question.getQuestion()); //set the question label
            Label instuctionsQuestionLabel;
            if (question.getInstructions() != null)
            	instuctionsQuestionLabel = new Label( "Instruction for question: " + question.getInstructions()); //set the question label
            else
            	instuctionsQuestionLabel = new Label("");
            questionCounter++;
            questionLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-weight: bold; -fx-font-size: 14px;");
            //Label pointsLabel = new Label("Points: " + "0"); //set the points label
            Label pointsLabel = new Label("Points: " + question.getPoints()); //set the points label
            pointsLabels.add(pointsLabel); //save the pointslabel for later changes
            pointsLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px;");
            gridPane.add(instuctionsQuestionLabel, 0, i + row);
            row++;
            gridPane.add(questionLabel, 0, i + row );
            gridPane.add(pointsLabel,1, i + row); 
            
            //setting the 4 radiobutton in a toggle group and set each answer
            ToggleGroup answerGroup = new ToggleGroup();
            for (int j = 0; j < 4; j++) {
            	row++;
                RadioButton answerRadioButton = new RadioButton(question.getAnswers()[j]);
                answerRadioButton.setMouseTransparent(true); 
                answerRadioButton.setToggleGroup(answerGroup);
                gridPane.add(answerRadioButton, 0, i + row); // Adjust the row and column indices as needed
            }
            row++;
            gridPane.add(new Label(), 0, i + row); // Adjust the row and column indices as needed
            toggleGroups.add(answerGroup);
        }
        dataVbox.getChildren().add(gridPane);
        if (screenState.equals("lecturerHodShowStudentTest") || screenState.equals("studentShowTest"))
        	showCorrectAnswersAndStudentAnswer();
        else // screenState.equals("lecturerHodShowTest")
        	showCorrectAnswers();
        
    }
 

    public void showCorrectAnswers() {
    	 for (int i = 0; i < toggleGroups.size(); i++) {
   	    	Question question = questions.get(i); //get a question and 4 answers        
   	        ToggleGroup questionToggleGroup = toggleGroups.get(i);
   	        int correctAnswerIndex = question.getCorrectAnswer() - 1;
   	        RadioButton correctRadioButton = (RadioButton) questionToggleGroup.getToggles().get(correctAnswerIndex);
   	        correctRadioButton.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px; -fx-text-fill: green;");
   	        correctRadioButton.setText(correctRadioButton.getText() + " - Correct answer");
   	        correctRadioButton.setSelected(true);
   	    }
	}
	
	public void showCorrectAnswersAndStudentAnswer() {
	    for (int i = 0; i < toggleGroups.size(); i++) {
	    	Question question = questions.get(i); //get a question and 4 answers        
	    	String[] answers = studentTest.getAnswers().split("");
	        ToggleGroup questionToggleGroup = toggleGroups.get(i);
	        int correctAnswerIndex = question.getCorrectAnswer() - 1;
	        RadioButton correctRadioButton = (RadioButton) questionToggleGroup.getToggles().get(correctAnswerIndex);
	        correctRadioButton.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px; -fx-text-fill: green;");
	        correctRadioButton.setText(correctRadioButton.getText() + " - Correct answer");
	        int selectedAnswerIndex = Integer.parseInt(answers[i]) - 1;
	        RadioButton selectedRadioButton = (RadioButton) questionToggleGroup.getToggles().get(selectedAnswerIndex);
	        selectedRadioButton.setSelected(true);
            if (selectedAnswerIndex != correctAnswerIndex) {  // Get the student's selected answer index
            	pointsLabels.get(i).setText("Points: 0"); 
            	pointsLabels.get(i).setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px; -fx-text-fill: red;");
            	selectedRadioButton.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px; -fx-text-fill: red;");
            	selectedRadioButton.setText(selectedRadioButton.getText() + " - Wrong answer");
            }  
	    }
	}
}