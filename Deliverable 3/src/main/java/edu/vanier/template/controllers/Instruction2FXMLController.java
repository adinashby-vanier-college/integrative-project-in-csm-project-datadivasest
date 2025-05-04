package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * @author Tabasuum
 * basic instructions on how to play game with game controls
 */
public class Instruction2FXMLController {
    private final static Logger logger = LoggerFactory.getLogger(Instruction2FXMLController.class);
    @FXML private Button nextBtn;
    @FXML private BorderPane borderPaneBg;
    @FXML private BorderPane borderPane;


    @FXML
    public void initialize() {
        logger.info("Initializing HelpFXMLController...");
        nextBtn.setOnAction(this::handleNext); //brings to next scene

        //ensures proper white space
        borderPane.setPrefHeight(BaseWindow.sceneHeight * 0.8);
        borderPane.setPrefWidth(BaseWindow.sceneWidth *0.8);

        setBackground(borderPaneBg);

        //sets size proportionally to user's screen
        setSizeBtn1(nextBtn);
        setButton(nextBtn, "next", 5, 5);
        //nextBtn.setMinSize(BaseWindow.sceneWidth * 0.10, BaseWindow.sceneHeight * 0.05);
    }

    public void setUI() {
        setBackground(borderPaneBg);
    }
    /**
     * Brings users to the proper next scene
     * @param e
     */
    private void handleNext(Event e) {
        System.out.println("Going to dialogue...");
        MainMenu.switchScene(GAME_SCENE);
        logger.info("Next button has been clicked...");
    }
}
