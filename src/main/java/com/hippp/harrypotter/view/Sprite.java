package com.hippp.harrypotter.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.InputStream;

public class Sprite extends Pane {
    private Image image;
    private double x, y; // position of the sprite
    private double dx, dy; // velocity of the sprite

    public Sprite(double x, double y, double dx, double dy, String imagePath) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream resourceStream = classLoader.getResourceAsStream(imagePath);
        this.image = new Image(resourceStream);
    }


    @Override
    protected void layoutChildren() {
        setTranslateX(x);
        setTranslateY(y);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        getChildren().add(imageView);
    }
}
