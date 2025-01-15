package com.electronicstore.view;

import com.electronicstore.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends Application {

    private List<User> users = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {

        loadUsersFromFile("/files/user.csv");
        // Left side: Image
        ImageView imageView = new ImageView(new Image("file:/C:/Users/User/Pictures/Saved Pictures/login.png"));
        //imageView.setFitHeight(200);
        //imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        VBox imagePane = new VBox(imageView);
        imagePane.setAlignment(Pos.CENTER);
        imagePane.setStyle("-fx-background-color: white;");
        imagePane.setPrefWidth(300);

        // Right side: Login form
        Label titleLabel = new Label("Portal Login");
        titleLabel.setStyle("-fx-font-size: 70px; -fx-font-weight: bold; -fx-text-fill: #1E4D92;");

        //Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        usernameField.setPrefWidth(250);
        usernameField.setPrefHeight(40); // Increased height
        usernameField.setStyle("-fx-font-size: 14px;");

        //Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setPrefWidth(250);
        passwordField.setPrefHeight(40); // Increased height
        passwordField.setStyle("-fx-font-size: 14px;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #1E4D92; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size:20px");
        loginButton.setPrefWidth(200); // Increased width
        loginButton.setPrefHeight(45); // Increased height

        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = authenticate(username, password);

            if (user != null) {
                messageLabel.setText("Login Successful! Redirecting...");
                messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
                navigateToRoleView(user.getAccess_level(), primaryStage);
            } else {
                messageLabel.setText("Invalid Username or Password.");
                messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            }
        });

        VBox formPane = new VBox(65, titleLabel,usernameField, passwordField, loginButton, messageLabel);
        formPane.setPadding(new Insets(20));
        formPane.setAlignment(Pos.CENTER);
        formPane.setStyle("-fx-background-color: #B3E5FC;");
        formPane.setPrefWidth(300);

        // SplitPane to divide the page
        SplitPane splitPane = new SplitPane(imagePane, formPane);
        splitPane.setDividerPositions(0.5);

        // Create scene and set stage
        Scene scene = new Scene(splitPane);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void loadUsersFromFile(String resourcePath) {
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9) {
                    String username = parts[2].trim();
                    String password = parts[7].trim();
                    String role = parts[8].trim();
                    users.add(new User(username, password, role));
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("Error loading users from resource: " + resourcePath);
        }
    }
    private User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void navigateToRoleView(String role, Stage stage) {
        Scene scene;
        switch (role.toLowerCase()) {
            case "manager":
                scene = new Scene(new Label("Welcome Manager!"), 400, 200);
                break;
            case "administrator":
                scene = new Scene(new Label("Welcome Administrator!"), 400, 200);
                break;
            case "cashier":
                scene = new Scene(new Label("Welcome Cashier!"), 400, 200);
                break;
            default:
                scene = new Scene(new Label("Unknown Role!"), 400, 200);
                break;
        }
        stage.setScene(scene);
        stage.setTitle(role + " View");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

