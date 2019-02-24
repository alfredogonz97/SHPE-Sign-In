import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class AnswersCircularSinglyLinkedList {

	/// Data Fields ///
	private AnswerBlock tail = null;
	private int size = 0;
	private VBox space;
	
	/// Size Variables ///
	
	/// Constructor ///
	public AnswersCircularSinglyLinkedList(QuestionsCircularSinglyLinkedList list) {
		space = new VBox();
		AskQuestionBlock walk = list.getFirst();
		while(walk.getNext() != list.getFirst()) {
			if(walk.getQuestion().toCharArray().length == 0) break;
			add(walk.getQuestion(),walk.getQuestionType());
			walk = walk.getNext();
		}
		if(list.getLast().getQuestion().toCharArray().length == 0) return;
		add(list.getLast().getQuestion(),list.getLast().getQuestionType());
	}
	
	private void add(String question, boolean qType) {
		if(size == 0) {
			tail = new AnswerBlock(question,qType,null);
			tail.setNext(tail);
		} else {
			AnswerBlock temp = new AnswerBlock(question,qType,tail.getNext());
			tail.setNext(temp);
			tail = temp;
		}
		space.getChildren().add(tail.getSpace());
		size++;
	}
	
	public VBox getSpace() {
		space.setAlignment(Pos.CENTER);
		space.setSpacing(5.0);
		return space;
	}
	
	public AnswerBlock getTail() {
		return tail;
	}
	
	public void clearAllFields() {
		if(size == 0) return;
		AnswerBlock walk = tail.getNext();
		while(walk.getNext() != tail.getNext()) {
			walk.clear();
			walk = walk.getNext();
		}
		tail.clear();
	}
	
	public String toCSV() {
		if(size == 0) return "";
		String out = "";
		AnswerBlock walk = tail.getNext();
		while(walk.getNext() != tail) {
			out += walk.toString();
			walk = walk.getNext();
		}
		out+= tail.toString();
		return out;
	}
	
}
