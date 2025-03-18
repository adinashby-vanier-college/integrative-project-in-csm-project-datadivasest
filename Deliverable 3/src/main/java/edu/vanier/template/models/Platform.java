package edu.vanier.spaceshooter.entities;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Base class for all sprites
 */
public class Platform extends ImageView {
    private final String family;
    private int sizeX;
    private int sizeY;
    private final String type;
    
    public Platform(int x, int y, String family, String type, int sizeY, int sizeX) {
        setPosition(x,y);
        this.family = family;
        this.type = type;
        setFitWidth(sizeX);
        setFitHeight(sizeY);
    }
    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }
    public void setPosition(double x, double y) {
        setX(Math.abs(x) % 1000);
        setY(Math.abs(y) % 1000);
    }
}