package edu.vanier.template.models;

import javafx.scene.media.AudioClip;

public enum Family {
    ALKALIMETAL("alkaliMetal", 0, new int[] {1,2,3,4}),
    ALKALINEEARTHMETALS("alkaliEarthMetal", 0, new int[] {1,2,3,4}),
    TRANSITIONMETAL3("transitionMetal3", 0, new int[] {1,2,3,4}),
    TRANSITIONMETAL4("transitionMetal4", 0, new int[] {1,2,3,4}),
    TRANSITIONMETAL5("transitionMetal5", 0, new int[] {1,2,3,4}),
    TRANSITIONMETAL6("transitionMetal6", 0, new int[] {1,2,3,4}),
    HALOGENS("halogens", 0, new int[] {1,2,3,4}),
    NOBLEGAS("nobleGas", 0, new int[] {1,2,3,4}),
    NOFAMILY("noFamily", 0, new int[] {1,2,3,4});

    private final int enemyCount; //spawning invaders
    private final int[] platformSprites; //decides how much of each sprite to choose
    //  For example {1,2,0,0,0,0} means
    //  1 TypeAInvader, 2 TypeBInvader, 0 TypeCInvader, 0 TypeDInvader, 0TypeEInvader, 0 Boss
    private final String name;
    private final String platformImage;
//    private final AudioClip music;
//    private final AudioClip explosionSound;

    Family(String name, int enemyCount, int[] platformSprites) {
        this.name = name;
        this.enemyCount = enemyCount;
        this.platformSprites = platformSprites;
        this.platformImage = "/images/" + name + "/platform.png";
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