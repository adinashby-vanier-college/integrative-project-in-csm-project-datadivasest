package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import edu.vanier.template.ui.MainMenu;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.control.CheckBox;
import javafx.application.Platform;


public class SettingsFXMLController {
    @FXML
    CheckBox checkBoxThemeSong;
    // TODO: In game sound management
    @FXML
    CheckBox checkboxSound;
    // TODO: NightMode
    @FXML
    CheckBox checkboxNightMode;
    @FXML
    Slider sliderVolume;
    @FXML
    Button btnQuit;
    @FXML
    Button btnBack;
    @FXML
    BorderPane borderPane;
    @FXML
    ImageView settingsImgView;
    private final static Logger logger = LoggerFactory.getLogger(SettingsFXMLController.class);
    @FXML
    public void initialize() {
        logger.info("Initializing SettingsController...");
        MainMenu.setUI(borderPane, settingsImgView, "settings.png");
        btnBack.setOnAction(this::loadMainMenu);
        btnQuit.setOnAction(this::quit);

        checkBoxThemeSong.setSelected(MainMenu.isMusicPlaying());
        checkBoxThemeSong.setOnAction(this::toggleThemeSong);

        sliderVolume.setValue(MainMenu.isMusicPlaying() ? 30 : 0);
        sliderVolume.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100; // Convert to 0-1 range
            MainMenu.setMusicVolume(volume);
        });
    }
    private void loadMainMenu(Event e) {
        MainMenu.switchScene(MainMenu.MAINMENU_SCENE);
        logger.info("Loaded the primary scene...");

    }
    private void quit(ActionEvent e) {
        logger.info("Exiting application...");
        Platform.exit();
        System.exit(0);
    }

    private void toggleThemeSong(ActionEvent e) {
        boolean play = checkBoxThemeSong.isSelected();
        MainMenu.toggleMusic(play);
        logger.info("Theme song: " + (play ? "Playing" : "Stopped"));
    }

}