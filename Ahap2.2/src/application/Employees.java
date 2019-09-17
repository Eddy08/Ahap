package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Employees {

	private String employeeType;
	private static int employeeCount = 0;
	private String firstName;
	private String lastName;
	private int employeeNumber;
	private String password;
	private String username;
	private String log = "";


	public  Employees(String employeeType, String firstname, String lastname, String username, String password) {
		this.employeeType = employeeType;
		this.firstName = firstname;
		this.lastName = lastname;
		this.password = password;
		this.username = username.toLowerCase();
		employeeNumber = ++employeeCount;
		System.out.println("EMPLOYEE CREATED" + " | TYPE: " + this.employeeType.toUpperCase() + " | " + employeeNumber );

	}


	public int getEmployeeNumber() {
		return this.employeeNumber;
	}


	public String getFirstName() {
		return this.firstName;
	}


	public String getLastName() {
		return this.lastName;
	}


	public String getUsername() {
		return this.username;
	}



	public String getEmployeeType() {
		return this.employeeType;
	}


	public String getLog() {
		return this.log;
	}


	public void addToLog(String logMessage) {
		this.log += logMessage + "  " + getTime() +  "\n" ;
	}
	
	

	private String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}


	public String getPassword() {
		return this.password;
	}
}
