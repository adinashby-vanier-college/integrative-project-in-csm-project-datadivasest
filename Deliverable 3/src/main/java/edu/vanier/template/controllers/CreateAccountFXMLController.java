package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * @author Sofia Martinez
 */
public class CreateAccountFXMLController {
    @FXML
    Button backBtn;
    @FXML
    private TextField usernameCAIn;
    @FXML
    private PasswordField passwordCAIn;
    @FXML
    private TextField confirmPasswordSIIn;
    @FXML
    Button createAccCABtn;
    @FXML
    BorderPane borderPane;
    @FXML
    ImageView signUpImgView;
    @FXML
    VBox vBox;
    @FXML
    Label messageLbl;
    private final static Logger logger = LoggerFactory.getLogger(MainMenuFXMLController.class);

    /**
     * initializes the scene
     * loads the next scene, depending on which button is clicked
     * @author Sofia Martinez
     */
    @FXML
    public void initialize() {
        logger.info("Initializing CreateAccountController...");
        setButton(backBtn, "back", 5 , 5);
        setSizeBtn1(backBtn);
        setBackground(borderPane);
        MainMenu.setUI(borderPane, signUpImgView, "titles/signUp.png");
        createAccCABtn.setOnAction(this::handleCreateAccountBtn);
        backBtn.setOnAction(this::handleBackBtn);
        //MainMenu.setUI(borderPane, signUpImgView, "signUp.png");
        vBox.setMaxWidth(BaseWindow.sceneWidth * 0.5);
    }

    public void setUI() {
        setBackground(borderPane);
    }
    /**
     * directs user to the diologue page
     * @param e is the event if the user clicks on the create account button
     */
    private void handleCreateAccountBtn(Event e) {
        messageLbl.setText("");
        String user = usernameCAIn.getText();
        String pass = passwordCAIn.getText();
        if (user.isEmpty() || pass.isEmpty()) {
            messageLbl.setText("Fill both fields.");
        } else {
            try {
                if (LoginFXMLController.registerUser(user, pass)) {
                    messageLbl.setText("Registration successful.");
                    usernameCAIn.setText("");
                    passwordCAIn.setText("");
                    MainMenu.switchScene(MainMenu.LOGIN_SCENE);
                    logger.info("Loaded the create account scene...");
                } else {
                    messageLbl.setText("User already exists.");
                }
            } catch (IOException ex) {
                messageLbl.setText("Error saving user.");
            }
        }
    }

    /**
     * returns to the main menu page
     * @param e is the action event of pressing the back button
     * @author Sofia Martinez
     */
    private void handleBackBtn(Event e) {
        usernameCAIn.setText("");
        passwordCAIn.setText("");
        MainMenu.goBack();
        logger.info("Loaded the main menu scene...");
    }
}
