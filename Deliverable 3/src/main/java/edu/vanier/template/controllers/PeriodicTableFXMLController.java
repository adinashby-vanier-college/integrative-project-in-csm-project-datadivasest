package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.vanier.template.ui.MainMenu.*;

public class PeriodicTableFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(PeriodicTableFXMLController.class);
    @FXML
    Button btnBack;
    @FXML
    GridPane gridPane;
    @FXML
    public void initialize() {
        setBackground(gridPane);
        btnBack.setOnAction(this::handleBack);
        setButton(btnBack, "back", 5, 5);
        setSizeBtn1(btnBack);
    }
    private void handleBack(Event e) {
        MainMenu.goBack();
        logger.info("Back button clicked");
    }
}
