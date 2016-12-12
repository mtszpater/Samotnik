package com.company.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * author @pater
 */
public class Pointer {
    private BufferedImage CheckerRed;
    private BufferedImage CheckerGreen;
    private BufferedImage currentChecker;

    private static Pointer ourInstance = new Pointer();

    public static Pointer getInstance() {
        return ourInstance;
    }

    private Pointer() {
        importImages();
        currentChecker = CheckerRed;
    }

    private void importImages() {
        try {
            CheckerRed = ImageIO.read(getClass().getResource("../../../resources/red.png"));
            CheckerGreen = ImageIO.read(getClass().getResource("../../../resources/green.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getCurrentChecker() {
        return currentChecker;
    }

    public void setCheckerAsGreen() {
        this.currentChecker = CheckerGreen;
    }

    public void setCheckerAsRed() {
        this.currentChecker = CheckerRed;
    }
    
}
