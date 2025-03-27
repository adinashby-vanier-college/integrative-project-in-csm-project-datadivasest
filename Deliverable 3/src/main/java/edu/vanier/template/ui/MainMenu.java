package edu.vanier.template.ui;

import edu.vanier.template.controllers.*;
import edu.vanier.template.helpers.FxUIHelper;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.Level;


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

        // The FXML file name of the primary scene.
        public static final String MAINAPP_SCENE = "MainApp_layout";
        // The FXML file name of the secondary scene.
        public static final String SECONDARY_SCENE = "secondary_layout";
        // The FXML file name of the dialogue scene.
        public static final String DIALOGUE_SCENE = "Dialogue_layout";
        // The FXML file name of the main menu scene
        public static final String MAINMENU_SCENE = "MainMenu";
        // The FXML file name of the create account scene
        public static final String CREATEACCOUNT_SCENE = "CreateAccountPage";
        // The FXML file name of the login scene
        public static final String LOGIN_SCENE = "Login";
        // The FXML file name of the settings scene
        public static final String SETTINGS_SCENE = "Settings_layout";
        private final static Logger logger = LoggerFactory.getLogger(edu.vanier.template.ui.MainMenu.class);
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
                Parent root = FxUIHelper.loadFXML(MAINMENU_SCENE, new MainMenuFXMLController());
                scene = new Scene(root, 640, 480);
                // Add the primary scene to the scene-switching controller.
                sceneController = new SceneController(scene);
                sceneController.addScene(MAINMENU_SCENE, root);
                primaryStage.setScene(scene);
                primaryStage.sizeToScene();
                // Request putting this appliation's main window on top of other
                // already-opened windows upon launching the app.
                primaryStage.setAlwaysOnTop(true);
                primaryStage.show();
                primaryStage.setAlwaysOnTop(false);
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
                java.util.logging.Logger.getLogger(edu.vanier.template.ui.MainMenu.class.getName()).log(Level.SEVERE, null, ex);
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

                } else if (fxmlFileName.equals(DIALOGUE_SCENE)) {
                    if (!sceneController.sceneExists(fxmlFileName)) {
                        // Instantiate the corresponding FXML controller if the
                        // specified scene is being loaded for the frist time.
                        DialogueFXMLController controller = new DialogueFXMLController();
                        Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                        sceneController.addScene(DIALOGUE_SCENE, root);
                    }
                    // The scene has been previously added, we active it.
                    sceneController.activateScene(fxmlFileName);
                } else if (fxmlFileName.equals(LOGIN_SCENE)) {
                    if (!sceneController.sceneExists(fxmlFileName)) {
                        // Instantiate the corresponding FXML controller if the
                        // specified scene is being loaded for the frist time.
                        LoginFXMLController controller = new LoginFXMLController();
                        Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                        sceneController.addScene(DIALOGUE_SCENE, root);
                    }
                    // The scene has been previously added, we active it.
                    sceneController.activateScene(fxmlFileName);
                } else if (fxmlFileName.equals(CREATEACCOUNT_SCENE)) {
                    if (!sceneController.sceneExists(fxmlFileName)) {
                        // Instantiate the corresponding FXML controller if the
                        // specified scene is being loaded for the frist time.
                        CreateAccountFXMLController controller = new CreateAccountFXMLController();
                        Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                        sceneController.addScene(CREATEACCOUNT_SCENE, root);
                    }
                    // The scene has been previously added, we active it.
                    sceneController.activateScene(fxmlFileName);
                } else if (fxmlFileName.equals(SETTINGS_SCENE)) {
                    if (!sceneController.sceneExists(fxmlFileName)) {
                        // Instantiate the corresponding FXML controller if the
                        // specified scene is being loaded for the frist time.
                        SettingsFXMLController controller = new SettingsFXMLController();
                        Parent root = FxUIHelper.loadFXML(fxmlFileName, controller);
                        sceneController.addScene(SETTINGS_SCENE, root);
                    }
                    // The scene has been previously added, we active it.
                    sceneController.activateScene(fxmlFileName);
                    //TODO: You can register or activate additional scenes here,
                    //      based on the logic used to add the secondary scene (as shown above).
                }
            }
            catch(IOException e){
                logger.error(e.getMessage(), e);
                java.util.logging.Logger.getLogger(edu.vanier.template.ui.MainMenu.class.getName()).log(Level.SEVERE, null, e);
                throw new RuntimeException();
            }
        }

    public static void main(String[] args) {}

}
