/* Created by Alfredo Gonzalez (UNF 2015 - 2020) 
 * 
 * Class: Student
 * 
 * This class is intended to be used to track student information. To be used with the SHPE-Sign-In gui.
 * 
 * Data fields:
 * 		firstName 	- First Name of the student
 * 		lastName	- Last Name of the student
 * 		nNum		- N# of the student
 * 		email		- Email of the student
 * 		resp1		- Response 1, may not be utilized. Intended to be used if questions were given to the students.
 * 		resp2		- Response 2, may not be utilized. Intended to be used if questions were given to the students.
 * 		resp3		- Response 3, may not be utilized. Intended to be used if questions were given to the students.
 * 
 */


public class Student {
	public String firstName;
	public String lastName;
	public String nNum;
	public String email;
	public char resp1;
	public char resp2;
	public char resp3;
	
	public Student () {
		
	}
	
	public Student(String firstName,String lastName, String nNum, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nNum = nNum;
		this.email = email;		
	}
	
	public Student(String firstName,String lastName, String nNum, String email, char resp1) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nNum = nNum;
		this.email = email;		
		this.resp1 = resp1;
	}
	
	public Student(String firstName,String lastName, String nNum, String email, char resp1, char resp2) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nNum = nNum;
		this.email = email;		
		this.resp1 = resp1;
		this.resp2 = resp2;
	}
	
	public Student(String firstName,String lastName, String nNum, String email, char resp1, char resp2, char resp3) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nNum = nNum;
		this.email = email;		
		this.resp1 = resp1;
		this.resp2 = resp2;
		this.resp3 = resp3;
	}

	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setNNUm(String nNum) {
		this.nNum = nNum;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setResp1(char resp1) {
		this.resp1 = resp1;
	}

	public void setResp2(char resp2) {
		this.resp2 = resp2;
	}
	
	public void setResp3(char resp3) {
		this.resp3 = resp3;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
	
	public String getNNum() {
		return this.nNum;
	}
	
	public String getEmail() {
		return this.email;
	}

	public char getResp1() {
		return this.resp1;
	}
	
	public char getResp2() {
		return this.resp2;
	}
	
	public char getResp3() {
		return this.resp3;
	}
	
	public String toString() {
		return firstName +" " +lastName +" " +nNum +" " +email +" " +resp1 +" " +resp2 +" " +resp3;
	}
	
	public String toCSV() {
		return firstName +"," +lastName +"," +nNum +"," +email +"," +resp1 +"," +resp2 +"," +resp3 +"\n";
	}
	
	public void clearFields() {
		firstName = "";
		lastName = "";
		nNum = "";
		email = "";
		resp1 = ' ';
		resp2 = ' ';
		resp3 = ' ';
	}
	
}
