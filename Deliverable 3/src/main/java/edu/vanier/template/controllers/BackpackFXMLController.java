package edu.vanier.template.controllers;

import edu.vanier.template.models.Sprite;
import edu.vanier.template.ui.BaseWindow;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static edu.vanier.template.ui.MainMenu.*;

/**
 * @author Sofia Martinez
 */
public class BackpackFXMLController {
    private final static Logger logger = LoggerFactory.getLogger(BackpackFXMLController.class);
    @FXML
    GridPane backpackGridPane;
    @FXML
    ScrollPane backpackScrollPane;
    @FXML
    Pane backpackPane;
    @FXML
    ImageView sodium;
    @FXML
    ImageView electron;
    @FXML
    ImageView proton;

    @FXML
    public Label sodiumLabel;
    @FXML
    public Label electronLabel;
    @FXML
    public Label protonLabel;

    public Label getSodiumLabel() {
        return sodiumLabel;
    }

    public void setSodiumLabel(Label sodiumLabel) {
        this.sodiumLabel = sodiumLabel;
    }

    public Label getElectronLabel() {
        return electronLabel;
    }

    public void setElectronLabel(Label electronLabel) {
        this.electronLabel = electronLabel;
    }

    public Label getProtonLabel() {
        return protonLabel;
    }

    public void setProtonLabel(Label protonLabel) {
        this.protonLabel = protonLabel;
    }

    public Label getOxygenLabel() {
        return oxygenLabel;
    }

    public void setOxygenLabel(Label oxygenLabel) {
        this.oxygenLabel = oxygenLabel;
    }

    public Label getHydrogenLabel() {
        return hydrogenLabel;
    }

    public void setHydrogenLabel(Label hydrogenLabel) {
        this.hydrogenLabel = hydrogenLabel;
    }

    private Label oxygenLabel;
    private Label hydrogenLabel;
    private int currentColumn;
    private int currentRow;
    public Map<String, Integer> itemsCount = new HashMap<>();
    public Map<String, Label> itemLabels = new HashMap<>();

    private int sodiumCounter;
    private int electronCounter;
    private int protonCounter;
    private int oxygenCounter;
    private int hydrogenCounter;
    private int numRows;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * actions performed with the backpack
     */
    @FXML
    public void initialize() {
        logger.info("initializing backpack");
        backpackGridPane.setPrefHeight(BaseWindow.sceneHeight * 0.8);
        backpackGridPane.setPrefWidth(BaseWindow.sceneWidth * 0.3);
        setUpGridPane();
        setUI();
        //setBackground(backpackGridPane);
       // setBackground(backpackPane);

        /* both done and exit will lead to the same next Scene, they should both go to the scene they were
        last on. In case the user presses the backpack by accident, they can exit it without having to take anything
        out of the backpack.
        */
        //doneBtn.setOnAction(this::loadLastScene);
        //exitBtn.setOnAction(this::loadLastScene);
    }

    public void isBackpackEmpty() {
        if (backpackGridPane.getChildren().isEmpty()) {
            setUpGridPane();
        }
    }

