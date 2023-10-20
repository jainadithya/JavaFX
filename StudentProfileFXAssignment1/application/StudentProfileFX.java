//TODO: Adithya Jwalakumar - 3097135
//Note: TODO comments are to provide helpful hints only, make sure to implement all tasks as per specification.

package application;

//Standard JavaFX Imports
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
//imports for controls
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
//layouts
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

//file imports
import java.io.File;
import java.util.function.IntToDoubleFunction;
import java.util.regex.Pattern;

//geometry
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class StudentProfileFX extends Application {
	BorderPane bpMain; //Main layout of window, needs to be global so we can change the background
	//TODO: Declare components that require class scope
	Image img;
	ImageView imView;
	Label lblPath, lblStudent;
	Text txtName, txtNumber,txtProgram;
	Button btnChoose, btnUpdate, btnCustomise, btnPlay, btnPause, btnCalculator;
	Region region;
	ColorSelector cs = new ColorSelector();	
	MediaPlayer mediaPlayer;
	

	//TODO: Instantiate components
	public StudentProfileFX() {
		lblPath = new Label("");
		btnChoose = new Button("Choose Picture");
		// initialise
		lblPath = new Label("");
		btnChoose = new Button("Choose Picture");
		
		// Using try catch
		try {
			img = new Image("./Assets/logo1.jpg");
			imView = new ImageView(img);
		}
		catch(Exception e) {
			System.err.println("Something happened");
		}
		lblStudent = new Label("MY STUDENT PROFILE");
		txtName = new Text("Adithya Jwalakumar");
		txtNumber = new Text("3097135");
		txtProgram = new Text("Higher Diploma In Computing");
		btnUpdate = new Button("Update Details");
		btnCustomise = new Button("Customise");
		btnPlay = new Button("Play");
		btnPause = new Button("Pause");
		btnCalculator = new Button("Calculator");		
	}
	
	//TODO: Event Handling for main UI
	@Override
	public void init() {
		//TODO: Handle events for Choose button
		btnChoose.setOnAction(event -> showPictureDialog());
		
		//TODO: Handle events for UpdateProfile button
		btnUpdate.setOnAction(event -> showDialog());
		
		//TODO: Handle events for Customise button
		btnCustomise.setOnAction(event -> showColorPicker());
		
		// event handling for media player button
		btnPlay.setOnAction(event -> showplayMusic());
		
		//event handling for calculator
		btnCalculator.setOnAction(event -> showCalculator());
		
	}
	
	
	// calculator method
	private void showCalculator() {
		
		// declaring the components and Instantiate the components required
		Label lblNumber1 = new Label("First Number");
		
		Label lblNumber2 = new Label("Second Number");
		Label lblResult = new Label("Result : ");
		Button btnAdd = new Button("Addition(+)");
		Button btnSub = new Button("Subtraction(-)");
		Button btnDiv = new Button("Division(/)");
		Button btnMul = new Button("Multiplication(*)");
		Button btnAverage = new Button("Average");
		Button btnPercent = new Button("Percentage(%)");
		Button btnCancel = new Button("Close");

		TextField txtfNumber1 = new TextField();
		TextField txtfNumber2 = new TextField();
		
		// setting the stage
		Stage dialogStage = new Stage();
		dialogStage.setHeight(325);
		dialogStage.setWidth(350);
		dialogStage.setTitle("Simple Calculator");
		try
		{
			//add the icon
			dialogStage.getIcons().add(new Image("./Assets/calci.png"));
		}
		catch(Exception e)
		{
			System.err.println("Something went wrong with the icon");
			e.printStackTrace();
		}
		btnCancel.setOnAction(btnclicked -> dialogStage.close());
		
        // Create grid pane and add components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(lblNumber1, 0, 0);
        gridPane.add(txtfNumber1, 1, 0);
        gridPane.add(lblNumber2, 0, 1);
        gridPane.add(txtfNumber2, 1, 1);
        gridPane.add(btnAdd, 0, 2);
        btnAdd.setPrefWidth(150);
        gridPane.add(btnSub, 1, 2);
        btnSub.setPrefWidth(150);
        gridPane.add(btnMul, 0, 3);
        btnMul.setPrefWidth(150);
        gridPane.add(btnDiv, 1, 3);
        btnDiv.setPrefWidth(150);
        gridPane.add(btnAverage, 0, 4);
        btnAverage.setPrefWidth(150);
        gridPane.add(btnPercent, 1, 4);
        btnPercent.setPrefWidth(150);
        gridPane.add(lblResult, 0, 5);
        gridPane.add(btnCancel, 1, 6);
        btnCancel.setPrefWidth(150);
		
		// event handling all the buttons
		btnAdd.setOnAction(event -> {
            try 
            {
                double firstNum = Double.parseDouble(txtfNumber1.getText());
                double secondNum = Double.parseDouble(txtfNumber2.getText());
                double result = firstNum + secondNum;
                lblResult.setText("Result: " + result);
            } 
            catch (NumberFormatException ex)
            {
                lblResult.setText("Invalid input");
            }
		});
		btnSub.setOnAction(event ->{
            try 
            {
                double firstNum = Double.parseDouble(txtfNumber1.getText());
                double secondNum = Double.parseDouble(txtfNumber2.getText());
                double result = firstNum - secondNum;
                lblResult.setText("Result: " + result);
            } 
            catch (NumberFormatException ex) 
            {
                lblResult.setText("Invalid input");
            }
		});
		btnDiv.setOnAction(event ->{
            try 
            {
                double firstNum = Double.parseDouble(txtfNumber1.getText());
                double secondNum = Double.parseDouble(txtfNumber2.getText());
                double result = firstNum / secondNum;
                lblResult.setText("Result: " + result);
            } 
            catch (NumberFormatException ex)
            {
                lblResult.setText("Invalid input");
            }
		});
		btnMul.setOnAction(event ->{
            try 
            {
                double firstNum = Double.parseDouble(txtfNumber1.getText());
                double secondNum = Double.parseDouble(txtfNumber2.getText());
                double result = firstNum * secondNum;
                lblResult.setText("Result: " + result);
            }
            catch (NumberFormatException ex)
            {
                lblResult.setText("Invalid input");
            }
		});
		btnAverage.setOnAction(event -> {
            try 
            {
                double firstNum = Double.parseDouble(txtfNumber1.getText());
                double secondNum = Double.parseDouble(txtfNumber2.getText());
                double result = (firstNum + secondNum)/2;
                lblResult.setText("Result: " + result);
            } 
            catch (NumberFormatException ex)
            {
                lblResult.setText("Invalid input");
            }
		});
		btnPercent.setOnAction(event -> {
            try 
            {
                double firstNum = Double.parseDouble(txtfNumber1.getText());
                double secondNum = Double.parseDouble(txtfNumber2.getText());
                double result = ((firstNum + secondNum)/200)*100;
                lblResult.setText("Result: " + result+"%");
            } 
            catch (NumberFormatException ex)
            {
                lblResult.setText("Invalid input");
            }
		});

        // Create scene and add grid pane
        Scene s = new Scene(gridPane);
        dialogStage.setScene(s);
        
		// linking stylesheet
		s.getStylesheets().add("./StudentProfile.css");
		// show the dialog
        dialogStage.show();

		
		
	}

	
	// media player method
	private void showplayMusic() {
        String musicUrl = "https://soundcloud.com/paulo-siqueira/super-mario-bros-theme-song?utm_source=clipboard&utm_medium=text&utm_campaign=social_sharing";
        Media media = new Media(musicUrl);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
//        Media media = new Media(new File("https://soundcloud.com/paulo-siqueira/super-mario-bros-theme-song?utm_source=clipboard&utm_medium=text&utm_campaign=social_sharing").toURI().toString());
//        mediaPlayer = new MediaPlayer(media);

        // Create play/pause button
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.pause();
            btnPlay.setText("Play");
        } 
        else
        {
            mediaPlayer.play();
            btnPlay.setText("Pause");
        }

	}

	
	//TODO: Show System Dialog to choose a profile photo (.png only)
	private void showPictureDialog() {
		FileChooser fc = new FileChooser();
		// Show System dialog (filter only .png images)
		fc.getExtensionFilters().add(new ExtensionFilter("Only .png files", "*.png"));
		//TODO: Get Image chosen by user
		File chosenFile = fc.showOpenDialog(null);
		// Show chosen image in main UI if not null
		try 
		{
			img = new Image(chosenFile.toURI().toString());
			imView.setImage(img);
		}
		catch(Exception e) 
		{
			System.err.println("Error occured");
			e.printStackTrace();
		}
	}
	
	// Show Custom Dialog to choose background color
	private void showColorPicker() {
		// Setup a new Stage for the dialog color picker
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Color Picker");
		try
		{
			//add the icon
			dialogStage.getIcons().add(new Image("./Assets/colors.jpg"));
		}
		catch(Exception e)
		{
			System.err.println("Something went wrong with the icon");
			e.printStackTrace();
		}
		dialogStage.setWidth(400);
		dialogStage.setHeight(350);
		
		// Create components for the dialog
		BorderPane bpDialog = new BorderPane();
		Button btnOk = new Button("Apply Background");
		Button btnCancel = new Button("Cancel");
		HBox hBox = new HBox();
		region = new Region();
		hBox.setHgrow(region, Priority.ALWAYS);





		// Event handling for dialog
		btnCancel.setOnAction(btnclicked -> dialogStage.close());
		// Event handling for dialog
		btnOk.setOnAction(ae -> {
			bpMain.setStyle("-fx-background-color: #"+cs.getHex()+";");
			dialogStage.close();
		});
		
		// Create and Manage Scene & Layouts for dialog
		Scene s = new Scene(bpDialog);
		dialogStage.setScene(s);		
		bpDialog.setTop(cs);
		bpDialog.setBottom(hBox);
        btnOk.setPrefWidth(150);
        btnCancel.setPrefWidth(150);
		hBox.getChildren().addAll(region, btnOk, btnCancel);

        
        hBox.setMargin(btnOk, new Insets(0,20,20,20));
        hBox.setMargin(btnCancel, new Insets(0,20,20,20));
		bpDialog.setPadding(new Insets(20));
		
		// linking stylesheet
		s.getStylesheets().add("./StudentProfile.css");
		
		//TODO: Show the dialog
		dialogStage.show();
		
	}
	
	//TODO: show a dialog to update user details
	private void showDialog() {
		// Set up the new Stage
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Please Enter Your Details");
		try
		{
			//add the icon
			dialogStage.getIcons().add(new Image("./Assets/details.png"));
		}
		catch(Exception e)
		{
			System.err.println("Something went wrong with the icon");
			e.printStackTrace();
		}
		dialogStage.setWidth(450);
		dialogStage.setHeight(300);
		
		// Adding Main layout for the dialog
		VBox vbDialog = new VBox();
		//subcontainers (GripPane, HBox)
		GridPane gpDialog = new GridPane();
		HBox hbButtons = new HBox();
		//spacing for the sub containers
		hbButtons.setSpacing(30);
		gpDialog.setVgap(20);
		gpDialog.setHgap(10);
		
		//TODO: Create components for the dialog
		Label lblName = new Label("Full Name");
		TextField txtfName = new TextField();
		Label lblNumber = new Label("Student Number");
		TextField txtfNumber = new TextField();
		Label lblCourse = new Label("Program To Study");
		TextField txtfCourse = new TextField();
		Button btnCancel = new Button("CANCEL");
		Button btnOk = new Button("OK");
		ChoiceBox<String> choiceBox = new ChoiceBox<>();

		
		// get the valuses to choice box
		choiceBox.getItems().addAll("Higher Diploma In Computing","Cyber Security","Data Analytics","Cloud Computing");
		// setting default value on choice box
		choiceBox.setValue("Higher Diploma In Computing");
		
		// Event handling for dialog
		btnCancel.setOnAction(btnclicked -> dialogStage.close());

		btnOk.setOnAction(btnclicked -> {
			
			//getting the choice from the choice box
			String course = choiceBox.getValue();
			
			//get the text from the main text
			String username = txtfName.getText();
			
			//validate the username (before doing anything with it)
			String pattern = "[A-Z]{1}[a-z]{1,50}\s[A-Z[.]]{1}[a-z]*";
			
			//get the text from the student number text field
			String studentnum = txtfNumber.getText();
			
			//pattern for student number(7-10 digits from 0-9)
			int minDigit = 7;
			int maxDigit = 8;
			String patternNum = "[0-9]{" + minDigit + "," + maxDigit + "}";
			
			//TODO: Validation on user input
			if((Pattern.matches(pattern, username)==false))
			{
				//prompts the user to tell them to give us a valid name
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Wrong name pattern");
				alert.setHeaderText("Please provide a valid user name");
				alert.setContentText("Name must have First name and Second name both starting with uppercase");
				alert.showAndWait();
			}
			else if((Pattern.matches(patternNum, studentnum)==false))
			{
				//prompts the user to tell them to give us a valid name
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Wrong number pattern");
				alert.setHeaderText("Please provide a valid user number");
				alert.setContentText("Student number must consist of "+ minDigit +" to "+maxDigit+" digits only");
				alert.showAndWait();
			}
			else
			{
				//show those two in the main text area
				txtName.setText(username);
				txtNumber.setText(studentnum);
				txtProgram.setText(course);
				
				// Printing in the console for reference
				// username
				System.out.println(username);
				// your student number is 12234
				System.out.println(studentnum);
				// your course is
				System.out.println(course);

				
				// close the dialog
				dialogStage.close();
			}
		});
		
		//TODO: Create and Manage Scene & Layouts for dialog
		gpDialog.add(lblName, 0, 0);
		gpDialog.add(txtfName, 1, 0);
		gpDialog.add(lblNumber, 0, 1);
		gpDialog.add(txtfNumber, 1, 1);
		gpDialog.add(lblCourse, 0, 2);
		gpDialog.add(choiceBox, 1, 2);
		
		hbButtons.getChildren().addAll(btnCancel, btnOk);
        btnCancel.setPrefWidth(100);
        btnOk.setPrefWidth(100);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		hbButtons.setPadding(new Insets(30));
		gpDialog.setPadding(new Insets(30));
//		gpDialog.setGridLinesVisible(true);

		
		// Adding sub-layout to the main layout
		vbDialog.getChildren().addAll(gpDialog, hbButtons);		
		
		// Creating scene
		Scene s = new Scene(vbDialog);
		// Set the scene
		dialogStage.setScene(s);
		// linking stylesheet
		s.getStylesheets().add("./StudentProfile.css");

		//TODO: Show the dialog
		dialogStage.show();

	}

	//TODO: Set up the main UI window & scene
	@Override
	public void start(Stage primaryStage) throws Exception {
		//TODO: set up main window
		//setup window and show it
		primaryStage.setTitle("3097135 - Adithya Jwalakumar");
		try
		{
			//add the icon
			primaryStage.getIcons().add(new Image("./Assets/logo1.jpg"));
		}
		catch(Exception e)
		{
			System.err.println("Something went wrong with the icon");
			e.printStackTrace();
		}
		primaryStage.setWidth(500);
		primaryStage.setHeight(350);
		
		//TODO: manage containers
		bpMain = new BorderPane(); //main container (background to be changed)
		HBox hBox = new HBox();		
		GridPane gPane = new GridPane();
		VBox vBox = new VBox();
		region = new Region();
		
		// Adding components to the sublayout
		hBox.getChildren().addAll(region, btnCalculator, btnPlay, btnCustomise);
		gPane.getChildren().addAll(lblStudent, txtName, txtNumber, txtProgram, btnUpdate);
		vBox.getChildren().addAll(imView, btnChoose);
		
		// Add components to the layout main layout
		bpMain.setBottom(hBox);
		bpMain.setLeft(vBox);
		bpMain.setCenter(gPane);
		
		// Adding spacing and padding to the layouts
		bpMain.setPadding(new Insets(20,20,0,20));
		hBox.setMargin(btnCustomise, new Insets(5));
		btnCustomise.setPrefWidth(100);
		hBox.setMargin(btnPlay, new Insets(5));
		btnPlay.setPrefWidth(100);
		hBox.setMargin(btnCalculator, new Insets(5));
		btnCalculator.setPrefWidth(100);
		hBox.setHgrow(region, Priority.ALWAYS);

//		gPane.setGridLinesVisible(true);
		gPane.setVgap(23);
		gPane.setHgap(23);
		gPane.setPadding(new Insets(20));
		GridPane.setConstraints(lblStudent, 0, 0, 9, 1);
		GridPane.setConstraints(txtName, 0, 1, 9, 1);
		GridPane.setConstraints(txtNumber, 0, 2, 9, 1);
		GridPane.setConstraints(txtProgram, 0, 3, 9, 1);
		GridPane.setConstraints(btnUpdate, 0, 4, 9, 1);
		
		// Settingup the image and button
		vBox.setVgrow(imView, Priority.ALWAYS);
		vBox.setVgrow(btnChoose, Priority.SOMETIMES);
		vBox.setAlignment(Pos.TOP_CENTER);
		btnChoose.setMaxWidth(Double.MAX_VALUE);
		
		//TODO: bind sizes of ImageView and Button to 25% of window size
		imView.setPreserveRatio(true);
		imView.fitWidthProperty().bind(primaryStage.widthProperty().divide(2.5));
				
		//TODO: set default background of main UI to last 6 digits of your student number
		changeBackground(cs.getHex());
		
		// Creating a new Scene, takes in main layout
		Scene s = new Scene(bpMain);
		
		// linking stylesheet
		s.getStylesheets().add("./StudentProfile.css");

		
		// Set the scene
		primaryStage.setScene(s);

		//TODO: show the window
		primaryStage.show();

	}


	//TODO: Launch the application
	public static void main(String[] args) {
		launch(args);
	}
	
	
	// ---- You do not need to change this method, just use as needed ---
	public void changeBackground(String hexcode) {
		//change background color of main layout
		bpMain.setStyle("-fx-background-color:\n"
				+ "            linear-gradient("
				+ "#" + hexcode
				+ ", #FFFFFF);");
	}
}
