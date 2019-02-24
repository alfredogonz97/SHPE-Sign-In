import javafx.scene.layout.VBox;

public class QuestionsCircularSinglyLinkedList {

	/// Data Fields ///
	private AskQuestionBlock tail = null;
	private int size = 0;
	private VBox space;
	
	/// Size Variables ///
	private double spaceSpacing = 5.0;
	
	/// Constructor ///
	public QuestionsCircularSinglyLinkedList() {
		createSpace();
	}
	
	/// Accessor Methods ///
	public VBox getSpace() {
		return space;
	}
	
	public int getSize() {
		return size;
	}
	
	public AskQuestionBlock getFirst() {
		return tail.getNext();
	}
	
	public AskQuestionBlock getLast() {
		return tail;
	}
	
	/// Update Methods ///
	public void add() {
		if(size == 0) {
			tail = new AskQuestionBlock(size+1, null); // add first node to the list
			tail.setNext(tail);	// make circular
		} else {
			AskQuestionBlock temp = new AskQuestionBlock(size+1, tail.getNext());
			tail.setNext(temp);
			tail = temp;
		}
		space.getChildren().add(tail.getSpace()); 
		size++;
	}
	
	public AskQuestionBlock removeFirst() {
		space.getChildren().remove(0);
		AskQuestionBlock temp = new AskQuestionBlock();
		switch(size) {
			case 0:
				temp = null;
				break;
			case 1:
				temp = tail;
				tail = null;
				size--;
				break;
			default:
				temp = tail.getNext();
				tail.setNext(tail.getNext().getNext());
				size--;
				break;
		}
		return temp;
	}
	
	public AskQuestionBlock removeLast() {
		AskQuestionBlock temp = new AskQuestionBlock();
		switch(size) {
			case 0:
				temp = null;
				break;
			case 1:
				temp = tail;
				tail = null;
				space.getChildren().remove(size-1);
				size--;
				break;
			default:
				temp = tail.getNext();
				while(temp.getNext() != tail) {
					temp = temp.getNext();
				}
				temp.setNext(tail.getNext());
				tail = temp;
				space.getChildren().remove(size-1);
				size--;
				break;
		}
		return temp;
	}
	
	/// Helper Methods ///
	private void createSpace() {
		space = new VBox();
		space.setStyle(Styling.getVBoxStyle());
		space.setSpacing(spaceSpacing);
	}

	public String toString() {
		if(size == 0) return "( List is Empty )";
		AskQuestionBlock temp = tail.getNext();
		String out = "";
		while(temp != tail) {
			out += "( " +temp.toString() +" )" +" ";
			temp = temp.getNext();
		}
		out += "( " +tail.toString() +" )" +" ";
		return out;
	}
	
	public String toCSV() {
		if(size == 0) return "";
		AskQuestionBlock temp = tail.getNext();
		String out = "";
		while(temp != tail) {
			out += temp.getQuestion() +",";
			temp = temp.getNext();
		}
		out += tail.getQuestion();
		return out;
	}
	
}
