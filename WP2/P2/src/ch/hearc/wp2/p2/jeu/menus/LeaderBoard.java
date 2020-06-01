package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ch.hearc.wp2.p2.jeu.Main;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.tools.Audio;
import ch.hearc.wp2.p2.jeu.tools.Design;
import ch.hearc.wp2.p2.jeu.tools.ExitButton;
import ch.hearc.wp2.p2.jeu.tools.JComponents;
import ch.hearc.wp2.p2.jeu.tools.QuickSort;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

@SuppressWarnings("serial")
public class LeaderBoard extends Box {

	private static LeaderBoard leaderBoard = null;
	private Image bgImage;
	private static int TOP = 10;

	private JTextField entry;
	private JButtonMenu buttonEntry;
	private ExitButton exitButton;
	private JLabel label;
	private JLabel leadersLabel;
	private int nbDeath;
	private boolean hasPlayedSound;
	private int worstTop;
	private String name = "";

	// singleton
	public static LeaderBoard getInstance() {
		if (leaderBoard == null)
			leaderBoard = new LeaderBoard();
		return leaderBoard;
	}

	private LeaderBoard() {
		super(BoxLayout.Y_AXIS);
		nbDeath = 0;
		this.buttonEntry = new JButtonMenu("Valider");
		this.entry = new JTextField();
		this.exitButton = new ExitButton("Back to Menu", "MainMenu");
		this.label = new JLabel();
		this.leadersLabel = new JLabel();
		buttonEntry.setVisible(false);
		entry.setVisible(false);
		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JComponents.setWidth(entry, 300);
		JComponents.setHeight(entry, 25);
		add(new JCenterH(exitButton));
		add(Box.createVerticalGlue());
		add(new JCenterH(entry));
		add(new JCenterH(buttonEntry));
		add(Box.createVerticalStrut(15));
		hasPlayedSound = false;
		showRead();

		buttonEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonEntry.setVisible(false);
				entry.setVisible(false);
				name = entry.getText().toString();
				if (nbDeath < worstTop) {
					if (!name.isBlank() && !name.isEmpty()) {
						String newLeader = new String();
						newLeader = name + "," + Integer.toString(nbDeath);
						try {
							write(newLeader);
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}

				}
			}
		});
	}

	public void showRead() {
		try {
			String[][] readRawData = read();
			if (readRawData == null)
				throw new IOException();
			String readData = new String();
			int[] tabNotSorted = new int[10];
			for (int i = 0; i < TOP; i++) {
				tabNotSorted[i] = Integer.parseInt(readRawData[i][1]);
			}
			int[] tabSorted = QuickSort.useSort(tabNotSorted);
			worstTop = tabSorted[TOP - 1];
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
		update(getGraphics());
	}

	public String[][] read() throws IOException {
		try {
			String[][] result = new String[TOP][2];
			int i = 0;
			List<String> list = new ArrayList<String>();
			Path source = Paths.get("ressources/data.csv");
			list = Files.readAllLines(source);
			for (String line : list) {
				if (i < TOP) {
					result[i++] = line.split(",");
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void write(String newLeader) throws IOException {
		Path source = Paths.get("ressources/data.csv");
		Files.deleteIfExists(source);
		Files.createFile(source);
		String []text = this.leadersLabel.getText().split(";");
		String []dataToWrite = new String[TOP];
		String data = new String();
		for(int i=0;i<TOP-1;i++) {
			text[i] = text[i].replaceAll(" : ", ",");
			text[i] = text[i].replaceAll(";", "");
			dataToWrite[i] = text[i]+"\n";
		}
		dataToWrite[TOP-1] = newLeader;
		for(int i=0;i<TOP;i++) {
			data += dataToWrite[i];
		}
		byte[] b = data.getBytes(Charset.forName("UTF-8"));
		Files.write(source, b, StandardOpenOption.WRITE);
		showRead();
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
			if (label.getText() == "win" && !hasPlayedSound && nbDeath >= 0) {
				buttonEntry.setVisible(true);
				entry.setVisible(true);
				Audio.playSound("/audio/win.wav");
				this.hasPlayedSound = true;
				Design.printSimpleString("You win after " + (nbDeath) + " death(s).", Main.WIDTH / 3, Main.WIDTH / 3,
						Main.WIDTH / 13, g2d);
			} else if (label.getText() == "fail" && !hasPlayedSound && nbDeath >= 1) {
				buttonEntry.setVisible(false);
				entry.setVisible(false);
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

	public JTextField getEntry() {
		return entry;
	}
}
