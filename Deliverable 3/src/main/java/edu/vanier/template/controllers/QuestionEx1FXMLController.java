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

public class QuestionEx1FXMLController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionEx1FXMLController.class);
    @FXML
    private Button btnBack;
    @FXML private Button btnIncrease;
    @FXML private Button btnDecrease;
    @FXML private Button btnCheck;
    @FXML private Button btnPeriodicTable;
    @FXML private Label lblAnswer;
    @FXML private Label lblQuestion;
    @FXML private BorderPane borderPane;
    @FXML private TextField inputField;

    private int number;

    public static final List<Elements> elements = new ArrayList<>();
    private final Random rnd = new Random();
    public static Elements currentElement;

    public static Elements getCurrentElement() {
        return currentElement;
    }

    public static void setCurrentElement(Elements currentElement) {
        QuestionEx1FXMLController.currentElement = currentElement;
    }

    private String currentQuestion;

    @FXML
    public void initialize() {
        logger.info("Initializing Question 1 Controller...");
        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/Files/png/BG.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

        // Set button actions
        btnBack.setOnAction(this::loadSettingsScene);
        btnCheck.setOnAction(this::handleCheck);
        btnPeriodicTable.setOnAction(this::loadPeriodicTableScene);
//        number = 1;
//        lblAnswer.setText("" + number);
//        btnIncrease.setOnAction(this::handleIncrease);
//        btnDecrease.setOnAction(this::handleDecrease);
        loadElements();
        currentElement = elements.get(rnd.nextInt(elements.size()));
        currentQuestion = "What is the atomic number of the element in group " + currentElement.getGroup() + " "
                + "and in period " + currentElement.getPeriod();
        lblQuestion.setText(currentQuestion);
    }

