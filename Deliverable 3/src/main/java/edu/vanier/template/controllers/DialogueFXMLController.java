package edu.vanier.template.controllers;

import com.sun.tools.javac.Main;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

import static edu.vanier.template.ui.MainMenu.setButton;

/**
 * Displays dialogue of two characters while alternating between their images and texts
 *
 * @author Tabasuum
 */
public class DialogueFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(DialogueFXMLController.class);

    //data members
    @FXML Button btnSkip;
    @FXML Button btnSettings;
    @FXML Button btnPlay;
    @FXML ImageView character1ImgView;
    @FXML ImageView character2ImgView;
    @FXML Text characterNameText;
    @FXML Text dialogueText;
    @FXML BorderPane borderPane;
    @FXML ImageView dialogueBoxImgView;

    private List<DialogueLine> dialogueLines = new ArrayList<>();
    private int currentLineIndex = 0;

    @FXML
    public void initialize() {
        logger.info("Initializing Dialogue Controller...");

        //Set up UI
        MainMenu.setBackground(borderPane, "Files/png/BG.png");
        setButton(btnSettings, "Button_settings", 5 ,5);
        setButton(btnSkip, "Button_right", 5, 5);
        setButton(btnPlay, "next", 5, 5);
        borderPane.setOnKeyPressed(this::handleKeyPressed); //checks if f key was pressed to skip dialogue

        //Set character images
        character1ImgView.setImage(MainMenu.getImage("dialogue/character1.png"));
        character2ImgView.setImage(MainMenu.getImage("dialogue/character2.png"));
        dialogueBoxImgView.setImage(MainMenu.getImage("dialogue/dialogueBox1.png")); //starts with the 1st one

        //Setup buttons actions
        btnSettings.setOnAction(this::loadSettingsScene);
        btnSkip.setOnAction(this::handleSkip);
        btnPlay.setOnAction(this::handlePlay);

        // Initialize sample dialogue (replace with actual dialogue)
        initializeDialogue();
        showCurrentLine();

        // Size adjustments
        MainMenu.fixSize(0.85, character1ImgView);
        MainMenu.fixSize(0.85, character2ImgView);
        character1ImgView.setX(0);
        character1ImgView.setY(BaseWindow.sceneHeight);
        character2ImgView.setY(BaseWindow.sceneHeight);
        character2ImgView.setX(BaseWindow.sceneWidth);

        MainMenu.fixSize(0.50, dialogueBoxImgView);
        dialogueText.setTranslateY( - 50 );
    }

    /**
     * Stores all the dialogue as well as who's image will be shown with the dialogue
     */
    private void initializeDialogue() {
        dialogueLines.add(new DialogueLine("", "A villain (Professor Enthropy) \n kidnaps the user \ninto a different dimension\n and the user must escape.. ", "dialogue/character1.png", null));
        dialogueLines.add(new DialogueLine("", " The user discovers that the villain has \nbooby traps in case he tries to leave.", null, "dialogue/character2.png"));
        dialogueLines.add(new DialogueLine("", "The booby traps are all \n chemistry questions\n (Oh no!).", "dialogue/character1.png", null));
        dialogueLines.add(new DialogueLine("", "But wait, the user just learned this stuff in class,\n can the user remember enough to escape.\n", null, "dialogue/character2.png"));
        dialogueLines.add(new DialogueLine("", "The villain leaves the world\n to get fresh vegetables\n from a local beet farm.\n", "dialogue/character1.png", null));
        dialogueLines.add(new DialogueLine("", "The user takes his chance but first he has \nto get through the obstacles.\n", null, "dialogue/character2.png"));

        // Add more lines as needed
    }


    /**
     * Displays dialogue  one at a time
     */
    private void showCurrentLine() {
        if (currentLineIndex < dialogueLines.size()) { //if all dialogue has been gone through then skips to game
            DialogueLine line = dialogueLines.get(currentLineIndex);
            characterNameText.setText(line.characterName());
            dialogueText.setText(line.text());

            dialogueText.setFont(new Font(40 * BaseWindow.sceneWidth / 2560));
            // Update character visibility
            character1ImgView.setVisible(line.character1Image() != null);
            character2ImgView.setVisible(line.character2Image() != null);

            // Update character images if needed
            if (line.character1Image() != null) {
                character1ImgView.setImage(MainMenu.getImage(line.character1Image()));
                dialogueBoxImgView.setImage(MainMenu.getImage("dialogue/dialogueBox1.png"));
                dialogueText.setTranslateX(-100);
            }
            if (line.character2Image() != null) {
//                dialogueText.setTranslateX();
                character2ImgView.setImage(MainMenu.getImage(line.character2Image()));
                dialogueBoxImgView.setImage(MainMenu.getImage("dialogue/dialogueBox2.png"));
            }
        } else {
            // Dialogue ended
            MainMenu.switchScene(MainMenu.QUESTIONEX1_SCENE);
        }
    }

    /**
     * sets up the dialogue to traverse through it line by line
     * @param dialogue each line that makes up the list of all the dialogues
     */
    public void setDialogue(List<DialogueLine> dialogue) {
        this.dialogueLines = new ArrayList<>(dialogue);
        this.currentLineIndex = 0;
        showCurrentLine();
    }

    /**
     * if the f key is pressed then the dialogue is skipped to the next one
     *
     * @param event key press, must correspond to F
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.F) {
            currentLineIndex++;
            showCurrentLine();
            event.consume(); // Prevent further handling
        }
    }

    //handles buttons

    private void handlePlay(Event e) {
        currentLineIndex++;
        showCurrentLine();
    }
    private void handleSkip(Event e) {
        MainMenu.switchScene(MainMenu.QUESTIONEX1_SCENE);
       // MainMenu.switchScene(MainMenu.QUESTIONEX3_SCENE);
        logger.info("Skip button clicked");
    }

    private void loadSettingsScene(Event e) {
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings button clicked");
    }

    //record to hold dialogue line information
    public record DialogueLine(String characterName, String text,
                               String character1Image, String character2Image) {
    }
}