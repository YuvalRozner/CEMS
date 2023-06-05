package gui;

import java.util.ArrayList;

import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import enteties.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class LecturerTestViewController extends AbstractController {
    
    @FXML
    private TableColumn<Test, String> courseCol, dateCol, idCol, selectCol;
    
    @FXML
    private TableView<Test> table;
    
    private ObservableList<Test> testsTable;
    
    private ArrayList<Test> arrdup = new ArrayList<>();
    
    private ToggleGroup toggleGroup; // ToggleGroup instance
    
    public LecturerTestViewController() {
        //arrdup = new ArrayList<Test>(Main.tests);
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("*");
    	msg.setFrom("test");
    	sendMsg(msg);
    	arrdup = new ArrayList<Test>(msgReceived.convertData(Test.class));
        testsTable = FXCollections.observableArrayList(arrdup);
        toggleGroup = new ToggleGroup(); // Initialize the ToggleGroup
        for (Test test : testsTable) {
            test.getRadioButton().setToggleGroup(toggleGroup);
        }
    }
    
    @FXML
    protected void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<Test, String>("id"));
        courseCol.setCellValueFactory(new PropertyValueFactory<Test, String>("Course"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Test, String>("Date"));
        selectCol.setCellValueFactory(new PropertyValueFactory<Test, String>("radioButton"));
        
        table.setItems(testsTable);
        table.refresh();
    }
	
	public void showStatistics(ActionEvent event) throws Exception {
		new LecturerStaticsReportController().start("lecturerStaticsReport", "lecturerTestView");
	}
}
