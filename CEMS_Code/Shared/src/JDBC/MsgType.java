package JDBC;

public enum MsgType {
	
	disconnect, // client ---> server. | indicates that the client want to disconnect from server.
	
	select, // client ---> server. | select query.
	
	update, // client ---> server. | update query.
	
	insert, // client ---> server. | insert query.
	
	manyMessages, // client ---> server. | Msg of list of messages. (probably each msg is a query).
	
	data, // server ---> client. | data from DB, returned by a select query.
	
	user, // server ---> client. | user from DB, returned by a select query.
	
	succeeded, // server ---> client. | indicate that a query executed successful (update / insert).
	
	succeededAll, // server ---> client. | indicate that list of queries executed successful.
	
	bye, // server ---> client. | indicate that the server got the 'disconnect' msg from client and acted properly.
	
	empty, // server ---> client. | indicates that the select query found no data.
	
	insertFail, // server ---> client. | indicates that the insert query faild.
	
	insertSucceeded, // server ---> client. | indicates that the isert query succeeded.
	
	lockTest // server ---> client. | indicates that a test got locked.
}
