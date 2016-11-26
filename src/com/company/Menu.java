package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * author @pater
 */
public class Menu  implements ActionListener {

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    spanel can;
    ButtonGroup group;


    public void createMenu( ) {
        createNavBar();
        createGameBar();
        createMoveBar();
        createSettingBar();
        createHelpBar();
    }

    private void createHelpBar() {
        aligningMenuToRight();
        createNavMenu("Pomoc", KeyEvent.VK_C);
        createHelpButtons();
    }

    private void createSettingBar() {

        createNavMenu("Ustawienia", KeyEvent.VK_G);
        createVersionGroup();
        createPawnsColorGroup();
        createBackgroundColorGroup();
    }

    private void createMoveBar() {
        createNavMenu("Ruchy", KeyEvent.VK_G);
        createMoveButtons();
    }
    private void createGameBar() {
        createNavMenu("Gra" , KeyEvent.VK_G);
        createGameButtons();
    }

    private void createHelpButtons() {
        createMenuItem("O Grze", Actions.ABOUT_GAME.toString(), KeyEvent.VK_ENTER);
        addSeparator();
        createMenuItem("O Aplikacji",Actions.ABOUT_APPLICATION.toString(),KeyEvent.VK_D);
    }

    private void createMoveButtons() {
        createMenuItem("Wybierz punkt", Actions.CHOOSE_POINT,     KeyEvent.VK_D);
        addSeparator();
        createMenuItem("Skocz w gore",  Actions.GO_UP  ,          KeyEvent.VK_D);
        createMenuItem("Skocz w dol",   Actions.GO_DOWN   ,       KeyEvent.VK_D);
        createMenuItem("Skocz w lewo",  Actions.GO_LEFT ,         KeyEvent.VK_D);
        createMenuItem("Skocz w prawo", Actions.GO_RIGHT  ,       KeyEvent.VK_D);
    }


    private void createGameButtons() {
        createMenuItem("Nowa gra",  Actions.NEW_GAME,      KeyEvent.VK_4);
        addSeparator();
        createMenuItem("Koniec",    Actions.END_GAME,      KeyEvent.VK_3);
        createMenuItem("Zatrzymaj", Actions.PAUSE_GAME,    KeyEvent.VK_3);
    }

    private void addSeparator() {
        menu.addSeparator();
    }

    private void createMenuItem(String text, String actionCommand, int Key) {

        menuItem = new JMenuItem(text);
        menuItem.addActionListener(this);
        menuItem.setActionCommand(actionCommand);
        menuItem.setMnemonic(Key);
        menu.add(menuItem);

    }

    private void createNavMenu(String Title, int Key) {
        menu = new JMenu(Title);
        menu.setMnemonic(Key);
        menuBar.add(menu);
    }

    private void createRadioButton(ButtonGroup group, String text, String setGame1, int vkR) {
        menuItem = new JRadioButtonMenuItem(text);
        menuItem.addActionListener(this);
        menuItem.setActionCommand(setGame1);
        menuItem.setMnemonic(vkR);
        group.add(menuItem);
        menu.add(menuItem);
    }
    private void aligningMenuToRight() {
        menuBar.add(Box.createHorizontalGlue());
    }

    public void createNavBar() {
        menuBar = new JMenuBar();
    }

    public Menu(spanel can) {
        this.can = can;
    }


    public JMenuBar getMenuBar() {
        return menuBar;
    }

    private void createBackgroundColorGroup() {
        addSeparator();
        createNewButtonsGroup();
        createRadioButton(group, "Czarne tło", Actions.SET_BACKGROUND_TO_BLACK, KeyEvent.VK_R);
        makeButtonSelected();
        createRadioButton(group, "Zielone tło", Actions.SET_BACKGROUND_TO_GREEN, KeyEvent.VK_O);
    }

    private void makeButtonSelected() {
        menuItem.setSelected(true);
    }

    private void createPawnsColorGroup() {
        addSeparator();
        createNewButtonsGroup();
        createRadioButton(group, "Czarne piony", Actions.SET_TO_BLACK, KeyEvent.VK_R);
        makeButtonSelected();
        createRadioButton(group, "Zielone piony", Actions.SET_TO_GREEN, KeyEvent.VK_O);
    }

    private void createVersionGroup() {
        createNewButtonsGroup();
        createRadioButton(group, "Wersja 1", Actions.SET_GAME_1, KeyEvent.VK_R);
        makeButtonSelected();
        createRadioButton(group, "Wersja 2", Actions.SET_GAME_2, KeyEvent.VK_O);
    }

    private void createNewButtonsGroup() {
        group = new ButtonGroup();
    }

    public void actionPerformed(ActionEvent e) {
        String i = e.getActionCommand();
        System.out.println(e.paramString());
        can.akcja(i);
    }
}
