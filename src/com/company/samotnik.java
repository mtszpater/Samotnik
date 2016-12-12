package com.company;
import javax.swing.*;




public class Samotnik{

    private static Samotnik instance = null;
    private Panel panel;
    static private JFrame gameFrame;
    private Window window;


    protected Samotnik() {
    }

    static Samotnik getInstance() {
        if(instance == null) {
            instance = new Samotnik();
        }
        return instance;
    }


    void init() {
        createWindow();
        createGUI();
        setVisibileOfFrame();

    }
    
    private void createWindow() {
        gameFrame = new JFrame();
        gameFrame.addNotify();
        window = new Window(gameFrame);
    }
    
    private void createGUI() {
        panel = new Panel(gameFrame.createImage(500, 500));
        gameFrame.add(panel);
        createMenu();
    }

    private void createMenu() {
        Menu menu = new Menu(panel);
        menu.createMenu();
        gameFrame.setJMenuBar(menu.getMenuBar());
    }
    
    private void setVisibileOfFrame() {
        window.setVisibleOfFrame();
    }

}
