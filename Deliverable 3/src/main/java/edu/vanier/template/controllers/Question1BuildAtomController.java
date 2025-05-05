package edu.vanier.template.controllers;

import edu.vanier.template.helpers.BackpackHelper;
import edu.vanier.template.helpers.FxUIHelper;
import edu.vanier.template.models.Elements;
import edu.vanier.template.models.Sprite;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static edu.vanier.template.ui.MainMenu.*;

public class Question1BuildAtomController {
    private final static Logger logger = LoggerFactory.getLogger(Question1BuildAtomController.class);
    @FXML
    Button btnMap;
    @FXML
    Button btnHelp;
    @FXML
    Button backpackBtn;
    @FXML
    Button btnSettings;
    @FXML
    Button btnBack;

    @FXML
    Text questionTxt;
    @FXML
    ImageView atomImg;
    @FXML
    Button checkBtn;
    @FXML
    private BorderPane borderPane;
    @FXML
    Pane backpackPane;

    public Stage backpackStage;
    public BackpackFXMLController backpack;
    public MapFXMLController mapFXMLController;
    public QuestionEx1FXMLController questionEx1FXMLController;
    private Elements currentElement;
    GameFXMLController gameFXMLController;

    @FXML
    public void initialize() {
        logger.info("Initializing the building atom controller");

        setBackground(borderPane);
        setButton(btnMap, "Button_132",3 ,3);
        setButton(btnHelp, "Button_help",3 ,3);
        setButton(backpackBtn, "backpack",3 ,3);
        setButton(btnSettings, "Button_settings", 3 ,3);
        setButton(checkBtn, "next", 3, 3);

        try {
            backpackFXMLController.setUpGridPane();
            backpackStage = new Stage();
            backpackStage.setTitle("Backpack");
            Scene backpackScene = new Scene(FxUIHelper.loadFXML(BACKPACK_SCENE, backpackFXMLController), 460, 574);
            backpackFXMLController.setElectronLabel(backpackFXMLController.getElectronLabel());
            backpackFXMLController.itemLabels.put("electron", backpackFXMLController.getElectronLabel());
            backpackStage.setScene(backpackScene);
            backpackStage.setResizable(false);
            backpackStage.setAlwaysOnTop(true);
        }
        catch (IOException err) {
            System.out.println(err.getMessage());
        }

        //Buttons
        btnBack.setOnAction(this::handleBack);
        btnBack.setLayoutX(BaseWindow.sceneWidth * 0.1);
        btnSettings.setOnAction(this::handleSettings);
        btnMap.setOnAction(this::handleMapButton);
        btnHelp.setOnAction(this::handleHelpButton);
        backpackBtn.setOnAction(this::handleBackpackButton);
        checkBtn.setOnAction(this::handleCheck);

        //setting up the question
        questionTxt.setText("Build the atom: " + QuestionEx1FXMLController.currentElement.getName() + " with the correct number of protons and electrons" + " \nHint: Use your the " +
                "elements you collected in your backpack");

        System.out.println(QuestionEx1FXMLController.currentElement.getName());

        //setting up the image
        Image baseAtomImage = switch (QuestionEx1FXMLController.currentElement.getPeriod()) {
            case 1 -> new Image(getClass().getResource("/images/atoms/shell1.png").toExternalForm());
            case 2 -> new Image(getClass().getResource("/images/atoms/shell2.png").toExternalForm());
            case 3 -> new Image(getClass().getResource("/images/atoms/shell3.png").toExternalForm());
            case 4 -> new Image(getClass().getResource("/images/atoms/shell4.png").toExternalForm());
            case 5 -> new Image(getClass().getResource("/images/atoms/shell5.png").toExternalForm());
            case 6 -> new Image(getClass().getResource("/images/atoms/shell6.png").toExternalForm());
            case 7 -> new Image(getClass().getResource("/images/atoms/shell7.png").toExternalForm());
            default -> null;
        };

        if(baseAtomImage != null) {
            atomImg.setImage(baseAtomImage);
            //atomImg = new ImageView(baseAtomImage);
            System.out.println("showing image: " + baseAtomImage.getUrl());
        }

        else {
            //this is the default image
            Image image = new Image(getClass().getResource("/images/atoms/shell3.png").toExternalForm());
            atomImg = new ImageView(image);
            atomImg.setImage(image);
        }
        atomImg.setFitWidth(500);
        atomImg.setFitHeight(495);

        borderPane.setOnDragOver(event -> {
            if (event.getGestureSource() != borderPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();

            System.out.println();
        });

        borderPane.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                String spriteType = dragboard.getString().toLowerCase();

                Image sprite = null;

                switch (spriteType) {
                    case "electron":
                        sprite = new Image(getClass().getResource("/images/Electron.png").toExternalForm());
                        gameFXMLController.sprite1List.add(new Sprite(spriteType, sprite));
                        break;
                    case "proton":
                        sprite = new Image(getClass().getResource("/images/Proton.png").toExternalForm());
                        gameFXMLController.sprite2List.add(new Sprite(spriteType, sprite));
                        break;
                    case "sodium":
                        sprite = new Image(getClass().getResource("/images/Sodium.png").toExternalForm());
                        gameFXMLController.sprite3List.add(new Sprite(spriteType, sprite));
                        break;
                    case "hydrogen":
                        sprite = new Image(getClass().getResource("/images/Hydrogen.png").toExternalForm());
                        gameFXMLController.sprite4List.add(new Sprite(spriteType, sprite));
                        break;
                    case "oxygen":
                        sprite = new Image(getClass().getResource("/images/Oxygen.png").toExternalForm());
                        gameFXMLController.sprite4List.add(new Sprite(spriteType, sprite));
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
                        case "electron" -> gameFXMLController.sprite1List.add(reuploadedSprite);
                        case "proton" -> gameFXMLController.sprite2List.add(reuploadedSprite);
                        case "sodium" -> gameFXMLController.sprite3List.add(reuploadedSprite);
                        case "hydrogen" -> gameFXMLController.sprite4List.add(reuploadedSprite);
                        case "oxygen" -> gameFXMLController.sprite1List.add(reuploadedSprite);
                    }
                    borderPane.getChildren().add(reuploadedSprite);
                }
                success = true;
            }

            event.setDropCompleted(success);
            System.out.println("Dropped: " + dragboard.getString());
            event.consume();
        });
    }
    private void handleCheck(Event e) {
        System.out.println("Going to instructions 2...");
        MainMenu.switchScene(INSTRUCTIONS2_SCENE);
        logger.info("Check has been clicked...");
    }

    private void handleBack(Event e) {
        System.out.println("Going back to...");
        MainMenu.goBack();
        logger.info("Back button has been clicked...");
    }
    private void handleSettings(Event e) {
        System.out.println("Going to settings...");
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings has been clicked...");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Help_layout.fxml"));
        Parent helpRoot = null;

        try {
            helpRoot = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(helpRoot, BaseWindow.sceneWidth * 0.6, BaseWindow.sceneHeight * 0.6));

        helpStage.initOwner(borderPane.getScene().getWindow());
        helpStage.show();
    }
//

}
