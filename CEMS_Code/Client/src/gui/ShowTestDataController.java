package gui;

import java.util.ArrayList;

import client.ChatClient;
import enteties.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ShowTestDataController  extends AbstractController{
	private ArrayList<Test> arrShowTest;

	private ObservableList<Test> shoeTestTable;
    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<Test, String> codecol,coursnamecol,datecol,numTestcol,showcol,typecol;
    
    @FXML
    private TableView<Test> table;
    public ShowTestDataController() {
    	arrShowTest = new ArrayList<Test>();
    	//arrShowTest.add(new Test("1234","logic","25.3.23","7896","online"));
    	//arrShowTest.add(new Test("5678","infi","27.3.23","5942","manual"));
        //ArrayList<Question> convertedList = new ArrayList<>();
        for (Test Test : arrShowTest) {
        	//Test.setShowT();
        	//Test.getShowT().setOnMouseClicked(event -> {
        		try {
        			//showTestOpen(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	//});
        }
        //shoeTestTable = FXCollections.observableArrayList(arrShowTest);
        //for (Question q : arrQuestion) {
        //    arrdup.add(new Question(q.getID(), q.getSubjectNum(), q.getCourseName(), q.getQuestion(), q.getNumber(),q.getLecturereCreated()));
        //}
    }
    @FXML
    private void showTestOpen(MouseEvent event) throws Exception{
    	start("executeTestShowTest", "showTestData");
        System.out.println("Button clicked!");
    }
    
    @FXML
	protected void initialize() {
    	codecol.setCellValueFactory(new PropertyValueFactory<Test, String>("code"));
    	coursnamecol.setCellValueFactory(new PropertyValueFactory<Test, String>("course"));
    	datecol.setCellValueFactory(new PropertyValueFactory<Test, String>("date"));
		
    	numTestcol.setCellValueFactory(new PropertyValueFactory<Test, String>("id"));
    	showcol.setCellValueFactory(new PropertyValueFactory<Test, String>("showT"));
		
    	typecol.setCellValueFactory(new PropertyValueFactory<Test, String>("type"));

		table.setItems(shoeTestTable);
		
		table.refresh();
	}
}
