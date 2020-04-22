package ch.hearc.wp2.p2.jeu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ch.hearc.wp2.p2.jeu.menus.MainMenu;

public class Main {

	public static Map map;

	public static void main(String[] args) {
		JFrame window = new JFrame("Impossible Platformer");
		window.setAlwaysOnTop(true);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setContentPane(new MainMenu());
		window.setVisible(true);
	}

}
