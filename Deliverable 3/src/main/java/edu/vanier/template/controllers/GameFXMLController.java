package edu.vanier.template.controllers;

import edu.vanier.template.models.Platform;
import edu.vanier.template.models.Sprite;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainApp;

import javafx.animation.AnimationTimer;

import edu.vanier.template.ui.MainMenu;
import javafx.animation.Animation;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static edu.vanier.template.controllers.SceneController.input;
import static edu.vanier.template.ui.MainMenu.*;

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
    BorderPane borderPane;

    @FXML
    Pane mainPane;

    private int score = 0;
    private long lastNanoTime = System.nanoTime();
    private AudioClip itemClip;
    private AnimationTimer animation;

    @FXML
    public void initialize() {
        logger.info("Initializing Game Controller...");
        btnBack.setOnAction(this::handleBack);
        btnSettings.setOnAction(this::handleSettings);
        Image imgPlatformFloor = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/forest_pack_05.png").toString());

        Image imgPlatformFloating = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/forest_pack_39.png").toString());
//        // Set the platform floor image
//        platformFloorImgView.setImage(imgPlatformFloor);
//        platformFloorImgView.setPreserveRatio(false);
//        platformFloorImgView.setFitWidth(BaseWindow.sceneWidth);
//        platformFloorImgView.setFitHeight(100);
//
//// Position it at the bottom of the scene
//        platformFloorImgView.setLayoutX(0);
//        platformFloorImgView.setLayoutY(BaseWindow.sceneHeight - platformFloorImgView.getFitHeight());

// Add the platform floor to the mainPane
        //mainPane.getChildren().add(platformFloorImgView);
        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/bg_forest.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        Platform platformFloor = new Platform(0, (int)BaseWindow.sceneHeight - 100, "floor", 100, (int)BaseWindow.sceneWidth, imgPlatformFloor);
        Platform platform1 = new Platform(300, 100, "floating", 60, 150, imgPlatformFloating);
        Platform platform2 = new Platform(100, 200, "floating", 60, 150, imgPlatformFloating);
        Platform platform3 = new Platform(200, 50, "floating", 60, 60, imgPlatformFloating);
        Platform platform4 = new Platform(20, 250, "floating", 60, 200, imgPlatformFloating);

        Canvas canvas = new Canvas(BaseWindow.sceneWidth, BaseWindow.sceneHeight);
        logger.info("" + canvas.getWidth() + " " + canvas.getHeight());
        mainPane.getChildren().addAll(platformFloor, platform1, platform2, platform3, platform4);
        mainPane.getChildren().add(canvas);

//        this.setOnCloseRequest((event) -> {
//            // Stop the animation timer upon closing this window.
//            if (animation != null) {
//                animation.stop();
//            }
//        });

        //-- Create and configure the media player.
        itemClip = new AudioClip(getClass().getResource("/sounds/item_pickup.wav").toExternalForm());

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font scoreFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(scoreFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        Image playerImg = new Image(MainAppFXMLController.class.
                getResource("/images/player.png").toString());
        Sprite player = new Sprite("player", playerImg);
        player.setWidth(50);
        player.setHeight(70);
        player.setImage(playerImg);
        player.setPosition(50, 250);

        List<Sprite> electronList = new ArrayList<>();

        Image electronImg = new Image(MainAppFXMLController.class.
                getResource("/images/coin.png").toString());

        for (int i = 0; i < 15; i++) {
            Sprite electron = new Sprite("electron", electronImg);
            electron.setSize(30);
            electron.setImage(electronImg);
            double px = 220 * Math.random() + 50;
            double py = 220 * Math.random() + 50;
            electron.setPosition(px, py);
            electronList.add(electron);
        }

        animation = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // Calculate time since last update
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1_000_000_000.0;
                lastNanoTime = currentNanoTime;

                // Canvas dimensions
                double canvasWidth = canvas.getWidth();
                double canvasHeight = canvas.getHeight();
                double playerWidth = player.getWidth();
                double playerHeight = player.getHeight();

                // Reset velocity
                player.setVelocity(0, 0);

                // Log inputs
                logger.info(input.toString());

                // Movement handling
                if (input.contains("A")) {
                    player.addVelocity(-250, 0);
                }
                if (input.contains("D")) {
                    player.addVelocity(250, 0);
                }
                if (input.contains("W")) {
                    player.addVelocity(0, -250);
                }
                 if (input.contains("S")) {
                     player.addVelocity(0, 250);
                 }

                // Update player position
                player.update(elapsedTime);

                // Ensure the player stays within bounds
                double newX = Math.max(0, Math.min(player.getPositionX(), canvasWidth - playerWidth));
                double newY = Math.max(0, Math.min(player.getPositionY(), canvasHeight - playerHeight - platformFloor.getFitHeight()));

                player.setPosition(newX, newY);

                // Collision detection
                Iterator<Sprite> electronIter = electronList.iterator();
                while (electronIter.hasNext()) {
                    Sprite electron = electronIter.next();
                    if (player.intersects(electron)) {
                        electronIter.remove();
                        itemClip.play();
                        score++;
                    }
                }

                // Render
                gc.clearRect(0, 0, canvasWidth, canvasHeight);
                player.render(gc);

                for (Sprite electron : electronList) {
                    electron.render(gc);
                }

                String pointsText = "electron: -" + (2 * score);
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);
            }
        };
        animation.start();
    }

        private void handleBack(Event e) {
        System.out.println("Going back to...");
        MainMenu.switchScene(MainMenu.DIALOGUE_SCENE);
        logger.info("Back button has been clicked...");
    }
    private void handleSettings(Event e) {
        System.out.println("Going to settings...");
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings has been clicked...");
    }
}
