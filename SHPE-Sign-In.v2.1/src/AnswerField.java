import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class AnswerField {
	
	/// Data Fields ///
	private String labelText;
	
	/// Pane Variables ///
	private Label label;
	private VBox space;
	private TextField answerField;
	
	/// Size Variables ///
	private double labelW = 150.0;
	private double labelH = 20.0;
	private double textFieldW = 500.0;
	private double textFieldH = 40.0;
	
	AnswerField(String labelText) {
		this.labelText = labelText;
		space = new VBox();
		createPane();
	}
	
	public VBox getSpace() {
		return space;
	}
	
	public String getAnswer() {
		return answerField.getText();
	}
	
	public void clear() {
		answerField.clear();
	}
	
	private void createPane() {
		/// Label ///
		label = Styling.createLabel(labelText, labelW, labelH);
		label.setAlignment(Pos.CENTER);
		label.setTextAlignment(TextAlignment.CENTER);
		space.getChildren().add(label);
		space.setAlignment(Pos.CENTER);
		
		/// Text Field ///
		answerField = Styling.createTextField(textFieldW, textFieldH);
		space.getChildren().add(answerField);
		space.setAlignment(Pos.CENTER);
		
		double pad = 10.0;
		space.setPadding(new Insets(pad,pad,pad,pad));
		space.setSpacing(5.0);
	}

}
