package edu.vanier.template.ui;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * @author Eliza Toma
 * BaseWindow
 */
public abstract class BaseWindow {
    // Screen size
    private static final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    // Calculate the scene size as a percentage of the screen size
    public static double sceneWidth = screenBounds.getWidth() * 0.9;

    public static double sceneHeight = screenBounds.getHeight() * 0.9;
}
