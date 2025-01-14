package com.electronicstore.view;

import com.electronicstore.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AdministratorView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Left part
        VBox leftSide = new VBox(20);
        leftSide.setStyle("-fx-padding: 20; -fx-background-color: rgb(255, 255, 255);");

        ImageView profileImage = new ImageView(new Image("file:C:/Users/Alesia/Desktop/Java Project/Java Project/User/AdministratorView/src/user.png"));
        profileImage.setFitHeight(100);
        profileImage.setFitWidth(100);

        Label profileName = new Label("Nik Lipi");//THIS SHOULD BE BASED ON THE NAME OF THE USER
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label role = new Label("Role: Administrator");
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label sector = new Label("Sector: Sector1");//THIS AS WELL
        sector.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");

        leftSide.getChildren().addAll(profileImage, profileName, role, sector);

        // Top part
        HBox topBar = new HBox(10);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: rgb(255, 255, 255);");

        Button logoutButton = new Button("Logout");


        ImageView logoutIcon = new ImageView(new Image("file:C:/Users/Alesia/Desktop/Java Project/Java Project/User/AdministratorView/src/user.png"));
        logoutIcon.setFitHeight(40);
        logoutIcon.setFitWidth(40);
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color:  white; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold ");

        // kjo perdoresh qe ta vije ne top right, e mesova nga chati
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView topLeftImage = new ImageView(new Image("file:C:/Users/Alesia/Desktop/Java Project/logo.jpg"));
        topLeftImage.setFitHeight(70);
        topLeftImage.setFitWidth(90);

        topBar.getChildren().addAll(topLeftImage, spacer, logoutButton);

        // per te ven ate vizen blu qe nkd pse alesia ma veshtirson jeten
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

        Label ListOfUsers=new Label("List of users");
        ListOfUsers.setStyle("-fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size:35 ");

        TableView<User> table=new TableView<User>();//CHECK OUT THE TABLE
        table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);
        table.setPrefSize(600,400);
        table.setMinSize(400, 300);
        table.setMaxSize(1200, 600);
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, LocalDate> dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));

        TableColumn<User, Integer> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, Integer> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<User,Void> actionsColumn=new TableColumn<>("Edit/Delete");
        Callback<TableColumn<User,Void>, TableCell<User,Void>>cellFactory=new Callback<>() {
            @Override
            public TableCell<User, Void> call(TableColumn<User, Void> userVoidTableColumn) {
                return new TableCell<>()
                {
                    private final Button editBtn=new Button();
                    private final Button deleteBtn=new Button();
                    private final HBox actionButtons=new HBox(10);
                    {
                        ImageView editIcon=new ImageView(new Image(getClass().getResource("/edit.png").toExternalForm()));
                        editIcon.setFitHeight(20);
                        editIcon.setFitWidth(20);
                        editBtn.setGraphic(editIcon);
                        editBtn.setStyle("-fx-background-color:transparent;");
                        editBtn.setAlignment(Pos.CENTER_LEFT);

                        ImageView deleteIcon=new ImageView(new Image(getClass().getResource("/delete.png").toExternalForm()));
                        deleteIcon.setFitWidth(20);
                        deleteIcon.setFitHeight(20);
                        deleteBtn.setGraphic(deleteIcon);
                        deleteBtn.setStyle("-fx-background-color:transparent;");
                        deleteBtn.setAlignment(Pos.CENTER_RIGHT);

                        editBtn.setOnAction(event->{
                            User user=getTableView().getItems().get(getIndex());
                            System.out.println("Editing: "+ user.getName());
                        });

                        deleteBtn.setOnAction(event->
                        {
                            User user=getTableView().getItems().get(getIndex());
                            System.out.println("Deleting: "+user.getName());
                        });

                        actionButtons.getChildren().addAll(editBtn,deleteBtn);
                    }
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(actionButtons);
                        }
                    }
                };
            }
        };
        actionsColumn.setCellFactory(cellFactory);
        actionsColumn.setPrefWidth(300);

        // Add columns to the TableView
        table.getColumns().addAll(nameColumn, dobColumn, phoneColumn, emailColumn, salaryColumn,actionsColumn);

        /*
        ObservableList<User> data = FXCollections.observableArrayList(
                new User(15,"John Doe","John", LocalDate.of(1985,04,12), 5551234, "john.doe@example.com", 50000),
                new User(16,"Jane Smith","Jane", LocalDate.of(1990,05,23), 5555678, "jane.smith@example.com", 48000)
        );

        table.setItems(data);*/
        center.add(ListOfUsers,0,0);
        center.add(table,0,1);

        GridPane.setHgrow(table, Priority.ALWAYS);
        GridPane.setVgrow(table, Priority.ALWAYS);

        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);
        pane.setCenter(center);

        // Scene
        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Administrator Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
