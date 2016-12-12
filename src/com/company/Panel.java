package com.company;

import javax.swing.*;
import java.awt.*;

class Panel extends JPanel {
	
	public Image image;


	public Panel(Image g) {
		addListener();
		image = g;
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
				JOptionPane.showMessageDialog(null, "Samotnik, jest prostą grą logiczną dla jednej osoby.\n" +
						" Pole do gry ma kształt krzyża z jednym pustym polem w środku oraz 32 polami zapełnionymi. \n" +
						"W grze jedynym dozwolonym ruchem jest przeskoczenie pionka innym pionkiem w pionie lub w poziomie,\n" +
						" co powoduje zbicie przeskoczonego pionka.\n" +
						" Celem gry jest pozostawienie na planszy jednego pionka, najlepiej jeśli będzie to pionek w centrum.");
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
		initRender();
		repaint();
	}

	private void setDefaultChecker() {
		Pointer pointer = Pointer.getInstance();
		pointer.setCheckerAsRed();
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