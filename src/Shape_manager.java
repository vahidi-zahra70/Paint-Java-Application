import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Shape_manager {
	
	public Shape_manager(){
		
	}
	
	public void insertLine(MyShape CC, Connection conn) throws SQLException {
		
		String query="INSERT INTO line (x1,x2,y1,y2,color,user) VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt5=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt5.setInt(1, CC.x1);
		pstmt5.setInt(2, CC.x2);
		pstmt5.setInt(3,CC.y1);
		pstmt5.setInt(4,CC.y2);
		pstmt5.setString(5,CC.color);
		pstmt5.setString(6,CC.user);
		pstmt5.addBatch();
		int[] rs8=pstmt5.executeBatch();
		
		if (pstmt5 != null)
			pstmt5.close();
	}
	
	public void insertCircle(MyShape CC, Connection conn) throws SQLException {
		
		String query="INSERT INTO circle (x1,x2,y1,y2,color,user) VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt5=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt5.setInt(1, CC.x1);
		pstmt5.setInt(2, CC.x2);
		pstmt5.setInt(3,CC.y1);
		pstmt5.setInt(4,CC.y2);
		pstmt5.setString(5,CC.color);
		pstmt5.setString(6,CC.user);
		pstmt5.addBatch();
		int[] rs8=pstmt5.executeBatch();
		
		if (pstmt5 != null)
			pstmt5.close();
	}
	
	public void insertRectangle(MyShape CC, Connection conn) throws SQLException {
		
		String query="INSERT INTO rectangle (x1,x2,y1,y2,color,user) VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt5=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt5.setInt(1, CC.x1);
		pstmt5.setInt(2, CC.x2);
		pstmt5.setInt(3,CC.y1);
		pstmt5.setInt(4,CC.y2);
		pstmt5.setString(5,CC.color);
		pstmt5.setString(6,CC.user);
		pstmt5.addBatch();
		int[] rs8=pstmt5.executeBatch();
		
		if (pstmt5 != null)
			pstmt5.close();
	}
	
	
	public MyShape[] showPreShapes( Connection conn, String username) throws SQLException {
		
		Statement stmt = conn.createStatement();

		
		ResultSet rs = stmt.executeQuery("SELECT count(id) FROM line where user like '%" + username+ "%' ");
		rs.next();
		int numLine=rs.getInt(1);
		
		ResultSet rs2 = stmt.executeQuery("SELECT count(id) FROM circle where user like '%" + username+ "%' ");
		rs2.next();
		int numCircle=rs2.getInt(1);
		
		ResultSet rs3 = stmt.executeQuery("SELECT count(id) FROM rectangle where user like '%" + username+ "%' ");
		rs3.next();
		int numRectangle=rs3.getInt(1);
		
		
		
		MyShape[] shapes=new MyShape[numLine+numCircle+numRectangle];
		
		int k=0;
		
		ResultSet rs1 = stmt.executeQuery("SELECT * FROM line where user like '%" + username+ "%'");
		while ((rs1.next())){
			int x1=rs1.getInt(2);
			int x2=rs1.getInt(3);
			int y1=rs1.getInt(4);
			int y2=rs1.getInt(5);
			String color=rs1.getString(6);
			String user=rs1.getString(7);
			shapes[k]=new Line();
			shapes[k].x1=x1;
			shapes[k].x2=x2;
			shapes[k].y1=y1;
			shapes[k].y2=y2;
			shapes[k].color=color;
			shapes[k].user=user;
			
			k=k+1;
		}
		
		ResultSet rs11 = stmt.executeQuery("SELECT * FROM circle where user like '%" + username+ "%'");
		while ((rs11.next())){
			int x1=rs11.getInt(2);
			int x2=rs11.getInt(3);
			int y1=rs11.getInt(4);
			int y2=rs11.getInt(5);
			String color=rs11.getString(6);
			String user=rs11.getString(7);
			shapes[k]=new Circle();
			shapes[k].x1=x1;
			shapes[k].x2=x2;
			shapes[k].y1=y1;
			shapes[k].y2=y2;
			shapes[k].color=color;
			shapes[k].user=user;
			
			k=k+1;
		}	
		
		ResultSet rs12 = stmt.executeQuery("SELECT * FROM rectangle where user like '%" + username+ "%'");
		while ((rs12.next())){
			int x1=rs12.getInt(2);
			int x2=rs12.getInt(3);
			int y1=rs12.getInt(4);
			int y2=rs12.getInt(5);
			String color=rs12.getString(6);
			String user=rs12.getString(7);
			shapes[k]=new Rectangle();
			shapes[k].x1=x1;
			shapes[k].x2=x2;
			shapes[k].y1=y1;
			shapes[k].y2=y2;
			shapes[k].color=color;
			shapes[k].user=user;
			
			k=k+1;
		}	
		
		if (stmt != null)
			stmt.close();
		if (rs != null)
			rs.close();
		if (rs1 != null)
			rs1.close();
		if (rs2 != null)
			rs2.close();
		if (rs3 != null)
			rs3.close();
		if (rs11 != null)
			rs11.close();
		if (rs12 != null)
			rs12.close();
		
		return shapes;
	}
}
