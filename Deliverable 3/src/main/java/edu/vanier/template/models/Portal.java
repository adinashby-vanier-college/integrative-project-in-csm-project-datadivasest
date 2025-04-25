package edu.vanier.template.models;

import edu.vanier.template.ui.MainMenu;
import javafx.scene.image.Image;

public class Portal extends Sprite{
    private boolean isOpen;
    private final String destination;
    public Portal(int x, int y, int sizeX, int sizeY, Image image, String destination) {
        super(x, y, "portal", sizeX, sizeY, image);
        setFitWidth(sizeX);
        setFitHeight(sizeY);
        isOpen = false;
        this.destination = destination;
    }
    public void unlock() {
        isOpen = true;
    }
    public void enter() {
        if (isOpen) {
            MainMenu.switchScene(destination);
        }
    }

}
