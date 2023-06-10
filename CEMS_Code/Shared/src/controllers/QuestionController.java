package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
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
	public Object checkInputs(String id, String number, String question, String subjectNum, String lecturerId, String ans1, String ans2, String ans3, String ans4, Integer correctAns, String instructions) {
		String error = new String("");
		if(!id.equals(subjectNum+number.toString())) error+= "the question id must buit by the subject number + question number.\n";
		try { if(Integer.valueOf(number)>999 || Integer.valueOf(number)<100) error+="the question number must be between 100 and 999.\n";
		}catch(Exception e) {error+= "the question number must be an integer between 100 and 999.\\n";}
		if(question.length()==0) error+="the question content can't be empty.\n";
		if(Integer.valueOf(subjectNum)<1 || Integer.valueOf(subjectNum)>99) error+= "the subject number must be a number between 01 to 99.\n";
		if(ans1.length()==0 || ans2.length()==0 || ans3.length()==0 || ans4.length()==0) error+= "you can't enter an empty answer.\n";
		if(correctAns<1 || correctAns>4) error+= "the correct answer must be a digit between 1 to 4.\n";
		if(instructions.length()>126) error+= "the instructions can be a string up to 128 characters.";
		if(error.length()!=0) return error;
		return new Question(id, Integer.valueOf(number), question, subjectNum, lecturerId, new String[] {ans1, ans2, ans3, ans4} , correctAns, instructions, null);
	}
	
	
	public Msg insertQuestion(Question q) {
		Msg msg = new Msg(MsgType.insert);
		msg.setTableToUpdate("cems.question");
		msg.setColNames("id, number, question, subjectNum, lecturerId, answer1, answer2, answer3, answer4, correctAnswer, instructions");
		ArrayList<Object> tmp = new ArrayList<>();
		tmp.add(q.getId());
		tmp.add(q.getNumber());
		tmp.add(q.getQuestion());
		tmp.add(q.getSubjectNum());
		tmp.add(q.getLecturerId());
		tmp.add(q.getAnswers()[0]);
		tmp.add(q.getAnswers()[1]);
		tmp.add(q.getAnswers()[2]);
		tmp.add(q.getAnswers()[3]);
		tmp.add(q.getCorrectAnswer());
		tmp.add(q.getInstructions());
		msg.setValues(tmp);
		return msg;
	}

}