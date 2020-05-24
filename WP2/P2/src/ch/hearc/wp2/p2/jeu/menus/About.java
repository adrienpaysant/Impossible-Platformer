package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.tools.position.JCenter;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

@SuppressWarnings("serial")
public class About extends Box {

	private Game game;
	private JButtonMenu buttonExit;
	private Image bgImage;
	private static About about=null;
	public static About getInstance() {
		if(about==null)
			about = new About();
		return about;
	}
	private About() {
		super(BoxLayout.Y_AXIS);
		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.game = Game.getInstance();
		buttonExit = new JButtonMenu("Back to Menu");
		add(new JCenterH(buttonExit));
		add(Box.createVerticalGlue());
		JLabel label = new JLabel("<html>This Project has been created by Adrien Paysant, Joris Monnet and Ugo Crucy<br><br>"
				+ "The purpose is to manage your way through trapped plateforms to reach the end.<br>"
				+ "Your number of death will determine your place on the leader board.<br>"
				+ "Challenge yourself and your friends to be the best player !!<br><br>"
				+ "This Project has been made for a School Project in Java in the HE-arc in Switzerland, in 2020.</html>",SwingConstants.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		add(new JCenter(label));
		add(Box.createVerticalGlue());
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(MainMenu.getInstance());
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// allow resizeEvent by deforming Image to the good width and height
		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), 0, 0, bgImage.getWidth(null), bgImage.getHeight(null),
				null);
	}
}
