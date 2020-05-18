package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;

import ch.hearc.wp2.p2.jeu.Main;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.tools.Audio;
import ch.hearc.wp2.p2.jeu.tools.Design;
import ch.hearc.wp2.p2.jeu.tools.ExitButton;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

@SuppressWarnings("serial")
public class Options extends Box {
	private static Options options = null;

	public static Options getInstance() {
		if (options == null)
			options = new Options();
		return options;
	}

	private ExitButton exitButton;
	private Image bgImage;
	private String whereIcomeFrom;

	private Options() {
		super(BoxLayout.Y_AXIS);

		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setWhereIcomeFrom("");
		this.exitButton = new ExitButton("Back to Menu", "MainMenu");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	private void draw(Graphics2D g2d) {
		add(new JCenterH(exitButton));
		g2d.drawImage(bgImage, 0, 0, getWidth(), getHeight(), 0, 0, bgImage.getWidth(null), bgImage.getHeight(null),
				null);

		g2d.setFont(new Font("Monospaced", Font.BOLD, 50));
		g2d.setColor(Color.white);
		Design.printSimpleString("Options", Main.WIDTH / 3, Main.WIDTH / 3, Main.WIDTH / 17, g2d);
		g2d.drawLine(0, Main.HEIGHT / 7, getWidth(), Main.HEIGHT / 7);
		g2d.setFont(new Font("Monospaced", Font.ITALIC, 25));

		g2d.drawLine(Main.WIDTH / 3, Main.HEIGHT / 9, 2 * Main.WIDTH / 3, Main.HEIGHT / 9);
	}

	public String getWhereIcomeFrom() {
		return whereIcomeFrom;
	}

	public void setWhereIcomeFrom(String whereIcomeFrom) {
		this.whereIcomeFrom = whereIcomeFrom;
	}
}
