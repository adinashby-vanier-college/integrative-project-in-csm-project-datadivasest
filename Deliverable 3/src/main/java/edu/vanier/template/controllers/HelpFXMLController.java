package edu.vanier.template.controllers;

import edu.vanier.template.helpers.FxUIHelper;
import edu.vanier.template.ui.BaseWindow;
import edu.vanier.template.ui.MainApp;
import edu.vanier.template.ui.MainMenu;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * @author Tabasuum
 *
 * Class for users to access help
 */
public class HelpFXMLController {
    /**
     * Stores Help and resource content
     */
    private static class HelpContent {
        String title;
        String htmlText; //explanation
        String imagePath;
        List<String> examples; //extra examples to understand
        String practiceLink; //link to helpful content

        HelpContent(String title, String htmlText, String imagePath, List<String> examples, String practiceLink) {
            this.title = title;
            this.htmlText = htmlText;
            this.imagePath = imagePath;
            this.examples = examples;
            this.practiceLink = practiceLink;
        }
    }

    //data members of the class
    private final static Logger logger = LoggerFactory.getLogger(HelpFXMLController.class);
    private final Map<Button, HelpContent> helpContents = new HashMap<>();
    @FXML private ImageView contentImage;
    @FXML private TextFlow contentTextFlow;
    @FXML private VBox exampleBox;
    @FXML private Hyperlink link;
    @FXML private Button commandsBtn;
    @FXML private VBox commandsVBox;
    @FXML private Button periodsBtn;
    @FXML private Button electronsBtn;
    @FXML private Button stoichiometryBtn;
    @FXML private Button neutralizationBtn;
    @FXML private Button photosynthesisBtn;
    @FXML private Button settingBtn;
    @FXML private BorderPane borderPaneBg;
    @FXML private BorderPane borderPane;
    @FXML private ImageView leftImgView;
    @FXML private ImageView rightImgView;
    @FXML private ImageView jumpImgView;
    @FXML private Text titleText;
    @FXML private VBox scienceVBox;

