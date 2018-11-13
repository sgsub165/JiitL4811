/** 
* Name: Stephen Griffith
* Date: 11/12/2018
* Source File Name: Records.java
* Lab: Lab4
*/

package Lab4;

//Import the required java libraries
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Records class is a sub-class of the BankRecords class.
 * The purpose of the Records class is to provide data analytics
 * for various permutations of the BankRecords objects.
 */
public class Records extends BankRecords{
		
	   //create a formatted object to write output directly to the
	   //console and to a file
	static FileWriter fileWrite = null;		//initialize fileWrite to null
		public Records(){
			
			//try catch blocks to handle file exceptions
			try {
				fileWrite = new FileWriter("BankRecords.txt");
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	
	    //the main method of the Records class creates a Records object
		//calls the methods to read data and perform analytics
	public static void main(String[] args)  {
        Records br = new Records();				//create Records object br
        br.readData();							//call the read data method
        //call functions to perform analytics 
    	    AverageIncome();  				// analyze average income for females and males
    	    CompareFemaleMortgage();		// analyze females with mortgage and savings account
    	    CompareMaleRegion();    		// analyze male count per region
    	    CreditTime();			// record and print the date & time stamp and programmer credit

    	    
    	  try {
			fileWrite.close();		//close file object//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * the AverageIncome method takes the sorted data from the gender
	 * comparator and filters to produce the average income data
	 * for females and males for printing to console and the output file
	 */
	private static void AverageIncome() {
		Arrays.sort(recordObjects,new GenderComparator());		//create a new GenderComparator object
		//define and initialize variables
 		double femaleIncomeSum = 0, maleIncomeSum=0, femaleHeadCount=0, maleHeadCount=0;
	
 		for (int i=0; i<recordObjects.length; i++)		//for loop to walk through the bank record objects
 			if (recordObjects[i].getGender().equals("FEMALE")) { 	//if statement for gender is female
 				femaleIncomeSum += recordObjects[i].getIncome();	//assign record objects for income to accumulator variable
 				++femaleHeadCount;			//increment the female accumulator
 			}
 			else if												//else if statement for gender is male
			(recordObjects[i].getGender().equals("MALE")) {		
				maleIncomeSum += recordObjects[i].getIncome();		//assign record objects for income to accumulator variable
				++maleHeadCount;			//increment the male accumulator
			}

		//print resulting averages to console and file
		double femaleAverageIncome = femaleIncomeSum/(femaleHeadCount);		//calculate the average female income
		femaleAverageIncome = Math.round(femaleAverageIncome * 100) / 100.0;	//format to 2 decimal places for fileWrite
		double maleAverageIncome = maleIncomeSum/(maleHeadCount);			//calculate the average male income
		maleAverageIncome = Math.round(maleAverageIncome * 100) / 100.0;	//format to 2 decimal places for fileWrite
		
		//print averages to console
		System.out.println("Welcome to the Bank of IIT");
		System.out.println("Data Analytics Result:\n");
		System.out.printf("Average Income for Females: $%,.2f \n", femaleAverageIncome);
		System.out.printf("Average Income for Males: $%,.2f \n", maleAverageIncome);
	 
	     	try {			//try and catch blocks to print averages to file
		 	fileWrite.write("Welcome to the Bank of IIT\n");
	     	fileWrite.write("Data Analytics Result:\n\n"); 
	     	fileWrite.write("Average Income for Females: $" + femaleAverageIncome + "\n");
		 	fileWrite.write("Average Income for Males: $" + maleAverageIncome + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		   }  
	}
	
	/**
	 * method to analyze and print records where
	 * gender is female having a mortgage and savings
	 * account
	 */
	private static void CompareFemaleMortgage() {
		Arrays.sort(recordObjects,new FemaleComparator());		//create a new FemaleComparator object
		int femaleCount = 0;			//define and initialize the female accumulator variable
		for (int i = 0; i < recordObjects.length; i++) {		//for loop to walk the bank records
			if (recordObjects[i].getGender().equals("FEMALE") 		//if statement to filter gender is female
					&& recordObjects[i].getMortgage().equals("YES") 	//mortgage is yes
					&& recordObjects[i].getSave_act().equals("YES"))	//savings account is yes
		femaleCount++;		//increment the female accumulator variable
		}
		//print processed analytics
		System.out.println("\nNumber of Females with a Mortgage and Savings Account: " + femaleCount + "\n");
		
			try {	//try and catch blocks to print processed analytics to file
				fileWrite.write("\nNumber of Females with a Mortgage and Savings Account: " + femaleCount + "\n");
			} catch (IOException e) {
				e.printStackTrace();
				}
	}

	/**
	 * method to analyze and print records for each region 
	 * where gender is male and client has vehicle and 1 child
	 */
	private static void CompareMaleRegion() {
		Arrays.sort(recordObjects,new MaleComparator());		//create a new MaleComparator object
		
		//define and initialize an array containing each region
		String [] regionArray = {"INNER_CITY" , "RURAL" , "SUBURBAN" , "TOWN"};
		for (int j = 0; j < regionArray.length; j++) {			//for loop to walk through the region array
			int maleCount = 0;			//define and initialize the male accumulator variable
				for (int i = 0; i < recordObjects.length; i++) {		//for loop to walk through the bank records
					if (recordObjects[i].getRegion().equals(regionArray[j])		//if statement to filter sort region
						&& recordObjects[i].getGender().equals("MALE") 			//filter gender is male
						&& recordObjects[i].getVehicle().equals("YES") 			//filter vehicle is yes
						&& recordObjects[i].getChildren().equals("1"))			//filter number of children is 1
					maleCount++;		//increment the male accumulator variable
			}
				//print analyzed data to console
			System.out.println(regionArray[j] + " Region, Males with Vehicle & 1 Child: " + maleCount);
			{
				try {		//try and catch blocks to print analyzed data to file
				fileWrite.write("\n" + regionArray[j] + " Region, Males with Vehicle & 1 Child: " + maleCount);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * method to generate the time/date stamp and programmer credit
	 * and print to console and file
	 */
	private static void CreditTime() {
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());	//Variable for timestamp
        String idMe = ("Current date = "+ timeStamp + "\nProgrammed by Stephen Griffith \n");	//Variable for programmer credit
    	
        System.out.println("\n" + idMe);	//print date and credit to console
		
    	try {		//try and catch blocks to print date and credit to file
		fileWrite.write("\n\n" + idMe);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
