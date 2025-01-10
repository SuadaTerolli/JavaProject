
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LogIn extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Left side: Image
        ImageView imageView = new ImageView(new Image("file:/C:/Users/User/Pictures/Saved Pictures/login.png"));
        /*imageView.setFitHeight(200);
        imageView.setFitWidth(200);*/
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

            // Dummy validation
            if (username.equals("admin") && password.equals("1234")) {
                messageLabel.setText("Login Successful!");
            } else {
                messageLabel.setText("Invalid Username or Password.");
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

    public static void main(String[] args) {
        launch(args);
    }
}

