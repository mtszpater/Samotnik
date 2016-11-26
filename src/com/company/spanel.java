package com.company;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class spanel extends JPanel implements MouseListener {
	private int Board[][];
	private  BufferedImage Checker, CheckerRed,CheckerGreen, BoardBackGround,Background, Background_green, Background_red;
	private Image image;
	private int CheckersCount = 0;
	private Point lastChecked, currChecked;
	private boolean Checked = false;
	private int BoardType;
	private Color currentColor;

	public spanel(Image g) {
		BoardType = 0;

		generateCurrentAndLastCheckedPoints();
		createBoard();
		createImagesForCheckers();
		addMouseListener(this);
		if (image == null) image = g;

		initRender();
	}

	private void createBoard() {
		Board = new int[20][20];
		fillTable0();
		fillRestOfTable();
	}


	public void update(Graphics g) {
		paint(g);
	}


	private void paintGrid() {
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++)
				if (Board[i][j] == 0)
					image.getGraphics().drawImage(Background, i * 25, j * 25, i * 25+ 25, j * 25+ 25, i * 25, j * 25, i * 25+ 25, j * 25+ 25, null);
				else
					image.getGraphics().drawImage(BoardBackGround, i * 25, j * 25, null);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int height = this.getSize().height;
		int width = this.getSize().width;
		int x = returnSmaller(width, height);
		g.drawImage(image, width>height?(width-height)/2:0, ifXisBiggerThanHeightReturn0ElseReturnHalf(height, width), x, x, null);
	}


	public void initRender() {
		image.getGraphics().clearRect(0, 0, 530, 601);
		paintGrid();
		paintCheckers();
	}

	private void paintCheckers() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (isEmptyPoint(Board[i][j])) {
					image.getGraphics().drawImage(Checker, i * 25, j * 25, null);
				}
			}
		}

	}

	private void moveChecker(Point n) {
		addBackgroundToEmptyPoint(currChecked);
		image.getGraphics().drawImage(BoardBackGround, (currChecked.x + n.x) / 2 * 25, (currChecked.y + n.y) / 2 * 25, null);
		addCheckerAtNewPoint(n);
	}


	private void moveSelection() {
		if (lastChecked.x != -1) {
			addBackgroundToEmptyPoint(lastChecked);
			addCheckerAtNewPoint(lastChecked);
		}
		setColorOfBorder();
	}

	public void mouseClicked(MouseEvent arg0) {

		int width = this.getSize().width;
		int height = this.getSize().height;

		int min = returnSmaller(width, height);
		int x = generateXPositionOnClick(arg0, width, height, min);
		int y = generateYPositionOnClick(arg0, width, height, min);

		x = x / 25;
		y = y / 25;


		if (InRange(x, y)) {
			if (!Checked) {
				if (isEmptyPoint(Board[x][y])) {
					lastChecked.x = currChecked.x;
					lastChecked.y = currChecked.y;
					currChecked.x = x;
					currChecked.y = y;
					Checked = true;
					if (lastCheckedisNotCurrChecked()) {
						moveSelection();
						repaint();
					}
				}
			} else {
				if ((CurrCheckedXIsInRangeOf2(x) && y == currChecked.y && isEmptyPoint(Board[(x + currChecked.x) / 2][y]) && Board[x][y] == 1)
						|| (CurrCheckedYIsInRangeOf2(y) && x == currChecked.x && isEmptyPoint(Board[x][(y + currChecked.y) / 2]) && Board[x][y] == 1)) {
					setPointAsEmpty();
					Checked = false;
					decreaseCount();
					JOptionPane.showMessageDialog(null, "Pozostalo pionkow "+ CheckersCount);
					moveChecker(new Point(x, y));
					setNewPointAsFill(x, y);

					repaint();

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

	private void createImagesForCheckers() {
		try {
			CheckerRed = ImageIO.read(getClass().getResource("red.png"));
			CheckerGreen = ImageIO.read(getClass().getResource("green.png"));
			setDefaultChecker();
			BoardBackGround = ImageIO.read(getClass().getResource("backbackground.jpg"));
			Background_green = ImageIO.read(getClass().getResource("green.jpg"));
			Background_red = ImageIO.read(getClass().getResource("red.jpg"));
			setDefaultBackground();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setDefaultBackground() {
		Background = Background_red;
	}

	private void setDefaultChecker() {
		currentColor = Color.red;
		Checker = CheckerRed;
	}

	private void fillRestOfTable() {

		standardVersionOfTable();
		if(BoardIsBritish())
			BritishVersionOfTable();

	}

	private void BritishVersionOfTable() {
		Board[6][6] = 0;
		Board[6][7] = 0;
		Board[6][11] = 0;
		Board[6][12] = 0;
		CheckersCount = 33;
	}

	private void standardVersionOfTable() {
		for (int i = 6; i < 13; i++) {
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
				CheckersCount = 36;
			}
		}


	}


	private void fillTable0() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				Board[i][j] = 0;
			}
		}
	}



	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e){
	}

	public void mouseReleased(MouseEvent e){

	}



	public void akcja(String i) {
		switch (i) {
			case Actions.NEW_GAME:
				createNewGame();
				break;
			case Actions.END_GAME:
				System.exit(0);
				break;
			case Actions.ABOUT_GAME:
				JOptionPane.showMessageDialog(null, "pomoc");
				break;
			case Actions.ABOUT_APPLICATION:
				JOptionPane.showMessageDialog(null, "Mateusz Pater -0.1");
				break;
			case Actions.GO_DOWN:
				break;
			case Actions.GO_UP:
				break;
			case Actions.GO_LEFT:
				break;
			case Actions.GO_RIGHT:
				break;
			case Actions.CHOOSE_POINT:
				break;
			case Actions.PAUSE_GAME:
				break;
			case Actions.SET_GAME_1:
				JOptionPane.showMessageDialog(null, "1");
				BoardType = 0;
				akcja(Actions.NEW_GAME);
				break;
			case Actions.SET_GAME_2:
				JOptionPane.showMessageDialog(null, "2");
				BoardType = 1;
				akcja(Actions.NEW_GAME);
				break;

			case Actions.SET_TO_GREEN:
				setCheckerAsGreen();
				createNewGame();
				break;
			case Actions.SET_TO_BLACK:
				setDefaultChecker();
				createNewGame();
				break;
			case Actions.SET_BACKGROUND_TO_BLACK:
				setDefaultBackground();
				createNewGame();
				break;
			case Actions.SET_BACKGROUND_TO_GREEN:
				setBackgroundAsGreen();
				createNewGame();
				break;
//		case 10:
//			Checker = CheckerRainbow;
//			initRender();
//			repaint();
//			break;
		}
	}

	private void setBackgroundAsGreen() {
		Background = Background_green;
	}

	private void setCheckerAsGreen() {
		Checker = CheckerGreen;
		currentColor = Color.green;
	}


	private void createNewGame() {
		createBoard();
		initRender();
		repaint();
	}


	private void setNewPointAsFill(int x, int y) {
		Board[x][y] = 2;
		Board[(x + currChecked.x) / 2][(y + currChecked.y) / 2] = 1;
	}


	private boolean BoardIsBritish() {
		return BoardType == 0;
	}

	private void decreaseCount() {
		CheckersCount--;
	}

	private void setPointAsEmpty() {
		Board[currChecked.x][currChecked.y] = 1;
	}

	private boolean CurrCheckedYIsInRangeOf2(int y) {
		return (y == currChecked.y + 2) || (y == currChecked.y - 2);
	}

	private boolean CurrCheckedXIsInRangeOf2(int x) {
		return (x == currChecked.x + 2) || (x == currChecked.x - 2);
	}

	private boolean isEmptyPoint(int i) {
		return i == 2;
	}

	private boolean lastCheckedisNotCurrChecked() {
		return lastChecked.x != currChecked.x || lastChecked.y != currChecked.y;
	}

	private void EndOfGameInformation() {
		JOptionPane.showMessageDialog(null, "Gratulajce! Wygra?e?!");
		akcja(Actions.ABOUT_GAME);
	}

	private boolean EndOfGame() {
		return CheckersCount == 1;
	}

	private int generateYPositionOnClick(MouseEvent arg0, int width, int height, int min) {
		return (arg0.getY()-(ifXisBiggerThanHeightReturn0ElseReturnHalf(height, width))) * 500 / min;
	}

	private int ifXisBiggerThanHeightReturn0ElseReturnHalf(int height, int width) {
		return width>height?0:(height-width)/2;
	}

	private int generateXPositionOnClick(MouseEvent arg0, int width, int height, int min) {
		return (arg0.getX()-(width>height?(width-height)/2:0)) * 500 / min;
	}

	private boolean InRange(int x, int y) {
		return x >= 0 && x < 20 && y >= 0 && y < 20;
	}

	private int returnSmaller(int width, int height) {
		return width>height?height:width;
	}

	private void generateCurrentAndLastCheckedPoints() {
		lastChecked = new Point(-1, -1);
		currChecked = new Point(-1, -1);
	}

	private boolean addCheckerAtNewPoint(Point n) {
		return image.getGraphics().drawImage(Checker, n.x * 25, n.y * 25, null);
	}

	private void addBackgroundToEmptyPoint(Point last) {
		image.getGraphics().drawImage(BoardBackGround, last.x * 25, last.y * 25, null);
	}

	private void setColorOfBorder() {
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setStroke(new BasicStroke(1));
		g2d.setPaint(currentColor);
		g2d.drawRect(currChecked.x * 25+ 1, currChecked.y * 25+ 1, 22, 22);
	}

}
