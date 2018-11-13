/** 
* Name: Stephen Griffith
* Date: 11/14/2018
* Source File Name: Dao.java
* Lab: Lab4
*/

/**
 * The purpose of this class is to provide methods to connect to the 
 * database (DB), create the DB table, insert records into the DB table,
 * and retrieve the records from the DB.
 */

package Lab4;

//import the ResultSet object, SQL exception and Statement interface
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Dao {			//class header

	//Initial DB objects declaration 
	DBConnect cnct = null;			//set DB connect object to null
	Statement stmnt = null;			//set DB statement object to null

	// Dao class constructor
	public Dao() { 					//create instance of the DB class
	cnct = new DBConnect();
	}

	// method to create the DB table
	public void createTable() {
		try {		//try catch block for table creation
			// Open a connection to the DB and alert user about connection status
			System.out.println("Connecting to the BoIIT loan database to create table s_grif_tab...");	//alert user about DB connection status
			
			stmnt = cnct.connect().createStatement();		//assign connection to variable 
			System.out.println("  Successfully connected to the database.");	//alert user about DB connection status
			
			// Execute create query
			System.out.println("\nCreating table s_sgrif_tab in the BoIIT loan database...");	//alert user about table creation status
			
			//sql statement to create table
			String sql = "CREATE TABLE s_grif_tab " 
					+ "(pid INTEGER not NULL AUTO_INCREMENT, " 
					+ " id VARCHAR(100), " 
					+ " income numeric(8,2), " 
					+ " pep VARCHAR(100), " 
					+ " PRIMARY KEY ( pid ))";
			
			stmnt.executeUpdate(sql);		//execute the sql statement
			System.out.println("  Successfully created table s_grif_tab in given database.");	//alert user about table creation status
			
			cnct.connect().close();			//close DB connection
		} catch (SQLException se) {			//catch block for JDBC errors
			// Handle errors for JDBC
			se.printStackTrace();
		}
	}

	//method to insert records into the DB
	public void insertRecords(BankRecords[] recordObjects) {	//method header
		int recCount = 0;			//initialize an accumulator for the number of records inserted
		try {
			// Execute a query
			System.out.println("\nConnecting to the BoIIT loan database to insert DB records...");	//alert user about insertion status
			stmnt = cnct.connect().createStatement();		//connection to DB
			System.out.println("  Successfully connected to the BoIIT loan database.");				//alert user about insertion status
			System.out.println("Inserting records into the table...");								//alert user about insertion status
			System.out.println("  Please be patient, this may take a while...");					//alert user about insertion status
			String sql = null;		//initialize sql statement
		
			// Include all object data to the database table
			for (int i = 0; i < 10; ++i) {		//for loop to walk through records for insert with recordObjects.length
	        	
				//sql statement to insert records data
				sql = "INSERT INTO s_grif_tab(id, income, pep)"
					+ "VALUES (' "+recordObjects[i].getId()+" '"
					+ ", ' "+recordObjects[i].getIncome()+" '"
					+ ", ' "+recordObjects[i].getPep()+" ' )";
				
			stmnt.executeUpdate(sql);		//execute the sql statement
			recCount++;						//increment record count
			}
	        cnct.connect().close();			//close DB connection
		   } catch (SQLException se) { se.printStackTrace(); }
		System.out.println("    " + recCount + " Records inserted into the table successfully.");	//alert user about insertion status
	}

		
	public ResultSet retrieveRecords() {
		 ResultSet rs = null;
		 System.out.println("\nConnecting to the BoIIT loan database to retrieve records...");
		 try {
			stmnt = cnct.connect().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("  Successfully connected to the database for record retrieval.");
		 
		 
		 String sql = "SELECT * FROM s_grif_tab ORDER BY pep DESC";
		 try {
			rs = stmnt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			cnct.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return rs;
	}
}
