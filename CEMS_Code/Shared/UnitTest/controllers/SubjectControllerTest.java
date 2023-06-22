package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Subject;

class SubjectControllerTest {

	SubjectController subjectController;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		subjectController = new SubjectController();
	}
	
	//Description: verifies the behavior of the getMsgForCourses method when the subject number is set("44").
	//Input: Course object = Course object with number 44.
	//Expected Result: Msg object should have the type MsgType.select, select all attributes from course table and have a WHERE condition
	//                 by the subject number got as input.
	@Test
    void getMsgForCourses_SubjectNumberSet() {  
        Subject subject = new Subject();
        subject.setNumber("44");
        Msg result = subjectController.getMsgForCourses(subject);
        assertEquals(MsgType.select, result.getType());
        assertEquals("*", result.getSelect().get(0));
        assertEquals("course", result.getFrom().get(0));
        assertEquals("subjectNum", result.getWhere().keySet().iterator().next());
        assertEquals("44", result.getWhere().values().iterator().next());
    }
	
	//Description: verifies the behavior of the getMsgForCourses method when the subject number is not set(null).
	//Input: Course object = Course object with null number.
	//Expected Result: Msg object should be initialize but without a where section(null).
	@Test
    void getMsgForCourses_SubjectNumberNuoSet() {  
        Subject subject = new Subject();
        Msg result = subjectController.getMsgForCourses(subject);
        assertEquals(MsgType.select, result.getType());
        assertEquals("*", result.getSelect().get(0));
        assertEquals("course", result.getFrom().get(0));
        assertEquals(null, result.getWhere());
    }

	//Description: verifies the behavior of the getMsgForQuestions method when the course input is null.
	//Input: Course object = null.
	//Expected Result: Msg object should not be initialize and should return as null.
	@Test
    void getMsgForCourses_SubjectInputIsNull() {  
        Subject subject = null;
        Msg result = null;
        try {
        	result = subjectController.getMsgForCourses(subject);
        }catch(Exception e) {assertTrue(false);}
        assertEquals(null, result);
    }

}
