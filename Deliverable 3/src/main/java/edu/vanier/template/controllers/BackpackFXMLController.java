package edu.vanier.template.controllers;

import edu.vanier.template.models.Sprite;
import edu.vanier.template.ui.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    GridPane itemsGridPane;
    @FXML
    ScrollPane scrollPaneBackpack;

    private int numRows = 0;
    private int currentColumn = 0;

    /**
     * actions performed with the backpack
     */
    public void initialize() {
        /* both done and exit will lead to the same next Scene, they should both go to the scene they were
        last on. In case the user presses the backpack by accident, they can exit it without having to take anything
        out of the backpack.
        */
        doneBtn.setOnAction(this::loadLastScene);
        exitBtn.setOnAction(this::loadLastScene);
    }

    public void addObject(Sprite sprite) {
        Image objectImage = sprite.getImage();
        if(sprite.getType().equals("electron") || sprite.getType().equals("proton") || sprite.getType().equals("atom")
                || sprite.getType().equals("power up") || sprite.getType().equals("coin")) {
            ImageView objectImageView = new ImageView(objectImage);

            itemsGridPane.add(objectImageView, currentColumn, numRows);
            currentColumn++;
            logger.info("Adding object: " + sprite.getType() + " to backpack");

            if (currentColumn == 2) {
                currentColumn = 0;
                numRows++;
            }
        }
    }

    //TODO: find a way to know what the last scene was in order to go back to it -->Hashmap?
    //TODO: find a way show whatever was selected from the backpack and show it in the right area of the
    // current scenes pane, and delete it from the backpack's data and inventory (both graphics and data)
    private void loadLastScene(Event e) {
        //switch scenes
        logger.info("Loaded the last scene...");
    }
}
