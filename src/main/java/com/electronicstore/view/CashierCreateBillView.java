package com.electronicstore.view;

import com.electronicstore.model.Bill;
import com.electronicstore.model.BillItem;
import com.electronicstore.model.Category;
import com.electronicstore.model.Item;
import com.electronicstore.model.Supplier;
import com.electronicstore.model.User;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;











import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CashierCreateBillView extends Application {
    private User loggedInUser;

    public CashierCreateBillView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {

        VBox leftSide = new VBox(20);
        leftSide.setStyle("-fx-padding: 20; -fx-background-color: rgb(255, 255, 255);");
        leftSide.setStyle("-fx-padding: 40; -fx-background-color: rgb(255, 255, 255);");


        ImageView profileImage = new ImageView(new Image("file:/C:/Users/user/OneDrive/Desktop/profile.png"));
        profileImage.setFitHeight(100);
        profileImage.setFitWidth(100);
        ImageView profileImage = new ImageView(new Image(getClass().getResource("/user.png").toExternalForm()));
        profileImage.setFitHeight(80);
        profileImage.setFitWidth(80);

        Label profileName = new Label("Nik Lipi");
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label role = new Label("Role: Cashier");
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label sector = new Label("Sector: Sector1");
        sector.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label profileName = new Label(loggedInUser.getName());
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label role = new Label("Role: " + loggedInUser.getAccess_level());
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");



        leftSide.getChildren().addAll(profileImage, profileName, role, sector);
        leftSide.getChildren().addAll(profileImage, profileName, role);

        HBox topBar = new HBox(10);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: rgb(255, 255, 255);");

        Button logoutButton = new Button("Logout");
        
        ImageView logoutIcon = new ImageView(new Image("file:/C:/Users/Hello/Downloads/logout.png/"));
        logoutIcon.setFitHeight(40);  
        logoutIcon.setFitWidth(40);   
        logoutButton.setOnAction(event -> {
            try {
                new LogIn().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ImageView logoutIcon = new ImageView(new Image(getClass().getResource("/logout.png").toExternalForm()));
        logoutIcon.setFitHeight(40);
        logoutIcon.setFitWidth(40);
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color:  white; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size: 16px;");
        logoutButton.setStyle("-fx-background-color: white; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold ");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView topLeftImage = new ImageView(new Image("file:/C:/Users/Hello/Downloads/login.png/"));
        topLeftImage.setFitHeight(50);  
        topLeftImage.setFitWidth(50);
        ImageView topLeftImage = new ImageView(new Image(getClass().getResource("/logo.png").toExternalForm()));
        topLeftImage.setFitHeight(80);
        topLeftImage.setFitWidth(80);

        topBar.getChildren().addAll(topLeftImage, spacer, logoutButton);
        topBar.setAlignment(Pos.CENTER);

        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75); -fx-min-height: 5px; -fx-max-height: 5px;");

        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);  
        topLayout.getChildren().addAll(topBar, blueLine);

        GridPane center=new GridPane();
        center.setVgap(30);
        GridPane center = new GridPane();
        center.setVgap(10);
        center.setHgap(50);
        center.setStyle("-fx-padding: 20;");

        Button goBack=new Button ("< Go Back");
        goBack.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 14px;");
        Button goBack = new Button("< Go Back");
        goBack.setStyle("-fx-background-color: rgb(5, 39, 75); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 20px; -fx-padding: 5 15;");
        center.add(goBack, 0, 0);
        goBack.setOnAction(e -> {
            CashierViewMain cashierView = new CashierViewMain(loggedInUser);
            try {
                cashierView.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label createNewBill=new Label("Create New Bill");
        createNewBill.setStyle("-fx-text-fill: rgb(0, 0, 0); -fx-font-weight: bold; -fx-font-size:20 ");
        Label createNewBill = new Label("Create New Bill");
        createNewBill.setStyle("-fx-text-fill: rgb(0, 0, 0); -fx-font-weight: bold; -fx-font-size: 20;");
        center.add(createNewBill, 0, 1);

        ComboBox<String> itemBox = new ComboBox<>();
        itemBox.getItems().addAll("Computer", "Mouse", "Keyboard", "Speaker", "Printer", "Headphones");
        itemBox.setValue("Select An Item");
        ComboBox<Item> itemBox = new ComboBox<>();
        ObservableList<Item> items = FXCollections.observableArrayList(new ItemLoader().loadItems());
        itemBox.setItems(items);

        itemBox.setCellFactory(cell -> new ListCell<>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });

        itemBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });

        itemBox.setPromptText("Select An Item");
        itemBox.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: rgb(92, 143, 198); -fx-font-size: 14px;");
        center.add(itemBox, 0, 2);

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-size: 14px;");
        center.add(quantityField, 1, 2); 
        
        center.add(quantityField, 1, 2);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: rgb(187, 0, 0); -fx-font-size: 14px;");
        errorLabel.setVisible(false);
        center.add(errorLabel, 0, 3, 2, 1);

        TableView<BillItem> billTable = new TableView<>();
        billTable.setStyle("-fx-padding: 10;");
        billTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        billTable.setPrefHeight(600);
        billTable.setPrefWidth(600);
        billTable.setPlaceholder(new Label("No items added."));

        TableColumn<BillItem, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getItem().getName()));

        TableColumn<BillItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getItemQuantity()).asObject());

        TableColumn<BillItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getItem().getSellingPrice() * data.getValue().getItemQuantity()).asObject());

        billTable.getColumns().addAll(itemColumn, quantityColumn, priceColumn);

        ObservableList<BillItem> billItems = FXCollections.observableArrayList();
        billTable.setItems(billItems);

        Button addItem = new Button("+");
        addItem.setStyle("-fx-background-color: rgb(228, 234, 239); -fx-text-fill: blue; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 50%;");
        center.add(addItem, 0, 3, 2, 1);
        GridPane.setHalignment(addItem, javafx.geometry.HPos.CENTER);
        addItem.setOnAction(event -> {
            Item selectedItem = itemBox.getValue();
            String quantityText = quantityField.getText();
            errorLabel.setVisible(false);

            if (selectedItem == null) {
                errorLabel.setText("Please select an item.");
                errorLabel.setVisible(true);
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    errorLabel.setText("Quantity must be greater than zero.");
                    errorLabel.setVisible(true);
                    return;
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("Invalid quantity. Please enter a valid number.");
                errorLabel.setVisible(true);
                return;
            }

            BillItem existingItem = billItems.stream()
                    .filter(item -> item.getItem().equals(selectedItem))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                existingItem.setItemQuantity(existingItem.getItemQuantity() + quantity);
            } else {
                billItems.add(new BillItem(selectedItem, quantity));
            }

            itemBox.setValue(null);
            quantityField.clear();
        });

        center.add(billTable, 2, 0, 1, 5);

        TextField searchField = new TextField();
        searchField.setPromptText("Search Item...");
        searchField.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-font-size: 14px;");

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: rgb(19, 68, 121); -fx-text-fill: white; -fx-font-size: 14px;");

        searchButton.setOnAction(event -> {
            String searchTerm = searchField.getText().trim().toLowerCase();
            ObservableList<BillItem> filteredItems = FXCollections.observableArrayList();

            for (BillItem item : billItems) {
                if (item.getItem().getName().toLowerCase().contains(searchTerm)) {
                    filteredItems.add(item);
                }
            }

            if (filteredItems.isEmpty()) {
                billTable.setPlaceholder(new Label("No items match the search term."));
            }
            billTable.setItems(filteredItems);
        });

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: rgb(228, 234, 239); -fx-text-fill: rgb(19, 68, 121); -fx-font-size: 14px;");
        resetButton.setOnAction(event -> {
            billTable.setItems(billItems);
            searchField.clear();
            billTable.setPlaceholder(new Label("No items added."));
        });

        HBox searchBox = new HBox(10, searchField, searchButton, resetButton);
        searchBox.setAlignment(Pos.CENTER_RIGHT);
        center.add(searchBox, 2, 5);
        VBox buttonContainer = new VBox(10);
        buttonContainer.setAlignment(Pos.CENTER);

        Button addItembtn = new Button("+");
        addItembtn.setStyle("-fx-background-color: rgb(228, 234, 239); -fx-text-fill: blue; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 50%;");

        Button createBillButton = new Button("Create Bill");
        createBillButton.setStyle("-fx-background-color: rgb(19, 68, 121); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 20;");
        center.add(createBillButton, 0, 4, 2, 1);
        GridPane.setHalignment(createBillButton, javafx.geometry.HPos.CENTER);
        

        createBillButton.setOnAction(event -> {
            if (billItems.isEmpty()) {
                errorLabel.setText("Cannot create an empty bill.");
                errorLabel.setVisible(true);
                return;
            }

            Date currentDate = new Date();
            double totalPrice = billItems.stream()
                    .mapToDouble(billItem -> billItem.getItem().getSellingPrice() * billItem.getItemQuantity())
                    .sum();

            ArrayList<BillItem> billItemList = new ArrayList<>(billItems);

            int sectorId = 1; // Arbitrarily assign sectorId = 1

            // Generate new billId and billNumber
            int newBillId = generateNewBillId(); // Ensure you have a method to generate a new Bill ID
            String billNumber = "INV" + String.format("%03d", newBillId);

            // Create a new Bill instance
            Bill newBill = new Bill(
                    newBillId,
                    billNumber,
                    currentDate, // Make sure this is a valid Date object
                    billItemList,
                    totalPrice,
                    sectorId
            );

            // Append the new bill to the CSV file
            appendBillToCSV(newBill);

            billItems.clear();
            errorLabel.setVisible(false);
            showAlert(Alert.AlertType.INFORMATION, "Bill created successfully.");
        });
        addItembtn.setOnAction(event -> {
            Item selectedItem = itemBox.getValue();
            String quantityText = quantityField.getText();

            errorLabel.setVisible(false);

            if (selectedItem == null || selectedItem.equals("Select An Item")) {
                errorLabel.setText("Please select an item.");
                errorLabel.setVisible(true);
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    errorLabel.setText("Quantity must be greater than zero.");
                    errorLabel.setVisible(true);
                    return;
                }
            } catch (NumberFormatException e) {
                errorLabel.setText("Invalid quantity. Please enter a valid number.");
                errorLabel.setVisible(true);
                return;
            }

            boolean itemExists = false;
            for (BillItem item : billItems) {
                if (item.getItem().equals(selectedItem)) {
                    //item.quantityProperty().set(item.getItemQuantity() + quantity);
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                billItems.add(new BillItem(selectedItem, quantity));
            }

            itemBox.setStyle("Select An Item");
            quantityField.clear();
        });

        buttonContainer.getChildren().addAll(addItembtn, createBillButton);
        center.add(buttonContainer, 0, 4, 2, 1);
        GridPane.setHalignment(buttonContainer, HPos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);  
        pane.setTop(topLayout);
        pane.setCenter(center);

        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setTitle("Cashier/CreateBill");
        Scene scene = new Scene(pane, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cashier - Create Bill");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private int generateNewBillId() {
        File billsFile = new File("src/main/resources/files/bills.csv");
        int maxId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(billsFile))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                int billId = Integer.parseInt(line.split(",")[0]);
                maxId = Math.max(maxId, billId);
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error generating Bill ID: " + e.getMessage());
        }
        return maxId + 1;
    }
    private void appendBillToCSV(Bill bill) {
        File billsFile = new File("src/main/resources/files/bills.csv");
        File itemsFile = new File("src/main/resources/files/items.csv");

        try (BufferedWriter billsWriter = new BufferedWriter(new FileWriter(billsFile, true))) {
            StringBuilder itemsString = new StringBuilder();
            for (BillItem billItem : bill.getBillItems()) {
                Item item = billItem.getItem();
                int quantitySold = billItem.getItemQuantity();
                itemsString.append(item.getName())
                        .append(" (")
                        .append(item.getSupplier().getName())
                        .append(") Quantity: ")
                        .append(quantitySold)
                        .append(", ");

                // Update the quantity in the items file
                updateItemQuantity(itemsFile, item.getItemId(), -quantitySold);
            }

            if (itemsString.length() > 0) {
                itemsString.setLength(itemsString.length() - 2); // Remove the trailing ", "
            }

            // Format the bill date as yyyy-MM-dd
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(bill.getBillDate());

            String billEntry = String.format(
                    "%d,%s,%s,%.2f,%d,\"%s\",%d",
                    bill.getBillId(),
                    bill.getBillNumber(),
                    formattedDate, // Save the formatted date
                    bill.getTotalAmount(),
                    bill.getSectorId(),
                    itemsString,
                    4 // Replace with the cashier ID if available
            );
            billsWriter.write(billEntry);
            billsWriter.newLine();

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error updating bills file: " + e.getMessage());
        }
    }

    private void updateItemQuantity(File itemsFile, int itemId, int quantityChange) {
        File tempFile = new File("items_temp.csv");
        try (
                BufferedReader reader = new BufferedReader(new FileReader(itemsFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String header = reader.readLine();
            writer.write(header);
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                int currentItemId = Integer.parseInt(columns[0]);
                if (currentItemId == itemId) {
                    int currentQuantity = Integer.parseInt(columns[8]);
                    columns[8] = String.valueOf(currentQuantity + quantityChange);
                    if (currentQuantity + quantityChange < 0) {
                        showAlert(Alert.AlertType.ERROR, "Not enough stock for item: " + columns[1]);
                        return;
                    }
                }
                writer.write(String.join(",", columns));
                writer.newLine();
            }

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error updating items file: " + e.getMessage());
        }

        if (!itemsFile.delete() || !tempFile.renameTo(itemsFile)) {
            showAlert(Alert.AlertType.ERROR, "Error finalizing items file update.");
        }
    }

    private List<Item> loadItems() {
        File itemsFile = new File("items.csv");
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(itemsFile))) {
            String line = reader.readLine(); // Skip the header
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                int itemId = Integer.parseInt(columns[0]);
                String name = columns[1];
                Category category = new Category(itemId, columns[2]);
                Supplier supplier = new Supplier(itemId, columns[3], itemId);
                Date purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(columns[4]);
                double purchasePrice = Double.parseDouble(columns[5]);
                double sellingPrice = Double.parseDouble(columns[6]);
                int quantity = Integer.parseInt(columns[7]);

                items.add(new Item(itemId, name, category, supplier, purchaseDate, purchasePrice, sellingPrice, quantity));
            }

        } catch (IOException | ParseException e) {
            showAlert(Alert.AlertType.ERROR, "Error loading items: " + e.getMessage());
        }
        return items;
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }














    public static void main(String[] args) {
        launch(args);
    }
}

    public class ItemLoader {

        // Method to load items from the CSV file
        public List<Item> loadItems() {
            List<Item> items = new ArrayList<>();
            String filePath = "src/main/resources/files/items.csv"; // Replace with the actual file path

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                br.readLine(); // Skip the header line
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");

                    // Parse data and create an Item object
                    int itemId = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    Category category = new Category(); // Assuming you have a Category constructor
                    Supplier supplier = new Supplier(); // Assuming you have a Supplier constructor
                    Date purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(data[4].trim());
                    double purchasePrice = Double.parseDouble(data[5].trim());
                    double sellingPrice = Double.parseDouble(data[6].trim());
                    int quantity = Integer.parseInt(data[7].trim());

                    Item item = new Item(itemId, name, category, supplier, purchaseDate, purchasePrice, sellingPrice, quantity);
                    items.add(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return items;
        }
    }
}