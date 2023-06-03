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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	@Override
	public String toString() {
		return "Msg [type=" + type ;
	}
}
