package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;

import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.Game;


@SuppressWarnings("serial")
public class LoosePage extends JPanel {

	private static LoosePage loosePage = null;

	private Game game;

	// singleton
	public static LoosePage getInstance() {
		if (loosePage == null)
			loosePage = new LoosePage();
		return loosePage;
	}

	private LoosePage() {

		this.game = Game.getInstance();

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
}
