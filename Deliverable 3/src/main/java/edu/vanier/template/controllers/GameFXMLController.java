package edu.vanier.template.controllers;

import edu.vanier.template.helpers.FxUIHelper;
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

import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

import static edu.vanier.template.controllers.SceneController.input;
import static edu.vanier.template.models.World.setSprites;
import static edu.vanier.template.ui.MainMenu.*;

public class GameFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(GameFXMLController.class);
    @FXML
    Button btnMap;
    @FXML
    Button btnHelp;
    @FXML
    Button backpackBtn;
    @FXML
    Button btnSettings;
//    @FXML
//    Button btnBack;
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
    private Sprite powerUp;
    private boolean facingLeft = false;
    private Map<String, Integer> elementCollected = new HashMap<>();
    MapFXMLController mapFXMLController;
    private Portal portal;
    //    private Portal portalBack;
    private Canvas canvas;
    private String strPlayerImg;
    private Sprite platformFloor;
    private String strPlayerFlippedImg;
    private ArrayList<Platform> platformList;
    private Image imgPlatformFloor;
    private BackgroundSize bSize;
    private Image imgPortal;
    private double volume;
    private boolean isSound;
    public List<Sprite> sprite1List = new ArrayList<>();
    public List<Sprite> sprite2List = new ArrayList<>();
    public List<Sprite> sprite3List = new ArrayList<>();
    public List<Sprite> sprite4List = new ArrayList<>();
    public Sprite sprite1;
    public Sprite sprite2;
    public Sprite sprite3;
    public Sprite sprite4;

    public GameFXMLController(Family currentFamily) {
        this.currentFamily = currentFamily;
    }

    public AnimationTimer getAnimation() {
        return animation;
    }

    public void setAnimation(AnimationTimer animation) {
        this.animation = animation;
    }

    public void setPortalDestination() {
        switch (currentFamily) {
            case LEVEL11 -> portal.setDestination(GAME_SCENE);
        }
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public void toggleMusic(boolean play) {
        isSound = play;
    }
    public void setWorld() {
        //Images for the game
        imgPlatformFloor = getImage("forest_pack_05.png");
        backgroundImg = getImage(currentFamily.getName()+"/background.png");
        bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        imgPortal = getImage("PNG/galaxy.png");
        strPlayerImg = MainAppFXMLController.class.
                getResource("/images/player.gif").toString();
        strPlayerFlippedImg = MainAppFXMLController.class.
                getResource("/images/playerFlipped.gif").toString();
        setBackground(borderPane, currentFamily.getName()+"/background.png");

        platformList = new ArrayList<>();
        platformFloor = new Platform(0, (int)BaseWindow.sceneHeight - 200, "floor", (int)BaseWindow.sceneWidth, 200, imgPlatformFloor);
        canvas = new Canvas(BaseWindow.sceneWidth, BaseWindow.sceneHeight);
        mainPane.getChildren().addAll(canvas, platformFloor);
        portal = new Portal((int)BaseWindow.sceneWidth - 100, (int)BaseWindow.sceneHeight - 100 - (int)platformFloor.getHeight(), 30, 100, imgPortal, QUESTION1BUILDATOM);
       // portal = new Portal((int)BaseWindow.sceneWidth - 100, (int)BaseWindow.sceneHeight - 100 - (int)platformFloor.getHeight(), 30, 100, imgPortal, INSTRUCTIONS2_SCENE);
        World.generateElements(currentFamily, platformList, portal);
    }
//    void setPortalBack() {
//        portalBack = new Portal(50, (int)BaseWindow.sceneHeight - 100 - (int)platformFloor.getHeight(), 30, 100, imgPortal, GAME_SCENE);
//        if (currentFamily.equals(Family.LEVEL12))
//            portalBack.setLevel(12);
//        else
//            portalBack.setLevel(32);
//        portalBack.setHeight(BaseWindow.sceneHeight);
//    }

    public void renderSprites(Player player, GraphicsContext gc) {
        iterateList(player, sprite1List);
        iterateList(player, sprite2List);
        iterateList(player, sprite3List);
        iterateList(player, sprite4List);

        renderList(sprite1List, gc);
        renderList(sprite2List, gc);
        renderList(sprite3List, gc);
        renderList(sprite4List, gc);
    }

    private void iterateList(Player player, List<Sprite> spriteList) {
        Iterator<Sprite> spriteIter = spriteList.iterator();
        while (spriteIter.hasNext()) {
            Sprite sprite = spriteIter.next();
            if (player.intersects(sprite)) {
                spriteIter.remove();
                itemClip.setVolume(volume);
                if (isSound)
                    itemClip.play();
                mainPane.getChildren().remove(sprite);
                updateCounts(sprite);
            }
        }
    }
    private void updateCounts(Sprite sprite) {
        score++;
        if (sprite.getType().equals("electron")) {
            electronNum++;
            elementCollected.put("electron", electronNum);
        } else if (sprite.getType().equals("proton")) {
            protonNum++;
            elementCollected.put("proton", protonNum);
        }
        if (backpackFXMLController != null)
            backpackFXMLController.increaseCount(sprite);
    }

    private void renderList(List<Sprite> spriteList, GraphicsContext gc) {
        for (Sprite sprite : spriteList) {
            sprite.render(gc);
        }
    }
    @FXML
    public void initialize() {
        logger.info("Initializing Game Controller...");

        setButton(btnMap, "Button_132",3 ,3);
        setButton(btnHelp, "Button_help",3 ,3);
        setButton(backpackBtn, "backpack",3 ,3);
        setButton(btnSettings, "Button_settings", 3 ,3);
//        setButton(btnBack, "back", 3 ,3);
        isSound = MainMenu.isSoundPlaying();
        volume = 0.3;

//        btnBack.setOnAction(this::handleBack);
        btnSettings.setOnAction(this::handleSettings);
        backpackBtn.setOnAction(this::handleBackpackButton);
        btnHelp.setOnAction(this::handleHelpButton);
        btnMap.setOnAction(this::handleMapButton);



        try {
            backpackFXMLController.setUpGridPane();
            backpackStage = new Stage();
            backpackStage.setTitle("Backpack");
            Scene backpackScene = new Scene(FxUIHelper.loadFXML(BACKPACK_SCENE, backpackFXMLController), 460, 574);
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
        jumpClip = new AudioClip(getClass().getResource("/sounds/jump.wav").toExternalForm());

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //@author Tabasuum
        //allows to flip character while moving to simulate animation
        Player player = new Player(0, 7000,
                (int) (50 * BaseWindow.sceneWidth/2560),
                (int) (70 * BaseWindow.sceneHeight/1440), new Image(strPlayerImg));
        player.setImage(strPlayerImg);
        player.setBounds(0,(int) canvas.getWidth(), 0,
                (int) canvas.getHeight() - (int) platformFloor.getHeight());

        setSprites(currentFamily, sprite1, sprite2, sprite3, sprite4, sprite1List, sprite2List, sprite3List , sprite4List);

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
                    if(!jumpClip.isPlaying() && isSound){
                        jumpClip.setVolume(volume);
                        if (isSound)
                            jumpClip.play();
                    }
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
                if (velocityY == 0)
                    cnt = 0;
                player.setPosition(nextX, nextY);

                if (score >= 0)
                    portal.unlock();

                // Sprite collection logic
                renderSprites(player, gc);

                if (portal.intersects(player)) {
                    portal.enter();
                }

                // Render
                gc.clearRect(0, 0, canvasWidth, canvasHeight);
                player.render(gc);
                portal.render(gc);
                renderSprites(player, gc);
//                portalBack.render(gc);

                for (Sprite platform : platformList) {
                    platform.render(gc);
                }
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

//    private void handleBack(Event e) {
//        System.out.println("Going back to...");
//
//        MainMenu.goBack();
//        logger.info("Back button has been clicked...");
//    }
    private void handleSettings(Event e) {
        System.out.println("Going to settings...");
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings has been clicked...");
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
