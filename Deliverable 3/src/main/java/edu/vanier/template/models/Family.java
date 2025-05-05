package edu.vanier.template.models;

import javafx.scene.media.AudioClip;

/**
 * author Tabasuum
 * defines the different worlds seen in the game (families and levels)
 */
public enum Family {
    ALKALIMETAL("alkaliMetal",   "2"),
    ALKALINEEARTHMETALS("alkalineEarthMetal",  "B"),
    TRANSITIONMETAL3("transitionMetal3",  "C"),
    TRANSITIONMETAL4("transitionMetal4",   "A"),
    TRANSITIONMETAL5("transitionMetal5",   "2"),
    TRANSITIONMETAL6("transitionMetal6",  "3.2"),
    HALOGENS("halogens", "A"),
    NOBLEGAS("nobleGas",    "B"),
    LEVEL11("noFamily",  "2"),
    LEVEL12("noFamily",  "1.2"),
    LEVEL2("noFamily",    "1.1"), //not being used
    LEVEL31("noFamily",  "3.1"),
    LEVEL32("noFamily",  "3.2"); //not being used


    private final String name;
    private final String platformImage;
    private final String layoutType;

    /**
     * constructor for a class
     * @param name what the level is called
     * @param layoutType what the platforms and portal layout will look like
     */
    Family(String name, String layoutType) {
        this.name = name;
        this.platformImage = "/images/" + name + "/platform.png";
        this.layoutType = layoutType;
    }
    public String getLayoutType() {
        return layoutType;
    }
    public String getName() {
        return name;
    }
    public String getPlatformImage() {
        return platformImage;
    }
}