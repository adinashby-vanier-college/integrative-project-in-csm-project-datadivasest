package edu.vanier.template.models;

import edu.vanier.template.controllers.MainAppFXMLController;
import edu.vanier.template.ui.BaseWindow;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * World-class
 * Initializes location of portals, sprites and platforms based on level and family
 *
 * @author Tabasuum Chowdhury
 */
public class World {
    private String name;
    private String terrain;
    private String chemistryTheme;
    private ArrayList <String> obstacles;
    private ArrayList <String> portals;

    private static Image electronImg = getImage("Electron.png");
    private static Image protonImg= getImage("Proton.png");

    //ensures there is proper white space
    static int widthScreen = (int) (BaseWindow.sceneWidth * 0.9);
    static int heightScreen = (int) (BaseWindow.sceneHeight * 0.9);

    /**
     * given type of the world it sets platform and portal accordingly
     * @param currentFamily the family of the world
     * @param platformList all the lists that will be shown
     * @param portal the portal that leads to the next world
     */
    public static void generateElements(Family currentFamily, List<Platform> platformList, Portal portal) {
        switch (currentFamily.getLayoutType()) {
            case "A" -> {
                setPlatformsTypeA(currentFamily, platformList);
                setPortalA(portal);
            }
            case "B" -> {
                setPlatformsTypeB(currentFamily, platformList);
                setPortalB(portal);
            }
            case "C" -> {
                setPlatformsTypeC(currentFamily, platformList);
                setPortalC(portal);
            }
            case "1.1" -> {
                setPlatformsType11(currentFamily, platformList);
                setPortal1(portal);
            }
            case "1.2" -> {
                setPlatformsType12(currentFamily, platformList);
                setPortal12(portal);
            }
            case "2" -> {
                setPlatformsType2(currentFamily, platformList);
                setPortal2(portal);
            }
            case "3.1" -> {
                setPlatformsType31(currentFamily, platformList);
                setPortal31(portal);
            }
            case "3.2" -> {
                setPlatformsType32(currentFamily, platformList);
                setPortal32(portal);
            }
        }
    }


