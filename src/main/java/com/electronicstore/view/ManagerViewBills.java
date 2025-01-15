package com.electronicstore.view;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
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

public class ManagerViewBills extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Left part
        VBox leftSide = new VBox(30);
        leftSide.setStyle("-fx-padding: 20; -fx-background-color: rgb(255, 255, 255);");
        leftSide.setPrefWidth(200);

        ImageView profileImage = new ImageView(new Image("file:C:/Users/User/Pictures/Saved Pictures/profile.jpg"));
        profileImage.setFitHeight(130);
        profileImage.setFitWidth(130);

        Label profileName = new Label("Nik Lipi");
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 20px;");
        Label role = new Label("Role: Manager");
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 18px;");
        Label notification = new Label("Item 1 will soon be out of stock!");
        notification.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(240, 14, 14); -fx-font-size: 10.5px;");

        leftSide.getChildren().addAll(profileImage, profileName, role, notification);

        // Top part
        HBox topBar = new HBox(15);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: rgb(245, 245, 245);");
        topBar.setPrefHeight(80);

        Label chooseSectorLabel = new Label("Choose sector:");
        chooseSectorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 16px;");
        ComboBox<String> sectorComboBox = new ComboBox<>();
        sectorComboBox.getItems().addAll("Electronics", "Clothing", "Grocery");
        sectorComboBox.setPrefWidth(150);

        Label choosePeriodLabel = new Label("Choose period:");
        choosePeriodLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 16px;");
        ComboBox<String> periodComboBox = new ComboBox<>();
        periodComboBox.getItems().addAll("This Week", "This Month", "This Year");
        periodComboBox.setPrefWidth(150);

        Button goBack = new Button("< Go Back");
        goBack.setStyle("-fx-background-color: rgb(5, 39, 75); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 20px; -fx-padding: 5 15;");

        Button logoutButton = new Button("Logout");
        ImageView logoutIcon = new ImageView(new Image("file:C:/Users/User/Pictures/Saved Pictures/logout.png"));
        logoutIcon.setFitHeight(20);
        logoutIcon.setFitWidth(20);
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color: transparent; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size: 14px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        ImageView topLeftImage = new ImageView(new Image("file:C:/Users/User/Pictures/Saved Pictures/login.png"));
        topLeftImage.setFitHeight(80);  
        topLeftImage.setFitWidth(80);
        

        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(topLeftImage, spacer,chooseSectorLabel,sectorComboBox,choosePeriodLabel, periodComboBox,goBack, logoutButton);

        // per te ven ate vizen blu qe nkd pse alesia ma veshtirson jeten
        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");

        //duhen ber top bar dhe viza blu include ne nje vbox
        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);  

        //kam dhe center part ktu po behet me tabel dhe kur ta marrim do e bej
        GridPane center = new GridPane();
        center.setVgap(30);
        center.setHgap(50);
        center.setStyle("-fx-padding: 20;");

        Label listOfBills = new Label("Bills");
        listOfBills.setStyle("-fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size: 35;");

        TableView<Bill> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefSize(600, 400);
        table.setMinSize(400, 300);
        table.setMaxSize(1200, 600);

        TableColumn<Bill, String> billNumberColumn = new TableColumn<>("Bill Number");
        billNumberColumn.setCellValueFactory(new PropertyValueFactory<>("billNumber"));

        TableColumn<Bill, String> itemsColumn = new TableColumn<>("Items");
        itemsColumn.setCellValueFactory(new PropertyValueFactory<>("items"));

        TableColumn<Bill, Double> soldPriceColumn = new TableColumn<>("Sold Price");
        soldPriceColumn.setCellValueFactory(new PropertyValueFactory<>("soldPrice"));

        table.getColumns().addAll(billNumberColumn, itemsColumn, soldPriceColumn);

        ObservableList<Bill> data = FXCollections.observableArrayList(
                new Bill("B001", "Item A, Item B", 200.50),
                new Bill("B002", "Item C, Item D", 340.00),
                new Bill("B003", "Item E", 150.25)
        );

        table.setItems(data);

        center.add(listOfBills, 0, 0);
        center.add(table, 0, 1);

        GridPane.setHgrow(table, Priority.ALWAYS);
        GridPane.setVgrow(table, Priority.ALWAYS);

        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);
        pane.setCenter(center);

        // Scene
        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Dashboard/Manager/Bills");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static class Bill {
        private final SimpleStringProperty billNumber;
        private final SimpleStringProperty items;
        private final SimpleDoubleProperty soldPrice;

        public Bill(String billNumber, String items, double soldPrice) {
            this.billNumber = new SimpleStringProperty(billNumber);
            this.items = new SimpleStringProperty(items);
            this.soldPrice = new SimpleDoubleProperty(soldPrice);
        }

        public String getBillNumber() {
            return billNumber.get();
        }

        public String getItems() {
            return items.get();
        }

        public double getSoldPrice() {
            return soldPrice.get();
        }
    }
}
