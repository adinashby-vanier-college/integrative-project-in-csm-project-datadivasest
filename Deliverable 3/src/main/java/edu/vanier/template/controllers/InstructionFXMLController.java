package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * @author Tabasuum
 * basic instructions on how to play game with game controls
 */
public class InstructionFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(InstructionFXMLController.class);
    @FXML private Button nextBtn;
    @FXML private BorderPane borderPaneBg;
    @FXML private BorderPane borderPane;
    @FXML private javafx.scene.image.ImageView leftImgView;
    @FXML private javafx.scene.image.ImageView rightImgView;
    @FXML private javafx.scene.image.ImageView jumpImgView;
    @FXML private ImageView nextDialogueImgView;


    /**
     * Introduces the user to the game with the instruction controllers
     *
     * @author Tabasuum
     */
    @FXML
    public void initialize() {
        logger.info("Initializing HelpFXMLController...");
        nextBtn.setOnAction(this::handleNext); //brings to next scene

        //ensures proper white space
        borderPane.setPrefHeight(BaseWindow.sceneHeight * 0.8);
        borderPane.setPrefWidth(BaseWindow.sceneWidth *0.8);
        borderPane.setOnKeyPressed(this::handleKeyPressed);

        setBackground(borderPaneBg);

        //images and setting of each control
        Image jumpImg = new Image(MainAppFXMLController.class.
                getResource("/images/commands/w.png").toString());
        jumpImgView.setImage(jumpImg);
        setSizeImg(jumpImgView);

        Image leftImg = new Image(MainAppFXMLController.class.
                getResource("/images/commands/a.png").toString());
        leftImgView.setImage(leftImg);
        setSizeImg(leftImgView);

        Image rightImg = new Image(MainAppFXMLController.class.
                getResource("/images/commands/d.png").toString());
        rightImgView.setImage(rightImg);
        setSizeImg(rightImgView);

        Image nextDialogueImg = new Image(MainAppFXMLController.class.
                getResource("/images/commands/f.png").toString());
        nextDialogueImgView.setImage(nextDialogueImg);
        setSizeImg(nextDialogueImgView);

        //sets size proportionally to user's screen
        setSizeBtn1(nextBtn);
        setButton(nextBtn, "next", 5, 5);
        //nextBtn.setMinSize(BaseWindow.sceneWidth * 0.10, BaseWindow.sceneHeight * 0.05);
    }

    public void setUI() {
        setBackground(borderPaneBg);
    }

    /**
     * if the f key is pressed then the brings to next scene
     *
     * @param event key press, must correspond to F
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.F) {
            System.out.println("Going to dialogue...");
            MainMenu.switchScene(DIALOGUE_SCENE);
            logger.info("F button has been clicked...");
            event.consume(); // Prevent further handling
        }
    }
    /**
     * Brings users to the proper next scene
     * @param e
     */
    private void handleNext(Event e) {
        System.out.println("Going to dialogue...");
        MainMenu.switchScene(MainMenu.DIALOGUE_SCENE);
        logger.info("Next button has been clicked...");
    }
}
