package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.vanier.template.ui.MainMenu.*;

public class PeriodicTableFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(PeriodicTableFXMLController.class);
    @FXML
    Button btnBack;
    @FXML
    BorderPane borderPane;
    @FXML
    HBox hbox;
    @FXML
    ImageView periodicTable;
    @FXML
    public void initialize() {
        btnBack.setOnAction(this::handleBack);
        setButton(btnBack, "back", 5, 5);
        setSizeBtn1(btnBack);
        periodicTable.setImage(new Image(MainAppFXMLController.class.
                getResource("/images/PNG/PeriodicTable.png").toString()));
        periodicTable.setFitHeight(BaseWindow.sceneHeight * 0.9);
        periodicTable.setFitWidth(BaseWindow.sceneWidth * 0.9);

        setBackground(borderPane);
    }
    private void handleBack(Event e) {
        MainMenu.goBack();
        logger.info("Back button clicked");
    }
}
