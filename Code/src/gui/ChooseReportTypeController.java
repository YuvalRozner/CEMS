package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;

public class ChooseReportTypeController extends AbstractController{

    @FXML
    private Button back;

    @FXML
    private ToggleGroup chooseTypeOfReport;

    @FXML
    private ComboBox<String> comboBoxNames;

    @FXML
    private Button report;

    @FXML
    void backBtn(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("HodMenu").display();
    }

    @FXML
    void showReport(ActionEvent event) throws Exception {
    	ChatClient.screens.putIfAbsent("LecturerStaticsReport", new LecturerStaticsReportController());
		ChatClient.getScreen("LecturerStaticsReport").start("LecturerStaticsReport");

    }

}