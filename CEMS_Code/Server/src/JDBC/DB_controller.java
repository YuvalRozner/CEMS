package JDBC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DB_controller {

	/**
	 * make a query to update integer value by value.
	 * @param tableToUpdate
	 * @param set
	 * @param where
	 * @return String update query. 
	 */
	public static String createUPDATEPlusOnequery(ArrayList<String> tableToUpdate, HashMap<String, Object> set, HashMap<String, Object> where) {
		if (tableToUpdate == null || set == null)
			return "";
		StringBuilder query = new StringBuilder("UPDATE ");
		query.append(tableToUpdate.get(0)); // append the name of table wanted to be updated.
		query.append(" SET ");
		query.append(buildConditionPlusOnePartWithComma(set)); // append the parameters to be updated.
		if (where == null)
			return query.toString() + ";";
		query.append(" WHERE ");
		query.append(buildConditionPartWithAnd(where, true));
		return query.toString() + ";";
	}
	
	/**
	 * build string of the parameters separated with commas.
	 * @param condition
	 * @return String set part of update query.
	 */
	private static String buildConditionPlusOnePartWithComma(HashMap<String, Object> condition) {
		if (condition == null)
			return "";
		StringBuilder res = new StringBuilder();

		for (Map.Entry<String, Object> entry : condition.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			res.append(key + "=" + key+"+"+ value.toString() + ",");
		}
		System.out.println("res is : "+res);
		res.deleteCharAt(res.length() - 1); // remove last comma.
		return res.toString();
	}

	/* the func gets 4 arraylists of parameters for a select query and return a string of the query. */
	public static String createSELECTquery(ArrayList<String> select, ArrayList<String> from, HashMap<String, Object> where, HashMap<String, Object> whereCol) {
		StringBuilder query = new StringBuilder("SELECT ");
		query.append(separateWithComma(select));
		query.append(" FROM ");
		query.append(separateWithComma(from));
		if (where == null)
			return query.toString() + ";";
		// get here if and only if there is WHRE to add:
		query.append(" WHERE ");
		query.append(buildConditionPartWithAnd(where, true));
		if(whereCol!=null && where!=null) query.append(" AND ");
		query.append(buildConditionPartWithAnd(whereCol, false));
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
		query.append(buildConditionPartWithAnd(where, true));
		return query.toString() + ";";
	}

	/*  */
	// example for me: INSERT INTO lab3.students (ID, FirstName, LastName,Faculty) VALUES ('1', 'Dor','shabat', 'a'), ('2', 'Dor1','shabat1', 'b');
	public static String createINSERTquery(ArrayList<String> tableToUpdate, ArrayList<String> colNames, ArrayList<ArrayList<Object>> values) {
		if (tableToUpdate == null || colNames == null || values == null || colNames.size() != values.size())
			return "";
		StringBuilder query = new StringBuilder("INSERT INTO ");
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
		if (lists == null || lists.size() == 0)	return "";
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
	private static String buildConditionPartWithAnd(HashMap<String, Object> condition, boolean needGeresh) {
		if (condition == null)
			return "";
		StringBuilder res = new StringBuilder();

		for (Map.Entry<String, Object> entry : condition.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (needGeresh && value instanceof String)
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