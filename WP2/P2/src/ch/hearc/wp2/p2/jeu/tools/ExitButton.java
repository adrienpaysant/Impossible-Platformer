package ch.hearc.wp2.p2.jeu.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.menus.LeaderBoard;
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
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setContentAreaFilled(false);
		JComponents.setHeight(this, 25);
		JComponents.setWidth(this, 200);
	}

	protected void paintComponent(Graphics g) {
		// round borders
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
		g2d.clip(r2d);

		g2d.setPaint(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		super.paintComponent(g);
	}

}
