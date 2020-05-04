
package ch.hearc.wp2.p2.jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.items.Character.type.Player;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.menus.MainMenu;

@SuppressWarnings("serial")
public class Map extends JPanel {
	private Game game;
	private JButton buttonExit;

	private Bloc[] tabBloc;
	private static Map map = null;
	private Player player;

	public static Map getInstance() {
		if (map == null)
			{
				map = new Map();
				}
		return map;
	}

	private Map() {
		this.game = Game.getInstance();
		this.buttonExit = new JButton("Back to Menu");

		tabBloc = new Bloc[100];
		for (int i = 0; i < tabBloc.length; i++) {
			if (i % 3 == 0)
				{
					tabBloc[i] = new Bloc(10 * i + 50, game.getHeight() / 2, 30, 30, false);
					}
			else if (i % 7 == 0)
				{
					tabBloc[i] = new Bloc(10 * i + 50, -30 + game.getHeight() / 2, 30, 30, true);
					}
			else
				{
					tabBloc[i] = new Bloc(10 * i + 50, game.getHeight() / 2, 30, 30, true);
					}

		}

		player = new Player(game.getHeight() / 2, game.getWidth() / 2 ,50, 37);

		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(MainMenu.getInstance());
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	private void draw(Graphics2D g2d) {
		add(buttonExit);
		// green
		g2d.setColor(new Color(51, 204, 250));
		g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

		// test blocs
		g2d.setColor(Color.green);
		for (int i = 0; i < tabBloc.length; i++) {
			if (tabBloc[i].isVisible())
				{
					g2d.fill(tabBloc[i].getRect());
					}
		}
	}
}
