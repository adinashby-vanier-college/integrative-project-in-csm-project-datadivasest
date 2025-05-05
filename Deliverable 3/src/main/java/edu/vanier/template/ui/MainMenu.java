package edu.vanier.template.ui;

import edu.vanier.template.controllers.*;
import edu.vanier.template.helpers.FxUIHelper;
import edu.vanier.template.models.Family;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.logging.Level;
import javafx.scene.media.AudioClip;


public class MainMenu extends Application {
    /**
     * This is a JavaFX project template to be used for creating GUI applications.
     *
     * The JavaFX GUI framework (version: 22.0.2) is linked to this project in the
     * build.gradle file.
     * @link: https://openjfx.io/javadoc/22/
     * @see: /Build Scripts/build.gradle
     * @author frostybee.
     */

//<<<<<<< HEAD
//        // The FXML file name of the primary scene.
//        public static final String MAINAPP_SCENE = "MainApp_layout";
//        // The FXML file name of the secondary scene.
//        public static final String SECONDARY_SCENE = "secondary_layout";
//        // The FXML file name of the dialogue scene.
//        public static final String DIALOGUE_SCENE = "Dialogue_layout";
//        // The FXML file name of the main menu scene
//        public static final String MAINMENU_SCENE = "MainMenu";
//        // The FXML file name of the create account scene
//        public static final String CREATEACCOUNT_SCENE = "CreateAccountPage";
//        // The FXML file name of the login scene
//        public static final String LOGIN_SCENE = "Login";
//        // The FXML file name of the settings scene
//        public static final String SETTINGS_SCENE = "Settings_layout";
//        private final static Logger logger = LoggerFactory.getLogger(edu.vanier.template.ui.MainMenu.class);
//        public static Scene scene;
//        private static SceneController sceneController;
//        public static final String GAME_SCENE = "World_layout";
//=======
    // The FXML file name of the primary scene.
    public static final String MAINAPP_SCENE = "MainApp_layout";
    // The FXML file name of the secondary scene.
    public static final String SECONDARY_SCENE = "secondary_layout";
    // The FXML file name of the dialogue scene.
    public static final String DIALOGUE_SCENE = "Dialogue_layout";
    // The FXML file name of the main menu scene
    public static final String MAINMENU_SCENE = "MainMenu";
    // The FXML file name of the Periodic Table scene
    public static final String PERIODICTABLE_SCENE = "PeriodicTable_layout";
    // The FXML file name of the QuestionEx1 scene
    public static final String QUESTIONEX1_SCENE = "QuestionEx1";
    // The FXML file name of the QuestionEx2 scene
    public static final String QUESTIONEX2_SCENE = "QuestionEx2";
    public static final String QUESTIONEX3_SCENE = "QuestionEx3";
    public static final String QUESTION1BUILDATOM = "Question1BuildAtom";
    // The FXML file name of the create account scene
    public static final String CREATEACCOUNT_SCENE = "CreateAccountPage";
    // The FXML file name of the login scene
    public static final String LOGIN_SCENE = "Login";
    // The FXML file name of the settings scene
    public static final String SETTINGS_SCENE = "Settings_layout";
    //The FXML file name of the backpack scene
    public static final String BACKPACK_SCENE = "BackpackScene";
    public static final String MAP_SCENE = "MapScene";
    public static final String HELP_SCENE = "Help_layout";
    public static final String INSTRUCTIONS_SCENE = "Instruction";
    public static final String INSTRUCTIONS2_SCENE = "Instruction2";

    private static GameFXMLController gameController;
    public static DialogueFXMLController dialogueController;
    public static MainMenuFXMLController mainMenuFXMLController;
    public static PeriodicTableFXMLController periodicTableFXMLController;
    public static QuestionEx1FXMLController questionEx1FXMLController;
    public static QuestionEx2FXMLController questionEx2FXMLController;
    public static QuestionEx3FXMLController questionEx3FXMLController;
    public static MapFXMLController mapFXMLController;
    public static Question1BuildAtomController question1BuildAtomController;
    public static CreateAccountFXMLController createAccountFXMLController;
    public static LoginFXMLController loginFXMLController;
    public static SettingsFXMLController settingsFXMLController;
    public static BackpackFXMLController backpackFXMLController;
    public static HelpFXMLController helpFXMLController;
    public static InstructionFXMLController instructionFXMLController;
    public static Instruction2FXMLController instruction2FXMLController;
    private final static Logger logger = LoggerFactory.getLogger(edu.vanier.template.ui.MainMenu.class);
    public static Scene scene;
    public static SceneController sceneController;
    public static final String GAME_SCENE = "World_layout";
    private static AudioClip backgroundMusic;
    public static Image backgroundImgLight;
    public static Image backgroundImgDark;
    public static Image backgroundImg;
    private static ArrayDeque<String> appFlow;
    private static boolean isSound;


