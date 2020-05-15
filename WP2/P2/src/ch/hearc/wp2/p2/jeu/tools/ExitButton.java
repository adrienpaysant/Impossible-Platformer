package ch.hearc.wp2.p2.jeu.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.menus.MainMenu;
import ch.hearc.wp2.p2.jeu.menus.PauseMenu;

public class ExitButton extends JButton {

	public ExitButton(String title, String target) {
		super(title);
		// listeners
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().setSize(Game.getInstance().getWidth() + 1, Game.getInstance().getHeight() + 1);

				switch (target) {
				case "MainMenu":
					Game.getInstance().setContentPane(MainMenu.getInstance());
					break;
				case "PauseMenu":
					Game.getInstance().setContentPane(PauseMenu.getInstance());
					break;

				default:
					Game.getInstance().setContentPane(PauseMenu.getInstance());
					break;
				}

				Game.getInstance().setSize(Game.getInstance().getWidth() - 1, Game.getInstance().getHeight() - 1);
			}
		});
	}

}
