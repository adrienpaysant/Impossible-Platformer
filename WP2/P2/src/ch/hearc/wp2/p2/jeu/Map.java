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

import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.items.Charactere.Player;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.items.blocs.MovingBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.actions.CheckPointBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.SpikeBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TrapBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TypeTrap;
import ch.hearc.wp2.p2.jeu.items.decoration.Cloud;
import ch.hearc.wp2.p2.jeu.menus.LeaderBoard;
import ch.hearc.wp2.p2.jeu.menus.PauseMenu;
import ch.hearc.wp2.p2.jeu.tools.Chrono;
import ch.hearc.wp2.p2.jeu.tools.ChronoMovingBloc;
import ch.hearc.wp2.p2.jeu.tools.ChronoTrap;
import ch.hearc.wp2.p2.jeu.tools.ExitButton;
import ch.hearc.wp2.p2.jeu.tools.Keyboard;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

@SuppressWarnings("serial")
public class Map extends JPanel {

	public static final int BLOC_WH = 50;
	private static final int SUN_WH = 150;
	private static final int CLOUD_WH = 75;
	private static final int SPEED = 3;
	private static final int PLAYER_H = 55;
	private static final int PLAYER_W = 30;
	public static final int GRAVITY = 4;
	private static final int HEART_WH = 25;
	private static final int PLAYER_NB_LIFE = 30;
	private static final boolean DEBUG = false;

	private Game game;
	private ExitButton exitButton;

	private ArrayList<Bloc> listBloc = new ArrayList<Bloc>();
	private ArrayList<CheckPointBloc> listCPBloc = new ArrayList<CheckPointBloc>();
	private ArrayList<Cloud> listCloud = new ArrayList<Cloud>();
	private ArrayList<TrapBloc> listTrap = new ArrayList<TrapBloc>();
	private ArrayList<MovingBloc> listMovingBloc = new ArrayList<MovingBloc>();

	private CheckPointBloc lastCP;
	private CheckPointBloc firstCP;

	private static Map map = null;
	private Player player;

	private double dX;
	private int groundH;
	private boolean win;
	private boolean lastCPset;

	public static Map getInstance() {
		if (map == null) {
			map = new Map();
			map.addKeyListener(new Keyboard());
		}

		return map;
	}

