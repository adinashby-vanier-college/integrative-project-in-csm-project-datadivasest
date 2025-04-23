package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QuestionEx2FXMLController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionEx2FXMLController.class);
    @FXML
    private Button btnBack;
    @FXML private Button btnCheck;
    @FXML private Button btnHelp;
    @FXML private Label lblQuestion;
    @FXML private TextField txtLeft;
    @FXML private Label lblLeft;
    @FXML private TextField txtRight;
    @FXML private Label lblRight;
    @FXML private BorderPane borderPane;

    @FXML
    public void initialize() {
        logger.info("Initializing Question 2 Controller...");
        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/Files/png/BG.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

        // Set button actions
        btnBack.setOnAction(this::handleBack);
        btnCheck.setOnAction(this::handleCheck);
        btnHelp.setOnAction(this::handleHelp);

    }
    private void handleCheck(Event e) {
        MainMenu.switchScene(MainMenu.GAME_SCENE);
        logger.info("Check button clicked");
    }

    private void handleBack(Event e) {
        MainMenu.switchScene(MainMenu.QUESTIONEX1_SCENE);
        logger.info("Back button clicked");
    }
    private void handleHelp(Event e) {
        logger.info("Help button clicked");
    }
}
