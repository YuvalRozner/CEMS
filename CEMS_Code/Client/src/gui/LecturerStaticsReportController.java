package gui;

import java.util.ArrayList;

import JDBC.Msg;
import client.ChatClient;
import controllers.TestToExecuteController;
import enteties.Course;
import enteties.TestToExecute;
import enteties.User;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;

public class LecturerStaticsReportController extends AbstractController{

	public static User selectedLecturer, selectedStudent;
	public static Course selectedCourse;
	//enum
	
	@FXML
    private Label avg;

    @FXML
    private Label median;
	
	@FXML
	private BarChart<String, Integer> barChart;
	
	/**
	 * object to use the TestToExecuteController class method.
	 */
    private static TestToExecuteController testToExecuteController = new TestToExecuteController();
    
    /**
	 * the list of TestToExecute for the BarChart according to the tests to execute of the user.
	 */
    private ArrayList<TestToExecute> testToExecuteLst;
	
	/**
     * Default constructor for the LecturerStaticsReportController class.
     * Initializes the data for BarChart.
     */
    public LecturerStaticsReportController() {
    	if(ChatClient.lastCurrentScreen instanceof ChooseReportTypeController) {
    		switch(((ChooseReportTypeController)ChatClient.lastCurrentScreen).reportType) {
    			case "lecturer": 
    				User lecturerselected = ((ChooseReportTypeController)ChatClient.lastCurrentScreen).selectedLecturer;
    				System.out.println("User = " + selectedLecturer);
    				Msg msg = testToExecuteController.selectTestToExecuteByLecturer(selectedLecturer.getId());
    		    	sendMsg(msg);
    		    	testToExecuteLst = msgReceived.convertData(TestToExecute.class);
    		    	System.out.println("Data = " + AbstractController.msgReceived.getData());
    				break;
    		}
    	}
    	
    }

    
    
	@SuppressWarnings("unchecked")
	@FXML
	protected void initialize() {
    	
		//Create series for the BarChart
	    XYChart.Series<String, Integer> series1 = new XYChart.Series<>(); //red
	    XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); //orange
	    XYChart.Series<String, Integer> series3 = new XYChart.Series<>(); //green

	    // Initialize the distribution array
	    int[] distribution = new int[10];

	    //Iterate over the testToExecuteLst and populate the distribution array
	    for (TestToExecute test : testToExecuteLst) {
	    	System.out.println("test = " + test);
	    	Integer[] distributionPerTest = test.getDistribusion();
	    	for(int i=0; i<distribution.length; i++) {
	    		distribution[i]+=distributionPerTest[i];
	    	}
	    }

	    //Add the data points to the series
	    for (int i = 0; i < distribution.length; i++) {
            if (i >= 0 && i<5) {
            	String label = getLabelForIndex(i); //Replace with your label for each index
                series1.getData().add(new Data<>(label, distribution[i]));
            } else if (i >=5  && i<8){
                String label = getLabelForIndex(i); //Replace with your label for each index
                series2.getData().add(new Data<>(label, distribution[i]));
            } else {
            	String label = getLabelForIndex(i); //Replace with your label for each index
                series3.getData().add(new Data<>(label, distribution[i]));
            }
	    }

	    //Add the series to the BarChart
	    barChart.getData().addAll(series1,series2,series3);
	    barChart.setBarGap(0); //Adjust the value to make the bars thicker
	    barChart.setCategoryGap(0);
	    barChart.setLegendVisible(false);
	}
	
	private String getLabelForIndex(int index) {
	    int lowerBound = index * 10;
	    int upperBound = (index * 10) + 10;
	    return lowerBound + "-" + upperBound;
	}
}
