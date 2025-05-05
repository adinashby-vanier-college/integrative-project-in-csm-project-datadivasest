package edu.vanier.template.controllers;

import edu.vanier.template.models.Family;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * basic instructions on how to play second part of the game
 * @author Tabasuum
 */
public class Instruction2FXMLController {
    private final static Logger logger = LoggerFactory.getLogger(Instruction2FXMLController.class);
    @FXML
    private Button nextBtn;
    @FXML
    private BorderPane borderPaneBg;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView imageView;


    @FXML
    public void initialize() {
        logger.info("Initializing Instructions2FXMLController...");
        nextBtn.setOnAction(this::handleNext); //brings to next scene

        //ensures proper white space
        borderPane.setPrefHeight(BaseWindow.sceneHeight * 0.8);
        borderPane.setPrefWidth(BaseWindow.sceneWidth *0.8);
        borderPane.setOnKeyPressed(this::handleKeyPressed);

        imageView.setPreserveRatio(false);
        imageView.setFitHeight(BaseWindow.sceneHeight * 0.7);
        imageView.setFitWidth(BaseWindow.sceneWidth * 0.7);
        imageView.setImage(getImage("map_pergament.png"));
        setBackground(borderPaneBg);

        //sets size proportionally to user's screen
        setSizeBtn1(nextBtn);
        setButton(nextBtn, "next", 5, 5);

        //nextBtn.setMinSize(BaseWindow.sceneWidth * 0.10, BaseWindow.sceneHeight * 0.05);
    }

    /**
     * sets the UI of the game to look good
     */
    public void setUI() {
        setBackground(borderPaneBg);
    }

    /**
     * Brings users to the proper next scene
     * @param e
     */
    private void handleNext(Event e) {
        System.out.println("Going to map scene...");

        sceneController.removeScene(GAME_SCENE);
        AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
        if (animationTimer != null)
            animationTimer.stop();
        MainMenu.switchScene(GAME_SCENE, Family.ALKALIMETAL); // switch to it
        logger.info("Noble Gas has been clicked...");
        logger.info("Next button has been clicked...");
    }

    /**
     * if the f key is pressed then the brings to next
     *
     * @param event key press, must correspond to F
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.F) {
            System.out.println("Going to Game Scene...");
            MainMenu.switchScene(GAME_SCENE, Family.ALKALINEEARTHMETALS);
            logger.info("F button has been clicked...");
            event.consume(); // Prevent further handling
        }
    }
}
