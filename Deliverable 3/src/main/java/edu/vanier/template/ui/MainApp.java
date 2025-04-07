package edu.vanier.template.ui;

import edu.vanier.template.controllers.*;
import edu.vanier.template.helpers.FxUIHelper;
import java.io.IOException;
import java.util.logging.Level;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 *
 * The JavaFX GUI framework (version: 22.0.2) is linked to this project in the
 * build.gradle file.
 * @link: https://openjfx.io/javadoc/22/
 * @see: /Build Scripts/build.gradle
 * @author frostybee.
 */
public class MainApp extends Application {

    // The FXML file name of the primary scene.
    public static final String MAINAPP_SCENE = "MainApp_layout";
    // The FXML file name of the secondary scene.
    public static final String SECONDARY_SCENE = "secondary_layout";
    // The FXML file name of the dialogue scene.
    public static final String DIALOGUE_SCENE = "DialoguePage";
    public static final String GAME_SCENE = "World_layout";
    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);
    private static Scene scene;
    private static SceneController sceneController;

    @Override
    public void stop() {
        // TODO: 
        // Here, we need to perform teardown operations such as stopping running 
        // animation, etc.
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Bootstrapping the application...");
            // Load the scene of the primary stage.
            Parent root = FxUIHelper.loadFXML(MAINAPP_SCENE, new MainAppFXMLController());
            scene = new Scene(root, 640, 480);
            // Add the primary scene to the scene-switching controller.
            sceneController = new SceneController(scene);
            sceneController.addScene(MAINAPP_SCENE, root);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.setTitle("An FX Project Template!");

            // Request putting this appliation's main window on top of other 
            // already-opened windows upon launching the app.
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Switches between scenes based on the provided FXML file name. This method
     * checks the type of scene (primary or secondary) and either activates an
     * existing scene or loads the specified FXML scene for the first time and
     * adds it to the scene controller.
     *
     * @param fxmlFileName the name of the FXML file that represents the scene
     * to switch to.
     */
    public static void switchScene(String fxmlFileName) {
        try {
            if (fxmlFileName.equals(MAINAPP_SCENE)) {
                // No need to register the primary scene as it 
                // was already done in the start method.                
                sceneController.activateScene(fxmlFileName);

            } else if (fxmlFileName.equals(DIALOGUE_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the 
                    // specified scene is being loaded for the frist time.
                    DialogueFXMLController controller = new DialogueFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(DIALOGUE_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            } else if (fxmlFileName.equals(GAME_SCENE)) {
                if (!sceneController.sceneExists(fxmlFileName)) {
                    // Instantiate the corresponding FXML controller if the
                    // specified scene is being loaded for the frist time.
                    GameFXMLController controller = new GameFXMLController();
                    Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                    sceneController.addScene(GAME_SCENE, root);
                }
                // The scene has been previously added, we activate it.
                sceneController.activateScene(fxmlFileName);
            }
            //TODO: You can register or activate additional scenes here, 
            //      based on the logic used to add the secondary scene (as shown above).            
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private static void transitionIn() {
        //-- 1) Ensure the current scene is black
        scene.setFill(Color.BLACK);

        //-- 2) Scale in the pane
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(500), scene.getRoot());
        scaleIn.setFromX(100);
        scaleIn.setFromY(100);
        scaleIn.setToX(1);
        scaleIn.setToY(1);
        scaleIn.setInterpolator(Interpolator.EASE_OUT);

        //-- 3) Fade in the pane from black
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), scene.getRoot());
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setInterpolator(Interpolator.EASE_OUT);

        //-- 4) Add both transitions to a parallel transition and play
        ParallelTransition parallelIn = new ParallelTransition();
        parallelIn.getChildren().addAll(scaleIn, fadeIn);
        parallelIn.play();
    }

    /**
     * Handles the transition out between scenes
     * Scales and fades out to black
     *
     * @return the transition out to be played when scenes changed
     */
    private static Transition transitionOut() {
        //-- 1) Ensure the current scene is black
        scene.setFill(Color.BLACK);

        //-- 2) Scale in the pane
        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(250), scene.getRoot());
        scaleOut.setFromX(1);
        scaleOut.setFromY(1);
        scaleOut.setToX(100);
        scaleOut.setToY(100);
        scaleOut.setInterpolator(Interpolator.EASE_OUT);

        //-- 3) Fade in the pane from black
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), scene.getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setInterpolator(Interpolator.EASE_OUT);

        //-- 4) Add both transitions to a parallel transition and play
        ParallelTransition parallelOut = new ParallelTransition();
        parallelOut.getChildren().addAll(scaleOut, fadeOut);
        return parallelOut;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
