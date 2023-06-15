package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.CountDown;
import controllers.QuestionController;
import enteties.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class OnlineTestController extends AbstractController implements CountDown{
   
	private ArrayList<ToggleGroup> toggleGroups = new ArrayList<ToggleGroup>();
   
    private static QuestionController questionController = new QuestionController();
    ArrayList<Question> questions = new ArrayList<Question>();
    
  
    @FXML
    private VBox dataVbox;

    @FXML
    private Label timeLbl;
    
    @FXML
    private Label info1,info2,info3,info4,info5,info6;
    
    String code;
    public OnlineTestController() {
    	
    	if (ChatClient.lastCurrentScreen instanceof StartTestController) {
    		code = ((StartTestController)ChatClient.lastCurrentScreen).getCode();
    	}
    	Msg msg = questionController.getQuestionAndPointsByTestCode(Integer.parseInt(code));
    	sendMsg(msg);
    	System.out.println();
    	questions = msgReceived.convertData(Question.class); //ArrayList
    	 
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
                RadioButton answerRadioButton = new RadioButton(question.getAnswers()[j]);
                answerRadioButton.setToggleGroup(answerGroup);
                gridPane.add(answerRadioButton, 0, i + row); // Adjust the row and column indices as needed
            }
            toggleGroups.add(answerGroup);
        }
        dataVbox.getChildren().add(gridPane);
    }
 
    @FXML
    void submmitBtn(ActionEvent event) throws Exception {
    	for (int i = 0; i < toggleGroups.size(); i++) {
    		if (toggleGroups.get(i).getSelectedToggle() == null) { //checking if the user answer on the question
    			System.out.println("you didnt answer on question "+ (i+1) + " , do you still want to submmit ? y/n ....");
    		}
    		else {
    			System.out.println("for question "  + (i + 1)+" ,selected answer was (by name): " + (((RadioButton)toggleGroups.get(i).getSelectedToggle()).getText()));		
    		}
    	}
 
    	//super.backBtn(event);
    }

	@Override
	public void setTextCountdown(String s) {
		timeLbl.setText(s);
	}
   

}
