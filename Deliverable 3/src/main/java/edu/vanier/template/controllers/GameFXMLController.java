package edu.vanier.template.controllers;

import edu.vanier.template.models.*;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainApp;

import javafx.animation.AnimationTimer;

import edu.vanier.template.ui.MainMenu;
import javafx.animation.Animation;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static edu.vanier.template.controllers.SceneController.input;
import static edu.vanier.template.ui.MainMenu.*;

public class GameFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(GameFXMLController.class);
    @FXML
    Button btnMap;
    @FXML
    Button btnHelp;
//    @FXML
//    ToggleButton toggleBackpack;
    @FXML
    Button backpackBtn;
    @FXML
    Button btnSettings;
    @FXML
    Button btnBack;
    @FXML
    BorderPane borderPane;

    @FXML
    Pane mainPane;
    @FXML
    Pane backpackPane;

    public Stage backpackStage;

    private int score = 0;
    private long lastNanoTime = System.nanoTime();
    private AudioClip itemClip;
    private AnimationTimer animation;
    private int coinNum = 0;
    private int electronNum = 0;
    private int protonNum = 0;
    private int powerUpNum = 0;
    private int chocholatePowerNum = 0;
    private Family currentFamily;
    private Map<String, Integer> elementCollected = new HashMap<>();
    BackpackFXMLController backpackFXMLController;
    MapFXMLController mapFXMLController;
    public void addPlatforms(List<Platform> platformList) {
        Image imgPlatformFloating = new Image(MainAppFXMLController.class.
                getResource("/images/" +currentFamily.getName()+ "/platform.png").toString());

        int[] nbPlatforms = currentFamily.getPlatformSprites();
        int smallPlatform = nbPlatforms[0];
        int mediumPlatform = nbPlatforms[1];
        int largePlatform = nbPlatforms[2];
        int xLargePlatform = nbPlatforms[3];
        int width = (int) (BaseWindow.sceneWidth * 0.9);
        int height = (int) (BaseWindow.sceneHeight * 0.9);

        for (int x = 1; x <=xLargePlatform; x++) {
            Platform platform = new Platform((int) (width *  Math.random() + 50),
                    (int) (height *  Math.random() + 50), "floating", 1000, 30, imgPlatformFloating);
            platform.setHeight(70);
            platform.setWidth(300);
            platformList.add(platform);
        }
        for (int x = 1; x <=largePlatform; x++) {
            Platform platform = new Platform((int) (width *  Math.random() + 50),
                    (int) (height *  Math.random() + 50), "floating", 700, 30, imgPlatformFloating);
            platform.setHeight(60);
            platform.setWidth(200);
            platformList.add(platform);
        }
        for (int x = 1; x <=mediumPlatform; x++) {
            Platform platform = new Platform((int) (width *  Math.random() + 50),
                    (int) (height *  Math.random() + 50), "floating", 500, 30, imgPlatformFloating);
            platform.setHeight(50);
            platform.setWidth(100);

            platformList.add(platform);
        }
        for (int x = 1; x <=smallPlatform; x++) {
            Platform platform = new Platform((int) (width *  Math.random() + 50),
                    (int) (height *  Math.random() + 50), "floating", 300, 30, imgPlatformFloating);
            platform.setHeight(30);
            platform.setWidth(50);

            platformList.add(platform);
        }
        System.out.println(platformList);

    }
    @FXML
    public void initialize() {
        logger.info("Initializing Game Controller...");
        btnBack.setOnAction(this::handleBack);
        btnSettings.setOnAction(this::handleSettings);
        backpackBtn.setOnAction(this::handleBackpackButton);
        btnHelp.setOnAction(this::handleHelpButton);
        btnMap.setOnAction(this::handleMapButton);
        currentFamily = Family.NOFAMILY;

        Image imgPlatformFloor = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/forest_pack_05.png").toString());

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
                getResource("/images/"+currentFamily+"/background.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        List<Platform> platformList = new ArrayList<>();
        addPlatforms(platformList);

        Platform platformFloor = new Platform(0, (int)BaseWindow.sceneHeight - 100, "floor", (int)BaseWindow.sceneWidth, 100, imgPlatformFloor);
        Canvas canvas = new Canvas(BaseWindow.sceneWidth, BaseWindow.sceneHeight);
        mainPane.getChildren().addAll(canvas, platformFloor);

        Image imgPortal = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/galaxy.png").toString());
        Portal portal = new Portal((int)BaseWindow.sceneWidth - 30, (int)BaseWindow.sceneHeight - 200 - (int)platformFloor.getHeight(), 30, 200, imgPortal);

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
        Player player = new Player(500, 250, 50, 70, playerImg);
        player.setBounds(0,(int) canvas.getWidth(), 0, (int) canvas.getHeight() - (int) platformFloor.getHeight());

        List<Sprite> electronList = new ArrayList<>();
        List<Sprite> protonList = new ArrayList<>();

        Image electronImg = new Image(MainAppFXMLController.class.
                getResource("/images/Electron.png").toString());
        Image protonImg = new Image(MainAppFXMLController.class.
                getResource("/images/Proton.png").toString());

        for (int i = 0; i < 15; i++) {
            Sprite electron = new Sprite("electron", electronImg);
            electron.setSize(30);
            electron.setImage(electronImg);
            double px1 = BaseWindow.sceneWidth * 0.7 * Math.random() + 50;
            double py1 = BaseWindow.sceneHeight * 0.7 * Math.random() + 50;
            electron.setPosition(px1, py1);
            electronList.add(electron);
            Sprite proton = new Sprite("proton", protonImg);
            proton.setSize(30);
            proton.setImage(protonImg );
            double px = BaseWindow.sceneWidth * 0.7 * Math.random() + 50;
            double py = BaseWindow.sceneHeight * 0.7 * Math.random() + 50;
            proton.setPosition(px, py);
            protonList.add(proton);
        }

        animation = new AnimationTimer() {
            private boolean isJumping = false;
            private boolean isFalling = false;
            private double gravity = 600; // Gravity force
            private double jumpStrength = -300; // Jumping force
            private double velocityY = 0; // Vertical velocity
            double cnt = 0;

            @Override
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1_000_000_000.0;
                lastNanoTime = currentNanoTime;

                double canvasWidth = canvas.getWidth();
                double canvasHeight = canvas.getHeight();
                double playerWidth = player.getWidth();
                double playerHeight = player.getHeight();

                double prevX = player.getPositionX();
                double prevY = player.getPositionY();

                // Reset horizontal velocity
                player.setVelocity(0, velocityY);

                if (input.contains("A")) {
                    player.addVelocity(-250, 0);
                }
                if (input.contains("D")) {
                    player.addVelocity(250, 0);
                }

                // Jumping logic (double jump)
                /*
                if (input.contains("W") && !isFalling && cnt < 2) {
                    velocityY = jumpStrength;
                    cnt++;
                    input.remove("W");
                }
                 */

                if (input.contains("W")) {
                    velocityY = jumpStrength;
                    cnt++;
                    input.remove("W");
                }

                // Apply gravity
                velocityY += gravity * elapsedTime;
                double nextX = prevX + player.getVelocityX() * elapsedTime;
                double nextY = prevY + velocityY * elapsedTime;

                // Collision detection with platforms
                for (Platform platform : platformList) {
                    if (player.intersectsAt(nextX, prevY, platform)) {
                        nextX = prevX;
                    }
                    if (player.intersectsAt(prevX, nextY, platform)) {
                        if (velocityY > 0) { // Falling down
                            nextY = platform.getPositionY() - playerHeight;
                            velocityY = 0;
                        } else if (velocityY < 0) { // Hitting the bottom of a platform
                            nextY = platform.getPositionY() + platform.getHeight();
                            velocityY = 0;
                        }
                    }
                }

                // Keep player within bounds
                nextX = Math.max(0, Math.min(nextX, canvasWidth - playerWidth));

                if (nextY < 0) {  // Prevent going above the screen
                    nextY = 0;
                    velocityY = 0;
                } else if (nextY >= canvasHeight - playerHeight - platformFloor.getHeight()) { // Ground collision
                    nextY = canvasHeight - playerHeight - platformFloor.getHeight();
                    velocityY = 0;
                }

                if (velocityY > 0) {
                    isFalling = true;
                }
                else isFalling = false;
                if (velocityY >= 0)
                    cnt = 0;
                player.setPosition(nextX, nextY);

                if (score >= 0)
                    portal.unlock();
                // Electron collection logic
                Iterator<Sprite> electronIter = electronList.iterator();
                while (electronIter.hasNext()) {
                    Sprite electron = electronIter.next();
                    if (player.intersects(electron)) {
                        electronIter.remove();
                        itemClip.play();
                        score++;
                        electronNum++;
                        elementCollected.put("electron", electronNum);
                        backpackFXMLController.setupCoinDrag(electron);
                        backpackFXMLController.increaseCount(electron);
                        //backpackFXMLController.addObject(electron);
                        //don't necessarily need ot delete the coin
                        //won't the electron need to be removed after it collides with the player
                        //we'll also need protons but this can be done for deliverable three (the differentiation)
                    }
                }
                Iterator<Sprite> protonIter = protonList.iterator();
                while (protonIter.hasNext()) {
                    Sprite proton = protonIter.next();
                    if (player.intersects(proton)) {
                        protonIter.remove();
                        itemClip.play();
                        score++;
                        protonNum++;
                        elementCollected.put("proton", protonNum);
                        //backpackFXMLController.addObject(electron);
                        //don't necessarily need ot delete the coin
                        //won't the electron need to be removed after it collides with the player
                        //we'll also need protons but this can be done for deliverable three (the differentiation)
                    }
                }

                if (portal.intersects(player)) {
                    portal.enter();
                }

                // Render
                gc.clearRect(0, 0, canvasWidth, canvasHeight);
                player.render(gc);
                portal.render(gc);
                for (Sprite electron : electronList) {
                    electron.render(gc);
                }
                for (Sprite proton : protonList) {
                    proton.render(gc);
                }
                for (Sprite platform : platformList) {
                    platform.render(gc);
                }

                String pointsText = "electron: -" + (2 * score);
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);
            }
        };
        animation.start();
    }
