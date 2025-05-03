package edu.vanier.template.controllers;

import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuestionEx2FXMLController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionEx2FXMLController.class);
    @FXML
    private Button btnBack;
    @FXML private Button btnCheck;
    @FXML private Button btnHelp;
    @FXML private Label lblQuestion;

    private Label lblReview;
    @FXML private TextField txtLeft;
    @FXML private Label lblLeft;
    @FXML private TextField txtRight;
    @FXML private Label lblRight;
    @FXML private BorderPane borderPane;
    private StoichProblem currentProblem;
    private Map<String, Double> molarMassMap = new HashMap<>();
    private static final Pattern ELEMENT_PATTERN = Pattern.compile("([A-Z][a-z]*)(\\d*)");




    @FXML
    public void initialize() {
        loadMolarMasses();
        logger.info("Initializing Question 2 Controller...");
        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/Files/png/BG.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        loadCsvProblems();
        pickAndShowProblem();
        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

        // Set button actions
        btnBack.setOnAction(this::handleBack);
        btnCheck.setOnAction(this::handleCheck);
        btnHelp.setOnAction(this::handleHelp);

    }

    private void loadMolarMasses() {
        try (InputStream is = getClass().getResourceAsStream("/database/periodicTable.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//            String header = br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] f = line.split(",");
                String symbol = f[2].trim();               // column "Symbol"
                double mass = Double.parseDouble(f[3].trim()); // column "AtomicMass"
                molarMassMap.put(symbol, mass);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void pickAndShowProblem() {
        if (problems.isEmpty()) {
            lblQuestion.setText("No problems found!");
            return;
        }

        currentProblem = problems.get(rnd.nextInt(problems.size()));

        String eq = String.format(
                "%d%s + %d%s \u2192 %d%s",
                currentProblem.aCoeff, currentProblem.elementX,
                currentProblem.bCoeff, currentProblem.elementY,
                currentProblem.cCoeff, currentProblem.productFormula
        );

        String prompt = String.format(
                "You need %d grams of %s!\nEnter the right grams of %s and %s to make it.",
                currentProblem.productMass,
                currentProblem.productFormula,
                currentProblem.elementX,
                currentProblem.elementY
        );

        lblQuestion.setText(eq + "\n\n" + prompt);
        lblLeft.setText(currentProblem.elementX);
        lblRight.setText(currentProblem.elementY);
        txtLeft.clear();
        txtRight.clear();
    }

    private double computeMolarMass(String formula) {
        Matcher matcher = ELEMENT_PATTERN.matcher(formula);
        double total = 0;
        while (matcher.find()) {
            String element = matcher.group(1);
            String countStr = matcher.group(2);
            int count = countStr.isEmpty() ? 1 : Integer.parseInt(countStr);
            Double atomicMass = molarMassMap.get(element);
            if (atomicMass == null) {
                throw new IllegalArgumentException("Unknown element: " + element);
            }
            total += atomicMass * count;
        }
        return total;
    }


    private static class StoichProblem {
        int aCoeff, bCoeff, cCoeff;
        int productMass;
        String elementX, elementY, productFormula;
        StoichProblem(int a, int b, int c, int mass, String x, String y, String prod) {
            this.aCoeff = a;
            this.bCoeff = b;
            this.cCoeff = c;
            this.productMass = mass;
            this.elementX = x;
            this.elementY = y;
            this.productFormula = prod;
        }
    }

    private final Random rnd = new Random();
    private List<StoichProblem> problems = new ArrayList<>();

    private void handleCheck(Event e) {
//        MainMenu.switchScene(MainMenu.GAME_SCENE);
        logger.info("Check button clicked");
        try {
            // 1) Parse inputs
            String inputX = txtLeft.getText().trim();
            String inputY = txtRight.getText().trim();
            double gramsX = Double.parseDouble(inputX);
            double gramsY = Double.parseDouble(inputY);

            // 2) Compute molar masses
            double mmX    = computeMolarMass(currentProblem.elementX);
            double mmY    = computeMolarMass(currentProblem.elementY);
            double mmProd = computeMolarMass(currentProblem.productFormula);

            // 3) Target product moles
            double targetProdMoles = currentProblem.productMass / mmProd;

            // 4) Required grams of each reactant
            double reqGramsX = targetProdMoles * currentProblem.aCoeff * mmX;
            double reqGramsY = targetProdMoles * currentProblem.bCoeff * mmY;

            // 5) Check within tolerance (±1g)
            double tol = 1.0;
            if (Math.abs(gramsX - reqGramsX) <= tol
                    && Math.abs(gramsY - reqGramsY) <= tol) {
                lblQuestion.setText("Yes! Congrats! That is the correct answer :)");
                // … proceed to next problem …
            } else {
                pickAndShowProblem();
                lblQuestion.setText("Mmm at least one of these is wrong... why don't you try again?");
            }
        } catch (NumberFormatException ex) {

            lblQuestion.setText("I think you should put numbers...");
        }

    }

    private void handleBack(Event e) {
        MainMenu.switchScene(MainMenu.GAME_SCENE);
        logger.info("Back button clicked");

    }

    private void loadCsvProblems() {
        try (InputStream is = getClass().getResourceAsStream("/database/stoichiometry.csv")) {
            if (is == null) {
                System.err.println("CSV file not found!");
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] formula = line.split(",");

//                String productsCell = formula[2];
//                productsCell = productsCell.replace(";", " + ");

                String[] reactants = formula[2].split(";");
                String[] reactantCoeffs = formula[3].split(";");
                if (reactants.length < 2 || reactantCoeffs.length < 2) continue;

                String rawProducts = formula[4];
                String[] productCoeffs = formula[5].split(";");
                if (productCoeffs.length < 1) continue;

                String product = rawProducts.replace(";", " + ");

                int a = Integer.parseInt(reactantCoeffs[0].trim());
                int b = Integer.parseInt(reactantCoeffs[1].trim());
                int c = Integer.parseInt(productCoeffs[0].trim());
                int productMass = 50 + new Random().nextInt(100);

                String elX = reactants[0].trim();
                String elY = reactants[1].trim();

                problems.add(new StoichProblem(a, b, c, productMass, elX, elY, product));
            }

            System.out.println("Loaded " + problems.size() + " real-element problems.");
        } catch (Exception ex) {
            ex.printStackTrace();
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
