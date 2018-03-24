package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorSQL {
	
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		
		String dbUrl = "jdbc:mysql://localhost:3306/barometr"
				+                   "?verifyServerCertificate=false" 
				+                    "&useSSL=false" 
				+                    "&requireSSL=false"
				+                    "&useLegacyDatetimeCode=false" 
				+                    "&amp" 
				+                    "&serverTimezone=UTC";
		String username = "root";
		String password = "Ujdbylf1998";
		
		QueryHandler handler = new QueryHandler();
		handler.isConnected();
		handler.insertData("http://test11", "Альбатрос, тікай нахуй", "http://testImage11", "2017-12-12");
		handler.selectData();
		handler.close();
		handler.isConnected();
		
		
	}

}
