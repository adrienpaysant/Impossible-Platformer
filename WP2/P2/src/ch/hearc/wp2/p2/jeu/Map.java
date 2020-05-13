package ch.hearc.wp2.p2.jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.hearc.wp2.p2.jeu.items.Charactere.Player;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.items.blocs.actions.CheckPointBloc;
import ch.hearc.wp2.p2.jeu.items.decoration.Cloud;
import ch.hearc.wp2.p2.jeu.menus.MainMenu;
import ch.hearc.wp2.p2.jeu.tools.Chrono;
import ch.hearc.wp2.p2.jeu.tools.Keyboard;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

@SuppressWarnings("serial")
public class Map extends JPanel {

	public static final int BLOC_WH = 50;
	private static final int SUN_WH = 150;
	private static final int CLOUD_WH = 75;
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
	private ArrayList<CheckPointBloc> listCPBloc = new ArrayList<CheckPointBloc>();
	private ArrayList<Cloud> listCloud = new ArrayList<Cloud>();

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
		this.player = new Player(getGame().getWidth() / 2, getGame().getHeight() / 3, PLAYER_W, PLAYER_H, true,
				PLAYER_NB_LIFE);
		this.dX = 0;
		this.groundH = 2 * getGame().getHeight() / 3;
		this.isPaused = false;

		// listeners
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getGame().setSize(getGame().getWidth() + 1, getGame().getHeight() + 1);
				getGame().setContentPane(MainMenu.getInstance());
				getGame().setSize(getGame().getWidth() - 1, getGame().getHeight() - 1);
			}
		});

		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				groundH = 2 * getGame().getHeight() / 3;
				if (listBloc.isEmpty())
					setBlocList();
				else {
					listBloc.clear();
					setBlocList();
				}
			}

		});

		Thread chronoMap = new Thread(new Chrono());
		chronoMap.start();

		init();
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

			// test & collisions
			for (Bloc bloc : listBloc) {
				player.contact(bloc);
			}

			// test statement player
			if (player.getMaxY() >= getGame().getHeight()) {
				player.setHeart(player.getHeart() - 1);
				player.setJumping(true);
				player.respawn();
			}
			if (player.getHeart() <= 0) {
				player.setAlive(false);
			}

			checkLastCP();

			// player is Alive ?
			if (player.isAlive()) {
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

				// blocs
				g2d.setColor(Color.green);
				for (Bloc bloc : listBloc) {
					// - dX to move with the player
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

				// cloud
				for (Cloud cld : listCloud) {
					// - dX to move with the player
					cld.moveByX(-dX * SPEED);
					if (cld.isVisible()) {
						g2d.drawImage(cld.getTexture(), (int) cld.x, (int) cld.y, (int) (cld.width + cld.x),
								(int) (cld.height + cld.y), 0, 0, cld.getTexture().getWidth(null),
								cld.getTexture().getHeight(null), null);

					}
				}

				// sun
				g2d.drawImage(ShopImage.SUN, game.getWidth() - SUN_WH, 0, game.getWidth(), SUN_WH, 0, 0,
						ShopImage.HEART.getWidth(null), ShopImage.HEART.getHeight(null), null);

			} else {
				System.out.println("youloose");
				init();

				getGame().setSize(getGame().getWidth() + 1, getGame().getHeight() + 1);
				getGame().setContentPane(MainMenu.getInstance());
				getGame().setSize(getGame().getWidth() - 1, getGame().getHeight() - 1);
			}
		}
	}

	private void checkLastCP() {
		// Update of last checkpoint
		for (CheckPointBloc cpBloc : listCPBloc) {
			if (player.getCenterX() >= cpBloc.getCenterX()) {
				cpBloc.setCheck(true);
			}
			if (listCPBloc.indexOf(cpBloc) == 0)
				cpBloc.setCheck(true);
		}
	}

	private void init() {
		player.setHeart(Map.PLAYER_NB_LIFE);
	}

	// creating the map
	private void setBlocList() {

		for (int i = 0; i < 2 * getGame().getWidth() / 50; i++) {
			int alea = 5 + (int) (Math.random() * ((15 - 5) + 1));
			if (i % 20 != 0) {
				// Bloc
				{
					// path = 1st Layer
					if (listBloc.isEmpty()) {
						CheckPointBloc b = new CheckPointBloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 - 1), groundH,
								BLOC_WH, BLOC_WH, true, ShopImage.ICEDIRTBLOCK);// CheckPoint
						listBloc.add(b);
						listCPBloc.add(b);
						b.setCheck(true);
					} else {
						listBloc.add(new Bloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 - 1), groundH, BLOC_WH,
								BLOC_WH, true, ShopImage.PATHBLOCK));// classic
						// bloc
					}
					// block in the sky
					if (i % 20 == 4) {
						listBloc.add(new Bloc(-BLOC_WH / 4 + BLOC_WH * (i + 2 + player.x / 50 - 1),
								-2.5 * BLOC_WH + groundH, BLOC_WH, BLOC_WH, true, ShopImage.SANDBLOCK));
					}

					// block on the ground
					if (i % 20 == 9) {
						listBloc.add(new Bloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 - 1), -BLOC_WH + groundH,
								BLOC_WH, BLOC_WH, true, ShopImage.ICEBLOCK));
					}

					// checkpoint
					if (i % 20 == 13) {
						CheckPointBloc b2 = new CheckPointBloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 - 1),
								groundH, BLOC_WH, BLOC_WH, true, ShopImage.ICEBLOCKTOP);// CheckPoint
						listBloc.add(b2);
						listCPBloc.add(b2);
					}
				}

			}
			// decoration
			{
				if (i % alea == 3) {//cloud between 151 & 221 on y parameter
					listCloud.add(new Cloud(CLOUD_WH*i,groundH/4+alea*7-CLOUD_WH/3+6, CLOUD_WH, CLOUD_WH,
							true, ShopImage.CLOUD));
				}
			}

		}
//end block
//		CheckPointBloc b = new CheckPointBloc(BLOC_WH * (1 + 5 * getGame().getWidth() / 50), groundH, BLOC_WH, BLOC_WH,
//				true, ShopImage.ICEDIRTBLOCK);// CheckPoint
//		listBloc.add(b);
//		listCPBloc.add(b);
	}

//getters & setters
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

	public ArrayList<Bloc> getListBloc() {
		return listBloc;
	}

	public ArrayList<CheckPointBloc> getListCPBloc() {
		return listCPBloc;
	}

	public Game getGame() {
		return game;
	}
}