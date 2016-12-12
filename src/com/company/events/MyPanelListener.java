package com.company.events;

import com.company.Board;
import com.company.Panel;
import com.company.gui.Background;
import com.company.gui.Pointer;
import com.company.tools.PointsCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * author @pater
 */
public class MyPanelListener implements MouseListener {
    private Panel panel;
    private Point lastChecked, currChecked;
    private boolean Checked = false;
    
    public MyPanelListener(Panel panel) {
        this.panel = panel;
        generateCurrentAndLastCheckedPoints();
    }

    private void generateCurrentAndLastCheckedPoints() {
        lastChecked = new Point(-1, -1);
        currChecked = new Point(-1, -1);
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) {

        int width = panel.getSize().width;
        int height = panel.getSize().height;

        int min = returnSmaller(width, height);
        int x = generateXPositionOnClick(arg0, width, height, min);
        int y = generateYPositionOnClick(arg0, width, height, min);

        x = x / 25;
        y = y / 25;

        Board board = Board.getInstance();

        if (InRange(x, y)) {
            if (!Checked) {
                if (board.IsEmptyPoint(x,y)) {
                    lastChecked.x = currChecked.x;
                    lastChecked.y = currChecked.y;
                    currChecked.x = x;
                    currChecked.y = y;
                    Checked = true;
                    if (lastCheckedisNotCurrChecked()) {
                        moveSelection();
                        panel.repaint();
                    }
                }
            } else {
                if ((CurrCheckedXIsInRangeOf2(x) && y == currChecked.y && board.IsEmptyPoint(x + currChecked.x/ 2, y) && board.isOne(x,y))
                        || (CurrCheckedYIsInRangeOf2(y) && x == currChecked.x && board.IsEmptyPoint(x, y + currChecked.y / 2) && board.isOne(x,y))) {
                    setPointAsEmpty();
                    Checked = false;
                    decreaseCount();
                    PointsCounter pointsCounter = PointsCounter.getInstance();
                    JOptionPane.showMessageDialog(null, "Pozostalo pionkow "+ pointsCounter.getCount());
                    moveChecker(new Point(x, y));
                    setNewPointAsFill(x, y);

                    panel.repaint();

                    currChecked.x = -1;

                    if (EndOfGame()) {
                        EndOfGameInformation();
                    }
                } else {
                    Checked = false;
                    mouseClicked(arg0);
                }
            }
        }
    }
    
    private int returnSmaller(int width, int height) {
        return width>height?height:width;
    }
    
    private int generateXPositionOnClick(MouseEvent arg0, int width, int height, int min) {
        return (arg0.getX()-(width>height?(width-height)/2:0)) * 500 / min;
    }
    
    private int generateYPositionOnClick(MouseEvent arg0, int width, int height, int min) {
        return (arg0.getY()-(ifXisBiggerThanHeightReturn0ElseReturnHalf(height, width))) * 500 / min;
    }

    private boolean InRange(int x, int y) {
        return x >= 0 && x < 20 && y >= 0 && y < 20;
    }
    
    private void moveChecker(Point n) {
        addBackgroundToEmptyPoint(currChecked);
        Background background = Background.getInstance();
        panel.image.getGraphics().drawImage(background.getBoardBackGround(), (currChecked.x + n.x) / 2 * 25, (currChecked.y + n.y) / 2 * 25, null);
        addCheckerAtNewPoint(n);
    }


    private void moveSelection() {
        if (lastChecked.x != -1) {
            addBackgroundToEmptyPoint(lastChecked);
            addCheckerAtNewPoint(lastChecked);
        }
    }

    private void setNewPointAsFill(int x, int y) {
        Board board = Board.getInstance();
        
        board.setPointAsFill(x,y);
        board.setPointAsVisited(x,y,currChecked);
    }

    private void decreaseCount() {
        PointsCounter pointsCounter = PointsCounter.getInstance();
        pointsCounter.decrease();
    }

    private void setPointAsEmpty() {
        Board board = Board.getInstance();
        
        board.setPointAsEmpty(currChecked);
    }

    private boolean CurrCheckedYIsInRangeOf2(int y) {
        return (y == currChecked.y + 2) || (y == currChecked.y - 2);
    }

    private boolean CurrCheckedXIsInRangeOf2(int x) {
        return (x == currChecked.x + 2) || (x == currChecked.x - 2);
    }

    private boolean lastCheckedisNotCurrChecked() {
        return lastChecked.x != currChecked.x || lastChecked.y != currChecked.y;
    }

    private void EndOfGameInformation() {
        JOptionPane.showMessageDialog(null, "Gratulajce! Wygrales!");
        ActionManager am = new ActionManager(panel);
        am.reaction(Actions.END_GAME);
    }

    private boolean EndOfGame() {
        return PointsCounter.getInstance().getCount() == 1;
    }


    private int ifXisBiggerThanHeightReturn0ElseReturnHalf(int height, int width) {
        return width>height?0:(height-width)/2;
    }
    

    private boolean addCheckerAtNewPoint(Point n) {
        Pointer pointer = Pointer.getInstance();
        
        return panel.image.getGraphics().drawImage(pointer.getCurrentChecker(), n.x * 25, n.y * 25, null);
    }

    private void addBackgroundToEmptyPoint(Point last) {
        Background background = Background.getInstance();
        
        panel.image.getGraphics().drawImage(background.getBoardBackGround(), last.x * 25, last.y * 25, null);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
