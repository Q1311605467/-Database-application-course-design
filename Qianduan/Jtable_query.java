package Frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jtable_query {
	public void getConnectionSqlServer() {
		 
		String driverName =("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String dbURL = "jdbc:sqlserver://213-PC:1433; DatabaseName=BRMSDB"; // 1433�Ƕ˿ڣ�"USCSecondhandMarketDB"�����ݿ�����
		String userName = "sa"; // �û���
		String userPwd = "123456"; // ����
 
		Connection dbConn = null;
		try {
 
			Class.forName(driverName).newInstance();
		} catch (Exception ex) {
			System.out.println("��������ʧ��");
			ex.printStackTrace();
		}
		try {
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("�ɹ��������ݿ⣡");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
 
			try {
				if (dbConn != null)
					dbConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
 
	public static void main(String[] args) {
		Jtable_query getConn = new Jtable_query();
		getConn.getConnectionSqlServer();
 
	}
}