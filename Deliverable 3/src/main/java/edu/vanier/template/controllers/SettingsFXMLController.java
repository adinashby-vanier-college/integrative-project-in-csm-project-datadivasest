package edu.vanier.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.*;

public class SettingsFXMLController {
    @FXML
    Checkbox checkBoxThemeSong;
    @FXML
    Checkbox checkboxSound;
    @FXML
    Checkbox checkboxNightMode;
    @FXML
    Slider sliderVolume;
    @FXML
    Button btnQuit;
    @FXML
    Button btnBack;
    @FXML
    public void initialize() {
        // TODO logger
        // logger.info("Initializing Dialogue Controller...");

    }
}
