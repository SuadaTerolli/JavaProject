package com.electronicstore.view;

import com.electronicstore.model.CashierPerformance;
import com.electronicstore.model.User;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CashierView extends Application {
    private User loggedInUser;

    public CashierView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public void start(Stage primaryStage) {
        // Left part
        // Left side - Profile and Role
        VBox leftSide = new VBox(30);
        leftSide.setStyle("-fx-padding: 20; -fx-background-color: rgb(255, 255, 255);");
        leftSide.setPrefWidth(200);

        ImageView profileImage = new ImageView(new Image("file:C:/Users/User/Pictures/Saved Pictures/profile.jpg"));
        ImageView profileImage = new ImageView(getClass().getResource("/user.png").toExternalForm());
        profileImage.setFitHeight(130);
        profileImage.setFitWidth(130);

        Label profileName = new Label("Nik Lipi");
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 20px;");
        Label role = new Label("Role: Manager");
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 18px;");
        
        Label profileName = new Label(loggedInUser.getName());
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);-fx-font-size: 20;");
        Label role = new Label("Role: " + loggedInUser.getAccess_level());
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);-fx-font-size: 18;");

        leftSide.getChildren().addAll(profileImage, profileName, role);

        // Top part
        // Top bar - Filters and Logout
        HBox topBar = new HBox(15);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: rgb(245, 245, 245);");
        topBar.setPrefHeight(80);
        topBar.setAlignment(Pos.CENTER);

        Label chooseSectorLabel = new Label("Choose sector:");
        chooseSectorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 16px;");
        // Load sectors and cashiers from files
        HashMap<String, Integer> sectorMapping = loadSectorsFromFile("src/main/resources/files/sectors.csv");
        Map<String, String> cashierMap = loadCashiersFromUserFile("src/main/resources/files/user.csv");

        ComboBox<String> sectorComboBox = new ComboBox<>();
        sectorComboBox.getItems().addAll("Electronics", "Clothing", "Grocery");
        sectorComboBox.setPrefWidth(150);
        sectorComboBox.getItems().addAll(sectorMapping.keySet());
        sectorComboBox.setValue("Choose Sector");
        sectorComboBox.setStyle("-fx-background-color: white; -fx-border-color: rgb(5, 39, 75); -fx-text-fill: rgb(92, 143, 198); -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 15; -fx-border-radius: 15;");
        sectorComboBox.setPrefWidth(200);

        Label choosePeriodLabel = new Label("Choose period:");
        choosePeriodLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 16px;");
        ComboBox<String> periodComboBox = new ComboBox<>();
        periodComboBox.getItems().addAll("This Week", "This Month", "This Year");
        periodComboBox.setPrefWidth(150);
        ComboBox<String> cashierBox = new ComboBox<>();
        cashierBox.getItems().addAll(cashierMap.keySet()); // Add cashier names
        cashierBox.setValue("Choose Cashier");
        cashierBox.setStyle("-fx-background-color: white; -fx-border-color: rgb(5, 39, 75); -fx-text-fill: rgb(92, 143, 198); -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 15; -fx-border-radius: 15;");
        cashierBox.setPrefWidth(180);

        Label chooseCashierLabel = new Label("Choose cashier:");
        chooseCashierLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 16px;");
        ComboBox<String> cashierComboBox = new ComboBox<>();
        cashierComboBox.getItems().addAll("1", "2", "3");
        cashierComboBox.setPrefWidth(150);

        Button goBack = new Button("< Go Back");
        goBack.setStyle("-fx-background-color: rgb(5, 39, 75); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 20px; -fx-padding: 5 15;");
        goBack.setOnAction(e -> {
            ManagerView managerView = new ManagerView(loggedInUser);
            try {
                managerView.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button logoutButton = new Button("Logout");
        ImageView logoutIcon = new ImageView(new Image("file:C:/Users/User/Pictures/Saved Pictures/logout.png"));
        ImageView logoutIcon = new ImageView(getClass().getResource("/logout.png").toExternalForm());
        logoutIcon.setFitHeight(20);
        logoutIcon.setFitWidth(20);
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color: transparent; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size: 14px;");
        logoutButton.setOnAction(event -> {
            try {
                new LogIn().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        ImageView topLeftImage = new ImageView(new Image("file:C:/Users/User/Pictures/Saved Pictures/login.png"));
        topLeftImage.setFitHeight(80);  
        ImageView topLeftImage = new ImageView(getClass().getResource("/logo.png").toExternalForm());
        topLeftImage.setFitHeight(80);
        topLeftImage.setFitWidth(80);
        

        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(topLeftImage, spacer,chooseSectorLabel,sectorComboBox,chooseCashierLabel, cashierComboBox,choosePeriodLabel, periodComboBox,goBack, logoutButton);
        topBar.getChildren().addAll(topLeftImage, spacer, sectorComboBox, cashierBox, logoutButton);

        // per te ven ate vizen blu qe nkd pse alesia ma veshtirson jeten
        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");
        blueLine.setStyle("-fx-background-color: rgb(5, 39, 75); -fx-min-height: 5px; -fx-max-height: 5px;");

        //duhen ber top bar dhe viza blu include ne nje vbox
        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);  
        topLayout.getChildren().addAll(topBar, blueLine);

        //kam dhe center part ktu po behet me tabel dhe kur ta marrim do e bej
        // Center part - Table
        VBox center = new VBox(20);
        center.setStyle("-fx-padding: 20;");

        Label title = new Label("Cashier Performance");
        title.setStyle("-fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size: 28px;");

        TableView<Performance> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefSize(600, 400);

        TableColumn<Performance, Integer> billsColumn = new TableColumn<>("Total Number of Bills");
        billsColumn.setCellValueFactory(new PropertyValueFactory<>("totalBills"));

        TableColumn<Performance, Integer> itemsSoldColumn = new TableColumn<>("Items Sold");
        itemsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("itemsSold"));

        TableColumn<Performance, Double> revenueColumn = new TableColumn<>("Total Revenue");
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("totalRevenue"));

        table.getColumns().addAll(billsColumn, itemsSoldColumn, revenueColumn);

        ObservableList<Performance> data = FXCollections.observableArrayList(
                new Performance(10, 50, 1500.75)
        );
        sectorComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            String selectedSectorId = null;
            if (newVal != null && !newVal.equals("Choose Sector")) {
                Integer sectorId = sectorMapping.get(newVal);
                selectedSectorId = (sectorId != null) ? sectorId.toString() : null;
            }

        table.setItems(data);
            String selectedCashierId = cashierBox.getValue() != null ? cashierMap.get(cashierBox.getValue()) : null;

        center.getChildren().addAll(title, table);
            List<CashierPerformance> filteredPerformance = calculateCashierPerformance(
                    "src/main/resources/files/bills.csv", selectedSectorId, selectedCashierId
            );

            updateTableData(filteredPerformance, table);
        });

        cashierBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            String selectedSectorId = null;
            if (sectorComboBox.getValue() != null && !sectorComboBox.getValue().equals("Choose Sector")) {
                Integer sectorId = sectorMapping.get(sectorComboBox.getValue());
                selectedSectorId = (sectorId != null) ? sectorId.toString() : null;
            }

            String selectedCashierId = cashierMap.get(newVal);

            List<CashierPerformance> filteredPerformance = calculateCashierPerformance(
                    "src/main/resources/files/bills.csv", selectedSectorId, selectedCashierId
            );

            updateTableData(filteredPerformance, table);
        });

        center.getChildren().addAll(goBack,title, table);

        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);
        pane.setCenter(center);

        // Scene
        Scene scene = new Scene(pane, 600, 400);
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Dashboard/Manager/Cashier Performance");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    private HashMap<String, Integer> loadSectorsFromFile(String filePath) {
        HashMap<String, Integer> sectorMapping = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                sectorMapping.put(fields[1], Integer.parseInt(fields[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sectorMapping;
    }
    public static class Performance {


















































































    private Map<String, String> loadCashiersFromUserFile(String filePath) {
        Map<String, String> cashierMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[8].trim().equalsIgnoreCase("cashier")) {
                    cashierMap.put(fields[1].trim(), fields[0].trim()); // Map name to ID
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cashierMap;
    }

    private void updateTableData(List<CashierPerformance> performanceData, TableView<Performance> table) {
        ObservableList<Performance> data = FXCollections.observableArrayList();
        for (CashierPerformance performance : performanceData) {
            data.add(new Performance(
                    performance.getTotalBills(),
                    performance.getItemsSold(),
                    performance.getTotalRevenue()
            ));
        }
        table.setItems(data);
    }

    private List<CashierPerformance> calculateCashierPerformance(String billsFilePath, String selectedSectorId, String selectedCashierId) {
        Map<String, CashierPerformance> performanceMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(billsFilePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String cashierId = fields[6].trim().replaceAll("\"", ""); // Clean cashier ID
                String sectorId = fields[4].trim();  // Sector ID
                double revenue = Double.parseDouble(fields[3].trim()); // Total amount
                String[] itemsArray = fields[5].trim().replaceAll("\"", "").split(","); // Clean and split items list

                // Apply filters for cashier and sector
                boolean matchesCashier = (selectedCashierId == null || selectedCashierId.equals("Choose Cashier") || selectedCashierId.equals(cashierId));
                boolean matchesSector = (selectedSectorId == null || selectedSectorId.equals("Choose Sector") || selectedSectorId.equals(sectorId));

                if (matchesCashier && matchesSector) {
                    // Calculate total items sold in this bill
                    int itemsSold = 0;
                    for (String itemEntry : itemsArray) {
                        String[] parts = itemEntry.split("Quantity: ");
                        if (parts.length == 2) {
                            itemsSold += Integer.parseInt(parts[1].trim());
                        }
                    }

                    // Ensure CashierPerformance entry exists for this cashier
                    performanceMap.putIfAbsent(cashierId, new CashierPerformance(cashierId));

                    // Update the cashier's performance
                    CashierPerformance performance = performanceMap.get(cashierId);
                    performance.incrementBills();
                    performance.addItemsSold(itemsSold);
                    performance.addRevenue(revenue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return all matching entries for selected cashier
        List<CashierPerformance> result = new ArrayList<>();
        if (selectedCashierId != null && !selectedCashierId.equals("Choose Cashier")) {
            for (CashierPerformance performance : performanceMap.values()) {
                if (performance.getCashierId().equals(selectedCashierId)) {
                    result.add(performance);
                }
            }
        } else {
            result.addAll(performanceMap.values());
        }

        return result;
    }    public static class Performance {


        private final SimpleIntegerProperty totalBills;
        private final SimpleIntegerProperty itemsSold;
        private final SimpleDoubleProperty totalRevenue;

        public Performance(int totalBills, int itemsSold, double totalRevenue) {
            this.totalBills = new SimpleIntegerProperty(totalBills);
            this.itemsSold = new SimpleIntegerProperty(itemsSold);
            this.totalRevenue = new SimpleDoubleProperty(totalRevenue);
        }

        public int getTotalBills() {
            return totalBills.get();
        }

        public int getItemsSold() {
            return itemsSold.get();
        }

        public double getTotalRevenue() {
            return totalRevenue.get();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