/*
    @FXML
    public void setUpGridPane() {
        itemsGridPane.setStyle("-fx-background-color: #fff");
        itemsGridPane.setPrefWidth(240);
        itemsGridPane.setPrefHeight(300);
        Image coinImage = new Image((MainAppFXMLController.class.getResource("/images/player.png").toString()));
        ImageView coinImageView = new ImageView(coinImage);
        coinImageView.setFitWidth(50);
        coinImageView.setFitHeight(50);
        coinImageView.setPreserveRatio(true);
        itemsGridPane.add(coinImageView, 0, 0);
        logger.info("Loading coin image: T/F" + coinImage.isError());
        coinLabel.setText(" x 0");
        itemsGridPane.add(coinLabel, 1, 0);
        Image electronImage = new Image((MainAppFXMLController.class.getResource("/images/player.png").toString()));
        ImageView electronImageView = new ImageView(electronImage);
        itemsGridPane.add(electronImageView, 0, 1);
        electronLabel.setText(" x 0");
        itemsGridPane.add(electronLabel, 1, 1);
        Image protonImage = new Image(MainAppFXMLController.class.getResource("/images/player.png").toString());
        ImageView protonImageView = new ImageView(protonImage);
        itemsGridPane.add(protonImageView, 0, 2);
        protonLabel.setText(" x 0");
        itemsGridPane.add(protonLabel, 1, 2);
        Image powerUpImage = new Image(MainAppFXMLController.class.getResource("/images/player.png").toString());
        ImageView powerUpImageView = new ImageView(powerUpImage);
        itemsGridPane.add(powerUpImageView, 0, 3);
        powerUpLabel.setText(" x 0");
        itemsGridPane.add(powerUpLabel, 1, 3);
        Image chocolatePowerUp = new Image(MainAppFXMLController.class.getResource("/images/player.png").toString());
        ImageView chocolatePowerUpImageView = new ImageView(chocolatePowerUp);
        itemsGridPane.add(chocolatePowerUpImageView, 0, 4);
        chocholatePowerLabel.setText(" x 0");
        itemsGridPane.add(chocholatePowerLabel, 1, 4);
        System.out.println("Number of items in the gridpane: " + itemsGridPane.getChildren().size());
    }

    public void isBackpackEmpty() {
        if (itemsGridPane.getChildren().isEmpty()) {
            setUpGridPane();
            System.out.println("isBackpackEmpty method being used");
        }
    }

    //should addToBackpack and removeFromBackpack be one method (updateBackpack)
    //how can I update the backpack

    public void updateBackpack() {
        coinLabel.setText(" x " + String.valueOf(coinNum));
        electronLabel.setText(" x " + String.valueOf(electronNum));
        protonLabel.setText(" x " + String.valueOf(protonNum));
        powerUpLabel.setText(" x " + String.valueOf(powerUpNum));
        chocholatePowerLabel.setText(" x " + String.valueOf(chocholatePowerNum));
    }

    public void dragElements() {

    }

*/
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
/*
    @FXML
    private void handleToggleBackpack(Event e) {
        System.out.println("Showing the backpack");
        boolean isBackpackVisible = backpackPane.isVisible(); //sets it to a true value//set to false in the FXML file originally
        if(isBackpackVisible == false) {
            isBackpackEmpty();
            backpackPane.setVisible(true); //now sets the backpack to visible
            backpackPane.setManaged(true); //now sets the backpack to be in the screen of the game
            borderPane.setRight(backpackPane);
            isBackpackVisible = true;
            logger.info("Backpack has been clicked...");
        }
        else {
            isBackpackEmpty();
            backpackPane.setVisible(false);
            backpackPane.setManaged(false);
            isBackpackVisible = false;
            logger.info("Backpack has been clicked...");
        }
    }
/*
    @FXML
    private void handleToggleBackpack(Event e) {
        // Check if the backpack is currently attached to the right region.
        if (borderPane.getRight() == null) {
            // Show the backpack: add it to the right region.
            borderPane.setRight(backpackScrollPane);
            backpackScrollPane.setVisible(true);
            backpackScrollPane.setManaged(true);
            // Optionally initialize its content if necessary.
            isBackpackEmpty();
            logger.info("Backpack shown.");
        } else {
            // Hide the backpack: remove it from the right region.
            borderPane.setRight(null);
            backpackScrollPane.setVisible(false);
            backpackScrollPane.setManaged(false);
            logger.info("Backpack hidden.");
        }
    }

 */

    //where should I use this
    private void setupDropTarget() {
        mainPane.setOnDragOver(event -> {
            if (event.getGestureSource() != mainPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
        mainPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                String itemType = db.getString();
                logger.info("Dropped coin of type: " + itemType);
                ImageView droppedCoin = createItemImageView(itemType);
                droppedCoin.setLayoutX(event.getX());
                droppedCoin.setLayoutY(event.getY());
                mainPane.getChildren().add(droppedCoin);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private ImageView createItemImageView(String itemType) {
        String imagePath;
        if ("electron".equalsIgnoreCase(itemType)) {
            imagePath = "/images/Electron.png";
        } else if ("proton".equalsIgnoreCase(itemType)) {
            imagePath = "/images/Proton.png";
        } else if ("coin".equalsIgnoreCase(itemType)) {
            imagePath = "/images/coin.png";
        }
        else if ("powerUp".equalsIgnoreCase(itemType)) {
            imagePath = "/images/PowerUp.png";
        }
        else if("chocolatePowerUp".equalsIgnoreCase(itemType)) {
            imagePath = "/images/ChocolatePowerUp.png";
        }
        else {
            imagePath = "/images/default.png";
        }
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public void handleBackpackButton(Event e) {
        try {
            System.out.println("Backpack has been clicked");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BackpackScene.fxml"));
            //loader.setController(this);
            Parent root = loader.load();
            backpackFXMLController = loader.getController();
           // backpackFXMLController.setUpGridPane();

            backpackStage = new Stage();
            backpackStage.setTitle("Backpack");
            Scene backpackScene = new Scene(root, 460, 574);
            backpackStage.setScene(backpackScene);
            backpackStage.setResizable(false);
            backpackStage.setAlwaysOnTop(true);

            backpackFXMLController.setUpGridPane();
            backpackStage.show();
            backpackStage.toFront();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void handleMapButton(Event e) {
        try {
            System.out.println("Map has been clicked");

            // Load FXML and controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Map_layout.fxml"));
            Parent root = loader.load();
            mapFXMLController = loader.getController();

            // Create a new stage
            Stage mapStage = new Stage();
            mapStage.setTitle("Map");
            mapStage.setScene(new Scene(root, BaseWindow.sceneWidth * 0.6,BaseWindow.sceneHeight * 0.6));
            mapStage.setResizable(false);
            mapStage.setAlwaysOnTop(true);

            // Show the new window on top of the current one
            mapStage.show();
            mapStage.toFront();

        } catch (Exception exception) {
            System.err.println("Error opening map window: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
    public void handleHelpButton(Event e){
        Stage helpStage = new Stage();
        Label testing = new Label("Testing");
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setMinHeight(600);
        VBox helpVbox = new VBox(scrollBar);
        Scene helpScene = new Scene(helpVbox, BaseWindow.sceneWidth *0.6,BaseWindow.sceneHeight *0.6);
        helpStage.setScene(helpScene);
        helpStage.show();
    }
}
