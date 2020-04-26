
package ch.hearc.wp2.p2.jeu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class Game extends JFrame {
	//singleton
	private static Game game=null;
	public static Game getGame() {
		if(game==null)
			game = new Game();
		return game;
	}
	private Game() {
		super("Impossible Platformer");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(3*Main.WIDTH/5,2*Main.HEIGHT/3));
		setLocationRelativeTo(null);
	}
}
