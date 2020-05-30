package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.Main;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.tools.Audio;
import ch.hearc.wp2.p2.jeu.tools.Design;
import ch.hearc.wp2.p2.jeu.tools.ExitButton;
import ch.hearc.wp2.p2.jeu.tools.QuickSort;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

@SuppressWarnings("serial")
public class LeaderBoard extends JPanel {

	private static LeaderBoard leaderBoard = null;
	private Image bgImage;
	private static int TOP = 10;

	private ExitButton exitButton;
	private JLabel label;
	private JLabel leadersLabel;
	private int nbDeath;
	private boolean hasPlayedSound;

	// singleton
	public static LeaderBoard getInstance() {
		if (leaderBoard == null)
			leaderBoard = new LeaderBoard();
		return leaderBoard;
	}

	private LeaderBoard() {
		nbDeath = 0;
		this.exitButton = new ExitButton("Back to Menu", "MainMenu");
		this.label = new JLabel();
		this.leadersLabel = new JLabel();
		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(new JCenterH(exitButton));
		hasPlayedSound = false;
		try {
			String readData = new String();
			String[][] readRawData = read();
			int[] tabNotSorted = new int[10];
			for (int i = 0; i < TOP; i++)
				tabNotSorted[i] = Integer.parseInt(readRawData[i][1]);
			int[] tabSorted = QuickSort.useSort(tabNotSorted);
			for (int i = 0; i < TOP; i++) {
				for (int j = 0; j < TOP; j++) {
					if (Integer.parseInt(readRawData[j][1]) == tabSorted[i] && readRawData[j][1] != "-1") {
						readData += readRawData[j][0] + " : " + readRawData[j][1] + ";";
						readRawData[j][1] = "-1";
					}
				}
			}
			leadersLabel.setText(readData);
			leadersLabel.setFont(new Font("Serif", Font.BOLD, 40));
			leadersLabel.setForeground(Color.WHITE);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String[][] read() throws IOException {
		File leaderBoard = new File(getClass().getResource("/data.csv").getFile());
		if (!leaderBoard.isFile()) {
			throw new IOException();
		}
		BufferedReader reader = new BufferedReader(new FileReader(leaderBoard));
		String[][] result = new String[TOP][];
		for (int i = 0; i < TOP; i++) {
			result[i] = reader.readLine().split(",");
		}
		reader.close();
		return result;
	}

	public void write(String[][] tab) throws IOException {
		FileWriter writer = new FileWriter(getClass().getResource("/data.csv").getFile());
		for (int i = 0; i < TOP; i++) {
			writer.append(String.join(",", tab[i]) + "\n");
		}
		writer.flush();
		writer.close();
	}

//drawing
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
		Design.printSimpleString("LeaderBoard", Main.WIDTH / 3, Main.WIDTH / 3, Main.WIDTH / 17, g2d);
		g2d.drawLine(0, Main.HEIGHT / 7, getWidth(), Main.HEIGHT / 7);
		g2d.setFont(new Font("Monospaced", Font.ITALIC, 25));
		if (Map.getInstance().isHasPlay())
			if (label.getText() == "win" && !hasPlayedSound && nbDeath >= 1) {
				hasPlayedSound = true;
				Audio.playSound("/audio/win.wav");
				Design.printSimpleString("You win after " + (nbDeath) + " death(s).", Main.WIDTH / 3, Main.WIDTH / 3,
						Main.WIDTH / 13, g2d);
			} else if (label.getText() == "fail" && !hasPlayedSound && nbDeath >= 1) {
				Audio.playSound("/audio/fail.wav");
				hasPlayedSound = true;
				System.out.println("fail nbd " + nbDeath);
				Design.printSimpleString("You play, and died " + (nbDeath) + " time(s)...You should train a bit",
						Main.WIDTH / 3, Main.WIDTH / 3, Main.WIDTH / 13, g2d);
			}
		g2d.drawLine(Main.WIDTH / 3, Main.HEIGHT / 9, 2 * Main.WIDTH / 3, Main.HEIGHT / 9);
		String[] results = leadersLabel.getText().split(";");
		for (int i = 0; i < results.length; i++)
			Design.printSimpleString(results[i], Main.WIDTH / 3, Main.WIDTH / 3, Main.HEIGHT / 3 + Main.HEIGHT / 20 * i,
					g2d);
	}

	// getters & setters
	public void setTextLabel(String txt) {
		this.label.setText(txt);
	}

	public JLabel getTextLabel() {
		return this.label;
	}

	public void setHasPlayedSound(boolean b) {
		this.hasPlayedSound = b;
	}

	public void setDeathCount(int nbDeath) {
		this.nbDeath = nbDeath;
	}

}
