package gui;

import java.util.ArrayList;

import enteties.Course;
import enteties.Question;
import enteties.Subject;
import enteties.Test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static ArrayList<Question> arr; 
	static {
		arr = new ArrayList<Question>();
		Question q1 = new Question("0124","math","logic","what is 1+1?","01","dor");
		Question q2 = new Question("0125","math","logic","what is 2+2?","02","dor");
		Question q3 = new Question("0141","math","infi","A={2n: n belong to N}. Is multiplying two terms in A is also a term in","01","julia");//added by mor
		arr.add(q1);
		arr.add(q2);
		arr.add(q3);//added by mor
	}
	
	public static ArrayList<Subject> subject; 
	static {
		Course c1 = new Course("1","infi");
		Course c2 = new Course("2","logic");
		Course c3 = new Course("3","data sturcture");
		ArrayList<Course> a1 = new ArrayList<Course>();
		a1.add(c1);
		a1.add(c2);
		ArrayList<Course> a2 = new ArrayList<Course>();
		a2.add(c3);
		
		subject = new ArrayList<Subject>();
		
		Subject s1 = new Subject("1","math",a1);
		Subject s2 = new Subject("1","software",a2);
		
		subject.add(s1);
		subject.add(s2);
		
	}
	public static ArrayList<Test> tests; 
	static {
		Test t1 = new Test("1","infi","22/05/2023");
		Test t2 = new Test("1","hedva","21/05/2023");
		Test t3 = new Test("1","logic","20/05/2023");
		
		ArrayList<Test> a1 = new ArrayList<Test>();
		a1.add(t1);
		a1.add(t2);
		a1.add(t3);
		tests = a1;
	}

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	String LoginfXML = "/fxml/Login.fxml"; //1
    	String LoginTitle = "Login";
    	
    	String LecturerMenufXML = "/fxml/LecturerMenu.fxml"; //2
    	String LecturerMenuTitle = "Lecturer Menu";
    	
    	String CreateQuestionfXML = "CreateQuestion.fxml"; //3
    	String CreateQuestionTitle = "Create Question";
    	
    	String CreateTestfXML = "CreateTest.fxml"; //4 
    	String CreateTestTitle = "Create Test";
    	
    	String LecturerTestViewfXML = "LecturerTestView.fxml";//5 
    	String LecturerTestViewTitle = "Lecturer Test View";
    	
    	String LecturerStaticsReportfXML = "LecturerStaticsReport.fxml";//6
    	String LecturerStaticsReportTitle = "Lecturer Statics Report";
    	
        Parent root = FXMLLoader.load(getClass().getResource(LecturerMenufXML));
        primaryStage.setTitle(CreateQuestionTitle);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
