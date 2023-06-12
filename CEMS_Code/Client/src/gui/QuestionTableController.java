package gui;

import java.util.ArrayList;

import JDBC.DB_controller;
import client.ChatClient;
import client.ClientUI;
import enteties.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
		
		//arrQuestion = new ArrayList<Question>(ChatClient.questionList);
		arrQuestion = new ArrayList<Question>();
		//arrQuestion.add(new Question("1234", "11200", "logic","how much is 1+1", 22,"ilena"));
		//arrQuestion.add(new Question("5678", "12645", "infi","how much is 6+8", 23,"dan"));
		QTable = FXCollections.observableArrayList(arrQuestion);
		for (Question Q : arrQuestion) {
        	Q.setNewShowQ();
        	Q.getShowQ().setOnMouseClicked(event -> {
        		try {
        			showQuestionOpen(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	});
        }
		//for (Question q : arrQuestion) {
		//	arrdup.add(new Question(q.getID(), q.getSubjectNum(), q.getCourseName(), q.getQuestion(), q.getNumber(),q.getLecturereCreated()));
		//}
		
	}
	
	@FXML
    private void showQuestionOpen(MouseEvent event) throws Exception{
		start("showQuestion", "questionTable");
        System.out.println("Button clicked!");
    }


   /*@FXML
    void saveBtn(ActionEvent event) {
        ArrayList<String> UpdateQueries = DB_controller.updateQuestions(table.getItems(), arrdup);
        for (String query : UpdateQueries) {
            System.out.println("Send to server update query -> " + query);
            ClientUI.chat.accept(query);
        }
    } */
    
    @FXML
	protected void initialize() {
    	idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("ID"));
		subjectCol.setCellValueFactory(new PropertyValueFactory<Question, String>("SubjectNum"));
		courseCol.setCellValueFactory(new PropertyValueFactory<Question, String>("CourseName"));
		
		questionNumberCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Number"));
		lecturerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("LecturereCreated"));
		
		questionTextCol.setCellValueFactory(new PropertyValueFactory<Question, String>("ShowQ"));
        //questionTextCol.setCellFactory(TextFieldTableCell.forTableColumn());
        //questionTextCol.setEditable(true); 
       
		//table.setEditable(true); // Set the table editable
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