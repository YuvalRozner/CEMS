package gui;

import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import controllers.JDBC.DB_controller;
import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import enteties.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class QuestionTableController extends AbstractController{
	
	private ArrayList<Question> arrQuestion;

	private ObservableList<Question> QTable;
	
    @FXML
    private TableView<Question> table = new TableView<Question>();

    @FXML
    private TableColumn<Question, String> courseCol, idCol, lecturerCol, questionNumberCol, questionTextCol, subjectCol;
    private ArrayList<Question> arrdup = new ArrayList<Question>();
	
	
	public QuestionTableController() {
		arrQuestion = new ArrayList<Question>(ChatClient.questionList);
		QTable = FXCollections.observableArrayList(arrQuestion);
		for (Question q : arrQuestion) {
			arrdup.add(new Question(q.getID(), q.getSubjectNum(), q.getCourseName(), q.getQuestion(), q.getNumber(),q.getLecturereCreated()));
		}
	}

    
    @FXML
    void backBtn(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.screens.putIfAbsent("Menu", new MenuController());
		ChatClient.getScreen("Menu").display();
    }

    @FXML
    void saveBtn(ActionEvent event) {
        ArrayList<Msg> UpdateQueries = DB_controller.updateQuestions1(table.getItems(), arrdup);
        Msg msg = new Msg(MsgType.manyMessages);
        msg.setMsgLst(UpdateQueries);
        sendMsg(msg);
    }
    
    @FXML
	protected void initialize() {
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("ID"));
		subjectCol.setCellValueFactory(new PropertyValueFactory<Question, String>("SubjectNum"));
		courseCol.setCellValueFactory(new PropertyValueFactory<Question, String>("CourseName"));
		
		questionNumberCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Number"));
		lecturerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("LecturereCreated"));
		
		questionTextCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Question"));
        questionTextCol.setCellFactory(TextFieldTableCell.forTableColumn());
        questionTextCol.setEditable(true); 
       
		table.setEditable(true); // Set the table editable
		table.setItems(QTable);
		
		table.refresh();
	}
    @FXML
    void onEditCommitQuestionTextCol(TableColumn.CellEditEvent<Question, String> event) {
    	 String newValue = event.getNewValue();
         Question question = event.getTableView().getItems().get(event.getTablePosition().getRow());
         question.setQuestion(newValue);
    }
 
    //shadow
    
    @FXML
    void onMouseEnterdBackBtn(MouseEvent event) {
    	 Button backbtn = (Button) event.getSource();
         DropShadow shadow = new DropShadow();
         backbtn.setEffect(shadow);
    }

    @FXML
    void onMouseEnteredSaveBtn(MouseEvent event) {
    	 Button savebtn = (Button) event.getSource();
         DropShadow shadow = new DropShadow();
         savebtn.setEffect(shadow);
    }
    
    @FXML
    void onMouseExitedBackBtn(MouseEvent event) {
        Button backbtn = (Button) event.getSource();
        backbtn.setEffect(null);
    }
    
    @FXML
    void onMouseExitedSaveBtn(MouseEvent event) {
        Button backbtn = (Button) event.getSource();
        backbtn.setEffect(null);
    }

}