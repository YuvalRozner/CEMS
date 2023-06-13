package controllers;

import java.util.ArrayList;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.Request;
import enteties.TestToExecute;
import enteties.User;

/**
 * Controller class for managing Request.
 */
public class RequestController {
	
    /**
     * creates and returns a Request object base on the data in a TestToExecute object.
     *
     * @param test The TestToExecute to create request by.
     * @return An Object representing the Request if input are valid or error String if inputs are not valid.
     */
	public Object createRequestByTest(TestToExecute test, User hod, User lecturer) {
		String error = new String("");
    	try {
    		if(test.getTest().getDuration().equals(Integer.valueOf(test.getTextField().getText())))
    			error+="The new duration you enter is the same as the old one.\n";
    		if(Integer.valueOf(test.getTextField().getText())<1 || Integer.valueOf(test.getTextField().getText())>500)
				error+="The duration must be an integer between 1 and 500 (minutes).\n";
    	}catch(Exception e) {error+="The duration must be an integer between 1 and 500 (minutes).\n";}
    	if(test.getTextField1().getText().length()==0) error+="You must write an explanation for changing the duration.\n";
    	if(error.length()!=0) return error;
		Request request = new Request();
		request.setTestCode(test.getTestCode());
		request.setLecturerId(lecturer.getId());
		request.setHodId(hod.getId());
		request.setDuration(Integer.valueOf(test.getTextField().getText()));  
		request.setExplanation(test.getTextField1().getText());
		return request;
	}
	
    /**
     * creates and returns a Msg for inserting a TestToExecute to DB.
     *
     * @param request The Request to insert.
     * @return A Msg object representing the database insert message.
     */
	public Msg insertRequest(Request request) {
		Msg msg = new Msg(MsgType.insert);
		msg.setTableToUpdate("cems.request");
		msg.setColNames("testCode, lecturerId, hodId, duration, explanation");
		ArrayList<Object> tmp = new ArrayList<>();
		tmp.add(request.getTestCode());
		tmp.add(request.getLecturerId());
		tmp.add(request.getHodId());
		tmp.add(request.getDuration());
		tmp.add(request.getExplanation());
		msg.setValues(tmp);
		return msg;
	}
}