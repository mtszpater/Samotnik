package com.company.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * author @pater
 */
public class Background {
    private static Background ourInstance = new Background();

    private Image Background;
    private BufferedImage Background_green;
    private BufferedImage Background_red;
    private Image BoardBackGround;
    
    public static Background getInstance() {
        return ourInstance;
    }

    private Background() {
        createImageForBackground();
    }


    private void createImageForBackground() {
        try {
            Background_green = ImageIO.read(getClass().getResource("../../../resources/green.jpg"));
            Background_red = ImageIO.read(getClass().getResource("../../../resources/red.jpg"));
            BoardBackGround = ImageIO.read(getClass().getResource("../../../resources/backbackground.jpg"));
            setDefaultBackground();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultBackground() {
        Background = Background_red;
    }

    public void setBackgroundAsGreen() {
        Background = Background_green;
    }

    public Image getBackground() {
        return Background;
    }

    public Image getBoardBackGround() {
        return BoardBackGround;
    }
}
