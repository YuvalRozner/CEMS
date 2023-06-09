package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Course;
import enteties.Question;

public class QuestionController {

	/**
	 * 
	 * @param question
	 * @param parameter
	 */
	public Question createQuestion(Question question, int parameter) {
		// TODO - implement QuestionController.createQuestion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param qustionINPUTS
	 */
	public Question checkInputs(String id, Integer number, String question, String subjectNum, String lecturerId, String ans1, String ans2, String ans3, String ans4, Integer correctAns, String instructions) {
		
		return new Question(id,  number,  question,  subjectNum,  lecturerId, new String[] {ans1, ans2, ans3, ans4} ,  correctAns, instructions, null);
	}
	
	public Msg insertQuestion(Question q) {
		Msg msg = new Msg(MsgType.insert);
		msg.setTableToUpdate("cems.question");
		msg.setColNames("id, number, question, subjectNum, lecturerId, answer1, answer2, answer3, answer4, correctAnswer, instructions");
		ArrayList<Object> tmp = new ArrayList<>();
		tmp.add(q.getId());
		tmp.add(q.getNumber());
		tmp.add(q.getSubjectNum());
		tmp.add(q.getLecturerId());
		tmp.add(q.getAnswers()[0]);
		tmp.add(q.getAnswers()[1]);
		tmp.add(q.getAnswers()[2]);
		tmp.add(q.getAnswers()[3]);
		tmp.add(q.getCorrectAns());
		tmp.add(q.getInstructions());
		msg.setValues(tmp);
		return msg;
	}

}