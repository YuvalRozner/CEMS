package gui;

import java.util.ArrayList;
import java.util.Collections;

import JDBC.Msg;
import client.ChatClient;
import controllers.CountDown;
import controllers.QuestionController;
import controllers.StudentTestController;
import controllers.TestController;
import controllers.TestToExecuteController;
import controllers.TimeController;
import enteties.Question;
import enteties.StudentTest;
import enteties.Test;
import enteties.TestToExecute;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import notifications.NotificationAlertsController;


public class OnlineTestController extends AbstractController implements CountDown, Testing{
   
	private ArrayList<ToggleGroup> toggleGroups = new ArrayList<ToggleGroup>();
   
    private static QuestionController questionController = new QuestionController();
    ArrayList<Question> questions = new ArrayList<Question>();
    private NotificationAlertsController alert= new NotificationAlertsController();
	private TestController testController =new TestController();
	private TestToExecuteController testToExecuteController = new TestToExecuteController();
	private StudentTestController studentTestController =new StudentTestController();
    
  
	/**
     * The container for displaying the test data.
     */
    @FXML
    private VBox dataVbox;
    
    /**
     * Labels for displaying test information.
     */
    @FXML
    private Label info1, info2, info3, info4, info5, info6, info7,timeLabel;
    
    /**
     * Container for displaying instructions for students/lecturers.
     */
    @FXML
    private TextFlow instructionsForStudentTextFlow,instructionsForLecturerTextFlow;
    
    /**
     * Labels for displaying points per question.
     */
    ArrayList<Label> pointsLabels = new ArrayList<>();
    
    GridPane gridPane;
    

    private Integer code;
	private Integer duration=0;
	private String lock="";
	private TestToExecute numbersOfStudent; 
	private Integer grade=0;
	private String answers="";
	private Msg msg;
	private Integer timeOfStudent = 100; //time fiktivy //dor
	private String flagEndOrMiddle;
    
	private TestToExecute testToExecute;
	
	TimeController timeController;
    public OnlineTestController() {
    	
    	if (ChatClient.lastCurrentScreen instanceof StartTestController) {
    		testToExecute = ((StartTestController) ChatClient.lastCurrentScreen).getTestToExecuteToShow();
    		code = testToExecute.getTestCode();;
    	}
    	Msg msg = questionController.getQuestionAndPointsByTestCode(code);
    	sendMsg(msg);
    	
    	System.out.println();
    	questions = msgReceived.convertData(Question.class); //ArrayList    	 
    	//timeController = new TimeController(testToExecute.getTest().getDuration(),this);
    	timeController = new TimeController(1,this);
    }
   
    /**
     * Initializes the controller and sets up the display of the test information and questions.
     * This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    protected void initialize() {
        setInfo();

        int questionCounter = 1;
        gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        int row = 0;

        // Iterate over the list of questions and create the necessary UI elements
        for (int i = 0; i < questions.size(); i++) {
	    	gridPane.add(new Label(), 0, i + row);
	    	row++;
            Question question = questions.get(i); 
            // Create and configure the question label
            Label questionLabel = new Label(questionCounter + ". " + question.getQuestion());
            Label instuctionsQuestionLabel;
            if (question.getInstructions() != null)
                instuctionsQuestionLabel = new Label("Instruction for question: " + question.getInstructions());
            else
                instuctionsQuestionLabel = new Label("");
            questionCounter++;
            questionLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-weight: bold; -fx-font-size: 14px;");

            // Create and configure the points label
            Label pointsLabel = new Label("Points: " + question.getPoints());
            pointsLabels.add(pointsLabel); // Save the points label for later changes
            pointsLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px;");

            // Add the question label, instruction label, and points label to the grid pane
            gridPane.add(instuctionsQuestionLabel, 0, i + row);
            row++;
            gridPane.add(questionLabel, 0, i + row);
            gridPane.add(pointsLabel, 1, i + row);

            // Set up the 4 radio buttons in a toggle group and set each answer
            ToggleGroup answerGroup = new ToggleGroup();
            for (int j = 0; j < 4; j++) {
                row++;
                NumberRadioButton answerRadioButton = new NumberRadioButton(question.getAnswers()[j],(j+1));
                answerRadioButton.setToggleGroup(answerGroup);
                gridPane.add(answerRadioButton, 0, i + row);
            }
           
            toggleGroups.add(answerGroup);
        }

        // Add the grid pane to the data VBox
        dataVbox.getChildren().add(gridPane);
        timeController.startTimer();
        
    }

 
    /**
     * Sets the information of the test and updates the display.
     * This method populates various labels and text flows with the relevant information
     * about the test and its instructions for students and lecturers, based on the current screen state.
     * It also adjusts the formatting and styling of the displayed elements.
     */
    public void setInfo() {
        // Set the info of the test
        Text textForTextFlow = null;
        info1.setText("Test Code: " + testToExecute.getTestCode());
        info2.setText("Course: " + testToExecute.getTest().getCourse().getName());
        info3.setText("Duration:" + testToExecute.getTest().getDuration() + " minutes");
        info4.setText("Date: " + testToExecute.getDate());
        info5.setText("Student Name:" + ChatClient.user.getName());
        // Set instructions for students
        if (testToExecute.getTest().getInstructionsForStudent() != null) {
            textForTextFlow = new Text("Instructions For Student: " + testToExecute.getTest().getInstructionsForStudent());
            textForTextFlow.wrappingWidthProperty().bind(instructionsForStudentTextFlow.widthProperty());
            textForTextFlow.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
            instructionsForStudentTextFlow.getChildren().addAll(textForTextFlow);
        }
    }

 
    @FXML
    void submitBtn(ActionEvent event) throws Exception {	
    	alert.setOnCancelAction(new Runnable() {	@Override public void run() {
		}});
		alert.setOnOkAction(new Runnable() {	@Override public void run() {
	
			duration = testToExecute.getTest().getDuration();
			
			if (testToExecute.getLock().equals("true")){
				flagEndOrMiddle="End";
				flagEndOrMiddle="Middle";
				testIsLockCantSubmmit();	
				updateAverageAndMedian();
			}
			else {
				testIsSubmit(timeOfStudent);
				checkIfStudentIsTheLastOne();
				updateAverageAndMedian();
			}
			try {start("studentMenu", "login");} catch (Exception e) {}}});
		alert.showConfirmationAlert(ChatClient.user.getName()+" Are you sure ?","After clicking the OK button, the submission is final and there is no option to change it");
    
    }
    
    
   
    
    public void checkIfStudentIsTheLastOne() {
		msg=testToExecuteController.checkIfTheStudentIsLast(StartTestController.getTestToExecute().getTestCode());
		sendMsg(msg);
		numbersOfStudent=msgReceived.convertData(TestToExecute.class).get(0);
		Integer needToLock=numbersOfStudent.getNumberOfStudentsStarted()-numbersOfStudent.getNumberOfStudentsFinished()-numbersOfStudent.getNumberOfStudents();
		if (needToLock==0) {
			msg=testToExecuteController.getMsgToLockTest(StartTestController.getTestToExecute());
			sendMsg(msg);
		}
    	
    }
	public void updateAverageAndMedian(){
		double average=0 , median=0;
		msg=studentTestController.selectAllstudentBySpecificCodeTest(code.toString());
		sendMsg(msg);
		ArrayList<StudentTest> listOfStudent =msgReceived.convertData(StudentTest.class);
		ArrayList<Integer> listOfGrades=new ArrayList<Integer>();
		for(StudentTest student : listOfStudent) {
			average+=student.getGrade();
			listOfGrades.add(student.getGrade());
		}
		average=average/listOfGrades.size();
		Collections.sort(listOfGrades);
		median=listOfGrades.get(listOfGrades.size()/2);
		msg=testToExecuteController.updateMedianAndAverage(code.toString(),average,median);
		sendMsg(msg);
		
	}
	