	// constructor
	private Map() {

		this.game = Game.getInstance();
		this.exitButton = new ExitButton("Back To Main Menu", "MainMenu");
		this.player = new Player(getGame().getWidth() / 2, 0, PLAYER_W, PLAYER_H, true,
				PLAYER_NB_LIFE);
		this.dX = 0;
		this.groundH = 2 * getGame().getHeight() / 3;
		this.win = false;
		this.lastCPset = false;
		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				groundH = 2 * getGame().getHeight() / 3;
				if (listBloc.isEmpty() && listCloud.isEmpty())
					setBlocList();
				else {
					listCloud.clear();
					setBlocList();
				}
			}

		});

		new Thread(new Chrono()).start();
		new Thread(new ChronoTrap()).start();
		new Thread(new ChronoMovingBloc()).start();
	}

	// painting
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	private void draw(Graphics2D g2d) {

		if (!win) {
			add(exitButton);
//				// background
//				g2d.setColor(new Color(51, 204, 250));
//				g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
//			
			g2d.drawImage(ShopImage.BACKGROUND, 0, 0, getWidth(), getHeight(), 0, 0,
					ShopImage.BACKGROUND.getWidth(null), ShopImage.BACKGROUND.getHeight(null), null);

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

			updateLastCP();

			// test the victory
			if (lastCPset)
				if (player.getMaxX() >= lastCP.getCenterX()) {
					win = true;
				}

			// player is Alive ?
			if (player.isAlive()) {
				// player
				player.moveByY(GRAVITY);
				//g2d.setColor(Color.black);
				if (player.isVisible()) {
					//g2d.fill(player);
					g2d.drawImage(player.getImage(), (int)player.height, (int)player.width, (int)player.x, (int)player.y, null);
//					g2d.drawImage(player.getImage(), (int)player.height/2, (int)player.width/2, (int)player.x*2, (int)player.y*2, 1, 1,
//							player.getImage().getHeight(null), player.getImage().getWidth(null), null);
					g2d.draw(player);
				}

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
				LeaderBoard.getInstance().setTextLabel("Hum... It's a fail ! You Loose !");
				init();
				getGame().setSize(getGame().getWidth() + 1, getGame().getHeight() + 1);
				getGame().setContentPane(LeaderBoard.getInstance());
				getGame().setSize(getGame().getWidth() - 1, getGame().getHeight() - 1);

			}
		} else {
			LeaderBoard.getInstance().setTextLabel("Congrats  ! You Win  !");
			init();
			getGame().setSize(getGame().getWidth() + 1, getGame().getHeight() + 1);
			getGame().setContentPane(LeaderBoard.getInstance());
			getGame().setSize(getGame().getWidth() - 1, getGame().getHeight() - 1);
		}

	}

	public CheckPointBloc checkLastCP() {
		CheckPointBloc last = new CheckPointBloc(Map.getInstance().getListCPBloc().get(0));
		for (CheckPointBloc cp : Map.getInstance().getListCPBloc()) {
			if (cp.isCheck()) {
				last = new CheckPointBloc(cp);
			}
		}
		return last;
	}

	private void updateLastCP() {
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
		player.setAlive(true);
		win = false;
		setdX(0);
		boolean first = true;
		for (CheckPointBloc cpBloc : listCPBloc) {
			if (first) {
				cpBloc.setCheck(true);
				first = false;
			} else {
				cpBloc.setCheck(false);
			}
		}
		for (TrapBloc trap : listTrap) {
			trap.revertAction();
		}
		player.respawn();

	}

	// creating the map
	private void setBlocList() {

		firstCP = new CheckPointBloc(-BLOC_WH / 4 + BLOC_WH * (player.x / 50 - 1), groundH, BLOC_WH, BLOC_WH, true,
				ShopImage.ICEDIRTBLOCK);
		listBloc.add(firstCP);
		listCPBloc.add(firstCP);
		firstCP.setCheck(true);
		for (int i = 0; i < 2 * Main.WIDTH / 50; i++) {

			int alea = 5 + (int) (Math.random() * ((15 - 5) + 1));
			if (i % 20 != 0) {
				// Bloc

				// path = 1st Layer

				listBloc.add(new Bloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 - 1), groundH, BLOC_WH, BLOC_WH, true,
						ShopImage.PATHBLOCK));// classic
				// bloc

//				// block in the sky
//				if (i % 20 == 4) {
//					listBloc.add(new Bloc(-BLOC_WH / 4 + BLOC_WH * (i + 2 + player.x / 50 - 1),
//							-2.5 * BLOC_WH + groundH, BLOC_WH, BLOC_WH, true, ShopImage.FORESTBLOCK));
//				}

				// checkpoint
				if (i % 20 == 13) {
					CheckPointBloc b2 = new CheckPointBloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 - 1), groundH,
							BLOC_WH, BLOC_WH, true, ShopImage.ICEBLOCKTOP);// CheckPoint
					listBloc.add(b2);
					listCPBloc.add(b2);
				}

			}

			// decoration
			if (i % alea == 1) {// cloud between 151 & 221 on y parameter
				listCloud.add(new Cloud(CLOUD_WH * i, groundH / 4 + alea * 7 - CLOUD_WH / 3 + 6, CLOUD_WH, CLOUD_WH,
						true, ShopImage.CLOUD));
			}

			// trap
//			// spike from bottom
			if (i % 15 == 76) {

				SpikeBloc tBloc = new SpikeBloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 - 1), groundH, BLOC_WH,
						BLOC_WH, false, ShopImage.SPIKEB, false, true, TypeTrap.SPIKEB);
				listBloc.add(tBloc);
				listTrap.add(tBloc);
			}
			// spike from top
			if (i % 11 == 23) {
				SpikeBloc tBloc = new SpikeBloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 + 3),
						-3 * BLOC_WH + groundH, BLOC_WH, BLOC_WH, false, ShopImage.SPIKET, true, true, TypeTrap.SPIKET);

				listBloc.add(tBloc);
				listTrap.add(tBloc);
			}
			// from left
			if (i % 15 == 16) {

				SpikeBloc tBloc = new SpikeBloc(-BLOC_WH / 4 + BLOC_WH * (i + player.x / 50 + 2), -BLOC_WH + groundH,
						BLOC_WH, BLOC_WH, false, ShopImage.SPIKER, false, false, TypeTrap.SPIKER);

				listBloc.add(tBloc);
				listTrap.add(tBloc);
			}

		}

		// last cp
		lastCP = new CheckPointBloc(-BLOC_WH / 4 + BLOC_WH * (2 * Main.WIDTH / 50 + player.x / 50 - 1), groundH,
				BLOC_WH, BLOC_WH, true, ShopImage.SANDBLOCK);
		listBloc.add(lastCP);
		listCPBloc.add(lastCP);
		lastCPset = true;
		player.respawn();

	}

//getters & setters
	public Player getPlayer() {
		return player;
	}

	public int getGroundH() {

		return (int) this.groundH;

	}

	public double getdX() {
		return dX;
	}

	public void setdX(double d) {
		this.dX = d;
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

	public ArrayList<TrapBloc> getListTrap() {

		return listTrap;
	}

	public ArrayList<MovingBloc> getListMovingBloc() {
		return listMovingBloc;
	}


}