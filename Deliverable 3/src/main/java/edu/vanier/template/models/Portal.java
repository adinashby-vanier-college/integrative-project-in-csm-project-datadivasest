package edu.vanier.template.models;

import edu.vanier.template.ui.MainMenu;
import javafx.scene.image.Image;

public class Portal extends Sprite{
    private boolean isOpen;
    private String destination;
    public Portal(int x, int y, int sizeX, int sizeY, Image image) {
        super(x, y, "portal", sizeX, sizeY, image);
        setFitWidth(sizeX);
        setFitHeight(sizeY);
        isOpen = false;
    }
    public void unlock() {
        isOpen = true;
    }
    public void enter() {
        if (isOpen) {
            System.out.println("Going back to...");
            MainMenu.switchScene(MainMenu.SETTINGS_SCENE);
        }
    }
}
