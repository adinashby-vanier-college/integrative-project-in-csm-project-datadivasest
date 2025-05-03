package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.vanier.template.ui.MainMenu.*;

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

    private final Random random = new Random();

    private static final String CSV_PATH = "/database/neutralizationReactions.csv";
    private final List<NeutraReactions> reactions = new ArrayList<>();

    private double correctAnswer;

    private final DecimalFormat format = new DecimalFormat("0.00");



    @FXML
    public void initialize(){
        logger.info("Initializing Question 1 Controller...");
        setBackground(borderPane);
        setButton(btnCheck, "Button_confirm", 5, 5);
        btnCheck.setOnAction(this::handleCheck);
        btnHelp.setOnAction(this::handleHelp);
        getReactionProbs();
        generateNeutralizationProblems();
    }
    private void handleCheck(Event e) {



    }

    public void generateNeutralizationProblems(){
        NeutraReactions neutraReaction = reactions.get(random.nextInt(reactions.size()));

        double volAcid  = Math.round((10 + random.nextDouble() * 40) * 100) / 100.0;
        double concAcid = Math.round((0.05 + random.nextDouble() * 0.20) * 1000) / 1000.0;
        double concBase = Math.round((0.05 + random.nextDouble() * 0.20) * 1000) / 1000.0;

        int acidEq = countProtons(neutraReaction.acidFormula);
        int baseEq = countOH(neutraReaction.baseFormula);
        double molesH = concAcid * volAcid / 1000 * acidEq;
        double volBase = (molesH / (concBase * baseEq)) * 1000;
        correctAnswer = Double.parseDouble(format.format(volBase));

        lblQuestion.setText(String.format(
                "A student has %s mL of %.3f M %s.%n" +
                        "What volume (mL) of %.3f M %s is needed to neutralize it?%n%n" +
                        "Balanced equation:%n%s",
                format.format(volAcid), concAcid, neutraReaction.acidFormula,
                concBase,      neutraReaction.baseFormula,
                neutraReaction.reactionString
        ));
        inputField.clear();
    }

    private int countProtons(String f) {
        Matcher m = Pattern.compile("^H(\\d*)").matcher(f);
        if (m.find() && !m.group(1).isEmpty()) {
            return Integer.parseInt(m.group(1));
        }
        return 1;
    }

    private int countOH(String f) {
        Matcher m = Pattern.compile("\\(OH\\)(\\d+)").matcher(f);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return f.contains("OH") ? 1 : 1;
    }

    public void getReactionProbs(){
        try (InputStream is = getClass().getResourceAsStream(CSV_PATH);
             BufferedReader br = new BufferedReader(new InputStreamReader(is)))
        {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if ("Strong".equalsIgnoreCase(p[4]) &&
                        "Strong".equalsIgnoreCase(p[5]))
                {
                    reactions.add(new NeutraReactions(p[0], p[1], p[6]));
                }
            }

        }
        catch (Exception ex){
            lblQuestion.setText("Error fetching database");
        }

    }

    private static class NeutraReactions {
        final String acidFormula, baseFormula, reactionString;
        NeutraReactions(String a, String b, String r) {
            acidFormula    = a;
            baseFormula    = b;
            reactionString = r;
        }
    }

    private void handleHelp(Event e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Help_layout.fxml"));

        Parent helpRoot = null;
        try {
            helpRoot = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(helpRoot, BaseWindow.sceneWidth * 0.8, BaseWindow.sceneHeight * 0.8));

        helpStage.initOwner(borderPane.getScene().getWindow());
        helpStage.show();
        logger.info("Help button clicked");
    }

}
