
package ch.hearc.wp2.p2.jeu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ch.hearc.wp2.p2.jeu.menus.MainMenu;

@SuppressWarnings("serial")
public class Game extends JFrame {
	public Game(String name) {
		super(name);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(500, 500));
		setContentPane(new MainMenu());
		setVisible(true);
	}
}
