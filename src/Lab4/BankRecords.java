/** 
* Name: Stephen Griffith
* Date: 11/02/2018
* Source File Name: BandRecords.java
* Lab: Lab4
*/

package Lab4;   //java package designation

/**
 * The purpose of the BandRecords class is provide the methods to read the 
 * records of the bank-Detail.csv file into an array list. The array list 
 * record data is added to an array of objects for processing.
 */

//Import the required java libraries
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The BankRecords constructor class extends the abstract class Client.
 * This class contains the inherited methods readData, processData and printData.
 */
public class BankRecords extends Client {
	
    static int maxRecordNum = 600;      //define the maximum number of records to read
    //Define the static objects for processing
    static BankRecords recordObjects[] = new BankRecords[maxRecordNum];  //define an array of bank record objects
    static ArrayList<List<String>> recordsArray = new ArrayList<>();   //define the array list to store the record rows and columns

    //BankRecords class instance fields
    private String id;			//DB id field	Client identification
    private int age;			//DB age field	Age of client
    private String gender;			//DB gender field	Gender of client
    private String region;			//DB region field	Region of client residence
    private double income;			//DB income field	Income of client
    private String maritalStatus;   //DB marital status field	Is client married Yes/No
    private String children;		//DB children status field	Client number of children
    private String vehicle;			//DB vehicle status field	Does client own vehicle
    private String save_act;		//DB Savings act field	Does client have active savings
    private String current_act;		//DB Current act field	Is client account active
    private String mortgage;		//DB Mortgage field		Does client have mortgage
    private String pep;			//DB PEP field			??????
    
    Scanner keyboard = new Scanner(System.in);      //define the Scanner for keyboard input
        
    //getters for the defined class instance variables
    public String getId() {
	return id;
    }
    public int getAge() {
	return age;
    }
    public String getGender() {
	return gender;
    }
    public String getRegion() {
	return region;
    }
    public double getIncome() {
	return income;
    }
    public String getMaritalStatus() {
	return maritalStatus;
    }
    public String getChildren() {
	return children;
    }
    public String getVehicle() {
	return vehicle;
    }
    public String getSave_act() {
	return save_act;
    }
    public String getCurrent_act() {
	return current_act;
    }
    public String getMortgage() {
	return mortgage;
    }
    public String getPep() {
	return pep;
    }

    //setters for the defined class instance variables
    public void setId(String id) {
	this.id = id;
    }
    public void setAge(int age) {
	this.age = age;
    }
    public void setGender(String gender) {
	this.gender = gender;
    }
    public void setRegion(String region) {
	this.region = region;
    }
    public void setIncome(double income) {
	this.income = income;
    }
    public void setMaritalStatus(String maritalStatus) {
	this.maritalStatus = maritalStatus;
    }
    public void setChildren(String children) {
	this.children = children;
    }
    public void setVehicle(String vehicle) {
	this.vehicle = vehicle;
    }
    public void setSave_act(String save_act) {
	this.save_act = save_act;
    }
    public void setCurrent_act(String current_act) {
	this.current_act = current_act;
    }
    public void setMortgage(String mortgage) {
	this.mortgage = mortgage;
    }
    public void setPep(String pep) {
	this.pep = pep;
    }

/**
 * The readData method is inherited from the abstract Client class.
 * This method reads the data file contents into an ArrayList recordsArray
 */    
    @Override
    public void readData() {
        
        BufferedReader bfrdr = null;       //define the BufferedReader variable and initialize to null
        try {                           //try statement to catch an incorrect or missing file
            bfrdr = new BufferedReader(new FileReader (new File ("bank-Detail.csv")));     //open the file for reading
            } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("The file to read was not found.\nPlease check the file name and location.");
            }
            //System.out.println("The file to read was not found.\nPlease check the file name and location.");
            String line;	      	//define the variable line to read line by line in the file
        try {
            while ((line=bfrdr.readLine()) != null) {      //while loop set to read file to the end of file
                recordsArray.add(Arrays.asList(line.split(",")));       //add each line of the file to the ArrayList with comma separators
            }
                } catch (IOException e) {
                e.printStackTrace();
                }
        
    processData();      //call to the processData method
        }

    /**
     * The processData method is inherited from the abstract Client class.
     * This method takes the record data from the ArrayList and adds the data
     * into each instance field using the setter methods as an array of objects.
     */
    @Override
    public void processData() {
	int index = 0;		//define the index value for walking the array
	for (List<String> rowData: recordsArray) {      //for loop to walk through each row of data records
            recordObjects[index] = new BankRecords();   //define new bank records for setting
            recordObjects[index].setId(rowData.get(0));     //set id as zero index of row object
            recordObjects[index].setAge(Integer.parseInt(rowData.get(1)));  //set age as index 1 of row object
            recordObjects[index].setGender(rowData.get(2));                 //set gender as index 2 of row object
            recordObjects[index].setRegion(rowData.get(3));                 //set region as index 3 of row object
            recordObjects[index].setIncome(Double.parseDouble(rowData.get(4)));     //set income as index 4 of row object
            recordObjects[index].setMaritalStatus(rowData.get(5));          //set marital status as index 5 of row object
            recordObjects[index].setChildren(rowData.get(6));               //set children as index 6 of row object
            recordObjects[index].setVehicle(rowData.get(7));                //set vehicle as index 7 of row object
            recordObjects[index].setSave_act(rowData.get(8));               //set save_act as index 8 of row object
            recordObjects[index].setCurrent_act(rowData.get(9));            //set current_act as index 9 of row object
            recordObjects[index].setMortgage(rowData.get(10));              //set mortgage as index 10 of row object
            recordObjects[index].setPep(rowData.get(11));                   //set pep as index 11 of row object
            index++;        //increment index by 1
            }
        }
}
        