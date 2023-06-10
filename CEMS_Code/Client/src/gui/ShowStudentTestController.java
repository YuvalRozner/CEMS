package gui;

import java.util.ArrayList;

import client.ChatClient;
import enteties.StudentTest;
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
    private Label info1,info2,info3,info4;

	
	private ArrayList<ArrayList<String>> quizData; // ArrayList of ArrayList of Strings
    private ArrayList<ToggleGroup> toggleGroups = new ArrayList<ToggleGroup>();

    ArrayList<Integer> correctAnswerIndices = new ArrayList<>();
    ArrayList<Integer> points = new ArrayList<>();
    ArrayList<Label> pointsLabels =new ArrayList<>();
    
    StudentTest studentTest;// from approveGradeController
    TestToExecute testToExecute;// from ExecuteTestController
    String testName; //need to bring test name ....
    
    public ShowStudentTestController() {
    	
        // Initialize quiz data (replace with your own data) fake data
    	testName = "logic 23/7 111"; //need to bring in diffrent way, i have the test code...
    	//points for each questions ,
    	points.add(25); 
    	points.add(25);
    	points.add(25);
    	points.add(25);
    	
    	try {
    		studentTest =  ((ApproveGradeController)ChatClient.screens.get("approveGrade")).getStudentTestToShow(); //get the studenttest to show from the last screen
    		System.out.println("i am print from ShowStudentTestController and now i got  " +studentTest);
        	System.out.println("now i can extract all the data i need here insted of using fake data..........");
        
    	}catch(Throwable t) {
    		System.out.println("it wasnt ApproveGradeController");
    	}
		
    	try {
		testToExecute =  ((ExecuteTestController)ChatClient.screens.get("executeTest")).getTestToShow(); //get the studenttest to show from the last screen
		System.out.println("i am print from ShowStudentTestController and now i got  " +testToExecute);
    	System.out.println("now i can extract all the data i need here insted of using fake data..........");
    	}catch(Throwable t) {
    		System.out.println("it wasnt ExecuteTestController");
    	}
    	
    	
    		//the correct answers
    	correctAnswerIndices.add(0);
    	correctAnswerIndices.add(0);
    	correctAnswerIndices.add(1);
    	correctAnswerIndices.add(3);
  
        quizData = new ArrayList<>();
        ArrayList<String> question1 = new ArrayList<>();
        question1.add("What is the capital of Israel?");
        question1.add("Jerusalem");
        question1.add("London");
        question1.add("Berlin");
        question1.add("Rome");
        quizData.add(question1);

        ArrayList<String> question2 = new ArrayList<>();
        question2.add("Iphone or samsung?");
        question2.add("iphone");
        question2.add("samsung");
        question2.add("answer 2 is the correct");
        question2.add("answer 3 is the correct");
        quizData.add(question2);
        
        ArrayList<String> question3 = new ArrayList<>();
        question3.add("how much time was six day war?");
        question3.add("60");
        question3.add("six");
        question3.add("it never heppen");
        question3.add("i dont believe in wars");
        quizData.add(question3);
        
        ArrayList<String> question4 = new ArrayList<>();
        question4.add("who has the best project??");
        question4.add("G10");
        question4.add("G10");
        question4.add("G10");
        question4.add("All the answers are correct");
        quizData.add(question4);

    }

    @FXML
    protected void initialize() {
    	
    	
    	
    	//set the info of the test
    	if (studentTest!= null) {
    		info1.setText("Test:" + testName);
    		info2.setText("Student Name:" + studentTest.getStudentName());
    		info3.setText("Grade:" + String.valueOf(studentTest.getGrade()));
    	}
    	else {
    		info1.setText("Test:" + testName);
    		info2.setText("Duration:" +  testToExecute.getTest().getDuration());
    		info3.setText("Instuction for lecturer:" + testToExecute.getTest().getInstructionsForLecturer());
    		info4.setText("Instuction for student:" + testToExecute.getTest().getInstructionsForStudent());
    	}
    	
    	int questionCounter = 1;
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        int row = 0;
        for (int i = 0; i < quizData.size(); i++) {
            ArrayList<String> questionData = quizData.get(i); //get a question and 4 answers
            
            Label questionLabel = new Label(questionCounter + ". " + questionData.get(0)); //set the question label
            questionCounter++;
            questionLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-weight: bold; -fx-font-size: 14px;");
            Label pointsLabel = new Label("Points: " + points.get(i)); //set the points label
            pointsLabels.add(pointsLabel); //save the pointslabel for later changes
            pointsLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px;");
            gridPane.add(questionLabel, 0, i + row);
            gridPane.add(pointsLabel,1, i + row); 
            
            //setting the 4 radiobutton in a toggle group and set each answer
            ToggleGroup answerGroup = new ToggleGroup();
            for (int j = 1; j < questionData.size(); j++) {
            	row++;
                RadioButton answerRadioButton = new RadioButton(questionData.get(j));
                answerRadioButton.setMouseTransparent(true);
                answerRadioButton.setToggleGroup(answerGroup);
                gridPane.add(answerRadioButton, 0, i + row); // Adjust the row and column indices as needed
            }
            toggleGroups.add(answerGroup);
        }
        dataVbox.getChildren().add(gridPane);
        if (studentTest!= null) {
        	showCorrectAnswersAndStudentAnswer();
        	}
        else {showCorrectAnswers();};
        
        

    }
 

    public void showCorrectAnswers() {
	    for (int i = 0; i < toggleGroups.size(); i++) {
	        ToggleGroup toggleGroup = toggleGroups.get(i);
	        int correctAnswerIndex = correctAnswerIndices.get(i);
	        RadioButton correctRadioButton = (RadioButton) toggleGroup.getToggles().get(correctAnswerIndex);
	        correctRadioButton.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
	        correctRadioButton.setText(correctRadioButton.getText() + " - Correct answer"); 
	    }
	}
	
	public void showCorrectAnswersAndStudentAnswer() {
	    for (int i = 0; i < toggleGroups.size(); i++) {
	    	System.out.println(studentTest.getAnswers());
	    	
	    	String[] answers = studentTest.getAnswers().split(",");
	        ToggleGroup toggleGroup = toggleGroups.get(i);
	        int correctAnswerIndex = correctAnswerIndices.get(i);
	        RadioButton correctRadioButton = (RadioButton) toggleGroup.getToggles().get(correctAnswerIndex);
	        System.out.println(i + " correctAnswerIndex: " + correctAnswerIndex);
	        // Set the style or text to indicate the correct answer
	        
	        correctRadioButton.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
	        correctRadioButton.setText(correctRadioButton.getText() + " - Correct answer");
	        // Get the student's selected answer index
	        int selectedAnswerIndex = Integer.parseInt(answers[i]) - 1;
	        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getToggles().get(selectedAnswerIndex);
	        selectedRadioButton.setSelected(true);
           
            if (selectedAnswerIndex != correctAnswerIndex) {
            	pointsLabels.get(i).setText("Points: 0");
            	pointsLabels.get(i).setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            	
            	
            	selectedRadioButton.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            	selectedRadioButton.setText(selectedRadioButton.getText() + " - Wrong answer");
            }
            
           
	    }
	}
}

	

   


