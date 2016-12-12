package com.company.events;

import com.company.Board;
import com.company.Panel;
import com.company.gui.*;


import javax.swing.*;

public class ActionManager {

    private final Panel panel;

    public ActionManager(Panel panel) {
        this.panel = panel;
    }

    public void reaction(String i) {
        Background instance = Background.getInstance();
        Pointer pointer = Pointer.getInstance();
        Board board = Board.getInstance();

        switch (i) {
            case Actions.NEW_GAME:
                createNewGame();
                break;
            case Actions.END_GAME:
                System.exit(0);
                break;
            case Actions.ABOUT_GAME:
                aboutGameInformation();
                break;
            case Actions.ABOUT_APPLICATION:
                JOptionPane.showMessageDialog(null, "Mateusz Pater -0.1");
                break;
            case Actions.PAUSE_GAME:
                break;
            case Actions.SET_GAME_1:
                board.createNewBoard(Actions.BRITISH);
                createNewGame();
                break;
            case Actions.SET_GAME_2:
                board.createNewBoard(Actions.NORMAL);
                createNewGame();
                break;
            case Actions.SET_TO_GREEN:
                pointer.setCheckerAsGreen();
                createNewGame();
                break;
            case Actions.SET_TO_BLACK:
                setDefaultChecker();
                createNewGame();
                break;
            case Actions.SET_BACKGROUND_TO_BLACK:
                instance.setDefaultBackground();
                createNewGame();
                break;
            case Actions.SET_BACKGROUND_TO_GREEN:
                instance.setBackgroundAsGreen();
                createNewGame();
                break;
        }
    }

    private void createNewGame() {
        panel.initRender();
        panel.repaint();
    }

    private void setDefaultChecker() {
        Pointer pointer = Pointer.getInstance();
        pointer.setCheckerAsRed();
    }

    private void aboutGameInformation() {
        JOptionPane.showMessageDialog(null, "Samotnik, jest prostą grą logiczną dla jednej osoby.\n" +
                " Pole do gry ma kształt krzyża z jednym pustym polem w środku oraz 32 polami zapełnionymi. \n" +
                "W grze jedynym dozwolonym ruchem jest przeskoczenie pionka innym pionkiem w pionie lub w poziomie,\n" +
                " co powoduje zbicie przeskoczonego pionka.\n" +
                " Celem gry jest pozostawienie na planszy jednego pionka, najlepiej jeśli będzie to pionek w centrum.");
    }
}
