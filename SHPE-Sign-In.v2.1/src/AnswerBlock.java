import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AnswerBlock {
	
	/// Data Fields ///
	private AnswerBlock next;
	private String question;
	private String answer = "";
	private boolean qType;
	private boolean isChecked = false;
	
	/// Pane Fields ///
	private HBox space;
	private Label qLabel;
	private TextField answerFill;
	private Button checkButton;
	
	/// Pane Size Variables
	private double qLabelH = 20.0;
	private double answerFillW = 500.0;
	private double answerFillH = 20.0;
	
	/// Constructor ///	
	AnswerBlock() {
		
	}
	
	AnswerBlock(String question, boolean qType, AnswerBlock next) {
		this.question = question;
		this.qType = qType;
		this.next = next;
		configureSpace();
	}
	
	/// Update Methods ///
	public void setNext(AnswerBlock next) {
		this.next = next;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setQType(boolean qType) {
		this.qType = qType;
	}
	
	/// Accessor Methods ///
	public AnswerBlock getNext() {
		return next;
	}
	
	public HBox getSpace() {
		return space;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer() {
		if(qType) {
			return answerFill.getText();
		} else {
			return answer;
		}
	}
	
	public boolean getQuestionType() {
		return qType;
	}
	
	/// Helper Methods ///
	private void configureSpace() {
		char[] temp = question.toCharArray();
		double qLabelW = temp.length * 12;
		qLabel = Styling.createLabel(question, qLabelW, qLabelH);
		qLabel.setAlignment(Pos.CENTER_RIGHT);
		
		if(qType) {
			answerFill = Styling.createTextField(answerFillW, answerFillH);
		} else {
			checkButton = Styling.createCheckButton();
			setButtonActions();
		}
		space = configureHBoxPane();
	}
	
	private void setButtonActions() {
		checkButton.setOnAction(e -> {
			if(isChecked) {
				checkButton.setGraphic(Styling.getEmptyCheckBox());
				answer = "";
			} else {
				checkButton.setGraphic(Styling.getCheckedBox());
				answer = "X";
			}
			isChecked = !isChecked;
		});
	}
	
	private HBox configureHBoxPane() {
		HBox pane = new HBox();
		
		/// Style ///
		pane.setStyle(Styling.getHBoxStyle());
		
		/// Add Elements ///
		pane.getChildren().add(qLabel);
		if(qType) {
			pane.getChildren().add(answerFill);
		} else {
			pane.getChildren().add(checkButton);
		}
		
		/// Alignment and Spacing///
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(15.0);
				
		return pane;
	}
	
	public void clear() {
		if(qType) {
			answerFill.clear();
		} else {
			answer = "";
			checkButton.setGraphic(Styling.getEmptyCheckBox());
			isChecked = false;
		}
	}
	
	public String toString() {
		if(qType) {
			return question +"," +answerFill.getText();
		} else {
			return question +"," +answer;
		}
	}

}
