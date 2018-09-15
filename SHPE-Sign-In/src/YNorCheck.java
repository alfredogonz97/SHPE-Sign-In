import java.net.URL;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class YNorCheck {
	
	String type; //Yes or No/ Check Box/ Textfield
	String text; //Text of the question
	char response;
	HBox pane;
	Button yes;
	Button no;
	Button check;
	
	Boolean checkPressed;
	Boolean checkFirstPress = false;
	
	Label label;
	
	double labelHeight = 50.0;
	double labelWidth = 300.0;
	String font = "Arial";
	int fontSize = 20;
	
	YNorCheck() {
		
	}
	
	YNorCheck(String type, String text) {
		this.type = type;
		this.text = text;
		label = createLabel(text,labelHeight,labelWidth,font,fontSize);
		pane = new HBox();
		pane.setPrefHeight(60.0);
		pane.setSpacing(30.0);
		pane.getChildren().add(label);
		if(type == "yn") {
			yes = new Button("Yes");
			yes.setOnAction(e -> {
				response = 'Y';
				yes.setStyle(chosenStyle(true));
				no.setStyle(chosenStyle(false));
			});
			no = new Button("No");
			no.setOnAction(e -> {
				response = 'N';
				yes.setStyle(chosenStyle(false));
				no.setStyle(chosenStyle(true));
			});
			pane.getChildren().addAll(yes,no);
		} else {
			check = new Button();
			check.setGraphic(getImage("resources/images/checkBoxBlank.jpg",30,30));
			check.setStyle("-fx-background-color: white;");
			check.setMinSize(30, 30);
			check.setOnAction(e -> {
				if(checkFirstPress == false) {
					response = 'X';
					checkFirstPress = true;
					checkPressed = true;
					check.setGraphic(getImage("resources/images/checkBox.jpg",30,30));
				} else {
					if(checkPressed == true) {
						response = ' ';
						checkPressed = false;
						check.setGraphic(getImage("resources/images/checkBoxBlank.jpg",30,30));
					} else {
						response = 'X';
						checkPressed = true;
						check.setGraphic(getImage("resources/images/checkBox.jpg",30,30));
					}
				}				
			});
			pane.getChildren().add(check);
		}
		pane.setAlignment(Pos.CENTER);
	}
	
	private Label createLabel(String text, double height, double width, String font, int fontSize) {
		Label label = new Label(text);
		label.setMaxSize(width, height);
		label.setMinSize(width, height);
		label.setFont(Font.font(font, FontWeight.BOLD, fontSize));
		return label;
	}
	
	public HBox getHBox() {
		return pane;
	}
	
	public char getResponse() {
		return response;
	}
	
	public String getQuestion() {
		return text;
	}
	
	private String chosenStyle(Boolean pressed) {
		if(pressed == true) {
			return "-fx-border-color: #483D8B";
		} else {
			return "-fx-border-color: #FFFFFF;\n";
		}
	}
	
	private ImageView getImage(String path, double height, double width) {
		URL url = SHPE_Sign_In.class.getResource(path);
		Image pic = new Image(url.toString());
		ImageView picView = new ImageView(pic);
		picView.setFitHeight(height);
		picView.setFitWidth(width);
		return picView;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void clearField() {
		response = ' ';
		if(yes != null) {
			yes.setStyle(chosenStyle(false));
		}
		if(no != null) {
			no.setStyle(chosenStyle(false));
		}
		if(check != null) {
			check.setGraphic(getImage("resources/images/checkBoxBlank.jpg",30,30));
		}
		
	}
	
}
