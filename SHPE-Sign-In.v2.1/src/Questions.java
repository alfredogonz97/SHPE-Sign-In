public class Questions {

	public class Node {
		/*
		 *  Students may be asked questions and will then be required to provide an answer.
		 *  This class is meant to hold the questions and answers in a specific order.
		 */
		
		///////// Data Fields /////////		
		private String question;
		private String answer;
		private Node next;
		
		public Node(String question, String answer, Node next) {
			this.question = question;
			this.answer = answer;
			this.next = next;
		}
		
		///////// Getter Methods /////////	
		public String getQuestion() { return question; }
		
		public String getAnswer() { return answer; }
		
		public Node getNext() { return next; }
		
		///////// Setter Methods /////////			
		public void setQuestion(String question) { this.question = question; };
		
		public void setAnswer(String answer) { this.answer = answer; }
		
		public void setNext(Node next) { this.next = next; }
		
		///////// Helper Methods /////////	
		public String toString() { return question +" " +answer; }
		
		public String toCSV() { return question +"," +answer; }
		
	}
	
	///////// Data Fields /////////	
	private Node tail = null;
	private int size = 0;
		
	///////// Add and Remove Methods /////////	
	public void add(String question, String answer) {
		/*
		 * This function adds a node to the end of the list and makes the list circular. 
		 * If the list is empty, then the tail becomes the only node in the list.
		 */
		
		if(size == 0) { //empty case
			tail = new Node(question,answer,null);
			tail.setNext(tail); //wrap tail around
		} else { //size > 0
			tail.setNext(new Node(question,answer,tail.getNext()));
			tail = tail.getNext();
		}
		size++; //increment size
	}
	
	public Node removeFirst() {
		/*
		 * This function removes the first element of the list and decrements the size. The first element
		 * gets returned.
		 */
		
		Node temp; //temp will be returned
		switch(size) {
			case 0: //empty list
				temp = null;
				break;
			case 1: //list size == 1
				temp = tail;
				tail = null;
				size--;
				break;
			default: //list size > 1
				temp = tail.getNext();
				tail.setNext(tail.getNext().getNext());
				size--;
		}	
		return temp; //return the first element
	}
	
	///////// Getters /////////	
	public Node getFirstNode() {
		/*
		 * This function simply return the first element in the list
		 */
		
		return tail.getNext();
	}
	
	public Node getTail() {
		/*
		 * This function simply returns the tail node
		 */
		return tail;
	}
	
	public int getSize() {
		/*
		 * This functions simply returns the size of the array
		 */
		
		return size;
	}

	///////// Helper Methods /////////	
	public String toString() {
		/*
		 * This function walks through the entire list and concatenates the elements of the 
		 * nodes into a string, which is then returned.
		 */
		
		String out = ""; //initialize return string
		Node walk = tail.getNext(); //get head of list 
		
		while(walk != tail) { //walk from the head to the tail of the list
			out += walk.toString() + " "; //concatenate the info to the string
			walk = walk.getNext(); //walk
		}
		
		out += walk.toString(); //put the tail into the output string
		return out;
	}
	
	public String toCSV() {
		String out = ""; //initialize return string
		Node walk = tail.getNext(); //get head of list 
		
		while(walk != tail) { //walk from the head to the tail of the list
			out += walk.toString() + ","; //concatenate the info to the string
			walk = walk.getNext(); //walk
		}
		
		out += walk.toString(); //put the tail into the output string
		return out;
	}
	
	public String answersToCSV() {
		String out = ""; //initialize return string
		Node walk = tail.getNext(); //get head of list 
		
		while(walk != tail) { //walk from the head to the tail of the list
			out += walk.getAnswer() + ","; //concatenate the info to the string
			walk = walk.getNext(); //walk
		}
		
		out += walk.getAnswer(); //put the tail into the output string
		return out;
	}
	
	public static Questions clone(Questions orig) {
		if(orig.getSize() == 0) return null;
		
		Questions copy = new Questions();
		Node walk = orig.getFirstNode();
		
		while(orig.getTail() != walk) {
			System.out.println("here");
			copy.add(walk.getQuestion(), walk.getAnswer());
			walk = walk.getNext();
		}
		copy.add(walk.getQuestion(), walk.getAnswer());
		return copy;		
	}
	
}
