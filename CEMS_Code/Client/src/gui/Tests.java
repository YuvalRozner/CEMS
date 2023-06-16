package gui;

import enteties.StudentTest;
import enteties.TestToExecute;

public interface Tests {
	public StudentTest getStudentTestToShow();
	public TestToExecute getTestToExecuteToShow();
	public String getScreenState();
}
