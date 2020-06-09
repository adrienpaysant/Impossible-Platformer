
package ch.hearc.wp2.p2.jeu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

//the JFrame for the game
@SuppressWarnings("serial")
public class Game extends JFrame {
	// singleton
	private String current;
	private static Game game = null;

	public static Game getInstance() {
		if (game == null)
			game = new Game();
		return game;
	}

	private Game() {
		super("Impossible Platformer");
		setAlwaysOnTop(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setFocusable(true);
		setIconImage(ShopImage.LOGO);
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}
}
