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

    @FXML
    public void initialize() {
        logger.info("Initializing HelpFXMLController...");
        nextBtn.setOnAction(this::handleNext); //brings to next scene

        //ensures proper white space
        borderPane.setPrefHeight(BaseWindow.sceneHeight * 0.8);
        borderPane.setPrefWidth(BaseWindow.sceneWidth *0.8);

        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/Files/png/BG.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        borderPaneBg.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

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
                getResource("/images/commands/s.png").toString());
        rightImgView.setImage(rightImg);
        setSizeImg(rightImgView);

        Image nextDialogueImg = new Image(MainAppFXMLController.class.
                getResource("/images/commands/f.png").toString());
        nextDialogueImgView.setImage(nextDialogueImg);
        setSizeImg(nextDialogueImgView);

        //sets size proportionally to user's screen
        nextBtn.setMinSize(BaseWindow.sceneWidth * 0.10, BaseWindow.sceneHeight * 0.05);
    }

    /**
     * Ensures the size of the images are proportional to user's screen
     * @param imageView the image to resize
     */
    private void setSizeImg(javafx.scene.image.ImageView imageView) {
        imageView.setFitWidth(BaseWindow.sceneWidth * 0.25);
        imageView.setFitHeight(BaseWindow.sceneHeight * 0.25);
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
