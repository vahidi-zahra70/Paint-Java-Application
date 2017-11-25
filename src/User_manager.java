import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User_manager {

	// Validating a user for entrance 
	public boolean validation_of_user(String username, String password, Connection conn) throws SQLException {
		
		boolean is_validate = false;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user where username like '%" + username
				+ "%' and password like '%" + password + "%'");
		if ((rs.next())) {
			is_validate = true;

		}
		if (stmt != null)
			stmt.close();
		if (rs != null)
			rs.close();
		return is_validate;

	}

	
	


	

	
}
