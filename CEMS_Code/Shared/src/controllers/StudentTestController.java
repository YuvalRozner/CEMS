package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.StudentTest;
import enteties.TestToExecute;
import enteties.User;

public class StudentTestController {
	
	/**
	 * Creates a message to select all student tests with a specific test code.
	 *
	 * @param code The test code to filter the student tests.
	 * @return A Msg object configured for the select operation.
	 */
	
	public Msg selectAllstudentBySpecificCodeTest(Integer code) {
		Msg msg=new Msg(MsgType.select);
		msg.setSelect("studenttest.grade");
		msg.setFrom("cems.studenttest");
		msg.setWhere("testCode",code);
		return msg;
	}
	
	
	/**
	 * Creates a message object to update the student's answers, grade, and other details in the database for a manual test.
	 *
	 * @param approved    The approval status for the test.
	 * @param timePassed  The time passed by the student to complete the test (in minutes).
	 * @param answers     The answers provided by the student.
	 * @param grade       The grade obtained by the student.
	 * @param id          The ID of the student.
	 * @param code        The code of the test.
	 * @return The message object for updating the student's test details.
	 */

	public Msg InsertAnswersAndGradeManual(String approved,Integer timePassed,String ansewrs , Integer grade, String id , Integer code) {
		Msg msg = new Msg(MsgType.update);
		msg.setTableToUpdate("cems.studenttest");
		msg.setSet("answers", ansewrs);
		msg.setSet("grade", grade);
		msg.setSet("timePassed", timePassed);
		msg.setSet("approved", approved);
		msg.setWhere("studentId", id);
		msg.setWhere("testCode", code);
		return msg;
	}


	/**
	 * constructs a database select message to check if user already did this test.
	 * 
	 * @param user
	 * @param code of test
	 * @return msg representing select query.
	 */

	public Msg studentAlreadyAccessed(User user, String code) {
		Msg msg = new Msg(MsgType.select);
		msg.setSelect("studenttest.studentId");
		msg.setFrom("cems.studenttest");
		msg.setWhereCol("studentId", user.getId());
		msg.setWhere("testCode", code);
		return msg;

	}

	/**
	 * Constructs a database insert message to insert StudentTest.
	 *
	 * @param user The User object for whom to insert the Test..
	 * @return A Msg object representing the database insert message.
	 */
	public Msg insertStudentTest(TestToExecute testToExecute, User user) {
		Msg msg = new Msg(MsgType.insert);
		msg.setTableToUpdate("cems.studenttest");
		msg.setColNames("studentId,testCode");
		ArrayList<Object> tmp = new ArrayList<Object>();
		tmp.add(user.getId());
		tmp.add(testToExecute.getTestCode());
		msg.setValues(tmp);
		return msg;
	}

	/**
	 * Constructs a database select message to retrieve StudentTest including test
	 * to execute course and test for the right student.
	 *
	 * @param user The User object for whom to retrieve the Test..
	 * @return A Msg object representing the database select message.
	 */

	public Msg getMsgForStudentTestsByID(User user) {
		Msg msg = new Msg(MsgType.select);
		msg.setSelect("studenttest.*,testtoexecute.*, test.*, course.*");
		msg.setFrom("cems.studenttest,cems.testtoexecute, cems.test, cems.course");
		msg.setWhereCol("testtoexecute.testId", "test.id");
		msg.setWhereCol("test.courseNumber", "course.number");
		msg.setWhereCol("studenttest.testCode", "testtoexecute.testCode");
		msg.setWhere("studenttest.studentId", user.getId());
		msg.setWhere("studenttest.approved", "true");
		return msg;
	}

	/**
	 * Constructs a database select message to retrieve StudentTest including
	 * studentName associated with a TestToExecute.
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
	 * Constructs a database update message to update a StudentTest approved field,
	 * grade and lecturerNote.
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