package controllers.JDBC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Msg implements Serializable{
	
	private static final long serialVersionUID = 1L; //default serial
	
	private MsgType type;
	private ArrayList<String> select = null; // for the SELECT part of query.
	private ArrayList<String> from = null; // for the FROM part of query.
	private HashMap<String, Object> where = null; // for the WHERE part of query.
	private ArrayList<String> tableToUpdate = null; // for the UPDATE part of query.
	private HashMap<String, Object> set = null; // for the SET part of query.
	private ArrayList<Msg> msgLst = null; //used to send a bunch of messages all at once.
	private Object data = null; //data from DB to client.
	private String dataType = null;


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
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
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
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String arrayList) {
		this.dataType = arrayList;
	}

	@Override
	public String toString() {
		return "Msg [type=" + type ;
	}
	
	
}
