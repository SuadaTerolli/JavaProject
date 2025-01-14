package com.electronicstore.view;

import com.electronicstore.model.FinancialSummary;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Date;

public class AdministratorStatistics extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Left part
        VBox leftSide = new VBox(20);
        leftSide.setStyle("-fx-padding: 40; -fx-background-color: rgb(255, 255, 255);");

        ImageView profileImage = new ImageView(new Image(getClass().getResource("/user.png").toExternalForm()));
        profileImage.setFitHeight(120);
        profileImage.setFitWidth(120);

        Label profileName = new Label("Nik Lipi");//THIS SHOULD BE BASED ON THE NAME OF THE USER
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);-fx-font-size:15;");
        Label role = new Label("Role: Administrator");
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);-fx-font-size:15;");
        Label sector = new Label("Sector: Sector1");//THIS AS WELL
        sector.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);-fx-font-size:15;");

        leftSide.getChildren().addAll(profileImage, profileName, role, sector);

        // Top part
        HBox topBar = new HBox(10);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: rgb(255, 255, 255);");

        Button StatisticsBtn=new Button("Statistics");
        StatisticsBtn.setStyle("-fx-background-color: rgb(5, 39, 75);\n"+
                "-fx-background-radius:5em;\n" +
                "-fx-text-fill:white;\n" +
                "-fx-font-weight:bold;\n" +
                "-fx-font-size:15");
        StatisticsBtn.setPrefWidth(150);

        Button logoutButton = new Button("Logout");


        ImageView logoutIcon = new ImageView(new Image(getClass().getResource("/user.png").toExternalForm()));
        logoutIcon.setFitHeight(40);
        logoutIcon.setFitWidth(40);
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color:  white; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold ");

        // kjo perdoresh qe ta vije ne top right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView topLeftImage = new ImageView(new Image(getClass().getResource("/logo.jpg").toExternalForm()));
        topLeftImage.setFitHeight(70);
        topLeftImage.setFitWidth(90);

        topBar.getChildren().addAll(topLeftImage, spacer, StatisticsBtn,logoutButton);
        topBar.setAlignment(Pos.CENTER);

        // per te ven ate vizen blu
        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");

        //duhen ber top bar dhe viza blu include ne nje vbox
        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);

        //kam dhe center part ktu po behet me tabel dhe kur ta marrim do e bej
        GridPane center=new GridPane();
        center.setVgap(30);
        center.setHgap(50);
        center.setStyle("-fx-padding: 20;");

        Button backBtn=new Button("< Go Back");
        backBtn.setStyle("-fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size:15;-fx-background-color: white;\n" +
                "    -fx-border-color: #d3d3d3;\n" +
                "    -fx-border-radius: 5px;\n" +
                "    -fx-padding: 5px;\n" +
                "    -fx-font-size: 14px;");

        Label StatisticsTxt=new Label("Statistics");
        StatisticsTxt.setStyle("-fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size:35 ");

        ComboBox<String> periodDropdown = new ComboBox<>();
        periodDropdown.getItems().addAll("Daily", "Weekly", "Monthly", "Yearly");
        periodDropdown.setPromptText("Period");

        periodDropdown.setStyle( "-fx-background-color: white; -fx-border-color: #d3d3d3; -fx-border-radius: 5px; -fx-padding: 5px; -fx-font-size: 15px;-fx-border-color: rgb(5, 39, 75);\n" +
                "    -fx-font-weight: bold;\n"+
                "    -fx-border-width: 2px;\n" +
                "    -fx-background-color: white;");

        center.setConstraints(periodDropdown, 0, 2);

        TableView<FinancialSummary> summariesTable=new TableView<FinancialSummary>();//CHECK OUT THE TABLE
        summariesTable.setColumnResizePolicy(summariesTable.CONSTRAINED_RESIZE_POLICY);
        summariesTable.setPrefSize(600,50);
        summariesTable.setMinSize(400, 50);
        summariesTable.setMaxSize(1200, 50);
        summariesTable.setPrefHeight(150);
        TableColumn<FinancialSummary, String> summaryTypeColumn = new TableColumn<>("Type");
        summaryTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<FinancialSummary, String> summaryValueColumn = new TableColumn<>("Value");
        summaryValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        summariesTable.getColumns().addAll(summaryTypeColumn, summaryValueColumn);

        TableView<FinancialSummary> detailsTable = new TableView<>();
        detailsTable.setColumnResizePolicy(detailsTable.CONSTRAINED_RESIZE_POLICY);
        detailsTable.setPrefSize(600,50);
        detailsTable.setMinSize(400, 50);
        detailsTable.setMaxSize(1200, 50);
        detailsTable.setPrefHeight(150);
        TableColumn<FinancialSummary, String> detailsTypeColumn = new TableColumn<>("Category");
        detailsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<FinancialSummary, String> detailsValueColumn = new TableColumn<>("Amount");
        detailsValueColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        detailsTable.getColumns().addAll(detailsTypeColumn, detailsValueColumn);

        //center.getChildren().addAll(table);

        /*
        ObservableList<FinancialSummary> summaryData = FXCollections.observableArrayList(
                new FinancialSummary("Total Income", "10000"),
                new FinancialSummary("Total Costs", "8000")
        );
        ObservableList<FinancialSummary> detailsData = FXCollections.observableArrayList(
                new FinancialSummary("Salaries", "5000"),
                new FinancialSummary("Items Purchased Costs", "3000")
        );
        summariesTable.setItems(summaryData);
        detailsTable.setItems(detailsData);*/

        center.add(backBtn,0,0);
        center.add(StatisticsTxt,0,1);
        center.add(periodDropdown,0,2);
        center.add(summariesTable,0,3);
        center.add(detailsTable,0,4);

        GridPane.setHgrow(summariesTable, Priority.ALWAYS);
        GridPane.setVgrow(summariesTable, Priority.ALWAYS);

        GridPane.setHgrow(detailsTable,Priority.ALWAYS);
        GridPane.setVgrow(detailsTable,Priority.ALWAYS);


        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);
        pane.setCenter(center);

        // Scene
        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Administrator Statistics Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}