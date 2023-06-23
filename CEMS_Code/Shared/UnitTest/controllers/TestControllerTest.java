package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Question;
import enteties.User;
import javafx.application.Platform;


class TestControllerTest {
	TestController testController;
	
	@BeforeAll
	public static void initializeJavaFXToolkit() {
	    // Initialize the JavaFX toolkit
	    Platform.startup(() -> {});
	}

	@BeforeEach
	void setUp() throws Exception {
		testController = new TestController();
	}

	/**
	 * Description: : verifies the behavior of the electTestByUser method when user is null.
	 * when the user is null.
	 * Input: user=null. 
	 * Expected Result: Null message object.
	 */
	@Test
	void selectTestByUserTestUserIsNull() {
		User user = null;
		Msg actualMsg = null;
		try {
			actualMsg = testController.selectTestByUser(user);
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
	}

	/**
	 * Description:verifies the behavior of the electTestByUser method when a valid user is provided. 
	 * Input: User object with a valid ID = 123456 
	 * Expected Result: Message object with type "select", select field set to "test.*, course.*", from field set to ["test", "user_subject",
	 * "course"], whereCol field containing mapping between "user_subject.subjectNum" and "course.subjectNum", "test.courseNumber" and
	 * "course.number", and where field with "user_subject.userId" key set to the
	 * user's ID.
	 */
	@Test
	void selectTestByUserTestValidUser() {
		User user = new User();
		user.setId("123456");
		Msg actualMsg = null;

		actualMsg = testController.selectTestByUser(user);

		ArrayList<String> arrSelect = new ArrayList<String>();
		arrSelect.add("test.*, course.*");

		ArrayList<String> arrFrom = new ArrayList<String>();
		arrFrom.add("test");
		arrFrom.add("user_subject");
		arrFrom.add("course");

		HashMap<String, Object> hashWherecol = new HashMap<String, Object>();
		hashWherecol.put("user_subject.subjectNum", "course.subjectNum");
		hashWherecol.put("test.courseNumber", "course.number");

		assertEquals(actualMsg.getType(), MsgType.select);
		assertEquals(actualMsg.getSelect(), arrSelect);
		assertEquals(actualMsg.getFrom(), arrFrom);

		assertEquals(hashWherecol, actualMsg.getWhereCol());

		assertEquals("user_subject.userId", actualMsg.getWhere().keySet().iterator().next());
		assertEquals("123456", actualMsg.getWhere().values().iterator().next());
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'id' parameter is null.
	 * Input: id = null, number = "70", courseNumber = "13", duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: actualMsg is null.
	 */
	@Test
	void checkInputsTestIdIsNull() {
		Object actualMsg = null;
		try {
			actualMsg = testController.checkInputs(null,"70","13","300","instructions","instructions");
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
		
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'number' parameter is null.
	 * Input: id = "22222", number = null, courseNumber = "13", duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: actualMsg is null.
	 */
	@Test
	void checkInputsTestNumberIsNull() {
		Object actualMsg = null;
		try {
			actualMsg = testController.checkInputs("22222",null,"13","300","instructions","instructions");
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
		
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'courseNumber' parameter is null.
	 * Input: id = "22222", number = "70", courseNumber = null, duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: actualMsg is null.
	 */
	@Test
	void checkInputsTestCourseNumberIsNull() {
		Object actualMsg = null;
		try {
			actualMsg = testController.checkInputs("22222","70",null,"300","instructions","instructions");
		} catch (Exception e) {
			assertFalse(true);
		}
		try {
			assertEquals(actualMsg, null);
		}catch (Exception e) {
			assertFalse(true);
		}
		
		
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'duration' parameter is null.
	 * Input: id = "22222", number = "70", courseNumber = "13", duration = null, instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: actualMsg is null.
	 */
	@Test
	void checkInputsTestDurationIsNull() {
		Object actualMsg = null;
		try {
			actualMsg = testController.checkInputs("22222","70","13",null,"instructions","instructions");
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
		
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'instructionsForStudent' parameter is null.
	 * Input: id = "22222", number = "70", courseNumber = "13", duration = "300", instructionsForStudent = null, instructionsForLecturer = "instructions"
	 * Expected Result: actualMsg is null.
	 */
	@Test
	void checkInputsTestInstructionsForStudentIsNull() {
		Object actualMsg = null;
		try {
			actualMsg = testController.checkInputs("22222","70","13","300",null,"instructions");
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
		
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'instructionsForLecturer' parameter is null.
	 * Input: id = "22222", number = "70", courseNumber = "13", duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = null
	 * Expected Result: actualMsg is null.
	 */
	@Test
	void checkInputsTestInstructionsForLecturerIsNull() {
		Object actualMsg = null;
		try {
			actualMsg = testController.checkInputs("22222","70","13","300","instructions",null);
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
		
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'id' parameter exceeds the upper limit of 999999.
	 * Input: id = "9999999", number = "90", courseNumber = "13", duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: string - "id is not leagal.\n".
	 */
	@Test
	void checkInputsTestIdIsUpTo999999() {
		Object actualMsg = null;
		actualMsg = testController.checkInputs("9999999","90","13","300","instructions","instructions");
		assertEquals(actualMsg, "id is not leagal.\n");
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'id' parameter is below the lower limit of 10101.
	 * Input: id = "1", number = "90", courseNumber = "13", duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result:string- "id is not leagal.\n".
	 */
	@Test
	void checkInputsTestIdIsDownOf10101() {
		Object actualMsg = null;
		actualMsg = testController.checkInputs("1","90","13","300","instructions","instructions");
		assertEquals(actualMsg, "id is not leagal.\n");
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'number' parameter exceeds the upper limit of 99.
	 * Input: id = "22222", number = "999", courseNumber = "13", duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: string-"test number must be an integer between 01 and 99.\n"
	 */
	@Test
	void checkInputsTestNumberIsUpTo99() {
		Object actualMsg = null;
		actualMsg = testController.checkInputs("22222","999","13","300","instructions","instructions");
		assertEquals(actualMsg, "test number must be an integer between 01 and 99.\n");
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'number' parameter is below the lower limit of 01.
	 * Input: id = "22222", number = "-6", courseNumber = "13", duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: string-"test number must be an integer between 01 and 99.\n"
	 */
	@Test
	void checkInputsTestNumberIsDownOf01() {
		Object actualMsg = null;
		actualMsg = testController.checkInputs("22222","-6","13","300","instructions","instructions");
		assertEquals(actualMsg, "test number must be an integer between 01 and 99.\n");
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'duration' parameter exceeds the upper limit of 500.
	 * Input: id = "22222", number = "70", courseNumber = "13", duration = "550", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result:string- "the test duration must be between 1 and 500.\n"
	 */
	@Test
	void checkInputsTestDurationIsUpTo500() {
		Object actualMsg = null;
		actualMsg = testController.checkInputs("22222","70","13","550","instructions","instructions");
		assertEquals(actualMsg, "the test duration must be between 1 and 500.\n");
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when the 'duration' parameter is below the lower limit of 1.
	 * Input: id = "22222", number = "70", courseNumber = "13", duration = "0", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: string- "the test duration must be between 1 and 500.\n"
	 */
	@Test
	void checkInputsTestDurationIsDownOf1() {
		Object actualMsg = null;
		actualMsg = testController.checkInputs("22222","70","13","0","instructions","instructions");
		try {
			assertEquals(actualMsg, "the test duration must be between 1 and 500.\n");
		}catch(Exception e) {assertFalse(true);}
		
	}
	/**
	 * Description: verifies the behavior of the checkInputs method when all input parameters are valid.
	 * Input: id = "22222", number = "70", courseNumber = "13", duration = "300", instructionsForStudent = "instructions", instructionsForLecturer = "instructions"
	 * Expected Result: An instance of the Test object with the provided inputs.
	 */
	@Test
	void checkInputsTestValidInput() {
		Object actualMsg = null;
		actualMsg = testController.checkInputs("22222","70","13","300","instructions","instructions");
		assertEquals(actualMsg,new enteties.Test("22222","70","13",300,"instructions","instructions"));
	}
	
	/**
	 * Description: verifies the behavior of the insertTest method when the 'test' parameter is null.
	 * Input: test = null, newTest_question = [ArrayList of Question objects]
	 * Expected Result: actualMsg is null.
	 */
	@Test
	void insertTestTest_test_IsNull() {
		Object actualMsg = null;
		ArrayList<Question> newTest_question= new ArrayList<Question>();
		
		try {
			actualMsg = testController.insertTest(null,newTest_question);
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
		
	}
	/**
	 * Description: verifies the behavior of the insertTest when the 'questionList' parameter is null.
	 * Input: test = Test("22222", "70", "13", 300, "instructions", "instructions"), questionList = null
	 * Expected Result: actualMsg is null.
	 */
	@Test
	void insertTestTestQuestionListIsNull() {
		Object actualMsg = null;
		try {
			actualMsg = testController.insertTest(new enteties.Test("22222","70","13",300,"instructions","instructions"),null);
		} catch (Exception e) {
			assertFalse(true);
		}
		assertEquals(actualMsg, null);
	}
	
	/**
	 * description:verifies the behavior of the insertTest when test and question is valid.
	 * Verifies that the method generates the correct messages for inserting a test and its questions.
	 * Input: New test object with a unique ID=22222, number=70, course number=13, duration=300, instructions for students=instruction, and instructions for lecturers=instruction.
	 * List of new questions associated with the test.
	 * Expected Result: Expected messages for inserting the test and its questions into the database.
	 */
	@Test
	void insertTestTestVaildInsert() {
		 // Initialize variables
		Msg actualMsg = null;
		ArrayList<Question> newTest_question= new ArrayList<Question>();
		enteties.Test test=new enteties.Test("22222","70","13",300,"instructions","instructions");
		Question q=new Question();
		q.setId("10");
		q.setPoints(20);
		newTest_question.add(q);
		
		// Invoke the insertTest method
		actualMsg = testController.insertTest(test,newTest_question);
		
		// Prepare expected values for assertions
		ArrayList<String> arrSelect = new ArrayList<String>();
		arrSelect.add("test");
		
		ArrayList<String> arrNames = new ArrayList<String>();
		arrNames.add("id, number, courseNumber, duration, instructionsForStudent, instructionsForLecturer");
		
		ArrayList<Object> arrTestInformation = new ArrayList<Object>();
		arrTestInformation.add(test.getId());
		arrTestInformation.add(test.getNumber());
		arrTestInformation.add(test.getCourseNumber());
		arrTestInformation.add(test.getDuration());
		arrTestInformation.add(test.getInstructionsForLecturer());
		arrTestInformation.add(test.getInstructionsForStudent());
		
		ArrayList<ArrayList<Object>>arrTestInformation1=new ArrayList<ArrayList<Object>>();
		arrTestInformation1.add(arrTestInformation);
		
		ArrayList<String> arrSelect2 = new ArrayList<String>();
		arrSelect2.add("test_question");
		
		ArrayList<String> arrNames2 = new ArrayList<String>();
		arrNames2.add("testId, questionId, points");
		
		ArrayList<Object> arrQuestionInformation = new ArrayList<Object>();
		arrQuestionInformation.add(test.getId());
		arrQuestionInformation.add(q.getId());
		arrQuestionInformation.add(q.getPoints());
		ArrayList<ArrayList<Object>>arrQuestionInformation1=new ArrayList<ArrayList<Object>>();
		arrQuestionInformation1.add(arrQuestionInformation);
		// Assertion 1: Verify the message type is manyMessages
		assertEquals(actualMsg.getType(), MsgType.manyMessages);
		
		// Assertion 2: Verify the first insert message for the test table of the test
		assertEquals(actualMsg.getMsgLst().get(0).getType(),MsgType.insert);
		assertEquals( actualMsg.getMsgLst().get(0).getTableToUpdate(),arrSelect);
		assertEquals(actualMsg.getMsgLst().get(0).getColNames(),arrNames);
		assertEquals( actualMsg.getMsgLst().get(0).getValues(),arrTestInformation1);
		
		// Assertion 3: Verify the second insert message for the test_question table of the question
		assertEquals(actualMsg.getMsgLst().get(1).getType(),MsgType.insert);
		assertEquals( actualMsg.getMsgLst().get(1).getTableToUpdate(),arrSelect2);
		assertEquals(actualMsg.getMsgLst().get(1).getColNames(),arrNames2);
		assertEquals( actualMsg.getMsgLst().get(1).getValues(),arrQuestionInformation1);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
