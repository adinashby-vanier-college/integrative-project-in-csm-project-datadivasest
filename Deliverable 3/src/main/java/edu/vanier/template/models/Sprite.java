package edu.vanier.spaceshooter.entities;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Base class for all sprites
 */
public class Sprite extends ImageView {
    private double health = 100;
    private boolean dead = false;
    private final String type;
    private double velocityX;
    private double velocityY;

    public Sprite(int x, int y, String type) {
        setPosition(x,y);
        this.type = type;
        setSize(100);
    }

//    public Projectile shootBullet() {
//        Projectile bullet = null;
//        return bullet;
//    }
    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y) {
        setX(Math.abs(x) % 1000);
        setY(Math.abs(y) % 1000);
    }

    public void setSize(double size) {
        setFitWidth(size);
        setFitHeight(size);
    }
    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    //@TODO Fix out of bounds
    public void moveLeft() {
        setPosition(Math.abs(getX() - 15) % (1000), getY());
    }

    public void moveRight() {
        setPosition(Math.abs(getX() + 15)% 1000, getY());    }

    public void moveUp() {
        setPosition(getX(), getY() - 5);
    }

    public void moveDown() {
        setPosition(getX(), getY() + 5);
    }

    public boolean isDead() {
        return dead;
    }

    public String getType() {
        return type;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
    public double getHealth() {
        return health;
    }
    public void setHealth(double damage) {
        health -= damage;
        if (health == 0)
            setDead(true);
    }
}