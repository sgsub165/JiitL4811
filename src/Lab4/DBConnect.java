/** 
* Name: Stephen Griffith
* Date: 11/14/2018
* Source File Name: DBConnect.java
* Lab: Lab4
*/

package Lab4;

/**
 * The purpose of this class is to provide a method to connect
 * to the database.
 */

//import the java DB connection interface, JDBC management class and SQL exception
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class DBConnect {		//class header
 
	// define the URL for the DB to be connected
	static final String DB_URL = "jdbc:mysql://localhost:3306/lab4?autoReconnect=true&useSSL=false";
	// define the DB user credentials
	static final String USER = "root", PASS = "eOncw7)obb";

	//DB connect method with exception handling
	public Connection connect() throws SQLException {

		return DriverManager.getConnection(DB_URL, USER, PASS);	//return elements for connection

	}
}

