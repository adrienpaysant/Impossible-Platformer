
package ch.hearc.wp2.p2.jeu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import ch.hearc.wp2.p2.jeu.menus.MainMenu;

@SuppressWarnings("serial")
public class Game extends JFrame {


	private MainMenu mainMenu;


	public Game(String name) {
		super(name);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(500, 500));

		mainMenu = new MainMenu(this);
		setContentPane(mainMenu);
		setVisible(true);
	}

	public MainMenu getMainMenu() {
		return this.mainMenu;
	}

}
