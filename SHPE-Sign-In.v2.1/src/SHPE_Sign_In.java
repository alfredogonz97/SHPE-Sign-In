import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SHPE_Sign_In extends Application {
	
	File csvFile;
	boolean triedToSave;
	boolean fileChooserCanceled;

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		
		///////////////// Configure the Stage /////////////////
		stage.setTitle(Styling.getStageTitle());
		stage.getIcons().add(Styling.getStageIcon());
		
		Scene askQuestionsScene;
		
		///////////////// Configure askQuestionScene /////////////////
		
		/// Create Scroll Pane and VBox ///
		ScrollPane scrollQ = new ScrollPane();
		VBox qPane = new VBox();
		scrollQ.setContent(qPane);
		scrollQ.setFitToHeight(true); scrollQ.setFitToWidth(true);
		
		/// VBox Styling ///
		qPane.setStyle(Styling.getVBoxStyle());
		qPane.setAlignment(Pos.CENTER);
		qPane.setSpacing(20.0);
		//qPane.setPadding(new Insets(10.0,10.0,10.0,10.0));
		
		/// Image Header ///
		qPane.getChildren().add(Styling.getMainLogo());
		
		/// Text Header ///
		Label header = Styling.createLabel("Have Any Questions to Ask?", 500.0, 60.0, 36, Styling.getLabelStyle());
		header.setAlignment(Pos.CENTER);
		qPane.getChildren().add(header);
		
		/// Question Fill Boxes ///
		QuestionsCircularSinglyLinkedList questions = new QuestionsCircularSinglyLinkedList();
		questions.add();
		qPane.getChildren().add(questions.getSpace());
		
		/// Question dd/Sub Buttons ///
		Button add = Styling.createButton("+", true);
		Button sub = Styling.createButton("-", true);
		add.setOnAction(e -> {
			questions.add();
		});
		sub.setOnAction(e -> {
			questions.removeLast();
		});
		HBox addSubHolder = new HBox();
		addSubHolder.setSpacing(2.0);
		addSubHolder.getChildren().addAll(add,sub);
		addSubHolder.setAlignment(Pos.CENTER);
		addSubHolder.setStyle(Styling.getHBoxStyle());
		qPane.getChildren().add(addSubHolder);
		
		/// Continue Button ///
		Button continueButton = Styling.createButton("Continue", true);
		continueButton.setOnAction(e -> {
			while(!fileChooser());
			createFile(questions);
			stage.setScene(createSignInScene(questions));
		});
		qPane.getChildren().add(continueButton);
		
		/// Create Scene ///
		askQuestionsScene = new Scene(scrollQ);
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		stage.setScene(askQuestionsScene);
		stage.setWidth(1100.0);
		stage.setHeight(825.0);
		//stage.setResizable(false);
		stage.show();
	}
	
	public Scene createSignInScene(QuestionsCircularSinglyLinkedList questions) {
		/// Create Scroll Pane and VBox ///
		ScrollPane scroll = new ScrollPane();
		VBox pane = new VBox();
		scroll.setContent(pane);
		scroll.setFitToHeight(true); scroll.setFitToWidth(true);
		
		/// VBox Styling ///
		pane.setStyle(Styling.getVBoxStyle());
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(30.0);
		pane.setPadding(new Insets(10.0,10.0,10.0,10.0));
		
		/// Image Header ///
		pane.getChildren().add(Styling.getMainLogo());
		
		/// Text Header ///
		Label header = Styling.createLabel("Sign In", 500.0, 60.0, 36, Styling.getLabelStyle());
		header.setAlignment(Pos.CENTER);
		pane.getChildren().add(header);
		
		/// Sign In Fields ///
		AnswerField firstName = new AnswerField("First Name");
		AnswerField lastName = new AnswerField("Last Name");
		AnswerField nNumber = new AnswerField("N Number");
		AnswerField prefEmail = new AnswerField("Preferred Email");
		AnswerField major = new AnswerField("Major");
		
		GridPane signInFields = new GridPane();
		signInFields.setStyle(Styling.getGridPaneStyle());
		
		signInFields.add(firstName.getSpace(), 0, 0);
		signInFields.add(lastName.getSpace(), 1, 0);
		signInFields.add(nNumber.getSpace(), 0, 1);
		signInFields.add(major.getSpace(), 1, 1);
		signInFields.add(prefEmail.getSpace(), 0, 2);
		GridPane.setColumnSpan(prefEmail.getSpace(), 2);
		signInFields.setAlignment(Pos.CENTER);
		
		pane.getChildren().add(signInFields);
		pane.setAlignment(Pos.CENTER);
		
		/// Answer Fill Boxes ///
		AnswersCircularSinglyLinkedList answers = new AnswersCircularSinglyLinkedList(questions);
		pane.getChildren().add(answers.getSpace());
		pane.setAlignment(Pos.CENTER);
		
		/// Save Button ///
		Button save = Styling.createButton("Save", true);
		save.setOnAction(e -> {
			Student hold = new Student(firstName.getAnswer(),lastName.getAnswer(),nNumber.getAnswer(),
					                   prefEmail.getAnswer(),major.getAnswer());
			AnswerBlock walk = answers.getTail().getNext();
			while(walk != answers.getTail()) {
				hold.addQuestion(walk.getQuestion(), walk.getAnswer());
				walk = walk.getNext();
			}
			hold.addQuestion(walk.getQuestion(), walk.getAnswer());	
			
			writeToCSV(hold);
			
			firstName.clear(); lastName.clear();
			nNumber.clear(); prefEmail.clear();
			major.clear();
			answers.clearAllFields();			
			
		});
		pane.getChildren().add(save);
		pane.setAlignment(Pos.CENTER);
		
		
		return new Scene(scroll);
	}
	
	public void createFile(QuestionsCircularSinglyLinkedList questions) {
		try {
			FileWriter write = new FileWriter(csvFile.toString());
			String header = "First Name, Last Name, N#, Preffered Email, UNF Email, Major," +questions.toCSV() +"," +new Date().toString() +"\n";
			write.append(header);
			write.flush();
			write.close();
		} catch(Exception e) {
			
		}
	}
	
	public boolean fileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		
		if(triedToSave == true) { // if this is the second or greater time you tried to save
			chooser.setDialogTitle("File already exists, try again");
		} else { // first intent to save
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
			fileChooserCanceled = false;
			return false;
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
