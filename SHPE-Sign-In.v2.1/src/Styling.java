import java.net.URL;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Styling {
	
	/// Data Fields ///
	private static String buttonOnStyle =     "-fx-background-radius: 5em; "
		    								+ "-fx-border-radius: 5em;"
		    								+ "-fx-border-width: 1px;"
		    								+ "-fx-background-color: #1F1573;"
		    								+ "-fx-text-fill: white;"
		    								+ "-fx-focus-color: none;";
	private static String buttonOffStyle =    "-fx-background-radius: none; "
											+ "-fx-border-radius: none;"
											+ "-fx-border-width: 1px;"
											+ "-fx-background-color: none;"
											+ "-fx-text-fill: black;"
											+ "-fx-focus-color: none;";
	private static String checkButtonStyle =  "-fx-background-radius: 5em; "
											+ "-fx-border-radius: 5em;"
											+ "-fx-border-width: 1px;"
											+ "-fx-background-color: white;";
	private static String labelStyle =        "-fx-background-color: white;"
										    + "-fx-text-fill: #1F1573;";
	private static String textFieldStyle =    "-fx-focus-color: none;"
											+ "-fx-background-color: white;"
											+ "-fx-border-color: #1F1573;"
											+ "-fx-border-radius: 5em;"
											+ "-fx-background-radius: 5em;"
											+ "-fx-border-width: 2px;";
	private static String gridPaneStyle =     "-fx-border-width: 3px;"
											+ "-fx-border-color: white;"
											+ "-fx-background-color: white;";
	private static String vBoxStyle =         "-fx-background-color: white;"
											+ "-fx-focus-color: none;";
	private static String hBoxStyle =         "-fx-background-color: white;"
											+ "-fx-focus-color: none;";
	private static String fontStyle;
	private static String stageTitle = "SHPE Sign In";
	private static String stageIcon = "resources/images/SHPE-Logo.jpeg";
	private static String mainLogo = "resources/images/UNFSHPE.jpeg";
	private static double mainLogoW = 900.0;
	private static double mainLogoH = 350.0;
	private static String checkedBox = "resources/images/checkBox.jpg";
	private static String checkBoxBlank = "resources/images/checkBoxBlank.jpg";
	private static double checkBoxW = 20.0;
	private static double checkBoxH = 20.0;
	
	/// Font Variables ///
	private static String font = "Arial";
	private static int labelFontSize = 18;
	private static double labelWidth = 120.0;
	private static double labelHeight = 20.0;
	private static double textFieldWidth = 600.0;
	private static double textFieldHeight = 10.0;
	private static double buttonWidth = 90.0;
	private static double buttonHeight = 15.0;
	private static double checkButtonWidth = 20.0;
	private static double checkButtonHeight = 20.0;
	
	/// Get Styles ///
	public static String getButtonStyle(boolean type) {
		return type ? getButtonOnStyle() : getButtonOffStyle();
	}
	
	public static String getButtonOnStyle() {
		return buttonOnStyle;
	}
	
	public static String getButtonOffStyle() {
		return buttonOffStyle;
	}
	
	public static String getLabelStyle() {
		return labelStyle;
	}
	
	public static String getTextFieldStyle() {
		return textFieldStyle;
	}
	
	public static String getGridPaneStyle() {
		return gridPaneStyle;
	}
	
	public static String getVBoxStyle() {
		return vBoxStyle;
	}
	
	public static String getHBoxStyle() {
		return hBoxStyle;
	}
	
	public static String getFontStyle() {
		return fontStyle;
	}

	public static String getStageTitle() {
		return stageTitle;
	}
	
	public static Image getStageIcon() {
		return new Image(stageIcon);
	}

	public static ImageView getMainLogo() {
		return getImage(mainLogo,mainLogoW,mainLogoH);
	}
	
	public static ImageView getCheckedBox() {
		return getImage(checkedBox,checkBoxW,checkBoxH);
	}
	
	public static ImageView getEmptyCheckBox() {
		return getImage(checkBoxBlank,checkBoxW,checkBoxH);
	}
	
	/// Create Children Methods ///
	public static Label createLabel(String labelText) {
		Label label = new Label(labelText);
		label.setPrefSize(labelWidth, labelHeight);
		label.setFont(Font.font(font, FontWeight.BOLD, labelFontSize));
		label.setStyle(labelStyle);
		return label;
	}
	
	public static Label createLabel(String labelText, double width, double height) {
		Label label = new Label(labelText);
		label.setPrefSize(width, height);
		label.setFont(Font.font(font, FontWeight.BOLD, labelFontSize));
		label.setStyle(labelStyle);
		return label;
	}
	
	public static Label createLabel(String labelText, double width, double height, int fontSize, String style) {
		Label label = new Label(labelText);
		label.setPrefSize(width, height);
		label.setFont(Font.font(font, FontWeight.BOLD, fontSize));
		label.setStyle(style);
		return label;
	}
	
	public static TextField createTextField() {
		TextField field = new TextField();
		field.setPrefSize(textFieldWidth, textFieldHeight);
		field.setStyle(textFieldStyle);
		return field;
	}
	
	public static TextField createTextField(double width, double height) {
		TextField field = new TextField();
		field.setPrefSize(width, height);
		field.setStyle(textFieldStyle);
		return field;
	}
	
	public static TextField createTextField(double width, double height, String style) {
		TextField field = new TextField();
		field.setPrefSize(width, height);
		field.setStyle(style);
		return field;
	}
	
	public static Button createButton(String text, boolean type) {
		Button temp = new Button(text);
		temp.setPrefSize(buttonWidth, buttonHeight);
		temp.setStyle(type ? buttonOnStyle : buttonOffStyle);
		return temp;
	}
	
	public static Button createButton(String text, boolean type, double width, double height) {
		Button temp = new Button(text);
		temp.setPrefSize(width, height);
		temp.setStyle(type ? buttonOnStyle : buttonOffStyle);
		return temp;
	}
	
	public static Button createCheckButton() {
		Button temp = new Button();
		temp.setGraphic(getEmptyCheckBox());
		temp.setStyle(checkButtonStyle);
		temp.setPrefSize(checkButtonWidth, checkButtonHeight);
		return temp;
	}
	
	/// Helper Methods ///
	
	private static ImageView getImage(String path, double width, double height) {
		URL stream = Styling.class.getResource(path);
		ImageView pic = new ImageView(new Image(stream.toString()));
		pic.setFitWidth(width);
		pic.setFitHeight(height);
		return pic;
	}
	
}
