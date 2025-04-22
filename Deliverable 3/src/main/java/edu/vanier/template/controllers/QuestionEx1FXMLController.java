package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionEx1FXMLController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionEx1FXMLController.class);
    @FXML
    private Button btnBack;
    @FXML private Button btnIncrease;
    @FXML private Button btnDecrease;
    @FXML private Button btnCheck;
    @FXML private Button btnPeriodicTable;
    @FXML private Label lblAnswer;
    @FXML private Label lblQuestion;
    private int number;

    @FXML
    public void initialize() {
        logger.info("Initializing Dialogue Controller...");

        // Set button actions
        btnBack.setOnAction(this::loadSettingsScene);
        btnCheck.setOnAction(this::handleCheck);
        btnPeriodicTable.setOnAction(this::loadPeriodicTableScene);
        number = 1;
        lblAnswer.setText("" + number);
        btnIncrease.setOnAction(this::handleIncrease);
        btnDecrease.setOnAction(this::handleDecrease);
    }

    private void handleIncrease(Event e) {
        number++;
        lblAnswer.setText("" + number);
    }
    private void handleDecrease(Event e) {
        if (number > 1)
            number--;
        lblAnswer.setText("" + number);
    }
    private void handleCheck(Event e) {
        MainMenu.switchScene(MainMenu.QUESTIONEX2_SCENE);
//        MainMenu.switchScene(MainMenu.GAME_SCENE);
        logger.info("Check button clicked");
    }

    private void loadSettingsScene(Event e) {
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings button clicked");
    }
    private void loadPeriodicTableScene(Event e) {
        MainMenu.switchScene(MainMenu.PERIODICTABLE_SCENE);
        logger.info("Periodic Table button clicked");
    }
}
