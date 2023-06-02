package controllers.JDBC;

import java.util.ArrayList;

import enteties.Question;
import javafx.collections.ObservableList;

public class DB_controller {

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

				// change arrdup
				arrdup.get(i).setNumber(q.getNumber());
				arrdup.get(i).setQuestion(q.getQuestion());
				UpdateQueries.add(sb.toString());
			}
			i++;
		}
		return UpdateQueries;
	}
	
	/* the func gets 4 arraylists of parameters for a select query and return a string of the query. */
	public static String createSELECTquery(ArrayList<String> select, ArrayList<String> from, ArrayList<String> whereCol, ArrayList<Object> whereValue) {
		StringBuilder query = new StringBuilder("SELECT ");
		query.append(separateWithComma(select));
		query.append(" FROM ");
		query.append(separateWithComma(from));
		if(whereCol==null || whereCol.get(0)=="")
			return query.toString()+";";
		//get here if and only if there is WHRE to add:
		query.append(" WHERE ");
		query.append(buildConditionPart(whereCol, whereValue));
		return query.toString()+";";
	}
	
	/* the func gets 5 arraylists of parameters for an update query and return a string of the query. */
	// example for me: UPDATE cems.question SET number = '106', question = 'what is the meaning of zero???' WHERE id = '02106';
	public static String createUPDATEquery(ArrayList<String> tableToUpdate, ArrayList<String> setCol, ArrayList<Object> setValue, ArrayList<String> whereCol, ArrayList<Object> whereValue) {
		if(tableToUpdate==null || tableToUpdate.size()==0 || setCol==null || setCol.size()==0 || setValue==null || setValue.size()==0 )
			return "";
		StringBuilder query = new StringBuilder("UPDATE ");
		query.append(tableToUpdate.get(0)); // append the name of table wanted to be updated.
		query.append(" SET ");
		query.append(buildConditionPart(setCol, setValue)); // append the parameters to be updated.
		if(whereCol==null || whereCol.get(0)=="")
			return query.toString()+";";
		query.append(" WHERE ");
		query.append(buildConditionPart(whereCol, whereValue));
		return query.toString()+";";
	}
	
	/* the func gets an arraylist of string parameters (select/from) for a query and return a string of the parameters separated with commas. */
	private static String separateWithComma(ArrayList<String> lst) {
		if(lst == null || lst.size()==0) return "";
		StringBuilder res = new StringBuilder();
		for(String str : lst)
			res.append(str + ",");
		res.deleteCharAt(res.length()-1);
		return res.toString();
	}
	
	/* the func gets 2 arraylists of string parameters (Condition part) for a query and return a string of the parameters separated with commas. */
	private static String buildConditionPart(ArrayList<String> whereCol, ArrayList<Object> whereValue) {
		if(whereCol==null || whereValue==null || whereCol.size()==0 || whereValue.size()==0 || whereCol.size()!=whereValue.size())
			return "";
		StringBuilder res = new StringBuilder();
		for(int i=0; i<whereCol.size(); i++)
			if(whereValue.get(i) instanceof String) res.append(whereCol.get(i) + "='" + whereValue.get(i).toString() + "',");
			else res.append(whereCol.get(i) + "=" + whereValue.get(i).toString() + ",");
		res.deleteCharAt(res.length()-1);
		return res.toString();
	}
}