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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class ManagerCreateItemView extends Application {
    @Override
    public void start(Stage primaryStage){

        HBox topBar = new HBox(30);
        topBar.setStyle("-fx-padding: 30; -fx-background-color: rgb(255, 255, 255);");

        ImageView topLeftImage = new ImageView(new Image("file:/C:/Users/Hello/Downloads/login.png/"));
        topLeftImage.setFitHeight(50);  
        topLeftImage.setFitWidth(50);

        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);

        ComboBox<String> sectionBox = new ComboBox<>();
        sectionBox.getItems().addAll("Sector 1", "Sector 2", "Sector 3");
        sectionBox.setValue("Choose Sector");
        sectionBox.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: rgb(92, 143, 198); -fx-font-weight: bold; -fx-font-size: 16px;");

        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        Button supplierButton = new Button("Suppliers");
        supplierButton.setStyle("-fx-background-color: rgb(5, 39, 75); -fx-text-fill: white; -fx-font-size: 16px;");

        Button logoutButton = new Button("Logout");
        
        ImageView logoutIcon = new ImageView(new Image("file:/C:/Users/Hello/Downloads/logout.png/"));
        logoutIcon.setFitHeight(40);  
        logoutIcon.setFitWidth(40);   
        logoutButton.setGraphic(logoutIcon);
        logoutButton.setStyle("-fx-background-color:  white; -fx-text-fill: rgb(5, 39, 75); -fx-font-weight: bold; -fx-font-size: 16px;");

        topBar.getChildren().addAll(topLeftImage, leftSpacer, sectionBox, rightSpacer, supplierButton, logoutButton);

        Region blueLine = new Region();
        blueLine.setStyle("-fx-background-color:rgb(5, 39, 75) ; -fx-min-height: 5px; -fx-max-height: 5px;");

        VBox topLayout = new VBox();
        topLayout.getChildren().addAll(topBar, blueLine);  

        VBox leftPart=new VBox(40);
        leftPart.setStyle("-fx-padding: 40; -fx-background-color: rgb(255, 255, 255);");

        ImageView profileImage = new ImageView(new Image("file:/C:/Users/Hello/Desktop/download.jfif"));
        profileImage.setFitHeight(100);
        profileImage.setFitWidth(100);

        Label profileName = new Label("Nik Lipi");
        profileName.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 16px;");

        Label role = new Label("Role: Manager");
        role.setStyle("-fx-font-weight: bold; -fx-text-fill: rgb(5, 39, 75); -fx-font-size: 16px;");

        leftPart.getChildren().addAll(profileImage, profileName, role);

        GridPane center=new GridPane();
        center.setVgap(30);
        center.setHgap(50);
        center.setStyle("-fx-padding: 20;");

        Button goBack=new Button ("< Go Back");
        goBack.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 14px;");
        center.add(goBack, 0, 0);

        Label addNewItem=new Label("Add New Item");
        addNewItem.setStyle("-fx-text-fill: rgb(0, 0, 0); -fx-font-weight: bold; -fx-font-size:20 ");
        center.add(addNewItem, 0, 1);

        TextField itemName = new TextField();
        itemName.setPromptText("Item Name");
        itemName.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-size: 14px;");
        center.add(itemName, 0, 2); 

        ComboBox<String> itemCategoryBox = new ComboBox<>();
        itemCategoryBox.getItems().addAll("Electronics", "Appliances", "Clothing", "Furniture");
        itemCategoryBox.setValue("Choose Category");
        itemCategoryBox.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: rgb(92, 143, 198); -fx-font-size: 14px;");
        center.add(itemCategoryBox, 1, 2);

        TextField purchaseDate = new TextField();
        purchaseDate.setPromptText("Purchase Date");
        purchaseDate.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-size: 14px;");
        center.add(purchaseDate, 0, 3);

        TextField purchasePrice = new TextField();
        purchasePrice.setPromptText("Purchase Price");
        purchasePrice.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-size: 14px;");
        center.add(purchasePrice, 1, 3);

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-text-fill: black; -fx-font-size: 14px;");
        center.add(quantityField, 0, 4); 

        Button createItemButton = new Button("Create Item");
        createItemButton.setStyle("-fx-background-color: rgb(19, 68, 121); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 20;");
        center.add(createItemButton, 0, 5, 2, 1);
        GridPane.setHalignment(createItemButton, javafx.geometry.HPos.CENTER);

        BorderPane pane=new BorderPane();
        pane.setLeft(leftPart);
        pane.setCenter(center);
        pane.setTop(topLayout);  

        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Manager View");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}
