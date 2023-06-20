package gui;

import JDBC.Msg;
import enteties.User;

public interface ChatClientInterface {
	void sendMsgIF(Msg msg);
	User getUser();
	void setUser(User user);
}