    @Override
    public void stop() {
        // TODO:
        // Here, we need to perform teardown operations such as stopping running
        // animation, etc.
        if (gameController != null) {
            AnimationTimer animationTimer = gameController.getAnimation();
            if (animationTimer != null)
                animationTimer.stop();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/Files/png/BG.png").toString());
            isSound = true;
            logger.info("Bootstrapping the application...");
            // Load the scene of the primary stage.
            mainMenuFXMLController = new MainMenuFXMLController();
            Parent root = FxUIHelper.loadFXML(MAINMENU_SCENE, mainMenuFXMLController);
            scene = new Scene(root, BaseWindow.sceneWidth, BaseWindow.sceneHeight);
            appFlow = new ArrayDeque<>();
            // Add the primary scene to the scene-switching controller.
            sceneController = new SceneController(scene);
            sceneController.addScene(MAINMENU_SCENE, root);
            appFlow.add(MAINMENU_SCENE);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.setTitle("LET'S CHEM");
            // Request putting this appliation's main window on top of other
            // already-opened windows upon launching the app.
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);
            backpackFXMLController = new BackpackFXMLController();
            Parent root1 = FxUIHelper.loadFXML(BACKPACK_SCENE, backpackFXMLController);
            sceneController.addScene(BACKPACK_SCENE, root1);
            initBackgroundMusic();
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            java.util.logging.Logger.getLogger(edu.vanier.template.ui.MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void initBackgroundMusic() {
        if (backgroundMusic == null) {
            backgroundMusic = new AudioClip(getClass().getResource("/audio/background_music.wav").toExternalForm());
            backgroundMusic.setCycleCount(AudioClip.INDEFINITE); // Loop indefinitely
            backgroundMusic.setVolume(0.3); // Default volume
            backgroundMusic.play();
        }
    }

    public static void toggleMusic(boolean play) {
        if (backgroundMusic != null) {
            if (play) {
                backgroundMusic.play();
            } else {
                backgroundMusic.stop();
            }
        }
    }
    public static void toggleSound(boolean play) {
        isSound = play;
        if (gameController != null)
            gameController.toggleMusic(play);
    }
    public static void toggleNightMode(boolean play) {
        if (play) {
            backgroundImgDark = new Image(MainAppFXMLController.class.
                    getResource("/images/backgroundDark.png").toString());
            backgroundImg = backgroundImgDark;
        }
        else {
            backgroundImgLight = new Image(MainAppFXMLController.class.
                    getResource("/images/Files/png/BG.png").toString());
            backgroundImg = backgroundImgLight;
        }
        mainMenuFXMLController.setUI();
        if (periodicTableFXMLController != null)
            periodicTableFXMLController.setUI();
        if (questionEx1FXMLController != null)
            questionEx1FXMLController.setUI();
        if (questionEx2FXMLController != null)
            questionEx2FXMLController.setUI();
        if (questionEx3FXMLController != null)
            questionEx3FXMLController.setUI();
        if (createAccountFXMLController != null)
            createAccountFXMLController.setUI();
        if (loginFXMLController != null)
            loginFXMLController.setUI();
        if (settingsFXMLController != null)
            settingsFXMLController.setUI();
        if (backpackFXMLController != null)
            backpackFXMLController.setUI();
        if (helpFXMLController != null)
            helpFXMLController.setUI();
        if (instructionFXMLController != null)
            instructionFXMLController.setUI();
        if (instruction2FXMLController != null)
            instruction2FXMLController.setUI();
    }

    public static void setMusicVolume(double volume) {
        if (isMusicPlaying()) {
            backgroundMusic.stop();
            backgroundMusic.setVolume(volume);
            backgroundMusic.play();
        }
    }


    public static boolean isMusicPlaying() {
        return backgroundMusic != null && backgroundMusic.isPlaying();
    }
    public static boolean isSoundPlaying() {
        return isSound;
    }

    public static void setGameController(GameFXMLController gameController) {
        MainMenu.gameController = gameController;
    }

    public static GameFXMLController getGameController() {
        return gameController;
    }

    public static SceneController getSceneController() {
        return sceneController;
    }

    public static void setSceneController(SceneController sceneController) {
        MainMenu.sceneController = sceneController;
    }
    public static void goBack() {
        if (appFlow.size() >= 2) {
            appFlow.removeLast();
            sceneController.activateScene(appFlow.getLast());
            logger.info("Going back to" + appFlow.getLast());
        }
    }

    /**
     * Switches between scenes based on the provided FXML file name. This method
     * checks the type of scene (primary or secondary) and either activates an
     * existing scene or loads the specified FXML scene for the first time and
     * adds it to the scene controller.
     *
     * @param fxmlFileName the name of the FXML file that represents the scene
     *                     to switch to.
     */
    public static void switchScene(String fxmlFileName) {
        try {
            if (fxmlFileName.equals(MAINMENU_SCENE)) {
                // No need to register the primary scene as it
                // was already done in the start method.
                sceneController.activateScene(fxmlFileName);

            } else if (fxmlFileName.equals(GAME_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    gameController = new GameFXMLController(Family.LEVEL11);
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, gameController);
                    sceneController.addScene(GAME_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(PERIODICTABLE_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    periodicTableFXMLController = new PeriodicTableFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, periodicTableFXMLController);
                    sceneController.addScene(PERIODICTABLE_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(QUESTIONEX1_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    questionEx1FXMLController = new QuestionEx1FXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, questionEx1FXMLController);
                    sceneController.addScene(QUESTIONEX1_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(QUESTIONEX2_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    questionEx2FXMLController = new QuestionEx2FXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, questionEx2FXMLController);
                    sceneController.addScene(QUESTIONEX2_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);

            }else if (fxmlFileName.equals(QUESTIONEX3_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    questionEx3FXMLController = new QuestionEx3FXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, questionEx3FXMLController);
                    sceneController.addScene(QUESTIONEX3_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            }
            else if(fxmlFileName.equals(QUESTION1BUILDATOM)) {
                if(!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    question1BuildAtomController = new Question1BuildAtomController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, question1BuildAtomController);
                    sceneController.addScene(QUESTION1BUILDATOM, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            }
            else if (fxmlFileName.equals(DIALOGUE_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the frist time.
                    dialogueController = new DialogueFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, dialogueController);
                    sceneController.addScene(DIALOGUE_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(LOGIN_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the fr    ist time.
                    loginFXMLController = new LoginFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, loginFXMLController);
                    sceneController.addScene(LOGIN_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(CREATEACCOUNT_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the frist time.
                    createAccountFXMLController = new CreateAccountFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, createAccountFXMLController);
                    sceneController.addScene(CREATEACCOUNT_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(SETTINGS_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    settingsFXMLController = new SettingsFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, settingsFXMLController);
                    sceneController.addScene(SETTINGS_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if(fxmlFileName.equals(BACKPACK_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                     // specified scene is being loaded for the first time.
                     backpackFXMLController = new BackpackFXMLController();
                     Parent root = FxUIHelper.loadFXML(fxmlFileName, backpackFXMLController);
                    sceneController.addScene(BACKPACK_SCENE, root);
                }
               // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if(fxmlFileName.equals(HELP_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                     // specified scene is being loaded for the first time.
                    helpFXMLController = new HelpFXMLController();
                     Parent root = FxUIHelper.loadFXML(fxmlFileName, helpFXMLController);
                    sceneController.addScene(HELP_SCENE, root);
                }
               // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if(fxmlFileName.equals(INSTRUCTIONS_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                     // specified scene is being loaded for the first time.
                    instructionFXMLController = new InstructionFXMLController();
                     Parent root = FxUIHelper.loadFXML(fxmlFileName, instructionFXMLController);
                    sceneController.addScene(INSTRUCTIONS_SCENE, root);
                }
               // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            }  else if(fxmlFileName.equals(INSTRUCTIONS2_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    instruction2FXMLController = new Instruction2FXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, instruction2FXMLController);
                    sceneController.addScene(INSTRUCTIONS2_SCENE, root);
                }
                sceneController.activateScene(fxmlFileName);
            }  else if(fxmlFileName.equals(MAP_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    mapFXMLController = new MapFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, mapFXMLController);
                    sceneController.addScene(MAP_SCENE, root);
                }
                sceneController.activateScene(fxmlFileName);
            }
            appFlow.add(fxmlFileName);
        }
        catch(IOException e){
            logger.error(e.getMessage(), e);
            java.util.logging.Logger.getLogger(edu.vanier.template.ui.MainMenu.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException();
        }
    }

    public static void switchScene(String fxmlFileName, Family family) {
        try {
            if (fxmlFileName.equals(GAME_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    gameController = new GameFXMLController(family);
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, gameController);
                    sceneController.addScene(GAME_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
                appFlow.add(fxmlFileName);
            }
        }
        catch(IOException e){
            logger.error(e.getMessage(), e);
            java.util.logging.Logger.getLogger(edu.vanier.template.ui.MainMenu.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException();
        }
    }


    //Start of helper methods to set the UI and images of the game
    //@author Tabasuum

    /**
     * fixes the image to a certain image of a title
     * @param borderPane where its placed
     * @param title name of the imageview
     * @param titleName name of image
     * @author Tabasuum
     */
    public static void setUI(BorderPane borderPane, ImageView title, String titleName) {
        //Sets background
        setBackground(borderPane);

        //Adds title
        double size;
        if (titleName.equals("title.png")) {
            size = 0.5;
        } else {
            size = 0.3;
        }

        Image titleImg = new Image(MainAppFXMLController.class.
                getResource("/images/" + titleName).toString());
        title.setImage(titleImg);
        title.setPreserveRatio(true);
        title.setFitWidth(BaseWindow.sceneWidth * size);
        title.setFitHeight(BaseWindow.sceneHeight * size);
    }

    /**
     * sets the pane to a specific image
     * @param borderPane the pane that gets set
     * @param backgroundName name of image
     * @author Tabasuum
     */
    public static void setBackground(BorderPane borderPane, String backgroundName) {
        //Sets background
        Image backgroundImg = getImage(backgroundName);
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
    }

    /**
     * Sets the background of the image of the background to pink one
     * @param borderPane the pane of which is sets the background
     * @author Tabasuum
     */
    public static void setBackground(Pane borderPane) {
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
    }

    /**
     * helper method to get image based on
     * @param imageName name of image
     * @return the image if it was found
     * @author Tabasuum
     */
    public static Image getImage(String imageName) {
        try {
            return new Image(MainAppFXMLController.class.
                    getResource("/images/" + imageName).toString());
        } catch(Error e) {
            System.out.println("Could not find image "+ imageName   );
        } return null;
    }

    /**
     * Makes images proportional to specific size
     * @param image different images
     * @author Tabasuum
     */

    public static void fixSize(double size, ImageView image) {
        image.setPreserveRatio(true);
        image.setFitWidth(BaseWindow.sceneWidth * size);
        image.setFitHeight(BaseWindow.sceneHeight * size);
    }

    /**
     * Makes images proportional to screen size
     * @param imageView different images
     * @author Tabasuum
     */
    public static void setSizeImg(ImageView imageView) {
        imageView.setFitWidth(BaseWindow.sceneWidth * 0.25);
        imageView.setFitHeight(BaseWindow.sceneHeight * 0.25);
    }

    /**
     * Makes buttons proportional to screen size
     * @param button
     * @author Tabasuum
     */
    public static void setSizeBtn(Button button) {
        button.setMinSize(BaseWindow.sceneWidth * 0.20, BaseWindow.sceneHeight * 0.05);
        button.setMaxSize(BaseWindow.sceneWidth * 0.20, BaseWindow.sceneHeight * 0.05);
    }

    /**
     * Makes buttons proportional to screen size
     * @param button
     * @author Tabasuum
     */
    public static void setSizeBtn1(Button button) {
        button.setMinSize(BaseWindow.sceneWidth * 0.01, BaseWindow.sceneHeight * 0.01);
        button.setMaxSize(BaseWindow.sceneWidth * 0.01, BaseWindow.sceneHeight * 0.01);
    }
    /**
     * Sets background to buttons
     * @param button
     * @param string
     * @author Tabasuum
     */
    public static void setButton(Button button, String string, int height, int width) {
        Image image = new Image(MainAppFXMLController.class.
                getResource("/images/buttons/" + string +".png").toExternalForm());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(BaseWindow.sceneWidth * width / 100);  // adjust size as needed
        imageView.setFitHeight(BaseWindow.sceneWidth * height / 100);

        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setText(null);
    }
    public static void main(String[] args) {
        launch(args);
    }

}