    public void getAnswers() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < toggleGroups.size(); i++) {
    		if (toggleGroups.get(i).getSelectedToggle() == null) { //checking if the user answer on the question
    			sb.append("0");
    		}
    		else {
    			sb.append((((NumberRadioButton)toggleGroups.get(i).getSelectedToggle()).getNumber()));
    		}
    	}
		answers=sb.toString();
    	
    }
    public void calculateGrade() {
		for (int i = 0; i < toggleGroups.size(); i++) {
    		if (toggleGroups.get(i).getSelectedToggle() != null) { //checking if the user answer on the question
    			if (questions.get(i).getCorrectAnswer()==(((NumberRadioButton)toggleGroups.get(i).getSelectedToggle()).getNumber())) {
    				grade+=questions.get(i).getPoints();
    				System.out.println(grade);
    			}
    		}
		}	
    }
    
    
	public void testIsSubmit(Integer timeOfStudent) {
		alert.showInformationAlert("The test was successfully submitted!");
		
		////////update data
		msg = testToExecuteController.updateNumberOfStudenByOne(1,code.toString(),"finish");
		sendMsg(msg);
		calculateGrade();
		getAnswers();
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,answers,grade,ChatClient.user.getId() ,code.toString());
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code.toString(),grade,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}

	}
	public void testIsLockCantSubmmit() {
		msg = testToExecuteController.updateNumberOfStudenByOne(1,Integer.toString(StartTestController.getTestToExecute().getTestCode()),"cantSubmit");
		sendMsg(msg);
		alert.showErrorAlert("The test is locked!\n You will not be able to submit the test!");
		if(flagEndOrMiddle.equals("End")) {
			grade=0;
			msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"00000",grade,ChatClient.user.getId() ,code.toString());
		}
		else {
			calculateGrade();
			getAnswers();
			msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,answers,grade,ChatClient.user.getId() ,code.toString());
		}
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code.toString(),0,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
	}
	
	
	/**
	 * Handles the case when the student has exceeded the allowed time for the test.
	 *
	 * @param timeOfStudent The time taken by the student to complete the test (in minutes).
	 */
	public void timeOfStudentIsOverLoad() {
		alert.showErrorAlert("You have exceeded the allowed time!");
		msg = testToExecuteController.updateNumberOfStudenByOne(1,code.toString(),"cantSubmit");
		sendMsg(msg);
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"00000",0,ChatClient.user.getId() ,code.toString());
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code.toString(),grade,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void setTextCountdown(String s) {
		timeLabel.setText(s);
	}
	@Override
	public void endOfTime() {
		System.out.println("the time is up my freind");
		/*
		timeOfStudentIsOverLoad();
		checkIfStudentIsTheLastOne();
		updateAverageAndMedian();
		*/
	}
	@Override
	public void testGotManualyLockedByLecturer(String testCode) {
		if(!testCode.equals(code)) return;
		alert.showErrorAlert("Sorry, but the test got locked by your lecturer..");
		flagEndOrMiddle="Middle";
		testIsLockCantSubmmit();
		updateAverageAndMedian();
	}
	 class NumberRadioButton extends RadioButton {
	        private int number;

	        public NumberRadioButton(String text, int number) {
	            super(text);
	            this.number = number;
	        }

	        public int getNumber() {
	            return number;
	        }
	    }
	
	
}
