package gui;

import java.util.ArrayList;

import enteties.Course;
import enteties.Question;
import enteties.StudentTest;
import enteties.Subject;
import enteties.Test;
import enteties.TestToExexeute;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	CountDownTimerController c;
	public static ArrayList<Question> arr; 
	static {
		arr = new ArrayList<Question>();
		Question q1 = new Question("0124","math","logic","what is 1+1?",01,"dor");
		Question q2 = new Question("0125","math","logic","what is 2+2?",02,"dor");
		Question q3 = new Question("0141","math","infi","A={2n: n belong to N}. Is multiplying two terms in A is also a term in",01,"julia");//added by mor
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
	
	///add by lior Grades
	public static ArrayList<StudentTest> arrGrades; 
	static {
		arrGrades = new ArrayList<StudentTest>();
		StudentTest infi = new StudentTest(new Course("1234","infi"),"good job!","85",new Test("0025"));
	
		StudentTest logic = new StudentTest(new Course("5678","logic"),"you can better!","70",new Test("0068"));
		
		arrGrades.add(infi);
		arrGrades.add(logic);

	}
	
	///add by lior runningTest
	public static ArrayList<TestToExexeute> arrRuningTest; 
	static {
		arrRuningTest = new ArrayList<TestToExexeute>();
		TestToExexeute testLior = new TestToExexeute("run","infi" , "3.5","1234");
		TestToExexeute testYuval = new TestToExexeute("lock" , "logic" , "2.5","5678");
		TestToExexeute testDor = new TestToExexeute("run","algebra","3","97454");
		arrRuningTest.add(testLior);
		arrRuningTest.add(testYuval);
		arrRuningTest.add(testDor);
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
	
	///add by lior AprroveGrade
	public static ArrayList<StudentTest> arrStudentTest; 
	static {
		arrStudentTest = new ArrayList<StudentTest>();
		StudentTest testLior = new StudentTest();
		testLior.setStudentId("1234");
		testLior.setGrade("75");
		
		StudentTest testYuval = new StudentTest();
		testYuval.setStudentId("123");
		testYuval.setGrade("69");
		
		StudentTest testDor = new StudentTest();
		testDor.setStudentId("91011");
		testDor.setGrade("82");
		
		
		arrStudentTest.add(testLior);
		arrStudentTest.add(testYuval);
		arrStudentTest.add(testDor);
	}
	
	///finish
	
	///add by lior executeTest
	public static ArrayList<TestToExexeute> arrExecuteTest; 
	static {
		arrExecuteTest = new ArrayList<TestToExexeute>();
		TestToExexeute exTestLior = new TestToExexeute();
		exTestLior.setCourse("infi");
		exTestLior.setDate("21.4.23");
		exTestLior.setTestNum("1234");
		
			
		TestToExexeute exTestYuval = new TestToExexeute();
		exTestYuval.setCourse("logic");
		exTestYuval.setDate("28.4.23");
		exTestYuval.setTestNum("5678");
		
		TestToExexeute exTestDor = new TestToExexeute();
		exTestDor.setCourse("data structure");
		exTestDor.setDate("3.5.23");
		exTestDor.setTestNum("91001");
		
		arrExecuteTest.add(exTestLior);
		arrExecuteTest.add(exTestYuval);
		arrExecuteTest.add(exTestDor);
	}
	
	
	
	

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	String LoginfXML = "/fxml/Login.fxml"; //1
    	String LoginTitle = "Login";
    	
    	String LecturerMenufXML = "/fxml/LecturerMenu.fxml"; //2
    	String LecturerMenuTitle = "Lecturer Menu";
    	
    	String CreateQuestionfXML = "/fxml/CreateQuestion.fxml"; //3
    	String CreateQuestionTitle = "Create Question";
    	
    	String CreateTestfXML = "/fxml/CreateTest.fxml"; //4 
    	String CreateTestTitle = "Create Test";
    	
    	String LecturerTestViewfXML = "/fxml/LecturerTestView.fxml";//5 
    	String LecturerTestViewTitle = "Lecturer Test View";
    	
    	String LecturerStaticsReportfXML = "/fxml/LecturerStaticsReport.fxml";//6
    	String LecturerStaticsReportTitle = "Lecturer Statics Report";
    	
    	String CountDounTimerfXML = "/fxml/CountDownTimer.fxml";//6
    	
    	
    	FXMLLoader f = new FXMLLoader();
        Parent root = f.load(getClass().getResource(CountDounTimerfXML));
        c = f.getController();
      
        primaryStage.setTitle("counting time yey");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }

    
    
    
    public static void main(String[] args) {
        launch(args);
       
    }
}
