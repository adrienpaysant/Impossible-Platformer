
package ch.hearc.wp2.p2.jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.menus.MainMenu;

@SuppressWarnings("serial")
public class Map extends JPanel {

	private Game game;
	private JButton buttonExit;

	private ArrayList<Bloc> listBloc = new ArrayList<Bloc>();

	private static Map map = null;

	private Rectangle2D.Double player;

	public static Map getInstance() {
		if (map == null)
			map = new Map();
		return map;
	}

	private Map() {
		this.game = Game.getInstance();
		this.buttonExit = new JButton("Back to Menu");
		player = new Rectangle2D.Double(game.getWidth() / 2, game.getHeight() / 3, 25, 55);

		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(MainMenu.getInstance());
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});

		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				if (listBloc.isEmpty())
					setBlocList();
				else {
					listBloc.clear();
					setBlocList();
				}
				repaint();
			}

			private void setBlocList() {
				for (int i = 0; i < game.getWidth() / 50 - 2; i++)
					listBloc.add(new Bloc(50 * i + 50, game.getHeight() / 2, 50, 30, true));
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
		// background
		g2d.setColor(new Color(51, 204, 250));
		g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

		// test blocs
		g2d.setColor(Color.green);
		for (Bloc bloc : listBloc) {
			if (bloc.isVisible())
				g2d.fill(bloc.getRect());
		}

		// player
		g2d.setColor(Color.black);
		g2d.fill(player);
	}
}
