import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AskQuestionBlock {

	/// Data Fields ///
	private AskQuestionBlock next;
	private int nodeNum;
	private boolean qType = true;
	
	/// Pane Fields ///
	private GridPane space;
	private Label qLabel;
	private TextField textFill;
	private Label typeLabel;
	private Button textButton;
	private Button checkButton;
	
	/// Pane Size Variables
	private double qLabelW = 150.0;
	private double qLabelH = 20.0;
	private double typeLabelW = 210.0;
	private double typeLabelH = 20.0;
	
	/// Constructor ///	
	AskQuestionBlock() {
		
	}
	
	AskQuestionBlock(int nodeNum, AskQuestionBlock next) {
		this.nodeNum = nodeNum;
		this.next = next;
		configureSpace();
	}
	
	/// Update Methods ///
	public void setNext(AskQuestionBlock next) {
		this.next = next;
	}
	
	public void setQuestion(String question) {
		textFill.setText(question);
	}
	
	public void setQType(boolean qType) {
		this.qType = qType;
		textButton.setStyle(Styling.getButtonStyle(qType));
		checkButton.setStyle(Styling.getButtonStyle(qType));
	}
	
	/// Accessor Methods ///
	public AskQuestionBlock getNext() {
		return next;
	}
	
	public GridPane getSpace() {
		return space;
	}
	
	public String getQuestion() {
		return textFill.getText();
	}
	
	public boolean getQuestionType() {
		return qType;
	}
	
	public int getQuestionNumber() {
		return nodeNum;
	}
	
	/// Helper Methods ///
	private void configureSpace() {
		qLabel = Styling.createLabel("Question "+nodeNum+": ", qLabelW, qLabelH);
		qLabel.setAlignment(Pos.CENTER_RIGHT);
		typeLabel = Styling.createLabel("   Select Question Type", typeLabelW, typeLabelH);
		textFill = Styling.createTextField();
		textButton = Styling.createButton("Text Fill",true);
		checkButton = Styling.createButton("Check Box",false);
		setButtonActions();
		space = configureGridPane();
	}
	
	private void setButtonActions() {
		textButton.setOnAction(e -> {
			qType = true;
			textButton.setStyle(Styling.getButtonOnStyle());
			checkButton.setStyle(Styling.getButtonOffStyle());
		});
		checkButton.setOnAction(e -> {
			qType = false;
			checkButton.setStyle(Styling.getButtonOnStyle());
			textButton.setStyle(Styling.getButtonOffStyle());
		});
	}
	
	private GridPane configureGridPane() {
		GridPane pane = new GridPane();
		
		/// Style ///
		pane.setStyle(Styling.getGridPaneStyle());
		
		/// Add Parts ///
		pane.add(qLabel, 0, 1);
		pane.add(textFill, 1, 1);
		pane.add(typeLabel, 2, 0);
		HBox buttonHolder = new HBox();
		buttonHolder.getChildren().addAll(textButton,checkButton);
		pane.add(buttonHolder, 2, 1);
		
		/// Element Alignment ///
		GridPane.setHalignment(qLabel, HPos.CENTER);
		GridPane.setHalignment(textFill, HPos.CENTER);
		GridPane.setHalignment(typeLabel, HPos.CENTER);
		GridPane.setValignment(qLabel, VPos.CENTER);
		GridPane.setValignment(textFill, VPos.CENTER);
		GridPane.setValignment(typeLabel, VPos.CENTER);
		buttonHolder.setAlignment(Pos.CENTER);
		buttonHolder.setPadding(new Insets(10,10,10,10));
		buttonHolder.setSpacing(20);
		
		pane.setAlignment(Pos.CENTER);
		return pane;
	}
	
	public String toString() {
		return nodeNum +" , " +textFill.getText() +" , " +(qType? "Text Fill" : "Check Box");
	}
	
}
