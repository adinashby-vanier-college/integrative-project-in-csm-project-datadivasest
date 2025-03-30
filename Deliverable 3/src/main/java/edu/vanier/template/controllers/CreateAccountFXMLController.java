package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sofia Martinez
 */
public class CreateAccountFXMLController {
    @FXML
    Button backBtn;
    @FXML
    private TextField usernameCAIn;
    @FXML
    private TextField passwordCAIn;
    @FXML
    private TextField confirmPasswordSIIn;
    @FXML
    Button loginCABtn;
    @FXML
    Button createAccCABtn;
    @FXML
    BorderPane borderPane;
    @FXML
    ImageView signUpImgView;
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
        logger.info("Initializing CreateAccountController...");
        loginCABtn.setOnAction(this::handleLoginBtn);
        createAccCABtn.setOnAction(this::handleCreateAccountBtn);
        backBtn.setOnAction(this::handleBackBtn);
        MainMenu.setUI(borderPane, signUpImgView, "signUp.png");
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
        MainMenu.switchScene(MainMenu.LOGIN_SCENE);
        logger.info("Loaded the settings scene...");
    }

    /**
     * directs user to the diologue page
     * @param e is the event if the user clicks on the create account button
     */
    private void handleCreateAccountBtn(Event e) {
        //TODO: call method to check if account is valid
        MainMenu.switchScene(MainMenu.DIALOGUE_SCENE);
        logger.info("Loaded the create account scene...");
    }

    //TODO: create a method to check that there are no existing users with this username and
    // check if the password meets all the requirements

    /**
     * returns to the main menu page
     * @param e is the action event of pressing the back button
     * @author Sofia Martinez
     */
    private void handleBackBtn(Event e) {
        MainMenu.switchScene(MainMenu.MAINMENU_SCENE);
        logger.info("Loaded the main menu scene...");
    }
}
