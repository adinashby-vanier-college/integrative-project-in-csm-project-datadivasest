package edu.vanier.template.controllers;

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

import static edu.vanier.template.ui.MainMenu.setButton;
import static edu.vanier.template.ui.MainMenu.setSizeBtn1;


public class SettingsFXMLController {
    @FXML
    CheckBox checkBoxThemeSong;
    @FXML
    CheckBox checkBoxSound;
    @FXML
    CheckBox checkBoxNightMode;
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

        setButton(btnBack, "back", 5 , 5);
        setSizeBtn1(btnBack);
        MainMenu.setUI(borderPane, settingsImgView, "titles/settings.png");
        btnBack.setOnAction(this::handleBack);
        btnQuit.setOnAction(this::quit);

        checkBoxNightMode.setSelected(false);
        checkBoxNightMode.setOnAction(this::toggleNightMode);
        checkBoxThemeSong.setSelected(MainMenu.isMusicPlaying());
        checkBoxThemeSong.setOnAction(this::toggleThemeSong);

        checkBoxThemeSong.setSelected(MainMenu.isSoundPlaying());
        checkBoxSound.setOnAction(this::toggleSound);

        sliderVolume.setValue(MainMenu.isMusicPlaying() ? 30 : 0);
        sliderVolume.valueProperty().addListener((observable, oldValue, newValue) -> {
            double volume = newValue.doubleValue() / 100; // Convert to 0-1 range
            MainMenu.setMusicVolume(volume);
            if (MainMenu.getGameController() != null)
                MainMenu.getGameController().setVolume(volume);
        });
    }
    public void setUI() {
        MainMenu.setBackground(borderPane);
    }
    private void handleBack(Event e) {
        MainMenu.goBack();
        logger.info("Going back...");
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
    private void toggleSound(ActionEvent e) {
        boolean play = checkBoxSound.isSelected();
        MainMenu.toggleSound(play);
        logger.info("In game sound: " + (play ? "Playing" : "Stopped"));
    }
    private void toggleNightMode(ActionEvent e) {
        boolean play = checkBoxNightMode.isSelected();
        MainMenu.toggleNightMode(play);
        logger.info("In game background: " + (play ? "Playing" : "Stopped"));
    }

}