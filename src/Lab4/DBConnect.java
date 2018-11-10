package Lab4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class DBConnect {
 
// Code database URL
static final String DB_URL = "jdbc:mysql://localhost:3306/lab4?autoReconnect=true&useSSL=false";
// Database credentials
static final String USER = "root", PASS = "eOncw7)obb";

public Connection connect() throws SQLException {

 return DriverManager.getConnection(DB_URL, USER, PASS);

}
}

