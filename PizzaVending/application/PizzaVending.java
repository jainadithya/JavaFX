package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.Locale;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PizzaVending extends Application {
	BorderPane bpMain;
	ListView<String> lvItems, lvContent;
	Label lblOrdering, lblCreateOrder;
	Button btnAdd, btnRemove, btnClear, btnPay;
	ProgressBar progBar;
	Text txtTotal;
	int price;
	CheckBox chkCheese;

	// one-off task
	Task<Void> task;

	public PizzaVending() {
		// TODO Auto-generated constructor stub
		lvItems = new ListView<String>();
		lvContent = new ListView<String>();
		txtTotal = new Text();
		lblOrdering = new Label("Add to order..");
		lblCreateOrder = new Label("Pizza Vending Machine - Create Order");
		btnAdd = new Button("Add to Order");
		btnRemove = new Button("Remove from Order");
		btnClear = new Button("Clear Cart");
		btnPay = new Button("Pay");
		progBar = new ProgressBar(0);
		progBar.setStyle("-fx-accent: green;");
		progBar.setVisible(false);
		price = 0;
		chkCheese = new CheckBox("Extra Cheese + €1");
		lblOrdering.getStyleClass().add("gridPane");

		readItemNames("./Assets/Items.csv");

	}

	// read a file, populate listview with items
	private void readItemNames(String ItemsFile) {
		// TODO Auto-generated method stub
		try {
			// String to store line we read
			String line;
			BufferedReader buf = new BufferedReader(new FileReader(ItemsFile));

			// read file line by line
			while ((line = buf.readLine()) != null) {
				// store data from each line in a 3 item array
				String[] itemDataArray = new String[3];
				itemDataArray = line.split(",");

				// add just the names to the listview(index 0)
				lvItems.getItems().add(itemDataArray[0] + " €" + itemDataArray[1]);
			}
			// close the buffered reader
			buf.close();

		} catch (Exception e) {
			System.err.println("Error reading this file " + ItemsFile);
		}

	}

	// event handling
	public void init() {
//		lvItems.setOnMousePressed(event -> addItems());

		btnPay.setOnAction(event -> confirmPayment());

		btnAdd.setOnAction(event -> toCart());

		btnRemove.setOnAction(event -> offCart());

		btnClear.setOnAction(event -> clearAll());

	}

	// --------------------------------------------
	private void clearAll() {
		lvContent.getItems().clear();
		price = 0;
		txtTotal.setText("");
		lblOrdering.setText("Cart Cleared..");
	}

	// -------------------------------------------------
	private void offCart() {
		// TODO Auto-generated method stub
		// get the name selected
		String selectedItem = lvContent.getSelectionModel().getSelectedItem();
		System.out.println(selectedItem);

		if (selectedItem != null) 
		{
			System.out.println("Found The Item" + selectedItem);
			lvContent.getItems().remove(selectedItem);
			String[] selection = new String[2];
			selection = selectedItem.split("€");
			price -= Integer.parseInt(selection[1]);
			txtTotal.setText("Total: " + price + "€");
			lvContent.getSelectionModel().clearSelection();// to clear the selected item
			lblOrdering.setText("Removed 1 Item From Cart");

		}
	}

	// ---------------------------------------------
	private void confirmPayment() {
		// TODO Auto-generated method stub
		lblOrdering.setText("Confirming Payment");
		if (txtTotal.getText().isBlank()) {
//			Platform.exit();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Add Items");
			alert.setHeaderText("Please Add Items To Make Payment");
			alert.setContentText("Total Items 0");
			alert.showAndWait();
			lblOrdering.setText("Add Items To Make Payment");
		} else {
			// prompt the user first(alert!) //should prompt save and quit??
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Payment");
			alert.setHeaderText("Please Confirm Payment");
			alert.setContentText("Total: €" + price);
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					progBar.setVisible(true);
					// save their work
					System.out.println("Making Payment");
					lblOrdering.setText("Payment Confirmed \n Preparing Food");
					try {
						task = new Task<Void>() {
							@Override
							public Void call() throws InterruptedException {
								// the functionality of the task
								final long max = 100000000;
								System.out.println("The thread is running and task started");

								// loop to simulate a long task
								for (long i = 1; i <= max; i++) {
									if (isCancelled()) {
										updateProgress(0, max);
										break;
									}
									updateProgress(i, max);
								}
								return null;
							}
						};
						new Thread(task).start();
						progBar.progressProperty().bind(task.progressProperty());
						task.setOnSucceeded(e -> {
							lvContent.getItems().clear();
							lblOrdering.setText("Order Completed \nEnjoy You Meal");
							progBar.setVisible(false);
							price = 0;
							txtTotal.setText("");
							chkCheese.setSelected(false);
							Alert alert1 = new Alert(AlertType.INFORMATION);
							alert1.setTitle("Thank You");
							alert1.setHeaderText("Enjoy Your Food, Visit Us Again");
							alert1.setContentText(" ");
							alert1.showAndWait();

						});
					} catch (Exception e) {
						System.err.println("Payment Failed");
						e.printStackTrace();
					}

				} else {
					// they dont want to save just close the app
					Platform.exit();
				}

			});
		}
	}

	// ------------------------------------------------------------

	private void toCart() {
		// TODO Auto-generated method stub
		// get the name selected
		String selectedItem = lvItems.getSelectionModel().getSelectedItem().toString();
		System.out.println(selectedItem);
		// read the csv file
		if (selectedItem.isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Add Items");
			alert.setHeaderText("Please Add Items To Make Payment");
			alert.setContentText("Total Items 0");
			alert.showAndWait();

		}

		else {

			if (selectedItem != null)
			{
				if (chkCheese.isSelected()) {
					lblOrdering.setText("Added 1 Item with extra cheese");

					String[] selection = new String[2];
					selection = selectedItem.split("€");
					price += Integer.parseInt(selection[1]);
					price = price + 1;
					txtTotal.setText("€" + price);
					int count = 1 + Integer.valueOf(selection[1]);
					lvContent.getItems().add(selection[0] + " (extra cheese) €" + count);
					lvItems.getSelectionModel().clearSelection();// to clear the selected item
					chkCheese.setSelected(false);
					txtTotal.setText("Total: " + price + "€");

				} else {
					System.out.println("Found The Item" + selectedItem);
					String[] selection1 = new String[2];
					selection1 = selectedItem.split("€");
					price += Integer.parseInt(selection1[1]);
					lvContent.getItems().add(selectedItem);
					lblOrdering.setText("Item Added Successfully");
					lvItems.getSelectionModel().clearSelection();// to clear the selected item
					chkCheese.setSelected(false);
					txtTotal.setText("Total: " + price + "€");
				}

			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// setup window and show it
		primaryStage.setTitle("3097135 - Adithya Jwalakumar");
		try {
			// add the icon
			primaryStage.getIcons().add(new Image("./Assets/ledger.png"));
		} catch (Exception e) {
			System.err.println("Something went wrong with the icon");
			e.printStackTrace();
		}
		primaryStage.setWidth(900);
		primaryStage.setHeight(650);

		// bind the width of the listView to 1/3rd pf the window width
		lvItems.minWidthProperty().bind(primaryStage.widthProperty().divide(3));
		// bind the width of the listView to 1/3rd pf the window width
		lvContent.minWidthProperty().bind(primaryStage.widthProperty().divide(3));

		// main container
		bpMain = new BorderPane();

		// sub container
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		StackPane sPane = new StackPane();

		// adding sub-containers to main container
		bpMain.setTop(lblCreateOrder);
		bpMain.setLeft(lvItems);
		bpMain.setCenter(vBox);
		bpMain.setBottom(sPane);
		bpMain.setRight(lvContent);
		Region region = new Region();
		hBox.setHgrow(region, Priority.ALWAYS);
		// adding components to sub-layout
		vBox.getChildren().addAll(btnAdd, btnRemove, btnClear, chkCheese);
		hBox.getChildren().addAll(lblOrdering, region, txtTotal, btnPay);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setMargin(btnAdd, new Insets(10));
		btnAdd.setMaxWidth(200);
		vBox.setMargin(btnRemove, new Insets(10));
		btnRemove.setMaxWidth(200);
		vBox.setMargin(btnClear, new Insets(10));
		btnClear.setMaxWidth(200);
		vBox.setMargin(chkCheese, new Insets(10));
		chkCheese.setMaxWidth(200);
		hBox.setMargin(lblOrdering, new Insets(10));
		hBox.setMargin(txtTotal, new Insets(10));
		hBox.setMargin(btnPay, new Insets(10));
		btnPay.setPrefWidth(100);
		bpMain.setMargin(lvItems, new Insets(10));
		bpMain.setMargin(lvContent, new Insets(10));
		bpMain.setMargin(lblCreateOrder, new Insets(10));
		sPane.getChildren().addAll(hBox, progBar);
		progBar.setMaxWidth(400);

		// Creating a new Scene, takes in main layout
		Scene s = new Scene(bpMain);
		// TODO: Apply a stylesheet ("intrcalc_style.css")
		s.getStylesheets().add("./PizzaVending.css");

		// Set the scene
		primaryStage.setScene(s);

		// TODO: show the window
		primaryStage.show();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