//    private void handleIncrease(Event e) {
//        number++;
//        lblAnswer.setText("" + number);
//    }
//    private void handleDecrease(Event e) {
//        if (number > 1)
//            number--;
//        lblAnswer.setText("" + number);
//    }




    private void handleCheck(Event e) {
        try {
//        MainMenu.switchScene(MainMenu.QUESTIONEX2_SCENE);
//        MainMenu.switchScene(MainMenu.GAME_SCENE);
            logger.info("Check button clicked");
            number = (int) Double.parseDouble(inputField.getText());
            if (number == currentElement.getAtomicNumber()) {

                lblQuestion.setText(currentQuestion + "\n\n\t\t\t\tThat's correct, good Job!");
                btnCheck.setText("Next");

                btnCheck.setOnAction(this::handleNext);
            } else {
                lblQuestion.setText(currentQuestion + "\n\n\t\t\t\tThat's not quite right.. try again!");
            }
        }
        catch (NumberFormatException ex){
            lblQuestion.setText(currentQuestion + "\n\n\t\t\t\t\tI think you should put numbers...");
        }
    }

    private void handleNext(ActionEvent actionEvent) {
        MainMenu.switchScene(GAME_SCENE);
        logger.info("Skip button clicked");
    }
    private void loadSettingsScene(Event e) {
        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        logger.info("Settings button clicked");
    }
    private void loadPeriodicTableScene(Event e) {
        MainMenu.switchScene(MainMenu.PERIODICTABLE_SCENE);
        logger.info("Periodic Table button clicked");
    }

    private void loadElements(){
        elements.add(new Elements(  1,1, 1, "Hydrogen",     "H") );
        elements.add(new Elements(  2,1,18, "Helium",       "He"));
        elements.add(new Elements(  3,2, 1, "Lithium",      "Li"));
        elements.add(new Elements(  4,2, 2, "Beryllium",    "Be"));
        elements.add(new Elements(  5,2,13, "Boron",        "B"));
        elements.add(new Elements(  6,2,14, "Carbon",       "C"));
        elements.add(new Elements(  7,2,15, "Nitrogen",     "N"));
        elements.add(new Elements(  8,2,16, "Oxygen",       "O"));
        elements.add(new Elements(  9,2,17, "Fluorine",     "F"));
        elements.add(new Elements( 10,2,18, "Neon",         "Ne"));
//
//        elements.add(new Elements( 11,3, 1, "Sodium",       "Na"));
//        elements.add(new Elements( 12,3, 2, "Magnesium",    "Mg"));
//        elements.add(new Elements( 13,3,13, "Aluminium",    "Al"));
//        elements.add(new Elements( 14,3,14, "Silicon",      "Si"));
//        elements.add(new Elements( 15,3,15, "Phosphorus",   "P"));
//        elements.add(new Elements( 16,3,16, "Sulfur",       "S"));
//        elements.add(new Elements( 17,3,17, "Chlorine",     "Cl"));
//        elements.add(new Elements( 18,3,18, "Argon",        "Ar"));
//
//        elements.add(new Elements( 19,4, 1, "Potassium",    "K"));
//        elements.add(new Elements( 20,4, 2, "Calcium",      "Ca"));
//        elements.add(new Elements( 21,4, 3, "Scandium",     "Sc"));
//        elements.add(new Elements( 22,4, 4, "Titanium",     "Ti"));
//        elements.add(new Elements( 23,4, 5, "Vanadium",     "V"));
//        elements.add(new Elements( 24,4, 6, "Chromium",     "Cr"));
//        elements.add(new Elements( 25,4, 7, "Manganese",    "Mn"));
//        elements.add(new Elements( 26,4, 8, "Iron",         "Fe"));
//        elements.add(new Elements( 27,4, 9, "Cobalt",       "Co"));
//        elements.add(new Elements( 28,4,10, "Nickel",       "Ni"));
//        elements.add(new Elements( 29,4,11, "Copper",       "Cu"));
//        elements.add(new Elements( 30,4,12, "Zinc",         "Zn"));
//        elements.add(new Elements( 31,4,13, "Gallium",      "Ga"));
//        elements.add(new Elements( 32,4,14, "Germanium",    "Ge"));
//        elements.add(new Elements( 33,4,15, "Arsenic",      "As"));
//        elements.add(new Elements( 34,4,16, "Selenium",     "Se"));
//        elements.add(new Elements( 35,4,17, "Bromine",      "Br"));
//        elements.add(new Elements( 36,4,18, "Krypton",      "Kr"));
//
//        elements.add(new Elements( 37,5, 1, "Rubidium",     "Rb"));
//        elements.add(new Elements( 38,5, 2, "Strontium",    "Sr"));
//        elements.add(new Elements( 39,5, 3, "Yttrium",      "Y"));
//        elements.add(new Elements( 40,5, 4, "Zirconium",    "Zr"));
//        elements.add(new Elements( 41,5, 5, "Niobium",      "Nb"));
//        elements.add(new Elements( 42,5, 6, "Molybdenum",   "Mo"));
//        elements.add(new Elements( 43,5, 7, "Technetium",   "Tc"));
//        elements.add(new Elements( 44,5, 8, "Ruthenium",    "Ru"));
//        elements.add(new Elements( 45,5, 9, "Rhodium",      "Rh"));
//        elements.add(new Elements( 46,5,10, "Palladium",    "Pd"));
//        elements.add(new Elements( 47,5,11, "Silver",       "Ag"));
//        elements.add(new Elements( 48,5,12, "Cadmium",      "Cd"));
//        elements.add(new Elements( 49,5,13, "Indium",       "In"));
//        elements.add(new Elements( 50,5,14, "Tin",          "Sn"));
//        elements.add(new Elements( 51,5,15, "Antimony",     "Sb"));
//        elements.add(new Elements( 52,5,16, "Tellurium",    "Te"));
//        elements.add(new Elements( 53,5,17, "Iodine",       "I"));
//        elements.add(new Elements( 54,5,18, "Xenon",        "Xe"));
//
//        elements.add(new Elements( 55,6, 1, "Caesium",      "Cs"));
//        elements.add(new Elements( 56,6, 2, "Barium",       "Ba"));
//
//        elements.add(new Elements( 57,6, 3, "Lanthanum",    "La"));
//        elements.add(new Elements( 58,6, 3, "Cerium",       "Ce"));
//        elements.add(new Elements( 59,6, 3, "Praseodymium", "Pr"));
//        elements.add(new Elements( 60,6, 3, "Neodymium",    "Nd"));
//        elements.add(new Elements( 61,6, 3, "Promethium",   "Pm"));
//        elements.add(new Elements( 62,6, 3, "Samarium",     "Sm"));
//        elements.add(new Elements( 63,6, 3, "Europium",     "Eu"));
//        elements.add(new Elements( 64,6, 3, "Gadolinium",   "Gd"));
//        elements.add(new Elements( 65,6, 3, "Terbium",      "Tb"));
//        elements.add(new Elements( 66,6, 3, "Dysprosium",   "Dy"));
//        elements.add(new Elements( 67,6, 3, "Holmium",      "Ho"));
//        elements.add(new Elements( 68,6, 3, "Erbium",       "Er"));
//        elements.add(new Elements( 69,6, 3, "Thulium",      "Tm"));
//        elements.add(new Elements( 70,6, 3, "Ytterbium",    "Yb"));
//        elements.add(new Elements( 71,6, 3, "Lutetium",     "Lu"));
//
//        elements.add(new Elements( 72,6, 4, "Hafnium",      "Hf"));
//        elements.add(new Elements( 73,6, 5, "Tantalum",     "Ta"));
//        elements.add(new Elements( 74,6, 6, "Tungsten",     "W"));
//        elements.add(new Elements( 75,6, 7, "Rhenium",      "Re"));
//        elements.add(new Elements( 76,6, 8, "Osmium",       "Os"));
//        elements.add(new Elements( 77,6, 9, "Iridium",      "Ir"));
//        elements.add(new Elements( 78,6,10, "Platinum",     "Pt"));
//        elements.add(new Elements( 79,6,11, "Gold",         "Au"));
//        elements.add(new Elements( 80,6,12, "Mercury",      "Hg"));
//        elements.add(new Elements( 81,6,13, "Thallium",     "Tl"));
//        elements.add(new Elements( 82,6,14, "Lead",         "Pb"));
//        elements.add(new Elements( 83,6,15, "Bismuth",      "Bi"));
//        elements.add(new Elements( 84,6,16, "Polonium",     "Po"));
//        elements.add(new Elements( 85,6,17, "Astatine",     "At"));
//        elements.add(new Elements( 86,6,18, "Radon",        "Rn"));
//
//        elements.add(new Elements( 87,7, 1, "Francium",     "Fr"));
//        elements.add(new Elements( 88,7, 2, "Radium",       "Ra"));
//        // Actinides (group 3)
//        elements.add(new Elements( 89,7, 3, "Actinium",     "Ac"));
//        elements.add(new Elements( 90,7, 3, "Thorium",      "Th"));
//        elements.add(new Elements( 91,7, 3, "Protactinium","Pa"));
//        elements.add(new Elements( 92,7, 3, "Uranium",      "U"));
//        elements.add(new Elements( 93,7, 3, "Neptunium",    "Np"));
//        elements.add(new Elements( 94,7, 3, "Plutonium",    "Pu"));
//        elements.add(new Elements( 95,7, 3, "Americium",    "Am"));
//        elements.add(new Elements( 96,7, 3, "Curium",       "Cm"));
//        elements.add(new Elements( 97,7, 3, "Berkelium",    "Bk"));
//        elements.add(new Elements( 98,7, 3, "Californium",  "Cf"));
//        elements.add(new Elements( 99,7, 3, "Einsteinium",  "Es"));
//        elements.add(new Elements(100,7, 3, "Fermium",      "Fm"));
//        elements.add(new Elements(101,7, 3, "Mendelevium",  "Md"));
//        elements.add(new Elements(102,7, 3, "Nobelium",     "No"));
//        elements.add(new Elements(103,7, 3, "Lawrencium",   "Lr"));
//
//        elements.add(new Elements(104,7, 4, "Rutherfordium","Rf"));
//        elements.add(new Elements(105,7, 5, "Dubnium",      "Db"));
//        elements.add(new Elements(106,7, 6, "Seaborgium",   "Sg"));
//        elements.add(new Elements(107,7, 7, "Bohrium",      "Bh"));
//        elements.add(new Elements(108,7, 8, "Hassium",      "Hs"));
//        elements.add(new Elements(109,7, 9, "Meitnerium",   "Mt"));
//        elements.add(new Elements(110,7,10, "Darmstadtium", "Ds"));
//        elements.add(new Elements(111,7,11, "Roentgenium",  "Rg"));
//        elements.add(new Elements(112,7,12, "Copernicium",  "Cn"));
//        elements.add(new Elements(113,7,13, "Nihonium",     "Nh"));
//        elements.add(new Elements(114,7,14, "Flerovium",    "Fl"));
//        elements.add(new Elements(115,7,15, "Moscovium",    "Mc"));
//        elements.add(new Elements(116,7,16, "Livermorium",  "Lv"));
//        elements.add(new Elements(117,7,17, "Tennessine",   "Ts"));
//        elements.add(new Elements(118,7,18, "Oganesson",    "Og"));

    }
}
