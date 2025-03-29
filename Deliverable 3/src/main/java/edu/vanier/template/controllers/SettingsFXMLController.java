package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import edu.vanier.template.ui.MainMenu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.control.CheckBox;


public class SettingsFXMLController {
    @FXML
    CheckBox checkBoxThemeSong;
    @FXML
    CheckBox checkboxSound;
    @FXML
    CheckBox checkboxNightMode;
    @FXML
    Slider sliderVolume;
    @FXML
    Button btnQuit;
    @FXML
    Button btnBack;
    private final static Logger logger = LoggerFactory.getLogger(SettingsFXMLController.class);
    @FXML
    public void initialize() {
        logger.info("Initializing SettingsController...");
        btnBack.setOnAction(this::loadMainMenu);
        btnQuit.setOnAction(this::quit);
    }
    private void loadMainMenu(Event e) {
        MainMenu.switchScene(MainMenu.MAINMENU_SCENE);
        logger.info("Loaded the primary scene...");
    }
    private void quit(ActionEvent e) {
        logger.info("Exiting application...");
        Platform.exit(); // Properly closes JavaFX application
        System.exit(0); // Ensures JVM exits (optional)
    }
}
