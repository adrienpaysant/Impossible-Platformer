package ch.hearc.wp2.p2.jeu.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.tools.JCenter;
import ch.hearc.wp2.p2.jeu.tools.JCenterH;

@SuppressWarnings("serial")
public class About extends Box {

	private Game game;
	private JButtonMenu buttonExit;

	public About(Game g) {
		super(BoxLayout.Y_AXIS);
		this.game = g;
		buttonExit = new JButtonMenu("Back to Menu");
		add(new JCenterH(buttonExit));
		add(Box.createVerticalGlue());
		add(new JCenter(new JLabel("I am the About page-P2 HEARC 2020")));
		
		
		buttonExit.addActionListener(new ActionListener() {
			// demander au profs
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				game.resize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(new MainMenu(game));
				game.resize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});
	}
}
