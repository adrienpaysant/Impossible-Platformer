package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.Main;
import ch.hearc.wp2.p2.jeu.Map;
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

//		try {
//			String tab[] = read();
//			System.out.println(tab);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	public String[] read() throws IOException {

		FileInputStream fis = new FileInputStream(getClass().getResource("data.csv").getFile());
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);

		List<String> listData = new LinkedList<String>();

		try {
			while (true) {
				String value = dis.readLine();
				listData.add(value);
			}
		} catch (EOFException e) {
			// rien
		}

		String[] tabData = new String[listData.size()];
		listData.toArray(tabData); // vide la liste dans le tableau, tableau qui a du etre creer avant

		fis.close();
		bis.close();
		dis.close();

		return tabData;
	}

	public static void write(String[] tab) throws IOException {
		FileOutputStream fos = new FileOutputStream("../ressources/data.csv");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);

		for (String value : tab) {
			dos.writeChars(value);
		}

		dos.close();
		bos.close();
		fos.close();
	}

	public void updateFile() {

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

		g2d.setFont(new Font("Monospaced", Font.BOLD, 50));
		g2d.setColor(Color.white);
		printSimpleString("LeaderBoard", Main.WIDTH / 3, Main.WIDTH / 3, Main.WIDTH / 17, g2d);
		g2d.drawLine(0, Main.HEIGHT / 7, getWidth(), Main.HEIGHT / 7);
		g2d.setFont(new Font("Monospaced", Font.ITALIC, 25));
		if (Map.getInstance().isHasPlay())
			if (label.getText() == "win") {
				printSimpleString("You win after " + (nbDeath) + " death(s).", Main.WIDTH / 3, Main.WIDTH / 3,
						Main.WIDTH / 13, g2d);
			} else if (label.getText() == "fail") {
				System.out.println("fail nbd " + nbDeath);
				printSimpleString("You play, and died " + (nbDeath) + " time(s)...You should train a bit",
						Main.WIDTH / 3, Main.WIDTH / 3, Main.WIDTH / 13, g2d);
			}
		g2d.drawLine(Main.WIDTH / 3, Main.HEIGHT / 9, 2 * Main.WIDTH / 3, Main.HEIGHT / 9);
	}

	/**
	 * Methode to draw a centered text in a 2D graphic context
	 * 
	 * Methode from Kyle Amburn found on :
	 * https://coderanch.com/t/336616/java/Center-Align-text-drawString
	 * 
	 * NOTE : adding Graphics2D parameter to the original methode from K.Amburn.
	 * 
	 * @param s
	 * @param width
	 * @param XPos
	 * @param YPos
	 * @param g2d
	 */
	private void printSimpleString(String s, int width, int XPos, int YPos, Graphics2D g2d) {
		int stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
		int start = width / 2 - stringLen / 2;
		g2d.drawString(s, start + XPos, YPos);
	}

	// getters & setters
	public void setTextLabel(String txt) {
		this.label.setText(txt);
	}

	public void setDeathCount(int nbDeath) {
		this.nbDeath = nbDeath;
	}
}
