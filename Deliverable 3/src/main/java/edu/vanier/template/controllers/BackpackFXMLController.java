package edu.vanier.template.controllers;

import edu.vanier.template.models.Sprite;
import edu.vanier.template.ui.MainApp;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author Sofia Martinez
 */
public class BackpackFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(BackpackFXMLController.class);
//    @FXML
//    Button doneBtn;
//    @FXML
//    Button exitBtn;
    @FXML
    GridPane itemsGridPane;
    @FXML
    public ScrollPane backpackScrollPane;

    //TODO: when done, do a drag and drop of the items into the game scene

    /**
     * actions performed with the backpack
     */
    public void initialize() {
        logger.info("initializing backpack");
        /* both done and exit will lead to the same next Scene, they should both go to the scene they were
        last on. In case the user presses the backpack by accident, they can exit it without having to take anything
        out of the backpack.
        */
        //doneBtn.setOnAction(this::loadLastScene);
        //exitBtn.setOnAction(this::loadLastScene);
    }

    public void isBackpackEmpty() {
        if (itemsGridPane.getChildren().isEmpty()) {
            setUpGridPane();
        }
    }

    //set up the grid pane first in the table, then add the object then implement the removal
    public void setUpGridPane() {
        Image coinImage = new Image(getClass().getResource("images/coin.png").toExternalForm());
        ImageView coinImageView = new ImageView(coinImage);
        itemsGridPane.add(coinImageView, 0, 0);
        logger.info("Loading coin image: T/F" + coinImage.isError());
        Image electronImage = new Image(getClass().getResource("images/Electron.png").toExternalForm());
        ImageView electronImageView = new ImageView(electronImage);
        itemsGridPane.add(electronImageView, 0, 1);
        Image protonImage = new Image((getClass().getResource("images/Proton.png")).toExternalForm());
        ImageView protonImageView = new ImageView(protonImage);
        itemsGridPane.add(protonImageView, 0, 2);
        Image powerUpImage = new Image(getClass().getResource("images/PowerUp.png").toExternalForm());
        ImageView powerUpImageView = new ImageView(powerUpImage);
        itemsGridPane.add(powerUpImageView, 0, 3);
        Image chocolatePowerUp = new Image(getClass().getResource("images/ChocolatePowerUp.png").toExternalForm());
        ImageView chocolatePowerUpImageView = new ImageView(chocolatePowerUp);
        itemsGridPane.add(chocolatePowerUpImageView, 0, 4);
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
}

