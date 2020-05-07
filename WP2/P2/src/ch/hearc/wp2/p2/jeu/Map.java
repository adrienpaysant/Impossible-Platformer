
package ch.hearc.wp2.p2.jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.hearc.wp2.p2.jeu.items.Caractere.Player;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.menus.MainMenu;
import ch.hearc.wp2.p2.jeu.tools.Chrono;
import ch.hearc.wp2.p2.jeu.tools.Keyboard;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

@SuppressWarnings("serial")
public class Map extends JPanel {

	private static final int BLOC_WH = 50;
	private static final int SPEED = 3;

	private Game game;
	private JButton buttonExit;

	private ArrayList<Bloc> listBloc = new ArrayList<Bloc>();

	private static Map map = null;
	private Player player;

	private int dX;
	public double groundH;

	public static Map getInstance() {
		if (map == null) {
			map = new Map();
			map.addKeyListener(new Keyboard());
			// for the focus in map
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					map.requestFocusInWindow();
				}
			});
		}

		return map;
	}

	// constructor
	private Map() {

		this.game = Game.getInstance();
		this.buttonExit = new JButton("Back to Menu");
		this.player = new Player(game.getWidth() / 2, game.getHeight() / 3, 25, 55, true);
		System.out.println("init " + player);
		this.dX = 0;
		this.groundH = 2 * game.getHeight() / 3;

		// listeners
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
			}

			private void setBlocList() {
				int alea = 5 + (int) (Math.random() * ((20 - 5) + 1));
				// initial path
				for (int i = 0; i < game.getWidth() / 50; i++) {
					if (i % alea != 0) {
						// path = 1st Layer
						listBloc.add(new Bloc(BLOC_WH * i, groundH, BLOC_WH, BLOC_WH, true, ShopImage.PATHBLOCK));
						// 2nd Layer
						listBloc.add(
								new Bloc(BLOC_WH * i, BLOC_WH + groundH, BLOC_WH, BLOC_WH, true, ShopImage.DIRTBLOCK));

						// trap test
						if (i % alea == 2)
							listBloc.add(new Bloc(BLOC_WH * i, -BLOC_WH + groundH, BLOC_WH, BLOC_WH, true,
									ShopImage.SPIKES));
					}

				}

			}

		});

		Thread chronoMap = new Thread(new Chrono());
		chronoMap.start();

		// TODO thread pour gérer les collisions ?
	}

	// getters & setters
	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	// painting
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
			if (bloc.isVisible()) {
				g2d.drawImage(bloc.getTexture(), (int) bloc.x, (int) bloc.y, (int) (bloc.width + bloc.x),
						(int) (bloc.height + bloc.y), 0, 0, bloc.getTexture().getWidth(null),
						bloc.getTexture().getHeight(null), null);
			}
		}
		// player
		// TODO TOFIX : wrong method : c'est la map qui doit bouger
		player.moveByX(SPEED * dX);
		g2d.setColor(Color.black);
		if (player.isVisible())
			g2d.draw(player);
	}
}
