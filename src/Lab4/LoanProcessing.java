/** 
* Name: Stephen Griffith
* Date: 11/14/2018
* Source File Name: LoadProcessing.java
* Lab: Lab4
*/

package Lab4;

//imports for sql results, exceptions and date/time stamp
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The LoanProcessing class contains the main function that 
 * creates a new bank records object and calls to methods to read
 * data, connects to the database for processing the database 
 * records.
 */
public class LoanProcessing extends BankRecords {
		
	public static void main(String[] args)  {		//main method header
		
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());	//Variable for timestamp
        String idMe = ("Current date = "+ timeStamp + "\nProgrammed by Stephen Griffith \n");	//Variable for programmer credit
        
        System.out.println("\n" + idMe);		//display program credits
		
		System.out.println("\tWelcome to the Bank of IIT (BoIIT) Loan Processing\n");	//Bank of IIT Banner
		
		BankRecords bnkrecs = new BankRecords();			//create new bank records object
		bnkrecs.readData();								//call to read bank records data
		Dao dao = new Dao();						//create a new Dao object
		dao.createTable();							//call to create DB table
		dao.insertRecords(recordObjects); 			//call to insert DB records
		ResultSet rsltset = dao.retrieveRecords(); 		//populate the results set
		//String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());	//Variable for timestamp
        //String idMe = ("Current date = "+ timeStamp + "\nProgrammed by Stephen Griffith \n");	//Variable for programmer credit


		System.out.println("Records were successfully retrieved.");		//alert user about records received status
		System.out.println("\nLoan Analysis Report is being generated...");	//alert to user about report generation
		try {
			Thread.sleep (3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	// Create heading for display
		System.out.println("\nBank of IIT Loan Analysis Report");
		System.out.println("\n   ID:" + "\t\t  INCOME" + "\t PEP");
		System.out.println(" -------" + "\t ---------" + "\t-----");
	
	// Extract data from result set
		try {	//try block for results getting
			while (rsltset.next()) {

				// Retrieve data by column name for reporting
				String id = rsltset.getString("id");
				double income = Double.parseDouble(rsltset.getString("income"));
				String pep = rsltset.getString("pep");
			
				// Display values for id,income,pep
				System.out.print(id);
				System.out.printf("\t%,10.2f", income);
				System.out.println("\t" + pep);
			  }
		} catch (SQLException e) {	//catch block for SQL errors
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {				//try block for closing results set
		rsltset.close();		//close results set
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // closes result set object
	
	//System.out.println("\n" + idMe);		//display program credits
}

}