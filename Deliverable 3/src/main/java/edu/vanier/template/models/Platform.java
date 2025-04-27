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
        setFitWidth(sizeX);
        setFitHeight(sizeY);
    }

    /**
     * Sets size and location for platforms of type A
     */
    public static void setPlatformsTypeA(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.0, 0.40, 800,400);
        addPlatform(currentFamily, platformList, 0.2, 0.70, 500,600);
        addPlatform(currentFamily, platformList, 0.25, 0.50, 80,400);
        addPlatform(currentFamily, platformList, 0.60, 0.40, 80,400);
        addPlatform(currentFamily, platformList, 0.90, 0.30, 100,300);
        addPlatform(currentFamily, platformList, 0.60, 0.80, 100,400);
        addPlatform(currentFamily, platformList, 0.97, 0.65, 100,300);
    }

    /**
     * Sets size and location for platforms of type B
     */
    public static void setPlatformsTypeB(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.10, 0.80, 150,400);
        addPlatform(currentFamily, platformList, 0.50, 0.70, 100,500);
        addPlatform(currentFamily, platformList, 0.30, 0.45, 80,300);
        addPlatform(currentFamily, platformList, 0.80, 0.50, 100,400);
        addPlatform(currentFamily, platformList, 0.50, 0.22, 80,500);
        addPlatform(currentFamily, platformList, 0.95, 0.30, 80,200);
    }

    /**
     * Sets size and location for platforms of type C
     */
    public static void setPlatformsTypeC(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.05, 0.90, 100,300);
        addPlatform(currentFamily, platformList, 0.45, 0.35, 100,200);
        addPlatform(currentFamily, platformList, 0.65, 0.30, 100,250);
        addPlatform(currentFamily, platformList, 0.20, 0.50, 100,400);
        addPlatform(currentFamily, platformList, 0.50, 0.85, 125,500);
        addPlatform(currentFamily, platformList, 0.80, 0.75, 100,200);
        addPlatform(currentFamily, platformList, 0.97, 0.65, 100,200);
        addPlatform(currentFamily, platformList, 0.05, 0.35, 100,200);
    }

    /**
     * Sets size and location for platforms for first part of Level 1
     */
    public static void setPlatformsType11(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.0, 0.30, 80,400);
        addPlatform(currentFamily, platformList, 0.20, 0.50, 80,600);
        addPlatform(currentFamily, platformList, 0.60, 0.70, 80,400);
        addPlatform(currentFamily, platformList, 0.68, 0.30, 80,200);
        addPlatform(currentFamily, platformList, 0.35, 0.90, 80,200);
        addPlatform(currentFamily, platformList, 0.90, 0.35, 100,600);
    }

    /**
     * Sets size and location for platforms for second part of Level 1
     */
    public static void setPlatformsType12(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.05, 0.60, 80,400);
        addPlatform(currentFamily, platformList, 0.70, 0.60, 80,500);
        addPlatform(currentFamily, platformList, 0.40, 0.45, 50,400);
        addPlatform(currentFamily, platformList, 0.30, 0.75, 50,300);
        addPlatform(currentFamily, platformList, 0.65, 0.30, 50,200);
        addPlatform(currentFamily, platformList, 0.85, 0.20, 50,400);
        addPlatform(currentFamily, platformList, 0.50, 0.90, 50,300);
    }

    /**
     * Sets size and location for platforms for Level 2
     * TODO: Finish setting the rest of the platforms
     */
    public static void setPlatformsType2(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.0, 0.40, 800,400);
//        addPlatform(currentFamily, platformList, 0.2, 0.70, 500,600);
//        addPlatform(currentFamily, platformList, 0.25, 0.50, 80,400);
//        addPlatform(currentFamily, platformList, 0.60, 0.40, 80,400);
//        addPlatform(currentFamily, platformList, 0.90, 0.30, 100,300);
//        addPlatform(currentFamily, platformList, 0.60, 0.80, 100,400);
//        addPlatform(currentFamily, platformList, 0.97, 0.65, 100,300);
//        addPlatform(currentFamily, platformList, 0.97, 0.65, 100,300);
//        addPlatform(currentFamily, platformList, 0.97, 0.65, 100,300);
    }

    /**
     * Sets size and location for platforms for first part of Level 3
     * Neutralization
     */
    public static void setPlatformsType31(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.0, 0.50, 600,500);
        addPlatform(currentFamily, platformList, 0.8, 0.95, 100,700);
        addAcidPlatform(platformList, 0.5, 300); //reacts differently
    }

    /**
     * Sets size and location for platforms for second part of Level 2
     * TODO: Finish adding the erst of the platforms
     */
    public static void setPlatformsType32(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.0, 0.40, 800,400);
//        addPlatform(currentFamily, platformList, 0.2, 0.70, 500,600);
//        addPlatform(currentFamily, platformList, 0.25, 0.50, 80,400);
//        addPlatform(currentFamily, platformList, 0.60, 0.40, 80,400);
//        addPlatform(currentFamily, platformList, 0.90, 0.30, 100,300);
//        addPlatform(currentFamily, platformList, 0.60, 0.80, 100,400);
//        addPlatform(currentFamily, platformList, 0.97, 0.65, 100,300);
//        addPlatform(currentFamily, platformList, 0.97, 0.65, 100,300);
//        addPlatform(currentFamily, platformList, 0.97, 0.65, 100,300);
    }

    /**
     * Adds to screen all the floating platforms at given location and for given size
     * @param currentFamily gets the picture of the platform
     * @param platformList how it gets added to the scene
     * @param posX position in terms of X
     * @param posY position in terms of Y
     * @param height of the platform
     * @param width of the platform
     */
    private static void addPlatform(Family currentFamily, List<Platform> platformList, double posX, double posY, double height, double width) {
        //Gets image specific to the family
        Image imgPlatformFloating = new Image(MainAppFXMLController.class.
                getResource("/images/" +currentFamily.getName()+ "/platform.png").toString());

        //Sets platform at a specific location and specific size
        Platform platform = new Platform((int) (widthScreen *  posX),
                (int) (heightScreen * posY), "floating",
                500 * widthScreen /2560, 30 * heightScreen / 1440,
                imgPlatformFloating);

        //proportional sizing based on screen size
        platform.setHeight(height * heightScreen / 1440);
        platform.setWidth(width * widthScreen /2560);
        platformList.add(platform);
    }

    /**
     * Adds to screen all the acid platforms at given location and for given size
     * Behaves differently than normal platforms
     * @param platformList how it gets added to the scene
     * @param posX position in terms of X
=     * @param width of the platform
     */
    private static void addAcidPlatform(List<Platform> platformList, double posX, double width) {
        Image imgPlatformFloating = new Image(MainAppFXMLController.class.
                getResource("/images/acidPlatform.png").toString());

        Platform platform = new Platform((int) (widthScreen *  posX),
                (int)BaseWindow.sceneHeight - 100, "floor",
                500 * widthScreen/2560, 100 * heightScreen/1440,
                imgPlatformFloating);
        platform.setWidth(widthScreen * width);
        platformList.add(platform);
        //TODO add the acid to the top layer so we can see it
    }
}