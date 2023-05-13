package controllers.JDBC;

import java.util.ArrayList;

import enteties.Question;
import javafx.collections.ObservableList;

public class DB_controller {
	
	public static String getAllQuestion() {
		return "SELECT * FROM cems.question;";
	}
	
	public static ArrayList<String> updateQuestions(ObservableList<Question> items, ArrayList<Question> arrdup) {
		ArrayList<String> UpdateQueries;
		UpdateQueries = new ArrayList<String>();
		int i = 0;
		for (Question q : items) {
			if (!q.equals(arrdup.get(i))) {
				StringBuilder sb = new StringBuilder();
				sb.append("UPDATE cems.question SET number = '");
				sb.append(q.getNumber());
				sb.append("', question = '");
				sb.append(q.getQuestion());
				sb.append("' WHERE id = '");
				sb.append(q.getID());
				sb.append("';");
				
				//change arrdup
				arrdup.get(i).setNumber(q.getNumber());
				arrdup.get(i).setQuestion(q.getQuestion());
				UpdateQueries.add(sb.toString());
			}
			i++;
		}
		return UpdateQueries;
	}
}