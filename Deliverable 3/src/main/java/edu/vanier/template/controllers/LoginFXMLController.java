package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static edu.vanier.template.ui.MainMenu.setButton;
import static edu.vanier.template.ui.MainMenu.setSizeBtn1;

/**
 * @author Sofia Martinez, Eliza Toma
 */
public class LoginFXMLController {
    @FXML
    Button backBtn;
    @FXML
    private TextField usernameLBtn;
    @FXML
    private PasswordField passwordLBtn;
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
    @FXML
    Label messageLbl;

    private final static Logger logger = LoggerFactory.getLogger(MainMenuFXMLController.class);
    private static final String CREDENTIALS_FILE = "credentials.txt";

    /**
     * initializes the scene
     * loads the next scene, depending on which button is clicked
     * @author Sofia Martinez
     */
    @FXML
    public void initialize() {
        logger.info("Initializing LoginController...");

        setButton(backBtn, "back", 5 , 5);
        setSizeBtn1(backBtn);
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
        String user = usernameLBtn.getText();
        String pass = passwordLBtn.getText();
        messageLbl.setText("");
        if (authenticate(user, pass)) {
            messageLbl.setText("Login successful.");
            usernameLBtn.setText("");
            passwordLBtn.setText("");
            MainMenu.switchScene(MainMenu.INSTRUCTIONS_SCENE);
            logger.info("Loaded the instruction scene...");
        } else {
            messageLbl.setText("Invalid credentials.");
        }
    }

    /**
     * directs user to the create account page
     * @param e is the event if the user clicks on the create account button
     */
    private void handleCreateAccountBtn(Event e) {
        usernameLBtn.setText("");
        passwordLBtn.setText("");
        MainMenu.switchScene(MainMenu.CREATEACCOUNT_SCENE);
        logger.info("Loaded the create account scene...");
    }

    /**
     * returns to the main menu page
     * @param e is the action event of pressing the back button
     */
    private void handleBackBtn(Event e) {
        usernameLBtn.setText("");
        passwordLBtn.setText("");
        MainMenu.switchScene(MainMenu.MAINMENU_SCENE);
        logger.info("Loaded the main menu scene...");
    }

    /**
     * Hash password using SHA_256 encryption algorithm
     * @param password
     * @return the hashed passoword
     */
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available");
        }
    }

    /**
     * Save username and password in the database if they do not already exist
     * @param username
     * @param password
     * @return true if registration is successful
     * @throws IOException
     */
    public static boolean registerUser(String username, String password) throws IOException {
        File file = new File(CREDENTIALS_FILE);
        if (!file.exists()) file.createNewFile();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length >= 2 && parts[0].equals(username)) {
                reader.close();
                return false;
            }
        }
        reader.close();
        FileWriter writer = new FileWriter(file, true);
        writer.write(username + ":" + hashPassword(password) + "\n");
        writer.close();
        return true;
    }

    /**
     * Check if the credentials are valid
     * @param username inputted username
     * @param password inputted password
     * @return true if the username-password pair is a valid account
     */
    private static boolean authenticate(String username, String password) {
        File file = new File(CREDENTIALS_FILE);
        if (!file.exists()) return false;
        String hashed = hashPassword(password);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(hashed)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
