package edu.vanier.template.controllers;

import com.sun.tools.javac.Main;
import edu.vanier.template.models.Family;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * FXML controller class for the primary stage scene.
 * The application starts from the main menu
 * @author Sofia Martinez
 */
public class MainMenuFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(MainMenuFXMLController.class);

    SceneController sceneController;
    @FXML
    Button loginBtn;
    @FXML
    Button createAccountBtn;
    @FXML
    Button settingsBtn;
    @FXML
    BorderPane borderPane;
    @FXML
    ImageView titleImgView;

    /**
     * initializes the scene
     * loads the next scene, depending on which button is clicked
     * @author Sofia Martinez
     */
    //
    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");
        loginBtn.setOnAction(this::loadLoginScene);
        createAccountBtn.setOnAction(this::loadCreateAccountScene);
        settingsBtn.setOnAction(this::loadSettingsScene);
        MainMenu.setUI(borderPane, titleImgView, "titles/title.png");
        borderPane.setOnKeyPressed(this::handleKeyPressed);

//        setButton(loginBtn, "login");
//        setButton(createAccountBtn, "createAcc");
//        setButton(settingsBtn, "settings");
    }

    /**
     * if the f key is pressed then the brings to next scene
     *
     * @param event key press, must correspond to F
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.F) {
          loadLoginScene(event);
          event.consume(); // Prevent further handling

        }
    }


    /**
     * switches to the login scene/page
     * @param e is the action of clicking the login button, directing the user to the login page
     */
    private void loadLoginScene(Event e) {
        MainMenu.switchScene(LOGIN_SCENE);
        logger.info("Loaded the login scene...");
    }

    /**
     * switches to the create account scene/page
     * @param e is the action of clicking the create account button, directing the user to the create account page
     */
    private void loadCreateAccountScene(Event e) {
        MainMenu.switchScene(MainMenu.CREATEACCOUNT_SCENE);
        logger.info("Loaded the create account scene...");
    }

    /**
     * switches to the settings scene/page
     * @param e is the action of clicking the settings button, directing the user to the settings page
     */
    private void loadSettingsScene(Event e) {
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Loaded the settings scene...");
    }
    public void setUI() {
        MainMenu.setUI(borderPane, titleImgView, "titles/title.png");
    }

    //TODO:
    // do I need to make an exit button,
    // can't they just use the regular x button on the windows to stop the application
}
