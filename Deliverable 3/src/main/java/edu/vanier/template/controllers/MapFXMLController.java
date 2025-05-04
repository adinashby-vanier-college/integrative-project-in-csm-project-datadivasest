package edu.vanier.template.controllers;

import edu.vanier.template.models.Family;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainApp;
import edu.vanier.template.ui.MainMenu;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * @author Tabasuum, Eliza
 * TODO: finish documentation
 */
public class MapFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(MapFXMLController.class);
    @FXML private ImageView alkaliMetalImgView;
    @FXML private ImageView alkalineEarthMetalImgView;
    @FXML private ImageView transitionMetal3ImgView;
    @FXML private ImageView transitionMetal4ImgView;
    @FXML private ImageView transitionMetal5ImgView;
    @FXML private ImageView transitionMetal6ImgView;
    @FXML private ImageView halogensImgView;
    @FXML private ImageView nobleGasImgView;
    @FXML private VBox vBox;
    @FXML private BorderPane borderPaneBg;
    @FXML private BorderPane borderPane;

    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");

        //initializes size
        borderPane.setPrefHeight(BaseWindow.sceneHeight * 0.8);
        borderPane.setPrefWidth(BaseWindow.sceneWidth * 0.8);
        vBox.setPrefHeight(BaseWindow.sceneHeight * 0.75);
        vBox.setPrefWidth(BaseWindow.sceneWidth * 0.60);

        //initializes images
        setBackground(borderPaneBg);
        Image bGImg = new Image(MainAppFXMLController.class.
                getResource("/images/map_pergament.png").toString());
        BackgroundSize bGSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);
        vBox.setBackground(new Background(new BackgroundImage(bGImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bGSize)));

        //set images
        alkaliMetalImgView.setImage(getImage("alkaliMetal"));
        alkalineEarthMetalImgView.setImage(getImage("alkalineEarthMetal"));
        transitionMetal3ImgView.setImage(getImage("transitionMetal3"));
        transitionMetal4ImgView.setImage(getImage("transitionMetal4"));
        transitionMetal5ImgView.setImage(getImage("transitionMetal5"));
        transitionMetal6ImgView.setImage(getImage("transitionMetal6"));
        halogensImgView.setImage(getImage("halogens"));
        nobleGasImgView.setImage(getImage("nobleGas"));

        //set on action
        alkaliMetalImgView.setOnMouseClicked(this::alkaliMetalWorld);
        alkalineEarthMetalImgView.setOnMouseClicked(this::alkalineEarthMetalWorld);
        transitionMetal3ImgView.setOnMouseClicked(this::transitionMetal3World);
        transitionMetal4ImgView.setOnMouseClicked(this::transitionMetal4World);
        transitionMetal5ImgView.setOnMouseClicked(this::transitionMetal5World);
        transitionMetal6ImgView.setOnMouseClicked(this::transitionMetal6World);
        halogensImgView.setOnMouseClicked(this::halogensWorld);
        nobleGasImgView.setOnMouseClicked(this::nobleGasWorld);
    }

    //TODO: find a way to know what the last scene was in order to go back to it
    private void loadLastScene(Event e) {
        MainMenu.goBack();
        logger.info("Loaded the primary scene...");
    }

    //gets the specific icon from the string name
    private Image getImage(String string) {
        return new Image(MainAppFXMLController.class.
                getResource("/images/" + string + "/icon.png").toString());
    }

    /**
     * Allows to open up and instantiate an alkali world
     * @param e image clicked
     */
    private void alkaliMetalWorld(Event e) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game_layout.fxml"));
//            Parent root = loader.load();
//            GameFXMLController controller = loader.getController();
            //line below was commented for a test
            MainMenu.getSceneController().removeScene(GAME_SCENE);
            AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
            if (animationTimer != null)
                animationTimer.stop();
            MainMenu.switchScene(GAME_SCENE, Family.ALKALIMETAL); // switch to it
            //MainMenu.getGameController().setWorld(Family.ALKALIMETAL); // ✅ pass the family


            //sceneController.removeScene(GAME_SCENE); // clear any previous version
            //sceneController.addScene(GAME_SCENE, root); // preload the new scene
            logger.info("Alkali Metals has been clicked...");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            logger.error("Failed to load Game scene from map.", ex);
//        }
    }

    /**
     * Allows to open up and instantiate an alkaline earth metal world
     * @param e image clicked
     */
    private void alkalineEarthMetalWorld(Event e) {
        MainMenu.getSceneController().removeScene(GAME_SCENE);
        AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
        if (animationTimer != null)
            animationTimer.stop();
        MainMenu.switchScene(GAME_SCENE, Family.ALKALINEEARTHMETALS); // switch to it
        logger.info("Alkaline Earth Metals has been clicked...");
    }


    /**
     * Allows to open up and instantiate a transmetal 3 world
     * @param e image clicked
     */
    private void transitionMetal3World(Event e) {
        MainMenu.getSceneController().removeScene(GAME_SCENE);
        AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
        if (animationTimer != null)
            animationTimer.stop();
        MainMenu.switchScene(GAME_SCENE, Family.TRANSITIONMETAL3); // switch to it
        logger.info("Transition Metal 3 has been clicked...");
    }

    /**
     * Allows to open up and instantiate a transmetal 4 world
     * @param e image clicked
     */
    private void transitionMetal4World(Event e) {
        MainMenu.getSceneController().removeScene(GAME_SCENE);
        AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
        if (animationTimer != null)
            animationTimer.stop();
        MainMenu.switchScene(GAME_SCENE, Family.TRANSITIONMETAL4); // switch to it
        logger.info("Transition Metal 4 has been clicked...");
    }

    /**
     * Allows to open up and instantiate a transmetal 5 world
     * @param e image clicked
     */
    private void transitionMetal5World(Event e) {
        MainMenu.getSceneController().removeScene(GAME_SCENE);
        AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
        if (animationTimer != null)
            animationTimer.stop();
        MainMenu.switchScene(GAME_SCENE, Family.TRANSITIONMETAL5); // switch to it
        logger.info("Transition Metal 5 has been clicked...");
    }

    /**
     * Allows to open up and instantiate a transmetal 6 world
     * @param e image clicked
     */
    private void transitionMetal6World(Event e) {
        MainMenu.getSceneController().removeScene(GAME_SCENE);
        AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
        if (animationTimer != null)
            animationTimer.stop();
        MainMenu.switchScene(GAME_SCENE, Family.TRANSITIONMETAL6); // switch to it
        logger.info("Transition Metal 6 has been clicked...");
    }

    /**
     * Allows to open up and instantiate a halogen world
     * @param e image clicked
     */
    private void halogensWorld(Event e) {
        MainMenu.getSceneController().removeScene(GAME_SCENE);
        AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
        if (animationTimer != null)
            animationTimer.stop();
        MainMenu.switchScene(GAME_SCENE, Family.HALOGENS); // switch to it
        logger.info("Halogens has been clicked...");
    }

    /**
     * Allows to open up and instantiate a noble gas world
     * @param e image clicked
     */
    private void nobleGasWorld(Event e) {
        MainMenu.getSceneController().removeScene(GAME_SCENE);
        AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
        if (animationTimer != null)
            animationTimer.stop();
        MainMenu.switchScene(GAME_SCENE, Family.NOBLEGAS); // switch to it
        logger.info("Noble Gas has been clicked...");
    }

    //TODO: change between scenes for periods
    private void handleSettings(Event e) {
        System.out.println("Going to settings...");
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings has been clicked...");
    }
}
