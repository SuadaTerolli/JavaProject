import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;

public class CashierCreateBillView extends Application {
    @Override
    public void start(Stage primaryStage) {

        VBox leftSide = new VBox(20);
        leftSide.setStyle("-fx-padding: 20; -fx-background-color: rgb(255, 255, 255);");

        ImageView profileImage = new ImageView(new Image("file:/C:/Users/user/OneDrive/Desktop/profile.png"));
        profileImage.setFitHeight(100);
        profileImage.setFitWidth(100);

        Label profileName = new Label("Nik Lipi");
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label role = new Label("Role: Cashier");
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");
        Label sector = new Label("Sector: Sector1");
        sector.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75);");

        leftSide.getChildren().addAll(profileImage, profileName, role, sector);

        HBox topBar = new HBox(10);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: rgb(255, 255, 255);");

        Button logoutButton = new Button("Logout");
        
        ImageView logoutIcon = new ImageView(new Image("file:/C:/Users/Hello/Downloads/logout.png/"));
        logoutIcon.setFitHeight(40);  
        logoutIcon.setFitWidth(40);   
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color:  white; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size: 16px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView topLeftImage = new ImageView(new Image("file:/C:/Users/Hello/Downloads/login.png/"));
        topLeftImage.setFitHeight(50);  
        topLeftImage.setFitWidth(50);

        topBar.getChildren().addAll(topLeftImage, spacer, logoutButton);

        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");

        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);  

        GridPane center=new GridPane();
        center.setVgap(30);
        center.setHgap(50);
        center.setStyle("-fx-padding: 20;");

        Button goBack=new Button ("< Go Back");
        goBack.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 14px;");
        center.add(goBack, 0, 0);

        Label createNewBill=new Label("Create New Bill");
        createNewBill.setStyle("-fx-text-fill: rgb(0, 0, 0); -fx-font-weight: bold; -fx-font-size:20 ");
        center.add(createNewBill, 0, 1);

        ComboBox<String> itemBox = new ComboBox<>();
        itemBox.getItems().addAll("Computer", "Mouse", "Keyboard", "Speaker", "Printer", "Headphones");
        itemBox.setValue("Select An Item");
        itemBox.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: rgb(92, 143, 198); -fx-font-size: 14px;");
        center.add(itemBox, 0, 2);

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-size: 14px;");
        center.add(quantityField, 1, 2); 
        
        Button addItem = new Button("+");
        addItem.setStyle("-fx-background-color: rgb(228, 234, 239); -fx-text-fill: blue; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 50%;");
        center.add(addItem, 0, 3, 2, 1);
        GridPane.setHalignment(addItem, javafx.geometry.HPos.CENTER);

        Button createBillButton = new Button("Create Bill");
        createBillButton.setStyle("-fx-background-color: rgb(19, 68, 121); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 20;");
        center.add(createBillButton, 0, 4, 2, 1);
        GridPane.setHalignment(createBillButton, javafx.geometry.HPos.CENTER);
        
        BorderPane pane = new BorderPane();
        pane.setLeft(leftSide);
        pane.setTop(topLayout);  
        pane.setCenter(center);

        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setTitle("Cashier/CreateBill");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
