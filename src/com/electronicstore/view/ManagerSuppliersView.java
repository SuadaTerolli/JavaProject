import java.util.function.Supplier;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerSuppliersView extends Application {
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

        Label suppliersLabel=new Label("Suppliers");
        suppliersLabel.setStyle("-fx-text-fill: rgb(0, 0, 0); -fx-font-weight: bold; -fx-font-size:20 ");
        center.add(suppliersLabel, 0, 1);

        TableView<Supplier> table = new TableView<Supplier>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefSize(600, 400);


        TableColumn<Supplier, String> supplierColumn = new TableColumn<>("Supplier");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        TableColumn<Supplier, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Supplier, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Supplier, Double> purchasePriceColumn = new TableColumn<>("Purchase Price");
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));

        // Add columns to the table
        table.getColumns().addAll(supplierColumn, itemNameColumn, categoryColumn, purchasePriceColumn);

        // Add sample data
        ObservableList<Supplier> data = FXCollections.observableArrayList(
                new Supplier("Supplier 1", "Item A", "Category X", 100.0),
                new Supplier("Supplier 2", "Item B", "Category Y", 200.0),
                new Supplier("Supplier 3", "Item C", "Category Z", 300.0)
        );
        table.setItems(data);
        center.add(table, 0, 2);

        BorderPane pane=new BorderPane();
        pane.setLeft(leftPart);
        pane.setCenter(center);
        pane.setTop(topLayout);  

        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Manager View");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static class Supplier {
        private final SimpleStringProperty supplier;
        private final SimpleStringProperty itemName;
        private final SimpleStringProperty category;
        private final SimpleDoubleProperty purchasePrice;
    
        public Supplier(String supplier, String itemName, String category, double purchasePrice) {
            this.supplier = new SimpleStringProperty(supplier);
            this.itemName = new SimpleStringProperty(itemName);
            this.category = new SimpleStringProperty(category);
            this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
        }
    
        public String getSupplier() {
            return supplier.get();
        }
    
        public String getItemName() {
            return itemName.get();
        }
    
        public String getCategory() {
            return category.get();
        }
    
        public double getPurchasePrice() {
            return purchasePrice.get();
        }
    }
    public static void main(String[] args){
        launch(args);
    }
}
