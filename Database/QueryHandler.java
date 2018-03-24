package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryHandler {
	
	private final Connection connection;
	private String dbUrl = "jdbc:mysql://localhost:3306/barometr"
			+                   "?verifyServerCertificate=false" 
			+                    "&useSSL=false" 
			+                    "&requireSSL=false"
			+                    "&useLegacyDatetimeCode=false" 
			+                    "&amp" 
			+                    "&serverTimezone=UTC";
	private String username = "root";
	private String password = "Ujdbylf1998";
	
	public QueryHandler(){
		try {
		this.connection= DriverManager.getConnection(this.dbUrl, this.username, this.password);
		}catch(SQLException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> selectData(){
		List<String> enter = new ArrayList();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("select*from articles");
			while(rs.next()) {
				enter.add(rs.getString(1));
				enter.add(rs.getString(2));
				enter.add(rs.getString(3)); 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		for(String s: enter) {
		System.out.println(s);
		}
		return enter;		
	}
	
	public void insertData(String url, String title, String imageUrl, String date){
		try { 
		Statement statement = this.connection.createStatement();
		try {
		statement.executeUpdate("insert into articles values ('"+url+"','"+title+"','"+imageUrl+"','"+date+"')");
		}catch(SQLIntegrityConstraintViolationException v){
			System.err.println("The entry with url "+url+" already exists");
		}
		}catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void close() {
		try{
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public boolean isConnected() {
		boolean result=true;
		try {
		 result = connection.isClosed();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
}
/*public QueryHandler(String dbUrl, String userName, String password) throws ClassNotFoundException, SQLException {
super();
this.dbUrl = dbUrl;
this.userName = userName;
this.password = password;
Class.forName("com.mysql.jdbc.Driver");
Connection connector1 = DriverManager.getConnection(dbUrl, userName, password);
Statement stmt = connector1.createStatement();
}	 

public void closeConnection() throws SQLException {
connector1.close();
}

public boolean isConnection(Connection con) throws SQLException {
return con.isClosed();
}*/