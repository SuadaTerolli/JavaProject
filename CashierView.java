import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CashierView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Left part
        VBox leftSide = new VBox(20);
        leftSide.setStyle("-fx-padding: 20; -fx-background-color: rgb(255, 255, 255);");

        ImageView profileImage = new ImageView(new Image("file:/C:/Users/user/OneDrive/Desktop/profile.png"));
        profileImage.setFitHeight(100);
        profileImage.setFitWidth(100);

        Label profileName = new Label("Nik Lipi");
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label role = new Label("Role: Manager");
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label sector = new Label("Sector: Sector1");
        sector.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");

        Button button = new Button("Create Bill");
        button.setStyle("-fx-background-color: rgb(5, 39, 75); -fx-text-fill: white");

        leftSide.getChildren().addAll(profileImage, profileName, role, sector, button);

        // Top part
        HBox topBar = new HBox(10);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: rgb(255, 255, 255);");

        Button logoutButton = new Button("Logout");

        
        ImageView logoutIcon = new ImageView(new Image("file:/C:/Users/user/OneDrive/Desktop/logout.png"));
        logoutIcon.setFitHeight(50);  
        logoutIcon.setFitWidth(40);   
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color:  white; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold ");

        // kjo perdoresh qe ta vije ne top right, e mesova nga chati
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView topLeftImage = new ImageView(new Image("file:/C:/Users/user/OneDrive/Desktop/digital.jpg"));
        topLeftImage.setFitHeight(70);  
        topLeftImage.setFitWidth(70);

        topBar.getChildren().addAll(topLeftImage, spacer, logoutButton);

        // per te ven ate vizen blu qe nkd pse alesia ma veshtirson jeten
        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");

        //duhen ber top bar dhe viza blu include ne nje vbox
        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);  

        //kam dhe center part ktu po behet me tabel dhe kur ta marrim do e bej

        
        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);  

        // Scene
        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Cashier Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

