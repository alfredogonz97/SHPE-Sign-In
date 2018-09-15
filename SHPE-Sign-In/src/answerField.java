import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class answerField {
	
	String text;
	String pos = "vertical";
	
	VBox vertPane;
	HBox horizPane;
	
	Label label;
	TextField answer;
	
	double labelHeight = 60.0;
	double labelWidth = 130.0;
	double textFieldHeight = 40.0;
	double textFieldWidth = 500.0;
	String font = "Arial";
	int fontSize = 20;
	
	public answerField() {
		
	}
	
	public answerField(String text) {
		this.text = text;
		createPane();
	}
	
	public answerField(String text, String pos) {
		this.text = text;
		this.pos = pos;
		createPane();
	}
	
	private void createPane() {
		label = createLabel(text, labelHeight, labelWidth, font, fontSize);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setAlignment(Pos.CENTER);
		answer = createTextField(textFieldHeight, textFieldWidth);
		if(pos == "vertical") {
			vertPane = new VBox();
			vertPane.getChildren().addAll(label,answer);
			vertPane.setAlignment(Pos.CENTER);
		} else {
			horizPane = new HBox();
			horizPane.getChildren().addAll(label,answer);
			vertPane.setAlignment(Pos.CENTER);
		}
	}
	
	private Label createLabel(String text, double height, double width, String font, int fontSize) {
		Label label = new Label(text);
		label.setMaxSize(width, height);
		label.setMinSize(width, height);
		label.setFont(Font.font(font, FontWeight.BOLD, fontSize));
		return label;
	}
	
	private TextField createTextField(double height, double width) {
		TextField field = new TextField();
		field.setPrefSize(width, height);
		return field;
	}
	
	public VBox getVBox() {
		return vertPane;
	}
	
	public HBox getHBox() {
		return horizPane;
	}
	
	public String getAnswer() {
		return answer.getText();
	}
	
	public void clearFields() {
		answer.clear();
	}

}
