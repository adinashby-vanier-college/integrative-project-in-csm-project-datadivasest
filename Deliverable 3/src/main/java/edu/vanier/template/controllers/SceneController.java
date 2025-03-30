package edu.vanier.template.controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static edu.vanier.template.ui.MainMenu.GAME_SCENE;
import static edu.vanier.template.ui.MainMenu.scene;

/**
 * The SceneController class is responsible for managing and switching between
 * different scenes in a JavaFX application. It allows adding, removing, and
 * activating scenes based on their names. The main scene can be set to a new
 * root whenever a scene is activated.
 *
 * @author frostybee
 */
public class SceneController {

    private final HashMap<String, Parent> scenesMap = new HashMap<>();
    public final Scene mainScene;
    public static List<String> input;

    /**
     * Constructs a SceneController with a given main scene.
     *
     * @param scene the main scene of the application.
     */
    public SceneController(Scene scene) {
        this.mainScene = scene;
        input = new ArrayList<>();
    }

    /**
     * Adds a new scene to the controller. If the scene already exists, it will
     * not be added again.
     *
     * @param sceneName the name of the scene to add.
     * @param pane the root pane of the scene to add.
     */
    public void addScene(String sceneName, Parent pane) {
        if (!sceneExists(sceneName)) {
            scenesMap.put(sceneName, pane);
        }
    }

    /**
     * Removes a scene from the controller by its name. If the scene does not
     * exist, nothing happens.
     *
     * @param sceneName the name of the scene to remove.
     */
    public void removeScene(String sceneName) {
        if (sceneExists(sceneName)) {
            scenesMap.remove(sceneName);
        }
    }

    /**
     * Activates a scene by changing the root of the main scene to the specified
     * scene's root.
     *
     * @param sceneName the name of the scene to activate.
     * @throws IllegalArgumentException if the scene does not exist.
     */
    public void activateScene(String sceneName) {
        if (sceneExists(sceneName)) {
            mainScene.setRoot(scenesMap.get(sceneName));
            if (sceneName.equals(GAME_SCENE)) {
                mainScene.setOnKeyPressed((KeyEvent e) -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                });

                mainScene.setOnKeyReleased((KeyEvent e) -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });
            }
        } else {
            throw new IllegalArgumentException("Unable to activate the requested scene, as it is not registered.");
        }
    }

    public Scene getScene(String sceneName) {
        if (sceneExists(sceneName)) {
            return scenesMap.get(sceneName).getScene();
        } else {
            throw new IllegalArgumentException("Unable to activate the requested scene, as it is not registered.");
        }
    }

    /**
     * Checks if a scene with the given name exists in the controller.
     *
     * @param sceneName the name of the scene to check.
     * @return true if the scene exists, false otherwise.
     */
    public boolean sceneExists(String sceneName) {
        return scenesMap.containsKey(sceneName);
    }
}
