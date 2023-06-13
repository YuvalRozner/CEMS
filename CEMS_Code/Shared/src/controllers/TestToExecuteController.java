package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Test;
import enteties.TestToExecute;
import enteties.User;
import notifications.NotificationAlertsController;

public class TestToExecuteController {
	
    /**
     * Constructs a database select message to retrieve TestToExecute associated with a code.
     *
     * @param code The code object for the right TestToExecute.
     * @return A Msg object representing the database select message.
     */
	public Msg selectTestToExecuteByCode(String code) {
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("*");
    	msg.setFrom("cems.testtoexecute");
    	msg.setWhere("testCode", code); 
    	return msg;
    }
	
    /**
     * check if the id is valid.
     *
     * @param id the id that enter the user to enter a test.
     * @param user the user to get the id of the user.
     * @return true if the id is valid and corresponding to the user.
     * @return false if the id is not valid.
     */
	
	public boolean checkValidId(String id,User user) {
		NotificationAlertsController alert = new NotificationAlertsController();
		if (id.isEmpty() == true){alert.showWarningAlert("ID is empty!");return false;}
		if (!user.getId().equals(id)) {alert.showWarningAlert("ID is worng!");return false;}
		return true;
	}
	
    /**
     * check if the code is valid to enter a test.
     *
     * @param code the code for the write test.
     * @return true if the code is valid.
     * @return false if the code is not valid.
     */
	
	public boolean checkValidCode(String code) {
		NotificationAlertsController alert = new NotificationAlertsController();
		if (code.isEmpty() == true){alert.showWarningAlert("You must enter code for a test, 4 digits!");return false;}
		if (code.length()!=4) {alert.showWarningAlert("The code must be 4 in length!");return false;}
		if (!code.matches("\\d+")) {alert.showWarningAlert("The code must consist of only digits!");return false;}
		return true;
	}
	
    /**
     * Constructs a database select message to retrieve TestToExecute associated with a user.
     *
     * @param user The User object for whom to retrieve the TestToExecute.
     * @return A Msg object representing the database select message.
     */
	public Msg selectTestToExecuteByUser(User user) {
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("testtoexecute.*, test.*, course.*");
    	msg.setFrom("cems.testtoexecute, cems.test, cems.course");
    	msg.setWhereCol("testtoexecute.testId", "test.id");
    	msg.setWhereCol("test.courseNumber", "course.number"); 
    	msg.setWhere("testtoexecute.lecturerId", user.getId()); 
    	return msg;
    }

    /**
     * Retrieves the names of the tests as an ObservableList.
     * 
     * @param subjectsLst The list of the testToExecuteLst.
     * @return The ObservableList of subject names.
     */
	public ArrayList<String> getTestToExecuteNames(ArrayList<TestToExecute> testToExecuteLst) {
        //ObservableList<String> testToExecuteNames = FXCollections.observableArrayList();
		ArrayList<String> testToExecuteNames = new ArrayList<String>();
        for (TestToExecute t : testToExecuteLst) {
        	testToExecuteNames.add("(code-"+t.getTestCode()+")  " + t.getTest().getCourse().getName() +"  " + t.getDate());
        }
        return testToExecuteNames;
	}

	/**
	 * Finds a TestToExecute object from the given list based on the provided name.
	 *
	 * @param newValue       The name of the TestToExecute to find.
	 * @param testToExecuteLst The list of TestToExecute objects to search in.
	 * @return The TestToExecute object with the matching name, or null if not found.
	 */
	public TestToExecute findTestToExecuteByName(String newValue, ArrayList<TestToExecute> testToExecuteLst) {
		for(TestToExecute t : testToExecuteLst)
			if(newValue.equals("(code-"+t.getTestCode()+")  " + t.getTest().getCourse().getName() +"  " + t.getDate()))
				return t;
		return null;
	}

	/**
	 * Executes a list of tests and generates a list of TestToExecute objects based on the provided test list and user.
	 * including the FX fields needed for the table.
	 *
	 * @param testLst The list of tests to execute.
	 * @param user The user executing the tests.
	 * @return The list of generated TestToExecute objects.
	 */
	@SuppressWarnings({ "unchecked" })
	public ArrayList<TestToExecute> executeListOfTests(ArrayList<Test> testLst, User user) {
		ArrayList<TestToExecute> lst = new ArrayList<>();
		for(Test t : testLst) {
			TestToExecute tmp = new TestToExecute();
			tmp.setTestId(t.getId());
			tmp.setAverage(-1.0);
			tmp.setMedian(-1.0);
			tmp.setLock("false");
			tmp.setLecturerId(user.getId());
			tmp.setCourse(t.getCourse());
			tmp.setNewButton();
			tmp.setButtonText("Show");
			tmp.setNewRadioButton();
			tmp.setNewComboBox();
        	tmp.getComboBox().getItems().addAll("online", "manual");
        	tmp.getComboBox().setValue("online");
        	tmp.setNewTextField(); // date
        	tmp.getTextField().setPromptText("22/06/2023");
        	tmp.setNewTextField1(); // code
        	tmp.getTextField1().setPromptText("4 digits");
        	tmp.getComboBox().setDisable(true);
        	tmp.getTextField().setDisable(true);
        	tmp.getTextField1().setDisable(true);
        	lst.add(tmp);
		}
		return lst;
	}

    /**
     * Checks the inputs for creating a new question.
     *
     * @param selectedTest The TestToExecute to check the inputs for.

     * @return An Object representing the new TestToExecute if the inputs are valid, or a String with an error message if the inputs are not invalid.
     */
	public Object checkInputs(TestToExecute selectedTest, User user) {
		String error = new String("");
		if(selectedTest.getTextField().getText().length()==0) error += "You must enter test date.\n";
		selectedTest.setDate(selectedTest.getTextField().getText()); 
		try{
			if(Integer.valueOf(selectedTest.getTextField1().getText())>9999 || Integer.valueOf(selectedTest.getTextField1().getText())<1000)
				error += "Test code must be a number between 1000 and 9999.\n";
			selectedTest.setTestCode(Integer.valueOf(selectedTest.getTextField1().getText()));}
		catch(Exception e) {error += "Test code must be an integer.\n";}
		selectedTest.setTestingType((String)selectedTest.getComboBox().getValue());
		if(error.length()!=0) return error;
		selectedTest.setLecturerId(user.getId());
		return selectedTest;
	}
	
    /**
     * creates and returns a Msg for inserting a TestToExecute to DB.
     *
     * @param newTestToExecute The newTestToExecute to insert.
     * @return A Msg object representing the database insert message.
     */
	public Msg insertTestToExecute(TestToExecute t) {
		Msg msg = new Msg(MsgType.insert);
		msg.setTableToUpdate("cems.testtoexecute");
		msg.setColNames("testCode, testId, testingType, date, lecturerId");
		ArrayList<Object> tmp = new ArrayList<>();
		tmp.add(t.getTestCode());
		tmp.add(t.getTestId());
		tmp.add(t.getTestingType());
		tmp.add(t.getDate());
		tmp.add(t.getLecturerId());
		msg.setValues(tmp);
		return msg;
	}
}