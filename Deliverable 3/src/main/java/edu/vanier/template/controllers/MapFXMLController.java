package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(MapFXMLController.class);
    private ImageView imgViewGrp1;
    private ImageView imgViewGrp2;
    private ImageView imgViewGrp3;
    private ImageView imgViewGrp4;
    private ImageView imgViewGrp5;
    private ImageView imgViewGrp6;
    private ImageView imgViewGrp7;
    private ImageView imgViewGrp8;
    private Button btnBack;

    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");
        btnBack.setOnAction(this::loadLastScene);
    }

    //TODO: find a way to know what the last scene was in order to go back to it
    private void loadLastScene(Event e) {
        MainApp.switchScene(MainApp.MAINAPP_SCENE);
        logger.info("Loaded the primary scene...");
    }
}
