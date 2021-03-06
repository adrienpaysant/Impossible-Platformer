package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ch.hearc.wp2.p2.jeu.tools.ExitButton;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;
import ch.hearc.wp2.p2.jeu.tools.position.JCenter;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

//page of the project description

@SuppressWarnings("serial")
public class About extends Box {

	private ExitButton buttonExit;
	private static About about = null;

	// singleton
	public static About getInstance() {
		if (about == null)
			about = new About();
		return about;
	}

	private About() {
		super(BoxLayout.Y_AXIS);
		buttonExit = new ExitButton("Back to Menu", "MainMenu");
		add(new JCenterH(buttonExit));
		add(Box.createVerticalGlue());
		JLabel label = new JLabel(
				"<html>This Project has been created by Adrien Paysant, Joris Monnet and Ugo Crucy<br><br>"
						+ "The purpose is to manage your way through trapped plateforms to reach the end.<br>"
						+ "Your number of death will determine your place on the leaderboard.<br>"
						+ "Challenge yourself and your friends to be the best player !!<br><br>"
						+ "This Project has been made for a School Project in Java in the HE-arc in Switzerland, in 2020.</html>",
				SwingConstants.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		add(new JCenter(label));
		add(Box.createVerticalGlue());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// allow resizeEvent by deforming Image to the good width and height
		g.drawImage(ShopImage.MENUBG, 0, 0, getWidth(), getHeight(), 0, 0, ShopImage.MENUBG.getWidth(null),
				ShopImage.MENUBG.getHeight(null), null);
	}
}
