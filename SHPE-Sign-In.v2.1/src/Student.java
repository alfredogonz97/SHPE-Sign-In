
public class Student {
	
	//////////////////////////////// Data Fields ////////////////////////////////
	private String firstName;
	private String lastName;
	private String nNumber;
	private String unfEmail;
	private String prefEmail;
	private String major;
	private Questions questions = new Questions();
	
	//////////////////////////////// Constructor ////////////////////////////////
	public Student() {
		
	}
	
	public Student(String firstName, String lastName, String nNumber, String prefEmail, String major) {
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.nNumber = validateNNumber(nNumber); 
		this.prefEmail = prefEmail;
		this.major = major;
		setUnfEmail();
	}
	
	//////////////////////////////// Setter Methods ////////////////////////////////
	public void setFullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setNNumber(String nNumber) {
		this.nNumber = validateNNumber(nNumber);
		setUnfEmail();
	}
	
	public void setPrefEmail(String prefEmail) {
		this.prefEmail = prefEmail;
	}
	
	public void setUnfEmail() {
		/*
		 *  UNF email are of the for "nXXXXXXXX@ospreys.unf.edu", which is just the n-number followed by "@ospreys.unf.edu".
		 */
		
		this.unfEmail = nNumber + "@ospreys.unf.edu";
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public void addQuestion(String question, String answer) {
		questions.add(question, answer);
	}
	
	public void removeFirstQuestion() {
		questions.removeFirst();
	}
	
//////////////////////////////// Getter Methods ////////////////////////////////
	public String getFullName() {
		return firstName + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getNNumber() {
		return nNumber;
	}
	
	public String getPrefEmail() {
		return prefEmail;
	}
	
	public String getUnfEmail() {
		return unfEmail;
	}
	
	public String getMajor() {
		return major;
	}
	
	public String getFirstQuestion() {
		if(questions.getSize() == 0) return null;
		return questions.getFirstNode().toString();
	}
	
	public String getAllQuestions() {
		return questions.toString();
	}
	
//////////////////////////////// Helper Methods ////////////////////////////////
	
	public String toString() {
		return firstName +" " +lastName +" " +nNumber +" " +prefEmail +" " +unfEmail +" " +major;
	}
	
	public String toCSV() {
		return firstName +"," +lastName +"," +nNumber +"," +prefEmail +"," +unfEmail +"," +major +"," +questions.answersToCSV() +"\n";
	}
	
	public void clearFields() {
		firstName = "";
		lastName = "";
		nNumber = "";
		prefEmail = "";
		unfEmail = "";
		major = "";
	}
	
	private String validateNNumber(String nNumber) {
		/*
		 * This function checks to see if the n-number input by the student is in the correct format, i.e. begins with 'N' or 'n'.
		 */
		
		char[] temp = nNumber.toCharArray();
		
		//Access the first character in the n-number and check it to see if it is either a 'n' or 'N'
		if(temp[0] == 'n' || temp[0] == 'N') { 
			return nNumber;
		} 
		
		return "N" + nNumber;
	}
	
}