    /*public void setUI() {
        setBackground(backpackGridPane);
        setBackground(backpackPane);
    }

     */
    @FXML
    //set up the grid pane first in the table, then add the object then implement the removal
    public void setUpGridPane() {
//        ObservableList<Node> cells = backpackGridPane.getChildren();
//        double width;
//        double height;
//        for (int i = 0; i < cells.size(); i++) {
//            Node cell= cells.get(i);
//            int rowNum = GridPane.getRowIndex(cell);
//            int colNum = GridPane.getColumnIndex(cell);
//
//            width = cell.getBoundsInLocal().getWidth();
//            height = cell.getBoundsInLocal().getHeight();
//        }

        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setPrefWidth(80); // Fixed width
        backpackGridPane.getColumnConstraints().add(colConstraint);
        int columnWidth = backpackGridPane.getColumnConstraints().size();
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPrefHeight(80);
        backpackGridPane.getRowConstraints().add(rowConstraint);
        int rowWidth = backpackGridPane.getRowConstraints().size();

        Image electronImage = new Image(getClass().getResource("/images/Electron.png").toExternalForm());
        electron = new ImageView(electronImage);
        electron.setFitWidth(50);
        electron.setFitHeight(50);
        electron.setId("electron");
        backpackGridPane.add(electron, 0, 0);
        GridPane.setHalignment(electron, HPos.CENTER);
        GridPane.setValignment(electron, VPos.CENTER);
        //electronCounter = 0;
        electronLabel = new Label(" x " + numElectron);
        backpackGridPane.add(electronLabel, 1, 0);
        itemsCount.put("electron", numElectron);
        itemLabels.put("electron", electronLabel);

        Image protonImage = new Image((getClass().getResource("/images/Proton.png")).toExternalForm());
        proton = new ImageView(protonImage);
        proton.setFitWidth(50);
        proton.setFitHeight(50);
        GridPane.setHalignment(proton, HPos.CENTER);
        GridPane.setValignment(proton, VPos.CENTER);
        protonCounter = 0;
        protonLabel = new Label(" x " + 0);
        backpackGridPane.add(protonLabel, 1, 1);
        proton.setId("proton");
        backpackGridPane.add(proton, 0, 1);
        itemsCount.put("proton", numProton);
        itemLabels.put("proton", protonLabel);

        Image sodiumImage = new Image(getClass().getResource("/images/elements/sodium.png").toExternalForm());
        sodium = new ImageView(sodiumImage);
        sodium.setFitHeight(50);
        sodium.setFitWidth(50);
        System.out.println("is backpack null: " + (backpackGridPane == null));
        sodium.setId("sodium");
        backpackGridPane.add(sodium, 0, 2);
        GridPane.setHalignment(sodium, HPos.CENTER);
        GridPane.setValignment(sodium, VPos.CENTER);
        sodiumCounter = 0;
        sodiumLabel = new Label(" x " + 0 );
        backpackGridPane.add(sodiumLabel, 1, 2);
        itemsCount.put("sodium", numSodium);
        itemLabels.put("sodium", sodiumLabel);

        Image oxygenImg = new Image(getClass().getResource("/images/elements/oxygen.png").toExternalForm());
        ImageView oxygen = new ImageView(oxygenImg);
        oxygen.setFitWidth(50);
        oxygen.setFitHeight(50);
        GridPane.setHalignment(oxygen, HPos.CENTER);
        GridPane.setValignment(oxygen, VPos.CENTER);
        oxygenCounter = 0;
        oxygenLabel = new Label(" x " + 0);
        backpackGridPane.add(oxygenLabel, 1, 3);
        oxygen.setId("oxygen");
        backpackGridPane.add(oxygen, 0, 3);
        itemsCount.put("oxygen", numOxygen);
        itemLabels.put("oxygen", oxygenLabel);

        Image hydrogenImage= new Image(getClass().getResource("/images/elements/hydrogen.png").toExternalForm());
        ImageView hydrogen = new ImageView(hydrogenImage);
        hydrogen.setFitWidth(50);
        hydrogen.setFitHeight(50);
        GridPane.setHalignment(hydrogen, HPos.CENTER);
        GridPane.setValignment(hydrogen, VPos.CENTER);
        hydrogenCounter = 0;
        hydrogenLabel = new Label(" x " + 0);
        backpackGridPane.add(hydrogenLabel, 1, 4);
        hydrogen.setId("hydrogen");
        backpackGridPane.add(hydrogen, 0, 4);
        itemsCount.put("hydrogen", numHydrogen);
        itemLabels.put("hydrogen", hydrogenLabel);
        //check how to adjust the font size
//        System.out.println("method is being executed/");

        electron.setOnDragDetected(event -> {
            if(itemsCount.getOrDefault("electron", 0) > 0) {
                Dragboard dragboard = electron.startDragAndDrop(TransferMode.COPY);

                ClipboardContent content = new ClipboardContent();
                content.putString("electron");
                dragboard.setContent(content);

                //showing a ghost image of the item in this case electron being dragged onto the screen
                dragboard.setDragView(electron.snapshot(null, null));
            }
            event.consume();
            System.out.println("drag being detected, mouse clicked");
        });

        electron.setOnDragDone(event -> {
            if(TransferMode.COPY == event.getTransferMode()) {
                Sprite electron = new Sprite("electron", electronImage);
                decreaseCount(electron);
                //commented out lines below are not necessary can be deleted, decrease count is much more efficient and works well
                /*electronCounter--;
                itemsCount.put("electron", electronCounter);

                Label itemLabel = itemLabels.get("electron");
                itemLabel.setText(" x " + itemsCount.get("electron"));
                 */
                System.out.println("drag done");
            }
        });
    }

