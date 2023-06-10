package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Controller class for managing course.
 */
public class CourseController {

    /**
     * Retrieves the names of the courses as an ObservableList.
     * 
     * @param coursesLst The list of the courses search in.
     * @return The ObservableList of course names.
     */
    public ObservableList<String> getCourseNames(ArrayList<Course> coursesLst) {
        ObservableList<String> couseNames = FXCollections.observableArrayList();
        for (Course course : coursesLst) {
        	couseNames.add(course.getName());
        }
        return couseNames;
    }
    
    /**
     * Finds a course by its name.
     *
     * @param courseName The name of the course to find.
     * @param courseLst The list of the courses search in.
     * @return The Course object with the specified name, or null if not found.
     */
    public Course findCourseByName(String courseName, ArrayList<Course> courseLst) {
        for (Course course : courseLst) {
            if (course.getName().equals(courseName)) {
                return course;
            }
        }
        return null; // Subject not found
    }

    /**
     * Constructs a database select message to retrieve questions associated with a course.
     *
     * @param course The Course object for whom to retrieve the questions.
     * @return A Msg object representing the database select message.
     */
	public Msg getMsgForQuestions(Course course) {
		Msg msg = new Msg(MsgType.select);
		msg.setSelect("question.*");
		msg.setFrom("cems.question_course, cems.question");
		msg.setWhereCol("question_course.questionId" ,"question.id");
		msg.setWhere("courseNum" ,course.getNumber());
		return msg;
		
	}
}
