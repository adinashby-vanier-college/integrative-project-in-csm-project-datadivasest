package edu.vanier.template.helpers;

import edu.vanier.template.controllers.BackpackFXMLController;

public class BackpackHelper {
    public static BackpackFXMLController currentBackpack;
    public static BackpackFXMLController getCurrentBackpack() {
        return currentBackpack;
    }

    public static void setCurrentBackpack(BackpackFXMLController currentBackpack) {
        BackpackHelper.currentBackpack = currentBackpack;
    }
}
//
