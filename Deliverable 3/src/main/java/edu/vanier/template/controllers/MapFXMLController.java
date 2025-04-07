package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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



    public void start(Stage stage) {
        logger.info("starting map");
    }
    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");

        vBox.setPrefHeight(BaseWindow.sceneHeight * 0.5);
        vBox.setPrefWidth(BaseWindow.sceneWidth *0.5);

        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/map_pergament.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        vBox.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

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

    }

    //TODO: find a way to know what the last scene was in order to go back to it
    private void loadLastScene(Event e) {
        MainApp.switchScene(MainApp.MAINAPP_SCENE);
        logger.info("Loaded the primary scene...");
    }
}
