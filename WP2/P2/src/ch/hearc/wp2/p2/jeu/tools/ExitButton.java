package ch.hearc.wp2.p2.jeu.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.menus.JButtonMenu;
import ch.hearc.wp2.p2.jeu.menus.MainMenu;
import ch.hearc.wp2.p2.jeu.menus.PauseMenu;

@SuppressWarnings("serial")
public class ExitButton extends JButtonMenu {

	public ExitButton(String title, String target) {
		super(title);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().setSize(Game.getInstance().getWidth() + 1, Game.getInstance().getHeight() + 1);

				switch (target) {
				case "MainMenu":
					Game.getInstance().setContentPane(MainMenu.getInstance());
					MainMenu.getInstance().getvC().getvS().setValue(PauseMenu.getInstance().getvC().getvS().getValue());
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
