package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Course;
import enteties.Subject;
import enteties.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Controller class for managing subjects.
 */
public class SubjectController {
	
    /**
     * Finds a subject by its name.
     *
     * @param subjectName The name of the subject to find.
     * @param subjectsLst The list of the subjects search in.
     * @return The Subject object with the specified name, or null if not found.
     */
    public Subject findSubjectByName(String subjectName, ArrayList<Subject> subjectsLst) {
        for (Subject subject : subjectsLst) {
            if (subject.getName().equals(subjectName)) {
                return subject;
            }
        }
        return null; // Subject not found
    }
    
    /**
     * Retrieves the names of the subjects as an ObservableList.
     * 
     * @param subjectsLst The list of the subjects search in.
     * @return The ObservableList of subject names.
     */
    public ObservableList<String> getSubjectNames(ArrayList<Subject> subjectsLst) {
        ObservableList<String> subjectNames = FXCollections.observableArrayList();
        for (Subject subject : subjectsLst) {
            subjectNames.add(subject.getName());
        }
        return subjectNames;
    }
    
    /**
     * Returns the list of courses associated with the selected subject,
     * with their checkBoxes initialized.
     *
     * @param subject The selected subject.
     * @return The list of courses with initialized checkBoxes.
     */
    public ArrayList<Course> returnCoursesWithCheckbox(Subject subject) {
    	ArrayList<Course> courses = subject.getCourses();
    	for (Course course : courses) {
    		course.setNewCheckbox();
        }
        return courses; 
    }
    
    
    public Msg selectSubjectByUser(User user) {
    	Msg msg = new Msg(MsgType.select);
    	msg.setSelect("subject.number, subject.name");
    	msg.setFrom("cems.subject, cems.user_subject");
    	msg.setWhereCol("subject.number", "user_subject.subjectNum"); 
    	msg.setWhere("user_subject.userId", user.getId()); 
    	return msg;
    }
}
