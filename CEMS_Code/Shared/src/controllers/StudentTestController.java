package controllers;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.StudentTest;
import enteties.TestToExecute;
import enteties.User;

public class StudentTestController {
	
	public Msg getMsgForStudentTestsByID(User user) {
		Msg msg = new Msg(MsgType.select);
		msg.setSelect("studenttest.*,testtoexecute.*, test.*, course.*");
		msg.setFrom("cems.studenttest,cems.testtoexecute, cems.test, cems.course");
		msg.setWhereCol("testtoexecute.testId", "test.id");
    	msg.setWhereCol("test.courseNumber", "course.number"); 
    	msg.setWhereCol("studenttest.testCode", "testtoexecute.testCode"); 
		msg.setWhere("studenttest.studentId", user.getId());
		return msg;
	}

    /**
     * Constructs a database select message to retrieve StudentTest including studentName associated with a TestToExecute.
     *
     * @param t The TestToExecute object for whom to retrieve the StudentTest.
     * @return A Msg object representing the database select message.
     */
	public Msg getMsgForStudentTestsByTestToExecute(TestToExecute t) {
		Msg msg = new Msg(MsgType.select);
		msg.setSelect("studenttest.*, user.name");
		msg.setFrom("cems.studenttest, cems.user");
		msg.setWhereCol("studenttest.studentId", "user.id");
		msg.setWhere("testCode", t.getTestCode());
		msg.setWhere("approved", "false");
		return msg;
	}
	
    /**
     * Constructs a database update message to update a StudentTest approved field, grade and lecturerNote.
     *
     * @param st The StudentTest object to be updated.
     * @return A Msg object representing the database select message.
     */
	public Msg getMsgToUpdateStudentTests(StudentTest st) {
		Msg msg = new Msg(MsgType.update);
		msg.setTableToUpdate("cems.studenttest");
		msg.setSet("approved", st.getApproved());
		msg.setSet("grade", st.getGrade());
		msg.setSet("lecturerNotes", st.getLecturerNotes());
		msg.setSet("changeReason", st.getChangeReason());
		msg.setWhere("studentId", st.getStudentId());
		msg.setWhere("testCode", st.getTestCode());
		return msg;
	}
	
}