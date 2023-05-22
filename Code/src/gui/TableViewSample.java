package gui;

import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import controllers.JDBC.DB_controller;
import enteties.Question;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableViewSample extends Application {

	private ArrayList<Question> arrdup = new ArrayList<Question>();
	private ObservableList<Question> observableList;
	private TableView<Question> table = new TableView<Question>();

	public TableViewSample(ArrayList<Question> arr) {
		for (Question q : arr) {
			arrdup.add(new Question(q.getID(), q.getSubjectNum(), q.getCourseName(), q.getQuestion(), q.getNumber(),q.getLecturereCreated()));
		}
		this.observableList = FXCollections.observableArrayList(arr);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(700);
		stage.setHeight(600);

		final Label label = new Label("Questions List");
		label.setFont(new Font("Arial", 20));

		Button backbtn = new Button("Back");
		Button savebtn = new Button("Save");

		table.setEditable(true);

		Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};

		TableColumn idCol = new TableColumn("ID");
		idCol.setMinWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<Question, String>("ID"));

		TableColumn subjectCol = new TableColumn("Subject");
		subjectCol.setMinWidth(100);
		subjectCol.setCellValueFactory(new PropertyValueFactory<Question, String>("subjectNum"));

		TableColumn courseCol = new TableColumn("Course");
		courseCol.setMinWidth(100);
		courseCol.setCellValueFactory(new PropertyValueFactory<Question, String>("courseName"));

		TableColumn questionTextCol = new TableColumn("Question Text");
		questionTextCol.setMinWidth(100);
		questionTextCol.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
		questionTextCol.setCellFactory(cellFactory);
		questionTextCol.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setQuestion(t.getNewValue());

			}
		});
		/// make the edit goes in enter pressing -lior-
		table.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) { // check if the key pressed is Enter
				// request focus on another control to remove focus from the text field
				table.requestFocus();
			}
		});

		TableColumn questionNumberCol = new TableColumn("Question Number");
		questionNumberCol.setMinWidth(150);
		questionNumberCol.setCellValueFactory(new PropertyValueFactory<Question, String>("number"));
		questionNumberCol.setCellFactory(cellFactory);
		questionNumberCol.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNumber(t.getNewValue());
			}
		});

		TableColumn lecturerCol = new TableColumn("Lecturer");
		lecturerCol.setMinWidth(100);
		lecturerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("lecturereCreated"));
		
		table.setItems(observableList);
		table.getColumns().addAll(idCol, subjectCol, courseCol, questionTextCol, questionNumberCol, lecturerCol);
		
		
		DropShadow shadow = new DropShadow();
		// Adding the shadow when the mouse cursor is on
		backbtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backbtn.setEffect(shadow);
			}
		});
		// Removing the shadow when the mouse cursor is off
		backbtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				backbtn.setEffect(null);
			}
		});

		// Adding the shadow when the mouse cursor is on
		savebtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				savebtn.setEffect(shadow);
			}
		});
		// Removing the shadow when the mouse cursor is off
		savebtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				savebtn.setEffect(null);
			}
		});

		// organize all element in location
		final Pane pane = new Pane();
		pane.getChildren().addAll(backbtn, label, savebtn, table);

		table.setLayoutX(15);
		table.setLayoutY(100.0);

		label.setLayoutX(270);
		label.setLayoutY(15);

		backbtn.setLayoutX(15);
		backbtn.setLayoutY(15);

		savebtn.setLayoutX(15);
		savebtn.setLayoutY(520);

		((Group) scene.getRoot()).getChildren().addAll(pane);

		backbtn.setOnAction(e -> {
			try {
				backBtn(e);
			} catch (Exception e1) {
				System.out.println("back crash");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		savebtn.setOnAction(e -> {
			try {
				getSavebtn(e);
			} catch (Exception e1) {
				System.out.println("save crash");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		stage.setScene(scene);
		stage.show();

	}

	public void getSavebtn(ActionEvent event) throws Exception {
		ArrayList<String> UpdateQueries = DB_controller.updateQuestions(table.getItems() , arrdup);
		for (String query : UpdateQueries) {
			System.out.println("Send to server update query -> " +query) ;
			ClientUI.chat.accept(query);
		}
		table.refresh();
	}

	public void backBtn(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.getScreen("Menu").display();
	}
}