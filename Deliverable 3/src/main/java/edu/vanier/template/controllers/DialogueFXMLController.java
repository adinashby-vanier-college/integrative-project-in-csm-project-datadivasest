package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller for the dialogue
 *
 * @author Eliza Toma
 */
public class DialogueFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(DialogueFXMLController.class);
    @FXML
    Button btnNext;
    @FXML
    Button btnSettings;
    @FXML
    Button btnSkip;

    @FXML
    public void initialize() {
        logger.info("Initializing Dialogue Controller...");

    }
}
