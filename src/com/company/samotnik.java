package com.company;
import javax.swing.*;




public class Samotnik{

    private static Samotnik instance = null;
    private spanel can;
    static private JFrame gameFrame;
    private Window window;
    public boolean isActive;


    protected Samotnik() {
        isActive = false;
    }

    public static Samotnik getInstance() {
        if(instance == null) {
            instance = new Samotnik();
        }
        return instance;
    }


    public void init() {

        createWindow();
        createGUI();
        setVisibileOfFrame();

    }

    private void createGUI() {
        can = new spanel(gameFrame.createImage(500, 500), this);
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
