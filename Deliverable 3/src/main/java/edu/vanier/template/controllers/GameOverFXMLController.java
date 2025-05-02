package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.vanier.template.ui.MainMenu.setBackground;

/**
 * @author Sofia Martinez
 */
public class GameOverFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(MainAppFXMLController.class);

    @FXML
    Button tryAgnBtn;
    @FXML
    Button MainMenuBtn;
    @FXML
    BorderPane borderPane;

    public void initialize() {
        logger.info("Initializing MainAppController...");
        tryAgnBtn.setOnAction(this::handleTryAgnBtn);
        MainMenuBtn.setOnAction(this::handleMainMenuBtn);
        setBackground(borderPane);
    }

    public void handleTryAgnBtn(Event e) {
        //TODO: switch scees to the first Game Scene\
        MainMenu.switchScene(MainMenu.MAINMENU_SCENE);
        logger.info("try again button has been clicked");
    }

    public void handleMainMenuBtn(Event e) {
        MainMenu.switchScene(MainMenu.MAINMENU_SCENE);
        logger.info("main menu button has been clicked");
    }
}
