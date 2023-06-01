package controllers.JDBC;

import java.io.Serializable;
import java.util.ArrayList;

public class Msg implements Serializable{
	
	private static final long serialVersionUID = 1L; //default serial
	
	private MsgType type;
	private ArrayList<Object> info = new ArrayList<>();
	
	
	public MsgType getType() {
		return type;
	}
	public void setType(MsgType type) {
		this.type = type;
	}
	public ArrayList<Object> getInfo() {
		return info;
	}
	public void setInfo(ArrayList<Object> info) {
		this.info = info;
	}	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getSelectInfo() {
		return (type==MsgType.select)? (ArrayList<Object>) info.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getFromInfo() {
		return (type==MsgType.select)? (ArrayList<Object>) info.get(1) : null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getWhereColInfo() {
		return (type==MsgType.select)? (ArrayList<Object>) info.get(2) : null;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getWhereDataInfo() {
		return (type==MsgType.select)? (ArrayList<Object>) info.get(3) : null;
	}
}
