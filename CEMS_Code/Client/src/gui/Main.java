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

	public static ArrayList<StudentTest> arrGrades; 
	static {
		arrGrades = new ArrayList<StudentTest>();
		StudentTest infi = new StudentTest(new Course("1234","infi"),"good job!","85",new Test("0025"));
	
		StudentTest logic = new StudentTest(new Course("5678","logic"),"you can better!","70",new Test("0068"));
		
		arrGrades.add(infi);
		arrGrades.add(logic);

	}

	public static ArrayList<TestToExecute> arrRuningTest; 
	static {
		arrRuningTest = new ArrayList<TestToExecute>();
		TestToExexeute testLior = new TestToExecute("run","infi" , "3.5","1234");
		TestToExexeute testYuval = new TestToExecute("lock" , "logic" , "2.5","5678");
		TestToExexeute testDor = new TestToExecute("run","algebra","3","97454");
		arrRuningTest.add(testLior);
		arrRuningTest.add(testYuval);
		arrRuningTest.add(testDor);
	}
	


	public static ArrayList<TestToExecute> arrExecuteTest; 
	static {
		arrExecuteTest = new ArrayList<TestToExecute>();
		TestToExecute exTestLior = new TestToExecute();
		exTestLior.setCourse("infi");
		exTestLior.setDate("21.4.23");
		exTestLior.setTestNum("1234");
		
			
		TestToExecute exTestYuval = new TestToExecute();
		exTestYuval.setCourse("logic");
		exTestYuval.setDate("28.4.23");
		exTestYuval.setTestNum("5678");
		
		TestToExecute exTestDor = new TestToExecute();
		exTestDor.setCourse("data structure");
		exTestDor.setDate("3.5.23");
		exTestDor.setTestNum("91001");
		
		arrExecuteTest.add(exTestLior);
		arrExecuteTest.add(exTestYuval);
		arrExecuteTest.add(exTestDor);
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
}
