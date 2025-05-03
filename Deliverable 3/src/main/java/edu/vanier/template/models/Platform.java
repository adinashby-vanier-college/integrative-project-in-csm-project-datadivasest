package edu.vanier.template.models;

import edu.vanier.template.controllers.MainAppFXMLController;
import edu.vanier.template.ui.BaseWindow;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * @author Tabasuum
 * Base class for all platforms
 */
public class Platform extends Sprite {
    private int sizeX;
    private int sizeY;
    private final String type;

    //ensures there is proper white space
    static int widthScreen = (int) (BaseWindow.sceneWidth * 0.9);
    static int heightScreen = (int) (BaseWindow.sceneHeight * 0.9);
    public Platform(int x, int y, String type, int sizeX, int sizeY, Image img) {
        super(x, y, "platform", sizeX, sizeY, img);
        this.type = type;
        setHeight(sizeY);
        setWidth(sizeX);
        setFitWidth(sizeX);
        setFitHeight(sizeY);
    }

}