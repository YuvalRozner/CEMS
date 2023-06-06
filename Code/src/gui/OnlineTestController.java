package gui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;


public class OnlineTestController extends AbstractController {
    private ArrayList<ArrayList<String>> quizData; // ArrayList of ArrayList of Strings
    private ArrayList<ToggleGroup> toggleGroups = new ArrayList<ToggleGroup>();
    boolean flagForSubmmit = false; //added by mor

    @FXML
    private VBox dataVbox;

    public OnlineTestController() {
        // Initialize quiz data (replace with your own data)
        quizData = new ArrayList<>();
        ArrayList<String> question1 = new ArrayList<>();
        question1.add("1. What is the capital of France?");
        question1.add("Paris");
        question1.add("London");
        question1.add("Berlin");
        question1.add("Rome");
        quizData.add(question1);

        ArrayList<String> question2 = new ArrayList<>();
        question2.add("2. What is the largest planet in our solar system?");
        question2.add("Jupiter");
        question2.add("Saturn");
        question2.add("Mars");
        question2.add("Earth");
        quizData.add(question2);
        
        ArrayList<String> question3 = new ArrayList<>();
        question3.add("3. how much time was six day war?");
        question3.add("60");
        question3.add("six");
        question3.add("it never heppen");
        question3.add("i dont believe in wars");
        quizData.add(question3);
        
        ArrayList<String> question4 = new ArrayList<>();
        question4.add("4. who has the best project??");
        question4.add("G10");
        question4.add("G10");
        question4.add("G10");
        question4.add("All the answers are correct");
        quizData.add(question4);

    }

    @FXML
    protected void initialize() {
    	for (ArrayList<String> questionData : quizData) {
            Label questionLabel = new Label(questionData.get(0));
            dataVbox.getChildren().add(questionLabel);
            ToggleGroup answerGroup = new ToggleGroup();
            for (int i = 1; i < questionData.size(); i++) {
                RadioButton answerRadioButton = new RadioButton(questionData.get(i));
                answerRadioButton.setToggleGroup(answerGroup);
                dataVbox.getChildren().add(answerRadioButton);
            }
            toggleGroups.add(answerGroup);
        }
    }
 
    @FXML
    void submmitBtn(ActionEvent event) throws Exception {
    	for (int i = 0; i < toggleGroups.size(); i++) {
    		if (toggleGroups.get(i).getSelectedToggle() == null) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    	        alert.setHeaderText("you didnt selected question " + (i+1));
    	        alert.show();
    	        flagForSubmmit = true;
    		}
    		else {System.out.println("selected answer was: " + (toggleGroups.get(i).getSelectedToggle().toString())); }
    	}
    	flagForSubmmit = false;
    	super.backBtn(event);
    }


}
