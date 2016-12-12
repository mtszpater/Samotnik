package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * author @pater
 */
class Window {

    static private JFrame gameFrame;

    Window(JFrame gameFrame) {
        Window.gameFrame = gameFrame;
        setWindowSizeProperty();
        addEventOnWindowExit();

    }

    private static void setWindowSizeProperty() {
        gameFrame.setSize(new Dimension(532, 602));
    }

    void setVisibleOfFrame() {
        gameFrame.setVisible(true);
    }

    private static void addEventOnWindowExit() {
        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
