package com.electronicstore.view;

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

public class CashierView extends Application {
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
        

        leftSide.getChildren().addAll(profileImage, profileName, role);

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

        Label chooseCashierLabel = new Label("Choose cashier:");
        chooseCashierLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 16px;");
        ComboBox<String> cashierComboBox = new ComboBox<>();
        cashierComboBox.getItems().addAll("1", "2", "3");
        cashierComboBox.setPrefWidth(150);

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
        topBar.getChildren().addAll(topLeftImage, spacer,chooseSectorLabel,sectorComboBox,chooseCashierLabel, cashierComboBox,choosePeriodLabel, periodComboBox,goBack, logoutButton);

        // per te ven ate vizen blu qe nkd pse alesia ma veshtirson jeten
        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");

        //duhen ber top bar dhe viza blu include ne nje vbox
        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);  

        //kam dhe center part ktu po behet me tabel dhe kur ta marrim do e bej
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

        table.setItems(data);

        center.getChildren().addAll(title, table);
        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);
        pane.setCenter(center);

        // Scene
        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Dashboard/Manager/Cashier Performance");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static class Performance {
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
}
