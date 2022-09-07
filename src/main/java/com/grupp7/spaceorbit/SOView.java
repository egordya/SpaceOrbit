package com.grupp7.spaceorbit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class SOView {


    private static final int width = 800;
    private static final int height = 600;
    final Image image = new Image(IMAGE_LOC);
    final Image image2 = new Image(IMAGE_KANYE);
    private static final String IMAGE_LOC = "http://icons.iconarchive.com/icons/uiconstock/flat-halloween/128/Halloween-Bat-icon.png";
    private static final String IMAGE_KANYE = System.getProperty("user.dir")
            + File.separatorChar + "src"
            + File.separatorChar + "main"
            + File.separatorChar + "resources"
            + File.separatorChar + "img"
            + File.separatorChar + "kanye.jpg";
    private int kanyeXSpeed = 1;
    private int kanyeYSpeed = 1;
    private double kanyeXPos = width / 2;
    private double kanyeYPos = height / 2;

    public void run(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        //set text
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));
        gc.fillOval(width / 2, height / 2, 15, 15);
        kanyeXPos+=kanyeXSpeed;
        kanyeYPos+=kanyeYSpeed;
        gc.drawImage(image2, kanyeXPos,kanyeYPos);
    }
}
