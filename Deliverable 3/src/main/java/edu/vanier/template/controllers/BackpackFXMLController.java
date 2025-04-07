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

/**
 * @author Sofia Martinez
 */
public class BackpackFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(BackpackFXMLController.class);
    @FXML
    Button doneBtn;
    @FXML
    Button exitBtn;
    @FXML
    GridPane backpackGridPane;
    @FXML
    ScrollPane backpackScrollPane;
    @FXML
    Pane backpackPane;
    @FXML
    ImageView coinImageView;
    @FXML
    ImageView electronImageView;
    @FXML
    ImageView protonImageView;

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

    //TODO: when done, do a drag and drop of the items into the game scene

    /**
     * actions performed with the backpack
     */
    public void initialize() {
        logger.info("initializing backpack");
        exitBtn.setOnAction(this::handleExitBtn);
        doneBtn.setOnAction(this::handleDoneBtn);
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
        coinImageView = new ImageView(coinImage);
        coinImageView.setFitHeight(50);
        coinImageView.setFitWidth(50);
        System.out.println("is backpack null: " + (backpackGridPane == null));
        backpackGridPane.add(coinImageView, 0, 0);
        GridPane.setHalignment(coinImageView, HPos.CENTER);
        GridPane.setValignment(coinImageView, VPos.CENTER);
        coinCounter = 0;
        coinLabel = new Label(" x " + 0 );
        backpackGridPane.add(coinLabel, 1, 0);
        itemsCount.put("coin", coinCounter);
        itemLabels.put("coin", coinLabel);
        //coinImageView.setFitHeight(GridPane.getRowIndex(coinImageView));
        logger.info("Loading coin image: T/F" + coinImage.isError());

        Image electronImage = new Image(getClass().getResource("/images/Electron.png").toExternalForm());
        electronImageView = new ImageView(electronImage);
        electronImageView.setFitWidth(50);
        electronImageView.setFitHeight(50);
        backpackGridPane.add(electronImageView, 0, 1);
        GridPane.setHalignment(electronImageView, HPos.CENTER);
        GridPane.setValignment(electronImageView, VPos.CENTER);
        electronCounter = 0;
        electronLabel = new Label(" x " + 0);
        backpackGridPane.add(electronLabel, 1, 1);
        itemsCount.put("electron", 2);
        itemLabels.put("electron", electronLabel);

        Image protonImage = new Image((getClass().getResource("/images/Proton.png")).toExternalForm());
        protonImageView = new ImageView(protonImage);
        protonImageView.setFitWidth(50);
        protonImageView.setFitHeight(50);
        GridPane.setHalignment(protonImageView, HPos.CENTER);
        GridPane.setValignment(protonImageView, VPos.CENTER);
        protonCounter = 0;
        protonLabel = new Label(" x " + 0);
        backpackGridPane.add(protonLabel, 1, 2);
        backpackGridPane.add(protonImageView, 0, 2);
        itemsCount.put("proton", protonCounter);
        itemLabels.put("proton", protonLabel);

        Image powerUpImage = new Image(getClass().getResource("/images/PowerUp.png").toExternalForm());
        ImageView powerUpImageView = new ImageView(powerUpImage);
        powerUpImageView.setFitWidth(50);
        powerUpImageView.setFitHeight(50);
        GridPane.setHalignment(powerUpImageView, HPos.CENTER);
        GridPane.setValignment(powerUpImageView, VPos.CENTER);
        powerUpCounter = 0;
        powerUpLabel = new Label(" x " + 0);
        backpackGridPane.add(powerUpLabel, 1, 3);
        backpackGridPane.add(powerUpImageView, 0, 3);
        itemsCount.put("powerUp", powerUpCounter);
        itemLabels.put("powerUp", powerUpLabel);

        Image chocolatePowerUp = new Image(getClass().getResource("/images/ChocolatePowerUp.png").toExternalForm());
        ImageView chocolatePowerUpImageView = new ImageView(chocolatePowerUp);
        chocolatePowerUpImageView.setFitWidth(50);
        chocolatePowerUpImageView.setFitHeight(50);
        GridPane.setHalignment(chocolatePowerUpImageView, HPos.CENTER);
        GridPane.setValignment(chocolatePowerUpImageView, VPos.CENTER);
        chocolatePowerUpCounter = 0;
        chocolatePowerUpLabel = new Label(" x " + 0);
        backpackGridPane.add(chocolatePowerUpLabel, 1, 4);
        backpackGridPane.add(chocolatePowerUpImageView, 0, 4);
        itemsCount.put("chocolatePowerUp", chocolatePowerUpCounter);
        itemLabels.put("chocolatePowerUp", chocolatePowerUpLabel);
        //check how to adjust the font size
    }

    public void setupCoinDrag(Sprite sprite) {
        // Start the drag when the user initiates a drag gesture.
        String type = sprite.getType();
        ImageView itemImage = new ImageView(sprite.getImage());
        itemImage.setOnDragDetected(event -> {
            // Only allow dragging if there is at least one coin available.
            if (itemsCount.getOrDefault(type, 0) <= 0) {
                event.consume();
                return;
            }
            // Begin the drag-and-drop operation using MOVE mode.
            Dragboard dragboard = itemImage.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            // Put the coin type as a string into the dragboard.
            content.putString(type);
            dragboard.setContent(content);
            // Set a drag view (a "ghost" image) so the original remains fixed.
            dragboard.setDragView(coinImageView.snapshot(null, null));
            logger.info("Started dragging " + type);
            event.consume();
        });

        // When the drag is complete, update the coin count.
        coinImageView.setOnDragDone(event -> {
            // Check if the drop was successful (i.e. TransferMode.MOVE).
            if (event.getTransferMode() == TransferMode.MOVE) {
                decreaseCount(sprite);
                logger.info("Drag completed for " + type + "; count decremented.");
            }
            event.consume();
        });
    }

    public void addItem(Sprite sprite) {
        ImageView objectImageView = new ImageView(sprite.getImage());
        objectImageView.setFitWidth(50);
        objectImageView.setFitHeight(50);
        objectImageView.setPreserveRatio(true);

        objectImageView.setOnDragDetected(event -> {
            // Only allow dragging if there is at least one coin in the count
            //check the counter when you inserted
            if (itemsCount.get(sprite.getType().toLowerCase()) <= 0) {
                event.consume();
                return;
            }

            if(itemsCount.get(sprite.getType().toLowerCase()) > 0){
            Dragboard dragboard = objectImageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(sprite.getType());
            dragboard.setContent(clipboardContent);
            dragboard.setDragView(objectImageView.snapshot(null, null));
            logger.info("Started dragging " + sprite.getType());
            event.consume();
        }});

        objectImageView.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                decreaseCount(sprite);
                logger.info("Dragging done for" + sprite.getType());
            }
            event.consume();
        });
        //updateCell(sprite.getType(), sprite.getImage(), itemsCount.getOrDefault(sprite.getType().toLowerCase(), 0));
    }

    public void updateGrid() {

    }
    //increase the number of the item available when it is collected/collides with the user's character
        public void increaseCount(Sprite sprite) {
        String type = sprite.getType().toLowerCase();

        if(itemsCount.containsKey(type)) {
            int updatedCountOfItem = itemsCount.get(type) + 1;
            itemsCount.put(type, updatedCountOfItem);

            Label itemLabel = itemLabels.get(type);
            itemLabel.setText("x " + updatedCountOfItem);
        }
        /*
            switch (sprite.getType()) {
                case "coin":
                    coinCounter++;
                    coinLabel.setText("x " + coinCounter);
                    itemsCount.put(sprite.getType().toLowerCase(), coinCounter);
                    break;
                case "electron":
                    electronCounter++;
                    electronLabel.setText("x " + electronCounter);
                    itemsCount.put(sprite.getType().toLowerCase(), electronCounter);
                    break;
                case "proton":
                    protonCounter++;
                    protonLabel.setText("x " + protonCounter);
                    itemsCount.put(sprite.getType().toLowerCase(), protonCounter);
                    break;
                case "powerUp":
                    powerUpCounter++;
                    powerUpLabel.setText("x " + powerUpCounter);
                    itemsCount.put(sprite.getType().toLowerCase(), powerUpCounter);
                    break;
                case "chocolatePowerUp":
                    chocolatePowerUpCounter++;
                    chocolatePowerUpLabel.setText("x " + chocolatePowerUpCounter);
                    itemsCount.put(sprite.getType().toLowerCase(), chocolatePowerUpCounter);
                    break;
                default:
                    break;
            }
            //then update the cell
           // updateCell(type, image, numAvailable);

         */
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
                    itemLabels.get(type).setText("x" + numAvailable);
                }
                logger.info("Number of available" + type + "is now " + numAvailable);
            }
        }




    //changing to a side panel view
    //instead of taking that image deleting it from the backpack, it'll be drag and dropped in the screen and the number of
    //those items will change
   /*  public void addObject(Sprite sprite) {
        double gridPaneHeight = itemsGridPane.getHeight();
        double rowHeight = gridPaneHeight / numRows;
        Image objectImage = sprite.getImage();
        ImageView objectImageView = new ImageView(objectImage);
        itemsGridPane.add(objectImageView, 0, 0);
        //will this actually store the sprite if it's the image
        if(sprite.getType().equals("electron") || sprite.getType().equals("proton") || sprite.getType().equals("atom")
                || sprite.getType().equals("power up") || sprite.getType().equals("coin")) {
            //ImageView objectImageView = new ImageView(objectImage);

            itemsGridPane.add(objectImageView, currentColumn, numRows);
            currentColumn++;
            logger.info("Adding object: " + sprite.getType() + " to backpack");

            if (currentColumn == 2) {
                currentColumn = 0;
                if(numRows < 3) {
                    RowConstraints newRow = new RowConstraints(rowHeight);
                    itemsGridPane.getRowConstraints().addAll(newRow);
                    numRows++;
                }
                else {
                  numRows++;
                }
            }
        }
    }



    */

    //instead of taking that image deleting it from the backpack, it'll be drag and dropped in the screen and the number of
    //those items will change
    //we could either remove the image or make it invisible
    //to remove the you can just remove then sprite since the spring and image are related
    public void removeObject(Sprite sprite) {

    }
