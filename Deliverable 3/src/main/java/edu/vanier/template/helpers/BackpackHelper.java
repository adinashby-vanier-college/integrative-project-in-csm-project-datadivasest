package edu.vanier.template.helpers;

import com.sun.tools.javac.Main;
import edu.vanier.template.controllers.BackpackFXMLController;
import edu.vanier.template.ui.MainMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BackpackHelper {
    private static BackpackFXMLController currentBackpack;

    public static BackpackFXMLController getCurrentBackpack() {
        return MainMenu.backpackFXMLController;
    }

    public static void setCurrentBackpack(BackpackFXMLController currentBackpack) {
        BackpackHelper.currentBackpack = currentBackpack;
    }

    //private static BackpackHelper currentBackpack;
    private Stage backpackStage;



   public void  initialize() {
        currentBackpack = MainMenu.backpackFXMLController;
    }

//    public static void setCurrentBackpack(BackpackFXMLController currentBackpack) {
//        BackpackHelper.currentBackpack = currentBackpack;
//    }

    private BackpackHelper() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BackpackScene.fxml"));
            Parent root = loader.load();
            currentBackpack = loader.getController();

            backpackStage = new Stage();
            backpackStage.setTitle("Backpack");
            Scene scene = new Scene(root);
            backpackStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
//
