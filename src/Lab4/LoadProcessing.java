/** 
* Name: Stephen Griffith
* Date: 11/14/2018
* Source File Name: LoadProcessing.java
* Lab: Lab4
*/

package Lab4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoadProcessing extends BankRecords {
		
	public static void main(String[] args)  {
		BankRecords br = new BankRecords();
		br.readData();
		Dao dao = new Dao();
		dao.createTable();
		dao.insertRecords(recordObjects); // perform inserts
		ResultSet rs = dao.retrieveRecords(); // fill result set object
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());	//Variable for timestamp
        String idMe = ("Current date = "+ timeStamp + "\nProgrammed by Stephen Griffith \n");	//Variable for programmer credit


		System.out.println("Records were successfully retrieved.");
		System.out.println("\nLoan Analysis Report is being generated...");
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
		try {
			while (rs.next()) {

				// Retrieve data by column name (i.e., for id,income,pep)
				String id = rs.getString("id");
				double income = Double.parseDouble(rs.getString("income"));
				String pep = rs.getString("pep");
			
				// Display values for id,income,pep
				//System.out.println(id + "\t" + income + "\t\t" + pep);
				System.out.print(id);
				System.out.printf("\t%,10.2f", income);
				System.out.println("\t" + pep);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // closes result set object
	
	System.out.println("\n" + idMe);
}

}