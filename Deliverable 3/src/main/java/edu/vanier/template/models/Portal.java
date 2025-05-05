package edu.vanier.template.models;

import edu.vanier.template.controllers.QuestionEx2FXMLController;
import edu.vanier.template.ui.MainMenu;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * Portal Class
 * @author Eliza
 */
public class Portal extends Sprite{
    private boolean isOpen;
    private String destination;
    private int level;
    public Portal(int x, int y, int sizeX, int sizeY, Image image, String destination) {
        super(x, y, "portal", sizeX, sizeY, image);
        setFitWidth(sizeX);
        setFitHeight(sizeY);
        isOpen = false;
        this.destination = destination;
    }
    public void setLevel(int level) {
        this.level = level;
        setOpacity(1);
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Unlock portal
     */
    public void unlock() {
        isOpen = true;
    }

    /**
     * Go to destination scene when entering the portal
     */
    public void enter() {
        if (isOpen) {
            if (destination.equals(MainMenu.GAME_SCENE)) {
                if (level == 11) {
                    MainMenu.getSceneController().removeScene(GAME_SCENE);
                    AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
                    if (animationTimer != null)
                        animationTimer.stop();
                    MainMenu.switchScene(GAME_SCENE, Family.LEVEL12); // switch to it
                }
                else if (level == 31) {
                    MainMenu.getSceneController().removeScene(GAME_SCENE);
                    AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
                    if (animationTimer != null)
                        animationTimer.stop();
                    MainMenu.switchScene(QUESTIONEX2_SCENE); // switch to it
                }
                else if (level == 12) {
                    MainMenu.getSceneController().removeScene(GAME_SCENE);
                    AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
                    if (animationTimer != null)
                        animationTimer.stop();
                    MainMenu.switchScene(GAME_SCENE, Family.ALKALIMETAL); // switch to it
                }
                else if (level == 32) {
                    MainMenu.getSceneController().removeScene(GAME_SCENE);
                    AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
                    if (animationTimer != null)
                        animationTimer.stop();
                    MainMenu.switchScene(GAME_SCENE, Family.LEVEL31); // switch to it
                }else if (level == 2) {
                    MainMenu.getSceneController().removeScene(GAME_SCENE);
                    AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
                    if (animationTimer != null)
                        animationTimer.stop();
                    MainMenu.switchScene(GAME_SCENE, Family.LEVEL31); // switch to it
                }
            }
            else
                MainMenu.switchScene(destination);
        }
    }

}
