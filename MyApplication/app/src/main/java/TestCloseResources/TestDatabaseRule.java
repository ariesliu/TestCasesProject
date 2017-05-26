package TestCloseResources;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseRule {

	/**
	 * 数据库连接对象中当Statement被关闭后，
	 * 由该Statement初始化的ResultSet对象也会自动关闭
	 * @throws SQLException 
	 */
	public void test_01_BUG() throws SQLException{
		String URL = "jdbc:oracle:thin:@amrood:1521:EMP";
		String USER = "username";
		String PASS = "password";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT a, b FROM TABLE2");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			//con.close();
		}	
	}
	
	/**
	 * 数据库连接对象中当Statement被关闭后，
	 * 由该Statement初始化的ResultSet对象也会自动关闭
	 * @throws SQLException 
	 */
	public void test_02_NOBUG() throws SQLException{
		String URL = "jdbc:oracle:thin:@amrood:1521:EMP";
		String USER = "username";
		String PASS = "password";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT a, b FROM TABLE2");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			stmt.close();
		}	
	}
	
}