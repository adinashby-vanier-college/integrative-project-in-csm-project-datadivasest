package edu.vanier.template.models;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Base class for all platforms
 */
public class Platform extends Sprite {
    private int sizeX;
    private int sizeY;
    private final String type;
    
    public Platform(int x, int y, String type, int sizeX, int sizeY, Image img) {
        super(x, y, "platform", sizeX, sizeY, img);
        this.type = type;
        setFitWidth(sizeX);
        setFitHeight(sizeY);
    }
//    public void setPosition(double x, double y) {
//        setX(Math.abs(x) % 1000);
//        setY(Math.abs(y) % 1000);
//    }
}