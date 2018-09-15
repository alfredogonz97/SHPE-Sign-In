import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class QuestionField {
	
	Label textLabel;
	TextField questField;
	Button ynBut;
	Button checkBut;
	
	String text;
	Boolean yn = true;
	Boolean check = false;
	
	double labelHeight = 60.0;
	double labelWidth = 130.0;
	double textFieldHeight = 40.0;
	double textFieldWidth = 400.0;
	String font = "Arial";
	int fontSize = 20;
	
	public QuestionField() {
		
	}
	
	public QuestionField(String text) {
		this.text = text;
		createField();
	}
	
	private void createField() {
		textLabel = createLabel(text, labelHeight, labelWidth, font, fontSize);
		questField = createTextField(textFieldHeight, textFieldWidth);
		ynBut = new Button("Yes / No");
		checkBut = new Button("Check Box");
		ynBut.setOnAction(e -> {
			yn = true;
			check = false;
			ynBut.setStyle(chosenStyle(true));
			checkBut.setStyle(chosenStyle(false));
		});
		checkBut.setOnAction(e -> {
			yn = false;
			check = true;
			ynBut.setStyle(chosenStyle(false));
			checkBut.setStyle(chosenStyle(true));
		});
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
	
	private String chosenStyle(Boolean pressed) {
		if(pressed == true) {
			return "-fx-border-color: #483D8B";
		} else {
			return "-fx-border-color: #FFFFFF;\n";
		}
	}

	public String getQuestionText() {
		return questField.getText();
	}
	
	public Boolean isYN() {
		return yn;
	}
	
	public Boolean isCheck() {
		return check;
	}
	
	public Label getLabel() {
		return textLabel;
	}
	
	public TextField getTextField() {
		return questField;
	}
	
	public Button getYNButton() {
		return ynBut;
	}
	
	public Button getCheckBut() {
		return checkBut;
	}
	
}
