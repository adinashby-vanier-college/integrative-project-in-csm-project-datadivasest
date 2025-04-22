package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeriodicTableFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(PeriodicTableFXMLController.class);
    @FXML
    Button btnBack;
    @FXML
    GridPane gridPane;
    @FXML
    public void initialize() {
        btnBack.setOnAction(this::handleBack);
    }
    private void handleBack(Event e) {
        MainMenu.switchScene(MainMenu.QUESTIONEX1_SCENE);
        logger.info("Back button clicked");
    }
}
