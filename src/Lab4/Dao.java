package Lab4;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

	//Declare DB objects 
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public Dao() { //create db object instance
	conn = new DBConnect();
	}

	// CREATE TABLE METHOD
	public void createTable() {
		try {
			// Open a connection
			System.out.println("Connecting to a selected database to create Table...");
			System.out.println("Connected database successfully...");
			
			// Execute create query
			System.out.println("Creating table in given database...");

//			stmt = conn.connect().createStatement();
//			String sql1 = "DROP TABLE s_grif_tab";
//			stmt.executeUpdate(sql1);

			// Execute create query
			System.out.println("Creating table in given database...");

			stmt = conn.connect().createStatement();

			String sql = "CREATE TABLE s_grif_tab " 
					+ "(pid INTEGER not NULL AUTO_INCREMENT, " 
					+ " id VARCHAR(100), " 
					+ " income numeric(8,2), " 
					+ " pep VARCHAR(100), " 
					+ " PRIMARY KEY ( pid ))";
			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			
			conn.connect().close(); //close db connection 
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
	}

	// INSERT INTO METHOD
	public void insertRecords(BankRecords[] recordObjects) {
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.connect().createStatement();
			String sql = null;
		
			// Include all object data to the database table
			for (int i = 0; i < recordObjects.length; ++i) {
				
//				System.out.println(recordObjects[i].getId());
//				System.out.println(recordObjects[i].getIncome());
//				System.out.println(recordObjects[i].getPep());
	        				
				sql = "INSERT INTO s_grif_tab(id, income, pep)"
					+ "VALUES (' "+recordObjects[i].getId()+" '"
					+ ", ' "+recordObjects[i].getIncome()+" '"
					+ ", ' "+recordObjects[i].getPep()+" ' )";
				
			stmt.executeUpdate(sql);
			}
	        conn.connect().close();
		   } catch (SQLException se) { se.printStackTrace(); }
	}

	public ResultSet retrieveRecords() {
		 ResultSet rs = null;

		 try {
			stmt = conn.connect().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 String sql = "SELECT * FROM s_grif_tab ORDER BY pep DESC";
		 try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			conn.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return rs;
	}
}
