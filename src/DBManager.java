import java.sql.*;

public class DBManager {
	
	public Connection conn;
	
	public DBManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
	Class.forName("com.mysql.jdbc.Driver").newInstance();

	String url="jdbc:mysql://localhost:3306/hw10_final?user=root";
	
	 conn = DriverManager.getConnection(url);
	
	}
	
	public Connection getconn() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

	
	
	return conn;
	
	}
}

