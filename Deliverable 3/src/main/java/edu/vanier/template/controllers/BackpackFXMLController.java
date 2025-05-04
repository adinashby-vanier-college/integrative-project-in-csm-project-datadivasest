package edu.vanier.template.controllers;

import edu.vanier.template.models.Sprite;
import edu.vanier.template.ui.MainApp;
import edu.vanier.template.ui.MainMenu;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static edu.vanier.template.ui.MainMenu.setBackground;

/**
 * @author Sofia Martinez
 */
public class BackpackFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(BackpackFXMLController.class);
    @FXML
    Button doneBtn;
    @FXML
    GridPane backpackGridPane;
    @FXML
    ScrollPane backpackScrollPane;
    @FXML
    Pane backpackPane;
    @FXML
    ImageView coin;
    @FXML
    ImageView electron;
    @FXML
    ImageView proton;

    @FXML
    public Label coinLabel;
    @FXML
    public Label electronLabel;
    @FXML
    public Label protonLabel;

    GameFXMLController gameFXMLController;

    private Label powerUpLabel;
    private Label chocolatePowerUpLabel;
    private int currentColumn;
    private int currentRow;
    private Map<String, Integer> itemsCount = new HashMap<>();
    private Map<String, Label> itemLabels = new HashMap<>();
    private int coinCounter;
    private int electronCounter;
    private int protonCounter;
    private int powerUpCounter;
    private int chocolatePowerUpCounter;
    private int numRows;

    //TODO: when done, do a drag and drop of the items into the game scene

    /**
     * actions performed with the backpack
     */
    public void initialize() {
        logger.info("initializing backpack");
        doneBtn.setOnAction(this::handleDoneBtn);
        setBackground(backpackGridPane);
        setBackground(backpackPane);

        /* both done and exit will lead to the same next Scene, they should both go to the scene they were
        last on. In case the user presses the backpack by accident, they can exit it without having to take anything
        out of the backpack.
        */
        //doneBtn.setOnAction(this::loadLastScene);
        //exitBtn.setOnAction(this::loadLastScene);
    }

    public void start(Stage stage) {
        logger.info("starting backpack");
    }
    public void isBackpackEmpty() {
        if (backpackGridPane.getChildren().isEmpty()) {
            setUpGridPane();
        }
    }

    public void setUI() {
        setBackground(backpackGridPane);
        setBackground(backpackPane);
    }
    @FXML
    //set up the grid pane first in the table, then add the object then implement the removal
    public void setUpGridPane() {
//        ObservableList<Node> cells = backpackGridPane.getChildren();
//        double width;
//        double height;
//        for (int i = 0; i < cells.size(); i++) {
//            Node cell= cells.get(i);
//            int rowNum = GridPane.getRowIndex(cell);
//            int colNum = GridPane.getColumnIndex(cell);
//
//            width = cell.getBoundsInLocal().getWidth();
//            height = cell.getBoundsInLocal().getHeight();
//        }

        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setPrefWidth(100); // Fixed width
        backpackGridPane.getColumnConstraints().add(colConstraint);
        int columnWidth = backpackGridPane.getColumnConstraints().size();
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPrefHeight(80);
        backpackGridPane.getRowConstraints().add(rowConstraint);
        int rowWidth = backpackGridPane.getRowConstraints().size();

        Image coinImage = new Image(getClass().getResource("/images/coin.png").toExternalForm());
        coin = new ImageView(coinImage);
        coin.setFitHeight(50);
        coin.setFitWidth(50);
        System.out.println("is backpack null: " + (backpackGridPane == null));
        coin.setId("coin");
        backpackGridPane.add(coin, 0, 0);
        GridPane.setHalignment(coin, HPos.CENTER);
        GridPane.setValignment(coin, VPos.CENTER);
        coinCounter = 0;
        coinLabel = new Label(" x " + 0 );
        backpackGridPane.add(coinLabel, 1, 0);
        itemsCount.put("coin", coinCounter);
        itemLabels.put("coin", coinLabel);
        //coinImageView.setFitHeight(GridPane.getRowIndex(coinImageView));
        logger.info("Loading coin image: T/F" + coinImage.isError());

        Image electronImage = new Image(getClass().getResource("/images/Electron.png").toExternalForm());
        electron = new ImageView(electronImage);
        electron.setFitWidth(50);
        electron.setFitHeight(50);
        electron.setId("electron");
        backpackGridPane.add(electron, 0, 1);
        GridPane.setHalignment(electron, HPos.CENTER);
        GridPane.setValignment(electron, VPos.CENTER);
        electronCounter = 0;
        electronLabel = new Label(" x " + 0);
        backpackGridPane.add(electronLabel, 1, 1);
        itemsCount.put("electron", electronCounter);
        itemLabels.put("electron", electronLabel);

        Image protonImage = new Image((getClass().getResource("/images/Proton.png")).toExternalForm());
        proton = new ImageView(protonImage);
        proton.setFitWidth(50);
        proton.setFitHeight(50);
        GridPane.setHalignment(proton, HPos.CENTER);
        GridPane.setValignment(proton, VPos.CENTER);
        protonCounter = 0;
        protonLabel = new Label(" x " + 0);
        backpackGridPane.add(protonLabel, 1, 2);
        proton.setId("proton");
        backpackGridPane.add(proton, 0, 2);
        itemsCount.put("proton", protonCounter);
        itemLabels.put("proton", protonLabel);

        Image powerUpImage = new Image(getClass().getResource("/images/PowerUp.png").toExternalForm());
        ImageView powerUp = new ImageView(powerUpImage);
        powerUp.setFitWidth(50);
        powerUp.setFitHeight(50);
        GridPane.setHalignment(powerUp, HPos.CENTER);
        GridPane.setValignment(powerUp, VPos.CENTER);
        powerUpCounter = 0;
        powerUpLabel = new Label(" x " + 0);
        backpackGridPane.add(powerUpLabel, 1, 3);
        powerUp.setId("powerUp");
        backpackGridPane.add(powerUp, 0, 3);
        itemsCount.put("powerUp", powerUpCounter);
        itemLabels.put("powerUp", powerUpLabel);

        Image chocolatePowerUpImage= new Image(getClass().getResource("/images/ChocolatePowerUp.png").toExternalForm());
        ImageView chocolatePowerUp = new ImageView(chocolatePowerUpImage);
        chocolatePowerUp.setFitWidth(50);
        chocolatePowerUp.setFitHeight(50);
        GridPane.setHalignment(chocolatePowerUp, HPos.CENTER);
        GridPane.setValignment(chocolatePowerUp, VPos.CENTER);
        chocolatePowerUpCounter = 0;
        chocolatePowerUpLabel = new Label(" x " + 0);
        backpackGridPane.add(chocolatePowerUpLabel, 1, 4);
        chocolatePowerUp.setId("chocolatePowerUp");
        backpackGridPane.add(chocolatePowerUp, 0, 4);
        itemsCount.put("chocolatePowerUp", chocolatePowerUpCounter);
        itemLabels.put("chocolatePowerUp", chocolatePowerUpLabel);
        //check how to adjust the font size
//        System.out.println("method is being executed/");

        electron.setOnDragDetected(event -> {
            if(itemsCount.getOrDefault("electron", 0) > 0) {
                Dragboard dragboard = electron.startDragAndDrop(TransferMode.COPY);

                ClipboardContent content = new ClipboardContent();
                content.putString("electron");
                dragboard.setContent(content);

                //showing a ghost image of the item in this case electron being dragged onto the screen
                dragboard.setDragView(electron.snapshot(null, null));
            }
            event.consume();
            System.out.println("drag being detected, mouse clicked");
        });

        electron.setOnDragDone(event -> {
            if(TransferMode.COPY == event.getTransferMode()) {
                Sprite electron = new Sprite("electron", electronImage);
                decreaseCount(electron);
                //commented out lines below are not necessary can be deleted, decrease count is much more efficient and works well
                /*electronCounter--;
                itemsCount.put("electron", electronCounter);

                Label itemLabel = itemLabels.get("electron");
                itemLabel.setText(" x " + itemsCount.get("electron"));
                 */
                System.out.println("drag done");

                //NEXT TASK: MAKE IT SO THAT THE BACKPACK IS SET UP WITHOUT HAVING THE CLICK THE BACKPACK BUTTON SO THAT WHEN THE USER COLLECTS ITEMS THEY DON'T NEED TO FIRST CLICK THE BACKPACK TO COLLECT ITEMS IN THEIR BACKPACK
                //THE USERE SHOULD ALREADY HAVE THEIR "BAKCPACK OPEN" WITHOUT HAVING TO OPEN THEIR BACKPACK FIRST
            }
        });
    }

    //increase the number of the item available when it is collected/collides with the user's character
        public void increaseCount(Sprite sprite) {
            String type = sprite.getType().toLowerCase();

            if (itemsCount.containsKey(type)) {
                int updatedCountOfItem = itemsCount.get(type) + 1;
                itemsCount.put(type, updatedCountOfItem);

                Label itemLabel = itemLabels.get(type);
                itemLabel.setText("x " + updatedCountOfItem);
            }
            System.out.println("method is being executed");
        }


        public void addNewItem(Sprite sprite) {
            String type = sprite.getType().toLowerCase();

            int currentNum = itemsCount.get(type);
            currentNum++;
            itemsCount.put(type, currentNum);

            Label itemLabel = itemLabels.get(type);
            itemLabel.setText("x " + currentNum);

        }


        //decreases the number of the type of item available whenever an element in the backpack gets dragg and dropped
        //in the specified drop zone successfully
        public void decreaseCount(Sprite sprite) {
        String type = sprite.getType().toLowerCase();
            int numAvailable = itemsCount.get(type);
            if (numAvailable > 0) {
                numAvailable--;
                itemsCount.put(type.toLowerCase(), numAvailable);

                //then update the cell
                if(itemLabels.containsKey(type)){
                    itemLabels.get(type).setText("x " + numAvailable);
                }
                logger.info("Number of available" + type + "is now " + numAvailable);
            }
        }


    //changing to a side panel view
    //instead of taking that image deleting it from the backpack, it'll be drag and dropped in the screen and the number of
    //those items will change

    //instead of taking that image deleting it from the backpack, it'll be drag and dropped in the screen and the number of
    //those items will change
    //we could either remove the image or make it invisible
    //to remove the can just remove then sprite since the spring and image are related

    public void handleDoneBtn (Event event) {

       // MainMenu.switchScene(MainMenu.GAME_OVER_SCENE);
        gameFXMLController.backpackStage.close();
    }

}

