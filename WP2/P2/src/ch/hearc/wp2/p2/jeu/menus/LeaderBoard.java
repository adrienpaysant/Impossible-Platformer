package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.tools.ExitButton;
import ch.hearc.wp2.p2.jeu.tools.position.JCenter;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

@SuppressWarnings("serial")
public class LeaderBoard extends JPanel {

	private static LeaderBoard leaderBoard = null;
	private Image bgImage;

	private Game game;
	private ExitButton exitButton;
	private JLabel label;
	private int nbDeath;
	private ArrayList<String> listData = new ArrayList<String>();

	// singleton
	public static LeaderBoard getInstance() {
		if (leaderBoard == null)
			leaderBoard = new LeaderBoard();
		return leaderBoard;
	}

	private LeaderBoard() {
		this.game = Game.getInstance();
		nbDeath = 0;
		this.exitButton = new ExitButton("Back to Menu", "MainMenu");
		this.label = new JLabel();
		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		add(new JCenterH(exitButton));
		add(new JCenter(label));

		readFile();
		updateFile();

	}

	public void readFile() {

	}

	public void updateFile() {

	}

	public void addToList() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	private void draw(Graphics2D g2d) {

		g2d.drawImage(bgImage, 0, 0, getWidth(), getHeight(), 0, 0, bgImage.getWidth(null), bgImage.getHeight(null),
				null);

	}

	// getters & setters
	public void setTextLabel(String txt) {
		this.label.setText(txt);
	}

	public void setDeathCount(int nbDeath) {
		this.nbDeath = nbDeath;
	}
}
