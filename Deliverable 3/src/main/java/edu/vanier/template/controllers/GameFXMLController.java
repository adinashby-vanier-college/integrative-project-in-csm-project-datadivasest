package edu.vanier.template.controllers;

import edu.vanier.template.models.Platform;
import edu.vanier.template.ui.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(GameFXMLController.class);
    @FXML
    Button btnMap;
    @FXML
    Button btnHelp;
    @FXML
    Button btnSettings;
    @FXML
    Button btnBack;
    @FXML
    ImageView platformFloorImgView;
    @FXML
    BorderPane borderPane;

    @FXML
    Pane mainPane;

    @FXML
    public void initialize() {
        logger.info("Initializing Game Controller...");
        btnBack.setOnAction(this::handleBack);
        Image platformFloor = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/forest_pack_05.png").toString());

        Image platformFloating = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/forest_pack_39.png").toString());
        platformFloorImgView.setImage(platformFloor);
        platformFloorImgView.setPreserveRatio(false);
        platformFloorImgView.setFitWidth(2000);
        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/bg_forest.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

        Platform platform1 = new Platform(300, 100,"floating", 60, 150 );
        Platform platform2 = new Platform(100, 200,"floating", 60, 150 );
        Platform platform3 = new Platform(200, 50,"floating", 60, 60 );
        Platform platform4 = new Platform(20, 250,"floating", 60, 200 );
        platform1.setImage(platformFloating);
        platform2.setImage(platformFloating);
        platform3.setImage(platformFloating);
        platform4.setImage(platformFloating);

        mainPane.getChildren().addAll(platform1,platform2, platform3, platform4);
    }

    private void handleBack(Event e) {
        System.out.println("Going back to...");
        MainApp.switchScene(MainApp.MAINAPP_SCENE);
        logger.info("Back button has been clicked...");
    }
}
