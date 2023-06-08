package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void showReport(ActionEvent event) throws Exception {
    	start("lecturerStaticsReport", "chooseReportType");

    }

}