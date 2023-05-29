package julia;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateTestController{
	
	private ArrayList<Question> arrQuestion;

	private ObservableList<Question> QTable;
	
    @FXML
    private TableView<Question> table = new TableView<Question>();
    @FXML
    private TableColumn<Question, String> courseCol, idCol, lecturerCol, questionNumberCol, questionTextCol, subjectCol,checkBoxCol,pointsCol;
   
    @FXML
    private ComboBox<String> subjectComboBox;
    
    private ArrayList<Question> arrdup = new ArrayList<Question>();
    

    public CreateTestController() {
        
        arrQuestion = new ArrayList<Question>(Main.arr);
       
        ArrayList<QuestionWithAddition> convertedList = new ArrayList<>();
        for (Question question : Main.arr) {
        	QuestionWithAddition questionWithAddition = new QuestionWithAddition(question);
            convertedList.add(questionWithAddition);
        }
        QTable = FXCollections.observableArrayList(convertedList);
        for (Question q : arrQuestion) {
            arrdup.add(new Question(q.getID(), q.getSubjectNum(), q.getCourseName(), q.getQuestion(), q.getNumber(),q.getLecturereCreated()));
        }
    }

    
    @FXML
	protected void initialize() {
    	idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("ID"));
		subjectCol.setCellValueFactory(new PropertyValueFactory<Question, String>("SubjectNum"));
		courseCol.setCellValueFactory(new PropertyValueFactory<Question, String>("CourseName"));
		questionNumberCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Number"));
		lecturerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("LecturereCreated"));
		questionTextCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Question"));
		
		checkBoxCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Select"));
		pointsCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Points"));
		
		table.setItems(QTable);
		table.refresh();
		subjectComboBox.getItems().addAll("math", "software");
	}
   
    
    


}

