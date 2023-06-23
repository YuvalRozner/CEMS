package controllers;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Course;
import enteties.Question;
import java.util.ArrayList;


class QuestionControllerTest {

	QuestionController questionController;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		questionController = new QuestionController();
	}

	@Test
	void InsertQuestionTest_WithCourses() {
	    Question question = new Question();
	    question.setId("22101");
	    question.setNumber(101);
	    question.setQuestion("Which logical operator represents the conjunction (AND) operation?");
	    question.setSubjectNum("22");
	    question.setLecturerId("21234560");
	    question.setAnswers(new String[]{"OR", "AND", "NOR", "NOT"});
	    question.setCorrectAnswer(2);
	    question.setInstructions("Select the correct answer.");

	    Msg tempMsg1 = new Msg(MsgType.insert);
	    tempMsg1.setTableToUpdate("question");
	    tempMsg1.setColNames("id, number, question, subjectNum, lecturerId, answer1, answer2, answer3, answer4, correctAnswer, instructions");
	    
	    ArrayList<Object> values = new ArrayList<>();
	    values.add("22101");
	    values.add(101);
	    values.add("Which logical operator represents the conjunction (AND) operation?");
	    values.add("22");
	    values.add("21234560");
	    values.add("OR");
	    values.add("AND");
	    values.add("NOR");
	    values.add("NOT");
	    values.add(2);
	    values.add("Select the correct answer.");
	    tempMsg1.setValues(values);
	    
	    Msg expectedMsg1 = new Msg(MsgType.manyMessages);
	    expectedMsg1.setMsgLst(tempMsg1);

	    ArrayList<Course> courses = new ArrayList<>();
	    courses.add(new Course("11", "Logic", "22"));
	    courses.add(new Course("12", "Algebra", "23"));
	    question.setCourses(courses);

	    Msg actualMsg1 = questionController.insertQuestion(question);
	    assertEquals(actualMsg1.getType(), expectedMsg1.getType());
	    assertEquals(expectedMsg1.getTableToUpdate(), actualMsg1.getTableToUpdate());
	    assertEquals(expectedMsg1.getColNames(), actualMsg1.getColNames());
	    assertEquals(expectedMsg1.getValues(), actualMsg1.getValues());
	}
	
	@Test
	void InsertQuestionTest_WithoutCourses() {
	    Question question = new Question();
	    question.setId("22101");
	    question.setNumber(101);
	    question.setQuestion("Which logical operator represents the conjunction (AND) operation?");
	    question.setSubjectNum("22");
	    question.setLecturerId("21234560");
	    question.setAnswers(new String[]{"OR", "AND", "NOR", "NOT"});
	    question.setCorrectAnswer(2);
	    question.setInstructions("Select the correct answer.");

	    Msg tempMsg1 = new Msg(MsgType.insert);
	    tempMsg1.setTableToUpdate("question");
	    tempMsg1.setColNames("id, number, question, subjectNum, lecturerId, answer1, answer2, answer3, answer4, correctAnswer, instructions");

	    ArrayList<Object> values = new ArrayList<>();
	    values.add("22101");
	    values.add(101);
	    values.add("Which logical operator represents the conjunction (AND) operation?");
	    values.add("22");
	    values.add("21234560");
	    values.add("OR");
	    values.add("AND");
	    values.add("NOR");
	    values.add("NOT");
	    values.add(2);
	    values.add("Select the correct answer.");
	    ArrayList<Course> courses = new ArrayList<>();
	    question.setCourses(courses);
	    tempMsg1.setValues(values);
	    
	    Msg expectedMsg1 = new Msg(MsgType.manyMessages);
	    expectedMsg1.setMsgLst(tempMsg1);

	    Msg actualMsg1 = questionController.insertQuestion(question);
	    assertEquals(actualMsg1.getType(), expectedMsg1.getType());
	    assertEquals(expectedMsg1.getTableToUpdate(), actualMsg1.getTableToUpdate());
	    assertEquals(expectedMsg1.getColNames(), actualMsg1.getColNames());
	    assertEquals(expectedMsg1.getValues(), actualMsg1.getValues());
	}
	
	@Test
	void InsertQuestionTest_WithNullQuestionFields() {
	    Question question = new Question();
	    Msg actualMsg1 = null;
	    Msg expectedMsg1 = null;
	    try {
		    question.setId(null);
		    question.setNumber(null);
		    question.setQuestion(null);
		    question.setSubjectNum(null);
		    question.setLecturerId(null);
		    question.setAnswers(null);
		    question.setCorrectAnswer(null);
		    question.setInstructions(null);

		    Msg tempMsg1 = new Msg(MsgType.insert);
		    tempMsg1.setTableToUpdate("question");
		    tempMsg1.setColNames("id, number, question, subjectNum, lecturerId, answer1, answer2, answer3, answer4, correctAnswer, instructions");

		    ArrayList<Object> values = new ArrayList<>();
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    values.add(null);
		    ArrayList<Course> courses = new ArrayList<>();
		    question.setCourses(courses);
		    tempMsg1.setValues(values);
		    
		    expectedMsg1 = new Msg(MsgType.manyMessages);
		    expectedMsg1.setMsgLst(tempMsg1);
		    actualMsg1 = questionController.insertQuestion(question);
	    }catch(NullPointerException e) {fail("Exception");};

	    assertEquals(actualMsg1.getType(), expectedMsg1.getType());
	    assertEquals(expectedMsg1.getTableToUpdate(), actualMsg1.getTableToUpdate());
	    assertEquals(expectedMsg1.getColNames(), actualMsg1.getColNames());
	    assertEquals(expectedMsg1.getValues(), actualMsg1.getValues());
	}


	@Test
    public void checkInputsTest_ValidInputs() {
        String id = "22101";
        String number = "101";
        String question = "Which logical operator represents the conjunction (AND) operation?";
        String subjectNum = "22";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = "NOR";
        String ans4 = "NOT";
        Integer correctAns = 2;
        String instructions = "Select the correct answer.";
        Object result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);

        assertTrue(result instanceof Question);
        Question createdQuestion = (Question) result;
        assertEquals(id, createdQuestion.getId());
        assertEquals(Integer.valueOf(number), createdQuestion.getNumber());
        assertEquals(question, createdQuestion.getQuestion());
        assertEquals(subjectNum, createdQuestion.getSubjectNum());
        assertEquals(lecturerId, createdQuestion.getLecturerId());
        assertArrayEquals(new String[]{ans1, ans2, ans3, ans4}, createdQuestion.getAnswers());
        assertEquals(correctAns, createdQuestion.getCorrectAnswer());
        assertEquals(instructions, createdQuestion.getInstructions());
    }

    @Test
    public void checkInputsTest_InvalidId() {
        String id = "22990";
        String number = "101";
        String question = "Which logical operator represents the conjunction (AND) operation?";
        String subjectNum = "22";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = "NOR";
        String ans4 = "NOT";
        Integer correctAns = 2;
        String instructions = "Select the correct answer.";
        Object result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);

        assertTrue(result instanceof String);
        Object errorMessage1 = result;
        assertEquals(errorMessage1, "the question id must buit by the subject number + question number.\n");
    }
    
    @Test
    public void checkInputsTest_InvalidNumber() {
        String id = "2299";
        String number = "99";
        String question = "Which logical operator represents the conjunction (AND) operation?";
        String subjectNum = "22";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = "NOR";
        String ans4 = "NOT";
        Integer correctAns = 2;
        String instructions = "Select the correct answer.";
        Object result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);

        assertTrue(result instanceof String);
        Object errorMessage = result;
        assertEquals(errorMessage, "the question number must be between 100 and 999.\n");
    }
    
    @Test
    public void checkInputsTest_InvalidQuestion() {
        String id = "22101";
        String number = "101";
        String question = "";
        String subjectNum = "22";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = "NOR";
        String ans4 = "NOT";
        Integer correctAns = 2;
        String instructions = "Select the correct answer.";
        Object result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);

        assertTrue(result instanceof String);
        Object errorMessage = result;
        assertEquals(errorMessage, "the question content can't be empty.\n");
    }
    
    @Test
    public void checkInputsTest_InvalidSubject() {
        String id = "100101";
        String number = "101";
        String question = "Which logical operator represents the conjunction (AND) operation?";
        String subjectNum = "100";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = "NOR";
        String ans4 = "NOT";
        Integer correctAns = 2;
        String instructions = "Select the correct answer.";
        Object result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);

        assertTrue(result instanceof String);
        Object errorMessage = result;
        assertEquals(errorMessage, "the subject number must be a number between 01 to 99.\n");
    }

    @Test
    public void checkInputsTest_InvalidAnswers() {
        String id = "22101";
        String number = "101";
        String question = "Which logical operator represents the conjunction (AND) operation?";
        String subjectNum = "22";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = "";
        String ans4 = "NOT";
        Integer correctAns = 2;
        String instructions = "Select the correct answer.";
        Object result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);

        assertTrue(result instanceof String);
        Object errorMessage = result;
        assertEquals(errorMessage, "you can't enter an empty answer.\n");
    }
    
    @Test
    public void checkInputsTest_InvalidCorrectAnswer() {
        String id = "22101";
        String number = "101";
        String question = "Which logical operator represents the conjunction (AND) operation?";
        String subjectNum = "22";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = "NOR";
        String ans4 = "NOT";
        Integer correctAns = 5;
        String instructions = "Select the correct answer.";
        Object result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);

        assertTrue(result instanceof String);
        Object errorMessage = result;
        assertEquals(errorMessage, "the correct answer must be a digit between 1 to 4.\n");
    }
    
    @Test
    public void checkInputsTest_InvalidInstructions() {
        String id = "22101";
        String number = "101";
        String question = "Which logical operator represents the conjunction (AND) operation?";
        String subjectNum = "22";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = "NOR";
        String ans4 = "NOT";
        Integer correctAns = 2;
        String instructions = "Select the correct answer.Select the correct answer.Select the correct answer.Select the correct answer.Select the correct answer.";
        Object result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);

        assertTrue(result instanceof String);
        Object errorMessage = result;
        assertEquals(errorMessage, "the instructions can be a string up to 128 characters.");
    }
    
    @Test
    public void checkInputsTest_NullAnswer() {
    	Object result = null;
        String id = "22101";
        String number = "101";
        String question = "Which logical operator represents the conjunction (AND) operation?";
        String subjectNum = "22";
        String lecturerId = "21234560";
        String ans1 = "OR";
        String ans2 = "AND";
        String ans3 = null;
        String ans4 = "NOT";
        Integer correctAns = 2;
        String instructions = "Select the correct answer.";
        try {
        	result = questionController.checkInputs(id, number, question, subjectNum, lecturerId, ans1, ans2, ans3, ans4, correctAns, instructions);
        }catch(NullPointerException e) {fail("Exception");};
        assertNull(result);
    }
    
    @Test
    public void checkPointsTest_ValidPointsSum() {
        ArrayList<Question> validQuestions = new ArrayList<>();
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the conjunction (AND) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 2, "Select the correct answer.", 30));
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the bla (NOR) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 3, "Select the correct answer.", 40));
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the blabla (NOT) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 4, "Select the correct answer.", 30));

        Object result = questionController.checkPoints(validQuestions);
        assertEquals(validQuestions, result);
    }
    
    @Test
    public void checkPointsTest_PointsSumLessThan100() {
        ArrayList<Question> validQuestions = new ArrayList<>();
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the conjunction (AND) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 2, "Select the correct answer.", 30));
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the bla (NOR) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 3, "Select the correct answer.", 30));
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the blabla (NOT) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 4, "Select the correct answer.", 30));

        Object result = questionController.checkPoints(validQuestions);
        assertEquals("the points sum must be 100.", result);
    }
    
    @Test
    public void checkPointsTest_PointsSumGreaterThan100() {
        ArrayList<Question> validQuestions = new ArrayList<>();
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the conjunction (AND) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 2, "Select the correct answer.", 40));
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the bla (NOR) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 3, "Select the correct answer.", 40));
        validQuestions.add(new Question("22101", 101, "Which logical operator represents the blabla (NOT) operation?", "22", "21234560", "OR", "AND", "NOR", "NOT", 4, "Select the correct answer.", 30));

        Object result = questionController.checkPoints(validQuestions);
        assertEquals("the points sum must be 100.", result);
    }
    
    @Test
    public void checkPointsTest_EmptyQuestionsList() { 
        ArrayList<Question> emptyQuestions = new ArrayList<>();
        Object result = questionController.checkPoints(emptyQuestions);
        assertEquals(null, result);
    }
    
    @Test
    public void checkPointsTest_NullQuestionsList() { 
    	Object result = null;
    	try {
    		result = questionController.checkPoints(null);
        }catch(NullPointerException e) {fail("Exception");};
        assertNull(result);
    }
}
