package controllers.JDBC;

public enum MsgType {
	
	disconnect, // client ---> server. | indicates that the client want to disconnect from server.
	
	select, // client ---> server. | select query.
	
	update, // client ---> server. | update query.
	
	insert, // client ---> server. | insert query.
	
	manyMessages, // client ---> server. | Msg of list of messages. (probably each msg is a query).
	
	data, // server ---> client. | data from DB, returned by a select query.
	
	succeeded, // server ---> client. | indicate that a query executed successful (update / insert).
	
	succeededAll, // server ---> client. | indicate that list of queries executed successful.
	
	bye, // server ---> client. | indicate that the server got the 'disconnect' msg from client and acted properly.

}
