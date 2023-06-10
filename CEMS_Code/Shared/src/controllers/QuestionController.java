package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Course;
import enteties.Question;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Controller class for managing questions.
 */
public class QuestionController {

    /**
     * Checks the inputs for creating a new question.
     *
     * @param id           The ID of the question.
     * @param number       The number of the question.
     * @param question     The content of the question.
     * @param subjectNum   The subject number associated with the question.
     * @param lecturerId   The ID of the lecturer creating the question.
     * @param ans1         The first answer option.
     * @param ans2         The second answer option.
     * @param ans3         The third answer option.
     * @param ans4         The fourth answer option.
     * @param correctAns   The correct answer option (1-4).
     * @param instructions The instructions for the question.
     * @return An Object representing the new Question if the inputs are valid, or a String with an error message if the inputs are invalid.
     */
	public Object checkInputs(String id, String number, String question, String subjectNum, String lecturerId, String ans1, String ans2, String ans3, String ans4, Integer correctAns, String instructions) {
		String error = new String("");
		if(!id.equals(subjectNum+number.toString())) error+= "the question id must buit by the subject number + question number.\n";
		try { if(Integer.valueOf(number)>999 || Integer.valueOf(number)<100) error+="the question number must be between 100 and 999.\n";
		}catch(Exception e) {error+= "the question number must be an integer between 100 and 999.\n";}
		if(question.length()==0) error+="the question content can't be empty.\n";
		if(Integer.valueOf(subjectNum)<1 || Integer.valueOf(subjectNum)>99) error+= "the subject number must be a number between 01 to 99.\n";
		if(ans1.length()==0 || ans2.length()==0 || ans3.length()==0 || ans4.length()==0) error+= "you can't enter an empty answer.\n";
		if(correctAns<1 || correctAns>4) error+= "the correct answer must be a digit between 1 to 4.\n";
		if(instructions.length()>126) error+= "the instructions can be a string up to 128 characters.";
		if(error.length()!=0) return error;
		return new Question(id, Integer.valueOf(number), question, subjectNum, lecturerId, new String[] {ans1, ans2, ans3, ans4} , correctAns, instructions, null);
	}
	
    /**
     * creates and returns a Msg for inserting a question to DB.
     *
     * @param q The Question with the courses field to be inserted.
     * @return A Msg object representing the database insert message.
     */
	public Msg insertQuestion(Question q) {
		Msg msgQ = new Msg(MsgType.insert);
		msgQ.setTableToUpdate("cems.question");
		msgQ.setColNames("id, number, question, subjectNum, lecturerId, answer1, answer2, answer3, answer4, correctAnswer, instructions");
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
		msgQ.setValues(tmp);
		
		Msg msgM = new Msg(MsgType.manyMessages);
		msgM.setMsgLst(msgQ);
		
		for(Course c : q.getCourses()) {
			Msg msgQ_C = new Msg(MsgType.insert);
			msgQ_C.setTableToUpdate("cems.question_course");
			msgQ_C.setColNames("questionId, courseNum");
			ArrayList<Object> qc = new ArrayList<Object>();
			qc.add(q.getId());
			qc.add(c.getNumber());
			msgQ_C.setValues(qc);
			msgM.setMsgLst(msgQ_C);
		}
		return msgM;
	}

    /**
     * Retrieves the questions of the subjects as an ObservableList with checkBox and textField for each question.
     * 
     * @param questions The list of the questions.
     * @return The ObservableList of questions with checkBox and textField.
     */
	public ObservableList<Question> getQuestionsForTable(ArrayList<Question> questions) {
		ObservableList<Question> res = FXCollections.observableArrayList();
		for(Question q : questions) {
			q.setNewCheckbox();
			q.setNewTextField();
        	q.getTextField().setDisable(true);
        	q.getCheckbox().selectedProperty().addListener(new ChangeListener<Boolean>() {
    			@Override
    			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    				q.getTextField().setDisable(!newValue);}
    		});
			res.add(q);
		}
		return res;
	}
}