package com.company;

import javax.swing.*;

public class samotnik{
    private spanel can;
    private statusbar statusBar;
    static private JFrame gameFrame;
    private Window window;


    public void init() {

        createWindow();
        createGUI();
        setVisibileOfFrame();

    }

    private void createGUI() {


        statusBar = new statusbar();
        gameFrame.getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);
        can = new spanel(gameFrame.createImage(500, 500));
        gameFrame.add(can);

        createMenu();
    }



    private void setVisibileOfFrame() {
        window.setVisibleOfFrame();
    }

    private void createWindow() {
        gameFrame = new JFrame();
        gameFrame.addNotify();
        window = new Window(gameFrame);
    }

    private void createMenu() {
        Menu menu = new Menu(can);
        menu.createMenu();
        gameFrame.setJMenuBar(menu.getMenuBar());
    }


}
