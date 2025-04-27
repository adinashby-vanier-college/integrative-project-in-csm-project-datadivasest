package edu.vanier.template.controllers;

import edu.vanier.template.models.Family;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainApp;
import edu.vanier.template.ui.MainMenu;
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

import static edu.vanier.template.ui.MainMenu.GAME_SCENE;
import static edu.vanier.template.ui.MainMenu.sceneController;

/**
 * @author Tabasuum
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
        borderPane.setPrefHeight(BaseWindow.sceneHeight * 0.8);
        borderPane.setPrefWidth(BaseWindow.sceneWidth * 0.8);

        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/Files/png/BG.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        borderPaneBg.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

        vBox.setPrefHeight(BaseWindow.sceneHeight * 0.75);
        vBox.setPrefWidth(BaseWindow.sceneWidth * 0.60);

        Image bGImg = new Image(MainAppFXMLController.class.
                getResource("/images/map_pergament.png").toString());
        BackgroundSize bGSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        vBox.setBackground(new Background(new BackgroundImage(bGImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bGSize)));

        Image alkaliMetalImg = new Image(MainAppFXMLController.class.
                getResource("/images/alkaliMetal/icon.png").toString());
        alkaliMetalImgView.setImage(alkaliMetalImg);

        Image alkalineEarthMetalImg = new Image(MainAppFXMLController.class.
                getResource("/images/alkalineEarthMetal/icon.png").toString());
        alkalineEarthMetalImgView.setImage(alkalineEarthMetalImg);

        Image transitionMetal3Img = new Image(MainAppFXMLController.class.
                getResource("/images/transitionMetal3/icon.png").toString());
        transitionMetal3ImgView.setImage(transitionMetal3Img);

        Image transitionMetal4Img = new Image(MainAppFXMLController.class.
                getResource("/images/transitionMetal4/icon.png").toString());
        transitionMetal4ImgView.setImage(transitionMetal4Img);

        Image transitionMetal5Img = new Image(MainAppFXMLController.class.
                getResource("/images/transitionMetal5/icon.png").toString());
        transitionMetal5ImgView.setImage(transitionMetal5Img);

        Image transitionMetal6Img = new Image(MainAppFXMLController.class.
                getResource("/images/transitionMetal6/icon.png").toString());
        transitionMetal6ImgView.setImage(transitionMetal6Img);

        Image halogensImg = new Image(MainAppFXMLController.class.
                getResource("/images/halogens/icon.png").toString());
        halogensImgView.setImage(halogensImg);

        Image nobleGasImg = new Image(MainAppFXMLController.class.
                getResource("/images/nobleGas/icon.png").toString());
        nobleGasImgView.setImage(nobleGasImg);

        alkaliMetalImgView.setOnMouseClicked(this::alkaliMetalWorld);

    }

    //TODO: find a way to know what the last scene was in order to go back to it
    private void loadLastScene(Event e) {
        MainApp.switchScene(MainApp.MAINAPP_SCENE);
        logger.info("Loaded the primary scene...");
    }

    private void alkaliMetalWorld(Event e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game_layout.fxml"));
            Parent root = loader.load();
            GameFXMLController controller = loader.getController();
            //line below was commented for a test
            //controller.setCurrentFamily(Family.ALKALIMETAL); // ✅ pass the family

            sceneController.removeScene(GAME_SCENE); // clear any previous version
            sceneController.addScene(GAME_SCENE, root); // preload the new scene
            MainMenu.switchScene(GAME_SCENE); // switch to it

            logger.info("Alkali Metals has been clicked...");
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error("Failed to load Game scene from map.", ex);
        }
    }

    //TODO: change between scenes for periods
    private void handleSettings(Event e) {
        System.out.println("Going to settings...");
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings has been clicked...");
    }
}
