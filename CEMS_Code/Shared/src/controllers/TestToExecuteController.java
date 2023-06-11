package controllers;

import java.util.ArrayList;
import JDBC.Msg;
import JDBC.MsgType;
import enteties.TestToExecute;
import enteties.User;

public class TestToExecuteController {
	
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
}