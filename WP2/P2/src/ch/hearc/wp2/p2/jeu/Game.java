
package ch.hearc.wp2.p2.jeu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ch.hearc.wp2.p2.jeu.menus.MainMenu;
import ch.hearc.wp2.p2.jeu.menus.PauseMenu;

@SuppressWarnings("serial")
public class Game extends JFrame {


	private MainMenu mainMenu;
	private PauseMenu pauseMenu;

	public Game(String name) {
		super(name);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
;
		setMinimumSize(new Dimension(3*Main.WIDTH/5,2*Main.HEIGTH/3));
		pauseMenu = new PauseMenu(this);
		mainMenu = new MainMenu(this);
		setContentPane(mainMenu);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public MainMenu getMainMenu() {
		return this.mainMenu;
	}
	public PauseMenu getPauseMenu() {
		return this.pauseMenu;
	}
}
