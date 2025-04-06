package edu.vanier.template.controllers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuestionLevelOne extends Application {

    public static void main(String[] arg){
        launch(arg);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Label questionLabel = new Label("What's 1+1");
        Label resultLabel = new Label();
        TextField answerText = new TextField();
        Button checkButton = new Button("Check Answer");
        Button helpButton = new Button("Help");

        checkButton.setOnAction(event -> {
            Double inputAnswer = Double.parseDouble(answerText.getText());
            if (inputAnswer == 2){
                resultLabel.setText("Good Job!");
            }
            else {
                resultLabel.setText("That's not quite right, try again!");
            }
        });

        HBox buttons = new HBox(helpButton, checkButton);
        VBox elements = new VBox(questionLabel, answerText, resultLabel, buttons);
        Scene scene = new Scene(elements);
        primaryStage.setScene(scene);


    }




}
