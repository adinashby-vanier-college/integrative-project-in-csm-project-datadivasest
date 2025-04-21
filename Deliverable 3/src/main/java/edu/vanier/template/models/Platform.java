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

    public static void setPlatformsTypeB(Family currentFamily, List<Platform> platformList) {
        Image imgPlatformFloating = new Image(MainAppFXMLController.class.
                getResource("/images/" +currentFamily.getName()+ "/platform.png").toString());

        int width = (int) (BaseWindow.sceneWidth * 0.9);
        int height = (int) (BaseWindow.sceneHeight * 0.9);

        Platform platform = new Platform((int) (width *  0.1),
                    (int) (height * 0.8), "floating", 500, 30, imgPlatformFloating);
        platform.setHeight(150);
        platform.setWidth(400);
        platformList.add(platform);

        Platform platform1 = new Platform((int) (width *  0.5),
                (int) (height * 0.70), "floating", 500, 30, imgPlatformFloating);
        platform1.setHeight(100);
        platform1.setWidth(500);
        platformList.add(platform1);

        Platform platform2 = new Platform((int) (width *  0.3),
                (int) (height * 0.45), "floating", 500, 30, imgPlatformFloating);
        platform2.setHeight(80);
        platform2.setWidth(300);
        platformList.add(platform2);

        Platform platform3 = new Platform((int) (width *  0.8),
                (int) (height * 0.50), "floating", 500, 30, imgPlatformFloating);
        platform3.setHeight(100);
        platform3.setWidth(400);
        platformList.add(platform3);

        Platform platform4 = new Platform((int) (width *  0.5),
                (int) (height * 0.22), "floating", 500, 30, imgPlatformFloating);
        platform4.setHeight(80);
        platform4.setWidth(500);
        platformList.add(platform4);

        Platform platform5 = new Platform((int) (width *  0.95),
                (int) (height * 0.30), "floating", 500, 30, imgPlatformFloating);
        platform5.setHeight(80);
        platform5.setWidth(200);
        platformList.add(platform5);
    }
    public static void setPlatformsTypeC(Family currentFamily, List<Platform> platformList) {

        Image imgPlatformFloating = new Image(MainAppFXMLController.class.
                getResource("/images/" +currentFamily.getName()+ "/platform.png").toString());

        int width = (int) (BaseWindow.sceneWidth * 0.9);
        int height = (int) (BaseWindow.sceneHeight * 0.9);

        Platform platform = new Platform((int) (width *  0.05),
                    (int) (height * 0.9), "floating", 500, 30, imgPlatformFloating);
        platform.setHeight(100);
        platform.setWidth(300);
        platformList.add(platform);

        Platform platform1 = new Platform((int) (width *  0.45),
                (int) (height * 0.35), "floating", 500, 30, imgPlatformFloating);
        platform1.setHeight(100);
        platform1.setWidth(200);
        platformList.add(platform1);

        Platform platform2 = new Platform((int) (width *  0.65),
                (int) (height * 0.30), "floating", 500, 30, imgPlatformFloating);
        platform2.setHeight(100);
        platform2.setWidth(250);
        platformList.add(platform2);

        Platform platform3 = new Platform((int) (width *  0.20),
                (int) (height * 0.5), "floating", 500, 30, imgPlatformFloating);
        platform3.setHeight(100);
        platform3.setWidth(400);
        platformList.add(platform3);

        Platform platform4 = new Platform((int) (width *  0.5),
                (int) (height * 0.85), "floating", 500, 30, imgPlatformFloating);
        platform4.setHeight(125);
        platform4.setWidth(500);
        platformList.add(platform4);

        Platform platform5 = new Platform((int) (width *  0.80),
                (int) (height * 0.75), "floating", 500, 30, imgPlatformFloating);
        platform5.setHeight(100);
        platform5.setWidth(200);
        platformList.add(platform5);

        Platform platform6 = new Platform((int) (width *  0.97),
                (int) (height * 0.65), "floating", 500, 30, imgPlatformFloating);
        platform6.setHeight(100);
        platform6.setWidth(200);
        platformList.add(platform6);

        Platform platform7 = new Platform((int) (width *  0.05),
                (int) (height * 0.35), "floating", 500, 30, imgPlatformFloating);
        platform7.setHeight(100);
        platform7.setWidth(200);
        platformList.add(platform7);
    }

    public static void setPlatformsTypeA(Family currentFamily, List<Platform> platformList) {
        Image imgPlatformFloating = new Image(MainAppFXMLController.class.
                getResource("/images/" +currentFamily.getName()+ "/platform.png").toString());

        int width = (int) (BaseWindow.sceneWidth * 0.9);
        int height = (int) (BaseWindow.sceneHeight * 0.9);

        Platform platform = new Platform((int) (width *  0.0),
                    (int) (height * 0.40), "floating", 500, 30, imgPlatformFloating);
        platform.setHeight(800);
        platform.setWidth(400);
        platformList.add(platform);

        Platform platform1 = new Platform((int) (width *  0.2),
                (int) (height * 0.70), "floating", 500, 30, imgPlatformFloating);
        platform1.setHeight(500);
        platform1.setWidth(600);
        platformList.add(platform1);

        Platform platform2 = new Platform((int) (width *  0.25),
                (int) (height * 0.50), "floating", 500, 30, imgPlatformFloating);
        platform2.setHeight(80);
        platform2.setWidth(400);
        platformList.add(platform2);

        Platform platform3 = new Platform((int) (width *  0.60),
                (int) (height * 0.40), "floating", 500, 30, imgPlatformFloating);
        platform3.setHeight(80);
        platform3.setWidth(400);
        platformList.add(platform3);

        Platform platform4 = new Platform((int) (width *  0.90),
                (int) (height * 0.30), "floating", 500, 30, imgPlatformFloating);
        platform4.setHeight(100);
        platform4.setWidth(300);
        platformList.add(platform4);

        Platform platform5 = new Platform((int) (width *  0.60),
                (int) (height * 0.80), "floating", 500, 30, imgPlatformFloating);
        platform5.setHeight(100);
        platform5.setWidth(400);
        platformList.add(platform5);

        Platform platform6 = new Platform((int) (width *  0.97),
                (int) (height * 0.65), "floating", 500, 30, imgPlatformFloating);
        platform6.setHeight(100);
        platform6.setWidth(300);
        platformList.add(platform6);
    }

}