package edu.vanier.template.models;

import javafx.scene.media.AudioClip;

public enum Family {
    ALKALIMETAL("alkaliMetal", 0, new int[] {1,2,3,4}, "A"),
    ALKALINEEARTHMETALS("alkalineEarthMetal", 0, new int[] {1,2,3,4}, "B"),
    TRANSITIONMETAL3("transitionMetal3", 0, new int[] {1,2,3,4}, "C"),
    TRANSITIONMETAL4("transitionMetal4", 0, new int[] {1,2,3,4}, "A"),
    TRANSITIONMETAL5("transitionMetal5", 0, new int[] {1,2,3,4}, "B"),
    TRANSITIONMETAL6("transitionMetal6", 0, new int[] {1,2,3,4}, "C"),
    HALOGENS("halogens", 0, new int[] {1,2,3,4}, "A"),
    NOBLEGAS("nobleGas", 0, new int[] {1,2,3,4}, "B"),
    LEVEL11("noFamily", 0, new int[] {1,2,3,4}, "1.1"),
    LEVEL12("noFamily", 0, new int[] {1,2,3,4}, "1.2"),
    LEVEL2("noFamily", 0, new int[] {1,2,3,4}, "2"),
    LEVEL31("noFamily", 0, new int[] {1,2,3,4}, "3.1"),
    LEVEL32("noFamily", 0, new int[] {1,2,3,4}, "3.2");


    private final int enemyCount; //spawning invaders
    private final int[] platformSprites; //decides how much of each sprite to choose
    //  For example {1,2,0,0,0,0} means
    //  1 TypeAInvader, 2 TypeBInvader, 0 TypeCInvader, 0 TypeDInvader, 0TypeEInvader, 0 Boss
    private final String name;
    private final String platformImage;
    private final String layoutType;
//    private final AudioClip music;
//    private final AudioClip explosionSound;

    Family(String name, int enemyCount, int[] platformSprites, String layoutType) {
        this.name = name;
        this.enemyCount = enemyCount;
        this.platformSprites = platformSprites;
        this.platformImage = "/images/" + name + "/platform.png";
        this.layoutType = layoutType;
//        this.music = new AudioClip((getClass().getResource
//                ("/audio/"+ name +".mp3").toExternalForm()));
//        music.setVolume(50);
//        this.explosionSound = new AudioClip((getClass().getResource
//                ("/audio/"+ name +"Explosion.mp3").toExternalForm()));
    }
    public int getEnemyCount() {
        return enemyCount;
    }

    public int[] getPlatformSprites() {
        return platformSprites;
    }

    public String getLayoutType() {
        return layoutType;
    }
    public String getName() {
        return name;
    }

//    /**
//     * Allows to get the next level if there is one
//     * @param level
//     * @return
//     */
//    public static Level getNextLevel(Level level) {
//        if (level == LEVEL1)
//            return LEVEL2;
//        else if (level == LEVEL2)
//            return LEVEL3;
//        return null;
//    }
    public String getPlatformImage() {
        return platformImage;
    }
//    public AudioClip getMusic() {
//        return music;
//    }
}