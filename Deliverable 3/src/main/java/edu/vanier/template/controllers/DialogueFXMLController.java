package edu.vanier.template.controllers;

import com.sun.tools.javac.Main;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class DialogueFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(DialogueFXMLController.class);

    @FXML private Button btnSkip;
    @FXML private Button btnSettings;
    @FXML private ImageView character1ImgView;
    @FXML private ImageView character2ImgView;
    @FXML private Text characterNameText;
    @FXML private Text dialogueText;
    @FXML private BorderPane borderPane;
    @FXML private ImageView dialogueBoxImgView;

    private List<DialogueLine> dialogueLines = new ArrayList<>();
    private int currentLineIndex = 0;

    @FXML
    public void initialize() {
        logger.info("Initializing Dialogue Controller...");

        // Set background
        MainMenu.setBG(borderPane, "dialogueBG.png");

        // Set character images
        character1ImgView.setImage(MainMenu.getImage("character1.png"));
        character2ImgView.setImage(MainMenu.getImage("character2.png"));
        dialogueBoxImgView.setImage(MainMenu.getImage("dialogueBox.png"));

        // Set button actions
        btnSettings.setOnAction(this::loadSettingsScene);
        btnSkip.setOnAction(this::handleSkip);

        // Initialize sample dialogue (replace with actual dialogue)
        initializeDialogue();
        showCurrentLine();

        // Size adjustments
        MainMenu.fixSize(0.85, character1ImgView);
        MainMenu.fixSize(0.85, character2ImgView);
        MainMenu.fixSize(0.75, dialogueBoxImgView);

        characterNameText.setTranslateY( - 140);
        characterNameText.setTranslateX( 300);
        dialogueText.setTranslateY( - 50 );
        dialogueText.setTranslateX(270);

        // Set focus for key events
        borderPane.setFocusTraversable(true);
        borderPane.requestFocus();
        borderPane.setOnKeyPressed(this::handleKeyPressed);
    }

    private void initializeDialogue() {
        // Add your dialogue lines here
        dialogueLines.add(new DialogueLine("Sayeri", "\"Make a funny voice!\"", "character1.png", null));
        dialogueLines.add(new DialogueLine("Name", "Dialogue: blah blah blah blah blah", null, "character2.png"));
        // Add more lines as needed
    }

    private void showCurrentLine() {
        if (currentLineIndex < dialogueLines.size()) {
            DialogueLine line = dialogueLines.get(currentLineIndex);
            characterNameText.setText(line.characterName());
            dialogueText.setText(line.text());

            // Update character visibility
            character1ImgView.setVisible(line.character1Image() != null);
            character2ImgView.setVisible(line.character2Image() != null);

            // Update character images if needed
            if (line.character1Image() != null) {
                character1ImgView.setImage(MainMenu.getImage(line.character1Image()));
            }
            if (line.character2Image() != null) {
                character2ImgView.setImage(MainMenu.getImage(line.character2Image()));
            }
        } else {
            // Dialogue ended
            MainMenu.switchScene(MainMenu.GAME_SCENE);
        }
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.F) {
            currentLineIndex++;
            showCurrentLine();
            event.consume(); // Prevent further handling
        }
    }

    public void setDialogue(List<DialogueLine> dialogue) {
        this.dialogueLines = new ArrayList<>(dialogue);
        this.currentLineIndex = 0;
        showCurrentLine();
    }

    private void handleSkip(Event e) {
        MainMenu.switchScene(MainMenu.GAME_SCENE);
        logger.info("Skip button clicked");
    }

    private void loadSettingsScene(Event e) {
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings button clicked");
    }

    // Record to hold dialogue line information
    public record DialogueLine(String characterName, String text,
                               String character1Image, String character2Image) {
    }
}