package controllers.JDBC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Msg implements Serializable{
	
	private static final long serialVersionUID = 1L; //default serial
	
	private MsgType type;
	private ArrayList<String> select = null;
	private ArrayList<String> from = null;
	private HashMap<String, Object> where = null;
	private ArrayList<String> tableToUpdate = null;
	private HashMap<String, Object> set = null;
	
	//private ArrayList<Object> dataToClient;
	
	
	
	
	
	private ArrayList<Object> info = new ArrayList<>();
	
	public Msg() {
	}
	
	//constructor using field.
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Object> getInfo() {
		return info;
	}
	public void setInfo(ArrayList<Object> info) {
		this.info = info;
	}	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getSelectInfo() {
		return (type==MsgType.select)? (ArrayList<String>) info.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getFromInfo() {
		return (type==MsgType.select)? (ArrayList<String>) info.get(1) : null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getWhereColInfo() {
		if(type==MsgType.select) return (ArrayList<String>) info.get(2); //in select msg, the whereCol is in index 2
		if(type==MsgType.update) return (ArrayList<String>) info.get(3); //in update msg, the whereCol is in index 3
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getWhereValueInfo() {
		if(type==MsgType.select) return (ArrayList<Object>) info.get(3); //in select msg, the whereCol is in index 3
		if(type==MsgType.update) return (ArrayList<Object>) info.get(4); //in update msg, the whereCol is in index 4
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getTableToUpdateInfo() {
		return (type==MsgType.update)? (ArrayList<String>) info.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getSetColInfo() {
		return (type==MsgType.update)? (ArrayList<String>) info.get(1) : null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getSetValueInfo() {
		return (type==MsgType.update)? (ArrayList<Object>) info.get(2) : null;
	}
	
	@Override
	public String toString() {
		return "Msg [type=" + type + ", info=" + info + "]";
	}
}