/*
    //TODO: find a way to know what the last scene was in order to go back to it -->Hashmap?
    //TODO: find a way show whatever was selected from the backpack and show it in the right area of the
    // current scenes pane, and delete it from the backpack's data and inventory (both graphics and data)
    private void loadLastScene(Event e) {
        //switch to the last scene
        //below is a placeholder until I work the actual method
        MainMenu.switchScene(MainMenu.GAME_SCENE);
        logger.info("Loaded the last scene...");
    }
*/
        /*
public void addObject(Sprite sprite) {
    // Get the image from the sprite
    Image objectImage = sprite.getImage();
    ImageView objectImageView = new ImageView(objectImage);

    // Constrain the image size so it's not too big
    objectImageView.setFitWidth(100);  // Adjust to desired width
    objectImageView.setFitHeight(100); // Adjust to desired height
    objectImageView.setPreserveRatio(true);

    // Only add the image once (remove redundant addition)
    // Instead of adding at (0,0) first, directly add at the correct position.
    if (sprite.getType().equalsIgnoreCase("electron") ||
            sprite.getType().equalsIgnoreCase("proton") ||
            sprite.getType().equalsIgnoreCase("atom") ||
            sprite.getType().equalsIgnoreCase("power up") ||
            sprite.getType().equalsIgnoreCase("coin")) {

        // Add the image to the grid at the current position
        backpackGridPane.add(objectImageView, currentColumn, currentRow);
        currentRow++;
        logger.info("Adding object: " + sprite.getType() + " to backpack");

        // If currentColumn reaches 2, move to next row
        if (currentColumn == 1) {
            currentColumn = 0;
            // Optionally add a new row constraint if needed
            RowConstraints newRow = new RowConstraints();
            // Set row height as a fraction of grid height (or fixed)
            newRow.setPrefHeight(50);
            backpackGridPane.getRowConstraints().add(newRow);
            currentRow++;
        }
    }

*/

    public void handleDoneBtn (Event event) {
        gameFXMLController.backpackStage.close();
    }

    public void handleExitBtn (Event event) {
        gameFXMLController.backpackStage.close();
    }
}

