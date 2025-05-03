package edu.vanier.template.controllers;

import edu.vanier.template.models.Elements;
import edu.vanier.template.models.Family;
import edu.vanier.template.ui.MainMenu;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.vanier.template.ui.MainMenu.GAME_SCENE;
import static edu.vanier.template.ui.MainMenu.sceneController;

public class QuestionEx3FXMLController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionEx3FXMLController.class);
    @FXML
    private Button btnBack;

    @FXML private Button btnCheck;
    @FXML private Button btnHelp;
    @FXML private Label lblAnswer;
    @FXML private Label lblQuestion;
    @FXML private BorderPane borderPane;
    @FXML private TextField inputField;


    @FXML
    public void initialize() {}
    private void handleCheck(Event e) {}

}
