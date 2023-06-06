package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentMenuController extends AbstractController {

	@FXML
	private Label welcomeLbl;

	@FXML
	void logOut(ActionEvent event) throws Exception {
		super.logout();
		super.backBtn(event);
	}

	@FXML
	void showGrade(ActionEvent event) throws Exception {
		start("showGrade", "studentMenu");
	}

	@FXML
	void startTest(ActionEvent event) throws Exception {
		start("startTest", "studentMenu");
	}

	public void setWelcome(String name) {
		welcomeLbl.setText(name);
	}
}