    //Start of setting platforms
    /**
     * Sets size and location for platforms of type A
     */
    private static void setPlatformsTypeA(Family currentFamily, List<Platform> platformList) {
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
    private static void setPlatformsTypeB(Family currentFamily, List<Platform> platformList) {
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
    private static void setPlatformsTypeC(Family currentFamily, List<Platform> platformList) {
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
    private static void setPlatformsType11(Family currentFamily, List<Platform> platformList) {
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
    private static void setPlatformsType12(Family currentFamily, List<Platform> platformList) {
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
     */
    private static void setPlatformsType2(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.1, 0.85, 100,700);
        addPlatform(currentFamily, platformList, 0.3, 0.80, 200,200);
        addPlatform(currentFamily, platformList, 0.15, 0.50, 80,500);
        addPlatform(currentFamily, platformList, 0.185, 0.45, 80,300);
        addPlatform(currentFamily, platformList, 0.45, 0.70, 100,600);
        addPlatform(currentFamily, platformList, 0.80, 0.70, 100,400);
        addPlatform(currentFamily, platformList, 0.83, 0.75, 300,100);
        addPlatform(currentFamily, platformList, 0.48, 0.75, 300,100);
        addPlatform(currentFamily, platformList, 0.45, 0.30, 100,100);
        addPlatform(currentFamily, platformList, 0.695, 0.30, 100,500);
        addPlatform(currentFamily, platformList, 0.685, 0.77, 200,100);
        addPlatform(currentFamily, platformList, 0.565, 0.70, 200,100);
        addPlatform(currentFamily, platformList, 0.565, 0.40, 100,200);
        addPlatform(currentFamily, platformList, 0.604, 0.70, 300,100);
        addPlatform(currentFamily, platformList, 0.93, 0.70, 200,100);
        addPlatform(currentFamily, platformList, 0.0, 0.20, 80,400);
        addPlatform(currentFamily, platformList, 0.0, 0.60, 80,200);
        addPlatform(currentFamily, platformList, 0.94, 0.20, 80,500);
        addPlatform(currentFamily, platformList, 1, 0.50, 80,300);
    }

    /**
     * Sets size and location for platforms for first part of Level 3
     * Neutralization
     */
    private static void setPlatformsType31(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.0, 0.50, 600,500);
        addPlatform(currentFamily, platformList, 0.8, 0.95, 100,700);
        addAcidPlatform(platformList, 0.5, 300); //reacts differently
    }

    /**
     * Sets size and location for platforms for second part of Level 3
     */
    private static void setPlatformsType32(Family currentFamily, List<Platform> platformList) {
        addPlatform(currentFamily, platformList, 0.00, 0.40, 100,400);
        addPlatform(currentFamily, platformList, 0.40, 0.150, 100,300);
        addPlatform(currentFamily, platformList, 0.90, 0.20, 100,600);
        addPlatform(currentFamily, platformList, 0.10, 0.70, 80,80);
        addPlatform(currentFamily, platformList, 0.50, 0.70, 400,320);
        addPlatform(currentFamily, platformList, 0.62, 0.50, 600,220);
        addPlatform(currentFamily, platformList, 0.70, 0.35, 800,120);
        addPlatform(currentFamily, platformList, 0.25, 0.55, 80,400);
        addPlatform(currentFamily, platformList, 0.80, 0.55, 80,200);
        addPlatform(currentFamily, platformList, 1.07, 0.70, 80,300);
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
                (int) BaseWindow.sceneHeight - 100, "floor",
                500 * widthScreen/2560, 100 * heightScreen/1440,
                imgPlatformFloating);
        platform.setWidth(widthScreen * width);
        platformList.add(platform);
        //TODO add the acid to the top layer so we can see it
    }

    // Sets location of portals
    /**
     * Sets location of portal for level 1.1 to leve 1.2
     * @param portal
     */
    private static void setPortal1(Portal portal) {
        portal.setDestination(GAME_SCENE);
        portal.setPositionX(BaseWindow.sceneWidth - 50);
        portal.setLevel(11); //TODO: change this just because its type 1.1 or type 2 doesnt mean its level 2
        // the same template is reused more than once, to know the level check currently Family == Family.LEVEL11
        // ex method -> if currentlyFamily == Family.LEVEL11 -> portal.setLevel(11);
        portal.setHeight(BaseWindow.sceneHeight);
    }

    /**
     * Sets location of portal for level 1.2 to question
     * @param portal
     */
    private static void setPortal12(Portal portal) {
        portal.setDestination(GAME_SCENE);
        portal.setPositionX(BaseWindow.sceneWidth - 50);
        portal.setLevel(12);
        portal.setHeight(BaseWindow.sceneHeight * 0.9);
    }

    /**
     * Sets location of portal for level 1.1 to leve 1.2
     * @param portal
     */
    private static void setPortal2(Portal portal) {
        portal.setDestination(GAME_SCENE);
        portal.setPositionX(BaseWindow.sceneWidth - 50);
        portal.setLevel(2);
        portal.setHeight(BaseWindow.sceneHeight);
    }
    /**
     * Sets location of portal for level 1.1 to leve 1.2
     * @param portal
     */
    private static void setPortal31(Portal portal) {
        portal.setDestination(GAME_SCENE);
        portal.setPositionX(BaseWindow.sceneWidth - 50);
        portal.setLevel(31);
        portal.setHeight(BaseWindow.sceneHeight);
    }
    /**
     * Sets location of portal for level 1.1 to leve 1.2
     * @param portal
     */
    private static void setPortal32(Portal portal) {
        portal.setDestination(GAME_SCENE);
        portal.setPositionX(BaseWindow.sceneWidth - 50);
        portal.setLevel(32);
        portal.setHeight(BaseWindow.sceneHeight);
    }
    /**
     * Sets location of portal for level 1.1 to leve 1.2
     * @param portal
     */
    private static void setPortalA(Portal portal) {
        portal.setDestination(GAME_SCENE);
        portal.setPositionX(BaseWindow.sceneWidth - 50);
        portal.setLevel(11);
        portal.setHeight(BaseWindow.sceneHeight);
    }

    /**
     * Sets location of portal for level 1.1 to leve 1.2
     * @param portal
     */
    private static void setPortalB(Portal portal) {
        portal.setDestination(GAME_SCENE);
        portal.setPositionX(BaseWindow.sceneWidth - 50);
        portal.setLevel(11);
        portal.setHeight(BaseWindow.sceneHeight);
    }

    /**
     * Sets location of portal for level 1.1 to leve 1.2
     * @param portal
     */
    private static void setPortalC(Portal portal) {
        portal.setDestination(GAME_SCENE);
        portal.setPositionX(BaseWindow.sceneWidth - 50);
        portal.setLevel(11);
        portal.setHeight(BaseWindow.sceneHeight);
    }
    /**
     * Based on the currently family it sets the sprites in their sprite lists accordingly
     * @param currentFamily the current look of the world
     */
    public static void setSprites(Family currentFamily,
                                  Sprite sprite1, Sprite sprite2, Sprite sprite3, Sprite sprite4,
                                  List<Sprite> sprite1List, List<Sprite> sprite2List,
                                  List<Sprite> sprite3List, List<Sprite> sprite4List) {
        switch (currentFamily.getName()) {
            case "alkaliMetal" -> setAlkaliSprites(sprite1, sprite2, sprite3, sprite4, sprite1List  , sprite2List, sprite3List, sprite4List);
            case "alkalineEarthMetal" -> setAlkalineEMSprites(sprite1, sprite2, sprite3, sprite1List  , sprite2List, sprite3List);
            case "transitionMetal3" -> setTM3Sprites(sprite1, sprite2, sprite3, sprite1List  , sprite2List, sprite3List);
            case "transitionMetal4" -> setTM4Sprites(sprite1, sprite2, sprite3, sprite1List  , sprite2List, sprite3List);
            case "transitionMetal5" -> setTM5Sprites(sprite1, sprite2, sprite3, sprite1List  , sprite2List, sprite3List);
            case "transitionMetal6" -> setTM6Sprites(sprite1, sprite2, sprite3, sprite1List  , sprite2List, sprite3List);
            case "halogens" -> setHalogensSprites(sprite1, sprite2, sprite3, sprite1List  , sprite2List, sprite3List);
            case "nobleGas" -> setNobleGasSprites(sprite1, sprite2, sprite3, sprite4, sprite1List  , sprite2List, sprite3List, sprite4List);
            case "noFamily" -> setLevelSprites(sprite1, sprite2, sprite1List, sprite2List);
        }
    }

    // Start of setting the sprites based on their family
    public static void setAlkaliSprites(Sprite sprite1, Sprite sprite2, Sprite sprite3, Sprite sprite4,
                                        List<Sprite> sprite1List, List<Sprite> sprite2List,
                                        List<Sprite> sprite3List, List<Sprite> sprite4List) {
        for (int i = 0; i < 5; i++) { //5 sprites of each king will be created
            initSprite("Hydrogen", getImage("elements/Hydrogen.png"), sprite1, sprite1List);
            initSprite("Lithium", getImage("elements/Lithium.png"), sprite2,  sprite2List);
            initSprite("Sodium", getImage("elements/Sodium.png"), sprite3,  sprite3List);
            initSprite("Potassium", getImage("elements/Potassium.png"), sprite4,  sprite4List);
        }
    }
    public static void setAlkalineEMSprites(Sprite sprite1, Sprite sprite2, Sprite sprite3,
                                            List<Sprite> sprite1List, List<Sprite> sprite2List,
                                            List<Sprite> sprite3List) {
        for (int i = 0; i < 5; i++) { //5 sprites of each king will be created
            initSprite("Beryllium", getImage("elements/Beryllium.png"), sprite1, sprite1List);
            initSprite("Magnesium", getImage("elements/Magnesium.png"), sprite2,  sprite2List);
            initSprite("Calcium", getImage("elements/Calcium.png"), sprite3,  sprite3List);

        }
    }
    public static void setTM3Sprites(Sprite sprite1, Sprite sprite2, Sprite sprite3,
                                     List<Sprite> sprite1List, List<Sprite> sprite2List,
                                     List<Sprite> sprite3List) {
        for (int i = 0; i < 5; i++) { //5 sprites of each king will be created
            initSprite("Boron", getImage("elements/Boron.png"), sprite1, sprite1List);
            initSprite("Aluminium", getImage("elements/Aluminium.png"), sprite2,  sprite2List);
            initSprite("Gallium", getImage("elements/Gallium.png"), sprite3,  sprite3List);
        }
    }
    public static void setTM4Sprites(Sprite sprite1, Sprite sprite2, Sprite sprite3,
                                     List<Sprite> sprite1List, List<Sprite> sprite2List,
                                     List<Sprite> sprite3List) {
        for (int i = 0; i < 5; i++) { //5 sprites of each king will be created
            initSprite("Carbon", getImage("elements/Carbon.png"), sprite1, sprite1List);
            initSprite("Silicon", getImage("elements/Silicon.png"), sprite2,  sprite2List);
            initSprite("Germanium", getImage("elements/Germanium.png"), sprite3,  sprite3List);
        }
    }
    public static void setTM5Sprites(Sprite sprite1, Sprite sprite2, Sprite sprite3,
                                     List<Sprite> sprite1List, List<Sprite> sprite2List,
                                     List<Sprite> sprite3List) {
        for (int i = 0; i < 5; i++) { //5 sprites of each king will be created
            initSprite("Nitrogen", getImage("elements/Nitrogen.png"), sprite1, sprite1List);
            initSprite("Phosphorus", getImage("elements/Phosphorus.png"), sprite2,  sprite2List);
            initSprite("Arsenic", getImage("elements/Arsenic.png"), sprite3,  sprite3List);
        }
    }
    public static void setTM6Sprites(Sprite sprite1, Sprite sprite2, Sprite sprite3,
                                     List<Sprite> sprite1List, List<Sprite> sprite2List,
                                     List<Sprite> sprite3List) {
        for (int i = 0; i < 5; i++) { //5 sprites of each king will be created
            initSprite("Oxygen", getImage("elements/Oxygen.png"), sprite1, sprite1List);
            initSprite("Sulfur", getImage("elements/Sulfur.png"), sprite2,  sprite2List);
            initSprite("Selenium", getImage("elements/Selenium.png"), sprite3,  sprite3List);
        }
    }

    public static void setHalogensSprites(Sprite sprite1, Sprite sprite2, Sprite sprite3,
                                          List<Sprite> sprite1List, List<Sprite> sprite2List,
                                          List<Sprite> sprite3List) {
        for (int i = 0; i < 5; i++) {
            initSprite("Fluorine", getImage("elements/Fluorine.png"), sprite1, sprite1List);
            initSprite("Chlorine", getImage("elements/Chlorine.png"), sprite2,  sprite2List);
            initSprite("Bromine", getImage("elements/Bromine.png"), sprite3,  sprite3List);
        }
    }
    public static void setNobleGasSprites(Sprite sprite1, Sprite sprite2, Sprite sprite3, Sprite sprite4,
                                          List<Sprite> sprite1List, List<Sprite> sprite2List,
                                          List<Sprite> sprite3List, List<Sprite> sprite4List) {
        for (int i = 0; i < 5; i++) { //5 sprites of each king will be created
            initSprite("Helium", getImage("elements/Helium.png"), sprite1, sprite1List);
            initSprite("Neon", getImage("elements/Neon.png"), sprite2,  sprite2List);
            initSprite("Argon", getImage("elements/Argon.png"), sprite3,  sprite3List);
            initSprite("Krypton", getImage("elements/Krypton.png"), sprite4,  sprite4List);
        }
    }
    public static void setLevelSprites(Sprite sprite1, Sprite sprite2, List<Sprite> sprite1List, List<Sprite> sprite2List) {
        for (int i = 0; i < 15; i++) {
            initSprite("electron", electronImg, sprite1, sprite1List);
            initSprite("proton", protonImg, sprite2,  sprite2List);
        }
    }

    /**
     * puts the sprites based on their images proportional to the screen size
     * @param type what kind of sprite is it (electron, proton, helium, etc.)
     * @param image the image that will be shown
     * @param sprite which of the four sprite is it
     * @param spriteList the list that will hold the sprites of this kin din this world
     */
    private static void initSprite(String type, Image image, Sprite sprite, List<Sprite> spriteList) {
        sprite = new Sprite(type, image);
        sprite.setImage(image);

        sprite.setSize(30 * BaseWindow.sceneHeight / 770);

        //position should be set with  the rest of the platforms
        double px = BaseWindow.sceneWidth * 0.9 * Math.random() + 50;
        double py = BaseWindow.sceneHeight * 0.7 * Math.random() + 50;

        sprite.setPosition(px, py);
        spriteList.add(sprite);
    }


}
