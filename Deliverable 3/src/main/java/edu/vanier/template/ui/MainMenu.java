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
    // The FXML file name of the create account scene
    public static final String CREATEACCOUNT_SCENE = "CreateAccountPage";
    // The FXML file name of the login scene
    public static final String LOGIN_SCENE = "Login";
    // The FXML file name of the settings scene
    public static final String SETTINGS_SCENE = "Settings_layout";
    //The FXML file name of the backpack scene
    public static final String BACKPACK_SCENE = "BackpackScene";
    public static final String HELP_SCENE = "Help_layout";
    public static final String INSTRUCTIONS_SCENE = "Instruction";
    private final static Logger logger = LoggerFactory.getLogger(edu.vanier.template.ui.MainMenu.class);
    public static Scene scene;
    public static SceneController sceneController;
    public static final String GAME_SCENE = "World_layout";
    private static AudioClip backgroundMusic;
    public static Image backgroundImg = new Image(MainAppFXMLController.class.
            getResource("/images/Files/png/BG.png").toString());
    private static GameFXMLController gameController;


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
            logger.info("Bootstrapping the application...");
            // Load the scene of the primary stage.
            Parent root = FxUIHelper.loadFXML(MAINMENU_SCENE, new MainMenuFXMLController());
            scene = new Scene(root, BaseWindow.sceneWidth, BaseWindow.sceneHeight);
            // Add the primary scene to the scene-switching controller.
            sceneController = new SceneController(scene);
            sceneController.addScene(MAINMENU_SCENE, root);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.setTitle("LET'S CHEM");
            // Request putting this appliation's main window on top of other
            // already-opened windows upon launching the app.
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);
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
                    PeriodicTableFXMLController controller = new PeriodicTableFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(PERIODICTABLE_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(QUESTIONEX1_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    QuestionEx1FXMLController controller = new QuestionEx1FXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(QUESTIONEX1_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(QUESTIONEX2_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    QuestionEx2FXMLController controller = new QuestionEx2FXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(QUESTIONEX2_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            }
            else if (fxmlFileName.equals(DIALOGUE_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the frist time.
                    DialogueFXMLController controller = new DialogueFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(DIALOGUE_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(LOGIN_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the frist time.
                    LoginFXMLController controller = new LoginFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(LOGIN_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(CREATEACCOUNT_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the frist time.
                    CreateAccountFXMLController controller = new CreateAccountFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(CREATEACCOUNT_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(SETTINGS_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the first time.
                    SettingsFXMLController controller = new SettingsFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(SETTINGS_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if(fxmlFileName.equals(BACKPACK_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                     // specified scene is being loaded for the first time.
                     BackpackFXMLController controller = new BackpackFXMLController();
                     Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(BACKPACK_SCENE, root);

                }
               // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if(fxmlFileName.equals(HELP_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                     // specified scene is being loaded for the first time.
                    HelpFXMLController controller = new HelpFXMLController();
                     Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(HELP_SCENE, root);
                }
               // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if(fxmlFileName.equals(INSTRUCTIONS_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                     // specified scene is being loaded for the first time.
                    InstructionFXMLController controller = new InstructionFXMLController();
                     Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(INSTRUCTIONS_SCENE, root);
                }
               // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            }
                //TODO: You can register or activate additional scenes here,
                //      based on the logic used to add the secondary scene (as shown above).

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
            }
        }
        catch(IOException e){
            logger.error(e.getMessage(), e);
            java.util.logging.Logger.getLogger(edu.vanier.template.ui.MainMenu.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException();
        }
    }
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
     * @param borderPane
     */
    public static void setBackground(Pane borderPane) {
        Image backgroundImg = new Image(MainAppFXMLController.class.
                getResource("/images/Files/png/BG.png").toString());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true);

        borderPane.setBackground(new Background(new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
    }
    public static Image getImage(String imageName) {
        return new Image(MainAppFXMLController.class.
                getResource("/images/" + imageName).toString());
    }

    public static void fixSize(double size, ImageView image) {
        image.setPreserveRatio(true);
        image.setFitWidth(BaseWindow.sceneWidth * size);
        image.setFitHeight(BaseWindow.sceneHeight * size);
    }

    /**
     * Makes images proportional to screen size
     * @param imageView different images
     */
    public static void setSizeImg(ImageView imageView) {
        imageView.setFitWidth(BaseWindow.sceneWidth * 0.25);
        imageView.setFitHeight(BaseWindow.sceneHeight * 0.25);
    }

    /**
     * Makes buttons proportional to screen size
     * @param button
     */
    public static void setSizeBtn(Button button) {
        button.setMinSize(BaseWindow.sceneWidth * 0.20, BaseWindow.sceneHeight * 0.05);
        button.setMaxSize(BaseWindow.sceneWidth * 0.20, BaseWindow.sceneHeight * 0.05);
    }

    /**
     * Makes buttons proportional to screen size
     * @param button
     */
    public static void setSizeBtn1(Button button) {
        button.setMinSize(BaseWindow.sceneWidth * 0.01, BaseWindow.sceneHeight * 0.01);
        button.setMaxSize(BaseWindow.sceneWidth * 0.01, BaseWindow.sceneHeight * 0.01);
    }
    /**
     * Sets background to buttons
     * @param button
     * @param string
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