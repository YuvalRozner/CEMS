package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import client.ChatClient;
import enteties.TestToExecute;

public class StudentTestController {
	
	public Msg insertStudentTest(TestToExecute testToExecute) {
    	Msg msg = new Msg(MsgType.insert);
    	msg.setTableToUpdate("cems.studenttest");
    	msg.setColNames("studentId,testCode");
    	ArrayList <Object> tmp = new ArrayList<Object>();
    	tmp.add(ChatClient.user.getId());
    	tmp.add(testToExecute.getTestCode());
    	msg.setValues(tmp);
    	return msg;
    }

}