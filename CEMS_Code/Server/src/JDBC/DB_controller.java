package JDBC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enteties.Question;
import javafx.collections.ObservableList;

public class DB_controller {

	////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Msg> updateQuestions1(ObservableList<Question> items, ArrayList<Question> arrdup) {
		ArrayList<Msg> UpdateQueries = new ArrayList<Msg>();
		int i = 0;
		for (Question q : items) {
			if (!q.equals(arrdup.get(i))) {
				Msg tmpMsg = new Msg(MsgType.update);
				tmpMsg.setTableToUpdate("question");
				tmpMsg.setSet("number", q.getNumber());
				tmpMsg.setSet("question", q.getQuestion());
				tmpMsg.setWhere("id", q.getID());
				UpdateQueries.add(tmpMsg);
			}
			i++;
		}
		return UpdateQueries;
	}

	/* the func gets 4 arraylists of parameters for a select query and return a string of the query. */
	public static String createSELECTquery(ArrayList<String> select, ArrayList<String> from, HashMap<String, Object> where) {
		StringBuilder query = new StringBuilder("SELECT ");
		query.append(separateWithComma(select));
		query.append(" FROM ");
		query.append(separateWithComma(from));
		if (where == null)
			return query.toString() + ";";
		// get here if and only if there is WHRE to add:
		query.append(" WHERE ");
		query.append(buildConditionPartWithAnd(where));
		return query.toString() + ";";
	}

	/* the func gets 5 arraylists of parameters for an update query and return a string of the query. */
	// example for me: UPDATE cems.question SET number = '106', question = 'what is the meaning of zero???' WHERE id = '02106';
	public static String createUPDATEquery(ArrayList<String> tableToUpdate, HashMap<String, Object> set, HashMap<String, Object> where) {
		if (tableToUpdate == null || set == null)
			return "";
		StringBuilder query = new StringBuilder("UPDATE ");
		query.append(tableToUpdate.get(0)); // append the name of table wanted to be updated.
		query.append(" SET ");
		query.append(buildConditionPartWithComma(set)); // append the parameters to be updated.
		if (where == null)
			return query.toString() + ";";
		query.append(" WHERE ");
		query.append(buildConditionPartWithAnd(where));
		return query.toString() + ";";
	}

	/*  */
	// example for me: INSERT INTO lab3.students (ID, FirstName, LastName,Faculty) VALUES ('1', 'Dor','shabat', 'a'), ('2', 'Dor1','shabat1', 'b');
	public static String createINSERTquery(ArrayList<String> tableToUpdate, ArrayList<String> colNames, ArrayList<ArrayList<Object>> values) {
		if (tableToUpdate == null || colNames == null || values == null || colNames.size() != values.size())
			return "";
		StringBuilder query = new StringBuilder("INSET INTO ");
		query.append(tableToUpdate.get(0)); // append the name of table wanted to be insert to.
		query.append(" (");
		query.append(separateWithComma(colNames)); // append the columns names to be updated.
		query.append(") VALUES ");
		query.append(separateValuesWithComma(values)); // append the values to insert.
		return query.toString() + ";";
	}

	/* the func gets an arraylist of string parameters (select/from) for a query and return a string of the parameters separated with commas. */
	private static String separateWithComma(ArrayList<String> lst) {
		if (lst == null || lst.size() == 0)
			return "";
		StringBuilder res = new StringBuilder();
		for (String str : lst)
			res.append(str + ",");
		res.deleteCharAt(res.length() - 1);
		return res.toString();
	}

	/* the func gets an arraylist of arraylist of objects (values) for insert query and return a string of the parameters separated with commas int breclets. */
	private static String separateValuesWithComma(ArrayList<ArrayList<Object>> lists) {
		if (lists == null || lists.size() == 0)
			return "";
		StringBuilder res = new StringBuilder();
		for (ArrayList<Object> lst : lists) {
			res.append("(");
			for (Object val : lst) {
				if (val instanceof String)
					res.append("'" + val + "',");
				else
					res.append(val + ",");
			}
			res.deleteCharAt(res.length() - 1);
			res.append("),");
		}
		res.deleteCharAt(res.length() - 1);
		return res.toString();
	}

	/* the func gets hashMap (Condition part) for a query and return a string of the parameters separated with commas. */
	private static String buildConditionPartWithComma(HashMap<String, Object> condition) {
		if (condition == null)
			return "";
		StringBuilder res = new StringBuilder();

		for (Map.Entry<String, Object> entry : condition.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String)
				res.append(key + "='" + value.toString() + "',");
			else
				res.append(key + "=" + value.toString() + ",");
		}
		res.deleteCharAt(res.length() - 1); // remove last comma.
		return res.toString();
	}

	/* the func gets hashMap (Condition part) for a query and return a string of the parameters separated with commas. */
	private static String buildConditionPartWithAnd(HashMap<String, Object> condition) {
		if (condition == null)
			return "";
		StringBuilder res = new StringBuilder();

		for (Map.Entry<String, Object> entry : condition.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String)
				res.append(key + "='" + value.toString() + "' AND ");
			else
				res.append(key + "=" + value.toString() + " AND ");
		}
		res.deleteCharAt(res.length() - 1); // remove last comma.
		res.deleteCharAt(res.length() - 1); // remove last comma.
		res.deleteCharAt(res.length() - 1); // remove last comma.
		res.deleteCharAt(res.length() - 1); // remove last comma.
		return res.toString();
	}
}