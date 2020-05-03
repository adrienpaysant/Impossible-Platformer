
package ch.hearc.wp2.p2.jeu;

import java.awt.Dimension;

import ch.hearc.wp2.p2.jeu.menus.MainMenu;

public class Main {
	
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static final int HEIGHT = (int)dimension.getHeight();
	public static final int WIDTH  = (int)dimension.getWidth();
	
	
	public static void main(String[] args) {
		Game game=Game.getInstance();
		game.setContentPane(MainMenu.getInstance());
		game.setVisible(true);
	}

}
