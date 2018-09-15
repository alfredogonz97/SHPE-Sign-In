import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URL;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SHPE_Sign_In extends Application{
	
	//Variables
	Boolean configured = false;
	int count = 0;
	YNorCheck q1 = new YNorCheck();
	YNorCheck q2 = new YNorCheck();
	YNorCheck q3 = new YNorCheck();
	double windowHeight = 800.0;
	double windowWidth = 1100;
	
	File csvFile;
	Boolean triedToSave = false;
	Boolean fileChooserCanceled = false;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws FileNotFoundException {
				
		//Header Photo
		
		VBox border = new VBox();

		border.setPadding(new Insets(10, 10, 10, 10));
		border.setStyle("-fx-background-color: white");
		border.setSpacing(5.0);
		
		ImageView headerPic = getImage("resources/images/UNFSHPE.jpeg",350.0,900.0);
		
		border.getChildren().add(headerPic);
		border.setAlignment(Pos.CENTER);
		
		//Title
		Label title = new Label("Have Any Questions to Ask?");
		title.setStyle("-fx-font-size: 50px;\n" + "-fx-font-weight: bold;\n" +
					   "-fx-text-fill: linear-gradient(#0000CD,#191970);");
		border.getChildren().add(title);
		
		
		//Startup Pane
		
		QuestionField qfield1 = new QuestionField("Question 1:");
		QuestionField qfield2 = new QuestionField("Question 2:");
		QuestionField qfield3 = new QuestionField("Question 3:");
		
		GridPane configure = new GridPane(); 
		configure.setHgap(30);
		configure.setVgap(5);
		configure.setPadding(new Insets(5));
		configure.add(qfield1.getLabel(), 0, 0);
		configure.add(qfield1.getTextField(), 1,0);
		configure.add(qfield1.getYNButton(), 2, 0);
		configure.add(qfield1.getCheckBut(), 3, 0);
		configure.add(qfield2.getLabel(), 0, 1);
		configure.add(qfield2.getTextField(), 1,1);
		configure.add(qfield2.getYNButton(), 2, 1);
		configure.add(qfield2.getCheckBut(), 3, 1);
		configure.add(qfield3.getLabel(), 0, 2);
		configure.add(qfield3.getTextField(), 1,2);
		configure.add(qfield3.getYNButton(), 2, 2);
		configure.add(qfield3.getCheckBut(), 3, 2);
		configure.setAlignment(Pos.TOP_CENTER);
		configure.setStyle("-fx-background-color: white");
		
		border.getChildren().add(configure);
		border.setAlignment(Pos.CENTER);
		
		//Create student sign in pane
		answerField FirstName = new answerField("First Name:", "vertical");
		answerField LastName = new answerField("Last Name:", "vertical");
		answerField Nnum = new answerField("N Number:", "vertical");
		answerField Email = new answerField("Email:", "vertical");
		
		GridPane signIn = new GridPane();
		signIn.add(FirstName.getVBox(), 0, 0);
		signIn.add(LastName.getVBox(), 1, 0);
		signIn.add(Nnum.getVBox(), 0, 1);
		signIn.add(Email.getVBox(), 1, 1);
		signIn.setAlignment(Pos.CENTER);
		signIn.setVgap(5.0);
		signIn.setHgap(30.0);
		
		//Save Button
		
		Button cont = new Button("Save");
		cont.setStyle(getStyle());
		cont.setPrefSize(80.0, 40.0);
		border.getChildren().add(cont);
		border.setAlignment(Pos.CENTER);
		cont.setOnAction(e -> {
			if(configured == false) { //Configure page has been completed
				
					while(!fileChooser());
					
					if(fileChooserCanceled == false) {
						
					createFile(qfield1,qfield2,qfield3);
					
					if(qfield1.getQuestionText().length() != 0) {
						windowHeight += 50.0;
						if(qfield1.isCheck()) {
							q1 = new YNorCheck("check",qfield1.getQuestionText());
							border.getChildren().add(q1.getHBox());
							border.setAlignment(Pos.CENTER);
						} else {
							q1 = new YNorCheck("yn", qfield1.getQuestionText());
							border.getChildren().add(q1.getHBox());
							border.setAlignment(Pos.CENTER);
						}
					}
					if(qfield2.getQuestionText().length() != 0) {
						windowHeight += 50.0;
						if(qfield2.isCheck()) {
							q2 = new YNorCheck("check",qfield2.getQuestionText());
							border.getChildren().add(q2.getHBox());
							border.setAlignment(Pos.CENTER);
						} else {
							q2 = new YNorCheck("yn", qfield2.getQuestionText());
							border.getChildren().add(q2.getHBox());
							border.setAlignment(Pos.CENTER);
						}
					}
					if(qfield3.getQuestionText().length() != 0) {
						windowHeight += 50.0;
						if(qfield3.isCheck()) {
							q3 = new YNorCheck("check",qfield3.getQuestionText());
							border.getChildren().add(q3.getHBox());
							border.setAlignment(Pos.CENTER);
						} else {
							q3 = new YNorCheck("yn", qfield3.getQuestionText());
							border.getChildren().add(q3.getHBox());
							border.setAlignment(Pos.CENTER);
						}
					}
					
					border.getChildren().remove(configure);
					border.getChildren().add(2, signIn);
					border.getChildren().remove(cont);
					border.getChildren().add(cont);
					
					title.setText("Sign In");
					
					configured = true;
					
					}
				
			} else {
				
				//Save student Data
				Student hold = new Student();
				hold.setFirstName(FirstName.getAnswer());
				hold.setLastName(LastName.getAnswer());
				hold.setNNUm(Nnum.getAnswer());
				hold.setEmail(Email.getAnswer());
				hold.setResp1(q1.getResponse());
				hold.setResp2(q2.getResponse());
				hold.setResp3(q3.getResponse());
				
				writeToCSV(hold);
				
				FirstName.clearFields();
				LastName.clearFields();
				Nnum.clearFields();
				Email.clearFields();
				q1.clearField();
				q2.clearField();
				q3.clearField();
				hold.clearFields();
				
			}
			stage.setHeight(windowHeight);
		});
		
		//Add border pane to scene
		
		Scene main = new Scene(border);
	
		stage.setTitle("SHPE Sign In");
		stage.setScene(main);
		stage.setResizable(false);
		stage.setWidth(windowWidth);
		stage.setHeight(windowHeight);
		stage.getIcons().add(new Image("resources/images/SHPE-Logo.jpeg"));
		stage.show();
		
	}
	
	public ImageView getImage(String path, double height, double width) {
		URL url = SHPE_Sign_In.class.getResource(path);
		Image pic = new Image(url.toString());
		ImageView picView = new ImageView(pic);
		picView.setFitHeight(height);
		picView.setFitWidth(width);
		return picView;
	}
	
	public Label createLabel(String text, double height, double width, String font, int fontSize){
		Label label = new Label(text);
		label.setMaxSize(width, height);
		label.setMinSize(width, height);
		label.setFont(Font.font(font, FontWeight.BOLD, fontSize));
		return label;
	}
			
	public String getStyle() {
		return "-fx-text-fill: white;\n" +
			   "-fx-font-weight: bold;\n" +
			   "-fx-background-color: linear-gradient(#ADD8E6,#00008B);";
	}
	
	public Boolean fileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		if(triedToSave == true) {
			chooser.setDialogTitle("File already exists, try again");
		} else {
			chooser.setDialogTitle("Save your file");
			triedToSave = true;
		}
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV","csv"));
		chooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
		chooser.setVisible(true);
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.CANCEL_OPTION) {
			triedToSave = false;
			fileChooserCanceled = true;
			return true;
		}
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String filePath = chooser.getSelectedFile().toString() + ".csv";
			File file = new File(filePath);
			if(file.exists()) {
				fileChooserCanceled = false;
				return false;
			} else {
				csvFile = file;
				fileChooserCanceled = false;
				return true;
			}
		} else {
			configured = false;
			fileChooserCanceled = false;
			return false;
		}
	}
	
	public void createFile(QuestionField q1, QuestionField q2, QuestionField q3) {
		try {
			FileWriter write = new FileWriter(csvFile.toString());
			String header = "First Name, Last Name, N#, Email," +
							q1.getQuestionText() +"," +q2.getQuestionText()
							+"," +q3.getQuestionText() +"," +new Date().toString() +"\n";
			write.append(header);
			write.flush();
			write.close();
		} catch(Exception e) {
			
		}
	}
	
	public void writeToCSV(Student stud) {
		try {
			FileWriter write = new FileWriter(csvFile.toString(), true);
			write.append(stud.toCSV());
			write.flush();
			write.close();
		} catch(Exception e) {
			
		}
	}
	
}
