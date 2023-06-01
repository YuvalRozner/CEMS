package gui;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class LecturerStaticsReportController extends AbstractController{

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	protected void initialize() {
		
		XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
		series1.getData().add(new Data<String, Integer>("0-54.9", 7));
		
		XYChart.Series<String, Integer> series2 = new XYChart.Series<String, Integer>();
		series2.getData().add(new Data<String, Integer>("55-64", 10));
		series2.getData().add(new Data<String, Integer>("65-69", 10));
		series2.getData().add(new Data<String, Integer>("70-74", 22));
		series2.getData().add(new Data<String, Integer>("75-79", 23));
		
		
		XYChart.Series<String, Integer> series3 = new XYChart.Series<String, Integer>();
		series3.getData().add(new Data<String, Integer>("80-84", 17));
		series3.getData().add(new Data<String, Integer>("85-89", 8));
		series3.getData().add(new Data<String, Integer>("90-94", 3));
		series3.getData().add(new Data<String, Integer>("95-100", 0));
		barChart.getData().addAll(series1,series2,series3);
		barChart.setBarGap(0); // Adjust the value to make the bars thicker
		barChart.setCategoryGap(0);
		barChart.setLegendVisible(false);
	}
	
	public void backBtn(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide();
		ChatClient.screens.putIfAbsent("lecturerTestView", new LecturerTestViewController());
		ChatClient.getScreen("lecturerTestView").display();
	}
	
}
