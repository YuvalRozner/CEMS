package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController {

    @FXML
    private TextField DBNameTxt;

    @FXML
    private TextField DBUsernameTxt;

    @FXML
    private Button btnConnect;

    @FXML
    private Button btnDisconnect;

    @FXML
    private Button btnExit;

    @FXML
    private TableColumn<?, ?> clientHostName;

    @FXML
    private TableColumn<?, ?> clientIp;

    @FXML
    private TableColumn<?, ?> clientStatus;

    @FXML
    private TextArea console;

    @FXML
    private Button minimizeBtn;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField portxt;

    @FXML
    private TextField serverIdTxt;

    @FXML
    void connect(ActionEvent event) {

    }

    @FXML
    void disconnect(ActionEvent event) {

    }

    @FXML
    void getExitBtn(ActionEvent event) {

    }

    @FXML
    void minimize(ActionEvent event) {

    }

}

