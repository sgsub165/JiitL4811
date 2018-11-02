/** 
* Name: Stephen Griffith
* Date: 11/02/2018
* Source File Name: GenderComparator.java
* Lab: Lab4
*/

package Lab4;

//import required java libraries
import java.util.Comparator;

/**
 * The GenderComparator class performs a sort 
 * for gender from the bank records and returns
 * the comparison data
 */
public class GenderComparator implements Comparator<BankRecords>{
	 
	@Override
	public int compare(BankRecords object1, BankRecords object2) {
	// use compareTo to compare strings
	int result = object1.getGender().compareTo(object2.getGender());
	return result;
	}
 }
