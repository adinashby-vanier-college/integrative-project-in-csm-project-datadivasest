package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.Event;
//import jdk.jfr.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Sofia Martinez
 */
public class LoginFXMLController {
    @FXML
    Button backBtn;
    @FXML
    private TextField usernameLBtn;
    @FXML
    private TextField passwordLBtn;
    @FXML
    Button loginLBtn;
    @FXML
    Button createAccLBtn;
    @FXML
    BorderPane borderPane;
    @FXML
    ImageView signInImgView;
    @FXML
    VBox vBox;

    private final static Logger logger = LoggerFactory.getLogger(MainMenuFXMLController.class);

    /**
     * initializes the scene
     * loads the next scene, depending on which button is clicked
     * @author Sofia Martinez
     */
    @FXML
    public void initialize() {
        logger.info("Initializing LoginController...");
        loginLBtn.setOnAction(this::handleLoginBtn);
        createAccLBtn.setOnAction(this::handleCreateAccountBtn);
        backBtn.setOnAction(this::handleBackBtn);
        MainMenu.setUI(borderPane, signInImgView, "signIn.png");
        vBox.setMaxWidth(BaseWindow.sceneWidth * 0.5);
    }


    /**
     * checks login information is correct,
     * if correct, switches to the dialogue scene
     * otherwise shows a message to reenter username/password and empties the textfields
     * @param e is the action event of clicking the login button on the Login Scene
     * @author Sofia Martinez
     */
    private void handleLoginBtn(Event e) {
        MainMenu.switchScene(MainMenu.DIALOGUE_SCENE);
        logger.info("Loaded the dialogue scene...");
    }

    /**
     * directs user to the create account page
     * @param e is the event if the user clicks on the create account button
     */
    private void handleCreateAccountBtn(Event e) {
        MainMenu.switchScene(MainMenu.CREATEACCOUNT_SCENE);
        logger.info("Loaded the create account scene...");
    }

    /**
     * returns to the main menu page
     * @param e is the action event of pressing the back button
     */
    private void handleBackBtn(Event e) {
        MainMenu.switchScene(MainMenu.MAINMENU_SCENE);
        logger.info("Loaded the main menu scene...");
    }
}
