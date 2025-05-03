package edu.vanier.template.controllers;

import edu.vanier.template.models.*;
import edu.vanier.template.models.Platform;
import edu.vanier.template.models.Player;
import edu.vanier.template.models.Portal;
import edu.vanier.template.models.Sprite;
import edu.vanier.template.ui.BaseWindow;

import javafx.animation.AnimationTimer;

import edu.vanier.template.ui.MainMenu;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    private Family currentFamily;

    public Stage backpackStage;

    private int score = 0;
    private long lastNanoTime = System.nanoTime();
    private AudioClip itemClip;
    private AudioClip jumpClip;
    private AnimationTimer animation;
    private int coinNum = 0;
    private int electronNum = 0;
    private int protonNum = 0;
    private int powerUpNum = 0;
    private int chocolatePowerNum = 0;
    private Sprite electron;
    private Sprite proton;
    private Sprite powerUp;
    private boolean facingLeft = false;
    private Map<String, Integer> elementCollected = new HashMap<>();
    BackpackFXMLController backpackFXMLController;
    MapFXMLController mapFXMLController;
    private Portal portal;
    private Canvas canvas;
    private String strPlayerImg;
    private Sprite platformFloor;
    private Image electronImg;
    private Image protonImg;
    private String strPlayerFlippedImg;
    private ArrayList<Platform> platformList;
    private Image imgPlatformFloor;
    private BackgroundSize bSize;
    private Image imgPortal;

    public GameFXMLController(Family currentFamily) {
        this.currentFamily = currentFamily;
    }

    public AnimationTimer getAnimation() {
        return animation;
    }

    public void setAnimation(AnimationTimer animation) {
        this.animation = animation;
    }

    public void setWorldSprites(List<Platform> platformList) {
        if (currentFamily.getLayoutType().equals("A")) {
            Platform.setPlatformsTypeA(currentFamily, platformList);
        } else if (currentFamily.getLayoutType().equals("B")) {
            Platform.setPlatformsTypeB(currentFamily, platformList);
        } else if (currentFamily.getLayoutType().equals("C")) {
            Platform.setPlatformsTypeC(currentFamily, platformList);
        } else if (currentFamily.getLayoutType().equals("1.1")) {
            Platform.setPlatformsType11(currentFamily, platformList);
            portal.setDestination(GAME_SCENE);
        } else if (currentFamily.getLayoutType().equals("1.2")) {
            Platform.setPlatformsType12(currentFamily, platformList);
        } else if (currentFamily.getLayoutType().equals("2")) {
            Platform.setPlatformsType2(currentFamily, platformList);
        } else if (currentFamily.getLayoutType().equals("3.1")) {
            Platform.setPlatformsType31(currentFamily, platformList);
            portal.setDestination(GAME_SCENE);
        } else if (currentFamily.getLayoutType().equals("3.2")) {
            Platform.setPlatformsType32(currentFamily, platformList);
        }
    }

    public void setWorld() {
        //Images for the game
        imgPlatformFloor = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/forest_pack_05.png").toString());
        backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/"+currentFamily.getName()+"/background.png").toString());
        bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        imgPortal = new Image(MainAppFXMLController.class.
                getResource("/images/PNG/galaxy.png").toString());
        strPlayerImg = MainAppFXMLController.class.
                getResource("/images/player.gif").toString();
        strPlayerFlippedImg = MainAppFXMLController.class.
                getResource("/images/playerFlipped.gif").toString();
        electronImg = new Image(MainAppFXMLController.class.
                getResource("/images/Electron.png").toString());
        protonImg = new Image(MainAppFXMLController.class.
                getResource("/images/Proton.png").toString());
        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        platformList = new ArrayList<>();
        platformFloor = new Platform(0, (int)BaseWindow.sceneHeight - 200, "floor", (int)BaseWindow.sceneWidth, 200, imgPlatformFloor);
        canvas = new Canvas(BaseWindow.sceneWidth, BaseWindow.sceneHeight);
        mainPane.getChildren().addAll(canvas, platformFloor);
        portal = new Portal((int)BaseWindow.sceneWidth - 100, (int)BaseWindow.sceneHeight - 100 - (int)platformFloor.getHeight(), 30, 100, imgPortal, QUESTIONEX2_SCENE);
        setWorldSprites(platformList);
    }

    @FXML
    public void initialize() {
        logger.info("Initializing Game Controller...");

        setButton(btnMap, "Button_132",3 ,3);
        setButton(btnHelp, "Button_help",3 ,3);
        setButton(backpackBtn, "backpack",3 ,3);
        setButton(btnSettings, "Button_settings", 3 ,3);
        setButton(btnBack, "back", 3 ,3);

        btnBack.setOnAction(this::handleBack);
        btnSettings.setOnAction(this::handleSettings);
        backpackBtn.setOnAction(this::handleBackpackButton);
        btnHelp.setOnAction(this::handleHelpButton);
        btnMap.setOnAction(this::handleMapButton);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BackpackScene.fxml"));
            //loader.setController(this);
            Parent root = loader.load();
            backpackFXMLController = loader.getController();
            backpackFXMLController.setUpGridPane();
            backpackStage = new Stage();
            backpackStage.setTitle("Backpack");
            Scene backpackScene = new Scene(root, 460, 574);
            backpackStage.setScene(backpackScene);
            backpackStage.setResizable(false);
            backpackStage.setAlwaysOnTop(true);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        setWorld();
        //user should first build the atom, below line directs them to build atom after collecting electrons and protons from the game scene
       // Portal portal = new Portal((int)BaseWindow.sceneWidth - 30, (int)BaseWindow.sceneHeight - 200 - (int)platformFloor.getHeight(), 30, 200, imgPortal, QUESTION1BUILDATOM);
        //-- Create and configure the media player.
        itemClip = new AudioClip(getClass().getResource("/sounds/item_pickup.wav").toExternalForm());
        itemClip.setVolume(0.40);
        jumpClip = new AudioClip(getClass().getResource("/sounds/jump.wav").toExternalForm());
        jumpClip.setVolume(0.40);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font scoreFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(scoreFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        //@author Tabasuum
        //allows to flip character while moving to simulate animation
        Player player = new Player(500, 250,
                (int) (50 * BaseWindow.sceneWidth/2560),
                (int) (70 * BaseWindow.sceneHeight/1440), new Image(strPlayerImg));
        player.setImage(strPlayerImg);
        player.setBounds(0,(int) canvas.getWidth(), 0,
                (int) canvas.getHeight() - (int) platformFloor.getHeight());

        //TODO: Generalize this code from being electrons and protons to being any sprite
        List<Sprite> sprite1List = new ArrayList<>();
        List<Sprite> sprite2List = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            //TODO: generalize
            Sprite electron = new Sprite("electron", electronImg);
            Sprite proton = new Sprite("proton", protonImg);
            electron.setSize(30 * BaseWindow.sceneHeight / 770);
            proton.setSize(30 * BaseWindow.sceneHeight / 770);
            electron.setImage(electronImg);
            proton.setImage(protonImg);

            //position should be set with  the rest of the platforms
            double px1 = BaseWindow.sceneWidth * 0.7 * Math.random() + 50;
            double py1 = BaseWindow.sceneHeight * 0.7 * Math.random() + 50;

            double px = BaseWindow.sceneWidth * 0.7 * Math.random() + 50;
            double py = BaseWindow.sceneHeight * 0.7 * Math.random() + 50;

            electron.setPosition(px1, py1);
            proton.setPosition(px, py);
            sprite2List.add(proton);
            sprite1List.add(electron);
        }

        animation = new AnimationTimer() {
            private boolean isJumping = false;
            private boolean isFalling = false;
            private double gravity = 600; // Gravity force
            private double jumpStrength = -300; // Jumping to force
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

                //@author Tabasuum
                //continues animation of gif to simulate movement
                if (input.contains("A") && !input.contains("D")) {
                    player.addVelocity(-250, 0);
                    if (!player.getImgStr().equals(strPlayerFlippedImg))
                        player.setImage(strPlayerFlippedImg);
                } else if (input.contains("D") && !input.contains("A")) {
                    player.addVelocity(250, 0);
                    if (!player.getImgStr().equals(strPlayerImg))
                        player.setImage(strPlayerImg);
                }
                // Jumping logic (double jump)
                if (input.contains("W") && cnt < 5) {
                    velocityY = jumpStrength;
                    cnt++;
                    input.remove("W");
                    if(!jumpClip.isPlaying()){
                        jumpClip.play();
                    }
                }

                /*
                if (input.contains("W")) {
                    velocityY = jumpStrength;
                    cnt++;
                    input.remove("W");
                    if(!jumpClip.isPlaying()){
                        jumpClip.play();
                    }
                }
                */

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
                if (velocityY == 0)
                    cnt = 0;
                player.setPosition(nextX, nextY);

                if (score >= 0)
                    portal.unlock();

                // Electron collection logic
                Iterator<Sprite> sprite1Iter = sprite1List.iterator();
                while (sprite1Iter.hasNext()) {
                    //TODO for logic of electron check if name is electron
                    Sprite electron = sprite1Iter.next();
                    if (player.intersects(electron)) {
                        sprite1Iter.remove();
                        itemClip.play();
                        System.out.println("interacting with a sprite");
                        score--;
                        electronNum++;
                        elementCollected.put("electron", electronNum);
                        mainPane.getChildren().remove(electron);
                        //backpackFXMLController.setupCoinDrag(electron);
                        if(backpackFXMLController != null) {
                            backpackFXMLController.increaseCount(electron);
                            System.out.println("backpack is not null");
                        }
                        //backpackFXMLController.addObject(electron);
                        //don't necessarily need ot delete the coin
                        //won't the electron need to be removed after it collides with the player
                        //we'll also need protons but this can be done for deliverable three (the differentiation)
                    }
                }
                Iterator<Sprite> sprite2Iter = sprite2List.iterator();
                while (sprite2Iter.hasNext()) {
                    Sprite proton = sprite2Iter.next();
                    if (player.intersects(proton)) {
                        sprite2Iter.remove();
                        itemClip.play();
                        score++;
                        protonNum++;
                        elementCollected.put("proton", protonNum);
                        backpackFXMLController.increaseCount(proton);
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
                for (Sprite electron : sprite1List) {
                    electron.render(gc);
                }
                for (Sprite proton : sprite2List) {
                    proton.render(gc);
                }

                for (Sprite platform : platformList) {
                    platform.render(gc);
                }

                String pointsText = "charge: " + (2 * score);
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);
            }
        };

        animation.start();

        mainPane.setOnDragOver(event -> {
            if (event.getGestureSource() != mainPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();

            System.out.println();
        });

        mainPane.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                String spriteType = dragboard.getString().toLowerCase();

                Image sprite = null;

                switch (spriteType) {
                    case "electron":
                        sprite = new Image(getClass().getResource("/images/Electron.png").toExternalForm());
                        break;
                    case "proton":
                        sprite = new Image(getClass().getResource("/images/Proton.png").toExternalForm());
                        break;
                }

                if (sprite != null) {
                    Sprite reuploadedSprite = new Sprite(spriteType, sprite);
                    reuploadedSprite.setImage(sprite);
                    reuploadedSprite.setSize(30);
                    reuploadedSprite.setPreserveRatio(true);

                    double positionX  = event.getX();
                    double positionY = event.getY();
                    reuploadedSprite.setPosition(positionX, positionY);

                    switch (spriteType) {
                        case "electron" -> sprite1List.add(reuploadedSprite);
                        case "proton" -> sprite2List.add(reuploadedSprite);
                    }
                    mainPane.getChildren().add(reuploadedSprite);
                }
                success = true;
            }

            event.setDropCompleted(success);
            System.out.println("Dropped: " + dragboard.getString());
            event.consume();
        });
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
        chocolatePowerLabel.setText(" x 0");
        itemsGridPane.add(chocolatePowerLabel, 1, 4);
        System.out.println("Number of items in the gridPane: " + itemsGridPane.getChildren().size());
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
        chocolatePowerLabel.setText(" x " + String.valueOf(chocolatePowerNum));
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

            if(backpackStage != null && !backpackStage.isShowing()) {
                backpackStage.show();
                backpackStage.toFront();
                //backpackFXMLController.setUpGridPane();
            }


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
            mapStage.setScene(new Scene(root, BaseWindow.sceneWidth * 0.8,BaseWindow.sceneHeight * 0.8));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Help_layout.fxml"));

        Parent helpRoot = null;
        try {
            helpRoot = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(helpRoot, BaseWindow.sceneWidth * 0.8, BaseWindow.sceneHeight * 0.8));

        helpStage.initOwner(borderPane.getScene().getWindow());
        helpStage.show();
    }
}
