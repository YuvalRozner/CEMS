package controllers;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.User;

/**
 * Controller class for managing user.
 */
public class UserController {

	/**
	 * Generates a message to update the "loggedin" status of a user to "no" in the database.
	 *
	 * @param user The User object representing the user to log out.
	 * @return The generated Msg object for the logout update.
	 */
	public Msg getLoggedinMsg(User user, String loggedin) {
		Msg msg = new Msg(MsgType.update);
		msg.setTableToUpdate("cems.user");
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
    	msg.setFrom("cems.user");
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
    	msg.setFrom("cems.user, cems.hod_subject");
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
		msg.setSelect("question.* , course.*");
        msg.setFrom("cems.question , cems.course , cems.question_course , cems.hod_subject");
        msg.setWhereCol("hod_subject.subjectNumber", "question.subjectNum");
        msg.setWhereCol("question_course.questionId", "question.id");
        msg.setWhereCol("question_course.courseNum", "course.number");
        msg.setWhere("hod_subject.hodId",hodId);
    	return msg;
	}
}