    //increase the number of the item available when it is collected/collides with the user's character
        public void increaseCount(Sprite sprite) {
            String type = sprite.getType().toLowerCase();

            if (itemsCount.containsKey(type)) {

                int updatedCountOfItem = itemsCount.get(type) + 1;
                switch (type) {
                    case "proton":
                        numProton = updatedCountOfItem;
                        break;
                    case "sodium":
                        numSodium = updatedCountOfItem;
                        break;
                    case "oxygen":
                        numOxygen = updatedCountOfItem;
                        break;
                    case "hydrogen":
                        numHydrogen = updatedCountOfItem;
                }

                itemsCount.put(type, updatedCountOfItem);

                Label itemLabel = itemLabels.get(type);
                itemLabel.setText("x " + updatedCountOfItem);

            }
            System.out.println("method is being executed");
        }

        public void globalInventory() {
            for(Map.Entry<String, Integer> entry : itemsCount.entrySet()) {
                String type = entry.getKey();
                int spriteCount = entry.getValue();

                Label itemLabel = itemLabels.get(type);
                itemLabel.setText("x " + spriteCount);
            }
        }

        public void setUI() {
         setBackground(backpackGridPane);
         setBackground(backpackPane);
        }

        public void addNewItem(Sprite sprite) {
            String type = sprite.getType().toLowerCase();

            int currentNum = itemsCount.get(type);
            currentNum++;
            itemsCount.put(type, currentNum);

            Label itemLabel = itemLabels.get(type);
            itemLabel.setText("x " + currentNum);

        }


        //decreases the number of the type of item available whenever an element in the backpack gets dragg and dropped
        //in the specified drop zone successfully
        public void decreaseCount(Sprite sprite) {
        String type = sprite.getType().toLowerCase();
            int numAvailable = itemsCount.get(type);
            if (numAvailable > 0) {
                numAvailable--;
                itemsCount.put(type.toLowerCase(), numAvailable);

                switch (type) {
                    case "proton":
                        numProton = numAvailable;
                        break;
                    case "sodium":
                        numSodium = numAvailable;
                        break;
                    case "oxygen":
                        numOxygen = numAvailable;
                        break;
                    case "hydrogen":
                        numHydrogen = numAvailable;
                        break;
                    case "electron":
                        numElectron = numAvailable;
                        break;
                }

                //then update the cell
                if(itemLabels.containsKey(type)){
                    itemLabels.get(type).setText("x " + numAvailable);
                }
                logger.info("Number of available" + type + "is now " + numAvailable);
            }
        }


    //changing to a side panel view
    //instead of taking that image deleting it from the backpack, it'll be drag and dropped in the screen and the number of
    //those items will change

    //instead of taking that image deleting it from the , it'll be drag and dropped in the screen and the number of
    //those items will change
    //we could either remove the image or make it invisible
    //to remove the can just remove then sprite since the spring and image are related


}

