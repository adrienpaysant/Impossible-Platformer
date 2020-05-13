package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;

import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.Game;


@SuppressWarnings("serial")
public class LeaderBoard extends JPanel {

	private static LeaderBoard leaderBoard = null;

	private Game game;

	// singleton
	public static LeaderBoard getInstance() {
		if (leaderBoard == null)
			leaderBoard = new LeaderBoard();
		return leaderBoard;
	}

	private LeaderBoard() {

		this.game = Game.getInstance();

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
}
