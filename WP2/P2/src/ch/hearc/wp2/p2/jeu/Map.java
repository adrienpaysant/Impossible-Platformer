package ch.hearc.wp2.p2.jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.hearc.wp2.p2.jeu.items.Caractere.Player;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.items.blocs.actions.ActionBloc;
import ch.hearc.wp2.p2.jeu.menus.MainMenu;
import ch.hearc.wp2.p2.jeu.tools.Chrono;
import ch.hearc.wp2.p2.jeu.tools.Keyboard;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

@SuppressWarnings("serial")
public class Map extends JPanel {

	public static final int BLOC_WH = 50;
	private static final int SPEED = 3;
	private static final int PLAYER_H = 55;
	private static final int PLAYER_W = 25;
	public static final int GRAVITY = 4;
	private static final int HEART_WH = 25;
	private static final int PLAYER_NB_LIFE = 10;
	private static final boolean DEBUG = false;

	private Game game;
	private JButton buttonExit;

	private ArrayList<Bloc> listBloc = new ArrayList<Bloc>();
	private ArrayList<ActionBloc> listActionBloc = new ArrayList<ActionBloc>();

	private static Map map = null;
	private Player player;

	private int dX;
	private int groundH;
	private boolean isPaused;

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
		this.player = new Player(game.getWidth() / 2, game.getHeight() / 3, PLAYER_W, PLAYER_H, true, PLAYER_NB_LIFE);
		this.dX = 0;
		this.groundH = 2 * game.getHeight() / 3;
		this.isPaused = false;

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
				// one bloc just under the player
				// listBloc.add(new Bloc(player.x-player.width/2, groundH, BLOC_WH, BLOC_WH,
				// true, ShopImage.PATHBLOCK));

				// listBloc.add()
				for (int i = 0; i < game.getWidth() / 50; i++) {
					if (i % alea != 0) {
						// path = 1st Layer
						listBloc.add(new Bloc(BLOC_WH * i, groundH, BLOC_WH, BLOC_WH, true, ShopImage.PATHBLOCK));

						// 2nd Layer
						if (i % alea == 4)
							listBloc.add(new Bloc(BLOC_WH * i + 2 * BLOC_WH, -2.5 * BLOC_WH + groundH, BLOC_WH, BLOC_WH,
									true, ShopImage.SANDBLOCK));

						// trap test
						if (i % alea == 2)
							listBloc.add(new Bloc(BLOC_WH * i, -BLOC_WH + groundH, BLOC_WH, BLOC_WH, true,
									ShopImage.ICEBLOCK));
					}

				}

			}

		});

		Thread chronoMap = new Thread(new Chrono());
		chronoMap.start();
		// TODO

		// TODO thread pour grer les collisions ?
	}

	// getters & setters
	public Player getPlayer() {
		return player;
	}

	public int getGroundH() {

		return (int) this.groundH;

	}

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}
	
	public ArrayList<Bloc> getListBloc(){
		return listBloc;
	}

	// painting
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	private void draw(Graphics2D g2d) {
		if (!isPaused) {
			add(buttonExit);
			// background
			g2d.setColor(new Color(51, 204, 250));
			g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

			// player
			player.moveByY(GRAVITY);
			g2d.setColor(Color.black);
			if (player.isVisible())
				g2d.fill(player);

			// hearts of the player :
			for (int i = 0; i < player.getHeart(); i++) {
				g2d.drawImage(ShopImage.HEART, 5 + i * HEART_WH, 0, 5 + i * HEART_WH + HEART_WH, HEART_WH, 0, 0,
						ShopImage.HEART.getWidth(null), ShopImage.HEART.getHeight(null), null);
			}

			// test & collisions
			for (Bloc bloc : listBloc) {
				player.contact(bloc);
			}

			// test statement player
			if (player.getMaxY() >= game.getHeight()) {
				player.setHeart(player.getHeart() - 1);
				// TODO move to last checkpoint
				player.moveTo(new Point2D.Double(game.getWidth() / 2, game.getHeight() / 3));
				// TODO
			}
			if (player.getHeart() <= 0) {
				player.setAlive(false);
			}

			// player is Alive ?
			if (player.isAlive()) {
				// blocs
				g2d.setColor(Color.green);
				for (Bloc bloc : listBloc) {
					// - dX to move the player

					bloc.moveByX(-dX * SPEED);

					if (DEBUG) {
						// debug mode
						g2d.drawRect((int) bloc.x, (int) bloc.y, (int) bloc.width, (int) bloc.height);
					} else {
						if (bloc.isVisible()) {
							g2d.drawImage(bloc.getTexture(), (int) bloc.x, (int) bloc.y, (int) (bloc.width + bloc.x),
									(int) (bloc.height + bloc.y), 0, 0, bloc.getTexture().getWidth(null),
									bloc.getTexture().getHeight(null), null);
						}
					}
				}

			} else {
				System.out.println("youloose");
				// TODO
				player.setHeart(PLAYER_NB_LIFE);
				// TODO TOFIX pb pour relancer une partie (fonction init() ??)
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(MainMenu.getInstance());
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		}
	}
}
