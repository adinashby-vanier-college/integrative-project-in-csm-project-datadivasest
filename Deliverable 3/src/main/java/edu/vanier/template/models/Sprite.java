package edu.vanier.template.models;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
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
    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;
    private double x1,x2,y1,y2;

    public Sprite(int x, int y, String type, int sizeX, int sizeY, Image image) {
        setPosition(x,y);

        this.image = image;
        velocityX = 0;
        velocityY = 0;
        width = sizeX;
        height = sizeY;

        this.type = type;
        setImage(image);
//        setSize(100);
    }

    public  Sprite(String type,Image image) {
        this.image = image;

        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
        this.type = type;
//        setSize(10);
    }

//    public Projectile shootBullet() {
//        Projectile bullet = null;
//        return bullet;
//    }

//    public void setPosition(double x, double y) {
//        setX(Math.abs(x) % 1000);
//        setY(Math.abs(y) % 1000);
//    }

    public void setSize(double size) {
        setWidth(size);
        setHeight(size);
    }
    public void setWidth(double size) {
        setFitWidth(size);
        width = size;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }

    public void setHeight(double size) {
        height = size;
        setFitHeight(size);
    }
    public void setBounds(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    /**
     * Check if position is in bounds
     *
     * @param x
     * @param y
     * @return true if the new position is in bounds, false otherwise
     */
    private boolean inBounds(double x, double y) {
        return (x >= x1) && (y >= y1) && (x <= x2) && (y <= y2);
    }



//    public void setVelocity(double x, double y) {
//        velocityX = x;
//        velocityY = y;
//    }
//
//    public void addVelocity(double x, double y) {
//        velocityX += x;
//        velocityY += y;
//    }
//
//    //@TODO Fix out of bounds
//    public void moveLeft() {
//        setPosition(Math.abs(getX() - 15) % (1000), getY());
//    }
//
//    public void moveRight() {
//        setPosition(Math.abs(getX() + 15)% 1000, getY());    }
//
//    public void moveUp() {
//        setPosition(getX(), getY() - 5);
//    }
//
//    public void moveDown() {
//        setPosition(getX(), getY() + 5);
//    }

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

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
        setX(x);
        setY(y);
        System.out.println("Position has been set to: (" + x +"," + y + ")");
    }
    public void setPositionX(double x) {
        positionX = x;
    }
    public void setPositionY(double Y) {
        positionY = Y;
    }


    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY, width, height);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }
    public boolean intersectsAt(double x, double y, Platform platform) {
        Rectangle2D futureBoundary = new Rectangle2D(x, y, this.getWidth(), this.getHeight());
        return futureBoundary.intersects(platform.getBoundary());
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public String toString() {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

    public double getPositionY() {
        return positionY;
    }
    public double getPositionX() {
        return positionX;
    }
}