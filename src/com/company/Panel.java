package com.company;

import com.company.events.MyPanelListener;
import com.company.gui.Background;
import com.company.gui.Pointer;

import javax.swing.*;
import java.awt.*;


public class Panel extends JPanel {
	
	public Image image;

	Panel(Image g) {
		image = g;
		addListener();
		initRender();
	}
	
	private void addListener() {
		addMouseListener(new MyPanelListener(this));
	}

	public void initRender() {
		image.getGraphics().clearRect(0, 0, 530, 601);
		paintGrid();
		paintCheckers();
	}

	private void paintGrid() {
		Background background = Background.getInstance();
		Board board = Board.getInstance();
		
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				if (board.isBoardEmpty(i,j))
					image.getGraphics().drawImage(background.getBackground(), i * 25, j * 25, i * 25+ 25, j * 25+ 25, i * 25, j * 25, i * 25+ 25, j * 25+ 25, null);
				else
					image.getGraphics().drawImage(background.getBoardBackGround(), i * 25, j * 25, null);
			}
	}

	private void paintCheckers() {
		Pointer pointer = Pointer.getInstance();
		Board board = Board.getInstance();
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (board.IsEmptyPoint(i,j)) {
					image.getGraphics().drawImage(pointer.getCurrentChecker(), i * 25, j * 25, null);
				}
			}
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int height = this.getSize().height;
		int width = this.getSize().width;
		int x = returnSmaller(width, height);
		g.drawImage(image, width>height?(width-height)/2:0, ifXisBiggerThanHeightReturn0ElseReturnHalf(height, width), x, x, null);
	}
	
	private int ifXisBiggerThanHeightReturn0ElseReturnHalf(int height, int width) {
		return width>height?0:(height-width)/2;
	}
	
	private int returnSmaller(int width, int height) {
		return width>height?height:width;
	}
	

}