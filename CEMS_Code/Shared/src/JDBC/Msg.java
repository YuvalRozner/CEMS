package JDBC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Msg implements Serializable{
	
	private static final long serialVersionUID = 1L; //default serial
	
	private MsgType type;
	private ArrayList<String> select = null; // for the SELECT part of query.
	private ArrayList<String> from = null; // for the FROM part of query.
	private HashMap<String, Object> where = null; // for the WHERE part of query.
	private ArrayList<String> tableToUpdate = null; // for the UPDATE part of query.
	private HashMap<String, Object> set = null; // for the SET part of query.
	private ArrayList<Msg> msgLst = null; //used to send a bunch of messages all at once.
	private ArrayList<ArrayList<Object>> data = null; //data from DB to client.
	private ArrayList<String> colNames = null;
	private ArrayList<ArrayList<Object>> values = null;

	/* original empty constructor. */
	public Msg() {}
	
	/* constructor using field MsgType. */
	public Msg(MsgType type) {
		this.type = type;
	}
	
	public ArrayList<String> getSelect() {
		return select;
	}

	public ArrayList<String> getFrom() {
		return from;
	}

	public MsgType getType() {
		return type;
	}
	
	public void setType(MsgType type) {
		this.type = type;
	}
	
	public HashMap<String, Object> getWhere() {
		return where;
	}

	public HashMap<String, Object> getSet() {
		return set;
	}
	
	public ArrayList<String> getTableToUpdate() {
		return tableToUpdate;
	}
	
	public ArrayList<Msg> getMsgLst() {
		return msgLst;
	}

	public void setMsgLst(Msg msg) {
		if(msgLst==null) msgLst = new ArrayList<Msg>();
		msgLst.add(msg);
	}
	
	public void setMsgLst(ArrayList<Msg> msgLst) {
		this.msgLst = msgLst;
	}
	
	public ArrayList<ArrayList<Object>> getData() {
		return data;
	}

	public void setData(ArrayList<ArrayList<Object>> data) {
		this.data = data;
	}	
	
	public void setSelect(String sel) {
		if(select==null) select=new ArrayList<String>();
		select.add(sel);
	}
	
	public void setFrom(String fr) {
		if(from==null) from=new ArrayList<String>();
		from.add(fr);
	}
	
	public void setWhere(String whereCol, Object whereValue) {
		if(where==null) where=new HashMap<String, Object>();
		where.put(whereCol, whereValue);
	}
	
	public void setTableToUpdate(String table) {
		if(tableToUpdate==null) tableToUpdate=new ArrayList<String>();
		tableToUpdate.add(table);
	}
	
	public void setSet(String setCol, Object setValue) {
		if(set==null) set=new HashMap<String, Object>();
		set.put(setCol, setValue);
	}

	@Override
	public String toString() {
		return "Msg [type=" + type +"]" ;
	}
	
	public ArrayList<String> getColNames() {
		return colNames;
	}

	public void setColNames(ArrayList<String> colNames) {
		this.colNames = colNames;
	}
	
	public void setColNames(String name) {
		if(colNames==null) colNames=new ArrayList<String>();
		colNames.add(name);
	}

	public ArrayList<ArrayList<Object>> getValues() {
		return values;
	}
	
	public void setValues(ArrayList<Object> lst) {
		if(values==null) values=new ArrayList<ArrayList<Object>>();
		values.add(lst);
	}

	/**
	 * @param <T>  class type of wanted return
	 * @param type ClassName.class , ClassName of wanted type return
	 * @return List of wanted class
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> convertData(Class<T> type) {
		ArrayList<T> converted = new ArrayList<>();
		try {
			for (List<Object> dataRow : data)
				converted.add((T) type.getConstructors()[0].newInstance(dataRow.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return converted;
	}
	
}
