package controllers;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.User;

/**
 * Controller class for managing user.
 */
public class UserController {
	public Msg getLecturerNameById(String id) {
		Msg msg = new Msg(MsgType.select);
		msg.setSelect("`user`.username");
		msg.setFrom("`user`");
		msg.setWhere("id", id);
		return msg;
	}

	/**
	 * Generates a message to update the "loggedin" status of a user to "no" in the database.
	 *
	 * @param user The User object representing the user to log out.
	 * @return The generated Msg object for the logout update.
	 */
	public Msg getLoggedinMsg(User user, String loggedin) {
		Msg msg = new Msg(MsgType.update);
		msg.setTableToUpdate("user");
		msg.setSet("loggedin", loggedin);
		msg.setWhere("username", user.getUsername());
		return msg;
	}
	
	/**
	 * Generates a message to select a user from the database based on the username.
	 *
	 * @param username The username of the user to select.
	 * @return The generated Msg object for the user selection.
	 */
	public Msg selectUser(String username) {
		Msg msg = new Msg(MsgType.select);
    	msg.setSelect("*");
    	msg.setFrom("user");
    	msg.setWhere("username", username);
    	return msg;
	}

	/**
	 * Generates a message to select a user (hod) from the database based on the subjectNumber.
	 *
	 * @param subjectNumber The subject number of the hod to select by.
	 * @return The generated Msg object for the user selection.
	 */
	public Msg selectHodBySubjectNumber(String subjectNumber) {
		Msg msg = new Msg(MsgType.select);
    	msg.setSelect("user.*");
    	msg.setFrom("user");
    	msg.setFrom("hod_subject");
    	msg.setWhereCol("user.id", "hod_subject.hodId");
    	msg.setWhere("hod_subject.subjectNumber", subjectNumber);
    	return msg;
	}
	
	/**
	 * Generates a message to select a question from the database based on the hodId.
	 *
	 * @param hodId The ID of the hod to select by.
	 * @return The generated Msg object for the user selection.
	 */
	public Msg selectQuestionByhodId(String hodId) {
		Msg msg = new Msg(MsgType.select);
		msg.setSelect("question.* , course.* , user.*");
        msg.setFrom("question");
        msg.setFrom("course");
        msg.setFrom("question_course");
        msg.setFrom("hod_subject");
        msg.setFrom("user");
        msg.setWhereCol("hod_subject.subjectNumber", "question.subjectNum");
        msg.setWhereCol("user.id", "question.lecturerId");
        msg.setWhereCol("question_course.questionId", "question.id");
        msg.setWhereCol("question_course.courseNum", "course.number");
        msg.setWhere("hod_subject.hodId",hodId);
    	return msg;
	}
	
	/**
	 * Generates a message to select a Lecturer from the database based on the hod ID.
	 *
	 * @param ID The ID of the hod.
	 * @return The generated Msg object for the user selection.
	 */
	public Msg selectUserByHodAndLecturer(String ID) {
		//Msg msgM = new Msg(MsgType.manyMessages);
		Msg msg1 = new Msg(MsgType.select);
    	msg1.setSelect("user.*");
    	msg1.setFrom("user ");
    	msg1.setFrom("user_subject ");
    	msg1.setFrom("hod_subject ");
    	msg1.setWhereCol("hod_subject.subjectNumber", "user_subject.subjectNum");
    	msg1.setWhereCol("user.id", "user_subject.userId");
    	msg1.setWhere("hod_subject.hodId", ID);
    	/*
    	Msg msg2 = new Msg(MsgType.select);
    	msg2.setSelect("user.*");
    	msg2.setFrom("user");
    	msg2.setFrom("studenttest");
    	msg2.setFrom("testtoexecute");
    	msg2.setFrom("test");
    	msg2.setFrom("course");
    	msg2.setFrom("hod_subject");
    	msg2.setWhereCol("user.id", "studenttest.studentId");
    	msg2.setWhereCol("studenttest.testCode", "testtoexecute.testCode");
    	msg2.setWhereCol("testtoexecute.testId", "test.id");
    	msg2.setWhereCol("test.courseNumber", "course.number");
    	msg2.setWhereCol("hod_subject.subjectNumber", "course.subjectNum");
    	msg2.setWhere("hod_subject.hodId", ID);
    	msgM.setMsgLst(msg1);
		msgM.setMsgLst(msg2);
		*/
    	//return msgM;
    	return msg1;
	}
	
	/**
	 * Generates a message to select a Student from the database based on the hod ID.
	 *
	 * @param ID The ID of the hod.
	 * @return The generated Msg object for the user selection.
	 */
	public Msg selectUserByHodAndStudent(String ID) {
		Msg msg = new Msg(MsgType.select);
    	msg.setSelect("user.*");
    	msg.setFrom("user ");
    	msg.setFrom("studenttest ");
    	msg.setFrom("testtoexecute ");
    	msg.setFrom("test ");
    	msg.setFrom("course ");
    	msg.setFrom("hod_subject ");
    	msg.setWhereCol("user.id", "studenttest.studentId");
    	msg.setWhereCol("studenttest.testCode", "testtoexecute.testCode");
    	msg.setWhereCol("testtoexecute.testId", "test.id");
    	msg.setWhereCol("test.courseNumber", "course.number");
    	msg.setWhereCol("hod_subject.subjectNumber", "course.subjectNum");
    	msg.setWhere("hod_subject.hodId", ID);
    	return msg;
	}
	
	/**
	 * Generates a message to select a Course from the database based on the hod ID.
	 *
	 * @param ID The ID of the hod.
	 * @return The generated Msg object for the user selection.
	 */
	public Msg selectUserByHodAndCourse(String ID) {
		Msg msg = new Msg(MsgType.select);
    	msg.setSelect("course.*");
    	msg.setFrom("course ");
    	msg.setFrom("hod_subject ");
    	msg.setWhereCol("course.subjectNum", "hod_subject.subjectNumber");
    	msg.setWhere("hod_subject.hodId", ID);
    	return msg;
	}

	/**
	 * Generates a message to select a user (hod) from the database based on the testToExecuteCode.
	 *
	 * @param testToExecuteCode The course number of the hod to select by.
	 * @return The generated Msg object for the user selection.
	 */
	public Msg selectHodBystudentTestToEcecuteCode(Integer testCode) {
		Msg msg = new Msg(MsgType.select);
    	msg.setSelect("user.*");
    	msg.setFrom("user ");
    	msg.setFrom("hod_subject");
    	msg.setFrom("course");
    	msg.setFrom("test");
    	msg.setFrom("testtoexecute");
    	msg.setWhere("testtoexecute.testCode", testCode);
    	msg.setWhereCol("user.id", "hod_subject.hodId");
    	msg.setWhereCol("test.id", "testtoexecute.testId");
    	msg.setWhereCol("test.courseNumber", "course.number");
    	msg.setWhereCol("hod_subject.subjectNumber", "course.subjectNum");
    	return msg;
	}
}