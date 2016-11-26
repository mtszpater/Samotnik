package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class samotnik implements ActionListener, ItemListener{
    public spanel can;
    public statusbar statusBar;
    static public JFrame gameFrame;
    static boolean applet;


    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;


    public void init() {

        createGUI();
    }

    private void createGUI() {


        statusBar = new statusbar();
        gameFrame.getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);
        can = new spanel(gameFrame.createImage(500, 500));
        gameFrame.add(can);

        createMenu();

        gameFrame.setJMenuBar(menuBar);
    }

    private void createMenu() {
        createNavBar();
        createGameBar();
        createMoveBar();
        createSettingBar();
        createHelpBar();
    }

    private void createHelpBar() {
        createNavMenu("Pomoc", KeyEvent.VK_C);
        createMenuItem("O Grze",        Actions.ABOUT_GAME.toString()  ,        KeyEvent.VK_ENTER,  true);
        addSeparator();
        createMenuItem("O Aplikacji",    Actions.ABOUT_APPLICATION.toString()  , KeyEvent.VK_D,  true);
    }

    private void createSettingBar() {

        createNavMenu("Ustawienia", KeyEvent.VK_G);

        ButtonGroup group = new ButtonGroup();

        createRadioButton(group, "Wersja 1", Actions.SET_GAME_1, KeyEvent.VK_R);
        menuItem.setSelected(true);
        createRadioButton(group, "Wersja 2", Actions.SET_GAME_2, KeyEvent.VK_O);

    }

    private void createRadioButton(ButtonGroup group, String text, String setGame1, int vkR) {
        menuItem = new JRadioButtonMenuItem(text);
        menuItem.setActionCommand(setGame1);
        menuItem.setMnemonic(vkR);
        group.add(menuItem);
        menu.add(menuItem);
    }


    private void createMoveBar() {
        createNavMenu("Ruchy"   ,               KeyEvent.VK_G);
        createMenuItem("Wybierz punkt", Actions.CHOOSE_POINT,     KeyEvent.VK_D,  !applet);
        addSeparator();
        createMenuItem("Skocz w gore",  Actions.GO_UP  ,          KeyEvent.VK_D,  !applet);
        createMenuItem("Skocz w dol",   Actions.GO_DOWN   ,       KeyEvent.VK_D,  !applet);
        createMenuItem("Skocz w lewo",  Actions.GO_LEFT ,         KeyEvent.VK_D,  !applet);
        createMenuItem("Skocz w prawo", Actions.GO_RIGHT  ,       KeyEvent.VK_D,  !applet);
    }

    private void createGameBar() {
        createNavMenu("Gra" ,                                        KeyEvent.VK_G);
        createMenuItem("Nowa gra",  Actions.NEW_GAME,      KeyEvent.VK_4, true);
        addSeparator();
        createMenuItem("Koniec",    Actions.END_GAME,      KeyEvent.VK_3, !applet);
        createMenuItem("Zatrzymaj", Actions.PAUSE_GAME,    KeyEvent.VK_3, !applet);
    }

    private void addSeparator() {
        menu.addSeparator();
    }

    private void createMenuItem(String text, String actionCommand, int Key, boolean Enable) {

        menuItem = new JMenuItem(text);
        menuItem.addActionListener(this);
        menuItem.setActionCommand(actionCommand);
        menuItem.setMnemonic(Key);
        if (Enable)
            menu.add(menuItem);

    }

    private void createNavMenu(String Title, int Key) {
        menu = new JMenu(Title);
        menu.setMnemonic(Key);
        menuBar.add(menu);
    }

    private void createNavBar() {
        menuBar = new JMenuBar();
    }

    public static void main(String[] arg) {


        gameFrame = new JFrame();
        gameFrame.addNotify();

        samotnik s = new samotnik();

        setWindowSizeProperty();
        addEventOnWindowExit();

        applet = false;
        s.init();
        setVisibleOfFrame();
    }

    private static void setWindowSizeProperty() {
        gameFrame.setSize(new Dimension(532, 602));
        gameFrame.setMaximumSize(new Dimension(602, 603));
    }

    private static void setVisibleOfFrame() {
        gameFrame.setVisible(true);
    }

    private static void addEventOnWindowExit() {
        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public samotnik() {
        applet = true;
    }


    public void actionPerformed(ActionEvent e) {
        String i = e.getActionCommand();

        System.out.println(e.paramString());
        can.akcja(i);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