    /**
     * initializes the world
     */
    @FXML
    public void initialize() {
        //sets on action
        logger.info("Initializing HelpFXMLController...");
        settingBtn.setOnAction(this::handleSettings);
        commandsBtn.setOnAction(this::handleCommands);

        //sets the borderpane size and image
        borderPane.setPrefHeight(BaseWindow.sceneHeight * 0.8);
        borderPane.setPrefWidth(BaseWindow.sceneWidth *0.8);
        setBackground(borderPaneBg);

        //create every type of help content
        helpContents.put(periodsBtn, new HelpContent( "Periods and Families",
                "What Are Periods?\n\nPeriods are horizontal rows in the periodic table. Each element in a period has the same number of atomic orbitals.\n\n" +
                        "**Periods vs Families**:\n- Periods = Rows → number of shells\n- Families = Columns → number of valence electrons\n\n" +
                        "**How to Use Them**:\nUse the period to find how many energy levels an element has. Use the family to determine its reactivity and valence electrons.",
                "/images/educational/periodic_table.png",
                List.of("Oxygen is in period 2 → has 2 shells.", "Sodium (Na) is in period 3 → 3 energy levels"),
                "https://www.khanacademy.org/science/chemistry/periodic-table"
        ));

        helpContents.put(electronsBtn, new HelpContent( "Electrons and Protons",
                "What Are Electrons?\n\nElectrons are negatively charged particles orbiting the nucleus.\n\n" +
                        "**In Neutral Atoms**:\n• Number of protons = number of electrons\n• Charge is 0\n\n" +
                        "Electrons determine chemical reactivity and bonding.",
                "/images/educational/atom_structure.png",
                List.of("Carbon has 6 protons → 6 electrons", "Chlorine (Cl) has 17 electrons in a neutral atom"),
                "https://www.khanacademy.org/science/chemistry/atomic-structure"
        ));

        helpContents.put(stoichiometryBtn, new HelpContent("Stoichiometry",
                "What is Stoichiometry?\n\nIt's the calculation of reactants and products in chemical reactions.\n\n" +
                        "**Steps**:\n1. Balance the equation\n2. Convert grams to moles\n3. Use mole ratio\n4. Convert moles to grams\n\nUseful in finding limiting reagents and yields.",
                "/images/educational/stoichiometry_chart.png",
                List.of("2H₂ + O₂ → 2H₂O (Mole ratio: 2:1:2)", "Given 10g of H₂, how many grams of H₂O?"),
                "https://www.khanacademy.org/science/chemistry/chemical-reactions-stoichiome"
        ));

        helpContents.put(neutralizationBtn, new HelpContent("Neutralization",
                "What is Neutralization?\n\nA neutralization reaction happens when an acid and a base react to form water and a salt.\n\n" +
                        "**Key Idea**:\nAcid (H⁺) + Base (OH⁻) → H₂O + Salt\n\n" +
                        "This reaction is important in titrations and pH balance.",
                "/images/educational/neutralization.png",
                List.of("HCl + NaOH → NaCl + H₂O", "H₂SO₄ + 2NaOH → Na₂SO₄ + 2H₂O"),
                "https://www.khanacademy.org/science/chemistry/acids-and-bases-topic"
        ));

        helpContents.put(photosynthesisBtn, new HelpContent("Photosynthesis",
                "What is Photosynthesis?\n\nPhotosynthesis is the process by which green plants convert sunlight, carbon dioxide, and water into glucose and oxygen.\n\n" +
                        "**Equation**:\n6CO₂ + 6H₂O + light → C₆H₁₂O₆ + 6O₂\n\nOccurs in chloroplasts using chlorophyll.",
                "/images/educational/photosynthesis.png",
                List.of("Plants absorb CO₂ from air and water from soil", "They release O₂ as a by-product"),
                "https://www.khanacademy.org/science/biology/photosynthesis-in-plants"
        ));


        //changes between science information layout of commands layout
        for (Map.Entry<Button, HelpContent> entry : helpContents.entrySet()) {
            entry.getKey().setOnAction(e -> {
                titleText.setText(entry.getValue().title);
                showHelpContent(entry.getValue());
                scienceVBox.setVisible(true);
                scienceVBox.setManaged(true);
                commandsVBox.setVisible(false);
                commandsVBox.setManaged(false);
            });
        }


        //commands of the game images and setting the sizes
        Image jumpImg = new Image(MainAppFXMLController.class.
                getResource("/images/commands/w.png").toString());
        jumpImgView.setImage(jumpImg);
        setSizeImg(jumpImgView);

        Image leftImg = new Image(MainAppFXMLController.class.
                getResource("/images/commands/a.png").toString());
        leftImgView.setImage(leftImg);
        setSizeImg(leftImgView);

        Image rightImg = new Image(MainAppFXMLController.class.
                getResource("/images/commands/d.png").toString());
        rightImgView.setImage(rightImg);
        setSizeImg(rightImgView);


        //sets size of tall the help resource buttons
        Button[] buttons = {commandsBtn,periodsBtn, electronsBtn,stoichiometryBtn,neutralizationBtn, photosynthesisBtn, settingBtn};
        for (Button btn : buttons) {
            setSizeBtn(btn);
        }
    }
    public void setUI() {
        setBackground(borderPaneBg);
    }

    /**
     * displays content of the category
     * @param content information given
     */
    private void showHelpContent(HelpContent content) {
        if (content.imagePath != null) { //verifies path exists
            Image img = new Image(getClass().getResource(content.imagePath).toString());
            contentImage.setImage(img);
            contentImage.setFitWidth(BaseWindow.sceneWidth * 0.5); //ensure size is not too big for screen
            contentImage.setFitHeight(BaseWindow.sceneHeight * 0.4);
            contentImage.setOpacity(1.0);
        }

        //prepares to change from a different instructions
        contentTextFlow.getChildren().clear();
        exampleBox.getChildren().clear();

        //ensures wraps correctly for visibility
        Text explanation = new Text(content.htmlText + "\n\n");
        explanation.setWrappingWidth(BaseWindow.sceneWidth * 0.65);
        explanation.setFont(new Font(BaseWindow.sceneHeight * 0.025));
        contentTextFlow.getChildren().add(explanation);

        for (String ex : content.examples) {
            Text exText = new Text("• " + ex + "\n");
            exText.setFont(new Font(BaseWindow.sceneHeight * 0.02));
            exampleBox.getChildren().add(exText);
        }

        //add link for extra support for uer
        link.setText("Practice here");
        link.setFont(new Font(BaseWindow.sceneHeight * 0.02));
        link.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(content.practiceLink));
            } catch (Exception ex) {
                logger.error("Failed to open link", ex);
            }
        });
    }

    /**
     * Brings back to settings scene
     * @param e
     */
    public void handleSettings(Event e) {
        System.out.println("Going to settings...");
//        MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        MainMenu.goBack();
        logger.info("Settings has been clicked...");
    }

    /**
     * If command is clicked no science explanation should be visible
     * @param e
     */
    private void handleCommands(Event e) {
        titleText.setText("How to Play");
        commandsVBox.setVisible(true);
        commandsVBox.setManaged(true);
        scienceVBox.setVisible(false);
        scienceVBox.setManaged(false);
    }
}
