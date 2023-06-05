package gui;

import controllers.JDBC.Msg;
import controllers.JDBC.MsgType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController extends AbstractController {
	
	@FXML
	private Button btnShow = null;
    
	public void show(ActionEvent event) throws Exception {
		Msg tmpMsg = new Msg(MsgType.select);
		tmpMsg.setSelect("*");
		tmpMsg.setFrom("question");
		
		try {
			sendMsg(tmpMsg);
		}catch(Throwable t) {System.out.println("accept dont work");};
		
		//the start of the new table
		new QuestionTableController().start("questionTable", "menu");
	}
}
