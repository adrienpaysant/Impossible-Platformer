package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;

import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.Game;


@SuppressWarnings("serial")
public class WinPage extends JPanel {

	private static WinPage winPage = null;

	private Game game;

	// singleton
	public static WinPage getInstance() {
		if (winPage == null)
			winPage = new WinPage();
		return winPage;
	}

	private WinPage() {

		this.game = Game.getInstance();

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
}
