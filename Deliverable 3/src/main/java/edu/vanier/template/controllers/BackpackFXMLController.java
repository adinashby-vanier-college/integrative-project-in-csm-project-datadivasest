package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    Pane itemsGridPane;
    @FXML
    Pane scrollPaneBackpack;

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
    //TODO: find a way to know what the last scene was in order to go back to it -->Hashmap?
    //TODO: find a way show whatever was selected from the backpack and show it in the right area of the
    // current scenes pane, and delete it from the backpack's data and inventory (both graphics and data)
    private void loadLastScene(Event e) {
        //switch scenes
        logger.info("Loaded the last scene...");
    }
}
