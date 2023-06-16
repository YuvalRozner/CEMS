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
import notifications.NotificationAlertsController;


public class OnlineTestController extends AbstractController implements CountDown, Testing{
   
	private ArrayList<ToggleGroup> toggleGroups = new ArrayList<ToggleGroup>();
   
    private static QuestionController questionController = new QuestionController();
    ArrayList<Question> questions = new ArrayList<Question>();
    private NotificationAlertsController alert= new NotificationAlertsController();
	private TestController testController =new TestController();
	private TestToExecuteController testToExecuteController = new TestToExecuteController();
	private StudentTestController studentTestController =new StudentTestController();
    
  
    @FXML
    private VBox dataVbox;

    @FXML
    private Label timeLbl;
    
    @FXML
    private Label info1,info2,info3,info4,info5,info6;
    
    private String code="";
	private Integer duration=0;
	private String lock="";
	private TestToExecute numbersOfStudent; 
	private Integer grade=0;
	private String answers="";
	private Msg msg;
	private Integer timeOfStudent = 100; //time fiktivy //dor
	private String flagEndOrMiddle;
    
    public OnlineTestController() {
    	
    	if (ChatClient.lastCurrentScreen instanceof StartTestController) {
    		code = ((StartTestController)ChatClient.lastCurrentScreen).getCode();
    	}
    	Msg msg = questionController.getQuestionAndPointsByTestCode(Integer.parseInt(code));
    	sendMsg(msg);
    	System.out.println();
    	questions = msgReceived.convertData(Question.class); //ArrayList    	 
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
    @FXML
    protected void initialize() {
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
            pointsLabel.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14px;");
            gridPane.add(questionLabel, 0, i + row);
            gridPane.add(pointsLabel,1, i + row); 
            
            //setting the 4 radiobutton in a toggle group and set each answer
            ToggleGroup answerGroup = new ToggleGroup();
            for (int j = 0; j < 4; j++) {
            	row++;
            	NumberRadioButton answerRadioButton = new NumberRadioButton(question.getAnswers()[j],j+1);
                answerRadioButton.setToggleGroup(answerGroup);
                gridPane.add(answerRadioButton, 0, i + row); // Adjust the row and column indices as needed
            }
            toggleGroups.add(answerGroup);
        }
        dataVbox.getChildren().add(gridPane);
    }
 
    @FXML
    void submmitBtn(ActionEvent event) throws Exception {
		
		alert.setOnCancelAction(new Runnable() {	@Override public void run() {
			((Node)event.getSource()).getScene().getWindow().hide();
			ChatClient.getScreen("onlineTest").display();
			getPrimaryStage().setTitle("onlineTest");
		}});
		
		
		alert.setOnOkAction(new Runnable() {	@Override public void run() {
			msg=testController.getDurationByCode(code);
			sendMsg(msg);
			duration=msgReceived.convertData(Test.class).get(0).getDuration();

			
			if(duration<timeOfStudent) {
				timeOfStudentIsOverLoad();
				checkIfStudentIsTheLastOne();
				updateAverageAndMedian();
			}
			else if (checkIfTestIsLock().equals("true")){
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
		msg=studentTestController.selectAllstudentBySpecificCodeTest(code);
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
		msg=testToExecuteController.updateMedianAndAverage(code,average,median);
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
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
		////////update data
		msg = testToExecuteController.updateNumberOfStudenByOne(1,code,"finish");
		sendMsg(msg);
		calculateGrade();
		getAnswers();
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,answers,grade,ChatClient.user.getId() ,code);
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code,grade,1);
		sendMsg(msg);

	}
	public void testIsLockCantSubmmit() {
		msg = testToExecuteController.updateNumberOfStudenByOne(1,Integer.toString(StartTestController.getTestToExecute().getTestCode()),"cantSubmit");
		sendMsg(msg);
		alert.showErrorAlert("The test is locked!\n You will not be able to submit the test!");
		if(flagEndOrMiddle.equals("End")) {
			grade=0;
			msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"00000",grade,ChatClient.user.getId() ,code);
		}
		else {
			calculateGrade();
			getAnswers();
			msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,answers,grade,ChatClient.user.getId() ,code);
		}
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code,0,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
	}
	
	/**
	 * check if it lock
	 * @return
	 */
    public String checkIfTestIsLock() {
    	msg=testToExecuteController.checkIfTheTestIsLock(code);
		sendMsg(msg);
		lock=msgReceived.convertData(TestToExecute.class).get(0).getLock();
		return lock;
    }
	/**
	 * Handles the case when the student has exceeded the allowed time for the test.
	 *
	 * @param timeOfStudent The time taken by the student to complete the test (in minutes).
	 */
	public void timeOfStudentIsOverLoad() {
		alert.showErrorAlert("You have exceeded the allowed time!");
		msg = testToExecuteController.updateNumberOfStudenByOne(1,code,"cantSubmit");
		sendMsg(msg);
		msg=studentTestController.InsertAnswersAndGradeManual("false",timeOfStudent,"00000",0,ChatClient.user.getId() ,code);
		sendMsg(msg);
		msg=testToExecuteController.insertDistributionByCode(code,grade,1);
		sendMsg(msg);
		try {start("studentMenu", "login");} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void setTextCountdown(String s) {
		timeLbl.setText(s);
	}
   
	@Override
	public void testGotManualyLockedByLecturer(String testCode) {
		if(!testCode.equals(code)) return;
		alert.showErrorAlert("Sorry, but the test got locked by your lecturer..");
		flagEndOrMiddle="Middle";
		testIsLockCantSubmmit();
		updateAverageAndMedian();
	}
}
