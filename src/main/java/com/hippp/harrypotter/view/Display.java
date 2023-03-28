package com.hippp.harrypotter.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Display extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Harry Potter");
        Label label = new Label("Hello, Potter");
        Scene scene = new Scene(new Pane(), 800, 600);

        Sprite mySprite = new Sprite(100, 100, 2, 2, "down1.png" );
        Pane root = (Pane) scene.getRoot();

        root.getChildren().add(mySprite);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
