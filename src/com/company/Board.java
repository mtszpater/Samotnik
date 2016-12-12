package com.company;

import com.company.events.Actions;
import com.company.tools.*;
import java.awt.*;

/**
 * author @pater
 */
public class Board {
    
    private int boardType;

    private int[][] Board;

    private static Board ourInstance = new Board();
    
    public static Board getInstance() {
        return ourInstance;
    }

    private Board() {
        createNewBoard(Actions.BRITISH);
    }

    public void createNewBoard(int boardType) {
        Board = new int[20][20];
        this.boardType = boardType;
        fillTable0();
        fillRestOfTable();
    }

    private void fillTable0() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Board[i][j] = 0;
            }
        }
    }
    
    private void fillRestOfTable() {
        standardVersionOfTable();
        if(BoardIsBritish())
            BritishVersionOfTable();
    }

    private void standardVersionOfTable() {
        for (int i = 6; i < 13; i++)
            for (int j = 6; j < 13; j++) {
                Board[i][j] = 2;
                Board[7][6] = 0;
                Board[7][7] = 0;
                Board[7][11] = 0;
                Board[7][12] = 0;
                Board[12][7] = 0;
                Board[12][6] = 0;
                Board[12][12] = 0;
                Board[12][11] = 0;
                Board[11][6] = 0;
                Board[11][7] = 0;
                Board[11][12] = 0;
                Board[11][11] = 0;
                Board[9][9] = 1;
                PointsCounter pointsCounter = PointsCounter.getInstance();
                pointsCounter.setCount(36);
            }
    }

    private boolean BoardIsBritish() {
        return boardType == Actions.BRITISH;
    }
    
    private void BritishVersionOfTable() {
        Board[6][6] = 0;
        Board[6][7] = 0;
        Board[6][11] = 0;
        Board[6][12] = 0;
        PointsCounter pointsCounter = PointsCounter.getInstance();
        pointsCounter.setCount(33);
    }
    
    boolean isBoardEmpty(int i, int j) {
        return Board[i][j] == 0;
    }

    public boolean IsEmptyPoint(int i, int j) {
        return Board[i][j] == 2;
    }

    public boolean isOne(int i, int j) {
        return Board[i][j] == 1;
    }

    public void setPointAsFill(int x, int y) {
        Board[x][y] = 2;
    }

    public void setPointAsVisited(int x, int y, Point currChecked) {
        Board[(x + currChecked.x) / 2][(y + currChecked.y) / 2] = 1;
    }

    public void setPointAsEmpty(Point currChecked) {
        Board[currChecked.x][currChecked.y] = 1;
    }
}
