package edu.vanier.template.models;

import edu.vanier.template.ui.MainMenu;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import static edu.vanier.template.ui.MainMenu.GAME_SCENE;

/**
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
        setOpacity(0);
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void unlock() {
        isOpen = true;
    }
    public void enter() {
        if (isOpen) {
            if (destination.equals(MainMenu.GAME_SCENE)) {
                if (level == 1) {
                    MainMenu.getSceneController().removeScene(GAME_SCENE);
                    AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
                    if (animationTimer != null)
                        animationTimer.stop();
                    MainMenu.switchScene(GAME_SCENE, Family.LEVEL12); // switch to it
                }
                else {
                    MainMenu.getSceneController().removeScene(GAME_SCENE);
                    AnimationTimer animationTimer = MainMenu.getGameController().getAnimation();
                    if (animationTimer != null)
                        animationTimer.stop();
                    MainMenu.switchScene(GAME_SCENE, Family.LEVEL32); // switch to it
                }
            }
            else
                MainMenu.switchScene(destination);
        }
    }

}
