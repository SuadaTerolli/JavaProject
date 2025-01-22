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

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AdministratorView extends Application {
    private User loggedInUser;

    public AdministratorView(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    @Override
    public void start(Stage primaryStage) {
        // File path for the CSV file
        String csvFilePath = "src/main/resources/files/user.csv";

        // Load data from the CSV file
        ObservableList<User> userData = loadUsersFromFile(csvFilePath);

        // Left part
        VBox leftSide = new VBox(20);
        leftSide.setStyle("-fx-padding: 20; -fx-background-color: rgb(255, 255, 255);");

        ImageView profileImage = new ImageView(new Image(getClass().getResource("/user.png").toExternalForm()));
        profileImage.setFitHeight(100);
        profileImage.setFitWidth(100);

        Label profileName = new Label(loggedInUser.getName());
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);-fx-font-size: 20;");
        Label role = new Label("Role: " + loggedInUser.getAccess_level());
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);-fx-font-size: 18;");

        leftSide.getChildren().addAll(profileImage, profileName, role);

        // Top part
        HBox topBar = new HBox(10);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: rgb(255, 255, 255);");

        Button logoutButton = new Button("Logout");
        ImageView logoutIcon = new ImageView(new Image(getClass().getResource("/logout.png").toExternalForm()));
        logoutIcon.setFitHeight(40);
        logoutIcon.setFitWidth(40);
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color:  white; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold ");
        // ======= Logout Functionality =======
        logoutButton.setOnAction(event -> {
            try {
                // Restart the LogIn application
                new LogIn().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button statisticsButton = new Button("Statistics");
        statisticsButton.setStyle("-fx-background-color: rgb(5, 39, 75); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 20px; -fx-padding: 5 15;");
        statisticsButton.setOnAction(event -> {
            AdministratorStatistics statisticsView = new AdministratorStatistics(loggedInUser);
            try {
                statisticsView.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView topLeftImage = new ImageView(new Image(getClass().getResource("/logo.png").toExternalForm()));
        topLeftImage.setFitHeight(80);
        topLeftImage.setFitWidth(80);

        topBar.getChildren().addAll(topLeftImage, spacer, statisticsButton,logoutButton);
        topBar.setAlignment(Pos.CENTER);

        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");

        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);

        GridPane center=new GridPane();
        center.setVgap(30);
        center.setHgap(50);
        center.setStyle("-fx-padding: 20;");

        Label listOfUsers = new Label("List of Users");
        listOfUsers.setStyle("-fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size:35 ");

        // Create TableView
        TableView<User> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefSize(600, 400);
        table.setItems(userData); // Set the data into the TableView

        // Create Columns
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, Date> dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));

        TableColumn<User, Long> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("access_level"));
        // Create "Edit/Delete" column
        TableColumn<User, Void> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setCellFactory(column -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox actionButtons = new HBox(10);

            {
                editButton.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                // Set up Edit button action
                editButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    showEditDialog(user); // Opens a dialog to edit the user
                    saveUsersToFile(table.getItems()); // Save changes to the file
                    getTableView().refresh(); // Refresh the TableView to reflect changes
                });

                // Set up Delete button action
                deleteButton.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(user); // Remove user from the table
                    saveUsersToFile(table.getItems()); // Save updated list to the file
                });

                actionButtons.getChildren().addAll(editButton, deleteButton);
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
        });

        // Add Columns to TableView
        table.getColumns().addAll(nameColumn, dobColumn, phoneColumn, emailColumn, salaryColumn,roleColumn, actionsColumn);
        //Create Add User Button
        Button addUserButton = new Button("Add User");
        addUserButton.setStyle("-fx-background-color: rgb(5, 39, 75); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 20px; -fx-padding: 5 15;");
        addUserButton.setOnAction(event -> {
            Dialog<User> dialog = new Dialog<>();
            dialog.setTitle("Add New User");

            // Dialog Pane Content
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField idField = new TextField();
            TextField nameField = new TextField();
            TextField usernameField = new TextField();
            DatePicker dobPicker = new DatePicker();
            TextField phoneField = new TextField();
            TextField emailField = new TextField();
            TextField salaryField = new TextField();
            TextField passwordField = new TextField();

            ComboBox<String> roleComboBox = new ComboBox<>();
            roleComboBox.setItems(FXCollections.observableArrayList("Cashier", "Manager"));
            roleComboBox.setPromptText("Select Role");

            grid.add(new Label("ID:"), 0, 0);
            grid.add(idField, 1, 0);
            grid.add(new Label("Name:"), 0, 1);
            grid.add(nameField, 1, 1);
            grid.add(new Label("Username:"), 0, 2);
            grid.add(usernameField, 1, 2);
            grid.add(new Label("Date of Birth:"), 0, 3);
            grid.add(dobPicker, 1, 3);
            grid.add(new Label("Phone:"), 0, 4);
            grid.add(phoneField, 1, 4);
            grid.add(new Label("Email:"), 0, 5);
            grid.add(emailField, 1, 5);
            grid.add(new Label("Salary:"), 0, 6);
            grid.add(salaryField, 1, 6);
            grid.add(new Label("Password:"), 0, 7);
            grid.add(passwordField, 1, 7);
            grid.add(new Label("Role:"), 0, 8);
            grid.add(roleComboBox, 1, 8);

            dialog.getDialogPane().setContent(grid);
            // Add OK and Cancel buttons
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    try {
                        int id = Integer.parseInt(idField.getText());
                        String name = nameField.getText();
                        String username = usernameField.getText();
                        LocalDate dob = dobPicker.getValue();
                        long phone = Long.parseLong(phoneField.getText());
                        String email = emailField.getText();
                        double salary = Double.parseDouble(salaryField.getText());
                        String password = passwordField.getText();
                        String roleAdd = roleComboBox.getValue();

                        if (name.isEmpty() || username.isEmpty() || dob == null || email.isEmpty() || password.isEmpty()|| roleAdd == null) {
                            throw new IllegalArgumentException("All fields must be filled out!");
                        }

                        User newUser = new User(id, name, username, java.sql.Date.valueOf(dob), phone, email, salary, password, roleAdd);
                        userData.add(newUser); // Add to the observable list
                        saveUsersToFile(userData); // Save the updated list to the file
                        return newUser;
                    } catch (Exception e) {
                        showErrorDialog("Invalid Input", "Please ensure all fields are filled correctly.");
                    }
                }
                return null;
            });

            dialog.showAndWait();
        });



        // Add components to the GridPane
        center.add(listOfUsers, 0, 0);
        center.add(addUserButton,1,0);
        center.add(table, 0, 1);

        GridPane.setHgrow(table, Priority.ALWAYS);
        GridPane.setVgrow(table, Priority.ALWAYS);

        // ======= Final Layout =======
        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);
        pane.setCenter(center);

        // Scene
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Administrator Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);

        // Customize the dialog style if desired
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        alert.showAndWait();
    }

    private ObservableList<User> loadUsersFromFile(String filePath) {
        ObservableList<User> users = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header row
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String username = parts[2].trim();
                    Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(parts[3].trim());
                    long phoneNumber = Long.parseLong(parts[4].trim());
                    String email = parts[5].trim();
                    double salary = Double.parseDouble(parts[6].trim());
                    String password = parts[7].trim();
                    String accessLevel = parts[8].trim();

                    User user = new User(id, name, username, dateOfBirth, phoneNumber, email, salary, password, accessLevel);
                    users.add(user);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return users;
    }
    private void saveUsersToFile(ObservableList<User> users) {
        String filePath = "src/main/resources/files/user.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write("id,name,username,datebirth,phone,email,salary,password,accesslevel");
            writer.newLine();

            // Write user data
            for (User user : users) {
                writer.write(user.getId() + "," +
                        user.getName() + "," +
                        user.getUsername() + "," +
                        new SimpleDateFormat("yyyy-MM-dd").format(user.getDate_of_birth()) + "," +
                        user.getPhoneNumber() + "," +
                        user.getEmail() + "," +
                        user.getSalary() + "," +
                        user.getPassword() + "," +
                        user.getAccess_level());
                writer.newLine();
            }
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showEditDialog(User user) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Edit User");

        // Dialog Pane Content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField(user.getName());
        TextField emailField = new TextField(user.getEmail());
        TextField phoneField = new TextField(String.valueOf(user.getPhoneNumber()));
        TextField salaryField = new TextField(String.valueOf(user.getSalary()));
        TextField roleField = new TextField(user.getAccess_level());

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Phone:"), 0, 2);
        grid.add(phoneField, 1, 2);
        grid.add(new Label("Salary:"), 0, 3);
        grid.add(salaryField, 1, 3);
        grid.add(new Label("Role:"), 0, 4);
        grid.add(roleField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // OK and Cancel Buttons
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setPhoneNumber(Integer.parseInt(phoneField.getText()));
                user.setSalary(Double.parseDouble(salaryField.getText()));
                user.setAccess_level(roleField.getText());
            }
            return null;
        });

        dialog.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
