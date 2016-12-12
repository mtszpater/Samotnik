package com.company;

import com.company.events.ActionManager;
import com.company.events.Actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * author @pater
 */
class Menu  implements ActionListener  {
    
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private Panel panel;
    private ButtonGroup group;



    void createMenu() {
        createNavBar();
        createGameBar();
        createSettingBar();
        createHelpBar();
    }
    
    private void createNavBar() {
        menuBar = new JMenuBar();
    }
    
    private void createGameBar() {
        createNavMenu("Gra");
        createGameButtons();
    }
    
    private void createNavMenu(String Title) {
        menu = new JMenu(Title);
        menuBar.add(menu);
    }

    private void createGameButtons() {
        createMenuItem("Nowa gra",  Actions.NEW_GAME);
        addSeparator();
        createMenuItem("Koniec",    Actions.END_GAME);
        createMenuItem("Zatrzymaj", Actions.PAUSE_GAME);
    }
    private void createMenuItem(String text, String actionCommand) {

        menuItem = new JMenuItem(text);
        menuItem.addActionListener(this);
        menuItem.setActionCommand(actionCommand);
        menu.add(menuItem);

    }
    
    private void addSeparator() {
        menu.addSeparator();
    }

    private void createSettingBar() {

        createNavMenu("Ustawienia");
        createVersionGroup();
        createPawnsColorGroup();
        createBackgroundColorGroup();
    }

    private void createVersionGroup() {
        createNewButtonsGroup();
        createRadioButton(group, "Wersja 1", Actions.SET_GAME_1);
        makeButtonSelected();
        createRadioButton(group, "Wersja 2", Actions.SET_GAME_2);
    }

    private void createRadioButton(ButtonGroup group, String text, String setGame1) {
        menuItem = new JRadioButtonMenuItem(text);
        menuItem.addActionListener(this);
        menuItem.setActionCommand(setGame1);
        group.add(menuItem);
        menu.add(menuItem);
    }

    private void createNewButtonsGroup() {
        group = new ButtonGroup();
    }
    
    private void createPawnsColorGroup() {
        addSeparator();
        createNewButtonsGroup();
        createRadioButton(group, "Czerwone piony", Actions.SET_TO_BLACK);
        makeButtonSelected();
        createRadioButton(group, "Zielone piony", Actions.SET_TO_GREEN);
    }

    private void makeButtonSelected() {
        menuItem.setSelected(true);
    }
    
    private void createBackgroundColorGroup() {
        addSeparator();
        createNewButtonsGroup();
        createRadioButton(group, "Czerwone tło", Actions.SET_BACKGROUND_TO_BLACK);
        makeButtonSelected();
        createRadioButton(group, "Zielone tło", Actions.SET_BACKGROUND_TO_GREEN);
    }
    
    private void createHelpBar() {
        aligningMenuToRight();
        createNavMenu("Pomoc");
        createHelpButtons();
    }

    private void aligningMenuToRight() {
        menuBar.add(Box.createHorizontalGlue());
    }
    
    private void createHelpButtons() {
        createMenuItem("O Grze", Actions.ABOUT_GAME);
        addSeparator();
        createMenuItem("O Aplikacji", Actions.ABOUT_APPLICATION);
    }
    
    Menu(Panel can) {
        this.panel = can;
    }
    
    JMenuBar getMenuBar() {
        return menuBar;
    }
    
    public void actionPerformed(ActionEvent e) {
        String i = e.getActionCommand();
        ActionManager am = new ActionManager(panel);
        am.reaction(i);
    }
}
