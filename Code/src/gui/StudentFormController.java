package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ClientUI;
import enteties.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class StudentFormController implements Initializable {
	private ArrayList<Question> questionList;
		
	@FXML
	private Label lblName;
	@FXML
	private Label lblSurname;
	@FXML
	private Label lblFaculty;
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtID;
	@FXML
	
	private Button btnclose=null;
	
	@FXML
	private ComboBox cmbFaculty;	
	
	ObservableList<String> list;
		
	public void loadStudent(ArrayList<Question> questionList) {
		for (int i = 0; i < questionList.size() ; i++) {
			
			System.out.println(questionList.get(i).toString());
		}
		this.questionList = questionList;
		/* 
		this.s=s1;
		this.txtID.setText(s.getId());
		this.txtName.setText(s.getPName());
		this.txtSurname.setText(s.getLName());		
		this.cmbFaculty.setValue(s.getFc().getFName());
		*/
	}
	
	// creating list of Faculties
	private void setFacultyComboBox() {
		ArrayList<String> al = new ArrayList<String>();	
		al.add("ME");
		al.add("IE");
		al.add("SE");

		list = FXCollections.observableArrayList(al);
		cmbFaculty.setItems(list);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		setFacultyComboBox();		
	}
	
	
	//Added: close button in student form
	//Functionality: go back to previous window
	public void getBackBtn(ActionEvent event) throws Exception {
		System.out.println("Exit Student Form");
		((Node)event.getSource()).getScene().getWindow().hide();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AcademicFrame.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
	}
	
	//Added: save button
	//Functionality: save the new data in the student form and send to server
	public void getSavebtn(ActionEvent event) throws Exception {
		//String sendData ="update "+ s.getId()+" "+txtID.getText()+" "+txtName.getText()+" "+txtSurname.getText()+" "+(String) cmbFaculty.getValue();
		String sendData = "UPDATE lab3.students SET FirstName = \"" +txtName.getText()+ "\", LastName = \"" +txtSurname.getText()+"\", Faculty = \""+(String) cmbFaculty.getValue() +"\" WHERE ID=\"";
		ClientUI.chat.accept(sendData);
        System.out.println("Update student");
	}

}
